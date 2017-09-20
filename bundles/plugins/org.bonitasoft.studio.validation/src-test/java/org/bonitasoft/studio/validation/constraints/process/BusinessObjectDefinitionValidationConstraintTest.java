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
package org.bonitasoft.studio.validation.constraints.process;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.DataTypeLabels;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.BusinessObjectType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.validation.IValidationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class BusinessObjectDefinitionValidationConstraintTest {

    @Spy
    private BusinessObjectDefinitionValidationConstraint constraintUnderTest;

    @Mock
    private BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store;

    @Mock
    private IValidationContext context;

    private BusinessObjectData data;

    @Mock
    private BusinessObjectModelFileStore fStore;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        doReturn(store).when(constraintUnderTest).getBusinessObjectDefinitionStore();
        final BusinessObjectType businessObjectType = ProcessFactory.eINSTANCE.createBusinessObjectType();
        businessObjectType.setName(DataTypeLabels.businessObjectType);
        data = ProcessFactory.eINSTANCE.createBusinessObjectData();
        data.setName("currentLeaveRequest");
        data.setDataType(businessObjectType);
        data.setClassName("org.bonitasoft.hr.LeaveRequest");
        when(context.getTarget()).thenReturn(data);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldBusinessObjectDefinitionExists_ReturnTrue() throws Exception {
        doReturn(Optional.of(fStore)).when(store).getChildByQualifiedName("org.bonitasoft.hr.LeaveRequest");
        doReturn(Optional.of(fStore)).when(store).getChildByQualifiedName("org.bonitasoft.hr.Employee");
        assertThat(constraintUnderTest.businessObjectDefinitionExists(data)).isTrue();
    }

    @Test
    public void shouldBusinessObjectDefinitionExists_ReturnFalse() throws Exception {
        doReturn(Optional.empty()).when(store).getChildByQualifiedName("org.bonitasoft.hr.LeaveRequest");
        doReturn(Optional.of(fStore)).when(store).getChildByQualifiedName("org.bonitasoft.hr.Employee");
        assertThat(constraintUnderTest.businessObjectDefinitionExists(data)).isFalse();
    }

    @Test
    public void shouldPerformBatchValidation_ReturnFailureStatus() throws Exception {
        doReturn(Optional.empty()).when(store).getChildByQualifiedName("org.bonitasoft.hr.LeaveRequest");
        doReturn(Optional.of(fStore)).when(store).getChildByQualifiedName("org.bonitasoft.hr.Employee");

        final IStatus failure = new Status(IStatus.ERROR, "org.bonitasoft.studio.validation.ex", "");
        when(context.createFailureStatus(anyObject())).thenReturn(failure);
        assertThat(constraintUnderTest.performBatchValidation(context)).isEqualTo(failure);
    }

    @Test
    public void shouldPerformBatchValidation_ReturnOkStatus() throws Exception {
        doReturn(Optional.of(fStore)).when(store).getChildByQualifiedName("org.bonitasoft.hr.LeaveRequest");
        doReturn(Optional.of(fStore)).when(store).getChildByQualifiedName("org.bonitasoft.hr.Employee");

        final IStatus success = Status.OK_STATUS;
        when(context.createSuccessStatus()).thenReturn(success);
        assertThat(constraintUnderTest.performBatchValidation(context)).isEqualTo(success);
    }

}
