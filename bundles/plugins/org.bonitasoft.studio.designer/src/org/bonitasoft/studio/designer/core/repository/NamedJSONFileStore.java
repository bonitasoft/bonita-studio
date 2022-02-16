/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.core.repository;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.designer.UIDesignerPlugin;


public class NamedJSONFileStore extends JSONFileStore {

    public static final String NAME_KEY = "name";
    private static final String UUID_KEY = "uuid";
    private static final String ID_KEY = "id";

    public NamedJSONFileStore(final String fileName, final IRepositoryStore<? extends IRepositoryFileStore> parentStore) {
        super(fileName, parentStore);
    }

    public String getId() {
        try {
            return getStringAttribute(ID_KEY);
        } catch (final ReadFileStoreException e) {
            BonitaStudioLog.error(
                    String.format("Failed to retrieve id in JSON file %s.json, with key %s.", getName(), ID_KEY),
                    UIDesignerPlugin.PLUGIN_ID);
            return null;
        }
    }

    public String getUUID() {
        try {
            return getStringAttribute(UUID_KEY);
        } catch (ReadFileStoreException e) {
            BonitaStudioLog.error(
                    String.format("Failed to retrieve id in JSON file %s.json, with key %s.", getName(), UUID_KEY),
                    UIDesignerPlugin.PLUGIN_ID);
            return null;
        }
    }
    
    public String getCustomPageName() {
        try {
            return getStringAttribute(NAME_KEY);
        } catch (ReadFileStoreException e) {
            BonitaStudioLog.error(String.format("Failed to retrieve name in JSON file %s.json, with key %s.", getName(), NAME_KEY), UIDesignerPlugin.PLUGIN_ID);
            return getResource().getName();
        }
    }

    @Override
    public String getDisplayName() {
        return getCustomPageName();
    }

}
