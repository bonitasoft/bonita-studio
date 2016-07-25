/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.simulation.commands;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.bonitasoft.simulation.engine.SimulationEngine;
import org.bonitasoft.simulation.model.process.SimProcess;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.simulation.InjectionPeriod;
import org.bonitasoft.studio.model.simulation.LoadProfile;
import org.bonitasoft.studio.simulation.engine.SimulationExporter;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.bonitasoft.studio.simulation.repository.SimulationLoadProfileRepositoryStore;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;

/**
 * @author Baptiste Mesta
 */
public class SimulationWithMonitorRunner implements IRunnableWithProgress {

    /**
     * @author Baptiste Mesta
     */
    private class SimulationRunnerThread extends Thread {

        private final AbstractProcess simulationProcess;

        public SimulationRunnerThread(final AbstractProcess process) {
            super("Simulation Runner");
            simulationProcess = process;
            new Color(255);//FIX CLASSLOADER ISSUE
        }

        private SimulationEngine simulationEngine;

        public synchronized void stopThread() {
            if (simulationEngine != null) {
                simulationEngine.stop();
            }
        }

        public long getCurrentTime() {
            return SimulationEngine.currentTime;
        }

        @Override
        public void run() {
            try {
                final SimulationExporter exporter = new SimulationExporter();
                final SimProcess simProc = exporter.createSimulationProcess(simulationProcess);
                final IRepositoryFileStore file = RepositoryManager.getInstance().getRepositoryStore(SimulationLoadProfileRepositoryStore.class)
                        .getChild(loadProfileId + "." + SimulationLoadProfileRepositoryStore.SIMULATION_LOADPROFILE_EXT);

                if (file != null) {
                    final LoadProfile profile = (LoadProfile) file.getContent();
                    final org.bonitasoft.simulation.model.loadprofile.LoadProfile loadProfile = exporter.createLoadProfile(profile);

                    final Properties executionProperties = new Properties();
                    executionProperties.put(SimulationEngine.REPORT_WORKSPACE, path);
                    executionProperties.put(SimulationEngine.REPORT_TIMESPAN, timespan);
                    executionProperties.put(SimulationEngine.EXPORT_MODE, SimulationEngine.HTML_MODE);

                    simulationEngine = new SimulationEngine(simProc, loadProfile, exporter.getSimProcessResources(simProc.getName()), executionProperties);
                    simulationEngine.start();
                    cancelled = simulationEngine.isStopped();
                    reportFile = simulationEngine.getReportFile();
                }
            } catch (final Exception e) {
                BonitaStudioLog.error(e);
                Display.getDefault().syncExec(new Runnable() {

                    @Override
                    public void run() {
                        ErrorDialog.openError(Display.getCurrent().getActiveShell(), "Error during simulation",
                                Messages.simulation_Error, new Status(Status.ERROR, org.bonitasoft.studio.simulation.SimulationPlugin.PLUGIN_ID, e
                                        .getMessage()));
                    }
                });
            }
        }

        public boolean isGeneratingReport() {
            if (simulationEngine != null) {
                return simulationEngine.isGeneratingReport();
            } else {
                return false;
            }
        }

        public int getExecutedInstances() {
            if (simulationEngine != null) {
                return simulationEngine.getTotalInstances();
            }
            return 0;
        }
    }

    private final String path;
    private final AbstractProcess selectedProcess;
    private final String loadProfileId;
    private final long timespan;
    private boolean cancelled = false;
    private String reportFile;
    private final SimpleDateFormat format;

    /**
     * @param selectedProcess
     * @param path
     * @param loadProfileId
     */
    public SimulationWithMonitorRunner(final AbstractProcess selectedProcess, final String path, final String loadProfileId, final long timespan) {
        this.selectedProcess = selectedProcess;
        this.path = path;
        this.loadProfileId = loadProfileId;
        this.timespan = timespan;
        format = new SimpleDateFormat("dd MMM yyyy - hh:mm:ss");
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core
     * .runtime.IProgressMonitor)
     */
    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {

        final SimulationRunnerThread t = new SimulationRunnerThread(selectedProcess);
        t.start();

        final IRepositoryFileStore file = RepositoryManager.getInstance().getRepositoryStore(SimulationLoadProfileRepositoryStore.class)
                .getChild(loadProfileId + "." + SimulationLoadProfileRepositoryStore.SIMULATION_LOADPROFILE_EXT);
        LoadProfile profile;
        try {
            profile = (LoadProfile) file.getContent();
        } catch (final ReadFileStoreException e) {
            throw new InvocationTargetException(e, "Failed to retrieve load profile content");
        }
        final EList<InjectionPeriod> periods = profile.getInjectionPeriods();
        long start = Long.MAX_VALUE;
        long end = 0;
        for (final InjectionPeriod injectionPeriod : periods) {
            start = Math.min(injectionPeriod.getBegin(), start);
            end = Math.max(end, injectionPeriod.getEnd());
        }

        monitor.beginTask(Messages.RunningSimulation, 100);
        final long total = end - start;
        int worked = 0;

        while (t.isAlive()) {
            final long currentTime = t.getCurrentTime();
            final int progress = (int) ((double) (currentTime - start) / (double) total * 100);
            final int work = Math.abs(progress - worked);
            if (work > 0 && work <= 100) {
                monitor.worked(work);
                worked = worked + work;
            }

            if (t.isGeneratingReport()) {
                monitor.subTask(Messages.generatingReport);
            } else {
                if (t.getCurrentTime() != 0) {
                    monitor.subTask(format.format(new Date(t.getCurrentTime())) + "\n" + "Finished instances : " + t.getExecutedInstances());
                }
            }

            if (monitor.isCanceled()) {
                t.stopThread();
            }
            Thread.sleep(100);
        }

    }

    public boolean isCancelled() {
        return cancelled;
    }

    public String getReportFile() {
        return reportFile;
    }

}
