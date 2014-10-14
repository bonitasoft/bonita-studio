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
package org.bonitasoft.studio.contract.ui.property.constraint;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.contract.core.ContractDefinitionValidator;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.contract.ui.property.AddRowOnEnterCellNavigationStrategy;
import org.bonitasoft.studio.contract.ui.property.CharriageColumnViewerEditorActivationStrategy;
import org.bonitasoft.studio.contract.ui.property.constraint.edit.ConstraintErrorMessagePropertyEditingSupport;
import org.bonitasoft.studio.contract.ui.property.constraint.edit.ConstraintExpressionPropertyEditingSupport;
import org.bonitasoft.studio.contract.ui.property.constraint.edit.ConstraintNamePropertyEditingSupport;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.CellNavigationStrategy;
import org.eclipse.jface.viewers.ColumnViewerEditor;
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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * @author Romain Bioteau
 *
 */
public class ContractConstraintsTableViewer extends TableViewer {

    private AdapterFactoryContentProvider propertySourceProvider;
    private AdapterFactoryLabelProvider adapterFactoryLabelProvider;
    private ContractDefinitionValidator contractValidator;
    private ContractConstraintController constraintController;

    public ContractConstraintsTableViewer(final Composite parent, final FormToolkit toolkit) {
        super(toolkit.createTable(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI));
        getTable().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_CONTRACT_CONSTRAINT_TABLE);
    }

    public void initialize(final ContractConstraintController constraintController, final ContractDefinitionValidator contractValidator) {
        this.contractValidator = contractValidator;
        this.constraintController = constraintController;
        final ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        propertySourceProvider = new AdapterFactoryContentProvider(composedAdapterFactory);
        adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(composedAdapterFactory);
        getTable().setHeaderVisible(true);
        getTable().setLinesVisible(true);
        setContentProvider(new ObservableListContentProvider());

        final CellNavigationStrategy cellNavigationStrategy = new AddRowOnEnterCellNavigationStrategy(this, constraintController);
        final TableViewerFocusCellManager focusCellManager = new TableViewerFocusCellManager(this, new FocusCellOwnerDrawHighlighter(
                this), cellNavigationStrategy);
        TableViewerEditor.create(this, focusCellManager, new CharriageColumnViewerEditorActivationStrategy(this), ColumnViewerEditor.TABBING_HORIZONTAL |
                ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR |
                ColumnViewerEditor.TABBING_VERTICAL |
                ColumnViewerEditor.KEYBOARD_ACTIVATION);

        ColumnViewerToolTipSupport.enableFor(this);

        configureTableLayout();
        createColumns();
    }

    public void createAddListener(final Button button) {
        button.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                constraintController.add(ContractConstraintsTableViewer.this);
            }
        });
    }

    public void createMoveUpListener(final Button button) {
        button.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                constraintController.moveUp(ContractConstraintsTableViewer.this);
            }
        });
    }

    public void createMoveDownListener(final Button button) {
        button.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                constraintController.moveDown(ContractConstraintsTableViewer.this);
            }
        });
    }



    public void createRemoveListener(final Button button) {
        button.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                constraintController.remove(ContractConstraintsTableViewer.this);
            }
        });
    }

    public void createColumns() {
        createConstraintNameColumn();
        createConstraintExpressionColumn();
        createConstraintErrorMessageColumn();
    }

    public void configureTableLayout() {
        final TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(1));
        tableLayout.addColumnData(new ColumnWeightData(2));
        tableLayout.addColumnData(new ColumnWeightData(2));
        getTable().setLayout(tableLayout);
    }

    protected void createConstraintNameColumn() {
        final TableViewerColumn nameColumnViewer = createColumnViewer(Messages.name + " *", SWT.FILL);
        nameColumnViewer.setLabelProvider(new ConstraintNameCellLabelProvider(this, propertySourceProvider));
        nameColumnViewer.setEditingSupport(new ConstraintNamePropertyEditingSupport(propertySourceProvider,
                this,
                adapterFactoryLabelProvider,
                contractValidator));
    }

    protected void createConstraintExpressionColumn() {
        final TableViewerColumn nameColumnViewer = createColumnViewer(Messages.expression + " *", SWT.FILL);
        nameColumnViewer.setLabelProvider(new ConstraintExpressionCellLabelProvider(this, propertySourceProvider));
        nameColumnViewer.setEditingSupport(new ConstraintExpressionPropertyEditingSupport(this, propertySourceProvider));
    }

    protected void createConstraintErrorMessageColumn() {
        final TableViewerColumn descriptionColumnViewer = createColumnViewer(Messages.errorMessage, SWT.FILL);
        descriptionColumnViewer.setLabelProvider(new ConstraintErrorMessageCellLabelProvider(this, propertySourceProvider));
        descriptionColumnViewer.setEditingSupport(new ConstraintErrorMessagePropertyEditingSupport(this, propertySourceProvider));
    }

    protected TableViewerColumn createColumnViewer(final String text, final int style) {
        final TableViewerColumn columnViewer = new TableViewerColumn(this, style);
        final TableColumn column = columnViewer.getColumn();
        column.setText(text);
        return columnViewer;
    }

    @Override
    protected void handleDispose(final DisposeEvent event) {
        super.handleDispose(event);
        if (adapterFactoryLabelProvider != null) {
            adapterFactoryLabelProvider.dispose();
        }
        if (propertySourceProvider != null) {
            propertySourceProvider.dispose();
        }
    }

}
