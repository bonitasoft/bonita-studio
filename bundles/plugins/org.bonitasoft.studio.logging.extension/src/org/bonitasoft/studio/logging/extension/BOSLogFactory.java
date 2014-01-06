/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.logging.extension;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import org.eclipse.equinox.log.Logger;
import org.eclipse.equinox.log.internal.LogServiceManager;
import org.eclipse.osgi.framework.log.FrameworkLog;
import org.eclipse.osgi.framework.log.FrameworkLogEntry;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkEvent;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.log.LogService;


/**
 * @author Romain Bioteau
 *
 */
public class BOSLogFactory implements ServiceFactory<FrameworkLog> {


    final BOSLogWriter defaultWriter;
    final LogServiceManager logManager;

    public BOSLogFactory(BOSLogWriter defaultWriter, LogServiceManager logManager) {
        this.defaultWriter = defaultWriter;
        this.logManager = logManager;
    }

    @Override
    public FrameworkLog getService(final Bundle bundle, ServiceRegistration<FrameworkLog> registration) {
        return createFrameworkLog(bundle, defaultWriter);
    }

    FrameworkLog createFrameworkLog(Bundle bundle, BOSLogWriter eclipseWriter) {
        final BOSLogWriter logWriter = eclipseWriter == null ? defaultWriter : eclipseWriter;
        final Logger logger = bundle == null ? logManager.getSystemBundleLog().getLogger(eclipseWriter.getLoggerName()) : logManager.getSystemBundleLog().getLogger(bundle, logWriter.getLoggerName());
        return new FrameworkLog() {

            @Override
            public void setWriter(Writer newWriter, boolean append) {
                logWriter.setWriter(newWriter, append);
            }

            @Override
            public void setFile(File newFile, boolean append) throws IOException {
                //                final File newLogFile =  new File(newFile.getParent(),"bonita."+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+".log");
                //                if(newLogFile.exists()){
                //                    logWriter.setFile(newLogFile, true );
                //                }else{
                //                    logWriter.setFile(newLogFile, false);
                //                }
                logWriter.setFile(newFile, append);
            }

            @Override
            public void setConsoleLog(boolean consoleLog) {
                logWriter.setConsoleLog(consoleLog);
            }

            @Override
            public void log(FrameworkLogEntry logEntry) {
                logger.log(logEntry, convertLevel(logEntry), logEntry.getMessage(), logEntry.getThrowable());
            }

            @Override
            public void log(FrameworkEvent frameworkEvent) {
                Bundle b = frameworkEvent.getBundle();
                Throwable t = frameworkEvent.getThrowable();
                String entry = b.getSymbolicName() == null ? b.getLocation() : b.getSymbolicName();
                int severity;
                switch (frameworkEvent.getType()) {
                    case FrameworkEvent.INFO :
                        severity = FrameworkLogEntry.INFO;
                        break;
                    case FrameworkEvent.ERROR :
                        severity = FrameworkLogEntry.ERROR;
                        break;
                    case FrameworkEvent.WARNING :
                        severity = FrameworkLogEntry.WARNING;
                        break;
                    default :
                        severity = FrameworkLogEntry.OK;
                }
                FrameworkLogEntry logEntry = new FrameworkLogEntry(entry, severity, 0, "", 0, t, null); //$NON-NLS-1$
                log(logEntry);
            }

            @Override
            public File getFile() {
                return logWriter.getFile();
            }

            @Override
            public void close() {
                logWriter.close();
            }
        };
    }

    @Override
    public void ungetService(Bundle bundle, ServiceRegistration<FrameworkLog> registration, FrameworkLog service) {
        // nothing
    }

    static int convertLevel(FrameworkLogEntry logEntry) {
        switch (logEntry.getSeverity()) {
            case FrameworkLogEntry.ERROR :
                return LogService.LOG_ERROR;
            case FrameworkLogEntry.WARNING :
                return LogService.LOG_WARNING;
            case FrameworkLogEntry.INFO :
                return LogService.LOG_INFO;
            case FrameworkLogEntry.OK :
                return LogService.LOG_DEBUG;
            case FrameworkLogEntry.CANCEL :
            default :
                return 32; // unknown
        }
    }

}
