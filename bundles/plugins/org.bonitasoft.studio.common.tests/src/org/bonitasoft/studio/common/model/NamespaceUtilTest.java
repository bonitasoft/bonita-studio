package org.bonitasoft.studio.common.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class NamespaceUtilTest {

    private static final String namespace = "http://documentation.bonitasoft.com/profile-xml-schema/1.0";

    @Test
    public void should_return_namespace_root() {
        String root = NamespaceUtil.namespaceRoot(namespace);
        assertThat(root).isEqualTo("http://documentation.bonitasoft.com/profile-xml-schema");
    }

    @Test
    public void should_return_namespace_version() {
        String root = NamespaceUtil.namespaceVersion(namespace);
        assertThat(root).isEqualTo("1.0");
    }

}
