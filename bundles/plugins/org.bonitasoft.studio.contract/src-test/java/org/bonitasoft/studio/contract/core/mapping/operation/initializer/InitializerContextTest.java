/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.contract.core.mapping.operation.initializer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder.aBO;
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.aCompositionField;
import static org.bonitasoft.studio.model.process.builders.DataBuilder.aData;

import org.bonitasoft.studio.contract.core.mapping.RelationFieldToContractInputMapping;
import org.junit.Test;

public class InitializerContextTest {

    @Test
    public void should_return_data_field_refrence() throws Exception {
        final InitializerContext initializerContext = new InitializerContext();
        initializerContext.setData(aData().withName("employee").build());
        initializerContext
                .setMapping(new RelationFieldToContractInputMapping(aCompositionField("address", aBO("Address").build())));

        final String ref = initializerContext.getRef(null);

        assertThat(ref).isEqualTo("employee.address");
    }

    @Test
    public void should_return_field_refrence_from_parent() throws Exception {
        final InitializerContext initializerContext = new InitializerContext();
        initializerContext.setData(aData().withName("employee").build());
        initializerContext
                .setMapping(new RelationFieldToContractInputMapping(aCompositionField("address", aBO("Address").build())));

        final InitializerContext parentContext = new InitializerContext();
        parentContext.setLocalVariableName("employeeVar");

        final String ref = initializerContext.getRef(parentContext);

        assertThat(ref).isEqualTo("employeeVar.address");
    }

}
