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

import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Sets.newHashSet;
import static org.bonitasoft.studio.ui.converter.ConverterBuilder.newConverter;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.groovyReferenceValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.mandatoryValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.maxLengthValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.multiValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.uniqueValidator;

import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.ui.BusinessObjectDataStyledLabelProvider;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.model.ModelSearch;
import org.bonitasoft.studio.common.widgets.CustomStackLayout;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.contract.ui.wizard.GenerationOptions.EditMode;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DocumentType;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.google.common.base.Function;

public class SelectDataWizardPage extends WizardPage {

    private static final String INPUT = "Input";
    private static final String DOC_INPUT = "DocumentInput";
    private static final int INPUT_NAME_MAX_LENGTH = 50;
    private List<Data> availableBusinessData;
    final WritableValue<Object> selectedDataObservable;
    private final BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> businessObjectStore;
    private Button businessVariableButton;
    private Button documentButton;
    private Composite businessVariableTableViewerComposite;
    private Composite documentTableViewerComposite;
    private final List<Document> availableDocuments;
    private final WritableValue<String> rootNameObservable;
    private final Contract contract;
    private String rootName;
    private SelectObservableValue<Boolean> selectionTypeObservable;
    private CustomStackLayout stackLayout;
    private boolean businessDataTypeSelected = true;
    private GenerationOptions generateOptions;

    public SelectDataWizardPage(final Contract contract, final List<Data> availableBusinessData,
            final List<Document> availableDocuments,
            final WritableValue<Object> selectedDataObservable,
            final WritableValue<String> rootNameObservable,
            GenerationOptions generateOptions,
            final BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> businessObjectStore) {
        super(SelectDataWizardPage.class.getName());
        setTitle(Messages.SelectBusinessDataWizardPageTitle);
        setDescription(Messages.selectBusinessDataWizardPageDescription);
        this.availableBusinessData = availableBusinessData;
        this.availableDocuments = availableDocuments;
        this.selectedDataObservable = selectedDataObservable;
        this.businessObjectStore = businessObjectStore;
        this.rootNameObservable = rootNameObservable;
        this.generateOptions = generateOptions;
        this.contract = contract;
    }

    @Override
    public void createControl(final Composite parent) {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(GridDataFactory.fillDefaults().create());
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        final DataBindingContext dbc = new DataBindingContext();
        createRadioButtonComposite(composite, dbc);
        final Composite stackedComposite = new Composite(composite, SWT.NONE);
        stackLayout = new CustomStackLayout(stackedComposite);
        stackedComposite.setLayout(stackLayout);
        stackedComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createBusinessVariableTableViewerComposite(stackedComposite, dbc);
        createDocumentTableViewerComposite(stackedComposite, dbc);
        createInputNameField(composite, dbc);
        createEditOrCreateOptionFields(composite, dbc);

        bindRadioButtonsToComposite(dbc);
        final MultiValidator multiValidator = new AvailableDataValidator(availableBusinessData,
                selectedDataObservable,
                availableDocuments,
                businessObjectStore);
        dbc.addValidationStatusProvider(multiValidator);
        WizardPageSupport.create(this, dbc);
        setControl(composite);
    }

    public void createRadioButtonComposite(final Composite parent, final DataBindingContext dbc) {
        final Composite radioButtonComposite = new Composite(parent, SWT.NONE);
        radioButtonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        radioButtonComposite.setLayout(GridLayoutFactory.swtDefaults().numColumns(2).create());
        businessVariableButton = new Button(radioButtonComposite, SWT.RADIO);
        businessVariableButton.setText(Messages.businessVariable);
        documentButton = new Button(radioButtonComposite, SWT.RADIO);
        documentButton.setText(Messages.document);
        selectionTypeObservable = new SelectObservableValue<Boolean>(Boolean.class);
        selectionTypeObservable.addOption(Boolean.TRUE, WidgetProperties.selection().observe(businessVariableButton));
        selectionTypeObservable.addOption(Boolean.FALSE, WidgetProperties.selection().observe(documentButton));

        if (availableBusinessData.isEmpty()) {
            setBusinessDataTypeSelected(false);
            businessVariableButton.setEnabled(false);
        }
        if (availableDocuments.isEmpty()) {
            setBusinessDataTypeSelected(true);
            documentButton.setEnabled(false);
        }
    }

    public void createBusinessVariableTableViewerComposite(final Composite parent, final DataBindingContext dbc) {
        businessVariableTableViewerComposite = new Composite(parent, SWT.NONE);
        businessVariableTableViewerComposite.setLayout(GridLayoutFactory.swtDefaults().create());
        businessVariableTableViewerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        final TableViewer businessDataTableViewer = new TableViewer(businessVariableTableViewerComposite,
                SWT.BORDER | SWT.SINGLE | SWT.NO_FOCUS | SWT.H_SCROLL
                        | SWT.V_SCROLL);
        businessDataTableViewer.getTable().setLayout(GridLayoutFactory.fillDefaults().create());
        businessDataTableViewer.getTable()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(200, 100).create());
        final ObservableListContentProvider contentProvider = new ObservableListContentProvider();
        businessDataTableViewer.setContentProvider(contentProvider);
        final IObservableSet knownElements = contentProvider.getKnownElements();
        final IObservableMap[] labelMaps = EMFObservables.observeMaps(knownElements,
                new EStructuralFeature[] { ProcessPackage.Literals.ELEMENT__NAME,
                        ProcessPackage.Literals.DATA__MULTIPLE,
                        ProcessPackage.Literals.JAVA_OBJECT_DATA__CLASS_NAME });
        businessDataTableViewer
                .setLabelProvider(new BusinessObjectDataStyledLabelProvider(businessObjectStore, labelMaps));
        businessDataTableViewer
                .setInput(new WritableList(availableBusinessData, ProcessPackage.Literals.BUSINESS_OBJECT_DATA));
        final IViewerObservableValue observeSingleSelection = ViewersObservables
                .observeSingleSelection(businessDataTableViewer);
        dbc.bindValue(observeSingleSelection, selectedDataObservable);
        businessVariableButton.addSelectionListener(createBusinessVariableSelectionAdapter());
    }

    protected SelectionAdapter createBusinessVariableSelectionAdapter() {
        return new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (!availableBusinessData.isEmpty()) {
                    setBusinessDataTypeSelected(true);
                    selectedDataObservable.setValue(availableBusinessData.get(0));
                }
            }
        };
    }

    public void createDocumentTableViewerComposite(final Composite parent, final DataBindingContext dbc) {
        documentTableViewerComposite = new Composite(parent, SWT.NONE);
        documentTableViewerComposite.setLayout(GridLayoutFactory.swtDefaults().create());
        documentTableViewerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        final TableViewer documentTableViewer = new TableViewer(documentTableViewerComposite,
                SWT.BORDER | SWT.SINGLE | SWT.NO_FOCUS | SWT.H_SCROLL
                        | SWT.V_SCROLL);
        documentTableViewer.getTable()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(200, 100).create());
        final ObservableListContentProvider contentProvider = new ObservableListContentProvider();
        documentTableViewer.setContentProvider(contentProvider);
        documentTableViewer.setLabelProvider(new DocumentStyledLabelProvider());
        documentTableViewer.setInput(new WritableList(availableDocuments, ProcessPackage.Literals.DOCUMENT));
        final IViewerObservableValue observeSingleSelection = ViewersObservables
                .observeSingleSelection(documentTableViewer);
        dbc.bindValue(observeSingleSelection, selectedDataObservable,
                updateValueStrategy().withValidator(createDefaultValueAlreadyDefinedValidator()).create(), null);
        documentButton.addSelectionListener(createDocumentSelectionAdapter());
    }

    protected SelectionAdapter createDocumentSelectionAdapter() {
        return new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (!availableDocuments.isEmpty()) {
                    setBusinessDataTypeSelected(false);
                    selectedDataObservable.setValue(availableDocuments.get(0));
                }
            }
        };
    }

    protected IValidator createDefaultValueAlreadyDefinedValidator() {
        return new IValidator() {

            @Override
            public IStatus validate(final Object value) {
                if (value instanceof Document && contract.eContainer() instanceof Pool) {
                    final Document document = (Document) value;
                    return DocumentType.NONE.equals(document.getDocumentType()) ? Status.OK_STATUS
                            : ValidationStatus
                                    .warning(Messages.bind(Messages.defaultValueAlreadyDefinedWarning,
                                            document.getName()));
                }
                return Status.OK_STATUS;
            }
        };
    }

    private void bindRadioButtonsToComposite(final DataBindingContext dbc) {
        dbc.bindValue(PojoProperties.value("topControl").observe(stackLayout), selectionTypeObservable,
                neverUpdateValueStrategy().create(),
                updateValueStrategy()
                        .withConverter(dataTypeSelectionToCompositeConverter()).create());
        dbc.bindValue(selectionTypeObservable, PojoProperties.value("businessDataTypeSelected").observe(this));
    }

    private IConverter dataTypeSelectionToCompositeConverter() {

        return new Converter(Boolean.class, Composite.class) {

            @Override
            public Object convert(final Object fromObject) {
                return fromObject != null && (Boolean) fromObject ? businessVariableTableViewerComposite
                        : documentTableViewerComposite;
            }
        };
    }

    private void createInputNameField(final Composite parent, final DataBindingContext dbc) {
        final Composite documentInputNameComposite = new Composite(parent, SWT.NONE);
        documentInputNameComposite.setLayout(GridLayoutFactory.swtDefaults().numColumns(3).create());
        documentInputNameComposite.setLayoutData(GridDataFactory.fillDefaults().create());

        final Label documentInputNameLabel = new Label(documentInputNameComposite, SWT.NONE);
        documentInputNameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        documentInputNameLabel.setText(Messages.rootContractInputName);
        final Text inputNameText = createContractInputNameText(documentInputNameComposite);
        inputNameText.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).hint(400, SWT.DEFAULT).create());
        final Label dataTypeLabel = new Label(documentInputNameComposite, SWT.NONE);
        final IObservableValue<String> prefixObservable = PojoProperties.value("rootName", String.class).observe(this);
        dbc.bindValue(prefixObservable,
                EMFObservables.observeDetailValue(Realm.getDefault(), selectedDataObservable,
                        ProcessPackage.Literals.ELEMENT__NAME),
                neverUpdateValueStrategy().create(),
                updateValueStrategy().withConverter(documentToRootContractInputName())
                        .create());
        dbc.bindValue(WidgetProperties.text(SWT.Modify).observe(inputNameText),
                prefixObservable, updateValueStrategy()
                        .withValidator(inputNameValidator())
                        .create(),
                null);
        dbc.bindValue(rootNameObservable, prefixObservable);
        dbc.bindValue(WidgetProperties.text().observe(dataTypeLabel), selectionTypeObservable,
                neverUpdateValueStrategy().create(),
                updateValueStrategy().withConverter(createSelectionTypeToLabelTextConverter()).create());

        dbc.addValidationStatusProvider(new MultiValidator() {

            @Override
            protected IStatus validate() {
                final Object value = prefixObservable.getValue();
                return inputNameValidator().validate(value);
            }
        });
    }

    protected Text createContractInputNameText(final Composite documentInputNameComposite) {
        return new Text(documentInputNameComposite, SWT.BORDER);
    }

    protected IValidator inputNameValidator() {
        return multiValidator()
                .addValidator(mandatoryValidator(Messages.rootContractInputName))
                .addValidator(maxLengthValidator(Messages.rootContractInputName, INPUT_NAME_MAX_LENGTH))
                .addValidator(groovyReferenceValidator(Messages.rootContractInputName).startsWithLowerCase())
                .addValidator(uniqueValidator().onProperty("name").in(contract.getInputs())).create();
    }

    private void createEditOrCreateOptionFields(final Composite parent, final DataBindingContext dbc) {
        final Composite radioButtonComposite = new Composite(parent, SWT.NONE);
        radioButtonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        radioButtonComposite.setLayout(GridLayoutFactory.swtDefaults().numColumns(1).create());
        Button createButton = new Button(radioButtonComposite, SWT.RADIO);
        createButton.setText(Messages.createDataFromContractChoice);
        Button editButton = new Button(radioButtonComposite, SWT.RADIO);
        editButton.setText(Messages.editDataFromContractChoice);
        SelectObservableValue creationTypeObservable = new SelectObservableValue<EditMode>(EditMode.class);
        creationTypeObservable.addOption(EditMode.CREATE, WidgetProperties.selection().observe(createButton));
        creationTypeObservable.addOption(EditMode.EDIT, WidgetProperties.selection().observe(editButton));
        dbc.bindValue(WidgetProperties.enabled().observe(editButton),
                selectionTypeObservable,
                null,
                updateValueStrategy().withConverter(newConverter()
                        .withConvertFunction(isBusinessDataTypeSelected -> (isBusinessDataTypeSelected !=null && (boolean) isBusinessDataTypeSelected)
                                && (contract.eContainer() instanceof Task))
                        .create()).create());
        selectionTypeObservable.addValueChangeListener(event -> { if ( !event.diff.getNewValue() ) { createButton.setSelection(true);editButton.setSelection(false);}} );
        generateOptions.setEditMode(contract.eContainer() instanceof Task ? EditMode.EDIT : EditMode.CREATE);
        dbc.bindValue(creationTypeObservable, generateOptions.getEditModeObservable());
    }

    protected IConverter createSelectionTypeToLabelTextConverter() {

        return new Converter(Boolean.class, String.class) {

            @Override
            public Object convert(final Object fromObject) {
                final Boolean isBusinessDataType = fromObject != null && (Boolean) fromObject;
                if (isBusinessDataType) {
                    return Messages.inputOfType;
                } else {
                    return Messages.fileInputType;
                }
            }
        };
    }

    private IConverter documentToRootContractInputName() {
        return new Converter(String.class, String.class) {

            @Override
            public Object convert(final Object fromObject) {
                if (fromObject == null) {
                    return null;
                }
                final String name = selectedDataObservable.getValue() instanceof Document ? fromObject + DOC_INPUT
                        : fromObject + INPUT;
                return NamingUtils.generateNewName(newHashSet(transform(contract.getInputs(), toContactInputName())),
                        name,
                        0);
            }
        };
    }

    private Function<ContractInput, String> toContactInputName() {
        return new Function<ContractInput, String>() {

            @Override
            public String apply(final ContractInput input) {
                return input.getName();
            }
        };
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.WizardPage#canFlipToNextPage()
     */
    @Override
    public boolean canFlipToNextPage() {
        final Object data = selectedDataObservable.getValue();
        if (data == null || data instanceof Document) {
            return false;
        }
        return super.canFlipToNextPage();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
     */
    @Override
    public boolean isPageComplete() {
        if (availableBusinessData.isEmpty() && isBusinessDataTypeSelected()
                || availableDocuments.isEmpty() && !isBusinessDataTypeSelected()) {
            return false;
        }
        if (selectedDataObservable.getValue() instanceof BusinessObjectData) {
            return isNoBusinessDataSelected() ? false : super.isPageComplete();
        }
        if (selectedDataObservable.getValue() instanceof Document) {
            return super.isPageComplete();
        }
        return super.isPageComplete();
    }

    protected boolean isNoBusinessDataSelected() {
        return availableBusinessData.isEmpty() || selectedDataObservable.getValue() == null;
    }

    /**
     * @return the rootName
     */
    public String getRootName() {
        return rootName;
    }

    /**
     * @param rootName the rootName to set
     */
    public void setRootName(final String rootName) {
        this.rootName = rootName;
    }

    /**
     * @return the businessDataTypeSelected
     */
    public boolean isBusinessDataTypeSelected() {
        return businessDataTypeSelected;
    }

    /**
     * @param businessDataTypeSelected the businessDataTypeSelected to set
     */
    public void setBusinessDataTypeSelected(final boolean businessDataTypeSelected) {
        this.businessDataTypeSelected = businessDataTypeSelected;
    }

}
