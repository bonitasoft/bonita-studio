/**
 * Copyright (C) 2010-2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.diagram.custom.handlers;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.ui.action.actions.global.GlobalActionManager;
import org.eclipse.gmf.runtime.common.ui.action.global.GlobalAction;
import org.eclipse.gmf.runtime.common.ui.action.global.GlobalActionId;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author Aurelien Pupier
 */
public class DeleteHandler extends AbstractHandler {

    private final List<Lane> lanes = new ArrayList<>();

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        if (part instanceof DiagramEditor) {
            final IStructuredSelection currentSelection = (IStructuredSelection) ((DiagramEditor) part).getDiagramGraphicalViewer().getSelection();
            if (currentSelection.getFirstElement() instanceof IGraphicalEditPart) {
                lanes.clear();
                boolean containsPool = false;
                boolean isMessageFlow = false;
                MessageFlow flow = null;
                final List<IGraphicalEditPart> newSelection = new ArrayList<IGraphicalEditPart>();
                for (final Object item : currentSelection.toArray()) {
                    final EObject semanticElement = ((IGraphicalEditPart) item).resolveSemanticElement();
                    if (semanticElement instanceof Pool) {
                        containsPool = true;
                    }
                    if (semanticElement instanceof Lane) {
                        lanes.add((Lane) semanticElement);
                    }
                    if (semanticElement instanceof MessageFlow) {
                        isMessageFlow = true;
                        flow = (MessageFlow) semanticElement;
                    }
                    if (item instanceof ShapeCompartmentEditPart) {
                        newSelection.add((IGraphicalEditPart) ((IGraphicalEditPart) item).getParent());
                    } else {
                        if (!(item instanceof LabelEditPart)) {
                            newSelection.add((IGraphicalEditPart) item);
                        } else {
                            newSelection.add((IGraphicalEditPart) ((LabelEditPart) item).getParent());
                        }

                    }
                }
                ((DiagramEditor) part).getDiagramGraphicalViewer().setSelection(new StructuredSelection(newSelection));
                GlobalAction actionHandler = GlobalActionManager.getInstance().createActionHandler(part, GlobalActionId.DELETE);
                if (containsPool) {
                    if (MessageDialog.openQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.deleteDialogTitle,
                            Messages.deleteDialogMessage)) {
                        upadateLaneItems();
                        actionHandler.refresh();
                        actionHandler.run();
                    }
                } else {
                    if (isMessageFlow) {
                        if (MessageDialog.openQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.deleteDialogTitle,
                                Messages.bind(Messages.deleteMessageFlow, flow.getName()))) {
                            removeMessage(flow);
                            actionHandler.refresh();
                            actionHandler.run();
                        }
                    } else {
                        actionHandler.refresh();
                        actionHandler.run();
                    }
                }

            }
        }
        return null;
    }

    private void upadateLaneItems() {
        final CompoundCommand cc = new CompoundCommand();
        for (final Lane l : lanes) {
            final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(l);
            for (final EObject task : ModelHelper.getAllItemsOfType(l, ProcessPackage.Literals.TASK)) {
                cc.append(SetCommand.create(domain, task, ProcessPackage.Literals.TASK__OVERRIDE_ACTORS_OF_THE_LANE, true));
            }
            domain.getCommandStack().execute(cc);
        }
    }


    public void removeMessage(final MessageFlow flow) {
        final MainProcess diagram = ModelHelper.getMainProcess(flow);
        Assert.isNotNull(diagram);
        final AbstractCatchMessageEvent catchEvent = flow.getTarget();
        final EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(diagram);
        Assert.isNotNull(domain);
        final CompoundCommand cc = new CompoundCommand();
        if (flow.getSource() != null) {
            final List<Message> messages = flow.getSource().getEvents();
            for (final Message message : messages) {
                if (flow.getName().equals(message.getName())) {
                    cc.append(DeleteCommand.create(domain, message));
                    break;
                }
            }
        }
        cc.append(SetCommand.create(domain, catchEvent, ProcessPackage.Literals.ABSTRACT_CATCH_MESSAGE_EVENT__EVENT, null));
        domain.getCommandStack().execute(cc);
    }

    /**
     * disable for MainProcess
     * 
     * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        final IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        final IStructuredSelection currentSelection = ((IStructuredSelection) part.getSite().getSelectionProvider().getSelection());
        if (currentSelection.getFirstElement() instanceof IGraphicalEditPart) {
            if ((currentSelection.getFirstElement() instanceof MainProcessEditPart)) {
                return false;
            }
        }
        return super.isEnabled();
    }
}
