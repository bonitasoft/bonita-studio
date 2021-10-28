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
package org.bonitasoft.studio.importer.bos.wizard;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import org.bonitasoft.studio.common.log.BonitaStudioLog;

public class URLTempPath {

    private URL url;
    private Path tmpPath;
    private String originalURL;

    public URLTempPath(String originalURL, URL remoteURL, Path tempFile) {
        this.originalURL = originalURL;
        url = remoteURL;
        tmpPath = tempFile;
    }
    
    public String getOriginalURL() {
        return originalURL;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public Path getTmpPath() {
        return tmpPath;
    }

    public void setTmpPath(Path tmpPath) {
        this.tmpPath = tmpPath;
    }

    public void deleteTmpFile() {
        if (tmpPath.toFile().exists()) {
            try {
                Files.delete(tmpPath);
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

}
