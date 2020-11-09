/**
 * Copyright (C) 2017 BonitaSoft S.A.
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

import java.util.regex.Matcher;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;
import org.eclipse.m2e.core.project.ResolverConfiguration;
import org.eclipse.osgi.util.NLS;

public class MavenProjectConfiguration {

    private static final String GROUP_ID = "\\[groupId\\]"; //$NON-NLS-1$

    private static final String ARTIFACT_ID = "\\[artifactId\\]"; //$NON-NLS-1$

    private static final String VERSION = "\\[version\\]"; //$NON-NLS-1$

    private static final String NAME = "\\[name\\]"; //$NON-NLS-1$

    /** resolver configuration bean */
    private ResolverConfiguration resolverConfiguration;

    /** the project name template */
    private String projectNameTemplate = ""; //$NON-NLS-1$

    /** Creates a new configuration. */
    public MavenProjectConfiguration(ResolverConfiguration resolverConfiguration) {
        this.resolverConfiguration = resolverConfiguration;
    }

    /** Creates a new configuration. */
    public MavenProjectConfiguration() {
        this(new ResolverConfiguration());
    }

    /** Returns the resolver configuration bean. */
    public ResolverConfiguration getResolverConfiguration() {
        return resolverConfiguration;
    }

    /** Sets the project name template. */
    public void setProjectNameTemplate(String projectNameTemplate) {
        this.projectNameTemplate = projectNameTemplate;
    }

    /** Returns the project name template. */
    public String getProjectNameTemplate() {
        return projectNameTemplate;
    }

    public String getProjectName(Model model) {
        // XXX should use resolved MavenProject or Model
        if (projectNameTemplate.length() == 0) {
            return cleanProjectNameComponent(model.getArtifactId(), false);
        }

        String artifactId = model.getArtifactId();
        String groupId = model.getGroupId();
        if (groupId == null && model.getParent() != null) {
            groupId = model.getParent().getGroupId();
        }
        String version = model.getVersion();
        if (version == null && model.getParent() != null) {
            version = model.getParent().getVersion();
        }
        String name = model.getName();
        if (name == null || name.trim().isEmpty()) {
            name = artifactId;
        }

        // XXX needs MavenProjectManager update to resolve groupId and version
        return projectNameTemplate.replaceAll(GROUP_ID, cleanProjectNameComponent(groupId, true))
                .replaceAll(ARTIFACT_ID, cleanProjectNameComponent(artifactId, true))
                .replaceAll(NAME, cleanProjectNameComponent(name, true))
                .replaceAll(VERSION, version == null ? "" : cleanProjectNameComponent(version, true)); //$NON-NLS-1$
    }

    private static final String cleanProjectNameComponent(String value, boolean quote) {
        // remove property placeholders
        value = value.replaceAll("\\$\\{[^\\}]++\\}", ""); //$NON-NLS-1$ $NON-NLS-2$
        if (quote) {
            value = Matcher.quoteReplacement(value);
        }
        return value;
    }

    public IProject getProject(IWorkspaceRoot root, Model model) {
        return root.getProject(getProjectName(model));
    }

    public IStatus validateProjectName(Model model) {
        String projectName = getProjectName(model);
        IWorkspace workspace = ResourcesPlugin.getWorkspace();

        // check if the project name is valid
        IStatus nameStatus = workspace.validateName(projectName, IResource.PROJECT);
        if (!nameStatus.isOK()) {
            return nameStatus;
        }

        // check if project already exists
        if (workspace.getRoot().getProject(projectName).exists()) {
            return ValidationStatus.error(NLS.bind(Messages.importProjectExists, projectName));
        }

        return ValidationStatus.ok();
    }

    public ProjectImportConfiguration toImportConfiguration() {
        ProjectImportConfiguration configuration = new ProjectImportConfiguration(getResolverConfiguration());
        configuration.setProjectNameTemplate(projectNameTemplate);
        return configuration;
    }

}
