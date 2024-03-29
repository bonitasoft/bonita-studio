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
package org.bonitasoft.studio.importer.bos.factory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;

import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BosArchiveProcessorTest {

    @Mock
    private BosArchiveProcessor bap;

    @Mock
    private ImportBosArchiveOperation ibo;

    @BeforeEach
    public void init() throws Exception {
        Mockito.doCallRealMethod().when(bap).getStatus();
        Mockito.doCallRealMethod().when(bap).createDiagram(Mockito.any(URL.class), Mockito.any(NullProgressMonitor.class));
        Mockito.doReturn(ibo).when(bap).createOperation(Mockito.any(File.class));

    }

    @Test
    void testGetStatusOkBasic(@TempDir Path tmpFolder) throws MalformedURLException, IOException, Exception {
        mockStatus(IStatus.OK);
        
        bap.createDiagram(tmpFolder.resolve("test").toFile().toURI().toURL(), new NullProgressMonitor());

        assertThat(bap.getStatus().isOK()).isTrue();
    }

    @Test
    void testGetStatusErrorForValidationOKForRun(@TempDir Path tmpFolder) throws MalformedURLException, IOException, Exception {
        mockStatus(IStatus.ERROR);

        bap.createDiagram(tmpFolder.resolve("test").toFile().toURI().toURL(), new NullProgressMonitor());

        assertThat(bap.getStatus().isOK()).isFalse();
    }

    @Test
    void testGetStatusErrorForValidationErrorForRun(@TempDir Path tmpFolder) throws MalformedURLException, IOException, Exception {
        mockStatus(IStatus.ERROR);

        bap.createDiagram(tmpFolder.resolve("test").toFile().toURI().toURL(), new NullProgressMonitor());

        assertThat(bap.getStatus().isOK()).isFalse();
    }

    @Test
    void testGetStatusOkForValidationErrorForRun(@TempDir Path tmpFolder) throws MalformedURLException, IOException, Exception {
        mockStatus(IStatus.ERROR);

        bap.createDiagram(tmpFolder.resolve("test").toFile().toURI().toURL(), new NullProgressMonitor());

        assertThat(bap.getStatus().isOK()).isFalse();
    }

    private void mockStatus(final int status) throws InvocationTargetException, InterruptedException {
        doReturn(new Status(status, "pluginId.test", "message")).when(ibo).getStatus();
    }

}
