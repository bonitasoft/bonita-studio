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
package org.bonitasoft.studio.contract.core.mapping;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;

import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.SimpleFieldBuilder;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.assertions.ContractInputAssert;
import org.junit.Test;

/**
 * @author aurelie
 */
public class RootContractInputGeneratorTest {

    @Test
    public void should_create_a_complex_contract_input_with_root_input_name() throws Exception {
        final RootContractInputGenerator rootContractInputGenerator = new RootContractInputGenerator("rootInputName",
                Collections.<FieldToContractInputMapping> emptyList());

        ContractInputAssert.assertThat(rootContractInputGenerator.toRootContractInput()).hasName("rootInputName").hasType(ContractInputType.COMPLEX);
    }

    @Test
    public void should_create_a_complex_contract_input_with_generated_child_input_from_mapping() throws Exception {
        final SimpleFieldToContractInputMapping notGeneratedMapping = new SimpleFieldToContractInputMapping((SimpleField) SimpleFieldBuilder.aStringField(
                "input2").build());
        notGeneratedMapping.setGenerated(false);
        final RootContractInputGenerator rootContractInputGenerator = new RootContractInputGenerator("rootInputName",
                newArrayList(notGeneratedMapping, new SimpleFieldToContractInputMapping((SimpleField) SimpleFieldBuilder
                        .aStringField("input1").build())));

        assertThat(rootContractInputGenerator.toRootContractInput().getInputs()).hasSize(1);
    }
}
