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
package org.bonitasoft.studio.common.repository.jdt;

import org.assertj.core.api.Assertions;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaModelException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JDTTypeHierarchyManagerTest {

    @Mock
    private IType type;

    @Mock
    private ITypeHierarchy typeHierarchy;

    private class JDTTypeHierarchyManagerWithoutListener extends JDTTypeHierarchyManager {

        @Override
        protected void registerModificationListener() {
            //Do nothing
        }
    }

    @Before
    public void setUp() throws Exception {
        Mockito.doReturn(typeHierarchy).when(type).newTypeHierarchy(Mockito.any(IProgressMonitor.class));
        new JDTTypeHierarchyManagerWithoutListener().clearCache();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetTypeHierarchy() throws JavaModelException {
        final JDTTypeHierarchyManager jdtTypeHierarchyManager = new JDTTypeHierarchyManagerWithoutListener();
        final JDTTypeHierarchyManager spy = Mockito.spy(jdtTypeHierarchyManager);
        Mockito.doNothing().when(spy).registerModificationListener();
        Assertions.assertThat(spy.getTypeHierarchy(type)).isEqualTo(typeHierarchy);
        Assertions.assertThat(spy.getTypeHierarchy(type)).isEqualTo(typeHierarchy);
        Assertions.assertThat(spy.getTypeHierarchy(type)).isEqualTo(typeHierarchy);
        Mockito.verify(type, Mockito.times(1)).newTypeHierarchy(Mockito.any(IProgressMonitor.class));
    }

    @Test
    public void testClearCache() throws JavaModelException {
        final JDTTypeHierarchyManager jdtTypeHierarchyManager = new JDTTypeHierarchyManagerWithoutListener();
        final JDTTypeHierarchyManager spy = Mockito.spy(jdtTypeHierarchyManager);
        Assertions.assertThat(spy.getTypeHierarchy(type)).isEqualTo(typeHierarchy);
        spy.clearCache();
        Assertions.assertThat(spy.getTypeHierarchy(type)).isEqualTo(typeHierarchy);
        Mockito.verify(type, Mockito.times(2)).newTypeHierarchy(Mockito.any(IProgressMonitor.class));
    }

}
