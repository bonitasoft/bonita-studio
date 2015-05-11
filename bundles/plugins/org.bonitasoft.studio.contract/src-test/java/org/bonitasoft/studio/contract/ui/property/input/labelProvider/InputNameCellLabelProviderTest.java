/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.contract.ui.property.input.labelProvider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;

import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.set.WritableSet;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class InputNameCellLabelProviderTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Test
    public void should_display_contract_input_name_as_text() throws Exception {
        final InputNameCellLabelProvider labelProvider = new InputNameCellLabelProvider(new AdapterFactoryContentProvider(
                new ProcessItemProviderAdapterFactory()), new WritableSet());

        assertThat(labelProvider.getText(aContractInput().withName("myInput").build())).isEqualTo("myInput");
    }

    @Test
    public void should_not_display_an_icon() throws Exception {
        final InputNameCellLabelProvider labelProvider = new InputNameCellLabelProvider(new AdapterFactoryContentProvider(
                new ProcessItemProviderAdapterFactory()), new WritableSet());

        assertThat(labelProvider.getImage(aContractInput().build())).isNull();
    }

}
