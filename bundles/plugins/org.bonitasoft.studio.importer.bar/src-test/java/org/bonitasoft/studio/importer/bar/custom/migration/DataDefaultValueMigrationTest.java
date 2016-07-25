/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.importer.bar.custom.migration;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.process.ProcessPackage;
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
public class DataDefaultValueMigrationTest {

    @Mock
    private Metamodel metamodel;
    @Mock
    private Model model;
    @Mock
    private Instance expression;
    private DataDefaultValueMigration migrationUnderTest;

    @Before
    public void setUp() throws Exception {
        doReturn(new BasicEList<Instance>()).when(model).getAllInstances("form.Widget");
        doReturn(new BasicEList<Instance>()).when(model).getAllInstances("process.Document");
        doReturn(expression).when(model).newInstance("expression.Expression");
        doReturn(ExpressionConstants.SCRIPT_TYPE).when(expression).get("type");
        doReturn(new BasicEList<Instance>()).when(expression).get("referencedElements");
        migrationUnderTest = spy(new DataDefaultValueMigration());
    }

    @Test
    public void testDataDefaultValueWithStringIncludingItSelfDataName() throws Exception {
        final BasicEList<Instance> processDatas = new BasicEList<Instance>();
        final Instance processData1 = mock(Instance.class);
        doReturn("myVar").when(processData1).get("name");
        doReturn(false).when(processData1).get("multiple");
        final Instance datatType = mock(Instance.class);
        doReturn(datatType).when(processData1).get("dataType");
        doReturn("${\"script including myVar in a String\"}").when(processData1).get("defaultValue");
        doReturn("\"script including myVar in a String\"").when(expression).get("content");
        doReturn(ProcessPackage.Literals.DATA_AWARE__DATA).when(processData1).getContainerReference();
        doReturn(false).when(processData1).get("transient");
        processDatas.add(processData1);
        doReturn(processDatas).when(model).getAllInstances("process.Data");
        final Instance processInstance = mock(Instance.class);
        doReturn(true).when(processInstance).instanceOf("process.AbstractProcess");
        doReturn(processDatas).when(processInstance).get("process.Data");
        doReturn(processInstance).when(processData1).getContainer();

        migrationUnderTest.migrateBefore(model, metamodel);
        migrationUnderTest.migrateAfter(model, metamodel);

        verify(expression).set("content", "\"script including myVar in a String\"");
        verify(expression, never()).add(eq("referencedElements"), any(Instance.class));
    }

}
