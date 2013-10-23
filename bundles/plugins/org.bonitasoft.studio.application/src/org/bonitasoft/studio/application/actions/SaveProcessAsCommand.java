/**
 * Copyright (C) 2009-2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.application.actions;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.ConfigurationIdProvider;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.OpenNameAndVersionForDiagramDialog;
import org.bonitasoft.studio.common.OpenNameAndVersionForDiagramDialog.ProcessesNameVersion;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.WebTemplatesUtil;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.ImageWidget;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.AssociatedFile;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ResourceFile;
import org.bonitasoft.studio.model.process.ResourceFolder;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Mickael Istria
 *
 */
public class SaveProcessAsCommand extends AbstractHandler {


    private String newProcessLabel;
    private String newProcessVersion;
    private List<ProcessesNameVersion> pools;
    private DiagramRepositoryStore diagramStore;


    /* (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        diagramStore = (DiagramRepositoryStore) RepositoryManager.getInstance().getCurrentRepository().getRepositoryStore(DiagramRepositoryStore.class) ;
        IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        if (! (editor instanceof ProcessDiagramEditor)) {
            MessageDialog.openWarning(Display.getDefault().getActiveShell(), "This is not a process diagram!", "Cannot perform \"Duplicate\" on something that is not a process diagram");
            return null;
        }

        ProcessDiagramEditor processEditor = (ProcessDiagramEditor)editor;

        MainProcess process = (MainProcess) ((IGraphicalEditPart)processEditor.getDiagramEditPart()).resolveSemanticElement();
        newProcessLabel = process.getName();
        newProcessVersion = process.getVersion();
        OpenNameAndVersionForDiagramDialog dialog = new OpenNameAndVersionForDiagramDialog(Display.getDefault().getActiveShell(), ModelHelper.getMainProcess(process),diagramStore,true) ;
        if (dialog.open() == Dialog.OK ) {
            newProcessLabel = dialog.getDiagramName() ;
            newProcessVersion = dialog.getDiagramVersion() ;
            pools = dialog.getPools();

            duplicate(process, newProcessLabel, newProcessVersion, pools);
            DiagramFileStore store = diagramStore.getDiagram(newProcessLabel, newProcessVersion);
            store.open();
        }
        return null;
    }

    /**
     * Initially make public only for test purpose
     * @param diagram
     * @param newProcessLabel
     * @param newProcessVersion
     * @param pools
     * @return
     */
    public void duplicate(final MainProcess diagram, final String newProcessLabel,final String newProcessVersion,final List<ProcessesNameVersion> pools) {
        IProgressService service = PlatformUI.getWorkbench().getProgressService();
        try {
            service.run(true, false, new IRunnableWithProgress() {

                @Override
                public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    monitor.beginTask(Messages.duplicatingDiagram, IProgressMonitor.UNKNOWN);
                    if (diagramStore==null){
                        diagramStore = (DiagramRepositoryStore) RepositoryManager.getInstance().getCurrentRepository().getRepositoryStore(DiagramRepositoryStore.class) ;
                    }
                    Copier copier = new Copier(true, false);
                    Collection<EObject> copiedElements = copier.copyAll(diagram.eResource().getContents());
                    copier.copyReferences();//don't forget this line otherwise we loose link between diagrams and model
                    DiagramFileStore store = diagramStore.createRepositoryFileStore(NamingUtils.toDiagramFilename(newProcessLabel, newProcessVersion));
                    store.save(copiedElements) ;


                    final MainProcess newDiagram = store.getContent();

                    ResourceSet rSet = newDiagram.eResource().getResourceSet() ;
                    final TransactionalEditingDomain createEditingDomain = GMFEditingDomainFactory.getInstance().createEditingDomain(rSet) ;
                    changeProcessNameAndVersion(newDiagram, createEditingDomain, newProcessLabel, newProcessVersion);
                    createEditingDomain.getCommandStack().execute(SetCommand.create(createEditingDomain, newDiagram, ProcessPackage.Literals.MAIN_PROCESS__CONFIG_ID, ConfigurationIdProvider.getConfigurationIdProvider().getConfigurationId((MainProcess) newDiagram)));
                    try {
                        OperationHistoryFactory.getOperationHistory().execute(new AbstractTransactionalCommand(createEditingDomain,"Duplicate",Collections.EMPTY_LIST) {

                            @Override
                            protected CommandResult doExecuteWithResult(IProgressMonitor arg0, IAdaptable arg1) throws ExecutionException {
                                changePathAndCopyResources(diagram, newDiagram, createEditingDomain);
                                return CommandResult.newOKCommandResult();
                            }

                            @Override
                            public boolean canUndo() {
                                return false;
                            }

                        },null,null);
                    } catch (ExecutionException e1) {
                        BonitaStudioLog.error(e1);
                    }


                    for(ProcessesNameVersion pnv : pools){
                        AbstractProcess fromPool = pnv.getAbstractProcess();
                        String fromPoolName = fromPool.getName();
                        String fromPoolVersion = fromPool.getVersion();
                        /*Find corresponding element in the duplicated model*/
                        for (Element element : newDiagram.getElements()) {
                            if(element instanceof AbstractProcess){
                                if(element.getName().equals(fromPoolName)
                                        && ((AbstractProcess) element).getVersion().equals(fromPoolVersion)){
                                    changeProcessNameAndVersion((AbstractProcess) element, createEditingDomain, pnv.getNewName(), pnv.getNewVersion());
                                    break;
                                }
                            }
                        }

                    }


                    try {
                        newDiagram.eResource().save(Collections.EMPTY_MAP);
                    } catch (IOException e) {
                        BonitaStudioLog.error(e);
                    }

                    duplicateConfigurations(diagram,newDiagram);
                }
            });
        } catch (InvocationTargetException e2) {
            BonitaStudioLog.error(e2);
        } catch (InterruptedException e2) {
            BonitaStudioLog.error(e2);
        }
    }




    private void duplicateConfigurations(MainProcess sourceDiagram, MainProcess newDiagram) {
        final ProcessConfigurationRepositoryStore confStore = (ProcessConfigurationRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ProcessConfigurationRepositoryStore.class);
        final List<Pool> pools = ModelHelper.getAllItemsOfType(sourceDiagram, ProcessPackage.Literals.POOL);
        for(Pool p : pools){
            ProcessConfigurationFileStore file = confStore.getChild(ModelHelper.getEObjectID(p)+"."+ProcessConfigurationRepositoryStore.CONF_EXT);
            if(file != null){
                final Copier copier = new Copier(true, false);
                final Collection<EObject> copiedElements = copier.copyAll(file.getContent().eResource().getContents());
                copier.copyReferences();
                if(!copiedElements.isEmpty()){
                    final int index = sourceDiagram.getElements().indexOf(p);
                    final Pool newPool = (Pool) newDiagram.getElements().get(index);
                    final ProcessConfigurationFileStore newFile = confStore.createRepositoryFileStore(ModelHelper.getEObjectID(newPool)+"."+ProcessConfigurationRepositoryStore.CONF_EXT);
                    newFile.save(copiedElements.iterator().next()) ;
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
     */
    private void changePathAndCopyResources(MainProcess oldProcess, MainProcess newProcess,
            TransactionalEditingDomain createEditingDomain) {

        ApplicationResourceRepositoryStore resourceStore = (ApplicationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class) ;
        for(AbstractProcess oldProc : ModelHelper.getAllProcesses(oldProcess)){
            AbstractProcess newProc = null;
            for(AbstractProcess process : ModelHelper.getAllProcesses(newProcess)){
                if(oldProc.getName().equals(process.getName())
                        && oldProc.getVersion().equals(process.getVersion())){
                    newProc = process;
                    break;
                }
            }
            String newProcId = ModelHelper.getEObjectID(newProc) ;
            /*Duplicate Resource Folders*/
            newProc.getResourceFolders().clear();
            for(ResourceFolder rf : oldProc.getResourceFolders()){
                File resourceFolder = WebTemplatesUtil.getFile(rf.getPath());
                if(resourceFolder != null){
                    WebTemplatesUtil.putResourcesInProcessTemplate(resourceFolder.getAbsolutePath(), null, createEditingDomain, newProc);
                }
            }
            /*Duplicate Resources Files*/
            newProc.getResourceFiles().clear();
            for(ResourceFile rf : oldProc.getResourceFiles()){
                File resourceFile = WebTemplatesUtil.getFile(rf.getPath());
                if(resourceFile != null){
                    WebTemplatesUtil.putResourcesInProcessTemplate(resourceFile.getAbsolutePath(), null, createEditingDomain, newProc);
                }
            }

            /*Duplicate Confirmation Template*/
            final AssociatedFile confirmationTemplate = newProc.getConfirmationTemplate();
            if(confirmationTemplate != null){
                File confirmationFile = WebTemplatesUtil.getFile(confirmationTemplate.getPath());
                if(confirmationFile != null){
                    ApplicationResourceFileStore artifact = getApplicationResourceFileStore(
                            resourceStore, newProcId);
                    String confTemplateRelative = artifact.setConfirmationTemplate(confirmationFile.getAbsolutePath(), newProc);
                    confirmationTemplate.setPath(confTemplateRelative);
                }else{
                    newProc.setConfirmationTemplate(null) ;
                }
            }
            /*And also in all tasks*/
            //TODO: duplicate confirmation template for tasks


            /*Duplicate Error Template*/
            final AssociatedFile errorTemplate = newProc.getErrorTemplate();
            if(errorTemplate != null){
                File errorFile = WebTemplatesUtil.getFile(errorTemplate.getPath());
                if(errorFile != null){
                    ApplicationResourceFileStore artifact = getApplicationResourceFileStore(
                            resourceStore, newProcId);
                    String errorTemplateRelative = artifact.setErrorTemplate(errorFile.getAbsolutePath());
                    errorTemplate.setPath(errorTemplateRelative);
                }else{
                    newProc.setErrorTemplate(null) ;
                }
            }

            /*Duplicate globalConsultation Template*/
            final AssociatedFile globalConsultationTemplate = newProc.getConsultationTemplate();
            if(globalConsultationTemplate != null){
                File globalConsultationFile = WebTemplatesUtil.getFile(globalConsultationTemplate.getPath());
                if(globalConsultationFile != null){
                    ApplicationResourceFileStore artifact = getApplicationResourceFileStore(
                            resourceStore, newProcId);
                    String globalConsultationTemplateRelative = artifact.setGlobalConsultationPage(globalConsultationFile.getAbsolutePath());
                    globalConsultationTemplate.setPath(globalConsultationTemplateRelative);
                }else{
                    newProc.setConsultationTemplate(null) ;
                }
            }

            /*Duplicate Login Page*/
            final AssociatedFile loginTemplate = newProc.getLogInPage();
            if(loginTemplate != null){
                File loginFile = WebTemplatesUtil.getFile(loginTemplate.getPath());
                if(loginFile != null){
                    ApplicationResourceFileStore artifact = getApplicationResourceFileStore(
                            resourceStore, newProcId);
                    String loginPageRelative = artifact.setLoginPage(loginFile.getAbsolutePath());
                    loginTemplate.setPath(loginPageRelative);
                }else{
                    newProc.setLogInPage(null) ;
                }
            }

            /*Duplicate Page Template*/
            final AssociatedFile pageTemplate = newProc.getPageTemplate();
            if(pageTemplate != null){
                File pageFile = WebTemplatesUtil.getFile(pageTemplate.getPath());
                if(pageFile != null){
                    ApplicationResourceFileStore artifact = getApplicationResourceFileStore(
                            resourceStore, newProcId);
                    String pageRelative = artifact.setGlobalPageTemplate(pageFile.getAbsolutePath());
                    pageTemplate.setPath(pageRelative);
                }else{
                    newProc.setPageTemplate(null) ;
                }
            }

            /*Duplicate Process Page Template*/
            final AssociatedFile processTemplate = newProc.getProcessTemplate();
            if(processTemplate != null){
                File processTemplateFile = WebTemplatesUtil.getFile(processTemplate.getPath());
                if(processTemplateFile != null){
                    ApplicationResourceFileStore artifact = getApplicationResourceFileStore(
                            resourceStore, newProcId);
                    String processTemplateRelative = artifact.setProcessTemplate(processTemplateFile.getAbsolutePath());
                    processTemplate.setPath(processTemplateRelative);
                }else{
                    newProc.setProcessTemplate(null) ;
                }
            }

            /*Duplicate Process Page Template*/
            final AssociatedFile welcomePage = newProc.getWelcomePage();
            if(welcomePage != null){
                File welcomePageFile = WebTemplatesUtil.getFile(welcomePage.getPath());
                if(welcomePageFile != null){
                    ApplicationResourceFileStore artifact = getApplicationResourceFileStore(
                            resourceStore, newProcId);
                    String welcomePageRelative = artifact.setWelcomePage(welcomePageFile.getAbsolutePath());
                    welcomePage.setPath(welcomePageRelative);
                }else{
                    newProc.setWelcomePage(null) ;
                }
            }

            /*Duplicate Image Path that are on ImageWidget*/
            for(Form form : newProc.getForm()){
                for(Widget w : form.getWidgets()){
                    if(w instanceof ImageWidget){
                        final Expression imgPath2 = ((ImageWidget) w).getImgPath();
                        if(imgPath2 != null){
                            final String imgPath = imgPath2.getContent();
                            if(imgPath != null){
                                File imageFile = WebTemplatesUtil.getFile(imgPath);
                                if(imageFile != null && imageFile.exists()){
                                    WebTemplatesUtil.putResourcesInProcessTemplate(imageFile.getAbsolutePath(), null, createEditingDomain, newProc);
                                }
                            }
                        }
                    }
                }
            }

        }


    }

    protected ApplicationResourceFileStore getApplicationResourceFileStore(
            ApplicationResourceRepositoryStore resourceStore, String newProcId) {
        ApplicationResourceFileStore artifact = (ApplicationResourceFileStore) resourceStore.getChild(newProcId);
        if (artifact == null) {
            artifact = (ApplicationResourceFileStore) resourceStore.createRepositoryFileStore(newProcId) ;
        }
        return artifact;
    }




    /* (non-Javadoc)
     * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        return editor != null && editor instanceof ProcessDiagramEditor;
    }




    /**
     * @param process
     * @param newProcessName2
     * @param newProcessVersion2
     * @param editingDomain
     * @param newProcessVersion2
     * @param newProcessLabel
     */
    private void changeProcessNameAndVersion(final AbstractProcess process, TransactionalEditingDomain editingDomain, final String newProcessLabel, final String newProcessVersion) {
        editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, process, ProcessPackage.Literals.ELEMENT__NAME, newProcessLabel));
        editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, process, ProcessPackage.Literals.ABSTRACT_PROCESS__VERSION, newProcessVersion));

        try {
            process.eResource().save(Collections.EMPTY_MAP);
        } catch (IOException e) {
            BonitaStudioLog.error(e);
        }
    }


}
