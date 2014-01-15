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
package org.bonitasoft.studio.connectors.operation;

import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.engine.bpm.process.ActivationState;
import org.bonitasoft.engine.bpm.process.IllegalProcessStateException;
import org.bonitasoft.engine.bpm.process.ProcessActivationException;
import org.bonitasoft.engine.bpm.process.ProcessDefinition;
import org.bonitasoft.engine.bpm.process.ProcessDefinitionNotFoundException;
import org.bonitasoft.engine.bpm.process.ProcessDeploymentInfo;
import org.bonitasoft.engine.bpm.process.ProcessDeploymentInfoCriterion;
import org.bonitasoft.engine.exception.DeletionException;
import org.bonitasoft.engine.expression.Expression;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.session.InvalidSessionException;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.configuration.ConfigurationSynchronizer;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.configuration.ConnectorsConfigurationSynchronizer;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesFileStore;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesRepositoryStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.export.BarExporter;
import org.bonitasoft.studio.engine.export.EngineExpressionUtil;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.configuration.DefinitionMapping;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.configuration.util.ConfigurationAdapterFactory;
import org.bonitasoft.studio.model.configuration.util.ConfigurationResourceFactoryImpl;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.AbstractExpression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.form.FormField;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.JavaType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.util.ProcessAdapterFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;


/**
 * @author Romain Bioteau
 *
 */
public class TestConnectorOperation implements IRunnableWithProgress {
	
	private static final String OUTPUT_NAME = "output";
	private static final String TEST_CONNECTOR_POOL = "TEST_CONNECTOR_POOL";
	private final Map<String, org.bonitasoft.engine.expression.Expression> inputParameters = new HashMap<String, org.bonitasoft.engine.expression.Expression>() ;
	private Map<String, Serializable> result;
	private ConnectorImplementation implementation;
	private ConnectorConfiguration connectorConfiguration;
	private final Map<String, Map<String, Serializable>> inputValues = new HashMap<String, Map<String,Serializable>>() ;
	private final Map<String, Serializable> outputValues = new HashMap<String,Serializable>();
	private static final ConnectorsConfigurationSynchronizer CONNECTORS_CONFIGURATION_SYNCHRONIZER = new ConnectorsConfigurationSynchronizer();
	private Set<IRepositoryFileStore> additionalJars;
	private List<org.bonitasoft.engine.operation.Operation> outputOperations = new ArrayList<org.bonitasoft.engine.operation.Operation>();
	private Map<String,org.bonitasoft.studio.model.expression.Expression> invalidExpressionForTest = new HashMap<String,org.bonitasoft.studio.model.expression.Expression>();
	private IStatus status;

	/* (non-Javadoc)
	 * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		Assert.isNotNull(implementation) ;

		monitor.beginTask(Messages.testConnector, IProgressMonitor.UNKNOWN);
		checkImplementationDependencies(implementation, monitor);
		
		if(!invalidExpressionForTest.isEmpty()){
			StringBuilder sb = new StringBuilder(Messages.unsuportedExpressionTypeForTesting);
			for(Entry<String, org.bonitasoft.studio.model.expression.Expression> e : invalidExpressionForTest.entrySet()){
				sb.append("\n");
				if(ExpressionConstants.PATTERN_TYPE.equals(e.getValue().getType()) || ExpressionConstants.SCRIPT_TYPE.equals(e.getValue().getType())){
					sb.append("-"+Messages.bind(Messages.unresolvedPatternOrScriptExpression,"'"+e.getKey()+"'"));
				}else{
					sb.append("-"+Messages.bind(Messages.unresolvedExpression,"'"+e.getKey()+"'"));
				}
			}
			status = new Status(IStatus.WARNING,ConnectorPlugin.PLUGIN_ID,sb.toString());
			return;
		}
		
		APISession session = null;
		ProcessAPI processApi = null;
		long procId = -1;
		ClassLoader cl = Thread.currentThread().getContextClassLoader() ;
		try {
			session = BOSEngineManager.getInstance().loginDefaultTenant(Repository.NULL_PROGRESS_MONITOR);
			processApi = BOSEngineManager.getInstance().getProcessAPI(session);
			Assert.isNotNull(processApi) ;
			final AbstractProcess proc = createAbstractProcess(implementation);

			final Configuration configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
			configuration.setName("TestConnectorConfiguration");
			new ConfigurationSynchronizer(proc, configuration).synchronize();
			configureProcess(configuration,implementation);
			final BusinessArchive businessArchive = BarExporter.getInstance().createBusinessArchive(proc,configuration,Collections.<EObject>emptySet());

			undeployProcess(proc, processApi);
			ProcessDefinition def = processApi.deploy(businessArchive);
			procId = def.getId();
			processApi.enableProcess(procId) ;

			Thread.currentThread().setContextClassLoader(RepositoryManager.getInstance().getCurrentRepository().createProjectClassloader());
			result = processApi.executeConnectorOnProcessDefinition(implementation.getDefinitionId(), implementation.getDefinitionVersion(), inputParameters, inputValues, outputOperations,outputValues, procId);
			status = Status.OK_STATUS;
		}catch (Exception e) {
			BonitaStudioLog.error(e);
			status = new Status(IStatus.ERROR,ConnectorPlugin.PLUGIN_ID,e.getMessage(),e);
			throw new InvocationTargetException(e);
		}finally{
			if(cl != null){
				Thread.currentThread().setContextClassLoader(cl);
			}
			if(processApi != null && procId != -1){
				try{
					processApi.disableProcess(procId) ;
					processApi.deleteProcess(procId);
				}catch (Exception e) {
					BonitaStudioLog.error(e);
				}
			}
			if(session != null){
				BOSEngineManager.getInstance().logoutDefaultTenant(session);
			}
		}
	}

	private void configureProcess(Configuration configuration, ConnectorImplementation implem) {
		final DatabaseConnectorPropertiesRepositoryStore dbStore = (DatabaseConnectorPropertiesRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DatabaseConnectorPropertiesRepositoryStore.class);
		final DatabaseConnectorPropertiesFileStore file = (DatabaseConnectorPropertiesFileStore) dbStore.getChild(implem.getDefinitionId()+"."+DatabaseConnectorPropertiesRepositoryStore.CONF_EXT);
		String driver = null;
		boolean addDriver = false;
		if(file != null){
			driver = file.getDefault();
			if(driver != null){
				final IFolder libFolder = RepositoryManager.getInstance().getCurrentRepository().getProject().getFolder("lib");
				if(libFolder.getFile(driver).exists()){
					addDriver = true;
				}else{
					throw new IllegalStateException("Database driver jar "+driver+" not found in repository");
				}
			}else{
				throw new IllegalStateException("No active database driver configured for this connector");
			}
		}

		for(DefinitionMapping association : configuration.getDefinitionMappings()){
			if(FragmentTypes.CONNECTOR.equals(association.getType())){
				if(association.getDefinitionId().equals(implem.getDefinitionId())){
					association.setImplementationId(implem.getImplementationId());
					association.setImplementationVersion(implem.getImplementationVersion());
					CompoundCommand cc = new CompoundCommand();
					AdapterFactoryEditingDomain domain = createEditingDomain();
					CONNECTORS_CONFIGURATION_SYNCHRONIZER.updateConnectorDependencies(configuration, association, implem, cc, domain,addDriver) ;
					domain.getCommandStack().execute(cc);
					break;
				}
			}
		}
		//Add jars from managed jars to dependency list of the process used to test the connector
		if(additionalJars!=null){
			for(FragmentContainer fc : configuration.getProcessDependencies()){
				if(FragmentTypes.OTHER.equals(fc.getId())){
					final IFolder libFolder = RepositoryManager.getInstance().getCurrentRepository().getProject().getFolder("lib");
					if(libFolder.exists()){
						try {
							for(IResource f : libFolder.members()){
								if(f instanceof IFile && ((IFile)f).getFileExtension() != null && ((IFile)f).getFileExtension().equalsIgnoreCase("jar") && isSelectedJar(((IFile)f).getName())){
									Fragment fragment = ConfigurationFactory.eINSTANCE.createFragment();
									fragment.setExported(true);
									fragment.setKey(f.getName());
									fragment.setValue(f.getName());
									fragment.setType(FragmentTypes.JAR);
									AdapterFactoryEditingDomain domain = createEditingDomain();
									domain.getCommandStack().execute(AddCommand.create(domain, fc, ConfigurationPackage.Literals.FRAGMENT_CONTAINER__FRAGMENTS, fragment));
								}
							}
						} catch (CoreException e) {
							BonitaStudioLog.error(e);
						}
					}
				}
			}
		}
	}


	private boolean isSelectedJar(String fileName){
		for(IRepositoryFileStore fileStore : additionalJars){
			if(fileStore.getName().equals(fileName)){
				return true;
			}
		}
		return false;
	}

	private AdapterFactoryEditingDomain createEditingDomain() {
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ConfigurationAdapterFactory()) ;
		adapterFactory.addAdapterFactory(new ProcessAdapterFactory()) ;

		// command stack that will notify this editor as commands are executed
		BasicCommandStack commandStack = new BasicCommandStack();

		// Create the editing domain with our adapterFactory and command stack.
		AdapterFactoryEditingDomain editingDomain = new AdapterFactoryEditingDomain(adapterFactory,commandStack, new HashMap<Resource, Boolean>());
		editingDomain.getResourceSet().getResourceFactoryRegistry().getExtensionToFactoryMap().put("conf", new ConfigurationResourceFactoryImpl()) ;
		return editingDomain;
	}

	private AbstractProcess createAbstractProcess(ConnectorImplementation implemen) {
		AbstractProcess proc = ProcessFactory.eINSTANCE.createPool();
		proc.setName(TEST_CONNECTOR_POOL);
		proc.setVersion("1.0");
		Connector connector = ProcessFactory.eINSTANCE.createConnector();
		connector.setConfiguration(EcoreUtil.copy(connectorConfiguration));
		connector.setEvent(ConnectorEvent.ON_ENTER.name());
		connector.setDefinitionId(implemen.getDefinitionId());
		connector.setDefinitionVersion(implemen.getDefinitionVersion());
		connector.setIgnoreErrors(false);
		addDatasOnProcess(proc);
		proc.getConnectors().add(connector);
		return proc;
	}

	public Map<String, Serializable> getResult(){
		return result ;
	}


	private void addDatasOnProcess(AbstractProcess process){
		Iterator<org.bonitasoft.engine.operation.Operation> it= outputOperations.iterator();
		JavaType type = ProcessFactory.eINSTANCE.createJavaType();

		while(it.hasNext()){
			org.bonitasoft.engine.operation.Operation output = it.next();
			JavaObjectData data = ProcessFactory.eINSTANCE.createJavaObjectData();
			data.setName(output.getLeftOperand().getName());
			data.setDataType(type);
			data.setClassName(Object.class.getName());
			process.getData().add(data);
		}
	}



	protected void addInputParameters(String inputName,AbstractExpression expression){
		Expression exp =  EngineExpressionUtil.createExpression(expression) ;
		if(exp != null){
			inputParameters.put(inputName,exp) ;
			inputValues.put(inputName,Collections.<String,Serializable>emptyMap());
		}
	}

	protected void addOutput(Operation operation, int cpt){
		Operation operationCopy = EcoreUtil.copy(operation);
		operationCopy.getLeftOperand().setType(ExpressionConstants.VARIABLE_TYPE);
		operationCopy.getLeftOperand().setReturnType(operation.getRightOperand().getReturnType());
		operationCopy.getLeftOperand().setContent(OUTPUT_NAME+cpt);
		operationCopy.getLeftOperand().setName(OUTPUT_NAME+cpt);
		Operator operator = ExpressionFactory.eINSTANCE.createOperator();
		operator.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		operationCopy.setOperator(operator);
		org.bonitasoft.engine.operation.Operation op=EngineExpressionUtil.createOperation(operationCopy,true);
		if (op!=null){
			outputOperations .add(op);
		}
	}

	public void setImplementation(ConnectorImplementation implementation){
		this.implementation = implementation ;
	}

	protected void undeployProcess(AbstractProcess process, ProcessAPI processApi) throws InvalidSessionException, ProcessDefinitionNotFoundException, IllegalProcessStateException, DeletionException {
		long nbDeployedProcesses = processApi.getNumberOfProcessDeploymentInfos() ;
		if(nbDeployedProcesses > 0){
			List<ProcessDeploymentInfo> processes = processApi.getProcessDeploymentInfos(0, (int) nbDeployedProcesses, ProcessDeploymentInfoCriterion.DEFAULT) ;
			for(ProcessDeploymentInfo info : processes){
				if(info.getName().equals(process.getName()) && info.getVersion().equals(process.getVersion())){
					try{
						if (processApi.getProcessDeploymentInfo(info.getProcessId()).getActivationState() == ActivationState.ENABLED){
							processApi.disableProcess(info.getProcessId()) ;
						}
					}catch (ProcessActivationException e) {

					}
					processApi.deleteProcess(info.getProcessId()) ;
				}
			}
		}
	}

	public static Set<String> checkImplementationDependencies(final ConnectorImplementation implementation,IProgressMonitor monitor) {
		Set<String> dependencies = new HashSet<String>();
		if(!implementation.getJarDependencies().getJarDependency().isEmpty()){
			DependencyRepositoryStore depStore = (DependencyRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class) ;
			final DefinitionResourceProvider resourceProvider = DefinitionResourceProvider.getInstance(RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class), ConnectorPlugin.getDefault().getBundle()) ;
			for(String jarName : implementation.getJarDependencies().getJarDependency()){
				dependencies.add(jarName);
				if(depStore.getChild(jarName) == null){
					InputStream is = resourceProvider.getDependencyInputStream(jarName) ;
					if(is != null){
						depStore.importInputStream(jarName, is) ;
					}
				}
			}
		}
		return dependencies;
	}

	public void setConnectorOutput(Connector connector){
		int i=0;
		for (Operation output:connector.getOutputs()){
			addOutput(output,i++);
		}
	}

	public void setConnectorConfiguration(ConnectorConfiguration configuration) {
		connectorConfiguration = configuration ;
		invalidExpressionForTest .clear();
		for(ConnectorParameter parameter : configuration.getParameters()){
			detectInvalidExpressions(parameter);
			addInputParameters(parameter.getKey(), parameter.getExpression()) ;
		}
	}

	protected void detectInvalidExpressions(ConnectorParameter parameter) {
		List<org.bonitasoft.studio.model.expression.Expression> expressions = ModelHelper.getAllItemsOfType(parameter, ExpressionPackage.Literals.EXPRESSION);
		for(org.bonitasoft.studio.model.expression.Expression e : expressions){
			if(ExpressionConstants.VARIABLE_TYPE.equals(e.getType())){
				invalidExpressionForTest.put(parameter.getKey(),e);
			}else if(ExpressionConstants.PARAMETER_TYPE.equals(e.getType())){
				invalidExpressionForTest.put(parameter.getKey(),e);
			}else if(ExpressionConstants.FORM_FIELD_TYPE.equals(e.getType())){
				invalidExpressionForTest.put(parameter.getKey(),e);
			}else if(ExpressionConstants.PATTERN_TYPE.equals(e.getType()) && !e.getReferencedElements().isEmpty()){
				invalidExpressionForTest.put(parameter.getKey(),e);
			}else if(ExpressionConstants.DOCUMENT_REF_TYPE.equals(e.getType())){
				invalidExpressionForTest.put(parameter.getKey(),e);
			}else if(ExpressionConstants.DOCUMENT_TYPE.equals(e.getType())){
				invalidExpressionForTest.put(parameter.getKey(),e);
			}else if(ExpressionConstants.SCRIPT_TYPE.equals(e.getType())  && !e.getReferencedElements().isEmpty()){
				for(EObject dep : e.getReferencedElements() ){
					if(dep instanceof Data){
						invalidExpressionForTest.put(parameter.getKey(),e);
					}else if(dep instanceof Parameter){
						invalidExpressionForTest.put(parameter.getKey(),e);
					}else if(dep instanceof FormField){
						invalidExpressionForTest.put(parameter.getKey(),e);
					}else if(dep instanceof Document){
						invalidExpressionForTest.put(parameter.getKey(),e);
					}
				}
			}
		}
	}

	public Set<IRepositoryFileStore> getAdditionalJars() {
		return additionalJars;
	}

	public void setAdditionalJars(Set<IRepositoryFileStore> additionalJars) {
		this.additionalJars = additionalJars;
	}

	public IStatus getStatus() {
		return status;
	}


}
