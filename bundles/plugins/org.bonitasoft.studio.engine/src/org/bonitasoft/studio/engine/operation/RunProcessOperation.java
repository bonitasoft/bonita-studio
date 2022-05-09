/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.engine.operation;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.bonitasoft.engine.api.ApplicationAPI;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.business.application.ApplicationNotFoundException;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.engine.preferences.EnginePreferenceConstants;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.preferences.browser.OpenBrowserOperation;
import org.bonitasoft.studio.ui.dialog.MultiStatusDialog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 */
public class RunProcessOperation implements IRunnableWithProgress, Runnable {

    private IStatus status;
    private AbstractProcess processToRun;
    private URL url;
    private final ProcessSelector processSelector;
    private final RunOperationExecutionContext executionContext;

    public RunProcessOperation(final RunOperationExecutionContext executionContext,
            final ProcessSelector processSelector) {
        this.executionContext = executionContext;
        this.processSelector = processSelector;
    }

    protected DeployProcessOperation createDeployProcessOperation() {
        return new DeployProcessOperation();
    }

    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.running, IProgressMonitor.UNKNOWN);

        final DeployProcessOperation deployOperation = createDeployProcessOperation();
        final String configurationId = executionContext.getConfigurationId();
        deployOperation.setConfigurationId(configurationId);
        deployOperation.setDisablePopup(executionContext.synchronousExecution());
        for (final AbstractProcess process : processSelector.getExecutableProcesses()) {
            deployOperation.addProcessToDeploy(process);
        }
        status = deployOperation.run(monitor);
        if (status == null
                || status.getSeverity() == IStatus.CANCEL) {
            return;
        }
        if (!status.isOK()) {
            if (!executionContext.synchronousExecution()) {
                Display.getDefault().syncExec(new Runnable() {

                    @Override
                    public void run() {
                        final StringBuilder sb = new StringBuilder(Messages.deploymentFailedMessage);
                        sb.append(":\n");
                        sb.append(status.getMessage());
                        if (status instanceof MultiStatus) {
                            MultiStatusDialog multiStatusDialog = new MultiStatusDialog(
                                    Display.getDefault().getActiveShell(),
                                    Messages.deploymentFailedMessage, Messages.deploymentFailedMessage,
                                    new String[] { IDialogConstants.OK_LABEL }, (MultiStatus) status);
                            multiStatusDialog.setLevel(IStatus.WARNING);
                            multiStatusDialog.open();
                        } else {
                            new BonitaErrorDialog(Display.getDefault().getActiveShell(),
                                    Messages.deploymentFailedMessage,
                                    sb.toString(), status, IStatus.ERROR | IStatus.WARNING).open();
                        }
                    }
                });
            }
            return;
        }
        processToRun = processSelector.getSelectedProcess();
        if (processToRun != null) {
            openBrowserForSelectedProcess(monitor, deployOperation, configurationId);
        }
    }

    private boolean isUserAppDeployed(APISession session) {
        String appToken = EnginePlugin.getDefault().getPreferenceStore()
                .getString(EnginePreferenceConstants.USER_APP_TOKEN);
        try {
            ApplicationAPI applicationAPI = BOSEngineManager.getInstance().getApplicationAPI(session);
            return applicationAPI.getApplicationByToken(appToken) != null;
        } catch (BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException
                | ApplicationNotFoundException e) {
            return false;
        }
    }

    protected void openBrowserForSelectedProcess(final IProgressMonitor monitor,
            final DeployProcessOperation deployOperation, final String configurationId) {
        final boolean hasInitiator = hasInitiator(processToRun);
        try {
            if (!executionContext.synchronousExecution()) {
                openBrowser(monitor, deployOperation, configurationId, hasInitiator);
            } else {
                //TODO: remove this use case which is used only in tests
                url = deployOperation.getUrlFor(processToRun, monitor);
            }
        } catch (final Exception e) {
            status = new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID, e.getMessage(), e);
            BonitaStudioLog.error(e);
        }
    }

    private void openBrowser(final IProgressMonitor monitor, final DeployProcessOperation deployOperation,
            final String configurationId, final boolean hasInitiator) throws Exception {
        BOSWebServerManager.getInstance().startServer(RepositoryManager.getInstance().getCurrentRepository(),
                monitor);
        final APISession session = BOSEngineManager.getInstance()
                .createSession(processToRun, configurationId, monitor);
        boolean userAppDeployed = isUserAppDeployed(session);
        if (hasInitiator) {
            if (hasInstanciationForm(processToRun)) {
                url = deployOperation.getUrlFor(processToRun, monitor);
            } else {
                if (hasContractOnInstanciation((Pool) processToRun)) {
                    url = redirectToTaskListWhenContractAndNoFormAndInitiator();
                } else {
                    final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
                    final Long caseId = processApi.startProcess(deployOperation.getProcessDefId(processToRun))
                            .getId();
                    url = new CaseDetailURLBuilder(processToRun, configurationId, caseId).toURL(monitor);
                }
            }
        } else {
            url = redirectToTaskListWhenNoInitiator();
        }
        if (userAppDeployed) {
            new OpenBrowserOperation(url).execute();
        } else {
            openProcessDeployedDialog();
        }
    }

    private void openProcessDeployedDialog() {
        Display.getDefault().syncExec(() -> {
            if (MessageDialog.openQuestion(Display.getDefault().getActiveShell(),
                    Messages.processDeployedTitle,
                    String.format(Messages.processDeployedButNoUserAppFound, 
                            processToRun.getName(),
                            processToRun.getVersion()))) {
                try {
                    new OpenBrowserOperation(new DirectoryAppURLBuilder().toURL(null)).execute();
                } catch (MalformedURLException | UnsupportedEncodingException | URISyntaxException e) {
                    BonitaStudioLog.error(e);
                }
            }
        });
    }

    private boolean hasContractOnInstanciation(final Pool processToRun) {
        final Contract contract = processToRun.getContract();
        return contract != null && !contract.getInputs().isEmpty();
    }

    private URL redirectToTaskListWhenContractAndNoFormAndInitiator() {
        return redirectToTaskList(
                EnginePreferenceConstants.TOGGLE_STATE_FOR_CONTRACT_AND_NOFORM_AND_INITIATOR,
                Messages.contractButNoFormTitle,
                NLS.bind(Messages.contractButNoFormMessage, processToRun.getName(), processToRun.getVersion()));
    }

    private URL redirectToTaskListWhenNoInitiator() {
        return redirectToTaskList(
                EnginePreferenceConstants.TOGGLE_STATE_FOR_NO_INITIATOR,
                Messages.noInitiatorDefinedTitle,
                NLS.bind(Messages.noInitiatorDefinedMessage, processToRun.getName()));
    }

    private URL redirectToTaskList(final String togglePreference, final String shellTitle,
            final String message) {
        Display.getDefault().syncExec(() -> {
            final IPreferenceStore preferenceStore = EnginePlugin.getDefault().getPreferenceStore();
            final String pref = preferenceStore.getString(togglePreference);
            if (MessageDialogWithToggle.NEVER.equals(pref)) {
                MessageDialogWithToggle.openWarning(Display.getDefault().getActiveShell(),
                        shellTitle,
                        message,
                        Messages.dontaskagain,
                        false,
                        preferenceStore,
                        togglePreference);
            }

        });
        try {
            return new TaskListURLBuilder(processToRun, executionContext.getConfigurationId()).toURL(null);
        } catch (MalformedURLException | UnsupportedEncodingException | URISyntaxException e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

    private boolean hasInstanciationForm(final AbstractProcess processToRun) {
        return !FormMappingType.NONE.equals(processToRun.getFormMapping().getType());
    }

    private boolean hasInitiator(final AbstractProcess p) {
        return p.getActors().stream().anyMatch(Actor::isInitiator);
    }

    public IStatus getStatus() {
        return status;
    }

    public URL getUrl() {
        return url;
    }

    @Override
    public void run() {
        try {
            run(AbstractRepository.NULL_PROGRESS_MONITOR);
        } catch (final InvocationTargetException e) {
            BonitaStudioLog.error(e);
        } catch (final InterruptedException e) {
            BonitaStudioLog.error(e);
        }
    }

}
