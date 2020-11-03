/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.diagram.custom.refactoring;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.EMFModelUpdater;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Romain Bioteau
 */
public class ProcessNamingTools {

    private EditingDomain editingDomain;
    private DiagramRepositoryStore diagramStore;

    public ProcessNamingTools(EditingDomain editingDomain) {
        this.editingDomain = editingDomain;
        this.diagramStore = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
    }

    protected Expression createStringExpression(String value) {
        Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setName(value);
        exp.setContent(value);
        exp.setReturnType(String.class.getName());
        exp.setType(ExpressionConstants.CONSTANT_TYPE);
        exp.setReturnTypeFixed(true);
        return exp;
    }

    public void changeProcessNameAndVersion(final AbstractProcess process, final String name, final String version) {
        proceedForPools(process, name, process.getName(), process.getVersion(), version);
        if (process instanceof MainProcess) {
            editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, process,
                    ProcessPackage.Literals.ABSTRACT_PROCESS__AUTHOR, System.getProperty("user.name", "Unknown")));
        }
    }

    public void proceedForPools(final Element pool, final String newPoolName, final String oldPoolName,
            final String oldVersion, final String newVersion) {
        List<AbstractProcess> processes = diagramStore.getAllProcesses();
        StringBuilder activitiesToUpdateMsg = new StringBuilder();
        final Set<CallActivity> callActivityToUpdate = new HashSet<>();
        final Set<Message> messagesToUpdate = new HashSet<>();
        for (AbstractProcess process : processes) {
            callActivityToUpdate.addAll(searchCallActivitiesReferences(oldPoolName, oldVersion, activitiesToUpdateMsg, process));
            messagesToUpdate.addAll(searchMessagesReferences(oldPoolName, process));
        }

        CompoundCommand cc = new CompoundCommand("Rename pool");
        cc.append(SetCommand.create(editingDomain, pool, ProcessPackage.eINSTANCE.getElement_Name(), newPoolName));
        cc.append(SetCommand.create(editingDomain, pool, ProcessPackage.eINSTANCE.getAbstractProcess_Version(), newVersion));
        editingDomain.getCommandStack().execute(cc);

        if (!messagesToUpdate.isEmpty() || (!callActivityToUpdate.isEmpty()
                && MessageDialog.openQuestion(Display.getDefault().getActiveShell(), Messages.updateReferencesTitle,
                        Messages.bind(Messages.updateReferencesMsg, activitiesToUpdateMsg.toString())))) {
            IProgressService service = PlatformUI.getWorkbench().getProgressService();
            try {
                service.run(true, false, new IRunnableWithProgress() {

                    @Override
                    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                        monitor.beginTask(Messages.updatingReferences, IProgressMonitor.UNKNOWN);
                        for (CallActivity sub : callActivityToUpdate) {
                            String subprocessName = null;
                            if (sub.getCalledActivityName() != null
                                    && sub.getCalledActivityName().getContent() != null) {
                                subprocessName = sub.getCalledActivityName().getContent();
                            }
                            String subprocessVersion = null;
                            if (sub.getCalledActivityVersion() != null
                                    && sub.getCalledActivityVersion().getContent() != null) {
                                subprocessVersion = sub.getCalledActivityVersion().getContent();
                            }
                            if (subprocessName != null) {

                                CompoundCommand cc = new CompoundCommand("Update pool references");
                                CallActivity toModify = sub;
                                TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(toModify);
                                boolean loadResource = domain == null;
                                Resource res = null;
                                if (loadResource) {
                                    URI uri = EcoreUtil.getURI(sub);
                                    domain = WorkspaceEditingDomainFactory.INSTANCE.createEditingDomain();
                                    res = domain.getResourceSet().createResource(uri);

                                    try {
                                        res.load(Collections.emptyMap());
                                    } catch (IOException e) {
                                        BonitaStudioLog.error(e);
                                    }

                                    MainProcess mainProc = (MainProcess) res.getContents().get(0);
                                    for (EObject subproc : ModelHelper.getAllItemsOfType(mainProc,
                                            ProcessPackage.Literals.CALL_ACTIVITY)) {
                                        if (ModelHelper.getEObjectID(subproc).equals(EcoreUtil.getURI(sub).fragment())) {
                                            toModify = (CallActivity) subproc;
                                            break;
                                        }
                                    }
                                }

                                if (!subprocessName.equals(NamingUtils.convertToId(newPoolName))) {
                                    Expression calledActivityNameExpression = toModify.getCalledActivityName();
                                    EMFModelUpdater<EObject> updater = new EMFModelUpdater<>()
                                            .from(calledActivityNameExpression);
                                    updater.editWorkingCopy(createStringExpression(newPoolName));
                                    cc.append(updater.createUpdateCommand(domain));
                                }
                                if (subprocessVersion != null && newVersion != null && !(newVersion.equals(oldVersion))) {
                                    Expression calledActivityVersionExpression = toModify.getCalledActivityVersion();
                                    EMFModelUpdater<EObject> updater = new EMFModelUpdater<>()
                                            .from(calledActivityVersionExpression);
                                    updater.editWorkingCopy(createStringExpression(newVersion));
                                    cc.append(updater.createUpdateCommand(domain));
                                }
                                domain.getCommandStack().execute(cc);

                                if (loadResource && res != null) {
                                    try {
                                        res.save(Collections.emptyMap());
                                    } catch (IOException e) {
                                        BonitaStudioLog.error(e);
                                    }
                                    domain.dispose();
                                }
                            }
                        }
                        
                        for(Message event : messagesToUpdate) {
                            CompoundCommand cc = new CompoundCommand("Update process references in messages");
                            Message toModify = event;
                            TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(event);
                            boolean loadResource = domain == null;
                            Resource res = null;
                            if (loadResource) {
                                URI uri = EcoreUtil.getURI(event);
                                domain = WorkspaceEditingDomainFactory.INSTANCE.createEditingDomain();
                                res = domain.getResourceSet().createResource(uri);
                                try {
                                    res.load(Collections.emptyMap());
                                } catch (IOException e) {
                                    BonitaStudioLog.error(e);
                                }

                                MainProcess mainProc = (MainProcess) res.getContents().get(0);
                                for (EObject message : ModelHelper.getAllItemsOfType(mainProc,
                                        ProcessPackage.Literals.MESSAGE)) {
                                    if (ModelHelper.getEObjectID(message).equals(EcoreUtil.getURI(event).fragment())) {
                                        toModify = (Message) message;
                                        break;
                                    }
                                }
                            }
                            Expression targetProcessExpression = toModify.getTargetProcessExpression();
                            EMFModelUpdater<EObject> updater = new EMFModelUpdater<>()
                                    .from(targetProcessExpression);
                            updater.editWorkingCopy(createStringExpression(newPoolName));
                            cc.append(updater.createUpdateCommand(domain));
                            domain.getCommandStack().execute(cc);
                            if (loadResource && res != null) {
                                try {
                                    res.save(Collections.emptyMap());
                                } catch (IOException e) {
                                    BonitaStudioLog.error(e);
                                }
                                domain.dispose();
                            }
                        }
                    }
                });
            } catch (InvocationTargetException e) {
                BonitaStudioLog.error(e);
            } catch (InterruptedException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    private Set<CallActivity> searchCallActivitiesReferences(final String oldPoolName, final String oldVersion,
            StringBuilder activitiesToUpdateMsg,AbstractProcess process) {
        List<CallActivity> callActivities = ModelHelper.getAllItemsOfType(process,
                ProcessPackage.Literals.CALL_ACTIVITY);
        Set<CallActivity> result = new HashSet<CallActivity>();
        for (CallActivity sub : callActivities) {
            String subprocessName = null;
            if (sub.getCalledActivityName() != null
                    && sub.getCalledActivityName().getContent() != null) {
                subprocessName = sub.getCalledActivityName().getContent();
            }
            String subprocessVersion = null;
            if (sub.getCalledActivityVersion() != null
                    && sub.getCalledActivityVersion().getContent() != null) {
                subprocessVersion = sub.getCalledActivityVersion().getContent();
            }

            if (subprocessName != null && subprocessName.equals(oldPoolName) //same pool name
                    && ((subprocessVersion == null || subprocessVersion.isEmpty() /* ie Latest */)
                            || subprocessVersion.equals(oldVersion))) { //same version or Latest
                activitiesToUpdateMsg.append(sub.getName() + " (" + process.getName() + ")");
                activitiesToUpdateMsg.append(SWT.CR);
                result.add(sub);
            }
        }
        return result;
    }
    
    private Set<Message> searchMessagesReferences(final String oldPoolName, AbstractProcess process) {
        return ModelHelper.getAllItemsOfType(process, ProcessPackage.Literals.MESSAGE).stream()
                .map(Message.class::cast)
                .filter(event -> event.getTargetProcessExpression() != null)
                .filter(event -> event.getTargetProcessExpression().hasContent())
                .filter(event -> ExpressionConstants.CONSTANT_TYPE.equals(event.getTargetProcessExpression().getType()))
                .filter(event -> Objects.equals(event.getTargetProcessExpression().getContent(), oldPoolName))
                .collect(Collectors.toSet());
    }
}
