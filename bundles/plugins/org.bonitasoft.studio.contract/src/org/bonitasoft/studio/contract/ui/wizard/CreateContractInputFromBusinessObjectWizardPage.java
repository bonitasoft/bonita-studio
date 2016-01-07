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

import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMappingFactory;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.contract.ui.wizard.labelProvider.FieldNameColumnLabelProvider;
import org.bonitasoft.studio.contract.ui.wizard.labelProvider.FieldTypeColumnLabelProvider;
import org.bonitasoft.studio.contract.ui.wizard.labelProvider.InputTypeColumnLabelProvider;
import org.bonitasoft.studio.contract.ui.wizard.labelProvider.MandatoryColumnLabelProvider;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.conversion.Converter;
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
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.IViewerObservableSet;
import org.eclipse.jface.databinding.viewers.ObservableListTreeContentProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * @author aurelie
 */
public class CreateContractInputFromBusinessObjectWizardPage extends WizardPage {

    private static final int DEFAULT_BUTTON_WIDTH_HINT = 200;
    private final WritableValue selectedDataObservable;
    private CheckboxTreeViewer treeViewer;
    private final FieldToContractInputMappingFactory fieldToContractInputMappingFactory;
    private List<FieldToContractInputMapping> mappings;
    private String rootName;
    private final Contract contract;
    private final BusinessObjectModelRepositoryStore businessObjectStore;
    private final GenerationOptions generationOptions;
    private SelectObservableValue actionObservable;
    private final WritableList fieldToContractInputMappingsObservable;
    private Button deselectAll;
    private Button selectMandatories;
    private Button selectAll;
    private EmptySelectionMultivalidator multiValidator;

    protected CreateContractInputFromBusinessObjectWizardPage(final Contract contract,
            final GenerationOptions generationOptions,
            final WritableValue selectedDataObservable,
            final FieldToContractInputMappingFactory fieldToContractInputMappingFactory,
            final WritableList fieldToContractInputMappingsObservable, final BusinessObjectModelRepositoryStore businessObjectStore) {
        super(CreateContractInputFromBusinessObjectWizardPage.class.getName());
        setDescription(Messages.selectFieldToGenerateDescription);
        this.selectedDataObservable = selectedDataObservable;
        this.fieldToContractInputMappingFactory = fieldToContractInputMappingFactory;
        this.contract = contract;
        this.generationOptions = generationOptions;
        this.businessObjectStore = businessObjectStore;
        this.fieldToContractInputMappingsObservable = fieldToContractInputMappingsObservable;
        mappings = new ArrayList<FieldToContractInputMapping>();
    }

    public void setTitle() {
        if (selectedDataObservable.getValue() != null) {
            setTitle(Messages.bind(Messages.selectFieldToGenerateTitle, ((Element) selectedDataObservable.getValue()).getName()));
            EMFObservables.observeDetailValue(Realm.getDefault(), selectedDataObservable, ProcessPackage.Literals.ELEMENT__NAME).addValueChangeListener(
                    new IValueChangeListener() {

                        @Override
                        public void handleValueChange(final ValueChangeEvent event) {
                            setTitle(Messages.bind(Messages.selectFieldToGenerateTitle, event.diff.getNewValue()));
                        }
                    });
        }
    };

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
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

    private void createReminderText(final EMFDataBindingContext dbc, final Composite composite) {
        final CLabel reminder = new CLabel(composite, SWT.NONE);
        final Display d = Display.getCurrent();
        final Image img = d.getSystemImage(SWT.ICON_WARNING);
        reminder.setImage(img);
        reminder.setLayoutData(GridDataFactory.fillDefaults().hint(600, SWT.DEFAULT).create());
        final Button autoGeneratedOperationButton = new Button(composite, SWT.RADIO);
        final Button manuallyDefinedOperationButton = new Button(composite, SWT.RADIO);
        actionObservable = new SelectObservableValue(Boolean.class);
        actionObservable.addOption(Boolean.TRUE, SWTObservables.observeSelection(autoGeneratedOperationButton));
        actionObservable.addOption(Boolean.FALSE, SWTObservables.observeSelection(manuallyDefinedOperationButton));
        if (contract.eContainer() instanceof Task) {
            reminder.setText(Messages.reminderForStepMessage);
            autoGeneratedOperationButton.setText(Messages.autoGeneratedOperationStepButton);
            manuallyDefinedOperationButton.setText(Messages.manuallyDefinedOperationStepButton);
        } else {
            reminder.setText(Messages.reminderForProcessMessage);
            autoGeneratedOperationButton.setText(Messages.autoGeneratedOperationProcessButton);
            manuallyDefinedOperationButton.setText(Messages.manuallyDefinedOperationProcessButton);
        }
        dbc.bindValue(actionObservable, PojoObservables.observeValue(generationOptions, "autogeneratedScript"));
    }

    public void disableAutoGeneration() {
        actionObservable.setValue(false);
    }

    private void createProcessDataMappingTreeViewer(final Composite composite, final EMFDataBindingContext dbc) {
        final Composite viewerComposite = new Composite(composite, SWT.NONE);
        viewerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        viewerComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(15, 15).create());
        createButtonComposite(viewerComposite);
        treeViewer = new CheckboxTreeViewer(viewerComposite, SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
        treeViewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 200).create());
        treeViewer.getTree().setHeaderVisible(true);
        treeViewer.addFilter(hidePersistenceIdMapping());
        final FieldToContractInputMappingViewerCheckStateManager manager = new FieldToContractInputMappingViewerCheckStateManager();
        treeViewer.addCheckStateListener(manager);
        treeViewer.setCheckStateProvider(manager);
        final ObservableListTreeContentProvider provider = new ObservableListTreeContentProvider(new FieldToContractInputMappingObservableFactory(),
                new FieldToContractInputMappingTreeStructureAdvisor());
        treeViewer.setContentProvider(provider);

        final TreeViewerColumn nameTreeViewerColumn = new TreeViewerColumn(treeViewer, SWT.FILL);
        nameTreeViewerColumn.getColumn().setText(Messages.attributeName);
        nameTreeViewerColumn.getColumn().setWidth(250);
        nameTreeViewerColumn.setLabelProvider(new FieldNameColumnLabelProvider());

        final TreeViewerColumn typeTreeViewerColumn = new TreeViewerColumn(treeViewer, SWT.FILL);
        typeTreeViewerColumn.getColumn().setText(Messages.attributetype);
        typeTreeViewerColumn.getColumn().setWidth(150);
        typeTreeViewerColumn.setLabelProvider(new FieldTypeColumnLabelProvider());

        final TreeViewerColumn inputTypeTreeViewerColumn = new TreeViewerColumn(treeViewer, SWT.FILL);
        inputTypeTreeViewerColumn.getColumn().setText(Messages.inputType);
        inputTypeTreeViewerColumn.getColumn().setWidth(150);
        inputTypeTreeViewerColumn.setLabelProvider(new InputTypeColumnLabelProvider());

        final TreeViewerColumn mandatoryTreeViewerColumn = new TreeViewerColumn(treeViewer, SWT.FILL);
        mandatoryTreeViewerColumn.getColumn().setText(Messages.mandatory);
        mandatoryTreeViewerColumn.getColumn().setWidth(80);
        mandatoryTreeViewerColumn.setLabelProvider(new MandatoryColumnLabelProvider());

        final IViewerObservableSet checkedElements = ViewersObservables.observeCheckedElements(treeViewer, FieldToContractInputMapping.class);
        final IObservableValue observeInput = ViewersObservables.observeInput(treeViewer);
        dbc.bindValue(observeInput,
                selectedDataObservable,
                null,
                updateValueStrategy().withConverter(selectedDataToFieldMappings()).create());
        createButtonListeners(checkedElements);
        final WritableValue checkedObservableValue = new WritableValue();
        checkedObservableValue.setValue(checkedElements);
        final WritableValue mappingsObservableValue = new WritableValue();
        mappingsObservableValue.setValue(fieldToContractInputMappingsObservable);
        multiValidator = new EmptySelectionMultivalidator(selectedDataObservable, checkedElements, mappings, contract.eContainer());
        dbc.addValidationStatusProvider(multiValidator);
        dbc.bindValue(checkedObservableValue, observeInput,
                updateValueStrategy().withConverter(createMappingsToCheckedElementsConverter(mappingsObservableValue)).create(), updateValueStrategy()
                        .withConverter(createCheckedElementsToMappingsConverter(checkedElements)).create());
    }

    protected Converter createMappingsToCheckedElementsConverter(final WritableValue mappingsObservableValue) {
        return new Converter(IObservableSet.class, WritableList.class) {

            @Override
            public Object convert(final Object fromObject) {
                final IObservableSet set = (IObservableSet) fromObject;
                for (final FieldToContractInputMapping mapping : mappings) {
                    mapping.setGenerated(set.contains(mapping));
                }
                return mappingsObservableValue;
            }
        };
    }

    protected Converter createCheckedElementsToMappingsConverter(final IObservableSet checkedElements) {
        return new Converter(WritableList.class, IObservableSet.class) {

            @Override
            public Object convert(final Object fromObject) {
                checkedElements.clear();
                for (final FieldToContractInputMapping mapping : mappings) {
                    if (mapping.isGenerated()) {
                        checkedElements.add(mapping);
                    }
                }
                return checkedElements;
            }
        };
    }

    protected void createButtonComposite(final Composite viewerComposite) {
        final Composite buttonsComposite = new Composite(viewerComposite, SWT.NONE);
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 3).create());
        selectAll = new Button(buttonsComposite, SWT.FLAT);
        selectAll.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create());
        selectAll.setText(Messages.selectAll);
        deselectAll = new Button(buttonsComposite, SWT.FLAT);
        deselectAll.setText(Messages.deselectAll);
        deselectAll.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create());
        selectMandatories = new Button(buttonsComposite, SWT.FLAT);
        selectMandatories.setText(Messages.selectMandatories);
        selectMandatories.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create());
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
                checkMandatoryAttributes(checkedElements, mappings);
            }

            protected void checkMandatoryAttributes(final IObservableSet checkedElements, final List<FieldToContractInputMapping> mappings) {
                for (final FieldToContractInputMapping mapping : mappings) {
                    final List<FieldToContractInputMapping> mappingChildren = mapping.getChildren();
                    if (!mapping.getField().isNullable() && !mapping.isGenerated()) {
                        checkedElements.add(mapping);
                        mapping.setGenerated(true);
                        checkAllMappings(checkedElements, mappingChildren);
                        generateAllMappings(mappingChildren, true);
                    }
                    if (mapping.isGenerated()) {
                        checkMandatoryAttributes(checkedElements, mappingChildren);
                        checkMandatoryAttributes(checkedElements, mappingChildren);
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
                generateAllMappings(mappings, false);
            }
        };
    }

    protected SelectionAdapter createSelectAllListener(final IObservableSet checkElements) {
        return new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                checkElements.clear();
                checkAllMappings(checkElements, mappings);
                generateAllMappings(mappings, true);
            }
        };
    }

    private ViewerFilter hidePersistenceIdMapping() {
        return new ViewerFilter() {

            @Override
            public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                return !Objects.equals(Field.PERSISTENCE_ID, ((FieldToContractInputMapping) element).getField().getName());
            }
        };
    }

    private IConverter selectedDataToFieldMappings() {
        return new Converter(BusinessObjectData.class, List.class) {

            @Override
            public Object convert(final Object selectedData) {
                if (selectedData == null || !(selectedData instanceof BusinessObjectData)) {
                    return Collections.emptyList();
                }
                mappings = fieldToContractInputMappingFactory.createMappingForBusinessObjectType(toBusinessObject((BusinessObjectData) selectedData));
                fieldToContractInputMappingsObservable.clear();
                fieldToContractInputMappingsObservable.addAll(mappings);
                if (multiValidator != null) {
                    multiValidator.setMappings(mappings);
                }
                return mappings;
            }
        };
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.WizardPage#canFlipToNextPage()
     */
    @Override
    public boolean canFlipToNextPage() {
        if (contract.eContainer() instanceof Pool) {
            return generationOptions.isAutogeneratedScript() && super.canFlipToNextPage();
        }
        return super.canFlipToNextPage();
    }

    private BusinessObject toBusinessObject(final BusinessObjectData selectedData) {
        return businessObjectStore.getBusinessObjectByQualifiedName(selectedData.getClassName());
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

    public void generateAllMappings(final List<FieldToContractInputMapping> mappingList, final boolean state) {
        for (final FieldToContractInputMapping mapping : mappingList) {
            if (!Field.PERSISTENCE_ID.equals(mapping.getField().getName())) {
                mapping.setGenerated(state);
                generateAllMappings(mapping.getChildren(), state);
            }
        }
    }

    public void checkAllMappings(final IObservableSet checkedElements, final List<FieldToContractInputMapping> mappingList) {
        for (final FieldToContractInputMapping mapping : mappingList) {
            checkedElements.add(mapping);
            checkAllMappings(checkedElements, mapping.getChildren());
        }
    }
}
