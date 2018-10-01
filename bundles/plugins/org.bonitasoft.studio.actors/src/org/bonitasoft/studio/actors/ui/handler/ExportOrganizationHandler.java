/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;


public class ExportOrganizationHandler extends AbstractHandler {


    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        final IRepositoryStore<? extends IRepositoryFileStore> organizationStore = RepositoryManager.getInstance().getRepositoryStore(OrganizationRepositoryStore.class) ;
        List<IRepositoryStore<? extends IRepositoryFileStore>> stores = new ArrayList<IRepositoryStore<? extends IRepositoryFileStore>>() ;
        stores.add(organizationStore) ;

        CommonRepositoryPlugin.exportArtifactsToFile(stores, getSelection(), Messages.exportOrganizationTitle);

        return null;
    }

    private Set<Object> getSelection() {
        Set<Object> res = new HashSet<>();
        ISelectionService service = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
        if (service == null) {
            return res;
        }
        ISelection selection = service.getSelection();
        if (selection instanceof IStructuredSelection
                && ((IStructuredSelection) selection).size() == 1) {
            Object sel = ((IStructuredSelection) selection).getFirstElement();
            if (sel instanceof IAdaptable && ((IAdaptable) sel).getAdapter(IResource.class) != null) {
                IResource adapter = ((IAdaptable) sel).getAdapter(IResource.class);
                if (adapter instanceof IFile) {
                    IRepositoryFileStore fileStore = RepositoryManager.getInstance().getCurrentRepository()
                            .getFileStore(adapter);
                    if (fileStore instanceof OrganizationFileStore) {
                        res.add(fileStore);
                    }
                }
            }
        }
        return res;
    }

}
