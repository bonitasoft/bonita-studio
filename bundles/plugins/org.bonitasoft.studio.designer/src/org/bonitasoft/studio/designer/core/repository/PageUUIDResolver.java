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
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.e4.core.di.annotations.Creatable;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Creatable
public class PageUUIDResolver {

    private File pageFolder;
    private ObjectMapper objectMapper = new ObjectMapper();

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

        Map<String, Object> index = toJSONObject(indexFile.toPath());
        if (index == null || !index.containsKey(uuid)) {
            return null;
        }
        return (String) index.get(uuid);
    }

    private Map<String, Object> toJSONObject(Path file) {
        try {
            var typeRef = new TypeReference<HashMap<String, Object>>() {
            };
            return objectMapper.readValue(file.toFile(), typeRef);
        } catch (IOException e) {
            return null;
        }
    }

}
