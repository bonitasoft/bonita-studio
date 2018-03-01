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
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ImportLibsOperationTest {

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();

    @Mock
    private DependencyRepositoryStore libStore;
    @Mock
    private DependencyFileStore libFileStore;

    @Before
    public void setup(){
        doReturn(libFileStore).when(libStore).createRepositoryFileStore(anyString());
    }

    @Test
    public void testImportASingleJar() throws Exception {
        final File tmpFileWhichWillBeCopied = tmpFolder.newFile("test.jar");
        FileUtil.copy(this.getClass().getResourceAsStream("test.jar"), new FileOutputStream(tmpFileWhichWillBeCopied));
        final String[] jarAndZips = new String[] { tmpFileWhichWillBeCopied.toString() };
        final ImportLibsOperation importLibsRunnable = new ImportLibsOperation(libStore, jarAndZips, "");
        importLibsRunnable.run(new NullProgressMonitor());

        verify(libStore).createRepositoryFileStore(tmpFileWhichWillBeCopied.getName());
        verify(libFileStore).save(notNull(Object.class));
    }

    @Test
    public void testImportSeveralJars() throws Exception {
        final File tmpFileWhichWillBeCopied1 = tmpFolder.newFile("test1.jar");
        FileUtil.copy(this.getClass().getResourceAsStream("test.jar"), new FileOutputStream(tmpFileWhichWillBeCopied1));
        final File tmpFileWhichWillBeCopied2 = tmpFolder.newFile("test2.jar");
        FileUtil.copy(this.getClass().getResourceAsStream("test.jar"), new FileOutputStream(tmpFileWhichWillBeCopied2));
        final String[] jarAndZips = new String[] { tmpFileWhichWillBeCopied1.toString(), tmpFileWhichWillBeCopied2.toString() };
        final ImportLibsOperation importLibsRunnable = new ImportLibsOperation(libStore, jarAndZips, "");
        importLibsRunnable.run(new NullProgressMonitor());

        verify(libStore, atLeast(1)).createRepositoryFileStore(tmpFileWhichWillBeCopied1.getName());
        verify(libStore).createRepositoryFileStore(tmpFileWhichWillBeCopied2.getName());
        verify(libFileStore, atLeast(2)).save(notNull(Object.class));
    }

    @Test
    public void testImportASingleZip() throws Exception {
        final File tmpFileWhichWillBeCopied1 = tmpFolder.newFile("test.zip");
        FileUtil.copy(this.getClass().getResourceAsStream("test.zip"), new FileOutputStream(tmpFileWhichWillBeCopied1));
        final String[] jarAndZips = new String[] { tmpFileWhichWillBeCopied1.toString() };
        final ImportLibsOperation importLibsRunnable = new ImportLibsOperation(libStore, jarAndZips, "");
        importLibsRunnable.run(new NullProgressMonitor());

        verify(libStore).createRepositoryFileStore("test.jar");
        verify(libStore).createRepositoryFileStore("test2.jar");
        verify(libFileStore, times(2)).save(notNull(Object.class));
    }

    @Test
    public void testImportASingleZipWithNoJarInside() throws Exception {
        final File tmpFileWhichWillBeCopied1 = tmpFolder.newFile("NoJar.zip");
        FileUtil.copy(this.getClass().getResourceAsStream("NoJar.zip"), new FileOutputStream(tmpFileWhichWillBeCopied1));
        final String[] jarAndZips = new String[] { tmpFileWhichWillBeCopied1.toString() };
        final ImportLibsOperation importLibsRunnable = new ImportLibsOperation(libStore, jarAndZips, "");
        importLibsRunnable.run(new NullProgressMonitor());

        verify(libStore, never()).createRepositoryFileStore(anyString());
        verify(libFileStore, never()).save(notNull(Object.class));
    }

    @Test
    public void testImportTwoZips() throws Exception {
        final File tmpFileWhichWillBeCopied1 = tmpFolder.newFile("test.zip");
        FileUtil.copy(this.getClass().getResourceAsStream("test.zip"), new FileOutputStream(tmpFileWhichWillBeCopied1));
        final File tmpFileWhichWillBeCopied2 = tmpFolder.newFile("test-2.zip");
        FileUtil.copy(this.getClass().getResourceAsStream("test-2.zip"), new FileOutputStream(tmpFileWhichWillBeCopied2));

        final String[] jarAndZips = new String[] { tmpFileWhichWillBeCopied1.toString(), tmpFileWhichWillBeCopied2.toString() };
        final ImportLibsOperation importLibsRunnable = new ImportLibsOperation(libStore, jarAndZips, "");
        importLibsRunnable.run(new NullProgressMonitor());

        verify(libStore).createRepositoryFileStore("test.jar");
        verify(libStore).createRepositoryFileStore("test2.jar");
        verify(libStore).createRepositoryFileStore("test-2.jar");
        verify(libStore).createRepositoryFileStore("test2-2.jar");
        verify(libFileStore, times(4)).save(notNull(Object.class));
    }

    @Test
    public void testImportJarsAndZips() throws Exception {
        final File tmpJarFileWhichWillBeCopied1 = tmpFolder.newFile("testjar.jar");
        FileUtil.copy(this.getClass().getResourceAsStream("test.jar"), new FileOutputStream(tmpJarFileWhichWillBeCopied1));
        final File tmpJarFileWhichWillBeCopied2 = tmpFolder.newFile("test2jar.jar");;
        FileUtil.copy(this.getClass().getResourceAsStream("test.jar"), new FileOutputStream(tmpJarFileWhichWillBeCopied2));

        final File tmpFileWhichWillBeCopied1 = tmpFolder.newFile("test.zip");
        FileUtil.copy(this.getClass().getResourceAsStream("test.zip"), new FileOutputStream(tmpFileWhichWillBeCopied1));
        final File tmpFileWhichWillBeCopied2 = tmpFolder.newFile("test-2.zip");
        FileUtil.copy(this.getClass().getResourceAsStream("test-2.zip"), new FileOutputStream(tmpFileWhichWillBeCopied2));

        final String[] jarAndZips = new String[] { tmpFileWhichWillBeCopied1.toString(), tmpFileWhichWillBeCopied2.toString(),
                tmpJarFileWhichWillBeCopied1.toString(), tmpJarFileWhichWillBeCopied2.toString() };
        final ImportLibsOperation importLibsRunnable = new ImportLibsOperation(libStore, jarAndZips, "");
        importLibsRunnable.run(new NullProgressMonitor());

        verify(libStore).createRepositoryFileStore("test.jar");
        verify(libStore).createRepositoryFileStore("test2.jar");
        verify(libStore).createRepositoryFileStore("test-2.jar");
        verify(libStore).createRepositoryFileStore("test2-2.jar");
        verify(libStore, atLeast(1)).createRepositoryFileStore(tmpJarFileWhichWillBeCopied1.getName());
        verify(libStore).createRepositoryFileStore(tmpJarFileWhichWillBeCopied2.getName());
        verify(libFileStore, atLeast(6)).save(notNull(Object.class));
    }

    @Test
    public void tesNoImportIfMonitorCancelled() throws IOException, InvocationTargetException, InterruptedException {
        final File tmpFileWhichWillBeCopied = tmpFolder.newFile("test.jar");
        FileUtil.copy(this.getClass().getResourceAsStream("test.jar"), new FileOutputStream(tmpFileWhichWillBeCopied));
        final String[] jarAndZips = new String[] { tmpFileWhichWillBeCopied.toString() };
        final ImportLibsOperation importLibsRunnable = new ImportLibsOperation(libStore, jarAndZips, "");
        final NullProgressMonitor monitor = new NullProgressMonitor();
        monitor.setCanceled(true);
        importLibsRunnable.run(monitor);

        verify(libStore, never()).createRepositoryFileStore(tmpFileWhichWillBeCopied.getName());
        verify(libFileStore, never()).save(notNull(Object.class));
    }

}
