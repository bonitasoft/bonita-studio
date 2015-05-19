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
package org.bonitasoft.studio.dependencies.handler;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ImportLibsRunnableTest {

    @Mock
    private DependencyRepositoryStore libStore;
    @Mock
    private DependencyFileStore libFileStore;

    private List<File> toClean;

    @Before
    public void setup(){
        doReturn(libFileStore).when(libStore).createRepositoryFileStore(anyString());
        toClean = new ArrayList<File>();
    }

    @After
    public void tearDown() {
        for (final File file : toClean) {
            file.delete();
        }
    }

    @Test
    public void testImportASingleJar() throws Exception {
        final File tmpFileWhichWillBeCopied = File.createTempFile("testImport", ".jar");
        toClean.add(tmpFileWhichWillBeCopied);
        Files.copy(this.getClass().getResourceAsStream("test.jar"), tmpFileWhichWillBeCopied.toPath(), StandardCopyOption.REPLACE_EXISTING);
        final String[] jarAndZips = new String[] { tmpFileWhichWillBeCopied.toString() };
        final ImportLibsRunnable importLibsRunnable = new ImportLibsRunnable(libStore, jarAndZips, "");
        importLibsRunnable.run(new NullProgressMonitor());

        verify(libStore).createRepositoryFileStore(tmpFileWhichWillBeCopied.getName());
        verify(libFileStore).save(notNull(Object.class));
    }

    @Test
    public void testImportSeveralJars() throws Exception {
        final File tmpFileWhichWillBeCopied1 = File.createTempFile("testImport1-", ".jar");
        toClean.add(tmpFileWhichWillBeCopied1);
        Files.copy(this.getClass().getResourceAsStream("test.jar"), tmpFileWhichWillBeCopied1.toPath(), StandardCopyOption.REPLACE_EXISTING);
        final File tmpFileWhichWillBeCopied2 = File.createTempFile("testImport2-", ".jar");
        toClean.add(tmpFileWhichWillBeCopied2);
        Files.copy(this.getClass().getResourceAsStream("test.jar"), tmpFileWhichWillBeCopied2.toPath(), StandardCopyOption.REPLACE_EXISTING);
        final String[] jarAndZips = new String[] { tmpFileWhichWillBeCopied1.toString(), tmpFileWhichWillBeCopied2.toString() };
        final ImportLibsRunnable importLibsRunnable = new ImportLibsRunnable(libStore, jarAndZips, "");
        importLibsRunnable.run(new NullProgressMonitor());

        verify(libStore).createRepositoryFileStore(tmpFileWhichWillBeCopied1.getName());
        verify(libStore).createRepositoryFileStore(tmpFileWhichWillBeCopied2.getName());
        verify(libFileStore, times(2)).save(notNull(Object.class));
    }

    @Test
    public void testImportASingleZip() throws Exception {
        final File tmpFileWhichWillBeCopied1 = File.createTempFile("testImport1-", ".zip");
        toClean.add(tmpFileWhichWillBeCopied1);
        Files.copy(this.getClass().getResourceAsStream("test.zip"), tmpFileWhichWillBeCopied1.toPath(), StandardCopyOption.REPLACE_EXISTING);
        final String[] jarAndZips = new String[] { tmpFileWhichWillBeCopied1.toString() };
        final ImportLibsRunnable importLibsRunnable = new ImportLibsRunnable(libStore, jarAndZips, "");
        importLibsRunnable.run(new NullProgressMonitor());

        verify(libStore).createRepositoryFileStore("test.jar");
        verify(libStore).createRepositoryFileStore("test2.jar");
        verify(libFileStore, times(2)).save(notNull(Object.class));
    }

    @Test
    public void testImportASingleZipWithNoJarInside() throws Exception {
        final File tmpFileWhichWillBeCopied1 = File.createTempFile("testImportNoJar-", ".zip");
        toClean.add(tmpFileWhichWillBeCopied1);
        Files.copy(this.getClass().getResourceAsStream("NoJar.zip"), tmpFileWhichWillBeCopied1.toPath(), StandardCopyOption.REPLACE_EXISTING);
        final String[] jarAndZips = new String[] { tmpFileWhichWillBeCopied1.toString() };
        final ImportLibsRunnable importLibsRunnable = new ImportLibsRunnable(libStore, jarAndZips, "");
        importLibsRunnable.run(new NullProgressMonitor());

        verify(libStore, never()).createRepositoryFileStore(anyString());
        verify(libFileStore, never()).save(notNull(Object.class));
    }

    @Test
    public void testImportTwoZips() throws Exception {
        final File tmpFileWhichWillBeCopied1 = File.createTempFile("testImport1-", ".zip");
        toClean.add(tmpFileWhichWillBeCopied1);
        Files.copy(this.getClass().getResourceAsStream("test.zip"), tmpFileWhichWillBeCopied1.toPath(), StandardCopyOption.REPLACE_EXISTING);
        final File tmpFileWhichWillBeCopied2 = File.createTempFile("testImport2-", ".zip");
        toClean.add(tmpFileWhichWillBeCopied2);
        Files.copy(this.getClass().getResourceAsStream("test-2.zip"), tmpFileWhichWillBeCopied2.toPath(), StandardCopyOption.REPLACE_EXISTING);

        final String[] jarAndZips = new String[] { tmpFileWhichWillBeCopied1.toString(), tmpFileWhichWillBeCopied2.toString() };
        final ImportLibsRunnable importLibsRunnable = new ImportLibsRunnable(libStore, jarAndZips, "");
        importLibsRunnable.run(new NullProgressMonitor());

        verify(libStore).createRepositoryFileStore("test.jar");
        verify(libStore).createRepositoryFileStore("test2.jar");
        verify(libStore).createRepositoryFileStore("test-2.jar");
        verify(libStore).createRepositoryFileStore("test2-2.jar");
        verify(libFileStore, times(4)).save(notNull(Object.class));
    }

    @Test
    public void testImportJarsAndZips() throws Exception {
        final File tmpJarFileWhichWillBeCopied1 = File.createTempFile("testImport1-", ".jar");
        toClean.add(tmpJarFileWhichWillBeCopied1);
        Files.copy(this.getClass().getResourceAsStream("test.jar"), tmpJarFileWhichWillBeCopied1.toPath(), StandardCopyOption.REPLACE_EXISTING);
        final File tmpJarFileWhichWillBeCopied2 = File.createTempFile("testImport2-", ".jar");
        toClean.add(tmpJarFileWhichWillBeCopied2);
        Files.copy(this.getClass().getResourceAsStream("test.jar"), tmpJarFileWhichWillBeCopied2.toPath(), StandardCopyOption.REPLACE_EXISTING);

        final File tmpFileWhichWillBeCopied1 = File.createTempFile("testImport1-", ".zip");
        toClean.add(tmpFileWhichWillBeCopied1);
        Files.copy(this.getClass().getResourceAsStream("test.zip"), tmpFileWhichWillBeCopied1.toPath(), StandardCopyOption.REPLACE_EXISTING);
        final File tmpFileWhichWillBeCopied2 = File.createTempFile("testImport2-", ".zip");
        toClean.add(tmpFileWhichWillBeCopied2);
        Files.copy(this.getClass().getResourceAsStream("test-2.zip"), tmpFileWhichWillBeCopied2.toPath(), StandardCopyOption.REPLACE_EXISTING);

        final String[] jarAndZips = new String[] { tmpFileWhichWillBeCopied1.toString(), tmpFileWhichWillBeCopied2.toString(),
                tmpJarFileWhichWillBeCopied1.toString(), tmpJarFileWhichWillBeCopied2.toString() };
        final ImportLibsRunnable importLibsRunnable = new ImportLibsRunnable(libStore, jarAndZips, "");
        importLibsRunnable.run(new NullProgressMonitor());

        verify(libStore).createRepositoryFileStore("test.jar");
        verify(libStore).createRepositoryFileStore("test2.jar");
        verify(libStore).createRepositoryFileStore("test-2.jar");
        verify(libStore).createRepositoryFileStore("test2-2.jar");
        verify(libStore).createRepositoryFileStore(tmpJarFileWhichWillBeCopied1.getName());
        verify(libStore).createRepositoryFileStore(tmpJarFileWhichWillBeCopied2.getName());
        verify(libFileStore, times(6)).save(notNull(Object.class));
    }

    @Test
    public void tesNoImportIfMonitorCancelled() throws IOException, InvocationTargetException, InterruptedException {
        final File tmpFileWhichWillBeCopied = File.createTempFile("testImport", ".jar");
        toClean.add(tmpFileWhichWillBeCopied);
        Files.copy(this.getClass().getResourceAsStream("test.jar"), tmpFileWhichWillBeCopied.toPath(), StandardCopyOption.REPLACE_EXISTING);
        final String[] jarAndZips = new String[] { tmpFileWhichWillBeCopied.toString() };
        final ImportLibsRunnable importLibsRunnable = new ImportLibsRunnable(libStore, jarAndZips, "");
        final NullProgressMonitor monitor = new NullProgressMonitor();
        monitor.setCanceled(true);
        importLibsRunnable.run(monitor);

        verify(libStore, never()).createRepositoryFileStore(tmpFileWhichWillBeCopied.getName());
        verify(libFileStore, never()).save(notNull(Object.class));
    }

}
