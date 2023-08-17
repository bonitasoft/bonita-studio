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
package org.bonitasoft.studio.identity.organization.repository;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.DeployOptions;
import org.bonitasoft.studio.common.repository.model.IDeployable;
import org.bonitasoft.studio.common.repository.model.IRenamable;
import org.bonitasoft.studio.common.repository.model.ITenantResource;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.bonitasoft.studio.common.ui.jface.FileActionDialog;
import org.bonitasoft.studio.identity.IdentityPlugin;
import org.bonitasoft.studio.identity.organization.handler.DeployOrganizationHandler;
import org.bonitasoft.studio.identity.organization.model.organization.DocumentRoot;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationFactory;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.bonitasoft.studio.identity.organization.operation.CleanPublishOrganizationOperation;
import org.bonitasoft.studio.identity.organization.operation.PublishOrganizationOperation;
import org.bonitasoft.studio.identity.organization.operation.UpdateOrganizationOperation;
import org.bonitasoft.studio.ui.i18n.Messages;
import org.bonitasoft.studio.ui.validator.ExtensionSupported;
import org.bonitasoft.studio.ui.validator.FileNameValidator;
import org.bonitasoft.studio.ui.validator.InputValidatorWrapper;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.google.common.io.Files;

public class OrganizationFileStore extends EMFFileStore<Organization>
        implements IDeployable, IRenamable, ITenantResource {

    private static final String ORGANIZATION_CONTENT_TYPE = "Organization";
	private static final String DEPLOY_ORGA_CMD = "org.bonitasoft.studio.organization.publish";
    public static final String ORGANIZATION_EXT = ".xml";
    private ActiveOrganizationProvider activeOrganizationProvider;

    public OrganizationFileStore(final String fileName, final OrganizationRepositoryStore store) {
        super(fileName, store);
        activeOrganizationProvider = new ActiveOrganizationProvider();
    }
    
    @Override
    protected Organization doGetContent() throws ReadFileStoreException {
        Object root = super.doGetContent();
        if (root instanceof DocumentRoot) {
            return ((DocumentRoot) root).getOrganization();
        }
        if (root instanceof Organization) {
            return (Organization) root;
        }
        return null;
    }
    
    @Override
    protected Resource doCreateEMFResource() {
        final URI uri = getResourceURI();
        try {
            final EditingDomain editingDomain = getParentStore().getEditingDomain(uri);
            final ResourceSet resourceSet = editingDomain.getResourceSet();
            var emfResource = resourceSet.createResource(uri, ORGANIZATION_CONTENT_TYPE);
            if (getResource().exists()) {
                emfResource.load(Map.of());
            }
            return emfResource;
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    @Override
    protected void doSave(final Object content) {
        if (content instanceof Organization) {
            final Resource emfResource = getEMFResource();
            emfResource.getContents().clear();
            final DocumentRoot root = OrganizationFactory.eINSTANCE.createDocumentRoot();
            Organization organization = (Organization) EcoreUtil.copy((EObject) content);
            if (organization.getUsers() == null) {
                organization.setUsers(OrganizationFactory.eINSTANCE.createUsers());
            }
            if (organization.getGroups() == null) {
                organization.setGroups(OrganizationFactory.eINSTANCE.createGroups());
            }
            if (organization.getRoles() == null) {
                organization.setRoles(OrganizationFactory.eINSTANCE.createRoles());
            }
            if (organization.getMemberships() == null) {
                organization.setMemberships(OrganizationFactory.eINSTANCE.createMemberships());
            }
            root.setOrganization(organization);
            organization.setName(null);
            organization.setDescription(null);
            emfResource.getContents().add(root);
            try {
                emfResource.save(Map.of());
            } catch (final IOException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    @Override
    public IStatus export(String targetAbsoluteFilePath) throws IOException {
        checkWritePermission(new File(targetAbsoluteFilePath));
        File to = new File(targetAbsoluteFilePath);
        if (targetAbsoluteFilePath.endsWith(".xml")) {
            to.getParentFile().mkdirs();
        } else {
            to.mkdirs();
        }
        File target = null;
        if (to.isDirectory()) {
            target = new File(to, getResource().getName());
        } else {
            target = to;
        }
        if (target.exists()) {
            if (FileActionDialog.overwriteQuestion(target.getName())) {
                PlatformUtil.delete(target, AbstractRepository.NULL_PROGRESS_MONITOR);
            } else {
                return ValidationStatus.cancel("");
            }
        }
        Files.copy(getResource().getLocation().toFile(), target);
        return ValidationStatus.ok();
    }

    @Override
    protected IWorkbenchPart doOpen() {
        try {
            return IDE.openEditor(getActivePage(), getResource());
        } catch (final PartInitException e) {
            throw new RuntimeException("Failed to open organization.", e);
        }
    }

    protected IWorkbenchPage getActivePage() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    }

    @Override
    public IFile getResource() {
        return getParentStore().getResource().getFile(getName());
    }

    public boolean isCorrectlySyntaxed() {
        try {
            if (getContent() == null) {
                return false;
            }
        } catch (ReadFileStoreException e) {
            BonitaStudioLog.warning(e.getMessage(), IdentityPlugin.PLUGIN_ID);
            return false;
        }
        return true;
    }

    @Override
    public void deployInUI() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(DeployOrganizationHandler.DEPLOY_ORGA_PARAMETER_NAME, getName());
        executeCommand(DEPLOY_ORGA_CMD, parameters);
    }

    @Override
    public void rename(String newName) {
    	var oldName = IDisplayable.toDisplayName(this).orElse(null);
        String newNameWithoutExtension = stripExtension(newName, ORGANIZATION_EXT);
        if (Objects.equals(activeOrganizationProvider.getActiveOrganization(), oldName)) {
            activeOrganizationProvider.saveActiveOrganization(newNameWithoutExtension);
        }
        renameLegacy(newName);
    }

    @Override
    public Optional<String> retrieveNewName() {
        String currentName = getDisplayName();
        OrganizationRepositoryStore repositoryStore = (OrganizationRepositoryStore) store;
        FileNameValidator validator = new FileNameValidator(repositoryStore, ExtensionSupported.ORGANIZATION,
                currentName);
        InputDialog dialog = new InputDialog(Display.getDefault().getActiveShell(), Messages.rename,
                Messages.renameFile, currentName, new InputValidatorWrapper(validator));
        if (dialog.open() == Dialog.OK
                && !currentName.equals(stripExtension(dialog.getValue(), ORGANIZATION_EXT))) {
            return Optional.of(stripExtension(dialog.getValue(), ORGANIZATION_EXT) + ORGANIZATION_EXT);
        }
        return Optional.empty();
    }

    public boolean isActiveOrganization() {
        Organization organization = null;
        try {
            organization = getContent();
        } catch (ReadFileStoreException e) {
            BonitaStudioLog.warning(e.getMessage(), IdentityPlugin.PLUGIN_ID);
        }
        return organization != null
                && Objects.equals(activeOrganizationProvider.getActiveOrganization(), IDisplayable.toDisplayName(this).orElse(null));
    }

    @Override
    public IStatus deploy(APISession session, Map<String, Object> options, IProgressMonitor monitor) {
        final String activeOrganization = activeOrganizationProvider.getActiveOrganization();
        Organization organization;
        try {
            organization = getContent();
        } catch (ReadFileStoreException e1) {
            return new Status(IStatus.ERROR, IdentityPlugin.PLUGIN_ID, e1.getMessage());
        }
        String name = IDisplayable.toDisplayName(organization).orElse(null);
		PublishOrganizationOperation operation = Objects.equals(name, activeOrganization)
                ? new UpdateOrganizationOperation(organization)
                : new CleanPublishOrganizationOperation(organization);
        if (!PlatformUtil.isACommunityBonitaProduct()) {
            operation.doNotApplyAllProfileToUsers();
        }
        operation.setSession(session);
        try {
            operation.run(monitor);
            String defaultUsername = String.valueOf(options.get(DeployOptions.DEFAULT_USERNAME));
            updateDefaultUserPreference(defaultUsername);
            return ValidationStatus
                    .info(String.format(org.bonitasoft.studio.identity.i18n.Messages.organizationDeployed,
                    		name));
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
            return new Status(IStatus.ERROR, IdentityPlugin.PLUGIN_ID,
                    "An error occured while depoying the Organization",
                    e);
        }
    }

    public void updateDefaultUserPreference(String userName) {
        Organization organization;
        try {
            organization = getContent();
        } catch (ReadFileStoreException e) {
            BonitaStudioLog.error(e);
            return;
        }
        if ((userName == null || userName.isEmpty()) && !organization.getUsers().getUser().isEmpty()) {
            User user = organization.getUsers().getUser().get(0);
            userName = user.getUserName();
        }
        var oldDefaultUser = activeOrganizationProvider.getDefaultUser();
        if (!Objects.equals(oldDefaultUser, userName)) {
            activeOrganizationProvider.saveDefaultUser(userName);
        }
    }

    public ActiveOrganizationProvider getActiveOrganizationProvider() {
        return activeOrganizationProvider;
    }

    @Override
    public boolean canBeDeleted() {
        var activeOrganization = activeOrganizationProvider.getActiveOrganization();
        return !Objects.equals(activeOrganization, getDisplayName()) && super.canBeDeleted();
    }

    /**
     * Get UI display name
     * 
     * @return display name
     */
    private String getDisplayName() {
        return IDisplayable.toDisplayName(this).orElse("");
    }

}
