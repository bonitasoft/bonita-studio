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
package org.bonitasoft.studio.maven.operation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Collection;

import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.maven.CustomPageProjectFileStore;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.swt.widgets.Display;

public class BuildAndExportCustomPageOperation {

    private MultiStatus status;

    public void run(Collection<CustomPageProjectFileStore> customPageFileStores, String targetDir,
            IRunnableContext runnableContext) {
        try {
            status = new MultiStatus(RestAPIExtensionActivator.PLUGIN_ID, 0, "", null);
            for (CustomPageProjectFileStore fileStore : customPageFileStores) {
                buildCustomPage(fileStore, targetDir, runnableContext);
            }
        } catch (CoreException | ReadFileStoreException | InvocationTargetException | InterruptedException | IOException e) {
            BonitaStudioLog.error(e);
            showErrorDialog(e);
        }
    }

    private void buildCustomPage(CustomPageProjectFileStore fileStore, String targetDir, IRunnableContext runnableContext)
            throws ReadFileStoreException, CoreException, InvocationTargetException, InterruptedException, IOException {
        BuildCustomPageOperation operation = fileStore.newBuildOperation();
        String archiveName = operation.getArchiveName();

        final File file = new File(targetDir, archiveName);
        if (file.exists()) {
            if (!FileActionDialog.overwriteQuestion(file.getAbsolutePath())) {
                status.add(ValidationStatus.cancel(String.format(Messages.buildCancel, fileStore.getDisplayName())));
                return;
            }
        }
        runnableContext.run(true, false, operation.asWorkspaceModifyOperation());
        if (operation.getStatus().isOK()) {
            try (var archiveContent = operation.getArchiveContent()) {
                Files.copy(archiveContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        }
        status.add(operation.getStatus());
    }

    protected void showErrorDialog(final Throwable e) {
        BonitaStudioLog.error("Failed to build custom page project.", e, RestAPIExtensionActivator.PLUGIN_ID);
        new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.errorTitle, Messages.errorDuringProjectBuild,
                e).open();
    }

    public MultiStatus getStatus() {
        return status;
    }

}
