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
package org.bonitasoft.studio.properties.sections.subprocess;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.model.process.builders.ContractInputBuilder;
import org.junit.Test;


public class ContractInputNamePredicateTest {

    @Test
    public void testApplyOk() {
        assertThat(new ContractInputNamePredicate("test").apply(ContractInputBuilder.aContractInput().withName("test").build())).isTrue();
    }

    @Test
    public void testApplyKO() {
        assertThat(new ContractInputNamePredicate("test").apply(ContractInputBuilder.aContractInput().withName("Test").build())).isFalse();
    }

    @Test
    public void testApply_withNullString() {
        assertThat(new ContractInputNamePredicate(null).apply(ContractInputBuilder.aContractInput().withName("test").build())).isFalse();
    }

    @Test
    public void testApply_withNullElement() {
        assertThat(new ContractInputNamePredicate("test").apply(null)).isFalse();
    }

    @Test
    public void testApply_withNullName() {
        assertThat(new ContractInputNamePredicate("test").apply(ContractInputBuilder.aContractInput().withName(null).build())).isFalse();
    }

}
