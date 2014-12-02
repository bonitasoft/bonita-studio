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
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class DuplicableMigration extends ReportCustomMigration {

	private Map<String, String> maxNumberOfDuplications = new HashMap<String,String>();
	private Map<String, String> minNumberOfDuplications = new HashMap<String,String>();
	private Map<String, String> displayLabelForAdds = new HashMap<String,String>();
	private Map<String, String> tooltipForAdds = new HashMap<String,String>();
	private Map<String, String> displayLabelForRemoves = new HashMap<String,String>();
	private Map<String, String> tooltipForRemoves = new HashMap<String,String>();
	
	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance widget : model.getAllInstances("form.Duplicable")){
			final Instance widgetContainer = widget.getContainer();
			if(widgetContainer != null && !(widgetContainer.instanceOf("expression.Expression"))){
				storeMaxNumberOfDuplications(widget);
				storeMinNumberOfDuplications(widget);
				storeDisplayLabelForAdds(widget);
				storeDisplayLabelForRemoves(widget);
				storeTooltipForAdds(widget);
				storeTooltipForRemoves(widget);
				storeTooltipForRemoves(widget);
			}
		}
	}

	private void storeTooltipForRemoves(Instance widget) {
		final String tooltipForRemove = widget.get("tooltipForRemove");
		if(tooltipForRemove != null && !tooltipForRemove.isEmpty()){
			tooltipForRemoves.put(widget.getUuid(), tooltipForRemove);
		}
	}

	private void storeTooltipForAdds(Instance widget) {
		final String tooltipForAdd = widget.get("tooltipForAdd");
		if(tooltipForAdd != null && !tooltipForAdd.isEmpty()){
			tooltipForAdds.put(widget.getUuid(), tooltipForAdd);
		}
	}

	private void storeDisplayLabelForRemoves(Instance widget) {
		final String displayLabelForRemove = widget.get("displayLabelForRemove");
		if(displayLabelForRemove != null && !displayLabelForRemove.isEmpty()){
			displayLabelForRemoves.put(widget.getUuid(), displayLabelForRemove);
		}
	}

	private void storeDisplayLabelForAdds(Instance widget) {
		final String displayLabelForAdd = widget.get("displayLabelForAdd");
		if(displayLabelForAdd != null && !displayLabelForAdd.isEmpty()){
			displayLabelForAdds.put(widget.getUuid(), displayLabelForAdd);
		}
	}

	private void storeMinNumberOfDuplications(Instance widget) {
		final String minNumberOfDuplication = widget.get("minNumberOfDuplication");
		if(minNumberOfDuplication != null && !minNumberOfDuplication.isEmpty()){
			minNumberOfDuplications.put(widget.getUuid(), minNumberOfDuplication);
		}
	}

	private void storeMaxNumberOfDuplications(Instance widget) {
		final String maxNumberOfDuplication = widget.get("maxNumberOfDuplication");
		if(maxNumberOfDuplication != null && !maxNumberOfDuplication.isEmpty()){
			maxNumberOfDuplications.put(widget.getUuid(), maxNumberOfDuplication);
		}
	}
	
	
	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance widget : model.getAllInstances("form.Duplicable")){
			final Instance widgetContainer = widget.getContainer();
			if(widgetContainer != null && !(widgetContainer.instanceOf("expression.Expression"))){
				setMaxNumberOfDuplications(widget,model);
				setMinNumberOfDuplications(widget,model);
				setDisplayLabelForAdds(widget,model);
				setDisplayLabelForRemoves(widget,model);
				setTooltipForAdds(widget,model);
				setTooltipForRemoves(widget,model);
			}
		}
	}

	private void setMaxNumberOfDuplications(Instance widget, Model model) {
		Instance expression = null ;
		if(maxNumberOfDuplications.containsKey(widget.getUuid())){
			expression = getConverter(model,getScope(widget)).parse(maxNumberOfDuplications.get(widget.getUuid()), Integer.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "maxNumberScript");
			}
			addReportChange((String) widget.get("name"),widget.getEClass().getName(), widget.getUuid(), Messages.maxNumberMigrationDescription, Messages.dataProperty, StringToExpressionConverter.getStatusForExpression(expression));
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", Integer.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		widget.set("maxNumberOfDuplication", expression);
	}
	
	private void setMinNumberOfDuplications(Instance widget, Model model) {
		Instance expression = null ;
		if(minNumberOfDuplications.containsKey(widget.getUuid())){
			expression = getConverter(model,getScope(widget)).parse(minNumberOfDuplications.get(widget.getUuid()), Integer.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "minNumberScript");
			}
			addReportChange((String) widget.get("name"),widget.getEClass().getName(), widget.getUuid(), Messages.minNumberMigrationDescription, Messages.dataProperty, StringToExpressionConverter.getStatusForExpression(expression));
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", Integer.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		widget.set("minNumberOfDuplication", expression);
	}
	
	private void setDisplayLabelForAdds(Instance widget, Model model) {
		Instance expression = null ;
		if(displayLabelForAdds.containsKey(widget.getUuid())){
			expression = getConverter(model,getScope(widget)).parse(displayLabelForAdds.get(widget.getUuid()), String.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "labelForAddScript");
			}
			addReportChange((String) widget.get("name"),widget.getEClass().getName(), widget.getUuid(), Messages.displayLabelForAddMigrationDescription, Messages.appearanceProperty, StringToExpressionConverter.getStatusForExpression(expression));
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		widget.set("displayLabelForAdd", expression);
	}
	
	private void setDisplayLabelForRemoves(Instance widget, Model model) {
		Instance expression = null ;
		if(displayLabelForRemoves.containsKey(widget.getUuid())){
			expression = getConverter(model,getScope(widget)).parse(displayLabelForRemoves.get(widget.getUuid()), String.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "labelForRemoveScript");
			}
			addReportChange((String) widget.get("name"),widget.getEClass().getName(), widget.getUuid(), Messages.displayLabelForRemoveMigrationDescription, Messages.appearanceProperty, StringToExpressionConverter.getStatusForExpression(expression));
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		widget.set("displayLabelForRemove", expression);
	}
	
	private void setTooltipForAdds(Instance widget, Model model) {
		Instance expression = null ;
		if(tooltipForAdds.containsKey(widget.getUuid())){
			expression = getConverter(model,getScope(widget)).parse(tooltipForAdds.get(widget.getUuid()), String.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "tooltipForAddScript");
			}
			addReportChange((String) widget.get("name"),widget.getEClass().getName(), widget.getUuid(), Messages.tooltipForAddMigrationDescription, Messages.appearanceProperty, StringToExpressionConverter.getStatusForExpression(expression));
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		widget.set("tooltipForAdd", expression);
	}
	
	private void setTooltipForRemoves(Instance widget, Model model) {
		Instance expression = null ;
		if(tooltipForRemoves.containsKey(widget.getUuid())){
			expression = getConverter(model,getScope(widget)).parse(tooltipForRemoves.get(widget.getUuid()), String.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "tooltipForRemoveScript");
			}
			addReportChange((String) widget.get("name"),widget.getEClass().getName(), widget.getUuid(), Messages.tooltipForRemoveMigrationDescription, Messages.appearanceProperty, StringToExpressionConverter.getStatusForExpression(expression));
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		widget.set("tooltipForRemove", expression);
	}
}
