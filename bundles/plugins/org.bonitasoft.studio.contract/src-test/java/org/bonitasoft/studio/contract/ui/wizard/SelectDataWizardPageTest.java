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
import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DocumentType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SelectDataWizardPageTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Mock
    private BusinessObjectModelRepositoryStore store;

    @Test
    public void should_isPageComplete_ReturnFalse_when_available_data_is_empty() {
        final List<Data> availableBusinessData = new ArrayList<Data>();
        final List<Document> availableDocuments = new ArrayList<Document>();
        final Contract contract = aContract().build();
        final WritableValue selectedDataObservable = new WritableValue();
        final SelectDataWizardPage page = new SelectDataWizardPage(contract, availableBusinessData, availableDocuments, selectedDataObservable,
                selectedDataObservable, new DataTypeSelectionOptions(), store);
        assertThat(page.isPageComplete()).isFalse();
    }

    @Test
    public void should_isPageComplete_ReturnFalse_when_available_documents_is_empty() {
        final List<Data> availableBusinessData = new ArrayList<Data>();
        final List<Document> availableDocuments = new ArrayList<Document>();
        final Contract contract = aContract().build();
        final WritableValue selectedDataObservable = new WritableValue();
        final DataTypeSelectionOptions dataTypeSelectionOptions = new DataTypeSelectionOptions();
        dataTypeSelectionOptions.setBusinessDataTypeSelected(false);
        final SelectDataWizardPage page = new SelectDataWizardPage(contract, availableBusinessData, availableDocuments, selectedDataObservable,
                selectedDataObservable, dataTypeSelectionOptions, store);
        assertThat(page.isPageComplete()).isFalse();
    }

    @Test
    public void should_isPageComplete_ReturnTrue_when_available_data_is_not_empty_selection_not_empty() {

        final List<Data> availableBusinessData = new ArrayList<Data>();
        final List<Document> availableDocuments = new ArrayList<Document>();
        final Contract contract = aContract().build();
        availableBusinessData.add(aBusinessData().build());
        final WritableValue selectedDataObservable = new WritableValue();
        selectedDataObservable.setValue(new Object());
        final SelectDataWizardPage page = new SelectDataWizardPage(contract, availableBusinessData, availableDocuments, selectedDataObservable,
                selectedDataObservable, new DataTypeSelectionOptions(), store);
        assertThat(page.isPageComplete()).isTrue();
    }

    @Test
    public void should_isPageComplete_ReturnTrue_when_available_documents_is_not_empty_selection_not_empty() {

        final List<Data> availableBusinessData = new ArrayList<Data>();
        final List<Document> availableDocuments = new ArrayList<Document>();
        final Contract contract = aContract().build();
        final BusinessObjectData bd = aBusinessData().build();
        availableBusinessData.add(bd);
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        availableDocuments.add(document);
        final WritableValue selectedDataObservable = new WritableValue();
        selectedDataObservable.setValue(document);
        final DataTypeSelectionOptions dataTypeSelectionOptions = new DataTypeSelectionOptions();
        dataTypeSelectionOptions.setBusinessDataTypeSelected(false);
        final SelectDataWizardPage page = new SelectDataWizardPage(contract, availableBusinessData, availableDocuments, selectedDataObservable,
                selectedDataObservable, dataTypeSelectionOptions, store);
        assertThat(page.isPageComplete()).isTrue();
    }

    @Test
    public void should_selectionNotEmpty_whenAvailableDataNotEmpty() {
        final List<Data> availableBusinessData = new ArrayList<Data>();
        final List<Document> availableDocuments = new ArrayList<Document>();
        final Contract contract = aContract().build();
        final BusinessObjectData bd = aBusinessData().build();
        availableBusinessData.add(bd);
        final WritableValue selectedDataObservable = new WritableValue();
        final SelectDataWizardPage page = new SelectDataWizardPage(contract, availableBusinessData, availableDocuments, selectedDataObservable,
                selectedDataObservable, new DataTypeSelectionOptions(), store);
        assertThat(selectedDataObservable).isNotNull();
        assertThat(page.isPageComplete()).isTrue();
    }

    @Test
    public void should_selectionNotEmpty_whenAvailableDocumentsNotEmpty() {
        final List<Data> availableBusinessData = new ArrayList<Data>();
        final List<Document> availableDocuments = new ArrayList<Document>();
        final Contract contract = aContract().build();
        final BusinessObjectData bd = aBusinessData().build();
        availableBusinessData.add(bd);
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        availableDocuments.add(document);
        final WritableValue selectedDataObservable = new WritableValue();
        selectedDataObservable.setValue(document);
        final DataTypeSelectionOptions dataTypeSelectionOptions = new DataTypeSelectionOptions();
        dataTypeSelectionOptions.setBusinessDataTypeSelected(false);
        final SelectDataWizardPage page = new SelectDataWizardPage(contract, availableBusinessData, availableDocuments, selectedDataObservable,
                selectedDataObservable, dataTypeSelectionOptions, store);
        assertThat(selectedDataObservable).isNotNull();
        assertThat(page.isPageComplete()).isTrue();
    }

    @Test
    public void shouldStatusBeOk_whenDocumentType_isSetToNone() {
        final List<Data> availableBusinessData = new ArrayList<Data>();
        final List<Document> availableDocuments = new ArrayList<Document>();
        final Contract contract = aContract().build();
        final BusinessObjectData bd = aBusinessData().build();
        availableBusinessData.add(bd);
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        availableDocuments.add(document);
        final WritableValue selectedDataObservable = new WritableValue();
        selectedDataObservable.setValue(document);
        final DataTypeSelectionOptions dataTypeSelectionOptions = new DataTypeSelectionOptions();
        dataTypeSelectionOptions.setBusinessDataTypeSelected(false);
        final SelectDataWizardPage page = new SelectDataWizardPage(contract, availableBusinessData, availableDocuments, selectedDataObservable,
                selectedDataObservable, dataTypeSelectionOptions, store);
        assertThat(page.createDefaultValueAlreadyDefinedValidator().validate(document)).isEqualTo(Status.OK_STATUS);
    }

    @Test
    public void shouldNotStatusBeOk_whenDocumentType_isSetToExternal() {
        final List<Data> availableBusinessData = new ArrayList<Data>();
        final List<Document> availableDocuments = new ArrayList<Document>();
        final Contract contract = aContract().build();
        final BusinessObjectData bd = aBusinessData().build();
        availableBusinessData.add(bd);
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setDocumentType(DocumentType.EXTERNAL);
        availableDocuments.add(document);
        final WritableValue selectedDataObservable = new WritableValue();
        selectedDataObservable.setValue(document);
        final DataTypeSelectionOptions dataTypeSelectionOptions = new DataTypeSelectionOptions();
        dataTypeSelectionOptions.setBusinessDataTypeSelected(false);
        final SelectDataWizardPage page = new SelectDataWizardPage(contract, availableBusinessData, availableDocuments, selectedDataObservable,
                selectedDataObservable, dataTypeSelectionOptions, store);
        assertThat(page.createDefaultValueAlreadyDefinedValidator().validate(document)).isNotEqualTo(Status.OK_STATUS);
    }

    @Test
    public void shouldCanFlipToNextPage_ReturnFalse_whenDocumentIsSelected() {
        final List<Data> availableBusinessData = new ArrayList<Data>();
        final List<Document> availableDocuments = new ArrayList<Document>();
        final Contract contract = aContract().build();
        final BusinessObjectData bd = aBusinessData().build();
        availableBusinessData.add(bd);
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        availableDocuments.add(document);
        final WritableValue selectedDataObservable = new WritableValue();
        selectedDataObservable.setValue(document);
        final DataTypeSelectionOptions dataTypeSelectionOptions = new DataTypeSelectionOptions();
        dataTypeSelectionOptions.setBusinessDataTypeSelected(false);
        final SelectDataWizardPage page = new SelectDataWizardPage(contract, availableBusinessData, availableDocuments, selectedDataObservable,
                selectedDataObservable, dataTypeSelectionOptions, store);
        assertThat(page.canFlipToNextPage()).isFalse();
    }

    @Test
    public void should_select_first_businessVariable_whenSelecting_businessDataType() {
        final List<Data> availableBusinessData = new ArrayList<Data>();
        final List<Document> availableDocuments = new ArrayList<Document>();
        final Contract contract = aContract().build();
        final BusinessObjectData bd = aBusinessData().build();
        availableBusinessData.add(bd);
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        availableDocuments.add(document);
        final WritableValue selectedDataObservable = new WritableValue();
        selectedDataObservable.setValue(document);
        final DataTypeSelectionOptions dataTypeSelectionOptions = new DataTypeSelectionOptions();
        dataTypeSelectionOptions.setBusinessDataTypeSelected(false);
        final SelectDataWizardPage page = new SelectDataWizardPage(contract, availableBusinessData, availableDocuments, selectedDataObservable,
                selectedDataObservable, dataTypeSelectionOptions, store);
        final SelectionAdapter bvSelectionAdapter = page.createBusinessVariableSelectionAdapter();
        bvSelectionAdapter.widgetSelected(mock(SelectionEvent.class));
        assertThat(dataTypeSelectionOptions.isBusinessDataTypeSelected()).isTrue();
        assertThat(selectedDataObservable.getValue()).isEqualTo(bd);
    }

    @Test
    public void should_select_first_document_whenSelecting_businessDataType() {
        final List<Data> availableBusinessData = new ArrayList<Data>();
        final List<Document> availableDocuments = new ArrayList<Document>();
        final Contract contract = aContract().build();
        final BusinessObjectData bd = aBusinessData().build();
        availableBusinessData.add(bd);
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        availableDocuments.add(document);
        final WritableValue selectedDataObservable = new WritableValue();
        selectedDataObservable.setValue(bd);
        final DataTypeSelectionOptions dataTypeSelectionOptions = new DataTypeSelectionOptions();
        dataTypeSelectionOptions.setBusinessDataTypeSelected(true);
        final SelectDataWizardPage page = new SelectDataWizardPage(contract, availableBusinessData, availableDocuments, selectedDataObservable,
                selectedDataObservable, dataTypeSelectionOptions, store);
        final SelectionAdapter documentSelectionAdapter = page.createDocumentSelectionAdapter();
        documentSelectionAdapter.widgetSelected(mock(SelectionEvent.class));
        assertThat(dataTypeSelectionOptions.isBusinessDataTypeSelected()).isFalse();
        assertThat(selectedDataObservable.getValue()).isEqualTo(document);
    }

}
