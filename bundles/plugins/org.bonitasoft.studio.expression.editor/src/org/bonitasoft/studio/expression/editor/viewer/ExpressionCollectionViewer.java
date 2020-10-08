/**
 * Copyright (C) 2010-2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.expression.editor.viewer;

import java.util.ArrayList;
import java.util.Collections;
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
import org.eclipse.emf.ecore.EObject;
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

    private EObject context;
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
    private List<ViewerFilter> viewerFilters = new ArrayList<>();
    private final List<IExpressionNatureProvider> viewerExprProviders = new ArrayList<>();
    private boolean withConnectors = false;
    private boolean isPageFlowContext = false;
    private final SelectionAdapter removeRowListener = new SelectionAdapter() {

        @Override
        public void widgetSelected(final SelectionEvent event) {
            super.widgetSelected(event);
            if (viewer != null && viewer.getSelection() != null
                    && !viewer.getSelection().isEmpty()) {
                final int indexRowToRemove = viewer.getTable()
                        .getSelectionIndex();
                removeRow(indexRowToRemove);
            }
            for (final Listener l : listeners) {
                l.handleEvent(new Event());
            }
        }
    };
    private LineTableCreator lineTableCreator;
    private EMFDataBindingContext validationContext;
    private String mandatoryLabel;
    private Binding bindValue;
    private final List<IAddLineListener> addLineLineListeners = new ArrayList<>();
    private Object input;

    public void setEditingDomain(final EditingDomain editingDomain) {
        this.editingDomain = editingDomain;
        for (final ExpressionCollectionEditingSupport ece : editingSupports) {
            ece.setEditingDomain(editingDomain);
        }
    }

    public ExpressionCollectionViewer(final Composite composite, final int nbRow,
            final boolean fixedRow, final int nbCol, final boolean fixedCol,
            final List<String> colCaptions,
            final TabbedPropertySheetWidgetFactory widgetFactory,
            final EditingDomain editingDomain, final boolean allowSwitchTableMode,
            final boolean allowRowSort) {
        this(composite, nbRow, fixedRow, nbCol, fixedCol, colCaptions, widgetFactory,
                editingDomain, allowSwitchTableMode, allowRowSort, false);
    }

    public ExpressionCollectionViewer(final Composite composite, final int nbRow,
            final boolean fixedRow, final int nbCol, final boolean fixedCol,
            final List<String> colCaptions, final boolean allowSwitchTableMode,
            final boolean allowRowSort) {
        this(composite, nbRow, fixedRow, nbCol, fixedCol, colCaptions, null,
                null, allowSwitchTableMode, allowRowSort);
    }

    public ExpressionCollectionViewer(final Composite composite, final int nbRow,
            final boolean fixedRow, final int nbCol, final boolean fixedCol,
            final List<String> colCaptions,
            final TabbedPropertySheetWidgetFactory widgetFactory,
            final EditingDomain editingDomain, final boolean allowSwitchTableMode,
            final boolean allowRowSort, final boolean withConnectors) {
        this.editingDomain = editingDomain;
        captions = colCaptions;
        minNbRow = nbRow;
        minNbCol = nbCol;
        listeners = new ArrayList<>();
        modeListeners = new ArrayList<>();
        this.fixedCol = fixedCol;
        this.fixedRow = fixedRow;
        editingSupports = new ArrayList<>();
        this.allowSwitchTableMode = allowSwitchTableMode;
        this.allowRowSort = allowRowSort;
        this.withConnectors = withConnectors;
        lineTableCreator = new LineTableCreator();
        createComposite(composite, widgetFactory);
    }

    public void setLineTableCreator(final LineTableCreator lineTableCreator) {
        this.lineTableCreator = lineTableCreator;
    }

    private void createComposite(final Composite parent,
            final TabbedPropertySheetWidgetFactory widgetFactory) {

        mainComposite = new MagicComposite(parent, SWT.NONE);
        if (widgetFactory != null) {
            widgetFactory.adapt(mainComposite);
        }

        mainComposite.setLayout(GridLayoutFactory.fillDefaults().margins(0, 0).create());

        if (widgetFactory != null) {
            viewerComposite = widgetFactory.createComposite(mainComposite,
                    SWT.NONE);
        } else {
            viewerComposite = new Composite(mainComposite, SWT.NONE);
        }

        viewerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        viewerComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());

        createButtonComposite(viewerComposite, widgetFactory);
        createTableViewerComposite(viewerComposite, widgetFactory);

        if (allowSwitchTableMode) {
            createSwitchModeLink(widgetFactory);
        }

        viewer.getTable().addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                updateButtons();
                for (final Listener l : listeners) {
                    l.handleEvent(new Event());
                }
            }
        });
        viewer.getTable().addMouseListener(new MouseListener() {

            @Override
            public void mouseUp(final MouseEvent e) {

            }

            @Override
            public void mouseDown(final MouseEvent e) {
                posClickX = e.x;
            }

            @Override
            public void mouseDoubleClick(final MouseEvent e) {

            }
        });

        updateButtons();
    }

    protected void createTableViewerComposite(final Composite parent,
            final TabbedPropertySheetWidgetFactory widgetFactory) {
        viewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
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
            public void mouseDown(final MouseEvent e) {
                if (minNbRow == 0 || getNbRows() < minNbRow) {
                    final Point point = new Point(e.x, e.y);
                    final TableItem item = viewer.getTable().getItem(point);
                    int currentWidth = 0;
                    int colIndex = 0;
                    for (int i = 0; i < viewer.getTable().getColumns().length; i++) {
                        final TableColumn c = viewer.getTable().getColumns()[i];
                        if (point.x >= currentWidth && point.x < currentWidth + c.getWidth()) {
                            colIndex = i;
                            break;
                        }
                        currentWidth = currentWidth + c.getWidth();
                    }
                    if (item == null && fixedRow) { //When fixedRow is true, no add button is present so we need to keep this behavior
                        addRow(colIndex);
                    }
                }
            }
        });

    }

    protected void createButtonComposite(final Composite parent,
            final TabbedPropertySheetWidgetFactory widgetFactory) {
        Composite buttonComposite = null;
        if (widgetFactory != null) {
            buttonComposite = widgetFactory.createComposite(parent,
                    SWT.NONE);
        } else {
            buttonComposite = new Composite(parent, SWT.NONE);
        }
        final int topIndent = captions == null || captions.isEmpty() ? 0 : 15;
        buttonComposite.setLayoutData(
                GridDataFactory.fillDefaults().grab(false, false).align(SWT.FILL, SWT.TOP).indent(0, topIndent).create());

        final RowLayout rl = new RowLayout(SWT.VERTICAL);
        rl.spacing = 3;
        rl.marginLeft = 15;
        rl.fill = true;
        buttonComposite.setLayout(rl);
        fillButtonComposite(parent, widgetFactory, buttonComposite);
    }

    protected void fillButtonComposite(final Composite parent,
            final TabbedPropertySheetWidgetFactory widgetFactory,
            final Composite buttonComposite) {
        if (!fixedRow) {
            createRowbuttons(widgetFactory, buttonComposite);
        }

        if (!fixedCol) {
            createColButtons(widgetFactory, buttonComposite);
        }

        if (allowRowSort) {
            createRowSortButtons(widgetFactory, buttonComposite);
        }
    }

    private void createSwitchModeLink(
            final TabbedPropertySheetWidgetFactory widgetFactory) {
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
                SWT.BORDER, widgetFactory, null, null, withConnectors);
        expressionEditor.setIsPageFlowContext(isPageFlowContext);
        expressionEditor.getControl().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).create());

        switchTableModeLink = new Link(mainComposite, SWT.NONE);
        switchTableModeLink.setLayoutData(GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.CENTER)
                .create());
        if (widgetFactory != null) {
            widgetFactory.adapt(switchTableModeLink, false, false);
        }
        switchTableModeLink.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent arg0) {
                switchTableMode();
            }
        });
    }

    private void initializeTableOrExpression(final boolean isTableMode) {
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
        final IObservableValue listValue = EMFObservables.observeValue(listExpression,
                ExpressionPackage.Literals.LIST_EXPRESSION__EXPRESSIONS);
        return new MultiValidator() {

            @Override
            protected IStatus validate() {
                if (isTableMode()) {
                    final AbstractExpression expression = getValue();
                    if (expression instanceof ListExpression) {
                        listValue.getValue();
                        if (listExpression.getExpressions() == null || listExpression.getExpressions().isEmpty()) {
                            return ValidationStatus
                                    .error(Messages.bind(Messages.AtLeastOneRowShouldBeAddedFor, mandatoryLabel));
                        }
                    }
                }
                return ValidationStatus.ok();
            }
        };
    }

    protected MultiValidator createTableExpressionValidator() {
        final TableExpression tableExpression = (TableExpression) getValue();
        final IObservableValue tableValue = EMFObservables.observeValue(tableExpression,
                ExpressionPackage.Literals.TABLE_EXPRESSION__EXPRESSIONS);
        return new MultiValidator() {

            @Override
            protected IStatus validate() {
                if (isTableMode()) {
                    final AbstractExpression expression = getValue();
                    if (expression instanceof TableExpression) {
                        tableValue.getValue();
                        if (tableExpression.getExpressions() == null || tableExpression.getExpressions().isEmpty()) {
                            return ValidationStatus
                                    .error(Messages.bind(Messages.AtLeastOneRowShouldBeAddedFor, mandatoryLabel));
                        }
                    }
                }
                return ValidationStatus.ok();
            }
        };
    }

    private void createRowSortButtons(
            final TabbedPropertySheetWidgetFactory widgetFactory,
            final Composite buttonComposite) {
        if (widgetFactory != null) {
            upRowSortButton = widgetFactory.createButton(buttonComposite,
                    Messages.upRowSort, SWT.FLAT);
        } else {
            upRowSortButton = new Button(buttonComposite, SWT.FLAT);
            upRowSortButton.setText(Messages.upRowSort);
        }
        upRowSortButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                if (viewer != null && viewer.getSelection() != null
                        && !viewer.getSelection().isEmpty()) {
                    final int indexRowToMoveUp = viewer.getTable()
                            .getSelectionIndex();
                    moveUpRow(indexRowToMoveUp);
                }
                for (final Listener l : listeners) {
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
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                if (viewer != null && viewer.getSelection() != null
                        && !viewer.getSelection().isEmpty()) {
                    final int indexRowToMoveDown = viewer.getTable()
                            .getSelectionIndex();
                    moveDownRow(indexRowToMoveDown);
                }
                for (final Listener l : listeners) {
                    l.handleEvent(new Event());
                }
            }
        });

    }

    protected void moveUpRow(final int indexRowToMoveUp) {
        if (indexRowToMoveUp > 0) {
            final int targetIndex = indexRowToMoveUp - 1;
            moveRowFromTo(indexRowToMoveUp, targetIndex);
        }
    }

    protected void moveDownRow(final int indexRowToMoveUp) {
        if (indexRowToMoveUp < getNbRows()) {
            final int targetIndex = indexRowToMoveUp + 1;
            moveRowFromTo(indexRowToMoveUp, targetIndex);
        }
    }

    protected void moveRowFromTo(final int indexRowToMoveUp, final int targetIndex) {
        final AbstractExpression expression = (AbstractExpression) viewer.getInput();
        if (editingDomain == null) {
            editingDomain = TransactionUtil.getEditingDomain(expression);
        }
        if (editingDomain != null) {
            if (expression instanceof ListExpression) {
                final Expression expressionToMoveUp = ((ListExpression) expression)
                        .getExpressions().get(indexRowToMoveUp);
                final Command moveCommand = MoveCommand
                        .create(editingDomain,
                                expression,
                                ExpressionPackage.Literals.LIST_EXPRESSION__EXPRESSIONS,
                                expressionToMoveUp, targetIndex);
                editingDomain.getCommandStack().execute(moveCommand);
            } else if (expression instanceof TableExpression) {
                final ListExpression expressionToMoveUp = ((TableExpression) expression)
                        .getExpressions().get(indexRowToMoveUp);
                final Command moveCommand = MoveCommand
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
        final Object expressionInput = viewer.getInput();
        AbstractExpression newExpression = null;
        if (expressionInput instanceof ListExpression) {
            newExpression = addElementInListExpression(expressionInput);
            fireLineAdded(Collections.<Expression> singletonList((Expression) newExpression));
        } else if (expressionInput instanceof TableExpression) {
            newExpression = addLineInTableExpression(expressionInput);
            fireLineAdded(((ListExpression) newExpression).getExpressions());
        }

        refresh();
        putCursorOnNewElement(colIndexToEdit, newExpression);
    }

    protected void fireLineAdded(List<Expression> expressions) {
        for (final IAddLineListener listener : addLineLineListeners) {
            listener.newLinedAdded(expressions);
        }
    }

    private AbstractExpression addElementInListExpression(final Object expressionInput) {
        AbstractExpression newExpression;
        newExpression = ExpressionFactory.eINSTANCE.createExpression();
        ((ListExpression) expressionInput).getExpressions().add((Expression) newExpression);
        return newExpression;
    }

    private ListExpression addLineInTableExpression(final Object expressionInput) {
        final ListExpression rowExp = createListExpressionForNewLineInTable();
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

        return rowExp;
    }

    private ListExpression createListExpressionForNewLineInTable() {
        return lineTableCreator.createListExpressionForNewLineInTable(Math.max(getNbCols(), minNbCol));
    }

    private void putCursorOnNewElement(final int colIndexToEdit, final AbstractExpression newExpression) {
        final AbstractExpression expressionToEdit = newExpression;
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
            final TabbedPropertySheetWidgetFactory widgetFactory,
            final Composite buttonComposite) {
        if (widgetFactory != null) {
            final Label separator = widgetFactory.createSeparator(buttonComposite,
                    SWT.SEPARATOR | SWT.HORIZONTAL);
            separator.setLayoutData(new RowData(1, 10));
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
            public void handleEvent(final Event event) {
                if (!viewerFilters.isEmpty()) {
                    viewerFilters.add(viewerFilters.get(viewerFilters.size() - 1));
                }
                final Expression newExp = addColumn(getNbCols());
                mainComposite.layout(true, true);
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
            public void handleEvent(final Event event) {
                if (viewer != null && viewer.getSelection() != null
                        && !viewer.getSelection().isEmpty()) {
                    final int indexColumnToRemove = posClickX / viewer.getTable().getColumns()[0].getWidth();
                    removeColumn(indexColumnToRemove);
                }
                for (final Listener l : listeners) {
                    l.handleEvent(new Event());
                }
            }
        });
    }

    private void createRowbuttons(
            final TabbedPropertySheetWidgetFactory widgetFactory,
            final Composite buttonComposite) {
        if (widgetFactory != null) {
            addRowButton = widgetFactory.createButton(buttonComposite,
                    Messages.addRow, SWT.FLAT);
        } else {
            addRowButton = new Button(buttonComposite, SWT.FLAT);
            addRowButton.setText(Messages.addRow);
        }

        addRowButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
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
        for (final TableColumn c : viewer.getTable().getColumns()) {
            c.dispose();
        }
        if (getNbRows() > 0) {
            for (int i = 0; i < Math.max(minNbCol, getNbCols()); i++) {
                addColumnToViewer(i);
            }
            refresh();
        }
    }

    protected void updateTableOrExpressionMode(final boolean isTableMode) {
        initializeTableOrExpression(isTableMode);
        final Point defaultSize = mainComposite.getSize();
        final Point size = mainComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
        mainComposite.setSize(defaultSize.x, size.y);
        mainComposite.layout(true, true);

        for (final Listener listener : listeners) {
            listener.handleEvent(null);
        }
        for (final IExpressionModeListener l : modeListeners) {
            if (isTableMode) {
                l.useTable();
            } else {
                l.useSimpleExpression();
            }
        }
    }

    protected void addColumnToViewer(final int index) {
        final TableViewerColumn column = new TableViewerColumn(viewer, SWT.FILL);

        if (captions != null && captions.size() > index
                && captions.get(index) != null) {
            column.getColumn().setText(captions.get(index));
        }

        final ExpressionCollectionEditingSupport editingSupport = new ExpressionCollectionEditingSupport(
                column.getViewer(), index, editingDomain, removeRowListener);
        if (context != null) {
            editingSupport.setInput(context);
        }
        if (viewerFilters != null && viewerFilters.size() > index) {
            final ViewerFilter filter = viewerFilters.get(index);
            if (filter != null) {
                editingSupport.addFilter(filter);
            }
            if (viewerExprProviders != null
                    && viewerExprProviders.size() > index) {
                final IExpressionNatureProvider provider = viewerExprProviders
                        .get(index);
                if (provider != null) {
                    editingSupport.setExpressionNatureProvider(provider);
                }
            }
        }
        editingSupports.add(index, editingSupport);
        column.setEditingSupport(editingSupport);
        column.setLabelProvider(new ExpressionColumnLabelProvider(index));
        viewer.getTable().setLayout(createTableLayout());
    }

    private TableLayout createTableLayout() {
        final int weight = (int) ((float) 1 / (float) viewer.getTable()
                .getColumnCount() * 100);
        final TableLayout layout = new TableLayout();
        for (int i = 0; i < viewer.getTable().getColumns().length; i++) {
            layout.addColumnData(new ColumnWeightData(weight));
        }
        return layout;
    }

    private Expression addColumn(final int index) {
        final TableExpression tableExpression = (TableExpression) viewer.getInput();
        final EList<ListExpression> expressions = tableExpression.getExpressions();
        Expression exp = null;
        if (editingDomain == null) {
            editingDomain = TransactionUtil.getEditingDomain(expressions);
        }
        if (editingDomain != null) {
            final CompoundCommand cc = new CompoundCommand("Add column at index "
                    + index);
            for (final ListExpression listExpression : expressions) {
                exp = ExpressionFactory.eINSTANCE.createExpression();
                cc.append(AddCommand
                        .create(editingDomain,
                                listExpression,
                                ExpressionPackage.Literals.LIST_EXPRESSION__EXPRESSIONS,
                                exp, index));
            }
            editingDomain.getCommandStack().execute(cc);
        } else {
            for (final ListExpression listExpression : expressions) {
                exp = ExpressionFactory.eINSTANCE.createExpression();
                final EList<Expression> subListExpression = listExpression
                        .getExpressions();
                subListExpression.add(exp);
            }
        }
        addColumnToViewer(index);
        return exp;
    }

    private void removeColumn(final int indexColumnToRemove) {
        final TableExpression tableExpression = (TableExpression) viewer.getInput();
        final EList<ListExpression> expressions = tableExpression.getExpressions();
        if (editingDomain == null) {
            editingDomain = TransactionUtil.getEditingDomain(expressions);
        }
        if (editingDomain != null) {
            final CompoundCommand cc = new CompoundCommand(
                    "Remove column of a TableExpression");
            for (final ListExpression listExpression : expressions) {
                if (indexColumnToRemove < listExpression.getExpressions().size()) {
                    final Expression expressionToRemove = listExpression.getExpressions()
                            .get(indexColumnToRemove);
                    final Command removeCommand = RemoveCommand
                            .create(editingDomain,
                                    listExpression,
                                    ExpressionPackage.Literals.LIST_EXPRESSION__EXPRESSIONS,
                                    expressionToRemove);
                    cc.append(removeCommand);
                }
            }
            editingDomain.getCommandStack().execute(cc);
        } else {
            for (final ListExpression listExpression : expressions) {
                listExpression.getExpressions().remove(indexColumnToRemove);
            }
        }

        updateColumns();
        refresh();
    }

    private void removeRow(final int indexRowToRemove) {
        final AbstractExpression expression = (AbstractExpression) viewer.getInput();
        if (editingDomain == null) {
            editingDomain = TransactionUtil.getEditingDomain(expression);
        }
        if (editingDomain != null) {
            if (expression instanceof ListExpression) {
                final Expression expressionToRemove = ((ListExpression) expression)
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
                final ListExpression expressionToRemove = ((TableExpression) expression)
                        .getExpressions().get(indexRowToRemove);
                final Command removeCommand = RemoveCommand
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
                    viewer.getTable().setLayout(createTableLayout());
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
        if (removeRowButton != null && !removeRowButton.isDisposed() && viewer.getSelection() != null) {
            removeRowButton.setEnabled(viewer.getTable().isEnabled()
                    && (!viewer.getSelection().isEmpty() || viewer
                            .isCellEditorActive()));
        }
        if (addColumnButton != null && !addColumnButton.isDisposed()) {
            addColumnButton.setEnabled(getNbRows() > 0);
        }

        if (removeColButton != null && !removeColButton.isDisposed() && viewer.getSelection() != null) {
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
    public void setLayoutData(final Object layoutData) {
        mainComposite.setLayoutData(layoutData);
        refresh();
    }

    /**
     * @param listener
     */
    public void addModifyListener(final Listener listener) {
        listeners.add(listener);
        if (expressionEditor != null) {
            expressionEditor.getTextControl().addListener(SWT.Modify, listener);
        }
    }

    public TableViewer getViewer() {
        return viewer;
    }

    public boolean isTableMode() {
        return isTableMode;
    }

    public boolean isEmpty() {
        if (isTableMode) {
            final AbstractExpression value = getValue();
            if (value instanceof ListExpression) {
                return ((ListExpression) value).getExpressions().isEmpty();
            } else if (value instanceof TableExpression) {
                return ((TableExpression) value).getExpressions().isEmpty();
            }
            return true;
        } else {
            final AbstractExpression value = getValue();
            if (value instanceof Expression) {
                return ((Expression) value).getContent() == null
                        || ((Expression) value).getContent().isEmpty();
            }
            return true;
        }
    }

    public void setSelection(final AbstractExpression input) {
        if (validationContext != null) {
            if (bindValue != null) {
                validationContext.removeBinding(bindValue);
                bindValue.dispose();
                bindValue = null;
            }
            if (expressionEditor != null) {
                expressionEditor.setMandatoryField(null, validationContext);
            }
        }

        if (input instanceof Expression) {
            initializeTableOrExpression(false);
            expressionEditor.setMandatoryField(mandatoryLabel, validationContext);
            expressionEditor.setSelection(new StructuredSelection(input));
        } else if (input instanceof ListExpression
                || input instanceof TableExpression) {
            if (expressionEditor != null) {
                expressionEditor.setSelection(new StructuredSelection(ExpressionFactory.eINSTANCE.createExpression()));
            }
            initializeTableOrExpression(true);
            viewer.setInput(input);
            if (input instanceof TableExpression) {
                if (!((TableExpression) input).getExpressions().isEmpty()) {
                    updateColumns();
                }
            }
            if (validationContext != null) {
                IObservableValue observeValue = null;
                if (input instanceof ListExpression) {
                    observeValue = EMFObservables.observeValue(input,
                            ExpressionPackage.Literals.LIST_EXPRESSION__EXPRESSIONS);
                } else {
                    observeValue = EMFObservables.observeValue(input,
                            ExpressionPackage.Literals.TABLE_EXPRESSION__EXPRESSIONS);
                }
                final UpdateValueStrategy strategy = new UpdateValueStrategy();
                strategy.setAfterGetValidator(new IValidator() {

                    @Override
                    public IStatus validate(final Object value) {
                        if (isTableMode()) {
                            final AbstractExpression expression = getValue();
                            if (expression instanceof ListExpression) {
                                if (((ListExpression) expression).getExpressions() == null
                                        || ((ListExpression) expression).getExpressions().isEmpty()) {
                                    return ValidationStatus
                                            .error(Messages.bind(Messages.AtLeastOneRowShouldBeAddedFor, mandatoryLabel));
                                }
                            }
                            if (expression instanceof TableExpression) {
                                if (((TableExpression) expression).getExpressions() == null
                                        || ((TableExpression) expression).getExpressions().isEmpty()) {
                                    return ValidationStatus
                                            .error(Messages.bind(Messages.AtLeastOneRowShouldBeAddedFor, mandatoryLabel));
                                }
                            }
                        }
                        return ValidationStatus.ok();
                    }
                });
                bindValue = validationContext.bindValue(new WritableValue(), observeValue, strategy, strategy);
            }
        }
    }

    public void addFilter(final ViewerFilter viewerFilter) {
        viewerFilters.add(viewerFilter);
        if (expressionEditor != null) {
            expressionEditor.addFilter(viewerFilter);
        }
    }

    public void removeFilter(final ViewerFilter viewerFilter) {
        viewerFilters.remove(viewerFilter);
        if (expressionEditor != null) {
            expressionEditor.removeFilter(viewerFilter);
        }
    }

    public void setContext(EObject context) {
        this.context = context;
    }

    protected boolean isOldContextAndInputSimilar(final Object input) {
        return input instanceof EObject && input.equals(context);
    }

    public void setInput(final Object input) {
        this.input = input;
        if (isOldContextAndInputSimilar(input)) {
            setContext((EObject) input);
        }
        if (expressionEditor != null) {
            expressionEditor.setContext(context);
            expressionEditor.setInput(input);
        }
        for (final ExpressionCollectionEditingSupport es : editingSupports) {
            es.setContext(context);
            es.setInput(input);
            if (viewerFilters.size() > editingSupports.indexOf(es)) {
                final ViewerFilter filter = viewerFilters.get(editingSupports.indexOf(es));
                if (filter != null) {
                    es.addFilter(filter);
                }

            }
            if (viewerExprProviders.size() > editingSupports.indexOf(es)) {
                final IExpressionNatureProvider natureProvider = viewerExprProviders.get(editingSupports.indexOf(es));
                if (natureProvider != null) {
                    es.setExpressionNatureProvider(natureProvider);
                }

            }

        }
    }

    public void addExpressionModeListener(final IExpressionModeListener listener) {
        modeListeners.add(listener);
    }

    public void removeExpressionModeListener(final IExpressionModeListener listener) {
        modeListeners.remove(listener);
    }

    public Button getAddRowButton() {
        return addRowButton;
    }

    public void setViewerFilters(final List<ViewerFilter> viewerFilters) {
        this.viewerFilters = viewerFilters;
        if (expressionEditor != null) {
            expressionEditor.getFilters().clear();
            for (final ViewerFilter filter : viewerFilters) {
                expressionEditor.addFilter(filter);
            }
        }
    }

    public Button getRemoveRowButton() {
        return removeRowButton;
    }

    private int getNbCols() {
        final Object input = viewer.getInput();
        if (input != null) {
            if (input instanceof ListExpression) {
                return 1;
            } else {
                final EList<ListExpression> expressions = ((TableExpression) input)
                        .getExpressions();
                return expressions.isEmpty() ? 0 : expressions.get(0)
                        .getExpressions().size();
            }
        } else {
            return 0;
        }
    }

    private int getNbRows() {
        final Object input = viewer.getInput();
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

    public void setAddRowLabel(final String addCorrelation) {
        if (addRowButton != null) {
            addRowButton.setText(addCorrelation);
        }
    }

    public void setRemoveRowLabel(final String removeCorrelation) {
        if (removeRowButton != null) {
            removeRowButton.setText(removeCorrelation);
        }
    }

    public void addExpressionNatureProvider(final IExpressionNatureProvider expressionNatureProvider) {
        viewerExprProviders.add(expressionNatureProvider);
        if (editingSupports != null && !editingSupports.isEmpty()) {
            for (int i = 0; i < editingSupports.size(); i++) {
                if (i < viewerExprProviders.size()) {
                    editingSupports.get(i).setExpressionNatureProvider(viewerExprProviders.get(i));
                }
            }
        }
    }

    public void removeExpressionNatureProvider(final int index) {
        if (editingSupports != null && !editingSupports.isEmpty()) {
            if (index < editingSupports.size()) {
                editingSupports.get(index).setExpressionNatureProvider(null);
            }
        }
        if (index < viewerExprProviders.size()) {
            viewerExprProviders.remove(index);
        }
    }

    public void setExpressionProposalLableProvider(final int colIndex,
            final IExpressionProposalLabelProvider expressionProposalLabelProvider) {
        if (editingSupports != null && !editingSupports.isEmpty()) {
            if (colIndex >= 0 && colIndex < editingSupports.size()) {
                editingSupports.get(colIndex).setExpressionProposalLableProvider(expressionProposalLabelProvider);
            }
        }
    }

    @Override
    public boolean isPageFlowContext() {
        return isPageFlowContext;
    }

    @Override
    public void setIsPageFlowContext(final boolean isPageFlowContext) {
        this.isPageFlowContext = isPageFlowContext;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#isOverViewContext()
     */
    @Override
    public boolean isOverViewContext() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsOverviewContext(boolean)
     */
    @Override
    public void setIsOverviewContext(final boolean isOverviewContext) {
    }

    public void setMandatoryField(final String label, final EMFDataBindingContext context) {
        expressionEditor.setMandatoryField(label, context);
        mandatoryLabel = label;
        validationContext = context;

    }

    public void addLineListener(IAddLineListener listener) {
        addLineLineListeners.add(listener);
    }

    public void setExpressionEditorFilter(ViewerFilter filter) {
        expressionEditor.getFilters().clear();
        expressionEditor.addFilter(filter);
    }

}
