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
package org.bonitasoft.studio.document.ui.validator;

import static com.google.common.collect.Lists.newArrayList;
import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;
import static org.bonitasoft.studio.model.process.builders.DocumentBuilder.aDocument;

import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DocumentType;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.junit.Rule;
import org.junit.Test;

public class MultipleContractInputValidatorTest {

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    @Test
    public void should_not_fail_when_updating_viewer_input_and_contract_input_is_null() throws Exception {
        final WritableValue selectionObservable = new WritableValue();
        final WritableValue viewerInputObservable = new WritableValue();
        final EMFDataBindingContext emfDataBindingContext = new EMFDataBindingContext();
        emfDataBindingContext.addValidationStatusProvider(new MultipleContractInputValidator(aDocument().withDocumentType(DocumentType.CONTRACT).build(),
                selectionObservable, viewerInputObservable));

        viewerInputObservable.setValue(newArrayList(aContractInput().build()));

        assertThat(statusFromContext(emfDataBindingContext)).isOK();
    }

    @Test
    public void should_fail_when_updating_multiple_document_value_and_contract_input_is_null() throws Exception {
        final WritableValue selectionObservable = new WritableValue();
        final WritableValue viewerInputObservable = new WritableValue();
        final EMFDataBindingContext emfDataBindingContext = new EMFDataBindingContext();
        final Document document = aDocument().withDocumentType(DocumentType.CONTRACT).build();
        emfDataBindingContext.addValidationStatusProvider(new MultipleContractInputValidator(document,
                selectionObservable, viewerInputObservable));

        document.setMultiple(true);

        assertThat(statusFromContext(emfDataBindingContext)).isNotOK();
    }

    @Test
    public void should_not_fail_when_updating_validation_and_document_type_is_not_contract() throws Exception {
        final WritableValue selectionObservable = new WritableValue();
        final WritableValue viewerInputObservable = new WritableValue();
        final EMFDataBindingContext emfDataBindingContext = new EMFDataBindingContext();
        final Document document = aDocument().withDocumentType(DocumentType.NONE).build();
        emfDataBindingContext.addValidationStatusProvider(new MultipleContractInputValidator(document,
                selectionObservable, viewerInputObservable));

        document.setMultiple(true);

        assertThat(statusFromContext(emfDataBindingContext)).isOK();
    }

    @Test
    public void should_not_fail_when_updating_validation_and_contract_input_is_not_null() throws Exception {
        final WritableValue selectionObservable = new WritableValue();
        final WritableValue viewerInputObservable = new WritableValue();
        final EMFDataBindingContext emfDataBindingContext = new EMFDataBindingContext();
        final Document document = aDocument().withDocumentType(DocumentType.CONTRACT).build();
        emfDataBindingContext.addValidationStatusProvider(new MultipleContractInputValidator(document,
                selectionObservable, viewerInputObservable));

        selectionObservable.setValue(aContractInput().build());

        assertThat(statusFromContext(emfDataBindingContext)).isOK();
    }

    protected IStatus statusFromContext(final EMFDataBindingContext emfDataBindingContext) {
        return (IStatus) ((ValidationStatusProvider) emfDataBindingContext.getValidationStatusProviders().get(0)).getValidationStatus().getValue();
    }

}
