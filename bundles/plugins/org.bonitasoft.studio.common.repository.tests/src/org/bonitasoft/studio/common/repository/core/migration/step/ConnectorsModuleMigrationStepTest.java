package org.bonitasoft.studio.common.repository.core.migration.step;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.function.Function;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.ZipUtil;
import org.bonitasoft.studio.common.repository.core.migration.step.ConnectorsModuleMigrationStep.ExtensionModuleCreationInputs;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConnectorsModuleMigrationStepTest {

    private Path project;

    @BeforeAll
    static void register() {
        /*
         * These operations are contributed by org.bonitasoft.studio.maven.model.migration.ArchetypesRegistration
         * to build modules from archetypes.
         * Here, we make something lighter just for the test purpose.
         */
        Function<ExtensionModuleCreationInputs, Path> makeModule = inputs -> {
            var extensionsPath = inputs.extensionsParentProjectLocation();
            var modulePath = extensionsPath.resolve(inputs.artifactId());
            modulePath.toFile().mkdirs();
            // write the pom.xml files
            try {
                Files.write(modulePath.resolve(Path.of("pom.xml")),
                        """
                                        <?xml version="1.0" encoding="UTF-8"?>
                                        <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
                                          <modelVersion>4.0.0</modelVersion>
                                          <parent>
                                            <groupId>org.bonitasoft</groupId>
                                            <artifactId>bonita-project-extensions</artifactId>
                                          </parent>
                                          <artifactId>%s</artifactId>
                                          <version>1.0.0-SNAPSHOT</version>
                                        </project>
                                """
                                .formatted(inputs.artifactId())
                                .getBytes());
            } catch (IOException e) {
                fail(e);
            }
            modulePath.resolve(Path.of("src", "main", "resources-filtered")).toFile().mkdirs();
            modulePath.resolve(Path.of("src", "main", "java")).toFile().mkdirs();
            return modulePath;
        };
        ConnectorsModuleMigrationStep.registerConnectorCreationOperation(makeModule);
        ConnectorsModuleMigrationStep.registerActorFilterCreationOperation(makeModule);
    }

    @BeforeEach
    void setup() throws Exception {
        var zipFile = new File(
                FileLocator
                        .toFileURL(ConnectorsModuleMigrationStepTest.class.getResource("/legacyConnectorsProject.zip"))
                        .getFile());
        project = ZipUtil.unzip(zipFile).resolve("legacyConnectorsProject");
        project.resolve("app").toFile().mkdir();
        project.resolve("extensions").toFile().mkdir();
        // write the pom.xml files
        Files.write(project.resolve(Path.of("pom.xml")),
                """
                        <?xml version="1.0" encoding="UTF-8"?>
                        <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
                          <modelVersion>4.0.0</modelVersion>
                          <parent>
                            <groupId>org.bonitasoft</groupId>
                            <artifactId>bonita-project</artifactId>
                            <version>%s</version>
                          </parent>
                          <groupId>com.bonitasoft.test</groupId>
                          <artifactId>my-project-parent</artifactId>
                          <version>1.0.0-SNAPSHOT</version>
                          <packaging>pom</packaging>
                          <modules>
                            <module>app</module>
                          </modules>
                        </project>
                        """
                        .formatted(ProductVersion.BONITA_RUNTIME_VERSION)
                        .getBytes());
        Files.write(project.resolve(Path.of("app", "pom.xml")),
                """
                        <?xml version="1.0" encoding="UTF-8"?>
                        <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
                          <modelVersion>4.0.0</modelVersion>
                          <parent>
                            <groupId>com.bonitasoft.test</groupId>
                            <artifactId>my-project-parent</artifactId>
                            <version>1.0.0-SNAPSHOT</version>
                          </parent>
                          <artifactId>my-project</artifactId>
                        </project>
                        """
                        .getBytes());
        new ExtensionsModuleMigrationStep().run(project, new NullProgressMonitor());
    }

    @AfterEach
    void cleanup() throws Exception {
        FileUtil.deleteDir(project.getParent());
    }

    @Test
    void moveLegacyConnectorFoldersContent() throws Exception {
        // given
        var step = new ConnectorsModuleMigrationStep();

        assertThat(project.resolve("connectors-def")).exists();
        assertThat(project.resolve("connectors-impl")).exists();
        assertThat(project.resolve("src-connectors")).exists();

        // when
        var report = step.run(project, new NullProgressMonitor());

        // then
        assertThat(project.resolve("connectors-def")).doesNotExist();
        assertThat(project.resolve("connectors-impl")).doesNotExist();
        assertThat(project.resolve("src-connectors")).doesNotExist();
        assertThat(report.removals()).isEmpty();
        assertThat(report.updates()).contains(MessageFormat.format(
                "Connector module ''{0}'' has been created. You should review it and ensure it compiles correctly with all the required dependencies.",
                "my-custom-connector"));

        var connectorProject = project.resolve(Path.of("extensions", "my-custom-connector"));
        var resources = connectorProject.resolve(Path.of("src", "main", "resources-filtered"));
        assertThat(resources.resolve("my-custom-connector.def")).exists().content()
                .contains("<input name=\"Entrée1\" type=\"java.lang.String\"/>");
        assertThat(resources.resolve("my-custom-connector.impl")).exists().content().contains(
                "<implementationClassname>org.mycompany.connector.MyCustomConnectorImpl</implementationClassname>");
        assertThat(resources.resolve("my-custom-connector.properties")).exists().content()
                .contains("myPage.pageDescription=asdasd");
        assertThat(connectorProject.resolve(
                Path.of("src", "main", "java", "org", "mycompany", "connector", "AbstractMyCustomConnectorImpl.java")))
                        .exists().content()
                        .contains("public abstract class AbstractMyCustomConnectorImpl extends AbstractConnector {");
    }

    @Test
    void moveLegacyFiltersFoldersContent() throws Exception {
        // given
        var step = new ConnectorsModuleMigrationStep();

        assertThat(project.resolve("filters-def")).exists();
        assertThat(project.resolve("filters-impl")).exists();
        assertThat(project.resolve("src-filters")).exists();

        // when
        var report = step.run(project, new NullProgressMonitor());

        // then
        assertThat(project.resolve("filters-def")).doesNotExist();
        assertThat(project.resolve("filters-impl")).doesNotExist();
        assertThat(project.resolve("src-filters")).doesNotExist();
        assertThat(report.removals()).isEmpty();
        assertThat(report.updates()).contains(MessageFormat.format(
                "Actor filter module ''{0}'' has been created. You should review it and ensure it compiles correctly with all the required dependencies.",
                "my-actor-filter"));

        var filterProject = project.resolve(Path.of("extensions", "my-actor-filter"));
        var resources = filterProject.resolve(Path.of("src", "main", "resources-filtered"));
        assertThat(resources.resolve("my-actor-filter.def")).exists().content()
                .contains("<input name=\"Entrée1\" type=\"java.lang.String\"/>");
        assertThat(resources.resolve("my-actor-filter.impl")).exists().content().contains(
                "<implementationClassname>org.mycompany.connector.MyActorFilterImpl</implementationClassname>");
        assertThat(resources.resolve("my-actor-filter.properties")).exists().content()
                .contains("connectorDefinitionLabel=My Actor Filer");
        assertThat(filterProject.resolve(
                Path.of("src", "main", "java", "org", "mycompany", "connector", "AbstractMyActorFilterImpl.java")))
                        .exists().content()
                        .contains("public abstract class AbstractMyActorFilterImpl extends AbstractUserFilter {");
    }

    @Test
    void addedConnectorAndFiltersAsAppDependencies() throws CoreException {
        // given
        var step = new ConnectorsModuleMigrationStep();

        assertThat(project.resolve("connectors-impl")).exists();
        assertThat(project.resolve("filters-impl")).exists();

        // when
        var report = step.run(project, new NullProgressMonitor());

        // then
        assertThat(project.resolve(Path.of("app", "pom.xml"))).content()
                .contains("<artifactId>my-custom-connector</artifactId>")
                .contains("<artifactId>my-actor-filter</artifactId>");

    }

}
