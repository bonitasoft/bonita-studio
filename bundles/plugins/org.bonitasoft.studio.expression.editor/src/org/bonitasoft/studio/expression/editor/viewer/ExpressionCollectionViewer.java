/**
 * Copyright (C) 2010-2014 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.expression.editor.viewer;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.IBonitaVariableContext;
import org.bonitasoft.studio.common.widgets.MagicComposite;
import org.bonitasoft.studio.expression.editor.autocompletion.IExpressionProposalLabelProvider;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.ExpressionColumnLabelProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.model.expression.AbstractExpression;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 */
public class ExpressionCollectionViewer implements IBonitaVariableContext {

    private Composite viewerComposite;
    private TableViewer viewer;
    private int posClickX;
    private ExpressionCollectionContentProvider provider;
    private Button addRowButton;
    private Button removeRowButton;
    private final List<Listener> listeners;
    private Link switchTableModeLink;
    private boolean allowSwitchTableMode = true;
    private boolean isTableMode = true;
    private ExpressionViewer expressionEditor;
    private Composite expressionComposite;
    private MagicComposite mainComposite;

    private Object context;
    private final int minNbRow;
    private final List<String> captions;
    private final int minNbCol;
    private final boolean fixedCol;
    private final boolean fixedRow;
    private final List<ExpressionCollectionEditingSupport> editingSupports;
    private final List<IExpressionModeListener> modeListeners;
    private Button removeColButton;
    private Button addColumnButton;
    private EditingDomain editingDomain;
    private final boolean allowRowSort;
    private Button upRowSortButton;
    private Button downRowSortButton;
    private List<ViewerFilter> viewerFilters = new ArrayList<ViewerFilter>();
    private final List<IExpressionNatureProvider> viewerExprProviders = new ArrayList<IExpressionNatureProvider>();
    private boolean withConnectors = false;
    private boolean isPageFlowContext = false;
    private final SelectionAdapter removeRowListener = new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent event) {
            super.widgetSelected(event);
            if (viewer != null && viewer.getSelection() != null
                    && !viewer.getSelection().isEmpty()) {
                int indexRowToRemove = viewer.getTable()
                        .getSelectionIndex();
                removeRow(indexRowToRemove);
            }
            for (Listener l : listeners) {
                l.handleEvent(new Event());
            }
        }
    };
    private LineTableCreator lineTableCreator;
    private EMFDataBindingContext validationContext;
    private String mandatoryLabel;
    private Binding bindValue;

    public void setEditingDomain(EditingDomain editingDomain) {
        this.editingDomain = editingDomain;
        for (ExpressionCollectionEditingSupport ece : editingSupports) {
            ece.setEditingDomain(editingDomain);
        }
    }

    public ExpressionCollectionViewer(Composite composite, int nbRow,
            boolean fixedRow, int nbCol, boolean fixedCol,
            List<String> colCaptions,
            TabbedPropertySheetWidgetFactory widgetFactory,
            EditingDomain editingDomain, boolean allowSwitchTableMode,
            boolean allowRowSort) {
        this(composite, nbRow, fixedRow, nbCol, fixedCol, colCaptions, widgetFactory,
                editingDomain, allowSwitchTableMode, allowRowSort,false);
    }

    public ExpressionCollectionViewer(Composite composite, int nbRow,
            boolean fixedRow, int nbCol, boolean fixedCol,
            List<String> colCaptions, boolean allowSwitchTableMode,
            boolean allowRowSort) {
        this(composite, nbRow, fixedRow, nbCol, fixedCol, colCaptions, null,
                null, allowSwitchTableMode, allowRowSort);
    }

    public ExpressionCollectionViewer(Composite composite) {
        this(composite, 0, false, 1, true, null, null, null, true, false);
    }


    public ExpressionCollectionViewer(Composite composite, int nbRow,
            boolean fixedRow, int nbCol, boolean fixedCol,
            List<String> colCaptions,
            TabbedPropertySheetWidgetFactory widgetFactory,
            EditingDomain editingDomain, boolean allowSwitchTableMode,
            boolean allowRowSort,boolean withConnectors) {
        this.editingDomain = editingDomain;
        captions = colCaptions;
        minNbRow = nbRow;
        minNbCol = nbCol;
        listeners = new ArrayList<Listener>();
        modeListeners = new ArrayList<IExpressionModeListener>();
        this.fixedCol = fixedCol;
        this.fixedRow = fixedRow;
        editingSupports = new ArrayList<ExpressionCollectionEditingSupport>();
        this.allowSwitchTableMode = allowSwitchTableMode;
        this.allowRowSort = allowRowSort;
        this.withConnectors  = withConnectors;
        this.lineTableCreator = new LineTableCreator();
        createComposite(composite, widgetFactory);
    }

    public void setLineTableCreator(LineTableCreator lineTableCreator){
        this.lineTableCreator = lineTableCreator;
    }

    private void createComposite(Composite parent,
            TabbedPropertySheetWidgetFactory widgetFactory) {

        mainComposite = new MagicComposite(parent, SWT.NONE);
        if (widgetFactory != null) {
            widgetFactory.adapt(mainComposite);
        }

        mainComposite.setLayout(GridLayoutFactory.fillDefaults().margins(0, 0).create());

        createTableViewerComposite(widgetFactory);
        createButtonComposite(widgetFactory);

        if (allowSwitchTableMode) {
            createSwitchModeLink(widgetFactory);
        }

        viewer.getTable().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                updateButtons();
                for (Listener l : listeners) {
                    l.handleEvent(new Event());
                }
            }
        });
        viewer.getTable().addMouseListener(new MouseListener() {

            @Override
            public void mouseUp(MouseEvent e) {

            }

            @Override
            public void mouseDown(MouseEvent e) {
                posClickX = e.x;
            }

            @Override
            public void mouseDoubleClick(MouseEvent e) {

            }
        });

        updateButtons();
    }

    protected void createTableViewerComposite(
            TabbedPropertySheetWidgetFactory widgetFactory) {
        if (widgetFactory != null) {
            viewerComposite = widgetFactory.createComposite(mainComposite,
                    SWT.NONE);
        } else {
            viewerComposite = new Composite(mainComposite, SWT.NONE);
        }

        viewerComposite.setLayoutData(GridDataFactory.fillDefaults().indent(12, 0).grab(true, true).create());
        viewerComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());
        Table table = new Table(viewerComposite, SWT.BORDER| SWT.FULL_SELECTION);
        viewer = new TableViewer(table);
        viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 70).create());
        viewer.getTable().setLinesVisible(true);
        viewer.getTable().setHeaderVisible(captions != null && !captions.isEmpty());

        provider = new ExpressionCollectionContentProvider();
        viewer.setContentProvider(provider);

        for (int i = 0; i < Math.max(minNbCol, getNbCols()); i++) {
            addColumnToViewer(i);
        }
        viewer.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDown(MouseEvent e) {
                if(minNbRow == 0 || getNbRows() < minNbRow){
                    Point point = new Point(e.x,e.y);
                    TableItem item = viewer.getTable().getItem(point);
                    int currentWidth = 0;
                    int colIndex = 0;
                    for(int i = 0; i<viewer.getTable().getColumns().length ; i++){
                        TableColumn c = viewer.getTable().getColumns()[i];
                        if(point.x >= currentWidth && point.x  < currentWidth + c.getWidth()){
                            colIndex = i;
                            break;
                        }
                        currentWidth = currentWidth + c.getWidth();
                    }
                    if(item == null){
                        addRow(colIndex);
                    }
                }
            }
        });

    }

    protected void createButtonComposite(
            TabbedPropertySheetWidgetFactory widgetFactory) {
        Composite buttonComposite = null;
        if (widgetFactory != null) {
            buttonComposite = widgetFactory.createComposite(viewerComposite,
                    SWT.NONE);
        } else {
            buttonComposite = new Composite(viewerComposite, SWT.NONE);
        }

        RowLayout rl = new RowLayout();
        rl.spacing = 5;
        rl.fill = true;
        rl.type = SWT.VERTICAL;
        buttonComposite.setLayout(rl);
        fillButtonComposite(widgetFactory, buttonComposite);
    }

    protected void fillButtonComposite(
            TabbedPropertySheetWidgetFactory widgetFactory,
            Composite buttonComposite) {
        if (!fixedRow) {
            createRowbuttons(widgetFactory, buttonComposite);
        } else {
            Composite filler = new Composite(viewerComposite, SWT.NONE);
            filler.setLayoutData(new GridData(0, 0));
        }

        if (!fixedCol) {
            createColButtons(widgetFactory, buttonComposite);
        } else {
            Composite filler = new Composite(viewerComposite, SWT.NONE);
            filler.setLayoutData(new GridData(0, 0));
        }

        if (allowRowSort) {
            createRowSortButtons(widgetFactory, buttonComposite);
        }

        if (fixedCol && !fixedRow) {
            Composite filler = new Composite(viewerComposite, SWT.NONE);
            filler.setLayoutData(new GridData(0, 0));
        }
    }

    private void createSwitchModeLink(
            TabbedPropertySheetWidgetFactory widgetFactory) {
        if (widgetFactory != null) {
            expressionComposite = widgetFactory.createComposite(mainComposite,
                    SWT.NONE);
        } else {
            expressionComposite = new Composite(mainComposite, SWT.NONE);
        }

        expressionComposite.setLayout(new GridLayout(1, false));
        expressionComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
        expressionEditor = new ExpressionViewer(expressionComposite,
                SWT.BORDER, widgetFactory, null,null,withConnectors);
        expressionEditor.setIsPageFlowContext(isPageFlowContext);
        expressionEditor.getControl().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).create());

        switchTableModeLink = new Link(mainComposite, SWT.NONE);
        switchTableModeLink.setLayoutData(GridDataFactory.fillDefaults()
                .create());
        if (widgetFactory != null) {
            widgetFactory.adapt(switchTableModeLink, false, false);
        }
        switchTableModeLink.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                switchTableMode();
            }
        });
    }

    private void initializeTableOrExpression(boolean isTableMode) {
        this.isTableMode = isTableMode;
        if (isTableMode) {
            if (allowSwitchTableMode) {
                switchTableModeLink.setText("<A>" + Messages.editAsExpression
                        + "</A>");
                expressionComposite.setData(MagicComposite.HIDDEN, true);
                expressionComposite.setVisible(false);
            }
            viewerComposite.setData(MagicComposite.HIDDEN, false);
            viewerComposite.setVisible(true);
        } else {
            if (allowSwitchTableMode) {
                switchTableModeLink.setText("<A>" + Messages.editAsTable
                        + "</A>");
                expressionComposite.setData(MagicComposite.HIDDEN, false);
                expressionComposite.setVisible(true);
            }
            viewerComposite.setData(MagicComposite.HIDDEN, true);
            viewerComposite.setVisible(false);
        }

    }

    protected MultiValidator createListExpressionValidator() {
        final ListExpression listExpression = (ListExpression) getValue();
        final IObservableValue listValue = EMFObservables.observeValue(listExpression, ExpressionPackage.Literals.LIST_EXPRESSION__EXPRESSIONS);
        return new MultiValidator() {

            @Override
            protected IStatus validate() {
                if(isTableMode()){
                    AbstractExpression expression = getValue();
                    if(expression instanceof ListExpression){
                        listValue.getValue();
                        if(listExpression.getExpressions() == null || listExpression.getExpressions().isEmpty()){
                            return ValidationStatus.error(Messages.bind(Messages.AtLeastOneRowShouldBeAddedFor,mandatoryLabel));
                        }
                    }
                }
                return ValidationStatus.ok();
            }
        };
    }

    protected MultiValidator createTableExpressionValidator() {
        final TableExpression tableExpression = (TableExpression) getValue();
        final IObservableValue tableValue = EMFObservables.observeValue(tableExpression, ExpressionPackage.Literals.TABLE_EXPRESSION__EXPRESSIONS);
        return new MultiValidator() {

            @Override
            protected IStatus validate() {
                if(isTableMode()){
                    AbstractExpression expression = getValue();
                    if(expression instanceof TableExpression){
                        tableValue.getValue();
                        if(tableExpression.getExpressions() == null || tableExpression.getExpressions().isEmpty()){
                            return ValidationStatus.error(Messages.bind(Messages.AtLeastOneRowShouldBeAddedFor,mandatoryLabel));
                        }
                    }
                }
                return ValidationStatus.ok();
            }
        };
    }

    private void createRowSortButtons(
            TabbedPropertySheetWidgetFactory widgetFactory,
            Composite buttonComposite) {
        if (widgetFactory != null) {
            upRowSortButton = widgetFactory.createButton(buttonComposite,
                    Messages.upRowSort, SWT.FLAT);
        } else {
            upRowSortButton = new Button(buttonComposite, SWT.FLAT);
            upRowSortButton.setText(Messages.upRowSort);
        }
        upRowSortButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                if (viewer != null && viewer.getSelection() != null
                        && !viewer.getSelection().isEmpty()) {
                    int indexRowToMoveUp = viewer.getTable()
                            .getSelectionIndex();
                    moveUpRow(indexRowToMoveUp);
                }
                for (Listener l : listeners) {
                    l.handleEvent(new Event());
                }

            }
        });

        if (widgetFactory != null) {
            downRowSortButton = widgetFactory.createButton(buttonComposite,
                    Messages.downRowSort, SWT.FLAT);
        } else {
            downRowSortButton = new Button(buttonComposite, SWT.FLAT);
            downRowSortButton.setText(Messages.downRowSort);
        }
        downRowSortButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                if (viewer != null && viewer.getSelection() != null
                        && !viewer.getSelection().isEmpty()) {
                    int indexRowToMoveDown = viewer.getTable()
                            .getSelectionIndex();
                    moveDownRow(indexRowToMoveDown);
                }
                for (Listener l : listeners) {
                    l.handleEvent(new Event());
                }
            }
        });

    }

    protected void moveUpRow(int indexRowToMoveUp) {
        if (indexRowToMoveUp > 0) {
            int targetIndex = indexRowToMoveUp - 1;
            moveRowFromTo(indexRowToMoveUp, targetIndex);
        }
    }

    protected void moveDownRow(int indexRowToMoveUp) {
        if (indexRowToMoveUp < getNbRows()) {
            int targetIndex = indexRowToMoveUp + 1;
            moveRowFromTo(indexRowToMoveUp, targetIndex);
        }
    }

    protected void moveRowFromTo(int indexRowToMoveUp, int targetIndex) {
        AbstractExpression expression = (AbstractExpression) viewer.getInput();
        if (editingDomain == null) {
            editingDomain = TransactionUtil.getEditingDomain(expression);
        }
        if (editingDomain != null) {
            if (expression instanceof ListExpression) {
                Expression expressionToMoveUp = ((ListExpression) expression)
                        .getExpressions().get(indexRowToMoveUp);
                Command moveCommand = MoveCommand
                        .create(editingDomain,
                                expression,
                                ExpressionPackage.Literals.LIST_EXPRESSION__EXPRESSIONS,
                                expressionToMoveUp, targetIndex);
                editingDomain.getCommandStack().execute(moveCommand);
            } else if (expression instanceof TableExpression) {
                ListExpression expressionToMoveUp = ((TableExpression) expression)
                        .getExpressions().get(indexRowToMoveUp);
                Command moveCommand = MoveCommand
                        .create(editingDomain,
                                expression,
                                ExpressionPackage.Literals.TABLE_EXPRESSION__EXPRESSIONS,
                                expressionToMoveUp, targetIndex);
                editingDomain.getCommandStack().execute(moveCommand);

            }
        } else {
            if (expression instanceof ListExpression) {
                ((ListExpression) expression).getExpressions().move(
                        indexRowToMoveUp, targetIndex);
            } else if (expression instanceof TableExpression) {
                ((TableExpression) expression).getExpressions().move(
                        indexRowToMoveUp, targetIndex);
            }
        }
        refresh();
    }

    protected void addRow(final int colIndexToEdit) {
        Object expressionInput = viewer.getInput();
        AbstractExpression newExpression = null;
        if (expressionInput instanceof ListExpression) {
            newExpression = addElementInListExpression(expressionInput);
        } else if (expressionInput instanceof TableExpression) {
            newExpression = addLineInTableExpression(expressionInput);
        }

        refresh();
        putCursorOnNewElement(colIndexToEdit, newExpression);
    }

    private AbstractExpression addElementInListExpression(Object expressionInput) {
        AbstractExpression newExpression;
        newExpression = ExpressionFactory.eINSTANCE.createExpression();
        ((ListExpression) expressionInput).getExpressions().add((Expression) newExpression);
        return newExpression;
    }

    private AbstractExpression addLineInTableExpression(Object expressionInput) {
        AbstractExpression newExpression;
        ListExpression rowExp = createListExpressionForNewLineInTable();
        if (editingDomain == null) {
            editingDomain = TransactionUtil.getEditingDomain(expressionInput);
        }
        if (editingDomain != null) {
            editingDomain
            .getCommandStack()
            .execute(
                    AddCommand
                    .create(editingDomain,
                            expressionInput,
                            ExpressionPackage.Literals.TABLE_EXPRESSION__EXPRESSIONS,
                            rowExp));
        } else {
            ((TableExpression) expressionInput).getExpressions().add(rowExp);
        }
        newExpression = rowExp;
        return newExpression;
    }

    private ListExpression createListExpressionForNewLineInTable() {
        return lineTableCreator.createListExpressionForNewLineInTable(Math.max(getNbCols(), minNbCol));
    }

    private void putCursorOnNewElement(final int colIndexToEdit, AbstractExpression newExpression) {
        final AbstractExpression expressionToEdit= newExpression;
        Display.getDefault().asyncExec(new Runnable() {

            @Override
            public void run() {
                if (!viewer.getControl().isDisposed()) {
                    viewer.getTable().layout();
                    viewer.refresh();
                    viewer.editElement(expressionToEdit, colIndexToEdit);
                }
                updateButtons();
            }
        });
    }

    private void createColButtons(
            TabbedPropertySheetWidgetFactory widgetFactory,
            Composite buttonComposite) {
        if (widgetFactory != null) {
            Label separator = widgetFactory.createSeparator(buttonComposite,
                    SWT.SEPARATOR | SWT.HORIZONTAL);
            separator.setLayoutData(new RowData(2, 20));
        }

        if (widgetFactory != null) {
            addColumnButton = widgetFactory.createButton(buttonComposite,
                    Messages.addColumn, SWT.FLAT);
        } else {
            addColumnButton = new Button(buttonComposite, SWT.FLAT);
            addColumnButton.setText(Messages.addColumn);
        }
        addColumnButton.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                if(!viewerFilters.isEmpty()){
                    viewerFilters.add(viewerFilters.get(viewerFilters.size()-1));
                }
                Expression newExp = addColumn(getNbCols());
                refresh();
                viewer.editElement(newExp, getNbCols() - 1);
            }
        });

        if (widgetFactory != null) {
            removeColButton = widgetFactory.createButton(buttonComposite,
                    Messages.removeColumn, SWT.FLAT);
        } else {
            removeColButton = new Button(buttonComposite, SWT.FLAT);
            removeColButton.setText(Messages.removeColumn);
        }
        removeColButton.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                if (viewer != null && viewer.getSelection() != null
                        && !viewer.getSelection().isEmpty()) {
                    int indexColumnToRemove = posClickX / viewer.getTable().getColumns()[0].getWidth();
                    removeColumn(indexColumnToRemove);
                }
                for (Listener l : listeners) {
                    l.handleEvent(new Event());
                }
            }
        });
    }

    private void createRowbuttons(
            TabbedPropertySheetWidgetFactory widgetFactory,
            Composite buttonComposite) {
        if (widgetFactory != null) {
            addRowButton = widgetFactory.createButton(buttonComposite,
                    Messages.addRow, SWT.FLAT);
        } else {
            addRowButton = new Button(buttonComposite, SWT.FLAT);
            addRowButton.setText(Messages.addRow);
        }

        addRowButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                addRow(0);
            }


        });

        removeRowButton = null;
        if (widgetFactory != null) {
            removeRowButton = widgetFactory.createButton(buttonComposite,
                    Messages.removeRow, SWT.FLAT);
        } else {
            removeRowButton = new Button(buttonComposite, SWT.FLAT);
            removeRowButton.setText(Messages.removeRow);
        }


        removeRowButton.addSelectionListener(removeRowListener);
    }

    protected void switchTableMode() {
        updateTableOrExpressionMode(!isTableMode);
    }

    private void updateColumns() {
        editingSupports.clear();
        for (TableColumn c : viewer.getTable().getColumns()) {
            c.dispose();
        }
        if (getNbRows() > 0) {
            for (int i = 0; i < Math.max(minNbCol, getNbCols()); i++) {
                addColumnToViewer(i);
            }
        }
    }

    protected void updateTableOrExpressionMode(boolean isTableMode) {
        initializeTableOrExpression(isTableMode);
        Point defaultSize = mainComposite.getSize();
        Point size = mainComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
        mainComposite.setSize(defaultSize.x, size.y);
        mainComposite.layout(true, true);

        for (Listener listener : listeners) {
            listener.handleEvent(null);
        }
        for (IExpressionModeListener l : modeListeners) {
            if (isTableMode) {
                l.useTable();
            } else {
                l.useSimpleExpression();
            }
        }
    }

    protected void addColumnToViewer(int index) {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.FILL);

        if (captions != null && captions.size() > index
                && captions.get(index) != null) {
            column.getColumn().setText(captions.get(index));
        }

        final ExpressionCollectionEditingSupport editingSupport = new ExpressionCollectionEditingSupport(
                column.getViewer(), index, editingDomain,removeRowListener);
        if (context != null) {
            editingSupport.setInput(context);
        }
        if (viewerFilters != null && viewerFilters.size() > index) {
            ViewerFilter filter = viewerFilters.get(index);
            if (filter != null) {
                editingSupport.addFilter(filter);
            }
            if (viewerExprProviders != null
                    && viewerExprProviders.size() > index) {
                IExpressionNatureProvider provider = viewerExprProviders
                        .get(index);
                if (provider != null) {
                    editingSupport.setExpressionNatureProvider(provider);
                }
            }
        }
        editingSupports.add(index, editingSupport);
        column.setEditingSupport(editingSupport);
        column.setLabelProvider(new ExpressionColumnLabelProvider(index));

        int weight = (int) (((float) 1 / (float) viewer.getTable()
                .getColumnCount()) * 100);
        TableLayout layout = new TableLayout();
        for (int i = 0; i < viewer.getTable().getColumns().length; i++) {
            layout.addColumnData(new ColumnWeightData(weight));
        }
        viewer.getTable().setLayout(layout);

        refresh();
    }

    private Expression addColumn(int index) {
        TableExpression tableExpression = (TableExpression) viewer.getInput();
        EList<ListExpression> expressions = tableExpression.getExpressions();
        Expression exp = null;
        if (editingDomain == null) {
            editingDomain = TransactionUtil.getEditingDomain(expressions);
        }
        if (editingDomain != null) {
            CompoundCommand cc = new CompoundCommand("Add column at index "
                    + index);
            for (ListExpression listExpression : expressions) {
                exp = ExpressionFactory.eINSTANCE.createExpression();
                cc.append(AddCommand
                        .create(editingDomain,
                                listExpression,
                                ExpressionPackage.Literals.LIST_EXPRESSION__EXPRESSIONS,
                                exp, index));
            }
            editingDomain.getCommandStack().execute(cc);
        } else {
            for (ListExpression listExpression : expressions) {
                exp = ExpressionFactory.eINSTANCE.createExpression();
                EList<Expression> subListExpression = listExpression
                        .getExpressions();
                subListExpression.add(exp);
            }
        }
        addColumnToViewer(index);
        mainComposite.layout(true, true);
        return exp;
    }

    private void removeColumn(int indexColumnToRemove) {
        TableExpression tableExpression = (TableExpression) viewer.getInput();
        EList<ListExpression> expressions = tableExpression.getExpressions();
        if (editingDomain == null) {
            editingDomain = TransactionUtil.getEditingDomain(expressions);
        }
        if (editingDomain != null) {
            CompoundCommand cc = new CompoundCommand(
                    "Remove column of a TableExpression");
            for (ListExpression listExpression : expressions) {
                if(indexColumnToRemove < listExpression.getExpressions().size()){
                    Expression expressionToRemove = listExpression.getExpressions()
                            .get(indexColumnToRemove);
                    Command removeCommand = RemoveCommand
                            .create(editingDomain,
                                    listExpression,
                                    ExpressionPackage.Literals.LIST_EXPRESSION__EXPRESSIONS,
                                    expressionToRemove);
                    cc.append(removeCommand);
                }
            }
            editingDomain.getCommandStack().execute(cc);
        } else {
            for (ListExpression listExpression : expressions) {
                listExpression.getExpressions().remove(indexColumnToRemove);
            }
        }

        updateColumns();
        refresh();
    }

    private void removeRow(int indexRowToRemove) {
        AbstractExpression expression = (AbstractExpression) viewer.getInput();
        if (editingDomain == null) {
            editingDomain = TransactionUtil.getEditingDomain(expression);
        }
        if (editingDomain != null) {
            if (expression instanceof ListExpression) {
                Expression expressionToRemove = ((ListExpression) expression)
                        .getExpressions().get(indexRowToRemove);
                editingDomain
                .getCommandStack()
                .execute(
                        RemoveCommand
                        .create(editingDomain,
                                expression,
                                ExpressionPackage.Literals.LIST_EXPRESSION__EXPRESSIONS,
                                expressionToRemove));
            } else if (expression instanceof TableExpression) {
                ListExpression expressionToRemove = ((TableExpression) expression)
                        .getExpressions().get(indexRowToRemove);
                Command removeCommand = RemoveCommand
                        .create(editingDomain,
                                expression,
                                ExpressionPackage.Literals.TABLE_EXPRESSION__EXPRESSIONS,
                                expressionToRemove);
                editingDomain.getCommandStack().execute(removeCommand);

            }
        } else {
            if (expression instanceof ListExpression) {
                ((ListExpression) expression).getExpressions().remove(
                        indexRowToRemove);
            } else if (expression instanceof TableExpression) {
                ((TableExpression) expression).getExpressions().remove(
                        indexRowToRemove);
            }
        }
        refresh();
    }

    public void refresh() {
        Display.getDefault().asyncExec(new Runnable() {

            @Override
            public void run() {
                if (!viewer.getControl().isDisposed()) {
                    viewer.getTable().layout();
                    viewer.refresh();
                }
                updateButtons();
            }
        });
    }

    protected void updateButtons() {
        if (addRowButton != null && !addRowButton.isDisposed()) {
            addRowButton.setEnabled(viewer.getTable().isEnabled()
                    && (minNbRow == 0 || getNbRows() < minNbRow));
        }
        if (removeRowButton != null && !removeRowButton.isDisposed() &&  viewer.getSelection() != null) {
            removeRowButton.setEnabled(viewer.getTable().isEnabled()
                    && (!viewer.getSelection().isEmpty() || viewer
                            .isCellEditorActive()));
        }
        if (addColumnButton != null && !addColumnButton.isDisposed()) {
            addColumnButton.setEnabled(getNbRows() > 0);
        }

        if (removeColButton != null  && !removeColButton.isDisposed() && viewer.getSelection() != null) {
            removeColButton.setEnabled(getNbRows() > 0
                    && getNbCols() > 0
                    && getNbCols() > minNbCol
                    && (!viewer.getSelection().isEmpty() || viewer
                            .isCellEditorActive()));
        }
        if (upRowSortButton != null && !upRowSortButton.isDisposed()) {
            upRowSortButton.setEnabled(!viewer.getSelection().isEmpty()
                    || viewer.isCellEditorActive());
        }

        if (downRowSortButton != null && !downRowSortButton.isDisposed()) {
            downRowSortButton.setEnabled(!viewer.getSelection().isEmpty()
                    || viewer.isCellEditorActive());
        }
    }

    /**
     * @return either the List<String> describing a table, or a groovy
     *         expression returning a List<Object>
     */
    public AbstractExpression getValue() {
        if (isTableMode) {
            return (AbstractExpression) viewer.getInput();
        } else {
            return (AbstractExpression) ((IStructuredSelection) expressionEditor
                    .getSelection()).getFirstElement();
        }
    }

    /**
     * @param gridData
     */
    public void setLayoutData(Object layoutData) {
        mainComposite.setLayoutData(layoutData);
        refresh();
    }

    /**
     * @param listener
     */
    public void addModifyListener(Listener listener) {
        listeners.add(listener);
        if (expressionEditor != null) {
            expressionEditor.getTextControl().addListener(SWT.Modify, listener);
        }
    }

    public TableViewer getViewer() {
        return viewer;
    }

    public boolean isTableMode(){
        return isTableMode;
    }

    public boolean isEmpty() {
        if (isTableMode) {
            AbstractExpression value = getValue();
            if (value instanceof ListExpression) {
                return ((ListExpression) value).getExpressions().isEmpty();
            } else if (value instanceof TableExpression) {
                return ((TableExpression) value).getExpressions().isEmpty();
            }
            return true;
        } else {
            AbstractExpression value = getValue();
            if (value instanceof Expression) {
                return ((Expression) value).getContent() == null
                        || ((Expression) value).getContent().isEmpty();
            }
            return true;
        }
    }


    public void setSelection(AbstractExpression input) {
        if(validationContext != null){
            if(bindValue != null){
                validationContext.removeBinding(bindValue);
                bindValue.dispose();
                bindValue = null;
            }
            if(expressionEditor != null){
                expressionEditor.setMandatoryField(null, validationContext);
            }
        }

        if (input instanceof Expression) {
            initializeTableOrExpression(false);
            expressionEditor.setMandatoryField(mandatoryLabel, validationContext);
            expressionEditor.setSelection(new StructuredSelection(input));
        } else if (input instanceof ListExpression
                || input instanceof TableExpression) {
            if(expressionEditor != null){
                expressionEditor.setSelection(new StructuredSelection(ExpressionFactory.eINSTANCE.createExpression()));
            }
            initializeTableOrExpression(true);
            viewer.setInput(input);
            if (input instanceof TableExpression
                    && !((TableExpression) input).getExpressions().isEmpty()) {
                updateColumns();
            }
            if(validationContext != null){
                IObservableValue observeValue = null;
                if(input instanceof ListExpression){
                    observeValue = EMFObservables.observeValue(input, ExpressionPackage.Literals.LIST_EXPRESSION__EXPRESSIONS);
                }else{
                    observeValue = EMFObservables.observeValue(input, ExpressionPackage.Literals.TABLE_EXPRESSION__EXPRESSIONS);
                }
                UpdateValueStrategy strategy = new UpdateValueStrategy();
                strategy.setAfterGetValidator(new IValidator() {

                    @Override
                    public IStatus validate(Object value) {
                        if(isTableMode()){
                            AbstractExpression expression = getValue();
                            if(expression instanceof ListExpression){
                                if(((ListExpression) expression).getExpressions() == null || ((ListExpression) expression).getExpressions().isEmpty()){
                                    return ValidationStatus.error(Messages.bind(Messages.AtLeastOneRowShouldBeAddedFor,mandatoryLabel));
                                }
                            }
                            if(expression instanceof TableExpression){
                                if(((TableExpression) expression).getExpressions() == null || ((TableExpression) expression).getExpressions().isEmpty()){
                                    return ValidationStatus.error(Messages.bind(Messages.AtLeastOneRowShouldBeAddedFor,mandatoryLabel));
                                }
                            }
                        }
                        return ValidationStatus.ok();
                    }
                });
                bindValue = validationContext.bindValue(new WritableValue(), observeValue,strategy,strategy);
            }
        }
    }


    public void addFilter(ViewerFilter viewerFilter) {
        viewerFilters.add(viewerFilter);
        if (expressionEditor != null) {
            expressionEditor.addFilter(viewerFilter);
        }
    }


    public void removeFilter(ViewerFilter viewerFilter) {
        viewerFilters.remove(viewerFilter);
        if (expressionEditor != null) {
            expressionEditor.removeFilter(viewerFilter);
        }
    }

    public void setInput(Object input) {
        context = input;
        if(expressionEditor != null){
            expressionEditor.setInput(context);
        }
        for (ExpressionCollectionEditingSupport es : editingSupports) {
            es.setInput(context);
            if(viewerFilters.size() > editingSupports.indexOf(es)  ){
                ViewerFilter filter = viewerFilters.get(editingSupports.indexOf(es));
                if(filter != null){
                    es.addFilter(filter);
                }

            }
            if(viewerExprProviders.size() > editingSupports.indexOf(es)  ){
                IExpressionNatureProvider natureProvider = viewerExprProviders.get(editingSupports.indexOf(es));
                if(natureProvider != null){
                    es.setExpressionNatureProvider(natureProvider);
                }

            }

        }
    }


    public void addExpressionModeListener(IExpressionModeListener listener) {
        modeListeners.add(listener);
    }

    public void removeExpressionModeListener(IExpressionModeListener listener) {
        modeListeners.remove(listener);
    }


    public Button getAddRowButton() {
        return addRowButton;
    }

    public void setViewerFilters(List<ViewerFilter> viewerFilters) {
        this.viewerFilters = viewerFilters;
        if(expressionEditor != null){
            for(ViewerFilter filter : viewerFilters){
                expressionEditor.addFilter(filter);
            }
        }
    }

    public Button getRemoveRowButton() {
        return removeRowButton;
    }

    private int getNbCols() {
        Object input = viewer.getInput();
        if (input != null) {
            if (input instanceof ListExpression) {
                return 1;
            } else {
                EList<ListExpression> expressions = ((TableExpression) input)
                        .getExpressions();
                return expressions.isEmpty() ? 0 : expressions.get(0)
                        .getExpressions().size();
            }
        } else {
            return 0;
        }
    }

    private int getNbRows() {
        Object input = viewer.getInput();
        if (input != null) {
            if (input instanceof TableExpression) {
                return ((TableExpression) input).getExpressions().size();
            } else {
                return ((ListExpression) input).getExpressions().size();
            }
        } else {
            return 0;
        }
    }

    public void setAddRowLabel(String addCorrelation) {
        if(addRowButton != null){
            addRowButton.setText(addCorrelation);
        }
    }

    public void setRemoveRowLabel(String removeCorrelation) {
        if(removeRowButton != null){
            removeRowButton.setText(removeCorrelation);
        } 
    }

    public void addExpressionNatureProvider(IExpressionNatureProvider expressionNatureProvider) {
        viewerExprProviders.add(expressionNatureProvider);
        if(editingSupports != null && !editingSupports.isEmpty()){
            for(int i = 0 ; i< editingSupports.size() ;i++){
                if(i < viewerExprProviders.size()){
                    editingSupports.get(i).setExpressionNatureProvider(viewerExprProviders.get(i));
                }
            }
        }
    }

    public void removeExpressionNatureProvider(int index){
        if(editingSupports != null && !editingSupports.isEmpty()){
            if(index < editingSupports.size()){
                editingSupports.get(index).setExpressionNatureProvider(null);
            }
        }
        if(index < viewerExprProviders.size()){
            viewerExprProviders.remove(index);
        }
    }

    public void setExpressionProposalLableProvider(int colIndex,IExpressionProposalLabelProvider expressionProposalLabelProvider) {
        if(editingSupports != null && !editingSupports.isEmpty()){
            if(colIndex >= 0 && colIndex < editingSupports.size()){
                editingSupports.get(colIndex).setExpressionProposalLableProvider(expressionProposalLabelProvider);
            }
        }
    }

    @Override
    public boolean isPageFlowContext() {
        return isPageFlowContext;
    }

    @Override
    public void setIsPageFlowContext(boolean isPageFlowContext) {
        this.isPageFlowContext = isPageFlowContext;
    }



    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#isOverViewContext()
     */
    @Override
    public boolean isOverViewContext() {
        return false;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsOverviewContext(boolean)
     */
    @Override
    public void setIsOverviewContext(boolean isOverviewContext) {
    }

    public void setMandatoryField(final String label, EMFDataBindingContext context) {
        expressionEditor.setMandatoryField(label, context);
        this.mandatoryLabel = label;
        this.validationContext = context;

    }

}

