/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.tests.designer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.operation.CreateFragmentOperation;
import org.bonitasoft.studio.designer.core.repository.WebFragmentFileStore;
import org.bonitasoft.studio.designer.core.repository.WebFragmentRepositoryStore;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Before;
import org.junit.Test;

public class FragmentCreationIT {

    private RepositoryAccessor repositoryAccessor;

    @Before
    public void init() {
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
    }

    @Test
    public void should_create_fragment() throws Exception {
        CreateFragmentOperation createFragmentOperation = new CreateFragmentOperation(
                PageDesignerURLFactory.INSTANCE, repositoryAccessor);
        String newName = getFragmentNewName();
        createFragmentOperation.run(new NullProgressMonitor());
        assertThat(repositoryAccessor.getRepositoryStore(WebFragmentRepositoryStore.class).getChild(newName, true))
                .isNotNull();
    }

    private String getFragmentNewName() {
        List<String> existingFragments = repositoryAccessor.getRepositoryStore(WebFragmentRepositoryStore.class)
                .getChildren()
                .stream()
                .map(WebFragmentFileStore::getDisplayName)
                .collect(Collectors.toList());
        return StringIncrementer.getNextIncrement(CreateFragmentOperation.DEFAULT_FRAGMENT_NAME, existingFragments);
    }

}
