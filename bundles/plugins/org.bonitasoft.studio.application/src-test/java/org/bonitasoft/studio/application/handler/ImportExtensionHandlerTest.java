package org.bonitasoft.studio.application.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.application.operation.extension.ExtensionUpdateParticipantFactory;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.MavenRepositoryRegistry;
import org.junit.Before;
import org.junit.Test;

public class ImportExtensionHandlerTest {

    private static final String EXISTING_DEP_GROUP_ID = "existingGroupdId";
    private static final String EXISTING_DEP_ARTIFACT_ID = "existingArtifactId";
    private static final String EXISTING_DEP_VERSION = "1.0.0";

    Model mavenModel;
    ImportExtensionHandler handler = new ImportExtensionHandler(mock(RepositoryAccessor.class),
            mock(MavenRepositoryRegistry.class),
            mock(ExtensionUpdateParticipantFactory.class),
            new MavenProjectHelper(),
            null,
            null);

    @Before
    public void init() {
        mavenModel = new Model();
        Dependency existingDependency = new Dependency();
        existingDependency.setGroupId(EXISTING_DEP_GROUP_ID);
        existingDependency.setArtifactId(EXISTING_DEP_ARTIFACT_ID);
        existingDependency.setVersion(EXISTING_DEP_VERSION);
        mavenModel.addDependency(existingDependency);
    }

    @Test
    public void should_detect_a_dependency_update() {
        Dependency newDependency = new Dependency();
        newDependency.setGroupId(EXISTING_DEP_GROUP_ID);
        newDependency.setArtifactId(EXISTING_DEP_ARTIFACT_ID);
        assertThat(handler.isUpdate(mavenModel, newDependency)).isTrue();
    }

    @Test
    public void should_detect_a_new_dependency() {
        Dependency newDependency = new Dependency();
        newDependency.setGroupId(EXISTING_DEP_GROUP_ID);
        newDependency.setArtifactId("other");
        assertThat(handler.isUpdate(mavenModel, newDependency)).isFalse();

        newDependency.setGroupId("other");
        newDependency.setArtifactId(EXISTING_DEP_ARTIFACT_ID);
        assertThat(handler.isUpdate(mavenModel, newDependency)).isFalse();

        newDependency.setGroupId("other");
        newDependency.setArtifactId("other");
        assertThat(handler.isUpdate(mavenModel, newDependency)).isFalse();
    }

}
