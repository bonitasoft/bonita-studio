/**
 * 
 */
package org.bonitasoft.studio.common.diagram.tools;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.dialog.OutlineDialog;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.refactoring.RemoveWidgetReferencesOperation;
import org.bonitasoft.studio.model.form.Widget;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.parts.DiagramGraphicalViewerKeyHandler;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author aurelie
 *
 */
@SuppressWarnings("restriction")
public class CustomDiagramGraphicalViewerKeyHandler extends
		DiagramGraphicalViewerKeyHandler {

	public CustomDiagramGraphicalViewerKeyHandler(GraphicalViewer viewer) {
		super(viewer);
		// TODO Auto-generated constructor stub
	}
	
@Override
public boolean keyPressed(KeyEvent event) {
	if (event.keyCode==127){
		GraphicalEditPart part = (GraphicalEditPart) getFocusEditPart();
		Widget widget =(Widget)part.resolveSemanticElement();
		String[] buttonList = {IDialogConstants.OK_LABEL,IDialogConstants.CANCEL_LABEL};
		List<Object> widgetSelected = new ArrayList<Object>();
		widgetSelected.add(widget);
		OutlineDialog dialog = new OutlineDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.deleteFormFieldDialogTitle, Display.getCurrent().getSystemImage(SWT.ICON_WARNING),Messages.bind(Messages.askConfirmationForDeleting, widget.getName()),MessageDialog.CONFIRM,buttonList,1,widgetSelected);
		int ok=0;
		RemoveWidgetReferencesOperation op = new RemoveWidgetReferencesOperation(ModelHelper.getPageFlow(widget),widget);
		if (ok==dialog.open()){
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
				event.doit=false;
				return false;
			}
			
		} else {
			event.doit=false;
			return false;
		}
	}
	return super.keyPressed(event);
}

@Override
public boolean keyReleased(KeyEvent event) {
	
	return super.keyReleased(event);
}


}
