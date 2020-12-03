/**
 * Copyright (C) 2009-2011 BonitaSoft S.A.
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

package org.bonitasoft.studio.engine.operation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveFactory;
import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.ConfigurationSynchronizer;
import org.bonitasoft.studio.configuration.ConfigurationValidator;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.export.BarExporter;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.ui.util.StatusCollectors;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.osgi.util.NLS;

/**
 * @author Romain Bioteau
 */
public class ExportBarOperation implements IRunnableWithProgress {

    private final List<AbstractProcess> processes;
    private final List<File> generatedBars;
    protected String configurationId;
    private String targetFolderPath;
    public IStatus status = Status.OK_STATUS;

    public ExportBarOperation() {
        processes = new ArrayList<>();
        generatedBars = new ArrayList<>();
    }

    public void addProcessToDeploy(final AbstractProcess process) {
        Assert.isTrue(!(process instanceof MainProcess), "process can't be a MainProcess");
        if (!processes.contains(process)) {
            processes.add(process);
        }
    }

    public void setTargetFolder(final String targetFolderPath) {
        this.targetFolderPath = targetFolderPath;
    }

    public void setConfigurationId(final String configurationId) {
        this.configurationId = configurationId;
    }
    
    public String getConfigurationId() {
        return configurationId;
    }

    /**
     * This method was planned only for test purpose.
     *
     * @return the list of bars generated during this export operation
     */
    public List<File> getGeneratedBars() {
        return generatedBars;
    }

    @Override
    public void run(final IProgressMonitor monitor) {
        monitor.beginTask(Messages.exporting, IProgressMonitor.UNKNOWN);

        Assert.isNotNull(targetFolderPath);
        Assert.isTrue(!processes.isEmpty());

        status = Status.OK_STATUS;

        if(!barWithoutConfigIsSupported()) {
            if (configurationId == null) {
                configurationId = ConfigurationPlugin.getDefault().getPreferenceStore()
                        .getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION);
            }
            status = validateConfiguration(processes, configurationId);
            if(status.getSeverity() == IStatus.ERROR) {
                return;
            }
        }
        
        for (final AbstractProcess process : processes) {
            final File targetFolder = new File(targetFolderPath);
            if (!targetFolder.exists()) {
                targetFolder.mkdirs();
            }

            final File outputFile = new File(targetFolder, process.getName() + "--" + process.getVersion() + ".bar");
            if (outputFile.exists()) {
                if (FileActionDialog.overwriteQuestion(outputFile.getName())) {
                    outputFile.delete();
                } else {
                    status = Status.CANCEL_STATUS;
                    return;
                }
            }

            status = exportBar(process, outputFile, monitor);
        }
        monitor.done();
    }

    protected IStatus exportBar(final AbstractProcess process, final File outputFile, final IProgressMonitor monitor) {
        monitor.beginTask(NLS.bind(Messages.buildingBar, process.getName(), process.getVersion()),
                IProgressMonitor.UNKNOWN);
        try {
            final BusinessArchive bar = getBarExporter().createBusinessArchive(process, configurationId);
            writeBusinessArchiveToFile(outputFile, bar);
            generatedBars.add(outputFile);
        } catch (final Exception ex) {
            BonitaStudioLog.error(ex);
            Throwable cause = ex;
            if (ex.getCause() != null) {
                cause = ex.getCause();
            }
            status = new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID, cause.getMessage(), cause);
            return status;
        } finally {
            monitor.done();
        }
        return status;
    }

    public BarExporter getBarExporter() {
        return BarExporter.getInstance();
    }

    public void writeBusinessArchiveToFile(final File outputFile, final BusinessArchive bar) throws IOException {
        BusinessArchiveFactory.writeBusinessArchiveToFile(bar, outputFile);
    }

    public IStatus getStatus() {
        return status;
    }
    
    private Configuration getConfiguration(final AbstractProcess process, String configurationId) {
        Configuration configuration = null;
        final ProcessConfigurationRepositoryStore processConfStore = getProcessConfigurationRepositoryStore();
        Configuration emptyConfig = ConfigurationFactory.eINSTANCE.createConfiguration();
        emptyConfig.setName(configurationId);
        emptyConfig.setVersion(ModelVersion.CURRENT_DIAGRAM_VERSION);
        if (configurationId.equals(ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON)) {
            final String id = ModelHelper.getEObjectID(process);
            ProcessConfigurationFileStore file = processConfStore.getChild(id + ".conf", true);
            if (file == null) {
                file = processConfStore.createRepositoryFileStore(id + ".conf");
                file.save(emptyConfig);
            }
            try {
                configuration = file.getContent();
            } catch (final ReadFileStoreException e) {
                BonitaStudioLog.error("Failed to read process configuration", e);
            }
        } else {
            configuration = process.getConfigurations().stream()
                    .filter(conf -> configurationId.equals(conf.getName()))
                    .findFirst()
                    .orElse(emptyConfig);
        }
        synchronizeConfiguration(process, configuration);
        return configuration;
    }

    private void synchronizeConfiguration(AbstractProcess process, Configuration configuration) {
        new ConfigurationSynchronizer(process, configuration).synchronize();
    }

    private ProcessConfigurationRepositoryStore getProcessConfigurationRepositoryStore() {
        return RepositoryManager.getInstance().getRepositoryStore(ProcessConfigurationRepositoryStore.class);
    }
    
    private boolean barWithoutConfigIsSupported() {
        return !PlatformUtil.isACommunityBonitaProduct();
    }

    private IStatus validateConfiguration(Collection<AbstractProcess> processes, String configurationId) {
        return processes.stream()
                .map(process ->  new ConfigurationValidator(process).validate(getConfiguration(process, configurationId)))
                .collect(StatusCollectors.toMultiStatus());
    }

}
