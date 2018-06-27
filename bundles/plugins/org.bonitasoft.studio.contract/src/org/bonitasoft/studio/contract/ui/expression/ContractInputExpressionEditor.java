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
package org.bonitasoft.studio.contract.ui.expression;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.contract.core.expression.ContractInputExpressionProvider;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.expression.editor.provider.SelectionAwareExpressionEditor;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.ContractInput;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 */
public class ContractInputExpressionEditor extends SelectionAwareExpressionEditor
        implements IExpressionEditor {

    private ContractInputTableViewer viewer;

    private Expression editorInputExpression;

    private Text typeText;

    private final ContractInputExpressionProvider contractInputExpressionProvider;

    public ContractInputExpressionEditor(final ContractInputExpressionProvider contractInputExpressionProvider) {
        this.contractInputExpressionProvider = contractInputExpressionProvider;
    }

    @Override
    public Control createExpressionEditor(final Composite parent, final EMFDataBindingContext ctx) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        new Label(mainComposite, SWT.NONE)
                .setLayoutData(GridDataFactory.fillDefaults().indent(0, -LayoutConstants.getSpacing().y + 1).create()); // Filler

        viewer = new ContractInputTableViewer(mainComposite, SWT.FULL_SELECTION | SWT.BORDER
                | SWT.SINGLE | SWT.V_SCROLL);
        viewer.getTable().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).create());
        viewer.initialize();
        viewer.addPostSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                if (!event.getSelection().isEmpty()) {
                    ContractInputExpressionEditor.this.fireSelectionChanged();
                }
            }
        });
        createReturnTypeComposite(parent);
        return mainComposite;
    }

    protected void createReturnTypeComposite(final Composite parent) {
        final Composite typeComposite = new Composite(parent, SWT.NONE);
        typeComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
        final GridLayout gl = new GridLayout(2, false);
        gl.marginWidth = 0;
        gl.marginHeight = 0;
        typeComposite.setLayout(gl);

        final Label typeLabel = new Label(typeComposite, SWT.NONE);
        typeLabel.setText(Messages.returnType);
        typeLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.FILL, SWT.CENTER).create());

        typeText = new Text(typeComposite, SWT.BORDER | SWT.READ_ONLY);
        typeText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false)
                .align(SWT.FILL, SWT.CENTER).indent(10, 0).create());
    }

    private void updateViewerInput(final EObject context) {
        final Set<ContractInput> input = new HashSet<>();
        final IExpressionProvider provider = getContractInputExpressionProvider();
        final Set<Expression> expressions = provider.getExpressions(context);
        for (final Expression expression : expressions) {
            if (editorInputExpression.isReturnTypeFixed()) {
                if (compatibleReturnType(editorInputExpression, expression)) {
                    input.add(getContractInputFromExpression(expression));
                }
            } else {
                input.add(getContractInputFromExpression(expression));
            }
        }
        viewer.setInput(input);
    }

    public ContractInput getContractInputFromExpression(final Expression expression) {
        return (ContractInput) expression.getReferencedElements().get(0);
    }

    protected IExpressionProvider getContractInputExpressionProvider() {
        return contractInputExpressionProvider;
    }

    @Override
    public void bindExpression(final EMFDataBindingContext dataBindingContext,
            final EObject context, final Expression inputExpression, final ViewerFilter[] filters,
            final ExpressionViewer expressionViewer) {
        editorInputExpression = inputExpression;
        updateViewerInput(context);

        final IObservableValue contentObservable = EMFObservables
                .observeValue(inputExpression,
                        ExpressionPackage.Literals.EXPRESSION__CONTENT);
        final IObservableValue nameObservable = EMFObservables.observeValue(
                inputExpression, ExpressionPackage.Literals.EXPRESSION__NAME);
        final IObservableValue<String> returnTypeObservable = EMFObservables.observeValue(inputExpression,
                ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE);
        final IObservableValue referenceObservable = EMFObservables.observeValue(
                inputExpression,
                ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS);

        IObservableValue<ContractInput> observeSingleSelection = ViewersObservables.observeSingleSelection(viewer);
        dataBindingContext.bindValue(observeSingleSelection, EMFObservables.observeValue(
                inputExpression, ExpressionPackage.Literals.EXPRESSION__TYPE),
                selectionToTypeStrategy(), new UpdateValueStrategy(
                        UpdateValueStrategy.POLICY_NEVER));

        dataBindingContext.bindValue(observeSingleSelection, nameObservable,
                selectionToInputNameStrategy(), new UpdateValueStrategy(
                        UpdateValueStrategy.POLICY_NEVER));
        dataBindingContext.bindValue(observeSingleSelection, contentObservable,
                selectionToInputNameStrategy(), new UpdateValueStrategy(
                        UpdateValueStrategy.POLICY_NEVER));
        observeSingleSelection.addValueChangeListener(e -> {
            if (e.getObservableValue().getValue() != null) {
                returnTypeObservable
                        .setValue(ExpressionHelper.getContractInputReturnType(e.getObservableValue().getValue()));
            }
        });
        dataBindingContext.bindValue(
                observeSingleSelection,
                referenceObservable, selectionToContractInputStrategy(),
                contractInputToSelectionStrategy());
        dataBindingContext.bindValue(WidgetProperties.text(SWT.Modify).observe(typeText), returnTypeObservable);
    }

    protected UpdateValueStrategy selectionToTypeStrategy() {
        final UpdateValueStrategy strategy = new UpdateValueStrategy();
        final IConverter converter = new Converter(ContractInput.class,
                String.class) {

            @Override
            public Object convert(final Object input) {
                return ExpressionConstants.CONTRACT_INPUT_TYPE;
            }

        };
        strategy.setConverter(converter);
        return strategy;
    }

    protected UpdateValueStrategy contractInputToSelectionStrategy() {
        final UpdateValueStrategy referencedDataToSelection = new UpdateValueStrategy();
        final IConverter referencetoDataConverter = new Converter(List.class,
                ContractInput.class) {

            @SuppressWarnings("unchecked")
            @Override
            public Object convert(final Object inputList) {
                final List<? extends EObject> list = inputList != null
                        ? (List<? extends EObject>) inputList
                        : Collections.emptyList();
                return list.stream()
                        .filter(ContractInput.class::isInstance)
                        .map(ContractInput.class::cast)
                        .findFirst()
                        .map(contractInput -> {
                            return ((Collection<ContractInput>) viewer.getInput()).stream()
                                    .filter(input -> EcoreUtil.equals(contractInput, input))
                                    .findFirst()
                                    .orElse(null);
                        })
                        .orElse(null);
            }

        };
        referencedDataToSelection.setConverter(referencetoDataConverter);
        return referencedDataToSelection;
    }

    protected UpdateValueStrategy selectionToContractInputStrategy() {
        final UpdateValueStrategy strategy = new UpdateValueStrategy();
        final IConverter referenceConverter = new Converter(ContractInput.class, List.class) {

            @Override
            public Object convert(final Object data) {
                if (data != null) {
                    return Collections.singletonList(data);
                } else {
                    return Collections.emptyList();
                }
            }

        };
        strategy.setConverter(referenceConverter);
        return strategy;
    }

    protected UpdateValueStrategy selectionToInputNameStrategy() {
        final UpdateValueStrategy strategy = new UpdateValueStrategy();
        final IConverter nameConverter = new Converter(ContractInput.class, String.class) {

            @Override
            public Object convert(final Object input) {
                return input != null ? ((ContractInput) input).getName() : null;
            }

        };
        strategy.setConverter(nameConverter);
        return strategy;
    }

    @Override
    public boolean canFinish() {
        return !viewer.getSelection().isEmpty();
    }

    protected TableViewer getViewer() {
        return viewer;
    }

    protected Expression getExpression() {
        return editorInputExpression;
    }

}
