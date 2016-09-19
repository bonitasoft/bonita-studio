/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.advisor;

import static org.bonitasoft.studio.common.repository.Repository.NULL_PROGRESS_MONITOR;

import java.io.File;

import org.bonitasoft.studio.application.ApplicationPlugin;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;


public class RecoverWorkspaceAdvisor extends InstallerApplicationWorkbenchAdvisor {

    private String targetPath;

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.application.advisor.InstallerApplicationWorkbenchAdvisor#preStartup()
     */
    @Override
    public void preStartup() {
        try {
            ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
        final IProject defaultDir = ResourcesPlugin.getWorkspace().getRoot().getProject("default");
        if (defaultDir != null && defaultDir.exists()) {
            super.preStartup();
        } else {
            if (defaultDir.getLocation() != null) {
                MessageDialog.openError(Display.getCurrent().getActiveShell(), Messages.invalidWorkspaceTitle,
                        Messages.bind(Messages.invalidWorkspace, defaultDir.getLocation().toFile().getParentFile().getAbsolutePath()));
            } else {
                BonitaStudioLog.error("Invalid input workspace, default project not found.", ApplicationPlugin.PLUGIN_ID);
            }
            PlatformUI.getWorkbench().close();
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.application.advisor.InstallerApplicationWorkbenchAdvisor#executePostStartupHandler()
     */
    @Override
    protected void executePostStartupHandler() {
        for (final IRepository repo : RepositoryManager.getInstance().getAllRepositories()) {
            if (!repo.isShared()) {
                RepositoryManager.getInstance().setRepository(repo.getName(), false, NULL_PROGRESS_MONITOR);
                RepositoryManager.getInstance().getRepository(repo.getName()).open(NULL_PROGRESS_MONITOR);
                repo.exportToArchive(targetPath + File.separatorChar + repo.getName() + ".bos");
                RepositoryManager.getInstance().getCurrentRepository().close();
            }
        }
        RepositoryManager.getInstance().setRepository("default", NULL_PROGRESS_MONITOR);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.application.advisor.InstallerApplicationWorkbenchAdvisor#postStartup()
     */
    @Override
    public void postStartup() {
        final IProject defaultDir = ResourcesPlugin.getWorkspace().getRoot().getProject("default");
        if (defaultDir != null && defaultDir.exists()) {
            super.postStartup();
        }
    }

    public void setNewWorkspaceLocation(String newWorkspaceLocation) {
        this.targetPath = newWorkspaceLocation;
    }

}
