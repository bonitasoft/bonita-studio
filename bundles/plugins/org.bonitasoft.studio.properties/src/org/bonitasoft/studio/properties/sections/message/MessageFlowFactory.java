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
package org.bonitasoft.studio.properties.sections.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bonitasoft.studio.common.emf.tools.EMFModelUpdater;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MessageFlowEditPart;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest.ConnectionViewAndElementDescriptor;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

/**
 * @author Romain Bioteau
 *         Util class to factorize Message Flow manipulation
 */
public class MessageFlowFactory {

    public static void createMessageFlow(TransactionalEditingDomain editingDomain, Message event, ThrowMessageEvent source,
            AbstractCatchMessageEvent target, DiagramEditPart dep) {
        EditPart targetEP = findEditPart(dep, target);
        EditPart sourceEP = findEditPart(dep, source);

        CreateConnectionViewAndElementRequest request = new CreateConnectionViewAndElementRequest(
                ProcessElementTypes.MessageFlow_4002, ((IHintedType) ProcessElementTypes.MessageFlow_4002).getSemanticHint(),
                dep.getDiagramPreferencesHint());
        Command createMessageFlowCommand = CreateConnectionViewAndElementRequest.getCreateCommand(request, sourceEP,
                targetEP);
        if (createMessageFlowCommand.canExecute()) {
            dep.getDiagramEditDomain().getDiagramCommandStack().execute(createMessageFlowCommand);
            dep.getDiagramEditDomain().getDiagramCommandStack().flush();
            dep.refresh();

            ConnectionViewAndElementDescriptor desc = (ConnectionViewAndElementDescriptor) request.getNewObject();
            MessageFlow message = desc.getElementAdapter().getAdapter(MessageFlow.class);
            SetCommand setCommand = new SetCommand(editingDomain, message, ProcessPackage.Literals.ELEMENT__NAME,
                    event.getName());
            editingDomain.getCommandStack().execute(setCommand);
        }
    }

    public static void removeMessageFlow(TransactionalEditingDomain editingDomain, Message event, ThrowMessageEvent source,
            DiagramEditPart dep) {
        EditPart ep = findEditPart(dep, source);
        CompositeCommand command = new CompositeCommand("Remove MessageFlows");

        for (Object connection : ((AbstractGraphicalEditPart) ep).getSourceConnections()) {
            if (connection instanceof MessageFlowEditPart) {
                MessageFlowEditPart connectionPart = (MessageFlowEditPart) connection;
                MessageFlow flow = (MessageFlow) connectionPart.resolveSemanticElement();
                if (flow.getTarget().getEvent() == null || flow.getTarget().getEvent().equals(event.getName())) {
                    List<Message> events = new ArrayList<>();
                    ModelHelper.findAllEvents(source, events);
                    for (Message ev : events) {
                        if (ev.eContainer().equals(source) && !ev.equals(event)) {
                            if (ev.getTargetProcessExpression() != null && event.getTargetProcessExpression() != null
                                    && ev.getTargetProcessExpression().getContent()
                                            .equals(event.getTargetProcessExpression().getContent())
                                    && ev.getTargetElementExpression() != null
                                    && ev.getTargetElementExpression().getContent() != null
                                    && event.getTargetElementExpression() != null && ev.getTargetElementExpression()
                                            .getContent().equals(event.getTargetElementExpression().getContent())) {
                                SetCommand setCommand = new SetCommand(editingDomain, ev,
                                        ProcessPackage.Literals.MESSAGE__TARGET_PROCESS_EXPRESSION, null);
                                editingDomain.getCommandStack().execute(setCommand);
                                setCommand = new SetCommand(editingDomain, ev,
                                        ProcessPackage.Literals.MESSAGE__TARGET_ELEMENT_EXPRESSION, null);
                                editingDomain.getCommandStack().execute(setCommand);
                            }
                        }
                    }
                    SetCommand c = new SetCommand(editingDomain, flow.getTarget(),
                            ProcessPackage.Literals.ABSTRACT_CATCH_MESSAGE_EVENT__EVENT, null);
                    editingDomain.getCommandStack().execute(c);
                    command.add(new DeleteCommand(editingDomain, connectionPart.getPrimaryView()));
                    DestroyElementRequest req = new DestroyElementRequest(editingDomain,
                            connectionPart.resolveSemanticElement(), false);
                    DestroyElementCommand rmComd = new DestroyElementCommand(req);
                    command.add(rmComd);

                }
            }
        }
        dep.getDiagramEditDomain().getDiagramCommandStack().execute(new ICommandProxy(command.reduce()));
        dep.getDiagramEditDomain().getDiagramCommandStack().flush();
        dep.refresh();
    }

    public static void removeMessageFlow(TransactionalEditingDomain editingDomain, AbstractCatchMessageEvent target,
            DiagramEditPart dep) {

        EditPart ep = findEditPart(dep, target);
        CompositeCommand command = new CompositeCommand("Remove MessageFlows");

        for (Object connection : ((AbstractGraphicalEditPart) ep).getTargetConnections()) {
            if (connection instanceof MessageFlowEditPart) {
                MessageFlowEditPart connectionPart = (MessageFlowEditPart) connection;
                MessageFlow flow = (MessageFlow) connectionPart.resolveSemanticElement();
                SetCommand c = new SetCommand(editingDomain, flow.getTarget(),
                        ProcessPackage.Literals.ABSTRACT_CATCH_MESSAGE_EVENT__EVENT, null);
                editingDomain.getCommandStack().execute(c);
                command.add(new DeleteCommand(editingDomain, connectionPart.getPrimaryView()));
                DestroyElementRequest req = new DestroyElementRequest(editingDomain, flow, false);
                DestroyElementCommand rmComd = new DestroyElementCommand(req);
                command.add(rmComd);
            }
        }

        dep.getDiagramEditDomain().getDiagramCommandStack().execute(new ICommandProxy(command.reduce()));
        dep.getDiagramEditDomain().getDiagramCommandStack().flush();
        dep.refresh();
    }

    @SuppressWarnings("unchecked")
    public static void editMessageFlowName(Message message, AbstractCatchMessageEvent target, DiagramEditPart dep) {
        EditPart targetEP = findEditPart(dep, target);
        Optional<MessageFlowEditPart> connectionPart = ((AbstractGraphicalEditPart) targetEP).getTargetConnections().stream()
                .filter(MessageFlowEditPart.class::isInstance)
                .map(MessageFlowEditPart.class::cast)
                .findFirst();
        if (connectionPart.isPresent()) {
            MessageFlow messageFlowToEdit = (MessageFlow) connectionPart.get().resolveSemanticElement();
            EMFModelUpdater<MessageFlow> updater = new EMFModelUpdater<>();
            MessageFlow workingCopy = updater.from(messageFlowToEdit).getWorkingCopy();
            workingCopy.setName(message.getName());
            updater.update();
            dep.refresh();
        }
    }

    public static void removeMessageFlow(TransactionalEditingDomain editingDoamin, List<Message> events,
            ThrowMessageEvent source, DiagramEditPart dep) {

        for (Message ev : events) {
            removeMessageFlow(editingDoamin, ev, source, dep);
        }
    }

    private static EditPart findEditPart(EditPart diagramEditPart, Element elementToFind) {
        return GMFTools.findEditPart(diagramEditPart, elementToFind);
    }
}
