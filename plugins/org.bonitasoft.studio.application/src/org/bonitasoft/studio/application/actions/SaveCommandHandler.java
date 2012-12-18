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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.form.part.ProcessDiagramEditorUtil;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.properties.sections.forms.FormsUtils;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.handlers.SaveHandler;


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
			IEditorPart editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			MainProcess proc = null ;
			boolean changed = false;
			if(editorPart instanceof DiagramEditor){
				DiagramRepositoryStore diagramStore = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class) ;
				if(editorPart instanceof ProcessDiagramEditor){
					DiagramEditPart diagram = ((ProcessDiagramEditor) editorPart).getDiagramEditPart();
					proc = (MainProcess) diagram.resolveSemanticElement() ;
				}else if(editorPart instanceof FormDiagramEditor){
					DiagramEditPart formDiagram = ((DiagramDocumentEditor)editorPart).getDiagramEditPart();
					Form form = (Form) formDiagram.resolveSemanticElement();
					proc = ModelHelper.getMainProcess(form.eContainer()) ;
				}
				DiagramFileStore oldArtifact = null;
				List<DiagramDocumentEditor> editorsWithSameResourceSet = new ArrayList<DiagramDocumentEditor>();
				if (nameOrVersionChanged(proc)) {
					IEditorReference[] editorReferences;
					editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
					IEditorInput editorInput = editorPart.getEditorInput();
					ResourceSet resourceSet = proc.eResource().getResourceSet();
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
						oldArtifact.open();
						MainProcess diagram =  oldArtifact.getContent();
						List<EObject> forms=ModelHelper.getAllItemsOfType(diagram, FormPackage.Literals.FORM);
						for (EObject form:forms){
							String id = ModelHelper.getEObjectID(form);
							if (formIds.contains(id)){
								FormsUtils.openDiagram((Form)form, null);
							}
						}

					}else{
						editorPart.doSave(Repository.NULL_PROGRESS_MONITOR);
					}
				} catch (Exception ex) {
					BonitaStudioLog.error(ex);
				}

			}
			else {//in case of the editor doesn't have a good handler defined (like xml design editor)
			editorPart.doSave(Repository.NULL_PROGRESS_MONITOR);
			}
		}

		return null;
	}
	/**
	 * @param proc
	 * @return
	 */
	 protected boolean nameOrVersionChanged(MainProcess proc) {
		MainProcess originalProcess = getOldProcess(proc);
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
