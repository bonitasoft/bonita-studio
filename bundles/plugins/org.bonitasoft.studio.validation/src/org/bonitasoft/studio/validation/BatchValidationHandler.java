/**
 * Copyright (C) 2012-2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.validation;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.ValidationDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.form.providers.ProcessMarkerNavigationProvider;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.bonitasoft.studio.validation.ui.view.ValidationViewPart;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Romain Bioteau
 *
 */
public class BatchValidationHandler extends AbstractHandler {

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {

		if(PlatformUI.isWorkbenchRunning()){
			Map<?,?> parameters = event.getParameters();
			Set<Diagram> toValidate = new HashSet<Diagram>();
			if(parameters != null && !parameters.isEmpty()){
				String files = event.getParameter("diagrams");
				if(files != null){
					final DiagramRepositoryStore store = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
					files = files.substring(1,files.length()-1);
					String[] allFiles = files.split(",");
					for(String fileName : allFiles){
						DiagramFileStore fileStore = store.getChild(fileName.trim());
						if(fileStore != null){
							Resource r = fileStore.getEMFResource();
							for(EObject eObject : r.getContents() ){
								if(eObject instanceof Diagram){
									toValidate.add((Diagram) eObject);
								}
							}
						}else{
							BonitaStudioLog.debug("Proc file : "+fileName.trim()+" not found in repository!", ValidationPlugin.PLUGIN_ID);
						}
					}
				}
			}
			if(toValidate.isEmpty() && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null 
					&& PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null){
				IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() ;
				if(part instanceof DiagramEditor){
					Resource resource = ((DiagramEditor) part).getDiagramEditPart().resolveSemanticElement().eResource();
					if(resource != null){
						for(EObject content : resource.getContents()){
							if(content instanceof Diagram){
								toValidate.add((Diagram) content);
							}
						}
					}
				}
			}

			if(!toValidate.isEmpty()){
				Resource eResource = toValidate.iterator().next().eResource();
				IFile target = eResource != null ? WorkspaceSynchronizer.getFile(eResource) : null;
				if (target != null) {
					ProcessMarkerNavigationProvider.deleteMarkers(target);
				}
			}

			final IProgressService service = PlatformUI.getWorkbench().getProgressService() ;
			final BatchValidationOperation validateOperation = new BatchValidationOperation();
			validateOperation.setDiagramToValidate(toValidate);
			Display.getDefault().syncExec(new Runnable() {

				public void run() {
					try {
						service.run(true, false, validateOperation );
					} catch (InvocationTargetException e) {
						BonitaStudioLog.error(e);
					} catch (InterruptedException e) {
						BonitaStudioLog.error(e);
					}
				}
			});

			Object showReport = parameters.get("showReport");
			if(showReport == null){
				showReport = Boolean.TRUE;
			}
			if(showReport instanceof Boolean){
				if(((Boolean)showReport).booleanValue()){
					showReport(validateOperation.getResult());
				}
			}

			if( PlatformUI.isWorkbenchRunning() && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null){
				IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				if(activePage != null){
					IViewPart part = activePage.findView("org.bonitasoft.studio.validation.view");
					if(part instanceof ValidationViewPart){
						((ValidationViewPart)part).refreshViewer();
					}
				}
			}


			return validateOperation.getResult();
		} else {
			return IStatus.OK;
		}
	}

	private void showReport(IStatus status) {
		if(statusContainsError(status)){
			String errorMessage = Messages.validationErrorFoundMessage + " " + PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getTitle() ;
			int result = new ValidationDialog(Display.getDefault().getActiveShell(), Messages.validationFailedTitle,errorMessage, ValidationDialog.OK_SEEDETAILS).open();

			if(result == ValidationDialog.SEE_DETAILS){
				final IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				IEditorPart part = activePage.getActiveEditor();
				if(part != null && part instanceof DiagramEditor){
					MainProcess proc = ModelHelper.getMainProcess(((DiagramEditor)part).getDiagramEditPart().resolveSemanticElement());
					String partName = proc.getName() +" ("+proc.getVersion()+")";
					for(IEditorReference ref : activePage.getEditorReferences()){
						if(partName.equals(ref.getPartName())){
							activePage.activate(ref.getPart(true));
							break;
						}
					}

				}
				Display.getDefault().asyncExec(new Runnable() {

					@Override
					public void run() {
						try{
							activePage.showView("org.bonitasoft.studio.validation.view");
						} catch (PartInitException e) {
							BonitaStudioLog.error(e);
						}
					}
				});
			}
		}

	}


	private boolean statusContainsError(IStatus validationStatus) {
		if(validationStatus != null){
			for(IStatus s : validationStatus.getChildren()){
				if(s.getSeverity() == IStatus.WARNING || s.getSeverity() == IStatus.ERROR){
					return true;
				}
			}
		}
		return false;
	}


	@Override
	public boolean isEnabled() {
		if(PlatformUI.isWorkbenchRunning() &&  PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null){
			IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() ;
			return part instanceof DiagramEditor;
		}
		return false;
	}
	
}
