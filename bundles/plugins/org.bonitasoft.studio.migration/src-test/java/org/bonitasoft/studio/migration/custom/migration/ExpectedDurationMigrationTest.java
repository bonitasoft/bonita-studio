/**
 * Copyright (C) 2016 BonitaSoft S.A.
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
package org.bonitasoft.studio.migration.custom.migration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.impl.MetamodelImpl;
import org.eclipse.emf.edapt.spi.migration.impl.ModelImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExpectedDurationMigrationTest {

    @Mock
    private ModelImpl model;

    @Mock
    private MetamodelImpl metamodel;
    
    @Test
    public void should_store_duration_value_before_migration() throws Exception {
        ExpectedDurationMigration migration = new ExpectedDurationMigration();
        final EList<Instance> taskList = new BasicEList<Instance>();
        Instance instance = mock(Instance.class);
        when(instance.get("duration")).thenReturn("1000");
        taskList.add(instance);
        when(model.getAllInstances("process.Task")).thenReturn(taskList);
        
        migration.migrateBefore(model, metamodel);
        
        assertThat(migration.taskDurations).containsValue("1000");
    }

    @Test
    public void should_convert_duration_into_expression_after_migration() throws Exception {
        Instance expressionInstance = mock(Instance.class);
        when(model.newInstance(anyString())).thenReturn(expressionInstance);
        ExpectedDurationMigration migration = new ExpectedDurationMigration();
        final EList<Instance> taskList = new BasicEList<Instance>();
        Instance instance = mock(Instance.class);
        when(instance.get("duration")).thenReturn("1000");
        taskList.add(instance);
        when(model.getAllInstances("process.Task")).thenReturn(taskList);
        
        migration.migrateBefore(model, metamodel);
        migration.migrateAfter(model, metamodel);
        
        verify(instance).set("expectedDuration", expressionInstance);
        verify(expressionInstance).set("name", "1000");
        verify(expressionInstance).set("content", "1000");
        verify(expressionInstance).set("type", ExpressionConstants.CONSTANT_TYPE);
        verify(expressionInstance).set("returnType", Long.class.getName());
        verify(expressionInstance).set("returnTypeFixed",true);
    }

}
