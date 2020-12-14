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

import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.definition.Array;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.provider.ExpressionLabelProvider;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionCollectionViewer;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * @author Maxence Raoux
 * 
 */
public class SForceUpdatesObjectComponentSwitch extends
		AbstractSforceComponentSwitch {

	private ExpressionViewer nameExpViewer;
	private ExpressionCollectionViewer expColViewer;
	private ExpressionViewer idExpViewer;

	public SForceUpdatesObjectComponentSwitch(
			IWizardContainer iWizardContainer, Composite parent,
			EObject container, ConnectorDefinition definition,
			ConnectorConfiguration connectorConfiguration,
			EMFDataBindingContext context,
			DefinitionResourceProvider messageProvider,
			AvailableExpressionTypeFilter connectorExpressionContentTypeFilter) {
		super(iWizardContainer, parent, container, definition,
				connectorConfiguration, context, messageProvider,
				connectorExpressionContentTypeFilter);
	}

	@Override
	protected ExpressionViewer createTextControl(Composite composite,
			org.bonitasoft.studio.connector.model.definition.Text object) {
		if (object.getInputName().equals(S_OBJECT_TYPE)
				&& allSforceObject != null) {
			nameExpViewer = super.createTextControl(composite, object);
			autoCompleteToAllObjects(nameExpViewer);
			nameExpViewer
					.addSelectionChangedListener(new ISelectionChangedListener() {
						@Override
						public void selectionChanged(SelectionChangedEvent event) {
							autoCompleteToField(((Text) nameExpViewer.getTextControl())
									.getText(), expColViewer);
							autoCompleteToId(((Text) nameExpViewer.getTextControl())
									.getText(), idExpViewer);
						}
					});
			return nameExpViewer;
		} else if (object.getInputName().equals(S_OBJECT_ID)) {
			idExpViewer = super.createTextControl(composite, object);
			idExpViewer.setExpressionProposalLableProvider(new ExpressionLabelProvider(){
				@Override
				public String getDescription(Expression expression) {
					return expression.getContent();
				}
				
				@Override
				public String getText(Object expression) {
					String string = objectAndIdMap.get(((Expression) expression).getContent());
					if(string == null){
						string = ((Expression) expression).getContent();
					}
					return string;
				}
			});
			return idExpViewer;
		} else {
			return super.createTextControl(composite, object);
		}
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
