/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.migration.custom.migration.callactivity;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class DefaultCallActivityDefaultMappingTest {

    private DefaultCallActivityDefaultMapping customMigration;

    @Mock
    private Model model;

    @Mock
    private Metamodel metamodel;

    @Mock
    private EEnum eenum;

    @Mock
    private EEnumLiteral enumLiteral;

    @Mock
    private Instance inputMappingInstance;

    @Before
    public void setUp() throws Exception {
        final BasicEList<Instance> inputMappingsEList = new BasicEList<Instance>();
        inputMappingsEList.add(inputMappingInstance);
        when(model.getAllInstances("process.InputMapping")).thenReturn(inputMappingsEList);
        when(metamodel.getEEnum("process.InputMappingAssignationType")).thenReturn(eenum);
        when(eenum.getEEnumLiteral("Data")).thenReturn(enumLiteral);
    }

    @Test
    public void testMigrateAfter() throws Exception {
        customMigration = new DefaultCallActivityDefaultMapping();
        customMigration.migrateAfter(model, metamodel);
        verify(inputMappingInstance).set(ProcessPackage.Literals.INPUT_MAPPING__ASSIGNATION_TYPE.getName(), enumLiteral);
    }


}
