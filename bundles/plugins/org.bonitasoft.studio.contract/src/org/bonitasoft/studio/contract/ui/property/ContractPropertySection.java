/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.contract.ui.property;

import org.bonitasoft.studio.common.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.common.properties.EObjectSelectionProviderSection;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.contract.ui.property.edit.CheckboxPropertyEditingSupport;
import org.bonitasoft.studio.contract.ui.property.edit.ConstraintColumnLabelProvider;
import org.bonitasoft.studio.contract.ui.property.edit.ConstraintPropertyEditingSupport;
import org.bonitasoft.studio.contract.ui.property.edit.InputMappingPropertyEditingSupport;
import org.bonitasoft.studio.contract.ui.property.edit.InputMappingProposal;
import org.bonitasoft.studio.contract.ui.property.edit.InputNamePropertyEditingSupport;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputMapping;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.views.properties.PropertyColumnLabelProvider;
import org.eclipse.ui.views.properties.PropertyEditingSupport;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;


/**
 * @author Romain Bioteau
 *
 */
public class ContractPropertySection extends EObjectSelectionProviderSection {

    private EMFDataBindingContext context;
    private ComposedAdapterFactory composedAdapterFactory;
    private AdapterFactoryContentProvider propertySourceProvider;
    private AdapterFactoryLabelProvider adapterFactoryLabelProvider;

    @Override
    public String getSectionDescription() {
        return Messages.contractSectionDescription;
    }

    @Override
    public void createControls(final Composite parent, final TabbedPropertySheetPage aTabbedPropertySheetPage) {
        super.createControls(parent, aTabbedPropertySheetPage);
        context = new EMFDataBindingContext();
        composedAdapterFactory =
                new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        propertySourceProvider = new AdapterFactoryContentProvider(composedAdapterFactory);
        adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(composedAdapterFactory);
        final Composite mainComposite = getWidgetFactory().createComposite(parent);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).extendedMargins(15, 15, 10, 5).create());

        createContractInputTable(mainComposite);
    }

    protected void addInput(final TableViewer inputsTableViewer) {
        final IObservableList input = (IObservableList) inputsTableViewer.getInput();
        final ContractInput defaultInput = createDefaultInput(input);
        input.add(defaultInput);
        inputsTableViewer.editElement(defaultInput, 0);
    }

    private ContractInput createDefaultInput(final IObservableList input) {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        //  contractInput.setName(NamingUtils.generateNewName(getInputNames(input), "input"));
        contractInput.setType(ContractInputType.TEXT);
        contractInput.setMapping(ProcessFactory.eINSTANCE.createContractInputMapping());
        return contractInput;
    }

    //    private Set<String> getInputNames(final IObservableList input) {
    //        final Set<String> inputNames = new HashSet<String>();
    //        final Iterator<?> iterator = input.iterator();
    //        while (iterator.hasNext()) {
    //            final ContractInput contractInput = (ContractInput) iterator.next();
    //            inputNames.add(contractInput.getName());
    //        }
    //        return inputNames;
    //    }

    private void createContractInputTable(final Composite parent) {
        final Composite buttonsComposite = getWidgetFactory().createComposite(parent);
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).align(SWT.FILL, SWT.TOP).create());
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).spacing(0, 3).create());

        final Button addButton = getWidgetFactory().createButton(buttonsComposite, Messages.add, SWT.PUSH);
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());


        final Button removeButton = getWidgetFactory().createButton(buttonsComposite, Messages.remove, SWT.PUSH);
        removeButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());


        final TableViewer inputsTableViewer = new TableViewer(getWidgetFactory().createTable(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.SINGLE));
        inputsTableViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(500, 180).create());
        inputsTableViewer.getTable().setHeaderVisible(true);
        final ObservableListContentProvider observableListContentProvider = new ObservableListContentProvider();
        inputsTableViewer.setContentProvider(observableListContentProvider);

        ColumnViewerToolTipSupport.enableFor(inputsTableViewer);

        final TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(3));
        tableLayout.addColumnData(new ColumnWeightData(1));
        tableLayout.addColumnData(new ColumnWeightData(1));
        tableLayout.addColumnData(new ColumnWeightData(1));
        tableLayout.addColumnData(new ColumnWeightData(3));
        tableLayout.addColumnData(new ColumnWeightData(1));
        tableLayout.addColumnData(new ColumnWeightData(5));
        inputsTableViewer.getTable().setLayout(tableLayout);

        createInputNameColumn(inputsTableViewer);
        createInputTypeColumn(inputsTableViewer);
        createMultipleColumn(inputsTableViewer);
        createMandatoryColumn(inputsTableViewer);
        createMappingColumn(inputsTableViewer);
        createConstraintColumn(inputsTableViewer);
        createInputDescriptionColumn(inputsTableViewer);

        final IObservableValue observeContractValue = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(), getEObjectObservable(),
                ProcessPackage.Literals.TASK__CONTRACT);
        final IObservableList observeContractInputDetailList = CustomEMFEditObservables.observeDetailList(Realm.getDefault(), observeContractValue,
                ProcessPackage.Literals.CONTRACT__INPUTS);

        inputsTableViewer.setInput(observeContractInputDetailList);
        addButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                addInput(inputsTableViewer);
            }
        });

        removeButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                removeInput(inputsTableViewer);
            }
        });

    }

    protected void removeInput(final TableViewer viewer) {
        final IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
        final IObservableList input = (IObservableList) viewer.getInput();
        input.removeAll(selection.toList());
    }

    protected void createInputNameColumn(final TableViewer viewer) {
        final TableViewerColumn nameColumnViewer = new TableViewerColumn(viewer, SWT.FILL);
        final TableColumn column = nameColumnViewer.getColumn();
        column.setText(Messages.name + " *");
        nameColumnViewer.setLabelProvider(new PropertyColumnLabelProvider(propertySourceProvider, "name") {

            @Override
            public Image getImage(final Object object) {
                return null;
            }
        });
        nameColumnViewer.setEditingSupport(new InputNamePropertyEditingSupport(propertySourceProvider, viewer));
    }

    protected void createInputDescriptionColumn(final TableViewer viewer) {
        final TableViewerColumn descriptionColumnViewer = new TableViewerColumn(viewer, SWT.FILL);
        final TableColumn column = descriptionColumnViewer.getColumn();
        column.setText(Messages.description);
        descriptionColumnViewer.setLabelProvider(new PropertyColumnLabelProvider(propertySourceProvider, "description") {

            @Override
            public Image getImage(final Object object) {
                return null;
            }
        });
        descriptionColumnViewer.setEditingSupport(new PropertyEditingSupport(viewer, propertySourceProvider, "description") {

            @Override
            protected void setValue(final Object element, final Object value) {
                super.setValue(element, value);
                getViewer().update(element, null);
            }
        });
    }

    protected void createMappingColumn(final TableViewer viewer) {
        final TableViewerColumn mappingColumnViewer = new TableViewerColumn(viewer, SWT.FILL);
        final TableColumn column = mappingColumnViewer.getColumn();
        column.setText(Messages.savedInto);
        mappingColumnViewer.setLabelProvider(new ColumnLabelProvider(){

            @Override
            public String getText(final Object element) {
                if (element instanceof ContractInput) {
                    final ContractInputMapping mapping = ((ContractInput) element).getMapping();
                    return new InputMappingProposal(mapping).getContent();
                }
                return null;
            }

            @Override
            public Image getImage(final Object element) {
                if (element instanceof ContractInput) {
                    final ContractInputMapping mapping = ((ContractInput) element).getMapping();
                    final Data data = mapping.getData();
                    if (data == null) {
                        return null;
                    }
                    return adapterFactoryLabelProvider.getImage(data);
                }
                return null;
            }
        });
        mappingColumnViewer.setEditingSupport(new InputMappingPropertyEditingSupport(propertySourceProvider, viewer));
    }

    protected void createConstraintColumn(final TableViewer viewer) {
        final TableViewerColumn constraintColumnViewer = new TableViewerColumn(viewer, SWT.FILL);
        final TableColumn column = constraintColumnViewer.getColumn();
        column.setText(Messages.constraints);
        constraintColumnViewer.setLabelProvider(new ConstraintColumnLabelProvider());
        constraintColumnViewer.setEditingSupport(new ConstraintPropertyEditingSupport(viewer));
    }

    protected void createInputTypeColumn(final TableViewer viewer) {
        final TableViewerColumn typeColumnViewer = new TableViewerColumn(viewer, SWT.FILL);
        final TableColumn column = typeColumnViewer.getColumn();
        column.setText(Messages.type);
        typeColumnViewer.setLabelProvider(new PropertyColumnLabelProvider(propertySourceProvider, "type") {

            @Override
            public Image getImage(final Object object) {
                return null;
            }
        });
        typeColumnViewer.setEditingSupport(new PropertyEditingSupport(viewer, propertySourceProvider, "type") {

            @Override
            protected void setValue(final Object object, final Object value) {
                super.setValue(object, value);
                viewer.update(object, null);
            }
        });
    }

    protected void createMandatoryColumn(final TableViewer viewer) {
        final TableViewerColumn mandatoryColumnViewer = new TableViewerColumn(viewer, SWT.CENTER);
        final TableColumn column = mandatoryColumnViewer.getColumn();
        column.setText(Messages.mandatory);
        mandatoryColumnViewer.setLabelProvider(new MandatoryInputCheckboxLabelProvider(viewer.getControl()));
        mandatoryColumnViewer.setEditingSupport(new CheckboxPropertyEditingSupport(propertySourceProvider, viewer, "mandatory"));
    }

    protected void createMultipleColumn(final TableViewer viewer) {
        final TableViewerColumn multipleColumnViewer = new TableViewerColumn(viewer, SWT.CENTER);
        final TableColumn column = multipleColumnViewer.getColumn();
        column.setText(Messages.multiple);
        multipleColumnViewer.setLabelProvider(new MultipleInputCheckboxLabelProvider(viewer.getControl()));
        multipleColumnViewer.setEditingSupport(new CheckboxPropertyEditingSupport(propertySourceProvider, viewer, "multiple"));
    }


    @Override
    public void dispose() {
        super.dispose();
        if (adapterFactoryLabelProvider != null) {
            adapterFactoryLabelProvider.dispose();
        }
        if (propertySourceProvider != null) {
            propertySourceProvider.dispose();
        }
        if (composedAdapterFactory != null) {
            composedAdapterFactory.dispose();
        }
        if (context != null) {
            context.dispose();
        }
    }

}
