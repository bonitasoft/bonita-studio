/**
 * 
 */
package org.bonitasoft.studio.condition.ui.expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.BonitaStudioFontRegistry;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.condition.conditionModel.ConditionModelPackage;
import org.bonitasoft.studio.condition.conditionModel.Expression_ProcessRef;
import org.bonitasoft.studio.condition.conditionModel.Operation_Compare;
import org.bonitasoft.studio.condition.scoping.ConditionModelGlobalScopeProvider;
import org.bonitasoft.studio.condition.ui.i18n.Messages;
import org.bonitasoft.studio.condition.ui.internal.ConditionModelActivator;
import org.bonitasoft.studio.expression.editor.ExpressionEditorPlugin;
import org.bonitasoft.studio.expression.editor.constant.ConstantTypeLabelProvider;
import org.bonitasoft.studio.expression.editor.constant.ExpressionReturnTypeContentProvider;
import org.bonitasoft.studio.expression.editor.provider.ExpressionContentProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.SelectionAwareExpressionEditor;
import org.bonitasoft.studio.expression.editor.viewer.SelectDependencyDialog;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.Data;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditor;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorFactory;
import org.eclipse.xtext.ui.editor.embedded.IEditedResourceProvider;
import org.eclipse.xtext.ui.resource.XtextResourceSetProvider;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;

import com.google.inject.Injector;

/**
 * @author Aurelie Zara
 * @author Romain Bioteau
 */
public class ComparisonExpressionEditor extends SelectionAwareExpressionEditor implements IExpressionEditor {

	private ComboViewer typeCombo;
	private Button automaticResolutionButton;
	protected Button removeDependencyButton;
	private Section dependencySection;
	private TableViewer dependenciesViewer;
	private Button addDependencyButton;
	@SuppressWarnings("restriction")
	private EmbeddedEditor comparisonEditor;
	private EObject context;
	private ComposedAdapterFactory adapterFactory;
	private AdapterFactoryLabelProvider adapterLabelProvider;
	private Expression inputExpression;
	private XtextResource resource;
	private Resource eResource;

	public ComparisonExpressionEditor(Resource eResource, EObject context) {
		this.context = context;
		this.eResource = eResource;
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterLabelProvider  = new AdapterFactoryLabelProvider(adapterFactory) ;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#createExpressionEditor(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public Control createExpressionEditor(Composite parent) {
		final Composite mainComposite =new Composite(parent,SWT.NONE);
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create());
		createHeader(mainComposite);
		createComparisonEditor(mainComposite);
		createDependanciesResolutionComposite(mainComposite);
		createReturnTypeComposite(mainComposite);
		return mainComposite;
	}

	protected void createHeader(Composite parent){
		Composite header = new Composite(parent,SWT.NONE);
		header.setLayout(GridLayoutFactory.fillDefaults().create());
		header.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		final Label supportedOperators = new Label(header,SWT.WRAP);
		supportedOperators.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		supportedOperators.setText(Messages.comparisonSupportedOperators);
		supportedOperators.setFont(BonitaStudioFontRegistry.getItalicFont());
		
	}

	@SuppressWarnings("restriction")
	protected void createComparisonEditor(Composite parent){
		IEditedResourceProvider resourceProvider = new IEditedResourceProvider() {
			@Override
			public XtextResource createResource() {
				try {
					final Injector injector = ConditionModelActivator.getInstance().getInjector(ConditionModelActivator.ORG_BONITASOFT_STUDIO_CONDITION_CONDITIONMODEL);
					final XtextResourceSetProvider xtextResourceSetProvider = injector.getInstance(XtextResourceSetProvider.class);
					final ResourceSet resourceSet = xtextResourceSetProvider.get(RepositoryManager.getInstance().getCurrentRepository().getProject());
					resource = (XtextResource) resourceSet.createResource(URI.createURI("somefile.cmodel"));

					final ConditionModelGlobalScopeProvider globalScopeProvider = injector.getInstance(ConditionModelGlobalScopeProvider.class);
					final List<String> accessibleObjects = new ArrayList<String>();
					for(Data d : ModelHelper.getAccessibleData(context)){
						accessibleObjects.add(ModelHelper.getEObjectID(d));
					}
					for(Parameter p : ModelHelper.getParentProcess(context).getParameters()){
						accessibleObjects.add(ModelHelper.getEObjectID(p));
					}
					globalScopeProvider.setAccessibleEObjects(accessibleObjects);
					return (XtextResource) resource;
				} catch (Exception e) {
					BonitaStudioLog.error(e, ExpressionEditorPlugin.PLUGIN_ID);
					return null;
				}
			}
		};
		ConditionModelActivator activator = ConditionModelActivator.getInstance();
		Injector injector =  activator.getInjector(ConditionModelActivator.ORG_BONITASOFT_STUDIO_CONDITION_CONDITIONMODEL);
		EmbeddedEditorFactory factory = injector.getInstance(EmbeddedEditorFactory.class);
		comparisonEditor = factory.newEditor(resourceProvider).withParent(parent);
		comparisonEditor.createPartialEditor(false);
		comparisonEditor.getViewer().getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		comparisonEditor.getViewer().addTextListener(new ITextListener() {

			@Override
			public void textChanged(TextEvent event) {
				comparisonEditor.getViewer().getControl().notifyListeners(SWT.Modify, new Event());
			}
		});
	}

	protected void createDependanciesResolutionComposite(Composite parent){
		final Composite dependanciesComposite = new Composite(parent,SWT.NONE);
		dependanciesComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.CENTER).create()) ;
		dependanciesComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());

		automaticResolutionButton = new Button(dependanciesComposite, SWT.CHECK) ;
		automaticResolutionButton.setText(Messages.automaticResolution) ;
		automaticResolutionButton.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).span(2, 1).create()) ;
		automaticResolutionButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(automaticResolutionButton.getSelection()){
					removeDependencyButton.setEnabled(false) ;
					updateDependencies();
				}
				dependencySection.setExpanded(!automaticResolutionButton.getSelection());
			}
		}) ;


		dependencySection = new Section(dependanciesComposite,Section.NO_TITLE);
		dependencySection.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
		dependencySection.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());


		Composite dependenciesComposite = new Composite(dependencySection, SWT.NONE) ;
		dependenciesComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		dependenciesComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create()) ;

		dependenciesViewer = new TableViewer(dependenciesComposite, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI) ;
		dependenciesViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 40).create());
		dependenciesViewer.setContentProvider(new ArrayContentProvider()) ;
		dependenciesViewer.setLabelProvider(adapterLabelProvider) ;
		dependenciesViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if(!event.getSelection().isEmpty() && !automaticResolutionButton.getSelection()){
					removeDependencyButton.setEnabled(true) ;
				}
			}
		}) ;

		Composite addRemoveComposite = new Composite(dependenciesComposite, SWT.NONE) ;
		addRemoveComposite.setLayoutData(GridDataFactory.fillDefaults().create()) ;
		addRemoveComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 0).create()) ;

		addDependencyButton = new Button(addRemoveComposite, SWT.FLAT) ;
		addDependencyButton.setText(Messages.add) ;
		addDependencyButton.setLayoutData(GridDataFactory.fillDefaults().create()) ;


		removeDependencyButton = new Button(addRemoveComposite, SWT.FLAT) ;
		removeDependencyButton.setText(Messages.remove) ;
		removeDependencyButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for(Object sel : ((IStructuredSelection)dependenciesViewer.getSelection()).toList()){
					inputExpression.getReferencedElements().remove(sel) ;
				}
			}
		}) ;
		removeDependencyButton.setEnabled(false) ;
		removeDependencyButton.setLayoutData(GridDataFactory.fillDefaults().create()) ;

		dependencySection.setClient(dependenciesComposite);
		automaticResolutionButton.setSelection(true);

	}

	protected void updateDependencies() {
		inputExpression.getReferencedElements().clear();
		final Injector injector = ConditionModelActivator.getInstance().getInjector(ConditionModelActivator.ORG_BONITASOFT_STUDIO_CONDITION_CONDITIONMODEL);
		final IResourceValidator xtextResourceChecker =	injector.getInstance(IResourceValidator.class);
		final List<Issue> issues = xtextResourceChecker.validate(resource, CheckMode.FAST_ONLY, null);
		if(issues.isEmpty()){//Validation is OK
			Operation_Compare compareOp = (Operation_Compare) resource.getContents().get(0);
			if(compareOp != null){
				List<Expression_ProcessRef> references = ModelHelper.getAllItemsOfType(compareOp, ConditionModelPackage.Literals.EXPRESSION_PROCESS_REF);
				for(Expression_ProcessRef ref : references){
					EObject dep = resolveProxy(ref.getValue());
					inputExpression.getReferencedElements().add(EcoreUtil.copy(dep));
				}
			}
		}
	}

	private EObject resolveProxy(EObject ref) {
		ResourceSet rSet = null;
		if(ref.eIsProxy()){
			rSet = eResource.getResourceSet();
		}
		EObject dep = EcoreUtil2.resolve(ref, rSet);
		if(rSet != null){
			rSet.getResources().remove(ref.eResource());
		}
		return dep;
	}

	protected void createReturnTypeComposite(Composite parent){
		final Composite returnTypeComposite = new Composite(parent,SWT.NONE);
		returnTypeComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
		returnTypeComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL,SWT.BOTTOM).create());

		Label typeLabel = new Label(returnTypeComposite, SWT.NONE) ;
		typeLabel.setText(Messages.returnType) ;

		typeCombo = new ComboViewer(returnTypeComposite, SWT.BORDER | SWT.READ_ONLY) ;
		typeCombo.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		typeCombo.setContentProvider(new ExpressionReturnTypeContentProvider()) ;
		typeCombo.setLabelProvider(new ConstantTypeLabelProvider()) ;
		typeCombo.setInput(new Object()) ;
	}


	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#bindExpression(org.eclipse.emf.databinding.EMFDataBindingContext, org.eclipse.emf.ecore.EObject, org.bonitasoft.studio.model.expression.Expression, org.eclipse.jface.viewers.ViewerFilter[])
	 */
	@Override
	public void bindExpression(EMFDataBindingContext dataBindingContext,
			EObject context, Expression inputExpression,
			ViewerFilter[] viewerTypeFilters) {
		this.inputExpression = inputExpression;
		final IObservableValue contentModelObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__CONTENT) ;
		final IObservableValue nameModelObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__NAME) ;
		final IObservableValue dependenciesModelObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS) ;
		final IObservableValue autoDepsModelObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__AUTOMATIC_DEPENDENCIES) ;
		final IObservableValue returnTypeModelObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE) ;
		final ISWTObservableValue observeText = SWTObservables.observeText(comparisonEditor.getViewer().getControl(),SWT.Modify);
		final UpdateValueStrategy updateStrategy = new UpdateValueStrategy();
		updateStrategy.setConverter(new Converter(String.class,String.class) {

			@Override
			public Object convert(Object fromObject) {
				if(ComparisonExpressionEditor.this.inputExpression.isAutomaticDependencies()){
					updateDependencies();
				}

				return fromObject;
			}
		});
		dataBindingContext.bindValue(observeText, contentModelObservable,updateStrategy,null) ;
		dataBindingContext.bindValue(observeText, nameModelObservable) ;
		dataBindingContext.bindValue(ViewersObservables.observeInput(dependenciesViewer), dependenciesModelObservable) ;

		UpdateValueStrategy opposite = new UpdateValueStrategy() ;
		opposite.setConverter(new Converter(Boolean.class,Boolean.class) {

			@Override
			public Object convert(Object fromObject) {
				return !((Boolean)fromObject);
			}
		}) ;

		dataBindingContext.bindValue(SWTObservables.observeSelection(automaticResolutionButton), autoDepsModelObservable) ;
		dataBindingContext.bindValue(SWTObservables.observeSelection(automaticResolutionButton), SWTObservables.observeEnabled(addDependencyButton),opposite,new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER)) ;
		dependencySection.setExpanded(!automaticResolutionButton.getSelection());

		addDependencyButton.setEnabled(!inputExpression.isAutomaticDependencies()) ;
		ControlDecorationSupport.create(dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(typeCombo), returnTypeModelObservable),SWT.LEFT) ;
		typeCombo.getCombo().setEnabled(!inputExpression.isReturnTypeFixed()) ;

		final ExpressionContentProvider provider = new ExpressionContentProvider() ;
		provider.setContext(context);

		final Set<Expression> filteredExpressions = new HashSet<Expression>() ;
		Expression[] expressions = provider.getExpressions();
		EObject input =  provider.getContext() ;
		if(expressions != null){
			filteredExpressions.addAll(Arrays.asList(expressions)) ;
			if(input != null && viewerTypeFilters != null){
				for(Expression exp : expressions) {
					for(ViewerFilter filter : viewerTypeFilters){
						if(filter != null && !filter.select(comparisonEditor.getViewer(), input, exp)){
							filteredExpressions.remove(exp) ;
						}
					}
				}
			}
		}

		addDependencyButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SelectDependencyDialog dialog =	new SelectDependencyDialog(Display.getDefault().getActiveShell(),filteredExpressions, ComparisonExpressionEditor.this.inputExpression.getReferencedElements()) ;
				dialog.open()  ;
			}
		}) ;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#canFinish()
	 */
	@Override
	public boolean canFinish() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#dispose()
	 */
	@Override
	public void dispose() {


	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#okPressed()
	 */
	@Override
	public void okPressed() {

	}

	@Override
	public Control getTextControl() {
		return comparisonEditor.getViewer().getTextWidget();
	}


}
