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

import org.bonitasoft.studio.importer.bar.i18n.Messages;
import org.bonitasoft.studio.migration.migrator.ReportCustomMigration;
import org.bonitasoft.studio.migration.utils.StringToExpressionConverter;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CallActivity2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CallActivityName2EditPart;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.eclipse.gmf.runtime.notation.NotationPackage;


/**
 * @author Romain Bioteau
 *
 */
public class CallActivityMigration extends ReportCustomMigration {

    private static final String SUBPROCESS_VIEW_TYPE = "3004";
    private static final String SUBPROCESS_LABEL_VIEW_TYPE = "5004";

    
    @Override
    public void migrateBefore(Model model, Metamodel metamodel) throws MigrationException {

    }

    @Override
    public void migrateAfter(Model model, Metamodel metamodel) throws MigrationException {
        for(Instance callActivity : model.getAllInstances("process.CallActivity")){
            String subprocessName = callActivity.get("subprocessName");
            String subprocessVersion = callActivity.get("subprocessVersion");

           final StringToExpressionConverter converter = getConverter(model,getScope(callActivity));
           final Instance nameExp = converter.parse(subprocessName,String.class.getName(),true);
        
            callActivity.set("calledActivityName", nameExp);

            addReportChange((String) callActivity.get("name"),
            		ProcessPackage.Literals.CALL_ACTIVITY.getName(),
            		callActivity.getUuid(),
            		Messages.callActivityTargetNameMigrationDescription,
            		Messages.callActivityTargetNameProperty,
            		IStatus.WARNING);
   
            final Instance versionExp = converter.parse(subprocessVersion,String.class.getName(),true);
            callActivity.set("calledActivityVersion", versionExp);
            
            addReportChange((String) callActivity.get("name"),
            		ProcessPackage.Literals.CALL_ACTIVITY.getName(),
            		callActivity.getUuid(),
            		Messages.callActivityTargetVersionMigrationDescription,
            		Messages.callActivityTargetVersionProperty,
            		IStatus.WARNING);
        }

        for(Instance shape :model.getAllInstances(NotationPackage.Literals.SHAPE)){
            String viewType = shape.get(NotationPackage.Literals.VIEW__TYPE);
            if(SUBPROCESS_VIEW_TYPE.equals(viewType)){
                shape.set(NotationPackage.Literals.VIEW__TYPE,String.valueOf(CallActivity2EditPart.VISUAL_ID));
            }
        }

        for(Instance decorationNode :model.getAllInstances(NotationPackage.Literals.DECORATION_NODE)){
            String viewType = decorationNode.get(NotationPackage.Literals.VIEW__TYPE);
            if(SUBPROCESS_LABEL_VIEW_TYPE.equals(viewType)){
                decorationNode.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf(CallActivityName2EditPart.VISUAL_ID));
            }
        }


    }

}
