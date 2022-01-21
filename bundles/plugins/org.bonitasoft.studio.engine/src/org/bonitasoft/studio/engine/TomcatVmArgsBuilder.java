/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.engine;

import java.io.File;
import java.net.InetAddress;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.DatabaseHandler;
import org.bonitasoft.studio.engine.preferences.EnginePreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;

public class TomcatVmArgsBuilder {

    private static final String HEALTH_CHECK_POLLING_INTERVAL = "org.bonitasoft.studio.healthcheck.interval";
    protected static final String STUDIO_HEALTHCHECK_ENDPOINT_PROPERTY = "org.bonitasoft.studio.healthcheck.endpoint";
    protected static final String BONITA_WEB_REGISTER = "bonita.web.register";

    private final RepositoryAccessor repositoryAccessor;
    private IPreferenceStore enginePreference;

    public TomcatVmArgsBuilder(final RepositoryAccessor repositoryAccessor, IPreferenceStore enginePreference) {
        this.repositoryAccessor = repositoryAccessor;
        this.enginePreference = enginePreference;
    }

    public String getVMArgs(final String bundleLocation) {
        final StringBuilder args = new StringBuilder();
        addMemoryOptions(args);
        final String tomcatExtraParams = enginePreference.getString(EnginePreferenceConstants.TOMCAT_EXTRA_PARAMS);
        if (tomcatExtraParams != null) {
            args.append(" " + tomcatExtraParams);
        }
        final String tomcatInstanceLocation = bundleLocation + File.separatorChar + "server";
        addSystemProperty(args, "catalina.home", "\"" + tomcatInstanceLocation + "\"");
        addSystemProperty(args, "CATALINA_HOME", "\"" + tomcatInstanceLocation + "\"");
        addSystemProperty(args, "CATALINA_TMPDIR", "\"" + tomcatInstanceLocation + File.separatorChar + "temp\"");
        addSystemProperty(args, "java.io.tmpdir", "\"" + tomcatInstanceLocation + File.separatorChar + "temp\"");
        addSystemProperty(args, "btm.root", "\"" + tomcatInstanceLocation + "\"");
        addSystemProperty(args, "wtp.deploy", "\"" + tomcatInstanceLocation + File.separatorChar + "webapps\"");
        addSystemProperty(args, "sysprop.bonita.db.vendor", "h2");
        addSystemProperty(args, "sysprop.bonita.bdm.db.vendor", "h2");
        addSystemProperty(args, "org.bonitasoft.platform.setup.folder",
                "\"" + bundleLocation + File.separatorChar + "setup\"");
        addSystemProperty(args, "org.bonitasoft.engine.incident.folder",
                "\"" + tomcatInstanceLocation + File.separatorChar + "logs\"");
        addSystemProperty(args, "java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
        if (tomcatExtraParams == null || !tomcatExtraParams.contains("-Dlog4j.configurationFile=")) {
            addSystemProperty(args, "log4j.configurationFile",
                    "\"" + tomcatInstanceLocation + File.separatorChar + "conf" + File.separatorChar
                            + "log4j2-appenders.xml,"+ tomcatInstanceLocation + File.separatorChar + "conf" + File.separatorChar
                            + "log4j2-loggers.xml\"");
        }
        addSystemProperty(args, "com.arjuna.ats.arjuna.common.propertiesFile", "\"" + tomcatInstanceLocation + File.separatorChar + "conf" + File.separatorChar + "jbossts-properties.xml\"");
        addSystemProperty(args, "file.encoding", "UTF-8");
        addStudioHealthCheckEndpointProperties(args);
        addSystemProperty(args, "eclipse.product", getProductApplicationId());
        addSystemProperty(args, BONITA_WEB_REGISTER, System.getProperty(BONITA_WEB_REGISTER, "1"));
        addSystemProperty(args, DatabaseHandler.DB_LOCATION_PROPERTY,
                "\"" + getDBLocation().getAbsolutePath() + "\"");
        addSystemProperty(args, "bonita.csrf.cookie.path", "\"/\"");
        addSystemProperty(args, "bonita.runtime.logger.sysout", "Console");
        final String res = args.toString();
        if (System.getProperty("log.tomcat.vm.args") != null) {
            BonitaStudioLog.info(res, EnginePlugin.PLUGIN_ID);
        }
        return res;
    }

    public File getDBLocation() {
        return repositoryAccessor.getCurrentRepository().getDatabaseHandler().getDBLocation();
    }

    public String getProductApplicationId() {
        return Platform.getProduct() != null ? Platform.getProduct().getApplication() : null;
    }

    public void addStudioHealthCheckEndpointProperties(final StringBuilder args) {
        addSystemProperty(args, STUDIO_HEALTHCHECK_ENDPOINT_PROPERTY, String.format("http://%s:%s/api/workspace/status/",
                InetAddress.getLoopbackAddress().getHostAddress(), 
                BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getInt(BonitaPreferenceConstants.HEALTHCHECK_SERVER_PORT)));
        addSystemProperty(args, HEALTH_CHECK_POLLING_INTERVAL, System.getProperty(HEALTH_CHECK_POLLING_INTERVAL, "20000"));
    }

    protected void addMemoryOptions(final StringBuilder args) {
        args.append(String.format("-Xmx%sm", enginePreference.getInt(EnginePreferenceConstants.TOMCAT_XMX_OPTION)));
    }

    protected void addSystemProperty(final StringBuilder sBuilder, final String key, final String value) {
        sBuilder.append(" ");
        sBuilder.append("-D" + key + "=" + value);
    }

    protected void addSystemProperty(final StringBuilder sBuilder, final String systemPropertyArgument) {
        sBuilder.append(" ");
        sBuilder.append(systemPropertyArgument);
    }

}
