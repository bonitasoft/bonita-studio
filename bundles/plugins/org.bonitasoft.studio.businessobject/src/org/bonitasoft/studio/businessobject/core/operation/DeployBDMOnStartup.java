/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.businessobject.core.operation;

import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.extension.IEngineAction;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;

/**
 * @author Romain Bioteau
 */
public class DeployBDMOnStartup implements IEngineAction {

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.extension.IEngineAction#run(org.bonitasoft.engine.session.APISession)
     */
    @Override
    public void run(APISession session) throws Exception {
        final BusinessObjectModelRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        final BusinessObjectModelFileStore fileStore = store.getChild(BusinessObjectModelFileStore.BOM_FILENAME);
        if (fileStore != null) {
            final DeployBDMOperation deployBDMOperation = new DeployBDMOperation(fileStore);
            deployBDMOperation.setSession(session);
            deployBDMOperation.run(Repository.NULL_PROGRESS_MONITOR);
        }
    }

}
