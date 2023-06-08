/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
import java.util.List;
import java.util.zip.ZipEntry;

public class MultiModuleBosArchiveEntryHandler extends DefaultBosArchiveEntryHandler implements BosArchiveEntryHandler {

    private String entryPrefix;

    public MultiModuleBosArchiveEntryHandler(File archiveFile) {
      super(archiveFile);
    }

    @Override
    protected List<String> splitEntry(ZipEntry entry) {
        var segments = super.splitEntry(entry);
        entryPrefix = segments.remove(0);
        return segments;
    }
    
    @Override
    protected String toEntryPath(Iterable<String> parentSegments) {
        return entryPrefix + "/" + super.toEntryPath(parentSegments);
    }
    
    @Override
    protected int getStoreDepth(List<String> segments) {
        if(segments.size() == 2 && 
                segments.get(0).equals("bdm")) {
            return 1;
        }
        if(!segments.isEmpty() && 
                segments.get(0).equals("extensions")) {
            return 1;
        }
        return super.getStoreDepth(segments);
    }
    
}
