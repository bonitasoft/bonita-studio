/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.ui.handler;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class NewFileHandler extends AbstractHandler {

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell activeShell, RepositoryAccessor repositoryAccessor) {
        openHelpDialog(activeShell);
        List<String> existingFileNameList = getRepositoryStore(repositoryAccessor).getChildren().stream()
                .map(IRepositoryFileStore::getDisplayName).collect(Collectors.toList());
        createFileStore(repositoryAccessor,
                StringIncrementer.getNextIncrement(getDefaultFileName(), existingFileNameList)).open();
    }

    protected abstract void openHelpDialog(Shell activeShell);

    protected abstract IRepositoryFileStore createFileStore(RepositoryAccessor repositoryAccessor, String fileName);

    protected abstract IRepositoryStore<? extends IRepositoryFileStore> getRepositoryStore(
            RepositoryAccessor repositoryAccessor);

    protected abstract String getDefaultFileName();

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        execute(Display.getDefault().getActiveShell(), RepositoryManager.getInstance().getAccessor());
        return null;
    }
}
