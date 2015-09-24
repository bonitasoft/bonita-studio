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

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.eclipse.jdt.core.JavaModelException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorMigrationTest {

    @Mock
    private Metamodel metamodel;
    @Mock
    private Model model;
    @Mock
    private Instance validator;
    @Mock
    private Instance expression;
    @Mock
    private EClass validatorEclass;
    private ValidatorMigration migrationUnderTest;

    @Before
    public void setup() throws JavaModelException {
        doReturn("uuidValidator").when(validator).getUuid();
        final BasicEList<Instance> uniqueValidatorEList = new BasicEList<Instance>();
        uniqueValidatorEList.add(validator);
        doReturn(uniqueValidatorEList).when(model).getAllInstances("form.Validator");
        doReturn("myParam").when(validator).get("parameter");
        doReturn(expression).when(model).newInstance("expression.Expression");
        doReturn(new BasicEList<Instance>()).when(model).getAllInstances("process.Data");
        doReturn(new BasicEList<Instance>()).when(model).getAllInstances("form.Widget");
        doReturn(new BasicEList<Instance>()).when(model).getAllInstances("process.Document");
        doReturn(validatorEclass).when(validator).getEClass();
        migrationUnderTest = spy(new ValidatorMigration());
        doNothing().when(migrationUnderTest).buildRepository();
        doNothing().when(migrationUnderTest).createValidatorDescriptor(validator);
    }

    @Test
    public void testParameterUpdateToBooleanForBonitaGroovyValidator() throws Exception {
        doReturn("org.bonitasoft.GroovyPageValidator").when(validator).get("validatorClass");

        migrationUnderTest.migrateBefore(model, metamodel);
        migrationUnderTest.migrateAfter(model, metamodel);

        verify(expression).set("returnType", Boolean.class.getName());
        verify(expression, times(2)).set("returnTypeFixed", true);
        verify(migrationUnderTest).addReportChange(anyString(), anyString(), anyString(), anyString(), anyString(), eq(IStatus.OK));
    }

    @Test
    public void testParameterUpdate() throws Exception {
        doReturn("another").when(validator).get("validatorClass");

        migrationUnderTest.migrateBefore(model, metamodel);
        migrationUnderTest.migrateAfter(model, metamodel);

        verify(expression, never()).set("returnType", Boolean.class.getName());
        verify(expression).set("returnTypeFixed", false);
        verify(migrationUnderTest).addReportChange(anyString(), anyString(), anyString(), anyString(), anyString(), eq(IStatus.WARNING));
    }

}
