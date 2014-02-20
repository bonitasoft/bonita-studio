/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.expression.editor.operation;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.IBonitaVariableContext;
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
import org.bonitasoft.studio.model.process.Lane;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
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
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Aurelien Pupier
 *
 */
public class OperationViewer extends Composite implements IBonitaVariableContext{

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
	private final OperatorLabelProvider labelProvider = new OperatorLabelProvider() ;
	private EditingDomain editingDomain;
	private final OperationReturnTypesValidator operationReturnTypeValidator;
	private DefaultToolTip operatorTooltip;
	private IExpressionNatureProvider actionExpressionProvider;
	private Operation operation;
	private EObject eObjectContext;
	private boolean isPageFlowContext;
	
	
	public OperationViewer(Composite parent,TabbedPropertySheetWidgetFactory widgetFactory ,EditingDomain editingDomain, ViewerFilter actionExpressionFilter, ViewerFilter storageExpressionFilter,boolean isPageFlowContext){
		super(parent, SWT.NONE);
		this.isPageFlowContext=isPageFlowContext;
		this.editingDomain = editingDomain ;
		this.widgetFactory = widgetFactory ;
		if(widgetFactory != null){
			widgetFactory.adapt(this);
		}
		this.actionExpressionFilter = actionExpressionFilter;
		this.storageExpressionFilter = storageExpressionFilter;
		operationReturnTypeValidator = new OperationReturnTypesValidator();
		setLayout(GridLayoutFactory.fillDefaults().numColumns(4).margins(0, 0).create());
		doCreateControls() ;
	}

	public OperationViewer(Composite parent,TabbedPropertySheetWidgetFactory widgetFactory ,EditingDomain editingDomain, ViewerFilter actionExpressionFilter, ViewerFilter storageExpressionFilter) {
		this(parent,widgetFactory,editingDomain,actionExpressionFilter,storageExpressionFilter,false);
	}

	public void refreshDatabinding(){
		final Operation action = getOperation() ;
		if(action != null){
			actionExpression.setExternalDataBindingContext(context); 
			storageViewer.setExternalDataBindingContext(context);
			if(actionExpressionProvider != null){
				actionExpression.setExpressionNatureProvider(actionExpressionProvider);
			}
			actionExpression.setInput(getEObject()) ;
			if(storageExpressionProvider != null){
				storageViewer.setExpressionNatureProvider(storageExpressionProvider);
			}
			if(eObjectContext!= null){
				storageViewer.setContext(eObjectContext);
			}
			storageViewer.setInput(action);

			Expression actionExp = action.getRightOperand() ;
			if(actionExp == null){
				actionExp  = ExpressionFactory.eINSTANCE.createExpression() ;
				if(actionExpressionFilter instanceof AvailableExpressionTypeFilter){
					if(!((AvailableExpressionTypeFilter) actionExpressionFilter).getContentTypes().contains(ExpressionConstants.CONSTANT_TYPE)){
						if(!((AvailableExpressionTypeFilter) actionExpressionFilter).getContentTypes().isEmpty()){
							actionExp.setType(((AvailableExpressionTypeFilter) actionExpressionFilter).getContentTypes().iterator().next());
						}
					}
				}
				if(editingDomain != null){
					editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, action, ExpressionPackage.Literals.OPERATION__RIGHT_OPERAND, actionExp));
				}else{
					action.setRightOperand(actionExp) ;
				}
			}
			Expression storageExp = action.getLeftOperand() ;
			if(storageExp == null){
				storageExp  = ExpressionFactory.eINSTANCE.createExpression() ;
				if(editingDomain != null){
					editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, action, ExpressionPackage.Literals.OPERATION__LEFT_OPERAND, storageExp));
				}else{
					action.setLeftOperand(storageExp) ;
				}
			}

			operationReturnTypeValidator.setDataExpression(action.getLeftOperand());
			operationReturnTypeValidator.setInputExpression(action.getRightOperand());

			IObservableValue leftOperandObservableValue =  EMFEditObservables.observeValue(editingDomain, action, ExpressionPackage.Literals.OPERATION__LEFT_OPERAND) ;
			UpdateValueStrategy targetToModel = new UpdateValueStrategy() ;
			targetToModel.setConverter(new Converter(Expression.class,Expression.class) {

				@Override
				public Object convert(Object from) {
					if(from != null){
						Expression copy =  EcoreUtil.copy((Expression)from);
						operationReturnTypeValidator.setDataExpression(copy);
						return copy;
					}
					return null;
				}
			} ) ;
			context.bindValue(ViewersObservables.observeSingleSelection(storageViewer), leftOperandObservableValue,targetToModel,null) ;



			actionExpression.addExpressionValidator(ExpressionConstants.ALL_TYPES, operationReturnTypeValidator);
			IObservableValue actionExpressionObservableValue = EMFEditProperties
					.value(editingDomain,
							ExpressionPackage.Literals.OPERATION__RIGHT_OPERAND)
							.observe(action);
			IObservableValue returnTypeExpressionObservableValue = EMFEditProperties
					.value(editingDomain,
							ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE)
							.observe(action.getRightOperand());
			context.bindValue(
					ViewerProperties.singleSelection().observe(actionExpression),
					actionExpressionObservableValue);
			
			returnTypeExpressionObservableValue.addValueChangeListener(new IValueChangeListener() {
				
				@Override
				public void handleValueChange(ValueChangeEvent event) {
					actionExpression.validate();
				}
			});


			operatorLink.setText("<A>"+labelProvider.getText(action.getOperator())+"</A>") ;
			if(!action.getOperator().getType().equals(ExpressionConstants.ASSIGNMENT_OPERATOR) && action.getOperator().getExpression() != null  && !action.getOperator().getExpression().isEmpty() ){
				operatorTooltip.setText(action.getOperator().getExpression());
			}

			IObservableValue value = EMFEditObservables.observeValue(editingDomain, actionExp, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE);
			value.addChangeListener(new IChangeListener() {

				@Override
				public void handleChange(ChangeEvent arg0) {
					actionExpression.validate();
				}
			});

			storageViewer.addSelectionChangedListener(new ISelectionChangedListener() {

				@Override
				public void selectionChanged(SelectionChangedEvent event) {
					Expression selectedExpression = (Expression) ((IStructuredSelection) event.getSelection()).getFirstElement() ;
					if(selectedExpression != null){
						Operator operator = action.getOperator();
						if(operator.getType() == null){
							if(editingDomain == null){
								operator.setType(ExpressionConstants.ASSIGNMENT_OPERATOR) ;
							}else{
								editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, operator, ExpressionPackage.Literals.OPERATOR__TYPE, ExpressionConstants.ASSIGNMENT_OPERATOR)) ;
							}
						}
						final OperatorLabelProvider labelProvider = new OperatorLabelProvider() ;
						operatorLink.setText("<A>"+labelProvider.getText(action.getOperator())+"</A>") ;
						if(!action.getOperator().getType().equals(ExpressionConstants.ASSIGNMENT_OPERATOR) && action.getOperator().getExpression() != null && !action.getOperator().getExpression().isEmpty()){
							operatorTooltip.setText(action.getOperator().getExpression());
						}
						operationReturnTypeValidator.setDataExpression(action.getLeftOperand());
						updateRightOperandReturnType(action);
						actionExpression.validate();
						operatorLink.getParent().layout(true,true) ;
					}
				}
			}) ;
		}
	}

	protected void updateRightOperandReturnType(Operation action) {
		Operator operator = action.getOperator();
		Expression right = action.getRightOperand();
		Expression left = action.getLeftOperand();
		if(operator.getType().equals(ExpressionConstants.ASSIGNMENT_OPERATOR) 
				&& ExpressionConstants.CONSTANT_TYPE.equals(right.getType())
				&& !left.getReturnType().equals(right.getReturnType())
				&& isPrimitiveType(left.getReturnType())){
			if(editingDomain == null){
				right.setReturnType(right.getReturnType()) ;
			}else{
				editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, right, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE, left.getReturnType())) ;
			}
		}
		
	}

	private boolean isPrimitiveType(String returnType) {
		return returnType.equals(String.class.getName()) 
				|| returnType.equals(Integer.class.getName()) 
				|| returnType.equals(Long.class.getName()) 
				|| returnType.equals(Boolean.class.getName()) 
				|| returnType.equals(Double.class.getName())
				|| returnType.equals(Float.class.getName()) ;
	}

	protected void doCreateControls() {
		storageViewer =  createStorageViewer() ;
		operatorLink = createOperatorLink();
		actionExpression = createActionExpressionViewer();
	}


	protected Link createOperatorLink() {
		final Link operatorLabel = new Link(this, SWT.NONE) ;
		if(widgetFactory != null){
			widgetFactory.adapt(operatorLabel,false,false) ;
		}
		operatorTooltip = new DefaultToolTip(operatorLabel);
		operatorTooltip.setText(Messages.changeOperator);
		operatorTooltip.setPopupDelay(50);
		operatorTooltip.setShift(new Point(10,5));

		operatorLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.CENTER).create()) ;
		operatorLabel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final Operation action = getOperation() ;
				OperatorSelectionDialog dialog =  new OperatorSelectionDialog(Display.getDefault().getActiveShell(),action) ;
				if(dialog.open() == Dialog.OK){
					Operator newOperator =  dialog.getOperator() ;
					if(editingDomain == null){
						action.setOperator(newOperator) ;
					}else{
						editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, action, ExpressionPackage.Literals.OPERATION__OPERATOR, newOperator));
					}

					operatorLabel.setText("<A>"+labelProvider.getText(newOperator)+"</A>") ;
					if(newOperator.getExpression() != null && !newOperator.getType().equals(ExpressionConstants.ASSIGNMENT_OPERATOR) && !newOperator.getExpression().isEmpty()){
						operatorTooltip.setText(newOperator.getExpression()) ;
					}
					actionExpression.validate();
					operatorLabel.getParent().layout(true, true) ;
				}
			}
		}) ;

		return operatorLabel ;
	}

	public void setEditingDomain(EditingDomain editingDomain){
		this.editingDomain = editingDomain ;
		actionExpression.setEditingDomain(editingDomain) ;
		storageViewer.setEditingDomain(editingDomain);
	}

	protected ReadOnlyExpressionViewer createStorageViewer() {
		final ReadOnlyExpressionViewer storageViewer = new ReadOnlyExpressionViewer(this,SWT.BORDER,widgetFactory,editingDomain,ExpressionPackage.Literals.OPERATION__LEFT_OPERAND) ;
		storageViewer.setIsPageFlowContext(isPageFlowContext);
		storageViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().hint(230, SWT.DEFAULT).grab(false, false).create());
		if(storageExpressionFilter != null){
			storageViewer.addFilter(storageExpressionFilter) ;
		}
		storageViewer.getEraseControl().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Operation action = getOperation();
				if(action != null){
					final Operator op = action.getOperator();
					if(op != null){
						if(editingDomain !=null){
							editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, op, ExpressionPackage.Literals.OPERATOR__TYPE, ExpressionConstants.ASSIGNMENT_OPERATOR));
						}else{
							op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
						}
						operatorLink.setText("<A>"+labelProvider.getText(op)+"</A>") ;
						actionExpression.validate();
						operatorLink.getParent().layout(true, true) ;
					}
				}
			}
		});
		return storageViewer;
	}


	protected ExpressionViewer createActionExpressionViewer() {
		final ExpressionViewer actionViewer = new ExpressionViewer(this,SWT.BORDER,widgetFactory,editingDomain, getActionTargetFeature()) ;
		actionViewer.setIsPageFlowContext(isPageFlowContext);
		actionViewer.addFilter(actionExpressionFilter) ;
		actionViewer.setExternalDataBindingContext(context);
		actionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true,false).hint(200, SWT.DEFAULT).create());
		return actionViewer;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.gmf.runtime.diagram.ui.properties.sections.
	 * AbstractModelerPropertySection#dispose()
	 */
	@Override
	public void dispose() {
		super.dispose();
		if (actionExpression != null && !actionExpression.getControl().isDisposed()) {
			actionExpression.getControl().dispose();
		}
		if (storageViewer != null && !storageViewer.getControl().isDisposed()) {
			storageViewer.getControl().dispose();
		}
		if (operatorLink != null && !operatorLink.isDisposed()) {
			operatorLink.dispose();
		}
	}

	public void setContext(EMFDataBindingContext emfDataBindingContext) {
		context = emfDataBindingContext ;
	}

	public void setEObject(EObject eObject) {
		this.eObject = eObject;
		if (eObject instanceof Widget) {
			setOperationContainmentFeature(FormPackage.Literals.WIDGET__ACTION) ;
		}
		refreshDatabinding() ;
	}
	
	public void setContext(EObject eObject) {
		this.eObjectContext = eObject;
	}

	public void setOperationContainmentFeature(EReference actionTargetFeature) {
		operationContainmentFeature = actionTargetFeature;
	}

	public EObject getEObject(){
		return eObject;
	}


	protected EReference getActionTargetFeature() {
		return operationContainmentFeature;
	}


	public void setOperation(Operation operation){
		this.operation = operation ;
	}

	private Operation getOperation() {
		if(operation != null){
			return operation;
		}
		EObject eObject = getEObject() ;
		if(eObject instanceof Operation){
			return (Operation) eObject;
		}
		if(getEObject() instanceof Lane){
			eObject = getEObject().eContainer() ;
		}
		return (Operation) eObject.eGet(getActionTargetFeature());
	}

	/**
	 * Set a specify IExpressionNamtureProvider for the left operand
	 * @param provider
	 */
	public void setStorageExpressionNatureProvider(IExpressionNatureProvider provider) {
		storageExpressionProvider = provider ;
	}

	/**
	 * Set a specify IExpressionNamtureProvider for the left operand
	 * @param provider
	 */
	public void setActionExpressionNatureProvider(IExpressionNatureProvider provider) {
		actionExpressionProvider = provider ;
	}

	public Control getTextControl() {
		return actionExpression.getTextControl();
	}

	public ToolItem getButtonControl() {
		return actionExpression.getButtonControl();
	}

	public Control getOperatorControl() {
		return operatorLink;
	}

	public Control getComboControl() {
		return storageViewer.getControl();
	}

	public void addActionExpressionValidator(String expressionType,IExpressionValidator validator) {
		actionExpression.addExpressionValidator(expressionType, validator);
	}

	@Override
	public boolean isPageFlowContext() {
		
		return isPageFlowContext;
	}

	@Override
	public void setIsPageFlowContext(boolean isPageFlowContext) {
		this.isPageFlowContext=isPageFlowContext;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.IBonitaVariableContext#isOverViewContext()
	 */
	@Override
	public boolean isOverViewContext() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsOverviewContext(boolean)
	 */
	@Override
	public void setIsOverviewContext(boolean isOverviewContext) {
	}
}
