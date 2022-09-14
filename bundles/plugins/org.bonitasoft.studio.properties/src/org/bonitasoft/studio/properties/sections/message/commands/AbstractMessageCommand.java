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

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;
import org.bonitasoft.studio.properties.sections.message.MessageFlowFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;

public abstract class AbstractMessageCommand extends RecordingCommand {

    protected TransactionalEditingDomain domain;
    protected Message message;
    protected ThrowMessageEvent throwMessageEvent;
    protected Message messageWorkingCopy;

    public AbstractMessageCommand(TransactionalEditingDomain domain, ThrowMessageEvent throwMessageEvent, Message message,
            Message messageWorkingCopy) {
        super(domain);
        this.domain = domain;
        this.message = message;
        this.throwMessageEvent = throwMessageEvent;
        this.messageWorkingCopy = messageWorkingCopy;
    }

    protected Optional<AbstractCatchMessageEvent> findCatchEvent(Message message) {
        if (message.getTargetProcessExpression() == null || message.getTargetElementExpression() == null) {
            return Optional.empty();
        }
        AbstractProcess process = ModelHelper.findProcess(message, message.getTargetProcessExpression().getName());
        return process != null
                ? ModelHelper.getAllCatchEvent(process).stream().filter(
                        catchEvent -> Objects.equals(catchEvent.getName(),
                                message.getTargetElementExpression().getContent()))
                        .findFirst()
                : Optional.empty();
    }

    protected void createMessageFlow(Message message, DiagramEditor editor, AbstractCatchMessageEvent catchEvent) {
        if (isOnDiagram(catchEvent)) {
            MessageFlowFactory.createMessageFlow(domain, message, throwMessageEvent, catchEvent,
                    editor.getDiagramEditPart());
        }
    }

    protected boolean isOnDiagram(AbstractCatchMessageEvent ev) {
        return ev.eResource().equals(throwMessageEvent.eResource());
    }

}
