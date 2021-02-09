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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitch;
import org.bonitasoft.studio.connector.wizard.sforce.tooling.SalesforceTool;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionCollectionViewer;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Maxence Raoux
 * 
 */
public abstract class AbstractSforceComponentSwitch extends PageComponentSwitch {

	protected static final String FIELD_VALUES = "fieldValues";
	protected static final String S_OBJECT_TYPE = "sObjectType";
	protected static final String FIELDS_TO_RETRIEVE = "fieldsToRetrieve";
	protected static final String S_OBJECT_IDS = "sObjectIds";
	protected static final String S_OBJECT_ID = "sObjectId";

	final protected SalesforceTool sfTool;
	final protected List<String> allSforceObject;
    protected Map<String, String> objectAndIdMap = new HashMap<>();

	public AbstractSforceComponentSwitch(IWizardContainer iWizardContainer,
			Composite parent, EObject container,
			ExtendedConnectorDefinition definition,
			ConnectorConfiguration connectorConfiguration,
			EMFDataBindingContext context,
			AvailableExpressionTypeFilter connectorExpressionContentTypeFilter) {
		super(iWizardContainer, parent, container, definition,
				connectorConfiguration, context,
				connectorExpressionContentTypeFilter);
		sfTool = SalesforceTool.getInstance();
		allSforceObject = sfTool.getAllSForceObjects();
	}

	protected void autoCompleteToAllObjects(ExpressionViewer viewer) {
		ExpressionNatureProvider provider = new ExpressionNatureProvider(
				allSforceObject);
		viewer.setExpressionNatureProvider(provider);
		viewer.updateAutocompletionProposals();
	}

	protected void autoCompleteToField(String objectName,
			ExpressionCollectionViewer expColViewer) {
		if (allSforceObject.contains(objectName)) {
			List<String> fieldsOfCurrentObject = sfTool
					.getFields(objectName);
			ExpressionNatureProvider provider = new ExpressionNatureProvider(
					fieldsOfCurrentObject);
			expColViewer.removeExpressionNatureProvider(0);
			expColViewer.removeExpressionNatureProvider(1);
			expColViewer.addExpressionNatureProvider(provider);
		}
	}

	protected void autoCompleteToPickList(String objectName,
			ExpressionCollectionViewer expColViewer) {
		StructuredSelection selection = (StructuredSelection) expColViewer
				.getViewer().getSelection();
		ListExpression l = (ListExpression) selection.getFirstElement();
		if (l != null && l.getExpressions() != null
				&& l.getExpressions().size() > 0) {
			String field = l.getExpressions().get(0).getContent();
			List<String> fieldsOfCurrentObject = sfTool
					.getFields(objectName);
			if (fieldsOfCurrentObject.contains(field)) {
				ExpressionNatureProvider provider = new ExpressionNatureProvider(
						sfTool.getPickValues(objectName, field));
				expColViewer.removeExpressionNatureProvider(1);
				expColViewer.addExpressionNatureProvider(provider);
			}
		} else {
			expColViewer.removeExpressionNatureProvider(1);
		}
	}

	protected void autoCompleteToId(String objectName,
			ExpressionCollectionViewer expIdColViewer) {
		if (allSforceObject.contains(objectName)) {
			objectAndIdMap = sfTool.getObjectIdAndName(objectName);
			ExpressionNameNatureProvider provider = new ExpressionNameNatureProvider(objectAndIdMap);
			expIdColViewer.removeExpressionNatureProvider(0);
			expIdColViewer.addExpressionNatureProvider(provider);
		}
	}

	protected void autoCompleteToId(String objectName,
			ExpressionViewer idExpViewer) {
		if (allSforceObject.contains(objectName)) {
			objectAndIdMap = sfTool.getObjectIdAndName(objectName);
			ExpressionNameNatureProvider provider = new ExpressionNameNatureProvider(objectAndIdMap);
			idExpViewer.setExpressionNatureProvider(provider);
			idExpViewer.updateAutocompletionProposals();
		}
	}

}
