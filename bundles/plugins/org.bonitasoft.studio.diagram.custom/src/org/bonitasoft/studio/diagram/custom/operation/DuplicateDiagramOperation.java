/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.custom.operation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.common.ConfigurationIdProvider;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.diagram.dialog.ProcessesNameVersion;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.WebTemplatesUtil;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.ImageWidget;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.AssociatedFile;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ResourceFile;
import org.bonitasoft.studio.model.process.ResourceFolder;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * @author Romain Bioteau
 */
public class DuplicateDiagramOperation implements IRunnableWithProgress {

    private MainProcess diagram;
    private String diagramVersion;
    private String diagramName;
    private List<ProcessesNameVersion> pools = new ArrayList<ProcessesNameVersion>();

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        Assert.isNotNull(diagram);
        Assert.isNotNull(diagramVersion);
        Assert.isNotNull(diagramName);
        monitor.beginTask(Messages.duplicatingDiagram, IProgressMonitor.UNKNOWN);

        final String oldName = diagram.getName();
        final String oldVersion = diagram.getVersion();

        final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        DiagramFileStore newFildeStore = null;
        if (!(oldName.equals(diagramName) && oldVersion.equals(diagramVersion))) {
            newFildeStore = copyDiagram();
        }
        if (newFildeStore == null) {
            newFildeStore = diagramStore.createRepositoryFileStore(NamingUtils.toDiagramFilename(diagramName, diagramVersion));
        }
        final MainProcess newDiagram = newFildeStore.getContent();
        final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(newFildeStore.getEMFResource());
        editingDomain.getCommandStack().execute(
                SetCommand.create(editingDomain, newDiagram, ProcessPackage.Literals.ABSTRACT_PROCESS__AUTHOR,
                        System.getProperty("user.name", "Unknown")));

        boolean poolRenamed = false;
        for (final ProcessesNameVersion pnv : pools) {
            final AbstractProcess fromPool = pnv.getAbstractProcess();
            final String fromPoolName = fromPool.getName();
            final String fromPoolVersion = fromPool.getVersion();
            /* Find corresponding element in the duplicated model */
            for (final Element element : newDiagram.getElements()) {
                if (element instanceof AbstractProcess) {
                    if (element.getName().equals(fromPoolName)
                            && ((AbstractProcess) element).getVersion().equals(fromPoolVersion)) {
                        if (!pnv.getNewName().equals(fromPoolName) || !pnv.getNewVersion().equals(fromPoolVersion)) {
                            changeProcessNameAndVersion((AbstractProcess) element, editingDomain, pnv.getNewName(), pnv.getNewVersion());
                            poolRenamed = true;
                            break;
                        }
                    }
                }
            }

        }
        if (poolRenamed) {
            newFildeStore.save(null);
        }
    }

    private DiagramFileStore copyDiagram() {
        final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);

        final Copier copier = new Copier(true, false);
        final Collection<EObject> copiedElements = copier.copyAll(diagram.eResource().getContents());
        copier.copyReferences();//don't forget this line otherwise we loose link between diagrams and model
        final DiagramFileStore store = diagramStore.createRepositoryFileStore(NamingUtils.toDiagramFilename(diagramName, diagramVersion));
        store.save(copiedElements);

        final MainProcess newDiagram = store.getContent();
        final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(newDiagram.eResource());
        changeProcessNameAndVersion(newDiagram, editingDomain, diagramName, diagramVersion);
        editingDomain.getCommandStack().execute(
                SetCommand.create(editingDomain, newDiagram, ProcessPackage.Literals.MAIN_PROCESS__CONFIG_ID, ConfigurationIdProvider
                        .getConfigurationIdProvider().getConfigurationId(newDiagram)));
        try {
            OperationHistoryFactory.getOperationHistory().execute(
                    new AbstractTransactionalCommand(editingDomain, "Duplicate", Collections.EMPTY_LIST) {

                        @Override
                        protected CommandResult doExecuteWithResult(final IProgressMonitor arg0, final IAdaptable arg1) throws ExecutionException {
                            try {
                                changePathAndCopyResources(diagram, newDiagram, editingDomain, copier);
                            } catch (final IOException e) {
                                BonitaStudioLog.error(e);
                                return CommandResult.newErrorCommandResult(e);
                            } catch (final CoreException e) {
                                BonitaStudioLog.error(e);
                                return CommandResult.newErrorCommandResult(e);
                            }
                            return CommandResult.newOKCommandResult();
                        }

                        @Override
                        public boolean canUndo() {
                            return false;
                        }

                    }, null, null);
        } catch (final ExecutionException e1) {
            BonitaStudioLog.error(e1);
        }
        duplicateConfigurations(diagram, newDiagram);
        return store;
    }

    private void duplicateConfigurations(final MainProcess sourceDiagram, final MainProcess newDiagram) {
        final ProcessConfigurationRepositoryStore confStore = RepositoryManager.getInstance().getRepositoryStore(
                ProcessConfigurationRepositoryStore.class);
        final List<Pool> pools = ModelHelper.getAllItemsOfType(sourceDiagram, ProcessPackage.Literals.POOL);
        for (final Pool p : pools) {
            final ProcessConfigurationFileStore file = confStore.getChild(ModelHelper.getEObjectID(p) + "." + ProcessConfigurationRepositoryStore.CONF_EXT);
            if (file != null) {
                final Copier copier = new Copier(true, false);
                final Collection<EObject> copiedElements = copier.copyAll(file.getContent().eResource().getContents());
                copier.copyReferences();
                if (!copiedElements.isEmpty()) {
                    final int index = sourceDiagram.getElements().indexOf(p);
                    final Pool newPool = (Pool) newDiagram.getElements().get(index);
                    final ProcessConfigurationFileStore newFile = confStore.createRepositoryFileStore(ModelHelper.getEObjectID(newPool) + "."
                            + ProcessConfigurationRepositoryStore.CONF_EXT);
                    newFile.save(copiedElements.iterator().next());
                }
            }
        }

    }

    /**
     * All elements referenced via ResourceFolder or ResourceFile need to have path updated and artifact duplicated after a duplication of a process.
     *
     * @param oldProcess
     * @param newProcess
     * @param createEditingDomain
     * @param copier
     * @throws IOException
     * @throws CoreException
     */
    private void changePathAndCopyResources(final MainProcess oldProcess, final MainProcess newProcess,
            final TransactionalEditingDomain createEditingDomain, final Copier copier) throws IOException, CoreException {

        final ApplicationResourceRepositoryStore resourceStore = RepositoryManager.getInstance().getRepositoryStore(
                ApplicationResourceRepositoryStore.class);
        for (final AbstractProcess oldProc : ModelHelper.getAllProcesses(oldProcess)) {
            AbstractProcess newProc = null;
            for (final AbstractProcess process : ModelHelper.getAllProcesses(newProcess)) {
                if (oldProc.getName().equals(process.getName())
                        && oldProc.getVersion().equals(process.getVersion())) {
                    newProc = process;
                    break;
                }
            }
            final String newProcId = ModelHelper.getEObjectID(newProc);
            /* Duplicate Resource Folders */
            newProc.getResourceFolders().clear();
            for (final ResourceFolder rf : oldProc.getResourceFolders()) {
                final File resourceFolder = WebTemplatesUtil.getFile(rf.getPath());
                if (resourceFolder != null) {
                    WebTemplatesUtil.putResourcesInProcessTemplate(resourceFolder.getAbsolutePath(), null, createEditingDomain, newProc);
                }
            }
            /* Duplicate Resources Files */
            newProc.getResourceFiles().clear();
            for (final ResourceFile rf : oldProc.getResourceFiles()) {
                final File resourceFile = WebTemplatesUtil.getFile(rf.getPath());
                if (resourceFile != null) {
                    WebTemplatesUtil.putResourcesInProcessTemplate(resourceFile.getAbsolutePath(), null, createEditingDomain, newProc);
                }
            }

            /* Duplicate Confirmation Template */
            final List<PageFlow> pageFlows = ModelHelper.getAllItemsOfType(newProc, ProcessPackage.Literals.PAGE_FLOW);
            for (final PageFlow pageFlow : pageFlows) {
                final AssociatedFile confirmationTemplate = pageFlow.getConfirmationTemplate();
                if (confirmationTemplate != null) {
                    final File confirmationFile = WebTemplatesUtil.getFile(confirmationTemplate.getPath());
                    if (confirmationFile != null) {
                        final ApplicationResourceFileStore artifact = getApplicationResourceFileStore(
                                resourceStore, newProcId);
                        final String confTemplateRelative = artifact.setConfirmationTemplate(confirmationFile.getAbsolutePath(), pageFlow);
                        confirmationTemplate.setPath(confTemplateRelative);
                    } else {
                        pageFlow.setConfirmationTemplate(null);
                    }
                }
            }

            /* Duplicate globalConsultation Template */
            final AssociatedFile globalConsultationTemplate = newProc.getConsultationTemplate();
            if (globalConsultationTemplate != null) {
                final File globalConsultationFile = WebTemplatesUtil.getFile(globalConsultationTemplate.getPath());
                if (globalConsultationFile != null) {
                    final ApplicationResourceFileStore artifact = getApplicationResourceFileStore(
                            resourceStore, newProcId);
                    final String globalConsultationTemplateRelative = artifact.setGlobalConsultationPage(globalConsultationFile.getAbsolutePath());
                    globalConsultationTemplate.setPath(globalConsultationTemplateRelative);
                } else {
                    newProc.setConsultationTemplate(null);
                }
            }

            /* Duplicate Login Page */
            final AssociatedFile loginTemplate = newProc.getLogInPage();
            if (loginTemplate != null) {
                final File loginFile = WebTemplatesUtil.getFile(loginTemplate.getPath());
                if (loginFile != null) {
                    final ApplicationResourceFileStore artifact = getApplicationResourceFileStore(
                            resourceStore, newProcId);
                    final String loginPageRelative = artifact.setLoginPage(loginFile.getAbsolutePath());
                    loginTemplate.setPath(loginPageRelative);
                } else {
                    newProc.setLogInPage(null);
                }
            }

            /* Duplicate Page Template */
            final AssociatedFile pageTemplate = newProc.getPageTemplate();
            if (pageTemplate != null) {
                final File pageFile = WebTemplatesUtil.getFile(pageTemplate.getPath());
                if (pageFile != null) {
                    final ApplicationResourceFileStore artifact = getApplicationResourceFileStore(
                            resourceStore, newProcId);
                    final String pageRelative = artifact.setGlobalPageTemplate(pageFile.getAbsolutePath());
                    pageTemplate.setPath(pageRelative);
                } else {
                    newProc.setPageTemplate(null);
                }
            }

            /* Duplicate Process Page Template */
            final AssociatedFile processTemplate = newProc.getProcessTemplate();
            if (processTemplate != null) {
                final File processTemplateFile = WebTemplatesUtil.getFile(processTemplate.getPath());
                if (processTemplateFile != null) {
                    final ApplicationResourceFileStore artifact = getApplicationResourceFileStore(
                            resourceStore, newProcId);
                    final String processTemplateRelative = artifact.setProcessTemplate(processTemplateFile.getAbsolutePath());
                    processTemplate.setPath(processTemplateRelative);
                } else {
                    newProc.setProcessTemplate(null);
                }
            }

            /* Duplicate Welcome Page Template */
            final AssociatedFile welcomePage = newProc.getWelcomePage();
            if (welcomePage != null) {
                final File welcomePageFile = WebTemplatesUtil.getFile(welcomePage.getPath());
                if (welcomePageFile != null) {
                    final ApplicationResourceFileStore artifact = getApplicationResourceFileStore(
                            resourceStore, newProcId);
                    final String welcomePageRelative = artifact.setWelcomePage(welcomePageFile.getAbsolutePath());
                    welcomePage.setPath(welcomePageRelative);
                } else {
                    newProc.setWelcomePage(null);
                }
            }

            /* Duplicate Image Path that are on ImageWidget */
            for (final Form form : newProc.getForm()) {
                for (final Widget w : form.getWidgets()) {
                    if (w instanceof ImageWidget) {
                        final Expression imgPath2 = ((ImageWidget) w).getImgPath();
                        if (imgPath2 != null) {
                            final String imgPath = imgPath2.getContent();
                            if (imgPath != null) {
                                final File imageFile = WebTemplatesUtil.getFile(imgPath);
                                if (imageFile != null && imageFile.exists()) {
                                    WebTemplatesUtil.putResourcesInProcessTemplate(imageFile.getAbsolutePath(), null, createEditingDomain, newProc);
                                }
                            }
                        }
                    }
                }
            }

            updateFormCustomTemplate(copier, resourceStore, oldProc, newProc);
        }
    }

    protected void updateFormCustomTemplate(final Copier copier, final ApplicationResourceRepositoryStore resourceStore, final AbstractProcess oldProc,
            final AbstractProcess newProc)
            throws CoreException, IOException {
        final List<Form> allForms = ModelHelper.getAllItemsOfType(newProc, FormPackage.Literals.FORM);
        for (final Form form : allForms) {
            final AssociatedFile htmlTemplate = form.getHtmlTemplate();
            if (htmlTemplate != null && htmlTemplate.getPath() != null) {
                //copy template
                final String path = htmlTemplate.getPath();
                final String newFormId = ModelHelper.getEObjectID(form);
                String originalFormId = null;
                for (final java.util.Map.Entry<EObject, EObject> entry : copier.entrySet()) {
                    if (form.equals(entry.getValue())) {
                        originalFormId = ModelHelper.getEObjectID(entry.getKey());
                        break;
                    }
                }
                if (originalFormId == null) {
                    throw new RuntimeException("No original object found for " + newFormId);
                }
                final IFile originalFile = resourceStore.getResource().getFile(path);
                if (originalFile.exists()) {
                    final String newProcId = ModelHelper.getEObjectID(newProc);
                    final String newPath = path.replace(ModelHelper.getEObjectID(oldProc), newProcId).replace(originalFormId, newFormId);
                    final IFile newFile = resourceStore.getResource().getFile(newPath);
                    originalFile.copy(newFile.getFullPath(), true, Repository.NULL_PROGRESS_MONITOR);
                    htmlTemplate.setPath(newPath);
                    replaceIdInFile(newFile, copier);
                }
            }
        }
    }

    private void replaceIdInFile(final IFile file, final Copier copier) throws IOException, CoreException {
        final File fileToModify = file.getLocation().toFile();
        final BufferedReader reader = new BufferedReader(new FileReader(fileToModify));
        StringBuilder sb = new StringBuilder();
        String line = ""; //$NON-NLS-1$
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append("\r\n");//$NON-NLS-1$
        }
        reader.close();

        for (final java.util.Map.Entry<EObject, EObject> entry : copier.entrySet()) {
            final String originalId = ModelHelper.getEObjectID(entry.getKey());
            final String newId = ModelHelper.getEObjectID(entry.getValue());
            final String content = sb.toString();
            if (content.contains(originalId)) {
                sb = new StringBuilder(content.replaceAll(originalId, newId));
            }
        }

        final FileWriter writer = new FileWriter(fileToModify.getAbsolutePath());
        writer.write(sb.toString());
        writer.close();
        file.refreshLocal(IResource.DEPTH_ONE, Repository.NULL_PROGRESS_MONITOR);
    }

    protected ApplicationResourceFileStore getApplicationResourceFileStore(
            final ApplicationResourceRepositoryStore resourceStore, final String newProcId) {
        ApplicationResourceFileStore artifact = resourceStore.getChild(newProcId);
        if (artifact == null) {
            artifact = resourceStore.createRepositoryFileStore(newProcId);
        }
        return artifact;
    }

    private void changeProcessNameAndVersion(final AbstractProcess process, final TransactionalEditingDomain editingDomain, final String newProcessLabel,
            final String newProcessVersion) {
        editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, process, ProcessPackage.Literals.ELEMENT__NAME, newProcessLabel));
        editingDomain.getCommandStack()
                .execute(SetCommand.create(editingDomain, process, ProcessPackage.Literals.ABSTRACT_PROCESS__VERSION, newProcessVersion));

        //        try {
        //            process.eResource().save(Collections.EMPTY_MAP);
        //        } catch (final IOException e) {
        //            BonitaStudioLog.error(e);
        //        }
    }

    public void setDiagramToDuplicate(final MainProcess diagram) {
        this.diagram = diagram;
    }

    public void setNewDiagramName(final String diagramName) {
        this.diagramName = diagramName;
    }

    public void setPoolsRenamed(final List<ProcessesNameVersion> pools) {
        this.pools = pools;
    }

    public void setNewDiagramVersion(final String diagramVersion) {
        this.diagramVersion = diagramVersion;
    }

}
