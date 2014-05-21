/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
 
package org.bonitasoft.studio.properties.sections.message.commands;

import java.util.List;

import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.ui.views.properties.tabbed.ISection;

/**
 * @author Romain Bioteau
 *
 */
public class DeleteMessageCommand extends AbstractTransactionalCommand {
	
	private ThrowMessageEvent element;
	private List<Message> eventsToDelete;
	private ISection callingSection;

	public DeleteMessageCommand(TransactionalEditingDomain domain, ThrowMessageEvent element , List<Message> eventsToDelete, ISection callingSection){
		super(domain, "Delete Events", getWorkspaceFiles(element));
		this.element = element ;
		this.eventsToDelete = eventsToDelete ;
		this.callingSection = callingSection;
	}
	
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		for(Message event : eventsToDelete){
			
			for(MessageFlow mes : element.getOutgoingMessages()){
				if(mes.getName().equals(event.getName())){
					mes.setName(""); 
					if(mes.getTarget()!= null){
						mes.getTarget().setEvent(""); //$NON-NLS-1$
					}
					break ;
				}
			}			
			element.getEvents().remove(event);
		}
		return CommandResult.newOKCommandResult(eventsToDelete);
	}
	
	@Override
	protected void didUndo(Transaction tx) {
		super.didUndo(tx);
		if(callingSection != null){
			callingSection.refresh();
		}
	}
	
	@Override
	protected void didRedo(Transaction tx) {
		super.didRedo(tx);
		if(callingSection != null){
			callingSection.refresh();
		}
	}
}
