/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.dependencies.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;

import org.bonitasoft.studio.common.ZipInputStreamIFileFriendly;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.dependencies.i18n.Messages;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

public class ImportLibsOperation implements IRunnableWithProgress {

    private static final String ZIP_EXTENSION = ".zip";
    private static final String JAR_EXTENSION = ".jar";
    private final DependencyRepositoryStore libStore;
    private final String[] jarAndZips;
    private final String filterPath;

    public ImportLibsOperation(final DependencyRepositoryStore libStore, final String[] jarAndZips,
            final String filterPath) {
        this.libStore = libStore;
        this.jarAndZips = jarAndZips;
        this.filterPath = filterPath;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.beginToAddJars, jarAndZips.length);
        final Map<String, File> jarsToImportMap = new HashMap<String, File>();
        for (final String filePath : jarAndZips) {
            if (monitor.isCanceled()) {
                return;
            }
            final File file = getFile(filePath);
            final String fileName = file.getName();

            if (fileName.endsWith(JAR_EXTENSION)) { //$NON-NLS-1$
                monitor.setTaskName(Messages.addingJar + " " + fileName);
                jarsToImportMap.put(fileName, file);
            } else if (fileName.endsWith(ZIP_EXTENSION)) { //$NON-NLS-1$
                try (FileInputStream fis = new FileInputStream(file)) {
                    importJarsFromZip(monitor, fis);
                } catch (final Exception ex) {
                    BonitaStudioLog.error(ex);
                    throw new InvocationTargetException(ex);
                }
            }
        }
        importJars(jarsToImportMap);
    }

    private void importJars(final Map<String, File> jarsToImportMap) {
        for (final Entry<String, File> entry : jarsToImportMap.entrySet()) {
            try (InputStream is = new FileInputStream(entry.getValue())) {
                libStore.createRepositoryFileStore(entry.getKey()).save(is);
            } catch (final Exception e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    private void importJarsFromZip(final IProgressMonitor monitor, final FileInputStream fis)
            throws IOException, InvocationTargetException {
        ZipInputStreamIFileFriendly zip = null;
        zip = new ZipInputStreamIFileFriendly(fis);
        ZipEntry entry = zip.getNextEntry();
        if (entry == null) {
            zip.close();
            throw new InvocationTargetException(
                    new Exception(org.bonitasoft.studio.dependencies.i18n.Messages.zipFileIsCorrupted),
                    org.bonitasoft.studio.dependencies.i18n.Messages.zipFileIsCorrupted);
        }
        while (entry != null) {
            if (entry.getName().endsWith(JAR_EXTENSION)) { //$NON-NLS-1$
                monitor.setTaskName(Messages.addingJar + " " + entry.getName()); //$NON-NLS-1$
                libStore.createRepositoryFileStore(entry.getName()).save(zip);
            }
            entry = zip.getNextEntry();
        }
        zip.forceClose();
    }

    private File getFile(final String filePath) {
        if (filePath.contains(filterPath)) {
            return new File(filePath);
        } else {
            return new File(filterPath + File.separator + filePath);
        }
    }
}
