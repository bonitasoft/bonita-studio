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
import java.io.InputStream;
import java.net.URL;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.document.core.repository.DocumentFileStore;
import org.bonitasoft.studio.document.core.repository.DocumentRepositoryStore;
import org.bonitasoft.studio.groovy.repository.GroovyFileStore;
import org.bonitasoft.studio.groovy.repository.GroovyRepositoryStore;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.eclipse.core.runtime.FileLocator;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author Mickael Istria
 */
public class TestImportRepository extends TestCase {

    private static final String TEST_ATTACHMENT_ARTIFACT_ID = "attachment.txt"; //$NON-NLS-1$

    private static final String TEST_ATTACHMENT_BAR_NAME = "Test Attachment Import-1.0.bos"; //$NON-NLS-1$

    private static final String TEST_GROOVY_ARTIFACT_ID = "Test.groovy";

    private File bosFile;

    private RepositoryAccessor repositoryAccessor;

    /*
     * (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        final URL url = TestImportRepository.class.getResource(TEST_ATTACHMENT_BAR_NAME);
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        try {
            final URL fileUrl = FileLocator.toFileURL(url);
            bosFile = new File(fileUrl.getFile());
            final ImportBosArchiveOperation op = new ImportBosArchiveOperation(repositoryAccessor);
            op.setArchiveFile(bosFile.getAbsolutePath());
            op.setCurrentRepository(repositoryAccessor.getCurrentRepository());
            op.run(Repository.NULL_PROGRESS_MONITOR);
        } catch (final Exception e) {
            e.printStackTrace();
            fail("Unable to find the test bar file"); //$NON-NLS-1$
        }
    }

    /*
     * (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        final GroovyRepositoryStore gStore = repositoryAccessor.getRepositoryStore(GroovyRepositoryStore.class);
        for (final GroovyFileStore artifact : gStore.getChildren()) {
            artifact.delete();
        }
        final DocumentRepositoryStore store = repositoryAccessor.getRepositoryStore(DocumentRepositoryStore.class);
        for (final DocumentFileStore artifact : store.getChildren()) {
            artifact.delete();
        }
    }

    @Test
    public void testImportAttachments() throws Exception {
        final DocumentRepositoryStore store = repositoryAccessor.getRepositoryStore(DocumentRepositoryStore.class);
        final DocumentFileStore fileStore = store.getChild(TEST_ATTACHMENT_ARTIFACT_ID, true);

        assertNotNull(fileStore);
        assertEquals(fileStore.getName(), TEST_ATTACHMENT_ARTIFACT_ID);
        final InputStream is = (InputStream) fileStore.getContent();
        assertTrue(is.read() > 0);
        /* don't forget to close the inputStream */
        is.close();

    }

    @Test
    public void testImportGroovy() throws Exception {
        final GroovyRepositoryStore gStore = RepositoryManager.getInstance().getRepositoryStore(GroovyRepositoryStore.class);
        final GroovyFileStore artifact = gStore.getChild(TEST_GROOVY_ARTIFACT_ID, true);
        assertNotNull(artifact);
        assertEquals(artifact.getName(), TEST_GROOVY_ARTIFACT_ID);
        final InputStream is = artifact.getResource().getContents();
        assertTrue(is.read() > 0);
        /* don't forget to close the inputStream */
        is.close();
    }

}
