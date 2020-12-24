/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.importer.bos.provider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.model.ConflictStatus;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.importer.bos.model.AbstractFileModel;
import org.bonitasoft.studio.importer.bos.model.ImportFileStoreModel;
import org.bonitasoft.studio.importer.bos.model.ImportStoreModel;
import org.junit.Test;

public class ImportModelLabelProviderTest {

    @Test
    public void should_display_folder_name() throws Exception {
        final ImportModelLabelProvider labelProvider = new ImportModelLabelProvider();

        final String text = labelProvider.getText(new ImportStoreModel("myRepo", repositoryStore("myRepo")));

        assertThat(text).isEqualTo("myRepo");
    }

    @Test
    public void should_detect_conflicting_status() throws Exception {
        ImportModelLabelProvider labelProvider = new ImportModelLabelProvider();
        assertThat(labelProvider.hasStatus(conflictingImportFileModel(), ConflictStatus.CONFLICTING)).isTrue();
    }

    @Test
    public void should_detect_same_content_status() throws Exception {
        final ImportModelLabelProvider labelProvider = new ImportModelLabelProvider();
        assertThat(labelProvider.hasStatus(sameContentImportFileModel(), ConflictStatus.SAME_CONTENT)).isTrue();
    }

    private Object sameContentImportFileModel() {
        return new ImportFileStoreModel("myArchive/myRepo/myConflictingFile",
                new ImportStoreModel("myRepo", null, repositoryStore("myRepo")),
                ConflictStatus.SAME_CONTENT);
    }

    private AbstractFileModel conflictingImportFileModel() {
        return new ImportFileStoreModel("myArchive/myRepo/myConflictingFile",
                new ImportStoreModel("myRepo", null, repositoryStore("myRepo")),
                ConflictStatus.CONFLICTING);
    }

    private IRepositoryStore repositoryStore(String name) {
        final IRepositoryStore repositoryStore = mock(IRepositoryStore.class);
        when(repositoryStore.getName()).thenReturn(name);
        return repositoryStore;
    }

}
