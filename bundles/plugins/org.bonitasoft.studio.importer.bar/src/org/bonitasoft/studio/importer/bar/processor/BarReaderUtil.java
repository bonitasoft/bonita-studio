/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.importer.bar.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.importer.bar.i18n.Messages;

/**
 * @author Aurelien Pupier
 */
public class BarReaderUtil {

    static List<String> findCustomConnectorClassName(final File archiveFile) throws ZipException, IOException {
        final List<String> res = new ArrayList<String>();
        try (ZipFile zipfile = new ZipFile(archiveFile);) {
            final Enumeration<?> enumEntries = zipfile.entries();
            ZipEntry zipEntry = null;
            String className = null;
            String startWith = null;
            while (enumEntries.hasMoreElements()) {
                zipEntry = (ZipEntry) enumEntries.nextElement();
                if (!zipEntry.isDirectory() && zipEntry.getName().endsWith(".class")) {
                    startWith = zipEntry.toString().replace(".class", "");
                    className = zipEntry.toString().replace("/", ".").replace(".class", "");
                    final Enumeration<? extends ZipEntry> newEntries = zipfile.entries();
                    while (newEntries.hasMoreElements()) {
                        final ZipEntry newEntry = newEntries.nextElement();
                        if (!newEntry.isDirectory() && newEntry.toString().endsWith(startWith + ".properties")) {
                            res.add(className);
                        }
                    }
                }
            }
        } catch (Exception e) {
            BonitaStudioLog.error(e);
            return res;
        }
        return res;
    }

    static File getProcFormBar(final File archiveFile) throws Exception {
        ZipInputStream zin = null;
        FileOutputStream out = null;
        try {
            zin = new ZipInputStream(new FileInputStream(archiveFile));
            ZipEntry zipEntry = zin.getNextEntry();
            while (zipEntry != null && !zipEntry.getName().endsWith(".proc")) {
                zipEntry = zin.getNextEntry();
            }
            if (zipEntry == null) {
                throw new FileNotFoundException(Messages.bind(Messages.invalidArchiveStructure, archiveFile.getName()));
            }
            final String entryName = zipEntry.getName();
            if (entryName.indexOf("/") != -1) {
                entryName.substring(entryName.lastIndexOf("/"));
            }
            final File tempFile = new File(ProjectUtil.getBonitaStudioWorkFolder(), entryName);
            final byte[] buf = new byte[1024];
            tempFile.delete();
            int len;
            out = new FileOutputStream(tempFile);
            while ((len = zin.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            return tempFile;
        } finally {
            if (zin != null) {
                zin.close();
            }
            if (out != null) {
                out.close();
            }
        }

    }
}
