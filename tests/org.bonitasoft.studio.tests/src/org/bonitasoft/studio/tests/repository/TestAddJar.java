/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.OperationCanceledException;

import junit.framework.TestCase;

/**
 * @author Mickael Istria
 */
public class TestAddJar extends TestCase {

    public void testAddJar() throws IOException, OperationCanceledException {
        URL url = getClass().getResource("TestConnectorBis.jar");
        url = FileLocator.toFileURL(url);
        File file = new File(url.getFile());
        DependencyRepositoryStore drs = (DependencyRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(DependencyRepositoryStore.class);
        drs.importInputStream(file.getName(), new FileInputStream(file));
        assertNotNull("Jar missing after import", drs.getChild(file.getName()));
    }

    public void testAddJarTwiceWithSameId() throws URISyntaxException, IOException, CoreException {
        URL url = getClass().getResource("antlr-2.7.7-1.jar");
        url = FileLocator.toFileURL(url);
        File file = new File(url.getFile());
        InputStream fis = new FileInputStream(file);
        DependencyRepositoryStore drs = (DependencyRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(DependencyRepositoryStore.class);
        drs.importInputStream("antlr-2.7.7.jar", fis);
        fis.close();

        url = getClass().getResource("antlr-2.7.7-2.jar");
        url = FileLocator.toFileURL(url);
        file = new File(url.getFile());
        fis = new FileInputStream(file);
        drs.importInputStream("antlr-2.7.7.jar", fis);
        fis.close();

        assertTrue("Overwrite jar has failed", drs.getChild("antlr-2.7.7.jar") != null
                && drs.getChild("antlr-2.7.7.jar").getResource().exists()
                && drs.getChild("antlr-2.7.7.jar").getResource().getContents().available() > 0);

        drs.getChild("antlr-2.7.7.jar").delete();
    }
}
