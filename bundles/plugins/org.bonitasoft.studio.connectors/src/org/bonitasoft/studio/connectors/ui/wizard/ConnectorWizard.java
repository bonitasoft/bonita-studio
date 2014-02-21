/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.ui.wizard;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.IBonitaVariableContext;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.jface.ExtensibleWizard;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.Array;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.studio.connector.model.definition.IConnectorDefinitionContainer;
import org.bonitasoft.studio.connector.model.definition.IDefinitionRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.connector.model.definition.Page;
import org.bonitasoft.studio.connector.model.definition.ScriptEditor;
import org.bonitasoft.studio.connector.model.definition.TextArea;
import org.bonitasoft.studio.connector.model.definition.WidgetComponent;
import org.bonitasoft.studio.connector.model.definition.wizard.AbstractConnectorConfigurationWizardPage;
import org.bonitasoft.studio.connector.model.definition.wizard.GeneratedConnectorWizardPage;
import org.bonitasoft.studio.connector.model.definition.wizard.SelectNameAndDescWizardPage;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.wizard.AbstractDefinitionSelectionImpementationWizardPage;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.extension.CustomWizardExtension;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.ui.wizard.page.AbstractConnectorOutputWizardPage;
import org.bonitasoft.studio.connectors.ui.wizard.page.ConnectorOutputWizardPage;
import org.bonitasoft.studio.connectors.ui.wizard.page.DatabaseConnectorConstants;
import org.bonitasoft.studio.connectors.ui.wizard.page.DatabaseConnectorDriversWizardPage;
import org.bonitasoft.studio.connectors.ui.wizard.page.DatabaseConnectorOutputWizardPage;
import org.bonitasoft.studio.connectors.ui.wizard.page.SelectAdvancedConnectorDefinitionWizardPage;
import org.bonitasoft.studio.connectors.ui.wizard.page.SelectDatabaseOutputTypeWizardPage;
import org.bonitasoft.studio.connectors.ui.wizard.page.SelectEventConnectorNameAndDescWizardPage;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationFactory;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.AbstractExpression;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.SubmitFormButton;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 *
 */
public class ConnectorWizard extends ExtensibleWizard implements IConnectorDefinitionContainer,IBonitaVariableContext {

	private static final String CUSTOM_WIZARD_ID = "org.bonitasoft.studio.connectors.connectorWizard";
	private static final String DATABASE_ID ="database";
	private static final String DATASOURCE_CONNECTOR_D = "database-datasource";

	protected final EObject container;
	protected Connector connectorWorkingCopy;
	private boolean editMode = false;
	protected Connector originalConnector;
	protected final Set<EStructuralFeature> featureToCheckForUniqueID ;
	protected final EStructuralFeature connectorContainmentFeature;
	protected AbstractDefinitionSelectionImpementationWizardPage selectionPage;
	private SelectNameAndDescWizardPage namePage;
	protected DefinitionResourceProvider messageProvider;
	protected CustomWizardExtension extension;
	private boolean isPageFlowContext = false;
	private List<CustomWizardExtension> contributions;

	private boolean useEvents = true;
	private AvailableExpressionTypeFilter expressionTypeFilter = new AvailableExpressionTypeFilter(new String[]{
			ExpressionConstants.CONSTANT_TYPE,
			ExpressionConstants.VARIABLE_TYPE,
			ExpressionConstants.SCRIPT_TYPE,
			ExpressionConstants.PARAMETER_TYPE
	}) ;
	private AvailableExpressionTypeFilter formExpressionTypeFilter = new AvailableExpressionTypeFilter(new String[]{
			ExpressionConstants.CONSTANT_TYPE,
			ExpressionConstants.VARIABLE_TYPE,
			ExpressionConstants.SCRIPT_TYPE,
			ExpressionConstants.PARAMETER_TYPE,
			ExpressionConstants.FORM_FIELD_TYPE
	}) ;
	protected List<ConnectorDefinition> definitions;

	public ConnectorWizard(EObject container,EStructuralFeature connectorContainmentFeature ,Set<EStructuralFeature> featureToCheckForUniqueID){
		this.container = container ;
		connectorWorkingCopy = ProcessFactory.eINSTANCE.createConnector() ;
		connectorWorkingCopy.setConfiguration(ConnectorConfigurationFactory.eINSTANCE.createConnectorConfiguration()) ;
		editMode = false ;
		this.connectorContainmentFeature = connectorContainmentFeature ;
		this.featureToCheckForUniqueID = new HashSet<EStructuralFeature>();
		this.featureToCheckForUniqueID.add(connectorContainmentFeature);
		setWindowTitle(Messages.connectors);
		setNeedsProgressMonitor(false);
		initialize() ;
	}

	public ConnectorWizard(Connector connector, EStructuralFeature connectorContainmentFeature ,Set<EStructuralFeature> featureToCheckForUniqueID){
		Assert.isNotNull(connector) ;
		container = connector.eContainer() ;
		originalConnector = connector ;
		this.connectorContainmentFeature = connectorContainmentFeature ;
		connectorWorkingCopy = EcoreUtil.copy(connector) ;
		editMode = true ;
		this.featureToCheckForUniqueID = featureToCheckForUniqueID ;
		setNeedsProgressMonitor(false);
		initialize() ;
		ConnectorDefinition def = getDefinition();
		DefinitionResourceProvider resourceProvider = initMessageProvider();
		String connectorDefinitionLabel = resourceProvider.getConnectorDefinitionLabel(def);
		if(connectorDefinitionLabel == null){
			connectorDefinitionLabel = def.getId();
		}
		setWindowTitle(connectorDefinitionLabel +" ("+def.getVersion()+")");
	}


	protected void setEditMode(boolean isEdit) {
		editMode = isEdit;
	}

	protected void initialize() {
		setDefaultPageImageDescriptor(Pics.getWizban()) ;
		setNeedsProgressMonitor(true) ;
		messageProvider = initMessageProvider();

		initializeContainment();

		contributions = new ArrayList<CustomWizardExtension>() ;
		for(IConfigurationElement element :  BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(CUSTOM_WIZARD_ID)){
			contributions.add(new CustomWizardExtension(element));
		}
		IDefinitionRepositoryStore defStore = getDefinitionStore();
		definitions = defStore.getDefinitions();
	}

	protected void initializeContainment() {
		if(container instanceof Element){
			AbstractProcess process =  ModelHelper.getParentProcess(container) ;
			EObject processCopy = EcoreUtil.copy(process) ;
			EObject containerCopy = null ;
			for(EObject element : ModelHelper.getAllItemsOfType(processCopy, container.eClass())){
				if(element instanceof Element && container instanceof Element){
					final String containerName = ((Element)container).getName();
					if(((Element) element).getName().equals(containerName)){
						containerCopy = element ;
						break ;
					}
				}
			}


			@SuppressWarnings("unchecked")
			List<EObject> connectors = (List<EObject>) containerCopy.eGet(connectorContainmentFeature) ;
			connectors.clear();
			connectors.add(connectorWorkingCopy) ;
		}
	}

	protected DefinitionResourceProvider initMessageProvider() {
		IRepositoryStore<? extends IRepositoryFileStore> store =RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class) ;
		return DefinitionResourceProvider.getInstance(store, ConnectorPlugin.getDefault().getBundle()) ;
	}

	@Override
	public void addPages() {
		if(!editMode){
			selectionPage = getSelectionPage(connectorWorkingCopy,messageProvider) ;
			addPage(selectionPage) ;
		}
		addNameAndDescriptionPage();
		if(editMode){
			final IDefinitionRepositoryStore definitionStore = getDefinitionStore();
			final ConnectorDefinition definition =  definitionStore.getDefinition(connectorWorkingCopy.getDefinitionId(),connectorWorkingCopy.getDefinitionVersion(),definitions) ;
			if(cleanConfiguration(definition)){
				MessageDialog.openWarning(Display.getDefault().getActiveShell(), Messages.configurationChangedTitle, Messages.configurationChangedMsg);
			}
			extension = findCustomWizardExtension(definition) ;
			List<IWizardPage> pages = getPagesFor(definition) ;
			for(IWizardPage p : pages){
				addAdditionalPage(p) ;
			}
			addOuputPage(definition) ;
		}

	}

	/**
	 * 
	 * @param definition
	 * @return true if configuration has been modified
	 */
	protected boolean cleanConfiguration(ConnectorDefinition definition) {
		ConnectorConfiguration configuration = connectorWorkingCopy.getConfiguration();
		boolean changed = false;
		if(configuration != null){
			EList<Input> inputs = definition.getInput();
			Set<String> inputNames = new HashSet<String>(); 
			for(Input in : inputs){
				inputNames.add(in.getName());
			}
			Set<String> connectorParamKey = new HashSet<String>(); 
			for(ConnectorParameter parameter : configuration.getParameters()){
				connectorParamKey.add(parameter.getKey());
			}

			if(!inputNames.equals(connectorParamKey)){
				connectorParamKey.removeAll(inputNames);
				List<ConnectorParameter> toRemove = new ArrayList<ConnectorParameter>();
				for(ConnectorParameter parameter : configuration.getParameters()){
					if(connectorParamKey.contains(parameter.getKey())){
						toRemove.add(parameter);
					}
				}
				if(!toRemove.isEmpty()){
					changed = configuration.getParameters().removeAll(toRemove);
				}
			}
		}
		EList<Output> outputs = definition.getOutput();
		Set<String> outputNames = new HashSet<String>(); 
		for(Output out : outputs){
			outputNames.add(out.getName());
		}
		for(Operation op : connectorWorkingCopy.getOutputs()){
			if( ExpressionConstants.CONNECTOR_OUTPUT_TYPE.equals(op.getRightOperand().getType()) 
					&& op.getRightOperand() != null 
					&& op.getRightOperand().getContent() != null 
					&& !outputNames.contains(op.getRightOperand().getContent())){
				op.setRightOperand(ExpressionHelper.createConstantExpression("", String.class.getName()));
				changed = true;
			}
		}
		return changed;
	}

	protected AbstractDefinitionSelectionImpementationWizardPage getSelectionPage(final Connector connectorWorkingCopy, DefinitionResourceProvider resourceProvider) {
		return new SelectAdvancedConnectorDefinitionWizardPage(connectorWorkingCopy, Collections.<ConnectorImplementation>emptyList(), definitions,  Messages.selectConnectorDefinitionTitle, Messages.selectConnectorDefinitionDesc, resourceProvider);
	}

	protected void addNameAndDescriptionPage() {
		if(useEvents ){
			namePage = new SelectEventConnectorNameAndDescWizardPage(container, connectorWorkingCopy,originalConnector, featureToCheckForUniqueID) ;
		}else{
			namePage = new SelectNameAndDescWizardPage(container, connectorWorkingCopy,originalConnector, featureToCheckForUniqueID) ;
		}
		addPage(namePage) ;
	}

	protected void setUseEvents(boolean useEvents) {
		this.useEvents = useEvents;
	}

	protected IDefinitionRepositoryStore getDefinitionStore() {
		return (IDefinitionRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
	}


	protected void addOuputPage(ConnectorDefinition definition) {
		final IWizardPage outputPage = getOutputPageFor(definition) ;
		if(outputPage != null){
			addAdditionalPage(outputPage) ;
		}
	}

	protected IWizardPage getOutputPageFor(ConnectorDefinition definition) {
		AbstractConnectorOutputWizardPage outputPage = null ;

		if(!definition.getOutput().isEmpty()){
			if(!editMode){
				if(!supportsDatabaseOutputMode(getDefinition())){
					if(connectorWorkingCopy.getOutputs().isEmpty()){ //Add default output
						createDefaultOutputs(definition) ;
					}
				}
			}
			if(extension != null && !extension.useDefaultOutputPage()){
				outputPage = extension.getOutputPage() ;
			}else{	
				if(supportsDatabaseOutputMode(definition)){
					outputPage = new DatabaseConnectorOutputWizardPage() ;
				}else{
					outputPage = new ConnectorOutputWizardPage() ;
				}

			}
			outputPage.setElementContainer(container) ;
			outputPage.setConnector(connectorWorkingCopy) ;
			outputPage.setDefinition(definition) ;
		}
		return outputPage;
	}

	protected boolean hasOutputPage(){
		return (extension != null && !extension.useDefaultOutputPage() && extension.getOutputPage() != null) || (!getDefinition().getOutput().isEmpty());
	}

	protected void createDefaultOutputs(ConnectorDefinition definition) {
		connectorWorkingCopy.getOutputs().clear();
		for(Output output : definition.getOutput()){
			Operation operation = ExpressionFactory.eINSTANCE.createOperation() ;
			Operator assignment = ExpressionFactory.eINSTANCE.createOperator() ;
			assignment.setType(ExpressionConstants.ASSIGNMENT_OPERATOR) ;
			operation.setOperator(assignment) ;

			Expression rightOperand = ExpressionFactory.eINSTANCE.createExpression() ;
			rightOperand.setName(output.getName()) ;
			rightOperand.setContent(output.getName()) ;
			rightOperand.setReturnType(output.getType()) ;
			rightOperand.setType(ExpressionConstants.CONNECTOR_OUTPUT_TYPE) ;
			rightOperand.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(output)) ;
			operation.setRightOperand(rightOperand) ;

			Expression leftOperand = ExpressionFactory.eINSTANCE.createExpression() ;
			operation.setLeftOperand(leftOperand) ;

			connectorWorkingCopy.getOutputs().add(operation) ;
		}
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if(page.equals(selectionPage)){
			final ConnectorDefinition definition = selectionPage.getSelectedConnectorDefinition();
			if(definition!=null){
				checkDefinitionDependencies(definition) ;
				extension = findCustomWizardExtension(definition) ;
				recreateConnectorConfigurationPages(definition,true);
			}
		}
		return super.getNextPage(page);
	}

	protected void checkDefinitionDependencies(final ConnectorDefinition definition) {
		if(!definition.getJarDependency().isEmpty()){
			try {
				getContainer().run(true, false, new IRunnableWithProgress() {

					@Override
					public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
						monitor.beginTask(Messages.addingDefinitionDependencies, IProgressMonitor.UNKNOWN) ;
						DependencyRepositoryStore depStore = (DependencyRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class) ;
						for(String jarName : definition.getJarDependency()){
							if( depStore.getChild(jarName) == null){
								InputStream is = messageProvider.getDependencyInputStream(jarName) ;
								if(is != null){
									depStore.importInputStream(jarName, is) ;
								}
							}
						}
					}
				}) ;
			} catch (Exception e){
				BonitaStudioLog.error(e) ;
			}
		}
	}

	protected CustomWizardExtension findCustomWizardExtension(ConnectorDefinition definition) {
		int priority = 0 ;
		CustomWizardExtension result = null ;
		for(CustomWizardExtension ext : contributions){
			if(ext.appliesTo(definition) && ext.getPriority() > priority ){
				result = ext ;
				priority = ext.getPriority() ;
			}
		}
		return result;
	}

	@Override
	public void recreateConnectorConfigurationPages(final ConnectorDefinition definition, boolean clearConfiguration) {
		if(clearConfiguration){
			clearConnectorConfiguration(definition) ;
		}
		List<IWizardPage> pages = getPagesFor(definition) ;

		//Remove already generated page in case of return
		removeAllAdditionalPages() ;
		for(IWizardPage p : pages){
			addAdditionalPage(p) ; //Additional pages control will be created lazily by the WizardContainer
		}
		addOuputPage(definition) ;
		initializeEmptyConnectorConfiguration(definition);
	}

	private void initializeEmptyConnectorConfiguration(ConnectorDefinition definition) {
		final ConnectorConfiguration configuration = connectorWorkingCopy.getConfiguration();
		for(Input input : definition.getInput()){
			if(getConnectorParameter(configuration, input.getName()) == null){
				final ConnectorParameter param = ConnectorConfigurationFactory.eINSTANCE.createConnectorParameter() ;
				param.setKey(input.getName()) ;
				param.setExpression(createExpression(definition, input));
				configuration.getParameters().add(param);
			}
		}
	}

	protected AbstractExpression createExpression(ConnectorDefinition definition,Input input) {
		String inputClassName = input.getType() ;
		WidgetComponent widget = null;
		List<WidgetComponent> widgets = ModelHelper.getAllItemsOfType(definition,ConnectorDefinitionPackage.Literals.WIDGET_COMPONENT);
		for(WidgetComponent w : widgets){
			if(w.getInputName().equals(input.getName())){
				widget = w;
				break;
			}
		}
		if( widget instanceof Array){
			final TableExpression expression = ExpressionFactory.eINSTANCE.createTableExpression() ;
			return expression ;
		}else if( widget instanceof org.bonitasoft.studio.connector.model.definition.List){
			final ListExpression expression = ExpressionFactory.eINSTANCE.createListExpression() ;
			return expression ;
		}else if (widget != null){
			final Expression expression = ExpressionFactory.eINSTANCE.createExpression() ;
			expression.setReturnType(inputClassName) ;
			expression.setReturnTypeFixed(true) ;
			expression.setType(ExpressionConstants.CONSTANT_TYPE) ;
			expression.setName(input.getDefaultValue()) ;
			expression.setContent(input.getDefaultValue()) ;
			if(widget instanceof ScriptEditor){
				expression.setType(ExpressionConstants.SCRIPT_TYPE);
				expression.setInterpreter(((ScriptEditor) widget).getInterpreter());
			}else if(widget instanceof TextArea){
				expression.setType(ExpressionConstants.PATTERN_TYPE);
			}
			return expression ;
		}
		return null;
	}

	private ConnectorParameter getConnectorParameter(ConnectorConfiguration configuration, String inputName) {
		for(ConnectorParameter param : configuration.getParameters()){
			if(param.getKey().equals(inputName)){
				return param ;
			}
		}
		return null;
	}

	@Override
	public boolean canFinish() {
		if(extension != null && extension.hasCanFinishProvider()){
			if(connectorWorkingCopy.getConfiguration() != null){
				return extension.canFinish(connectorWorkingCopy.getConfiguration()) ;
			}
		}
		if(!isConfigurationValid(getDefinition(),connectorWorkingCopy.getConfiguration())){
			return false;
		}
		if(supportsDatabaseOutputMode(getDefinition())){
			return  super.canFinish() && !connectorWorkingCopy.getOutputs().isEmpty();
		}

		return super.canFinish();
	}

	private boolean isConfigurationValid(ConnectorDefinition def,ConnectorConfiguration configuration) {
		if(def == null){
			return false;
		}
		for(ConnectorParameter parameter : configuration.getParameters()){
			Input input = getConnectorInput(def,parameter.getKey());
			if(input != null && input.isMandatory()){
				if(expressionIsEmpty(parameter.getExpression())){
					return false;
				}
			}
		}
		return true;
	}

	private boolean expressionIsEmpty(AbstractExpression expression) {
		if(expression == null){
			return true;
		}
		if(expression instanceof Expression){
			return ((Expression) expression).getContent() == null || ((Expression) expression).getContent().isEmpty();
		}else if(expression instanceof TableExpression){
			return ((TableExpression) expression).getExpressions().isEmpty();
		}else if(expression instanceof ListExpression){
			return ((ListExpression) expression).getExpressions().isEmpty();
		}
		return false;
	}

	private Input getConnectorInput(ConnectorDefinition def,String inputName) {
		for(Input input : def.getInput()){
			if(input.getName().equals(inputName)){
				return input ;
			}
		}
		return null;
	}

	protected void clearConnectorConfiguration(ConnectorDefinition definition) {
		ConnectorConfiguration configuration =  connectorWorkingCopy.getConfiguration() ;
		configuration.getParameters().clear() ;
	}



	protected List<IWizardPage> getPagesFor(ConnectorDefinition definition) {
		List<IWizardPage> result = new ArrayList<IWizardPage>() ;

		if (isDatabaseConnector(definition)){//DRIVER SELECTION PAGE
			result.add(new DatabaseConnectorDriversWizardPage(definition.getId()));
		}

		if(extension != null && (!extension.hasCanBeUsedProvider() || extension.canBeUsed(definition,connectorWorkingCopy))){ //Extension page
			List<AbstractConnectorConfigurationWizardPage> advancedPages = extension.getPages();
			for(AbstractConnectorConfigurationWizardPage p : advancedPages){
				p.setIsPageFlowContext(isPageFlowContext);
				p.setMessageProvider(messageProvider) ;
				p.setConfiguration(connectorWorkingCopy.getConfiguration()) ;
				p.setDefinition(definition) ;
				int i = advancedPages.indexOf(p);
				if(definition.getPage().size() > i){
					p.setPage(definition.getPage().get(i));
				}
				p.setElementContainer(container) ;
				p.setExpressionTypeFilter(getExpressionTypeFilter());
				result.add(p) ;
			}

			if(extension.useDefaultGeneratedPages()){
				for(Page p : definition.getPage()){
					result.add(createDefaultConnectorPage(definition,p)) ;
				}
			}
		}else{ //Default page
			for(Page p : definition.getPage()){
				result.add(createDefaultConnectorPage(definition,p)) ;
			}
		}

		boolean alwaysUseScripting = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getBoolean(BonitaPreferenceConstants.ALWAYS_USE_SCRIPTING_MODE);
		if (!alwaysUseScripting && supportsDatabaseOutputMode(definition)){//OUTPUT TYPE SELECTION PAGE
			final SelectDatabaseOutputTypeWizardPage selectOutputPage = addDatabaseOutputModeSelectionPage(definition);
			result.add(selectOutputPage);
		}
		initializeEmptyConnectorConfiguration(definition);
		return result;
	}

	protected boolean supportsDatabaseOutputMode(ConnectorDefinition def) {
		boolean containsOutputModeInput = false;
		if(def!=null){
			for (Input input : def.getInput()){
				if(DatabaseConnectorConstants.OUTPUT_TYPE_KEY.equals(input.getName())){
					containsOutputModeInput = true;
					break;
				}
			}
		}
		if(containsOutputModeInput){
			boolean hasSingleOutput = false;
			boolean hasNRowOutput = false;
			boolean hasOneRowOutput = false;
			boolean hasTableOutput = false;
			for(Output output : def.getOutput()){
				if(DatabaseConnectorConstants.SINGLE_RESULT_OUTPUT.equals(output.getName())){
					hasSingleOutput = true;
				}else if(DatabaseConnectorConstants.NROW_ONECOL_RESULT_OUTPUT.equals(output.getName())){
					hasNRowOutput = true;
				}else if(DatabaseConnectorConstants.ONEROW_NCOL_RESULT_OUTPUT.equals(output.getName())){
					hasOneRowOutput = true;
				}else if(DatabaseConnectorConstants.TABLE_RESULT_OUTPUT.equals(output.getName())){
					hasTableOutput = true;
				}
			}
			return hasSingleOutput && hasNRowOutput && hasOneRowOutput && hasTableOutput;
		}

		return false;
	}

	protected SelectDatabaseOutputTypeWizardPage addDatabaseOutputModeSelectionPage(
			ConnectorDefinition definition) {
		final SelectDatabaseOutputTypeWizardPage selectOutputPage = new SelectDatabaseOutputTypeWizardPage(isEditMode());
		selectOutputPage.setIsPageFlowContext(isPageFlowContext);
		selectOutputPage.setMessageProvider(messageProvider) ;
		selectOutputPage.setConfiguration(connectorWorkingCopy.getConfiguration()) ;
		selectOutputPage.setDefinition(definition) ;
		selectOutputPage.setElementContainer(container) ;
		selectOutputPage.setExpressionTypeFilter(getExpressionTypeFilter());
		return selectOutputPage;
	}

	protected IWizardPage createDefaultConnectorPage(ConnectorDefinition def,Page page) {
		AbstractConnectorConfigurationWizardPage wizPage = new GeneratedConnectorWizardPage() ;
		wizPage.setIsPageFlowContext(isPageFlowContext);
		wizPage.setMessageProvider(messageProvider) ;
		wizPage.setConfiguration(connectorWorkingCopy.getConfiguration()) ;
		wizPage.setDefinition(def) ;
		wizPage.setElementContainer(container) ;
		wizPage.setPage(page) ;
		wizPage.setExpressionTypeFilter(getExpressionTypeFilter());
		return wizPage ;
	}

	protected AvailableExpressionTypeFilter getExpressionTypeFilter() {
		if(container instanceof Form || container instanceof SubmitFormButton){
			return formExpressionTypeFilter;
		}
		return expressionTypeFilter;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		final EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(container) ;
		if(editMode){
			List<?> connectorsList = (List<?>) container.eGet(connectorContainmentFeature) ;
			int index = connectorsList.indexOf(originalConnector) ;
			editingDomain.getCommandStack().execute(RemoveCommand.create(editingDomain, container, connectorContainmentFeature, originalConnector)) ;
			editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, container, connectorContainmentFeature, connectorWorkingCopy,index)) ;
		}else{
			editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, container, connectorContainmentFeature, connectorWorkingCopy)) ;
		}
		return true;
	}

	public Connector getOriginalConnector() {
		return originalConnector;
	}

	public Connector getWorkingCopyConnector() {
		return connectorWorkingCopy;
	}

	@Override
	public ConnectorDefinition getDefinition() {
		IDefinitionRepositoryStore defStore = getDefinitionStore();
		if(originalConnector != null){
			return defStore.getDefinition(originalConnector.getDefinitionId(), originalConnector.getDefinitionVersion(),definitions) ;
		}else{
			if(connectorWorkingCopy.getDefinitionId() != null && !connectorWorkingCopy.getDefinitionId().isEmpty()){
				return defStore.getDefinition(connectorWorkingCopy.getDefinitionId(), connectorWorkingCopy.getDefinitionVersion(),definitions) ;
			}
		}
		return null;
	}

	@Override
	public IWizardPage getPreviousPage(IWizardPage page) {
		IWizardPage previousPage = super.getPreviousPage(page);
		if(previousPage != null && previousPage.equals(selectionPage)){
			return null;
		}
		return previousPage;
	}

	public boolean isEditMode() {
		return editMode;
	}

	private boolean isDatabaseConnector(ConnectorDefinition def){
		List<Category> categories = def.getCategory();
		for (Category category:categories){
			if (DATABASE_ID.equals(category.getId()) && !def.getId().equals(DATASOURCE_CONNECTOR_D)){
				return true;
			}
		}
		return false;
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
