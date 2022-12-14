/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.BonitaProjectNature;
import org.bonitasoft.studio.common.repository.core.internal.BonitaProjectImpl;
import org.bonitasoft.studio.common.repository.core.maven.BonitaProjectBuilder;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.team.GitProject;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.m2e.core.internal.IMavenConstants;

public interface BonitaProject extends GitProject, IAdaptable {

    public static final String REST_API_EXTENSIONS_FOLDER = "restAPIExtensions";
    public static final String THEMES_FOLDER = "themes";
    Collection<String> NATRUES = List.of(IMavenConstants.NATURE_ID, BonitaProjectNature.NATURE_ID, JavaCore.NATURE_ID,
            "org.eclipse.jdt.groovy.core.groovyNature");
    Collection<String> BUILDERS = List.of(IMavenConstants.BUILDER_ID, BonitaProjectBuilder.ID, JavaCore.BUILDER_ID);
    String APP_MODULE = "app";
    String BDM_MODULE = "bdm";

    String getId();

    String getDisplayName();

    ProjectMetadata getProjectMetadata(IProgressMonitor monitor) throws CoreException;

    void update(ProjectMetadata metadata, IProgressMonitor monitor) throws CoreException;

    void open(IProgressMonitor monitor) throws CoreException;

    void close(IProgressMonitor monitor) throws CoreException;

    void delete(IProgressMonitor monitor) throws CoreException;

    String getBonitaVersion();
    
    IProject getParentProject();

    IProject getBdmParentProject();

    IProject getBdmModelProject();

    IProject getBdmDaoClientProject();

    IProject getAppProject();

    List<IProject> getRelatedProjects();

    IScopeContext getScopeContext();

    void removeModule(String module, IProgressMonitor monitor) throws CoreException;

    void refresh(IProgressMonitor monitor) throws CoreException;
    
    void refresh(boolean updateConfiguration, IProgressMonitor monitor) throws CoreException;

    boolean exists();
    
    static List<IProject> getRelatedProjects(String id) {
        var relatedProjects = new ArrayList<IProject>();
        var bdmParentProject = getBdmParentProject(id);
        if (bdmParentProject.exists()) {
            relatedProjects.add(bdmParentProject);
        }
        var bdmModelProject = getBdmModelProject(id);
        if (bdmModelProject.exists()) {
            relatedProjects.add(bdmModelProject);
        }
        var bdmDaoClientProject = getBdmDaoClientProject(id);
        if (bdmDaoClientProject.exists()) {
            relatedProjects.add(bdmDaoClientProject);
        }
        var appProject = getAppProject(id);
        if (appProject.exists()) {
            relatedProjects.add(appProject);
        }
        relatedProjects.addAll(getRestApiExtensionProjects(id));
        relatedProjects.addAll(getThemesProjects(id));
        var parentProject = getParentProject(id);
        if (parentProject.exists()) {
            relatedProjects.add(parentProject);
        }
        return relatedProjects;
    }

    static Collection<? extends IProject> getThemesProjects(String id) {
        return listProjects(id, THEMES_FOLDER);
    }

    static Collection<? extends IProject> getRestApiExtensionProjects(String id) {
        return listProjects(id, REST_API_EXTENSIONS_FOLDER);
    }

    private static Collection<? extends IProject> listProjects(String id, String folderName) {
        var project = getProject(id);
        if (project.exists()) {
            var extensionsFolder = project.getFolder(APP_MODULE).getFolder(folderName);
            if (extensionsFolder.exists()) {
                try {
                    var resources = extensionsFolder.members();
                    return Stream.of(resources)
                            .filter(IFolder.class::isInstance)
                            .map(IFolder.class::cast)
                            .filter(IFolder::exists)
                            .map(folder -> getProject(folder.getName()))
                            .filter(IProject::exists)
                            .collect(Collectors.toList());
                } catch (CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
        return List.of();
    }

    static IProject getBdmParentProject(String id) {
        return getProject(id + "-bdm-parent");
    }

    static IProject getBdmModelProject(String id) {
        return getProject(id + "-bdm-model");
    }

    static IProject getBdmDaoClientProject(String id) {
        return getProject(id + "-bdm-dao-client");
    }

    static IProject getParentProject(String id) {
        return getProject(id);
    }

    static IProject getAppProject(String id) {
        return getProject(id + "-app");
    }

    static IProject getProject(String name) {
        return ResourcesPlugin.getWorkspace().getRoot().getProject(name);
    }



    static BonitaProject create(String projectId) {
        return new BonitaProjectImpl(projectId);
    }

}
