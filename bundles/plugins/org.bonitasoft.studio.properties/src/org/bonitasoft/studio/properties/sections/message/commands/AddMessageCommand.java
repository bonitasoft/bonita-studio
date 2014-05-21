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
public class AddMessageCommand extends AbstractTransactionalCommand {

    private final String name ;
    private final String throwEvent ;
    private final ThrowMessageEvent element;
    private final String description;
    private final Message event;
    private final String ttl;
    private final Expression targetElementName;
    private final Expression targetProcessName;
    private final ISection callingSection;

    public AddMessageCommand(TransactionalEditingDomain domain, ThrowMessageEvent element,Message eventObject, String name, String label,String description, String throwEvent, String ttl, Expression targetElementName, Expression targetProcessName, ISection callingSection){
        super(domain, "Add EventObject Command",getWorkspaceFiles(eventObject));
        this.element = element ;
        event = eventObject ;
        this.name = name ;
        this.description = description ;
        this.throwEvent = throwEvent ;
        this.ttl = ttl ;
        this.targetElementName = targetElementName ;
        this.targetProcessName = targetProcessName ;
        this.callingSection = callingSection;
    }

    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
            IAdaptable info) throws ExecutionException {
        event.setName(name);
        event.setDocumentation(description);
        event.setThrowEvent(throwEvent);
        event.setTtl(ttl);
        event.setTargetElementExpression(targetElementName);

        for(AbstractCatchMessageEvent ev : ModelHelper.getAllCatchEvent(ModelHelper.getMainProcess(element))){
            if(ev.getName().equals(targetElementName)){
                ev.setEvent(name) ;
            }
        }

        event.setTargetProcessExpression(targetProcessName);
        element.getEvents().add(event);


        return CommandResult.newOKCommandResult(event);
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
