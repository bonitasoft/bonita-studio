/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.form.sections.actions.table;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.form.DynamicTable;
import org.bonitasoft.studio.model.form.FormPackage;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Aurelien Pupier
 */
public class DynamicTableDataPropertySection extends AbstractTableDataPropertySection {

    private ExpressionViewer textOrDataMaxRow;
    private Button limitMaxRowButton;
    private ExpressionViewer textOrDataMinRow;
    private Button limitMinRowButton;
    private Button allowAddRemoveRowButton;
    private Button allowAddRemoveColumnButton;
    private Button limitMinColumnButton;
    private ExpressionViewer textOrDataMinColumn;
    private Button limitMaxColumnButton;
    private ExpressionViewer textOrDataMaxColumn;
    private Composite outputComposite;

    protected void createAddRemoveColumn(final Composite composite,
            final TabbedPropertySheetWidgetFactory widgetFactory) {

        final Composite compo = widgetFactory.createComposite(composite);
        compo.setLayout(new GridLayout(2, false));
        compo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;

        allowAddRemoveColumnButton = widgetFactory.createButton(compo, Messages.data_allowAddRemoveColumn, SWT.CHECK);

        /*Number limitation*/
        final Composite compoForNumberLimitation = widgetFactory.createComposite(compo);
        compoForNumberLimitation.setLayout(new GridLayout(2, false));
        compoForNumberLimitation.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;

        limitMinColumnButton = widgetFactory.createButton(compoForNumberLimitation, Messages.data_setMinColumn, SWT.CHECK);
        textOrDataMinColumn = new ExpressionViewer(compoForNumberLimitation, SWT.BORDER, widgetFactory, getEditingDomain(), FormPackage.Literals.DYNAMIC_TABLE__MIN_NUMBER_OF_COLUMN);
        textOrDataMinColumn.addFilter(new AvailableExpressionTypeFilter(new String[]{ExpressionConstants.CONSTANT_TYPE,
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.PARAMETER_TYPE,
                ExpressionConstants.SCRIPT_TYPE}));

        limitMaxColumnButton = widgetFactory.createButton(compoForNumberLimitation, Messages.data_setMaxColumn, SWT.CHECK);
        textOrDataMaxColumn = new ExpressionViewer(compoForNumberLimitation, SWT.BORDER, widgetFactory, getEditingDomain(), FormPackage.Literals.DYNAMIC_TABLE__MAX_NUMBER_OF_COLUMN);
        textOrDataMaxColumn.addFilter(new AvailableExpressionTypeFilter(new String[]{ExpressionConstants.CONSTANT_TYPE,
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.PARAMETER_TYPE,
                ExpressionConstants.SCRIPT_TYPE}));
    }

    protected Composite createAddRemoveRow(final Composite composite,
            final TabbedPropertySheetWidgetFactory widgetFactory) {

        final Composite compo = widgetFactory.createComposite(composite);
        compo.setLayout(new GridLayout(2, false));
        compo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;

        allowAddRemoveRowButton = widgetFactory.createButton(compo, Messages.data_allowAddRemoveRow, SWT.CHECK);

        /*Number limitation*/
        final Composite compoForNumberLimitation = widgetFactory.createComposite(compo);
        compoForNumberLimitation.setLayout(new GridLayout(2, false));
        compoForNumberLimitation.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;

        limitMinRowButton = widgetFactory.createButton(compoForNumberLimitation, Messages.data_setMinRow, SWT.CHECK);
        textOrDataMinRow = new ExpressionViewer(compoForNumberLimitation, SWT.BORDER, widgetFactory, getEditingDomain(), FormPackage.Literals.DYNAMIC_TABLE__MIN_NUMBER_OF_ROW);
        textOrDataMinRow.addFilter(new AvailableExpressionTypeFilter(new String[]{ExpressionConstants.CONSTANT_TYPE,
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.PARAMETER_TYPE,
                ExpressionConstants.SCRIPT_TYPE}));


        limitMaxRowButton = widgetFactory.createButton(compoForNumberLimitation, Messages.data_setMaxRow, SWT.CHECK);
        textOrDataMaxRow = new ExpressionViewer(compoForNumberLimitation, SWT.BORDER, widgetFactory, getEditingDomain(), FormPackage.Literals.DYNAMIC_TABLE__MAX_NUMBER_OF_ROW);
        textOrDataMaxRow.addFilter(new AvailableExpressionTypeFilter(new String[]{ExpressionConstants.CONSTANT_TYPE,
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.PARAMETER_TYPE,
                ExpressionConstants.SCRIPT_TYPE}));

        return compoForNumberLimitation;
    }

    @Override
    protected DynamicTable getEObject() {
        return (DynamicTable) super.getEObject();
    }

    @Override
    public void refresh() {

        super.refresh();
    }

    @Override
    protected boolean isOuputActivated() {
        return true;
    }

    @Override
    protected void refreshDataBinding() {
        super.refreshDataBinding();
        final DynamicTable dynamicTable = getEObject();
        if(dynamicTable != null){
            bindRow(dynamicTable);
            bindColumn(dynamicTable);
        }
    }


    protected void bindColumn(final DynamicTable dynamicTable) {
        dataBindingContext.bindValue(SWTObservables.observeSelection(allowAddRemoveColumnButton), EMFEditObservables.observeValue(getEditingDomain(), dynamicTable, FormPackage.Literals.DYNAMIC_TABLE__ALLOW_ADD_REMOVE_COLUMN));

        if(dynamicTable != null){
            Expression minNumberOfColumn = dynamicTable.getMinNumberOfColumn();
            if(minNumberOfColumn == null){
                minNumberOfColumn =  ExpressionFactory.eINSTANCE.createExpression();
                minNumberOfColumn.setReturnType(Integer.class.getName());
                minNumberOfColumn.setReturnTypeFixed(true);
                getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), dynamicTable, FormPackage.Literals.DYNAMIC_TABLE__MIN_NUMBER_OF_COLUMN, minNumberOfColumn));
            }
        }
        if(dynamicTable != null){
            Expression maxNumberOfColumn = dynamicTable.getMaxNumberOfColumn();
            if(maxNumberOfColumn == null){
                maxNumberOfColumn =  ExpressionFactory.eINSTANCE.createExpression();
                maxNumberOfColumn.setReturnType(Integer.class.getName());
                maxNumberOfColumn.setReturnTypeFixed(true);
                getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), dynamicTable, FormPackage.Literals.DYNAMIC_TABLE__MAX_NUMBER_OF_COLUMN, maxNumberOfColumn));
            }
        }

        /*
         * Synchronize with the model.
         * */
        dataBindingContext.bindValue(
                ViewerProperties.singleSelection().observe(textOrDataMinColumn),
                EMFEditProperties.value(getEditingDomain(), FormPackage.Literals.DYNAMIC_TABLE__MIN_NUMBER_OF_COLUMN).observe(dynamicTable));
        dataBindingContext.bindValue(
                ViewerProperties.singleSelection().observe(textOrDataMaxColumn),
                EMFEditProperties.value(getEditingDomain(), FormPackage.Literals.DYNAMIC_TABLE__MAX_NUMBER_OF_COLUMN).observe(dynamicTable));

        dataBindingContext.bindValue(SWTObservables.observeSelection(limitMinColumnButton), EMFEditObservables.observeValue(getEditingDomain(), dynamicTable, FormPackage.Literals.DYNAMIC_TABLE__LIMIT_MIN_NUMBER_OF_COLUMN));
        dataBindingContext.bindValue(SWTObservables.observeSelection(limitMaxColumnButton), EMFEditObservables.observeValue(getEditingDomain(), dynamicTable, FormPackage.Literals.DYNAMIC_TABLE__LIMIT_MAX_NUMBER_OF_COLUMN));

        /*
         * Enable the combo only if we want to set a number limitation.
         * */
        dataBindingContext.bindValue(SWTObservables.observeEnabled(textOrDataMinColumn.getControl()), SWTObservables.observeSelection(limitMinColumnButton));
        dataBindingContext.bindValue(SWTObservables.observeEnabled(textOrDataMaxColumn.getControl()), SWTObservables.observeSelection(limitMaxColumnButton));
        
        dataBindingContext.bindValue(SWTObservables.observeVisible(limitMinColumnButton),SWTObservables.observeSelection(allowAddRemoveColumnButton),new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),null);
        dataBindingContext.bindValue(SWTObservables.observeVisible(limitMaxColumnButton),SWTObservables.observeSelection(allowAddRemoveColumnButton),new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),null);
        dataBindingContext.bindValue(SWTObservables.observeVisible(textOrDataMinColumn.getControl()),SWTObservables.observeSelection(allowAddRemoveColumnButton),new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),null);
        dataBindingContext.bindValue(SWTObservables.observeVisible(textOrDataMaxColumn.getControl()),SWTObservables.observeSelection(allowAddRemoveColumnButton),new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),null);
   
        textOrDataMaxColumn.setInput(dynamicTable);
        textOrDataMinColumn.setInput(dynamicTable);
    }


    protected void bindRow(final DynamicTable dynamicTable) {
        dataBindingContext.bindValue(SWTObservables.observeSelection(allowAddRemoveRowButton), EMFEditObservables.observeValue(getEditingDomain(), dynamicTable, FormPackage.Literals.DYNAMIC_TABLE__ALLOW_ADD_REMOVE_ROW));

        if(dynamicTable != null){
            Expression minNumberOfRow = dynamicTable.getMinNumberOfColumn();
            if(minNumberOfRow == null){
                minNumberOfRow =  ExpressionFactory.eINSTANCE.createExpression();
                minNumberOfRow.setReturnType(Integer.class.getName());
                minNumberOfRow.setReturnTypeFixed(true);
                getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), dynamicTable, FormPackage.Literals.DYNAMIC_TABLE__MIN_NUMBER_OF_ROW, minNumberOfRow));
            }
        }

        if(dynamicTable != null){
            Expression maxNumberOfRow = dynamicTable.getMaxNumberOfRow();
            if(maxNumberOfRow == null){
                maxNumberOfRow =  ExpressionFactory.eINSTANCE.createExpression();
                maxNumberOfRow.setReturnType(Integer.class.getName());
                maxNumberOfRow.setReturnTypeFixed(true);
                getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), dynamicTable, FormPackage.Literals.DYNAMIC_TABLE__MAX_NUMBER_OF_ROW, maxNumberOfRow));
            }
        }

        /*
         * Synchronize with the model.
         * */
        dataBindingContext.bindValue(
                ViewerProperties.singleSelection().observe(textOrDataMinRow),
                EMFEditProperties.value(getEditingDomain(), FormPackage.Literals.DYNAMIC_TABLE__MIN_NUMBER_OF_ROW).observe(dynamicTable));
        dataBindingContext.bindValue(
                ViewerProperties.singleSelection().observe(textOrDataMaxRow),
                EMFEditProperties.value(getEditingDomain(), FormPackage.Literals.DYNAMIC_TABLE__MAX_NUMBER_OF_ROW).observe(dynamicTable));
        dataBindingContext.bindValue(SWTObservables.observeSelection(limitMinRowButton), EMFEditObservables.observeValue(getEditingDomain(), dynamicTable, FormPackage.Literals.DYNAMIC_TABLE__LIMIT_MIN_NUMBER_OF_ROW));
        dataBindingContext.bindValue(SWTObservables.observeSelection(limitMaxRowButton), EMFEditObservables.observeValue(getEditingDomain(), dynamicTable, FormPackage.Literals.DYNAMIC_TABLE__LIMIT_MAX_NUMBER_OF_ROW));

        /*
         * Enable the combo only if we want to set a number limitation.
         * */
        dataBindingContext.bindValue(SWTObservables.observeEnabled(textOrDataMinRow.getControl()), SWTObservables.observeSelection(limitMinRowButton));
        dataBindingContext.bindValue(SWTObservables.observeEnabled(textOrDataMaxRow.getControl()), SWTObservables.observeSelection(limitMaxRowButton));

        dataBindingContext.bindValue(SWTObservables.observeVisible(limitMinRowButton),EMFEditObservables.observeValue(getEditingDomain(), dynamicTable, FormPackage.Literals.DYNAMIC_TABLE__ALLOW_ADD_REMOVE_ROW), new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),null);
        dataBindingContext.bindValue(SWTObservables.observeVisible(limitMaxRowButton),EMFEditObservables.observeValue(getEditingDomain(), dynamicTable, FormPackage.Literals.DYNAMIC_TABLE__ALLOW_ADD_REMOVE_ROW),new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),null);
        dataBindingContext.bindValue(SWTObservables.observeVisible(textOrDataMinRow.getControl()),EMFEditObservables.observeValue(getEditingDomain(), dynamicTable, FormPackage.Literals.DYNAMIC_TABLE__ALLOW_ADD_REMOVE_ROW), new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),null);
        dataBindingContext.bindValue(SWTObservables.observeVisible(textOrDataMaxRow.getControl()),EMFEditObservables.observeValue(getEditingDomain(), dynamicTable, FormPackage.Literals.DYNAMIC_TABLE__ALLOW_ADD_REMOVE_ROW), new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),null);
    
        textOrDataMinRow.setInput(dynamicTable);
        textOrDataMaxRow.setInput(dynamicTable);   
    }

    @Override
    public String getSectionDescription() {
        return null;
    }

    @Override
    protected void setEObject(final EObject object) {
        eObject = object;
        if (getEObject() != null && !getEObject().equals(lastKnownObject)) {
            disposeDataBinding();
            lastKnownObject = getEObject();
            if (isOuputActivated()) {
                outputComposite.setVisible(true);
                contrib.setEditingDomain(getEditingDomain());
                contrib.setEObject(lastKnownObject);
                contrib.superBind();
            } else {
                outputComposite.setVisible(false);
            }
            updateViewerInput() ;
            refreshDataBinding();
        }
    }

    @Override
    protected void createContent(final Composite parent) {
        final TabbedPropertySheetWidgetFactory widgetFactory = getWidgetFactory();
        outputComposite = widgetFactory.createComposite(parent);
        outputComposite.setLayout(new GridLayout(2, false));
        outputComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        widgetFactory.createCLabel(outputComposite, contrib.getLabel());
        final Composite rightOutputComposite = widgetFactory.createComposite(outputComposite);
        rightOutputComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        contrib.createControl(rightOutputComposite, widgetFactory, null);

        createAddRemoveRow(parent, widgetFactory);
        createAddRemoveColumn(parent, widgetFactory);

    }
}
