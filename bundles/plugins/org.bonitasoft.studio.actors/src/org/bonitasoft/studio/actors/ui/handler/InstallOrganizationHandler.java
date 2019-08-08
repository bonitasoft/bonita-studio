/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.actors.ui.handler;

import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.actors.ActorsPlugin;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.operation.CleanPublishOrganizationOperation;
import org.bonitasoft.studio.actors.operation.PublishOrganizationOperation;
import org.bonitasoft.studio.actors.operation.UpdateOrganizationOperation;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 */
public class InstallOrganizationHandler extends AbstractHandler {

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        if (event != null) {
            final String id = event.getParameter("artifact");
            final IRepositoryStore<?> organizationStore = RepositoryManager.getInstance()
                    .getRepositoryStore(OrganizationRepositoryStore.class);
            IRepositoryFileStore file = organizationStore.getChild(id);
            if (file == null) {
                BonitaStudioLog.warning("Organization : " + id + " not found !", ActorsPlugin.PLUGIN_ID);
                final List<? extends IRepositoryFileStore> organizationFiles = organizationStore.getChildren();
                if (organizationFiles.isEmpty()) {
                    BonitaStudioLog.warning("No organization found in repository", ActorsPlugin.PLUGIN_ID);
                    return null;
                } else {
                    file = organizationFiles.get(0);
                }
            }

            Organization organization;
            try {
                organization = (Organization) file.getContent();
            } catch (final ReadFileStoreException e1) {
                throw new ExecutionException("Failed to read organization content", e1);
            }

            final PublishOrganizationOperation op = publishOperation(organization);
            try {
                op.run(Repository.NULL_PROGRESS_MONITOR);
            } catch (final Exception e) {
                if (PlatformUI.isWorkbenchRunning()) {
                    Display.getDefault().syncExec(new Runnable() {

                        @Override
                        public void run() {
                            new BonitaErrorDialog(Display.getDefault().getActiveShell(), "Error",
                                    "An error occured during synchronization", e).open();
                        }

                    });
                }
                throw new ExecutionException("", e);
            }

        }
        return null;
    }

    protected PublishOrganizationOperation publishOperation(Organization organization) {
        final String activeOrganization = new ActiveOrganizationProvider().getActiveOrganization();
        return Objects.equals(organization.getName(), activeOrganization) ? new UpdateOrganizationOperation(organization)
                : new CleanPublishOrganizationOperation(organization);
    }

}
