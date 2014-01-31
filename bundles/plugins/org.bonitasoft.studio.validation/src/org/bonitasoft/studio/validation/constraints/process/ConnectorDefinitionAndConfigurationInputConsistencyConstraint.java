package org.bonitasoft.studio.validation.constraints.process;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.AbstractDefinitionRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.process.ActorFilter;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.validation.IValidationContext;

public class ConnectorDefinitionAndConfigurationInputConsistencyConstraint extends AbstractLiveValidationMarkerConstraint {

	public static final String ID = "org.bonitasoft.studio.validation.constraints.connectorDefAndConfigInputConsistency";

	@Override
	protected IStatus performLiveValidation(IValidationContext context) {
		return null;
	}

	@Override
	protected IStatus performBatchValidation(IValidationContext context) {
		Connector connector = (Connector)context.getTarget();
		AbstractDefinitionRepositoryStore<?> connectorDefStore = null;
		boolean isConnector = true;
		if(!(connector instanceof ActorFilter)){
			connectorDefStore = getConnectorDefinitionRepositoryStore();
		}else{
			isConnector = false;
			connectorDefStore = getActorFilterDefinitionStore();
		}
		ConnectorConfiguration configuration = connector.getConfiguration();
		if(configuration == null){
			return context.createSuccessStatus();
		}
		ConnectorDefinition def = connectorDefStore.getDefinition(configuration.getDefinitionId(),configuration.getVersion());
		if (def!=null){
			IStatus inputStatus = checkInputConsistency(configuration, def,context);
			if(!inputStatus.isOK()){
				if(isConnector){
					return context.createFailureStatus( Messages.bind(Messages.Validation_InconsistentConnectorDefAndConfigurationInput,new Object[]{connector.getName(),connector.getDefinitionId()+"--"+connector.getDefinitionVersion(),inputStatus.getMessage()}));
				}else{
					return context.createFailureStatus( Messages.bind(Messages.Validation_InconsistentActorDefAndConfigurationInput,new Object[]{connector.getName(),connector.getDefinitionId()+"--"+connector.getDefinitionVersion(),inputStatus.getMessage()}));
				}
			}
		}
		return context.createSuccessStatus();
	}

	protected IStatus checkInputConsistency(ConnectorConfiguration configuration,ConnectorDefinition def, IValidationContext context) {
		EList<Input> inputs = def.getInput();
		Set<String> inputNames = new HashSet<String>(); 
		for(Input in : inputs){
			inputNames.add(in.getName());
		}
		Set<String> connectorParamKey = new HashSet<String>(); 
		for(ConnectorParameter parameter : configuration.getParameters()){
			connectorParamKey.add(parameter.getKey());
		}

		StringBuilder sb = new StringBuilder();
		if(inputNames.equals(connectorParamKey)){
			return context.createSuccessStatus();
		}else if(inputNames.size() > connectorParamKey.size()){
			inputNames.removeAll(connectorParamKey);
			for(Input in : inputs){
				if(inputNames.contains(in.getName())
						&& !in.isMandatory()){
					inputNames.remove(in.getName());
				}
			}
			if(inputNames.isEmpty()){//No new mandatory input
				return context.createSuccessStatus();
			}else{
				for(String name : inputNames){
					sb.append(name);
					sb.append(", ");
				}
			}
		}
		for(String key : connectorParamKey){
			if(!inputNames.contains(key)){
				sb.append(key);
				sb.append(", ");
			}
		}
		if(sb.length() > 1 ){
			sb = sb.delete(sb.length()-2, sb.length());
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
