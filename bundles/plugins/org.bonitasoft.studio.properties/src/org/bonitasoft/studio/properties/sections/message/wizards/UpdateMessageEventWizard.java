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

package org.bonitasoft.studio.properties.sections.message.wizards;

import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.properties.sections.message.MessageFlowFactory;
import org.bonitasoft.studio.properties.sections.message.ThrowEventSection;
import org.bonitasoft.studio.properties.sections.message.commands.UpdateMessageCommand;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.ISection;

/**
 * @author Romain Bioteau
 *
 */
public class UpdateMessageEventWizard extends Wizard implements IWizard {

    private final TransactionalEditingDomain editingDomain;
    private AddMessageEventWizardPage page;
    protected ThrowMessageEvent element;
    private final ISection callingSection;
    protected Message originalMessage ;
    protected Message workingCopyMessage ;
    protected MainProcess diagram;

    public UpdateMessageEventWizard(MainProcess diagram,ThrowMessageEvent element , TransactionalEditingDomain editingDomain,ISection callingSection){
        this.editingDomain = editingDomain ;
        this.element = element ;
        this.callingSection = callingSection ;
        setWindowTitle(Messages.messageEventAddWizardPageTitle);
        this.diagram=diagram;
    }

    public UpdateMessageEventWizard(MainProcess diagram,ThrowMessageEvent element, Message firstElement,TransactionalEditingDomain editingDomain,ThrowEventSection throwEventSection) {
        this(diagram,element,editingDomain,throwEventSection);
        originalMessage = firstElement ;
        workingCopyMessage = EcoreUtil.copy(originalMessage);
    }

    @Override
    public void addPages() {
        if(workingCopyMessage == null){
            workingCopyMessage = ProcessFactory.eINSTANCE.createMessage();
        }
        page = createAddMessageEventWizardPage();
        addPage(page);
    }

    protected AddMessageEventWizardPage createAddMessageEventWizardPage() {
        return new AddMessageEventWizardPage(diagram,element, originalMessage, workingCopyMessage);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        IUndoableOperation operation = new UpdateMessageCommand(
                editingDomain,
                element,
                originalMessage,
                EcoreUtil.copy(workingCopyMessage),
                callingSection);
        try {
            OperationHistoryFactory.getOperationHistory().execute(operation, new NullProgressMonitor(), null);
        } catch (ExecutionException e) {
            BonitaStudioLog.error(e);
        }

        //page.databindingContext.updateModels();

        if(originalMessage != null){
            updateMessageFlows(originalMessage);
        } else {
            updateMessageFlows(workingCopyMessage);
        }

        callingSection.refresh();
        return true;
    }

    protected void updateMessageFlows(Message eventObject) {
        DiagramEditor editor = (DiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() ;
        List<AbstractCatchMessageEvent> events = ModelHelper.findAllCatchEventsCatching(ModelHelper.getMainProcess(element),eventObject.getName()) ;
        for(AbstractCatchMessageEvent ev : events){
            SetCommand setCommand = new SetCommand(editingDomain, ev, ProcessPackage.Literals.ABSTRACT_CATCH_MESSAGE_EVENT__EVENT, null) ;
            editingDomain.getCommandStack().execute(setCommand) ;
            if(isOnDiagram(ev)){
                MessageFlowFactory.removeMessageFlow(editingDomain, eventObject, ev, editor.getDiagramEditPart()) ;
            }
        }

        for(AbstractCatchMessageEvent catchEvent : events){
        	if(eventObject.getTargetElementExpression() != null && catchEvent.getName().equals(eventObject.getTargetElementExpression().getContent())) { 
        		if(eventObject.getTargetProcessExpression() != null && ModelHelper.getParentProcess(catchEvent).getName().equals(eventObject.getTargetProcessExpression().getContent())){
        			if(isOnDiagram(catchEvent)){
        				MessageFlowFactory.createMessageFlow(editingDomain,eventObject,element,catchEvent,editor.getDiagramEditPart()) ;
        			}
        		}
        	}
        }
    }

    @Override
    public boolean performCancel() {
        //		IUndoableOperation operation = new UpdateMessageCommand(
        //				editingDomain,
        //				element,
        //				originalMessage,
        //				workingCopyMessage,
        //				callingSection
        //		);
        //		try {
        //			OperationHistoryFactory.getOperationHistory().execute(operation, new NullProgressMonitor(), null);
        //		} catch (ExecutionException e) {
        //			BonitaStudioLog.log(e);
        //		}

        callingSection.refresh();
        return super.performCancel();
    }


    private boolean isOnDiagram(AbstractCatchMessageEvent ev) {
        return ev.eResource().equals(element.eResource());
    }

}
