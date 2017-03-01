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
import org.bonitasoft.studio.businessobject.core.operation.DeployBDMOperation;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class DeployBDROperationIT {

    private BusinessObjectModelRepositoryStore defStore;
    private DependencyRepositoryStore depStore;
    private BusinessObjectModelFileStore businessObjectDefinitionFileStore;
    private APISession apiSession;
    private BOSEngineManager managerEx;

    @After
    public void deleteFileStore() throws Exception {
        if (apiSession != null) {
            managerEx.logoutDefaultTenant(apiSession);
        }
        final BusinessObjectModelFileStore fileStore = defStore.getChild("bdm.zip");
        if (fileStore != null) {
            fileStore.delete();
        }
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        PlatformUtil.delete(ProjectUtil.getBonitaStudioWorkFolder(), Repository.NULL_PROGRESS_MONITOR);
        depStore = RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class);
        defStore = RepositoryManager.getInstance().getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        final BusinessObjectModelFileStore fileStore = defStore.getChild("bdm.zip");
        if (fileStore != null) {
            fileStore.delete();
        }
        businessObjectDefinitionFileStore = defStore.importInputStream("bdm.zip",
                DeployBDROperationIT.class.getResourceAsStream("/bdm.zip"));
        managerEx = BOSEngineManager.getInstance();
    }

    @Test
    public void shouldRun_DeployBDRInEngine() throws Exception {
        final DeployBDMOperation operation = new DeployBDMOperation(businessObjectDefinitionFileStore);
        operation.run(null);

        apiSession = managerEx.loginDefaultTenant(null);
        final TenantAdministrationAPI tenantManagementAPI = managerEx.getTenantAdministrationAPI(apiSession);
        assertThat(tenantManagementAPI.isPaused()).isFalse();

        final String dependencyName = businessObjectDefinitionFileStore.getDependencyName();
        final DependencyFileStore fileStore = depStore.getChild(dependencyName);
        assertThat(fileStore).isNotNull();
        assertThat(fileStore.getResource().exists()).isTrue();

        final IJavaProject javaProject = RepositoryManager.getInstance().getCurrentRepository().getJavaProject();
        final IType iType = javaProject.findType("org.bonita.CompanyUser");
        assertThat(iType).isNotNull();
    }

}
