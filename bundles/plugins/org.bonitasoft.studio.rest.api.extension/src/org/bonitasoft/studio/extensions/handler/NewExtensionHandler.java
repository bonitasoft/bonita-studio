/**
 * Copyright (C) 2025 BonitaSoft S.A.
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
package org.bonitasoft.studio.extensions.handler;

import static org.bonitasoft.studio.maven.model.ExtensionProjectArchetypes.buildDefaultArchetypeConfiguration;

import java.text.MessageFormat;

import org.bonitasoft.studio.application.ui.control.model.dependency.ArtifactType;
import org.bonitasoft.studio.application.views.overview.ProjectOverviewEditorPart;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.ui.jface.CustomWizardDialog;
import org.bonitasoft.studio.extensions.wizard.NewExtensionWizard;
import org.bonitasoft.studio.maven.ExtensionRepositoryStore;
import org.bonitasoft.studio.maven.MavenProjectConfiguration;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.rest.api.extension.core.RestAPIAddressResolver;
import org.bonitasoft.studio.rest.api.extension.ui.wizard.NewRestAPIExtensionWizard;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import jakarta.inject.Named;

/**
 * Handles the creation of a new extension project.
 */
public class NewExtensionHandler {

    @Execute
    public IStatus execute(@Named(ProjectOverviewEditorPart.EXTENSION_TYPE_PARAMETER) String artifactType)
            throws ExecutionException {
        ArtifactType type;
        try {
            type = ArtifactType.valueOf(artifactType);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new ExecutionException("Invalid artifact type: " + artifactType, e);
        }
        RepositoryAccessor repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        try {
            var wizard = newWizard(type, repositoryAccessor);
            var dialog = newWizardDialog(wizard, Messages.create);
            if (dialog.open() == IDialogConstants.OK_ID) {
                wizard.getNewFileStore().open();
                return Status.OK_STATUS;
            }
        } catch (CoreException e) {
            throw new ExecutionException(MessageFormat.format("Failed to create {0} Extension", type.name()), e);
        }
        return Status.CANCEL_STATUS;
    }

    @CanExecute
    public boolean canExecute(RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getCurrentRepository().filter(IRepository::isLoaded).isPresent();
    }

    protected NewExtensionWizard newWizard(ArtifactType type, RepositoryAccessor repositoryAccessor)
            throws CoreException {
        var projectMetadata = repositoryAccessor.getCurrentProject().orElseThrow()
                .getProjectMetadata(new NullProgressMonitor());
        var store = repositoryAccessor.getRepositoryStore(ExtensionRepositoryStore.class);
        var config = buildDefaultArchetypeConfiguration(type, projectMetadata);
        if (type == ArtifactType.REST_API) {
            // use a specific wizard for REST API extensions
            var addressResolver = new RestAPIAddressResolver(
                    InstanceScope.INSTANCE.getNode(BonitaStudioPreferencesPlugin.PLUGIN_ID));
            return new NewRestAPIExtensionWizard(projectMetadata, store, new MavenProjectConfiguration(),
                    repositoryAccessor.getWorkspace(), new WidgetFactory(), bdmExists(repositoryAccessor),
                    addressResolver);
        } else {
            return new NewExtensionWizard(type, store, config, new MavenProjectConfiguration(),
                    repositoryAccessor.getWorkspace(), new WidgetFactory());
        }
    }

    private boolean bdmExists(RepositoryAccessor repositoryAccessor) throws CoreException {
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = repositoryAccessor
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        final BusinessObjectModelFileStore bdmFilsStore = repositoryStore
                .getChild(BusinessObjectModelFileStore.BOM_FILENAME, true);
        return bdmFilsStore != null;
    }

    protected WizardDialog newWizardDialog(final Wizard wizard, String finishLabel) {
        return new CustomWizardDialog(Display.getDefault().getActiveShell(), wizard, finishLabel);
    }

}
