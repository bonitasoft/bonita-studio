package org.bonitasoft.studio.validation.constraints.connector;

import java.util.Objects;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.provider.ConnectorDefinitionRegistry;
import org.bonitasoft.studio.connector.model.definition.AbstractDefinitionRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.model.process.ActorFilter;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.osgi.util.NLS;

public class ConnectorExistenceConstraint extends AbstractLiveValidationMarkerConstraint {

    private static final String JASPER_DEF_ID = "jasper";
    public static final String ID = "org.bonitasoft.studio.validation.constraints.connectorexistence";

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
            connectorDefStore = RepositoryManager
                    .getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
        } else {
            isConnector = false;
            connectorDefStore = (AbstractDefinitionRepositoryStore<?>) RepositoryManager
                    .getInstance().getRepositoryStore(ActorFilterDefRepositoryStore.class);
        }
        var registry = connectorDefStore.getResourceProvider()
                .getConnectorDefinitionRegistry();
        ConnectorDefinition def = registry
                .find(connector.getDefinitionId(), connector.getDefinitionVersion())
                .orElse(null);
        if (def != null) {
            return context.createSuccessStatus();
        } else {
            if (isConnector) {
                if (JASPER_DEF_ID.equals(connector.getDefinitionId())) {
                    return context.createFailureStatus(
                            String.format(Messages.Validation_jasperConnectorRemoved, connector.getName()));
                } else {
                    if (otherVersionAvailable(connector, registry)) {
                        return context.createFailureStatus(NLS.bind(Messages.Validation_ConnectorDefUpdateRequired,
                                connector.getName(),
                                connector.getDefinitionId() + "--" + connector.getDefinitionVersion()));
                    }
                    return context.createFailureStatus(NLS.bind(Messages.Validation_noConnectorDefFound,
                            connector.getName(),
                            connector.getDefinitionId() + "--" + connector.getDefinitionVersion()));
                }

            } else {
                if (otherVersionAvailable(connector, registry)) {
                    return context.createFailureStatus(NLS.bind(Messages.Validation_ActorFilterDefUpdateRequired,
                            connector.getName(),
                            connector.getDefinitionId() + "--" + connector.getDefinitionVersion()));
                }
                return context.createFailureStatus(NLS.bind(Messages.Validation_noActorFilterDefFound,
                        connector.getName(), connector.getDefinitionId() + "--" + connector.getDefinitionVersion()));
            }

        }
    }

    private boolean otherVersionAvailable(Connector connector, ConnectorDefinitionRegistry registry) {
        return registry.getDefinitions().stream()
                .anyMatch(def -> Objects.equals(connector.getDefinitionId(), def.getId()));
    }

    @Override
    protected String getConstraintId() {
        return ID;
    }

}
