/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.connectors.operation;

import java.io.File;
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
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.bonitasoft.bonita2bar.process.builder.GroovyConnectorConfigurationConverter;
import org.bonitasoft.bonita2bar.process.expression.EngineExpressionUtil;
import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.ConfigurationFactory;
import org.bonitasoft.bpm.model.configuration.ConfigurationPackage;
import org.bonitasoft.bpm.model.configuration.DefinitionMapping;
import org.bonitasoft.bpm.model.configuration.Fragment;
import org.bonitasoft.bpm.model.configuration.FragmentContainer;
import org.bonitasoft.bpm.model.configuration.util.ConfigurationAdapterFactory;
import org.bonitasoft.bpm.model.configuration.util.ConfigurationResourceFactoryImpl;
import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.bpm.model.expression.AbstractExpression;
import org.bonitasoft.bpm.model.expression.ExpressionFactory;
import org.bonitasoft.bpm.model.expression.ExpressionPackage;
import org.bonitasoft.bpm.model.expression.Operation;
import org.bonitasoft.bpm.model.expression.Operator;
import org.bonitasoft.bpm.model.parameter.Parameter;
import org.bonitasoft.bpm.model.process.AbstractProcess;
import org.bonitasoft.bpm.model.process.Connector;
import org.bonitasoft.bpm.model.process.Data;
import org.bonitasoft.bpm.model.process.Document;
import org.bonitasoft.bpm.model.process.JavaObjectData;
import org.bonitasoft.bpm.model.process.JavaType;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.bonitasoft.bpm.model.process.util.ProcessAdapterFactory;
import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.bonitasoft.bpm.model.util.FragmentTypes;
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
import org.bonitasoft.engine.expression.InvalidExpressionException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.session.InvalidSessionException;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.configuration.ConfigurationSynchronizer;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.configuration.ConnectorsConfigurationSynchronizer;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesFileStore;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesRepositoryStore;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.export.BarExporter;
import org.eclipse.core.runtime.Assert;
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
 */
public class TestConnectorOperation implements IRunnableWithProgress {

    private static final String OUTPUT_NAME = "output";
    private static final String TEST_CONNECTOR_POOL = "TEST_CONNECTOR_POOL";
    private final Map<String, org.bonitasoft.engine.expression.Expression> inputParameters = new HashMap<String, org.bonitasoft.engine.expression.Expression>();
    private Map<String, Serializable> result;
    private ConnectorImplementation implementation;
    private ConnectorConfiguration connectorConfiguration;
    private final Map<String, Map<String, Serializable>> inputValues = new HashMap<String, Map<String, Serializable>>();
    private final Map<String, Serializable> outputValues = new HashMap<String, Serializable>();
    private static final ConnectorsConfigurationSynchronizer CONNECTORS_CONFIGURATION_SYNCHRONIZER = new ConnectorsConfigurationSynchronizer();
    private Set<DependencyFileStore> additionalJars = new HashSet<>();
    private final List<org.bonitasoft.engine.operation.Operation> outputOperations = new ArrayList<org.bonitasoft.engine.operation.Operation>();
    private final Map<String, org.bonitasoft.bpm.model.expression.Expression> invalidExpressionForTest = new HashMap<String, org.bonitasoft.bpm.model.expression.Expression>();
    private IStatus status;

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        Assert.isNotNull(implementation);

        monitor.beginTask(Messages.testConnector, IProgressMonitor.UNKNOWN);
        checkImplementationDependencies(implementation, monitor);

        if (!invalidExpressionForTest.isEmpty()) {
            final StringBuilder sb = new StringBuilder(Messages.unsuportedExpressionTypeForTesting);
            for (final Entry<String, org.bonitasoft.bpm.model.expression.Expression> e : invalidExpressionForTest
                    .entrySet()) {
                sb.append("\n");
                if (ExpressionConstants.PATTERN_TYPE.equals(e.getValue().getType())
                        || ExpressionConstants.SCRIPT_TYPE.equals(e.getValue().getType())) {
                    sb.append(
                            "-" + Messages.bind(Messages.unresolvedPatternOrScriptExpression, "'" + e.getKey() + "'"));
                } else {
                    sb.append("-" + Messages.bind(Messages.unresolvedExpression, "'" + e.getKey() + "'"));
                }
            }
            status = new Status(IStatus.WARNING, ConnectorPlugin.PLUGIN_ID, sb.toString());
            return;
        }

        APISession session = null;
        ProcessAPI processApi = null;
        long procId = -1;
        try {
            session = BOSEngineManager.getInstance().loginDefaultTenant(AbstractRepository.NULL_PROGRESS_MONITOR);
            processApi = BOSEngineManager.getInstance().getProcessAPI(session);
            Assert.isNotNull(processApi);
            var proc = createProcess(implementation);

            final Configuration configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
            configuration.setName("TestConnectorConfiguration");
            new ConfigurationSynchronizer(proc, configuration).synchronize();
            configureProcess(configuration, implementation);
            final BusinessArchive businessArchive = BarExporter.getInstance().createBusinessArchive(proc,
                    configuration);

            undeployProcess(proc, processApi);
            final ProcessDefinition def = processApi.deploy(businessArchive);
            procId = def.getId();
            processApi.enableProcess(procId);
            result = processApi.executeConnectorOnProcessDefinition(implementation.getDefinitionId(),
                    implementation.getDefinitionVersion(), inputParameters,
                    inputValues, outputOperations, outputValues, procId);
            status = Status.OK_STATUS;
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
            status = new Status(IStatus.ERROR, ConnectorPlugin.PLUGIN_ID, e.getMessage(), e);
            throw new InvocationTargetException(e);
        } finally {
            if (processApi != null && procId != -1) {
                try {
                    processApi.disableProcess(procId);
                    processApi.deleteProcessDefinition(procId);
                } catch (final Exception e) {
                    BonitaStudioLog.error(e);
                }
            }
            if (session != null) {
                BOSEngineManager.getInstance().logoutDefaultTenant(session);
            }
        }
    }

    private boolean shouldAddJarToClasspath(File jar) {
        return implementation.getJarDependencies() != null
                && implementation.getJarDependencies().getJarDependency().contains(jar.getName())
                || additionalJars.stream()
                        .map(IRepositoryFileStore::getName).anyMatch(jarName -> Objects.equals(jarName, jar.getName()));
    }

    private void configureProcess(final Configuration configuration, final ConnectorImplementation implem) {
        final DatabaseConnectorPropertiesRepositoryStore dbStore = RepositoryManager.getInstance().getRepositoryStore(
                DatabaseConnectorPropertiesRepositoryStore.class);
        final DatabaseConnectorPropertiesFileStore file = dbStore
                .getChild(implem.getDefinitionId() + "." + DatabaseConnectorPropertiesRepositoryStore.PROPERTIES_EXT,
                        true);
        String driver = null;
        boolean addDriver = false;
        if (file != null) {
            driver = file.getDefault();
            if (driver != null) {
                final DependencyRepositoryStore depStore = RepositoryManager.getInstance().getCurrentRepository()
                        .orElseThrow()
                        .getRepositoryStore(DependencyRepositoryStore.class);
                Optional<DependencyFileStore> result = depStore.findDependencyByName(driver);
                if (result.isPresent()) {
                    addDriver = true;
                }
            } else {
                throw new IllegalStateException("No active database driver configured for this connector");
            }
        }

        for (final DefinitionMapping association : configuration.getDefinitionMappings()) {
            if (FragmentTypes.CONNECTOR.equals(association.getType())) {
                if (association.getDefinitionId().equals(implem.getDefinitionId())) {
                    association.setImplementationId(implem.getImplementationId());
                    association.setImplementationVersion(implem.getImplementationVersion());
                    final CompoundCommand cc = new CompoundCommand();
                    final AdapterFactoryEditingDomain domain = createEditingDomain();
                    CONNECTORS_CONFIGURATION_SYNCHRONIZER.updateConnectorDependencies(configuration, association,
                            implem, cc, domain, addDriver);
                    domain.getCommandStack().execute(cc);
                    break;
                }
            }
        }
        //Add jars from managed jars to dependency list of the process used to test the connector
        if (!additionalJars.isEmpty()) {
            for (final FragmentContainer fc : configuration.getProcessDependencies()) {
                if (FragmentTypes.OTHER.equals(fc.getId())) {
                    for (final DependencyFileStore f : additionalJars) {
                        final Fragment fragment = createJarFragment(f.getFile());
                        final AdapterFactoryEditingDomain domain = createEditingDomain();
                        domain.getCommandStack().execute(
                                AddCommand.create(domain, fc,
                                        ConfigurationPackage.Literals.FRAGMENT_CONTAINER__FRAGMENTS,
                                        fragment));
                        f.getTransitiveDependencies().stream()
                                .map(TestConnectorOperation::createJarFragment)
                                .forEach(frag -> domain.getCommandStack().execute(
                                        AddCommand.create(domain, fc,
                                                ConfigurationPackage.Literals.FRAGMENT_CONTAINER__FRAGMENTS,
                                                frag)));
                    }
                }
            }
        }
    }

    private static Fragment createJarFragment(File f) {
        final Fragment fragment = ConfigurationFactory.eINSTANCE.createFragment();
        fragment.setExported(true);
        fragment.setKey(f.getName());
        fragment.setValue(f.getName());
        fragment.setType(FragmentTypes.JAR);
        return fragment;
    }

    private AdapterFactoryEditingDomain createEditingDomain() {
        final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
                ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
        adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
        adapterFactory.addAdapterFactory(new ConfigurationAdapterFactory());
        adapterFactory.addAdapterFactory(new ProcessAdapterFactory());

        // command stack that will notify this editor as commands are executed
        final BasicCommandStack commandStack = new BasicCommandStack();

        // Create the editing domain with our adapterFactory and command stack.
        final AdapterFactoryEditingDomain editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack,
                new HashMap<Resource, Boolean>());
        editingDomain.getResourceSet().getResourceFactoryRegistry().getExtensionToFactoryMap().put("conf",
                new ConfigurationResourceFactoryImpl());
        return editingDomain;
    }

    private Pool createProcess(final ConnectorImplementation implemen) {
    	Pool proc = ProcessFactory.eINSTANCE.createPool();
        proc.setName(TEST_CONNECTOR_POOL);
        proc.setVersion("1.0");
        final Connector connector = ProcessFactory.eINSTANCE.createConnector();
        connector.setConfiguration(EcoreUtil.copy(connectorConfiguration));
        connector.setEvent(ConnectorEvent.ON_ENTER.name());
        connector.setDefinitionId(implemen.getDefinitionId());
        connector.setDefinitionVersion(implemen.getDefinitionVersion());
        connector.setIgnoreErrors(false);
        addDatasOnProcess(proc);
        proc.getConnectors().add(connector);
        return proc;
    }

    public Map<String, Serializable> getResult() {
        return result;
    }

    private void addDatasOnProcess(final AbstractProcess process) {
        final Iterator<org.bonitasoft.engine.operation.Operation> it = outputOperations.iterator();
        final JavaType type = ProcessFactory.eINSTANCE.createJavaType();

        while (it.hasNext()) {
            final org.bonitasoft.engine.operation.Operation output = it.next();
            final JavaObjectData data = ProcessFactory.eINSTANCE.createJavaObjectData();
            data.setName(output.getLeftOperand().getName());
            data.setDataType(type);
            data.setClassName(Object.class.getName());
            process.getData().add(data);
        }
    }

    protected void addInputParameters(final String inputName, final AbstractExpression expression) throws InvalidExpressionException {
        final Expression exp = EngineExpressionUtil.createExpression(expression);
        if (exp != null) {
            inputParameters.put(inputName, exp);
            inputValues.put(inputName, Collections.<String, Serializable> emptyMap());
        }
    }

    protected void addOutput(final Operation operation, final int cpt) throws InvalidExpressionException {
        final Operation operationCopy = EcoreUtil.copy(operation);
        operationCopy.getLeftOperand().setType(ExpressionConstants.VARIABLE_TYPE);
        operationCopy.getLeftOperand().setReturnType(operation.getRightOperand().getReturnType());
        operationCopy.getLeftOperand().setContent(OUTPUT_NAME + cpt);
        operationCopy.getLeftOperand().setName(OUTPUT_NAME + cpt);
        final Operator operator = ExpressionFactory.eINSTANCE.createOperator();
        operator.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
        operationCopy.setOperator(operator);
        final org.bonitasoft.engine.operation.Operation op = EngineExpressionUtil.createOperation(operationCopy, true);
        if (op != null) {
            outputOperations.add(op);
        }
    }

    public void setImplementation(final ConnectorImplementation implementation) {
        this.implementation = implementation;
    }

    protected void undeployProcess(final AbstractProcess process, final ProcessAPI processApi)
            throws InvalidSessionException,
            ProcessDefinitionNotFoundException, IllegalProcessStateException, DeletionException {
        final long nbDeployedProcesses = processApi.getNumberOfProcessDeploymentInfos();
        if (nbDeployedProcesses > 0) {
            final List<ProcessDeploymentInfo> processes = processApi.getProcessDeploymentInfos(0,
                    (int) nbDeployedProcesses,
                    ProcessDeploymentInfoCriterion.DEFAULT);
            for (final ProcessDeploymentInfo info : processes) {
                if (info.getName().equals(process.getName()) && info.getVersion().equals(process.getVersion())) {
                    try {
                        if (processApi.getProcessDeploymentInfo(info.getProcessId())
                                .getActivationState() == ActivationState.ENABLED) {
                            processApi.disableProcess(info.getProcessId());
                        }
                    } catch (final ProcessActivationException e) {

                    }
                    processApi.deleteProcessDefinition(info.getProcessId());
                }
            }
        }
    }

    public static Set<String> checkImplementationDependencies(final ConnectorImplementation implementation,
            final IProgressMonitor monitor) {
        final Set<String> dependencies = new HashSet<>();
        if (!implementation.getJarDependencies().getJarDependency().isEmpty()) {
            for (final String jarName : implementation.getJarDependencies().getJarDependency()) {
                dependencies.add(jarName);
            }
        }
        return dependencies;
    }

    public void setConnectorOutput(final Connector connector) throws InvalidExpressionException {
        int i = 0;
        for (final Operation output : connector.getOutputs()) {
            addOutput(output, i++);
        }
    }

    public void setConnectorConfiguration(final ConnectorConfiguration configuration) throws InvalidExpressionException {
        final GroovyConnectorConfigurationConverter groovyConnectorConfigurationConverter = new GroovyConnectorConfigurationConverter();
        connectorConfiguration = configuration;
        ConnectorConfiguration convertedConfiguration = configuration;
        if (groovyConnectorConfigurationConverter.appliesTo(configuration)) {
            convertedConfiguration = groovyConnectorConfigurationConverter.convert(configuration);
        }
        invalidExpressionForTest.clear();
        for (final ConnectorParameter parameter : convertedConfiguration.getParameters()) {
            detectInvalidExpressions(parameter);
            addInputParameters(parameter.getKey(), parameter.getExpression());
        }
    }

    protected void detectInvalidExpressions(final ConnectorParameter parameter) {
        final List<org.bonitasoft.bpm.model.expression.Expression> expressions = ModelHelper.getAllItemsOfType(
                parameter,
                ExpressionPackage.Literals.EXPRESSION);
        for (final org.bonitasoft.bpm.model.expression.Expression e : expressions) {
            if (ExpressionConstants.VARIABLE_TYPE.equals(e.getType())) {
                invalidExpressionForTest.put(parameter.getKey(), e);
            } else if (ExpressionConstants.PARAMETER_TYPE.equals(e.getType())) {
                invalidExpressionForTest.put(parameter.getKey(), e);
            } else if (ExpressionConstants.PATTERN_TYPE.equals(e.getType()) && !e.getReferencedElements().isEmpty()) {
                invalidExpressionForTest.put(parameter.getKey(), e);
            } else if (ExpressionConstants.DOCUMENT_REF_TYPE.equals(e.getType())) {
                invalidExpressionForTest.put(parameter.getKey(), e);
            } else if (ExpressionConstants.DOCUMENT_TYPE.equals(e.getType())) {
                invalidExpressionForTest.put(parameter.getKey(), e);
            } else if (ExpressionConstants.SCRIPT_TYPE.equals(e.getType()) && !e.getReferencedElements().isEmpty()) {
                for (final EObject dep : e.getReferencedElements()) {
                    if (dep instanceof Data) {
                        invalidExpressionForTest.put(parameter.getKey(), e);
                    } else if (dep instanceof Parameter) {
                        invalidExpressionForTest.put(parameter.getKey(), e);
                    } else if (dep instanceof Document) {
                        invalidExpressionForTest.put(parameter.getKey(), e);
                    }
                }
            }
        }
    }

    public Set<DependencyFileStore> getAdditionalJars() {
        return additionalJars;
    }

    public void setAdditionalJars(final Set<DependencyFileStore> additionalJars) {
        this.additionalJars = additionalJars;
    }

    public IStatus getStatus() {
        return status;
    }

}
