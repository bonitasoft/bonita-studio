/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.diagram.custom.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.diagram.custom.Activator;
import org.bonitasoft.studio.migration.model.report.Report;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolEditPart;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * @author Romain Bioteau
 *
 */
public class DiagramFileStore extends EMFFileStore implements IRepositoryFileStore{

	public static final String PROC_EXT = "proc";

	private final NotificationListener poolListener = new PoolNotificationListerner();

	public DiagramFileStore(String fileName, IRepositoryStore store) {
		super(fileName, store);
	}

	@Override
	public synchronized MainProcess getContent() {
		return (MainProcess) super.getContent() ;
	}

	@Override
	public Resource getEMFResource() {
		DiagramEditor editor = getOpenedEditor();
		if(editor != null){
			final DiagramEditPart diagramEditPart = editor.getDiagramEditPart();
			if(diagramEditPart != null){
				final EObject resolveSemanticElement = diagramEditPart.resolveSemanticElement();
				if(resolveSemanticElement != null){
					return resolveSemanticElement.eResource();
				}
			}
		}
		return super.getEMFResource();
	}

	public void registerListeners(EObject input,TransactionalEditingDomain domain) {
		DiagramEventBroker.getInstance(domain).addNotificationListener(input, ProcessPackage.Literals.CONTAINER__ELEMENTS, poolListener ) ;
	}


	public void unregisterListeners(EObject input,TransactionalEditingDomain domain) {
		DiagramEventBroker.getInstance(domain).removeNotificationListener(input, ProcessPackage.Literals.CONTAINER__ELEMENTS, poolListener) ;
	}

	public DiagramEditor getOpenedEditor(){
		if(PlatformUI.isWorkbenchRunning() && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null){
			final String resourceName = getResource().getName();
			for (IEditorReference ref:PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences()){
				String inputName;
				try {
					inputName = ref.getEditorInput().getName();
					if (resourceName.equals(inputName)){
						IEditorPart editor = ref.getEditor(false);
						if(editor instanceof ProcessDiagramEditor){
							return (DiagramEditor) editor;
						}

					}
				} catch (PartInitException e) {
					BonitaStudioLog.error(e,Activator.PLUGIN_ID);
				}
			}
		}
		return null;
	}

	public List<AbstractProcess> getProcesses()  {
		MainProcess diagram = null;
		DiagramEditor editor = getOpenedEditor();
		if(editor!= null){
			diagram = (MainProcess) editor.getDiagramEditPart().resolveSemanticElement();
		}
		if(diagram == null){
			diagram = getContent() ;
		}
		final List<AbstractProcess> allProcesses = ModelHelper.getAllProcesses(diagram);
		List<AbstractProcess> pools = new ArrayList<AbstractProcess>();
		for(AbstractProcess abstractProcess : allProcesses){
			if(abstractProcess instanceof Pool){
				pools.add(abstractProcess);
			}
		}
		return pools;
	}

	@Override
	protected void doSave(Object content) {
		Resource resource = getEMFResource() ;
		if(content instanceof MainProcess){
			if(!resource.getContents().isEmpty()){
				resource.getContents().remove(0) ;
			}
			resource.getContents().add(0,(MainProcess)content) ;
		}else if(content instanceof Diagram){
			if(resource.getContents().size()>1){
				resource.getContents().remove(1) ;
			}
			resource.getContents().add(1,(EObject) content) ;
		}else if(content instanceof Collection){
			Collection<EObject> collectionsOfContents = (Collection<EObject>)content;
			resource.getContents().addAll(collectionsOfContents);
		}else if(content instanceof DiagramDocumentEditor){
			((DiagramDocumentEditor)content).doSave(Repository.NULL_PROGRESS_MONITOR);
		}


		try {
			resource.save(ProcessDiagramEditorUtil.getSaveOptions()) ;
		} catch (IOException e) {
			BonitaStudioLog.error(e) ;
		}
	}

	@Override
	protected IWorkbenchPart doOpen() {
		final MainProcess newProcess = getContent() ;
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		for (IEditorReference editor : activePage.getEditorReferences()) {
			IEditorPart simpleEditor = editor.getEditor(true);
			if (simpleEditor instanceof DiagramEditor) {
				DiagramEditor diagramEditor = (DiagramEditor) simpleEditor;
				EObject input = diagramEditor.getDiagramEditPart().resolveSemanticElement();
				MainProcess oldProcess = null;
				if (input instanceof MainProcess) {
					oldProcess =(MainProcess) input;
				}else if(input instanceof Form){
					oldProcess = ModelHelper.getMainProcess(input);
				}

				if(oldProcess != null){
					if(newProcess != null){
						if (oldProcess.getName().equals(newProcess.getName()) &&
								oldProcess.getVersion().equals(newProcess.getVersion())) {
							activePage.closeEditor(diagramEditor, false);
						}
					} else {
						BonitaStudioLog.log("The new Process is null. Name of currentDiagramFileStore is: " +getName());
					}
				}
			}
		}
		IEditorPart part = null;
		try {
			part = IDE.openEditor(activePage,getParentStore().getResource().getFile(getName()));
			if(part instanceof DiagramEditor){
				final DiagramEditor editor = (DiagramEditor) part;
				MainProcess diagram = (MainProcess) editor.getDiagramEditPart().resolveSemanticElement() ;
				if(isReadOnly()){
					editor.getDiagramEditPart().disableEditMode() ;
				}
				registerListeners(diagram, editor.getEditingDomain()) ;
				IGraphicalEditPart editPart = editor.getDiagramEditPart().getChildBySemanticHint(PoolEditPart.VISUAL_ID+"");
				if(editPart != null) {
					editor.getDiagramEditPart().getViewer().select(editPart);
				}

				if(getMigrationReport() != null){
					activePage.showView("org.bonitasoft.studio.migration.view");
				} else {
					final IViewPart migrationView = activePage.findView("org.bonitasoft.studio.migration.view");
					if(migrationView != null){
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().hideView(migrationView);
					}
				}

				return editor;
			}
		} catch (PartInitException e) {
			BonitaStudioLog.error(e) ;
		}
		return part ;
	}

	@Override
	protected void doClose() {
		if(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null){
			IEditorReference[] editors = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
			// look for the resource in other editors
			for (IEditorReference iEditorReference : editors) {
				try {
					IEditorInput input = iEditorReference.getEditorInput();
					IResource iResource = (IResource) input.getAdapter(IResource.class);
					if (getResource().equals(iResource)) {
						IWorkbenchPart part = iEditorReference.getPart(false);
						if(part != null && part instanceof DiagramDocumentEditor){
							((DiagramDocumentEditor)part).close(true);
						}
					}
				} catch (PartInitException e) {
					// no input? -> nothing to do
				}
			}
		}
		super.doClose();
	}

	@Override
	public IFile getResource() {
		return getParentStore().getResource().getFile(getName());
	}


	/**
	 * 
	 * @return the migration report if exists otherwise returns null
	 */
	public Report getMigrationReport(){
		final Resource emfResource = getEMFResource();
		if(emfResource != null){
			for(EObject root : emfResource.getContents()){
				if(root instanceof Report){
					return (Report) root;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * Remove the migration report from the proc file.
	 * @param save , Whether we save the resoruce after deletion or not
	 * @throws IOException 
	 */
	public void clearMigrationReport(boolean save) throws IOException{
		EObject toRemove = null;
		final Resource emfResource = getEMFResource();
		final Report report = getMigrationReport(); 
		if(report != null){
			final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(toRemove);
			if(domain != null){
				domain.getCommandStack().execute(new RecordingCommand(domain) {
					protected void doExecute() {
						emfResource.getContents().remove(report);
					}
				});
				if(save){
					emfResource.save(Collections.emptyMap());
				}
			}
		}
	}


}
