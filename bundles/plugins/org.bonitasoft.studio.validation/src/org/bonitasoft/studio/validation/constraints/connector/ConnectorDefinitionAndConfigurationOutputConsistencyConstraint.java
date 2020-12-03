package org.bonitasoft.studio.validation.constraints.connector;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.AbstractDefinitionRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.process.ActorFilter;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

public class ConnectorDefinitionAndConfigurationOutputConsistencyConstraint extends AbstractLiveValidationMarkerConstraint {

    public static final String ID = "org.bonitasoft.studio.validation.constraints.connectorDefAndConfigOutputConsistency";

    @Override
    protected IStatus performLiveValidation(IValidationContext context) {
        return null;
    }

    @Override
    protected IStatus performBatchValidation(IValidationContext context) {
        Connector connector = (Connector) context.getTarget();
        AbstractDefinitionRepositoryStore<?> connectorDefStore = null;
        boolean isConnector = true;
        if (!(connector instanceof ActorFilter)) {
            connectorDefStore = getConnectorDefinitionRepositoryStore();
        } else {
            isConnector = false;
            connectorDefStore = getActorFilterDefinitionStore();
        }
        ConnectorConfiguration configuration = connector.getConfiguration();
        if (configuration == null) {
            return context.createSuccessStatus();
        }
        ConnectorDefinition def = connectorDefStore.getDefinition(configuration.getDefinitionId(),
                configuration.getVersion());
        if (def != null) {
            IStatus outputStatus = checkOutputConsistency(connector, def, context);
            if (!outputStatus.isOK()) {
                if (isConnector) {
                    return context.createFailureStatus(
                            Messages.bind(Messages.Validation_InconsistentConnectorDefAndConfigurationOutput,
                                    new Object[] { connector.getName(),
                                            connector.getDefinitionId() + "--" + connector.getDefinitionVersion(),
                                            outputStatus.getMessage() }));
                } else {
                    return context.createFailureStatus(
                            Messages.bind(Messages.Validation_InconsistentActorDefAndConfigurationOutput,
                                    new Object[] { connector.getName(),
                                            connector.getDefinitionId() + "--" + connector.getDefinitionVersion(),
                                            outputStatus.getMessage() }));
                }
            }
        }
        return context.createSuccessStatus();
    }

    protected IStatus checkOutputConsistency(Connector connector, ConnectorDefinition def, IValidationContext context) {
        EList<Output> outputs = def.getOutput();
        Set<String> outputNames = new HashSet<>();
        for (Output output : outputs) {
            outputNames.add(output.getName());
        }
        StringBuilder sb = new StringBuilder();
        Set<String> connectorOutputs = new HashSet<>();
        for (Operation outputOp : connector.getOutputs()) {
            Expression rightOperand = outputOp.getRightOperand();
            if (rightOperand != null && !rightOperand.getReferencedElements().isEmpty()) {
                for (EObject ref : rightOperand.getReferencedElements()) {
                    if (ref instanceof Output) {
                        connectorOutputs.add(((Output) ref).getName());
                    }
                }
            }
        }

        if (outputNames.containsAll(connectorOutputs)) {
            return context.createSuccessStatus();
        }
        for (String outputName : connectorOutputs) {
            if (!outputNames.contains(outputName)) {
                sb.append(outputName);
                sb.append(", ");
            }
        }
        if (sb.length() > 1) {
            sb = sb.delete(sb.length() - 2, sb.length());
        }
        return context.createFailureStatus(sb.toString());
    }

    protected AbstractDefinitionRepositoryStore<?> getActorFilterDefinitionStore() {
        return RepositoryManager.getInstance().getRepositoryStore(ActorFilterDefRepositoryStore.class);
    }

    protected AbstractDefinitionRepositoryStore<?> getConnectorDefinitionRepositoryStore() {
        return RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
    }

    @Override
    protected String getConstraintId() {
        return ID;
    }

}
