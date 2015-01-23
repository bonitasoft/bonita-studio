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
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class SequenceFlowConditionMigration extends ReportCustomMigration {

	private Map<String, String> sequenceFlowConditions = new HashMap<String,String>();

	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance sequenceFlow : model.getAllInstances("process.SequenceFlow")){
			final String condition = sequenceFlow.get("condition");
			sequenceFlow.set("condition", null);
			if(condition != null && !condition.trim().isEmpty()){
				sequenceFlowConditions.put(sequenceFlow.getUuid(), condition);
			}
		}
	}
	
	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance sequenceFlow : model.getAllInstances("process.SequenceFlow")){
			final StringToExpressionConverter converter = getConverter(model,getScope(sequenceFlow));
			final String uuid = sequenceFlow.getUuid();
			String condition = sequenceFlowConditions.get(uuid);
			Instance expression = null;
			if(condition != null){
				condition = "${"+condition+"}";
				expression = converter.parse(condition, Boolean.class.getName(), true);
				String expressionType = expression.get("type");
				if(ExpressionConstants.SCRIPT_TYPE.equals(expressionType)){
					expression.set("name","conditionScript");
					addReportChange((String) sequenceFlow.get("name"), ProcessPackage.Literals.SEQUENCE_FLOW.getName(), uuid, Messages.sequenceFlowConditionMigrationDescription, Messages.sequenceFlowConditionProperty, IStatus.WARNING);
				}else{
					addReportChange((String) sequenceFlow.get("name"), ProcessPackage.Literals.SEQUENCE_FLOW.getName(), uuid, Messages.sequenceFlowConditionMigrationDescription, Messages.sequenceFlowConditionProperty, IStatus.OK);
				}
			}else{
				 expression = StringToExpressionConverter.createExpressionInstance(model, "", "", Boolean.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
			}
			sequenceFlow.set("condition", expression);
		}
	}
	
}
