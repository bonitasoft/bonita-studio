/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.form.sections.actions.table;

import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Table;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Aurelien Pupier
 */
public class TableDataPropertySection extends AbstractTableDataPropertySection {

    private Button allowSelectionButton;
    private Button allowMultipleSelection;
    private Composite allowedSelectionCompo;
    private ExpressionViewer initialSelectedValues;
    private ExpressionViewer initialFromColumn;
    private Button allowSingleSelection;
    private ExpressionViewer paginationMaxNumber;

    @Override
    protected void createContent(final Composite parent) {
        createSaveTo(parent);

    }

    protected void createSaveTo(final Composite parent) {
        final TabbedPropertySheetWidgetFactory widgetFactory = getWidgetFactory();
        final Composite compoForSaveTo = widgetFactory.createComposite(parent, SWT.NONE);
        compoForSaveTo.setLayout(new GridLayout(1, false));
        compoForSaveTo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        final Composite paginationComposite = widgetFactory.createComposite(compoForSaveTo);
        paginationComposite.setLayout(new GridLayout(2, false));
        paginationComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        widgetFactory.createCLabel(paginationComposite, Messages.table_paginationNumber + " ");//$NON-NLS-1$
        paginationMaxNumber = new ExpressionViewer(paginationComposite, SWT.BORDER, widgetFactory, getEditingDomain(),
                FormPackage.Literals.TABLE__MAX_ROW_FOR_PAGINATION);
        paginationMaxNumber.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        final Composite selectionButtonsComposite = widgetFactory.createComposite(compoForSaveTo);
        selectionButtonsComposite.setLayout(new RowLayout(SWT.HORIZONTAL));

        //Messages.data_allowSelectionInTable
        allowSelectionButton = widgetFactory.createButton(selectionButtonsComposite, Messages.data_allowSelectionInTable,
                SWT.CHECK);
        //Messages.data_multipleSelectionInTable
        allowSingleSelection = widgetFactory.createButton(selectionButtonsComposite, Messages.table_selectionSingle,
                SWT.RADIO);

        allowMultipleSelection = widgetFactory.createButton(selectionButtonsComposite, Messages.table_selectionMultiple,
                SWT.RADIO);

        allowedSelectionCompo = widgetFactory.createComposite(compoForSaveTo);
        allowedSelectionCompo.setLayout(new GridLayout(1, false));
        allowedSelectionCompo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        createInitialSelectedValues(widgetFactory);

        final Composite outputComposite = widgetFactory.createComposite(allowedSelectionCompo);
        outputComposite.setLayout(new GridLayout(2, false));
        outputComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        widgetFactory.createCLabel(outputComposite, contrib.getLabel());
        final Composite rightOutputComposite = widgetFactory.createComposite(outputComposite);
        rightOutputComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        contrib.createControl(rightOutputComposite, widgetFactory, null);
    }

    protected void createInitialSelectedValues(
            final TabbedPropertySheetWidgetFactory widgetFactory) {
        final Composite initialSelectedValuesCompo = widgetFactory.createComposite(allowedSelectionCompo);
        initialSelectedValuesCompo.setLayout(new GridLayout(4, false));
        initialSelectedValuesCompo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        widgetFactory.createLabel(initialSelectedValuesCompo, Messages.data_initialSelectedTableValues);
        initialSelectedValues = new ExpressionViewer(initialSelectedValuesCompo, SWT.BORDER, widgetFactory,
                getEditingDomain(), FormPackage.Literals.TABLE__SELECTED_VALUES);
        initialSelectedValues.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        widgetFactory.createLabel(initialSelectedValuesCompo, Messages.data_initialSelectedTableValuesFromColumn);
        initialFromColumn = new ExpressionViewer(initialSelectedValuesCompo, SWT.BORDER, widgetFactory, getEditingDomain(),
                FormPackage.Literals.TABLE__COLUMN_FOR_INITIAL_SELECTION_INDEX);
        initialFromColumn.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        initialFromColumn.setMessage(Messages.data_initialSelectedTableValuesFromColumn_hint);
    }

    @Override
    protected void refreshDataBinding() {
        super.refreshDataBinding();
        if (getEObject() != null) {
            final UpdateValueStrategy not = new UpdateValueStrategy()
                    .setConverter(new Converter(Boolean.class, Boolean.class) {

                        public Object convert(final Object fromObject) {
                            return !((Boolean) fromObject);
                        }
                    });
            dataBindingContext.bindValue(SWTObservables.observeSelection(allowSelectionButton), EMFEditObservables
                    .observeValue(getEditingDomain(), getEObject(), FormPackage.Literals.TABLE__ALLOW_SELECTION));
            dataBindingContext.bindValue(SWTObservables.observeSelection(allowSingleSelection), EMFEditObservables
                    .observeValue(getEditingDomain(), getEObject(), FormPackage.Literals.TABLE__SELECTION_MODE_IS_MULTIPLE),
                    not, not);
            dataBindingContext.bindValue(SWTObservables.observeSelection(allowMultipleSelection), EMFEditObservables
                    .observeValue(getEditingDomain(), getEObject(), FormPackage.Literals.TABLE__SELECTION_MODE_IS_MULTIPLE));

            Expression maxRowForPagination = getEObject().getMaxRowForPagination();
            if (maxRowForPagination == null) {
                maxRowForPagination = ExpressionFactory.eINSTANCE.createExpression();
                getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), getEObject(),
                        FormPackage.Literals.TABLE__MAX_ROW_FOR_PAGINATION, maxRowForPagination));
            }
            dataBindingContext.bindValue(ViewerProperties.singleSelection().observe(paginationMaxNumber), EMFEditProperties
                    .value(getEditingDomain(), FormPackage.Literals.TABLE__MAX_ROW_FOR_PAGINATION).observe(getEObject()));
            paginationMaxNumber.setInput(getEObject());

            dataBindingContext.bindValue(SWTObservables.observeEnabled(paginationMaxNumber.getControl()),
                    EMFEditObservables.observeValue(getEditingDomain(),
                            getEObject(), FormPackage.Literals.TABLE__ALLOW_SELECTION),
                    new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), not);

            Expression selectedValues = getEObject().getMaxRowForPagination();
            if (selectedValues == null) {
                selectedValues = ExpressionFactory.eINSTANCE.createExpression();
                getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), getEObject(),
                        FormPackage.Literals.TABLE__SELECTED_VALUES, selectedValues));
            }
            dataBindingContext.bindValue(ViewerProperties.singleSelection().observe(initialSelectedValues), EMFEditProperties
                    .value(getEditingDomain(), FormPackage.Literals.TABLE__SELECTED_VALUES).observe(getEObject()));
            initialSelectedValues.setInput(getEObject());

            Expression columnForInitialSelectionIndex = getEObject().getColumnForInitialSelectionIndex();
            if (columnForInitialSelectionIndex == null) {
                columnForInitialSelectionIndex = ExpressionFactory.eINSTANCE.createExpression();
                getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), getEObject(),
                        FormPackage.Literals.TABLE__COLUMN_FOR_INITIAL_SELECTION_INDEX, columnForInitialSelectionIndex));
            }
            dataBindingContext.bindValue(
                    ViewerProperties.singleSelection().observe(initialFromColumn),
                    EMFEditProperties
                            .value(getEditingDomain(), FormPackage.Literals.TABLE__COLUMN_FOR_INITIAL_SELECTION_INDEX)
                            .observe(getEObject()));
            initialFromColumn.setInput(getEObject());

            /* Hide if no selection allowed */
            dataBindingContext.bindValue(SWTObservables.observeVisible(allowedSelectionCompo),
                    EMFEditObservables.observeValue(getEditingDomain(), getEObject(),
                            FormPackage.Literals.TABLE__ALLOW_SELECTION),
                    new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), null);
            dataBindingContext.bindValue(SWTObservables.observeVisible(allowSingleSelection),
                    EMFEditObservables.observeValue(getEditingDomain(), getEObject(),
                            FormPackage.Literals.TABLE__ALLOW_SELECTION),
                    new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), null);
            dataBindingContext.bindValue(SWTObservables.observeVisible(allowMultipleSelection),
                    EMFEditObservables.observeValue(getEditingDomain(), getEObject(),
                            FormPackage.Literals.TABLE__ALLOW_SELECTION),
                    new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), null);
            contrib.setEditingDomain(getEditingDomain());
            contrib.setEObject(getEObject());
            contrib.superBind();
        }
    }

    @Override
    protected Table getEObject() {
        return (Table) super.getEObject();
    }

    @Override
    protected boolean isOuputActivated() {
        return getEObject().isAllowSelection();
    }

    @Override
    public String getSectionDescription() {
        return null;
    }
}
