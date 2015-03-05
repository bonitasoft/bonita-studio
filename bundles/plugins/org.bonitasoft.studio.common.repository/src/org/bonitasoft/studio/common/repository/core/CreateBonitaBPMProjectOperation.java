/**
 * Copyright (C) 2014 BonitaSoft S.A.
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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Romain Bioteau
 */
public class CreateBonitaBPMProjectOperation implements IWorkspaceRunnable {

    private IProject project;
    private final IWorkspace workspace;
    private final String projectName;

    public CreateBonitaBPMProjectOperation(final IWorkspace workspace, final String projectName) {
        this.workspace = workspace;
        this.projectName = projectName;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws CoreException {
        //        final long init = System.currentTimeMillis();
        //        final boolean projectExists = project.exists();
        //        if (!projectExists) {
        //            project.create(monitor);
        //        }
        //        disableBuild();
        //        open();
        //        if (!projectExists) {
        //            initializeProject(project);
        //        }
        //
        //        initRepositoryStores(migrateStoreIfNeeded);
        //        refreshClasspath(project);
        //
        //        enableBuild();
        //        try {
        //            project.build(IncrementalProjectBuilder.FULL_BUILD, monitor);
        //        } catch (final CoreException e) {
        //            BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
        //        }
        //        if (BonitaStudioLog.isLoggable(IStatus.OK)) {
        //            final long duration = System.currentTimeMillis() - init;
        //            BonitaStudioLog.debug("Repository " + project.getName() + " created in " + DateUtil.getDisplayDuration(duration),
        //                    CommonRepositoryPlugin.PLUGIN_ID);
        //        }

    }

    public IProject getProject() {
        return project;
    }

}
