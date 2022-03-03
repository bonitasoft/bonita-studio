/**
 * Copyright (C) 2009-2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests;

import static org.junit.Assert.assertFalse;

import java.io.File;

import org.eclipse.core.runtime.Platform;
import org.junit.Test;

public class TestPathSize {

    @Test
    public void testMaxItemSizeIsEclipse() {
        String location = Platform.getBundle("org.eclipse.core.runtime").getLocation();
        if (location.startsWith("reference:file:")) {
            location = location.substring("reference:file:".length());
        }
        File pluginFolder = new File(location).getParentFile();
        File longestPath = findLongestPath(pluginFolder);
        File longestPathNotBonita = findLongestPathNotBonita(pluginFolder);
        final String longestAbsolutePath = longestPath.getAbsolutePath();
        final int extraChars = longestAbsolutePath.length() - longestPathNotBonita.getAbsolutePath().length();
        assertFalse("Longest path is [" + longestAbsolutePath + "]. The size of the path contains " + extraChars
                + "extra characters", longestAbsolutePath.contains("org.bonitasoft") && extraChars > 0);
    }

    private File findLongestPathNotBonita(File dir) {
        if (!dir.isDirectory()) {
            return dir;
        }

        File currentLongFile = null;
        for (File file : dir.listFiles()) {
            File longestSubFile = findLongestPathNotBonita(file);
            if ((currentLongFile == null ||
                    longestSubFile.getAbsolutePath().length() > currentLongFile.getAbsolutePath().length())
                    && !longestSubFile.getAbsolutePath().contains("org.bonitasoft")) {
                currentLongFile = longestSubFile;
            }
        }
        if (currentLongFile == null) { // empty dir
            return dir;
        } else {
            return currentLongFile;
        }
    }

    private File findLongestPath(File dir) {
        if (!dir.isDirectory()) {
            return dir;
        }

        File currentLongFile = null;
        for (File file : dir.listFiles()) {
            File longestSubFile = findLongestPath(file);
            if (currentLongFile == null ||
                    longestSubFile.getAbsolutePath().length() > currentLongFile.getAbsolutePath().length()) {
                currentLongFile = longestSubFile;
            }
        }
        if (currentLongFile == null) { // empty dir
            return dir;
        } else {
            return currentLongFile;
        }
    }
}
