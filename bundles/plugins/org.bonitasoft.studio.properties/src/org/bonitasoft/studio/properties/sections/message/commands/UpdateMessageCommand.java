/**
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.sections.message.commands;

import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.common.emf.tools.EMFModelUpdater;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;
import org.bonitasoft.studio.properties.sections.message.MessageFlowFactory;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.ui.PlatformUI;

public class UpdateMessageCommand extends AbstractMessageCommand {

    private EMFModelUpdater<Message> emfModelUpdater;

    public UpdateMessageCommand(TransactionalEditingDomain domain, ThrowMessageEvent throwMessageEvent, Message message,
            Message messageWorkingCopy, EMFModelUpdater<Message> emfModelUpdater) {
        super(domain, throwMessageEvent, message, messageWorkingCopy);
        this.emfModelUpdater = emfModelUpdater;
    }

    @Override
    protected void doExecute() {
        Optional<AbstractCatchMessageEvent> oldCatchMessageEvent = findCatchEvent(message);
        emfModelUpdater.update();
        DiagramEditor editor = (DiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .getActiveEditor();
        if (oldCatchMessageEvent.isPresent()) {
            updateMessageFlows(message, oldCatchMessageEvent.get(), editor);
        } else {
            findCatchEvent(message).ifPresent(newCatchEvent -> createMessageFlow(message, editor, newCatchEvent));
        }
    }

    private void updateMessageFlows(Message message, AbstractCatchMessageEvent oldCatchMessageEvent, DiagramEditor editor) {
        if (shouldReplaceMessageFlow(message, oldCatchMessageEvent)) {
            removeMessageFlow(editor, oldCatchMessageEvent);
            findCatchEvent(message)
                    .ifPresent(newCatchEvent -> createMessageFlow(message, editor, newCatchEvent));
        } else {
            editMessageFlow(message, editor, oldCatchMessageEvent);
        }
    }

    private boolean shouldReplaceMessageFlow(Message message, AbstractCatchMessageEvent catchEvent) {
        boolean sameElement = message.getTargetElementExpression() != null
                && Objects.equals(catchEvent.getName(), message.getTargetElementExpression().getContent());
        boolean sameProcess = message.getTargetProcessExpression() != null
                && Objects.equals(ModelHelper.getParentProcess(catchEvent).getName(),
                        message.getTargetProcessExpression().getContent());
        return !sameElement || !sameProcess;
    }

    private void removeMessageFlow(DiagramEditor editor, AbstractCatchMessageEvent catchEvent) {
        if (isOnDiagram(catchEvent)) {
            MessageFlowFactory.removeMessageFlow(domain, catchEvent, editor.getDiagramEditPart());
        }
    }

    private void editMessageFlow(Message message, DiagramEditor editor, AbstractCatchMessageEvent catchEvent) {
        if (isOnDiagram(catchEvent)) {
            MessageFlowFactory.editMessageFlowName(message, catchEvent, editor.getDiagramEditPart());
            SetCommand updateCatchMessageEventCommand = new SetCommand(domain, catchEvent,
                    ProcessPackage.Literals.ABSTRACT_CATCH_MESSAGE_EVENT__EVENT, message.getName());
            domain.getCommandStack().execute(updateCatchMessageEventCommand);
        }
    }

}
