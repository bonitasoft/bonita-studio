package org.bonitasoft.studio.importer.bar.custom.migration.connector.mapper;

import org.bonitasoft.studio.connectors.extension.AbstractConnectorDefinitionMapper;
import org.bonitasoft.studio.connectors.extension.IConnectorDefinitionMapper;

public class CreateFolderAlfrescoConnectorMapper extends
		AbstractConnectorDefinitionMapper implements IConnectorDefinitionMapper {
	
	private static final String CREATE_FOLDER ="Alfresco34CreateFolderByPath";
	private static final String LEGACY_CREATE_FOLDER="AlfrescoCreateFolderByPath";

	@Override
	public String getDefinitionId() {
		return CREATE_FOLDER;
	}

	@Override
	public String getLegacyConnectorId() {
		return LEGACY_CREATE_FOLDER;
	}

}
