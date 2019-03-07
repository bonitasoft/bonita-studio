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
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DocumentType;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.swt.WidgetFinder;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SelectDataWizardPageTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    private final WidgetFinder widgetFinder = new WidgetFinder();

    @Mock
    private BusinessObjectModelRepositoryStore store;
    
    @Before
    public void setUp() throws Exception {
       when(store.getBusinessObjectByQualifiedName(Mockito.anyString())).thenReturn(Optional.empty());
    }

    @Test
    public void should_isPageComplete_ReturnFalse_when_available_data_is_empty() {
        final List<Data> availableBusinessData = new ArrayList<Data>();
        final List<Document> availableDocuments = new ArrayList<Document>();
        final Contract contract = aContract().build();
        final WritableValue selectedDataObservable = new WritableValue();
        final SelectDataWizardPage page = new SelectDataWizardPage(contract, availableBusinessData, availableDocuments, selectedDataObservable,
                selectedDataObservable, new GenerationOptions(), store);
        assertThat(page.isPageComplete()).isFalse();
    }

    @Test
    public void should_isPageComplete_ReturnFalse_when_available_documents_is_empty() {
        final List<Data> availableBusinessData = new ArrayList<Data>();
        final List<Document> availableDocuments = new ArrayList<Document>();
        final Contract contract = aContract().build();
        final WritableValue selectedDataObservable = new WritableValue();
        final SelectDataWizardPage page = new SelectDataWizardPage(contract, availableBusinessData, availableDocuments, selectedDataObservable,
                selectedDataObservable, new GenerationOptions(), store);
        page.setBusinessDataTypeSelected(false);
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
                selectedDataObservable, new GenerationOptions(), store);
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
        final SelectDataWizardPage page = new SelectDataWizardPage(contract, availableBusinessData, availableDocuments, selectedDataObservable,
                selectedDataObservable, new GenerationOptions(), store);
        page.setBusinessDataTypeSelected(false);
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
                selectedDataObservable, new GenerationOptions(), store);
        assertThat(selectedDataObservable).isNotNull();
        assertThat(page.isPageComplete()).isTrue();
    }

    @Test
    public void should_rootInputName_be_dataName_input() {
        final List<Data> availableBusinessData = new ArrayList<Data>();
        final List<Document> availableDocuments = new ArrayList<Document>();
        final Contract contract = aContract().build();
        final BusinessObjectData bd = aBusinessData().withName("myData").build();
        availableBusinessData.add(bd);
        final WritableValue selectedDataObservable = new WritableValue();
        selectedDataObservable.setValue(bd);
        final WritableValue rootNameObservable = new WritableValue();
        final SelectDataWizardPage page = new SelectDataWizardPage(contract, availableBusinessData, availableDocuments, selectedDataObservable,
                rootNameObservable, new GenerationOptions(), store);
        page.setWizard(wizardWithContainer());
        page.createControl(realmWithDisplay.createComposite());
        assertThat(rootNameObservable.getValue()).isEqualTo("myDataInput");
    }

    @Test
    public void should_rename_inputName_when_entering_a_text() {
        final List<Data> availableBusinessData = new ArrayList<Data>();
        final List<Document> availableDocuments = new ArrayList<Document>();
        final Contract contract = aContract().build();
        final BusinessObjectData bd = aBusinessData().withName("myData").build();
        availableBusinessData.add(bd);
        final WritableValue selectedDataObservable = new WritableValue();
        selectedDataObservable.setValue(bd);
        final WritableValue rootNameObservable = new WritableValue();
        final SelectDataWizardPage page = new SelectDataWizardPage(contract, availableBusinessData, availableDocuments, selectedDataObservable,
                rootNameObservable, new GenerationOptions(), store);
        page.setWizard(wizardWithContainer());
        page.createControl(realmWithDisplay.createComposite());
        final Text text = widgetFinder.<Text> withLabel(realmWithDisplay.getShell(), org.bonitasoft.studio.contract.i18n.Messages.rootContractInputName);
        text.setText("myDataInput");
        text.notifyListeners(SWT.Modify, new Event());
        assertThat(rootNameObservable.getValue()).isEqualTo("myDataInput");
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
        final SelectDataWizardPage page = new SelectDataWizardPage(contract, availableBusinessData, availableDocuments, selectedDataObservable,
                selectedDataObservable, new GenerationOptions(), store);
        page.setBusinessDataTypeSelected(false);
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
        final SelectDataWizardPage page = new SelectDataWizardPage(contract, availableBusinessData, availableDocuments, selectedDataObservable,
                selectedDataObservable, new GenerationOptions(), store);
        page.setBusinessDataTypeSelected(false);
        assertThat(page.createDefaultValueAlreadyDefinedValidator().validate(document)).isEqualTo(Status.OK_STATUS);
    }

    @Test
    public void shouldNotStatusBeOk_whenDocumentType_isSetToExternal() {
        final List<Data> availableBusinessData = new ArrayList<Data>();
        final List<Document> availableDocuments = new ArrayList<Document>();
        final Contract contract = aContract().build();
        final Pool pool = aPool().build();
        pool.setContract(contract);
        final BusinessObjectData bd = aBusinessData().build();
        availableBusinessData.add(bd);
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setDocumentType(DocumentType.EXTERNAL);
        availableDocuments.add(document);
        final WritableValue selectedDataObservable = new WritableValue();
        selectedDataObservable.setValue(document);
        final SelectDataWizardPage page = new SelectDataWizardPage(contract, availableBusinessData, availableDocuments, selectedDataObservable,
                selectedDataObservable, new GenerationOptions(), store);
        page.setBusinessDataTypeSelected(false);
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
        final SelectDataWizardPage page = new SelectDataWizardPage(contract, availableBusinessData, availableDocuments, selectedDataObservable,
                selectedDataObservable, new GenerationOptions(), store);
        page.setBusinessDataTypeSelected(false);
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
        final SelectDataWizardPage page = new SelectDataWizardPage(contract, availableBusinessData, availableDocuments, selectedDataObservable,
                selectedDataObservable, new GenerationOptions(), store);
        page.setBusinessDataTypeSelected(false);
        final SelectionAdapter bvSelectionAdapter = page.createBusinessVariableSelectionAdapter();
        bvSelectionAdapter.widgetSelected(mock(SelectionEvent.class));
        assertThat(page.isBusinessDataTypeSelected()).isTrue();
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
        final SelectDataWizardPage page = new SelectDataWizardPage(contract, availableBusinessData, availableDocuments, selectedDataObservable,
                selectedDataObservable, new GenerationOptions(), store);
        page.setBusinessDataTypeSelected(true);
        final SelectionAdapter documentSelectionAdapter = page.createDocumentSelectionAdapter();
        documentSelectionAdapter.widgetSelected(mock(SelectionEvent.class));
        assertThat(page.isBusinessDataTypeSelected()).isFalse();
        assertThat(selectedDataObservable.getValue()).isEqualTo(document);
    }

    private IWizard wizardWithContainer() {
        final IWizard wizard = mock(IWizard.class);
        final IWizardContainer wizardContainer = mock(IWizardContainer.class);
        when(wizardContainer.getShell()).thenReturn(realmWithDisplay.getShell());
        when(wizard.getContainer()).thenReturn(wizardContainer);
        return wizard;
    }

}
