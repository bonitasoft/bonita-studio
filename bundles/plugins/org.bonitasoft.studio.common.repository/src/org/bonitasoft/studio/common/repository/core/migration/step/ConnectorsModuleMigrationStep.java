/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.migration.step;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.maven.model.Dependency;
import org.apache.maven.shared.utils.StringUtils;
import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.bpm.connector.model.definition.DocumentRoot;
import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.bpm.model.util.ModelLoader;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.StepDescription;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.osgi.framework.Version;

public class ConnectorsModuleMigrationStep implements MigrationStep {

    public static record ExtensionModuleCreationInputs(String artifactId, boolean useJava,
            ProjectMetadata projectMetadata, Path extensionsParentProjectLocation) {
        // This record is used to pass the inputs to the module creation operation
    }

    /** Legacy folder for connector sources */
    public static final String CONNECTORS_SRC_FOLDER = "src-connectors";
    /** Legacy folder for connector definitions */
    private static final String CONNECTORS_DEF_FOLDER = "connectors-def";
    /** Legacy folder for connector implementations */
    private static final String CONNECTORS_IMPL_FOLDER = "connectors-impl";
    /** Legacy folder for actor filter sources */
    public static final String FILTERS_SRC_FOLDER = "src-filters";
    /** Legacy folder for actor filter definitions */
    private static final String FILTERS_DEF_FOLDER = "filters-def";
    /** Legacy folder for actor filter implementations */
    private static final String FILTERS_IMPL_FOLDER = "filters-impl";

    /** Path in connector module to the resource folder */
    private static final Path RESOURCES_IN_EXTENSION_MODULE = Path.of("src", "main", "resources-filtered");
    /** Path in connector/actor filter module to the Groovy src folder */
    private static final Path GROOVY_SRC_IN_EXTENSION_MODULE = Path.of("src", "main", "groovy");
    /** Path in connector/actor filter module to the Java src folder */
    private static final Path JAVA_SRC_IN_EXTENSION_MODULE = Path.of("src", "main", "java");

    /** Operation to create a connector module (registered by addon fragment) */
    private static Function<ExtensionModuleCreationInputs, Path> createConnectorOperation;

    /**
     * Register the operation to create a connector module from its artifact id.
     * 
     * @param createConnectorOperation the operation to register
     */
    public static void registerConnectorCreationOperation(
            Function<ExtensionModuleCreationInputs, Path> createConnectorOperation) {
        ConnectorsModuleMigrationStep.createConnectorOperation = createConnectorOperation;
    }

    /** Operation to create an actor filter module (registered by addon fragment) */
    private static Function<ExtensionModuleCreationInputs, Path> createActorFilterOperation;

    /**
     * Register the operation to create an actor filter module from its artifact id.
     * 
     * @param createActorFilterOperation the operation to register
     */
    public static void registerActorFilterCreationOperation(
            Function<ExtensionModuleCreationInputs, Path> createActorFilterOperation) {
        ConnectorsModuleMigrationStep.createActorFilterOperation = createActorFilterOperation;
    }

    @Override
    public StepDescription getDescription() {
        return new StepDescription(Messages.connectorsModuleMigrationTitle,
                Messages.connectorsModuleMigrationDescription);
    }

    private Map<String, Path> createdConnectorModules = new HashMap<>();
    private Map<String, Path> createdActorFilterModules = new HashMap<>();
    /** Whether to use Java instead of Groovy for new modules */
    private boolean useJava = false;
    private ProjectMetadata projectMetadata;
    private Path extensions;

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.core.migration.MigrationStep#appliesToProject(java.nio.file.Path)
     */
    @Override
    public boolean appliesToProject(Path projectRoot) throws CoreException {
        return MigrationStep.super.appliesToProject(projectRoot);
    }

    @Override
    public MigrationReport run(Path project, IProgressMonitor monitor) throws CoreException {
        monitor.subTask(Messages.connectorsModuleMigrationTaskLoad);
        BonitaStudioLog.info(String.format("Starting %s...", ConnectorsModuleMigrationStep.class.getName()));
        var report = MigrationReport.emptyReport();
        report.updated(
                "Connector and Actor filter projects have been moved in the project layout to benefit from the Maven multi module approach. It means that files location inside the project have changed.  "
                        + "It is a technical change and will not impact the design usage in Bonita Studio."
                        + "New maven modules and their respective `pom.xml` files are *reserved for internal Studio use*.");
        // initialize state in case step instance is reused
        createdConnectorModules.clear();
        createdActorFilterModules.clear();
        useJava = false;
        extensions = project.resolve(BonitaProject.EXTENSIONS_MODULE);

        try {
            var extensionsPomFile = extensions.resolve(POM_FILE_NAME);
            // extensions module should already have been created by ExtensionsModuleMigrationStep
            if (!Files.isDirectory(extensions) || !Files.exists(extensionsPomFile)) {
                // it might not exist yet in when step is invoked independently in bos import
                var extensionsReport = new ExtensionsModuleMigrationStep().run(project, monitor);
                extensionsReport.merge(report);
            }
            report.updated("Project's extensions are now built in their own maven module. "
                    + "While it does not impact the design usage, this internal change allow the usage of a standard Maven build lifecycle.  "
                    + "All extensions share the same `version` and `groupId` of the parent project. "
                    + "It is enforced by the format of the Bonita project and must not be changed.");

            var appModel = loadMavenModel(project.resolve(BonitaProject.APP_MODULE));
            projectMetadata = ProjectMetadata.read(appModel);

            monitor.subTask(Messages.connectorsModuleMigrationTaskConnectors);
            migrateAsExtensionModules(project, report, false);
            monitor.subTask(Messages.connectorsModuleMigrationTaskActorFilters);
            migrateAsExtensionModules(project, report, true);

            // add dependencies in app module
            monitor.subTask(Messages.connectorsModuleMigrationTaskDependencies);
            Consumer<? super Path> addDependency = module -> {
                var dependency = new Dependency();
                dependency.setGroupId("${project.groupId}");
                dependency.setArtifactId(module.getFileName().toString());
                dependency.setVersion("${project.version}");
                appModel.addDependency(dependency);
            };
            createdConnectorModules.values().forEach(addDependency);
            createdActorFilterModules.values().forEach(addDependency);
            saveMavenModel(appModel, project.resolve(BonitaProject.APP_MODULE));
        } catch (IOException | UncheckedIOException e) {
            throw new CoreException(Status.error("Failed to update project layout to multi-module.", e));
        }
        return report;
    }

    /**
     * Migrate the project connectors or actor filters to extension modules.
     * 
     * @param project the project to migrate
     * @param report the migration report to update with the migration results
     * @param handlingActorFilter true when handling actor filter migration, false when handling connector migration
     * @throws IOException if an error occurs while migrating the project
     * @throws UncheckedIOException if an error occurs while migrating the project (wrapped in a runtime exception)
     */
    private void migrateAsExtensionModules(Path project, MigrationReport report, boolean handlingActorFilter)
            throws IOException, UncheckedIOException {
        List<File> filesToMoveToAllModules = new ArrayList<>();
        var app = project.resolve(BonitaProject.APP_MODULE);
        /*
         * Inspect source folder to know whether to create java or groovy modules
         */
        var srcFolders = Stream.of(
                project.resolve(handlingActorFilter ? FILTERS_SRC_FOLDER : CONNECTORS_SRC_FOLDER),
                app.resolve(handlingActorFilter ? FILTERS_SRC_FOLDER : CONNECTORS_SRC_FOLDER))
                .filter(Files::isDirectory).filter(Files::exists).toList();
        // AtomicReference let us use the lambda function in a recursive way when initializing it
        final AtomicReference<Function<File, Stream<File>>> listFilesRecursively = new AtomicReference<>();
        listFilesRecursively.set(f -> {
            if (f.isDirectory()) {
                return Stream.of(f.listFiles()).flatMap(listFilesRecursively.get());
            } else {
                return Stream.of(f);
            }
        });

        var srcFiles = srcFolders.stream().map(Path::toFile).flatMap(srcFolder -> Stream.of(srcFolder.listFiles()))
                .toList();
        useJava = srcFiles.stream().flatMap(listFilesRecursively.get()).noneMatch(
                f -> f.isFile() && f.getName().endsWith(".groovy"))
                && srcFiles.stream().flatMap(listFilesRecursively.get()).anyMatch(
                        f -> f.isFile() && f.getName().endsWith(".java"));

        /*
         * Inspect definitions and move them to their own module
         */
        var definitionFolders = Stream.of(
                project.resolve(handlingActorFilter ? FILTERS_DEF_FOLDER : CONNECTORS_DEF_FOLDER),
                app.resolve(handlingActorFilter ? FILTERS_DEF_FOLDER : CONNECTORS_DEF_FOLDER))
                .filter(Files::isDirectory).filter(Files::exists)
                .toList();

        var defFiles = definitionFolders.stream().map(Path::toFile)
                .flatMap(defFolder -> Stream.of(defFolder.listFiles()));
        defFiles.forEach(defFile -> {
            moveFileToDedicatedModule(defFile, handlingActorFilter, false, filesToMoveToAllModules);
        });

        // Inspect implementations and move them to their own module
        var implFolders = Stream.of(
                project.resolve(handlingActorFilter ? FILTERS_IMPL_FOLDER : CONNECTORS_IMPL_FOLDER),
                app.resolve(handlingActorFilter ? FILTERS_IMPL_FOLDER : CONNECTORS_IMPL_FOLDER))
                .filter(Files::isDirectory).filter(Files::exists)
                .toList();

        var implFiles = implFolders.stream().map(Path::toFile)
                .flatMap(implFolder -> Stream.of(implFolder.listFiles()));
        implFiles.forEach(implFile -> {
            moveFileToDedicatedModule(implFile, handlingActorFilter, true, filesToMoveToAllModules);
        });

        // duplicate other files to all modules
        Map<String, Path> createdModules = handlingActorFilter ? createdActorFilterModules : createdConnectorModules;
        filesToMoveToAllModules.forEach(file -> {
            createdModules.values().forEach(module -> copyResourceFileToModule(file, module));
        });
        srcFiles.forEach(srcFile -> {
            createdModules.values().forEach(module -> copySourceFileToModule(srcFile, module));
        });

        // finally, remove legacy folders
        for (var defFolder : definitionFolders) {
            FileUtil.deleteDir(defFolder);
        }
        for (var implFolder : implFolders) {
            FileUtil.deleteDir(implFolder);
        }
        for (var srcFolder : srcFolders) {
            FileUtil.deleteDir(srcFolder);
        }

        String moduleTypeName = handlingActorFilter ? "actor filter" : "connector";
        switch (createdModules.size()) {
            case 0 -> BonitaStudioLog.info(MessageFormat.format("No {0} module was created.", moduleTypeName));
            case 1 -> {
                Path newModuleName = createdModules.values().iterator().next().getFileName();
                BonitaStudioLog.info(MessageFormat.format("Created a new {0} module: {1}",
                        moduleTypeName, newModuleName));
                report.updated(MessageFormat.format(
                        "{0} module ''{1}'' has been created. You should review it and ensure it compiles correctly with all the required dependencies.",
                        StringUtils.capitalise(moduleTypeName), newModuleName));
            }
            default -> {
                var list = createdModules.values().stream()
                        .map(Path::getFileName).map(Path::toString).toList();
                BonitaStudioLog.info(MessageFormat.format("Created multiple {0} modules: {1}", moduleTypeName, list));
                report.updated(MessageFormat.format(
                        "{0} modules {1} have been created. Sources and resources have been duplicated in all of them. You should review each module, refactor to remove unused code, and ensure it compiles correctly with all the required dependencies.",
                        StringUtils.capitalise(moduleTypeName),
                        list.stream().collect(Collectors.joining("', '", "'", "'"))));
            }
        }
    }

    /**
     * Copy a source file to the given module source folder.
     * 
     * @param srcFile the source file/directory to copy
     * @param module the target module
     */
    private void copySourceFileToModule(File srcFile, Path module) {
        try {
            var targetPath = module.resolve(useJava ? JAVA_SRC_IN_EXTENSION_MODULE
                    : GROOVY_SRC_IN_EXTENSION_MODULE).resolve(srcFile.getName());
            if (srcFile.isDirectory()) {
                FileUtil.copyDirectory(srcFile.toPath(), targetPath);
            } else {
                Files.copy(srcFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            String msg = MessageFormat.format("Failed to copy {0} to target module {1}.",
                    srcFile, module.getFileName().toString());
            BonitaStudioLog.error(msg, e);
            throw new UncheckedIOException(msg, e);
        }
    }

    /**
     * Copy a resource file to the given module resources folder.
     * 
     * @param file file/directory to copy
     * @param module the target module
     */
    private void copyResourceFileToModule(File file, Path module) {
        try {
            var targetPath = module.resolve(RESOURCES_IN_EXTENSION_MODULE).resolve(file.getName());
            if (file.isDirectory()) {
                FileUtil.copyDirectory(file.toPath(), targetPath);
            } else {
                Files.copy(file.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            String msg = MessageFormat.format("Failed to copy {0} to target module {1}.",
                    file, module.getFileName().toString());
            BonitaStudioLog.error(msg, e);
            throw new UncheckedIOException(msg, e);
        }
    }

    /**
     * Move file to a dedicated module when it is a relevant connector/actor filter definition/implementation file.
     * 
     * @param definitionOrImplementationFile the file with eventual connector definition or implementation
     * @param handlingActorFilter true when handling actor filter, false when handling connector
     * @param handlingImplementation true when handling implementation file, false when handling definition file
     * @param filesToMoveToAllModules the list of files that could not be moved to a dedicated module
     */
    private void moveFileToDedicatedModule(File definitionOrImplementationFile, boolean handlingActorFilter,
            boolean handlingImplementation, List<File> filesToMoveToAllModules) {
        boolean movedToDefinitionModule = false;
        var ext = handlingImplementation ? ".impl" : ".def";
        if (definitionOrImplementationFile.isFile() && definitionOrImplementationFile.exists()
                && definitionOrImplementationFile.getName().endsWith(ext)) {
            var definitionId = extractDefinitionId(definitionOrImplementationFile, handlingImplementation);
            if (definitionId.isPresent()) {
                Path module;
                if (handlingActorFilter) {
                    String artifactId = definitionId.get().replaceAll("\\$\\{.*\\}", "filter");
                    module = createActorFilterModule(artifactId);
                } else {
                    String artifactId = definitionId.get().replaceAll("\\$\\{.*\\}", "connector");
                    module = createConnectorModule(artifactId);
                }
                // move file to module resources folder
                var targetPath = module.resolve(RESOURCES_IN_EXTENSION_MODULE)
                        .resolve(module.getFileName().toString() + ext);
                try {
                    Files.move(definitionOrImplementationFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING,
                            StandardCopyOption.ATOMIC_MOVE);
                    movedToDefinitionModule = true;
                } catch (IOException e) {
                    String msg = MessageFormat.format("Failed to move {0} to target module.",
                            definitionOrImplementationFile);
                    BonitaStudioLog.error(msg, e);
                    throw new UncheckedIOException(msg, e);
                }
                if (movedToDefinitionModule) {
                    // also move the properties file if it exists
                    var propertiesFile = new File(definitionOrImplementationFile.getParent(),
                            definitionOrImplementationFile.getName().replace(ext, ".properties"));
                    var propertiesTargetPath = module.resolve(RESOURCES_IN_EXTENSION_MODULE)
                            .resolve(module.getFileName().toString() + ".properties");
                    handlePropertiesFile(propertiesFile, propertiesTargetPath, filesToMoveToAllModules);
                }
            }
        }
        if (!movedToDefinitionModule && definitionOrImplementationFile.exists()) {
            // this file should be moved to all modules
            filesToMoveToAllModules.add(definitionOrImplementationFile);
        }
    }

    /**
     * Handle the properties file to move it to the target path or merge it.
     * 
     * @param propertiesOriginFile original properties file to move or merge
     * @param propertiesTargetPath the target path where to move the properties
     * @param filesToMoveToAllModules the list of files that could not be moved to a dedicated module
     */
    private void handlePropertiesFile(File propertiesOriginFile, Path propertiesTargetPath,
            List<File> filesToMoveToAllModules) {
        if (propertiesOriginFile.exists()) {
            try {
                if (Files.exists(propertiesTargetPath)) {
                    // merge properties file
                    var prop = new Properties();
                    try (InputStream stream = Files.newInputStream(propertiesTargetPath)) {
                        prop.load(stream);
                    }
                    try (InputStream stream = Files.newInputStream(propertiesOriginFile.toPath())) {
                        prop.load(stream);
                    }
                    try (OutputStream out = Files.newOutputStream(propertiesTargetPath)) {
                        prop.store(out, "");
                    }
                } else {
                    // just move the properties file
                    Files.move(propertiesOriginFile.toPath(), propertiesTargetPath, StandardCopyOption.ATOMIC_MOVE);
                }
                // this file won't need to be duplicated to all modules
                filesToMoveToAllModules.remove(propertiesOriginFile);
                propertiesOriginFile.delete();
            } catch (IOException e) {
                String msg = MessageFormat.format("Failed to move {0} to target module.",
                        propertiesOriginFile);
                BonitaStudioLog.error(msg, e);
                throw new UncheckedIOException(msg, e);
            }
        }
    }

    /**
     * Extract the definition id from the given definition/implementation file.
     * 
     * @param definitionOrImplementationFile the file with connector definition or implementation
     * @param isImplementation true for implementation file, false for definition file
     * @return the definition id if found, empty otherwise
     */
    private Optional<String> extractDefinitionId(File definitionOrImplementationFile, boolean isImplementation) {
        // inspect file to get the definition id
        /*
         * Note: this may migrate the definition/implementation file by the way, which is a desirable side effect.
         */
        Resource model = ModelLoader.create().loadModel(URI.createFileURI(definitionOrImplementationFile.getPath()));
        if (isImplementation) {
            Class<org.bonitasoft.bpm.connector.model.implementation.DocumentRoot> docRootClass = org.bonitasoft.bpm.connector.model.implementation.DocumentRoot.class;
            var impl = model.getContents().stream().filter(docRootClass::isInstance).map(docRootClass::cast)
                    .map(org.bonitasoft.bpm.connector.model.implementation.DocumentRoot::getConnectorImplementation)
                    .filter(Objects::nonNull).findFirst();
            return impl.map(ConnectorImplementation::getDefinitionId);
        } else {
            Class<DocumentRoot> docRootClass = DocumentRoot.class;
            var def = model.getContents().stream().filter(docRootClass::isInstance)
                    .map(docRootClass::cast).map(DocumentRoot::getConnectorDefinition)
                    .filter(Objects::nonNull).findFirst();
            return def.map(ConnectorDefinition::getId);
        }
    }

    /**
     * Create a connector module as project submodule (or get the already created one).
     * 
     * @param artifactId the artifact id of the connector module to create
     * @return the created project or null if the project could not be created
     */
    private Path createConnectorModule(String artifactId) {
        return createdConnectorModules.computeIfAbsent(artifactId, id -> {
            // avoid homonyms with actor filter modules
            var finalId = createdActorFilterModules.containsKey(id) ? id + "-connector" : id;
            // check first if the module already exists
            Path targetPath = extensions.resolve(finalId);
            if (Files.exists(targetPath) && Files.exists(targetPath.resolve(POM_FILE_NAME))) {
                BonitaStudioLog.info(MessageFormat.format("Connector module {0} already exists, reusing it.", finalId));
                return targetPath;
            }
            return Optional.ofNullable(createConnectorOperation.apply(
                    new ExtensionModuleCreationInputs(id, useJava, projectMetadata, extensions)))
                    .orElseThrow(() -> {
                        String msg = MessageFormat.format("Failed to create new connector module named {0}.",
                                finalId);
                        BonitaStudioLog.error(msg, ConnectorsModuleMigrationStep.class);
                        return new UncheckedIOException(new IOException(msg));
                    });
        });
    }

    /**
     * Create an actor filter module as project submodule (or get the already created one).
     * 
     * @param artifactId the artifact id of the connector module to create
     * @return the created project or null if the project could not be created
     */
    private Path createActorFilterModule(String artifactId) {
        return createdActorFilterModules.computeIfAbsent(artifactId, id -> {
            // avoid homonyms with connector modules
            var finalId = createdConnectorModules.containsKey(id) ? id + "-filter" : id;
            // check first if the module already exists
            Path targetPath = extensions.resolve(finalId);
            if (Files.exists(targetPath) && Files.exists(targetPath.resolve(POM_FILE_NAME))) {
                BonitaStudioLog
                        .info(MessageFormat.format("Actor filter module {0} already exists, reusing it.", finalId));
                return targetPath;
            }
            return Optional.ofNullable(createActorFilterOperation.apply(
                    new ExtensionModuleCreationInputs(finalId, useJava, projectMetadata, extensions)))
                    .orElseThrow(() -> {
                        String msg = MessageFormat.format("Failed to create new actor filter module named {0}.",
                                finalId);
                        BonitaStudioLog.error(msg, ConnectorsModuleMigrationStep.class);
                        return new UncheckedIOException(new IOException(msg));
                    });
        });
    }

    @Override
    public boolean appliesToVersion(String sourceVersion) {
        return Version.parseVersion(sourceVersion).compareTo(new Version("7.13.0")) < 0;
    }
}
