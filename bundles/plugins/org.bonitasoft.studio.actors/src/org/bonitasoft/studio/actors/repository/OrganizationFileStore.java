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
package org.bonitasoft.studio.actors.repository;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.actors.ActorsPlugin;
import org.bonitasoft.studio.actors.model.organization.DocumentRoot;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.OrganizationFactory;
import org.bonitasoft.studio.actors.model.organization.PasswordType;
import org.bonitasoft.studio.actors.model.organization.User;
import org.bonitasoft.studio.actors.model.organization.util.OrganizationXMLProcessor;
import org.bonitasoft.studio.actors.operation.CleanPublishOrganizationOperation;
import org.bonitasoft.studio.actors.operation.PublishOrganizationOperation;
import org.bonitasoft.studio.actors.operation.UpdateOrganizationOperation;
import org.bonitasoft.studio.actors.styler.ActiveOrganizationStyler;
import org.bonitasoft.studio.actors.ui.handler.DeployOrganizationHandler;
import org.bonitasoft.studio.actors.ui.wizard.ManageOrganizationWizard;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.DeployOptions;
import org.bonitasoft.studio.common.repository.model.IDeployable;
import org.bonitasoft.studio.common.repository.model.IRenamable;
import org.bonitasoft.studio.common.repository.model.ITenantResource;
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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.io.Files;

/**
 * @author Romain Bioteau
 */
public class OrganizationFileStore extends EMFFileStore implements IDeployable, IRenamable, ITenantResource {

    private static final String DEPLOY_ORGA_CMD = "org.bonitasoft.studio.organization.publish";
    private static final String ORGANIZATION_EXT = ".organization";
    private ActiveOrganizationProvider activeOrganizationProvider;
    private ActiveOrganizationStyler activeOrganizationStyler;

    public OrganizationFileStore(final String fileName, final OrganizationRepositoryStore store) {
        super(fileName, store);
        activeOrganizationProvider = new ActiveOrganizationProvider();
        activeOrganizationStyler = new ActiveOrganizationStyler();
    }

    @Override
    public String getDisplayName() {
        return getContent().getName();
    }

    @Override
    public Image getIcon() {
        return getParentStore().getIcon();
    }

    @Override
    public Organization getContent() {
        final DocumentRoot root = (DocumentRoot) super.getContent();
        if (root != null) {
            return root.getOrganization();
        }
        return null;
    }

    @Override
    protected void doSave(final Object content) {
        if (content instanceof Organization) {
            final Resource emfResource = getEMFResource();
            emfResource.getContents().clear();
            final DocumentRoot root = OrganizationFactory.eINSTANCE.createDocumentRoot();
            root.setOrganization((Organization) EcoreUtil.copy((EObject) content));
            emfResource.getContents().add(root);
            try {
                final Map<Object, Object> options = new HashMap<>();
                options.put(XMLResource.OPTION_ENCODING, "UTF-8");
                options.put(XMLResource.OPTION_XML_VERSION, "1.0");
                if (emfResource instanceof XMLResourceImpl) {
                    options.putAll(((XMLResourceImpl) emfResource).getDefaultSaveOptions());
                }
                emfResource.save(options);
            } catch (final IOException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    @Override
    public IStatus export(String targetAbsoluteFilePath) throws IOException {
        checkWritePermission(new File(targetAbsoluteFilePath));
        Organization organization = getContent();
        DocumentRoot root = OrganizationFactory.eINSTANCE.createDocumentRoot();
        Organization exportedCopy = EcoreUtil.copy(organization);
        exportedCopy.setName(null);
        exportedCopy.setDescription(null);
        root.setOrganization(exportedCopy);
        File to = new File(targetAbsoluteFilePath);
        if (targetAbsoluteFilePath.endsWith(".xml")) {
            to.getParentFile().mkdirs();
        } else {
            to.mkdirs();
        }
        File target = null;
        if (to.isDirectory()) {
            String targetFilename = organization.getName() + ".xml";
            target = new File(to, targetFilename);
        } else {
            target = to;
        }
        if (target.exists()) {
            if (FileActionDialog.overwriteQuestion(target.getName())) {
                PlatformUtil.delete(target, Repository.NULL_PROGRESS_MONITOR);
            } else {
                return ValidationStatus.cancel("");
            }
        }
        Resource resource = new XMLResourceImpl(URI.createFileURI(target.getAbsolutePath()));
        resource.getContents().add(root);
        Map<String, Object> options = new HashMap<>();
        options.put(XMLResource.OPTION_ENCODING, "UTF-8");
        options.put(XMLResource.OPTION_XML_VERSION, "1.0");
        options.put(XMLResource.OPTION_KEEP_DEFAULT_CONTENT, Boolean.TRUE);
        XMLProcessor processor = new OrganizationXMLProcessor();
        Files.write(processor.saveToString(resource, options).getBytes("UTF-8"), target);
        return ValidationStatus.ok();
    }

    @Override
    protected IWorkbenchPart doOpen() {
        final Wizard newWizard = new ManageOrganizationWizard(getContent());
        final WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), newWizard) {

            @Override
            protected Point getInitialSize() {
                return new Point(1200, 800);
            }

        };
        dialog.open();
        return null;
    }

    @Override
    public IFile getResource() {
        return getParentStore().getResource().getFile(getName());
    }

    public boolean isCorrectlySyntaxed() {
        if (getContent() == null) {
            return false;
        }
        return true;
    }

    @Override
    public void deploy() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(DeployOrganizationHandler.DEPLOY_ORGA_PARAMETER_NAME, getName());
        executeCommand(DEPLOY_ORGA_CMD, parameters);
    }

    @Override
    public void rename(String newName) {
        Organization organization = getContent();
        String oldName = organization.getName();
        String newNameWithoutExtension = stripExtension(newName, ORGANIZATION_EXT);
        organization.setName(newNameWithoutExtension);
        if (Objects.equals(activeOrganizationProvider.getActiveOrganization(), oldName)) {
            activeOrganizationProvider.saveActiveOrganization(newNameWithoutExtension);
        }
        doSave(organization);
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

    @Override
    public StyledString getStyledString() {
        StyledString styledString = super.getStyledString();
        if (isActiveOrganization()) {
            styledString.append(String.format("  (%s)", org.bonitasoft.studio.actors.i18n.Messages.active),
                    activeOrganizationStyler);
        }
        return styledString;
    }

    public boolean isActiveOrganization() {
        Organization organization = getContent();
        return organization != null && Objects.equals(activeOrganizationProvider.getActiveOrganization(), organization.getName());
    }

    @Override
    public IStatus deploy(APISession session, Map<String, Object> options, IProgressMonitor monitor) {
        final String activeOrganization = activeOrganizationProvider.getActiveOrganization();
        Organization organization = getContent();
        PublishOrganizationOperation operation = Objects.equals(organization.getName(), activeOrganization)
                ? new UpdateOrganizationOperation(organization)
                : new CleanPublishOrganizationOperation(organization);
        if(!PlatformUtil.isACommunityBonitaProduct()) {
            operation.doNotAllProfileToUsers();
        }
        operation.setSession(session);
        try {
            operation.run(monitor);
            String defaultUsername = String.valueOf(options.get(DeployOptions.DEFAULT_USERNAME));
            updateDefaultUserPreference(organization, defaultUsername);
            return ValidationStatus.info(String.format(org.bonitasoft.studio.actors.i18n.Messages.organizationDeployed,
                    organization.getName()));
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
            return new Status(IStatus.ERROR, ActorsPlugin.PLUGIN_ID, "An error occured while depoying the Organization", e);
        }
    }
    
    protected void updateDefaultUserPreference(Organization organization,
            String userName) {
        if(userName == null || userName.isEmpty()) {
            if(!organization.getUsers().getUser().isEmpty()) {
                User user = organization.getUsers().getUser().get(0);
                userName = user.getUserName();
            }
        }
        String defaultUserName = userName;
        activeOrganizationProvider.saveDefaultUser(userName);
        if (organization != null) {
            activeOrganizationProvider.saveDefaultPassword(organization.getUsers().getUser().stream()
                    .filter(user -> Objects.equals(user.getUserName(), defaultUserName))
                    .findFirst()
                    .map(User::getPassword)
                    .map(PasswordType::getValue)
                    .orElse(""));
        }
    }

}
