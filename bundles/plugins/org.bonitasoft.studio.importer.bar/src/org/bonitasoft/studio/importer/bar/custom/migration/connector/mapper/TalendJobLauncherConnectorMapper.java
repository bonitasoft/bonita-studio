package org.bonitasoft.studio.importer.bar.custom.migration.connector.mapper;

import org.bonitasoft.studio.connectors.extension.AbstractConnectorDefinitionMapper;
import org.bonitasoft.studio.connectors.extension.IConnectorDefinitionMapper;

public class TalendJobLauncherConnectorMapper extends
		AbstractConnectorDefinitionMapper implements IConnectorDefinitionMapper {

	private static final String TALEND_JOB_LAUNCHER_ID = "talend-job-launcher";
	private static final String LEGACY_TALEND_JOB_LAUNCHER_ID="TalendJobLauncher";
	
	@Override
	public String getDefinitionId() {
		// TODO Auto-generated method stub
		return TALEND_JOB_LAUNCHER_ID;
	}

	@Override
	public String getLegacyConnectorId() {
		// TODO Auto-generated method stub
		return LEGACY_TALEND_JOB_LAUNCHER_ID;
	}

}
