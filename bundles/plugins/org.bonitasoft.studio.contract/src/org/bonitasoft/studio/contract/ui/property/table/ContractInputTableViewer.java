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
package org.bonitasoft.studio.contract.ui.property.table;

import java.util.EventObject;

import org.bonitasoft.studio.contract.core.ContractDefinitionValidator;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.contract.ui.property.FieldDecoratorProvider;
import org.bonitasoft.studio.contract.ui.property.edit.CheckboxPropertyEditingSupport;
import org.bonitasoft.studio.contract.ui.property.edit.DescriptionCellLabelProvider;
import org.bonitasoft.studio.contract.ui.property.edit.DescriptionPropertyEditingSupport;
import org.bonitasoft.studio.contract.ui.property.edit.InputNameCellLabelProvider;
import org.bonitasoft.studio.contract.ui.property.edit.InputNamePropertyEditingSupport;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.CellNavigationStrategy;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.FocusCellOwnerDrawHighlighter;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.jface.viewers.TableViewerFocusCellManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.views.properties.PropertyColumnLabelProvider;
import org.eclipse.ui.views.properties.PropertyEditingSupport;


/**
 * @author Romain Bioteau
 *
 */
public class ContractInputTableViewer extends TableViewer {

    private AdapterFactoryContentProvider propertySourceProvider;
    private AdapterFactoryLabelProvider adapterFactoryLabelProvider;
    private ContractDefinitionValidator contractValidator;
    private final FieldDecoratorProvider decoratorProvider = new FieldDecoratorProvider();

    public ContractInputTableViewer(final Composite parent, final FormToolkit toolkit) {
        super(toolkit.createTable(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI));
    }

    public void initialize(final ContractInputController inputController, final ContractDefinitionValidator contractValidator) {
        this.contractValidator = contractValidator;
        final ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        propertySourceProvider = new AdapterFactoryContentProvider(composedAdapterFactory);
        adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(composedAdapterFactory);
        getTable().setHeaderVisible(true);
        setContentProvider(new ObservableListContentProvider());
        final ColumnViewerEditorActivationStrategy activationSupport = new ColumnViewerEditorActivationStrategy(this) {

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

        final CellNavigationStrategy cellNavigationStrategy = new ContracInputTableViewerCellNavigationStrategy(this, inputController);
        final TableViewerFocusCellManager focusCellManager = new TableViewerFocusCellManager(this, new FocusCellOwnerDrawHighlighter(
                this), cellNavigationStrategy);
        TableViewerEditor.create(this, focusCellManager, activationSupport, ColumnViewerEditor.TABBING_HORIZONTAL |
                ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR |
                ColumnViewerEditor.TABBING_VERTICAL |
                ColumnViewerEditor.KEYBOARD_ACTIVATION);

        ColumnViewerToolTipSupport.enableFor(this);

        configureTableLayout();
        createColumns();
    }

    public void createColumns() {
        createInputNameColumn();
        createInputTypeColumn();
        createMultipleColumn();
        createMandatoryColumn();
        //   createMappingColumn();
        createInputDescriptionColumn();
    }

    public void configureTableLayout() {
        final TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(3));
        tableLayout.addColumnData(new ColumnWeightData(1));
        tableLayout.addColumnData(new ColumnWeightData(1));
        tableLayout.addColumnData(new ColumnWeightData(1));
        //   tableLayout.addColumnData(new ColumnWeightData(3));
        tableLayout.addColumnData(new ColumnWeightData(5));
        getTable().setLayout(tableLayout);
    }

    protected void createInputNameColumn() {
        final TableViewerColumn nameColumnViewer = new TableViewerColumn(this, SWT.FILL);
        final TableColumn column = nameColumnViewer.getColumn();
        column.setText(Messages.name + " *");
        nameColumnViewer.setLabelProvider(new InputNameCellLabelProvider(this, propertySourceProvider));
        nameColumnViewer.setEditingSupport(new InputNamePropertyEditingSupport(propertySourceProvider,
                this,
                adapterFactoryLabelProvider,
                contractValidator,
                decoratorProvider));
    }

    protected void createInputDescriptionColumn() {
        final TableViewerColumn descriptionColumnViewer = new TableViewerColumn(this, SWT.FILL);
        final TableColumn column = descriptionColumnViewer.getColumn();
        column.setText(Messages.description);
        descriptionColumnViewer.setLabelProvider(new DescriptionCellLabelProvider(this, propertySourceProvider));
        descriptionColumnViewer.setEditingSupport(new DescriptionPropertyEditingSupport(this, propertySourceProvider, contractValidator));
    }

    //    protected void createMappingColumn() {
    //        final TableViewerColumn mappingColumnViewer = new TableViewerColumn(this, SWT.FILL);
    //        final TableColumn column = mappingColumnViewer.getColumn();
    //        column.setText(Messages.savedInto);
    //        mappingColumnViewer.setLabelProvider(new ColumnLabelProvider() {
    //
    //            @Override
    //            public String getText(final Object element) {
    //                if (element instanceof ContractInput) {
    //                    final ContractInputMapping mapping = ((ContractInput) element).getMapping();
    //                    return new InputMappingProposal(mapping).getContent();
    //                }
    //                return null;
    //            }
    //
    //            @Override
    //            public Image getImage(final Object element) {
    //                if (element instanceof ContractInput) {
    //                    final ContractInputMapping mapping = ((ContractInput) element).getMapping();
    //                    final Data data = mapping.getData();
    //                    if (data == null) {
    //                        return null;
    //                    }
    //                    return adapterFactoryLabelProvider.getImage(data);
    //                }
    //                return null;
    //            }
    //        });
    //        mappingColumnViewer.setEditingSupport(new InputMappingPropertyEditingSupport(propertySourceProvider, this));
    //    }

    protected void createInputTypeColumn() {
        final TableViewerColumn typeColumnViewer = new TableViewerColumn(this, SWT.FILL);
        final TableColumn column = typeColumnViewer.getColumn();
        column.setText(Messages.type);
        typeColumnViewer.setLabelProvider(new PropertyColumnLabelProvider(propertySourceProvider, ProcessPackage.Literals.CONTRACT_INPUT__TYPE.getName()) {

            @Override
            public Image getImage(final Object object) {
                return null;
            }
        });
        typeColumnViewer.setEditingSupport(new PropertyEditingSupport(this, propertySourceProvider, ProcessPackage.Literals.CONTRACT_INPUT__TYPE.getName()) {

            @Override
            protected void setValue(final Object object, final Object value) {
                super.setValue(object, value);
                ContractInputTableViewer.this.update(object, null);
            }
        });
    }

    protected void createMandatoryColumn() {
        final TableViewerColumn mandatoryColumnViewer = new TableViewerColumn(this, SWT.CENTER);
        final TableColumn column = mandatoryColumnViewer.getColumn();
        column.setText(Messages.mandatory);
        mandatoryColumnViewer.setLabelProvider(new MandatoryInputCheckboxLabelProvider(getControl()));
        mandatoryColumnViewer.setEditingSupport(new CheckboxPropertyEditingSupport(propertySourceProvider, this,
                ProcessPackage.Literals.CONTRACT_INPUT__MANDATORY.getName()));
    }

    protected void createMultipleColumn() {
        final TableViewerColumn multipleColumnViewer = new TableViewerColumn(this, SWT.CENTER);
        final TableColumn column = multipleColumnViewer.getColumn();
        column.setText(Messages.multiple);
        multipleColumnViewer.setLabelProvider(new MultipleInputCheckboxLabelProvider(getControl()));
        multipleColumnViewer.setEditingSupport(new CheckboxPropertyEditingSupport(propertySourceProvider, this,
                ProcessPackage.Literals.CONTRACT_INPUT__MULTIPLE.getName()));
    }

    @Override
    protected void handleDispose(final DisposeEvent event) {
        super.handleDispose(event);
        if (event.widget.equals(getTable())) {
            if (adapterFactoryLabelProvider != null) {
                adapterFactoryLabelProvider.dispose();
            }
            if (propertySourceProvider != null) {
                propertySourceProvider.dispose();
            }
        }
    }

}
