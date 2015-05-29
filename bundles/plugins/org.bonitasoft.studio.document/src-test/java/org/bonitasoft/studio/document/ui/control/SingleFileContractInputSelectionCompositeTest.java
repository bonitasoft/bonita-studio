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
package org.bonitasoft.studio.document.ui.control;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;
import static org.bonitasoft.studio.model.process.builders.DocumentBuilder.aDocument;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;

import java.util.List;

import org.bonitasoft.studio.document.ui.validator.SingleContractInputValidator;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.viewers.StructuredSelection;
import org.junit.Rule;
import org.junit.Test;

public class SingleFileContractInputSelectionCompositeTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Test
    public void should_fill_combo_with_single_file_contract_input_if_document_is_single() throws Exception {
        final FileContractInputSelectionComposite control = new SingleFileContractInputSelectionComposite(
                realmWithDisplay.createComposite());

        control.bindControl(aDocument().build(),
                aPool().havingContract(aContract().havingInput(aContractInput().withName("myFile").withType(ContractInputType.FILE))).build(),
                new EMFDataBindingContext());

        assertThat((List<ContractInput>) control.getContractInputComboViewer().getInput()).extracting("name").contains("myFile");
    }

    @Test
    public void should_fill_combo_with_multiple_file_contract_input_if_document_is_multiple() throws Exception {
        final FileContractInputSelectionComposite control = new SingleFileContractInputSelectionComposite(
                realmWithDisplay.createComposite());

        control.bindControl(aDocument().multiple().build(),
                aPool().havingContract(aContract().havingInput(aContractInput().withName("myMultipleFile").withType(ContractInputType.FILE).multiple()))
                        .build(),
                new EMFDataBindingContext());

        assertThat((List<ContractInput>) control.getContractInputComboViewer().getInput()).extracting("name").contains("myMultipleFile");
    }

    @Test
    public void should_not_fill_combo_with_single_file_contract_inputif_document_is_multiple() throws Exception {
        final FileContractInputSelectionComposite control = new SingleFileContractInputSelectionComposite(
                realmWithDisplay.createComposite());

        control.bindControl(aDocument().multiple().build(),
                aPool().havingContract(aContract().havingInput(aContractInput().withName("myFile").withType(ContractInputType.FILE))).build(),
                new EMFDataBindingContext());

        assertThat((List<ContractInput>) control.getContractInputComboViewer().getInput()).isEmpty();
    }

    @Test
    public void should_fill_combo_with_single_file_contract_input_contained_in_a_single_comlex_input() throws Exception {
        final FileContractInputSelectionComposite control = new SingleFileContractInputSelectionComposite(
                realmWithDisplay.createComposite());

        control.bindControl(aDocument().build(),
                aPool().havingContract(aContract().havingInput(aContractInput().withName("parent").withType(ContractInputType.COMPLEX)
                        .havingInput(aContractInput().withName("myFile").withType(ContractInputType.FILE)))).build(),
                new EMFDataBindingContext());

        assertThat((List<ContractInput>) control.getContractInputComboViewer().getInput()).extracting("name").contains("myFile");
    }

    @Test
    public void should_not_fill_combo_with_multiple_file_contract_input() throws Exception {
        final FileContractInputSelectionComposite control = new SingleFileContractInputSelectionComposite(
                realmWithDisplay.createComposite());

        control.bindControl(aDocument().build(),
                aPool().havingContract(aContract().havingInput(aContractInput().withName("myFile").withType(ContractInputType.FILE).multiple())).build(),
                new EMFDataBindingContext());

        assertThat((List<ContractInput>) control.getContractInputComboViewer().getInput()).isEmpty();
    }

    @Test
    public void should_not_fill_combo_with_single_file_contract_input_contained_in_a_multiple_complex_input() throws Exception {
        final FileContractInputSelectionComposite control = new SingleFileContractInputSelectionComposite(
                realmWithDisplay.createComposite());

        control.bindControl(aDocument().build(),
                aPool().havingContract(aContract().havingInput(aContractInput().withName("parent").withType(ContractInputType.COMPLEX).multiple()
                        .havingInput(aContractInput().withName("myFile").withType(ContractInputType.FILE)))).build(),
                new EMFDataBindingContext());

        assertThat((List<ContractInput>) control.getContractInputComboViewer().getInput()).isEmpty();
    }

    @Test
    public void should_fill_combo_with_single_file_contract_input_contained_in_a_multiple_complex_input_if_document_is_multiple() throws Exception {
        final FileContractInputSelectionComposite control = new SingleFileContractInputSelectionComposite(
                realmWithDisplay.createComposite());

        control.bindControl(aDocument().multiple().build(),
                aPool().havingContract(aContract().havingInput(aContractInput().withName("parent").withType(ContractInputType.COMPLEX).multiple()
                        .havingInput(aContractInput().withName("myFile").withType(ContractInputType.FILE)))).build(),
                new EMFDataBindingContext());

        assertThat((List<ContractInput>) control.getContractInputComboViewer().getInput()).extracting("name").contains("myFile");
    }

    @Test
    public void should_combo_selection_set_document_contract_input() throws Exception {
        final FileContractInputSelectionComposite control = new SingleFileContractInputSelectionComposite(
                realmWithDisplay.createComposite());

        final Document document = aDocument().build();
        final ContractInput contractInput = aContractInput().withName("myFile").withType(ContractInputType.FILE).build();
        control.bindControl(document,
                aPool().havingContract(aContract().havingInput(contractInput)).build(),
                new EMFDataBindingContext());
        control.getContractInputComboViewer().setSelection(new StructuredSelection(contractInput));

        assertThat(document.getContractInput()).isEqualTo(contractInput);
    }

    @Test
    public void should_create_a_SingleContratInputValidator() throws Exception {
        final FileContractInputSelectionComposite control = new SingleFileContractInputSelectionComposite(
                realmWithDisplay.createComposite());

        assertThat(control.createContractInputParameter(aDocument().build(), new WritableValue(), new WritableValue())).isInstanceOf(
                SingleContractInputValidator.class);
    }
}
