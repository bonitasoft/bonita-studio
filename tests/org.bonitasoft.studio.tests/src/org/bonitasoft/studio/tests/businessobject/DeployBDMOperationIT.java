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

import java.util.Properties;

import org.apache.maven.Maven;
import org.apache.maven.execution.MavenExecutionRequest;
import org.apache.maven.execution.MavenExecutionResult;
import org.bonitasoft.engine.api.TenantAdministrationAPI;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.tenant.TenantResourceState;
import org.bonitasoft.studio.businessobject.core.operation.DeployBDMOperation;
import org.bonitasoft.studio.businessobject.core.operation.GenerateBDMOperation;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.tests.util.InitialProjectRule;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.ICallable;
import org.eclipse.m2e.core.embedder.IMaven;
import org.eclipse.m2e.core.embedder.IMavenExecutionContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.common.collect.Lists;

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
        bomRepositoryStore = RepositoryManager.getInstance().getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        final BusinessObjectModelFileStore fileStore = bomRepositoryStore.getChild("bdm.zip", true);
        if (fileStore != null) {
            fileStore.delete();
        }
        try (var is = DeployBDMOperationIT.class.getResourceAsStream("/bdm.zip")){
            businessObjectDefinitionFileStore = (BusinessObjectModelFileStore) bomRepositoryStore.importInputStream("bdm.zip",
                    is);
        }
        managerEx = BOSEngineManager.getInstance();
    }

    @Test
    public void should_generate_and_deploy_bdm() throws Exception {
        new GenerateBDMOperation(businessObjectDefinitionFileStore).run(null);

        var currentRepository = RepositoryManager.getInstance().getCurrentRepository().orElseThrow();
        final IJavaProject javaProject = currentRepository.getJavaProject();
        final IType iType = javaProject.findType("org.bonita.CompanyUser");
        assertThat(iType).isNotNull();

        final DeployBDMOperation operation = new DeployBDMOperation(businessObjectDefinitionFileStore);
        operation.run(null);

        apiSession = managerEx.loginDefaultTenant(null);
        final TenantAdministrationAPI tenantManagementAPI = managerEx.getTenantAdministrationAPI(apiSession);
        assertThat(tenantManagementAPI.getBusinessDataModelResource().getState()).isEqualTo(TenantResourceState.INSTALLED);
        assertThat(tenantManagementAPI.isPaused()).isFalse();

        var metadata = ProjectMetadata.defaultMetadata();
        MavenExecutionResult executionResult = resolveMavenDependency(metadata.getGroupId(),metadata.getArtifactId() + "-bdm-model", metadata.getVersion());
        assertThat(executionResult.hasExceptions())
            .overridingErrorMessage("BDM model maven dependency not installed in local repository:\n%s", getException(executionResult))
            .isFalse();
    }


    // Update assertj to use lazy message initialization and avoid this
    private Throwable getException(MavenExecutionResult executionResult) {
        return  !executionResult.getExceptions().isEmpty() ? executionResult.getExceptions().get(0)  : null;
    }


    private MavenExecutionResult resolveMavenDependency( String groupId, String artifactId, String version) throws CoreException {
        IMaven maven = MavenPlugin.getMaven();
        final IMavenExecutionContext context = maven.createExecutionContext();
        final MavenExecutionRequest request = context.getExecutionRequest();
        request.setGoals(Lists.newArrayList("dependency:get"));
        request.setUpdateSnapshots(true);
        request.setInteractiveMode(false);
        request.setCacheNotFound(true);
        return context.execute(new ICallable<MavenExecutionResult>() {

            @Override
            public MavenExecutionResult call(final IMavenExecutionContext context, final IProgressMonitor innerMonitor)
                    throws CoreException {
                final Properties systemProperties = request.getSystemProperties();
                systemProperties.setProperty("groupId", groupId);
                systemProperties.setProperty("artifactId", artifactId);
                systemProperties.setProperty("version", version);
                systemProperties.setProperty("packaging", "jar");
                request.setSystemProperties(systemProperties);
                return maven.lookup(Maven.class).execute(request);
            }
        }, new NullProgressMonitor());
    }

}
