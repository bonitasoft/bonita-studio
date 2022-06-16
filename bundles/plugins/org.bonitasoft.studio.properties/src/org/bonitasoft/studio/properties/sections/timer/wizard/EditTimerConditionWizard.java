/**
 * Copyright (C) 2009 BonitaSoft S.A.
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

package org.bonitasoft.studio.properties.sections.timer.wizard;

import org.bonitasoft.studio.common.emf.tools.EMFModelUpdater;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.AbstractTimerEvent;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Romain Bioteau
 */
public class EditTimerConditionWizard extends Wizard {

    private TimerConditionWizardPage page;

    private Expression condition;

    private AbstractTimerEvent event;

    private IExtensibleGridPropertySectionContribution section;

    private EMFModelUpdater<Expression> emfModelUpdater;

    public EditTimerConditionWizard(AbstractTimerEvent event, TransactionalEditingDomain editingDomain,
            IExtensibleGridPropertySectionContribution section) {
        this.event = event;
        Expression condition = event.getCondition();
        if (condition == null) {
            condition = ExpressionFactory.eINSTANCE.createExpression();
            editingDomain.getCommandStack().execute(
                    SetCommand.create(editingDomain, event, ProcessPackage.Literals.ABSTRACT_TIMER_EVENT__CONDITION,
                            condition));
        }
        this.emfModelUpdater = new EMFModelUpdater<>();
        this.condition = emfModelUpdater.from(condition).getWorkingCopy();
        this.section = section;
        this.setWindowTitle(Messages.timerConditionWizardTitle);
    }

    @Override
    public void addPages() {
        page = new TimerConditionWizardPage(event, condition);
        this.addPage(page);
    }

    @Override
    public boolean performFinish() {
        emfModelUpdater.update();
        section.refresh();
        return true;
    }

}
