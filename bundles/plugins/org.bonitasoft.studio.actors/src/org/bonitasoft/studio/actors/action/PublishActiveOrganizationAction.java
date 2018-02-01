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
package org.bonitasoft.studio.actors.action;

import java.io.FileNotFoundException;

import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.SearchException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.session.InvalidSessionException;
import org.bonitasoft.studio.actors.operation.PublishOrganizationOperation;
import org.bonitasoft.studio.actors.operation.UpdateOrganizationOperation;
import org.bonitasoft.studio.actors.repository.OrganizationFileStore;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.common.extension.IEngineAction;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;

/**
 * @author Romain Bioteau
 */
public class PublishActiveOrganizationAction implements IEngineAction {

    private final ActiveOrganizationProvider activeOrganizationProvider;

    public PublishActiveOrganizationAction() {
        activeOrganizationProvider = new ActiveOrganizationProvider();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.extension.IEngineAction#run(org.bonitasoft.engine.session.APISession)
     */
    @Override
    public void run(final APISession session) throws Exception {
        if (noOrganizationDeployed(session)
                || BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                        .getBoolean(BonitaPreferenceConstants.LOAD_ORGANIZATION)) {
            final String artifactId = activeOrganizationProvider.getActiveOrganization();
            final OrganizationRepositoryStore store = RepositoryManager.getInstance()
                    .getRepositoryStore(OrganizationRepositoryStore.class);
            final OrganizationFileStore organizationFileStore = store
                    .getChild(artifactId + "." + OrganizationRepositoryStore.ORGANIZATION_EXT);
            if (organizationFileStore == null) {
                throw new FileNotFoundException(artifactId + "." + OrganizationRepositoryStore.ORGANIZATION_EXT);
            }
            final PublishOrganizationOperation op = new UpdateOrganizationOperation(organizationFileStore.getContent());
            op.setSession(session);
            op.run(Repository.NULL_PROGRESS_MONITOR);
        }
    }

    private boolean noOrganizationDeployed(final APISession session)
            throws InvalidSessionException, BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException,
            SearchException {
        final IdentityAPI identityAPI = BOSEngineManager.getInstance().getIdentityAPI(session);
        return identityAPI.searchUsers(new SearchOptionsBuilder(0, 1).done()).getCount() == 0
                && identityAPI.searchGroups(new SearchOptionsBuilder(0, 1).done()).getCount() == 0
                && identityAPI.searchRoles(new SearchOptionsBuilder(0, 1).done()).getCount() == 0;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.extension.IEngineAction#shouldRun()
     */
    @Override
    public boolean shouldRun() {
        return true;
    }
}
