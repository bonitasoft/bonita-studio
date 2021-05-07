/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.core.repository;

import java.io.File;
import java.util.Properties;

import org.bonitasoft.plugin.analyze.report.model.RestAPIExtension;
import org.bonitasoft.studio.common.extension.properties.ExtensionPagePropertiesReader;

public class DependencyRestAPIExtensionDescriptor extends RestAPIExtensionDescriptor {

    private RestAPIExtension extension;

    public DependencyRestAPIExtensionDescriptor(RestAPIExtension extension) {
        super();
        this.extension = extension;
    }

    @Override
    public Properties getPageProperties() {
        File file = new File(extension.getArtifact().getFile());
        return ExtensionPagePropertiesReader.getPageProperties(file);
    }

}
