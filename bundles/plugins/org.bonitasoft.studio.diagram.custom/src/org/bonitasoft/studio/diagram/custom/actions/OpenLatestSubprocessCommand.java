/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.custom.actions;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.CallActivity;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;

/**
 * @author Mickael Istria
 * 
 */
public class OpenLatestSubprocessCommand extends AbstractHandler {

	private DiagramRepositoryStore diagramSotre;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.
	 * ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IRepository repository = RepositoryManager.getInstance().getCurrentRepository() ;
		diagramSotre = (DiagramRepositoryStore) repository.getRepositoryStore(DiagramRepositoryStore.class) ;

		try {
			IStructuredSelection selection = (IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
			Object item = selection.getFirstElement();
			CallActivity subProcess = (CallActivity) ((GraphicalEditPart) item).resolveSemanticElement();
			final Expression calledProcessName = subProcess.getCalledActivityName();
			String subprocessName = null ;
			if(calledProcessName != null
					&& calledProcessName.getContent() != null
					&& calledProcessName.getType().equals(ExpressionConstants.CONSTANT_TYPE)){
				subprocessName = calledProcessName.getContent() ;
			}
			final Expression calledProcessVersion = subProcess.getCalledActivityVersion();
			String subprocessVersion = null ;
			if(calledProcessVersion != null
					&& calledProcessVersion.getContent() != null
					&& calledProcessVersion.getType().equals(ExpressionConstants.CONSTANT_TYPE)){
				subprocessVersion = calledProcessVersion.getContent() ;
			}
			if(subprocessName != null){
				AbstractProcess subProcessParent = ModelHelper.findProcess(subprocessName,subprocessVersion, diagramSotre.getAllProcesses());

				if (subProcessParent == null) {
					/* we don't find the process so we can't open it */
					return null;
				}

				Resource r = subProcessParent.eResource() ;
				if(r != null){
					String fileName = r.getURI().lastSegment() ;
					DiagramFileStore store = diagramSotre.getChild(URI.decode(fileName), true) ;
					// if the diagram is already opened
					if(store.getOpenedEditor()!=null){
						for(IEditorReference ref : PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences()){
							if(ref.getEditorInput().getName().equals(store.getName())){
								PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().activate(ref.getPart(true));
								break;
							}
						} 
					}else{ //if the diagram referenced is not opened
						store.open() ;
					}
					final DiagramEditor editor =  (DiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
					final IGraphicalEditPart findEditPart = GMFTools.findEditPart(editor.getDiagramEditPart(), subProcessParent);
					if(findEditPart != null){
						editor.getDiagramGraphicalViewer().select(findEditPart);
					}
				} else {
					MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.errorSubprocessNotFound,
							Messages.errorSubprocessDoesNotExist);
					throw new ExecutionException("Could not open specified subprocess, it probably doesn't exist");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		IStructuredSelection selection = (IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		Object item = selection.getFirstElement();
		EObject element = ((GraphicalEditPart) item).resolveSemanticElement();
		return element instanceof CallActivity && ((CallActivity) element).getCalledActivityName() != null;
	}

}
