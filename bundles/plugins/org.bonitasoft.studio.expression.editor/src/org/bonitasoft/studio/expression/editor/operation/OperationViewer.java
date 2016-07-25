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

import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.IBonitaVariableContext;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionValidator;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.expression.editor.viewer.ReadOnlyExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Lane;
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
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
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

/**
 * @author Aurelien Pupier
 */
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
    protected EMFDataBindingContext context;
    private final TabbedPropertySheetWidgetFactory widgetFactory;
    private EObject eObject;
    private EReference operationContainmentFeature;
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

    public OperationViewer(final Composite parent, final TabbedPropertySheetWidgetFactory widgetFactory, final EditingDomain editingDomain,
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
        doCreateControls();
    }

    public OperationViewer(final Composite parent, final TabbedPropertySheetWidgetFactory widgetFactory, final EditingDomain editingDomain,
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

    public void refreshDatabinding() {
        final Operation action = getOperation();
        if (action != null) {
            defaultReturnTypeResolver = new DefaultReturnTypeResolver(action);
            getActionExpression().setExternalDataBindingContext(context);
            storageViewer.setExternalDataBindingContext(context);

            initOperands(action);
            bindStorageViewer(action);
            bindActionExpression(action);
            updateVisibilityOfActionExpressionControl(action);

            final IObservableValue value = EMFEditObservables.observeValue(getEditingDomain(), action.getRightOperand(),
                    ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE);
            value.addChangeListener(new RevalidateActionExpressionChangeListener());

            if (getOperation() != null) {
                bindOperator();
            }
        }
    }

    private void bindOperator() {
        final UpdateValueStrategy uvsOperator = new UpdateValueStrategy();
        uvsOperator.setConverter(new OperatorTypeToStringLinkConverter(EMFEditObservables.observeValue(getEditingDomain(), getOperation(),
                ExpressionPackage.Literals.OPERATION__OPERATOR)));
        final IObservableValue operatorExpressionObserveValue = EMFEditObservables.observeValue(getEditingDomain(), getOperation().getOperator(),
                ExpressionPackage.Literals.OPERATOR__EXPRESSION);
        final IObservableValue operatorObservedValue = EMFEditObservables.observeValue(getEditingDomain(), getOperation().getOperator(),
                ExpressionPackage.Literals.OPERATOR__TYPE);
        operatorObservedValue.addChangeListener(new RevalidateActionExpressionChangeListener());
        operatorObservedValue.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                getActionExpression().setDefaultReturnType(defaultReturnTypeResolver.guessRightOperandReturnType());
            }
        });

        context.bindValue(SWTObservables.observeText(getOperatorLink()), operatorObservedValue, null, uvsOperator);
        context.bindValue(SWTObservables.observeText(getOperatorLink()), operatorExpressionObserveValue, null, uvsOperator);
        context.bindValue(SWTObservables.observeTooltipText(getOperatorLink()),
                operatorExpressionObserveValue);
    }

    private void bindStorageViewer(final Operation action) {
        if (storageExpressionProvider != null) {
            storageViewer.setExpressionNatureProvider(storageExpressionProvider);
        }
        if (eObjectContext != null) {
            storageViewer.setContext(eObjectContext);
        }
        storageViewer.setInput(action);

        final IObservableValue leftOperandObservableValue = EMFEditObservables.observeValue(getEditingDomain(), action,
                ExpressionPackage.Literals.OPERATION__LEFT_OPERAND);
        storageViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                final Object value = ((IStructuredSelection) event.getSelection()).getFirstElement();
                if (value instanceof Expression) {
                    final boolean isLeftOperandABusinessData = isExpressionReferenceABusinessData((Expression) value);
                    getActionExpression().getControl().setVisible(
                            !ExpressionConstants.DELETION_OPERATOR.equals(action.getOperator().getType()) || !isLeftOperandABusinessData);
                    refreshActionExpressionTooltip((Expression) value);
                }
            }

        });

        context.bindValue(ViewersObservables.observeSingleSelection(storageViewer), leftOperandObservableValue);
        storageViewer.addExpressionValidator(new TransientDataValidator());
        storageViewer.addSelectionChangedListener(new StorageViewerChangedListener(this, action));
        storageViewer.getEraseControl().addListener(SWT.ALL, new Listener() {

            @Override
            public void handleEvent(final Event arg0) {
                getActionExpression().removeMessages(IStatus.INFO);

            }
        });
    }

    private void bindActionExpression(final Operation action) {
        if (actionExpressionProvider != null) {
            getActionExpression().setExpressionNatureProvider(actionExpressionProvider);
        }
        getActionExpression().setInput(getEObject());

        getActionExpression().addExpressionValidator(new OperationReturnTypesValidator());
        final IObservableValue actionExpressionObservableValue = EMFEditProperties
                .value(getEditingDomain(),
                        ExpressionPackage.Literals.OPERATION__RIGHT_OPERAND)
                .observe(action);
        final IObservableValue returnTypeExpressionObservableValue = EMFEditProperties
                .value(getEditingDomain(),
                        ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE)
                .observe(action.getRightOperand());
        context.bindValue(
                ViewerProperties.singleSelection().observe(getActionExpression()),
                actionExpressionObservableValue);

        context.bindValue(PojoObservables.observeValue(getActionExpression(), "defaultReturnType"),
                EMFObservables.observeDetailValue(Realm.getDefault(),
                        EMFObservables.observeValue(action, ExpressionPackage.Literals.OPERATION__LEFT_OPERAND),
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

    private void updateVisibilityOfActionExpressionControl(final Operation action) {
        final boolean isLeftOperandABusinessData = operation != null && isExpressionReferenceABusinessData(operation.getLeftOperand());
        getActionExpression().getControl().setVisible(
                !ExpressionConstants.DELETION_OPERATOR.equals(action.getOperator().getType()) || !isLeftOperandABusinessData);
    }

    private void initOperands(final Operation action) {
        final CompoundCommand cc = new CompoundCommand("Init Operands of Operation");
        Expression actionExp = action.getRightOperand();
        if (actionExp == null) {
            actionExp = createInitialRightOperand(cc, action);
        }
        final Expression storageExp = action.getLeftOperand();
        if (storageExp == null) {
            createInitialLeftOperand(cc, action);
        }
        if (getEditingDomain() != null) {
            getEditingDomain().getCommandStack().execute(cc);
        }
    }

    private void createInitialLeftOperand(final CompoundCommand cc, final Operation action) {
        Expression storageExp;
        storageExp = ExpressionFactory.eINSTANCE.createExpression();
        if (getEditingDomain() != null) {
            cc.append(
                    SetCommand.create(getEditingDomain(), action, ExpressionPackage.Literals.OPERATION__LEFT_OPERAND, storageExp));
        } else {
            action.setLeftOperand(storageExp);
        }
    }

    private Expression createInitialRightOperand(final CompoundCommand cc, final Operation action) {
        Expression actionExp;
        actionExp = ExpressionFactory.eINSTANCE.createExpression();
        if (actionExpressionFilter instanceof AvailableExpressionTypeFilter) {
            final Set<String> possibleContentTypes = ((AvailableExpressionTypeFilter) actionExpressionFilter).getContentTypes();
            if (!possibleContentTypes.contains(ExpressionConstants.CONSTANT_TYPE)) {
                if (!possibleContentTypes.isEmpty()) {
                    actionExp.setType(possibleContentTypes.iterator().next());
                }
            }
        }
        if (getEditingDomain() != null) {
            cc.append(
                    SetCommand.create(getEditingDomain(), action, ExpressionPackage.Literals.OPERATION__RIGHT_OPERAND, actionExp));
        } else {
            action.setRightOperand(actionExp);
        }
        return actionExp;
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
                final OperatorSelectionDialog dialog = new OperatorSelectionDialog(Display.getDefault().getActiveShell(), action);
                if (dialog.open() == Dialog.OK) {
                    final Operator newOperator = updateModelOperator(action, dialog.getOperator());
                    getActionExpression().validate();
                    getActionExpression().getControl().setVisible(!newOperator.getType().equals(ExpressionConstants.DELETION_OPERATOR));
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
            cc.append(SetCommand.create(getEditingDomain(), operator, ExpressionPackage.Literals.OPERATOR__TYPE, newOperator.getType()));
            cc.append(SetCommand.create(getEditingDomain(), operator, ExpressionPackage.Literals.OPERATOR__INPUT_TYPES, newOperator.getInputTypes()));
            getEditingDomain().getCommandStack().execute(cc);
        }
        if (actionExpression != null && defaultReturnTypeResolver != null) {
            actionExpression.setDefaultReturnType(defaultReturnTypeResolver.guessRightOperandReturnType());
        }
        return newOperator;
    }

    public void setEditingDomain(final EditingDomain editingDomain) {
        this.editingDomain = editingDomain;
        getActionExpression().setEditingDomain(editingDomain);
        storageViewer.setEditingDomain(editingDomain);
    }

    protected ReadOnlyExpressionViewer createStorageViewer() {
        final ReadOnlyExpressionViewer storageViewer = new ReadOnlyExpressionViewer(this, SWT.BORDER, widgetFactory, getEditingDomain(),
                ExpressionPackage.Literals.OPERATION__LEFT_OPERAND);
        storageViewer.setIsPageFlowContext(isPageFlowContext);
        storageViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().hint(230, SWT.DEFAULT).grab(false, false).create());
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
        final ExpressionViewer actionViewer = new ExpressionViewer(this, SWT.BORDER, widgetFactory, getEditingDomain(), getActionTargetFeature());
        actionViewer.setIsPageFlowContext(isPageFlowContext);
        actionViewer.addFilter(actionExpressionFilter);
        actionViewer.setExternalDataBindingContext(context);
        actionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(200, SWT.DEFAULT).create());
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

    public void setContext(final EMFDataBindingContext emfDataBindingContext) {
        context = emfDataBindingContext;
    }

    public void setEObject(final EObject eObject) {
        this.eObject = eObject;
        if (eObject instanceof Widget) {
            setOperationContainmentFeature(FormPackage.Literals.WIDGET__ACTION);
        }
        refreshDatabinding();
    }

    public void setContext(final EObject eObject) {
        eObjectContext = eObject;
    }

    public void setOperationContainmentFeature(final EReference actionTargetFeature) {
        operationContainmentFeature = actionTargetFeature;
    }

    public EObject getEObject() {
        return eObject;
    }

    protected EReference getActionTargetFeature() {
        return operationContainmentFeature;
    }

    public void setOperation(final Operation operation) {
        this.operation = operation;
    }

    private Operation getOperation() {
        if (operation != null) {
            return operation;
        }
        EObject eObject = getEObject();
        if (eObject instanceof Operation) {
            return (Operation) eObject;
        }
        if (getEObject() instanceof Lane) {
            eObject = getEObject().eContainer();
        }
        return (Operation) eObject.eGet(getActionTargetFeature());
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
                    getActionExpression().setMessage(Messages.messageOperationWithListDocumentInTask, IStatus.INFO);
                } else {
                    getActionExpression().setMessage(Messages.messageOperationWithDocumentInTask, IStatus.INFO);
                }

            } else {
                if (isLeftOperandAListDocument) {
                    if (PlatformUtil.isACommunityBonitaProduct()) {
                        getActionExpression().setMessage(Messages.messageOperationWithListDocumentInFormInCommunity, IStatus.INFO);
                    } else {
                        getActionExpression().setMessage(Messages.messageOperationWithListDocumentInForm, IStatus.INFO);
                    }
                } else {
                    getActionExpression().setMessage(Messages.messageOperationWithDocumentInForm, IStatus.INFO);
                }
            }
        } else {
            getActionExpression().removeMessages(IStatus.INFO);
        }
        getActionExpression().validate();
    }

    private boolean isOperationContainerIsATask() {
        return operation != null && operation.eContainer() instanceof Task;
    }

}
