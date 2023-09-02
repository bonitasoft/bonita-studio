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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.project.MavenProject;
import org.bonitasoft.bonita2bar.BarBuilderFactory;
import org.bonitasoft.bonita2bar.BarBuilderFactory.BuildConfig;
import org.bonitasoft.bonita2bar.BuildBarException;
import org.bonitasoft.bonita2bar.ClasspathResolver;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry.ConnectorImplementationJar;
import org.bonitasoft.bonita2bar.SourcePathProvider;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.ConfigurationFactory;
import org.bonitasoft.bpm.model.process.AbstractProcess;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.plugin.analyze.report.model.DependencyReport;
import org.bonitasoft.plugin.analyze.report.model.Implementation;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.ConfigurationSynchronizer;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.bar.RestFormBuilder;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
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

	public BusinessArchive createBusinessArchive(final Pool process, final Configuration configuration)
			throws BuildBarException {
		checkArgument(configuration != null);
		BonitaStudioLog.info("Building bar for process " + process.getName() + " (" + process.getVersion() + " )...",
				EnginePlugin.PLUGIN_ID);
		Path workdir = null;
		try {
			workdir = Files.createTempDirectory("bar");
			var project = RepositoryManager.getInstance().getCurrentProject().orElseThrow();
			var reportStore = RepositoryManager.getInstance().getCurrentRepository().orElseThrow()
					.getProjectDependenciesStore();
			var diagramStore = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
			var report = reportStore.getReport()
					.orElseGet(() -> reportStore.analyze(new NullProgressMonitor()).orElseThrow());
			var mavenProject = getMavenProject(project.getAppProject(), new NullProgressMonitor());
			var barBuilder = BarBuilderFactory.create(BuildConfig.builder()
					.allowEmptyFormMapping(Platform
							.getBundle("com.bonitasoft.studio.runtime-bundle") != null)
					.includeParameters(true)
					.classpathResolver(ClasspathResolver.of(buildClasspath(mavenProject)))
					.connectorImplementationRegistry(getConnectorImplementationRegistry(report))
					.formBuilder(new RestFormBuilder(PageDesignerURLFactory.INSTANCE))
					.processRegistry(diagramStore)
					.sourcePathProvider(
							SourcePathProvider.of(project.getAppProject().getLocation().toFile().toPath()))
					.workingDirectory(workdir).build());
			var result = barBuilder.build(process, configuration.getName());
			return result.getBusinessArchives().get(0);
		} catch (IOException | CoreException | DependencyResolutionRequiredException e) {
			throw new BuildBarException(e);
		} finally {
			try {
				FileUtil.deleteDir(workdir);
			} catch (IOException e) {
				BonitaStudioLog.error(e);
			}
		}
	}
	
	private ConnectorImplementationRegistry getConnectorImplementationRegistry(DependencyReport dependencyReport) {
	        var implementations = new ArrayList<ConnectorImplementationJar>();
	        dependencyReport.getConnectorImplementations().stream()
	                .map(BarExporter::toConnectorImplementationJar)
	                .forEach(implementations::add);
	        dependencyReport.getFilterImplementations().stream()
	                .map(BarExporter::toConnectorImplementationJar)
	                .forEach(implementations::add);
	        return ConnectorImplementationRegistry.of(implementations);
	    }
	
	private static ConnectorImplementationJar toConnectorImplementationJar(Implementation implementation) {
        return ConnectorImplementationJar.of(implementation.getImplementationId(),
                implementation.getImplementationVersion(), new File(implementation.getArtifact().getFile()),
                implementation.getJarEntry());
    }
	
	private List<String> buildClasspath(MavenProject mavenProject) throws DependencyResolutionRequiredException {
		 return Stream
                 .concat(mavenProject.getCompileClasspathElements().stream(),
                		 mavenProject.getRuntimeClasspathElements().stream())
                 .distinct()
                 .collect(Collectors.toList());
	}

	private MavenProject getMavenProject(IProject project, IProgressMonitor monitor) throws CoreException {
		IMavenProjectFacade projectFacade = MavenPlugin.getMavenProjectRegistry().getProject(project);
		if (projectFacade == null) {
			throw new CoreException(Status.error("Cannot find Maven project for "+ project));
		}
		return projectFacade.getMavenProject(monitor);
	}


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
		if (configurationId.equals(ConfigurationPreferenceConstants.LOCAL_CONFIGURATION)) {
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
		} else if (Objects.equals(configurationId, ConfigurationPreferenceConstants.NONE_CONFIGURATION)) {
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
