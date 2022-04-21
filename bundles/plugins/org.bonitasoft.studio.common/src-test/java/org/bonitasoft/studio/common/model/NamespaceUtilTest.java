package org.bonitasoft.studio.common.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class NamespaceUtilTest {

    private static final String namespace = "http://www.bonitasoft.org/ns/profile/6.1";

    @Test
    public void should_return_namespace_root() {
        String root = NamespaceUtil.namespaceRoot(namespace);
        assertThat(root).isEqualTo("http://www.bonitasoft.org/ns/profile");
    }

    @Test
    public void should_return_namespace_version() {
        String root = NamespaceUtil.namespaceVersion(namespace);
        assertThat(root).isEqualTo("6.1");
    }

}
