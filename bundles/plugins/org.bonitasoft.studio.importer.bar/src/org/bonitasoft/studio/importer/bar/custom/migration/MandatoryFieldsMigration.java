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
public class MandatoryFieldsMigration extends ReportCustomMigration {

	private Map<String, String> mandatorySymbols = new HashMap<String,String>();
	private Map<String, String> mandatoryLabels = new HashMap<String,String>();
	
	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance widget : model.getAllInstances("form.MandatoryFieldsCustomization")){
			if(widget.getContainer() == null || !(widget.getContainer().instanceOf("expression.Expression"))){
				storeMandatorySymbols(widget);
				storeMandatoryLabels(widget);
			}
		}
	}
	
	private void storeMandatorySymbols(Instance widget) {
		final String symbol = widget.get("mandatorySymbol");
		if(symbol != null && !symbol.isEmpty()){
			mandatorySymbols.put(widget.getUuid(), symbol);
		}
	}

	private void storeMandatoryLabels(Instance widget) {
		final String label = widget.get("mandatoryLabel");
		if(label != null && !label.isEmpty()){
			mandatoryLabels.put(widget.getUuid(), label);
		}
	}
	
	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance widget : model.getAllInstances("form.MandatoryFieldsCustomization")){
			if(widget.getContainer() == null || !(widget.getContainer().instanceOf("expression.Expression"))){
				setMandatorySymbols(widget,model);
				setMandatoryLabels(widget,model);
			}
		}
	}

	private void setMandatorySymbols(Instance widget, Model model) {
		Instance expression = null ;
		if(mandatorySymbols.containsKey(widget.getUuid())){
			expression = getConverter(model,getScope(widget)).parse(mandatorySymbols.get(widget.getUuid()), String.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "mandatorySymbolScript");
			}
			addReportChange((String) widget.get("name"),widget.getEClass().getName(), widget.getUuid(), Messages.mandatorySymbolMigrationDescription, Messages.dataProperty, StringToExpressionConverter.getStatusForExpression(expression));
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		widget.set("mandatorySymbol", expression);
	}
	
	private void setMandatoryLabels(Instance widget, Model model) {
		Instance expression = null ;
		if(mandatoryLabels.containsKey(widget.getUuid())){
			expression = getConverter(model,getScope(widget)).parse(mandatoryLabels.get(widget.getUuid()), String.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "mandatoryLabelScript");
			}
			addReportChange((String) widget.get("name"),widget.getEClass().getName(), widget.getUuid(), Messages.mandatoryLabelMigrationDescription, Messages.dataProperty, StringToExpressionConverter.getStatusForExpression(expression));
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		widget.set("mandatoryLabel", expression);
	}
	
}
