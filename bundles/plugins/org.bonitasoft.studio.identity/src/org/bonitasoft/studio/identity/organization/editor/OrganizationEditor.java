/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.identity.organization.editor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.editor.formpage.group.GroupFormPage;
import org.bonitasoft.studio.identity.organization.editor.formpage.role.RoleFormPage;
import org.bonitasoft.studio.identity.organization.editor.formpage.user.UserFormPage;
import org.bonitasoft.studio.identity.organization.model.organization.DocumentRoot;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationFactory;
import org.bonitasoft.studio.identity.organization.model.organization.util.OrganizationResourceImpl;
import org.bonitasoft.studio.identity.organization.model.organization.util.OrganizationXMLProcessor;
import org.bonitasoft.studio.identity.organization.repository.OrganizationFileStore;
import org.bonitasoft.studio.identity.organization.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.ui.editors.xmlEditors.AbstractEditor;
import org.eclipse.jface.text.IDocument;

public class OrganizationEditor extends AbstractEditor<Organization> {

    private RepositoryAccessor repositoryAccessor;
    private OrganizationXMLProcessor xmlProcessor;
    private GroupFormPage groupFormPage;
    private RoleFormPage roleFormPage;
    private UserFormPage userFormPage;

    @Override
    protected void createFormPages() {
        groupFormPage = new GroupFormPage("groups", Messages.groups, getContext());
        roleFormPage = new RoleFormPage("roles", Messages.roles, getContext());
        userFormPage = new UserFormPage("users", Messages.users, getContext());
        formPages.add(groupFormPage);
        formPages.add(roleFormPage);
        formPages.add(userFormPage);
    }

    @Override
    protected void initVariablesAndListeners() {
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        xmlProcessor = new OrganizationXMLProcessor();
        IDocument document = fSourceEditor.getDocumentProvider().getDocument(getEditorInput());
        try {
            workingCopy = getFileStore(getEditorInput().getName()).getContent();
            groupFormPage.init(workingCopy, document);
            roleFormPage.init(workingCopy, document);
            userFormPage.init(workingCopy, document);
        } catch (ReadFileStoreException e) {
            workingCopy = OrganizationFactory.eINSTANCE.createOrganization();
            groupFormPage.init(workingCopy, document);
            roleFormPage.init(workingCopy, document);
            userFormPage.init(workingCopy, document);
            groupFormPage.setErrorState(true);
            roleFormPage.setErrorState(true);
            userFormPage.setErrorState(true);
            setActivePage(0);
            setActiveEditor(fSourceEditor);
        }
    }

    private OrganizationFileStore getFileStore(String fileName) {
        return repositoryAccessor.getRepositoryStore(OrganizationRepositoryStore.class).getChild(fileName, false);
    }

    @Override
    protected Optional<Organization> xmlToModel(byte[] xml) {
        try (InputStream is = new ByteArrayInputStream(xml)) {
            OrganizationResourceImpl resource = (OrganizationResourceImpl) xmlProcessor.load(is, null);
            Optional<Organization> orga = resource.getContents().stream()
                    .filter(DocumentRoot.class::isInstance)
                    .map(DocumentRoot.class::cast)
                    .map(DocumentRoot::getOrganization)
                    .findFirst();
            return orga;
        } catch (IOException e) {
            BonitaStudioLog.error(e);
            return Optional.empty();
        }
    }

    @Override
    protected void updateWorkingCopy(Organization newModel) {
        // TODO Auto-generated method stub
    }

}
