/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.engine.api.ApiAccessType;
import org.bonitasoft.engine.util.APITypeManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;

/**
 * @author Romain Bioteau
 */
public class BonitaHomeUtil {

    public static final String HTTP = "HTTP";
    private static final String SERVER_URL = "server.url";
    private static final String APPLICATION_NAME = "application.name";
    private static final String BONITA_APPLICATION = "bonita";
    private static final String BONITA_CLIENT_HOST_DEFAULT = "bonita.client.host.default";
    private static final String BONITA_CLIENT_PORT_DEFAULT = "bonita.client.port.default";

    public static synchronized void configureBonitaClient() {
        try {
            final String host = System.getProperty(BONITA_CLIENT_HOST_DEFAULT, "localhost");
            final int serverPort = Integer
                    .parseInt(System.getProperty(BONITA_CLIENT_PORT_DEFAULT, "8080"));
            BonitaStudioLog.debug("Configuring bonita client on host " + host + ":" + serverPort + " with API_TYPE=" + HTTP,
                    Activator.PLUGIN_ID);
            final Map<String, String> parameters = new HashMap<>();
            parameters.put(SERVER_URL, "http://" + host + ":" + serverPort);
            parameters.put(APPLICATION_NAME, BONITA_APPLICATION);
            parameters.put("basicAuthentication.active", "true");
            parameters.put("basicAuthentication.username", "http-api");
            parameters.put("basicAuthentication.password", "h11p-@p1");
            APITypeManager.setAPITypeAndParams(ApiAccessType.valueOf(HTTP), parameters);
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
    }

}
