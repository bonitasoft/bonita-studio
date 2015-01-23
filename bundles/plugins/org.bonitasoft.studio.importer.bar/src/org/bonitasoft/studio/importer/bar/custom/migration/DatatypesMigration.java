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

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.DataTypeLabels;
import org.bonitasoft.studio.migration.migrator.ReportCustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;


/**
 * @author Romain Bioteau
 *
 */
public class DatatypesMigration extends ReportCustomMigration {



	@Override
	public void migrateBefore(Model model, Metamodel metamodel) throws MigrationException {
		removeAttachmentType(model);
	}

	protected void removeAttachmentType(Model model) {
		for (Instance data : model.getAllInstances("process.AttachmentData")) {
			data.set("dataType", null);
		}
		for (Instance mainProcess : model.getAllInstances("process.MainProcess")) {
			List<Object> datatypes = mainProcess.get("datatypes");
			List<Instance> attachmentTypes = new ArrayList<Instance>();
			for(Object datatype : datatypes){
				if(((Instance)datatype).instanceOf("process.AttachmentType")){
					attachmentTypes.add((Instance) datatype);
				}
			}
			for(Instance attachmentTypeInstance : attachmentTypes){
				model.delete(attachmentTypeInstance);
			}
		}

	}



	@Override
	public void migrateAfter(Model model, Metamodel metamodel) throws MigrationException {
		Instance doubleDataType = null;
		for (Instance mainProcess : model.getAllInstances("process.MainProcess")) {
			List<Object> datatypes = mainProcess.get("datatypes");
//			doubleDataType = model.newInstance("process.DoubleType");
//			doubleDataType.set("name", DataTypeLabels.doubleDataType);
//			datatypes.add(doubleDataType);
			Instance longDataType = model.newInstance("process.LongType");
			longDataType.set("name", DataTypeLabels.longDataType);
			datatypes.add(longDataType);
		}


//
//		for (Instance data : model.getAllInstances("process.Data")) {
//			Instance datatype = data.get("dataType");
//			if(datatype != null && datatype.instanceOf("process.FloatType")){
//				data.set("dataType", doubleDataType);
//			}   
//		}

		for (Instance mainProcess : model.getAllInstances("process.MainProcess")) {
			Instance floatTypeInstance = null;
			List<Object> datatypes = mainProcess.get("datatypes");
			for(Object datatype : datatypes){
				if(((Instance)datatype).instanceOf("process.FloatType")){
					floatTypeInstance = (Instance) datatype;
				}
			}
			floatTypeInstance.migrate("process.DoubleType");
		}
	}



}

