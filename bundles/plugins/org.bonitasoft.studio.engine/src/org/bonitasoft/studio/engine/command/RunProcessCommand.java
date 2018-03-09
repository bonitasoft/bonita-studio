/**
 * Copyright (C) 2010-2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.engine.command;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.engine.operation.ProcessSelector;
import org.bonitasoft.studio.engine.operation.RunOperationExecutionContext;
import org.bonitasoft.studio.engine.operation.RunProcessOperation;
import org.bonitasoft.studio.engine.preferences.ServerPreferencePage;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.validation.common.operation.BatchValidationOperation;
import org.bonitasoft.studio.validation.common.operation.OffscreenEditPartFactory;
import org.bonitasoft.studio.validation.common.operation.RunProcessesValidationOperation;
import org.bonitasoft.studio.validation.common.operation.ValidationMarkerProvider;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

public class RunProcessCommand extends AbstractHandler implements IHandler {

    protected static final String APPLI_PATH = "/bonita?"; //$NON-NLS-1$;
    protected boolean runSynchronously;
    private Set<EObject> excludedObject;
    private IStatus status;
    private URL url;

    public RunProcessCommand() {
        this(false);
    }

    public RunProcessCommand(final boolean runSynchronously) {
        this.runSynchronously = runSynchronously;
        excludedObject = new HashSet<EObject>();
    }

    public RunProcessCommand(final Set<EObject> excludedObject) {
        new RunProcessCommand(excludedObject, false);
    }

    public RunProcessCommand(final Set<EObject> excludedObject, final boolean runSynchronously) {
        this(runSynchronously);
        this.excludedObject = excludedObject;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final String configurationId = retrieveConfigurationId(event);
        final String currentTheme = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getString(BonitaPreferenceConstants.DEFAULT_USERXP_THEME);
        final String installedTheme = ServerPreferencePage.getInstalledThemeId();
        final IProgressService service = PlatformUI.getWorkbench().getProgressService();
        if (installedTheme != null && !installedTheme.equals(currentTheme)) {
            BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.DEFAULT_USERXP_THEME, currentTheme);
        }

        final Set<AbstractProcess> executableProcesses = new ProcessSelector(event).getExecutableProcesses();
        if (BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getBoolean(BonitaPreferenceConstants.VALIDATION_BEFORE_RUN)) {
            final List<AbstractProcess> processes = new ArrayList<AbstractProcess>(executableProcesses);
            final RunProcessesValidationOperation validationOperation = new RunProcessesValidationOperation(new BatchValidationOperation(
                    new OffscreenEditPartFactory(org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory.getInstance()), new ValidationMarkerProvider()));
            validationOperation.addProcesses(processes);
            try {
                if (runSynchronously) {
                    validationOperation.run(Repository.NULL_PROGRESS_MONITOR);
                } else {
                    service.run(true, false, validationOperation);
                }
            } catch (final InvocationTargetException e) {
                throw new ExecutionException("Error occured during validation", e);
            } catch (final InterruptedException e) {
                //Continue
            }
            if (!validationOperation.displayConfirmationDialog()) {
                return null;
            }
        }

        final RunOperationExecutionContext executionContext = new RunOperationExecutionContext(configurationId);
        executionContext.setSynchronousExecution(runSynchronously);
        if (excludedObject != null && !excludedObject.isEmpty()) {
            executionContext.setExcludedObject(excludedObject);
        }
        final RunProcessOperation runProcessOperation = createRunProcessOperation(event, executionContext);

        try {
            if (runSynchronously) {
                Display.getDefault().syncExec(runProcessOperation);
            } else {
                service.run(true, false, runProcessOperation);
            }
            url = runProcessOperation.getUrl();
            status = runProcessOperation.getStatus();
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
            status = new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID, e.getMessage(), e);
            if (!runSynchronously && !status.isOK()) {
                Display.getDefault().syncExec(new Runnable() {

                    @Override
                    public void run() {
                        StringBuilder sb = new StringBuilder(Messages.deploymentFailedMessage);
                        sb.append("\n");
                        sb.append(status.getMessage());
                        new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.deploymentFailedMessage, sb.toString(), status,
                                IStatus.ERROR)
                                        .open();
                    }
                });
            }
        }
        return status;
    }

    protected RunProcessOperation createRunProcessOperation(final ExecutionEvent event, final RunOperationExecutionContext executionContext) {
        return new RunProcessOperation(executionContext, new ProcessSelector(event));
    }

    protected String retrieveConfigurationId(final ExecutionEvent event) {
        String configurationId = null;
        if (event != null) {
            configurationId = event.getParameter("configuration");
        }
        if (configurationId == null) {
            configurationId = ConfigurationPlugin.getDefault().getPreferenceStore().getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION);
        }
        return configurationId;
    }

    @Override
    public boolean isEnabled() {
        if (PlatformUI.isWorkbenchRunning() && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null) {
            final MainProcess process = ProcessSelector.getProcessInEditor();
            return process != null && process.isEnableValidation();
        }
        return false;
    }

    public URL getUrl() {
        return url;
    }

    public void setExcludedObject(final Set<EObject> exludedObject) {
        excludedObject = exludedObject;
    }

    public void setRunSynchronously(final boolean runSynchronously) {
        this.runSynchronously = runSynchronously;
    }

}
