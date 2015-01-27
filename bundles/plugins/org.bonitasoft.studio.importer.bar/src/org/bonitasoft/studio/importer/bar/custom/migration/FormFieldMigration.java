/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.bar.custom.migration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.importer.bar.i18n.Messages;
import org.bonitasoft.studio.migration.migrator.ReportCustomMigration;
import org.bonitasoft.studio.migration.utils.StringToExpressionConverter;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class FormFieldMigration extends ReportCustomMigration {

	private Map<String, String> exampleScripts = new HashMap<String,String>();
	private Map<String, String> defaultValueScripts = new HashMap<String,String>();
	private Map<String, Instance> defaultValueConnector = new HashMap<String,Instance>();
	private Map<String, Instance> defaultConnectorAfterEvent = new HashMap<String,Instance>();
	private Map<String, String> defaultValueAfterEventScripts = new HashMap<String,String>();
	private Map<String, Instance> initialValueConnectors = new HashMap<String, Instance>();
	private Map<String, Instance> afterEventConnector = new HashMap<String, Instance>();

	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance widget : model.getAllInstances("form.FormField")){
			final Instance widgetContainer = widget.getContainer();
			if(widgetContainer != null && !(widgetContainer.instanceOf("expression.Expression"))){
				storeExampleMessages(widget);
			}
		}
		for(Instance widget : model.getAllInstances("form.MultipleValuatedFormField")){
			final Instance widgetContainer = widget.getContainer();
			if(widgetContainer != null && !(widgetContainer.instanceOf("expression.Expression"))){
				storeDefaultValues(widget);
				storeDefaultValueConnector(widget);
				storeDefaultValuesAfterEvent(widget);
				storeDefaultValueAfterEventConnector(widget);
				storeAfterEventConnectors(widget);
				storeInputConnectors(widget);
			}
		}
	}

	private void storeDefaultValueAfterEventConnector(Instance widget) {
		final Instance expression = widget.get("afterEventExpression");
		if(expression != null){
			final List<Instance> defaultValueConnectors = expression.get("connectors");
			if(!defaultValueConnectors.isEmpty()){
				defaultConnectorAfterEvent.put(widget.getUuid(),defaultValueConnectors.get(0).copy());	
			}
		}
	}

	private void storeDefaultValueConnector(Instance widget) {
		final Instance expression = widget.get("inputExpression");
		if(expression != null){
			final List<Instance> defaultValueConnectors = expression.get("connectors");
			if(!defaultValueConnectors.isEmpty()){
				defaultValueConnector.put(widget.getUuid(),defaultValueConnectors.get(0).copy());	
			}
		}
	}

	private void storeInputConnectors(Instance widget) {
		if(widget.instanceOf("form.MultipleValuatedFormField")){
			List<Instance> connectors = widget.get("defaultConnectors");
			if(!connectors.isEmpty()){
				final Instance instance = connectors.get(0);
				initialValueConnectors.put(widget.getUuid(), instance.copy());
			}
		}

	}

	private void storeAfterEventConnectors(Instance widget) {
		if(widget.instanceOf("form.MultipleValuatedFormField")){
			List<Instance> connectors = widget.get("defaultAfterEventConnectors");
			if(!connectors.isEmpty()){
				final Instance instance = connectors.get(0);
				afterEventConnector.put(widget.getUuid(), instance.copy());
			}
		}
	}

	private void storeDefaultValuesAfterEvent(Instance widget) {
		final String defaultValue = widget.get("defaultValueAfterEvent");
		widget.set("defaultValueAfterEvent", null);
		if(defaultValue != null && !defaultValue.trim().isEmpty()){
			defaultValueAfterEventScripts.put(widget.getUuid(), defaultValue);
		}
	}

	private void storeExampleMessages(Instance widget) {
		final String exampleScript = widget.get("exampleMessage");
		widget.set("exampleMessage", null);
		if(exampleScript != null && !exampleScript.trim().isEmpty()){
			exampleScripts.put(widget.getUuid(), exampleScript);
		}
	}

	private void storeDefaultValues(Instance widget) {
		final String defaultValue = widget.get("defaultValue");
		widget.set("defaultValue", null);
		if(defaultValue != null && !defaultValue.trim().isEmpty()){
			defaultValueScripts.put(widget.getUuid(), defaultValue);
		}
	}

	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance widget : model.getAllInstances("form.FormField")){
			final Instance widgetContainer = widget.getContainer();
			if(widgetContainer != null && !(widgetContainer.instanceOf("expression.Expression"))){
				setExampleMessage(model, widget);
			}
		}
		for(Instance widget : model.getAllInstances("form.MultipleValuatedFormField")){
			final Instance widgetContainer = widget.getContainer();
			if(widgetContainer != null && !(widgetContainer.instanceOf("expression.Expression"))){
				setDefaultValue(model, widget);
				setInitialValueConnector(model, widget);
				setDefaultValueAfterEvent(model, widget);
				setAfterEventConnector(widget, model);
			}
		}
	}

	private void setInitialValueConnector(Model model, Instance widget) {
		Instance expression = null;
		if(initialValueConnectors.containsKey(widget.getUuid())){
			Instance connector = initialValueConnectors.get(widget.getUuid());
			expression = model.newInstance("expression.Expression");
			expression.set("name", connector.get("name"));
			expression.set("content",  connector.get("name"));
			expression.set("type", ExpressionConstants.CONNECTOR_TYPE);
			List<Instance> outputs = connector.get("outputs");
			if(outputs.isEmpty()){
				connector.add("outputs", createWidgetOutput(model,connector));
			}
			expression.add("connectors", connector);
			Instance oldExpression = widget.get("inputExpression");
			if(oldExpression != null){
				model.delete(oldExpression);
			}
			widget.set("inputExpression", expression);
		}
		
	}

	private void setAfterEventConnector(Instance widget, Model model) {
		Instance expression = null ;
		if(afterEventConnector.containsKey(widget.getUuid())){
			Instance connector = afterEventConnector.get(widget.getUuid());
			expression = model.newInstance("expression.Expression");
			expression.set("name", connector.get("name"));
			expression.set("content",  connector.get("name"));
			expression.set("type", ExpressionConstants.CONNECTOR_TYPE);
			List<Instance> outputs = connector.get("outputs");
			if(outputs.isEmpty()){
				connector.add("outputs", createWidgetOutput(model,connector));
			}
			expression.add("connectors", connector);
			Instance oldExpression = widget.get("afterEventExpression");
			if(oldExpression != null){
				model.delete(oldExpression);
			}
			widget.set("afterEventExpression", expression);
		}
	}

	private void setExampleMessage(Model model, Instance widget) {
		Instance expression = null;
		if(exampleScripts.containsKey(widget.getUuid())){
			expression = getConverter(model,getScope(widget)).parse(exampleScripts.get(widget.getUuid()), String.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "exampleScript");
			}
			addReportChange((String) widget.get("name"),widget.getEClass().getName(), widget.getUuid(), Messages.exampleMigrationDescription, Messages.userAidsProperty, StringToExpressionConverter.getStatusForExpression(expression));
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		widget.set("exampleMessage", expression);
	}

	private void setDefaultValue(Model model, Instance widget) {
		Instance expression = null;
		if(defaultValueConnector.containsKey(widget.getUuid())){
			Instance connector = defaultValueConnector.get(widget.getUuid());
			expression = model.newInstance("expression.Expression");
			expression.set("name", connector.get("name"));
			expression.set("content",  connector.get("name"));
			expression.set("type", ExpressionConstants.CONNECTOR_TYPE);
			List<Instance> outputs = connector.get("outputs");
			if(outputs.isEmpty()){
				connector.add("outputs", createWidgetOutput(model,connector));
			}
			expression.add("connectors", connector);
		}else if(defaultValueScripts.containsKey(widget.getUuid())){
			expression = getConverter(model,getScope(widget)).parse(defaultValueScripts.get(widget.getUuid()), String.class.getName(), false);
			if(ExpressionConstants.VARIABLE_TYPE.equals(expression.get("type")) && getParentPageFlow(widget).instanceOf("process.Pool")){
				model.delete(expression);
				widget.set("defaultExpression", StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true));
				addReportChange((String) widget.get("name"),widget.getEClass().getName(), widget.getUuid(), Messages.widgetDataInputAtProcessLevelMigrationDescription, Messages.dataProperty, IStatus.ERROR);
				return;
			}
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "initialValueScript");
			}
			addReportChange((String) widget.get("name"),widget.getEClass().getName(), widget.getUuid(), Messages.initialValueMigrationDescription, Messages.dataProperty, StringToExpressionConverter.getStatusForExpression(expression));
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, false);
		}
		widget.set("defaultExpression", expression);
	}

	private Instance createWidgetOutput(Model model,Instance connector) {
		Instance output = model.newInstance("expression.Operation");
		Instance expression = model.newInstance("expression.Expression");
		output.set("rightOperand", expression);
		return output;
	}

	private Instance getParentPageFlow(Instance widget) {
		Instance current =  widget.getContainer();
		while(current != null && !current.instanceOf("process.AbstractPageFlow")){
			current = current.getContainer();
		}
		return current;
	}

	private void setDefaultValueAfterEvent(Model model, Instance widget) {
		Instance expression = null;
		if(defaultConnectorAfterEvent.containsKey(widget.getUuid())){
			Instance connector = defaultConnectorAfterEvent.get(widget.getUuid());
			expression = model.newInstance("expression.Expression");
			expression.set("name", connector.get("name"));
			expression.set("content",  connector.get("name"));
			expression.set("type", ExpressionConstants.CONNECTOR_TYPE);
			List<Instance> outputs = connector.get("outputs");
			if(outputs.isEmpty()){
				connector.add("outputs", createWidgetOutput(model,connector));
			}
			expression.add("connectors", connector);
		}else if(defaultValueAfterEventScripts.containsKey(widget.getUuid())){
			expression = getConverter(model,getScope(widget)).parse(defaultValueAfterEventScripts.get(widget.getUuid()), Object.class.getName(), false);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "updateSelectedValueScript");
			}
			addReportChange((String) widget.get("name"),widget.getEClass().getName(), widget.getUuid(), Messages.updateSelectedValueMigrationDescription, Messages.contingencyProperty, StringToExpressionConverter.getStatusForExpression(expression));
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", Object.class.getName(), ExpressionConstants.CONSTANT_TYPE, false);
		}
		widget.set("defaultExpressionAfterEvent", expression);
	}

}
