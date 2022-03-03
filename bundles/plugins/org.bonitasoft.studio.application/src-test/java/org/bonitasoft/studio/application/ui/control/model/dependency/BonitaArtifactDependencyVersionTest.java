package org.bonitasoft.studio.application.ui.control.model.dependency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.Version;

public class BonitaArtifactDependencyVersionTest {

    private static final String OLDEST_VERSION = "1.0.0";
    private static final String MIDDLE_VERSION = "1.1.0";
    private static final String LATEST_VERSION = "2.0.0";

    private static final String CURRENT_BONITA_VERSION = "7.12.0";
    private static final String OLDEST_BONITA_MIN_VERSION = "7.12.0";
    private static final String LATEST_BONITA_MIN_VERSION = "7.13.0";

    private BonitaArtifactDependencyVersion version1;
    private BonitaArtifactDependencyVersion version2;
    private BonitaArtifactDependencyVersion version3;

    @Before
    public void init() {
        version1 = createVersion(OLDEST_VERSION, OLDEST_BONITA_MIN_VERSION);
        version2 = createVersion(MIDDLE_VERSION, OLDEST_BONITA_MIN_VERSION);
        version3 = createVersion(LATEST_VERSION, LATEST_BONITA_MIN_VERSION);
    }

    @Test
    public void should_sort_in_decreasing_order() {
        assertThat(version1.compareTo(version1)).isEqualTo(0);
        assertThat(version2.compareTo(version2)).isEqualTo(0);
        assertThat(version3.compareTo(version3)).isEqualTo(0);

        assertThat(version1.compareTo(version2)).isGreaterThan(0);
        assertThat(version1.compareTo(version3)).isGreaterThan(0);

        assertThat(version2.compareTo(version1)).isLessThan(0);
        assertThat(version2.compareTo(version3)).isGreaterThan(0);

        assertThat(version3.compareTo(version1)).isLessThan(0);
        assertThat(version3.compareTo(version2)).isLessThan(0);
    }

    @Test
    public void should_return_if_version_is_compatible() {
        assertThat(version1.isCompatible()).isTrue();
        assertThat(version2.isCompatible()).isTrue();
        assertThat(version3.isCompatible()).isFalse();
    }

    private BonitaArtifactDependencyVersion createVersion(String version, String bonitaMinVersion) {
        BonitaArtifactDependencyVersion artifactDependencyVersion = spy(new BonitaArtifactDependencyVersion());
        artifactDependencyVersion.setVersion(version);
        artifactDependencyVersion.setBonitaMinVersion(bonitaMinVersion);

        Version currentVersion = new Version(CURRENT_BONITA_VERSION);
        doReturn(currentVersion).when(artifactDependencyVersion).getProductVersion();

        return artifactDependencyVersion;
    }

}
