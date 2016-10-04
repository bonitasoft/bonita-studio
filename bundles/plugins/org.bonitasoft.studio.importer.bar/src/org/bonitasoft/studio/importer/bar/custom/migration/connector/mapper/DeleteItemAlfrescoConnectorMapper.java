package org.bonitasoft.studio.importer.bar.custom.migration.connector.mapper;

import org.bonitasoft.studio.connectors.extension.AbstractConnectorDefinitionMapper;
import org.bonitasoft.studio.connectors.extension.IConnectorDefinitionMapper;

public class DeleteItemAlfrescoConnectorMapper extends
		AbstractConnectorDefinitionMapper implements IConnectorDefinitionMapper {

    private static final String DELETE_ITEM_ALFRESCO_ID = "AlfrescoDeleteItemById";
	private static final String LEGACY_DELETE_ITEM_ALFRESCO_ID="AlfrescoDeleteItemById";
	
	@Override
	public String getDefinitionId() {
		return DELETE_ITEM_ALFRESCO_ID;
	}

	@Override
	public String getLegacyConnectorId() {
		return LEGACY_DELETE_ITEM_ALFRESCO_ID;
	}

}
