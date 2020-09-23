/**
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.handler;

import java.util.Optional;

import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.bonitasoft.studio.ui.dialog.SaveBeforeDeployDialog;
import org.bonitasoft.studio.ui.dialog.SaveBeforeDeployDialog.DeployStrategy;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;

public class DeployHandler extends AbstractHandler {

    private FileStoreFinder selectionFinder = new FileStoreFinder();

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        selectionFinder
                .findElementToDeploy(RepositoryManager.getInstance().getCurrentRepository())
                .ifPresent(deployable -> {
                    if (deployable instanceof AbstractFileStore && ((AbstractFileStore) deployable).isDirty()) {
                        DeployStrategy choice = SaveBeforeDeployDialog.open(((AbstractFileStore) deployable).getName());
                        if (choice == DeployStrategy.SAVE_AND_DEPLOY) {
                            ((AbstractFileStore) deployable).saveOpenedEditor();
                            if (((AbstractFileStore) deployable).isDirty()) {
                                MessageDialog.openInformation(Display.getDefault().getActiveShell(),
                                        Messages.deployCancelTitle, Messages.deployCancel);
                                return;
                            }
                        } else if (choice == DeployStrategy.CANCEL) {
                            return; // cancel
                        }
                    }
                    deployable.deployInUI();
                });
        return null;
    }

    @Override
    public boolean isEnabled() {
        Optional<IStructuredSelection> selection = selectionFinder.getCurrentStructuredSelection();
        if (selection.isPresent()
                && selection.get().toList().size() == 1
                && selection.get().getFirstElement() instanceof IAdaptable) {
            IResource resource = ((IAdaptable) selection.get().getFirstElement()).getAdapter(IResource.class);
            if (resource != null) {
                AbstractRepository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
                boolean resourceMatch = selectionFinder.findElementToDeploy(resource, currentRepository)
                        .isPresent();
                if (!resourceMatch) {
                    IProject project = resource.getProject();
                    if (project != null) {
                        resourceMatch = selectionFinder.findElementToDeploy(project, currentRepository)
                                .isPresent();
                    }
                }
                return resourceMatch;
            }
        }
        return false;
    }

}
