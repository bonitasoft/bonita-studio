package org.bonitasoft.studio.application.ui.control;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.model.dependency.ArtifactType;
import org.junit.jupiter.api.Test;

class ExtensionTypeHandlerTest {

    @Test
    void should_return_connector_extension_type() {
        assertThat(new ExtensionTypeHandler(ArtifactType.CONNECTOR).getExtensionType()).isEqualTo("jar");
    }

    @Test
    void should_return_connector_hint_message() {
        assertThat(new ExtensionTypeHandler(ArtifactType.CONNECTOR).getHintMessage())
                .hasValue(Messages.legacyArchiveFormatHint);
    }

    @Test
    void should_return_actor_filter_extension_type() {
        assertThat(new ExtensionTypeHandler(ArtifactType.ACTOR_FILTER).getExtensionType()).isEqualTo("jar");
    }

    @Test
    void should_return_actor_filter_hint_message() {
        assertThat(new ExtensionTypeHandler(ArtifactType.ACTOR_FILTER).getHintMessage())
                .hasValue(Messages.legacyArchiveFormatHint);
    }

    @Test
    void should_return_theme_extension_type() {
        assertThat(new ExtensionTypeHandler(ArtifactType.THEME).getExtensionType()).isEqualTo("zip");
    }

    @Test
    void should_return_theme_hint_message() {
        assertThat(new ExtensionTypeHandler(ArtifactType.THEME).getHintMessage()).isEmpty();
    }

    @Test
    void should_return_rest_api_extension_type() {
        assertThat(new ExtensionTypeHandler(ArtifactType.REST_API).getExtensionType()).isEqualTo("zip");
    }

    @Test
    void should_return_rest_api_hint_message() {
        assertThat(new ExtensionTypeHandler(ArtifactType.REST_API).getHintMessage()).isEmpty();
    }

    @Test
    void should_return_other_extension_type() {
        assertThat(new ExtensionTypeHandler(ArtifactType.OTHER).getExtensionType()).isEqualTo("jar");
    }

    @Test
    void should_return_other_hint_message() {
        assertThat(new ExtensionTypeHandler(ArtifactType.OTHER).getHintMessage()).isEmpty();
    }

}
