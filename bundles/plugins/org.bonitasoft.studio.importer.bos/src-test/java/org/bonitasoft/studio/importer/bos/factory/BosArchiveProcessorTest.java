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

import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BosArchiveProcessorTest {

    @Mock
    private BosArchiveProcessor bap;

    @Mock
    private ImportBosArchiveOperation ibo;

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();

    @Before
    public void init() throws Exception {
        Mockito.doCallRealMethod().when(bap).getStatus();
        Mockito.doCallRealMethod().when(bap).createDiagram(Mockito.any(URL.class), Mockito.any(NullProgressMonitor.class));
        Mockito.doReturn(ibo).when(bap).createOperation(Mockito.any(File.class));

    }

    @Test
    public void testGetStatusOkBasic() throws MalformedURLException, IOException, Exception {
        mockStatus(IStatus.OK);

        bap.createDiagram(tmpFolder.newFile("test").toURI().toURL(), new NullProgressMonitor());

        assertThat(bap.getStatus().isOK()).isTrue();
    }

    @Test
    public void testGetStatusErrorForValidationOKForRun() throws MalformedURLException, IOException, Exception {
        mockStatus(IStatus.ERROR);

        bap.createDiagram(tmpFolder.newFile("test").toURI().toURL(), new NullProgressMonitor());

        assertThat(bap.getStatus().isOK()).isFalse();
    }

    @Test
    public void testGetStatusErrorForValidationErrorForRun() throws MalformedURLException, IOException, Exception {
        mockStatus(IStatus.ERROR);

        bap.createDiagram(tmpFolder.newFile("test").toURI().toURL(), new NullProgressMonitor());

        assertThat(bap.getStatus().isOK()).isFalse();
    }

    @Test
    public void testGetStatusOkForValidationErrorForRun() throws MalformedURLException, IOException, Exception {
        mockStatus(IStatus.ERROR);

        bap.createDiagram(tmpFolder.newFile("test").toURI().toURL(), new NullProgressMonitor());

        assertThat(bap.getStatus().isOK()).isFalse();
    }

    private void mockStatus(final int status) throws InvocationTargetException, InterruptedException {
        doReturn(new Status(status, "pluginId.test", "message")).when(ibo).getStatus();
    }

}
