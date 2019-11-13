/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.tests.migration;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import org.assertj.core.api.Assertions;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DocumentType;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.core.runtime.FileLocator;
import org.junit.Before;
import org.junit.Test;

public class DocumentMigrationIT {

    private RepositoryAccessor repositoryAccessor;

    @Before
    public void init() {
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
    }

    @Test
    public void testDocumentMigrationTypeFrom63() throws IOException, InvocationTargetException, InterruptedException {
        final ImportBosArchiveOperation op = new ImportBosArchiveOperation(repositoryAccessor);
        final URL fileURL1 = FileLocator
                .toFileURL(DocumentMigrationIT.class.getResource("DiagramToTestDocumentTypeMigration-1.0.bos"));
        op.setArchiveFile(FileLocator.toFileURL(fileURL1).getFile());
        op.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        op.run(Repository.NULL_PROGRESS_MONITOR);

        final DiagramRepositoryStore store = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class);
        final MainProcess mainProcess = store.getChild("DiagramToTestDocumentTypeMigration-1.0.proc", true).getContent();
        final Pool pool = (Pool) mainProcess.getElements().get(0);
        for (final Document document : pool.getDocuments()) {
            final String documentName = document.getName();
            if ("documentWithExternalCheckedWithoutInitialContent".equals(documentName)
                    || "documentWithInternalCheckedWithoutInitialContent".equals(documentName)) {
                Assertions.assertThat(document.getDocumentType()).isEqualTo(DocumentType.NONE);
            } else if ("documentWithExternalCheckedWithInitialContent".equals(documentName)) {
                Assertions.assertThat(document.getDocumentType()).isEqualTo(DocumentType.EXTERNAL);
                Assertions.assertThat(document.getUrl().getContent()).isEqualTo("http://test.demo");
            } else if ("documentWithInternalCheckedWithInitialContent".equals(documentName)) {
                Assertions.assertThat(document.getDocumentType()).isEqualTo(DocumentType.INTERNAL);
                Assertions.assertThat(document.getDefaultValueIdOfDocumentStore()).isNotEmpty();
            }
        }
    }

    @Test
    public void should_migrate_multiple_document_attribute()
            throws IOException, InvocationTargetException, InterruptedException {
        final ImportBosArchiveOperation op = new ImportBosArchiveOperation(repositoryAccessor);
        final URL fileURL1 = FileLocator.toFileURL(DocumentMigrationIT.class.getResource("MultipleDocumentDiagram-1.0.bos"));
        op.setArchiveFile(FileLocator.toFileURL(fileURL1).getFile());
        op.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        op.run(Repository.NULL_PROGRESS_MONITOR);

        final DiagramRepositoryStore store = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class);
        final MainProcess mainProcess = store.getChild("MultipleDocumentDiagram-1.0.proc", true).getContent();
        final Pool pool = (Pool) mainProcess.getElements().get(0);
        assertThat(pool.getDocuments()).hasSize(1);
        assertThat(pool.getDocuments().get(0).isMultiple()).isTrue();
    }

}
