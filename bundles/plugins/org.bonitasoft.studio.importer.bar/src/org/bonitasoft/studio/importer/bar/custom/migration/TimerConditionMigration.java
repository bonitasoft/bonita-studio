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
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class TimerConditionMigration extends ReportCustomMigration {

	private Map<String, String> timerConditions = new HashMap<String,String>();

	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance timer : model.getAllInstances("process.AbstractTimerEvent")){
			final String script = timer.get("condition");
			timer.set("condition", null);
			if(script != null && !script.trim().isEmpty()){
				timerConditions.put(timer.getUuid(), script);
			}
		}
	}
	
	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance timer : model.getAllInstances("process.AbstractTimerEvent")){
			final StringToExpressionConverter converter = getConverter(model,getScope(timer));
			final String uuid = timer.getUuid();
			final String timerCondition = timerConditions.get(uuid);
			Instance expression = null;
			if(timerCondition != null){
				expression = converter.parse(timerCondition, Long.class.getName(), false);
				String expressionType = expression.get("type");
				if(ExpressionConstants.SCRIPT_TYPE.equals(expressionType)){
					expression.set("name","timerConditionScript");
					addReportChange((String) timer.get("name"), timer.getType().getEClass().getName(), uuid, Messages.timerConditionMigrationDescription, Messages.timerConditionProperty, IStatus.WARNING);
				}else{
					addReportChange((String) timer.get("name"), timer.getType().getEClass().getName(), uuid, Messages.timerConditionMigrationDescription, Messages.timerConditionProperty, IStatus.OK);
				}
			}else{
				 expression = StringToExpressionConverter.createExpressionInstance(model, "", "", Long.class.getName(), ExpressionConstants.CONSTANT_TYPE, false);
			}
			timer.set("condition", expression);
		}
	}
	
}
