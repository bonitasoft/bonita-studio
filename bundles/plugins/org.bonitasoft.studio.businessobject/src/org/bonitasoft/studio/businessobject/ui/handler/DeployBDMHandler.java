/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.businessobject.ui.handler;

import org.bonitasoft.studio.businessobject.core.operation.DeployBDMJob;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;

public class DeployBDMHandler {

    @Execute
    public void deploy(RepositoryAccessor repositoryAccessor, Shell shell) {
        BusinessObjectModelRepositoryStore store = repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        DeployBDMJob job = new DeployBDMJob((BusinessObjectModelFileStore) store.getChild(BusinessObjectModelFileStore.BOM_FILENAME, true),false);
        job.setUser(true);
        job.schedule();
    }

    @CanExecute
    public boolean bdmExists(RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)
                .getChild(BusinessObjectModelFileStore.BOM_FILENAME, false) != null;
    }
}
