/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.core;

import static org.bonitasoft.studio.ui.wizard.WizardBuilder.newWizard;
import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bonitasoft.engine.api.ApplicationAPI;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.operation.GetApiSessionOperation;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.application.ui.provider.DeployApplicationFileStoreLabelProvider;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.dialog.MultiStatusDialog;
import org.bonitasoft.studio.ui.page.SelectionSinglePage;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

public class DeployApplicationAction {

    protected RepositoryAccessor repositoryAccessor;

    public DeployApplicationAction(RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
    }

    /**
     * Open a wizard to select an unique application descriptor to deploy, and then deploy this application descriptor
     */
    public void selectAndDeploy(Shell shell) {
        createWizard(newWizard())
                .open(shell, Messages.deploy)
                .ifPresent(applicationNodeContainer -> deployApplicationNodeContainer(shell, applicationNodeContainer,
                        new String[] { IDialogConstants.OK_LABEL }));
    }

    private WizardBuilder<ApplicationNodeContainer> createWizard(WizardBuilder<ApplicationNodeContainer> builder) {
        SelectionSinglePage<ApplicationRepositoryStore> selectApplicationDescriptorPage = new SelectionSinglePage<>(
                repositoryAccessor, ApplicationRepositoryStore.class, new DeployApplicationFileStoreLabelProvider());
        selectApplicationDescriptorPage.addUnselectableElements(getUnparsableFiles());
        return builder.withTitle(Messages.deployExistingApplication)
                .havingPage(newPage()
                        .withTitle(Messages.deployExistingApplication)
                        .withDescription(Messages.selectApplicationToDeploy)
                        .withControl(selectApplicationDescriptorPage))
                .onFinish(wizardContainer -> getApplicationNodeContainer(selectApplicationDescriptorPage));
    }

    private IRepositoryFileStore[] getUnparsableFiles() {
        return repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class).getChildren()
                .stream()
                .filter(this::isUnparsable)
                .collect(Collectors.toList())
                .toArray(new IRepositoryFileStore[0]);
    }

    private boolean isUnparsable(ApplicationFileStore fileStore) {
        try {
            fileStore.getContent();
            return false;
        } catch (ReadFileStoreException e) {
            return true;
        }
    }

    private Optional<ApplicationNodeContainer> getApplicationNodeContainer(
            SelectionSinglePage<ApplicationRepositoryStore> page) {
        ApplicationFileStore singleSelection = (ApplicationFileStore) page.getSingleSelection();
        if (singleSelection != null) {
            try {
                return Optional.ofNullable(singleSelection.getContent());
            } catch (ReadFileStoreException e) {
                throw new RuntimeException("Unable to retrieve application file store content", e);
            }
        }
        return Optional.empty();
    }

    public int deployApplicationNodeContainer(Shell shell, ApplicationNodeContainer applicationNodeContainer,
            String[] onFinishButtons) {
        if (applicationNodeContainer.getApplications().isEmpty()) {
            MessageDialog.openInformation(shell, Messages.deployDoneTitle, Messages.nothingToDeploy);
            return Dialog.CANCEL;
        }
        final GetApiSessionOperation apiSessionOperation = new GetApiSessionOperation();
        try {
            final APISession apiSession = apiSessionOperation.execute();
            final ApplicationAPI applicationAPI = BOSEngineManager.getInstance().getApplicationAPI(apiSession);
            final IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
            final DeployApplicationDescriptorRunnable deployOperation = getDeployOperation(apiSession, applicationAPI,
                    applicationNodeContainer);
            progressService.run(true, false, deployOperation);
            return openStatusDialog(shell, deployOperation, onFinishButtons);
        } catch (InvocationTargetException | InterruptedException | BonitaHomeNotSetException | ServerAPIException
                | UnknownAPITypeException e) {
            new ExceptionDialogHandler().openErrorDialog(shell, Messages.deployFailedTitle, e);
            return Dialog.CANCEL;
        } finally {
            apiSessionOperation.logout();
        }
    }

    protected int openStatusDialog(Shell shell, final DeployApplicationDescriptorRunnable deployOperation,
            String[] onFinishButtons) {
        MultiStatus status = deployOperation.getStatus() instanceof MultiStatus ? (MultiStatus) deployOperation.getStatus()
                : new MultiStatus(LivingApplicationPlugin.PLUGIN_ID, 0, new IStatus[] { deployOperation.getStatus() }, "",
                        null);
        return new MultiStatusDialog(shell, Messages.deployDoneTitle, Messages.deployDoneMessage, MessageDialog.INFORMATION,
                onFinishButtons, status).open();
    }

    protected DeployApplicationDescriptorRunnable getDeployOperation(APISession apiSession,
            final ApplicationAPI applicationAPI, final ApplicationNodeContainer applicationDescriptor)
            throws BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException {
        return new DeployApplicationDescriptorRunnable(applicationAPI, applicationDescriptor,
                RepositoryManager.getInstance() .getRepositoryStore(ApplicationRepositoryStore.class).getConverter());
    }

}
