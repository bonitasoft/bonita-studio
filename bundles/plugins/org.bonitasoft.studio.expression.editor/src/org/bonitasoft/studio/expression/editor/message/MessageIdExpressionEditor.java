/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.expression.editor.message;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.expression.editor.ExpressionProviderService;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.expression.editor.provider.SelectionAwareExpressionEditor;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
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
 * 
 */
public class MessageIdExpressionEditor extends SelectionAwareExpressionEditor implements IExpressionEditor {

    private Text valueText;

    private Expression inputExpression;

    private TableViewer viewer;

    private boolean isPageFlowContext = false;

    @Override
    public Control createExpressionEditor(Composite parent, EMFDataBindingContext ctx) {

        Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(new GridLayout(2, false));

        createViewer(mainComposite);

        Label valueLabel = new Label(mainComposite, SWT.NONE);
        valueLabel.setText(Messages.messageDataId);

        valueText = new Text(mainComposite, SWT.BORDER);
        valueText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        return mainComposite;
    }

    private void createViewer(Composite mainComposite) {
        viewer = new TableViewer(mainComposite, SWT.FULL_SELECTION | SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(100, false));
        viewer.getTable().setLayout(layout);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());

        TableViewerColumn columnViewer = new TableViewerColumn(viewer, SWT.NONE);
        TableColumn column = columnViewer.getColumn();
        column.setText(Messages.availableIds);

        TableColumnSorter sorter = new TableColumnSorter(viewer);
        sorter.setColumn(column);

        viewer.getTable().setHeaderVisible(true);
        viewer.setContentProvider(new ArrayContentProvider());
        viewer.setLabelProvider(new LabelProvider() {

            @Override
            public String getText(Object element) {
                return super.getText(element);
            }

            @Override
            public Image getImage(Object element) {
                return ExpressionProviderService.getInstance().getExpressionProvider(ExpressionConstants.MESSAGE_ID_TYPE).getTypeIcon();
            }
        });

        viewer.addPostSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                if (!event.getSelection().isEmpty()) {
                    MessageIdExpressionEditor.this.fireSelectionChanged();
                }
            }
        });
    }

    @Override
    public void bindExpression(EMFDataBindingContext dataBindingContext, EObject context, Expression inputExpression, ViewerFilter[] filters,
            ExpressionViewer expressionViewer) {
        this.inputExpression = inputExpression;
        IObservableValue nameModelObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__NAME);
        IObservableValue contentModelObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__CONTENT);

        Set<String> input = new HashSet<String>();
        IExpressionProvider provider = ExpressionProviderService.getInstance().getExpressionProvider(ExpressionConstants.MESSAGE_ID_TYPE);
        for (Expression e : provider.getExpressions(context)) {
            input.add(e.getName());
        }
        viewer.setInput(input);

        dataBindingContext.bindValue(SWTObservables.observeText(valueText, SWT.Modify), nameModelObservable);
        dataBindingContext.bindValue(SWTObservables.observeText(valueText, SWT.Modify), contentModelObservable);
        dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(viewer), nameModelObservable);
        dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(viewer), contentModelObservable);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#canFinish()
     */
    @Override
    public boolean canFinish() {
        return valueText != null && !valueText.isDisposed() && valueText.getText() != null && !valueText.getText().isEmpty();
    }

    @Override
    public void okPressed() {
        final String expressionContent = inputExpression.getContent();
        if (expressionContent != null && !expressionContent.equals(inputExpression.getName())) {
            inputExpression.setName(expressionContent);
        }
    }

    @Override
    public Control getTextControl() {
        return valueText;
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
