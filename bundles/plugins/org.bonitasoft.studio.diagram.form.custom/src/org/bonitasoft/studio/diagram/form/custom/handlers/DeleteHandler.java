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
package org.bonitasoft.studio.diagram.form.custom.handlers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.dialog.OutlineDialog;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.refactoring.RemoveWidgetReferencesOperation;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormEditPart;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.ui.action.actions.global.GlobalActionManager;
import org.eclipse.gmf.runtime.common.ui.action.global.GlobalActionId;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Aurelien Pupier
 */
public class DeleteHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if(part instanceof DiagramEditor){
		   List<?> selectedEps =  ((DiagramEditor)part).getDiagramGraphicalViewer().getSelectedEditParts();
		   if(!selectedEps.isEmpty()){
		       Object ep = selectedEps.get(0);
		       if(ep instanceof IGraphicalEditPart){
		           EObject semanticElement = ((IGraphicalEditPart) ep).resolveSemanticElement();
		           if(semanticElement instanceof Widget){
		               String[] buttonList = {IDialogConstants.OK_LABEL,IDialogConstants.CANCEL_LABEL};
		               List<Object> widgetSelected = new ArrayList<Object>();
		               widgetSelected.add(semanticElement);
		               OutlineDialog dialog = new OutlineDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.deleteFormFieldDialogTitle, Display.getCurrent().getSystemImage(SWT.ICON_WARNING),Messages.bind(Messages.askConfirmationForDeleting, ((Element) semanticElement).getName()),MessageDialog.CONFIRM,buttonList,1,widgetSelected);
		               int ok=0;
		               RemoveWidgetReferencesOperation op = new RemoveWidgetReferencesOperation(ModelHelper.getPageFlow((Widget) semanticElement),(Widget) semanticElement);
		               if (dialog.open() == ok){
		                   CompoundCommand cc = new CompoundCommand();
		                   op.setCompoundCommand(cc);
		                   op.updateReferencesInScripts();
		                   IProgressService service = PlatformUI.getWorkbench().getProgressService();
		                   try {
		                       service.busyCursorWhile(op);
		                   } catch (InvocationTargetException e) {
		                       BonitaStudioLog.error(e);
		                   } catch (InterruptedException e) {
		                       BonitaStudioLog.error(e);
		                   }
		                   if (!op.isCanExecute()){
		                       return null;
		                   }
		               } else{
		                   return null;
		               }
		           }
		       }
		   }
		}
		GlobalActionManager.getInstance().createActionHandler(part, GlobalActionId.DELETE).run();
		return null;
	}
	
	
	/**
	 * disable for Form
	 * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		IStructuredSelection currentSelection = ((IStructuredSelection) part.getSite().getSelectionProvider().getSelection());
		if (currentSelection.getFirstElement() instanceof IGraphicalEditPart){
			if( (currentSelection.getFirstElement() instanceof FormEditPart)){
				return false;
			}
		}
		return super.isEnabled();
	}
}
