/**
 * Copyright (C) 2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.engine.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.bonitasoft.engine.api.ApiAccessType;
import org.bonitasoft.engine.util.APITypeManager;
import org.bonitasoft.studio.common.Activator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;

/**
 * @author Romain Bioteau
 */
public class ClientBonitaHomeBuildler {

    private static final String API_TYPE = "org.bonitasoft.engine.api-type";
    private static final String HTTP = "HTTP";
    private static final String SERVER_URL = "server.url";
    private static final String APPLICATION_NAME = "application.name";
    private static final String BONITA_APPLICATION = "bonita";

    public static ClientBonitaHomeBuildler newClientBonitaHomeBuilder() {
        return new ClientBonitaHomeBuildler();
    }

    private final Properties clientProperties;
    private String host;
    private int port;

    private ClientBonitaHomeBuildler() {
        clientProperties = new Properties();
    }

    public ClientBonitaHomeBuildler withHTTPAPIType() {
        clientProperties.setProperty(API_TYPE, HTTP);
        return this;
    }

    public ClientBonitaHomeBuildler withHost(final String host) {
        this.host = host;
        return this;
    }

    public ClientBonitaHomeBuildler withPort(final int port) {
        this.port = port;
        return this;
    }

    public void writeClientProperties() throws IOException {
        BonitaStudioLog.debug("Configuring bonita client on host " + host + ":" + port + " with API_TYPE=" + clientProperties.get(API_TYPE),
                Activator.PLUGIN_ID);
        final Map<String, String> param = new HashMap<String, String>();
            if (HTTP.equals(clientProperties.get(API_TYPE))) {
            param.put(SERVER_URL, "http://" + host + ":" + port);
            param.put(APPLICATION_NAME, BONITA_APPLICATION);
            }
        APITypeManager.setAPITypeAndParams(ApiAccessType.valueOf(clientProperties.getProperty(API_TYPE)), param);
    }
}
