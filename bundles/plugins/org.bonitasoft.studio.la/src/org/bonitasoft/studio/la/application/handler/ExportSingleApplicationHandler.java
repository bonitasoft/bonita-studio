/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.handler;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;

public class ExportSingleApplicationHandler extends AbstractHandler {

    private ExportApplicationFileAction exportApplicationFileAction;
    private FileStoreFinder fileStoreFinder;

    public ExportSingleApplicationHandler() {
        exportApplicationFileAction = new ExportApplicationFileAction();
        fileStoreFinder = new FileStoreFinder();
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        fileStoreFinder.findSelectedFileStore(RepositoryManager.getInstance().getCurrentRepository())
                .filter(ApplicationFileStore.class::isInstance)
                .map(ApplicationFileStore.class::cast)
                .ifPresent(
                        fileStore -> exportApplicationFileAction.export(Display.getDefault().getActiveShell(), fileStore));
        return null;
    }

}
