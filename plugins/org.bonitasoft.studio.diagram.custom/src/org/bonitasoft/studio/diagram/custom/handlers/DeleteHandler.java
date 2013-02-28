/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.custom.handlers;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.diagram.custom.Messages;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.EventSubProcessPool;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.ui.action.actions.global.GlobalActionManager;
import org.eclipse.gmf.runtime.common.ui.action.global.GlobalActionId;
import org.eclipse.gmf.runtime.common.ui.services.editor.EditorService;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.forms.widgets.FormUtil;

/**
 * @author Aurelien Pupier
 */
public class DeleteHandler extends AbstractHandler {

	private List<Lane> lanes = new ArrayList<Lane>();

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();

		if (part != null && part instanceof DiagramEditor) {
			IStructuredSelection currentSelection = (IStructuredSelection) ((DiagramEditor) part).getDiagramGraphicalViewer().getSelection();
			if (currentSelection.getFirstElement() instanceof IGraphicalEditPart) {
				lanes.clear() ;
				boolean containsPool = false;
				boolean isMessageFlow = false;
				MessageFlow flow  = null;
				List<IGraphicalEditPart> newSelection = new ArrayList<IGraphicalEditPart>() ;
				for (Object item : currentSelection.toArray()) {
					if (((IGraphicalEditPart) item).resolveSemanticElement() instanceof Pool
							||((IGraphicalEditPart) item).resolveSemanticElement() instanceof EventSubProcessPool ) {
						containsPool = true;
					}
					if(((IGraphicalEditPart) item).resolveSemanticElement() instanceof Lane){
						lanes.add((Lane) ((IGraphicalEditPart) item).resolveSemanticElement()) ;
					}
					if (((IGraphicalEditPart) item).resolveSemanticElement() instanceof PageFlow) {

						PageFlow element = (PageFlow)((IGraphicalEditPart) item).resolveSemanticElement();
						List<Form> forms =element.getForm();
						closeFormsRelatedToDiagramElement(forms);
					} 
					if  (((IGraphicalEditPart) item).resolveSemanticElement() instanceof MessageFlow) {
						isMessageFlow = true;
						flow = (MessageFlow)((IGraphicalEditPart) item).resolveSemanticElement();
						
						//removeMessageFlow(flow);
						
					}
					if(item instanceof ShapeCompartmentEditPart){
						newSelection.add((IGraphicalEditPart) ((IGraphicalEditPart) item).getParent()) ;
					}else{
						newSelection.add((IGraphicalEditPart) item) ;
					}
				}
				((DiagramEditor) part).getDiagramGraphicalViewer().setSelection(new StructuredSelection(newSelection)) ;
				
				if (containsPool) {
					if (MessageDialog.openQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.deleteDialogTitle,
							Messages.deleteDialogMessage)){
						upadateLaneItems() ;
						GlobalActionManager.getInstance().createActionHandler(part, GlobalActionId.DELETE).run() ;
					}
				} else {
					if (isMessageFlow){
					
						if (MessageDialog.openQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.deleteDialogTitle,
								Messages.bind(Messages.deleteMessageFlow,flow.getName()))){
							removeMessage(flow);
							GlobalActionManager.getInstance().createActionHandler(part, GlobalActionId.DELETE).run();
						}
					} else {
					GlobalActionManager.getInstance().createActionHandler(part, GlobalActionId.DELETE).run();
					}
				}

			}
		}
		return null;
	}

	private void upadateLaneItems() {
		CompoundCommand cc = new CompoundCommand() ;
		for(Lane l : lanes){
			TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(l) ;
			for(EObject task : ModelHelper.getAllItemsOfType(l, ProcessPackage.Literals.TASK)){
				cc.append(SetCommand.create(domain, task, ProcessPackage.Literals.TASK__OVERRIDE_ACTORS_OF_THE_LANE, true)) ;
			}
			domain.getCommandStack().execute(cc) ;
		}
	}
	
	private void closeFormsRelatedToDiagramElement(List<Form> forms){
		for (Form form:forms){
			URI uri = EcoreUtil.getURI(form);
			List<IEditorPart> editors =(List<IEditorPart>)EditorService.getInstance().getRegisteredEditorParts();
			for (IEditorPart editor:editors){
				if (editor instanceof FormDiagramEditor){
					FormDiagramEditor formEditor = (FormDiagramEditor)editor;
					Form availableform= (Form)formEditor.getDiagramEditPart().resolveSemanticElement();
					if (availableform.equals(form)){
						((FormDiagramEditor) editor).close(false);
					}
				}
			}
		}
	}

	
	public void removeMessage(MessageFlow flow){
		AbstractCatchMessageEvent catchEvent = flow.getTarget();
		TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(catchEvent) ;
		CompoundCommand cc = new CompoundCommand();
		List<Message> messages =flow.getSource().getEvents();
		for (Message message:messages){
			if (flow.getName().equals(message.getName())){
				cc.append(RemoveCommand.create(domain, flow.getSource(),ProcessPackage.Literals.THROW_MESSAGE_EVENT__EVENTS, message));
				break;
			}
		}
		cc.append(SetCommand.create(domain,catchEvent,ProcessPackage.Literals.ABSTRACT_CATCH_MESSAGE_EVENT__EVENT,null));
		domain.getCommandStack().execute(cc);
	}
	/**
	 * disable for MainProcess
	 * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		IStructuredSelection currentSelection = ((IStructuredSelection) part.getSite().getSelectionProvider().getSelection());
		if (currentSelection.getFirstElement() instanceof IGraphicalEditPart){
			if( (currentSelection.getFirstElement() instanceof MainProcessEditPart)){
				return false;
			}
		}
		return super.isEnabled();
	}
}
