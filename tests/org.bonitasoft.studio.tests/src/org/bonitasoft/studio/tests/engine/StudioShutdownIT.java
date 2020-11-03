/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.engine;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.DatabaseHandler;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.junit.Test;

public class StudioShutdownIT {

    @Test
    public void should_delete_h2_db_files_on_shutdown() throws Exception {
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.DELETE_TENANT_ON_EXIT, true);

        BOSEngineManager.getInstance().stop();

        DatabaseHandler databaseHandler = RepositoryManager.getInstance().getCurrentRepository().getDatabaseHandler();
        File h2Folder = databaseHandler.getDBLocation();
        assertThat(h2Folder.listFiles(f -> {
            try {
                return f.getName().startsWith(databaseHandler.getBonitaDBName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        })).isEmpty();
    }

}
