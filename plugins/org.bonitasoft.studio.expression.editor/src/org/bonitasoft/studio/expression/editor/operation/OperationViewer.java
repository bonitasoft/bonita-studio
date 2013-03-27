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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.ExpressionEditorService;
import org.bonitasoft.studio.expression.editor.provider.ExpressionComparator;
import org.bonitasoft.studio.expression.editor.provider.ExpressionLabelProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Lane;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
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
public class OperationViewer extends Composite {

	public static final String SWTBOT_ID_REMOVE_LINE = "actionLinesCompositeRemoveButton";
	protected ArrayList<ExpressionViewer> actionsViewers = new ArrayList<ExpressionViewer>();
	protected ArrayList<ComboViewer> storageCombo = new ArrayList<ComboViewer>();
	protected ArrayList<Button> removes = new ArrayList<Button>();
	protected ArrayList<Link> operatorLinks = new ArrayList<Link>();
	protected EMFDataBindingContext context;
	protected ArrayList<List<Binding>> bindings = new ArrayList<List<Binding>>();
	private final TabbedPropertySheetWidgetFactory widgetFactory;
	private EObject eObject;
	private EReference operationContainmentFeature;
	private final ViewerFilter storageExpressionFilter;
	private final ViewerFilter actionExpressionFilter;
	private IExpressionProvider storageExpressionProvider;
	private ComboViewer storageComboViewer;
	private Link operatorLink;
	private ExpressionViewer actionExpression;
	private final OperatorLabelProvider labelProvider = new OperatorLabelProvider() ;
	private EditingDomain editingDomain;
	private final OperationReturnTypesValidator operationReturnTypeValidator;
	private Binding actionExpressionBinding;

	public OperationViewer(Composite parent,TabbedPropertySheetWidgetFactory widgetFactory ,EditingDomain editingDomain, ViewerFilter actionExpressionFilter, ViewerFilter storageExpressionFilter) {
		super(parent, SWT.NONE);
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

	public void refreshDatabinding(){
		final Operation action = getOperation() ;
		if(action != null){
			actionExpression.setInput(getEObject()) ;
			Expression actionExp = action.getRightOperand() ;
			if(actionExp == null){
				actionExp  = ExpressionFactory.eINSTANCE.createExpression() ;
				if(editingDomain != null){
					editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, action, ExpressionPackage.Literals.OPERATION__RIGHT_OPERAND, actionExp));
				}else{
					action.setRightOperand(actionExp) ;
				}

			}

			operationReturnTypeValidator.setDataExpression(action.getLeftOperand());
			final IStatus status = operationReturnTypeValidator.validate(action.getRightOperand());
			actionExpression.setMessage(status.getMessage(),status.getSeverity());
			UpdateValueStrategy actionExpressionUpdateStrategy = new UpdateValueStrategy();
			actionExpressionUpdateStrategy.setAfterGetValidator(operationReturnTypeValidator);

			IObservableValue actionExpressionObservableValue = EMFEditProperties
					.value(editingDomain,
							ExpressionPackage.Literals.OPERATION__RIGHT_OPERAND)
							.observe(action);
			actionExpressionBinding = context.bindValue(
					ViewerProperties.singleSelection().observe(actionExpression),
					actionExpressionObservableValue,
					actionExpressionUpdateStrategy, null);

			if(storageExpressionProvider != null){
				storageComboViewer.setInput(new WritableList(storageExpressionProvider.getExpressions(getEObject()),Expression.class)) ;
			}else{
				IExpressionProvider provider = ExpressionEditorService.getInstance().getExpressionProvider(ExpressionConstants.VARIABLE_TYPE) ;
				storageComboViewer.setInput(new WritableList(provider.getExpressions(getEObject()),Expression.class)) ;
			}

			operatorLink.setText("<A>"+labelProvider.getText(action.getOperator())+"</A>") ;
			if(!action.getOperator().getType().equals(ExpressionConstants.ASSIGNMENT_OPERATOR)){
				operatorLink.setToolTipText(action.getOperator().getExpression()) ;
			}

			IObservableValue leftOperandObservableValue = null ;
			if(editingDomain == null){
				leftOperandObservableValue = EMFObservables.observeValue(action, ExpressionPackage.Literals.OPERATION__LEFT_OPERAND) ;
			}else{
				leftOperandObservableValue = EMFEditObservables.observeValue(editingDomain, action, ExpressionPackage.Literals.OPERATION__LEFT_OPERAND) ;
			}
			UpdateValueStrategy modelToTarget = new UpdateValueStrategy() ;
			modelToTarget.setConverter(new Converter(Expression.class,Expression.class) {

				@Override
				public Object convert(Object from) {
					if(from != null){
						Expression modelExpression = (Expression) from ;
						EObject originalEObject = modelExpression.getReferencedElements().get(0) ;
						for(int i = 0 ; i < storageComboViewer.getCombo().getItems().length ; i++){
							Expression exp = (Expression) storageComboViewer.getElementAt(i) ;
							if(!exp.getReferencedElements().isEmpty()){
								EObject eObject = exp.getReferencedElements().get(0) ;
								if(originalEObject instanceof Element && eObject instanceof Element){
									if(((Element) originalEObject).getName().equals(((Element) eObject).getName())){
										return exp ;
									}
								}
							}
						}
					}
					return null;
				}
			}) ;

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
			targetToModel.setBeforeSetValidator(operationReturnTypeValidator) ;
			final Binding storageBinding = context.bindValue(ViewersObservables.observeSingleSelection(storageComboViewer), leftOperandObservableValue,targetToModel,modelToTarget) ;
			ISelectionChangedListener updateExpressionListener = new ISelectionChangedListener() {

				@Override
				public void selectionChanged(SelectionChangedEvent event) {
					if(storageBinding.getTarget() != null){
						storageBinding.validateTargetToModel() ;
					}
					if (actionExpressionBinding.getTarget() != null) {
						actionExpressionBinding.validateTargetToModel();
						IStatus status = (IStatus) actionExpressionBinding.getValidationStatus().getValue();
						actionExpression.setMessage(status.getMessage(),status.getSeverity());
					}
				}
			} ;
			actionExpression.addSelectionChangedListener(updateExpressionListener);
			actionExpression.addExpressionEditorChangedListener(updateExpressionListener) ;
			IObservableValue value = EMFEditObservables.observeValue(editingDomain, actionExp, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE);
			value.addChangeListener(new IChangeListener() {

				@Override
				public void handleChange(ChangeEvent arg0) {
					if (actionExpressionBinding.getTarget() != null) {
						actionExpressionBinding.validateTargetToModel();
						IStatus status = (IStatus) actionExpressionBinding.getValidationStatus().getValue();
						actionExpression.setMessage(status.getMessage(),status.getSeverity());
					}
				}
			});



			storageComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {

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
						if(!action.getOperator().getType().equals(ExpressionConstants.ASSIGNMENT_OPERATOR)){
							operatorLink.setToolTipText(action.getOperator().getExpression()) ;
						}
						if(actionExpressionBinding.getTarget() != null){
							actionExpressionBinding.validateTargetToModel();
						}
						IStatus status = (IStatus) actionExpressionBinding.getValidationStatus().getValue();
						actionExpression.setMessage(status.getMessage(),status.getSeverity());
						operatorLink.getParent().layout(true,true) ;
					}
				}
			}) ;
		}
	}

	protected void doCreateControls() {
		storageComboViewer =  createStorageComboViewer() ;
		operatorLink = createOperatorLink();
		actionExpression = createActionExpressionViewer();
	}


	protected Link createOperatorLink() {
		final Link operatorLabel = new Link(this, SWT.NONE) ;
		if(widgetFactory != null){
			widgetFactory.adapt(operatorLabel,false,false) ;
		}
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
					if(!newOperator.getType().equals(ExpressionConstants.ASSIGNMENT_OPERATOR)){
						operatorLabel.setToolTipText(newOperator.getExpression()) ;
					}
					if (actionExpressionBinding.getTarget() != null) {
						actionExpressionBinding.validateTargetToModel();
						IStatus status = (IStatus) actionExpressionBinding.getValidationStatus().getValue();
						actionExpression.setMessage(status.getMessage(),status.getSeverity());
					}
					operatorLabel.getParent().layout(true, true) ;
				}
			}
		}) ;

		return operatorLabel ;
	}

	public void setEditingDomain(EditingDomain editingDomain){
		this.editingDomain = editingDomain ;
		actionExpression.setEditingDomain(editingDomain) ;
	}

	protected ComboViewer createStorageComboViewer() {
		final ComboViewer storageComboViewer = new ComboViewer(this,SWT.BORDER | SWT.READ_ONLY) ;
		storageComboViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().hint(200, SWT.DEFAULT).grab(false, false).create());
		storageComboViewer.setContentProvider(new ObservableListContentProvider()) ;
		storageComboViewer.setLabelProvider(new ExpressionLabelProvider(){
			@Override
			public String getText(Object expression) {
				return super.getText(expression) +" ("+((Expression) expression).getReturnType()+")";
			}
		}) ;

		if(storageExpressionFilter != null){
			storageComboViewer.addFilter(storageExpressionFilter) ;
		}

		if(widgetFactory != null){
			widgetFactory.adapt(storageComboViewer.getCombo(),false,false) ;
		}

		storageComboViewer.setSorter(new ViewerSorter(){

			final ExpressionComparator comparator = new ExpressionComparator();

			@Override
			public int compare(Viewer viewer, Object e1, Object e2) {
				return comparator.compare((Expression)e1,(Expression)e2);
			}
		});

		return storageComboViewer;
	}


	protected ExpressionViewer createActionExpressionViewer() {
		final ExpressionViewer actionViewer = new ExpressionViewer(this,SWT.BORDER,widgetFactory,editingDomain, getActionTargetFeature()) ;
		actionViewer.addFilter(actionExpressionFilter) ;
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
		for (ExpressionViewer text : actionsViewers) {
			if (text != null && !text.getControl().isDisposed()) {
				text.getControl().dispose();
			}
		}
		for (Link toDispose : operatorLinks) {
			if (toDispose != null && !toDispose.isDisposed()) {
				toDispose.dispose();
			}
		}
		for (Button toDispose : removes) {
			if (toDispose != null && !toDispose.isDisposed()) {
				toDispose.dispose();
			}
		}

		if(context != null){
			context.dispose();
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

	private void setOperationContainmentFeature(EReference actionTargetFeature) {
		operationContainmentFeature = actionTargetFeature;
	}

	public EObject getEObject(){
		return eObject;
	}


	protected EReference getActionTargetFeature() {
		return operationContainmentFeature;
	}


	private Operation getOperation() {
		EObject eObject = getEObject() ;
		if(getEObject() instanceof Lane){
			eObject = getEObject().eContainer() ;
		}
		return (Operation) eObject.eGet(getActionTargetFeature());
	}

	/**
	 * Set a specify IExpressionProvider for the left operand
	 * @param dataFeature
	 */
	 public void setStorageExpressionContentProvider(IExpressionProvider provider) {
		 storageExpressionProvider = provider ;
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
		 return storageComboViewer.getControl();
	 }

	 public void refresh(){
		 if(storageComboViewer != null && !storageComboViewer.getCombo().isDisposed()){
			 if(storageExpressionProvider != null){
				 storageComboViewer.setInput(new WritableList(storageExpressionProvider.getExpressions(getEObject()),Expression.class)) ;
			 }else{
				 IExpressionProvider provider = ExpressionEditorService.getInstance().getExpressionProvider(ExpressionConstants.VARIABLE_TYPE) ;
				 storageComboViewer.setInput(new WritableList(provider.getExpressions(getEObject()),Expression.class)) ;
			 }
			 Iterator it = ((WritableList)storageComboViewer.getInput()).iterator();
			 Expression dataExp = getOperation().getLeftOperand();
			 while (it.hasNext()) {
				 Expression exp = (Expression) it.next();
				 if(dataExp != null && exp != null && exp.getName() != null && exp.getName().equals(dataExp.getName())){
					 storageComboViewer.setSelection(new StructuredSelection(exp));
					 break;
				 }
			 }
		 }
	 }
}
