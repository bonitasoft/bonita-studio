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
package org.bonitasoft.studio.contract.ui.property.constraint.edit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;

import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.viewers.TableViewer;
import org.junit.Rule;
import org.junit.Test;

public class ErrorMessageObservableEditingSupportTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Test
    public void should_create_a_convert_update_strategy() throws Exception {
        final ErrorMessageObservableEditingSupport editingSupport = new ErrorMessageObservableEditingSupport(aTableViewer(),
                null, new EMFDataBindingContext());

        final UpdateValueStrategy convertStrategy = editingSupport.targetToModelConvertStrategy(aContractInput().build());

        assertThat(convertStrategy.getUpdatePolicy()).isEqualTo(UpdateValueStrategy.POLICY_CONVERT);
    }

    private TableViewer aTableViewer() {
        return new TableViewer(realmWithDisplay.createComposite());
    }
}
