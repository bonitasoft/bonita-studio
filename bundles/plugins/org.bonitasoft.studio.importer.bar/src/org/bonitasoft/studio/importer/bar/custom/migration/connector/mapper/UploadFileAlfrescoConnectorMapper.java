package org.bonitasoft.studio.importer.bar.custom.migration.connector.mapper;

import org.bonitasoft.studio.connectors.extension.AbstractConnectorDefinitionMapper;
import org.bonitasoft.studio.connectors.extension.IConnectorDefinitionMapper;

public class UploadFileAlfrescoConnectorMapper extends
		AbstractConnectorDefinitionMapper implements IConnectorDefinitionMapper {

	private static final String UPLOAD_FILE_ALFRESCO_ID="Alfresco34UploadFileByPath";
	private static final String LEGACY_UPLOAD_FILE_ALFRESCO_ID="AlfrescoUploadFileByPath";
	
	
	@Override
	public String getDefinitionId() {
		return UPLOAD_FILE_ALFRESCO_ID;
	}

	@Override
	public String getLegacyConnectorId() {
		return LEGACY_UPLOAD_FILE_ALFRESCO_ID;
	}

}
