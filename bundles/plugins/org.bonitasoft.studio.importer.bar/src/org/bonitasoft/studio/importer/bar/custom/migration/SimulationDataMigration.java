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
public class SimulationDataMigration extends ReportCustomMigration {
	
	private Map<String, String> expressions = new HashMap<String,String>();
	private Map<String, String> values = new HashMap<String,String>();

	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance data : model.getAllInstances("simulation.SimulationData")){
			final String expression = data.get("expression");
			data.set("expression", null);
			if(expression != null && !expression.trim().isEmpty()){
				expressions.put(data.getUuid(), expression);
			}
		}
		for(Instance dataChange : model.getAllInstances("simulation.DataChange")){
			final String value = dataChange.get("value");
			dataChange.set("value", null);
			if(value != null && !value.trim().isEmpty()){
				values.put(dataChange.getUuid(), value);
			}
		}
	}
	
	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance data : model.getAllInstances("simulation.SimulationData")){
			setExpressions(data, model);
		}
		for(Instance dataChange : model.getAllInstances("simulation.DataChange")){
			setValues(dataChange, model);
		}
	}
	
	private void setExpressions(Instance data, Model model) {
		Instance expression = null ;
		if(expressions.containsKey(data.getUuid())){
			expression = getConverter(model,getScope(data)).parse(expressions.get(data.getUuid()), String.class.getName(), false);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "expressionScript");
			}
			addReportChange((String) data.get("name"),data.getEClass().getName(), data.getUuid(), Messages.simDataExpressionMigrationDescription, Messages.dataProperty, StringToExpressionConverter.getStatusForExpression(expression));
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, false);
		}
		data.set("expression", expression);
	}
	
	private void setValues(Instance widget, Model model) {
		Instance expression = null ;
		if(values.containsKey(widget.getUuid())){
			StringToExpressionConverter converter = getConverter(model,getScope(widget));
			converter.setUseSimulationDataScope(true);
			expression = converter.parse(values.get(widget.getUuid()), String.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "updateValueScript");
			}
			addReportChange((String) widget.getContainer().get("name"),widget.getContainer().getEClass().getName(), widget.getUuid(), Messages.simDataChangeValueMigrationDescription, Messages.dataProperty, StringToExpressionConverter.getStatusForExpression(expression));
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, false);
		}
		widget.set("value", expression);
	}
}
