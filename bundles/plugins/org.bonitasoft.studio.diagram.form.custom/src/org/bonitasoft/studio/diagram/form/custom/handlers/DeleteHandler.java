/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.diagram.form.custom.handlers;

import static org.bonitasoft.studio.common.Messages.removalConfirmationDialogTitle;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.diagram.refactoring.RemoveWidgetReferencesOperation;
import org.bonitasoft.studio.common.dialog.OutlineDialog;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormEditPart;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.ui.action.actions.global.GlobalActionManager;
import org.eclipse.gmf.runtime.common.ui.action.global.GlobalAction;
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

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        if (part instanceof DiagramEditor) {
            final List<?> selectedEps = ((DiagramEditor) part).getDiagramGraphicalViewer().getSelectedEditParts();
            if (!selectedEps.isEmpty()) {
                final Object ep = selectedEps.get(0);
                if (ep instanceof IGraphicalEditPart) {
                    final EObject semanticElement = ((IGraphicalEditPart) ep).resolveSemanticElement();
                    if (semanticElement instanceof Widget) {
                        final String[] buttonList = { IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL };
                        final List<Object> widgetSelected = new ArrayList<Object>();
                        widgetSelected.add(semanticElement);
                        final OutlineDialog dialog = new OutlineDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                                removalConfirmationDialogTitle, Display.getCurrent().getSystemImage(SWT.ICON_WARNING), Messages.bind(
                                        Messages.askConfirmationForDeleting, ((Element) semanticElement).getName()), MessageDialog.CONFIRM, buttonList, 1,
                                widgetSelected);
                        final int ok = 0;
                        final RemoveWidgetReferencesOperation op = new RemoveWidgetReferencesOperation(ModelHelper.getPageFlow((Widget) semanticElement),
                                (Widget) semanticElement);
                        op.setEditingDomain(TransactionUtil.getEditingDomain(semanticElement));
                        if (dialog.open() == ok) {
                            final IProgressService service = PlatformUI.getWorkbench().getProgressService();
                            try {
                                service.busyCursorWhile(op);
                            } catch (final InvocationTargetException e) {
                                BonitaStudioLog.error(e);
                            } catch (final InterruptedException e) {
                                BonitaStudioLog.error(e);
                            }
                            if (!op.isCancelled()) {
                                final GlobalAction handler = GlobalActionManager.getInstance().createActionHandler(part, GlobalActionId.DELETE);
                                if (handler.isRunnable()) {
                                    handler.run();
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * disable for Form
     *
     * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        final IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        final IStructuredSelection currentSelection = (IStructuredSelection) part.getSite().getSelectionProvider().getSelection();
        if (currentSelection.getFirstElement() instanceof IGraphicalEditPart) {
            if (currentSelection.getFirstElement() instanceof FormEditPart) {
                return false;
            }
        }
        return super.isEnabled();
    }
}
