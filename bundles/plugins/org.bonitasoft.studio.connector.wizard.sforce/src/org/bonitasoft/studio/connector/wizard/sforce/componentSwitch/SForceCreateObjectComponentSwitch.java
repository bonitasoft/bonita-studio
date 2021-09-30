/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connector.wizard.sforce.componentSwitch;

import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Array;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionCollectionViewer;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.expression.AbstractExpression;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * @author Maxence Raoux
 * 
 */
public class SForceCreateObjectComponentSwitch extends
		AbstractSforceComponentSwitch {

	private ExpressionViewer nameExpViewer;
	private ExpressionCollectionViewer expColViewer;

	public SForceCreateObjectComponentSwitch(IWizardContainer iWizardContainer,
			Composite parent, EObject container,
			ExtendedConnectorDefinition definition,
			ConnectorConfiguration connectorConfiguration,
			EMFDataBindingContext context,
			AvailableExpressionTypeFilter connectorExpressionContentTypeFilter) {
		super(iWizardContainer, parent, container, definition,
				connectorConfiguration, context,
				connectorExpressionContentTypeFilter);
	}

	@Override
	protected ExpressionViewer createTextControl(Composite composite,
			org.bonitasoft.studio.connector.model.definition.Text object) {
		// Type d'objet
		if (object.getInputName().equals(S_OBJECT_TYPE)
				&& allSforceObject != null) {
			nameExpViewer = super.createTextControl(composite, object);
			autoCompleteToAllObjects(nameExpViewer);
			nameExpViewer
					.addSelectionChangedListener(new ISelectionChangedListener() {
						@Override
						public void selectionChanged(SelectionChangedEvent event) {
							final String sObjectName = ((Text) nameExpViewer
									.getTextControl()).getText();
							removeAllRow(expColViewer.getViewer());
							autoCompleteToField(sObjectName, expColViewer);
							List<String> mandotoryFields = sfTool
									.getMandatoryFields(sObjectName);
							for (String field : mandotoryFields) {
								addRow(expColViewer.getViewer(), field);
							}
							expColViewer.getViewer().refresh();
						}
					});
			return nameExpViewer;
		}
		return super.createTextControl(composite, object);
	}

	private void removeAllRow(TableViewer viewer) {
		final AbstractExpression expression = (AbstractExpression) viewer
				.getInput();
		((TableExpression) expression).getExpressions().clear();
		expColViewer.getViewer().refresh();
	}

	private void addRow(TableViewer viewer, String value) {
		Object expressionInput = viewer.getInput();
		Expression newExpression = null;
		if (expressionInput instanceof TableExpression) {
			ListExpression rowExp = ExpressionFactory.eINSTANCE
					.createListExpression();
			EList<Expression> expressions = rowExp.getExpressions();
			newExpression = ExpressionHelper.createConstantExpression(value,
					String.class.getName());
			expressions.add(newExpression);
			newExpression = ExpressionFactory.eINSTANCE.createExpression();
			expressions.add(newExpression);
			((TableExpression) expressionInput).getExpressions().add(rowExp);

		}
		viewer.editElement(newExpression, 0);
	}

	@Override
	protected ExpressionCollectionViewer createArrayControl(
			Composite composite, Array object) {
		if (object.getInputName().equals(FIELD_VALUES)) {
			expColViewer = super.createArrayControl(composite, object);
			expColViewer.getViewer().addSelectionChangedListener(
					new ISelectionChangedListener() {
						@Override
						public void selectionChanged(SelectionChangedEvent event) {
							autoCompleteToPickList(((Text) nameExpViewer
									.getTextControl()).getText(), expColViewer);
						}
					});
			return expColViewer;
		} else {
			return super.createArrayControl(composite, object);
		}
	}
}
