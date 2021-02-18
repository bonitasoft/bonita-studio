/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
import java.io.InputStream;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.document.core.repository.DocumentFileStore;
import org.bonitasoft.studio.document.core.repository.DocumentRepositoryStore;
import org.bonitasoft.studio.groovy.repository.GroovyFileStore;
import org.bonitasoft.studio.groovy.repository.GroovyRepositoryStore;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author Aurelien Pupier
 */
public class TestImportExportAndDeleteRepository extends TestCase {

    @Test
    public void testImportExportGroovy() throws Exception {
        /* Join with the job because it adds DefaultUserScript.groovy to the artifacts */
        final GroovyRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(GroovyRepositoryStore.class);
        final int nbOfGroovyArtifacts = store.getChildren().size();
        final InputStream stream = TestImportExportAndDeleteRepository.class
                .getResourceAsStream("GroovyScriptForTestImportExportRepository.groovy");
        assertNotNull("Can't test groovy import because cannot retrieve the .groovy file", stream);
        final GroovyFileStore artifact = store.importInputStream("GroovyScriptForTestImportExportRepository.groovy", stream);
        assertEquals("import of Groovy doesn't work", nbOfGroovyArtifacts + 1, store.getChildren().size());
        final String s = System.getProperty("java.io.tmpdir");
        final File f = new File(s + File.separatorChar + "GroovyScriptForTestImportExportRepository.groovy");
        f.deleteOnExit();

        artifact.export(f.getParentFile().getAbsolutePath());

        final FileInputStream fis = new FileInputStream(f);
        assertTrue("The export of Groovy doesn't work.", fis.read() > 0);
        fis.close();

        /* Test delete of the artifact */
        artifact.delete();
        assertEquals("The delete of groovy doesn't work.", nbOfGroovyArtifacts, store.getChildren().size());
    }

    @Test
    public void testImportExportAttachment() throws Exception {
        final DocumentRepositoryStore store = RepositoryManager.getInstance()
                .getRepositoryStore(DocumentRepositoryStore.class);
        final int nbOfAttachmentArtrifacts = store.getChildren().size();
        final InputStream stream = TestImportExportAndDeleteRepository.class
                .getResourceAsStream("JarForTestImportExportRepository.jar");
        final DocumentFileStore artifact = store.importInputStream("JarForTestImportExportRepository.jar", stream);
        assertEquals("import of attachment doesn't work", nbOfAttachmentArtrifacts + 1, store.getChildren().size());
        final String s = System.getProperty("java.io.tmpdir");
        final File f = new File(s + File.separatorChar + "JarForTestImportExportRepository.jar");
        f.delete();
        artifact.export(f.getParentFile().getAbsolutePath());

        final FileInputStream fis = new FileInputStream(f);
        assertTrue("The export of attachment doesn't work.", fis.read() > 0);
        fis.close();

        /* Test delete of the artifact */
        artifact.delete();
        assertEquals("The delete of attachment doesn't work.", nbOfAttachmentArtrifacts, store.getChildren().size());
    }

}
