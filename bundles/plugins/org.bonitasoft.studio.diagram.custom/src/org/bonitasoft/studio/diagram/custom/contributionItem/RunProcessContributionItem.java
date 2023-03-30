/**
 * Copyright (C) 2018 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.custom.contributionItem;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.swt.widgets.Listener;

public class RunProcessContributionItem extends ListProcessContributionItem {

    private static final String RUN_COMMAND = "org.bonitasoft.studio.engine.runCommand";

    @Override
    protected Listener createSelectionListener(AbstractProcess process) {
        return e -> {
            Command command = iCommandService.getCommand(RUN_COMMAND);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("process", process);
            ExecutionEvent executionEvent = new ExecutionEvent(command, parameters, this, null);
            try {
                command.executeWithChecks(executionEvent);
            } catch (ExecutionException | NotDefinedException | NotEnabledException | NotHandledException e1) {
                throw new RuntimeException(String.format("An error occured while executing command %s", RUN_COMMAND),
                        e1);
            }
        };
    }

}
