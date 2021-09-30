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
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.apache.maven.project.MavenProject;
import org.bonitasoft.studio.common.extension.properties.ExtensionPagePropertiesReader;
import org.bonitasoft.studio.common.extension.properties.PagePropertyConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.project.IMavenProjectRegistry;

public abstract class CustomPageMavenProjectDescriptor {

    protected IProject project;
    private static final String PAGE_PROPERTIES_PATH = "src/main/resources/page.properties";

    public CustomPageMavenProjectDescriptor() {

    }

    public CustomPageMavenProjectDescriptor(final IProject project) {
        this.project = project;
    }

    protected IMavenProjectRegistry mavenProjectRegistry() {
        return MavenPlugin.getMavenProjectRegistry();
    }

    protected String getPagePropertyPath() {
        return PAGE_PROPERTIES_PATH;
    }

    public IProject getProject() {
        return project;
    }

    public String getName() {
        return project != null ? project.getName() : getCustomPageName();
    }

    public Properties getPageProperties() {
        final Properties properties = new Properties();
        if (project != null) {
            InputStream contents = null;
            try {
                final IFile propertyFile = getPropertyFile();
                if (!propertyFile.exists()) {
                    return properties;
                }
                contents = propertyFile.getContents();
                properties.load(contents);
            } catch (IOException | CoreException e) {
                BonitaStudioLog.error(e);
            } finally {
                if (contents != null) {
                    try {
                        contents.close();
                    } catch (final IOException e) {
                        BonitaStudioLog.error(e);
                    }
                }
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

    protected void ensureProjectOpen() {
        if (project != null && !project.isOpen()) {
            try {
                project.open(AbstractRepository.NULL_PROGRESS_MONITOR);
            } catch (final CoreException e) {
                BonitaStudioLog.error("Failed to open project " + getName(), e);
            }
        }
    }

    public Optional<MavenProject> getMavenProject() {
        return Optional.ofNullable(project)
                .flatMap(p -> Optional.ofNullable(MavenPlugin.getMavenProjectRegistry().getProject(p)))
                .flatMap(facade -> {
                    try {
                        return Optional.ofNullable(facade.getMavenProject(AbstractRepository.NULL_PROGRESS_MONITOR));
                    } catch (CoreException e) {
                        BonitaStudioLog.error(e);
                        return null;
                    }
                });
    }

    public String getArtifactId() {
        return getMavenProject().map(MavenProject::getArtifactId).orElse(null);
    }

    public String getVersion() {
        return getMavenProject().map(MavenProject::getVersion).orElse(null);
    }

    public abstract List<IFile> getFilesToOpen();

    public String getDescription() {
        return staticProperty(PagePropertyConstants.DESCRIPTION)
                .orElse(getMavenProject().map(MavenProject::getDescription).orElse(null));
    }

    public String getDisplayName() {
        return staticProperty(PagePropertyConstants.DISPLAY_NAME)
                .orElse(getMavenProject().map(MavenProject::getName).orElse(null));
    }

    public String getCustomPageName() {
        return staticProperty(PagePropertyConstants.NAME).orElse("custompage_" + getArtifactId());
    }

    private Optional<String> staticProperty(String property) {
        return ExtensionPagePropertiesReader.getProperty(getPageProperties(), property);
    }

}
