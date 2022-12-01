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
package org.bonitasoft.studio.common.repository.core.internal;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.MultiModuleProject;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.m2e.core.MavenPlugin;

public class BonitaProjectAdapterFactory implements IAdapterFactory {

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
        if (adapterType.equals(MultiModuleProject.class)) {
            if (adaptableObject instanceof IRepository) {
                var repository = (IRepository) adaptableObject;
                IProject project = repository.getProject();
                if (project == null || project.getLocation() == null || !project.getLocation().toFile().exists()) {
                    return null;
                }
                Model model = null;
                var mavenFacade = MavenPlugin.getMavenProjectRegistry().create(project, new NullProgressMonitor());
                if (mavenFacade == null) {
                    var pomFile = project.getLocation().toFile().toPath().resolve("pom.xml");
                    try {
                        model = MavenProjectHelper.readModel(pomFile.toFile());
                    } catch (CoreException e) {
                        BonitaStudioLog.error(e);
                        return null;
                    }
                } else {
                    try {
                        model = mavenFacade.getMavenProject(new NullProgressMonitor()).getModel();
                    } catch (CoreException e) {
                        BonitaStudioLog.error(e);
                        return null;
                    }
                }
                if (model.getParent() != null) {
                    return (T) new MultiModuleBonitaProjectImpl(repository);
                }
                return null;
            }
        } else if (adapterType.equals(BonitaProject.class)) {
            if (adaptableObject instanceof IRepository) {
                var repository = (IRepository) adaptableObject;
                IProject project = repository.getProject();
                if (project == null || project.getLocation() == null || !project.getLocation().toFile().exists()) {
                    return null;
                }
                var pomFile = project.getLocation().toFile().toPath().resolve("pom.xml");
                try {
                    var model = MavenProjectHelper.readModel(pomFile.toFile());
                    if (model.getParent() != null) {
                        return (T) new MultiModuleBonitaProjectImpl(repository);
                    }
                    return (T) new BonitaProjectImpl(repository);
                } catch (CoreException e1) {
                    BonitaStudioLog.error(e1);
                    return null;
                }
            } else if (adaptableObject instanceof IProject) {
                var repository = RepositoryManager.getInstance().getRepository(((IProject) adaptableObject).getName());
                return getAdapter(repository, adapterType);
            }
        }
        return null;
    }

    @Override
    public Class<?>[] getAdapterList() {
        return new Class[] { BonitaProject.class, MultiModuleProject.class };
    }

}
