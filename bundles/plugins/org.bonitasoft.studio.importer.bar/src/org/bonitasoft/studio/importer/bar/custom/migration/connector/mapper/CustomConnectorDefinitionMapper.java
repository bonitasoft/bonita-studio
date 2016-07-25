/**
 * 
 */
package org.bonitasoft.studio.importer.bar.custom.migration.connector.mapper;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connectors.extension.AbstractConnectorDefinitionMapper;
import org.bonitasoft.studio.connectors.repository.ConnectorDefFileStore;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;

/**
 * @author Romain
 *
 */
public class CustomConnectorDefinitionMapper extends AbstractConnectorDefinitionMapper {

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.connectors.extension.IConnectorDefinitionMapper#getDefinitionId()
	 */
	@Override
	public String getDefinitionId() {
		if(definition != null){
			return definition.getId();
		}
		return "";
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.connectors.extension.IConnectorDefinitionMapper#getLegacyConnectorId()
	 */
	@Override
	public String getLegacyConnectorId() {
		return "";
	}
	
	@Override
	public boolean appliesTo(String legacyConnectorId) {
		ConnectorDefRepositoryStore connectorDefStore = (ConnectorDefRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
		for(ConnectorDefinition def : connectorDefStore.getDefinitions()){
			if(legacyConnectorId.equals(def.getId())){
				this.definition = def;
				ConnectorDefFileStore fileStore = connectorDefStore.getChild(NamingUtils.toConnectorDefinitionFilename(this.definition.getId(), this.definition.getVersion(), true));
				if(fileStore != null && !fileStore.isReadOnly()){
					break;
				}else{
					this.definition = null;
				}
			}
		}
		return this.definition != null;
	}

}
