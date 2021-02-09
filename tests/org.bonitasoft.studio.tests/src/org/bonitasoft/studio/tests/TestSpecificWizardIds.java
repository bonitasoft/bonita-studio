/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.eclipse.core.runtime.IConfigurationElement;
import org.junit.Test;

public class TestSpecificWizardIds {

	private static final String SPECIFIC_CONNECTOR_WIZARD = "org.bonitasoft.studio.connectors.connectorWizard";

	private static final Map<String,String> WIZARD_IDS ;
	static{
		WIZARD_IDS = new HashMap<>();
		WIZARD_IDS.put("database-mysql","1.0.0");
		WIZARD_IDS.put("database-db2","1.0.0");
		WIZARD_IDS.put("database-h2","1.0.0");
		WIZARD_IDS.put("database-hsqldb","1.0.0");
		WIZARD_IDS.put("database-oracle10g","1.0.0");
		WIZARD_IDS.put("database-oracle11g","1.0.0");
		WIZARD_IDS.put("database-postgresql84","1.0.0");
		WIZARD_IDS.put("database-postgresql92","1.0.0");
		WIZARD_IDS.put("database-mssqlserver","1.2.1");
	};

	@Test
	public void testSpecificWizardDefinitionId() throws Exception {
		ConnectorDefRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
		for (String id: WIZARD_IDS.keySet()) {
			assertNotNull("Definition " + id + " doesn't exists anymore in version " + WIZARD_IDS.get(id), store.getDefinition(id, WIZARD_IDS.get(id)));
			boolean found = false ;
			for(IConfigurationElement element : BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(SPECIFIC_CONNECTOR_WIZARD)){
				String defId = element.getAttribute("DefinitionId");
				if (defId.equals(id)) {
					found = true ;
				}
			}
			assertTrue("No specific wizard page found for "+id,found);
		}
	}

}
