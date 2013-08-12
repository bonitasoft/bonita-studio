package org.bonitasoft.studio.validation.constraints.process;

import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.AbstractDefinitionRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

public class ConnectorExistenceConstraint extends
		AbstractLiveValidationMarkerConstraint {

	@Override
	protected IStatus performLiveValidation(IValidationContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IStatus performBatchValidation(IValidationContext context) {
		final AbstractDefinitionRepositoryStore<?> connectorDefStore = (AbstractDefinitionRepositoryStore<?>) RepositoryManager
				.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
		Connector connector = (Connector)context.getTarget();
		ConnectorDefinition def = connectorDefStore.getDefinition(connector.getDefinitionId(),connector.getDefinitionVersion());
		if (def!=null){
			return context.createSuccessStatus();
		}  else {
		return context.createFailureStatus( Messages.bind(Messages.Validation_noConnectorDefFound,connector.getName()));
		}
	}

	@Override
	protected String getConstraintId() {
		
		return "org.bonitasoft.studio.validation.constraints.connectorexistence";
	}

}
