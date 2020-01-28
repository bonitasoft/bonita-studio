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
package org.bonitasoft.studio.businessobject.ui.wizard.control;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.engine.bdm.BDMQueryUtil;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.Query;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.wizard.editingsupport.QueryContentEditingSupport;
import org.bonitasoft.studio.businessobject.ui.wizard.editingsupport.QueryNameEditingSupport;
import org.bonitasoft.studio.businessobject.ui.wizard.editingsupport.ReadOnlyQueryContentEditingSupport;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.fieldassist.ControlDecoration;
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
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Widget;

/**
 * @author Romain Bioteau
 */
public class QueriesTabItemControl extends AbstractTabItemControl {

    private final IObservableList fieldsList;

    private final WritableList defaultQueriesList;

    private static final String DEFAULT_QUERY_NAME = "query";

    private static final String DEFAULT = "default";

    private static final String CUSTOM = "custom";

    public QueriesTabItemControl(TabFolder parent, DataBindingContext ctx,
            final IViewerObservableValue viewerObservableValue, IObservableList fieldsList) {
        super(parent, SWT.NONE);
        this.fieldsList = fieldsList;
        this.defaultQueriesList = new WritableList();
        createControl(ctx, viewerObservableValue);
        viewerObservableValue.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(ValueChangeEvent event) {
                if (event.diff.getNewValue() instanceof BusinessObject) {
                    final BusinessObject selectedBo = (BusinessObject) event.diff.getNewValue();
                    updateDefaultQueries(selectedBo);
                } else {
                    defaultQueriesList.clear();
                }
            }

        });
        parent.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                final Widget item = e.item;
                Object value = viewerObservableValue.getValue();
                if (value instanceof BusinessObject
                        && item instanceof TabItem
                        && Messages.queries.equals(((TabItem) item).getText())) {
                    updateDefaultQueries((BusinessObject) value);
                }
            }
        });
    }

    protected void createControl(DataBindingContext ctx, final IViewerObservableValue viewerObservableValue) {
        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 0).create());
        setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());

        final Composite radioComposite = new Composite(this, SWT.NONE);
        radioComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).span(2, 1).create());
        radioComposite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(2).extendedMargins(5, 5, 5, 0).spacing(20, 0).create());

        final Button defaultQueriesRadioButton = new Button(radioComposite, SWT.RADIO);
        defaultQueriesRadioButton.setLayoutData(GridDataFactory.swtDefaults().create());
        defaultQueriesRadioButton.setText(Messages.defaultQueriesOption);

        final ControlDecoration controlDecoration = new ControlDecoration(defaultQueriesRadioButton, SWT.RIGHT);
        controlDecoration.setDescriptionText(Messages.defaultQueriesHint);
        controlDecoration.setImage(Pics.getImage(PicsConstants.hint));
        controlDecoration.setShowOnlyOnFocus(false);
        controlDecoration.setMarginWidth(-5);

        final Button customQueriesRadioButton = new Button(radioComposite, SWT.RADIO);
        customQueriesRadioButton.setText(Messages.customQueriesOption);

        final ControlDecoration customControlDecoration = new ControlDecoration(customQueriesRadioButton, SWT.RIGHT);
        customControlDecoration.setDescriptionText(Messages.countQueryInfo);
        customControlDecoration.setImage(Pics.getImage(PicsConstants.hint));
        customControlDecoration.setShowOnlyOnFocus(false);
        customControlDecoration.setMarginWidth(-5);

        final Composite stackComposite = new Composite(this, SWT.NONE);
        stackComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        final StackLayout stackLayout = new StackLayout();
        stackComposite.setLayout(stackLayout);

        final Composite defaultComposite = createDefaultQueriesControl(ctx, viewerObservableValue, stackComposite);
        final Composite customComposite = createCustomQueriesControl(ctx, viewerObservableValue, stackComposite);

        final SelectObservableValue radioGroupObservable = new SelectObservableValue(String.class);
        radioGroupObservable.addOption(DEFAULT, SWTObservables.observeSelection(defaultQueriesRadioButton));
        radioGroupObservable.addOption(CUSTOM, SWTObservables.observeSelection(customQueriesRadioButton));
        radioGroupObservable.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(ValueChangeEvent event) {
                final Object newValue = event.diff.getNewValue();
                if (DEFAULT.equals(newValue)) {
                    stackLayout.topControl = defaultComposite;
                } else {
                    stackLayout.topControl = customComposite;
                }
                stackComposite.layout();
            }
        });
        defaultQueriesRadioButton.setSelection(true);
        stackLayout.topControl = defaultComposite;
    }

    protected void deleteQuery(IObservableList queryObserveDetailList, TableViewer queriesTableViewer) {
        final IStructuredSelection selection = (IStructuredSelection) queriesTableViewer.getSelection();
        queryObserveDetailList.removeAll(selection.toList());
    }

    protected void addQuery(IViewerObservableValue viewerObservableValue, IObservableList queryObserveDetailList,
            TableViewer queriesTableViewer) {
        final Query query = new Query(generateQueryName(viewerObservableValue), "", List.class.getName());
        queryObserveDetailList.add(query);

        Display.getDefault().asyncExec(() -> queriesTableViewer.editElement(query, 1));
    }

    protected String generateQueryName(IViewerObservableValue viewerObservableValue) {
        final Set<String> existingNames = new HashSet<>();
        final BusinessObject businessObject = (BusinessObject) viewerObservableValue.getValue();
        final List<Query> queries = businessObject.getQueries();
        for (final Query q : queries) {
            existingNames.add(q.getName());
        }
        return NamingUtils.generateNewName(existingNames, DEFAULT_QUERY_NAME, 1);
    }

    protected TableViewerColumn createQueryContentColumn(DataBindingContext ctx, TableViewer queriesTableViewer) {
        final TableViewerColumn queryContentColumnViewer = new TableViewerColumn(queriesTableViewer, SWT.LEFT);
        final TableColumn column = queryContentColumnViewer.getColumn();
        column.setText(Messages.query);
        queryContentColumnViewer.setLabelProvider(new ColumnLabelProvider() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
             */
            @Override
            public String getText(Object element) {
                if (element instanceof Query) {
                    return ((Query) element).getContent().replaceAll("\n", " ");
                }
                return super.getText(element);
            }
        });

        return queryContentColumnViewer;

    }

    protected TableViewerColumn createQueryNameColumn(DataBindingContext ctx, TableViewer queriesTableViewer) {
        final TableViewerColumn nameColumnViewer = new TableViewerColumn(queriesTableViewer, SWT.LEFT);
        final TableColumn column = nameColumnViewer.getColumn();
        column.setText(Messages.name + " *");
        nameColumnViewer.setLabelProvider(new ColumnLabelProvider() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
             */
            @Override
            public String getText(Object element) {
                if (element instanceof Query) {
                    return ((Query) element).getName();
                }
                return super.getText(element);
            }
        });
        return nameColumnViewer;
    }

    protected TableViewerColumn createStatusColumn(DataBindingContext ctx, IViewerObservableValue viewerObservableValue,
            TableViewer queriesTableViewer) {
        final TableViewerColumn statusColumn = new TableViewerColumn(queriesTableViewer, SWT.LEFT);
        statusColumn.setLabelProvider(new QueryStatusLabelProvider(viewerObservableValue));
        return statusColumn;
    }

    protected Composite createCustomQueriesControl(DataBindingContext ctx,
            final IViewerObservableValue viewerObservableValue, final Composite parent) {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(5, 5).create());

        final Composite buttonsComposite = new Composite(composite, SWT.NONE);
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).indent(0, 20).create());
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).spacing(0, 3).create());

        final Button addButton = createAddButton(buttonsComposite);
        final Button deleteButton = createDeleteButton(buttonsComposite);

        final TableViewer queriesTableViewer = new TableViewer(composite,
                SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI);
        queriesTableViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        queriesTableViewer.getTable().setEnabled(viewerObservableValue.getValue() instanceof BusinessObject);
        queriesTableViewer.getTable().setLinesVisible(true);
        queriesTableViewer.getTable().setHeaderVisible(true);
        queriesTableViewer.setContentProvider(new ObservableListContentProvider());

        final TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(1));
        tableLayout.addColumnData(new ColumnWeightData(3));
        tableLayout.addColumnData(new ColumnWeightData(9));
        queriesTableViewer.getTable().setLayout(tableLayout);

        UpdateValueStrategy enableStrategy = new UpdateValueStrategy();
        enableStrategy.setConverter(new Converter(Object.class, Boolean.class) {

            @Override
            public Object convert(Object fromObject) {
                return fromObject != null;
            }
        });
        ctx.bindValue(SWTObservables.observeEnabled(queriesTableViewer.getTable()), viewerObservableValue, null,
                enableStrategy);

        final IViewerObservableValue constaintObserveSingleSelection = ViewersObservables
                .observeSingleSelection(queriesTableViewer);
        ctx.bindValue(SWTObservables.observeEnabled(deleteButton), constaintObserveSingleSelection, null, enableStrategy);

        enableStrategy = new UpdateValueStrategy();
        enableStrategy.setConverter(new Converter(Object.class, Boolean.class) {

            @Override
            public Object convert(Object fromObject) {
                return fromObject instanceof List && !((List<?>) fromObject).isEmpty();
            }
        });

        fieldsList.addListChangeListener(new IListChangeListener() {

            @Override
            public void handleListChange(ListChangeEvent event) {
                addButton.setEnabled(!event.getObservableList().isEmpty());
            }
        });

        ColumnViewerToolTipSupport.enableFor(queriesTableViewer);

        createStatusColumn(ctx, viewerObservableValue, queriesTableViewer);
        final TableViewerColumn nameColumnViewer = createQueryNameColumn(ctx, queriesTableViewer);
        nameColumnViewer
                .setEditingSupport(new QueryNameEditingSupport(viewerObservableValue, nameColumnViewer.getViewer(), ctx));
        final TableViewerColumn queryContentColumnViewer = createQueryContentColumn(ctx, queriesTableViewer);
        queryContentColumnViewer.setEditingSupport(
                new QueryContentEditingSupport(queryContentColumnViewer.getViewer(), viewerObservableValue));

        final IObservableList queryObserveDetailList = PojoObservables.observeDetailList(viewerObservableValue, "queries",
                Query.class);
        queriesTableViewer.setInput(queryObserveDetailList);

        addButton.addSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                addQuery(viewerObservableValue, queryObserveDetailList, queriesTableViewer);
            }
        });

        deleteButton.addSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                deleteQuery(queryObserveDetailList, queriesTableViewer);
            }
        });

        return composite;
    }

    private Composite createDefaultQueriesControl(DataBindingContext ctx, final IViewerObservableValue viewerObservableValue,
            final Composite parent) {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        composite.setLayout(GridLayoutFactory.fillDefaults().margins(5, 5).create());

        final TableViewer queriesTableViewer = new TableViewer(composite,
                SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI);
        queriesTableViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        queriesTableViewer.getTable().setLinesVisible(true);
        queriesTableViewer.getTable().setHeaderVisible(true);
        queriesTableViewer.setContentProvider(new ObservableListContentProvider());

        final TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(1));
        tableLayout.addColumnData(new ColumnWeightData(3));
        queriesTableViewer.getTable().setLayout(tableLayout);

        createQueryNameColumn(ctx, queriesTableViewer);
        final TableViewerColumn queryContentColumnViewer = createQueryContentColumn(ctx, queriesTableViewer);
        queryContentColumnViewer.setEditingSupport(
                new ReadOnlyQueryContentEditingSupport(queryContentColumnViewer.getViewer(), viewerObservableValue));

        queriesTableViewer.setInput(defaultQueriesList);

        return composite;
    }

    private void updateDefaultQueries(BusinessObject businessObject) {
        if (defaultQueriesList != null) {
            defaultQueriesList.clear();
            if (businessObject != null) {
                defaultQueriesList.addAll(
                        BDMQueryUtil.createProvidedQueriesForBusinessObject(consistentBusinessObject(businessObject)));
            }
        }
    }

    private BusinessObject consistentBusinessObject(BusinessObject businessObject) {
        final BusinessObject result = new BusinessObject();
        result.setQualifiedName(businessObject.getQualifiedName());
        result.getFields().addAll(new ArrayList<>(businessObject.getFields()));
        businessObject.getUniqueConstraints().stream()
                .filter(uc -> !uc.getFieldNames().isEmpty())
                .forEach(result.getUniqueConstraints()::add);
        return result;
    }
}
