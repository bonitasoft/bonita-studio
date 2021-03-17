/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.ui.handler;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Named;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.contribution.InstallLocalRepositoryContribution;
import org.bonitasoft.studio.maven.MavenProjectConfiguration;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.rest.api.extension.core.RestAPIAddressResolver;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionRepositoryStore;
import org.bonitasoft.studio.rest.api.extension.ui.wizard.NewRestAPIExtensionWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class NewRestAPIExtensionHandler extends AbstractHandler {

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell,
            RepositoryAccessor repositoryAccessor,
            WidgetFactory widgetFactory,
            IWorkspace workspace,
            RestAPIAddressResolver addressResolver)
            throws ExecutionException {
        installDependencies();
        NewRestAPIExtensionWizard wizard;
        try {
            wizard = newWizard(repositoryAccessor, widgetFactory, workspace, addressResolver);
            final int open = newWizardDialog(wizard, Messages.create).open();
            if (open == IDialogConstants.OK_ID) {
                wizard.getNewFileStore().open();
            }
        } catch (CoreException e) {
            throw new ExecutionException("Failed to create REST API Extension", e);
        }

    }

    protected void installDependencies() throws ExecutionException {
        try {
            new ProgressMonitorDialog(Display.getDefault().getActiveShell()).run(true, false, monitor -> {
                monitor.beginTask(Messages.installingDependencies, IProgressMonitor.UNKNOWN);
                new InstallLocalRepositoryContribution().execute();
            });
        } catch (InvocationTargetException | InterruptedException e) {
            throw new ExecutionException(e.getMessage(), e);
        }
    }

    @CanExecute
    public boolean canExecute(RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getCurrentRepository().isLoaded();
    }

    protected NewRestAPIExtensionWizard newWizard(RepositoryAccessor repositoryAccessor, WidgetFactory widgetFactory,
            IWorkspace workspace, RestAPIAddressResolver addressReolver) throws CoreException {
        return new NewRestAPIExtensionWizard(
                repositoryAccessor.getRepositoryStore(RestAPIExtensionRepositoryStore.class),
                MavenPlugin.getProjectConfigurationManager(), new MavenProjectConfiguration(), workspace, widgetFactory,
                currentBDMGroupId(repositoryAccessor),currentBDMVersion(repositoryAccessor), addressReolver);
    }

    protected WizardDialog newWizardDialog(final NewRestAPIExtensionWizard wizard, String finishLabel) {
        return new CustomWizardDialog(Display.getDefault().getActiveShell(), wizard, finishLabel);
    }

    private String currentBDMGroupId(RepositoryAccessor repositoryAccessor) throws CoreException {
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = repositoryAccessor
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        final BusinessObjectModelFileStore bdmFilsStore = repositoryStore
                .getChild(BusinessObjectModelFileStore.BOM_FILENAME, true);
        return bdmFilsStore != null
                ? bdmFilsStore.loadArtifactDescriptor().getGroupId()
                : null;
    }
    
    private String currentBDMVersion(RepositoryAccessor repositoryAccessor) throws CoreException {
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = repositoryAccessor
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        final BusinessObjectModelFileStore bdmFilsStore = repositoryStore
                .getChild(BusinessObjectModelFileStore.BOM_FILENAME, true);
        return bdmFilsStore != null
                ? bdmFilsStore.loadArtifactDescriptor().getVersion()
                : null;
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        RepositoryAccessor repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        WidgetFactory widgetFactory = new WidgetFactory();
        RestAPIAddressResolver restAPIAddressResolver = new RestAPIAddressResolver(
                InstanceScope.INSTANCE.getNode(BonitaStudioPreferencesPlugin.PLUGIN_ID));
        execute(Display.getDefault().getActiveShell(), repositoryAccessor, widgetFactory,
                repositoryAccessor.getWorkspace(), restAPIAddressResolver);
        return null;
    }

}
