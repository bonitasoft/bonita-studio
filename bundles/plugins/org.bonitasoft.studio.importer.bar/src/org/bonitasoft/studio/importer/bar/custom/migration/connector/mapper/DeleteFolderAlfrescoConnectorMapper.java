package org.bonitasoft.studio.importer.bar.custom.migration.connector.mapper;

import org.bonitasoft.studio.connectors.extension.AbstractConnectorDefinitionMapper;
import org.bonitasoft.studio.connectors.extension.IConnectorDefinitionMapper;

public class DeleteFolderAlfrescoConnectorMapper extends
		AbstractConnectorDefinitionMapper implements IConnectorDefinitionMapper {

	private static final String DELETE_FOLDER_ALFRESCO_ID ="Alfresco34DeleteFileByPath";
	private static final String LEGACY_DELETE_FOLDER_ALFRESCO_ID="AlfrescoDeleteFileByPath";
	
	@Override
	public String getDefinitionId() {
		return DELETE_FOLDER_ALFRESCO_ID;
	}

	@Override
	public String getLegacyConnectorId() {
		return LEGACY_DELETE_FOLDER_ALFRESCO_ID;
	}

}
