/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.common.predicate.EMFPredicates.featureNotNull;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;

import org.bonitasoft.studio.model.process.ProcessPackage;
import org.junit.Test;

public class EMFPredicatesTest {

    @Test
    public void eobject_feature_is_null_if_no_value_is_set() throws Exception {
        assertThat(featureNotNull(ProcessPackage.Literals.ELEMENT__NAME).apply(aPool().withName(null).build())).isFalse();
    }

    @Test
    public void eobject_feature_is_not_null_if_feature_valeu_is_set() throws Exception {
        assertThat(featureNotNull(ProcessPackage.Literals.ELEMENT__NAME).apply(aPool().withName("Pool").build())).isTrue();
    }

    @Test(expected = AssertionError.class)
    public void throw_AssertionError_if_feature_is_not_found() throws Exception {
        featureNotNull(ProcessPackage.Literals.TASK__EXPECTED_DURATION).apply(aPool().build());
    }
}
