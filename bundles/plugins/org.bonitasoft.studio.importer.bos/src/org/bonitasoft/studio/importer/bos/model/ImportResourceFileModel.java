/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.bos.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipFile;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.model.ImportAction;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class ImportResourceFileModel extends ImportFileModel implements ImportableUnit {

    private IProject project;

    public ImportResourceFileModel(String filePath, AbstractFolderModel parent, IProject project) {
        super(filePath, parent);
        this.project = project;
    }
    
    @Override
    public String getName() {
        return getFileName();
    }

    @Override
    public IRepositoryFileStore doImport(ZipFile archive, IProgressMonitor monitor) {
        archive.stream()
                .filter(e -> e.getName().startsWith(path))
                .forEach(zipEntry -> {
                    String filePath = zipEntry.getName();
                    filePath = filePath.substring(filePath.indexOf('/') + 1);
                    IFile targetFile = project.getFile(filePath);
                    if (targetFile.exists()) {
                        try(InputStream is = archive.getInputStream(zipEntry)){
                            targetFile.setContents(is, IResource.FORCE, monitor);
                        } catch (CoreException | IOException e) {
                           BonitaStudioLog.error(e);
                        }
                    } else if (!targetFile.exists()) {
                        try(InputStream is = archive.getInputStream(zipEntry)){
                            targetFile.getLocation().toFile().getParentFile().mkdirs();
                            targetFile.getParent().refreshLocal(IResource.DEPTH_INFINITE, monitor);
                            targetFile.create(is, IResource.FORCE, monitor);
                        } catch (CoreException | IOException e) {
                           BonitaStudioLog.error(e);
                        }
                    }
                });

        return null;
    }


}
