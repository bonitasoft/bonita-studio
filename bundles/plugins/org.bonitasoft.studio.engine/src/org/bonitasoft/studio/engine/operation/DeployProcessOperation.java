/**
 * Copyright (C) 2009-2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.bonitasoft.studio.engine.operation;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.engine.bpm.process.Problem;
import org.bonitasoft.engine.bpm.process.ProcessActivationException;
import org.bonitasoft.engine.bpm.process.ProcessDefinition;
import org.bonitasoft.engine.bpm.process.ProcessDefinitionNotFoundException;
import org.bonitasoft.engine.bpm.process.ProcessDeployException;
import org.bonitasoft.engine.bpm.process.ProcessEnablementException;
import org.bonitasoft.engine.exception.DeletionException;
import org.bonitasoft.engine.exception.ProcessInstanceHierarchicalDeletionException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.export.BarExporter;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.engine.ui.dialog.ProcessEnablementProblemsDialog;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 * @author Aurelien Pupier
 */
public class DeployProcessOperation {

    private static final int MAX_RESULTS = 1000;

    private Set<EObject> excludedObject;

    private String configurationId;

    private final List<AbstractProcess> processes;

    private final Map<AbstractProcess, Long> processIdsMap;

    private int problemResolutionResult;

    public DeployProcessOperation() {
        processes = new ArrayList<AbstractProcess>();
        processIdsMap = new HashMap<AbstractProcess, Long>();
    }

    public void setObjectToExclude(Set<EObject> excludedObject) {
        this.excludedObject = excludedObject;
    }

    public void addProcessToDeploy(AbstractProcess process) {
        Assert.isTrue(!(process instanceof MainProcess), "process can't be a MainProcess");
        if (!processes.contains(process)) {
            processes.add(process);
        }
    }

    public void setConfigurationId(String configurationId) {
        this.configurationId = configurationId;
    }

    public URL getUrlFor(AbstractProcess process, IProgressMonitor monitor) throws MalformedURLException, UnsupportedEncodingException, URISyntaxException {
        long pId = 0;
        for (AbstractProcess p : processIdsMap.keySet()) {
            if (p.getName().equals(process.getName()) && p.getVersion().equals(process.getVersion())) {
                pId = processIdsMap.get(p);
            }
        }
        return new ApplicationURLBuilder(process, pId, configurationId).toURL(monitor);
    }

    public IStatus run(IProgressMonitor monitor) {
        Assert.isTrue(!processes.isEmpty());
        try {
            undeploy(monitor);
        } catch (Exception e) {
            BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
            return new Status(Status.ERROR, EnginePlugin.PLUGIN_ID, Messages.undeploymentFailedMessage, e);
        }
        try {
            IStatus status = deploy(monitor);
            if (status.getSeverity() == IStatus.OK) {
                status = enable(monitor);
            }
            return status;
        } catch (Exception e) {
            BonitaStudioLog.error(e);
            return new Status(Status.ERROR, EnginePlugin.PLUGIN_ID, Messages.deploymentFailedMessage, e);
        }
    }

    protected APISession createSession(AbstractProcess process, IProgressMonitor monitor) throws Exception {
        Configuration configuration = BarExporter.getInstance().getConfiguration(process, configurationId);
        APISession session;
        try {
            session = BOSEngineManager.getInstance().loginTenant(configuration.getUsername(), configuration.getPassword(), monitor);
        } catch (Exception e1) {
            throw new Exception(Messages.bind(Messages.loginFailed, new String[] { configuration.getUsername(), process.getName(), process.getVersion() }), e1);
        }
        if (session == null) {
            throw new Exception(Messages.bind(Messages.loginFailed, new String[] { configuration.getUsername(), process.getName(), process.getVersion() }));
        }
        return session;
    }

    private IStatus enable(IProgressMonitor monitor) {
        IStatus status = Status.CANCEL_STATUS;
        for (Entry<AbstractProcess, Long> entry : processIdsMap.entrySet()) {
            AbstractProcess process = entry.getKey();
            monitor.subTask(Messages.bind(Messages.enablingProcess, getProcessLabel(process)));
            try {
                status = enableProcess(process, monitor);
                if (!status.isOK()) {
                    return status;
                }
            } catch (Exception e) {
                return new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID, e.getMessage(), e);
            }

        }
        return status;
    }

    protected IStatus deploy(IProgressMonitor monitor) {
        try {
            IStatus status = Status.OK_STATUS;
            for (AbstractProcess process : processes) {
                status = deployProcess(process, monitor);
                if (status.getSeverity() != IStatus.OK) {
                    return status;
                }
            }
        } catch (Exception e) {
            BonitaStudioLog.error(e);
            if (e.getMessage() != null) {
                return new Status(Status.ERROR, EnginePlugin.PLUGIN_ID, e.getMessage(), e);
            } else {
                return new Status(Status.ERROR, EnginePlugin.PLUGIN_ID, Messages.deploymentFailedMessage, e);
            }
        }

        return Status.OK_STATUS;
    }

    private IStatus deployProcess(AbstractProcess process, IProgressMonitor monitor) throws Exception {
        monitor.subTask(Messages.bind(Messages.deployingProcess, getProcessLabel(process)));
        BusinessArchive bar = BarExporter.getInstance().createBusinessArchive(process, configurationId, excludedObject);
        ProcessDefinition def = null;
        APISession session = null;
        try {
            session = createSession(process, monitor);
            final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
            def = processApi.deploy(bar);
        } catch (ProcessDeployException e) {
            if (process != null) {
                BonitaStudioLog.log("Error when trying to deploy the process named: " + process.getName());
                BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
            }
            return new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID, e.getMessage(), e);
        } catch (Exception e1) {
            if (process != null) {
                BonitaStudioLog.error("Error when trying to deploy the process named: " + process.getName(), EnginePlugin.PLUGIN_ID);
            }
            BonitaStudioLog.error(e1, EnginePlugin.PLUGIN_ID);
            return new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID, e1.getMessage(), e1);
        } finally {
            if (session != null) {
                BOSEngineManager.getInstance().logoutDefaultTenant(session);
            }
        }
        processIdsMap.put(process, def.getId());
        return Status.OK_STATUS;
    }

    protected IStatus enableProcess(final AbstractProcess process, IProgressMonitor monitor) throws Exception {
        APISession session = createSession(process, monitor);
        final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
        Long processDefinitionId = processIdsMap.get(process);
        try {
            processApi.enableProcess(processDefinitionId);
        } catch (ProcessEnablementException e) {
            List<Problem> processResolutionProblems = processApi.getProcessResolutionProblems(processDefinitionId);
            IStatus status = openProcessEnablementProblemsDialog(process, processResolutionProblems);
            if (status.isOK()) {
                undeployProcess(process, monitor);
                status = deployProcess(process, monitor);
                if (status.getSeverity() != IStatus.OK) {
                    return status;
                }
                return enableProcess(process, monitor);
            } else {
                return Status.CANCEL_STATUS;
            }
        } finally {
            if (session != null) {
                BOSEngineManager.getInstance().logoutDefaultTenant(session);
            }
        }
        return Status.OK_STATUS;
    }

    /**
     * @param monitor
     * @throws DeletingEnabledProcessException
     * @throws Exception
     */
    protected void undeploy(IProgressMonitor monitor) throws Exception {
        for (AbstractProcess process : processes) {
            undeployProcess(process, monitor);
        }
    }

    protected void undeployProcess(AbstractProcess process, IProgressMonitor monitor) throws Exception {
        final APISession session = createSession(process, monitor);
        try {
            final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
            long nbDeployedProcesses = processApi.getNumberOfProcessDeploymentInfos();
            if (nbDeployedProcesses > 0) {
                long processDefinitionId = processApi.getProcessDefinitionId(process.getName(), process.getVersion());
                disableProcessDefinition(process, processApi, processDefinitionId, monitor);
                deleteProcessInstances(process, processApi, processDefinitionId, monitor);
                deleteArchivedProcessInstances(processApi, processDefinitionId);
                deleteProcessDefinition(process, processApi, processDefinitionId, monitor);
            }
        } catch (ProcessDefinitionNotFoundException e) {
            // Skip
        } finally {
            if (session != null) {
                BOSEngineManager.getInstance().logoutDefaultTenant(session);
            }
        }
    }

    private void deleteProcessDefinition(AbstractProcess process, final ProcessAPI processApi, long processDefinitionId, IProgressMonitor monitor)
            throws DeletionException {
        monitor.subTask(Messages.bind(Messages.deletingProcessDefinition, getProcessLabel(process)));
        processApi.deleteProcessDefinition(processDefinitionId);
    }

    private void deleteArchivedProcessInstances(final ProcessAPI processApi, long processDefinitionId) throws DeletionException {
        boolean allInstancesDeleted = false;
        while (!allInstancesDeleted) {
            long nbDeletedProcessInstances = processApi.deleteArchivedProcessInstances(processDefinitionId, 0, MAX_RESULTS);
            allInstancesDeleted = nbDeletedProcessInstances < MAX_RESULTS;
        }
    }

    private void deleteProcessInstances(AbstractProcess process, final ProcessAPI processApi, long processDefinitionId, IProgressMonitor monitor)
            throws DeletionException {
        boolean allInstancesDeleted = false;
        monitor.subTask(Messages.bind(Messages.deletingProcessInstances, getProcessLabel(process)));
        while (!allInstancesDeleted) {
            try {
                long nbDeletedProcessInstances = processApi.deleteProcessInstances(processDefinitionId, 0, MAX_RESULTS);
                allInstancesDeleted = nbDeletedProcessInstances < MAX_RESULTS;
            } catch (DeletionException e) {
                if (e instanceof ProcessInstanceHierarchicalDeletionException) {
                    long blockingProcessId = ((ProcessInstanceHierarchicalDeletionException) e).getProcessInstanceId();
                    processApi.deleteProcessInstance(blockingProcessId);
                } else {
                    throw e;
                }
            }
        }
    }

    private void disableProcessDefinition(AbstractProcess process, final ProcessAPI processApi, long processDefinitionId, IProgressMonitor monitor)
            throws ProcessDefinitionNotFoundException {
        monitor.subTask(Messages.bind(Messages.undeploying, getProcessLabel(process)));
        try {
            monitor.subTask(Messages.bind(Messages.disablingProcessDefinition, getProcessLabel(process)));
            processApi.disableProcess(processDefinitionId);
        } catch (ProcessActivationException e) {

        }
    }

    private String getProcessLabel(AbstractProcess process) {
        return process.getName() + " (" + process.getVersion() + ")";
    }

    protected IStatus openProcessEnablementProblemsDialog(final AbstractProcess process, final List<Problem> processResolutionProblems) {
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                problemResolutionResult = createProcessEnablementProblemsDialog(process, processResolutionProblems).open();
            }

        });

        return problemResolutionResult == IDialogConstants.OK_ID ? Status.OK_STATUS : Status.CANCEL_STATUS;
    }

    protected ProcessEnablementProblemsDialog createProcessEnablementProblemsDialog(final AbstractProcess process,
            final List<Problem> processResolutionProblems) {
        return new ProcessEnablementProblemsDialog(Display.getDefault().getActiveShell(), Messages.processEnableFailedMessage,
                process, processResolutionProblems);
    }

}
