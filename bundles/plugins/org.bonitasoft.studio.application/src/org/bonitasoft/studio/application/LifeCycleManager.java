/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.application;

import java.io.File;

import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;

public class LifeCycleManager {

    @PostContextCreate
    public void disablePersistState(IEclipseContext context) {
        System.setProperty(IWorkbench.CLEAR_PERSISTED_STATE, String.valueOf(shouldClearPersistedState()));
    }

    protected boolean shouldClearPersistedState() {
        File installFolder = new File(Platform.getInstallLocation().getURL().getFile());
        File clearStateFile = installFolder.toPath().resolve(".clearState").toFile();
        if (clearStateFile.exists()) {
            clearStateFile.delete();
            return true;
        }
        return false;
    }

}
