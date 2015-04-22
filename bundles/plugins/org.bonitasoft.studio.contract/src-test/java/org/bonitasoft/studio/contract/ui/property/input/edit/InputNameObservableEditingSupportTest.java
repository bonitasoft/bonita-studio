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
package org.bonitasoft.studio.contract.ui.property.input.edit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;

import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.progress.IProgressService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InputNameObservableEditingSupportTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();
    @Mock
    private IMessageManager messageManager;
    @Mock
    private IProgressService progressService;

    @Test
    public void should_create_a_convert_update_strategy() throws Exception {
        final InputNameObservableEditingSupport editingSupport = new InputNameObservableEditingSupport(aTableViewer(),
                null, null, new EMFDataBindingContext(), progressService);

        final UpdateValueStrategy convertStrategy = editingSupport.taregtToModelConvertStrategy();

        assertThat(convertStrategy.getUpdatePolicy()).isEqualTo(UpdateValueStrategy.POLICY_CONVERT);
    }

    @Test
    public void should_fails_validation_if_input_name_already_exists() throws Exception {
        final InputNameObservableEditingSupport editingSupport = new InputNameObservableEditingSupport(aTableViewer(),
                aContract().havingInput(aContractInput().withName("employee").havingInput(aContractInput().withName("firstName")))
                        .build(), messageManager, new EMFDataBindingContext(), progressService);

        final UpdateValueStrategy convertStrategy = editingSupport.taregtToModelConvertStrategy();
        final IStatus status = convertStrategy.validateAfterGet("firstName");

        assertThat(status).isNotOK();
    }

    @Test
    public void should_fails_validation_if_input_name_is_empty_exists() throws Exception {
        final InputNameObservableEditingSupport editingSupport = new InputNameObservableEditingSupport(aTableViewer(),
                aSimpleContract(), messageManager, new EMFDataBindingContext(), progressService);

        final UpdateValueStrategy convertStrategy = editingSupport.taregtToModelConvertStrategy();
        final IStatus status = convertStrategy.validateAfterGet("");

        assertThat(status).isNotOK();
    }

    @Test
    public void should_fails_validation_if_input_name_is_longer_than_50_chars_exists() throws Exception {
        final InputNameObservableEditingSupport editingSupport = new InputNameObservableEditingSupport(aTableViewer(),
                aSimpleContract(), messageManager, new EMFDataBindingContext(), progressService);

        final UpdateValueStrategy convertStrategy = editingSupport.taregtToModelConvertStrategy();
        final IStatus status = convertStrategy
                .validateAfterGet("afkjdshfkjdhsfkjdhsfkjhdskjfhdkjshfdkjshfkjdshfdkjshfkjsdhfkjsdhfkjsdhfkjsdhfkjdshfdkjshfkjdshfdkjshfdkjshf");

        assertThat(status).isNotOK();
    }

    @Test
    public void should_fails_validation_if_input_name_is_not_a_valid_java_identifiable() throws Exception {
        final InputNameObservableEditingSupport editingSupport = new InputNameObservableEditingSupport(aTableViewer(),
                aSimpleContract(), messageManager, new EMFDataBindingContext(), progressService);

        final UpdateValueStrategy convertStrategy = editingSupport.taregtToModelConvertStrategy();
        final IStatus status = convertStrategy
                .validateAfterGet("1startsWithOne");

        assertThat(status).isNotOK();
    }

    @Test
    public void should_fails_validation_if_input_name_contais_spaces() throws Exception {
        final InputNameObservableEditingSupport editingSupport = new InputNameObservableEditingSupport(aTableViewer(),
                aSimpleContract(), messageManager, new EMFDataBindingContext(), progressService);

        final UpdateValueStrategy convertStrategy = editingSupport.taregtToModelConvertStrategy();
        final IStatus status = convertStrategy
                .validateAfterGet("first name");

        assertThat(status).isNotOK();
    }

    @Test
    public void should_fails_validation_if_input_name_startsWith_an_uppercase() throws Exception {
        final InputNameObservableEditingSupport editingSupport = new InputNameObservableEditingSupport(aTableViewer(),
                aSimpleContract(), messageManager, new EMFDataBindingContext(), progressService);

        final UpdateValueStrategy convertStrategy = editingSupport.taregtToModelConvertStrategy();
        final IStatus status = convertStrategy
                .validateAfterGet("Firstname");

        assertThat(status).isNotOK();
    }

    private TableViewer aTableViewer() {
        return new TableViewer(realmWithDisplay.createComposite());
    }

    private Contract aSimpleContract() {
        return aContract().havingInput(aContractInput().withName("firstName"))
                .build();
    }
}
