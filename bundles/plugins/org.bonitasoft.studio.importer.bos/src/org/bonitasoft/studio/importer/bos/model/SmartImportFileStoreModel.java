/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.bos.model;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipFile;

import org.apache.commons.io.FileUtils;
import org.bonitasoft.studio.common.model.ConflictStatus;
import org.bonitasoft.studio.common.model.ImportAction;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.smartImport.ISmartImportable;
import org.bonitasoft.studio.common.repository.model.smartImport.SmartImportableModel;
import org.bonitasoft.studio.common.repository.model.smartImport.SmartImportableUnit;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;

public class SmartImportFileStoreModel extends ImportFileStoreModel {

    private ISmartImportable currentFileStore;
    private boolean smartImportable = false;
    private BosArchive bosArchive;
    private SmartImportableModel smartImportableModel;

    public SmartImportFileStoreModel(BosArchive bosArchive, String filePath, AbstractFolderModel parent,
            ISmartImportable currentFileStore) {
        super(filePath, parent);
        this.bosArchive = bosArchive;
        this.currentFileStore = currentFileStore;
    }

    @Override
    public void setStatus(ConflictStatus status) {
        super.setStatus(status);
        if (Objects.equals(status, ConflictStatus.CONFLICTING)) {
            smartImportableModel = currentFileStore.getAdapter(SmartImportableModel.class);
            if (smartImportableModel != null) {
                try (ZipFile zipFile = bosArchive.getZipFile()) {
                    File file = toFile(zipFile);
                    if (smartImportableModel.validateSmartImportable(file).isOK()) {
                        smartImportableModel.buildSmartImportModel(file);
                        importAction = ImportAction.SMART_IMPORT;
                        smartImportable = true;
                    }
                } catch (IOException e) {
                    Display.getDefault().syncExec(() -> {
                        new ExceptionDialogHandler().openErrorDialog(Display.getDefault().getActiveShell(), e.getMessage(),
                                e);
                    });
                }
            }
        }
    }

    private File toFile(ZipFile zipFile) throws IOException {
        File file = File.createTempFile(getFileName(), null);
        file.deleteOnExit();
        FileUtils.copyInputStreamToFile(zipFile.getInputStream(zipFile.getEntry(getPath())), file);
        return file;
    }

    public boolean isSmartImportable() {
        return smartImportable;
    }

    @Override
    public IRepositoryFileStore doImport(ZipFile archive, IProgressMonitor monitor) {
        if (smartImportable && Objects.equals(importAction, ImportAction.SMART_IMPORT) && smartImportableModel != null) {
            return smartImportableModel.performSmartImport(monitor).isOK()
                    ? smartImportableModel.getAdapter(IRepositoryFileStore.class)
                    : null;
        }
        return super.doImport(archive, monitor);
    }

    public List<SmartImportableUnit> getChildren() {
        return smartImportableModel != null
                ? smartImportableModel.getSmartImportableUnits()
                : Collections.emptyList();
    }

}
