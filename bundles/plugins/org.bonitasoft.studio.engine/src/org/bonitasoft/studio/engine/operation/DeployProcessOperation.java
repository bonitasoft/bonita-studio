/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
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

import static com.google.common.collect.Iterables.filter;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.engine.bpm.process.Problem;
import org.bonitasoft.engine.bpm.process.ProcessDefinition;
import org.bonitasoft.engine.bpm.process.ProcessDefinitionNotFoundException;
import org.bonitasoft.engine.bpm.process.ProcessDeployException;
import org.bonitasoft.engine.bpm.process.ProcessEnablementException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.export.BarCreationException;
import org.bonitasoft.studio.engine.export.BarExporter;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.engine.ui.dialog.ProcessEnablementProblemsDialog;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;

import com.google.common.base.Predicate;

/**
 * @author Romain Bioteau
 * @author Aurelien Pupier
 */
public class DeployProcessOperation {

    private String configurationId;

    private final List<AbstractProcess> processes = new ArrayList<>();

    private final Map<AbstractProcess, Long> processIdsMap = new HashMap<>();

    private int problemResolutionResult;

    private boolean disablePopup;

    private IStatus status = ValidationStatus.ok();

    public void addProcessToDeploy(final AbstractProcess process) {
        Assert.isTrue(!(process instanceof MainProcess), "process can't be a MainProcess");
        if (!processes.contains(process)) {
            processes.add(process);
        }
    }

    public void setConfigurationId(final String configurationId) {
        this.configurationId = configurationId;
    }

    public URL getUrlFor(final AbstractProcess process, final IProgressMonitor monitor) throws MalformedURLException,
            UnsupportedEncodingException, URISyntaxException {
        long pId = 0;
        for (final AbstractProcess p : processIdsMap.keySet()) {
            if (p.getName().equals(process.getName()) && p.getVersion().equals(process.getVersion())) {
                pId = processIdsMap.get(p);
            }
        }
        return new ApplicationURLBuilder(process, configurationId, pId).toURL(monitor);
    }

    public IStatus run(final IProgressMonitor monitor) {
        Assert.isTrue(!processes.isEmpty());
        try {
            status = undeploy(processes, monitor);
        } catch (final Exception e) {
            BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
            status = ValidationStatus.error(Messages.undeploymentFailedMessage, e);
        }
        if (!status.isOK()) {
            return status;
        }
        try {
            status = deploy(monitor);
            if (status.isOK()) {
                status = enable(monitor);
            }
        } catch (Exception e) {
            BonitaStudioLog.error(e);
            status = ValidationStatus.error(Messages.deploymentFailedMessage, e);
        }
        return status;
    }

    private IStatus enable(final IProgressMonitor monitor) {
        IStatus status = Status.CANCEL_STATUS;
        for (final Entry<AbstractProcess, Long> entry : processIdsMap.entrySet()) {
            final AbstractProcess process = entry.getKey();
            monitor.subTask(Messages.bind(Messages.enablingProcess, getProcessLabel(process)));
            try {
                status = enableProcess(process, monitor);
                if (!status.isOK()) {
                    return status;
                }
            } catch (final Exception e) {
                return new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID, e.getMessage(), e);
            }

        }
        return status;
    }

    protected IStatus deploy(final IProgressMonitor monitor) {
        try {
            IStatus status = Status.OK_STATUS;
            for (final AbstractProcess process : processes) {
                status = deployProcess(process, monitor);
                if (status.getSeverity() != IStatus.OK) {
                    return status;
                }
            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
            if (e.getMessage() != null) {
                return new Status(Status.ERROR, EnginePlugin.PLUGIN_ID, e.getMessage(), e);
            }
            return new Status(Status.ERROR, EnginePlugin.PLUGIN_ID, Messages.deploymentFailedMessage, e);
        }

        return Status.OK_STATUS;
    }

    private IStatus deployProcess(final AbstractProcess process, final IProgressMonitor monitor) {
        monitor.subTask(Messages.bind(Messages.deployingProcess, getProcessLabel(process)));
        BusinessArchive bar;
        try {
            bar = BarExporter.getInstance().createBusinessArchive(addDefaultFormMapping(process), configurationId);
        } catch (final BarCreationException bce) {
            if (process != null) {
                BonitaStudioLog.log(String.format("Error when trying to create bar for process %s (%s)", process.getName(),
                        process.getVersion()));
                BonitaStudioLog.error(bce, EnginePlugin.PLUGIN_ID);
            }
            return new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID, bce.getDetails(), bce);
        }
        ProcessDefinition def = null;
        APISession session = null;
        try {
            session = BOSEngineManager.getInstance().createSession(process, configurationId, monitor);
            final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
            def = processApi.deploy(bar);
        } catch (final ProcessDeployException e) {
            if (process != null) {
                BonitaStudioLog.log(String.format("Error when trying to deploy process %s (%s)", process.getName(),
                        process.getVersion()));
                BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
            }
            return new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID, e.getMessage(), e);
        } catch (final Exception e1) {
            if (process != null) {
                BonitaStudioLog.error("Error when trying to deploy the process named: " + process.getName(),
                        EnginePlugin.PLUGIN_ID);
            }
            BonitaStudioLog.error(e1, EnginePlugin.PLUGIN_ID);
            return new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID, e1.getMessage(), e1);
        } finally {
            removeDefaultFormMapping(process);
            if (session != null) {
                BOSEngineManager.getInstance().logoutDefaultTenant(session);
            }
        }
        processIdsMap.put(process, def.getId());
        return Status.OK_STATUS;
    }

    private AbstractProcess addDefaultFormMapping(final AbstractProcess process) {
        for (final FormMapping mapping : filter(ModelHelper.getAllElementOfTypeIn(process, FormMapping.class),
                emptyDesignerFormMapping())) {
            try {
                mapping.eSetDeliver(false);
                mapping.setTargetForm(ExpressionHelper.createFormReferenceExpression(defaultCustomPage(mapping), null));
            } finally {
                mapping.eSetDeliver(true);
            }
        }
        return process;
    }

    private AbstractProcess removeDefaultFormMapping(final AbstractProcess process) {
        for (final FormMapping mapping : filter(ModelHelper.getAllElementOfTypeIn(process, FormMapping.class),
                emptyDesignerFormMapping())) {
            try {
                mapping.eSetDeliver(false);
                mapping.setTargetForm(ExpressionHelper.createFormReferenceExpression("", ""));
            } finally {
                mapping.eSetDeliver(true);
            }
        }
        return process;
    }

    private String defaultCustomPage(final FormMapping mapping) {
        return mapping.eContainer() instanceof Pool ? "processAutogeneratedForm" : "taskAutogeneratedForm";
    }

    private Predicate<FormMapping> emptyDesignerFormMapping() {
        return new Predicate<FormMapping>() {

            @Override
            public boolean apply(final FormMapping input) {
                return input.getType() == FormMappingType.INTERNAL
                        && !input.getTargetForm().hasContent()
                        && !input.eContainmentFeature().equals(ProcessPackage.Literals.RECAP_FLOW__OVERVIEW_FORM_MAPPING);
            }
        };
    }

    protected IStatus enableProcess(final AbstractProcess process, final IProgressMonitor monitor) throws Exception {
        final APISession session = BOSEngineManager.getInstance().createSession(process, configurationId, monitor);
        final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
        final Long processDefinitionId = processIdsMap.get(process);
        try {
            processApi.enableProcess(processDefinitionId);
        } catch (final ProcessEnablementException e) {
            return disablePopup ? new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID,
                    String.format("Failed to enable process '%s (%s)'", process.getName(), process.getVersion()), e)
                    : handleProcessEnablementException(process, monitor, processApi, processDefinitionId, e);
        } finally {
            if (session != null) {
                BOSEngineManager.getInstance().logoutDefaultTenant(session);
            }
        }
        return Status.OK_STATUS;
    }

    protected IStatus handleProcessEnablementException(final AbstractProcess process, final IProgressMonitor monitor,
            final ProcessAPI processApi,
            final Long processDefinitionId, final ProcessEnablementException e)
            throws ProcessDefinitionNotFoundException, Exception {
        final List<Problem> processResolutionProblems = processApi.getProcessResolutionProblems(processDefinitionId);
        if (processResolutionProblems.isEmpty()) {
            BonitaStudioLog.error(e);
        }
        IStatus status = openProcessEnablementProblemsDialog(process, processResolutionProblems);
        if (status.isOK()) {
            undeploy(Collections.singletonList(process), monitor);
            status = deployProcess(process, monitor);
            if (status.getSeverity() != IStatus.OK) {
                return status;
            }
            return enableProcess(process, monitor);
        } else {
            return Status.CANCEL_STATUS;
        }
    }

    protected IStatus undeploy(final List<AbstractProcess> processes, final IProgressMonitor monitor) throws Exception {
        final UndeployProcessOperation undeployProcessOperation = new UndeployProcessOperation(
                BOSEngineManager.getInstance());
        for (final AbstractProcess process : processes) {
            undeployProcessOperation.addProcessToUndeploy(process);
        }
        return undeployProcessOperation.run(monitor);
    }

    private String getProcessLabel(final AbstractProcess process) {
        return process.getName() + " (" + process.getVersion() + ")";
    }

    protected IStatus openProcessEnablementProblemsDialog(final AbstractProcess process,
            final List<Problem> processResolutionProblems) {
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
        return new ProcessEnablementProblemsDialog(Display.getDefault().getActiveShell(),
                Messages.processEnableFailedMessage,
                process, processResolutionProblems);
    }

    public long getProcessDefId(final AbstractProcess process) {
        return processIdsMap.get(process);
    }

    public void setDisablePopup(boolean synchronousExecution) {
        this.disablePopup = synchronousExecution;
    }

    public IStatus getStatus() {
        return status;
    }

}
