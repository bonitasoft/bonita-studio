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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.InputStreamSupplier;

public class ArchiveInputStreamSupplier implements InputStreamSupplier {

    private ZipFile zipFile;
    private ZipEntry entry;
    private Path tempFile;
    private List<InputStream> openedStreams = new ArrayList<>();

    public ArchiveInputStreamSupplier(File archiveFile, ZipEntry entry) {
        try {
            this.zipFile = new ZipFile(archiveFile);
        } catch (IOException e) {
            BonitaStudioLog.error(e);
        }
        this.entry = entry;
    }

    @Override
    public InputStream get() {
        try {
            if(zipFile != null) {
                InputStream inputStream = zipFile.getInputStream(entry);
                openedStreams.add(inputStream);
                return inputStream;
            }
        } catch (IOException e) {
            BonitaStudioLog.error(e);
            return null;
        }
        return null;
    }

    @Override
    public String getName() {
        return Paths.get(entry.getName()).toFile().getName();
    }

    @Override
    public File toTempFile() {
        if (tempFile == null || !tempFile.toFile().exists()) {
            try {
                tempFile = Paths.get(System.getProperty("java.io.tmpdir")).resolve(getName());
                Files.copy(get(), tempFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
        }
        return tempFile.toFile();
    }

    @Override
    public void close() throws Exception {
        if(tempFile != null) {
            Files.deleteIfExists(tempFile);
        }
        for(InputStream is : openedStreams) {
            if(is != null) {
                is.close();
            }
        }
        if (zipFile != null) {
            zipFile.close();
        }
    }

}
