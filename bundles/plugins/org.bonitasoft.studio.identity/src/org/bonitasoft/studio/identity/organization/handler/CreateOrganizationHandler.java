/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.identity.organization.handler;

import static org.bonitasoft.studio.identity.organization.repository.OrganizationFileStore.ORGANIZATION_EXT;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationFactory;
import org.bonitasoft.studio.identity.organization.repository.OrganizationFileStore;
import org.bonitasoft.studio.identity.organization.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.ui.handler.NewFileHandler;
import org.eclipse.swt.widgets.Shell;

public class CreateOrganizationHandler extends NewFileHandler {

    @Override
    protected void openHelpDialog(Shell activeShell) {
    }

    @Override
    protected IRepositoryFileStore createFileStore(RepositoryAccessor repositoryAccessor, String fileName) {
        OrganizationRepositoryStore repositoryStore = repositoryAccessor
                .getRepositoryStore(OrganizationRepositoryStore.class);

        String fileNameTrimed = fileName.endsWith(ORGANIZATION_EXT)
                ? fileName.substring(0, fileName.length() - ORGANIZATION_EXT.length())
                : fileName;
        OrganizationFileStore fileStore = repositoryStore
                .createRepositoryFileStore(String.format("%s%s", fileNameTrimed, ORGANIZATION_EXT));
        fileStore.save(createOrganization(fileNameTrimed));
        return fileStore;
    }

    private Organization createOrganization(String name) {
        Organization organization = OrganizationFactory.eINSTANCE.createOrganization();
        organization.setName(name);
        organization.setGroups(OrganizationFactory.eINSTANCE.createGroups());
        organization.setUsers(OrganizationFactory.eINSTANCE.createUsers());
        organization.setRoles(OrganizationFactory.eINSTANCE.createRoles());
        organization.setMemberships(OrganizationFactory.eINSTANCE.createMemberships());
        organization.setCustomUserInfoDefinitions(OrganizationFactory.eINSTANCE.createCustomUserInfoDefinitions());
        return organization;
    }

    @Override
    protected IRepositoryStore<? extends IRepositoryFileStore> getRepositoryStore(RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getRepositoryStore(OrganizationRepositoryStore.class);
    }

    @Override
    protected String getDefaultFileName() {
        return Messages.defaultOrganizationName;
    }

}
