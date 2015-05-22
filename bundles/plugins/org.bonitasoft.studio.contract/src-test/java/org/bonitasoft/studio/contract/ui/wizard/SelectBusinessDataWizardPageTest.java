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
package org.bonitasoft.studio.contract.ui.wizard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SelectBusinessDataWizardPageTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Mock
    private BusinessObjectModelRepositoryStore store;

    @Test
    public void should_isPageComplete_ReturnFalse_when_available_data_is_empty() {
        final List<Data> availableBusinessData = new ArrayList<Data>();
        final WritableValue selectedDataObservable = new WritableValue();
        final SelectBusinessDataWizardPage page = new SelectBusinessDataWizardPage(availableBusinessData, selectedDataObservable, store);
        assertThat(page.isPageComplete()).isFalse();
    }

    @Test
    public void should_isPageComplete_ReturnTrue_when_available_data_is_not_empty() {
        final List<Data> availableBusinessData = new ArrayList<Data>();
        availableBusinessData.add(aBusinessData().build());
        final WritableValue selectedDataObservable = new WritableValue();
        final SelectBusinessDataWizardPage page = new SelectBusinessDataWizardPage(availableBusinessData, selectedDataObservable, store);
        assertThat(page.isPageComplete()).isTrue();
    }
}
