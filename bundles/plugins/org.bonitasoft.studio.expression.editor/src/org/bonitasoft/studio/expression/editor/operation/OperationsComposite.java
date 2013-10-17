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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.IBonitaVariableContext;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionValidator;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.SubmitFormButton;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.OperationContainer;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.databinding.Binding;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Aurelien Pupier
 * @author Aurelie Zara
 * @author Romain Bioteau
 */
public abstract class OperationsComposite extends Composite implements IBonitaVariableContext {

	public static final String SWTBOT_ID_REMOVE_LINE = "actionLinesCompositeRemoveButton";
	protected List<OperationViewer> operationViewers = new ArrayList<OperationViewer>();
	protected ArrayList<Button> removes = new ArrayList<Button>();
	protected TabbedPropertySheetPage tabbedPropertySheetPage;
	protected EMFDataBindingContext context;
	protected ArrayList<List<Binding>> bindings = new ArrayList<List<Binding>>();
	private TabbedPropertySheetWidgetFactory widgetFactory;
	private EObject eObject;
	protected final Composite mainComposite;
	private EReference operationContainmentFeature;
	private final ViewerFilter storageExpressionFilter;
	private final ViewerFilter actionExpressionFilter;
	private IExpressionNatureProvider storageExpressionNatureProvider;
	private IExpressionNatureProvider actionExpressionNatureProvider;
	private Map<String, IExpressionValidator> validatorMap = new HashMap<String, IExpressionValidator>();
	private EObject eObjectContext;
	private boolean isPageFlowContext = false;
	
	
	public OperationsComposite(TabbedPropertySheetPage tabbedPropertySheetPage,
			Composite mainComposite, ViewerFilter actionExpressionFilter,
			ViewerFilter storageExpressionFilter,boolean isPageFlowContext){
		super(mainComposite, SWT.NONE);
		this.mainComposite = mainComposite;
		this.isPageFlowContext=isPageFlowContext;
		if (tabbedPropertySheetPage != null) {
			widgetFactory = tabbedPropertySheetPage.getWidgetFactory();
			if (widgetFactory != null) {
				widgetFactory.adapt(this);
			}
			this.tabbedPropertySheetPage = tabbedPropertySheetPage;
		}
		this.actionExpressionFilter = actionExpressionFilter;
		this.storageExpressionFilter = storageExpressionFilter;
		setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

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
			ViewerFilter storageExpressionFilter) {
		this(tabbedPropertySheetPage,mainComposite,actionExpressionFilter,storageExpressionFilter,false);	
		
	}

	protected EditingDomain getEditingDomain() {
		return TransactionUtil.getEditingDomain(getEObject());
	}

	public int getNbLines(){
		return operationViewers.size();
	}
	
	public void removeLinesUI() {
		if(!operationViewers.isEmpty()){
			for (int i = operationViewers.size() - 1; i >= 0; i--) {
				removeLineUI(i);
			}
			refresh();
		}
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
		refresh();
	}

	/**
	 * @param i
	 * @return
	 */
	protected int removeLineUI(int i) {
		int lineNumber = i;
		removes.get(lineNumber).dispose();
		removes.remove(lineNumber);
		final OperationViewer operationViewer = operationViewers.get(lineNumber);
		operationViewer.dispose();
		operationViewers.remove(lineNumber);
		return lineNumber;
	}

	public void addLineUI(final Operation action) {
		final OperationViewer opViewer = createOperationViewer(action);
		operationViewers.add(opViewer);
		removes.add(createRemoveButton());
	}

	protected OperationViewer createOperationViewer(Operation action) {
		final OperationViewer viewer = new OperationViewer(this, widgetFactory, getEditingDomain(), actionExpressionFilter, storageExpressionFilter,isPageFlowContext) ;
		viewer.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		if(context != null){
			viewer.setContext(context);
		}
		if(eObjectContext != null){
			viewer.setContext(eObjectContext);
		}
		if(storageExpressionNatureProvider != null){
			viewer.setStorageExpressionNatureProvider(storageExpressionNatureProvider);
		}
		if(actionExpressionNatureProvider != null){
			viewer.setActionExpressionNatureProvider(actionExpressionNatureProvider);
		}
		for(Entry<String, IExpressionValidator> validator : validatorMap.entrySet()){
			viewer.addActionExpressionValidator(validator.getKey(), validator.getValue());
		}

		viewer.setOperation(action);
		viewer.setEObject(eObject);

		return viewer;
	}
	
	public void setContext(EObject context){
		this.eObjectContext = context;
	}

	public void addActionExpressionValidator(String expressionType,IExpressionValidator validator){
		validatorMap.put(expressionType,validator);
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
		for (OperationViewer text : operationViewers) {
			if (text != null) {
				text.dispose();
			}
		}
		for (Button toDispose : removes) {
			if (toDispose != null && !toDispose.isDisposed()) {
				toDispose.dispose();
			}
		}
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


	public abstract void refresh() ;


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

	public void setActionExpressionNatureContentProvider(
			IExpressionNatureProvider provider) {
		actionExpressionNatureProvider = provider;
	}

	@Override
	public boolean isPageFlowContext() {
		
		return isPageFlowContext;
	}
	
	
	@Override
	public void setIsPageFlowContext(boolean isPageFlowContext) {
		this.isPageFlowContext = isPageFlowContext;
		
	}

}
