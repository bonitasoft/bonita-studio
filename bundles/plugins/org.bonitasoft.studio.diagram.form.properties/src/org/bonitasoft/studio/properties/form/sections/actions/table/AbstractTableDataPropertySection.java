/**
 * Copyright (C) 2010-2011 BonitaSoft S.A.
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
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.common.widgets.MagicComposite;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionCollectionViewer;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.expression.editor.viewer.IExpressionModeListener;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.form.AbstractTable;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.properties.form.sections.actions.contributions.InitialValueExpressionFilter;
import org.bonitasoft.studio.properties.form.sections.actions.contributions.OutputSectionContribution;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * 
 * @author Mickael Istria
 * 
 */
public abstract class AbstractTableDataPropertySection extends AbstractBonitaDescriptionSection {

    /**
     * @author Baptiste Mesta something ugly but it works, use the contribution
     *         in a normal section
     * 
     */
    final class OutputSectionContributionExtension extends OutputSectionContribution {
        @Override
        protected void bindWidgets() {
        }

        public void superBind() {
            super.bindWidgets();
        }
    }

    protected EMFDataBindingContext dataBindingContext;
    protected AbstractTable lastKnownObject;
    private Button allowHtmlButton;
    protected OutputSectionContributionExtension contrib = new OutputSectionContributionExtension();
    private Button useHorizontalHeader;
    private Button useVerticalHeader;
    private ExpressionViewer tableVerticalHeaders;
    private ExpressionViewer tableHorizontalHeaders;
    private Button topHeaderButton;
    private Button bottomHeaderButton;
    private Button leftHeaderButton;
    private Button rightHeaderButton;
    private ExpressionCollectionViewer tableViewer;
    private IExpressionModeListener expressionModeListener;
    private MagicComposite magicComposite;
    private Composite headersComposite;
    private Composite headersValueComposite;
	private InitialValueExpressionFilter initialValueExpressionFilter;


    public AbstractTableDataPropertySection() {

    }


    protected void refreshDataBinding() {
        disposeDataBinding();
        if (tableViewer != null) {
            final AbstractTable table = getEObject() ;
            if(initialValueExpressionFilter != null){
                initialValueExpressionFilter.setWidget(table);
            }
      
            if(expressionModeListener != null){
                tableViewer.removeExpressionModeListener(expressionModeListener) ;
            }

            expressionModeListener = new IExpressionModeListener() {

                public void useTable() {
                    getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), table, FormPackage.Literals.ABSTRACT_TABLE__INITIALIZED_USING_CELLS, true));
                    tableViewer.setSelection(table.getTableExpression()) ;
                    tableViewer.setEditingDomain(getEditingDomain());
                    magicComposite.show(headersComposite) ;
                    magicComposite.hide(headersValueComposite) ;
                    magicComposite.getParent().getParent().layout(true, true);
                }

                public void useSimpleExpression() {
                    getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), table, FormPackage.Literals.ABSTRACT_TABLE__INITIALIZED_USING_CELLS, false));
                    tableViewer.setSelection(table.getInputExpression()) ;
                    tableViewer.setEditingDomain(getEditingDomain());
                    magicComposite.hide(headersComposite) ;
                    magicComposite.show(headersValueComposite) ;
                    magicComposite.getParent().getParent().layout(true, true);
                }
            };

            tableViewer.addExpressionModeListener(expressionModeListener) ;

            dataBindingContext = new EMFDataBindingContext();

            dataBindingContext.bindValue(SWTObservables.observeSelection(allowHtmlButton), EMFEditObservables.observeValue(getEditingDomain(), table.getInputExpression(), ExpressionPackage.Literals.EXPRESSION__HTML_ALLOWED));

            dataBindingContext.bindValue(SWTObservables.observeSelection(useHorizontalHeader), EMFEditObservables.observeValue(getEditingDomain(), getEObject(),
                    FormPackage.Literals.ABSTRACT_TABLE__USE_HORIZONTAL_HEADER));
            dataBindingContext.bindValue(SWTObservables.observeSelection(useVerticalHeader), EMFEditObservables.observeValue(getEditingDomain(), getEObject(),
                    FormPackage.Literals.ABSTRACT_TABLE__USE_VERTICAL_HEADER));

            dataBindingContext.bindValue(SWTObservables.observeSelection(topHeaderButton), EMFEditObservables.observeValue(getEditingDomain(), getEObject(), FormPackage.Literals.ABSTRACT_TABLE__FIRST_ROW_IS_HEADER));
            dataBindingContext.bindValue(SWTObservables.observeSelection(leftHeaderButton), EMFEditObservables.observeValue(getEditingDomain(), getEObject(), FormPackage.Literals.ABSTRACT_TABLE__LEFT_COLUMN_IS_HEADER));
            dataBindingContext.bindValue(SWTObservables.observeSelection(rightHeaderButton), EMFEditObservables.observeValue(getEditingDomain(), getEObject(), FormPackage.Literals.ABSTRACT_TABLE__RIGHT_COLUMN_IS_HEADER));
            dataBindingContext.bindValue(SWTObservables.observeSelection(bottomHeaderButton), EMFEditObservables.observeValue(getEditingDomain(), getEObject(), FormPackage.Literals.ABSTRACT_TABLE__LAST_ROW_IS_HEADER));

            dataBindingContext.bindValue( ViewersObservables.observeSingleSelection(tableVerticalHeaders), EMFEditObservables.observeValue(getEditingDomain(), getEObject(), FormPackage.Literals.ABSTRACT_TABLE__VERTICAL_HEADER_EXPRESSION));
            dataBindingContext.bindValue( ViewersObservables.observeSingleSelection(tableHorizontalHeaders), EMFEditObservables.observeValue(getEditingDomain(), getEObject(), FormPackage.Literals.ABSTRACT_TABLE__HORIZONTAL_HEADER_EXPRESSION));
            if(table.isInitializedUsingCells()){
                tableViewer.setSelection(table.getTableExpression()) ;
            }else{
                tableViewer.setSelection(table.getInputExpression()) ;
            }
            if(tableViewer.isTableMode()){
                magicComposite.show(headersComposite) ;
                magicComposite.hide(headersValueComposite) ;
            }else{
                magicComposite.show(headersValueComposite) ;
                magicComposite.hide(headersComposite) ;
            }
            magicComposite.getParent().getParent().layout(true, true);
        }
    }


    protected void disposeDataBinding() {
        if (dataBindingContext != null) {
            dataBindingContext.dispose();
            dataBindingContext = null;
        }
    }

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
        super.createControls(parent, aTabbedPropertySheetPage);
        parent.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        TabbedPropertySheetWidgetFactory widgetFactory = aTabbedPropertySheetPage.getWidgetFactory();
        Composite mainComposite = widgetFactory.createComposite(parent);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
        mainComposite.setLayout(new GridLayout(2, false));

        allowHtmlButton = widgetFactory.createButton(mainComposite, Messages.GeneralSection_allowHTML, SWT.CHECK);
        allowHtmlButton.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).create()) ;

        tableViewer = new ExpressionCollectionViewer(mainComposite,0,false,2,false,null,widgetFactory,getEditingDomain(), true, false,true) ;
        tableViewer.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).span(2, 1).create()) ;
        tableViewer.addFilter(getExpressionViewerFilter());
        tableViewer.addFilter(getExpressionViewerFilter());
        
        magicComposite = new MagicComposite(mainComposite, SWT.NONE) ;
        magicComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).span(2,1).create());
     
        magicComposite.setLayout(new GridLayout(1, false)) ;
        widgetFactory.adapt(magicComposite) ;
        headersComposite = widgetFactory.createComposite(magicComposite);
        headersComposite.setLayout(new GridLayout(2, false));
        headersComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;

        useHorizontalHeader = widgetFactory.createButton(headersComposite, Messages.table_userFirstRowAsHeader, SWT.CHECK);
        useHorizontalHeader.addSelectionListener(new SelectionAdapter() {
            /* (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                if(useHorizontalHeader.getSelection()) {
                    getEditingDomain().getCommandStack().execute(new SetCommand(getEditingDomain(), getEObject(), FormPackage.Literals.ABSTRACT_TABLE__FIRST_ROW_IS_HEADER, true));
                } else {
                    getEditingDomain().getCommandStack().execute(new SetCommand(getEditingDomain(), getEObject(), FormPackage.Literals.ABSTRACT_TABLE__FIRST_ROW_IS_HEADER, false));
                    getEditingDomain().getCommandStack().execute(new SetCommand(getEditingDomain(), getEObject(), FormPackage.Literals.ABSTRACT_TABLE__LAST_ROW_IS_HEADER, false));
                }
            }
        });
        useVerticalHeader = widgetFactory.createButton(headersComposite, Messages.table_userFirstColumnAsHeader, SWT.CHECK);
        useVerticalHeader.addSelectionListener(new SelectionAdapter() {
            /* (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                if(useVerticalHeader.getSelection()){
                    getEditingDomain().getCommandStack().execute(new SetCommand(getEditingDomain(), getEObject(), FormPackage.Literals.ABSTRACT_TABLE__LEFT_COLUMN_IS_HEADER, true));
                } else {
                    getEditingDomain().getCommandStack().execute(new SetCommand(getEditingDomain(), getEObject(), FormPackage.Literals.ABSTRACT_TABLE__LEFT_COLUMN_IS_HEADER, false));
                    getEditingDomain().getCommandStack().execute(new SetCommand(getEditingDomain(), getEObject(), FormPackage.Literals.ABSTRACT_TABLE__RIGHT_COLUMN_IS_HEADER, false));
                }
            }
        });

        headersValueComposite = widgetFactory.createComposite(magicComposite);
        headersValueComposite.setLayout(new GridLayout(2, false));
        headersValueComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;

        //horizontal headers
        widgetFactory.createLabel(headersValueComposite, Messages.data_horizontalHeaderValues).setLayoutData(GridDataFactory.swtDefaults().align(SWT.END, SWT.CENTER).create()) ;
        Composite tableHorizontalHeadersComposite = widgetFactory.createComposite(headersValueComposite);
        tableHorizontalHeadersComposite.setLayout(new GridLayout(3, false));
        tableHorizontalHeadersComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
        tableHorizontalHeaders = new ExpressionViewer(tableHorizontalHeadersComposite,SWT.BORDER, widgetFactory, getEditingDomain(), FormPackage.Literals.ABSTRACT_TABLE__HORIZONTAL_HEADER_EXPRESSION) ;
        tableHorizontalHeaders.addFilter(new AvailableExpressionTypeFilter(new String[]{ExpressionConstants.VARIABLE_TYPE,ExpressionConstants.SCRIPT_TYPE}));
        tableHorizontalHeaders.setMessage(Messages.data_tooltip_tableHeaders,IStatus.INFO) ;
        tableHorizontalHeaders.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.CENTER).create());


        topHeaderButton = widgetFactory.createButton(tableHorizontalHeadersComposite, "", SWT.TOGGLE);//$NON-NLS-1$
        topHeaderButton.setImage(Pics.getImage(PicsConstants.headingTop));
        bottomHeaderButton = widgetFactory.createButton(tableHorizontalHeadersComposite, "", SWT.TOGGLE);//$NON-NLS-1$
        bottomHeaderButton.setImage(Pics.getImage(PicsConstants.headingBottom));

        //vertical headers
        widgetFactory.createLabel(headersValueComposite, Messages.data_verticalHeaderValues).setLayoutData(GridDataFactory.swtDefaults().align(SWT.END, SWT.CENTER).create()) ;

        Composite tableVerticalHeadersComposite = widgetFactory.createComposite(headersValueComposite);
        tableVerticalHeadersComposite.setLayout(new GridLayout(3, false));
        tableVerticalHeadersComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
        tableVerticalHeaders = new ExpressionViewer(tableVerticalHeadersComposite,SWT.BORDER, widgetFactory, getEditingDomain(), FormPackage.Literals.ABSTRACT_TABLE__VERTICAL_HEADER_EXPRESSION) ;
        tableVerticalHeaders.addFilter(new AvailableExpressionTypeFilter(new String[]{ExpressionConstants.VARIABLE_TYPE,ExpressionConstants.SCRIPT_TYPE}));
        tableVerticalHeaders.setMessage(Messages.data_tooltip_tableHeaders,IStatus.INFO) ;
        tableVerticalHeaders.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true,false).align(SWT.FILL, SWT.CENTER).create());

        leftHeaderButton = widgetFactory.createButton(tableVerticalHeadersComposite, "", SWT.TOGGLE);//$NON-NLS-1$
        leftHeaderButton.setImage(Pics.getImage(PicsConstants.headingLeft));
        rightHeaderButton = widgetFactory.createButton(tableVerticalHeadersComposite, "", SWT.TOGGLE);//$NON-NLS-1$
        rightHeaderButton.setImage(Pics.getImage(PicsConstants.headingRight));
    }
    
    protected AvailableExpressionTypeFilter getExpressionViewerFilter() {
		if(initialValueExpressionFilter == null){
			initialValueExpressionFilter = new InitialValueExpressionFilter(new String[]{
					ExpressionConstants.VARIABLE_TYPE,
					ExpressionConstants.SCRIPT_TYPE,
					ExpressionConstants.CONSTANT_TYPE,
					ExpressionConstants.PARAMETER_TYPE,
					ExpressionConstants.SCRIPT_TYPE,
					ExpressionConstants.DOCUMENT_TYPE,
					ExpressionConstants.XPATH_TYPE,
					ExpressionConstants.I18N_TYPE});
			initialValueExpressionFilter.setWidget(getEObject());
		}
		return initialValueExpressionFilter;
	}

    protected abstract boolean isOuputActivated();


    protected void updateViewerInput(){
        if(tableViewer != null && !tableViewer.getViewer().getControl().isDisposed()){
            AbstractTable table = getEObject() ;
            TableExpression tableInput = table.getTableExpression() ;
            TransactionalEditingDomain editingDomain = getEditingDomain() ;
            if(tableInput == null){
                tableInput =  ExpressionFactory.eINSTANCE.createTableExpression() ;
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, table, FormPackage.Literals.ABSTRACT_TABLE__TABLE_EXPRESSION, ExpressionFactory.eINSTANCE.createTableExpression()));
            }
            Expression input = table.getInputExpression() ;
            if(input == null){
                input = ExpressionFactory.eINSTANCE.createExpression() ;
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, table, FormPackage.Literals.WIDGET__INPUT_EXPRESSION,input));
            }

            tableViewer.setInput(table) ;
            tableViewer.setEditingDomain(getEditingDomain());
        }

        if(tableHorizontalHeaders != null && !tableHorizontalHeaders.getControl().isDisposed()){
            AbstractTable table = getEObject() ;
            Expression input = table.getHorizontalHeaderExpression() ;
            if(input == null){
                TransactionalEditingDomain editingDomain = getEditingDomain() ;
                input = ExpressionFactory.eINSTANCE.createExpression() ;
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, table, FormPackage.Literals.ABSTRACT_TABLE__HORIZONTAL_HEADER_EXPRESSION, input));
            }
            tableHorizontalHeaders.setInput(table) ;
        }

        if(tableVerticalHeaders != null && !tableVerticalHeaders.getControl().isDisposed()){
            AbstractTable table = getEObject() ;
            Expression input = table.getVerticalHeaderExpression() ;
            if(input == null){
                TransactionalEditingDomain editingDomain = getEditingDomain() ;
                input = ExpressionFactory.eINSTANCE.createExpression() ;
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, table, FormPackage.Literals.ABSTRACT_TABLE__VERTICAL_HEADER_EXPRESSION, input));
            }
            tableVerticalHeaders.setInput(table) ;
        }
    }

    @Override
    protected AbstractTable getEObject() {
        return (AbstractTable) super.getEObject();
    }

    @Override
    protected void setEObject(EObject object) {
        super.setEObject(object);
        if(contrib != null){
            contrib.setEditingDomain(getEditingDomain()) ;
            contrib.setEObject(getEObject()) ;
        }
        if (object != null && !object.equals(lastKnownObject)) {
            disposeDataBinding();
            updateViewerInput() ;
            refreshDataBinding();
            lastKnownObject = (AbstractTable) eObject;
        }
    }


    @Override
    public void dispose() {
        super.dispose();
        if(contrib != null){
            contrib.dispose();
        }
    }

}
