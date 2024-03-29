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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.UIDesignerServerManager;
import org.bonitasoft.studio.designer.core.operation.CreateCustomWidgetOperation;
import org.bonitasoft.studio.designer.core.operation.CreateFragmentOperation;
import org.bonitasoft.studio.designer.core.operation.CreateLayoutOperation;
import org.bonitasoft.studio.designer.core.operation.CreatePageOperation;
import org.bonitasoft.studio.designer.core.operation.CreateUIDArtifactOperation;
import org.bonitasoft.studio.designer.core.repository.WebFragmentRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebWidgetRepositoryStore;
import org.bonitasoft.studio.tests.util.InitialProjectRule;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class UIDArtifactCreationIT {

    @Rule
    public InitialProjectRule projectRule = InitialProjectRule.INSTANCE;

    private RepositoryAccessor repositoryAccessor;

    @Before
    public void init() {
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        UIDesignerServerManager.getInstance().start(repositoryAccessor.getCurrentRepository().orElseThrow(),
                new NullProgressMonitor());
    }

    @Test
    public void should_create_application_page() throws Exception {
        var createPageOperation = new CreatePageOperation(PageDesignerURLFactory.INSTANCE,
                repositoryAccessor).disableOpenBrowser();
        String newName = getPageNewName("page", CreateUIDArtifactOperation.DEFAULT_PAGE_NAME);
        createPageOperation.run(new NullProgressMonitor());
        assertThat(repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class).getChild(newName, true))
                .isNotNull();
    }

    @Test
    public void should_create_layout() throws Exception {
        var createLayoutOperation = new CreateLayoutOperation(PageDesignerURLFactory.INSTANCE,
                repositoryAccessor).disableOpenBrowser();
        String newName = getPageNewName("layout", CreateUIDArtifactOperation.DEFAULT_LAYOUT_NAME);
        createLayoutOperation.run(new NullProgressMonitor());
        assertThat(repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class).getChild(newName, true))
                .isNotNull();
    }

    @Test
    public void should_create_custom_widget() throws Exception {
        var createCustomWidgetOperation = new CreateCustomWidgetOperation(
                PageDesignerURLFactory.INSTANCE, repositoryAccessor)
                .disableOpenBrowser();
        String newName = getCustomWidgetNewName();
        createCustomWidgetOperation.run(new NullProgressMonitor());
        String newArtifactId = createCustomWidgetOperation.getNewArtifactId();
        assertThat(newArtifactId.toLowerCase()).endsWith(newName.toLowerCase());
        assertThat(repositoryAccessor.getRepositoryStore(WebWidgetRepositoryStore.class).getChild(newArtifactId, true))
                .isNotNull();
    }
    
    @Test
    public void should_create_fragment() throws Exception {
        var createFragmentOperation = new CreateFragmentOperation(
                PageDesignerURLFactory.INSTANCE, repositoryAccessor).disableOpenBrowser();
        String newName = getFragmentNewName();
        createFragmentOperation.run(new NullProgressMonitor());
        assertThat(repositoryAccessor.getRepositoryStore(WebFragmentRepositoryStore.class).getChild(newName, true))
                .isNotNull();
    }

    private String getFragmentNewName() {
        List<String> existingFragments = repositoryAccessor.getRepositoryStore(WebFragmentRepositoryStore.class)
                .getChildren()
                .stream()
                .map(IDisplayable::toDisplayName).filter(Optional::isPresent).map(Optional::get)
                .collect(Collectors.toList());
        return StringIncrementer.getNextIncrement(CreateFragmentOperation.DEFAULT_FRAGMENT_NAME, existingFragments);
    }

    private String getPageNewName(String artifactType, String defaultName) {
        List<String> existingPages = repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class).getChildren()
                .stream()
                .filter(store -> Objects.equals(store.getType(), artifactType))
                .map(IDisplayable::toDisplayName).filter(Optional::isPresent).map(Optional::get)
                .collect(Collectors.toList());
        return StringIncrementer.getNextIncrement(defaultName, existingPages);
    }

    private String getCustomWidgetNewName() {
        List<String> existingWidgets = repositoryAccessor.getRepositoryStore(WebWidgetRepositoryStore.class)
                .getChildren().stream()
                .map(IDisplayable::toDisplayName).filter(Optional::isPresent).map(Optional::get)
                .collect(Collectors.toList());
        return StringIncrementer.getNextIncrement(CreateUIDArtifactOperation.DEFAULT_WIDGET_NAME, existingWidgets);
    }

}
