/**
 * Copyright (C) 2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.migration.custom.migration.contract;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class CreateContractCustomMigrationTest {

    private AbstractCreateContractCustomMigration customMigration;

    @Mock
    private Model model;

    @Mock
    private Metamodel metamodel;

    @Mock
    private Instance originalTaskInstance;

    @Mock
    private Instance newContractInstance, existingContractInstance;

    @Before
    public void setUp() throws Exception {
        customMigration = new CreateContractCustomMigration();
        final BasicEList<Instance> uniqueTaskEList = new BasicEList<Instance>();
        uniqueTaskEList.add(originalTaskInstance);
        when(model.getAllInstances("process.Task")).thenReturn(uniqueTaskEList);
        when(model.newInstance("process.Contract")).thenReturn(newContractInstance);
    }

    @Test
    public void should_migrateAfter_add_an_empty_contract_to_a_task() throws Exception {
        customMigration.migrateAfter(model, metamodel);
        verify(originalTaskInstance).set("contract", newContractInstance);
    }

    @Test
    public void should_migrateAfter_NOTadd_an_empty_contract_to_a_task() throws Exception {
        when(originalTaskInstance.get("contract")).thenReturn(existingContractInstance);
        customMigration.migrateAfter(model, metamodel);
        verify(model, never()).newInstance("process.Contract");
    }


}
