/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.engine.export;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.maven.project.MavenProject;
import org.bonitasoft.bonita2bar.BarBuilderFactory;
import org.bonitasoft.bonita2bar.BarBuilderFactory.BuildConfig;
import org.bonitasoft.bonita2bar.BuildBarException;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.ConfigurationFactory;
import org.bonitasoft.bpm.model.process.AbstractProcess;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.ConfigurationSynchronizer;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.BuildScheduler;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.bar.RestFormBuilder;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.engine.ConnectorImplementationRegistryHelper;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.project.IMavenProjectFacade;

public class BarExporter {

    private static BarExporter INSTANCE;

    protected BarExporter() {
    }

    public static BarExporter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BarExporter();
        }
        return INSTANCE;
    }

    /**
     * Creates a Business Archive (BAR) for the given process and configuration.
     * <p>
     * <b>Important:</b> This method reads the compiled Maven artifacts from the target directory.
     * Therefore, you <b>MUST</b> call {@link MavenProjectBuilder#cleanInstall()} or
     * {@link MavenProjectBuilder#cleanInstall(org.bonitasoft.studio.common.repository.core.BonitaProject)}
     * <b>BEFORE</b> calling this method to ensure the artifacts are up-to-date and properly compiled.
     * </p>
     * <p>
     * Example usage:
     * <pre>
     * // 1. Build the Maven project FIRST
     * MavenProjectBuilder mavenBuilder = new MavenProjectBuilder();
     * IStatus buildStatus = mavenBuilder.cleanInstall(project, monitor);
     * if (!buildStatus.isOK()) {
     *     throw new BuildBarException("Maven build failed");
     * }
     *
     * // 2. THEN create the BAR
     * BarExporter exporter = BarExporter.getInstance();
     * BusinessArchive bar = exporter.createBusinessArchive(process, configuration);
     * </pre>
     * </p>
     *
     * @param process the process to export as a BAR, must not be null
     * @param configuration the configuration to use for the export, must not be null
     * @return the created Business Archive
     * @throws BuildBarException if the BAR creation fails
     * @see MavenProjectBuilder#cleanInstall()
     * @see MavenProjectBuilder#cleanInstall(org.bonitasoft.studio.common.repository.core.BonitaProject)
     */
    public BusinessArchive createBusinessArchive(final Pool process, final Configuration configuration)
            throws BuildBarException {
        checkArgument(configuration != null);
        BonitaStudioLog.info("Building bar for process " + process.getName() + " (" + process.getVersion() + " )...",
                EnginePlugin.PLUGIN_ID);
        Path workdir = null;
        try {
            workdir = Files.createTempDirectory("bar");
            var project = RepositoryManager.getInstance().getCurrentProject().orElseThrow();
            var diagramStore = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
            var mavenProject = getMavenProject(project.getAppProject(), new NullProgressMonitor());
            var barBuilder = BarBuilderFactory.create(BuildConfig.builder()
                    .allowEmptyFormMapping(Platform
                            .getBundle("com.bonitasoft.studio.runtime-bundle") != null)
                    .includeParameters(true)
                    .mavenProject(mavenProject)
                    .connectorImplementationRegistry(
                            ConnectorImplementationRegistryHelper.getConnectorImplementationRegistry())
                    .formBuilder(new RestFormBuilder(PageDesignerURLFactory.INSTANCE))
                    .processRegistry(diagramStore)
                    .workingDirectory(workdir).build());
            var result = BuildScheduler.callWithBuildRule(() -> {
                return barBuilder.build(process, configuration);
            });
            return result.getBusinessArchives().get(0);
        } catch (IOException | CoreException e) {
            throw new BuildBarException(e);
        } finally {
            try {
                FileUtil.deleteDir(workdir);
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    private MavenProject getMavenProject(IProject project, IProgressMonitor monitor) throws CoreException {
        IMavenProjectFacade projectFacade = MavenPlugin.getMavenProjectRegistry().getProject(project);
        if (projectFacade == null) {
            throw new CoreException(Status.error("Cannot find Maven project for " + project));
        }
        return projectFacade.getMavenProject(monitor);
    }

    /**
     * Creates a Business Archive (BAR) for the given process and configuration ID.
     * <p>
     * This is a convenience method that resolves the configuration by ID and delegates to
     * {@link #createBusinessArchive(Pool, Configuration)}.
     * </p>
     * <p>
     * <b>Important:</b> This method reads the compiled Maven artifacts from the target directory.
     * Therefore, you <b>MUST</b> call {@link MavenProjectBuilder#cleanInstall()} or
     * {@link MavenProjectBuilder#cleanInstall(org.bonitasoft.studio.common.repository.core.BonitaProject)}
     * <b>BEFORE</b> calling this method to ensure the artifacts are up-to-date and properly compiled.
     * </p>
     *
     * @param process the process to export as a BAR, must not be null
     * @param configurationId the configuration ID to use for the export
     * @return the created Business Archive
     * @throws BuildBarException if the BAR creation fails
     * @see #createBusinessArchive(Pool, Configuration)
     * @see MavenProjectBuilder#cleanInstall()
     * @see MavenProjectBuilder#cleanInstall(org.bonitasoft.studio.common.repository.core.BonitaProject)
     */
    public BusinessArchive createBusinessArchive(final Pool process, final String configurationId)
            throws BuildBarException {
        return createBusinessArchive(process, getConfiguration(process, configurationId));
    }

    public Configuration getConfiguration(final AbstractProcess process, String configurationId) {
        Configuration configuration = null;
        final ProcessConfigurationRepositoryStore processConfStore = getProcessConfigurationRepositoryStore();

        if (configurationId == null) {
            configurationId = ConfigurationPlugin.getDefault().getPreferenceStore()
                    .getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION);
        }

        if (ConfigurationPreferenceConstants.LOCAL_CONFIGURATION.equals(configurationId)) {
            final String id = ModelHelper.getEObjectID(process);
            var file = processConfStore.getChild(id + ".conf", true);
            if (file == null) {
                file = processConfStore.createRepositoryFileStore(id + ".conf");
                configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
                configuration.setName(configurationId);
                configuration.setVersion(ModelVersion.CURRENT_DIAGRAM_VERSION);
                file.save(configuration);
            }
            try {
                configuration = file.getContent();
            } catch (final ReadFileStoreException e) {
                BonitaStudioLog.error("Failed to read process configuration", e);
            }
        } else if (java.util.Objects.equals(configurationId, ConfigurationPreferenceConstants.NONE_CONFIGURATION)) {
            configuration = createEmptyConfiguration(configurationId);
        } else {
            for (final Configuration conf : process.getConfigurations()) {
                if (configurationId.equals(conf.getName())) {
                    configuration = conf;
                }
            }
        }

        // TODO Remove configuration sync when all bar artifacts will be live update
        // friendly (connectors, dependencies, parameters...) ?
        if (configuration == null) {
            configuration = createEmptyConfiguration(configurationId);
        }

        synchronizeConfiguration(process, configuration);
        return configuration;
    }

    protected void synchronizeConfiguration(final AbstractProcess process, Configuration configuration) {
        // Synchronize configuration with definition
        new ConfigurationSynchronizer(process, configuration).synchronize();
    }

    protected ProcessConfigurationRepositoryStore getProcessConfigurationRepositoryStore() {
        return RepositoryManager.getInstance().getRepositoryStore(ProcessConfigurationRepositoryStore.class);
    }

    protected Configuration createEmptyConfiguration(String id) {
        Configuration configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
        configuration.setName(id);
        configuration.setVersion(ModelVersion.CURRENT_DIAGRAM_VERSION);
        return configuration;
    }

}

