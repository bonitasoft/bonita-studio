/**
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.maven;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

import org.bonitasoft.studio.common.extension.properties.ExtensionPagePropertiesReader;
import org.bonitasoft.studio.common.extension.properties.PagePropertyConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

/**
 * Describes a custom page project (theme or Rest API extension).
 */
public abstract class CustomPageProjectDescriptor extends ExtensionProjectDescriptor {

    private String pagePropertiesPath = "src/main/resources/page.properties";

    public CustomPageProjectDescriptor(String pagePropertiesPath) {
        super();
        this.pagePropertiesPath = pagePropertiesPath;
    }

    public CustomPageProjectDescriptor(final IProject project, String pagePropertiesPath) {
        super(project);
        this.pagePropertiesPath = pagePropertiesPath;
    }

    public CustomPageProjectDescriptor(final IProject project) {
        super(project);
    }

    protected String getPagePropertyPath() {
        return pagePropertiesPath;
    }

    public String getName() {
        return Optional.ofNullable(super.getName()).orElseGet(this::getCustomPageName);
    }

    public Properties getPageProperties() {
        final Properties properties = new Properties();
        if (project != null) {
            var propertyFile = getPropertyFile();
            if (!propertyFile.exists()) {
                return properties;
            }
            try (var is = getPropertyFile().getContents()) {
                properties.load(is);
            } catch (IOException | CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        return properties;
    }

    public void savePageProperties(final Properties pageProperties) {
        if (project != null) {
            final IFile propertyFile = getPropertyFile();
            final File file = propertyFile.getLocation().toFile();
            file.delete();
            try (final FileWriter fileWriter = new FileWriter(file);) {
                pageProperties.store(fileWriter, null);
                propertyFile.refreshLocal(IResource.DEPTH_ONE, AbstractRepository.NULL_PROGRESS_MONITOR);
            } catch (final CoreException | IOException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    public IFile getPropertyFile() {
        ensureProjectOpen();
        return project.getFile(getPagePropertyPath());
    }

    public String getDescription() {
        return staticProperty(PagePropertyConstants.DESCRIPTION).orElseGet(super::getDescription);
    }

    public String getDisplayName() {
        return staticProperty(PagePropertyConstants.DISPLAY_NAME).orElseGet(super::getDisplayName);
    }

    public String getContentType() {
        return staticProperty(PagePropertyConstants.CONTENT_TYPE).orElse(null);
    }

    public String getCustomPageName() {
        return staticProperty(PagePropertyConstants.NAME).orElse("custompage_" + getArtifactId());
    }

    private Optional<String> staticProperty(String property) {
        return ExtensionPagePropertiesReader.getProperty(getPageProperties(), property);
    }

}
