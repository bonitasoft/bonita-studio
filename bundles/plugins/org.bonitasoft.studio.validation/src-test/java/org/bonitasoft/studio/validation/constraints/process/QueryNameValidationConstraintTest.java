/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

import static com.google.common.collect.Lists.newArrayList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.Query;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.osgi.util.NLS;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class QueryNameValidationConstraintTest {

    @Spy
    private QueryNameValidationConstraint constraint;
    @Mock
    private IValidationContext context;


    @Before
    public void setUp() throws Exception {
        when(context.createSuccessStatus()).thenReturn(ValidationStatus.ok());
        when(context.createFailureStatus(any())).thenReturn(ValidationStatus.error("error"));
    }

    @Test
    public void should_fail_if_query_name_is_conflicting() throws Exception {
        doReturn(aBusinessObjectModelFileStoreWithBo(aBoWithConflictingQueryNames())).when(constraint).getCurrentBDM();

        final IStatus status = constraint.performBatchValidation(context);

        verify(context).createFailureStatus(NLS.bind(Messages.conflictingQueryNamesInBusinessObject, "Employee", "findByName"));
        StatusAssert.assertThat(status).isNotOK();
    }

    @Test
    public void should_not_fail_if_no_query_name_is_conflicting() throws Exception {
        doReturn(aBusinessObjectModelFileStoreWithBo(aBoWithoutConflictingQueryNames())).when(constraint).getCurrentBDM();

        final IStatus status = constraint.performBatchValidation(context);

        verify(context, never()).createFailureStatus(any());
        StatusAssert.assertThat(status).isOK();
    }

    private BusinessObject aBoWithConflictingQueryNames() {
        return new BusinessObjectBuilder("Employee")
                .withField(FieldBuilder.aStringField("name").build())
                .withQuery(new Query("findByName", "", List.class.getName()))
                .build();
    }

    private BusinessObject aBoWithoutConflictingQueryNames() {
        return new BusinessObjectBuilder("Employee")
                .withField(FieldBuilder.aStringField("name").build())
                .withQuery(new Query("myFindByName", "", List.class.getName()))
                .build();
    }

    private BusinessObjectModelFileStore aBusinessObjectModelFileStoreWithBo(BusinessObject... businessObjects) {
        final BusinessObjectModelFileStore fStore = mock(BusinessObjectModelFileStore.class);
        when(fStore.getBusinessObjects()).thenReturn(newArrayList(businessObjects));
        return fStore;
    }

}
