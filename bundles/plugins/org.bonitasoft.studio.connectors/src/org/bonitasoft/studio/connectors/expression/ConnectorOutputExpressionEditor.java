/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.connectors.expression;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.expression.editor.ExpressionProviderService;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.expression.editor.provider.SelectionAwareExpressionEditor;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 */
public class ConnectorOutputExpressionEditor extends SelectionAwareExpressionEditor implements IExpressionEditor {

    private Composite mainComposite;

    private TableViewer viewer;

    private Expression inputExpression;

    private Text typeText;

    private boolean isPageFlowContext = false;

    public ConnectorOutputExpressionEditor() {

    }

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#createExpressionEditor(org.eclipse.swt.widgets.
     * Composite)
     */
    @Override
    public Control createExpressionEditor(Composite parent, EMFDataBindingContext ctx) {
        mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(new GridLayout(1, false));

        viewer = new TableViewer(mainComposite, SWT.FULL_SELECTION | SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(100, false));
        viewer.getTable().setLayout(layout);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        TableViewerColumn columnViewer = new TableViewerColumn(viewer, SWT.NONE);
        TableColumn column = columnViewer.getColumn();
        column.setText(Messages.name);

        TableColumnSorter sorter = new TableColumnSorter(viewer);
        sorter.setColumn(column);

        viewer.getTable().setHeaderVisible(true);

        viewer.setContentProvider(new ArrayContentProvider());
        viewer.setLabelProvider(new LabelProvider() {

            @Override
            public String getText(Object element) {
                return ((Output) element).getName();
            }

            @Override
            public Image getImage(Object element) {
                return Pics.getImage("output.png", ConnectorPlugin.getDefault());
            }
        });
        viewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent arg0) {
                ConnectorOutputExpressionEditor.this.fireSelectionChanged();
            }
        });

        createReturnTypeComposite(parent);

        return mainComposite;
    }

    protected void createReturnTypeComposite(Composite parent) {
        Composite typeComposite = new Composite(parent, SWT.NONE);
        typeComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        GridLayout gl = new GridLayout(2, false);
        gl.marginWidth = 0;
        gl.marginHeight = 0;
        typeComposite.setLayout(gl);

        Label typeLabel = new Label(typeComposite, SWT.NONE);
        typeLabel.setText(Messages.returnType);
        typeLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).create());

        typeText = new Text(typeComposite, SWT.BORDER | SWT.READ_ONLY);
        typeText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.CENTER).create());

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#bindExpression(org.eclipse.emf.databinding.
     * EMFDataBindingContext,
     * org.eclipse.emf.ecore.EObject, org.bonitasoft.studio.model.expression.Expression,
     * org.eclipse.emf.edit.domain.EditingDomain)
     */
    @Override
    public void bindExpression(EMFDataBindingContext dataBindingContext, EObject context, Expression inputExpression,
            ViewerFilter[] filters,
            ExpressionViewer expressionViewer) {
        this.inputExpression = inputExpression;
        Set<Output> input = new HashSet<>();
        IExpressionProvider provider = ExpressionProviderService.getInstance()
                .getExpressionProvider(ExpressionConstants.CONNECTOR_OUTPUT_TYPE);
        for (Expression e : provider.getExpressions(context)) {
            if (inputExpression.isReturnTypeFixed()) {
                if (e.getReturnType().equals(inputExpression.getReturnType())) {
                    input.add((Output) e.getReferencedElements().get(0));
                }
            } else {
                input.add((Output) e.getReferencedElements().get(0));
            }
        }
        viewer.setInput(input);
        for (ViewerFilter filter : filters) {
            viewer.addFilter(filter);
        }

        IObservableValue contentObservable = EMFObservables.observeValue(inputExpression,
                ExpressionPackage.Literals.EXPRESSION__CONTENT);
        IObservableValue nameObservable = EMFObservables.observeValue(inputExpression,
                ExpressionPackage.Literals.EXPRESSION__NAME);
        IObservableValue returnTypeObservable = EMFObservables.observeValue(inputExpression,
                ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE);
        IObservableValue referenceObservable = EMFObservables.observeValue(inputExpression,
                ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS);

        UpdateValueStrategy selectionToName = new UpdateValueStrategy();

        IConverter nameConverter = new Converter(Output.class, String.class) {

            @Override
            public Object convert(Object output) {
                return output != null ? ((Output) output).getName() : null;
            }

        };
        selectionToName.setConverter(nameConverter);

        UpdateValueStrategy selectionToContent = new UpdateValueStrategy();
        IConverter contentConverter = new Converter(Output.class, String.class) {

            @Override
            public Object convert(Object output) {
                return output != null ? ((Output) output).getName() : null;
            }

        };
        selectionToContent.setConverter(contentConverter);

        UpdateValueStrategy selectionToReturnType = new UpdateValueStrategy();
        IConverter returnTypeConverter = new Converter(Output.class, String.class) {

            @Override
            public Object convert(Object output) {
                return output != null ? ((Output) output).getType() : null;
            }

        };
        selectionToReturnType.setConverter(returnTypeConverter);

        UpdateValueStrategy selectionToReferencedData = new UpdateValueStrategy();
        IConverter referenceConverter = new Converter(Output.class, List.class) {

            @Override
            public Object convert(Object output) {
                return output != null ? Collections.singletonList(output) : Collections.emptyList();
            }

        };
        selectionToReferencedData.setConverter(referenceConverter);

        UpdateValueStrategy referencedDataToSelection = new UpdateValueStrategy();
        IConverter modelReferenceToTarget = new Converter(List.class, Output.class) {

            @Override
            public Object convert(Object outputList) {
                List<Output> list = (List<Output>) outputList;
                if (!list.isEmpty()) {
                    Output output = list.get(0);
                    for (int i = 0; i < viewer.getTable().getItemCount(); i++) {
                        Output out = (Output) viewer.getElementAt(i);
                        if (out.getName().equals(output.getName())
                                && out.getType().equals(output.getType())) {
                            return out;
                        }
                    }
                }
                return null;
            }

        };
        referencedDataToSelection.setConverter(modelReferenceToTarget);
        dataBindingContext.bindValue(SWTObservables.observeText(typeText, SWT.Modify), returnTypeObservable);
        dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(viewer), nameObservable, selectionToName,
                new UpdateValueStrategy(
                        UpdateValueStrategy.POLICY_NEVER));
        dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(viewer), contentObservable,
                selectionToContent, new UpdateValueStrategy(
                        UpdateValueStrategy.POLICY_NEVER));
        dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(viewer), returnTypeObservable,
                selectionToReturnType, new UpdateValueStrategy(
                        UpdateValueStrategy.POLICY_NEVER));
        dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(viewer), referenceObservable,
                selectionToReferencedData,
                referencedDataToSelection);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#canFinish()
     */
    @Override
    public boolean canFinish() {
        return !viewer.getSelection().isEmpty();
    }

    @Override
    public void okPressed() {
        if (!inputExpression.getContent().equals(inputExpression.getName())) {
            inputExpression.setName(inputExpression.getContent());
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
    public void setIsPageFlowContext(boolean isPageFlowContext) {
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
    public void setIsOverviewContext(boolean isOverviewContext) {
    }

}
