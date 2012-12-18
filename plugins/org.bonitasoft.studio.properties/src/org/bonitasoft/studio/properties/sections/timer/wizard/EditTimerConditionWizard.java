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

package org.bonitasoft.studio.properties.sections.timer.wizard;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractTimerEvent;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.StartTimerEvent;
import org.bonitasoft.studio.model.process.StartTimerScriptType;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;

/**
 * @author Romain Bioteau
 * 
 */
public class EditTimerConditionWizard extends Wizard {

	private WizardPage page;
	private Expression condition;
	private AbstractTimerEvent event;
	private TransactionalEditingDomain editingDomain;
	private IExtensibleGridPropertySectionContribution section;

	public EditTimerConditionWizard(AbstractTimerEvent event, TransactionalEditingDomain editingDomain,
			IExtensibleGridPropertySectionContribution section) {
		this.event = event;
		this.condition = EcoreUtil.copy(event.getCondition());
		this.editingDomain = editingDomain;
		this.section = section;
		this.setWindowTitle(Messages.timerConditionWizardTitle);
	}
	
	

	@Override
	public void addPages() {
		if (event instanceof StartTimerEvent
				&& !ModelHelper.isInEvenementialSubProcessPool(event)) {
			page = new StartTimerConditionWizardPage(event, condition);
		} else {
			page = new TimerConditionWizardPage(event, condition);
		}
		this.addPage(page);
	}

	@Override
	public boolean performFinish() {
		page.setErrorMessage(null);
		if (page instanceof TimerConditionWizardPage) {
			editingDomain.getCommandStack().execute(
					new SetCommand(editingDomain, event, ProcessPackage.Literals.ABSTRACT_TIMER_EVENT__CONDITION, ((TimerConditionWizardPage) page)
							.getCondition()));
		} else {
			StartTimerConditionWizardPage startTimerPage = (StartTimerConditionWizardPage) page;
			StartTimerScriptType scriptType = startTimerPage.getScriptType();
			CompoundCommand command = new CompoundCommand();
			command.append(new SetCommand(editingDomain, event, ProcessPackage.Literals.START_TIMER_EVENT__SCRIPT_TYPE, scriptType));
			command.append(new SetCommand(editingDomain, event, ProcessPackage.Literals.ABSTRACT_TIMER_EVENT__CONDITION, startTimerPage.getCondition()));
			command.append(new SetCommand(editingDomain, event, ProcessPackage.Literals.START_TIMER_EVENT__DAY_NUMBER, startTimerPage.getDayNumber()));
			command.append(new SetCommand(editingDomain, event, ProcessPackage.Literals.START_TIMER_EVENT__DAY, startTimerPage.getDay()));
			command.append(new SetCommand(editingDomain, event, ProcessPackage.Literals.START_TIMER_EVENT__MONTH, startTimerPage.getMonth()));
			command.append(new SetCommand(editingDomain, event, ProcessPackage.Literals.START_TIMER_EVENT__HOURS, startTimerPage.getHours()));
			command.append(new SetCommand(editingDomain, event, ProcessPackage.Literals.START_TIMER_EVENT__MINUTES, startTimerPage.getMinutes()));
			command.append(new SetCommand(editingDomain, event, ProcessPackage.Literals.START_TIMER_EVENT__FROM, startTimerPage.getFromDate()));
			command.append(new SetCommand(editingDomain, event, ProcessPackage.Literals.START_TIMER_EVENT__AT, startTimerPage.getToDate()));
			if(page.getErrorMessage() != null){
				return false;
			}
			editingDomain.getCommandStack().execute(command);
		}
		section.refresh();
		return true;
	}

}
