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
package org.bonitasoft.studio.designer.core.repository;

import java.io.File;
import java.io.IOException;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

@Creatable
public class PageUUIDResolver {

    private File pageFolder;

    public PageUUIDResolver(File pageFolder) {
        this.pageFolder = pageFolder;
    }
    
    public static File indexFile(File pageFolder) {
        return pageFolder.toPath().resolve(".metadata").resolve(".index.json").toFile();
    }

    public String resolveUUID(String uuid) {
        File indexFile = indexFile(pageFolder);
        if (!indexFile.exists()) {
                return null;
        }

        JSONObject index = loadIndex(indexFile);
        if (!index.has(uuid)) {
           return null;
        }
        try {
            return index.getString(uuid);
        } catch (JSONException e) {
            return null;
        }
    }

    private JSONObject loadIndex(File indexFile) {
        try {
            return new org.json.JSONObject(Files.toString(indexFile, Charsets.UTF_8));
        } catch (JSONException | IOException e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }
  
}
