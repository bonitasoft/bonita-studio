/**
 * Copyright (C) 2009-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.application.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.diagram.Identifier;
import org.bonitasoft.studio.common.diagram.dialog.OpenNameAndVersionForDiagramDialog;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.properties.operation.RenameDiagramOperation;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
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
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.handlers.SaveHandler;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Romain Bioteau
 * @author Mickael Istria: Check version and name don't override another file
 */
public class SaveCommandHandler extends SaveHandler {

    public SaveCommandHandler() {
        super();
    }

    @Override
    public Object execute(final ExecutionEvent event) {
        if (isDirty()) {
            final IEditorPart editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            if (editorPart instanceof DiagramEditor) {
                final IProgressService service = PlatformUI.getWorkbench().getProgressService();
                try {
                    service.run(false, false, new IRunnableWithProgress() {

                        @Override
                        public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                            doSaveDiagram((DiagramEditor) editorPart);
                        }
                    });
                } catch (final InvocationTargetException e) {
                    return null;
                } catch (final InterruptedException e) {
                    return null;
                }

            } else {//in case of the editor doesn't have a good handler defined (like xml design editor)
                super.execute(event);
            }
        }else {
            super.execute(event);
        }

        return null;
    }

    protected void doSaveDiagram(final DiagramEditor editorPart) {
        boolean changed = false;
        final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        final MainProcess proc = findProc(editorPart);
        DiagramFileStore oldArtifact = null;
        final List<DiagramDocumentEditor> editorsWithSameResourceSet = new ArrayList<DiagramDocumentEditor>();
        if (nameOrVersionChanged(proc, editorPart)) {
            IEditorReference[] editorReferences;
            editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
            final IEditorInput editorInput = editorPart.getEditorInput();
            final ResourceSet resourceSet = proc.eResource().getResourceSet();
            maintainListOfEditorsWithSameResourceSet(editorsWithSameResourceSet, editorReferences, editorInput, resourceSet);
            oldArtifact = diagramStore.getChild(NamingUtils.toDiagramFilename(getOldProcess(proc)));
            changed = true;
        }

        try {
            final IEditorPart editorToSave = editorPart;
            if (changed && oldArtifact != null) {
                editorToSave.doSave(Repository.NULL_PROGRESS_MONITOR);
                ((DiagramDocumentEditor) editorToSave).close(true);
                final Set<String> formIds = new HashSet<String>();
                for (final DiagramDocumentEditor diagramDocumentEditor : editorsWithSameResourceSet) {
                    formIds.add(ModelHelper.getEObjectID(diagramDocumentEditor.getDiagramEditPart().resolveSemanticElement()));
                    diagramDocumentEditor.close(true);
                }
                oldArtifact.renameLegacy(NamingUtils.toDiagramFilename(proc));
                oldArtifact.open();
            } else {
                final EObject root = editorPart.getDiagramEditPart().resolveSemanticElement();
                final Resource res = root.eResource();
                if (res != null) {
                    final String procFile = URI.decode(res.getURI().lastSegment());
                    final DiagramFileStore fileStore = diagramStore.getChild(procFile);
                    if (fileStore != null) {
                        fileStore.save(editorPart);
                    }
                } else {
                    editorPart.doSave(Repository.NULL_PROGRESS_MONITOR);
                }

            }
        } catch (final Exception ex) {
            BonitaStudioLog.error(ex);
        }
    }


    private MainProcess findProc(final IEditorPart editorPart) {
        MainProcess proc = null;
        if (editorPart instanceof ProcessDiagramEditor) {
            final DiagramEditPart diagram = ((ProcessDiagramEditor) editorPart).getDiagramEditPart();
            proc = (MainProcess) diagram.resolveSemanticElement();
        }
        return proc;
    }

    private void maintainListOfEditorsWithSameResourceSet(
            final List<DiagramDocumentEditor> editorsWithSameResourceSet,
            final IEditorReference[] editorReferences, final IEditorInput editorInput,
            final ResourceSet resourceSet) {
        for (final IEditorReference editorRef : editorReferences) {
            try {
                final IEditorInput currentEditorInput = editorRef.getEditorInput();
                if (currentEditorInput != editorInput) {
                    final IEditorPart openEditor = editorRef.getEditor(false);
                    if (openEditor instanceof DiagramDocumentEditor) {
                        final DiagramDocumentEditor openDiagramEditor = (DiagramDocumentEditor) openEditor;
                        final ResourceSet diagramResourceSet = openDiagramEditor.getEditingDomain().getResourceSet();
                        if (diagramResourceSet == resourceSet) {
                            editorsWithSameResourceSet.add(openDiagramEditor);
                        }
                    }
                }
            } catch (final Exception ex) {
                BonitaStudioLog.error(ex);
            }
        }
    }

    /**
     * @param proc
     * @param editorPart
     * @return
     */
    protected boolean nameOrVersionChanged(final MainProcess proc, DiagramEditor editorPart) {
        final MainProcess originalProcess = getOldProcess(proc);
        if (originalProcess.getAuthor() == null
                && BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getBoolean(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE)) {
            final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
            final OpenNameAndVersionForDiagramDialog nameDialog = new OpenNameAndVersionForDiagramDialog(Display.getDefault().getActiveShell(), proc,
                    diagramStore) {

                private boolean askRename = true;

                @Override
                protected void createButtonsForButtonBar(final Composite parent) {
                    final GridData gridData = (GridData) parent.getLayoutData();
                    gridData.horizontalAlignment = SWT.FILL;
                    ((GridLayout) parent.getLayout()).numColumns++;
                    ((GridLayout) parent.getLayout()).makeColumnsEqualWidth = false;
                    final Button askAgainButton = new Button(parent, SWT.CHECK);
                    askAgainButton.setText(Messages.doNotDisplayForOtherDiagrams);
                    askAgainButton.setLayoutData(GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.CENTER).grab(true, false).create());
                    askAgainButton.addSelectionListener(new SelectionAdapter() {

                        @Override
                        public void widgetSelected(final SelectionEvent e) {
                            askRename = !askAgainButton.getSelection();
                        }
                    });
                    createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
                            true).addSelectionListener(new SelectionAdapter() {

                        @Override
                        public void widgetSelected(final SelectionEvent e) {
                            BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                                    .setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, askRename);
                        }
                    });
                }
            };
            if (nameDialog.open() == Dialog.OK) {
                final String author = System.getProperty("user.name", "unknown");
                final TransactionalEditingDomain editingDomain = TransactionUtil
                        .getEditingDomain(proc.eResource());
                editingDomain.getCommandStack().execute(
                        SetCommand.create(editingDomain,
                                ((DiagramEditor) editorPart).getDiagramEditPart().resolveSemanticElement(),
                                ProcessPackage.Literals.ABSTRACT_PROCESS__AUTHOR, author));
                editorPart.doSave(Repository.NULL_PROGRESS_MONITOR);
                final RenameDiagramOperation renameDiagramOperation = new RenameDiagramOperation();
                renameDiagramOperation.setDiagramToDuplicate(proc);
                final Identifier identifier = nameDialog.getIdentifier();
                renameDiagramOperation.setNewDiagramName(identifier.getName());
                renameDiagramOperation.setNewDiagramVersion(nameDialog.getIdentifier().getVersion());
                renameDiagramOperation.setPoolsRenamed(nameDialog.getPools());
                final IProgressService service = PlatformUI.getWorkbench().getProgressService();
                try {
                    service.run(false, false, renameDiagramOperation);
                } catch (final InvocationTargetException e) {
                    BonitaStudioLog.error(e);
                } catch (final InterruptedException e) {
                    BonitaStudioLog.error(e);
                }
            }

        }
        return !(originalProcess.getName().equals(proc.getName()) && originalProcess.getVersion().equals(proc.getVersion()));
    }

    private MainProcess getOldProcess(final MainProcess proc) {
        final URI resourceUri = proc.eResource().getURI();
        final ResourceSet set = new ResourceSetImpl();
        final Resource resource = set.getResource(resourceUri, true);
        final MainProcess originalProcess = (MainProcess) resource.getContents().get(0);
        return originalProcess;
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled() || isDirty();
    }

    protected boolean isDirty() {
        final IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        return part != null && part.isDirty();
    }

}
