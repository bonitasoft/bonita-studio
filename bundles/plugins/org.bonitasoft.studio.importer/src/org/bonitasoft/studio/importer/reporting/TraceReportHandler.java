package org.bonitasoft.studio.importer.reporting;

import org.bonitasoft.studio.common.log.BonitaStudioLog;

public class TraceReportHandler implements ReportHandler {
	
    @Override
    public void traceImport(String source, String version) {
    	BonitaStudioLog.log("Trace: source= "+source+", version= "+ version);
    }
}