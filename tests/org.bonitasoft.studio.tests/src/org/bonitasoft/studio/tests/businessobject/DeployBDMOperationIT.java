/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.businessobject;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.engine.api.TenantAdministrationAPI;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.tenant.TenantResourceState;
import org.bonitasoft.studio.businessobject.core.operation.DeployBDMOperation;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.tests.util.Await;
import org.bonitasoft.studio.tests.util.InitialProjectRule;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class DeployBDMOperationIT {

    @Rule
    public InitialProjectRule projectRule = InitialProjectRule.INSTANCE;

    private BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> bomRepositoryStore;
    private BusinessObjectModelFileStore businessObjectDefinitionFileStore;
    private APISession apiSession;
    private BOSEngineManager managerEx;

    @After
    public void deleteFileStore() throws Exception {
        if (apiSession != null) {
            managerEx.logoutDefaultTenant(apiSession);
        }
        final BusinessObjectModelFileStore fileStore = bomRepositoryStore.getChild("bdm.zip", true);
        if (fileStore != null) {
            fileStore.delete();
        }
    }

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
        bomRepositoryStore = RepositoryManager.getInstance()
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        final BusinessObjectModelFileStore fileStore = bomRepositoryStore.getChild("bdm.zip", true);
        if (fileStore != null) {
            fileStore.delete();
        }
        managerEx = BOSEngineManager.getInstance();
    }

    @Test
    public void should_generate_and_deploy_bdm() throws Exception {
        try (var is = DeployBDMOperationIT.class.getResourceAsStream("/bdm.zip")) {
            businessObjectDefinitionFileStore = (BusinessObjectModelFileStore) bomRepositoryStore.importInputStream(
                    "bdm.zip",
                    is);
        }
        var currentRepository = RepositoryManager.getInstance().getCurrentRepository().orElseThrow();
        Await.waitUntil(() -> {
        	final IJavaProject javaProject = currentRepository.getJavaProject();
            try {
				return javaProject.findType("org.bonita.CompanyUser") != null;
			} catch (JavaModelException e) {
				BonitaStudioLog.error(e);
				return false;
			}
        }, 5000, 100);

        final DeployBDMOperation operation = new DeployBDMOperation(businessObjectDefinitionFileStore);
        operation.run(null);

        apiSession = managerEx.loginDefaultTenant(null);
        final TenantAdministrationAPI tenantManagementAPI = managerEx.getTenantAdministrationAPI(apiSession);
        assertThat(tenantManagementAPI.getBusinessDataModelResource().getState())
                .isEqualTo(TenantResourceState.INSTALLED);
        assertThat(tenantManagementAPI.isPaused()).isFalse();
    }


}
