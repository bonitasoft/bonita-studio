/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.model;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.maven.i18n.Messages;

public class ConnectorArchetypeConfiguration extends ArchetypeConfigurationImpl
        implements ExtensionProjectArchetypeConfiguration, ArchetypeConfigurationWithBonitaVersion,
        ArchetypeConfigurationWithLanguage, ArchetypeConfigurationWithClassName {

    public static ConnectorArchetypeConfiguration defaultArchetypeConfiguration(ProjectMetadata projectMetadata) {
        final ConnectorArchetypeConfiguration configuration = new ConnectorArchetypeConfiguration();
        configuration.setProjectName("myCustomConnector");
        configuration.setGroupId(projectMetadata.getGroupId());
        configuration.setVersion(projectMetadata.getVersion());
        configuration.setLanguage(GROOVY_LANGUAGE);
        return configuration;
    }

    @Override
    public Map<String, String> toProperties() {
        final Map<String, String> properties = new HashMap<>();
        properties.put("bonitaVersion", getBonitaVersion());
        properties.put("className", getClassName());
        properties.put("language", getLanguage());
        properties.put("wrapper", "false");
        return properties;
    }

    @Override
    public String getArtifactLabel() {
        return Messages.connectorsRepositoryName;
    }

}
