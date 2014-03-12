/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.OpenNameAndVersionForDiagramDialog;
import org.bonitasoft.studio.common.OpenNameAndVersionForDiagramDialog.ProcessesNameVersion;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.refactoring.ProcessNamingTools;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.properties.sections.forms.FormsUtils;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.handlers.SaveHandler;
import org.eclipse.ui.progress.IProgressService;


/**
 * @author Romain Bioteau
 * @author Mickael Istria: Check version and name don't override another file
 */
public class SaveCommandHandler extends SaveHandler {

    public SaveCommandHandler(){
        super();
    }

    @Override
    public Object execute(ExecutionEvent event) {
        if(isDirty()){
            final IEditorPart editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            if(editorPart instanceof DiagramEditor){
                IProgressService service = PlatformUI.getWorkbench().getProgressService();
                try {
                    service.run(false,false,new IRunnableWithProgress() {

                        @Override
                        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                            doSaveDiagram((DiagramEditor) editorPart);
                        }
                    });
                } catch (InvocationTargetException e) {
                    return null;
                } catch (InterruptedException e) {
                    return null;
                }

            }else {//in case of the editor doesn't have a good handler defined (like xml design editor)
                editorPart.doSave(Repository.NULL_PROGRESS_MONITOR);
            }
        }

        return null;
    }

    protected void doSaveDiagram(DiagramEditor editorPart) {
        String formName = null;
        boolean changed = false;
        DiagramRepositoryStore diagramStore = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class) ;
        MainProcess proc = findProc(editorPart);
        DiagramFileStore oldArtifact = null;
        List<DiagramDocumentEditor> editorsWithSameResourceSet = new ArrayList<DiagramDocumentEditor>();
        if (nameOrVersionChanged(proc)) {
            IEditorReference[] editorReferences;
            editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
            IEditorInput editorInput = editorPart.getEditorInput();
            ResourceSet resourceSet = proc.eResource().getResourceSet();
            IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            if(editor instanceof FormDiagramEditor){
                editorsWithSameResourceSet.add((DiagramDocumentEditor) editor);
                formName = ((FormDiagramEditor)editor).getPartName();
            }

            maintainListOfEditorsWithSameResourceSet(editorsWithSameResourceSet, editorReferences,editorInput, resourceSet);
            oldArtifact = diagramStore.getChild(NamingUtils.toDiagramFilename(getOldProcess(proc)));
            changed = true;
        }

        try {
            IEditorPart editorToSave = editorPart;
            if(changed &&  oldArtifact!= null){
                editorToSave.doSave(Repository.NULL_PROGRESS_MONITOR);
                ((DiagramDocumentEditor)editorToSave).close(true);
                Set<String> formIds = new HashSet<String>();
                for (DiagramDocumentEditor diagramDocumentEditor : editorsWithSameResourceSet) {
                    formIds.add(ModelHelper.getEObjectID(diagramDocumentEditor.getDiagramEditPart().resolveSemanticElement()));
                    diagramDocumentEditor.close(true);
                }
                oldArtifact.rename(NamingUtils.toDiagramFilename(proc)) ;
                IWorkbenchPart newEditorOfDiagram = oldArtifact.open();

                List<EObject> forms = openDiagramsForFormsId(oldArtifact, formIds);
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().bringToTop(newEditorOfDiagram);
                openFormDiagramWithNameIfInList(formName, forms);
            }else{
                EObject root = ((DiagramEditor)editorPart).getDiagramEditPart().resolveSemanticElement();
                Resource res = root.eResource();
                if(res != null){
                    final String procFile = URI.decode(res.getURI().lastSegment());
                    final DiagramFileStore fileStore =diagramStore.getChild(procFile);
                    if(fileStore != null){
                        fileStore.save(editorPart);
                    }
                }else{
                    editorPart.doSave(Repository.NULL_PROGRESS_MONITOR);
                }


            }
        } catch (Exception ex) {
            BonitaStudioLog.error(ex);
        }
    }

    private void openFormDiagramWithNameIfInList(String formName,
            List<EObject> forms) {
        if(formName!=null){
            for (EObject form:forms){
                if(form instanceof Form && ((Form)form).getName().equals(formName)){
                    FormsUtils.openDiagram((Form)form, null);
                    break;
                }
            }
        }
    }

    private List<EObject> openDiagramsForFormsId(DiagramFileStore oldArtifact,
            Set<String> formIds) {
        MainProcess diagram =  oldArtifact.getContent();
        List<EObject> forms=ModelHelper.getAllItemsOfType(diagram, FormPackage.Literals.FORM);
        for (EObject form:forms){
            String id = ModelHelper.getEObjectID(form);
            if (formIds.contains(id)){
                //TODO: find a way to just open the diagram without bringing them to top and make the UI blinking
                FormsUtils.openDiagram((Form)form, null);
            }
        }
        return forms;
    }

    private MainProcess findProc(IEditorPart editorPart) {
        MainProcess proc = null ;
        if(editorPart instanceof ProcessDiagramEditor){
            DiagramEditPart diagram = ((ProcessDiagramEditor) editorPart).getDiagramEditPart();
            proc = (MainProcess) diagram.resolveSemanticElement() ;
        }else if(editorPart instanceof FormDiagramEditor){
            DiagramEditPart formDiagram = ((DiagramDocumentEditor)editorPart).getDiagramEditPart();
            Form form = (Form) formDiagram.resolveSemanticElement();
            proc = ModelHelper.getMainProcess(form.eContainer()) ;
        }
        return proc;
    }

    private void maintainListOfEditorsWithSameResourceSet(
            List<DiagramDocumentEditor> editorsWithSameResourceSet,
            IEditorReference[] editorReferences, IEditorInput editorInput,
            ResourceSet resourceSet) {
        for (IEditorReference editorRef : editorReferences) {
            try {
                IEditorInput currentEditorInput = editorRef.getEditorInput();
                if (currentEditorInput != editorInput) {
                    IEditorPart openEditor = editorRef.getEditor(false);
                    if (openEditor instanceof DiagramDocumentEditor) {
                        DiagramDocumentEditor openDiagramEditor = (DiagramDocumentEditor) openEditor;
                        ResourceSet diagramResourceSet = openDiagramEditor.getEditingDomain().getResourceSet();
                        if (diagramResourceSet == resourceSet) {
                            editorsWithSameResourceSet.add(openDiagramEditor);
                        }
                    }
                }
            } catch (Exception ex) {
                BonitaStudioLog.error(ex);
            }
        }
    }
    /**
     * @param proc
     * @return
     */
    protected boolean nameOrVersionChanged(MainProcess proc) {
        MainProcess originalProcess = getOldProcess(proc);
        if(originalProcess.getAuthor() == null && BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getBoolean(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE)){
            DiagramRepositoryStore diagramStore = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
            final OpenNameAndVersionForDiagramDialog nameDialog = new OpenNameAndVersionForDiagramDialog(Display.getDefault().getActiveShell(),proc,diagramStore){

                private boolean askRename = true;

                @Override
                protected void createButtonsForButtonBar(Composite parent) {
                    GridData gridData = (GridData) parent.getLayoutData();
                    gridData.horizontalAlignment = SWT.FILL;
                    ((GridLayout) parent.getLayout()).numColumns++;
                    ((GridLayout) parent.getLayout()).makeColumnsEqualWidth = false;
                    final Button askAgainButton = new Button(parent, SWT.CHECK);
                    askAgainButton.setText(Messages.doNotDisplayForOtherDiagrams);
                    askAgainButton.setLayoutData(GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.CENTER).grab(true, false).create());
                    askAgainButton.addSelectionListener(new SelectionAdapter() {


                        @Override
                        public void widgetSelected(SelectionEvent e) {
                            askRename = !askAgainButton.getSelection();
                        }
                    });
                    createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
                            true).addSelectionListener(new SelectionAdapter() {
                                @Override
                                public void widgetSelected(SelectionEvent e) {
                                    BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE,askRename);
                                }
                            });
                }
            };
            if(nameDialog.open() == Dialog.OK) {
                EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(proc);
                final ProcessNamingTools tool = new ProcessNamingTools(domain);
                tool.changeProcessNameAndVersion(proc, nameDialog.getDiagramName(), nameDialog.getDiagramVersion());
                for(ProcessesNameVersion pnv : nameDialog.getPools()){
                    tool.changeProcessNameAndVersion(pnv.getAbstractProcess(), pnv.getNewName(), pnv.getNewVersion());
                }
            }

        }
        return ! (originalProcess.getName().equals(proc.getName()) && originalProcess.getVersion().equals(proc.getVersion()));
    }

    private MainProcess getOldProcess(MainProcess proc) {
        URI resourceUri = proc.eResource().getURI();
        ResourceSet set = new ResourceSetImpl();
        Resource resource = set.getResource(resourceUri, true);
        MainProcess originalProcess = (MainProcess) resource.getContents().get(0);
        return originalProcess;
    }

    @Override
    public boolean isEnabled() {
        boolean res = super.isEnabled() || isDirty() ;
        return res ;
    }


    protected boolean isDirty() {
        IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() ;
        return  part != null &&  part.isDirty();
    }




}
