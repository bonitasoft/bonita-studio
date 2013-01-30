/**
 * 
 */
package org.bonitasoft.studio.expression.editor.comparison;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.BonitaStudioFontRegistry;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.condition.scoping.ConditionModelGlobalScopeProvider;
import org.bonitasoft.studio.condition.ui.internal.ConditionModelActivator;
import org.bonitasoft.studio.expression.editor.ExpressionEditorPlugin;
import org.bonitasoft.studio.expression.editor.constant.ConstantTypeLabelProvider;
import org.bonitasoft.studio.expression.editor.constant.ExpressionReturnTypeContentProvider;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.SelectionAwareExpressionEditor;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.Data;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.DialogTray;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditor;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorFactory;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorModelAccess;
import org.eclipse.xtext.ui.editor.embedded.IEditedResourceProvider;
import org.eclipse.xtext.ui.resource.XtextResourceSetProvider;

import com.google.inject.Injector;

/**
 * @author Aurelie Zara
 *
 */
public class ComparisonExpressionEditor extends SelectionAwareExpressionEditor implements IExpressionEditor {

	private ComboViewer typeCombo;
	private Button automaticResolutionButton;
	protected Button removeDependencyButton;
	private Section dependencySection;
	private TableViewer dependenciesViewer;
	private Button addDependencyButton;
	private EmbeddedEditor comparisonEditor;
	private EmbeddedEditorModelAccess partialEditor;

	private Resource processResource;
	private EObject context;

	public ComparisonExpressionEditor(Resource eResource, EObject context) {
		processResource = eResource;
		this.context = context;
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
		final CLabel supportedOperators = new CLabel(parent,SWT.NONE);
		supportedOperators.setText(Messages.comparisonSupportedOperators);
		supportedOperators.setFont(BonitaStudioFontRegistry.getItalicFont());

	}

	protected void createComparisonEditor(Composite parent){
		IEditedResourceProvider resourceProvider = new IEditedResourceProvider() {

			@Override
			public XtextResource createResource() {
				try {
					final Injector injector = ConditionModelActivator.getInstance().getInjector(ConditionModelActivator.ORG_BONITASOFT_STUDIO_CONDITION_CONDITIONMODEL);
					final XtextResourceSetProvider xtextResourceSetProvider = injector.getInstance(XtextResourceSetProvider.class);
					final ResourceSet resourceSet = xtextResourceSetProvider.get(RepositoryManager.getInstance().getCurrentRepository().getProject());
					final XtextResource resource = (XtextResource) resourceSet.createResource(URI.createURI("somefile.cmodel"));
				
					final ConditionModelGlobalScopeProvider globalScopeProvider = injector.getInstance(ConditionModelGlobalScopeProvider.class);
					List<String> accessibleObjects = new ArrayList<String>();
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
		partialEditor = comparisonEditor.createPartialEditor(false);
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
					// dependencyJob.schedule() ;
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
		//        dependenciesViewer.setLabelProvider(adapterLabelProvider) ;
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
				//                for(Object sel : ((IStructuredSelection)dependenciesViewer.getSelection()).toList()){
				//                    inputExpression.getReferencedElements().remove(sel) ;
				//                }
			}
		}) ;
		removeDependencyButton.setEnabled(false) ;
		removeDependencyButton.setLayoutData(GridDataFactory.fillDefaults().create()) ;

		dependencySection.setClient(dependenciesComposite);
		automaticResolutionButton.setSelection(true);

	}

	protected void createReturnTypeComposite(Composite parent){
		final Composite returnTypeComposite = new Composite(parent,SWT.NONE);
		returnTypeComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
		returnTypeComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).align(SWT.FILL,SWT.BOTTOM).create());

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

		final IObservableValue contentModelObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__CONTENT) ;
		final IObservableValue nameModelObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__NAME) ;
		final IObservableValue dependenciesModelObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS) ;
		final IObservableValue autoDepsModelObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__AUTOMATIC_DEPENDENCIES) ;
		final IObservableValue returnTypeModelObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE) ;
		final ISWTObservableValue observeText = SWTObservables.observeText(comparisonEditor.getViewer().getControl(),SWT.Modify);
		dataBindingContext.bindValue(observeText, contentModelObservable) ;
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
		UpdateValueStrategy targetToModel = new UpdateValueStrategy();
		targetToModel.setAfterConvertValidator(new IValidator() {

			@Override
			public IStatus validate(Object value) {
				if(value == null || value.toString().isEmpty()){
					return ValidationStatus.error(Messages.returnTypeIsMandatory);
				}
				return ValidationStatus.ok();
			}
		});
		ControlDecorationSupport.create(dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(typeCombo), returnTypeModelObservable,targetToModel,null),SWT.LEFT) ;
		typeCombo.getCombo().setEnabled(!inputExpression.isReturnTypeFixed()) ;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#canFinish()
	 */
	@Override
	public boolean canFinish() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#addListener(org.eclipse.swt.widgets.Listener)
	 */
	@Override
	public void addListener(Listener listener) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#getListeners()
	 */
	@Override
	public List<Listener> getListeners() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#provideDialogTray()
	 */
	@Override
	public boolean provideDialogTray() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#createDialogTray()
	 */
	@Override
	public DialogTray createDialogTray() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#getTextControl()
	 */
	@Override
	public Control getTextControl() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#getContentObservable()
	 */
	@Override
	public IObservable getContentObservable() {
		// TODO Auto-generated method stub
		return null;
	}

}
