/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.common;

import java.util.Map;

import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.ui.PlatformUI;

public class CommandExecutor {

    private ECommandService eCommandService;
    private EHandlerService eHandlerService;

    public Object executeCommand(String command, Map<String, Object> parameters) {
        initServices();
        ParameterizedCommand parameterizedCommand = eCommandService.createCommand(command, parameters);
        if (eHandlerService.canExecute(parameterizedCommand)) {
            return eHandlerService.executeHandler(parameterizedCommand);
        }
        throw new RuntimeException(String.format("Can't execute command %s", parameterizedCommand.getId()));
    }

    public boolean canExecute(String command, Map<String, Object> parameters) {
        initServices();
        ParameterizedCommand parameterizedCommand = eCommandService.createCommand(command, parameters);
        return eHandlerService.canExecute(parameterizedCommand);
    }

    protected void initServices() {
        if (eCommandService == null || eHandlerService == null) {
            eCommandService = PlatformUI.getWorkbench().getService(ECommandService.class);
            eHandlerService = PlatformUI.getWorkbench().getService(EHandlerService.class);
        }
    }

}
