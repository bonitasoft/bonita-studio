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

import org.bonitasoft.studio.common.DatasourceConstants;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.importer.bar.i18n.Messages;
import org.bonitasoft.studio.migration.migrator.ReportCustomMigration;
import org.bonitasoft.studio.migration.utils.StringToExpressionConverter;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class DataDefaultValueMigration extends ReportCustomMigration {

    private final Map<String, String> dataDefaultValue = new HashMap<String,String>();

    @Override
    public void migrateBefore(final Model model, final Metamodel metamodel)
            throws MigrationException {
        for(final Instance data : model.getAllInstances("process.Data")){
            final String script = data.get("defaultValue");
            data.set("defaultValue", null);
            if(script != null && !script.trim().isEmpty()){
                dataDefaultValue.put(data.getUuid(), script);
            }
        }
    }

    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel)
            throws MigrationException {
        for(final Instance data : model.getAllInstances("process.Data")){
            if(!data.instanceOf("process.AttachmentData")){
                final String uuid = data.getUuid();
                Instance expression = null;
                if (dataDefaultValue.containsKey(uuid)) {
                    final String defaultValue = dataDefaultValue.get(uuid);
                    final StringToExpressionConverter converter = getConverter(model, getScope(data));
                    final String returnType = StringToExpressionConverter.getDataReturnType(data);
                    final String dataName = data.get("name");
                    converter.setDataToIgnore(dataName);
                    expression = converter.parse(defaultValue, returnType, false);
                    final String expressionType = expression.get("type");
                    if(ExpressionConstants.SCRIPT_TYPE.equals(expressionType)){
                        expression.set("name",dataName+"DefaultValueScript");
                        final List<Instance> dependencies =  expression.get("referencedElements");
                        boolean invalidDependency = false ;
                        for(final Instance dependency : dependencies){
                            if(dependency.instanceOf("process.Data")){
                                final List<Instance> containerData = data.getContainer().get("data");
                                for(final Instance dataInstance : containerData){
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
                data.set("datasourceId", getDatasource(data));
            }
        }
    }

    private String getDatasource(final Instance data) {
        final EReference feature = data.getContainerReference();
        if(feature.equals(ProcessPackage.Literals.PAGE_FLOW__TRANSIENT_DATA)
                || feature.equals(ProcessPackage.Literals.RECAP_FLOW__RECAP_TRANSIENT_DATA)
                || feature.equals(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA)){
            return DatasourceConstants.PAGEFLOW_DATASOURCE ;
        }else if((Boolean) data.get("transient")){
            return DatasourceConstants.IN_MEMORY_DATASOURCE;
        }else{
            return DatasourceConstants.BOS_DATASOURCE ;
        }
    }
}
