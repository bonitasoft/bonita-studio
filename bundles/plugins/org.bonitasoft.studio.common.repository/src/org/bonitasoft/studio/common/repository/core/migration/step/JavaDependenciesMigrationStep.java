package org.bonitasoft.studio.common.repository.core.migration.step;

import static java.util.function.Predicate.not;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.FileInputStreamSupplier;
import org.bonitasoft.studio.common.repository.core.InputStreamSupplier;
import org.bonitasoft.studio.common.repository.core.maven.DefinitionUsageOperation;
import org.bonitasoft.studio.common.repository.core.maven.DependencyUsageOperation;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.MavenRepositoryRegistry;
import org.bonitasoft.studio.common.repository.core.maven.migration.ProjectDependenciesMigrationOperation;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup.Status;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.dependencies.operation.DependenciesUpdateOperationFactory;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.osgi.framework.Version;

public class JavaDependenciesMigrationStep implements MigrationStep {

    private MavenRepositoryRegistry mavenRepositoryRegistry = new MavenRepositoryRegistry();
    private DependenciesUpdateOperationFactory dependenciesUpdateOperationFactory;

    public JavaDependenciesMigrationStep() {
        var eclipseContext = EclipseContextFactory.create();
        this.dependenciesUpdateOperationFactory = ContextInjectionFactory
                .make(DependenciesUpdateOperationFactory.class, eclipseContext);
    }

    @Override
    public MigrationReport run(Path project, IProgressMonitor monitor) throws CoreException {
        MigrationReport report = MigrationReport.emptyReport();
        try {
            Set<DependencyLookup> dependencyLookups = doMigrateToMavenDependencies(project, monitor);
            StringBuilder dependenciesUpdatesReport = new StringBuilder(
                    "Upgrade of java archives files into Maven dependencies:\n");
            dependencyLookups.stream()
                    .forEach(dl -> {
                        String fileName = dl.getFileName();
                        if (!dl.isUsed()) {
                            dependenciesUpdatesReport.append(String.format(
                                    "** `%s` file has been removed from the project as it was not used in any process configuration or implementation.\n",
                                    fileName));
                        } else {
                            if (fileName != null) {
                                if (dl.getStatus() == Status.FOUND) {
                                    dependenciesUpdatesReport.append(String.format(
                                            "** `%s` file has been replaced by a remote Maven dependency with the following coordinates: `%s`\n",
                                            fileName, dl.getGAV()));
                                } else if (dl.getStatus() == Status.LOCAL) {
                                    dependenciesUpdatesReport.append(String.format(
                                            "`%s` file has been replaced by a local Maven dependency with the following coordinates: `%s`\n",
                                            fileName, dl.getGAV()));
                                }
                            } else {
                                dependenciesUpdatesReport.append(String.format(
                                        "** `%s` remote Maven dependency has been added to the project.\n",
                                        dl.getGAV()));
                            }
                        }
                        dl.setSelected(dl.isUsed());
                    });
            if (!dependencyLookups.isEmpty()) {
                report.updated(dependenciesUpdatesReport.toString());
            }
            var localDependencyStore = new LocalDependenciesStore(project);
            var mavenModel = MavenProjectHelper.readModel(project.resolve(POM_FILE_NAME).toFile());
            var dependenciesToInstall = dependencyLookups.stream()
                    .filter(DependencyLookup::isSelected)
                    .collect(Collectors.toSet());

            var processConfigurationUpdateOperation = dependenciesUpdateOperationFactory.create();
            for (var dl : dependenciesToInstall) {
                try {
                    localDependencyStore.install(dl);
                    localDependencyStore.deleteBackup(dl.toMavenDependency());
                } catch (CoreException e) {
                    BonitaStudioLog.error(e);
                }
                if (dl.hasFileNameChanged()) {
                    dl.getJarNames().stream()
                            .filter(jarName -> !Objects.equals(jarName, dl.dependencyFileName()))
                            .forEach(jarName -> processConfigurationUpdateOperation.addJarUpdateChange(jarName,
                                    dl.dependencyFileName()));
                }
                var newDependeny = dl.toMavenDependency();
                MavenProjectHelper.findDependency(mavenModel,
                        newDependeny.getGroupId(),
                        newDependeny.getArtifactId())
                        .ifPresentOrElse(existingDep -> {
                            mavenModel.removeDependency(existingDep);
                            mavenModel.addDependency(newDependeny);
                        }, () -> mavenModel.addDependency(newDependeny));
            }

            MavenProjectHelper.saveModel(project.resolve(POM_FILE_NAME), mavenModel);

            dependencyLookups.stream()
                    .filter(not(DependencyLookup::isSelected))
                    .flatMap(dl -> dl.getJarNames().stream())
                    .forEach(processConfigurationUpdateOperation::addJarRemovedChange);

            report.addPostMigrationOperation(processConfigurationUpdateOperation);

            var libFolder = project.resolve("lib");
            if (Files.exists(libFolder)) {
                FileUtil.deleteDir(libFolder);
                report.removed("`lib` folder and its content has been removed.");
            }
        } catch (InvocationTargetException e) {
            throw new CoreException(new org.eclipse.core.runtime.Status(IStatus.ERROR, getClass(),
                    e.getTargetException().getMessage(), e.getTargetException()));
        } catch (InterruptedException e) {
            BonitaStudioLog.error(e);
        } catch (IOException e) {
            throw new CoreException(org.eclipse.core.runtime.Status.error("Failed to delete lib folder", e));
        }
        return report;
    }

    protected Set<DependencyLookup> doMigrateToMavenDependencies(Path project, IProgressMonitor monitor)
            throws InvocationTargetException, InterruptedException, CoreException {
        Set<String> usedDependencies = dependencyUsageAnalysis(project, monitor);
        Set<String> usedDefinitions = definitionUsageAnalysis(project, monitor);
        // Automatic dependency lookup
        List<InputStreamSupplier> jars = new ArrayList<>();
        try {
            jars = createJarInputStreamSuppliers(project.resolve("lib"));
            var projectDependenciesMigrationOperation = new ProjectDependenciesMigrationOperation(jars)
                    .addUsedDependencies(usedDependencies)
                    .addUsedDefinitions(usedDefinitions);
            mavenRepositoryRegistry.updateRegistry().run(monitor);
            mavenRepositoryRegistry
                    .getGlobalRepositories()
                    .stream()
                    .map(org.eclipse.m2e.core.repository.IRepository::getUrl)
                    .forEach(projectDependenciesMigrationOperation::addRemoteRespository);
            projectDependenciesMigrationOperation.run(monitor);
            return projectDependenciesMigrationOperation.getResult();
        } finally {
            for (InputStreamSupplier jar : jars) {
                try {
                    jar.close();
                } catch (Exception e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
    }

    private List<InputStreamSupplier> createJarInputStreamSuppliers(Path libFolder) throws CoreException {
        return  Files.exists(libFolder) ? Stream.of(libFolder.toFile().listFiles())
                .filter(File.class::isInstance)
                .map(File.class::cast)
                .filter(file -> file.getName().toLowerCase().endsWith(".jar"))
                .map(FileInputStreamSupplier::new)
                .collect(Collectors.toList()) : List.of();
    }

    private Set<String> definitionUsageAnalysis(Path project, IProgressMonitor monitor)
            throws CoreException, InvocationTargetException, InterruptedException {
        var diagrams = project.resolve("diagrams");
        if (!Files.exists(diagrams)) {
            return Set.of();
        }
        List<InputStreamSupplier> files = Stream.of(diagrams.toFile().listFiles())
                .filter(File.class::isInstance)
                .map(File.class::cast)
                .filter(file -> file.getName().toLowerCase().endsWith(".proc"))
                .map(FileInputStreamSupplier::new)
                .collect(Collectors.toList());
        DefinitionUsageOperation dependencyUsageOperation = new DefinitionUsageOperation(files);
        dependencyUsageOperation.run(monitor);
        return dependencyUsageOperation.getUsedDefinitions();
    }

    private Set<String> dependencyUsageAnalysis(Path project, IProgressMonitor monitor)
            throws CoreException, InvocationTargetException, InterruptedException {
        var diagrams = project.resolve("diagrams");
        var processConfiguration = project.resolve("process_configurations");
        var connectorsImplConfiguration = project.resolve("connectors-impl");
        var filtersImplConfiguration = project.resolve("filters-impl");

        List<InputStreamSupplier> files = new ArrayList<>();
        collectFileCandidates(diagrams, ".proc", files);
        collectFileCandidates(processConfiguration, ".conf", files);
        collectFileCandidates(connectorsImplConfiguration, ".impl", files);
        collectFileCandidates(filtersImplConfiguration, ".impl", files);

        DependencyUsageOperation dependencyUsageOperation = new DependencyUsageOperation(files);
        dependencyUsageOperation.run(monitor);
        return dependencyUsageOperation.getUsedDependencies();
    }

    private void collectFileCandidates(Path folder, String fileExtension, List<InputStreamSupplier> candidates)
            throws CoreException {
        if (Files.exists(folder)) {
            Stream.of(folder.toFile().listFiles())
                    .filter(File.class::isInstance)
                    .map(File.class::cast)
                    .filter(file -> file.getName().toLowerCase().endsWith(fileExtension))
                    .map(FileInputStreamSupplier::new)
                    .forEach(candidates::add);
        }
    }

    @Override
    public boolean appliesTo(String sourceVersion) {
        return Version.parseVersion(sourceVersion).compareTo(new Version("7.13.0")) < 0;
    }

}
