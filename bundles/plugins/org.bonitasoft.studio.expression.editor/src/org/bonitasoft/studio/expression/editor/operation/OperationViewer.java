/**
 * Copyright (C) 2012-2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.expression.editor.operation;

import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.IBonitaVariableContext;
import org.bonitasoft.studio.common.jface.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionValidator;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.expression.editor.viewer.ReadOnlyExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.DefaultToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class OperationViewer extends Composite implements IBonitaVariableContext {

    private final class RevalidateActionExpressionChangeListener implements IChangeListener {

        @Override
        public void handleChange(final ChangeEvent arg0) {
            getActionExpression().validate();
        }
    }

    private final class RevalidateActionExpressionValueChangedListener implements IValueChangeListener {

        @Override
        public void handleValueChange(final ValueChangeEvent event) {
            getActionExpression().validate();
        }
    }

    public static final String SWTBOT_ID_REMOVE_LINE = "actionLinesCompositeRemoveButton";
    private final TabbedPropertySheetWidgetFactory widgetFactory;
    private final ViewerFilter storageExpressionFilter;
    private final ViewerFilter actionExpressionFilter;
    private IExpressionNatureProvider storageExpressionProvider;
    private ReadOnlyExpressionViewer storageViewer;
    private Link operatorLink;
    private ExpressionViewer actionExpression;
    private EditingDomain editingDomain;
    private DefaultToolTip operatorTooltip;
    private IExpressionNatureProvider actionExpressionProvider;
    private Operation operation;
    private EObject eObjectContext;
    private boolean isPageFlowContext;
    private DefaultReturnTypeResolver defaultReturnTypeResolver;
    private final IObservableValue operationObervable;
    private final WritableValue eObjectObervable;

    public OperationViewer(final Composite parent, final TabbedPropertySheetWidgetFactory widgetFactory,
            final EditingDomain editingDomain,
            final ViewerFilter actionExpressionFilter,
            final ViewerFilter storageExpressionFilter, final boolean isPageFlowContext) {
        super(parent, SWT.NONE);
        this.isPageFlowContext = isPageFlowContext;
        this.editingDomain = editingDomain;
        this.widgetFactory = widgetFactory;
        if (widgetFactory != null) {
            widgetFactory.adapt(this);
        }
        this.actionExpressionFilter = actionExpressionFilter;
        this.storageExpressionFilter = storageExpressionFilter;
        setLayout(GridLayoutFactory.fillDefaults().numColumns(4).margins(0, 0).create());
        operationObervable = new WritableValue(Realm.getDefault());
        eObjectObervable = new WritableValue(Realm.getDefault());
        doCreateControls();
    }

    public OperationViewer(final Composite parent, final TabbedPropertySheetWidgetFactory widgetFactory,
            final EditingDomain editingDomain,
            final ViewerFilter actionExpressionFilter,
            final ViewerFilter storageExpressionFilter) {
        this(parent, widgetFactory, editingDomain, actionExpressionFilter, storageExpressionFilter, false);
    }

    public void refresh() {
        if (storageViewer != null) {
            storageViewer.validate();
        }
        if (actionExpression != null) {
            actionExpression.validate();
        }
    }

    public void createDatabinding(EMFDataBindingContext context) {
        defaultReturnTypeResolver = new DefaultReturnTypeResolver(operationObervable);

        bindStorageViewer(context, operationObervable);
        bindActionExpression(context, operationObervable);
        updateVisibilityOfActionExpressionControl(operationObervable);

        final IObservableValue rightOperandObservable = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                operationObervable,
                ExpressionPackage.Literals.OPERATION__RIGHT_OPERAND);
        final IObservableValue rightOperandReturnTypeObservable = CustomEMFEditObservables.observeDetailValue(
                Realm.getDefault(), rightOperandObservable,
                ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE);
        rightOperandReturnTypeObservable.addChangeListener(new RevalidateActionExpressionChangeListener());
        bindOperator(context, operationObervable);

    }

    private void bindOperator(EMFDataBindingContext context, IObservableValue operationObervable) {
        final UpdateValueStrategy uvsOperator = new UpdateValueStrategy();
        final IObservableValue operatorObservableValue = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                operationObervable,
                ExpressionPackage.Literals.OPERATION__OPERATOR);
        final IObservableValue operatorExpressionObserveValue = CustomEMFEditObservables.observeDetailValue(
                Realm.getDefault(), operatorObservableValue,
                ExpressionPackage.Literals.OPERATOR__EXPRESSION);
        final IObservableValue operatorTypeObservedValue = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                operatorObservableValue,
                ExpressionPackage.Literals.OPERATOR__TYPE);
        uvsOperator.setConverter(new OperatorTypeToStringLinkConverter(operatorObservableValue));
        operatorTypeObservedValue.addChangeListener(new RevalidateActionExpressionChangeListener());
        operatorTypeObservedValue.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                getActionExpression().setDefaultReturnType(defaultReturnTypeResolver.guessRightOperandReturnType());
            }
        });

        context.bindValue(SWTObservables.observeText(getOperatorLink()), operatorTypeObservedValue, null, uvsOperator);
        context.bindValue(SWTObservables.observeText(getOperatorLink()), operatorExpressionObserveValue, null, uvsOperator);
        context.bindValue(SWTObservables.observeTooltipText(getOperatorLink()),
                operatorExpressionObserveValue);
    }

    private void bindStorageViewer(EMFDataBindingContext context, final IObservableValue operationObservable) {
        if (storageExpressionProvider != null) {
            storageViewer.setExpressionNatureProvider(storageExpressionProvider);
        }
        if (eObjectContext != null) {
            storageViewer.setContext(eObjectContext);
        }
        storageViewer.setExternalDataBindingContext(context);
        context.bindValue(ViewersObservables.observeInput(storageViewer), operationObservable);

        final IObservableValue leftOperandObservableValue = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                operationObservable,
                ExpressionPackage.Literals.OPERATION__LEFT_OPERAND);
        final IObservableValue operatorObservableValue = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                operationObservable,
                ExpressionPackage.Literals.OPERATION__OPERATOR);
        storageViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                final Object value = ((IStructuredSelection) event.getSelection()).getFirstElement();
                if (value instanceof Expression) {
                    final boolean isLeftOperandABusinessData = isExpressionReferenceABusinessData((Expression) value);
                    final Operator operator = (Operator) operatorObservableValue.getValue();
                    getActionExpression().getControl().setVisible(
                            !ExpressionConstants.DELETION_OPERATOR.equals(operator.getType())
                                    || !isLeftOperandABusinessData);
                    refreshActionExpressionTooltip((Expression) value);
                }
            }

        });

        context.bindValue(ViewersObservables.observeSingleSelection(storageViewer), leftOperandObservableValue);
        storageViewer.addExpressionValidator(new TransientDataValidator());
        storageViewer.addSelectionChangedListener(new StorageViewerChangedListener(this));
        storageViewer.getEraseControl().addListener(SWT.ALL, new Listener() {

            @Override
            public void handleEvent(final Event arg0) {
                getActionExpression().resetStatus();

            }
        });
    }

    private void bindActionExpression(EMFDataBindingContext context, final IObservableValue operationObservable) {
        if (actionExpressionProvider != null) {
            getActionExpression().setExpressionNatureProvider(actionExpressionProvider);
        }
        getActionExpression().setExternalDataBindingContext(context);
        context.bindValue(ViewersObservables.observeInput(getActionExpression()), operationObservable);

        getActionExpression().addExpressionValidator(new OperationReturnTypesValidator());

        final IObservableValue actionExpressionObservableValue = CustomEMFEditObservables.observeDetailValue(
                Realm.getDefault(), operationObservable,
                ExpressionPackage.Literals.OPERATION__RIGHT_OPERAND);
        final IObservableValue leftOperandObservableValue = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                operationObservable,
                ExpressionPackage.Literals.OPERATION__LEFT_OPERAND);
        final IObservableValue returnTypeExpressionObservableValue = CustomEMFEditObservables.observeDetailValue(
                Realm.getDefault(),
                actionExpressionObservableValue,
                ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE);
        context.bindValue(
                ViewerProperties.singleSelection().observe(getActionExpression()),
                actionExpressionObservableValue);

        context.bindValue(PojoObservables.observeValue(getActionExpression(), "defaultReturnType"),
                CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                        leftOperandObservableValue,
                        ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE),
                neverUpdateValueStrategy().create(),
                updateValueStrategy().withConverter(returnTypeConverter()).create());
        returnTypeExpressionObservableValue.addValueChangeListener(new RevalidateActionExpressionValueChangedListener());
    }

    private IConverter returnTypeConverter() {
        return new Converter(String.class, String.class) {

            @Override
            public Object convert(final Object fromObject) {
                return defaultReturnTypeResolver.guessRightOperandReturnType();
            }
        };
    }

    private void updateVisibilityOfActionExpressionControl(final IObservableValue operationObservableValue) {
        operationObservableValue.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(ValueChangeEvent event) {
                final IObservableValue observableValue = event.getObservableValue();
                final Operation operation = (Operation) observableValue.getValue();
                final boolean isLeftOperandABusinessData = operation != null
                        && isExpressionReferenceABusinessData(operation.getLeftOperand());
                final Control control = getActionExpression().getControl();
                if (!control.isDisposed()) {
                    control.setVisible(
                            !ExpressionConstants.DELETION_OPERATOR.equals(operation.getOperator().getType())
                                    || !isLeftOperandABusinessData);
                }
            }
        });

    }

    protected void doCreateControls() {
        storageViewer = createStorageViewer();
        setOperatorLink(createOperatorLink());
        setActionExpression(createActionExpressionViewer());
    }

    protected Link createOperatorLink() {
        final Link operatorLabel = new Link(this, SWT.NONE);
        if (widgetFactory != null) {
            widgetFactory.adapt(operatorLabel, false, false);
        }

        setOperatorTooltip(new DefaultToolTip(operatorLabel));
        getOperatorTooltip().setText(Messages.changeOperator);
        getOperatorTooltip().setPopupDelay(50);
        getOperatorTooltip().setShift(new Point(10, 5));

        operatorLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
        operatorLabel.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final Operation action = getOperation();
                final OperatorSelectionDialog dialog = new OperatorSelectionDialog(Display.getDefault().getActiveShell(),
                        action);
                if (dialog.open() == Dialog.OK) {
                    final Operator newOperator = updateModelOperator(action, dialog.getOperator());
                    getActionExpression().validate();
                    getActionExpression().getControl()
                            .setVisible(!newOperator.getType().equals(ExpressionConstants.DELETION_OPERATOR));
                    operatorLabel.update();
                    operatorLabel.getParent().layout(true, true);
                }
            }

        });

        return operatorLabel;
    }

    protected Operator updateModelOperator(final Operation action, final Operator newOperator) {
        if (getEditingDomain() == null) {
            final Operator operator = action.getOperator();
            operator.setType(newOperator.getType());
            operator.setExpression(newOperator.getExpression());
            operator.getInputTypes().clear();
            operator.getInputTypes().addAll(newOperator.getInputTypes());
        } else {
            final CompoundCommand cc = new CompoundCommand("Update Operator");
            final Operator operator = action.getOperator();
            cc.append(SetCommand.create(getEditingDomain(), operator, ExpressionPackage.Literals.OPERATOR__EXPRESSION,
                    newOperator.getExpression()));
            cc.append(SetCommand.create(getEditingDomain(), operator, ExpressionPackage.Literals.OPERATOR__TYPE,
                    newOperator.getType()));
            cc.append(SetCommand.create(getEditingDomain(), operator, ExpressionPackage.Literals.OPERATOR__INPUT_TYPES,
                    newOperator.getInputTypes()));
            getEditingDomain().getCommandStack().execute(cc);
        }
        if (actionExpression != null && defaultReturnTypeResolver != null) {
            actionExpression.setDefaultReturnType(defaultReturnTypeResolver.guessRightOperandReturnType());
        }
        return newOperator;
    }

    public void setEditingDomain(final EditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

    protected ReadOnlyExpressionViewer createStorageViewer() {
        final ReadOnlyExpressionViewer storageViewer = new ReadOnlyExpressionViewer(this, SWT.BORDER, widgetFactory,
                getEditingDomain(),
                ExpressionPackage.Literals.OPERATION__LEFT_OPERAND);
        storageViewer.setIsPageFlowContext(isPageFlowContext);
        storageViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().hint(150, SWT.DEFAULT).grab(true, false).create());
        if (storageExpressionFilter != null) {
            storageViewer.addFilter(storageExpressionFilter);
        }
        storageViewer.getEraseControl().addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final Operation action = getOperation();
                if (action != null) {
                    final Operator op = action.getOperator();
                    if (op != null) {
                        if (getEditingDomain() != null) {
                            getEditingDomain().getCommandStack().execute(
                                    SetCommand.create(getEditingDomain(), op, ExpressionPackage.Literals.OPERATOR__TYPE,
                                            ExpressionConstants.ASSIGNMENT_OPERATOR));
                        } else {
                            op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
                        }
                        getActionExpression().validate();
                        getActionExpression().getControl().setVisible(true);
                        getOperatorLink().getParent().layout(true, true);
                    }
                }
            }
        });
        return storageViewer;
    }

    protected ExpressionViewer createActionExpressionViewer() {
        final ExpressionViewer actionViewer = new ExpressionViewer(this, SWT.BORDER, widgetFactory);
        actionViewer.setIsPageFlowContext(isPageFlowContext);
        actionViewer.addFilter(actionExpressionFilter);
        actionViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(200, SWT.DEFAULT).create());
        return actionViewer;
    }

    /*
     * (non-Javadoc)
     * @seeorg.eclipse.gmf.runtime.diagram.ui.properties.sections.
     * AbstractModelerPropertySection#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
        if (getActionExpression() != null && !getActionExpression().getControl().isDisposed()) {
            getActionExpression().getControl().dispose();
        }
        if (storageViewer != null && !storageViewer.getControl().isDisposed()) {
            storageViewer.getControl().dispose();
        }
        if (getOperatorLink() != null && !getOperatorLink().isDisposed()) {
            getOperatorLink().dispose();
        }
    }

    public void setEObject(final EObject eObject) {
        this.eObjectObervable.setValue(eObject);
    }

    public EObject getEObject() {
        return (EObject) eObjectObervable.getValue();
    }

    public void setContext(final EObject eObject) {
        eObjectContext = eObject;
    }

    public void setOperation(final Operation operation) {
        this.operation = operation;
        operationObervable.setValue(operation);
    }

    public Operation getOperation() {
        return this.operation;
    }

    /**
     * Set a specify IExpressionNamtureProvider for the left operand
     *
     * @param provider
     */
    public void setStorageExpressionNatureProvider(final IExpressionNatureProvider provider) {
        storageExpressionProvider = provider;
    }

    /**
     * Set a specify IExpressionNamtureProvider for the left operand
     *
     * @param provider
     */
    public void setActionExpressionNatureProvider(final IExpressionNatureProvider provider) {
        actionExpressionProvider = provider;
    }

    public Control getTextControl() {
        return getActionExpression().getTextControl();
    }

    public ToolItem getButtonControl() {
        return getActionExpression().getButtonControl();
    }

    public Control getOperatorControl() {
        return getOperatorLink();
    }

    public Control getComboControl() {
        return storageViewer.getControl();
    }

    public void addActionExpressionValidator(final IExpressionValidator validator) {
        getActionExpression().addExpressionValidator(validator);
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

    public Link getOperatorLink() {
        return operatorLink;
    }

    private void setOperatorLink(final Link operatorLink) {
        this.operatorLink = operatorLink;
    }

    public DefaultToolTip getOperatorTooltip() {
        return operatorTooltip;
    }

    private void setOperatorTooltip(final DefaultToolTip operatorTooltip) {
        this.operatorTooltip = operatorTooltip;
    }

    public ExpressionViewer getActionExpression() {
        return actionExpression;
    }

    private void setActionExpression(final ExpressionViewer actionExpression) {
        this.actionExpression = actionExpression;
    }

    public EditingDomain getEditingDomain() {
        return editingDomain;
    }

    private boolean isExpressionReferenceABusinessData(final Expression value) {
        if (value != null) {
            final EList<EObject> referencedElements = value.getReferencedElements();
            return !referencedElements.isEmpty()
                    && referencedElements.get(0) instanceof BusinessObjectData;
        } else {
            return false;
        }
    }

    public static boolean isExpressionReferenceADocument(final Expression value) {
        if (value != null) {
            final EList<EObject> referencedElements = value.getReferencedElements();
            return !referencedElements.isEmpty()
                    && referencedElements.get(0) instanceof Document;
        } else {
            return false;
        }
    }

    public static boolean isExpressionReferenceAListDocument(final Expression value) {
        if (value != null) {
            final EList<EObject> referencedElements = value.getReferencedElements();
            return !referencedElements.isEmpty()
                    && referencedElements.get(0) instanceof Document
                    && ((Document) referencedElements.get(0)).isMultiple();
        } else {
            return false;
        }
    }

    public void refreshActionExpressionTooltip(final Expression value) {
        final boolean isLeftOperandADocument = isExpressionReferenceADocument(value);
        final boolean isLeftOperandAListDocument = isExpressionReferenceAListDocument(value);

        if (isLeftOperandADocument && !value.getName().isEmpty()) {

            if (isOperationContainerIsATask()) {
                if (isLeftOperandAListDocument) {
                    getActionExpression().setMessage(Messages.messageOperationWithListDocumentInTask);
                } else {
                    getActionExpression().setMessage(Messages.messageOperationWithDocumentInTask);
                }

            } else {
                if (isLeftOperandAListDocument) {
                    if (PlatformUtil.isACommunityBonitaProduct()) {
                        getActionExpression().setMessage(Messages.messageOperationWithListDocumentInFormInCommunity);
                    } else {
                        getActionExpression().setMessage(Messages.messageOperationWithListDocumentInForm);
                    }
                } else {
                    getActionExpression().setMessage(Messages.messageOperationWithDocumentInForm);
                }
            }
        } else {
            getActionExpression().resetStatus();
        }
        getActionExpression().validate();
    }

    private boolean isOperationContainerIsATask() {
        return operation != null && operation.eContainer() instanceof Task;
    }

}
