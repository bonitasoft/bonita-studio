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
package org.bonitasoft.studio.data.ui.wizard;

import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.bonitasoft.studio.model.process.builders.DataBuilder.aData;

import java.util.Collections;

import org.bonitasoft.studio.model.process.Data;
import org.eclipse.core.runtime.IStatus;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.common.collect.Sets;

/**
 * @author Romain Bioteau
 */
public class DataNameUnicityValidatorTest {

    @Rule
    public ExpectedException expectionRule = ExpectedException.none();

    @Test
    public void throw_IllegalArgumentException_for_null_dataScope() throws Exception {
        expectionRule.expect(IllegalArgumentException.class);
        new DataNameUnicityValidator(null, aData().build());
    }

    @Test
    public void unique_data_name_returns_a_valid_status() throws Exception {
        final DataNameUnicityValidator validator = new DataNameUnicityValidator(Collections.<Data> emptyList(), null);

        final IStatus status = validator.validate("myData");

        assertThat(status).isOK();
    }

    @Test
    public void duplicated_data_name_returns_a_error_status() throws Exception {
        final DataNameUnicityValidator validator = new DataNameUnicityValidator(Sets.newHashSet(aData().withName("myData").build()), null);

        final IStatus status = validator.validate("myData");

        assertThat(status).isNotOK();
    }

    @Test
    public void same_data_renamed_returns_a_valid_status() throws Exception {
        final Data aData = aData().withName("myData").build();
        final DataNameUnicityValidator validator = new DataNameUnicityValidator(Sets.newHashSet(aData), aData);

        final IStatus status = validator.validate("myData");

        assertThat(status).isOK();
    }

    @Test
    public void unique_data_name_among_others_returns_a_valid_status() throws Exception {
        final Data aData = aData().withName("myData").build();
        final DataNameUnicityValidator validator = new DataNameUnicityValidator(Sets.newHashSet(aData,
                aData().withName("myData2").build(),
                aData().build()),
                aData);

        final IStatus status = validator.validate("newDataName");

        assertThat(status).isOK();
    }
}
