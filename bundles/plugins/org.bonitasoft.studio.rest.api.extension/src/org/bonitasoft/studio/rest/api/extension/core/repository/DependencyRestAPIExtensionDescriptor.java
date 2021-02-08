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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;
import java.util.zip.ZipFile;

import org.bonitasoft.plugin.analyze.report.model.RestAPIExtension;
import org.bonitasoft.studio.common.log.BonitaStudioLog;

public class DependencyRestAPIExtensionDescriptor extends RestAPIExtensionDescriptor {

    private RestAPIExtension extension;

    public DependencyRestAPIExtensionDescriptor(RestAPIExtension extension) {
        super();
        this.extension = extension;
    }

    @Override
    public Properties getPageProperties() {
        File file = new File(extension.getFilePath());
        try {
            return findZipEntry(file, entry -> entry.getName().equals("page.properties"))
                    .map(entry -> {
                        try (ZipFile zipFile = new ZipFile(file);
                                Reader reader = new InputStreamReader(zipFile.getInputStream(entry),
                                        StandardCharsets.UTF_8)) {
                            Properties prop = new Properties();
                            prop.load(reader);
                            return prop;
                        } catch (IOException e) {
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .orElse(new Properties());
        } catch (IOException e) {
            BonitaStudioLog.error(e);
            return new Properties();
        }
    }

}
