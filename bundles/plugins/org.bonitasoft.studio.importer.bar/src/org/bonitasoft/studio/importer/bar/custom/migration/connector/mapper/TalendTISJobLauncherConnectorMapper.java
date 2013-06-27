package org.bonitasoft.studio.importer.bar.custom.migration.connector.mapper;

import org.bonitasoft.studio.connectors.extension.AbstractConnectorDefinitionMapper;
import org.bonitasoft.studio.connectors.extension.IConnectorDefinitionMapper;

public class TalendTISJobLauncherConnectorMapper extends AbstractConnectorDefinitionMapper
		implements IConnectorDefinitionMapper {

	private static final String TALEND_TIS_JOB_LAUNCHER_ID ="talend-job-ws"; 
	private static final String LEGACY_TALEND_TIS_JOB_LAUNCHER_ID="TISJobLauncher";
	@Override
	public String getDefinitionId() {	
		return TALEND_TIS_JOB_LAUNCHER_ID;
	}

	@Override
	public String getLegacyConnectorId() {
		return LEGACY_TALEND_TIS_JOB_LAUNCHER_ID;
	}

	
}
