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
import java.net.Inet6Address;
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

    // Keep both literals in sync with StudioWatchdogListener (bonita-studio-watchdog): the WAR
    // is a separate build world and cannot share the constants. A rename here would not fail
    // loudly - the watchdog would disarm with only a catalina.log line and every runtime would
    // outlive a Studio death. TomcatVmArgsBuilderTest pins the two -D literals for that reason.
    private static final String HEALTH_CHECK_POLLING_INTERVAL = "org.bonitasoft.studio.healthcheck.interval";
    protected static final String STUDIO_HEALTHCHECK_ENDPOINT_PROPERTY = "org.bonitasoft.studio.healthcheck.endpoint";
    protected static final String BONITA_WEB_REGISTER = "bonita.web.register";
    protected static final String BONITA_WEB_GET_LOGIN_ENABLED = "org.bonitasoft.web.login.get.enabled";

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
        // AFTER tomcatExtraParams on purpose: the last -D wins, so a user-supplied endpoint or
        // interval in TOMCAT_EXTRA_PARAMS cannot redirect the watchdog probe. Keep this call
        // below the extra params if this method is ever reordered
        addStudioHealthCheckEndpointProperties(args);
        addSystemProperty(args, "eclipse.product", getProductApplicationId());
        addSystemProperty(args, BONITA_WEB_REGISTER, System.getProperty(BONITA_WEB_REGISTER, "1"));
        //Enable login with GET method when opening Bonita application (deactivated by default for security reasons)
        addSystemProperty(args, BONITA_WEB_GET_LOGIN_ENABLED, Boolean.TRUE.toString());
        addSystemProperty(args, DatabaseHandler.DB_LOCATION_PROPERTY,
                "\"" + getDBLocation().getAbsolutePath() + "\"");
        addSystemProperty(args, "bonita.csrf.cookie.path", "\"/\"");
        addSystemProperty(args, "bonita.runtime.logger.sysout", "Console");
        var res = args.toString();
        // Additional jvm args for Hazelcast
        res = res + " --add-modules java.se";
        res = res + " --add-exports java.base/jdk.internal.ref=ALL-UNNAMED";
        res = res + " --add-opens java.base/java.lang=ALL-UNNAMED";
        res = res + " --add-opens java.base/sun.nio.ch=ALL-UNNAMED";
        res = res + " --add-opens java.management/sun.management=ALL-UNNAMED";
        res = res + " --add-opens jdk.management/com.sun.management.internal=ALL-UNNAMED";
        // Additional jvm args for XStream serialization
        res = res + " --add-opens=java.xml/javax.xml.namespace=ALL-UNNAMED";
        res = res + " --add-opens=java.xml/com.sun.org.apache.xerces.internal.dom=ALL-UNNAMED";
        res = res + " --add-opens=java.xml/com.sun.org.apache.xerces.internal.xni=ALL-UNNAMED";
        res = res + " --add-opens=java.base/java.time=ALL-UNNAMED";
        res = res + " --add-opens=java.base/java.time.chrono=ALL-UNNAMED";
        res = res + " --add-opens=java.base/java.text=ALL-UNNAMED";
        if (System.getProperty("log.tomcat.vm.args") != null) {
            BonitaStudioLog.info(res, EnginePlugin.PLUGIN_ID);
        }
        return res;
    }

    public File getDBLocation() {
        return repositoryAccessor.getCurrentRepository()
                .orElseThrow()
                .getDatabaseHandler()
                .getDBLocation();
    }

    public String getProductApplicationId() {
        return Platform.getProduct() != null ? Platform.getProduct().getApplication() : null;
    }

    public void addStudioHealthCheckEndpointProperties(final StringBuilder args) {
        // null-guarded like HealthCheckServerManager.updatePreference, and falling back to the
        // port the branch below reports: no preferences bundle means a disarmed watchdog, never
        // an endpoint built from a value nobody validated
        var preferences = BonitaStudioPreferencesPlugin.getDefault();
        int healthCheckPort = preferences == null ? 0
                : preferences.getPreferenceStore().getInt(BonitaPreferenceConstants.HEALTHCHECK_SERVER_PORT);
        if (healthCheckPort <= 0) {
            // 0 means the healthcheck server is not running. The watchdog disarms on its side, but
            // only into catalina.log - surface it where a user looks.
            //
            // The property is still EMITTED, unlike UIDesignerServerManager which omits its URL on
            // the same preference: these -D come after TOMCAT_EXTRA_PARAMS so a user-supplied
            // endpoint can never win, and omitting ours would hand a spoofed one the last word.
            // Port 0 is a value parseEndpoint rejects, so emitting it disarms just as firmly
            BonitaStudioLog.error(String.format(
                    "Studio healthcheck port preference is %s: the watchdog in the Tomcat runtime"
                            + " will be disarmed and an orphaned runtime may survive a Studio crash.",
                    healthCheckPort), EnginePlugin.PLUGIN_ID);
        }
        addSystemProperty(args, STUDIO_HEALTHCHECK_ENDPOINT_PROPERTY, String.format("http://%s:%s" + BonitaPreferenceConstants.HEALTHCHECK_ENDPOINT_PATH,
                healthCheckEndpointHost(InetAddress.getLoopbackAddress()),
                healthCheckPort));
        addSystemProperty(args, HEALTH_CHECK_POLLING_INTERVAL, System.getProperty(HEALTH_CHECK_POLLING_INTERVAL, "20000"));
    }

    /**
     * Loopback literal for the healthcheck URL, bracketed when it is IPv6: an unbracketed IPv6
     * literal makes the authority non server-based, and the watchdog then disarms because the
     * URL it is given has no parseable host.
     */
    static String healthCheckEndpointHost(InetAddress loopback) {
        return loopback instanceof Inet6Address ? "[" + loopback.getHostAddress() + "]"
                : loopback.getHostAddress();
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
