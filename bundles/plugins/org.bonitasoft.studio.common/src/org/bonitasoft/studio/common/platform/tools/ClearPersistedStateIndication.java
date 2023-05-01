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
package org.bonitasoft.studio.common.platform.tools;

import java.io.File;
import java.io.IOException;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.runtime.Platform;

/**
 * Lets an indication in platform that the workbench's persisted state should be cleared.
 */
public class ClearPersistedStateIndication {

    /**
     * Let an indication in platform that the workbench's persisted state should be cleared.
     */
    public static void letIndication() {
        File clearStateFile = getIndicationFile();
        try {
            clearStateFile.delete();
            clearStateFile.createNewFile();
        } catch (IOException e) {
            BonitaStudioLog.error(e);
        }
    }

    /**
     * Test whether indication to clear the persisted state is present.
     * 
     * @return true when indication is present
     */
    public static boolean hasIndication() {
        File clearStateFile = getIndicationFile();
        if (clearStateFile.exists()) {
            clearStateFile.delete();
            return true;
        }
        return false;
    }

    /**
     * Get the file to use as an indication
     * 
     * @return file (which existence gives the indication)
     */
    private static File getIndicationFile() {
        File installFolder = new File(Platform.getInstallLocation().getURL().getFile());
        File clearStateFile = installFolder.toPath().resolve(".clearState").toFile();
        return clearStateFile;
    }
}
