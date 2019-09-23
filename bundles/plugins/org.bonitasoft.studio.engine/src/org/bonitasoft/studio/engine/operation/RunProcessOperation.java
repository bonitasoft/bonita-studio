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

import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
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
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;

/**
 * @author Romain Bioteau
 */
public class RunProcessOperation implements IRunnableWithProgress, Runnable {

    private IStatus status;
    private AbstractProcess processToRun;
    private URL url;
    private final ProcessSelector processSelector;
    private final RunOperationExecutionContext executionContext;

    public RunProcessOperation(final RunOperationExecutionContext executionContext, final ProcessSelector processSelector) {
        this.executionContext = executionContext;
        this.processSelector = processSelector;
    }

    protected DeployProcessOperation createDeployProcessOperation() {
        return new DeployProcessOperation();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
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
                        new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.deploymentFailedMessage,
                                sb.toString(), status, IStatus.ERROR | IStatus.WARNING).open();
                    }
                });
            }
            return;
        }
        processToRun = processSelector.getSelectedProcess();
        if (processToRun != null) {
            openBrowserForSelectedProcess(monitor, deployOperation, configurationId);
        } else {
            if (!executionContext.synchronousExecution()) {
                status = openConsole();
            }
        }
    }

    protected void openBrowserForSelectedProcess(final IProgressMonitor monitor,
            final DeployProcessOperation deployOperation, final String configurationId) {
        final boolean hasInitiator = hasInitiator(processToRun);
        try {
            if (!executionContext.synchronousExecution()) {
                BOSWebServerManager.getInstance().startServer(RepositoryManager.getInstance().getCurrentRepository(),
                        monitor);
                if (hasInitiator) {
                    if (hasInstanciationForm(processToRun)) {
                        url = deployOperation.getUrlFor(processToRun, monitor);
                        new OpenBrowserOperation(url).execute();
                    } else {
                        if (hasContractOnInstanciation((Pool) processToRun)) {
                            url = deployOperation.getUrlFor(processToRun, monitor);
                            redirectToPortalTaskListWhenContractAndNoFormAndInitiator();
                        } else {
                            final APISession session = BOSEngineManager.getInstance().createSession(processToRun,
                                    configurationId,
                                    monitor);
                            final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
                            final Long caseId = processApi.startProcess(deployOperation.getProcessDefId(processToRun))
                                    .getId();
                            url = new CaseDetailURLBuilder(processToRun, configurationId, caseId).toURL(monitor);
                            new OpenBrowserOperation(url).execute();
                        }
                    }
                } else {
                    url = deployOperation.getUrlFor(processToRun, monitor);
                    redirectToPortalTaskListWhenNoInitiator();
                }
            } else {
                //TODO: remove this use case which is used only in tests
                url = deployOperation.getUrlFor(processToRun, monitor);
            }
        } catch (final Exception e) {
            status = new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID, e.getMessage(), e);
            BonitaStudioLog.error(e);
        }
    }

    private boolean hasContractOnInstanciation(final Pool processToRun) {
        final Contract contract = processToRun.getContract();
        return contract != null && !contract.getInputs().isEmpty();
    }

    protected void redirectToPortalTaskListWhenContractAndNoFormAndInitiator() {
        redirectToPortalTaskList(
                EnginePreferenceConstants.TOGGLE_STATE_FOR_CONTRACT_AND_NOFORM_AND_INITIATOR,
                Messages.contractButNoFormTitle,
                Messages.bind(Messages.contractButNoFormMessage, processToRun.getName(),
                        org.bonitasoft.studio.common.Messages.bonitaPortalModuleName));
    }

    protected void redirectToPortalTaskListWhenNoInitiator() {
        redirectToPortalTaskList(
                EnginePreferenceConstants.TOGGLE_STATE_FOR_NO_INITIATOR,
                Messages.noInitiatorDefinedTitle,
                Messages.bind(Messages.noInitiatorDefinedMessage, processToRun.getName(),
                        org.bonitasoft.studio.common.Messages.bonitaPortalModuleName));
    }

    private void redirectToPortalTaskList(final String togglePreference, final String shellTitle, final String message) {
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
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

            }
        });

        status = openConsole();
    }

    private boolean hasInstanciationForm(final AbstractProcess processToRun) {
        return !FormMappingType.NONE.equals(processToRun.getFormMapping().getType());
    }

    private Status openConsole() {
        Status status = null;
        final ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
        final Command cmd = service.getCommand("org.bonitasoft.studio.application.openConsole");
        try {
            cmd.executeWithChecks(new ExecutionEvent());
        } catch (final Exception ex) {
            status = new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID, ex.getMessage(), ex);
            BonitaStudioLog.error(ex);
        }
        return status;
    }

    private boolean hasInitiator(final AbstractProcess p) {
        for (final Actor a : p.getActors()) {
            if (a.isInitiator()) {
                return true;
            }
        }
        return false;
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
            run(Repository.NULL_PROGRESS_MONITOR);
        } catch (final InvocationTargetException e) {
            BonitaStudioLog.error(e);
        } catch (final InterruptedException e) {
            BonitaStudioLog.error(e);
        }
    }

}
