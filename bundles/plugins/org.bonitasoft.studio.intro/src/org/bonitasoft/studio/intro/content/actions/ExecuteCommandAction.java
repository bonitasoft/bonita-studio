/**
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.intro.content.actions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.bonitasoft.studio.common.CommandExecutor;
import org.eclipse.ui.intro.IIntroSite;
import org.eclipse.ui.intro.config.IIntroAction;

public class ExecuteCommandAction implements IIntroAction {

    private static final Set<String> FILTERED_PARAMS = new HashSet<>();
    static {
        FILTERED_PARAMS.add("id");
        FILTERED_PARAMS.add("class");
        FILTERED_PARAMS.add("pluginId");
    }
    private CommandExecutor commandExecutor = new CommandExecutor();

    @Override
    public void run(IIntroSite introSite, Properties param) {
        Map<String, Object> parameters = asMap(param);
        if(commandExecutor.canExecute(param.getProperty("id"), parameters)) {
            commandExecutor.executeCommand(param.getProperty("id"), parameters);
        }
    }

    private Map<String, Object> asMap(Properties properties) {
        Map<String, Object> result = new HashMap<String, Object>();
        properties.entrySet().stream().filter(entry -> !FILTERED_PARAMS.contains(entry.getKey()))
                .forEach(entry -> result.put((String) entry.getKey(), entry.getValue()));
        return result;
    }

}
