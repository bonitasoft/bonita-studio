/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.businessobject.ui.expression;

import static com.google.common.base.Predicates.instanceOf;
import static com.google.common.base.Predicates.not;
import static com.google.common.collect.Iterables.removeIf;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.bonitasoft.studio.businessobject.core.expression.QueryExpressionProvider;
import org.bonitasoft.studio.businessobject.core.expression.model.BusinessObjectExpressionQuery;
import org.bonitasoft.studio.businessobject.core.expression.model.QueryExpressionModel;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.provider.ExpressionColumnLabelProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.SelectionAwareExpressionEditor;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

public class QueryExpressionEditor extends SelectionAwareExpressionEditor implements IExpressionEditor {

    private IViewerObservableValue observeBOSingleSelection;

    private IViewerObservableValue observeQuerySingleSelection;

    private final QueryExpressionProvider queryExpressionProvider;

    private ReferencedExpressionEditingSupport editingSupport;

    private Expression inputExpression;

    private QueryExpressionModel queryExpressionModel;

    private IObservableList queryParameterObserveDetailList;

    public QueryExpressionEditor(final QueryExpressionProvider queryExpressionProvider) {
        this.queryExpressionProvider = queryExpressionProvider;
    }

    @Override
    public Control createExpressionEditor(final Composite parent, final EMFDataBindingContext ctx) {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).indent(0, 1).create());

        final Label queryExpressionGuidanceLabel = new Label(composite, SWT.WRAP);
        queryExpressionGuidanceLabel
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(0, 0).span(2, 1).create());
        queryExpressionGuidanceLabel.setText(Messages.queryExpressionGuidance);

        final QueryExpressionModel queryExpresisonModel = getQueryExpressionModel();

        final IObservableList observeBoList = PojoObservables.observeList(queryExpresisonModel, "businessObjects");
        createBusinessObjectComboViewer(composite, observeBoList);

        final IObservableList observeQueryList = PojoObservables.observeDetailList(observeBOSingleSelection,
                "queryExpressions", Expression.class);
        createQueryComboViewer(composite, observeQueryList);
        createQueryTextContent(composite, ctx);
        createQueryParametersTable(composite);
        createReturnTypeText(composite, ctx);
        return composite;
    }

    private QueryExpressionModel getQueryExpressionModel() {
        if (queryExpressionModel == null) {
            queryExpressionModel = queryExpressionProvider.buildQueryExpressionModel();
        }
        return queryExpressionModel;
    }

    private void createQueryParametersTable(final Composite parent) {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, true).create());
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 2).create());

        final Label parameterLabel = new Label(composite, SWT.NONE);
        parameterLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).create());
        parameterLabel.setText(Messages.parameters);

        final ControlDecoration parameterDecoration = new ControlDecoration(parameterLabel, SWT.RIGHT);
        parameterDecoration.setShowOnlyOnFocus(false);
        parameterDecoration.setDescriptionText(Messages.paginationParameterHint);
        parameterDecoration.setImage(Pics.getImage(PicsConstants.hint));
        parameterDecoration.hide();
        observeQuerySingleSelection.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                final Object newValue = event.diff.getNewValue();
                if (newValue instanceof Expression) {
                    if (List.class.getName().equals(((Expression) newValue).getReturnType())) {
                        parameterDecoration.show();
                    } else {
                        parameterDecoration.hide();
                    }
                }

            }
        });
        Table table = new Table(composite, SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL) ;
        final TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(1, false));
        tableLayout.addColumnData(new ColumnWeightData(1, false));
        table.setLayout(tableLayout);
        final TableViewer parametersTableViewer = new TableViewer(table);
        table.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 80).create());
        parametersTableViewer.getTable().setLinesVisible(false);
        parametersTableViewer.getTable().setHeaderVisible(true);
        parametersTableViewer.setContentProvider(new ObservableListContentProvider());
        
        queryParameterObserveDetailList = EMFObservables.observeDetailList(Realm.getDefault(),
                observeQuerySingleSelection,
                ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS);

        createNameColumn(parametersTableViewer);
        createValueColumn(parametersTableViewer);
        
        parametersTableViewer.setInput(queryParameterObserveDetailList);
    }

    private void createNameColumn(final TableViewer tableViewer) {
        final TableViewerColumn nameColumnViewer = new TableViewerColumn(tableViewer, SWT.LEFT);
        final TableColumn column = nameColumnViewer.getColumn();
        column.setText(Messages.name);
        nameColumnViewer.setLabelProvider(
                new LabelProviderBuilder<Expression>()
                        .withTextProvider(Expression::getName)
                        .createColumnLabelProvider());
    }

    private void createValueColumn(final TableViewer tableViewer) {
        final TableViewerColumn valueColumnViewer = new TableViewerColumn(tableViewer, SWT.FILL);
        final TableColumn column = valueColumnViewer.getColumn();
        column.setText(Messages.value);
        final ExpressionColumnLabelProvider expressionLabelProvider = new ExpressionColumnLabelProvider(0);
        valueColumnViewer.setLabelProvider(new LabelProviderBuilder<Expression>()
                .withTextProvider(exp -> exp.getReferencedElements().isEmpty() ? null
                        : expressionLabelProvider.getText(exp.getReferencedElements().get(0)))
                .withImageProvider(exp -> exp.getReferencedElements().isEmpty() ? null
                        : expressionLabelProvider.getImage(exp.getReferencedElements().get(0)))
                .createColumnLabelProvider());

        editingSupport = new ReferencedExpressionEditingSupport(valueColumnViewer.getViewer());
        editingSupport.setFilter(new AvailableExpressionTypeFilter(ExpressionConstants.CONSTANT_TYPE,
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.PARAMETER_TYPE,
                ExpressionConstants.SCRIPT_TYPE,
                ExpressionConstants.CONTRACT_INPUT_TYPE));
        valueColumnViewer.setEditingSupport(editingSupport);
    }

    private void createQueryTextContent(final Composite composite, final EMFDataBindingContext ctx) {
        final Composite queryContentComposite = new Composite(composite, SWT.NONE);
        queryContentComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
        queryContentComposite
                .setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 2).create());

        final Label queryContentLabel = new Label(queryContentComposite, SWT.NONE);
        queryContentLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).span(2, 1).create());
        queryContentLabel.setText(Messages.queryContent);

        final Text queryContentText = new Text(queryContentComposite,
                SWT.BORDER | SWT.READ_ONLY | SWT.V_SCROLL | SWT.H_SCROLL | SWT.WRAP | SWT.MULTI);
        queryContentText.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 80).create());
        ctx.bindValue(SWTObservables.observeText(queryContentText),
                EMFObservables.observeDetailValue(Realm.getDefault(), observeQuerySingleSelection,
                        ExpressionPackage.Literals.EXPRESSION__CONTENT));
    }

    private void createReturnTypeText(final Composite composite, final EMFDataBindingContext ctx) {
        final Composite returnTypeComposite = new Composite(composite, SWT.NONE);
        returnTypeComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        returnTypeComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());

        final Label returnTypeLabel = new Label(returnTypeComposite, SWT.NONE);
        returnTypeLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.CENTER).create());
        returnTypeLabel.setText(Messages.returnType);

        final Text returnTypeText = new Text(returnTypeComposite, SWT.BORDER | SWT.READ_ONLY);
        returnTypeText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        ctx.bindValue(SWTObservables.observeText(returnTypeText),
                EMFObservables.observeDetailValue(Realm.getDefault(), observeQuerySingleSelection,
                        ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE));
    }

    private void createQueryComboViewer(final Composite composite, final IObservableList observeQueryList) {
        final Composite queryComposite = new Composite(composite, SWT.NONE);
        queryComposite.setLayoutData(GridDataFactory.fillDefaults().create());
        queryComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 2).create());

        final Label queryLabel = new Label(queryComposite, SWT.NONE);
        queryLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).create());
        queryLabel.setText(Messages.queries);

        final ComboViewer queryCombo = new ComboViewer(queryComposite, SWT.READ_ONLY | SWT.BORDER);
        queryCombo.getControl().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).hint(150, SWT.DEFAULT).create());
        queryCombo.setLabelProvider(new LabelProvider() {

            @Override
            public String getText(final Object element) {
                if (element instanceof Expression) {
                    String name = ((Expression) element).getName();
                    if (name.indexOf(".") != -1) {
                        name = name.substring(name.indexOf(".") + 1, name.length());
                    }
                    return name;
                }
                return super.getText(element);
            }
        });
        queryCombo.setContentProvider(new ObservableListContentProvider());

        queryCombo.setInput(observeQueryList);

        observeQuerySingleSelection = ViewersObservables.observeSingleSelection(queryCombo);
        observeBOSingleSelection.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                if (event.diff.getOldValue() != null && observeQuerySingleSelection.getValue() == null) {
                    observeQuerySingleSelection.setValue(observeQueryList.get(0));
                }
            }
        });
    }

    private void createBusinessObjectComboViewer(final Composite composite, final IObservableList observeBoList) {
        final Composite boComposite = new Composite(composite, SWT.NONE);
        boComposite.setLayoutData(GridDataFactory.fillDefaults().create());
        boComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 2).create());

        final Label boLabel = new Label(boComposite, SWT.NONE);
        boLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).create());
        boLabel.setText(Messages.businessObject);

        final ComboViewer boCombo = new ComboViewer(boComposite, SWT.READ_ONLY | SWT.BORDER);
        boCombo.getControl().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).minSize(100, SWT.DEFAULT).create());
        boCombo.setLabelProvider(new LabelProvider() {

            @Override
            public String getText(final Object element) {
                if (element instanceof BusinessObjectExpressionQuery) {
                    return ((BusinessObjectExpressionQuery) element).getQualifiedName();
                }
                return super.getText(element);
            }
        });
        boCombo.setContentProvider(new ObservableListContentProvider());

        boCombo.setInput(observeBoList);

        observeBOSingleSelection = ViewersObservables.observeSingleSelection(boCombo);
    }

    @Override
    public void bindExpression(final EMFDataBindingContext dataBindingContext, final EObject context,
            final Expression inputExpression,
            final ViewerFilter[] viewerTypeFilters,
            final ExpressionViewer viewer) {
        editingSupport.setInput(context);
        this.inputExpression = inputExpression;
        final QueryExpressionModel expressionModel = getQueryExpressionModel();
        final String queryName = inputExpression.getName();
        if (queryName == null || queryName.isEmpty()) {
            if (!expressionModel.getBusinessObjects().isEmpty()) {
                final BusinessObjectExpressionQuery businessObjectExpressionQuery = expressionModel.getBusinessObjects()
                        .get(0);
                observeBOSingleSelection.setValue(businessObjectExpressionQuery);
                observeQuerySingleSelection.setValue(businessObjectExpressionQuery.getQueryExpressions().get(0));
            }
        } else {
            for (final BusinessObjectExpressionQuery bo : expressionModel.getBusinessObjects()) {
                for (final Expression exp : bo.getQueryExpressions()) {
                    if (exp.getName().equals(queryName)) {
                        observeBOSingleSelection.setValue(bo);
                        synchronizeQueryParameterExpressions(inputExpression, exp);
                        observeQuerySingleSelection.setValue(exp);
                        break;
                    }
                }
            }
            if (observeQuerySingleSelection.getValue() == null) {
                if (!expressionModel.getBusinessObjects().isEmpty()) {
                    final BusinessObjectExpressionQuery businessObjectExpressionQuery = expressionModel
                            .getBusinessObjects()
                            .get(0);
                    observeBOSingleSelection.setValue(businessObjectExpressionQuery);
                    observeQuerySingleSelection.setValue(businessObjectExpressionQuery.getQueryExpressions().get(0));
                }
            }
        }

        dataBindingContext.bindValue(
                EMFObservables.observeDetailValue(Realm.getDefault(), observeQuerySingleSelection,
                        ExpressionPackage.Literals.EXPRESSION__NAME),
                EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__NAME), null,
                new UpdateValueStrategy(
                        UpdateValueStrategy.POLICY_NEVER));
        dataBindingContext.bindValue(
                EMFObservables.observeDetailValue(Realm.getDefault(), observeQuerySingleSelection,
                        ExpressionPackage.Literals.EXPRESSION__CONTENT),
                EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__CONTENT), null,
                new UpdateValueStrategy(
                        UpdateValueStrategy.POLICY_NEVER));
        dataBindingContext.bindValue(
                EMFObservables.observeDetailValue(Realm.getDefault(), observeQuerySingleSelection,
                        ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE),
                EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE), null,
                new UpdateValueStrategy(
                        UpdateValueStrategy.POLICY_NEVER));
        dataBindingContext.bindValue(
                EMFObservables.observeDetailValue(Realm.getDefault(), observeQuerySingleSelection,
                        ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE_FIXED),
                EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE_FIXED),
                null,
                new UpdateValueStrategy(
                        UpdateValueStrategy.POLICY_NEVER));
        dataBindingContext.bindValue(
                EMFObservables.observeDetailValue(Realm.getDefault(), observeQuerySingleSelection,
                        ExpressionPackage.Literals.EXPRESSION__TYPE),
                EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__TYPE), null,
                new UpdateValueStrategy(
                        UpdateValueStrategy.POLICY_NEVER));
    }

    private void synchronizeQueryParameterExpressions(final Expression inputExpression, final Expression exp) {
        final List<EObject> referencedElements = newArrayList(inputExpression.getReferencedElements());
        inputExpression.getReferencedElements().clear();
        inputExpression.getReferencedElements().addAll(recomputeDependencies(exp, referencedElements));
    }

    private List<EObject> recomputeDependencies(final Expression exp, final List<EObject> referencedElements) {
        removeIf(referencedElements, not(instanceOf(Expression.class)));
        for (final EObject refElem : referencedElements) {
            final Expression existingParam = (Expression) refElem;
            for (final EObject ref : exp.getReferencedElements()) {
                final Expression queryParam = (Expression) ref;
                if (queryParam.getName().equals(existingParam.getName())
                        && queryParam.getReturnType().equals(existingParam.getReturnType())) {
                    queryParam.getReferencedElements().clear();
                    queryParam.getReferencedElements().add(
                            ExpressionHelper.createDependencyFromEObject(existingParam.getReferencedElements().get(0)));
                }
            }
        }
        return referencedElements;
    }

    @Override
    public boolean canFinish() {
        return true;
    }

    @Override
    public void okPressed() {
        final Expression queryExpression = (Expression) observeQuerySingleSelection.getValue();
        inputExpression.getReferencedElements().clear();
        for (final EObject refElem : queryExpression.getReferencedElements()) {
            final Expression existingParam = (Expression) refElem;
            inputExpression.getReferencedElements().add(EcoreUtil.copy(existingParam));
        }
    }

    @Override
    public Control getTextControl() {
        return null;
    }

    @Override
    public boolean isPageFlowContext() {
        return false;
    }

    @Override
    public void setIsPageFlowContext(final boolean isPageFlowContext) {

    }

    @Override
    public boolean isOverViewContext() {
        return false;
    }

    @Override
    public void setIsOverviewContext(final boolean isOverviewContext) {

    }

}
