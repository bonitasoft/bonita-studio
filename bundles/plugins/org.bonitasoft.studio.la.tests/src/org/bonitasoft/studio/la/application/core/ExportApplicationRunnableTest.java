/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.core;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class ExportApplicationRunnableTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void should_export_applications() throws Exception {
        final File folder = temporaryFolder.newFolder();

        final ApplicationFileStore appication1 = mock(ApplicationFileStore.class);
        when(appication1.getName()).thenReturn("app1");
        final ApplicationFileStore appication2 = mock(ApplicationFileStore.class);
        when(appication2.getName()).thenReturn("app2");

        final List<IRepositoryFileStore> list = new ArrayList<>();
        list.add(appication1);
        list.add(appication2);

        final ExportApplicationRunnable operation = new ExportApplicationRunnable(folder.getPath(), list);
        operation.run(new NullProgressMonitor());

        verify(appication1).export(folder.getPath());
        verify(appication2).export(folder.getPath());
    }
}
