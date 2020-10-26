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
package org.bonitasoft.studio.importer.bos.model;

import java.io.IOException;
import java.util.stream.Stream;
import java.util.zip.ZipFile;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.ImportArchiveData;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class RootFileModel extends ImportStoreModel {

    private String fileName;

    public RootFileModel(String fileName, String path) {
        super(path, null);
        this.fileName = fileName;
    }

    @Override
    public Image getImage() {
        return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
    }

    @Override
    public Stream<ImportableUnit> importableUnits() {
        return Stream.of(new ImportableUnit() {

            @Override
            public String getName() {
                return fileName;
            }

            @Override
            public IRepositoryFileStore doImport(ZipFile archive, IProgressMonitor monitor) {
                ImportArchiveData data = new ImportArchiveData(archive, archive.getEntry(path), true);
                IFile file = RepositoryManager.getInstance().getCurrentRepository().getProject().getFile(getName());
                try {
                    if (file.exists()) {
                        file.setContents(data.getInputStream(), IResource.KEEP_HISTORY, monitor);
                    } else {
                        file.create(data.getInputStream(), false, monitor);
                    }
                } catch (CoreException | IOException e) {
                    BonitaStudioLog.error(e);
                }
                return null;
            }
        });
    }

}
