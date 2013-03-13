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
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class DataDefaultValueMigration extends ReportCustomMigration {

	private Map<String, String> dataDefaultValue = new HashMap<String,String>();

	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance data : model.getAllInstances("process.Data")){
			final String script = data.get("defaultValue");
			data.set("defaultValue", null);
			if(script != null && !script.trim().isEmpty()){
				dataDefaultValue.put(data.getUuid(), script);
			}
		}
	}

	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance data : model.getAllInstances("process.Data")){
			if(!data.instanceOf("process.AttachmentData")){
				final StringToExpressionConverter converter = getConverter(model,getScope(data));
				final String uuid = data.getUuid();
				final String defaultValue = dataDefaultValue.get(uuid);
				Instance expression = null;
				if(defaultValue != null){
					final String returnType = StringToExpressionConverter.getDataReturnType(data);
					expression = converter.parse(defaultValue, returnType, false);
					String expressionType = expression.get("type");
					if(ExpressionConstants.SCRIPT_TYPE.equals(expressionType)){
						expression.set("name",data.get("name")+"DefaultValueScript");
						List<Instance> dependencies =  expression.get("referencedElements");
						boolean invalidDependency = false ;
						for(Instance dependency : dependencies){
							if(dependency.instanceOf("process.Data")){
								List<Instance> containerData = data.getContainer().get("data");
								for(Instance dataInstance : containerData){
									if(dataInstance.get("name").equals(dependency.get("name"))){
										invalidDependency = true;
										addReportChange((String) data.get("name"), ProcessPackage.Literals.DATA.getName(), uuid, Messages.dataDefaultValueWithOtherDataDependencyMigrationDescription, Messages.dataDefaultValueProperty, IStatus.ERROR);
									}
								}
							}
						}
						if(!invalidDependency){
							addReportChange((String) data.get("name"), ProcessPackage.Literals.DATA.getName(), uuid, Messages.dataDefaultValueMigrationDescription, Messages.dataDefaultValueProperty, IStatus.WARNING);
						}
					}else if(ExpressionConstants.VARIABLE_TYPE.equals(expressionType)){
						if(data.getContainer().instanceOf("process.AbstractProcess")){
							expression.set("type",ExpressionConstants.CONSTANT_TYPE);
							addReportChange((String) data.get("name"), ProcessPackage.Literals.DATA.getName(), uuid, Messages.dataDefaultValueWithOtherDataDependencyMigrationDescription, Messages.dataDefaultValueProperty, IStatus.ERROR);
						}
					}else{
						addReportChange((String) data.get("name"), ProcessPackage.Literals.DATA.getName(), uuid, Messages.dataDefaultValueMigrationDescription, Messages.dataDefaultValueProperty, IStatus.OK);
					}
				}else{
					expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, false);
				}
				data.set("defaultValue", expression);
			}
		}
	}
}
