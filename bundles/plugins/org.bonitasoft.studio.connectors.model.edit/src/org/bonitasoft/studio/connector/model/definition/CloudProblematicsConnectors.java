/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.connector.model.definition;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.connector.model.i18n.Messages;

public class CloudProblematicsConnectors {

    public static final Map<String, String> WARNING_CONNECTORS = new HashMap<>();

    static {
        // system script
        WARNING_CONNECTORS.put("scripting-shell", Messages.connectorScriptingShellForbidden);

        // SAP
        WARNING_CONNECTORS.put("sap-jco3-callfunction", Messages.jarSapConnector);
    }

}
