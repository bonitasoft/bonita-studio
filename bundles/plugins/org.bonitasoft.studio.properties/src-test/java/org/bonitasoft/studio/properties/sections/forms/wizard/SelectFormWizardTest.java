/**
 * Copyright (C) 2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.sections.forms.wizard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataTypeBuilder.aBusinessObjectDataType;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.emf.ecore.EObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class SelectFormWizardTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();
    @Mock
    private BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> businessObjectStore;
    @Mock
    private BusinessObjectModelFileStore bomFStore;

    @Before
    public void setUp() throws Exception {
        when(businessObjectStore.getChildByQualifiedName("org.bos.Person")).thenReturn(Optional.of(bomFStore));
    }

    @Test
    public void should_not_add_business_data_without_simple_fields_in_accessible_elements() throws Exception {
        //Given
        final SelectFormWizard formWizard = new SelectFormWizard(null, null, null, businessObjectStore);
        final BusinessObjectData businessDataWithNoSimpleFields = aBusinessData().havingDataType(aBusinessObjectDataType())
                .withClassname(
                        "org.bos.Person")
                .build();
        final Task task = aTask().havingData(businessDataWithNoSimpleFields).build();
        when(bomFStore.getBusinessObject("org.bos.Person")).thenReturn(new BusinessObject());

        //When
        final List<EObject> accessibleModelElements = formWizard.getAccessibleModelElements(task,
                ProcessPackage.Literals.PAGE_FLOW__FORM);

        assertThat(accessibleModelElements).doesNotContain(businessDataWithNoSimpleFields);
    }

    @Test
    public void should_add_business_data_with_simple_fields_in_accessible_elements() throws Exception {
        //Given
        final SelectFormWizard formWizard = new SelectFormWizard(null, null, null, businessObjectStore);
        final BusinessObjectData businessDataWithSimpleFields = aBusinessData().havingDataType(aBusinessObjectDataType())
                .withClassname(
                        "org.bos.Person")
                .build();
        final Task task = aTask().havingData(businessDataWithSimpleFields).build();
        final BusinessObject businessObject = new BusinessObject();
        businessObject.addField(new SimpleField());
        when(bomFStore.getBusinessObject("org.bos.Person")).thenReturn(businessObject);

        //When
        final List<EObject> accessibleModelElements = formWizard.getAccessibleModelElements(task,
                ProcessPackage.Literals.PAGE_FLOW__FORM);

        assertThat(accessibleModelElements).contains(businessDataWithSimpleFields);
    }
}
