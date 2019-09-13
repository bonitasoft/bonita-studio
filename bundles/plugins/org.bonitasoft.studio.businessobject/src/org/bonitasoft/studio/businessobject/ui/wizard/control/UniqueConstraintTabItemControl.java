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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.UniqueConstraint;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.wizard.editingsupport.UniqueConstraintFieldsEditingSupport;
import org.bonitasoft.studio.businessobject.ui.wizard.editingsupport.UniqueConstraintNameEditingSupport;
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
public class UniqueConstraintTabItemControl extends AbstractTabItemControl {

    private static final String DEFAULT_UNIQUE_CONSTRAINT_NAME = "UNIQUE_CONSTRAINT_";

    private final IObservableList fieldsList;

    private final BusinessObjectModel bom;

    public UniqueConstraintTabItemControl(final TabFolder parent, final DataBindingContext ctx,
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

        final TableViewer constraintsTableViewer = new TableViewer(this,
                SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI);
        constraintsTableViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        constraintsTableViewer.getTable().setEnabled(viewerObservableValue.getValue() instanceof BusinessObject);
        constraintsTableViewer.getTable().setLinesVisible(true);
        constraintsTableViewer.getTable().setHeaderVisible(true);
        constraintsTableViewer.setContentProvider(new ObservableListContentProvider());

        final TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(1, 200));
        tableLayout.addColumnData(new ColumnWeightData(1));
        constraintsTableViewer.getTable().setLayout(tableLayout);

        UpdateValueStrategy enableStrategy = new UpdateValueStrategy();
        enableStrategy.setConverter(new Converter(Object.class, Boolean.class) {

            @Override
            public Object convert(final Object fromObject) {
                return fromObject instanceof BusinessObject;
            }
        });
        ctx.bindValue(SWTObservables.observeEnabled(constraintsTableViewer.getTable()), viewerObservableValue, null,
                enableStrategy);

        final IViewerObservableValue constaintObserveSingleSelection = ViewersObservables
                .observeSingleSelection(constraintsTableViewer);
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

        createUniqueConstraintNameColumn(ctx, constraintsTableViewer, viewerObservableValue);
        createUniqueConstraintFieldsColumn(ctx, constraintsTableViewer, viewerObservableValue);

        final IObservableList uniqueConstraintObserveDetailList = PojoObservables.observeDetailList(viewerObservableValue,
                "uniqueConstraints", List.class);
        constraintsTableViewer.setInput(uniqueConstraintObserveDetailList);

        addButton.addSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                addUniqueConstraint(viewerObservableValue, uniqueConstraintObserveDetailList, constraintsTableViewer);
            }
        });

        deleteButton.addSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                deleteUniqueConstraint(uniqueConstraintObserveDetailList, constraintsTableViewer);
            }
        });

    }

    protected void deleteUniqueConstraint(final IObservableList uniqueConstraintListObservable,
            final TableViewer constraintsTableViewer) {
        final IStructuredSelection selection = (IStructuredSelection) constraintsTableViewer.getSelection();
        uniqueConstraintListObservable.removeAll(selection.toList());
    }

    protected void addUniqueConstraint(final IViewerObservableValue viewerObservableValue,
            final IObservableList uniqueConstraintObservableList,
            final TableViewer constraintsTableViewer) {
        final UniqueConstraint uc = new UniqueConstraint();
        uc.setName(generateConstraintName());
        uc.setFieldNames(Collections.emptyList());
        uniqueConstraintObservableList.add(uc);
        Display.getDefault().asyncExec(() -> constraintsTableViewer.editElement(uc, 0));
    }

    protected String generateConstraintName() {
        final Set<String> existingNames = new HashSet<>();
        final List<UniqueConstraint> constraints = new ArrayList<>();
        for (final BusinessObject businessObject : bom.getBusinessObjects()) {
            constraints.addAll(businessObject.getUniqueConstraints());
        }
        for (final UniqueConstraint constraint : constraints) {
            existingNames.add(constraint.getName());
        }
        return NamingUtils.generateNewName(existingNames, DEFAULT_UNIQUE_CONSTRAINT_NAME, 1);
    }

    protected void createUniqueConstraintNameColumn(final DataBindingContext ctx, final TableViewer constraintsTableViewer,
            final IViewerObservableValue viewerObservableValue) {
        final TableViewerColumn nameColumnViewer = new TableViewerColumn(constraintsTableViewer, SWT.LEFT);
        final TableColumn column = nameColumnViewer.getColumn();
        column.setText(Messages.name + " *");
        nameColumnViewer.setLabelProvider(new ColumnLabelProvider() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
             */
            @Override
            public String getText(final Object element) {
                if (element instanceof UniqueConstraint) {
                    return ((UniqueConstraint) element).getName();
                }
                return super.getText(element);
            }
        });
        nameColumnViewer.setEditingSupport(
                new UniqueConstraintNameEditingSupport(viewerObservableValue, nameColumnViewer.getViewer(), ctx));
    }

    protected void createUniqueConstraintFieldsColumn(final DataBindingContext ctx, final TableViewer constraintsTableViewer,
            final IViewerObservableValue viewerObservableValue) {
        final TableViewerColumn constraintFieldsColumnViewer = new TableViewerColumn(constraintsTableViewer, SWT.LEFT);
        final TableColumn column = constraintFieldsColumnViewer.getColumn();
        column.setText(Messages.attributes);
        constraintFieldsColumnViewer.setLabelProvider(new ColumnLabelProvider() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
             */
            @Override
            public String getText(final Object element) {
                if (element instanceof UniqueConstraint && ((UniqueConstraint) element).getFieldNames() != null
                        && !((UniqueConstraint) element).getFieldNames().isEmpty()) {
                    return ((UniqueConstraint) element).getFieldNames().toString();
                }
                return "";
            }
        });
        constraintFieldsColumnViewer
                .setEditingSupport(new UniqueConstraintFieldsEditingSupport(viewerObservableValue,
                        constraintFieldsColumnViewer.getViewer()));
    }
}
