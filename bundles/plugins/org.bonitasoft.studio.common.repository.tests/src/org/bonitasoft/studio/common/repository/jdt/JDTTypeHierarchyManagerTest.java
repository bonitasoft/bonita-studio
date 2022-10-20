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
package org.bonitasoft.studio.common.repository.jdt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaModelException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JDTTypeHierarchyManagerTest {

    @Mock
    private IType type;

    @Mock
    private ITypeHierarchy typeHierarchy;

    @Mock
    private IResource jarResource;

    @Mock
    private IResource notAJarResource;

    @Before
    public void setUp() throws Exception {
        Mockito.doReturn(typeHierarchy).when(type).newTypeHierarchy(Mockito.any());
        new JDTTypeHierarchyManager().clearCache();
        doReturn("jar").when(jarResource).getFileExtension();
        doReturn("whatever").when(notAJarResource).getFileExtension();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetTypeHierarchy() throws JavaModelException {
        final JDTTypeHierarchyManager jdtTypeHierarchyManager = new JDTTypeHierarchyManager();
        final JDTTypeHierarchyManager spy = spy(jdtTypeHierarchyManager);
        assertThat(spy.getTypeHierarchy(type)).isEqualTo(typeHierarchy);
        assertThat(spy.getTypeHierarchy(type)).isEqualTo(typeHierarchy);
        assertThat(spy.getTypeHierarchy(type)).isEqualTo(typeHierarchy);
        verify(type, times(1)).newTypeHierarchy(any());
    }

    @Test
    public void testClearCache() throws JavaModelException {
        final JDTTypeHierarchyManager jdtTypeHierarchyManager = new JDTTypeHierarchyManager();
        final JDTTypeHierarchyManager spy = spy(jdtTypeHierarchyManager);
        assertThat(spy.getTypeHierarchy(type)).isEqualTo(typeHierarchy);
        spy.clearCache();
        assertThat(spy.getTypeHierarchy(type)).isEqualTo(typeHierarchy);
        verify(type, Mockito.times(2)).newTypeHierarchy(any());
    }

    @Test
    public void should_clear_cache_when_catching_a_file_store_event_related_to_a_jar() throws JavaModelException {
        final JDTTypeHierarchyManager jdtTypeHierarchyManager = spy(new JDTTypeHierarchyManager());

        final FileStoreChangeEvent event = mock(FileStoreChangeEvent.class, RETURNS_DEEP_STUBS);
        when(event.getFileStore().getResource()).thenReturn(jarResource);

        jdtTypeHierarchyManager.handleFileStoreEvent(event);

        verify(jdtTypeHierarchyManager).clearCache();
    }

    @Test
    public void should_not_clear_cache_when_catching_a_file_store_event_not_related_to_ajar() throws JavaModelException {
        final JDTTypeHierarchyManager jdtTypeHierarchyManager = spy(new JDTTypeHierarchyManager());

        final FileStoreChangeEvent event = mock(FileStoreChangeEvent.class, RETURNS_DEEP_STUBS);
        when(event.getFileStore().getResource()).thenReturn(notAJarResource);

        jdtTypeHierarchyManager.handleFileStoreEvent(event);

        verify(jdtTypeHierarchyManager, never()).clearCache();
    }

    @Test
    public void should_not_clear_cache_when_catching_a_file_store_event_with_no_fileStore() throws JavaModelException {
        final JDTTypeHierarchyManager jdtTypeHierarchyManager = spy(new JDTTypeHierarchyManager());

        final FileStoreChangeEvent event = mock(FileStoreChangeEvent.class, RETURNS_DEEP_STUBS);
        when(event.getFileStore()).thenReturn(null);

        jdtTypeHierarchyManager.handleFileStoreEvent(event);

        verify(jdtTypeHierarchyManager, never()).clearCache();
    }

    @Test
    public void should_not_clear_cache_when_catching_a_file_store_event_with_no_resources() throws JavaModelException {
        final JDTTypeHierarchyManager jdtTypeHierarchyManager = spy(new JDTTypeHierarchyManager());

        final FileStoreChangeEvent event = mock(FileStoreChangeEvent.class, RETURNS_DEEP_STUBS);
        when(event.getFileStore().getResource()).thenReturn(null);

        jdtTypeHierarchyManager.handleFileStoreEvent(event);

        verify(jdtTypeHierarchyManager, never()).clearCache();
    }
}
