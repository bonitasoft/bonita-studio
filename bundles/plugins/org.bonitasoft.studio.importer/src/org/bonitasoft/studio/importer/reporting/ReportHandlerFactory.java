package org.bonitasoft.studio.importer.reporting;

public class ReportHandlerFactory {
	
    public static ReportHandler getHandler() {
        return new TraceReportHandler();
   }
}
