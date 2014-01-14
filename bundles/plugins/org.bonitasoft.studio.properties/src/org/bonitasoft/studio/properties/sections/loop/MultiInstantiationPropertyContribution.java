/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.sections.loop;


import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.data.ui.property.section.DataLabelProvider;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.filter.DisplayEngineExpressionWithName;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.MultiInstantiation;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jdt.internal.core.search.JavaSearchScope;
import org.eclipse.jdt.internal.ui.dialogs.FilteredTypesSelectionDialog;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Mickael Istria
 * @author Aurelien Pupier
 *
 */
public class MultiInstantiationPropertyContribution implements IExtensibleGridPropertySectionContribution {

	/**
	 * @author Aurelien
	 * Display only Process data, it filters out step data. The step requires to be the context of the expressionviewer
	 */
	private final class StepDataViewerFilter extends ViewerFilter {
		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if(element instanceof Expression){
				if(ExpressionConstants.VARIABLE_TYPE.equals(((Expression) element).getType())){
					if(parentElement instanceof Activity){
						final String expressionName = ((Expression) element).getName();
						for (Data activityData : ((Activity) parentElement).getData()) {
							if(expressionName.equals(activityData.getName())){
								return false;
							}
						}
					}
				}
			}
			return true;
		}
	}

	protected static final String NONE = "none"; //$NON-NLS-1$
	protected static final String MULTI = "multi"; //$NON-NLS-1$
	protected static final String LOOP = "loop"; //$NON-NLS-1$

	private Activity activity;
	private TransactionalEditingDomain editingDomain;
	private Button multiRadio;
	private MultiInstantiation multiInstantiation;
	private Button loopRadio;
	private Composite parentLayer;
	private StackLayout stackLayout;
	private Composite multiInstanciatorComposite;
	private Composite loopComposite;
	private Button noneRadio;
	private Composite noneComposite;
	private Button testBefore;
	private ExpressionViewer loopConditionViewer;
	private ExpressionViewer maximumViewer;
	private Button testAfter;
	private EMFDataBindingContext context ;
	private Button useCardinalityButton;
	private ExpressionViewer cardinalityExpression;
	private Button useCollectionButton;
	private ComboViewer collectionDataChooser;
	private ComboViewer inputDataChooser;
	private ComboViewer outputDataChooser;
	private ExpressionViewer completionConditionViewer;
	private Button sequentialButton;
	private ComboViewer listOutputDataChooser;
	private ControlDecoration cdOutputData;
	private ControlDecoration cdlistOutputData;
	private Composite inputOutputDataComposite;
	private ControlDecoration cdInput;

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#createControl(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory, org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection)
	 */
	@Override
	public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, ExtensibleGridPropertySection extensibleGridPropertySection) {
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create());
		Composite radioComposite = widgetFactory.createComposite(composite, SWT.NONE);
		final RowLayout rowLayoutForFirstLineRadioButton = new RowLayout(SWT.HORIZONTAL);
		rowLayoutForFirstLineRadioButton.marginTop = 0;
		radioComposite.setLayout(rowLayoutForFirstLineRadioButton);

		createFirstLevelRadioButtons(widgetFactory, radioComposite);

		parentLayer = widgetFactory.createComposite(composite, SWT.NONE);
		parentLayer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		stackLayout = new StackLayout() ;
		parentLayer.setLayout(stackLayout);

		noneComposite =  widgetFactory.createComposite(parentLayer, SWT.NONE);

		multiInstanciatorComposite = widgetFactory.createComposite(parentLayer, SWT.NONE);
		multiInstanciatorComposite.setLayout(new GridLayout(1, false));

		loopComposite = widgetFactory.createComposite(parentLayer, SWT.NONE);
		loopComposite.setLayout(new GridLayout(1, false));
		loopComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

		createLoopParameterComposite(widgetFactory);

		createMultiInstanceParametersComposite(widgetFactory);

		if(activity.getIsLoop()){
			loopRadio.setSelection(true);
			updateStack(LOOP);
		}else if(activity.isIsMultiInstance()){
			multiRadio.setSelection(true);
			updateStack(MULTI);
			if(activity.getMultiInstantiation() != null && activity.getMultiInstantiation().isUseCardinality()){
				useCardinalityButton.setSelection(true);
			} else {
				useCollectionButton.setSelection(true);
			}
		}else{
			noneRadio.setSelection(true);
			updateStack(NONE);
		}

		refreshBinding();
	}


	protected void createMultiInstanceParametersComposite(TabbedPropertySheetWidgetFactory widgetFactory) {
		Composite parametersComposite = widgetFactory.createComposite(multiInstanciatorComposite, SWT.NONE);
		parametersComposite.setLayout(new GridLayout(3, false));
		parametersComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

		createCardinalityLine(widgetFactory, parametersComposite);

		createUseExpressionBlock(widgetFactory, parametersComposite);

		sequentialButton = widgetFactory.createButton(parametersComposite, Messages.multiInstance_sequentialButton, SWT.CHECK);
		sequentialButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());
		ControlDecoration cdsequential = new ControlDecoration(sequentialButton, SWT.RIGHT,parametersComposite);
		cdsequential.setDescriptionText(Messages.multiInstance_sequentialDescription);
		cdsequential.setImage(Pics.getImage(PicsConstants.hint));


		Label completionConditionLabel = widgetFactory.createLabel(parametersComposite, Messages.multiInstance_completionConditionLabel);
		completionConditionViewer = new ExpressionViewer(parametersComposite, SWT.BORDER, widgetFactory, ProcessPackage.Literals.MULTI_INSTANTIATION__COMPLETION_CONDITION);
		completionConditionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
		completionConditionViewer.addFilter(new AvailableExpressionTypeFilter(new String[]{ExpressionConstants.CONSTANT_TYPE,
				ExpressionConstants.VARIABLE_TYPE,
				ExpressionConstants.PARAMETER_TYPE,
				ExpressionConstants.SCRIPT_TYPE}));
		completionConditionViewer.addFilter(new DisplayEngineExpressionWithName(new String[]{
				org.bonitasoft.engine.expression.ExpressionConstants.NUMBER_OF_ACTIVE_INSTANCES.getEngineConstantName(),
				org.bonitasoft.engine.expression.ExpressionConstants.NUMBER_OF_COMPLETED_INSTANCES.getEngineConstantName(),
				org.bonitasoft.engine.expression.ExpressionConstants.NUMBER_OF_INSTANCES.getEngineConstantName(),
				org.bonitasoft.engine.expression.ExpressionConstants.NUMBER_OF_TERMINATED_INSTANCES.getEngineConstantName()}));
		ControlDecoration cdCompletionCondition = new ControlDecoration(  completionConditionLabel, SWT.RIGHT,parametersComposite);
		cdCompletionCondition.setDescriptionText(Messages.multiInstance_completionConditionDescription);
		cdCompletionCondition.setImage(Pics.getImage(PicsConstants.hint));
		cdCompletionCondition.setMarginWidth(5);


		// remove decorator if Collection multiInstance is not enabled
		if(useCollectionButton.getSelection()){
			cdsequential.show();
			cdCompletionCondition.show();
		}
	}


	protected void createUseExpressionBlock(TabbedPropertySheetWidgetFactory widgetFactory,	Composite parametersComposite) {

		SelectionListener inputDataAndListOutputDataListener = new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateInputDataDecorator();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		};

		ModifyListener inputDataAndListOutputDataModifyListener = new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				updateInputDataDecorator();

			}
		};

		useCollectionButton = widgetFactory.createButton(parametersComposite, Messages.multiInstance_useCollection, SWT.RADIO);
		ControlDecoration cdUseCollection = new ControlDecoration(useCollectionButton, SWT.RIGHT,parametersComposite);
		cdUseCollection.setDescriptionText(Messages.multiInstance_useCollectionDescription);
		cdUseCollection.setImage(Pics.getImage(PicsConstants.hint));
		collectionDataChooser = createChooser(widgetFactory, parametersComposite);
		collectionDataChooser.getCombo().addModifyListener(inputDataAndListOutputDataModifyListener);

		inputOutputDataComposite = widgetFactory.createComposite(parametersComposite);
		inputOutputDataComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(6).create());
		inputOutputDataComposite.setLayoutData(GridDataFactory.fillDefaults().span(3, 1).create());

		SelectionListener outputDataAndListOutputDataListener = new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				updateOutputDataDecorator();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		};

		ModifyListener outputDataAndListOutputDataModifyListener = new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				updateOutputDataDecorator();

			}
		};


		useCollectionButton.addSelectionListener(outputDataAndListOutputDataListener);
		useCollectionButton.addSelectionListener(inputDataAndListOutputDataListener);


		widgetFactory.createLabel(inputOutputDataComposite, Messages.multiInstance_inputData);
		inputDataChooser = createChooser(widgetFactory, inputOutputDataComposite);
		inputDataChooser.getCombo().addModifyListener(inputDataAndListOutputDataModifyListener);
		cdInput = new ControlDecoration(inputDataChooser.getControl(), SWT.LEFT,parametersComposite);
		cdInput.setImage(Pics.getImage(PicsConstants.hint));
		cdInput.setDescriptionText(Messages.multiInstance_inputDataDescription);
		widgetFactory.createLabel(inputOutputDataComposite, Messages.multiInstance_outputData);
		outputDataChooser = createChooser(widgetFactory, inputOutputDataComposite);
		outputDataChooser.getCombo().addModifyListener(outputDataAndListOutputDataModifyListener);
		cdOutputData = new ControlDecoration(outputDataChooser.getControl(), SWT.LEFT,parametersComposite);
		cdOutputData.setImage(Pics.getImage(PicsConstants.hint));
		cdOutputData.setDescriptionText(Messages.multiInstance_outputDataDescription);

		widgetFactory.createLabel(parametersComposite, Messages.multiInstance_listOutputDataLabel);
		listOutputDataChooser = createChooser(widgetFactory, parametersComposite);
		listOutputDataChooser.getCombo().addModifyListener(outputDataAndListOutputDataModifyListener);
		cdlistOutputData = new ControlDecoration(listOutputDataChooser.getControl(), SWT.LEFT,parametersComposite);
		cdlistOutputData.setImage(Pics.getImage(PicsConstants.hint));
		cdlistOutputData.setDescriptionText(Messages.multiInstance_listOutputDataDescription);

	}

	/**
	 * 
	 */
	protected void updateOutputDataDecorator() {
		if(useCollectionButton.getSelection() && !outputDataChooser.getCombo().getText().isEmpty() && listOutputDataChooser.getCombo().getText().isEmpty()){
			cdOutputData.setImage(Pics.getImage(PicsConstants.error));
			cdOutputData.setDescriptionText(Messages.errorOutputDataMultiInstanceCollection);
		}else{
			cdOutputData.setImage(Pics.getImage(PicsConstants.hint));
			cdOutputData.setDescriptionText(Messages.multiInstance_outputDataDescription);
		}
	}

	/**
	 * 
	 */
	protected void updateInputDataDecorator() {
		if(useCollectionButton.getSelection() && !inputDataChooser.getCombo().getText().isEmpty() && collectionDataChooser.getCombo().getText().isEmpty()){
			cdInput.setImage(Pics.getImage(PicsConstants.error));
			cdInput.setDescriptionText(Messages.errorInputDataMultiInstanceCollection);
		}else{
			cdInput.setImage(Pics.getImage(PicsConstants.hint));
			cdInput.setDescriptionText(Messages.multiInstance_inputDataDescription);
		}
	}

	protected void createCardinalityLine(TabbedPropertySheetWidgetFactory widgetFactory, Composite parametersComposite) {
		useCardinalityButton = widgetFactory.createButton(parametersComposite, Messages.multiInstance_useCardinality, SWT.RADIO);
		ControlDecoration cd = new ControlDecoration(useCardinalityButton, SWT.RIGHT,parametersComposite);
		cd.setDescriptionText(Messages.multiInstance_useCardinalityDescription);
		cd.setImage(Pics.getImage(PicsConstants.hint));
		cardinalityExpression = new ExpressionViewer(parametersComposite, SWT.BORDER, widgetFactory, ProcessPackage.Literals.MULTI_INSTANTIATION__CARDINALITY);
		cardinalityExpression.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		cardinalityExpression.addFilter(new AvailableExpressionTypeFilter(new String[]{ExpressionConstants.CONSTANT_TYPE,
				ExpressionConstants.VARIABLE_TYPE,
				ExpressionConstants.PARAMETER_TYPE,
				ExpressionConstants.SCRIPT_TYPE}));
		cardinalityExpression.getControl().setLayoutData(GridDataFactory.fillDefaults().span(2, 1).create());
		// cardinalityExpression.setMessage(Messages.multiInstance_useCardinalityDescription, IStatus.INFO);
	}


	protected ComboViewer createChooser(TabbedPropertySheetWidgetFactory widgetFactory, Composite inputOutputDataComposite) {
		final ComboViewer comboViewer = new ComboViewer(new Combo(inputOutputDataComposite, SWT.BORDER | SWT.READ_ONLY));
		comboViewer.setContentProvider(new ObservableListContentProvider());
		comboViewer.setLabelProvider(new DataLabelProvider());
		comboViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(5, 0).create());
		ToolBar toolBar = new ToolBar(inputOutputDataComposite, SWT.FLAT);
		widgetFactory.adapt(toolBar);
		ToolItem toolItem = new ToolItem(toolBar, SWT.FLAT);
		toolItem.setImage(Pics.getImage(PicsConstants.clear));
		toolItem.setToolTipText(Messages.clearSelection);
		toolItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				comboViewer.setSelection(new StructuredSelection());
			}
		});
		return comboViewer;
	}


	protected void createLoopParameterComposite(TabbedPropertySheetWidgetFactory widgetFactory) {
		Composite loopParametersComposite = widgetFactory.createComposite(loopComposite, SWT.NONE);
		loopParametersComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(20, 10).create());
		loopParametersComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;

		new Label(loopParametersComposite, SWT.NONE); //FILLER

		testAfter = widgetFactory.createButton(loopParametersComposite, Messages.testAfterLabel, SWT.RADIO);
		testAfter.setLayoutData(GridDataFactory.swtDefaults().create());
		testAfter.setSelection(!activity.getTestBefore());

		testAfter.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				testBefore.setSelection(!testAfter.getSelection()) ;
				editingDomain.getCommandStack().execute(
						new SetCommand(editingDomain, activity, ProcessPackage.Literals.ACTIVITY__TEST_BEFORE, !testAfter.getSelection()));
			}
		});

		testBefore = widgetFactory.createButton(loopParametersComposite, Messages.testBeforeLabel, SWT.RADIO);
		testBefore.setLayoutData(GridDataFactory.swtDefaults().create());
		testBefore.setSelection(activity.getTestBefore());

		testBefore.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				testAfter.setSelection(!testBefore.getSelection()) ;
				editingDomain.getCommandStack().execute(
						new SetCommand(editingDomain, activity, ProcessPackage.Literals.ACTIVITY__TEST_BEFORE, testBefore.getSelection()));
			}
		});



		widgetFactory.createLabel(loopParametersComposite, Messages.loopConditionLabel).setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
		loopConditionViewer = new ExpressionViewer(loopParametersComposite,SWT.BORDER,widgetFactory,editingDomain, ProcessPackage.Literals.ACTIVITY__LOOP_CONDITION);
		loopConditionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true,false).span(2, 1).create());
		loopConditionViewer.addFilter(new AvailableExpressionTypeFilter(new String[]{ExpressionConstants.CONSTANT_TYPE,
				ExpressionConstants.VARIABLE_TYPE,
				ExpressionConstants.PARAMETER_TYPE,
				ExpressionConstants.SCRIPT_TYPE}));
		loopConditionViewer.addFilter(new DisplayEngineExpressionWithName(new String[]{org.bonitasoft.engine.expression.ExpressionConstants.LOOP_COUNTER.getEngineConstantName()}));
		loopConditionViewer.addFilter(new StepDataViewerFilter());

		widgetFactory.createLabel(loopParametersComposite, Messages.maximumLoopLabel).setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
		maximumViewer = new ExpressionViewer(loopParametersComposite,SWT.BORDER,widgetFactory,editingDomain, ProcessPackage.Literals.ACTIVITY__LOOP_MAXIMUM);
		maximumViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true,false).span(2, 1).create());
		maximumViewer.addFilter(new AvailableExpressionTypeFilter(new String[]{ExpressionConstants.CONSTANT_TYPE,
				ExpressionConstants.VARIABLE_TYPE,
				ExpressionConstants.PARAMETER_TYPE,
				ExpressionConstants.SCRIPT_TYPE}));
		maximumViewer.setMessage(Messages.optionalLabel,IStatus.INFO) ;
		maximumViewer.addFilter(new StepDataViewerFilter());
	}


	protected void createFirstLevelRadioButtons(TabbedPropertySheetWidgetFactory widgetFactory,	Composite radioComposite) {
		noneRadio = widgetFactory.createButton(radioComposite,Messages.noneLabel , SWT.RADIO);
		noneRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				if(noneRadio.getSelection()){
					loopRadio.setSelection(false);
					multiRadio.setSelection(false);
					updateStack(NONE);
				}
			}
		});

		multiRadio = widgetFactory.createButton(radioComposite, Messages.isMultiInstantiated, SWT.RADIO);
		multiRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				if(multiRadio.getSelection()){
					loopRadio.setSelection(false);
					noneRadio.setSelection(false);
					updateStack(MULTI);
				}
			}
		});

		loopRadio = widgetFactory.createButton(radioComposite, Messages.isLoopLabel, SWT.RADIO);
		loopRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				if(loopRadio.getSelection()){
					multiRadio.setSelection(false);
					noneRadio.setSelection(false);
					updateStack(LOOP);
				}
			}
		});
	}

	protected void updateStack(String type) {
		if(type.equals(NONE)){
			stackLayout.topControl = noneComposite ;
		}else if(type.equals(MULTI)){
			stackLayout.topControl = multiInstanciatorComposite ;
		}else if(type.equals(LOOP)){
			stackLayout.topControl = loopComposite ;
		}
		parentLayer.layout();
	}


	protected void initMultiInstanciationModelIfNeeded() {
		if (multiInstantiation == null) {
			MultiInstantiation res = ProcessFactory.eINSTANCE.createMultiInstantiation();
			final Expression cardinality = ExpressionFactory.eINSTANCE.createExpression();
			cardinality.setReturnType(Integer.class.getName());
			cardinality.setReturnTypeFixed(true);
			res.setCardinality(cardinality);
			res.setUseCardinality(true);
			Command cmd = new SetCommand(editingDomain, activity, ProcessPackage.Literals.ACTIVITY__MULTI_INSTANTIATION, res);
			if(editingDomain != null){
				editingDomain.getCommandStack().execute(cmd);
			} else {
				/*a stack has been found where the editing domain was null (but don't know why and can't reproduce)
				 * so try to create our own editing domain*/
				TransactionalEditingDomain domain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain();
				domain.getCommandStack().execute(cmd);
			}
			multiInstantiation = res;
		}
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#dispose()
	 */
	@Override
	public void dispose() {
		if(context != null){
			context.dispose();
		}
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#getLabel()
	 */
	@Override
	public String getLabel() {
		return "";
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#isRelevantFor(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public boolean isRelevantFor(EObject eObject) {
		return eObject instanceof Activity;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#refresh()
	 */
	@Override
	public void refresh() {
		if(inputDataChooser != null && !inputDataChooser.getCombo().isDisposed()){
			inputDataChooser.refresh();
		}
		if(outputDataChooser != null && !outputDataChooser.getCombo().isDisposed()){
			outputDataChooser.refresh();
		}
		if(collectionDataChooser != null && !collectionDataChooser.getCombo().isDisposed()){
			collectionDataChooser.refresh();
		}
		if(listOutputDataChooser != null && !collectionDataChooser.getCombo().isDisposed()){
			collectionDataChooser.refresh();
		}
	}

	protected void refreshBinding(){
		if(context != null){
			context.dispose();
		}
		context = new EMFDataBindingContext();
		Expression condition =  activity.getLoopCondition() ;
		if(condition == null){
			condition = ExpressionFactory.eINSTANCE.createExpression() ;
			condition.setReturnTypeFixed(true);
			condition.setReturnType(Boolean.class.getName());
			editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, activity, ProcessPackage.Literals.ACTIVITY__LOOP_CONDITION, condition)) ;
		}
		loopConditionViewer.setEditingDomain(editingDomain) ;
		loopConditionViewer.setInput(activity) ;

		loopConditionViewer.setSelection(new StructuredSelection(condition)) ;

		Expression max =  activity.getLoopMaximum() ;
		if(max == null){
			max = ExpressionFactory.eINSTANCE.createExpression() ;
			max.setReturnTypeFixed(true);
			max.setReturnType(Integer.class.getName());
			editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, activity, ProcessPackage.Literals.ACTIVITY__LOOP_MAXIMUM, max)) ;
		}
		maximumViewer.setEditingDomain(editingDomain) ;
		maximumViewer.setInput(activity) ;
		maximumViewer.setSelection(new StructuredSelection(max)) ;


		context.bindValue(WidgetProperties.selection().observe(loopRadio),
				EMFEditProperties.value(editingDomain, ProcessPackage.Literals.ACTIVITY__IS_LOOP).observe(activity));


		bindMultiInstanciationParameters();
	}


	protected void bindMultiInstanciationParameters() {
		multiInstantiation = activity.getMultiInstantiation();
		initMultiInstanciationModelIfNeeded();

		context.bindValue(WidgetProperties.selection().observe(multiRadio),
				EMFEditProperties.value(editingDomain, ProcessPackage.Literals.ACTIVITY__IS_MULTI_INSTANCE).observe(activity));

		bindCardinality();
		bindCompletionCondition();

		context.bindValue(WidgetProperties.selection().observe(sequentialButton),
				EMFEditProperties.value(editingDomain, ProcessPackage.Literals.MULTI_INSTANTIATION__SEQUENTIAL).observe(multiInstantiation));



		/*Collection Data Chooser*/
		collectionDataChooser.setInput(new WritableList(ModelHelper.getAccessibleData(ModelHelper.getParentProcess(activity)), Data.class));
		context.bindValue(ViewersObservables.observeSingleSelection(collectionDataChooser),EMFEditObservables.observeValue(editingDomain,multiInstantiation, ProcessPackage.Literals.MULTI_INSTANTIATION__COLLECTION_DATA_TO_MULTI_INSTANTIATE));


		listOutputDataChooser.setInput(new WritableList(ModelHelper.getAccessibleData(ModelHelper.getParentProcess(activity)), Data.class));
		/*List for output data*/
		context.bindValue(
				ViewerProperties.singleSelection().observe(listOutputDataChooser),
				EMFEditProperties.value(editingDomain, ProcessPackage.Literals.MULTI_INSTANTIATION__LIST_DATA_CONTAINING_OUTPUT_RESULTS).observe(multiInstantiation));


		/*Input Data Chooser*/
		inputDataChooser.setInput(EMFObservables.observeList(activity, ProcessPackage.Literals.DATA_AWARE__DATA));
		context.bindValue(
				ViewerProperties.singleSelection().observe(inputDataChooser),
				EMFEditProperties.value(editingDomain, ProcessPackage.Literals.MULTI_INSTANTIATION__INPUT_DATA).observe(multiInstantiation));



		/*Output Data Chooser*/
		outputDataChooser.setInput(EMFObservables.observeList(activity, ProcessPackage.Literals.DATA_AWARE__DATA));
		context.bindValue(
				ViewerProperties.singleSelection().observe(outputDataChooser),
				EMFEditProperties.value(editingDomain, ProcessPackage.Literals.MULTI_INSTANTIATION__OUTPUT_DATA).observe(multiInstantiation));


		bindFieldsForEnablement();
	}


	protected void bindCompletionCondition() {
		context.bindValue(ViewerProperties.singleSelection().observe(completionConditionViewer),
				EMFEditProperties.value(editingDomain, ProcessPackage.Literals.MULTI_INSTANTIATION__COMPLETION_CONDITION).observe(multiInstantiation));
		Expression completionCondition = multiInstantiation.getCompletionCondition();
		if(completionCondition == null){
			completionCondition = ExpressionFactory.eINSTANCE.createExpression();
			completionCondition.setReturnType(Boolean.class.getName());
			completionCondition.setReturnTypeFixed(true);
		}
		completionConditionViewer.setContext(activity);
		completionConditionViewer.setInput(multiInstantiation);
		completionConditionViewer.setSelection(new StructuredSelection(completionCondition));
	}


	protected void bindCardinality() {
		context.bindValue(WidgetProperties.selection().observe(useCardinalityButton),
				EMFEditProperties.value(editingDomain, ProcessPackage.Literals.MULTI_INSTANTIATION__USE_CARDINALITY).observe(multiInstantiation));
		context.bindValue(ViewerProperties.singleSelection().observe(cardinalityExpression),
				EMFEditProperties.value(editingDomain, ProcessPackage.Literals.MULTI_INSTANTIATION__CARDINALITY).observe(multiInstantiation));

		Expression cardinality = multiInstantiation.getCardinality();
		if(cardinality == null){
			cardinality = ExpressionFactory.eINSTANCE.createExpression();
			cardinality.setReturnType(Integer.class.getName());
			cardinality.setReturnTypeFixed(true);
		}
		cardinalityExpression.setInput(multiInstantiation);
		cardinalityExpression.setSelection(new StructuredSelection(cardinality));
	}


	protected void bindFieldsForEnablement() {
		context.bindValue(
				WidgetProperties.enabled().observe(cardinalityExpression.getTextControl()),
				WidgetProperties.selection().observe(useCardinalityButton));
		context.bindValue(
				WidgetProperties.enabled().observe(cardinalityExpression.getButtonControl()),
				WidgetProperties.selection().observe(useCardinalityButton));

		context.bindValue(
				WidgetProperties.enabled().observe(collectionDataChooser.getControl()),
				WidgetProperties.selection().observe(useCollectionButton));
		context.bindValue(
				WidgetProperties.enabled().observe(inputDataChooser.getControl()),
				WidgetProperties.selection().observe(useCollectionButton));
		context.bindValue(
				WidgetProperties.enabled().observe(outputDataChooser.getControl()),
				WidgetProperties.selection().observe(useCollectionButton));
		context.bindValue(
				WidgetProperties.enabled().observe(listOutputDataChooser.getControl()),
				WidgetProperties.selection().observe(useCollectionButton));
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#setEObject(org.eclipse.emf.ecore.EObject)
	 */
	 @Override
	 public void setEObject(EObject object) {
		activity = (Activity)object;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#setEditingDomain(org.eclipse.emf.transaction.TransactionalEditingDomain)
	 */
	 @Override
	 public void setEditingDomain(TransactionalEditingDomain editingDomain) {
		 this.editingDomain = editingDomain;
	 }

	 /* (non-Javadoc)
	  * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#setSelection(org.eclipse.jface.viewers.ISelection)
	  */
	 @Override
	 public void setSelection(ISelection selection) {
	 }

	 /**
	  * @param instantiatorName
	  * @return
	  */
	 @SuppressWarnings("restriction")
	 public static FilteredTypesSelectionDialog createSearchDialog(final Shell shell) {
		 try {
			 JavaSearchScope scope = new JavaSearchScope();
			 //            scope.add( ExtensionProjectUtil.getJavaProject() );
			 //            final IType multiInstantiationType = ExtensionProjectUtil.getJavaProject().findType(MultiInstantiator.class.getName());
			 //            TypeSelectionExtension filterExtension = new TypeSelectionExtension() {
			 //
			 //                @Override
			 //                public ITypeInfoFilterExtension getFilterExtension() {
			 //                    return new ITypeInfoFilterExtension() {
			 //                        public boolean select(ITypeInfoRequestor typeInfoRequestor) {
			 //                            try {
			 //                                IType type = ExtensionProjectUtil.getJavaProject().findType(typeInfoRequestor.getPackageName() + "." + typeInfoRequestor.getTypeName()); //$NON-NLS-1$
			 //                                if (type == null) {
			 //                                    return false;
			 //                                } else {
			 //                                    ITypeHierarchy hierarchy = type.newSupertypeHierarchy(new NullProgressMonitor());
			 //                                    return hierarchy.contains(multiInstantiationType);
			 //                                }
			 //                            } catch (Exception ex) {
			 //                                BonitaStudioLog.log(ex);
			 //                                return false;
			 //                            }
			 //                        }
			 //                    };
			 //                }
			 //            };
			 FilteredTypesSelectionDialog searchDialog = null ;// new FilteredTypesSelectionDialog(shell, false, null, scope, IJavaSearchConstants.CLASS, filterExtension);
			 return searchDialog;
		 } catch (Exception ex) {
			 BonitaStudioLog.error(ex);
			 return null;
		 }
	 }

}
