/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.tests.connectors;

import static org.junit.Assert.assertNotNull;

import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.AddDependencyOperation;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.junit.Before;
import org.junit.Test;

public class TestWebserviceVersionForBPMNImport {

    @Before
    public void init() throws Exception {
        AddDependencyOperation addDependencyOperation = new AddDependencyOperation("org.bonitasoft.connectors", "bonita-connector-webservice", "1.3.2");
        addDependencyOperation.run(AbstractRepository.NULL_PROGRESS_MONITOR);
    }
    
    
    @Test
    public void testWebserviceIDAndVersionUsedInBPMImportISOK() {
        //WARNING: if you have to change something in this test, you need to modify BPMNToProc.handleConnector
        ConnectorDefRepositoryStore cdrs = RepositoryManager.getInstance()
                .getRepositoryStore(ConnectorDefRepositoryStore.class);

        assertNotNull(
                "The webservice connector defintion has been modified, you need to update the BPMNToProc.handleConnector method",
                cdrs.getDefinition("webservice", "1.1.0"));
    }
}
