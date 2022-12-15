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
package org.bonitasoft.studio.connectors.configuration;

import static java.util.Map.entry;

import java.util.Map;
import java.util.Set;

public class CustomConnectorDefinitionInput {

    private static final String WIZARD_MODE_INPUT = "wizardMode";
    private static final String GROOVY_FAKE_SCRIPT_EXPRESSION_INPUT = "fakeScriptExpression";
    public static final Map<String, Set<String>> CUSTOM_DEFINITION_INPUT = Map.ofEntries(
            entry("scripting-groovy-script", Set.of(GROOVY_FAKE_SCRIPT_EXPRESSION_INPUT)),
            entry("database-postgres", Set.of(WIZARD_MODE_INPUT)),
            entry("database-db2", Set.of(WIZARD_MODE_INPUT)),
            entry("database-oracle11g", Set.of(WIZARD_MODE_INPUT)),
            entry("database-h2", Set.of(WIZARD_MODE_INPUT)),
            entry("database-jdbc", Set.of(WIZARD_MODE_INPUT)),
            entry("database-postgresql92", Set.of(WIZARD_MODE_INPUT)),
            entry("database-mssqlserver", Set.of(WIZARD_MODE_INPUT)),
            entry("database-mysql", Set.of(WIZARD_MODE_INPUT)),
            entry("database-informix", Set.of(WIZARD_MODE_INPUT)),
            entry("database-teradata", Set.of(WIZARD_MODE_INPUT)),
            entry("database-hsqldb", Set.of(WIZARD_MODE_INPUT)),
            entry("database-ingres", Set.of(WIZARD_MODE_INPUT)),
            entry("database-sybase", Set.of(WIZARD_MODE_INPUT)));

}
