/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
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

package org.bonitasoft.studio.properties.sections.message.commands;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 */
public class AddMessageCommand extends AbstractMessageCommand {

    public AddMessageCommand(TransactionalEditingDomain domain, ThrowMessageEvent throwMessageEvent, Message message,
            Message messageWorkingCopy) {
        super(domain, throwMessageEvent, message, messageWorkingCopy);
    }

    @Override
    protected void doExecute() {
        message.setName(messageWorkingCopy.getName());
        message.setDocumentation(messageWorkingCopy.getDocumentation());
        message.setThrowEvent(messageWorkingCopy.getThrowEvent());
        message.setTtl(messageWorkingCopy.getTtl());
        message.setTargetElementExpression(messageWorkingCopy.getTargetElementExpression());

        Expression targetProcessName = messageWorkingCopy.getTargetProcessExpression();
        if (targetProcessName != null && targetProcessName.getContent() != null
                && !targetProcessName.getContent().isEmpty()) {
            for (AbstractCatchMessageEvent ev : ModelHelper
                    .getAllCatchEvent(ModelHelper.getMainProcess(throwMessageEvent))) {
                if (messageWorkingCopy.getTargetProcessExpression() != null && ModelHelper.getParentProcess(ev).getName()
                        .equals(messageWorkingCopy.getTargetProcessExpression().getContent())) {
                    if (ev.getName().equals(message.getTargetElementExpression().getContent())) {
                        ev.setEvent(message.getName());
                    }
                }
            }
        }
        message.setTargetProcessExpression(EcoreUtil.copy(targetProcessName));
        message.setCorrelation(messageWorkingCopy.getCorrelation());
        message.setMessageContent(messageWorkingCopy.getMessageContent());

        throwMessageEvent.getEvents().add(message);

        DiagramEditor editor = (DiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .getActiveEditor();
        findCatchEvent(message)
                .ifPresent(catchEvent -> createMessageFlow(messageWorkingCopy, editor, catchEvent));
    }

}
