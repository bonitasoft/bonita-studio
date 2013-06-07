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
package org.bonitasoft.studio.migration.version;

import org.bonitasoft.studio.common.ConfigurationIdProvider;
import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class UpdateModelVersion extends CustomMigration {

	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance mainProc : model.getAllInstances("process.MainProcess")){
			final EStructuralFeature f = mainProc.getEClass().getEStructuralFeature("configId");
			MainProcess currentDiagram = null;
			if(f != null){
				String oldName = mainProc.get("name");
				String oldVersion = mainProc.get("version");
				String oldBonitaModelVersion = mainProc.get("bonitaModelVersion");
				String oldProductVersion = mainProc.get("bonitaVersion");
				String oldConfigId = mainProc.get("configId");
				if(!ProductVersion.CURRENT_VERSION.equals(oldProductVersion) || !ModelVersion.CURRENT_VERSION.equals(oldBonitaModelVersion)){
					currentDiagram = ProcessFactory.eINSTANCE.createMainProcess();
					currentDiagram.setName(oldName);
					currentDiagram.setVersion(oldVersion);
					currentDiagram.setBonitaModelVersion(oldBonitaModelVersion);
					currentDiagram.setBonitaVersion(oldProductVersion);
					currentDiagram.setConfigId(oldConfigId);
				}
			}
			mainProc.set("bonitaVersion", ProductVersion.CURRENT_VERSION);
			mainProc.set("bonitaModelVersion", ModelVersion.CURRENT_VERSION);


			if(currentDiagram != null){
				if(ConfigurationIdProvider.getConfigurationIdProvider().isConfigurationIdValid(currentDiagram)){
					final MainProcess diagram = ProcessFactory.eINSTANCE.createMainProcess();
					diagram.setName((String) mainProc.get("name"));
					diagram.setVersion((String) mainProc.get("version"));
					diagram.setBonitaModelVersion(ModelVersion.CURRENT_VERSION);
					diagram.setBonitaVersion(ProductVersion.CURRENT_VERSION);
					mainProc.set("configId",ConfigurationIdProvider.getConfigurationIdProvider().getConfigurationId(diagram));
				}
			}
		}
	}

}
