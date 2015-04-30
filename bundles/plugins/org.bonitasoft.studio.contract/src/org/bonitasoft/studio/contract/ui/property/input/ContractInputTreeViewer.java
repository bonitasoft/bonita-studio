/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.contract.ui.property.input;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.contract.core.refactoring.ContractInputRefactorOperationFactory;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.contract.ui.property.AddRowOnEnterCellNavigationStrategy;
import org.bonitasoft.studio.contract.ui.property.CharriageColumnViewerEditorActivationStrategy;
import org.bonitasoft.studio.contract.ui.property.input.edit.CheckboxPropertyEditingSupport;
import org.bonitasoft.studio.contract.ui.property.input.edit.ContractInputTypeEditingSupport;
import org.bonitasoft.studio.contract.ui.property.input.edit.DescriptionObservableEditingSupport;
import org.bonitasoft.studio.contract.ui.property.input.edit.InputNameObservableEditingSupport;
import org.bonitasoft.studio.contract.ui.property.input.labelProvider.ContractInputTypeCellLabelProvider;
import org.bonitasoft.studio.contract.ui.property.input.labelProvider.DescriptionCellLabelProvider;
import org.bonitasoft.studio.contract.ui.property.input.labelProvider.InputNameCellLabelProvider;
import org.bonitasoft.studio.contract.ui.property.input.labelProvider.MultipleInputCheckboxLabelProvider;
import org.bonitasoft.studio.contract.ui.property.input.labelProvider.NotNullableInputCheckboxLabelProvider;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.databinding.viewers.ObservableListTreeContentProvider;
import org.eclipse.jface.viewers.CellNavigationStrategy;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.FocusCellOwnerDrawHighlighter;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.TreeViewerEditor;
import org.eclipse.jface.viewers.TreeViewerFocusCellManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Romain Bioteau
 */
public class ContractInputTreeViewer extends TreeViewer {

    private AdapterFactoryContentProvider propertySourceProvider;
    private AdapterFactoryLabelProvider adapterFactoryLabelProvider;
    private ContractInputController inputController;
    private EMFDataBindingContext emfDataBindingContext;
    private final IProgressService progressService;
    private IMessageManager messageManager;

    public ContractInputTreeViewer(final Composite parent, final FormToolkit toolkit, final IProgressService progressService) {
        super(toolkit.createTree(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI));
        getTree().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_CONTRACT_INPUT_TREE);
        this.progressService = progressService;
    }

    public void createAddListener(final Button button) {
        button.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                inputController.add(ContractInputTreeViewer.this);
            }
        });
    }

    public void createAddChildListener(final Button button) {
        button.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                inputController.addChildInput(ContractInputTreeViewer.this);
            }
        });
    }

    public void createRemoveListener(final Button button) {
        button.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                inputController.remove(ContractInputTreeViewer.this);
            }
        });
    }

    public void initialize(final ContractInputController inputController, final IMessageManager messageManager,
            final EMFDataBindingContext emfDataBindingContext) {
        this.messageManager = messageManager;
        this.inputController = inputController;
        this.emfDataBindingContext = emfDataBindingContext;
        final ProcessItemProviderAdapterFactory adapterFactory = new ProcessItemProviderAdapterFactory();
        propertySourceProvider = new AdapterFactoryContentProvider(adapterFactory);
        adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(adapterFactory);
        getTree().setHeaderVisible(true);
        getTree().setLinesVisible(true);
        addFilter(new ComplexTypeChildrenViewerFilter());
        final ObservableListTreeContentProvider contentProvider = new ObservableListTreeContentProvider(new ContractInputObservableFactory(),
                new ContractInputTreeStructureAdvisor());
        setContentProvider(contentProvider);
        final CellNavigationStrategy cellNavigationStrategy = new AddRowOnEnterCellNavigationStrategy(this, inputController);
        final TreeViewerFocusCellManager focusCellManager = new TreeViewerFocusCellManager(this, new FocusCellOwnerDrawHighlighter(
                this), cellNavigationStrategy);
        TreeViewerEditor.create(this, focusCellManager, new CharriageColumnViewerEditorActivationStrategy(this), ColumnViewerEditor.TABBING_HORIZONTAL |
                ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR |
                ColumnViewerEditor.TABBING_VERTICAL |
                ColumnViewerEditor.KEYBOARD_ACTIVATION);

        ColumnViewerToolTipSupport.enableFor(this);

        configureTableLayout();
        createColumns();
    }

    protected void createColumns() {
        createInputNameColumn();
        createInputTypeColumn();
        createMultipleColumn();
        createMandatoryColumn();
        createInputDescriptionColumn();
    }

    protected void configureTableLayout() {
        final TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(3));
        tableLayout.addColumnData(new ColumnWeightData(1));
        tableLayout.addColumnData(new ColumnWeightData(1));
        tableLayout.addColumnData(new ColumnWeightData(1));
        tableLayout.addColumnData(new ColumnWeightData(5));
        getTree().setLayout(tableLayout);
    }

    private IObservableSet knownElements() {
        final ObservableListTreeContentProvider contentProvider = (ObservableListTreeContentProvider) getContentProvider();
        return contentProvider.getKnownElements();
    }

    protected void createInputNameColumn() {
        final TreeViewerColumn nameColumnViewer = createColumnViewer(Messages.name + " *", SWT.FILL);
        nameColumnViewer.setLabelProvider(new InputNameCellLabelProvider(propertySourceProvider,
                knownElements()));
        final InputNameObservableEditingSupport editingSupport = new InputNameObservableEditingSupport(this,
                messageManager,
                emfDataBindingContext,
                new ContractInputRefactorOperationFactory(),
                progressService);
        editingSupport.setControlId(SWTBotConstants.SWTBOT_ID_INPUT_NAME_TEXTEDITOR);
        nameColumnViewer.setEditingSupport(editingSupport);
    }

    protected void createInputDescriptionColumn() {
        final TreeViewerColumn descriptionColumnViewer = createColumnViewer(Messages.description, SWT.FILL);
        descriptionColumnViewer.setLabelProvider(new DescriptionCellLabelProvider(propertySourceProvider,
                knownElements()));
        descriptionColumnViewer.setEditingSupport(new DescriptionObservableEditingSupport(this,
                messageManager, emfDataBindingContext));
    }

    protected void createInputTypeColumn() {
        final TreeViewerColumn typeColumnViewer = createColumnViewer(Messages.type, SWT.FILL);
        typeColumnViewer.setLabelProvider(new ContractInputTypeCellLabelProvider(propertySourceProvider, knownElements()));
        typeColumnViewer.setEditingSupport(new ContractInputTypeEditingSupport(this, propertySourceProvider, inputController));
    }

    protected void createMandatoryColumn() {
        final TreeViewerColumn mandatoryColumnViewer = createColumnViewer(Messages.nullable, SWT.CENTER);
        mandatoryColumnViewer.setLabelProvider(new NotNullableInputCheckboxLabelProvider(knownElements()));
        mandatoryColumnViewer.setEditingSupport(new CheckboxPropertyEditingSupport(propertySourceProvider, this,
                ProcessPackage.Literals.CONTRACT_INPUT__MANDATORY.getName()));
    }

    protected void createMultipleColumn() {
        final TreeViewerColumn multipleColumnViewer = createColumnViewer(Messages.multiple, SWT.CENTER);
        multipleColumnViewer.setLabelProvider(new MultipleInputCheckboxLabelProvider(knownElements()));
        multipleColumnViewer.setEditingSupport(new CheckboxPropertyEditingSupport(propertySourceProvider, this,
                ProcessPackage.Literals.CONTRACT_INPUT__MULTIPLE.getName()));
    }

    protected TreeViewerColumn createColumnViewer(final String text, final int style) {
        final TreeViewerColumn columnViewer = new TreeViewerColumn(this, style);
        final TreeColumn column = columnViewer.getColumn();
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
