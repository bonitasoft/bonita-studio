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
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.uniqueValidator;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMappingFactory;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.contract.ui.wizard.labelProvider.FieldNameColumnLabelProvider;
import org.bonitasoft.studio.contract.ui.wizard.labelProvider.FieldTypeColumnLabelProvider;
import org.bonitasoft.studio.contract.ui.wizard.labelProvider.InputTypeColumnLabelProvider;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.google.common.base.Function;

/**
 * @author aurelie
 */
public class CreateContractInputFromBusinessObjectWizardPage extends WizardPage {

    private final WritableValue selectedDataObservable;
    private CheckboxTreeViewer treeViewer;
    private final FieldToContractInputMappingFactory fieldToContractInputMappingFactory;
    private List<FieldToContractInputMapping> mappings;

    private String rootName;
    private final Contract contract;
    private final BusinessObjectModelRepositoryStore businessObjectStore;
    private final GenerationOptions generationOptions;
    private SelectObservableValue actionObservable;
    private final WritableValue rootNameObservable;
    private final WritableList fieldToContractInputMappingsObservable;

    protected CreateContractInputFromBusinessObjectWizardPage(final Contract contract,
            final GenerationOptions generationOptions,
            final WritableValue selectedDataObservable,
            final WritableValue rootNameObservable, final FieldToContractInputMappingFactory fieldToContractInputMappingFactory,
            final WritableList fieldToContractInputMappingsObservable, final BusinessObjectModelRepositoryStore businessObjectStore) {
        super(CreateContractInputFromBusinessObjectWizardPage.class.getName());
        setDescription(Messages.selectFieldToGenerateDescription);
        this.selectedDataObservable = selectedDataObservable;
        this.fieldToContractInputMappingFactory = fieldToContractInputMappingFactory;
        this.contract = contract;
        this.generationOptions = generationOptions;
        this.businessObjectStore = businessObjectStore;
        this.rootNameObservable = rootNameObservable;
        this.fieldToContractInputMappingsObservable = fieldToContractInputMappingsObservable;

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
        createRootNameControl(dbc, composite);
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

    private void createRootNameControl(final EMFDataBindingContext dbc, final Composite composite) {
        final Composite rootNameComposite = new Composite(composite, SWT.NONE);
        rootNameComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(10, 10).create());
        rootNameComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        final Label label = new Label(rootNameComposite, SWT.NONE);
        label.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        label.setText(Messages.rootContractInputName);
        final Text prefixText = new Text(rootNameComposite, SWT.BORDER);
        prefixText.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        final Label typeLabel = new Label(rootNameComposite, SWT.NONE);
        typeLabel.setText(Messages.inputOfType);
        final IObservableValue prefixObservable = PojoObservables.observeValue(this, "rootName");

        dbc.bindValue(prefixObservable,
                EMFObservables.observeDetailValue(Realm.getDefault(), selectedDataObservable, ProcessPackage.Literals.ELEMENT__NAME),
                neverUpdateValueStrategy().create(), updateValueStrategy().withConverter(dataNameToRootContractInputName()).create());
        dbc.bindValue(SWTObservables.observeText(prefixText, SWT.Modify),
                prefixObservable, updateValueStrategy().withValidator(uniqueValidator().onProperty("name").in(contract.getInputs())).create(),
                null);
        dbc.bindValue(rootNameObservable, prefixObservable);
    }

    private IConverter dataNameToRootContractInputName() {
        return new Converter(String.class, String.class) {

            @Override
            public Object convert(final Object fromObject) {
                final String className = (String) EMFObservables.observeDetailValue(Realm.getDefault(), selectedDataObservable,
                        ProcessPackage.Literals.JAVA_OBJECT_DATA__CLASS_NAME).getValue();
                if (className != null) {
                    final String name = fromObject + "Input";
                    return NamingUtils.generateNewName(newHashSet(transform(contract.getInputs(), toContactInputName())), name, 0);
                }
                return "";
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

    private void createProcessDataMappingTreeViewer(final Composite composite, final EMFDataBindingContext dbc) {
        treeViewer = new CheckboxTreeViewer(composite, SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL);

        treeViewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 200).create());
        treeViewer.getTree().setHeaderVisible(true);
        treeViewer.addFilter(hidePersistenceIdMapping());
        final FieldToContractInputMappingViewerCheckStateManager manager = new FieldToContractInputMappingViewerCheckStateManager();
        treeViewer.addCheckStateListener(manager);
        treeViewer.setCheckStateProvider(manager);
        treeViewer.setContentProvider(new ObservableListTreeContentProvider(new FieldToContractInputMappingObservableFactory(),
                new FieldToContractInputMappingTreeStructureAdvisor()));

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

        dbc.bindValue(ViewersObservables.observeInput(treeViewer),
                selectedDataObservable,
                null,
                updateValueStrategy().withConverter(selectedDataToFieldMappings()).create());
        dbc.addValidationStatusProvider(new MultiValidator() {

            @Override
            protected IStatus validate() {
                final IViewerObservableSet checkedElements = ViewersObservables.observeCheckedElements(treeViewer, FieldToContractInputMapping.class);
                if (checkedElements.isEmpty()) {
                    return ValidationStatus.error(Messages.atLeastOneAttributeShouldBeSelectedError);
                }
                return ValidationStatus.ok();
            }
        });

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
                if (selectedData == null) {
                    return Collections.emptyList();
                }
                mappings = fieldToContractInputMappingFactory.createMappingForBusinessObjectType(toBusinessObject((BusinessObjectData) selectedData));
                fieldToContractInputMappingsObservable.clear();
                fieldToContractInputMappingsObservable.addAll(mappings);
                return mappings;
            }

        };
    }

    private BusinessObject toBusinessObject(final BusinessObjectData selectedData) {
        return businessObjectStore.getBusinessObjectByQualifiedName(selectedData.getClassName());
    }

    public List<FieldToContractInputMapping> getMappings() {
        return mappings;
    }

    /**
     * @param mappings the mappings to set
     */
    public void setMappings(final List<FieldToContractInputMapping> mappings) {
        this.mappings = mappings;
    }

    public String getRootName() {
        return rootName;
    }

    public void setRootName(final String rootName) {
        this.rootName = rootName;
    }

}
