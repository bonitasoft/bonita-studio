package org.bonitasoft.studio.common.repository.core.maven;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.junit.Before;
import org.junit.Test;

public class MavenProjectHelperTest {

    private static final String EXISTING_DEP_GROUP_ID = "existingGroupdId";
    private static final String EXISTING_DEP_ARTIFACT_ID = "existingArtifactId";
    private static final String EXISTING_DEP_ARTIFACT_ID_2 = "existingArtifactId2";
    private static final String EXISTING_DEP_VERSION = "1.0.0";
    private static final String EXISTING_DEP_TYPE = "jar";
    private static final String EXISTING_DEP_CLASSIFIER = "toto";

    Model mavenModel;
    MavenProjectHelper helper = new MavenProjectHelper();

    @Before
    public void init() {
        mavenModel = new Model();
        Dependency existingDependency = new Dependency();
        existingDependency.setGroupId(EXISTING_DEP_GROUP_ID);
        existingDependency.setArtifactId(EXISTING_DEP_ARTIFACT_ID);
        existingDependency.setVersion(EXISTING_DEP_VERSION);
        existingDependency.setType(EXISTING_DEP_TYPE);
        existingDependency.setClassifier(EXISTING_DEP_CLASSIFIER);

        Dependency existingDependency2 = new Dependency();
        existingDependency2.setGroupId(EXISTING_DEP_GROUP_ID);
        existingDependency2.setArtifactId(EXISTING_DEP_ARTIFACT_ID_2);
        existingDependency2.setVersion(EXISTING_DEP_VERSION);
        existingDependency2.setType(EXISTING_DEP_TYPE);

        mavenModel.addDependency(existingDependency);
        mavenModel.addDependency(existingDependency2);
    }

    @Test
    public void should_return_same_existing_dependency() {
        Dependency newDependency = new Dependency();
        newDependency.setGroupId(EXISTING_DEP_GROUP_ID);
        newDependency.setArtifactId(EXISTING_DEP_ARTIFACT_ID);
        newDependency.setVersion("whatever");
        newDependency.setType(EXISTING_DEP_TYPE);
        newDependency.setClassifier(EXISTING_DEP_CLASSIFIER);

        Optional<Dependency> existingDependency = helper.findDependencyInAnyVersion(mavenModel, newDependency);
        assertThat(existingDependency.isPresent());
        assertThat(existingDependency.get().getGroupId()).isEqualTo(EXISTING_DEP_GROUP_ID);
        assertThat(existingDependency.get().getArtifactId()).isEqualTo(EXISTING_DEP_ARTIFACT_ID);
        assertThat(existingDependency.get().getVersion()).isEqualTo(EXISTING_DEP_VERSION);
        assertThat(existingDependency.get().getType()).isEqualTo(EXISTING_DEP_TYPE);
        assertThat(existingDependency.get().getClassifier()).isEqualTo(EXISTING_DEP_CLASSIFIER);

        existingDependency = helper.findDependencyInAnyVersion(mavenModel,
                EXISTING_DEP_GROUP_ID,
                EXISTING_DEP_ARTIFACT_ID,
                EXISTING_DEP_TYPE,
                EXISTING_DEP_CLASSIFIER);
        assertThat(existingDependency.isPresent());
        assertThat(existingDependency.get().getGroupId()).isEqualTo(EXISTING_DEP_GROUP_ID);
        assertThat(existingDependency.get().getArtifactId()).isEqualTo(EXISTING_DEP_ARTIFACT_ID);
        assertThat(existingDependency.get().getVersion()).isEqualTo(EXISTING_DEP_VERSION);
        assertThat(existingDependency.get().getType()).isEqualTo(EXISTING_DEP_TYPE);
        assertThat(existingDependency.get().getClassifier()).isEqualTo(EXISTING_DEP_CLASSIFIER);
    }

    @Test
    public void should_not_find_any_same_existing_dependency() {
        Dependency newDependency = new Dependency();
        newDependency.setGroupId("other");
        newDependency.setArtifactId(EXISTING_DEP_ARTIFACT_ID);
        newDependency.setVersion("whatever");
        newDependency.setType(EXISTING_DEP_TYPE);
        newDependency.setClassifier(EXISTING_DEP_CLASSIFIER);

        Optional<Dependency> existingDependency = helper.findDependencyInAnyVersion(mavenModel, newDependency);
        assertThat(existingDependency).isEmpty();

        existingDependency = helper.findDependencyInAnyVersion(mavenModel,
                "other",
                EXISTING_DEP_ARTIFACT_ID,
                EXISTING_DEP_TYPE,
                EXISTING_DEP_CLASSIFIER);
        assertThat(existingDependency).isEmpty();
    }

    @Test
    public void should_manage_null_or_empty_classifier() {
        Optional<Dependency> existingDependency = helper.findDependencyInAnyVersion(mavenModel,
                EXISTING_DEP_GROUP_ID,
                EXISTING_DEP_ARTIFACT_ID_2,
                EXISTING_DEP_TYPE,
                null);
        assertThat(existingDependency).isPresent();

        existingDependency = helper.findDependencyInAnyVersion(mavenModel,
                EXISTING_DEP_GROUP_ID,
                EXISTING_DEP_ARTIFACT_ID_2,
                EXISTING_DEP_TYPE,
                "");
        assertThat(existingDependency).isPresent();
    }

}
