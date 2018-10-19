/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.actors.ui.handler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.repository.OrganizationFileStore;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

public class ExportOrganizationHandler extends AbstractHandler {

    FileStoreFinder fileStoreFinder;

    public ExportOrganizationHandler() {
        fileStoreFinder = new FileStoreFinder();
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        final IRepositoryStore<? extends IRepositoryFileStore> organizationStore = RepositoryManager.getInstance()
                .getRepositoryStore(OrganizationRepositoryStore.class);
        List<IRepositoryStore<? extends IRepositoryFileStore>> stores = new ArrayList<>();
        stores.add(organizationStore);

        CommonRepositoryPlugin.exportArtifactsToFile(stores, getSelection(), Messages.exportOrganizationTitle);

        return null;
    }

    private Set<Object> getSelection() {
        Set<Object> res = new HashSet<>();
        fileStoreFinder.findSelectedFileStore(RepositoryManager.getInstance().getCurrentRepository())
                .filter(OrganizationFileStore.class::isInstance)
                .map(OrganizationFileStore.class::cast)
                .ifPresent(res::add);
        return res;
    }

}
