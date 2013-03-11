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
import java.util.Arrays;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.ExpressionComparator;
import org.bonitasoft.studio.expression.editor.provider.ExpressionContentProvider;
import org.bonitasoft.studio.expression.editor.provider.ExpressionLabelProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.SubmitFormButton;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.OperationContainer;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
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
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Aurelien Pupier
 * @author Aurelie Zara
 * 
 */
public class OperationsComposite extends Composite {

	public static final String SWTBOT_ID_REMOVE_LINE = "actionLinesCompositeRemoveButton";
	protected ArrayList<ExpressionViewer> actionsViewers = new ArrayList<ExpressionViewer>();
	protected ArrayList<ComboViewer> storageCombo = new ArrayList<ComboViewer>();
	protected ArrayList<Button> removes = new ArrayList<Button>();
	protected ArrayList<Link> operatorLinks = new ArrayList<Link>();
	protected Image imagemoins;
	protected TabbedPropertySheetPage tabbedPropertySheetPage;
	protected EMFDataBindingContext context;
	protected ArrayList<List<Binding>> bindings = new ArrayList<List<Binding>>();
	private TabbedPropertySheetWidgetFactory widgetFactory;
	private EObject eObject;
	private final Composite mainComposite;
	private EReference operationContainmentFeature;
	private final ViewerFilter storageExpressionFilter;
	private final ViewerFilter actionExpressionFilter;
	private IExpressionNatureProvider storageExpressionNatureProvider;
	private IExpressionProvider storageExpressionProvider;
	private IExpressionNatureProvider actionExpressionNatureProvider;
	private IValidator actionExpressionsValidator;
	private final OperationReturnTypesValidator operationReturnTypeValidator;
	private boolean displayStorageExpressionType;

	public OperationsComposite(TabbedPropertySheetPage tabbedPropertySheetPage,
			Composite mainComposite, ViewerFilter actionExpressionFilter,
			ViewerFilter storageExpressionFilter,boolean displayStorageExpressionType) {
		super(mainComposite, SWT.NONE);
		this.displayStorageExpressionType = displayStorageExpressionType;
		this.mainComposite = mainComposite;
		operationReturnTypeValidator = new OperationReturnTypesValidator();
		if (tabbedPropertySheetPage != null) {
			widgetFactory = tabbedPropertySheetPage.getWidgetFactory();
			if (widgetFactory != null) {
				widgetFactory.adapt(this);
			}
			this.tabbedPropertySheetPage = tabbedPropertySheetPage;
		}
		this.actionExpressionFilter = actionExpressionFilter;
		this.storageExpressionFilter = storageExpressionFilter;
		setLayout(GridLayoutFactory.fillDefaults().numColumns(4).create());

		Button addButton = null;
		if (widgetFactory != null) {
			addButton = widgetFactory.createButton(mainComposite, "", SWT.PUSH);
		} else {
			addButton = new Button(mainComposite, SWT.PUSH);
		}
		addButton.setText(Messages.addAction);
		addButton.setLayoutData(GridDataFactory.fillDefaults()
				.align(SWT.BEGINNING, SWT.TOP).hint(85, SWT.DEFAULT).create());
		addButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				final Operation action = ExpressionFactory.eINSTANCE.createOperation();
				final Operator operator = ExpressionFactory.eINSTANCE.createOperator();
				operator.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
				action.setOperator(operator);
				final EditingDomain domain = getEditingDomain();
				if (domain != null) {
					domain.getCommandStack().execute(
							AddCommand.create(domain, getEObject(),
									getActionTargetFeature(), action));
				} else {
					getActions().add(action);
				}
				addLineUI(action);
				refresh();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

	}

	public OperationsComposite(TabbedPropertySheetPage tabbedPropertySheetPage,
			Composite mainComposite, ViewerFilter actionExpressionFilter,
			ViewerFilter storageExpressionFilter,
			IValidator actionExpressionsValidator,boolean displayStorageExpressionType) {
		this(tabbedPropertySheetPage, mainComposite, actionExpressionFilter,
				storageExpressionFilter,displayStorageExpressionType);
		this.actionExpressionsValidator = actionExpressionsValidator;
	}

	protected EditingDomain getEditingDomain() {
		return TransactionUtil.getEditingDomain(getEObject());
	}

	public void removeLinesUI() {
		for (int i = storageCombo.size() - 1; i >= 0; i--) {
			removeLineUI(i);
		}
		refresh();
	}

	private void removeLine(int i) {
		int lineNumber = removeLineUI(i);
		final EditingDomain domain = getEditingDomain();
		if (domain != null) {
			getEditingDomain().getCommandStack().execute(
					RemoveCommand.create(getEditingDomain(), getEObject(),
							getActionTargetFeature(),
							getActions().get(lineNumber)));
		} else {
			getActions().remove(i);
		}

	}

	/**
	 * @param i
	 * @return
	 */
	protected int removeLineUI(int i) {
		int lineNumber = i;
		removes.get(lineNumber).dispose();
		removes.remove(lineNumber);
		storageCombo.get(lineNumber).getControl().dispose();
		storageCombo.remove(lineNumber);
		operatorLinks.get(i).dispose();
		operatorLinks.remove(lineNumber);
		actionsViewers.get(lineNumber).getControl().dispose();
		actionsViewers.remove(lineNumber);
		if (context != null) {
			for (Binding binding : bindings.get(lineNumber)) {
				context.removeBinding(binding);
				binding.dispose();
			}
		}
		refresh();
		return lineNumber;
	}

	public void addLineUI(final Operation action) {
		List<Binding> lineBindings = new ArrayList<Binding>();

		final ComboViewer storageComboViewer = createStorageComboViewer(action);
		storageCombo.add(storageComboViewer);
		final Link operatorLink = createOperatorLink(action);
		operatorLinks.add(operatorLink);
		final ExpressionViewer actionExpression = createActionExpressionViewer(action);
		actionExpression.setInput(action);

		UpdateValueStrategy actionExpressionUpdateStrategy = null;
		if (operationReturnTypeValidator != null) {
			operationReturnTypeValidator.setDataExpression((Expression) ((IStructuredSelection) storageComboViewer.getSelection()).getFirstElement());
			final IStatus status = operationReturnTypeValidator.validate(action.getRightOperand());
			actionExpression.setMessage(status.getMessage(),status.getSeverity());
			actionExpressionUpdateStrategy = new UpdateValueStrategy();
			actionExpressionUpdateStrategy.setAfterGetValidator(operationReturnTypeValidator);
		}
		if (actionExpressionsValidator != null) {
			final IStatus status = actionExpressionsValidator.validate(action.getRightOperand());
			actionExpression.setMessage(status.getMessage(),status.getSeverity());
			actionExpressionUpdateStrategy = new UpdateValueStrategy();
			actionExpressionUpdateStrategy.setBeforeSetValidator(actionExpressionsValidator);
		}

		IObservableValue actionExpressionObservableValue = EMFEditProperties
				.value(getEditingDomain(),
						ExpressionPackage.Literals.OPERATION__RIGHT_OPERAND)
						.observe(action);
		final Binding actionExpressionBinding = context.bindValue(
				ViewerProperties.singleSelection().observe(actionExpression),
				actionExpressionObservableValue,
				actionExpressionUpdateStrategy, null);



		actionsViewers.add(actionExpression);
		removes.add(createRemoveButton());

		IObservableValue leftOperandObservableValue = null;
		if (getEditingDomain() == null) {
			leftOperandObservableValue = EMFObservables.observeValue(action,
					ExpressionPackage.Literals.OPERATION__LEFT_OPERAND);
		} else {
			leftOperandObservableValue = EMFEditObservables.observeValue(
					getEditingDomain(), action,
					ExpressionPackage.Literals.OPERATION__LEFT_OPERAND);
		}
		UpdateValueStrategy modelToTarget = new UpdateValueStrategy();
		modelToTarget.setConverter(new Converter(Expression.class,
				Expression.class) {

			@Override
			public Object convert(Object from) {
				if (from != null) {
					Expression modelExpression = (Expression) from;
					EObject originalEObject = modelExpression
							.getReferencedElements().get(0);
					for (int i = 0; i < storageComboViewer.getCombo()
							.getItems().length; i++) {
						Expression exp = (Expression) storageComboViewer
								.getElementAt(i);
						if (!exp.getReferencedElements().isEmpty()) {
							
							EObject eObject = exp.getReferencedElements()
									.get(0);
							if (originalEObject instanceof SearchIndex){
								if (modelExpression.getName().equals(exp.getName())){
											return exp;
								}
							} else {
								if (originalEObject instanceof Element
										&& eObject instanceof Element) {
									if (((Element) originalEObject).getName()
											.equals(((Element) eObject).getName())) {
										return exp;
									}
								}
							}
						}
					}
				}
				return null;
			}
		});

		UpdateValueStrategy targetToModel = new UpdateValueStrategy();
		targetToModel.setConverter(new Converter(Expression.class,
				Expression.class) {

			@Override
			public Object convert(Object from) {
				if (from != null) {
					Expression copy =  EcoreUtil.copy((Expression)from);
					operationReturnTypeValidator.setDataExpression(copy);
					return copy;
				}
				return null;
			}
		});
		targetToModel.setBeforeSetValidator(operationReturnTypeValidator);
		final Binding storageBinding = context.bindValue(
				ViewersObservables.observeSingleSelection(storageComboViewer),
				leftOperandObservableValue, targetToModel, modelToTarget);

		ISelectionChangedListener updateExpressionListener = new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent arg0) {
				if (storageBinding.getTarget() != null) {
					storageBinding.validateTargetToModel();
				}
				if (actionExpressionBinding.getTarget() != null) {
					actionExpressionBinding.validateTargetToModel();
					IStatus status = (IStatus) actionExpressionBinding
							.getValidationStatus().getValue();
					actionExpression.setMessage(status.getMessage(),status.getSeverity());

				}
			}

		};
		actionExpression.addSelectionChangedListener(updateExpressionListener);
		actionExpression.addExpressionEditorChangedListener(updateExpressionListener);
		

		storageComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Expression selectedExpression = (Expression) ((IStructuredSelection) event
						.getSelection()).getFirstElement();
				if (selectedExpression != null) {
					Operator operator = action.getOperator();
					if (getEditingDomain() == null) {
						operator.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
					} else {
						getEditingDomain()
						.getCommandStack()
						.execute(
								SetCommand
								.create(getEditingDomain(),
										operator,
										ExpressionPackage.Literals.OPERATOR__TYPE,
										ExpressionConstants.ASSIGNMENT_OPERATOR));
					}
					final OperatorLabelProvider labelProvider = new OperatorLabelProvider();
					operatorLink.setText("<A>"
							+ labelProvider.getText(action
									.getOperator().getType()) + "</A>");
					if (!action
							.getOperator()
							.getType()
							.equals(ExpressionConstants.ASSIGNMENT_OPERATOR)) {
						operatorLink.setToolTipText(action
								.getOperator().getExpression());
					}
					actionExpressionBinding.validateTargetToModel();
					IStatus status = (IStatus) actionExpressionBinding.getValidationStatus().getValue();
					actionExpression.setMessage(status.getMessage(),status.getSeverity());
					operatorLink.getParent().layout(true, true);
				}
			}
		});


		
		lineBindings.add(storageBinding);
		
		bindings.add(lineBindings);
		
		//validate line
		actionExpressionBinding.validateTargetToModel();
		IStatus status = (IStatus) actionExpressionBinding.getValidationStatus().getValue();
		actionExpression.setMessage(status.getMessage(),status.getSeverity());
		
	}

	protected Button createRemoveButton() {
		Button remove = new Button(this, SWT.FLAT);
		if (widgetFactory != null) {
			widgetFactory.adapt(remove, false, false);
		}
		remove.setData("org.eclipse.swtbot.widget.key", SWTBOT_ID_REMOVE_LINE);
		remove.setLayoutData(GridDataFactory.swtDefaults().create());
		remove.setImage(Pics.getImage("delete.png"));
		remove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removeLine(removes.indexOf(e.getSource()));
			}
		});

		return remove;
	}

	protected Link createOperatorLink(final Operation action) {
		final Link operatorLabel = new Link(this, SWT.NONE);
		if (widgetFactory != null) {
			widgetFactory.adapt(operatorLabel, false, false);
		}
		operatorLabel.setLayoutData(GridDataFactory.swtDefaults()
				.align(SWT.BEGINNING, SWT.CENTER).create());
		final OperatorLabelProvider labelProvider = new OperatorLabelProvider();

		operatorLabel.setText("<A>"
				+ labelProvider.getText(action.getOperator()) + "</A>");
		if (!action.getOperator().getType()
				.equals(ExpressionConstants.ASSIGNMENT_OPERATOR)) {
			operatorLabel.setToolTipText(action.getOperator().getExpression());
		}

		operatorLabel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				OperatorSelectionDialog dialog = new OperatorSelectionDialog(
						Display.getDefault().getActiveShell(), action);
				if (dialog.open() == Dialog.OK) {
					Operator newOperator = dialog.getOperator();

					if (getEditingDomain() == null) {
						action.setOperator(newOperator);
					} else {
						getEditingDomain()
						.getCommandStack()
						.execute(
								SetCommand
								.create(getEditingDomain(),
										action,
										ExpressionPackage.Literals.OPERATION__OPERATOR,
										newOperator));
					}

					operatorLabel.setText("<A>"
							+ labelProvider.getText(newOperator) + "</A>");
					if (!newOperator.getType().equals(
							ExpressionConstants.ASSIGNMENT_OPERATOR)) {
						operatorLabel.setToolTipText(newOperator
								.getExpression());
					}
					operatorLabel.getParent().layout(true, true);
				}
			}
		});

		return operatorLabel;
	}

	protected ComboViewer createStorageComboViewer(Operation action) {
		final ComboViewer storageComboViewer = new ComboViewer(this, SWT.BORDER
				| SWT.READ_ONLY);
		storageComboViewer.getControl().setLayoutData(
				GridDataFactory.fillDefaults().grab(false, false).hint(250, SWT.DEFAULT).create());
		storageComboViewer.setContentProvider(new ObservableListContentProvider());
		storageComboViewer.setLabelProvider(new ExpressionLabelProvider() {
			@Override
			public String getText(Object expression) {
				if(displayStorageExpressionType){
					return super.getText(expression) + " ("
							+ ((Expression) expression).getReturnType() + ") -- "+((Expression)expression).getType();
				}else{
					return super.getText(expression) + " ("
							+ ((Expression) expression).getReturnType() + ")";
				}
			}
		});

		if (widgetFactory != null) {
			widgetFactory.adapt(storageComboViewer.getCombo(), false, false);
		}

		if (storageExpressionFilter != null) {
			storageComboViewer.addFilter(storageExpressionFilter);
		}

		if (storageExpressionNatureProvider != null) {
			storageExpressionNatureProvider.setContext(getEObject());
			storageComboViewer.setInput(new WritableList(Arrays.asList(storageExpressionNatureProvider
					.getExpressions()),Expression.class));
		} else if (storageExpressionProvider != null) {
			storageComboViewer.setInput(new WritableList(storageExpressionProvider
					.getExpressions(getEObject()),Expression.class));
		} else {
			storageExpressionNatureProvider = new ExpressionContentProvider();
			storageExpressionNatureProvider.setContext(getEObject());
			storageComboViewer.setInput(new WritableList(Arrays.asList(storageExpressionNatureProvider
					.getExpressions()),Expression.class));
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

	protected ExpressionViewer createActionExpressionViewer(Operation action) {
		final ExpressionViewer actionViewer = new ExpressionViewer(this,
				SWT.BORDER, widgetFactory, getEditingDomain(),
				ExpressionPackage.Literals.OPERATION__RIGHT_OPERAND);
		actionViewer.addFilter(actionExpressionFilter);
		actionViewer.getControl().setLayoutData(
				GridDataFactory.fillDefaults().grab(true, false).create());
		actionViewer.getControl().setToolTipText(getExpressionTooltip());

		Expression actionExp = action.getRightOperand();
		if (actionExp == null) {
			actionExp = ExpressionFactory.eINSTANCE.createExpression();
			final EditingDomain domain = getEditingDomain();
			if (domain != null) {
				getEditingDomain()
				.getCommandStack()
				.execute(
						SetCommand
						.create(getEditingDomain(),
								action,
								ExpressionPackage.Literals.OPERATION__RIGHT_OPERAND,
								actionExp));
			} else {
				action.setRightOperand(actionExp);
			}

		}
		actionViewer.setContext(getEObject());
		if (actionExpressionNatureProvider != null) {
			actionExpressionNatureProvider.setContext(getEObject());
			actionViewer
			.setExpressionNatureProvider(actionExpressionNatureProvider);
		} else {
			actionExpressionNatureProvider = new ExpressionContentProvider();
			actionExpressionNatureProvider.setContext(getEObject());
			actionViewer
			.setExpressionNatureProvider(actionExpressionNatureProvider);
		}


		return actionViewer;
	}

	protected String getExpressionTooltip() {
		return Messages.shortExpr_tooltip;
	}

	/**
	 * add lines from the form
	 */
	public void fillTable() {
		for (Operation action : getActions()) {
			addLineUI(action);
		}
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

		if (context != null) {
			context.dispose();
		}
		// don't dispose imageMoins and imagePlus because they are get from
		// imageRegistry
	}

	public void setContext(EMFDataBindingContext emfDataBindingContext) {
		context = emfDataBindingContext;
	}

	public void setEObject(EObject eObject) {
		this.eObject = eObject;
		if (eObject instanceof Form) {
			setOperationContainmentFeature(FormPackage.Literals.FORM__ACTIONS);
		} else if (eObject instanceof SubmitFormButton) {
			setOperationContainmentFeature(FormPackage.Literals.SUBMIT_FORM_BUTTON__ACTIONS);
		} else if (eObject instanceof AbstractCatchMessageEvent) {
			setOperationContainmentFeature(ProcessPackage.Literals.ABSTRACT_CATCH_MESSAGE_EVENT__MESSAGE_CONTENT);
		} else if (eObject instanceof Connector) {
			setOperationContainmentFeature(ProcessPackage.Literals.CONNECTOR__OUTPUTS);
		} else if (eObject instanceof OperationContainer) {
			setOperationContainmentFeature(ProcessPackage.Literals.OPERATION_CONTAINER__OPERATIONS);
		}
	}

	public void setOperationContainmentFeature(EReference actionTargetFeature) {
		operationContainmentFeature = actionTargetFeature;
	}

	public EObject getEObject() {
		return eObject;
	}

	public void refresh() {
		Shell shell = mainComposite.getShell();
		Point defaultSize = shell.getSize();
		Point size = shell.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
		shell.setSize(defaultSize.x, size.y);
		shell.layout(true, true);

		if (tabbedPropertySheetPage != null) {
			tabbedPropertySheetPage.resizeScrolledComposite();
		}

		for(ComboViewer viewer : storageCombo){
			viewer.refresh();
		}
	}

	protected EReference getActionTargetFeature() {
		return operationContainmentFeature;
	}

	@SuppressWarnings("unchecked")
	private List<Operation> getActions() {
		EObject eObject = getEObject();
		if (getEObject() instanceof Lane) {
			eObject = getEObject().eContainer();
		}
		return (List<Operation>) eObject.eGet(getActionTargetFeature());
	}

	/**
	 * Set a specify IExpressionNatureProvider for the left operand
	 * 
	 * @param dataFeature
	 */
	public void setStorageExpressionNatureContentProvider(
			IExpressionNatureProvider provider) {
		storageExpressionNatureProvider = provider;
	}

	/**
	 * Set a specify IExpressionProvider for the left operand
	 * 
	 * @param dataFeature
	 */
	public void setStorageExpressionContentProvider(IExpressionProvider provider) {
		storageExpressionProvider = provider;
	}

	public void setActionExpressionNatureContentProvider(
			IExpressionNatureProvider provider) {
		actionExpressionNatureProvider = provider;
	}

	public void setActionExpressionValidator(
			IValidator actionExpressionsValidator) {
		this.actionExpressionsValidator = actionExpressionsValidator;
	}

}
