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
package org.bonitasoft.studio.parameters.expression.editor;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.expression.editor.ExpressionProviderService;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.expression.editor.provider.SelectionAwareExpressionEditor;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.parameters.i18n.Messages;
import org.bonitasoft.studio.parameters.property.section.provider.ParameterNameLabelProvider;
import org.bonitasoft.studio.parameters.wizard.page.AddParameterWizard;
import org.bonitasoft.studio.parameters.wizard.page.ParameterWizardDialog;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 */
public class ParameterEditor extends SelectionAwareExpressionEditor implements
        IExpressionEditor {

    private TableViewer viewer;

    private GridLayout gridLayout;

    private Expression editorInputExpression;

    private Composite mainComposite;

    private Text typeText;

    private Button addExpressionButton;

    private boolean isPageFlowContext = false;

    @Override
    public Control createExpressionEditor(final Composite parent, final EMFDataBindingContext ctx) {

        mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        createTableViewer(mainComposite);

        createAddExpressionButton(mainComposite);

        createReturnTypeComposite(mainComposite);

        return mainComposite;
    }

    private void createTableViewer(final Composite parent) {

        final Label filler = new Label(mainComposite, SWT.NONE);
        filler.setLayoutData(
                GridDataFactory.fillDefaults().span(2, 1).indent(0, -LayoutConstants.getSpacing().y + 1).create());

        viewer = new TableViewer(mainComposite, SWT.FULL_SELECTION | SWT.BORDER
                | SWT.SINGLE | SWT.V_SCROLL);
        final TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(100, false));
        viewer.getTable().setLayout(layout);
        viewer.getTable().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());

        final TableViewerColumn columnViewer = new TableViewerColumn(viewer, SWT.NONE);
        final TableColumn column = columnViewer.getColumn();
        column.setText(Messages.name);

        final TableColumnSorter sorter = new TableColumnSorter(viewer);
        sorter.setColumn(column);

        viewer.getTable().setHeaderVisible(true);
        viewer.setContentProvider(new ArrayContentProvider());
        viewer.setLabelProvider(new ParameterNameLabelProvider());

        viewer.addPostSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                if (!event.getSelection().isEmpty()) {
                    ParameterEditor.this.fireSelectionChanged();
                }
            }
        });
    }

    private void createAddExpressionButton(final Composite parent) {
        addExpressionButton = new Button(parent, SWT.FLAT);
        addExpressionButton.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.LEFT, SWT.CENTER).hint(85, SWT.DEFAULT).create());
        addExpressionButton.setText(Messages.addData);
    }

    protected void createReturnTypeComposite(final Composite parent) {
        final Composite typeComposite = new Composite(parent, SWT.NONE);
        typeComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).align(SWT.RIGHT, SWT.CENTER).create());
        typeComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        final Label typeLabel = new Label(typeComposite, SWT.NONE);
        typeLabel.setText(Messages.returnType);
        typeLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.FILL, SWT.CENTER).create());

        typeText = new Text(typeComposite, SWT.BORDER | SWT.READ_ONLY);
        typeText.setLayoutData(GridDataFactory.fillDefaults().hint(250, SWT.DEFAULT)
                .align(SWT.FILL, SWT.CENTER).indent(10, 0).create());

    }

    protected void handleSpecificDatatypeEdition(final Data data) {
        if (gridLayout.numColumns > 1) {
            mainComposite.getChildren()[1].dispose();
            gridLayout.numColumns--;
            viewer.getTable().setLayoutData(
                    GridDataFactory.fillDefaults().grab(true, true).create());
            mainComposite.layout();
        }

    }

    private void fillViewerInput(final EObject context) {
        final Set<Parameter> input = new HashSet<>();
        final IExpressionProvider provider = ExpressionProviderService.getInstance()
                .getExpressionProvider(ExpressionConstants.PARAMETER_TYPE);
        for (final Expression e : provider.getExpressions(context)) {
            if (editorInputExpression.isReturnTypeFixed()) {
                if (compatibleReturnType(editorInputExpression, e)) {
                    input.add((Parameter) e.getReferencedElements().get(0));
                }
            } else {
                input.add((Parameter) e.getReferencedElements().get(0));
            }
        }
        viewer.setInput(input);
    }

    private void expressionButtonListener(final EObject context) {
        final ParameterWizardDialog parameterDialog = new ParameterWizardDialog(
                Display.getCurrent().getActiveShell(),
                new AddParameterWizard(ModelHelper.getParentProcess(context), TransactionUtil.getEditingDomain(context)));
        if (parameterDialog.open() == Dialog.OK) {
            fillViewerInput(context);
        }
    }

    @Override
    public void bindExpression(final EMFDataBindingContext dataBindingContext,
            final EObject context, final Expression inputExpression, final ViewerFilter[] filters,
            final ExpressionViewer expressionViewer) {
        final EObject finalContext = context;
        addExpressionButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                expressionButtonListener(finalContext);
            }
        });
        editorInputExpression = inputExpression;
        fillViewerInput(context);

        final IObservableValue contentObservable = EMFObservables
                .observeValue(inputExpression,
                        ExpressionPackage.Literals.EXPRESSION__CONTENT);
        final IObservableValue nameObservable = EMFObservables.observeValue(
                inputExpression, ExpressionPackage.Literals.EXPRESSION__NAME);
        final IObservableValue<String> returnTypeObservable = EMFObservables.observeValue(
                inputExpression,
                ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE);
        final IObservableValue referenceObservable = EMFObservables.observeValue(
                inputExpression,
                ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS);

        final UpdateValueStrategy selectionToName = new UpdateValueStrategy();
        final IConverter nameConverter = new Converter(Parameter.class, String.class) {

            @Override
            public Object convert(final Object parameter) {
                return parameter != null ? ((Parameter) parameter).getName() : null;
            }

        };
        selectionToName.setConverter(nameConverter);

        final UpdateValueStrategy selectionToContent = new UpdateValueStrategy();
        final IConverter contentConverter = new Converter(Parameter.class,
                String.class) {

            @Override
            public Object convert(final Object parameter) {
                return parameter != null ? ((Parameter) parameter).getName() : null;
            }

        };
        selectionToContent.setConverter(contentConverter);

        final UpdateValueStrategy selectionToReferencedData = new UpdateValueStrategy();
        final IConverter referenceConverter = new Converter(Parameter.class,
                List.class) {

            @Override
            public Object convert(final Object parameter) {
                return parameter != null ? Collections.singletonList(parameter) : Collections.emptyList();
            }

        };
        selectionToReferencedData.setConverter(referenceConverter);

        final UpdateValueStrategy referencedDataToSelection = new UpdateValueStrategy();
        final IConverter referencetoDataConverter = new Converter(List.class,
                Parameter.class) {

            @SuppressWarnings("unchecked")
            @Override
            public Object convert(final Object parameterList) {
                final List<? extends EObject> list = parameterList != null
                        ? (List<? extends EObject>) parameterList
                        : Collections.emptyList();
                return list.stream()
                        .filter(Parameter.class::isInstance)
                        .map(Parameter.class::cast)
                        .findFirst()
                        .map(parameterInput -> {
                            return ((Collection<Parameter>) viewer.getInput()).stream()
                                    .filter(parameter -> Objects.equals(parameterInput.getName(), parameter.getName()))
                                    .filter(parameter -> Objects.equals(parameterInput.getTypeClassname(),
                                            parameter.getTypeClassname()))
                                    .findFirst()
                                    .orElse(null);
                        })
                        .orElse(null);
            }

        };
        referencedDataToSelection.setConverter(referencetoDataConverter);

        IObservableValue<Parameter> observeSingleSelection = ViewersObservables.observeSingleSelection(viewer);

        dataBindingContext.bindValue(observeSingleSelection, nameObservable,
                selectionToName, neverUpdateValueStrategy().create());
        dataBindingContext.bindValue(observeSingleSelection, contentObservable,
                selectionToContent, neverUpdateValueStrategy().create());
        observeSingleSelection.addValueChangeListener(e -> {
            if (e.getObservableValue().getValue() != null) {
                returnTypeObservable.setValue(e.getObservableValue().getValue().getTypeClassname());
            }
        });
        dataBindingContext.bindValue(observeSingleSelection, referenceObservable, selectionToReferencedData,
                referencedDataToSelection);
        dataBindingContext.bindValue(WidgetProperties.text(SWT.Modify).observe(typeText), returnTypeObservable);
    }

    @Override
    public boolean canFinish() {
        return !viewer.getSelection().isEmpty();
    }

    @Override
    public void okPressed() {
        if (!editorInputExpression.getContent().equals(
                editorInputExpression.getName())) {
            editorInputExpression.setName(editorInputExpression.getContent());
        }
    }

    @Override
    public Control getTextControl() {
        return null;
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

}
