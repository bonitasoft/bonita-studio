/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.engine;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.actors.model.organization.Group;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.Role;
import org.bonitasoft.studio.actors.model.organization.User;
import org.bonitasoft.studio.actors.repository.OrganizationFileStore;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.actors.ui.wizard.page.GroupContentProvider;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.server.StartEngineJob;
import org.eclipse.core.runtime.jobs.Job;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class TestCheckTenantAfterStartup {

    private APISession session;

    @Before
    public void setUp() throws Exception {
        Job.getJobManager().join(StartEngineJob.FAMILY, Repository.NULL_PROGRESS_MONITOR);
        session = BOSEngineManager.getInstance().loginDefaultTenant(Repository.NULL_PROGRESS_MONITOR);
        assertThat(session).isNotNull();
    }

    @After
    public void tearDown() throws Exception {
        if (session != null) {
            BOSEngineManager.getInstance().logoutDefaultTenant(session);
        }
    }

    @Test
    public void testCheckTenantIsEmptyAfterStartup() throws Exception {
        final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);

        assertThat(processApi.getNumberOfProcessDeploymentInfos()).isZero().as("No process definition should exists");
        assertThat(processApi.getNumberOfProcessInstances()).isZero().as("No process instance should exists");
    }

    @Test
    public void testOrganizationSynchronization() throws Exception {
        final IdentityAPI identityAPI = BOSEngineManager.getInstance().getIdentityAPI(session);

        final String activeOrganization = new ActiveOrganizationProvider().getActiveOrganization();
        final OrganizationRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(OrganizationRepositoryStore.class);
        final OrganizationFileStore fileStore = store.getChild(activeOrganization + "." + OrganizationRepositoryStore.ORGANIZATION_EXT, true);

        final Organization organization = fileStore.getContent();
        for (final Group group : organization.getGroups().getGroup()) {
            assertThat(identityAPI.getGroupByPath(GroupContentProvider.getGroupPath(group))).isNotNull()
                    .as("Group " + group.getName() + " is missing after restart");
        }

        for (final Role role : organization.getRoles().getRole()) {
            assertThat(identityAPI.getRoleByName(role.getName())).isNotNull().as("Role " + role.getName() + " is missing after restart");
        }

        for (final User user : organization.getUsers().getUser()) {
            assertThat(identityAPI.getUserByUserName(user.getUserName())).isNotNull().as("User " + user.getUserName() + " is missing after restart");
        }
    }

}
