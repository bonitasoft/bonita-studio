/**
 * Copyright (C) 2012-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.engine.export.builder;

import static com.google.common.collect.Iterables.find;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.engine.bpm.process.impl.ActorDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.ConnectorDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.DataDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.DescriptionBuilder;
import org.bonitasoft.engine.bpm.process.impl.FlowElementBuilder;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.UserTaskDefinitionBuilder;
import org.bonitasoft.engine.expression.Expression;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.contribution.BuildProcessDefinitionException;
import org.bonitasoft.studio.engine.contribution.IEngineDefinitionBuilder;
import org.bonitasoft.studio.engine.export.EngineExpressionUtil;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.kpi.AbstractKPIBinding;
import org.bonitasoft.studio.model.kpi.DatabaseKPIBinding;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.util.ProcessSwitch;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Predicate;

/**
 * @author Romain Bioteau
 */
public abstract class AbstractProcessBuilder extends ProcessSwitch<Element> {

    private static final String ENGINE_DEFINITION_BUILDER_EXTENSION_ID = "org.bonitasoft.studio.engine.definition.builder";

    protected final Set<EObject> eObjectNotExported;

    private List<IEngineDefinitionBuilder<?>> engineDefinitionBuilders;

    public static final String DB_CONNECTOR_FOR_KPI_ID = "database-jdbc";
    public static final String DB_CONNECTOR_VERSION = "1.0.0";
    public static final String DB_DRIVER = "driver";
    public static final String DB_URL = "url";
    public static final String DB_QUERY = "script";
    public static final String DB_USER = "username";
    public static final String DB_PASSWORD = "password";

    private static final String SUFFIX_CONTEXT = "_ref";

    public AbstractProcessBuilder(final Set<EObject> eObjectNotExported) {
        this.eObjectNotExported = eObjectNotExported;
    }

    protected List<IEngineDefinitionBuilder<?>> createEngineDefinitionBuilders() {
        final List<IEngineDefinitionBuilder<?>> result = new ArrayList<IEngineDefinitionBuilder<?>>();
        final IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
                ENGINE_DEFINITION_BUILDER_EXTENSION_ID);
        for (final IConfigurationElement cfgElement : elements) {
            IEngineDefinitionBuilder<?> builder = null;
            try {
                builder = (IEngineDefinitionBuilder<?>) cfgElement.createExecutableExtension("class");
            } catch (final CoreException e) {
                BonitaStudioLog.error("Failed to initialize IEngineDefinitionBuilder: " + cfgElement, e, EnginePlugin.PLUGIN_ID);
            }
            if (builder != null) {
                result.add(builder);
            }
        }
        return result;
    }

    protected IEngineDefinitionBuilder<?> getEngineDefinitionBuilder(final EObject context, final EObject element) {
        if (engineDefinitionBuilders == null) {
            engineDefinitionBuilders = createEngineDefinitionBuilders();
        }
        return find(engineDefinitionBuilders, builderApplyingTo(context, element), null);
    }

    private Predicate<? super IEngineDefinitionBuilder<?>> builderApplyingTo(final EObject context, final EObject element) {
        return new Predicate<IEngineDefinitionBuilder<?>>() {

            @Override
            public boolean apply(final IEngineDefinitionBuilder<?> builder) {
                return builder.appliesTo(context, element);
            }
        };
    }

    protected void addActors(final ProcessDefinitionBuilder builder, final AbstractProcess process) {
        for (final Actor a : process.getActors()) {
            final ActorDefinitionBuilder actorBuilder = builder.addActor(a.getName(), a.isInitiator());
            if (a.getDocumentation() != null) {
                actorBuilder.addDescription(a.getDocumentation());
            }
        }
    }

    protected void addParameters(final ProcessDefinitionBuilder builder, final AbstractProcess process) {
        for (final Parameter p : process.getParameters()) {
            final String description = p.getDescription();
            builder.addParameter(p.getName(), p.getTypeClassname()).addDescription(description == null ? "" : description);
        }
    }

    protected void addConnector(final FlowElementBuilder builder, final ConnectableElement element) {
        for (final Connector connector : element.getConnectors()) {
            if (!eObjectNotExported.contains(connector)) {
                final GroovyConnectorConfigurationConverter groovyConnectorConfigurationConverter = new GroovyConnectorConfigurationConverter();
                ConnectorConfiguration configuration = connector.getConfiguration();
                if(configuration == null){
                    throw new MissingConnectorConfigurationException(connector,element);
                }
                if (groovyConnectorConfigurationConverter.appliesTo(configuration)) {
                    configuration = groovyConnectorConfigurationConverter.convert(connector.getConfiguration());
                }
                final ConnectorDefinitionBuilder connectorBuilder = builder.addConnector(connector.getName(), connector.getDefinitionId(),
                        connector.getDefinitionVersion(), ConnectorEvent.valueOf(connector.getEvent()));
                handleConnectorBehaviorOnFailure(connector, connectorBuilder);
                handleConnectorInputs(configuration, connectorBuilder);
                handleConnectorOutputs(connector, connectorBuilder);
            }
        }
    }

    private void handleConnectorBehaviorOnFailure(final Connector connector, final ConnectorDefinitionBuilder connectorBuilder) {
        if (connector.isIgnoreErrors()) {
            connectorBuilder.ignoreError();
        } else if (connector.isThrowErrorEvent()) {
            connectorBuilder.throwErrorEventWhenFailed(connector.getNamedError());
        }
    }

    private void handleConnectorInputs(final ConnectorConfiguration configuration, final ConnectorDefinitionBuilder connectorBuilder) {
        for (final ConnectorParameter parameter : configuration.getParameters()) {
            final Expression inputExpression = EngineExpressionUtil.createExpression(parameter.getExpression());
            if (inputExpression != null) {
                connectorBuilder.addInput(parameter.getKey(), inputExpression);
            } else {
                if (BonitaStudioLog.isLoggable(IStatus.OK)) {
                    BonitaStudioLog.debug("Expression of input " + parameter.getKey() + " is null for connector " + configuration.getName(),
                            EnginePlugin.PLUGIN_ID);
                }
            }
        }
    }

    private void handleConnectorOutputs(final Connector connector, final ConnectorDefinitionBuilder connectorBuilder) {
        for (final Operation outputOperation : connector.getOutputs()) {
            if (outputOperation.getLeftOperand() != null
                    && outputOperation.getLeftOperand().getContent() != null
                    && !outputOperation.getLeftOperand().getContent().isEmpty()
                    && outputOperation.getRightOperand() != null
                    && outputOperation.getRightOperand().getContent() != null) {
                connectorBuilder.addOutput(EngineExpressionUtil.createOperation(outputOperation));
            }
        }
    }

    protected void addKPIBinding(final FlowElementBuilder builder, final ConnectableElement element) {
        for (final AbstractKPIBinding kpiBinding : element.getKpis()) {
            if (kpiBinding instanceof DatabaseKPIBinding) {
                final ConnectorDefinitionBuilder connectorBuilder = builder.addConnector(kpiBinding.getName(), DB_CONNECTOR_FOR_KPI_ID, DB_CONNECTOR_VERSION,
                        ConnectorEvent.valueOf(kpiBinding.getEvent()));
                if (kpiBinding.isIgnoreError()) {
                    connectorBuilder.ignoreError();
                }
                final DatabaseKPIBinding dbKPI = (DatabaseKPIBinding) kpiBinding;
                connectorBuilder.addInput(DB_DRIVER, EngineExpressionUtil.createExpression(dbKPI.getDriverclassName()));
                connectorBuilder.addInput(DB_URL, EngineExpressionUtil.createExpression(dbKPI.getJdbcUrl()));
                connectorBuilder.addInput(DB_QUERY, EngineExpressionUtil.createExpression(dbKPI.getRequest()));
                final Expression dbUserExpression = EngineExpressionUtil.createExpression(dbKPI.getUser());
                if (dbUserExpression != null) {
                    connectorBuilder.addInput(DB_USER, dbUserExpression);
                }
                final Expression dbPasswordExpression = EngineExpressionUtil.createExpression(dbKPI.getPassword());
                if (dbPasswordExpression != null) {
                    connectorBuilder.addInput(DB_PASSWORD, dbPasswordExpression);
                }
            }
        }
    }

    protected void addData(final FlowElementBuilder dataContainerBuilder,
            final DataAware dataAwareContainer) {
        for (final Data data : dataAwareContainer.getData()) {
            final ProcessSwitch<DataDefinitionBuilder> dataSwitch = getDataSwitch(dataContainerBuilder, data);
            final DataDefinitionBuilder dataBuilder = dataSwitch.doSwitch(data.getDataType());
            if (data.isTransient() && dataBuilder != null) {
                dataBuilder.isTransient();
            }
        }
    }

    protected EngineDataBuilder getDataSwitch(
            final FlowElementBuilder dataContainerBuilder, final Data data) {
        return new EngineDataBuilder(data, dataContainerBuilder);
    }

    protected void addDescription(final DescriptionBuilder builder, final String description) {
        if (description != null && !description.isEmpty()) {
            builder.addDescription(description);
        }
    }

    protected void addContract(final FlowElementBuilder builder, final ContractContainer contractContainer) {
        if (contractContainer != null) {
            final Contract contract = contractContainer.getContract();
            if (contract != null) {
                final IEngineDefinitionBuilder contractBuilder = getEngineDefinitionBuilder(contractContainer, contract);
                contractBuilder.setEngineBuilder(builder);
                try {
                    contractBuilder.build(contract);
                } catch (final BuildProcessDefinitionException e) {
                    throw new RuntimeException("Failed to export contract definition for " + ((Element) contractContainer).getName(), e);
                }
            }
        }
    }

    protected void addDocuments(final ProcessDefinitionBuilder builder, final Pool pool) {
        if (pool != null) {
            for (final Document document : pool.getDocuments()) {
                final IEngineDefinitionBuilder documentBuilder = getEngineDefinitionBuilder(pool, document);
                if (documentBuilder != null) {
                    documentBuilder.setEngineBuilder(builder);
                    try {
                        documentBuilder.build(document);
                    } catch (final BuildProcessDefinitionException e) {
                        throw new RuntimeException("Failed to export document definition for " + ((Element) pool).getName(), e);
                    }
                }
            }
        }
    }

    protected void addContext(final Object contextBuilder, final Task task) {
        final Pool pool = ModelHelper.getParentPool(task);
        addContext(contextBuilder, pool);
        addIteratorToContext(contextBuilder, task);
    }

    /**
     * @param contextBuilder
     * @param task
     */
    protected void addIteratorToContext(final Object contextBuilder, final Task task) {
        final org.bonitasoft.studio.model.expression.Expression iteratorExpression = task.getIteratorExpression();
        if (iteratorExpression != null &&
                ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE.equals(iteratorExpression.getType())
                && iteratorExpression.getName() != null
                && !iteratorExpression.getName().isEmpty()
                && task instanceof DataAware) {
            final String referenceName = iteratorExpression.getName() + SUFFIX_CONTEXT;
            final Data data = ExpressionHelper.dataFromIteratorExpression(task, iteratorExpression,
                    ModelHelper.getMainProcess(task));
            if (data instanceof BusinessObjectData) {
                final Expression expression = EngineExpressionUtil.createBusinessObjectDataReferenceExpression(
                        (BusinessObjectData) data);
                addContextEntry(contextBuilder, referenceName, expression);
            }
        }
    }

    protected void addContext(final Object contextBuilder, final Pool pool) {
        addBusinessDataInContext(contextBuilder, pool);
        addDocumentInContext(contextBuilder, pool);
    }

    protected void addDocumentInContext(final Object contextBuilder, final Pool pool) {
        for (final Document document : pool.getDocuments()) {
            final String referenceName = document.getName() + SUFFIX_CONTEXT;
            final Expression documentReferenceExpression = EngineExpressionUtil.createDocumentExpression(document);
            addContextEntry(contextBuilder, referenceName, documentReferenceExpression);
        }
    }

    protected void addBusinessDataInContext(final Object contextBuilder, final Pool pool) {
        for (final Data data : pool.getData()) {
            if (data instanceof BusinessObjectData) {
                final String referenceName = data.getName() + SUFFIX_CONTEXT;
                final Expression referenceExpression = EngineExpressionUtil.createBusinessObjectDataReferenceExpression((BusinessObjectData) data);
                addContextEntry(contextBuilder, referenceName, referenceExpression);
            }
        }
    }

    protected void addContextEntry(final Object contextBuilder, final String referenceName,
            final org.bonitasoft.engine.expression.Expression referenceExpression) {
        if (contextBuilder instanceof UserTaskDefinitionBuilder) {
            ((UserTaskDefinitionBuilder) contextBuilder).addContextEntry(referenceName, referenceExpression);
        } else if (contextBuilder instanceof ProcessDefinitionBuilder) {
            ((ProcessDefinitionBuilder) contextBuilder).addContextEntry(referenceName, referenceExpression);
        }
    }

}
