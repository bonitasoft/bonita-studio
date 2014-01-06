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
import java.net.URLConnection;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Properties;

import org.eclipse.core.runtime.adaptor.EclipseStarter;
import org.eclipse.core.runtime.adaptor.LocationManager;
import org.eclipse.equinox.log.internal.LogServiceManager;
import org.eclipse.osgi.baseadaptor.BaseAdaptor;
import org.eclipse.osgi.baseadaptor.HookConfigurator;
import org.eclipse.osgi.baseadaptor.HookRegistry;
import org.eclipse.osgi.baseadaptor.hooks.AdaptorHook;
import org.eclipse.osgi.framework.internal.core.Constants;
import org.eclipse.osgi.framework.internal.core.FrameworkProperties;
import org.eclipse.osgi.framework.log.FrameworkLog;
import org.eclipse.osgi.internal.baseadaptor.AdaptorUtil;
import org.eclipse.osgi.service.datalocation.Location;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceRegistration;


/**
 * @author Romain Bioteau
 *
 */
public class BOSLogHook implements HookConfigurator, AdaptorHook {

    static final String EQUINOX_LOGGER_NAME = "org.eclipse.equinox.logger"; //$NON-NLS-1$
    static final String PERF_LOGGER_NAME = "org.eclipse.performance.logger"; //$NON-NLS-1$
    private static final String PROP_LOG_ENABLED = "eclipse.log.enabled"; //$NON-NLS-1$

    // The eclipse log file extension */
    private static final String LOG_EXT = ".log"; //$NON-NLS-1$
    private final LogServiceManager logServiceManager;
    private final BOSLogFactory bosLogFactory;
    private final BOSLogWriter logWriter;
    private final BOSLogWriter perfWriter;

    public BOSLogHook() {
        String logFileProp = FrameworkProperties.getProperty(EclipseStarter.PROP_LOGFILE);
        boolean enabled = "true".equals(FrameworkProperties.getProperty(PROP_LOG_ENABLED, "true")); //$NON-NLS-1$ //$NON-NLS-2$
        if (logFileProp != null) {
            logWriter = new BOSLogWriter(new File(logFileProp), EQUINOX_LOGGER_NAME, enabled);
        } else {
            Location location = LocationManager.getConfigurationLocation();
            File configAreaDirectory = null;
            if (location != null) {
                // TODO assumes the URL is a file: url
                configAreaDirectory = new File(location.getURL().getFile());
            }

            if (configAreaDirectory != null) {
                String logFileName = Long.toString(System.currentTimeMillis()) + LOG_EXT;
                File logFile = new File(configAreaDirectory, logFileName);
                FrameworkProperties.setProperty(EclipseStarter.PROP_LOGFILE, logFile.getAbsolutePath());
                logWriter = new BOSLogWriter(logFile, EQUINOX_LOGGER_NAME, enabled);
            } else {
                logWriter = new BOSLogWriter((Writer) null, EQUINOX_LOGGER_NAME, enabled);
            }
        }

        File logFile = logWriter.getFile();
        if (logFile != null) {
            File perfLogFile = new File(logFile.getParentFile(), "performance.log"); //$NON-NLS-1$
            perfWriter = new BOSLogWriter(perfLogFile, PERF_LOGGER_NAME, true);
        } else {
            perfWriter = new BOSLogWriter((Writer) null, PERF_LOGGER_NAME, true);
        }
        if ("true".equals(FrameworkProperties.getProperty(EclipseStarter.PROP_CONSOLE_LOG))) {
            logWriter.setConsoleLog(true);
        }
        logServiceManager = new LogServiceManager(logWriter, perfWriter);
        bosLogFactory = new BOSLogFactory(logWriter, logServiceManager);

    }

    private ServiceRegistration<?> frameworkLogReg;
    private ServiceRegistration<?> perfLogReg;

    @Override
    public void addHooks(HookRegistry hookRegistry) {
        hookRegistry.addAdaptorHook(this);
    }

    @Override
    public void initialize(BaseAdaptor initAdaptor) {
        // Nothing
    }

    /**
     * @throws BundleException
     */
    @Override
    public void frameworkStart(BundleContext context) throws BundleException {
        logServiceManager.start(context);
        frameworkLogReg = AdaptorUtil.register(FrameworkLog.class.getName(), bosLogFactory, context);
        perfLogReg = registerPerformanceLog(context);
    }

    /**
     * @throws BundleException
     */
    @Override
    public void frameworkStop(BundleContext context) throws BundleException {
        frameworkLogReg.unregister();
        perfLogReg.unregister();
        logServiceManager.stop(context);
    }

    @Override
    public void frameworkStopping(BundleContext context) {
        // do nothing

    }

    @Override
    public void addProperties(Properties properties) {
        // do nothing
    }

    /**
     * @throws IOException
     */
    @Override
    public URLConnection mapLocationToURLConnection(String location) throws IOException {
        // do nothing
        return null;
    }

    @Override
    public void handleRuntimeError(Throwable error) {
        // do nothing
    }

    @Override
    public FrameworkLog createFrameworkLog() {
        return bosLogFactory.createFrameworkLog(null, logWriter);
    }

    private ServiceRegistration<?> registerPerformanceLog(BundleContext context) {
        Object service = createPerformanceLog(context.getBundle());
        String serviceName = FrameworkLog.class.getName();
        Dictionary<String, Object> serviceProperties = new Hashtable<String, Object>(7);
        Dictionary<String, String> headers = context.getBundle().getHeaders();

        serviceProperties.put(Constants.SERVICE_VENDOR, headers.get(Constants.BUNDLE_VENDOR));
        serviceProperties.put(Constants.SERVICE_RANKING, new Integer(Integer.MIN_VALUE));
        serviceProperties.put(Constants.SERVICE_PID, context.getBundle().getBundleId() + '.' + service.getClass().getName());
        serviceProperties.put(FrameworkLog.SERVICE_PERFORMANCE, Boolean.TRUE.toString());

        return context.registerService(serviceName, service, serviceProperties);
    }

    private FrameworkLog createPerformanceLog(Bundle systemBundle) {
        return bosLogFactory.createFrameworkLog(systemBundle, perfWriter);
    }
}
