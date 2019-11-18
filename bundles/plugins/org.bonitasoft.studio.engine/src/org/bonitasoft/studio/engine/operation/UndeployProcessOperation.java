/**
 * Copyright (C) 2015 Bonitasoft S.A.
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

package org.bonitasoft.studio.engine.operation;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.process.ProcessActivationException;
import org.bonitasoft.engine.bpm.process.ProcessDefinitionNotFoundException;
import org.bonitasoft.engine.bpm.process.ProcessDeploymentInfo;
import org.bonitasoft.engine.bpm.process.ProcessDeploymentInfoCriterion;
import org.bonitasoft.engine.exception.DeletionException;
import org.bonitasoft.engine.exception.ProcessInstanceHierarchicalDeletionException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * @author Romain Bioteau
 * @author Aurelien Pupier
 */
public class UndeployProcessOperation {

    private static final String BONITA_API_TOKEN_HEADER = "X-Bonita-API-Token";
    private static final String API_LOGOUT = "/bonita/logoutservice";
    private static final String API_SESSION = "/bonita/API/system/session/1";
    private static final String API_PROCESS_RESOURCE = "/bonita/API/bpm/process/";
    private static final String HTTP_METHOD_DELETE = "DELETE";
    private static final String HTTP_METHOD_POST = "POST";
    private static final int MAX_RESULTS = 1000;
    private String configurationId;
    private final List<AbstractProcess> processes = new ArrayList<AbstractProcess>();
    private final BOSEngineManager engineManager;
    private boolean undeployAll;

    public UndeployProcessOperation(final BOSEngineManager engineManager) {
        this.engineManager = engineManager;
    }

    public UndeployProcessOperation undeployAll() {
        this.undeployAll = true;
        return this;
    }

    public void addProcessToUndeploy(final AbstractProcess process) {
        Assert.isTrue(!(process instanceof MainProcess), "process can't be a MainProcess");
        if (!processes.contains(process)) {
            processes.add(process);
        }
    }

    public void setConfigurationId(final String configurationId) {
        this.configurationId = configurationId;
    }

    public IStatus run(final IProgressMonitor monitor) {
        if (undeployAll) {
            try {
                return undeployAll(monitor);
            } catch (final Exception e) {
                BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
                return new Status(Status.ERROR, EnginePlugin.PLUGIN_ID, String.format(Messages.undeploymentFailedMessage+": %s",e.getMessage()), e);
            }
        } else {
            Assert.isTrue(!processes.isEmpty());
            return undeploy(monitor);
        }

    }

    protected IStatus undeploy(final IProgressMonitor monitor) {
        for (final AbstractProcess process : processes) {
            try {
                undeployProcess(process, monitor);
            } catch (Exception e) {
                BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
                return new Status(Status.ERROR, EnginePlugin.PLUGIN_ID, String.format("%s (%s): %s",process.getName(),process.getVersion(),e.getMessage()), e);
            }
        }
        return Status.OK_STATUS;
    }

    protected IStatus undeployAll(final IProgressMonitor monitor) throws Exception {
        final APISession session = engineManager.loginDefaultTenant(monitor);
        try {
            final ProcessAPI processApi = engineManager.getProcessAPI(session);
            for (final ProcessDeploymentInfo info : processApi.getProcessDeploymentInfos(0, Integer.MAX_VALUE, ProcessDeploymentInfoCriterion.DEFAULT)) {
                disableProcessDefinition(info.getDisplayName(), processApi, info.getProcessId(), monitor);
                deleteProcessInstances(info.getDisplayName(), processApi, info.getProcessId(), monitor);
                deleteArchivedProcessInstances(processApi, info.getProcessId());
                deleteProcessDefinition(info.getDisplayName(), processApi, info.getProcessId(), monitor);
            }
        } catch (final ProcessDefinitionNotFoundException e) {
            // Skip
        } finally {
            if (session != null) {
                engineManager.logoutDefaultTenant(session);
            }
        }
        return Status.OK_STATUS;
    }

    protected void undeployProcess(final AbstractProcess process, final IProgressMonitor monitor) throws Exception {
        final APISession session = engineManager.createSession(process, configurationId, monitor);
        try {
            final ProcessAPI processApi = engineManager.getProcessAPI(session);
            final long nbDeployedProcesses = processApi.getNumberOfProcessDeploymentInfos();
            if (nbDeployedProcesses > 0) {
                final long processDefinitionId = processApi.getProcessDefinitionId(process.getName(), process.getVersion());
                disableProcessDefinition(getProcessLabel(process), processApi, processDefinitionId, monitor);
                deleteProcessInstances(getProcessLabel(process), processApi, processDefinitionId, monitor);
                deleteArchivedProcessInstances(processApi, processDefinitionId);
                deleteProcessDefinition(getProcessLabel(process), processApi, processDefinitionId, monitor);
            }
        } catch (final ProcessDefinitionNotFoundException e) {
            // Skip
        } finally {
            if (session != null) {
                engineManager.logoutDefaultTenant(session);
            }
        }
    }

    private void deleteProcessDefinition(final String processLabel, final ProcessAPI processApi, final long processDefinitionId,
            final IProgressMonitor monitor)
            throws DeletionException, URISyntaxException, IOException {
        monitor.subTask(Messages.bind(Messages.deletingProcessDefinition, processLabel));
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        String apiToken = logToSession(monitor);
        try {
            deleteProcessDefinition(processApi, processDefinitionId,apiToken);
        } finally {
            logoutFromSession(apiToken);
        }
    }

    protected void deleteProcessDefinition(final ProcessAPI processApi, final long processDefinitionId, String apiToken) throws IOException, MalformedURLException,
            ProtocolException, DeletionException {
        final HttpURLConnection deleteConnection = openConnection(getUrlBase() + API_PROCESS_RESOURCE + processDefinitionId);
        deleteConnection.setRequestMethod(HTTP_METHOD_DELETE);
        deleteConnection.setRequestProperty(BONITA_API_TOKEN_HEADER, apiToken);
        if (HttpURLConnection.HTTP_OK != deleteConnection.getResponseCode()) {
            processApi.deleteProcessDefinition(processDefinitionId);
        }
        deleteConnection.disconnect();
    }

    protected HttpURLConnection openConnection(final String url) throws IOException, MalformedURLException {
        return (HttpURLConnection) new URL(url).openConnection();
    }

    protected String getUrlBase() {
        return BOSWebServerManager.getInstance().generateUrlBase();
    }

    protected void logoutFromSession(String apiToken) throws IOException, MalformedURLException, ProtocolException {
        final HttpURLConnection logoutConnection = openConnection(getUrlBase() + API_LOGOUT);
        logoutConnection.setRequestMethod(HTTP_METHOD_POST);
        logoutConnection.setRequestProperty(BONITA_API_TOKEN_HEADER, apiToken);
        if (HttpURLConnection.HTTP_OK != logoutConnection.getResponseCode()) {
            BonitaStudioLog.error("Cannot unlog to session " + logoutConnection.getResponseCode(), EnginePlugin.PLUGIN_ID);
        }
    }

    protected String logToSession(final IProgressMonitor monitor) throws MalformedURLException, UnsupportedEncodingException, URISyntaxException, IOException {
        HttpURLConnection connection = openLoginConnection(monitor);
        if (HttpURLConnection.HTTP_OK != connection.getResponseCode()) {
            BonitaStudioLog.error("Cannot log to session " + connection.getResponseCode(), EnginePlugin.PLUGIN_ID);
        }
        connection.disconnect();

        connection = openConnection(getUrlBase() + API_SESSION);
        String apiToken = connection.getHeaderField(BONITA_API_TOKEN_HEADER);
        connection.disconnect();
        return apiToken;
    }

    protected HttpURLConnection openLoginConnection(final IProgressMonitor monitor) throws MalformedURLException, UnsupportedEncodingException,
            URISyntaxException, IOException {
        final URL loginUrl = new LoginUrlBuilder().toURL(monitor);
        final HttpURLConnection connection = (HttpURLConnection) loginUrl.openConnection();
        return connection;
    }

    private void deleteArchivedProcessInstances(final ProcessAPI processApi, final long processDefinitionId) throws DeletionException {
        boolean allInstancesDeleted = false;
        while (!allInstancesDeleted) {
            final long nbDeletedProcessInstances = processApi.deleteArchivedProcessInstances(processDefinitionId, 0, MAX_RESULTS);
            allInstancesDeleted = nbDeletedProcessInstances < MAX_RESULTS;
        }
    }

    private void deleteProcessInstances(final String processLabel, final ProcessAPI processApi, final long processDefinitionId,
            final IProgressMonitor monitor)
            throws DeletionException {
        boolean allInstancesDeleted = false;
        monitor.subTask(Messages.bind(Messages.deletingProcessInstances, processLabel));
        while (!allInstancesDeleted) {
            try {
                final long nbDeletedProcessInstances = processApi.deleteProcessInstances(processDefinitionId, 0, MAX_RESULTS);
                allInstancesDeleted = nbDeletedProcessInstances < MAX_RESULTS;
            } catch (final DeletionException e) {
                if (e instanceof ProcessInstanceHierarchicalDeletionException) {
                    final long blockingProcessId = ((ProcessInstanceHierarchicalDeletionException) e).getProcessInstanceId();
                    processApi.deleteProcessInstance(blockingProcessId);
                } else {
                    throw e;
                }
            }
        }
    }

    private void disableProcessDefinition(final String processLabel, final ProcessAPI processApi, final long processDefinitionId,
            final IProgressMonitor monitor)
            throws ProcessDefinitionNotFoundException {
        monitor.subTask(Messages.bind(Messages.undeploying, processLabel));
        try {
            monitor.subTask(Messages.bind(Messages.disablingProcessDefinition, processLabel));
            processApi.disableProcess(processDefinitionId);
        } catch (final ProcessActivationException e) {
            BonitaStudioLog.debug("Failed to disable the process", e, EnginePlugin.PLUGIN_ID);
        }
    }

    private String getProcessLabel(final AbstractProcess process) {
        return process.getName() + " (" + process.getVersion() + ")";
    }

}
