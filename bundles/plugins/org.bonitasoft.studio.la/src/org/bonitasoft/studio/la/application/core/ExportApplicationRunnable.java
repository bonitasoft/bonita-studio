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
package org.bonitasoft.studio.la.application.core;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.la.i18n.Messages;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

public class ExportApplicationRunnable implements IRunnableWithProgress {

    private final String path;
    private final List<IRepositoryFileStore> applicationFileStoreList;

    public ExportApplicationRunnable(String path, List<IRepositoryFileStore> applicationFileStoreList) {
        this.path = path;
        this.applicationFileStoreList = applicationFileStoreList;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.exportOperation, applicationFileStoreList.size());
        for (final IRepositoryFileStore fStore : applicationFileStoreList) {
            monitor.setTaskName(String.format(Messages.exporting, fStore.getDisplayName()));
            try {
                fStore.export(path);
            } catch (final IOException e) {
                throw new InvocationTargetException(e, "Export failed");
            }
            monitor.worked(1);
        }
        monitor.done();
    }

}
