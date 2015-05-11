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
package org.bonitasoft.studio.common.diagram.dialog;

import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;

import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.IStatus;
import org.junit.Rule;
import org.junit.Test;

public class MustUpdateValidatorTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Test
    public void should_not_fail_if_version_is_changed() throws Exception {
        final MustUpdateValidator mustUpdateValidator = new MustUpdateValidator(aPool().withName("Pool").withVersion("1.0").build(), new WritableValue("Pool",
                String.class), new WritableValue("1.1", String.class));

        final IStatus status = mustUpdateValidator.validate();

        assertThat(status).isOK();
    }

    @Test
    public void should_not_fail_if_name__is_changed() throws Exception {
        final MustUpdateValidator mustUpdateValidator = new MustUpdateValidator(aPool().withName("Pool").withVersion("1.0").build(), new WritableValue("Pool2",
                String.class), new WritableValue("1.0", String.class));

        final IStatus status = mustUpdateValidator.validate();

        assertThat(status).isOK();
    }

    @Test
    public void should_fail_if_neither_name_nor_version_is_changed() throws Exception {
        final MustUpdateValidator mustUpdateValidator = new MustUpdateValidator(aPool().withName("Pool").withVersion("1.0").build(), new WritableValue("Pool",
                String.class), new WritableValue("1.0", String.class));

        final IStatus status = mustUpdateValidator.validate();

        assertThat(status).isNotOK();
    }
}
