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

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.di.annotations.Creatable;

@Creatable
public class CommandExecutor {

    /**
     * This class gives access to E4 services to command executors which are not instanciated with DI.
     * 
     * @author Vincent Hemery
     */
    @Creatable
    @Singleton
    private static final class ServicesAccess {

        @Inject
        private ECommandService eCommandService;

        @Inject
        private EHandlerService eHandlerService;

        /** the singleton instance exposed */
        private static ServicesAccess instance;

        /**
         * Default Constructor.
         */
        @Inject
        public ServicesAccess() {
        }

        @PostConstruct
        private void exposeSingleton() {
            instance = this;
        }

        /**
         * Get singleton instance
         * 
         * @return the instance
         */
        public static ServicesAccess getInstance() {
            return instance;
        }

        /**
         * Get command service
         * 
         * @return the ECommandService
         */
        public ECommandService getCommandService() {
            return eCommandService;
        }

        /**
         * Get handler service
         * 
         * @return the EHandlerService
         */
        public EHandlerService getHandlerService() {
            return eHandlerService;
        }
    }

    public Object executeCommand(String command, Map<String, Object> parameters) {
        ServicesAccess serviceAccess = ServicesAccess.getInstance();
        if (serviceAccess != null) {
            ParameterizedCommand parameterizedCommand = serviceAccess.getCommandService().createCommand(command,
                    parameters);
            if (serviceAccess.getHandlerService().canExecute(parameterizedCommand)) {
                return serviceAccess.getHandlerService().executeHandler(parameterizedCommand);
            }
            throw new RuntimeException(String.format("Can't execute command %s", parameterizedCommand.getId()));
        }
        return null;
    }

    public boolean canExecute(String command, Map<String, Object> parameters) {
        ServicesAccess serviceAccess = ServicesAccess.getInstance();
        if (serviceAccess != null) {
            ParameterizedCommand parameterizedCommand = serviceAccess.getCommandService().createCommand(command,
                    parameters);
            return serviceAccess.getHandlerService().canExecute(parameterizedCommand);
        }
        return false;
    }

    public String getCommandName(String commandId) {
        ServicesAccess serviceAccess = ServicesAccess.getInstance();
        if (serviceAccess != null) {
            try {
                return serviceAccess.getCommandService().getCommand(commandId).getName();
            } catch (NotDefinedException e) {
                BonitaStudioLog.error(e);
                return null;
            }
        }
        return null;
    }

}
