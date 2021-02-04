/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.maven;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public abstract class MavenModelOperation implements IWorkspaceRunnable {

    private MavenProjectHelper helper = new MavenProjectHelper();

    protected Model readModel(IProject project) throws CoreException {
        return helper.getMavenModel(project);
    }

    protected void saveModel(IProject project, Model model, IProgressMonitor monitor) throws CoreException {
        helper.saveModel(project, model, monitor);
    }

    protected IProject getCurrentProject() {
        return RepositoryManager.getInstance().getCurrentRepository().getProject();
    }

}
