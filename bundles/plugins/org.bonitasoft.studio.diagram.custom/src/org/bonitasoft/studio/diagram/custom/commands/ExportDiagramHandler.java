/**
 * Copyright (C) 2018 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.custom.commands;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.ui.PlatformUI;

@SuppressWarnings("restriction")
public class ExportDiagramHandler extends AbstractHandler {

    public static final String EXPORT_COMMAND = "org.bonitasoft.studio.exportBosArchive";
    public static final String EXPORT_PARAMETER = "diagram";

    private FileStoreFinder fileStoreFinder;
    private ECommandService eCommandService;
    private EHandlerService eHandlerService;

    public ExportDiagramHandler() {
        fileStoreFinder = new FileStoreFinder();
        eCommandService = PlatformUI.getWorkbench().getService(ECommandService.class);
        eHandlerService = PlatformUI.getWorkbench().getService(EHandlerService.class);
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        fileStoreFinder
                .findSelectedFileStore(RepositoryManager.getInstance().getCurrentRepository())
                .ifPresent(fileStore -> {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put(EXPORT_PARAMETER, fileStore.getName());
                    ParameterizedCommand parameterizedCommand = eCommandService.createCommand(EXPORT_COMMAND, parameters);
                    if (eHandlerService.canExecute(parameterizedCommand)) {
                        eHandlerService.executeHandler(parameterizedCommand);
                    } else {
                        throw new RuntimeException(String.format("Can't execute command %s", parameterizedCommand.getId()));
                    }
                });
        return null;
    }

}
