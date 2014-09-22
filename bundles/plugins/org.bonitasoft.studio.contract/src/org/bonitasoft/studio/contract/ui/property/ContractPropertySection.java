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

import java.util.EventObject;

import org.bonitasoft.studio.common.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.common.properties.EObjectSelectionProviderSection;
import org.bonitasoft.studio.contract.core.ContractDefinitionValidator;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.contract.ui.property.edit.CheckboxPropertyEditingSupport;
import org.bonitasoft.studio.contract.ui.property.edit.ConstraintColumnLabelProvider;
import org.bonitasoft.studio.contract.ui.property.edit.ConstraintPropertyEditingSupport;
import org.bonitasoft.studio.contract.ui.property.edit.DescriptionPropertyEditingSupport;
import org.bonitasoft.studio.contract.ui.property.edit.InputMappingPropertyEditingSupport;
import org.bonitasoft.studio.contract.ui.property.edit.InputNamePropertyEditingSupport;
import org.bonitasoft.studio.contract.ui.property.edit.proposal.InputMappingProposal;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputMapping;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.CellNavigationStrategy;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.FocusCellOwnerDrawHighlighter;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.jface.viewers.TableViewerFocusCellManager;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.views.properties.PropertyColumnLabelProvider;
import org.eclipse.ui.views.properties.PropertyEditingSupport;


/**
 * @author Romain Bioteau
 *
 */
public class ContractPropertySection extends EObjectSelectionProviderSection {

    private EMFDataBindingContext context;
    private ComposedAdapterFactory composedAdapterFactory;
    private AdapterFactoryContentProvider propertySourceProvider;
    private AdapterFactoryLabelProvider adapterFactoryLabelProvider;
    private ContractDefinitionValidator contractValidator;
    private IObservableValue observeContractValue;

    @Override
    public String getSectionDescription() {
        return Messages.contractSectionDescription;
    }

    @Override
    public String getSectionTitle() {
        return Messages.contractInputs;
    }

    @Override
    protected void createContent(final Composite parent) {
        contractValidator = new ContractDefinitionValidator(
                getMessageManager());

        context = new EMFDataBindingContext();
        composedAdapterFactory =
                new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        propertySourceProvider = new AdapterFactoryContentProvider(composedAdapterFactory);
        adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(composedAdapterFactory);

        final CTabFolder tabFolder = getWidgetFactory().createTabFolder(parent, SWT.FLAT | SWT.TOP);
        tabFolder.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        getWidgetFactory().adapt(tabFolder, true, true);

        final CTabItem inputTabItem = getWidgetFactory().createTabItem(tabFolder, SWT.NULL);
        inputTabItem.setText("Inputs");
        final Composite inputComposite = getWidgetFactory().createComposite(tabFolder);
        inputComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        inputComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).extendedMargins(15, 15, 10, 5).create());

        createContractInputTable(inputComposite);

        inputTabItem.setControl(inputComposite);

        final Composite constraintComposite = getWidgetFactory().createComposite(tabFolder);
        constraintComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        constraintComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).extendedMargins(15, 15, 10, 5).create());
        final CTabItem constraintTabItem = getWidgetFactory().createTabItem(tabFolder, SWT.NULL);
        constraintTabItem.setText("Constraints");
        constraintTabItem.setControl(constraintComposite);

        tabFolder.setSelection(0);
    }

    protected void addInput(final TableViewer inputsTableViewer) {
        final IObservableList input = (IObservableList) inputsTableViewer.getInput();
        final ContractInput defaultInput = createDefaultInput(input);
        input.add(defaultInput);
        inputsTableViewer.editElement(defaultInput, 0);
    }

    private ContractInput createDefaultInput(final IObservableList input) {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setType(ContractInputType.TEXT);
        contractInput.setMapping(ProcessFactory.eINSTANCE.createContractInputMapping());
        return contractInput;
    }


    private void createContractInputTable(final Composite parent) {
        final Composite buttonsComposite = getWidgetFactory().createComposite(parent);
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).align(SWT.FILL, SWT.TOP).create());
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).spacing(0, 3).extendedMargins(0, 0, 25, 0).create());

        final Button addButton = getWidgetFactory().createButton(buttonsComposite, Messages.add, SWT.PUSH);
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());


        final Button removeButton = getWidgetFactory().createButton(buttonsComposite, Messages.remove, SWT.PUSH);
        removeButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());


        final TableViewer inputsTableViewer = new TableViewer(getWidgetFactory().createTable(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI));
        inputsTableViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(500, 180).create());
        inputsTableViewer.getTable().setHeaderVisible(true);
        final ObservableListContentProvider observableListContentProvider = new ObservableListContentProvider();
        inputsTableViewer.setContentProvider(observableListContentProvider);


        final ColumnViewerEditorActivationStrategy activationSupport = new ColumnViewerEditorActivationStrategy(inputsTableViewer) {

            @Override
            protected boolean isEditorActivationEvent(final ColumnViewerEditorActivationEvent event) {
                if (event.eventType == ColumnViewerEditorActivationEvent.MOUSE_CLICK_SELECTION) {
                    final EventObject source = event.sourceEvent;
                    if (source instanceof MouseEvent && ((MouseEvent) source).button == 3) {
                        return false;
                    }
                }
                return super.isEditorActivationEvent(event) || event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED && event.keyCode == SWT.CR;
            }
        };

        final CellNavigationStrategy cellNavigationStrategy = new CellNavigationStrategy() {

            @Override
            public boolean isNavigationEvent(final ColumnViewer viewer, final Event event) {
                switch (event.keyCode) {
                    case SWT.CR:
                        return true;
                    case SWT.DEL:
                        return true;
                    default:
                        return super.isNavigationEvent(viewer, event);
                }
            }

            @Override
            public ViewerCell findSelectedCell(final ColumnViewer viewer, final ViewerCell currentSelectedCell, final Event event) {
                switch (event.keyCode) {
                    case SWT.CR:
                        if (currentSelectedCell != null) {
                            ViewerCell nextCell = currentSelectedCell.getNeighbor(ViewerCell.BELOW, false);
                            if (nextCell == null && currentSelectedCell.getColumnIndex() == 0) {
                                addInput(inputsTableViewer);
                            }
                            nextCell = currentSelectedCell.getNeighbor(ViewerCell.BELOW, false);
                            return nextCell;
                        }
                        break;
                    case SWT.DEL:
                        if(currentSelectedCell != null){
                            final ViewerCell aboveCell = currentSelectedCell.getNeighbor(ViewerCell.ABOVE, false);
                            removeInput(inputsTableViewer);
                            return aboveCell;
                        }
                        break;
                    default:
                        return super.findSelectedCell(viewer, currentSelectedCell, event);
                }
                return super.findSelectedCell(viewer, currentSelectedCell, event);

            }
        };

        final TableViewerFocusCellManager focusCellManager = new TableViewerFocusCellManager(inputsTableViewer, new FocusCellOwnerDrawHighlighter(
                inputsTableViewer), cellNavigationStrategy);
        TableViewerEditor.create(inputsTableViewer, focusCellManager, activationSupport, ColumnViewerEditor.TABBING_HORIZONTAL |
                ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR |
                ColumnViewerEditor.TABBING_VERTICAL |
                ColumnViewerEditor.KEYBOARD_ACTIVATION);

        ColumnViewerToolTipSupport.enableFor(inputsTableViewer);

        final TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(3));
        tableLayout.addColumnData(new ColumnWeightData(1));
        tableLayout.addColumnData(new ColumnWeightData(1));
        tableLayout.addColumnData(new ColumnWeightData(1));
        tableLayout.addColumnData(new ColumnWeightData(3));
        //  tableLayout.addColumnData(new ColumnWeightData(1));
        tableLayout.addColumnData(new ColumnWeightData(5));
        inputsTableViewer.getTable().setLayout(tableLayout);

        createInputNameColumn(inputsTableViewer);
        createInputTypeColumn(inputsTableViewer);
        createMultipleColumn(inputsTableViewer);
        createMandatoryColumn(inputsTableViewer);
        createMappingColumn(inputsTableViewer);
        //   createConstraintColumn(inputsTableViewer);
        createInputDescriptionColumn(inputsTableViewer);

        observeContractValue = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(), getEObjectObservable(),
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
        observeContractValue.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                validate((Contract) event.diff.getNewValue());
            }
        });


        final UpdateValueStrategy modelStrategy = new UpdateValueStrategy();
        modelStrategy.setConverter(new Converter(Object.class,Boolean.class) {

            @Override
            public Object convert(final Object from) {
                return from != null;
            }
        });
        context.bindValue(SWTObservables.observeEnabled(removeButton), ViewersObservables.observeSingleSelection(inputsTableViewer),new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),modelStrategy);

        inputsTableViewer.getTable().setFocus();
    }

    protected void validate(final Contract contract) {
        final ContractDefinitionValidator definitionValidator = new ContractDefinitionValidator(getMessageManager());
        definitionValidator.validate(contract);
    }

    protected void removeInput(final TableViewer viewer) {
        final IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
        final IObservableList input = (IObservableList) viewer.getInput();
        for (final Object o : selection.toList()) {
            contractValidator.clearMessages(o);
        }
        input.removeAll(selection.toList());
        contractValidator.validateDuplicatedInputs((Contract) observeContractValue.getValue());
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
        nameColumnViewer
        .setEditingSupport(new InputNamePropertyEditingSupport(propertySourceProvider, viewer, adapterFactoryLabelProvider, contractValidator));
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

            @Override
            public String getToolTipText(final Object element) {
                return ((ContractInput) element).getDescription();
            }
        });
        descriptionColumnViewer.setEditingSupport(new DescriptionPropertyEditingSupport(viewer, propertySourceProvider, contractValidator));
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
