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
import org.json.JSONException;

/**
 * @author Romain Bioteau
 */
public class WebWidgetFileStore extends InFolderJSONFileStore {

    public WebWidgetFileStore(final String folderName, final IRepositoryStore<? extends IRepositoryFileStore> parentStore) {
        super(folderName, parentStore);
    }

    @Override
    public boolean canBeExported() {
        try {
            return getBooleanAttribute("custom");
        } catch (JSONException | ReadFileStoreException e) {
            BonitaStudioLog.error(String.format("Failed to retrieve 'custom' attribute in widget %s", getName()), e);
        }
        return super.canBeExported();
    }
}
