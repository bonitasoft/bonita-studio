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
package org.bonitasoft.studio.contract.ui.property.input;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.contract.core.ContractDefinitionValidator;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.contract.ui.property.CharriageColumnViewerEditorActivationStrategy;
import org.bonitasoft.studio.contract.ui.property.FieldDecoratorProvider;
import org.bonitasoft.studio.contract.ui.property.input.edit.CheckboxPropertyEditingSupport;
import org.bonitasoft.studio.contract.ui.property.input.edit.ContractInputTypeEditingSupport;
import org.bonitasoft.studio.contract.ui.property.input.edit.DescriptionPropertyEditingSupport;
import org.bonitasoft.studio.contract.ui.property.input.edit.InputNamePropertyEditingSupport;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.databinding.viewers.ObservableListTreeContentProvider;
import org.eclipse.jface.viewers.CellNavigationStrategy;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DecoratingStyledCellLabelProvider;
import org.eclipse.jface.viewers.DecorationContext;
import org.eclipse.jface.viewers.FocusCellOwnerDrawHighlighter;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.TreeViewerEditor;
import org.eclipse.jface.viewers.TreeViewerFocusCellManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * @author Romain Bioteau
 *
 */
public class ContractInputTreeViewer extends TreeViewer {

    private AdapterFactoryContentProvider propertySourceProvider;
    private AdapterFactoryLabelProvider adapterFactoryLabelProvider;
    private ContractDefinitionValidator contractValidator;
    private final FieldDecoratorProvider decoratorProvider = new FieldDecoratorProvider();
    private ContractInputController inputController;

    public ContractInputTreeViewer(final Composite parent, final FormToolkit toolkit) {
        super(toolkit.createTree(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI));
        getTree().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_CONTRACT_INPUT_TREE);
    }

    public void initialize(final ContractInputController inputController, final ContractDefinitionValidator contractValidator) {
        this.contractValidator = contractValidator;
        this.inputController = inputController;
        final ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        propertySourceProvider = new AdapterFactoryContentProvider(composedAdapterFactory);
        adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(composedAdapterFactory);
        getTree().setHeaderVisible(true);
        getTree().setLinesVisible(true);
        addFilter(new ComplexTypeChildrenViewerFilter());
        setContentProvider(new ObservableListTreeContentProvider(new ContractInputObservableFactory(),
                new ContractInputTreeStructureAdvisor()));


        final CellNavigationStrategy cellNavigationStrategy = new ContracInputTreeViewerCellNavigationStrategy(this, inputController);
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
        getTree().setLayout(tableLayout);
    }

    protected void createInputNameColumn() {
        final TreeViewerColumn nameColumnViewer = createColumnViewer(Messages.name + " *", SWT.FILL);
        nameColumnViewer.setLabelProvider(new DecoratingStyledCellLabelProvider(new InputNameCellLabelProvider(propertySourceProvider),
                new ValidationLabelDecorator(), new DecorationContext()));
        nameColumnViewer.setEditingSupport(new InputNamePropertyEditingSupport(propertySourceProvider,
                this,
                adapterFactoryLabelProvider,
                contractValidator,
                decoratorProvider));
    }

    protected void createInputDescriptionColumn() {
        final TreeViewerColumn descriptionColumnViewer = createColumnViewer(Messages.description, SWT.FILL);
        descriptionColumnViewer.setLabelProvider(new DescriptionCellLabelProvider(this, propertySourceProvider));
        descriptionColumnViewer.setEditingSupport(new DescriptionPropertyEditingSupport(this, propertySourceProvider, contractValidator));
    }

    //    protected void createMappingColumn() {
    //        final TreeViewerColumn mappingColumnViewer = createColumnViewer(Messages.savedInto,SWT.FILL);
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
        final TreeViewerColumn typeColumnViewer = createColumnViewer(Messages.type, SWT.FILL);
        typeColumnViewer.setLabelProvider(new ContractInputTypeCellLabelProvider(propertySourceProvider));
        typeColumnViewer.setEditingSupport(new ContractInputTypeEditingSupport(this, propertySourceProvider, inputController));
    }

    protected void createMandatoryColumn() {
        final TreeViewerColumn mandatoryColumnViewer = createColumnViewer(Messages.mandatory, SWT.CENTER);
        mandatoryColumnViewer.setLabelProvider(new MandatoryInputCheckboxLabelProvider(getControl()));
        mandatoryColumnViewer.setEditingSupport(new CheckboxPropertyEditingSupport(propertySourceProvider, this,
                ProcessPackage.Literals.CONTRACT_INPUT__MANDATORY.getName()));
    }

    protected void createMultipleColumn() {
        final TreeViewerColumn multipleColumnViewer = createColumnViewer(Messages.multiple, SWT.CENTER);
        multipleColumnViewer.setLabelProvider(new MultipleInputCheckboxLabelProvider(getControl()));
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
