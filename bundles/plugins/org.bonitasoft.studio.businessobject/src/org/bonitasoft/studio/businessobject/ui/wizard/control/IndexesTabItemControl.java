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

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.Index;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.wizard.editingsupport.IndexFieldsEditingSupport;
import org.bonitasoft.studio.businessobject.ui.wizard.editingsupport.IndexNameEditingSupport;
import org.bonitasoft.studio.common.NamingUtils;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TableColumn;

/**
 * @author Romain Bioteau
 */
public class IndexesTabItemControl extends AbstractTabItemControl {

    private static final String DEFAULT_INDEX_NAME = "INDEX_";

    private final IObservableList fieldsList;

    private final BusinessObjectModel bom;

    public IndexesTabItemControl(final TabFolder parent, final DataBindingContext ctx,
            final IViewerObservableValue viewerObservableValue,
            final IObservableList fieldsList,
            final BusinessObjectModel bom) {
        super(parent, SWT.NONE);
        this.fieldsList = fieldsList;
        this.bom = bom;
        createControl(ctx, viewerObservableValue);
    }

    protected void createControl(final DataBindingContext ctx, final IViewerObservableValue viewerObservableValue) {
        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());

        final Composite buttonsComposite = new Composite(this, SWT.NONE);
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).indent(0, 20).create());
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).spacing(0, 3).create());

        final Button addButton = createAddButton(buttonsComposite);
        final Button deleteButton = createDeleteButton(buttonsComposite);

        final TableViewer indexesTableViewer = new TableViewer(this,
                SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI);
        indexesTableViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        indexesTableViewer.getTable().setEnabled(viewerObservableValue.getValue() instanceof BusinessObject);
        indexesTableViewer.getTable().setLinesVisible(true);
        indexesTableViewer.getTable().setHeaderVisible(true);
        indexesTableViewer.setContentProvider(new ObservableListContentProvider());

        final TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(1));
        tableLayout.addColumnData(new ColumnWeightData(2));
        indexesTableViewer.getTable().setLayout(tableLayout);

        UpdateValueStrategy enableStrategy = new UpdateValueStrategy();
        enableStrategy.setConverter(new Converter(Object.class, Boolean.class) {

            @Override
            public Object convert(final Object fromObject) {
                return fromObject instanceof BusinessObject;
            }
        });
        ctx.bindValue(SWTObservables.observeEnabled(indexesTableViewer.getTable()), viewerObservableValue, null,
                enableStrategy);

        final IViewerObservableValue constaintObserveSingleSelection = ViewersObservables
                .observeSingleSelection(indexesTableViewer);
        ctx.bindValue(SWTObservables.observeEnabled(deleteButton), constaintObserveSingleSelection, null, enableStrategy);

        enableStrategy = new UpdateValueStrategy();
        enableStrategy.setConverter(new Converter(Object.class, Boolean.class) {

            @Override
            public Object convert(final Object fromObject) {
                return fromObject instanceof List && !((List<?>) fromObject).isEmpty();
            }
        });

        fieldsList.addListChangeListener(new IListChangeListener() {

            @Override
            public void handleListChange(final ListChangeEvent event) {
                addButton.setEnabled(!event.getObservableList().isEmpty());
            }
        });

        createIndexNameColumn(ctx, indexesTableViewer, viewerObservableValue);
        createIndexFieldsColumn(ctx, indexesTableViewer, viewerObservableValue);

        final IObservableList indexesObserveDetailList = PojoObservables.observeDetailList(viewerObservableValue, "indexes",
                Index.class);
        indexesTableViewer.setInput(indexesObserveDetailList);

        addButton.addSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                addIndex(viewerObservableValue, indexesObserveDetailList, indexesTableViewer);
            }
        });

        deleteButton.addSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                deleteIndex(indexesObserveDetailList, indexesTableViewer);
            }
        });
    }

    protected void createIndexNameColumn(final DataBindingContext ctx, final TableViewer indexesTableViewer,
            final IViewerObservableValue viewerObservableValue) {
        final TableViewerColumn nameColumnViewer = new TableViewerColumn(indexesTableViewer, SWT.LEFT);
        final TableColumn column = nameColumnViewer.getColumn();
        column.setText(Messages.name + " *");
        nameColumnViewer.setLabelProvider(new ColumnLabelProvider() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
             */
            @Override
            public String getText(final Object element) {
                if (element instanceof Index) {
                    return ((Index) element).getName();
                }
                return super.getText(element);
            }
        });
        nameColumnViewer.setEditingSupport(new IndexNameEditingSupport(nameColumnViewer.getViewer(), ctx, bom));
    }

    protected void createIndexFieldsColumn(final DataBindingContext ctx, final TableViewer viewer,
            final IViewerObservableValue viewerObservableValue) {
        final TableViewerColumn indexesFieldsColumnViewer = new TableViewerColumn(viewer, SWT.LEFT);
        final TableColumn column = indexesFieldsColumnViewer.getColumn();
        column.setText(Messages.attributes);
        indexesFieldsColumnViewer.setLabelProvider(new ColumnLabelProvider() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
             */
            @Override
            public String getText(final Object element) {
                if (element instanceof Index && ((Index) element).getFieldNames() != null
                        && !((Index) element).getFieldNames().isEmpty()) {
                    return ((Index) element).getFieldNames().toString();
                }
                return "";
            }
        });
        indexesFieldsColumnViewer
                .setEditingSupport(
                        new IndexFieldsEditingSupport(viewerObservableValue, indexesFieldsColumnViewer.getViewer()));
    }

    protected void addIndex(final IViewerObservableValue viewerObservableValue, final IObservableList indexesObservableList,
            final TableViewer indexesTableViewer) {
        final Index index = new Index();
        index.setName(generateIndexName(viewerObservableValue));
        indexesObservableList.add(index);

        Display.getDefault().asyncExec(() -> indexesTableViewer.editElement(index, 0));
    }

    protected void deleteIndex(final IObservableList indexListObservable, final TableViewer indexesTableViewer) {
        final IStructuredSelection selection = (IStructuredSelection) indexesTableViewer.getSelection();
        indexListObservable.removeAll(selection.toList());
    }

    protected String generateIndexName(final IViewerObservableValue viewerObservableValue) {
        final Set<String> existingNames = new HashSet<>();
        final List<Index> indexes = new ArrayList<>();
        for (final BusinessObject businessObject : bom.getBusinessObjects()) {
            indexes.addAll(businessObject.getIndexes());

        }
        if (indexes != null) {
            for (final Index index : indexes) {
                existingNames.add(index.getName());
            }
        }

        return NamingUtils.generateNewName(existingNames, DEFAULT_INDEX_NAME, 1);
    }
}
