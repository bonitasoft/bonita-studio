package org.bonitasoft.studio.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.osgi.framework.Version;

public class ProductVersionTest {
    
    @Test
    public void should_detect_timestamp_qualifier() throws Exception {
        assertThat(ProductVersion.hasTimestampQualifier(new Version("1.0.0.1233274"))).isTrue();
        assertThat(ProductVersion.hasTimestampQualifier(new Version("1.0.0"))).isFalse();
        assertThat(ProductVersion.hasTimestampQualifier(new Version("1.0.0.alpha-01"))).isFalse();
        assertThat(ProductVersion.hasTimestampQualifier(new Version("1.0.0.qualifier"))).isTrue();
    }

}
