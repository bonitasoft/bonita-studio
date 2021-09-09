package org.bonitasoft.studio.application.ui.control.model.dependency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.Arrays;

import org.junit.Test;
import org.osgi.framework.Version;

public class BonitaArtifactDependencyTest {

    private static final String OLDEST_VERSION = "1.0.0";
    private static final String MIDDLE_VERSION = "1.1.0";
    private static final String LATEST_VERSION = "2.0.0";

    private static final String CURRENT_BONITA_VERSION = "7.12.0";
    private static final String OLD_BONITA_VERSION = "7.11.0";
    private static final String OLDEST_BONITA_MIN_VERSION = "7.12.0";
    private static final String LATEST_BONITA_MIN_VERSION = "7.13.0";

    private BonitaArtifactDependencyVersion version1;
    private BonitaArtifactDependencyVersion version2;
    private BonitaArtifactDependencyVersion version3;

    @Test
    public void should_return_latest_compatibleVersion() {
        version1 = createVersion(OLDEST_VERSION, OLDEST_BONITA_MIN_VERSION, CURRENT_BONITA_VERSION);
        version2 = createVersion(MIDDLE_VERSION, OLDEST_BONITA_MIN_VERSION, CURRENT_BONITA_VERSION);
        version3 = createVersion(LATEST_VERSION, LATEST_BONITA_MIN_VERSION, CURRENT_BONITA_VERSION);

        BonitaArtifactDependency dependency = new BonitaArtifactDependency();
        dependency.setVersions(Arrays.asList(version1, version2, version3));
        assertThat(dependency.getLatestCompatibleVersion().get()).isEqualTo(version2);
    }

    @Test
    public void should_not_find_any_compatibleVersion() {
        version1 = createVersion(OLDEST_VERSION, OLDEST_BONITA_MIN_VERSION, OLD_BONITA_VERSION);
        version2 = createVersion(MIDDLE_VERSION, OLDEST_BONITA_MIN_VERSION, OLD_BONITA_VERSION);
        version3 = createVersion(LATEST_VERSION, LATEST_BONITA_MIN_VERSION, OLD_BONITA_VERSION);

        BonitaArtifactDependency dependency = new BonitaArtifactDependency();
        dependency.setVersions(Arrays.asList(version1, version2, version3));
        assertThat(dependency.getLatestCompatibleVersion()).isEmpty();
    }

    private BonitaArtifactDependencyVersion createVersion(String version, String bonitaMinVersion, String bonitaVersion) {
        BonitaArtifactDependencyVersion artifactDependencyVersion = spy(new BonitaArtifactDependencyVersion());
        artifactDependencyVersion.setVersion(version);
        artifactDependencyVersion.setBonitaMinVersion(bonitaMinVersion);

        Version currentVersion = new Version(bonitaVersion);
        doReturn(currentVersion).when(artifactDependencyVersion).getProductVersion();

        return artifactDependencyVersion;
    }

}
