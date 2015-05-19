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
package org.bonitasoft.studio.tests.dependencies.handler;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.dependencies.handler.ImportLibsRunnable;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ImportLibsRunnableIT {

    private List<File> toClean;

    @Before
    public void setup() {
        toClean = new ArrayList<File>();
    }

    @After
    public void tearDown() {
        for (final File file : toClean) {
            file.delete();
        }
    }

    @Test
    public void testImportLibs() throws IOException, InvocationTargetException, InterruptedException {
        final File tmpJarFileWhichWillBeCopied1 = File.createTempFile("testImport1-", ".jar");
        toClean.add(tmpJarFileWhichWillBeCopied1);
        FileUtil.copy(this.getClass().getResourceAsStream("test.jar"), new FileOutputStream(tmpJarFileWhichWillBeCopied1));
        final File tmpJarFileWhichWillBeCopied2 = File.createTempFile("testImport2-", ".jar");
        toClean.add(tmpJarFileWhichWillBeCopied2);
        FileUtil.copy(this.getClass().getResourceAsStream("test.jar"), new FileOutputStream(tmpJarFileWhichWillBeCopied2));

        final File tmpFileWhichWillBeCopied1 = File.createTempFile("testImport1-", ".zip");
        toClean.add(tmpFileWhichWillBeCopied1);
        FileUtil.copy(this.getClass().getResourceAsStream("test.zip"), new FileOutputStream(tmpFileWhichWillBeCopied1));
        final File tmpFileWhichWillBeCopied2 = File.createTempFile("testImport2-", ".zip");
        toClean.add(tmpFileWhichWillBeCopied2);
        FileUtil.copy(this.getClass().getResourceAsStream("test-2.zip"), new FileOutputStream(tmpJarFileWhichWillBeCopied2));

        final String[] jarAndZips = new String[] { tmpFileWhichWillBeCopied1.toString(), tmpFileWhichWillBeCopied2.toString(),
                tmpJarFileWhichWillBeCopied1.toString(), tmpJarFileWhichWillBeCopied2.toString() };
        final DependencyRepositoryStore libStore = RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class);
        final ImportLibsRunnable importLibsRunnable = new ImportLibsRunnable(libStore, jarAndZips, "");
        importLibsRunnable.run(new NullProgressMonitor());

        assertThat(libStore.getChild("test.jar")).isNotNull();
        assertThat(libStore.getChild("test2.jar")).isNotNull();
        assertThat(libStore.getChild("test-2.jar")).isNotNull();
        assertThat(libStore.getChild("test2-2.jar")).isNotNull();
        assertThat(libStore.getChild(tmpJarFileWhichWillBeCopied1.getName())).isNotNull();
        assertThat(libStore.getChild(tmpJarFileWhichWillBeCopied2.getName())).isNotNull();
    }

}
