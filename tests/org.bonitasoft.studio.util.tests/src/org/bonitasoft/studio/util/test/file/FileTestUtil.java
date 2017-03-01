/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.util.test.file;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.bonitasoft.studio.common.log.BonitaStudioLog;

/**
 * @author Baptiste Mesta
 */
public class FileTestUtil {

    public static boolean fileContains(File file, String string) {
        Reader in = null;
        try {
            in = new FileReader(file);
            char[] data = string.toCharArray(); // <--
            // there should be a method something like this in there.
            // I don't have specs with me
            int place = 0;
            char c;
            while ((c = (char) in.read()) != -1) {
                if (c == data[place]) {
                    place++;
                    if (place == data.length) {
                        in.close();
                        return true;
                    }
                } else
                    place = 0;
            }
        } catch (IOException e) {
            BonitaStudioLog.error(e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
        }

        return false;
    }

}
