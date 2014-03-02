
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
package org.bonitasoft.studio.expression.editor.viewer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.bonitasoft.studio.common.AbstractRefactorOperation;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.IBonitaVariableContext;
import org.bonitasoft.studio.common.emf.tools.WidgetHelper;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.expression.editor.ExpressionEditorPlugin;
import org.bonitasoft.studio.expression.editor.autocompletion.AutoCompletionField;
import org.bonitasoft.studio.expression.editor.autocompletion.BonitaContentProposalAdapter;
import org.bonitasoft.studio.expression.editor.autocompletion.ExpressionProposal;
import org.bonitasoft.studio.expression.editor.autocompletion.IBonitaContentProposalListener2;
import org.bonitasoft.studio.expression.editor.autocompletion.IExpressionProposalLabelProvider;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.ExpressionComparator;
import org.bonitasoft.studio.expression.editor.provider.ExpressionContentProvider;
import org.bonitasoft.studio.expression.editor.provider.ExpressionLabelProvider;
import org.bonitasoft.studio.expression.editor.provider.ExpressionTypeLabelProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionToolbarContribution;
import org.bonitasoft.studio.expression.editor.provider.IExpressionValidator;
import org.bonitasoft.studio.expression.editor.widget.ContentAssistText;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.form.Duplicable;
import org.bonitasoft.studio.model.form.TextFormField;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalListener;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.DefaultToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 * 
 */
public class ExpressionViewer extends ContentViewer implements ExpressionConstants, SWTBotConstants,
 IContentProposalListener, IBonitaContentProposalListener2, IBonitaVariableContext {

	protected Composite control;
	private Text textControl;
	protected ToolItem editControl;
	private AutoCompletionField autoCompletion;
	protected EMFDataBindingContext internalDataBindingContext;
	protected EditingDomain editingDomain;
	protected Expression selectedExpression;
	protected final Set<ViewerFilter> filters;
	private String example;
	private ControlDecoration messageDecoration;
	private boolean disposeDomain = false;
	protected String mandatoryFieldName;
	private ControlDecoration typeDecoration;
	private boolean editing = false;
	private EObject context;
	private final List<ISelectionChangedListener> expressionEditorListener = new ArrayList<ISelectionChangedListener>();
	private boolean withConnector = false;
	private List<IExpressionValidationListener> validationListeners = new ArrayList<IExpressionValidationListener>();
	private ToolItem eraseControl;
	private boolean isPageFlowContext = false;
	private boolean isOverviewContext = false;
	private AbstractRefactorOperation operation;
	private AbstractRefactorOperation  removeOperation;

	protected final DisposeListener disposeListener = new DisposeListener() {

		@Override
		public void widgetDisposed(DisposeEvent e) {
			handleDispose(e);
		}
	};
	private final EReference expressionReference;
	protected IExpressionNatureProvider expressionNatureProvider = new ExpressionContentProvider();
	protected DataBindingContext externalDataBindingContext;
	protected Binding expressionBinding;
	private final Map<Integer, String> messages = new HashMap<Integer, String>();
	private ToolBar toolbar;
	private List<IExpressionToolbarContribution> toolbarContributions = new ArrayList<IExpressionToolbarContribution>();
	private Map<String, IExpressionValidator> validatorsForType = new HashMap<String, IExpressionValidator>();
	protected boolean isPassword;
	private DefaultToolTip textTooltip;
	private IExpressionProposalLabelProvider expressionProposalLableProvider;
	private ContentAssistText contentAssistText;

	public ExpressionViewer(Composite composite, int style, EReference expressionReference) {
		this(composite, style, null, null, expressionReference);
	}

	public ExpressionViewer(Composite composite, int style, TabbedPropertySheetWidgetFactory widgetFactory,
			EReference expressionReference) {
		this(composite, style, widgetFactory, null, expressionReference);
	}

	public ExpressionViewer(Composite composite, int style, TabbedPropertySheetWidgetFactory widgetFactory,
			EditingDomain editingDomain, EReference expressionReference) {
		this(composite, style, widgetFactory, editingDomain, expressionReference, false);

	}
	

	public ExpressionViewer(Composite composite, int style, TabbedPropertySheetWidgetFactory widgetFactory,
			EditingDomain editingDomain, EReference expressionReference, boolean withConnector) {
		this.editingDomain = editingDomain;
		this.expressionReference = expressionReference;
		filters = new HashSet<ViewerFilter>();
		this.withConnector = withConnector;
		createControl(composite, style, widgetFactory);
		setContentProvider(new ArrayContentProvider());
		setLabelProvider(new ExpressionLabelProvider());
	}

	protected void createControl(Composite composite, int style, TabbedPropertySheetWidgetFactory widgetFactory) {
		if (widgetFactory != null) {
			control = widgetFactory.createComposite(composite, SWT.INHERIT_DEFAULT);
		} else {
			control = new Composite(composite, SWT.INHERIT_DEFAULT);
		}
		control.addDisposeListener(disposeListener);
		control.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).spacing(0, 0).create());
		createTextControl(style, widgetFactory);
		createToolbar(style, widgetFactory);

	}

	protected void createToolbar(int style, TabbedPropertySheetWidgetFactory widgetFactory) {
		toolbar = new ToolBar(control, SWT.FLAT | SWT.NO_FOCUS);
		toolbar.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(false, false).create());
		editControl = createEditToolItem(toolbar);
		if (withConnector) {
			createToolBarExtension(toolbar);
		}
		createEraseToolItem(toolbar);
		if (widgetFactory != null) {
			widgetFactory.adapt(toolbar, true, true);
		}
	}

	protected void createToolBarExtension(ToolBar toolbar) {
		for (IConfigurationElement elem : BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
				"org.bonitasoft.studio.expression.editor.expressionViewerToolbar")) {
			try {
				final IExpressionToolbarContribution item = (IExpressionToolbarContribution) elem
						.createExecutableExtension("class");
				if (item.getId() == "ConnectorInExpressionViewer" && withConnector) {
					item.fill(toolbar, -1);
					item.setExpressionViewer(this);
				}
				toolbarContributions.add(item);
			} catch (CoreException e) {
				BonitaStudioLog.error(e, ExpressionEditorPlugin.PLUGIN_ID);
			}
		}
	}

	protected ToolItem createEditToolItem(final ToolBar tb) {
		final ToolItem editControl = new ToolItem(tb, SWT.PUSH | SWT.NO_FOCUS);
		editControl.setImage(Pics.getImage(PicsConstants.edit));
		editControl.setToolTipText(Messages.editAndContinue);

		/* For test purpose */
		editControl.setData(SWTBOT_WIDGET_ID_KEY, SWTBOT_ID_EDITBUTTON);
		editControl.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				boolean connectorEdit = false;
				if(tb != null && withConnector && selectedExpression != null && ExpressionConstants.CONNECTOR_TYPE.equals(selectedExpression.getType())){
					for(ToolItem ti : tb.getItems()){
						Object data = ti.getData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY);
						if(data != null && data.equals(SWTBotConstants.SWTBOT_ID_CONNECTORBUTTON)){
							connectorEdit = true;
							ti.notifyListeners(SWT.Selection, event);
						}
					}
				}
				if(!connectorEdit){
					openEditDialog();
				}
			}
		});

		editControl.addDisposeListener(disposeListener);
		return editControl;
	}

	protected ToolItem createEraseToolItem(ToolBar tb) {
		eraseControl = new ToolItem(tb, SWT.PUSH | SWT.NO_FOCUS);
		eraseControl.setImage(Pics.getImage(PicsConstants.clear));
		eraseControl.setToolTipText(Messages.eraseExpression);

		/* For test purpose */
		eraseControl.setData(SWTBOT_WIDGET_ID_KEY, SWTBOT_ID_ERASEBUTTON);
		eraseControl.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				String type = selectedExpression.getType();
				if (ExpressionConstants.SCRIPT_TYPE.equals(type)) {
					if (!MessageDialog.openConfirm(Display.getDefault().getActiveShell(), Messages.cleanExpressionTitle,
							Messages.cleanExpressionMsg)) {
						return;
					}
				}
				if (!ExpressionConstants.CONDITION_TYPE.equals(type)) {
					type = ExpressionConstants.CONSTANT_TYPE;
				}
				if (editingDomain != null) {

					CompoundCommand cc=new CompoundCommand();
					cc.append(SetCommand.create(editingDomain, selectedExpression,
							ExpressionPackage.Literals.EXPRESSION__TYPE, type));
					cc.append(SetCommand.create(editingDomain, selectedExpression,
							ExpressionPackage.Literals.EXPRESSION__NAME, ""));
					cc.append(SetCommand.create(editingDomain, selectedExpression,
							ExpressionPackage.Literals.EXPRESSION__CONTENT, ""));
					cc.append(RemoveCommand.create(editingDomain, selectedExpression,
							ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS,
							selectedExpression.getReferencedElements()));
					cc.append(RemoveCommand.create(editingDomain, selectedExpression,
							ExpressionPackage.Literals.EXPRESSION__CONNECTORS, selectedExpression.getConnectors()));
					boolean hasBeenExecuted = executeRemoveOperation(cc);
					if (!hasBeenExecuted){
						editingDomain.getCommandStack().execute(cc);
					}
				} else {
					selectedExpression.setType(type);
					selectedExpression.setName("");
					selectedExpression.setContent("");
					selectedExpression.getReferencedElements().clear();
					selectedExpression.getConnectors().clear();
				}

				textControl.setText("");
				validate();
				refresh();
			}
		});


		eraseControl.addDisposeListener(disposeListener);
		return eraseControl;
	}

	public void setExpressionProposalLableProvider(IExpressionProposalLabelProvider expressionProposalLableProvider) {
		this.expressionProposalLableProvider = expressionProposalLableProvider;
		if (autoCompletion != null) {
			autoCompletion.setExpressionProposalLabelProvider(expressionProposalLableProvider);
		}
	}

	public ContentAssistText getContentAssistText() {
		return contentAssistText;
	}

	protected void createTextControl(int style, TabbedPropertySheetWidgetFactory widgetFactory) {
		if (expressionProposalLableProvider == null) {
			expressionProposalLableProvider = new ExpressionLabelProvider();
		}
		contentAssistText = new ContentAssistText(control, expressionProposalLableProvider, style);
		textControl = contentAssistText.getTextControl();
		if (widgetFactory != null) {
			widgetFactory.adapt(textControl, false, false);
		}
		textControl.addDisposeListener(disposeListener);
		textTooltip = new DefaultToolTip(textControl) {
			@Override
			protected boolean shouldCreateToolTip(Event event) {
				return super.shouldCreateToolTip(event) && getText(event) != null;
			}
		};
		textTooltip.setShift(new Point(5, 5));
		textTooltip.setRespectMonitorBounds(true);
		textTooltip.setPopupDelay(100);

		typeDecoration = new ControlDecoration(contentAssistText.getToolbar(), SWT.LEFT, control);
		typeDecoration.setMarginWidth(0);

		messageDecoration = new ControlDecoration(contentAssistText, SWT.LEFT, control);
		messageDecoration.setShowHover(true);
		messageDecoration.setMarginWidth(1);
		messageDecoration.hide();

		contentAssistText.addContentAssistListener(this);
		autoCompletion = contentAssistText.getAutocompletion();
		autoCompletion.addExpressionProposalListener(this);

		int indent = 0;
		if ((style & SWT.PASSWORD) != 0) {
			isPassword = true;
		}
		if ((style & SWT.BORDER) != 0) {
			indent = 16;
		}
		contentAssistText.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).indent(indent, 0)
				.grab(true, false).create());
	}

	protected void openEditDialog() {
		final Object input = getInput();
		EObject editInput = context;
		if (input != null) {
			if (editInput == null) {
				if (input instanceof EObject) {
					editInput = (EObject) input;
				} else {
					editInput = (EObject) ((IObservableValue) input).getValue();
				}
			}
		}
		EditExpressionDialog dialog = createEditDialog(editInput);
		dialog.setIsPageFlowContext(isPageFlowContext);
		if (dialog.open() == Dialog.OK) {
			Expression newExpression = dialog.getExpression();
			executeOperation(newExpression.getName());
			updateSelection(newExpression);
			setSelection(new StructuredSelection(selectedExpression));
			if (editingDomain == null) {

				selectedExpression.setReturnType(newExpression.getReturnType());
				selectedExpression.setType(newExpression.getType());
			} else {
				editingDomain.getCommandStack().execute(
						SetCommand.create(editingDomain, selectedExpression,
								ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE, newExpression.getReturnType()));
				editingDomain.getCommandStack().execute(
						SetCommand.create(editingDomain, selectedExpression, ExpressionPackage.Literals.EXPRESSION__TYPE,
								newExpression.getType()));
			}

			refresh();
			fireExpressionEditorChanged(new SelectionChangedEvent(this, new StructuredSelection(selectedExpression)));
		}
	}

	protected EditExpressionDialog createEditDialog(EObject editInput) {
		return new EditExpressionDialog(control.getShell(), isPassword, EcoreUtil.copy(selectedExpression), editInput,
				editingDomain, filters.toArray(new ViewerFilter[filters.size()]), this);
	}

	protected void fireExpressionEditorChanged(SelectionChangedEvent selectionChangedEvent) {
		for (ISelectionChangedListener listener : expressionEditorListener) {
			listener.selectionChanged(selectionChangedEvent);
		}

	}

	@Override
	public Control getControl() {
		return control;
	}

	public ToolBar getToolbar() {
		return toolbar;
	}

	@Override
	public ISelection getSelection() {
		if (selectedExpression != null) {
			return new StructuredSelection(selectedExpression);
		} else {
			return new StructuredSelection();
		}
	}

	public BonitaContentProposalAdapter getContentProposal() {
		return autoCompletion.getContentProposalAdapter();
	}

	@Override
	public void refresh() {
		if (selectedExpression != null) {
			internalRefresh(selectedExpression);
		}
	}

	private Image getLabelProviderImage(ILabelProvider labelProvider, Object input) {
		return labelProvider.getImage(input);
	}

	@Override
	public void setSelection(ISelection selection, boolean reveal) {

		if (selection instanceof IStructuredSelection) {
			Object sel = ((IStructuredSelection) selection).getFirstElement();
			if (sel instanceof Expression) {
				selectedExpression = (Expression) sel;
				bindExpression();
				if (editingDomain == null) {
					editingDomain = TransactionUtil.getEditingDomain(selectedExpression);
				}
				final StructuredSelection selection2 = new StructuredSelection(selectedExpression);
				fireSelectionChanged(new SelectionChangedEvent(this, selection2));
				for (IExpressionToolbarContribution contribution : toolbarContributions) {
					contribution.setExpression(selectedExpression);
				}
				if (ExpressionConstants.CONDITION_TYPE.equals(selectedExpression.getType())) {
					autoCompletion.getContentProposalAdapter().setEnabled(false);
					autoCompletion.getContentProposalAdapter().setProposalAcceptanceStyle(
							ContentProposalAdapter.PROPOSAL_INSERT);
				} else {
					autoCompletion.getContentProposalAdapter().setEnabled(true);
					autoCompletion.getContentProposalAdapter().setProposalAcceptanceStyle(
							ContentProposalAdapter.PROPOSAL_REPLACE);
				}
				validate();
				refresh();
			} else {
				selectedExpression = null;
			}
		}
		editing = false;

	}

	protected void updateSelection(Expression expression) {
		new ExpressionSynchronizer(editingDomain, expression, selectedExpression).synchronize();
		refresh();
	}

	@Override
	protected void inputChanged(Object input, Object oldInput) {
		if (input instanceof IObservableValue) {
			input = ((IObservableValue) input).getValue();
		}
		if (input != null && input instanceof EObject && expressionReference != null) {
			selectedExpression = (Expression) ((EObject) input).eGet(expressionReference);
		} else {
			// FIXME: it might be
			// org.eclipse.emf.ecore.util.EObjectContainmentEList
		}
		if (editingDomain == null) {
			if (input != null && input instanceof EObject) {
				editingDomain = TransactionUtil.getEditingDomain(input);
			}
		}
		manageNatureProviderAndAutocompletionProposal(input);
	}

	public void manageNatureProviderAndAutocompletionProposal(Object input) {
		if (expressionNatureProvider != null) {
			if (context == null) {
				expressionNatureProvider.setContext((EObject) input);
			} else {
				expressionNatureProvider.setContext(context);
			}
		}
		if (selectedExpression != null && ExpressionConstants.CONDITION_TYPE.equals(selectedExpression.getType())) {
			setProposalsFiltering(false);
			autoCompletion.getContentProposalAdapter().setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_INSERT);
		} else {
			autoCompletion.getContentProposalAdapter().setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
		}
		autoCompletion.setContext(expressionNatureProvider.getContext());
		final Set<Expression> filteredExpressions = getFilteredExpressions();
		autoCompletion.setProposals(filteredExpressions.toArray(new Expression[filteredExpressions.size()]));

		final ArrayList<String> filteredExpressionType = getFilteredExpressionType();
		autoCompletion.setFilteredExpressionType(filteredExpressionType);
		if((filteredExpressionType.contains(ExpressionConstants.VARIABLE_TYPE) 
				&& filteredExpressionType.contains(ExpressionConstants.PARAMETER_TYPE) 
				&& filteredExpressions.isEmpty())){
			contentAssistText.setProposalEnabled(false);
		}
		else {
			contentAssistText.setProposalEnabled(true);
		}
	}

	private ArrayList<String> getFilteredExpressionType() {
		final ArrayList<String> filteredExpressions = new ArrayList<String>();
		final Set<ViewerFilter> fitlers = getFilters();
		final EObject input = expressionNatureProvider.getContext();

		Expression exp = ExpressionFactory.eINSTANCE.createExpression();
		exp.setName("");
		if (filters != null && expressionNatureProvider != null && input!=null) {
			for (ViewerFilter viewerFilter : fitlers) {
				exp.setType(ExpressionConstants.VARIABLE_TYPE);
				if(!viewerFilter.select(this, input, exp)){
					filteredExpressions.add(ExpressionConstants.VARIABLE_TYPE);
				}
				exp.setType(ExpressionConstants.PARAMETER_TYPE);
				if(!viewerFilter.select(this, input, exp)){
					filteredExpressions.add(ExpressionConstants.PARAMETER_TYPE);
				}
				exp.setType(ExpressionConstants.CONSTANT_TYPE);
				if(!viewerFilter.select(this, input, exp)){
					filteredExpressions.add(ExpressionConstants.CONSTANT_TYPE);
				}
				exp.setType(ExpressionConstants.DOCUMENT_TYPE);
				if (!viewerFilter.select(this, input, exp)){
					filteredExpressions.add(ExpressionConstants.DOCUMENT_TYPE);
				}
				exp.setType(ExpressionConstants.DOCUMENT_REF_TYPE);
				if (!viewerFilter.select(this, input, exp)){
					filteredExpressions.add(ExpressionConstants.DOCUMENT_REF_TYPE);
				}
			}
		}
		return filteredExpressions;
	}

	public void setExpressionNatureProvider(IExpressionNatureProvider expressionNatureProvider) {
		this.expressionNatureProvider = expressionNatureProvider;
	}

	protected Set<Expression> getFilteredExpressions() {
		Set<Expression> filteredExpressions = new TreeSet<Expression>(new ExpressionComparator());
		if (expressionNatureProvider != null) {
			if (!(expressionNatureProvider instanceof ExpressionContentProvider)) {
				final ExpressionContentProvider provider = new ExpressionContentProvider();
				if (context == null) {
					provider.setContext((EObject) getInput());
				} else {
					provider.setContext(context);
				}
				Expression[] expressions = provider.getExpressions();
				if (expressions != null) {
					filteredExpressions.addAll(Arrays.asList(expressions));
				}
			}
			Expression[] expressions = expressionNatureProvider.getExpressions();
			EObject input = expressionNatureProvider.getContext();
			if (input == null) {
				if (getInput() instanceof EObject) {
					input = (EObject) getInput();
				}
			}
			if (expressions != null) {
				filteredExpressions.addAll(Arrays.asList(expressions));
			}
			Set<Expression> toRemove = new HashSet<Expression>();
			if (input != null) {
				for (Expression exp : filteredExpressions) {
					for (ViewerFilter filter : getFilters()) {
						if (filter != null && !filter.select(this, input, exp)) {
							toRemove.add(exp);
						}
					}
					if (selectedExpression != null
							&& !ExpressionConstants.CONDITION_TYPE.equals(selectedExpression.getType())) {
						if (selectedExpression != null && selectedExpression.isReturnTypeFixed()
								&& selectedExpression.getReturnType() != null) {
							if (!compatibleReturnTypes(selectedExpression,exp)) {
								toRemove.add(exp);
							}
						}
					}
				}
			}
			filteredExpressions.removeAll(toRemove);
		}
		return filteredExpressions;
	}

	protected boolean compatibleReturnTypes(Expression currentExpression,Expression targetExpression) {
		final String currentReturnType = currentExpression.getReturnType();
		final String targetReturnType = targetExpression.getReturnType();
		if (currentReturnType.equals(targetReturnType)) {
			return true;
		}
		try {
			Class<?> currentReturnTypeClass = Class.forName(currentReturnType);
			Class<?> targetReturnTypeClass = Class.forName(targetReturnType);
			return currentReturnTypeClass.isAssignableFrom(targetReturnTypeClass);
		} catch (ClassNotFoundException e) {
			BonitaStudioLog.debug("Failed to determine the compatibility between " + targetReturnType + " and "
					+ currentReturnType, ExpressionEditorPlugin.PLUGIN_ID);
		}
		return true;
	}

	protected Set<ViewerFilter> getFilters() {
		return filters;
	}

	protected void bindExpression() {
		if (expressionBinding != null && externalDataBindingContext != null) {
			externalDataBindingContext.removeBinding(expressionBinding);
			expressionBinding.dispose();
		}
		if (internalDataBindingContext != null) {
			internalDataBindingContext.dispose();
		}
		internalDataBindingContext = new EMFDataBindingContext();

		IObservableValue nameObservable = null;
		if (editingDomain != null) {
			nameObservable = EMFEditProperties.value(editingDomain, ExpressionPackage.Literals.EXPRESSION__NAME)
					.observeDetail(ViewerProperties.singleSelection().observe(this));
		} else {
			nameObservable = EMFProperties.value(ExpressionPackage.Literals.EXPRESSION__NAME).observeDetail(
					ViewerProperties.singleSelection().observe(this));
		}

		IObservableValue typeObservable = null;
		if (editingDomain != null) {
			typeObservable = EMFEditProperties.value(editingDomain, ExpressionPackage.Literals.EXPRESSION__TYPE)
					.observeDetail(ViewerProperties.singleSelection().observe(this));
		} else {
			typeObservable = EMFProperties.value(ExpressionPackage.Literals.EXPRESSION__TYPE).observeDetail(
					ViewerProperties.singleSelection().observe(this));
		}

		UpdateValueStrategy targetToModelNameStrategy = new UpdateValueStrategy();
		if (mandatoryFieldName != null) {
			targetToModelNameStrategy.setBeforeSetValidator(new EmptyInputValidator(mandatoryFieldName));
		}
		targetToModelNameStrategy.setAfterConvertValidator(new IValidator() {

			@Override
			public IStatus validate(Object value) {
				IExpressionValidator validator = null;
				if (selectedExpression != null) {
					validator = validatorsForType.get(selectedExpression.getType());
				}
				if (validator == null) {
					validator = validatorsForType.get(ExpressionConstants.ALL_TYPES);
				}
				if (validator != null) {
					validator.setDomain(editingDomain);
					validator.setContext(context);
					if (selectedExpression != null) {
						validator.setInputExpression(selectedExpression);
					}
					setMessage(null, IStatus.OK);
					final IStatus status = validator.validate(value);
					if (status.isOK()) {
						setMessage(null, status.getSeverity());
					} else {
						String message = status.getMessage();
						if (status instanceof MultiStatus) {
							StringBuilder sb = new StringBuilder();
							for (IStatus statusChild : status.getChildren()) {
								sb.append(statusChild.getMessage());
								sb.append("\n");
							}
							if (sb.length() > 0) {
								sb.delete(sb.length() - 1, sb.length());
							}
							message = sb.toString();
						}
						setMessage(message, status.getSeverity());
					}
					return status;
				} else {
					setMessage(null, IStatus.OK);
				}
				return ValidationStatus.ok();
			}
		});
		targetToModelNameStrategy.setConverter(getNameConverter());

		final ISWTObservableValue observeDelayedValue = SWTObservables.observeDelayedValue(500,
				SWTObservables.observeText(textControl, SWT.Modify));
		expressionBinding = internalDataBindingContext.bindValue(observeDelayedValue, nameObservable,
				targetToModelNameStrategy, null);

		expressionBinding.getValidationStatus().addValueChangeListener(new IValueChangeListener() {

			@Override
			public void handleValueChange(ValueChangeEvent event) {
				IStatus status = (IStatus) event.diff.getNewValue();
				fireValidationStatusChanged(status.getSeverity());
			}
		});

		bindEditableText(typeObservable);
		nameObservable.addValueChangeListener(new IValueChangeListener() {

			@Override
			public void handleValueChange(ValueChangeEvent arg0) {
				fireSelectionChanged(new SelectionChangedEvent(ExpressionViewer.this, getSelection()));
			}
		});
		if (externalDataBindingContext != null) {
			externalDataBindingContext.addBinding(expressionBinding);
		}
	}

	protected void bindEditableText(IObservableValue typeObservable) {
		UpdateValueStrategy modelToTargetTypeStrategy = new UpdateValueStrategy();
		modelToTargetTypeStrategy.setConverter(new Converter(String.class, Boolean.class) {

			@Override
			public Object convert(Object from) {
				final boolean isScriptType = ExpressionConstants.SCRIPT_TYPE.equals(from.toString());
				final boolean isConnectorType = from.toString().equals(ExpressionConstants.CONNECTOR_TYPE);
				final boolean isXPathType = from.toString().equals(ExpressionConstants.XPATH_TYPE);
				final boolean isJavaType = from.toString().equals(ExpressionConstants.JAVA_TYPE);
				if (isScriptType) {
					textTooltip.setText(Messages.editScriptExpressionTooltip);
				} else if (isXPathType) {
					textTooltip.setText(Messages.editXpathExpressionTooltip);
				} else if (isJavaType) {
					textTooltip.setText(Messages.editJavaExpressionTooltip);
				} else if (isConnectorType) {
					textTooltip.setText(Messages.editConnectorExpressionTooltip);
				} else {
					textTooltip.setText(null);
				}
				return !(isScriptType || isConnectorType || isJavaType || isXPathType);
			}

		});

		internalDataBindingContext.bindValue(SWTObservables.observeEditable(textControl), typeObservable,
				new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), modelToTargetTypeStrategy);
	}

	protected void updateContent(String newContent) {
		if (newContent != null && !newContent.equals(selectedExpression.getContent())) {
			if (editingDomain != null) {
				editingDomain.getCommandStack().execute(
						SetCommand.create(editingDomain, selectedExpression, ExpressionPackage.Literals.EXPRESSION__CONTENT,
								newContent));
			} else {
				selectedExpression.setContent(newContent);
			}
		}
	}

	protected void updateContentType(String newContentType) {
		if (!newContentType.equals(selectedExpression.getType())) {
			if (editingDomain != null) {
				editingDomain.getCommandStack().execute(
						SetCommand.create(editingDomain, selectedExpression, ExpressionPackage.Literals.EXPRESSION__TYPE,
								newContentType));
			} else {
				selectedExpression.setType(newContentType);
			}
			updateInterpreter(newContentType);
		}
	}

	private void updateInterpreter(String newContentType) {
		if (!ExpressionConstants.SCRIPT_TYPE.equals(newContentType)) {
			if (editingDomain != null) {
				editingDomain.getCommandStack().execute(
						SetCommand.create(editingDomain, selectedExpression,
								ExpressionPackage.Literals.EXPRESSION__INTERPRETER, null));
			} else {
				selectedExpression.setInterpreter(null);
			}
		}
	}

	protected String getContentFromInput(String input) {
		final String selectedExpressionType = selectedExpression.getType();
		if (ExpressionConstants.SCRIPT_TYPE.equals(selectedExpressionType)
				|| ExpressionConstants.PATTERN_TYPE.equals(selectedExpressionType)
				|| ExpressionConstants.XPATH_TYPE.equals(selectedExpressionType)
				|| ExpressionConstants.JAVA_TYPE.equals(selectedExpressionType)) {
			return selectedExpression.getContent(); // NO CONTENT UPDATE WHEN
			// THOSES TYPES
		}

		Set<String> cache = new HashSet<String>();
		for (Expression e : getFilteredExpressions()) {
			if (e.getName() != null && e.getName().equals(input)) {
				cache.add(e.getContent());
			}
		}
		if (cache.size() > 1) {
			for (String content : cache) {
				if (content.equals(selectedExpression.getContent())) {
					return content;
				}
			}
			return cache.iterator().next();
		} else if (cache.size() == 1) {
			return cache.iterator().next();
		}
		return input;
	}

	protected String getContentTypeFromInput(String input) {
		Assert.isNotNull(selectedExpression);
		String expressionType = selectedExpression.getType();
		if (CONSTANT_TYPE.equals(expressionType)) {
			return expressionType;
		}
		if (selectedExpression.getType() == null) {
			expressionType = CONSTANT_TYPE;
		}
		if (ExpressionConstants.SCRIPT_TYPE.equals(expressionType)) {
			return ExpressionConstants.SCRIPT_TYPE;
		} else if (ExpressionConstants.CONDITION_TYPE.equals(expressionType)) {
			return ExpressionConstants.CONDITION_TYPE;
		} else if (ExpressionConstants.CONNECTOR_TYPE.equals(expressionType)) {
			return ExpressionConstants.CONNECTOR_TYPE;
		} else if (ExpressionConstants.PATTERN_TYPE.equals(expressionType)) {
			return ExpressionConstants.PATTERN_TYPE;
		} else if (ExpressionConstants.JAVA_TYPE.equals(expressionType)) {
			return ExpressionConstants.JAVA_TYPE;
		} else if (ExpressionConstants.XPATH_TYPE.equals(expressionType)) {
			return ExpressionConstants.XPATH_TYPE;
		} else if (ExpressionConstants.URL_ATTRIBUTE_TYPE.equals(expressionType)) {
			return ExpressionConstants.URL_ATTRIBUTE_TYPE;
		} else if (ExpressionConstants.SEARCH_INDEX_TYPE.equals(expressionType)){
			return ExpressionConstants.SEARCH_INDEX_TYPE;
		}

		Set<String> cache = new HashSet<String>();
		for (Expression e : getFilteredExpressions()) {
			if (e.getName() != null && e.getName().equals(input)) {
				cache.add(e.getType());
			}
		}
		if (cache.size() > 1) {
			for (String type : cache) {
				if (type.equals(selectedExpression.getType())) {
					return type;
				}
			}
			return cache.iterator().next();
		} else if (cache.size() == 1) {
			return cache.iterator().next();
		} else {
			expressionType = CONSTANT_TYPE;
		}

		return expressionType;
	}

	protected void internalRefresh(Object element) {
		Control composite = getControl();
		if (!composite.isDisposed()) {
			ILabelProvider labelProvider = (ILabelProvider) getLabelProvider();
			Image icon = getLabelProviderImage(labelProvider, selectedExpression);
			ExpressionTypeLabelProvider expTypeProvider = new ExpressionTypeLabelProvider();
			String desc = expTypeProvider.getText(selectedExpression.getType());
			typeDecoration.setImage(icon);
			typeDecoration.setDescriptionText(desc);
			if (!editing) {
				if (selectedExpression.getName() == null || selectedExpression.getName().isEmpty()) {
					if (!ExpressionConstants.CONDITION_TYPE.equals(selectedExpression.getType())) {
						if (typeDecoration.isVisible()) {
							typeDecoration.hide();
						}
					}
				} else {
					if (!typeDecoration.isVisible()) {
						typeDecoration.show();
					}
				}
			}
			refreshMessageDecoration();

		}

	}

	private void refreshMessageDecoration() {
		final Entry<Integer, String> message = getMessageToDisplay();
		if (message != null) {
			messageDecoration.setDescriptionText(message.getValue());
			if (message.getKey() == IStatus.INFO) {
				// Issue with focus
				messageDecoration.setShowOnlyOnFocus(false);
			} else {
				messageDecoration.setShowOnlyOnFocus(false);
			}

			Image icon = getImageForMessageKind(message.getKey());
			if (icon != null) {
				messageDecoration.setImage(icon);
			}

			messageDecoration.show();

		} else {

			messageDecoration.hide();

		}
	}

	private Image getImageForMessageKind(Integer messageKind) {
		switch (messageKind) {
		case IStatus.WARNING:
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK);
		case IStatus.INFO:
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_INFO_TSK);
		case IStatus.ERROR:
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK);
		default:
			break;
		}

		return null;
	}

	private Entry<Integer, String> getMessageToDisplay() {
		Entry<Integer, String> errorEntry = null;
		Entry<Integer, String> warningEntry = null;
		Entry<Integer, String> infoEntry = null;
		for (Entry<Integer, String> entry : messages.entrySet()) {
			if (entry.getKey() == IStatus.ERROR && entry.getValue() != null) {
				errorEntry = entry;
			} else if (entry.getKey() == IStatus.WARNING && entry.getValue() != null) {
				warningEntry = entry;
			} else if (entry.getKey() == IStatus.INFO && entry.getValue() != null) {
				infoEntry = entry;
			}
		}
		if (errorEntry != null) {
			return errorEntry;
		} else if (warningEntry != null) {
			return warningEntry;
		} else if (infoEntry != null) {
			return infoEntry;
		} else {
			return null;
		}
	}

	@Override
	public void setLabelProvider(IBaseLabelProvider labelProvider) {
		Assert.isTrue(labelProvider instanceof ExpressionLabelProvider);
		super.setLabelProvider(labelProvider);
	}

	public Text getTextControl() {
		return textControl;
	}

	public ToolItem getButtonControl() {
		return editControl;
	}

	public void addFilter(ViewerFilter viewerFilter) {
		filters.add(viewerFilter);
	}

	public void removeFilter(ViewerFilter viewerFilter) {
		filters.remove(viewerFilter);
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
		textControl.setMessage(example);
		textControl.redraw();
	}

	public String getMessage(int messageKind) {
		return messages.get(messageKind);
	}

	public void setMessage(String message, int messageKind) {
		if (IStatus.OK == messageKind) {
			messages.remove(IStatus.ERROR);
			messages.remove(IStatus.WARNING);
		} else {
			if (messageKind == IStatus.WARNING) {
				messages.remove(IStatus.ERROR);
			}
			if (messageKind == IStatus.INFO) {
				messages.remove(IStatus.ERROR);
				messages.remove(IStatus.WARNING);
			}
			messages.put(messageKind, message);
		}
		refresh();
	}

	protected void fireValidationStatusChanged(int newStatus) {
		for (IExpressionValidationListener listener : validationListeners) {
			listener.validationStatusChanged(newStatus);
		}
	}

	@Override
	protected void handleDispose(DisposeEvent event) {
		if (disposeDomain) {
			WorkspaceEditingDomainFactory.INSTANCE.unmapResourceSet((TransactionalEditingDomain) editingDomain);
			editingDomain = null;
			disposeDomain = false;
		}
		if (expressionBinding != null && externalDataBindingContext != null) {
			externalDataBindingContext.removeBinding(expressionBinding);
			expressionBinding.dispose();
		}
		if (internalDataBindingContext != null) {
			internalDataBindingContext.dispose();
		}

		super.handleDispose(event);
	}

	public void setEditingDomain(EditingDomain editingDomain) {
		this.editingDomain = editingDomain;
	}

	public void setMandatoryField(String fieldName, DataBindingContext dbc) {
		mandatoryFieldName = fieldName;
		externalDataBindingContext = dbc;
	}

	public void addExpressionEditorChangedListener(ISelectionChangedListener iSelectionChangedListener) {
		expressionEditorListener.add(iSelectionChangedListener);
	}

	public EReference getExpressionReference() {
		return expressionReference;
	}

	public void setContext(EObject context) {
		this.context = context;
	}

	public void updateAutocompletionProposals() {
		if (expressionNatureProvider != null) {
			Set<Expression> filteredExpressions = getFilteredExpressions();
			autoCompletion.setProposals(filteredExpressions.toArray(new Expression[filteredExpressions.size()]));
		}
	}

	public void setProposalsFiltering(boolean filterProposal) {
		autoCompletion.getContentProposalProvider().setFiltering(filterProposal);
	}

	public ToolItem getEraseControl() {
		return eraseControl;
	}

	protected Converter getNameConverter() {
		Converter nameConverter = new Converter(String.class, String.class) {

			@Override
			public Object convert(Object fromObject) {
				int caretPosition = textControl.getCaretPosition();
				String input = (String) fromObject;
				updateContentType(getContentTypeFromInput(input));
				updateContent(getContentFromInput(input));
				boolean hasBeenExecuted = executeOperation(input);
				refresh();
				if (hasBeenExecuted){
					textControl.setSelection(caretPosition, caretPosition);
				}
				return fromObject;
			}
		};
		return nameConverter;
	}

	public void addExpressionValidator(String expressionType, IExpressionValidator comaprisonExpressionValidator) {
		validatorsForType.put(expressionType, comaprisonExpressionValidator);
	}

	public void addExpressionValidationListener(IExpressionValidationListener listener) {
		if (!validationListeners.contains(listener)) {
			validationListeners.add(listener);
		}
	}

	public void validate() {
		if (expressionBinding != null && expressionBinding.getTarget() != null) {
			expressionBinding.validateTargetToModel();
		}
	}

	public void setExternalDataBindingContext(DataBindingContext ctx) {
		this.externalDataBindingContext = ctx;
	}

	@Override
	public void proposalAccepted(IContentProposal proposal) {
		int proposalAcceptanceStyle = autoCompletion.getContentProposalAdapter().getProposalAcceptanceStyle();
		if (proposalAcceptanceStyle == ContentProposalAdapter.PROPOSAL_REPLACE) {
			ExpressionProposal prop = (ExpressionProposal) proposal;
			final Expression copy = EcoreUtil.copy((Expression) prop.getExpression());
			copy.setReturnTypeFixed(selectedExpression.isReturnTypeFixed());
			if (copy.getType().equals(ExpressionConstants.FORM_FIELD_TYPE)) {
				EObject parent = context;
				if (parent == null) {
					parent = expressionNatureProvider.getContext();
				}
				if (parent instanceof Widget) {
					final Widget w = (Widget) parent;
					if (w != null && w instanceof TextFormField && copy.getName().equals(WidgetHelper.FIELD_PREFIX + w.getName())) {
						String returnTypeModifier = w.getReturnTypeModifier();
						if (returnTypeModifier != null) {
							if(w instanceof Duplicable && ((Duplicable) w).isDuplicate()){
								returnTypeModifier = List.class.getName();
							}
							if (!copy.isReturnTypeFixed()) {
								copy.setReturnType(returnTypeModifier);
							}
						}
					}
				}
			}

			updateSelection(copy);
			fireSelectionChanged(new SelectionChangedEvent(ExpressionViewer.this,
					new StructuredSelection(selectedExpression)));
			validate();
		}
	}

	@Override
	public void proposalPopupOpened(BonitaContentProposalAdapter adapter) {
		manageNatureProviderAndAutocompletionProposal(getInput());
	}

	@Override
	public void proposalPopupClosed(BonitaContentProposalAdapter adapter) {

	}

	public IExpressionNatureProvider getExpressionNatureProvider() {
		return expressionNatureProvider;
	}

	@Override
	public boolean isPageFlowContext() {

		return isPageFlowContext;
	}

	@Override
	public void setIsPageFlowContext(boolean isPageFlowContext) {
		this.isPageFlowContext=isPageFlowContext;

	}

	public void setRefactorOperationToExecuteWhenUpdatingContent(AbstractRefactorOperation operation){
		this.operation = operation;
	}

	private boolean executeOperation(String newValue){
		boolean hasBeenExecuted = false;
		if (operation!=null){
			operation.setNewValue(newValue);
			IProgressService service = PlatformUI.getWorkbench().getProgressService();
			try {
				service.busyCursorWhile(operation);
				hasBeenExecuted =true;
			} catch (InvocationTargetException e) {
				BonitaStudioLog.error(e);
			} catch (InterruptedException e) {
				BonitaStudioLog.error(e);
			}

		}
		return hasBeenExecuted;
	}

	public void setRemoveOperation(AbstractRefactorOperation removeOperation){
		this.removeOperation = removeOperation;
	}

	private boolean executeRemoveOperation(CompoundCommand cc){
		boolean isExecuted=false;
		if (removeOperation !=null){
			removeOperation.setCompoundCommand(cc);
			IProgressService service = PlatformUI.getWorkbench().getProgressService();
			try {
				service.busyCursorWhile(removeOperation);
				isExecuted = true;
			} catch (InvocationTargetException e) {
				BonitaStudioLog.error(e);
			} catch (InterruptedException e) {
				BonitaStudioLog.error(e);
			}

		}
		return isExecuted;
	}



	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.IBonitaVariableContext#isOverViewContext()
	 */
	@Override
	public boolean isOverViewContext() {
		return isOverviewContext;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsOverviewContext(boolean)
	 */
	@Override
	public void setIsOverviewContext(boolean isOverviewContext) {
		this.isOverviewContext=isOverviewContext;
		
	}

}
