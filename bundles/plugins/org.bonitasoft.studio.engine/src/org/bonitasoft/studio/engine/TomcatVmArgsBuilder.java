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
import java.io.IOException;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.DatabaseHandler;
import org.bonitasoft.studio.engine.preferences.EnginePreferenceConstants;
import org.bonitasoft.studio.engine.server.WatchdogManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;

public class TomcatVmArgsBuilder {

    private static final String WATCHDOG_TIMER = "org.bonitasoft.studio.watchdog.timer";
    protected static final String WATCHDOG_PORT_PROPERTY = "org.bonitasoft.studio.watchdog.port";
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
        addSystemProperty(args, "org.bonitasoft.platform.setup.folder",
                "\"" + bundleLocation + File.separatorChar + "setup\"");
        addSystemProperty(args, "org.bonitasoft.engine.incident.folder",
                "\"" + tomcatInstanceLocation + File.separatorChar + "logs\"");
        addSystemProperty(args, "bitronix.tm.configuration",
                "\"" + tomcatInstanceLocation + File.separatorChar + "conf" + File.separatorChar
                        + "bitronix-config.properties\"");
        addSystemProperty(args, "java.util.logging.manager", "org.apache.juli.ClassLoaderLogManager");
        if (tomcatExtraParams == null || !tomcatExtraParams.contains("-Djava.util.logging.config.file=")) {
            addSystemProperty(args, "java.util.logging.config.file",
                    "\"" + tomcatInstanceLocation + File.separatorChar + "conf" + File.separatorChar
                            + "logging.properties\"");
        }
        addSystemProperty(args, "file.encoding", "UTF-8");
        addWatchDogProperties(args);
        addSystemProperty(args, "eclipse.product", getProductApplicationId());
        addSystemProperty(args, BONITA_WEB_REGISTER, System.getProperty(BONITA_WEB_REGISTER, "1"));
        addSystemProperty(args, DatabaseHandler.DB_LOCATION_PROPERTY,
                "\"" + getDBLocation().getAbsolutePath() + "\"");
        addSystemProperty(args, "bonita.csrf.cookie.path", "\"/\"");
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

    public void addWatchDogProperties(final StringBuilder args) {
        addSystemProperty(args, WATCHDOG_PORT_PROPERTY, String.valueOf(WatchdogManager.WATCHDOG_PORT));
        addSystemProperty(args, WATCHDOG_TIMER, System.getProperty(WATCHDOG_TIMER, "20000"));
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
