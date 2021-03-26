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
package org.bonitasoft.studio.identity.organization.action;

import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.SearchException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.common.repository.extension.IEngineAction;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.operation.PublishOrganizationOperation;
import org.bonitasoft.studio.identity.organization.operation.UpdateOrganizationOperation;
import org.bonitasoft.studio.identity.organization.repository.OrganizationFileStore;
import org.bonitasoft.studio.identity.organization.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.ui.notification.BonitaNotificator;

/**
 * @author Romain Bioteau
 */
public class PublishActiveOrganizationAction implements IEngineAction {

    private final ActiveOrganizationProvider activeOrganizationProvider;

    public PublishActiveOrganizationAction() {
        activeOrganizationProvider = new ActiveOrganizationProvider();
    }

    @Override
    public void run(final APISession session, IRepository repository) throws Exception {
        if (noOrganizationDeployed(session)
                || BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                        .getBoolean(BonitaPreferenceConstants.LOAD_ORGANIZATION)) {
            final String artifactId = activeOrganizationProvider.getActiveOrganization();
            final OrganizationRepositoryStore store = repository
                    .getRepositoryStore(OrganizationRepositoryStore.class);
            OrganizationFileStore organizationFileStore = store
                    .getChild(artifactId + "." + OrganizationRepositoryStore.ORGANIZATION_EXT, true);
            if (organizationFileStore == null && !store.getChildren().isEmpty()) {
                organizationFileStore = store.getChildren().get(0);
            }
            if (organizationFileStore == null) {
                // No organization to deploy
                BonitaNotificator.openNotification(Messages.noOrganizationFoundTitle,
                        Messages.noOrganizationFoundMsg);
                return;
            }
            final PublishOrganizationOperation op = new UpdateOrganizationOperation(
                    organizationFileStore.getContent());
            op.setSession(session);
            op.run(AbstractRepository.NULL_PROGRESS_MONITOR);
        }
    }

    private boolean noOrganizationDeployed(final APISession session)
            throws BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException, SearchException {
        final IdentityAPI identityAPI = BOSEngineManager.getInstance().getIdentityAPI(session);
        return identityAPI.searchUsers(new SearchOptionsBuilder(0, 1).done()).getCount() == 0
                && identityAPI.searchGroups(new SearchOptionsBuilder(0, 1).done()).getCount() == 0
                && identityAPI.searchRoles(new SearchOptionsBuilder(0, 1).done()).getCount() == 0;
    }

    @Override
    public boolean shouldRun(IRepository repository) {
        return repository.isLoaded();
    }
}
