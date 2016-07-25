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
package org.bonitasoft.studio.contract.core.mapping.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder.aBO;

import org.junit.Test;

public class VariableNameResolverTest {

    @Test
    public void should_create_a_variable_name_for_a_business_object() throws Exception {
        final VariableNameResolver variableNameResolver = new VariableNameResolver();

        final String varName = variableNameResolver.newVarName(aBO("org.test.Employee").build());

        assertThat(varName).isEqualTo("employeeVar");
    }

    @Test
    public void should_create_a_variable_name_for_a_business_object_with_suffix_if_variable_is_already_in_the_context() throws Exception {
        final VariableNameResolver variableNameResolver = new VariableNameResolver();

        final String varName = variableNameResolver.newVarName(aBO("org.test.Employee").build());
        final String varName2 = variableNameResolver.newVarName(aBO("org.test.Employee").build());
        final String varName3 = variableNameResolver.newVarName(aBO("org.test.Employee").build());

        assertThat(varName).isEqualTo("employeeVar");
        assertThat(varName2).isEqualTo("employeeVar2");
        assertThat(varName3).isEqualTo("employeeVar3");
    }

    @Test
    public void should_create_a_list_variable_name_for_a_business_object() throws Exception {
        final VariableNameResolver variableNameResolver = new VariableNameResolver();

        final String varName = variableNameResolver.newListVarName(aBO("org.test.Employee").build());

        assertThat(varName).isEqualTo("employeeList");
    }

    @Test
    public void should_create_a_list_variable_name_for_a_business_object_with_suffix_if_variable_is_already_in_the_context() throws Exception {
        final VariableNameResolver variableNameResolver = new VariableNameResolver();

        final String varName = variableNameResolver.newListVarName(aBO("org.test.Employee").build());
        final String varName2 = variableNameResolver.newListVarName(aBO("org.test.Employee").build());
        final String varName3 = variableNameResolver.newListVarName(aBO("org.test.Employee").build());

        assertThat(varName).isEqualTo("employeeList");
        assertThat(varName2).isEqualTo("employeeList2");
        assertThat(varName3).isEqualTo("employeeList3");
    }

}
