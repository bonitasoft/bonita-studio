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
package org.bonitasoft.studio.businessobject.core.operation;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.engine.api.TenantAdministrationAPI;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.eclipse.core.runtime.IProgressMonitor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class DeployBDMOperationTest {

    private DeployBDMOperation operationUnderTest;

    @Mock
    private BOSEngineManager manager;

    @Mock
    private TenantAdministrationAPI tenantAdminAPI;

    @Mock
    private BusinessObjectModelFileStore bdmFileStore;

    private File parentFolder;

    private BusinessObjectModel bom;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        bom = new BusinessObjectModel();
        final BusinessObject bo = new BusinessObject();
        bo.setQualifiedName("org.bonita.test.Employee");
        final SimpleField firstName = new SimpleField();
        firstName.setName("firstName");
        firstName.setType(FieldType.STRING);
        bo.getFields().add(firstName);
        bom.getBusinessObjects().add(bo);
        operationUnderTest = spy(new DeployBDMOperation(bdmFileStore));
        doReturn(bom).when(bdmFileStore).getContent();
        final Map<String, byte[]> result = new HashMap<>();
        result.put("bdm-client", new byte[512]);
        doReturn(false).when(operationUnderTest).dropDBOnInstall();
        when(manager.getTenantAdministrationAPI((APISession) anyObject())).thenReturn(tenantAdminAPI);
        doReturn(manager).when(operationUnderTest).getEngineManager();
        doNothing().when(operationUnderTest).uninstallBDMAccessControl(any(IProgressMonitor.class));
        doNothing().when(operationUnderTest).forceH2Drop();
        doNothing().when(operationUnderTest).updateDeployRequiredProperty();
        parentFolder = new File("test");
        parentFolder.mkdirs();
        doReturn(parentFolder).when(operationUnderTest).getTargetFolder();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        final File[] listFiles = parentFolder.listFiles();
        if (listFiles != null) {
            for (final File f : listFiles) {
                f.delete();
            }
        }
        parentFolder.delete();
    }

    @Test
    public void shouldRun_DeployBDRInEngine_CallDeployBusinessDataRepository() throws Exception {
        doReturn(parentFolder).when(operationUnderTest).getTargetFolder();
        operationUnderTest.run(AbstractRepository.NULL_PROGRESS_MONITOR);
        verify(manager).loginDefaultTenant(AbstractRepository.NULL_PROGRESS_MONITOR);
        verify(bdmFileStore).getContent();
        final InOrder inOrder = inOrder(tenantAdminAPI);
        inOrder.verify(tenantAdminAPI).pause();
        inOrder.verify(tenantAdminAPI).uninstallBusinessDataModel();
        inOrder.verify(tenantAdminAPI).installBusinessDataModel(any(byte[].class));
        inOrder.verify(tenantAdminAPI).resume();
    }

    @Test
    public void shouldRun_DeployBDRInEngine_CallCleanAndUndeployBusinessDataRepository() throws Exception {
        doReturn(true).when(operationUnderTest).dropDBOnInstall();
        doReturn(parentFolder).when(operationUnderTest).getTargetFolder();
        operationUnderTest.run(AbstractRepository.NULL_PROGRESS_MONITOR);
        verify(manager).loginDefaultTenant(AbstractRepository.NULL_PROGRESS_MONITOR);
        verify(bdmFileStore).getContent();
        final InOrder inOrder = inOrder(tenantAdminAPI);
        inOrder.verify(tenantAdminAPI).pause();
        verify(operationUnderTest).forceH2Drop();
        inOrder.verify(tenantAdminAPI).cleanAndUninstallBusinessDataModel();
        inOrder.verify(tenantAdminAPI).installBusinessDataModel(any(byte[].class));
        inOrder.verify(tenantAdminAPI).resume();
    }

    @Test
    public void should_uninstall_bdm_from_tenant_when_bdm_is_empty() throws Exception {
        final BusinessObjectModel emptyBom = new BusinessObjectModel();
        doReturn(emptyBom).when(bdmFileStore).getContent();
        doReturn(parentFolder).when(operationUnderTest).getTargetFolder();
        operationUnderTest.run(AbstractRepository.NULL_PROGRESS_MONITOR);
        verify(manager).loginDefaultTenant(AbstractRepository.NULL_PROGRESS_MONITOR);
        verify(bdmFileStore).getContent();
        final InOrder inOrder = inOrder(tenantAdminAPI);
        inOrder.verify(tenantAdminAPI).pause();
        inOrder.verify(tenantAdminAPI).uninstallBusinessDataModel();
        inOrder.verify(tenantAdminAPI).resume();
        verify(tenantAdminAPI, never()).installBusinessDataModel(any(byte[].class));
    }

}
