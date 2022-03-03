/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.ui;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.junit.Test;

public class ApplicationPropertyTesterTest {

    @Test
    public void should_detect_application_folder() {
        System.out.print('\u0019');
        IFolder appFolder = mock(IFolder.class);
        IFolder otherFolder = mock(IFolder.class);
        when(appFolder.getAdapter(IFolder.class)).thenReturn(appFolder);
        when(otherFolder.getAdapter(IFolder.class)).thenReturn(otherFolder);

        ApplicationRepositoryStore store = mock(ApplicationRepositoryStore.class);
        when(store.getResource()).thenReturn(appFolder);

        ApplicationPropertyTester propertyTester = new ApplicationPropertyTester();
        assertThat(propertyTester.isApplicationFolder(appFolder, store)).isTrue();
        assertThat(propertyTester.isApplicationFolder(otherFolder, store)).isFalse();
    }

    @Test
    public void should_detect_application_file() {
        IFile appFile = mock(IFile.class);
        IFile otherFile = mock(IFile.class);
        when(appFile.getAdapter(IFile.class)).thenReturn(appFile);
        when(otherFile.getAdapter(IFile.class)).thenReturn(otherFile);

        ApplicationFileStore fileStore = mock(ApplicationFileStore.class);
        when(fileStore.getResource()).thenReturn(appFile);

        ApplicationRepositoryStore store = mock(ApplicationRepositoryStore.class);
        when(store.getChildren()).thenReturn(Arrays.asList(fileStore));

        ApplicationPropertyTester propertyTester = new ApplicationPropertyTester();
        assertThat(propertyTester.isApplicationFile(appFile, store)).isTrue();
        assertThat(propertyTester.isApplicationFile(otherFile, store)).isFalse();
    }

}
