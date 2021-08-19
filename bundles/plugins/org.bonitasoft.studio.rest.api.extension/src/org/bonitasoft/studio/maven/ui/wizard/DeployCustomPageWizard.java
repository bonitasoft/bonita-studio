/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui.wizard;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.http.HttpClientFactory;
import org.bonitasoft.studio.maven.CustomPageProjectFileStore;
import org.bonitasoft.studio.maven.CustomPageProjectRepositoryStore;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.operation.BuildCustomPageOperation;
import org.bonitasoft.studio.maven.operation.DeployCustomPageOperation;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.maven.ui.handler.CustomPageProjectSelectionProvider;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionFileStore;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;

public abstract class DeployCustomPageWizard extends Wizard {

    private final CustomPageProjectRepositoryStore<? extends CustomPageProjectFileStore> repositoryStore;
    private final WidgetFactory widgetFactory;
    private final IObservableValue<CustomPageProjectFileStore> fileStoreObservable;
    private final BOSEngineManager engineManager;
    private HttpClientFactory httpClientFactory;

    public DeployCustomPageWizard(final CustomPageProjectRepositoryStore<? extends CustomPageProjectFileStore> repositoryStore,
            final BOSEngineManager engineManager,
            final WidgetFactory widgetFactory,
            final HttpClientFactory httpClientFactory,
            final CustomPageProjectSelectionProvider selectionProvider) {
        this.repositoryStore = repositoryStore;
        this.engineManager = engineManager;
        this.httpClientFactory = httpClientFactory;
        this.widgetFactory = widgetFactory;
        fileStoreObservable = new WritableValue<CustomPageProjectFileStore>(selectionProvider.getSelection(), RestAPIExtensionFileStore.class);
        setDefaultPageImageDescriptor(Pics.getWizban());
        setNeedsProgressMonitor(true);
        setWindowTitle(Messages.deployWizardTitle);
    }

    @Override
    public void addPages() {
        final SelectCustomPageProjectPage selectionPage = new SelectCustomPageProjectPage(repositoryStore, widgetFactory,
                fileStoreObservable);
        selectionPage.setTitle(getSelectionPageTitle());
        selectionPage.setDescription(getSelectionPageDeployDescription());
        addPage(selectionPage);
    }
    
    protected abstract String getSelectionPageDeployDescription();

    protected abstract String getSelectionPageTitle();
   
    @Override
    public boolean performFinish() {
        final CustomPageProjectFileStore fileStore = fileStoreObservable.getValue();
        if(!fileStore.isReadOnly()) {
            return build(fileStore) && deploy(fileStore);
        }
        return deploy(fileStore);
    }

    protected boolean deploy(final CustomPageProjectFileStore fileStore) {
        final DeployCustomPageOperation operation = new DeployCustomPageOperation(engineManager,
                httpClientFactory,
                fileStore);
        try {
            getContainer().run(true, false, operation);
            final IStatus status = operation.getStatus();
            if (!status.isOK()) {
                return showDeployErrorDialog(status);
            }
        } catch (InvocationTargetException | InterruptedException e) {
            new BonitaErrorDialog(getShell(), Messages.errorTitle,
                    NLS.bind(Messages.deployFailedMessage, fileStore.getDisplayName()), e).open();
            BonitaStudioLog.error(e);
            return false;
        }
        return openDeploySuccessDialog(fileStore.getDisplayName());
    }

    protected boolean build(final CustomPageProjectFileStore fileStore) {
        try {
            final BuildCustomPageOperation buildOperation = fileStore.newBuildOperation();
            getContainer().run(true, false, buildOperation.asWorkspaceModifyOperation());
            return handleBuildResult(buildOperation.getStatus());
        } catch (final ReadFileStoreException | InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
            return showBuildErrorDialog(e);
        }
    }

    protected boolean handleBuildResult(final IStatus status) {
        if (!status.isOK()) {
            new BuildResultDialog(getShell(), status).open();
        }
        return status.isOK();
    }

    protected boolean openDeploySuccessDialog(final String restApiName) {
        MessageDialog.openInformation(getShell(), Messages.deploySuccessTitle,
                NLS.bind(Messages.deploySuccessMessage, restApiName));
        return true;
    }

    protected boolean showDeployErrorDialog(final IStatus status) {
        BonitaStudioLog.error("Failed to deploy custom page project.", status.getException(),
                RestAPIExtensionActivator.PLUGIN_ID);
        new BonitaErrorDialog(getShell(), Messages.errorTitle, status.getMessage(), status, IStatus.ERROR).open();
        return false;
    }

    protected boolean showBuildErrorDialog(final Throwable e) {
        BonitaStudioLog.error("Failed to build custom page project.", e, RestAPIExtensionActivator.PLUGIN_ID);
        new BonitaErrorDialog(getShell(), Messages.errorTitle, Messages.errorDuringProjectBuild, e).open();
        return false;
    }

}
