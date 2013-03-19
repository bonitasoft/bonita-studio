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
import java.util.Map;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.importer.bar.i18n.Messages;
import org.bonitasoft.studio.migration.migrator.ReportCustomMigration;
import org.bonitasoft.studio.migration.utils.StringToExpressionConverter;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class FormFieldMigration extends ReportCustomMigration {

	private Map<String, String> exampleScripts = new HashMap<String,String>();
	private Map<String, String> defaultValueScripts = new HashMap<String,String>();
	private Map<String, String> defaultValueAfterEventScripts = new HashMap<String,String>();
	
	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance widget : model.getAllInstances("form.FormField")){
			if(!(widget.getContainer().instanceOf("expression.Expression"))){
				storeExampleMessages(widget);
			}
		}
		for(Instance widget : model.getAllInstances("form.MultipleValuatedFormField")){
			if(!(widget.getContainer().instanceOf("expression.Expression"))){
				storeDefaultValues(widget);
				storeDefaultValuesAfterEvent(widget);
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
			if(!(widget.getContainer().instanceOf("expression.Expression"))){
				setExampleMessage(model, widget);
			}
		}
		for(Instance widget : model.getAllInstances("form.MultipleValuatedFormField")){
			if(!(widget.getContainer().instanceOf("expression.Expression"))){
				setDefaultValue(model, widget);
				setDefaultValueAfterEvent(model, widget);
			}
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
		if(defaultValueScripts.containsKey(widget.getUuid())){
			expression = getConverter(model,getScope(widget)).parse(defaultValueScripts.get(widget.getUuid()), String.class.getName(), true);
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
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		widget.set("defaultExpression", expression);
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
		if(defaultValueAfterEventScripts.containsKey(widget.getUuid())){
			expression = getConverter(model,getScope(widget)).parse(defaultValueAfterEventScripts.get(widget.getUuid()), String.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "updateSelectedValueScript");
			}
			addReportChange((String) widget.get("name"),widget.getEClass().getName(), widget.getUuid(), Messages.updateSelectedValueMigrationDescription, Messages.contingencyProperty, StringToExpressionConverter.getStatusForExpression(expression));
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		widget.set("defaultExpressionAfterEvent", expression);
	}
	
}
