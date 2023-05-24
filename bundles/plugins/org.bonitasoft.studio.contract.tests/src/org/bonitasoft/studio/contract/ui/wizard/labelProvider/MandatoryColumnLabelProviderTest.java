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
package org.bonitasoft.studio.contract.ui.wizard.labelProvider;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.contract.core.mapping.SimpleFieldToContractInputMapping;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.SimpleFieldBuilder;
import org.junit.Test;

/**
 * @author aurelie
 */
public class MandatoryColumnLabelProviderTest {

    @Test
    public void should_return_true_when_attribute_is_mandatory() {
        final SimpleFieldToContractInputMapping mapping = new SimpleFieldToContractInputMapping(SimpleFieldBuilder.aTextField("employee")
                .withName("employee").notNullable().build());
        final MandatoryColumnLabelProvider provider = new MandatoryColumnLabelProvider();
        assertThat(provider.getText(mapping)).isEqualTo(Boolean.TRUE.toString());
    }

    @Test
    public void should_return_false_when_attribute_is_mandatory() {
        final SimpleFieldToContractInputMapping mapping = new SimpleFieldToContractInputMapping(SimpleFieldBuilder.aTextField("employee")
                .withName("employee").nullable().build());
        final MandatoryColumnLabelProvider provider = new MandatoryColumnLabelProvider();
        assertThat(provider.getText(mapping)).isEqualTo(Boolean.FALSE.toString());
    }

}
