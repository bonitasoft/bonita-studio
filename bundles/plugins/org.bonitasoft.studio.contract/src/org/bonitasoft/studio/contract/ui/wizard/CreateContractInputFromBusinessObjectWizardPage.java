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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.contract.UngenerateAggregatedReferenceChildren;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMappingFactory;
import org.bonitasoft.studio.contract.core.mapping.RemoveAggregateReferencesChildren;
import org.bonitasoft.studio.contract.core.mapping.UnselectLazyReferencesInMultipleContainer;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.contract.ui.wizard.GenerationOptions.EditMode;
import org.bonitasoft.studio.contract.ui.wizard.edit.ContractInputTypeEditingSupport;
import org.bonitasoft.studio.contract.ui.wizard.labelProvider.FieldNameColumnLabelProvider;
import org.bonitasoft.studio.contract.ui.wizard.labelProvider.FieldTypeColumnLabelProvider;
import org.bonitasoft.studio.contract.ui.wizard.labelProvider.InputTypeColumnLabelProvider;
import org.bonitasoft.studio.contract.ui.wizard.labelProvider.MandatoryColumnLabelProvider;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.preferences.browser.OpenBrowserOperation;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.IViewerObservableSet;
import org.eclipse.jface.databinding.viewers.ObservableListTreeContentProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;

public class CreateContractInputFromBusinessObjectWizardPage extends WizardPage {

    private static final int DEFAULT_BUTTON_WIDTH_HINT = 200;
    private static final String FORM_GENERATION_REDIRECT_ID = "685";
    private final WritableValue<BusinessObjectData> selectedDataObservable;
    private CheckboxTreeViewer treeViewer;
    private final FieldToContractInputMappingFactory fieldToContractInputMappingFactory;
    private List<FieldToContractInputMapping> mappings;
    private String rootName;
    private final Contract contract;
    private final BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> businessObjectStore;
    private final GenerationOptions generationOptions;
    private SelectObservableValue<Boolean> actionObservable;
    private final WritableList<FieldToContractInputMapping> fieldToContractInputMappingsObservable;
    private Button deselectAll;
    private Button selectMandatories;
    private Button selectAll;
    private EmptySelectionMultivalidator multiValidator;
    private ContractContainer contractContainer;
    private UnselectLazyReferencesInMultipleContainer lazyFieldStatusProvider;

    protected CreateContractInputFromBusinessObjectWizardPage(ContractContainer contractContainer,
            GenerationOptions generationOptions,
            WritableValue<BusinessObjectData> selectedDataObservable,
            FieldToContractInputMappingFactory fieldToContractInputMappingFactory,
            WritableList<FieldToContractInputMapping> fieldToContractInputMappingsObservable,
            BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> businessObjectStore) {
        super(CreateContractInputFromBusinessObjectWizardPage.class.getName());
        setDescription(Messages.selectFieldToGenerateDescription);
        this.selectedDataObservable = selectedDataObservable;
        this.fieldToContractInputMappingFactory = fieldToContractInputMappingFactory;
        this.contractContainer = contractContainer;
        this.contract = contractContainer.getContract();
        this.generationOptions = generationOptions;
        this.businessObjectStore = businessObjectStore;
        this.fieldToContractInputMappingsObservable = fieldToContractInputMappingsObservable;
        mappings = new ArrayList<>();
    }

    public void setTitle() {
        if (selectedDataObservable.getValue() != null) {
            setTitle(Messages.bind(Messages.selectFieldToGenerateTitle,
                    ((Element) selectedDataObservable.getValue()).getName()));
            EMFObservables
                    .observeDetailValue(Realm.getDefault(), selectedDataObservable,
                            ProcessPackage.Literals.ELEMENT__NAME)
                    .addValueChangeListener(
                            new IValueChangeListener() {

                                @Override
                                public void handleValueChange(final ValueChangeEvent event) {
                                    setTitle(Messages.bind(Messages.selectFieldToGenerateTitle,
                                            event.diff.getNewValue()));
                                }
                            });
        }
    };

    @Override
    public void createControl(final Composite parent) {
        final EMFDataBindingContext dbc = new EMFDataBindingContext();
        WizardPageSupport.create(this, dbc);
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(10, 10).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createProcessDataMappingTreeViewer(composite, dbc);
        createReminderText(dbc, composite);
        setControl(composite);
    }

    private void openBrowser(String redirectId) {
        try {
            new OpenBrowserOperation(new URL(String.format(
                    "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=%s&bos_redirect_product=bos&bos_redirect_major_version=%s",
                    redirectId,
                    ProductVersion.majorVersion()))).execute();
        } catch (MalformedURLException e) {
            BonitaStudioLog.error(e);
        }
    }

    private void createReminderText(final EMFDataBindingContext dbc, final Composite composite) {
        final CLabel reminder = new CLabel(composite, SWT.NONE);
        final Display d = Display.getCurrent();
        final Image img = d.getSystemImage(SWT.ICON_WARNING);
        reminder.setImage(img);
        reminder.setLayoutData(GridDataFactory.fillDefaults().hint(600, SWT.DEFAULT).create());
        final Button autoGeneratedOperationButton = new Button(composite, SWT.RADIO);
        final Button manuallyDefinedOperationButton = new Button(composite, SWT.RADIO);
        actionObservable = new SelectObservableValue<>(Boolean.class);
        actionObservable.addOption(Boolean.TRUE, WidgetProperties.selection().observe(autoGeneratedOperationButton));
        actionObservable.addOption(Boolean.FALSE, WidgetProperties.selection().observe(manuallyDefinedOperationButton));
        if (contract.eContainer() instanceof Task) {
            reminder.setText(Messages.reminderForStepMessage);
            autoGeneratedOperationButton.setText(Messages.autoGeneratedOperationStepButton);
            manuallyDefinedOperationButton.setText(Messages.manuallyDefinedOperationStepButton);
        } else {
            reminder.setText(Messages.reminderForProcessMessage);
            autoGeneratedOperationButton.setText(Messages.autoGeneratedOperationProcessButton);
            manuallyDefinedOperationButton.setText(Messages.manuallyDefinedOperationProcessButton);
        }
        dbc.bindValue(actionObservable, generationOptions.getAutoGeneratedScriptObservable());
    }

    public void disableAutoGeneration() {
        actionObservable.setValue(false);
    }

    private void createProcessDataMappingTreeViewer(Composite composite, EMFDataBindingContext dbc) {
        final Composite viewerComposite = new Composite(composite, SWT.NONE);
        viewerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        viewerComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(15, 15).create());
        createButtonComposite(viewerComposite);
        treeViewer = new CheckboxTreeViewer(viewerComposite,
                SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
        treeViewer.getTree()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 200).create());
        treeViewer.getTree().setHeaderVisible(true);
        treeViewer.addFilter(hidePersistenceIdMapping());
        final FieldToContractInputMappingViewerCheckStateManager manager = new FieldToContractInputMappingViewerCheckStateManager();
        treeViewer.addCheckStateListener(manager);
        treeViewer.setCheckStateProvider(manager);
        final ObservableListTreeContentProvider provider = new ObservableListTreeContentProvider(
                new FieldToContractInputMappingObservableFactory(),
                new FieldToContractInputMappingTreeStructureAdvisor());
        treeViewer.setContentProvider(provider);

        final TreeViewerColumn nameTreeViewerColumn = new TreeViewerColumn(treeViewer, SWT.FILL);
        nameTreeViewerColumn.getColumn().setText(Messages.attributeName);
        nameTreeViewerColumn.getColumn().setWidth(250);
        lazyFieldStatusProvider = new UnselectLazyReferencesInMultipleContainer();
        nameTreeViewerColumn.setLabelProvider(new FieldNameColumnLabelProvider(lazyFieldStatusProvider));

        final TreeViewerColumn typeTreeViewerColumn = new TreeViewerColumn(treeViewer, SWT.FILL);
        typeTreeViewerColumn.getColumn().setText(Messages.attributetype);
        typeTreeViewerColumn.getColumn().setWidth(150);
        typeTreeViewerColumn.setLabelProvider(new FieldTypeColumnLabelProvider());

        final TreeViewerColumn inputTypeTreeViewerColumn = new TreeViewerColumn(treeViewer, SWT.FILL);
        inputTypeTreeViewerColumn.getColumn().setText(Messages.inputType);
        inputTypeTreeViewerColumn.getColumn().setWidth(150);
        inputTypeTreeViewerColumn.setLabelProvider(new InputTypeColumnLabelProvider(contract));
        inputTypeTreeViewerColumn.setEditingSupport(new ContractInputTypeEditingSupport(treeViewer, contract));

        final TreeViewerColumn mandatoryTreeViewerColumn = new TreeViewerColumn(treeViewer, SWT.FILL);
        mandatoryTreeViewerColumn.getColumn().setText(Messages.mandatory);
        mandatoryTreeViewerColumn.getColumn().setWidth(80);
        mandatoryTreeViewerColumn.setLabelProvider(new MandatoryColumnLabelProvider());

        IViewerObservableSet checkedElements = ViewersObservables.observeCheckedElements(treeViewer,
                FieldToContractInputMapping.class);

        final IObservableValue<FieldToContractInputMapping> observeInput = ViewersObservables.observeInput(treeViewer);
        dbc.bindValue(observeInput,
                selectedDataObservable,
                null,
                UpdateStrategyFactory.updateValueStrategy().withConverter(selectedDataToFieldMappings()).create());

        generationOptions.getEditModeObservable().addValueChangeListener(event -> {
            if (selectedDataObservable.getValue() instanceof BusinessObjectData) {
                createMapping(selectedDataObservable.getValue());
                treeViewer.setInput(mappings);
            }
        });

        createButtonListeners(checkedElements);

        multiValidator = new EmptySelectionMultivalidator(selectedDataObservable, checkedElements, mappings,
                contract.eContainer(), generationOptions.getEditModeObservable());
        dbc.addValidationStatusProvider(multiValidator);

        new Label(viewerComposite, SWT.NONE); //FILLER

        Link formGenerationDocLink = new Link(viewerComposite, SWT.NONE);
        formGenerationDocLink.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        formGenerationDocLink.setText(Messages.moreInfoFormGenerationLink);
        formGenerationDocLink.addListener(SWT.Selection, event -> openBrowser(FORM_GENERATION_REDIRECT_ID));

        ColumnViewerToolTipSupport.enableFor(treeViewer);
    }

    protected void createButtonComposite(final Composite viewerComposite) {
        final Composite buttonsComposite = new Composite(viewerComposite, SWT.NONE);
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 3).create());
        selectAll = new Button(buttonsComposite, SWT.FLAT);
        selectAll.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create());
        selectAll.setText(Messages.selectAll);
        deselectAll = new Button(buttonsComposite, SWT.FLAT);
        deselectAll.setText(Messages.deselectAll);
        deselectAll.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create());
        selectMandatories = new Button(buttonsComposite, SWT.FLAT);
        selectMandatories.setText(Messages.selectMandatories);
        selectMandatories.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create());
    }

    protected void createButtonListeners(final IObservableSet checkedElements) {
        selectAll.addSelectionListener(createSelectAllListener(checkedElements));
        deselectAll.addSelectionListener(createDeselectAllListener(checkedElements));
        selectMandatories.addSelectionListener(createMandatoryAttributesSelectionListener(checkedElements));
    }

    protected SelectionAdapter createMandatoryAttributesSelectionListener(final IObservableSet checkedElements) {
        return new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                BusyIndicator.showWhile(Display.getDefault(), () -> {
                    checkedElements.clear();
                    List<FieldToContractInputMapping> selectedMappings = new ArrayList<>();
                    generateMandatoryAttributes(selectedMappings, mappings);
                    checkedElements.addAll(selectedMappings);
                });

            }

            protected void generateMandatoryAttributes(List<FieldToContractInputMapping> selectedMappings,
                    final List<FieldToContractInputMapping> mappingList) {
                for (final FieldToContractInputMapping mapping : mappingList) {
                    if (!mapping.getField().isNullable()) {
                        selectedMappings.add(mapping);
                        mapping.setGenerated(true);
                        generateMandatoryAttributes(selectedMappings, mapping.getChildren());
                    } else if (mapping.isGenerated()) {
                        mapping.setGenerated(false);
                        doNotGenerateAnyMapping(mapping.getChildren());
                    }
                }
            }
        };
    }

    protected SelectionAdapter createDeselectAllListener(final IObservableSet checkElements) {
        return new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                checkElements.clear();
                doNotGenerateAnyMapping(mappings);
            }
        };
    }

    protected SelectionAdapter createSelectAllListener(final IObservableSet checkElements) {
        return new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                BusyIndicator.showWhile(Display.getDefault(), new Runnable() {

                    @Override
                    public void run() {
                        checkElements.clear();
                        final List<FieldToContractInputMapping> selectedMappings = new ArrayList<>();
                        checkAllMappings(selectedMappings, mappings);
                        checkElements.addAll(selectedMappings);
                        generateAllMappings(mappings);
                    }

                });

            }
        };
    }

    private ViewerFilter hidePersistenceIdMapping() {
        return new ViewerFilter() {

            @Override
            public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                return !Objects.equals(FieldToContractInputMappingFactory.PERSISTENCE_ID_STRING_FIELD_NAME,
                        ((FieldToContractInputMapping) element).getField().getName());
            }
        };
    }

    private IConverter selectedDataToFieldMappings() {
        return ConverterBuilder.<Object, List> newConverter()
                .fromType(Object.class)
                .toType(List.class)
                .withConvertFunction(data -> {
                    if (data == null || !(data instanceof BusinessObjectData)) {
                        return Collections.emptyList();
                    }
                    createMapping((BusinessObjectData) data);
                    return mappings;
                })
                .create();
    }

    private void createMapping(BusinessObjectData data) {
        mappings = fieldToContractInputMappingFactory.createMappingForBusinessObjectType(contractContainer, data);
        if (generationOptions.getEditMode() == EditMode.EDIT) {
            lazyFieldStatusProvider.apply(mappings, data);
            new UngenerateAggregatedReferenceChildren().apply(mappings);
        } else if (generationOptions.getEditMode() == EditMode.CREATE) {
            new RemoveAggregateReferencesChildren().apply(mappings);
        }
        fieldToContractInputMappingsObservable.clear();
        fieldToContractInputMappingsObservable.addAll(mappings);
        if (multiValidator != null) {
            multiValidator.setMappings(mappings);
        }
    }

    @Override
    public boolean canFlipToNextPage() {
        if (contract.eContainer() instanceof Pool) {
            return generationOptions.isAutoGeneratedScript() && super.canFlipToNextPage();
        }
        return super.canFlipToNextPage();
    }

    public List<FieldToContractInputMapping> getMappings() {
        return mappings;
    }

    public void setMappings(final List<FieldToContractInputMapping> mappings) {
        this.mappings = mappings;
    }

    public String getRootName() {
        return rootName;
    }

    public void setRootName(final String rootName) {
        this.rootName = rootName;
    }

    public void generateAllMappings(final List<FieldToContractInputMapping> mappingList) {
        for (final FieldToContractInputMapping mapping : mappingList) {
            mapping.setGenerated(true);
            generateAllMappings(mapping.getChildren());
        }
    }

    public void doNotGenerateAnyMapping(final List<FieldToContractInputMapping> mappingList) {
        for (final FieldToContractInputMapping mapping : mappingList) {
            mapping.setGenerated(false);
            doNotGenerateAnyMapping(mapping.getChildren());
        }
    }

    public void checkAllMappings(List<FieldToContractInputMapping> selectedMappings,
            final List<FieldToContractInputMapping> mappingList) {
        for (final FieldToContractInputMapping mapping : mappingList) {
            selectedMappings.add(mapping);
            checkAllMappings(selectedMappings, mapping.getChildren());
        }
    }
}
