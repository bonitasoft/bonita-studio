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

package org.bonitasoft.studio.properties.sections.message.wizards;

import org.bonitasoft.studio.common.emf.tools.EMFModelUpdater;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.properties.sections.message.ThrowEventSection;
import org.bonitasoft.studio.properties.sections.message.commands.AbstractMessageCommand;
import org.bonitasoft.studio.properties.sections.message.commands.AddMessageCommand;
import org.bonitasoft.studio.properties.sections.message.commands.UpdateMessageCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.views.properties.tabbed.ISection;

/**
 * @author Romain Bioteau
 */
public class UpdateMessageEventWizard extends Wizard implements IWizard {

    private final TransactionalEditingDomain editingDomain;
    private AddMessageEventWizardPage page;
    protected ThrowMessageEvent element;
    private final ISection callingSection;
    protected Message originalMessage;
    protected Message workingCopyMessage;
    protected MainProcess diagram;
    private EMFModelUpdater<Message> emfModelUpdater = new EMFModelUpdater();
    private boolean creationMode = false;

    public UpdateMessageEventWizard(MainProcess diagram, ThrowMessageEvent element, TransactionalEditingDomain editingDomain,
            ISection callingSection) {
        this.editingDomain = editingDomain;
        this.element = element;
        this.callingSection = callingSection;
        setWindowTitle(Messages.messageEventAddWizardPageTitle);
        this.diagram = diagram;
    }

    public UpdateMessageEventWizard(MainProcess diagram, ThrowMessageEvent element, Message firstElement,
            TransactionalEditingDomain editingDomain, ThrowEventSection throwEventSection) {
        this(diagram, element, editingDomain, throwEventSection);
        originalMessage = firstElement;
    }

    @Override
    public void addPages() {
        if (originalMessage == null) {
            creationMode = true;
            originalMessage = ProcessFactory.eINSTANCE.createMessage();
        }
        workingCopyMessage = emfModelUpdater.from(originalMessage).getWorkingCopy();
        page = createAddMessageEventWizardPage();
        addPage(page);
    }

    protected AddMessageEventWizardPage createAddMessageEventWizardPage() {
        return new AddMessageEventWizardPage(diagram, element, originalMessage, workingCopyMessage);
    }

    @Override
    public boolean performFinish() {
        AbstractMessageCommand command = creationMode ? addCommand() : updateCommand();
        editingDomain.getCommandStack().execute(command);
        callingSection.refresh();
        return true;
    }

    private AddMessageCommand addCommand() {
        return new AddMessageCommand(
                editingDomain,
                element,
                originalMessage,
                workingCopyMessage);
    }

    private UpdateMessageCommand updateCommand() {
        return new UpdateMessageCommand(
                editingDomain,
                element,
                originalMessage,
                workingCopyMessage,
                emfModelUpdater);
    }

    @Override
    public boolean performCancel() {
        callingSection.refresh();
        return super.performCancel();
    }

}
