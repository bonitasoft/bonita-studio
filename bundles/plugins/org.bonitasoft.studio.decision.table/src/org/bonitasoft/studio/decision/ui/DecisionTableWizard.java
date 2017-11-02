/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.decision.ui;

import org.bonitasoft.studio.decision.i18n.Messages;
import org.bonitasoft.studio.decision.ui.condition.TakeTransitionLabelProvider;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.decision.DecisionFactory;
import org.bonitasoft.studio.model.process.decision.DecisionTable;
import org.bonitasoft.studio.model.process.decision.DecisionTableAction;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IElementComparer;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Mickael Istria
 *
 */
public class DecisionTableWizard extends Wizard {

	public DecisionTable table;
	private Element container;
	private DecisionTableAction[] lineActions;
	private IBaseLabelProvider actionLabelProvider;
	private DecisionTableAction[] defaultTableActions;
	private IElementComparer actionComparer;
	private DecisionTable oldTable;
	
	public DecisionTableWizard(Element container, DecisionTable table) {
		this.container = container;
		setTable(table);
		setWindowTitle(Messages.wizardPageTitle);
	}
	
	private void setTable(DecisionTable table) {
		if (table != null) {
			this.oldTable = table ;
			this.table = EcoreUtil.copy(table);
		} else {
			this.table = DecisionFactory.eINSTANCE.createDecisionTable();
		}
	}
	
	@Override
	public void addPages() {
		addPage(new DecisionTableWizardPage(this, container, table));
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		return true;
	}
	
	@Override
	public boolean performCancel() {
		table = oldTable ;
		return super.performCancel();
	}

	/**
	 * @return
	 */
	public DecisionTable getDecisionTable() {
		return table;
	}

	/**
	 * @return
	 */
	public DecisionTableAction[] getPossibleLineActions() {
		return this.lineActions;
	}
	
	/**
	 * @return
	 */
	public DecisionTableAction[] getPossibleDefaultTableActions() {
		return this.defaultTableActions;
	}

	/**
	 * @return
	 */
	public IBaseLabelProvider getActionLabelProvider() {
		return this.actionLabelProvider;
	}

	/**
	 * @param decisionTableActions
	 */
	public void setAvailableLineActions(DecisionTableAction[] lineActions) {
		this.lineActions = lineActions;
	}

	/**
	 * @param decisionTableActions
	 */
	public void setAvailableTableActions(DecisionTableAction[] defaultTableActions) {
		this.defaultTableActions = defaultTableActions;
	}

	/**
	 * @param actionLabelProvider
	 */
	public void setActionsLabelProvider(TakeTransitionLabelProvider actionLabelProvider) {
		this.actionLabelProvider = actionLabelProvider;
	}

	public void setActionsComparer(IElementComparer comparer) {
		this.actionComparer = comparer;
	}
	
	/**
	 * @return
	 */
	public IElementComparer getActionsComparer() {
		return this.actionComparer;
	}

}
