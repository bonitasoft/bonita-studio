/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.engine.test;

import junit.framework.TestCase;

import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.ProcessManagementAPI;
import org.bonitasoft.engine.api.TenantAPIAccessor;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.actors.model.organization.Group;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.Role;
import org.bonitasoft.studio.actors.model.organization.User;
import org.bonitasoft.studio.actors.preference.ActorsPreferenceConstants;
import org.bonitasoft.studio.actors.repository.OrganizationFileStore;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.actors.ui.wizard.page.GroupContentProvider;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;

/**
 * @author Baptiste Mesta
 * 
 */
public class TestCheckTenantAfterStartup extends TestCase {

    private APISession session;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        session = BOSEngineManager.getInstance().loginDefaultTenant(Repository.NULL_PROGRESS_MONITOR);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        if(session != null){
            BOSEngineManager.getInstance().logoutDefaultTenant(session);
        }
    }

    /**
     * Check if the data base was droped and elements are retrieved
     */
    public void testCheckTenantIsEmptyAfterStartup() throws Exception {
        final ProcessManagementAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);

        assertEquals("No process definition should exists",0, processApi.getNumberOfProcesses());
        assertEquals("No process instance should exists",0, processApi.getNumberOfProcessInstances());
    }


    public void testOrganizationSynchronization() throws Exception {
        final IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);

        final String activeOrganization = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getString(ActorsPreferenceConstants.DEFAULT_ORGANIZATION) ;
        final OrganizationRepositoryStore store = (OrganizationRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(OrganizationRepositoryStore.class);
        final OrganizationFileStore fileStore = store.getChild(activeOrganization+"."+OrganizationRepositoryStore.ORGANIZATION_EXT);

        final Organization organization = fileStore.getContent();
        for(Group group : organization.getGroups().getGroup()){
            assertNotNull("Group "+group.getName()+" is missing after restart",identityAPI.getGroupByPath(GroupContentProvider.getGroupPath(group)));
        }

        for(Role role : organization.getRoles().getRole()){
            assertNotNull("Role "+role.getName()+" is missing after restart",identityAPI.getRoleByName(role.getName()));
        }

        for(User user : organization.getUsers().getUser()){
            assertNotNull("User "+user.getUserName()+" is missing after restart",identityAPI.getUserByUserName(user.getUserName()));
        }
    }





}
