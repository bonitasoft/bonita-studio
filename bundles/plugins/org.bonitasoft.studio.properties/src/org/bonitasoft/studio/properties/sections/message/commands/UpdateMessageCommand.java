/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.properties.sections.message.commands;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.ui.views.properties.tabbed.ISection;

/**
 * @author Romain Bioteau
 *
 */
public class UpdateMessageCommand extends AbstractTransactionalCommand {

    private Message originalMessage ;
    private final ThrowMessageEvent element;
    private final ISection sectionTorefresh;
    private final Message workingCopymessage;

    public UpdateMessageCommand(TransactionalEditingDomain domain, ThrowMessageEvent element,Message originalMessage, Message workingCopymessage, ISection callingSection){
        super(domain, "Add EventObject Command", getWorkspaceFiles(element));
        if(originalMessage == null){//Creation case
            this.originalMessage = ProcessFactory.eINSTANCE.createMessage();
        } else {
            this.originalMessage = originalMessage ;
        }
        this.element = element ;
        this.workingCopymessage = workingCopymessage;
        sectionTorefresh = callingSection;
    }

    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
            IAdaptable info) throws ExecutionException {
        originalMessage.setName(workingCopymessage.getName());
        originalMessage.setDocumentation(workingCopymessage.getDocumentation());
        originalMessage.setThrowEvent(workingCopymessage.getThrowEvent());
        originalMessage.setTtl(workingCopymessage.getTtl());
        originalMessage.setTargetElementExpression(workingCopymessage.getTargetElementExpression());

        Expression targetProcessName = workingCopymessage.getTargetProcessExpression();
        if(targetProcessName  != null && targetProcessName.getContent() != null && !targetProcessName.getContent().isEmpty()){
        	for(AbstractCatchMessageEvent ev : ModelHelper.getAllCatchEvent(ModelHelper.getMainProcess(element))){
        		if(workingCopymessage.getTargetProcessExpression() != null && ModelHelper.getParentProcess(ev).getName().equals(workingCopymessage.getTargetProcessExpression().getContent())){
        			if(ev.getName().equals(originalMessage.getTargetElementExpression().getContent())){
        				ev.setEvent(originalMessage.getName()) ;
        			}
        		}
        	}
        }
        originalMessage.setTargetProcessExpression(EcoreUtil.copy(targetProcessName));
        originalMessage.setCorrelation(workingCopymessage.getCorrelation());
        originalMessage.setMessageContent(workingCopymessage.getMessageContent());

        element.getEvents().add(originalMessage);
        return CommandResult.newOKCommandResult(originalMessage);
    }

    @Override
    protected void didUndo(Transaction tx) {
        super.didUndo(tx);
        if(sectionTorefresh != null){
            sectionTorefresh.refresh();
        }
    }

    @Override
    protected void didRedo(Transaction tx) {
        super.didRedo(tx);
        if(sectionTorefresh != null){
            sectionTorefresh.refresh();
        }
    }

}
