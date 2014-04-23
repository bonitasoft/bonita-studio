/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.diagram.tools;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.diagram.refactoring.RemoveWidgetReferencesOperation;
import org.bonitasoft.studio.common.dialog.OutlineDialog;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.form.Widget;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.parts.DiagramGraphicalViewerKeyHandler;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author aurelie Zara
 * 
 */
@SuppressWarnings("restriction")
public class CustomDiagramGraphicalViewerKeyHandler extends
        DiagramGraphicalViewerKeyHandler {

    public CustomDiagramGraphicalViewerKeyHandler(GraphicalViewer viewer) {
        super(viewer);
    }

    @Override
    public boolean keyPressed(KeyEvent event) {
        if (event.keyCode == 127) {
            GraphicalEditPart part = (GraphicalEditPart) getFocusEditPart();
            Widget widget = (Widget) part.resolveSemanticElement();
            String[] buttonList = { IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL };
            List<Object> widgetSelected = new ArrayList<Object>();
            widgetSelected.add(widget);
            OutlineDialog dialog = new OutlineDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.deleteFormFieldDialogTitle,
                    Display.getCurrent().getSystemImage(SWT.ICON_WARNING), Messages.bind(Messages.askConfirmationForDeleting, widget.getName()),
                    MessageDialog.CONFIRM, buttonList, 1, widgetSelected);
            int ok = 0;
            RemoveWidgetReferencesOperation op = new RemoveWidgetReferencesOperation(ModelHelper.getPageFlow(widget), widget);
            if (ok == dialog.open()) {
                CompoundCommand cc = new CompoundCommand();
                op.setCompoundCommand(cc);
                op.setEditingDomain(AdapterFactoryEditingDomain.getEditingDomainFor(widget));
                IProgressService service = PlatformUI.getWorkbench().getProgressService();
                try {
                    service.busyCursorWhile(op);
                } catch (InvocationTargetException e) {
                    BonitaStudioLog.error(e);
                } catch (InterruptedException e) {
                    BonitaStudioLog.error(e);
                }
                if (!op.canExecute()) {
                    event.doit = false;
                    return false;
                }

            } else {
                event.doit = false;
                return false;
            }
        }
        return super.keyPressed(event);
    }

}
