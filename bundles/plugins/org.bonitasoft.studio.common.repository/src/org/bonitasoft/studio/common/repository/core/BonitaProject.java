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

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.BonitaProjectNature;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.core.internal.BonitaProjectImpl;
import org.bonitasoft.studio.common.repository.core.maven.BonitaProjectBuilder;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.team.GitProject;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.eclipse.m2e.core.internal.jobs.MavenWorkspaceJob;
import org.eclipse.m2e.core.internal.project.ProjectConfigurationManager;
import org.eclipse.m2e.core.project.MavenUpdateRequest;

public interface BonitaProject extends GitProject, IAdaptable {

    Collection<String> NATRUES = List.of(IMavenConstants.NATURE_ID, BonitaProjectNature.NATURE_ID, JavaCore.NATURE_ID,
            "org.eclipse.jdt.groovy.core.groovyNature");
    Collection<String> BUILDERS = List.of(IMavenConstants.BUILDER_ID, BonitaProjectBuilder.ID, JavaCore.BUILDER_ID);
    String APP_MODULE = "app";
    String BDM_MODULE = "bdm";
    String EXTENSIONS_MODULE = "extensions";

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

    IProject getExtensionsParentProject();

    List<IProject> getExtensionsProjects();

    List<IProject> getRelatedProjects();

    IScopeContext getScopeContext();

    void removeModule(IProject parentProject, String module, IProgressMonitor monitor) throws CoreException;

    void addModule(IProject parentProject, String module, IProgressMonitor monitor) throws CoreException;

    void refresh(IProgressMonitor monitor) throws CoreException;

    void refresh(boolean updateConfiguration, IProgressMonitor monitor) throws CoreException;

    boolean exists();

    static List<IProject> getRelatedProjects(String id) {
        var projectPaths = getMavenProjects();
        var parentProject = getParentProject(id);

        List<IProject> relatedProjects = new ArrayList<>();
        if (parentProject.exists()) {
            collectRelatedProjects(parentProject, projectPaths, relatedProjects);
            relatedProjects.add(parentProject);
        }
        return relatedProjects;
    }

    private static void collectRelatedProjects(IProject parentProject, TreeMap<String, IProject> projectPaths,
            List<IProject> relatedProjects) {
        String elePath = getElePath(parentProject);
        String prevPath = null;
        for (String path : projectPaths.keySet()) {
            if (elePath != null && path.length() != elePath.length() && path.startsWith(elePath)) {
                if (prevPath == null || !path.startsWith(prevPath)) {
                    prevPath = path;
                    IProject project = projectPaths.get(path);
                    relatedProjects.add(project);
                    collectRelatedProjects(project, projectPaths, relatedProjects);
                }
            }
        }
    }

    private static TreeMap<String, IProject> getMavenProjects() {
        var projectPaths = new TreeMap<String, IProject>();
        for (IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
            if (project.getLocationURI() != null) {
                String path = getElePath(project);
                if (path != null) {
                    projectPaths.put(path, project);
                }
            }
        }
        return projectPaths;
    }

    private static String getElePath(Object element) {
        if (element instanceof IProject project) {
            URI locationURI = project.getLocationURI();

            try {
                IFileStore store = EFS.getStore(locationURI);
                File file = store.toLocalFile(0, null);
                if (file == null) {
                    file = store.toLocalFile(EFS.CACHE, null);
                }
                return file.toString() + System.getProperty("file.separator");
            } catch (CoreException ex) {
                BonitaStudioLog.error(ex);
            }
        }
        return null;
    }

    static List<IProject> getExtensionsProjects(String id) {
        var parent = getExtensionsParentProject(id);
        if (parent.exists() && parent.getFile("pom.xml").exists()) {
            try (var is = parent.getFile("pom.xml").getContents()) {
                var model = MavenPlugin.getMaven().readModel(is);
                return model.getModules().stream()
                        .map(BonitaProject::getProject)
                        .filter(IProject::exists)
                        .collect(Collectors.toList());
            } catch (IOException | CoreException e) {
                BonitaStudioLog.error(e);
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

    static IProject getExtensionsParentProject(String id) {
        return getProject(id + "-extensions");
    }

    static IProject getProject(String name) {
        return ResourcesPlugin.getWorkspace().getRoot().getProject(name);
    }

    static BonitaProject create(String projectId) {
        return new BonitaProjectImpl(projectId);
    }

    static WorkspaceJob updateMavenProjectsJob(Collection<IProject> projects, boolean updateConfiguration) {
        return new MavenWorkspaceJob("Update maven projects") {

            @Override
            public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
                ProjectConfigurationManager configurationManager = (ProjectConfigurationManager) MavenPlugin
                        .getProjectConfigurationManager();

                MavenUpdateRequest request = new MavenUpdateRequest(projects, false, false);
                Map<String, IStatus> updateStatus = configurationManager.updateProjectConfiguration(request,
                        updateConfiguration,
                        true, true, monitor);
                Map<String, Throwable> errorMap = new LinkedHashMap<>();
                ArrayList<IStatus> errors = new ArrayList<>();

                for (Map.Entry<String, IStatus> entry : updateStatus.entrySet()) {
                    if (!entry.getValue().isOK()) {
                        errors.add(entry.getValue());
                        errorMap.put(entry.getKey(), new CoreException(entry.getValue()));
                    }
                }
                if (errors.size() == 1) {
                    return errors.get(0);
                } else {
                    return new MultiStatus(CommonRepositoryPlugin.PLUGIN_ID, -1,
                            errors.toArray(new IStatus[errors.size()]),
                            "Error occured when updating maven projects", null);
                }
            }
        };
    }

}
