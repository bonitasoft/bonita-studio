/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.tests.connectors.sforce;

import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.bonitasoft.studio.connector.wizard.sforce.tooling.SalesforceTool;
import org.mockito.Mockito;


/**
 * @author Romain Bioteau
 *
 */
public class SalesforceMockUtil {

    private SalesforceTool salesforceToolMock;

    public void initMock() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        salesforceToolMock = Mockito.mock(SalesforceTool.class);
        final Field instanceField = SalesforceTool.class.getDeclaredField("INSTANCE");
        instanceField.setAccessible(true);
        instanceField.set(SalesforceTool.getInstance(), salesforceToolMock);
    }

    public void mockGetMandatoryFields(final String objectName, final List<String> mandatoryFields) {
        when(salesforceToolMock.getMandatoryFields(objectName)).thenReturn(mandatoryFields);
    }

    public void mockGetFields(final String objectName, final List<String> fields) {
        when(salesforceToolMock.getFields(objectName)).thenReturn(fields);
    }

    public void mockGetAllSForceObjects() {
        when(salesforceToolMock.getAllSForceObjects()).thenReturn(Arrays.asList("Account", "Contact"));
    }

    public void mockGetPickValues(final String objectName, final String fieldName, final List<String> values) {
        when(salesforceToolMock.getPickValues(objectName, fieldName)).thenReturn(values);
    }

    public void setSalesforceIsLogged(final boolean isLogged) {
        when(salesforceToolMock.isLogged()).thenReturn(isLogged);
    }

}
