/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.net;

import org.eclipse.wst.server.core.util.SocketUtil;

public class PortSelector {

    // A lot of ports are reserved under this value;
    public static final int MIN_PORT_NUMBER = 6697;
    // This specific port is blocked by web browser
    public static final int AMANDA_BLOCKED_PORT = 10080;

    public static final int MAX_PORT_NUMBER = 65535;
    
    private static int findUnusedPort(final int low, final int hight) {
        int port = AMANDA_BLOCKED_PORT;
        while (port == AMANDA_BLOCKED_PORT) {
            port = SocketUtil.findUnusedPort(low, hight);
        }
        return port;
    }
    
    public static int findFreePort() {
        return findUnusedPort(MIN_PORT_NUMBER, MAX_PORT_NUMBER);
    }
    
}
