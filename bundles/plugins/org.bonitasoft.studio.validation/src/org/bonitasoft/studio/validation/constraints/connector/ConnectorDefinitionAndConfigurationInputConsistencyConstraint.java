package org.bonitasoft.studio.validation.constraints.connector;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.AbstractDefFileStore;
import org.bonitasoft.studio.connector.model.definition.AbstractDefinitionRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.process.ActorFilter;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.validation.IValidationContext;

public class ConnectorDefinitionAndConfigurationInputConsistencyConstraint extends AbstractLiveValidationMarkerConstraint {

    public static final String ID = "org.bonitasoft.studio.validation.constraints.connectorDefAndConfigInputConsistency";

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
            AbstractDefFileStore fStore = getDefFileStore(connectorDefStore, def);
            if (!fStore.isReadOnly()) {
                IStatus inputStatus = checkInputConsistency(configuration, def, context);
                if (!inputStatus.isOK()) {
                    if (isConnector) {
                        return context.createFailureStatus(
                                Messages.bind(Messages.Validation_InconsistentConnectorDefAndConfigurationInput,
                                        new Object[] { connector.getName(),
                                                connector.getDefinitionId() + "--" + connector.getDefinitionVersion(),
                                                inputStatus.getMessage() }));
                    } else {
                        return context.createFailureStatus(
                                Messages.bind(Messages.Validation_InconsistentActorDefAndConfigurationInput,
                                        new Object[] { connector.getName(),
                                                connector.getDefinitionId() + "--" + connector.getDefinitionVersion(),
                                                inputStatus.getMessage() }));
                    }
                }
            }
        }
        return context.createSuccessStatus();
    }

    protected AbstractDefFileStore getDefFileStore(AbstractDefinitionRepositoryStore<?> connectorDefStore,
            ConnectorDefinition def) {
        return (AbstractDefFileStore) connectorDefStore.getChild(URI.decode(def.eResource().getURI().lastSegment()), true);
    }

    protected IStatus checkInputConsistency(ConnectorConfiguration configuration, ConnectorDefinition def,
            IValidationContext context) {
        List<Input> inputs = def.getInput();
        Set<String> inputNames = new HashSet<>();
        for (Input in : inputs) {
            inputNames.add(in.getName());
        }
        Set<String> connectorParamKey = new HashSet<>();
        for (ConnectorParameter parameter : configuration.getParameters()) {
            connectorParamKey.add(parameter.getKey());
        }
        if (inputNames.equals(connectorParamKey)) {
            return context.createSuccessStatus();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(checkNewMandatoryInputs(inputs, connectorParamKey).toString());
        sb.append(checkRemovedInputs(inputs, connectorParamKey).toString());

        if (sb.length() > 1) {
            sb = sb.delete(sb.length() - 2, sb.length());
        }
        if (sb.length() == 0) {
            return context.createSuccessStatus();
        }
        return context.createFailureStatus(sb.toString());
    }

    protected StringBuilder checkRemovedInputs(List<Input> inputs, Set<String> connectorParamKey) {
        StringBuilder sb = new StringBuilder();
        Set<String> inputNames = new HashSet<>();
        for (Input in : inputs) {
            inputNames.add(in.getName());
        }
        for (String key : connectorParamKey) {
            if (!inputNames.contains(key)) {
                sb.append(key);
                sb.append("(" + Messages.removed + "), ");
            }
        }
        return sb;
    }

    protected StringBuilder checkNewMandatoryInputs(List<Input> inputs, Set<String> connectorParamKey) {
        StringBuilder sb = new StringBuilder();
        Set<String> inputNames = new HashSet<>();
        for (Input in : inputs) {
            inputNames.add(in.getName());
        }
        inputNames.removeAll(connectorParamKey);
        for (Input in : inputs) {
            if (inputNames.contains(in.getName())
                    && !in.isMandatory()) {
                inputNames.remove(in.getName());
            }
        }
        for (String name : inputNames) {
            sb.append(name);
            sb.append("(" + Messages.mandatory + "), ");
        }
        return sb;
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
