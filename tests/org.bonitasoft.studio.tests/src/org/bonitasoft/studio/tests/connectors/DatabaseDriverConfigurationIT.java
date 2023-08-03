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
package org.bonitasoft.studio.tests.connectors;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.Fragment;
import org.bonitasoft.bpm.model.process.AbstractProcess;
import org.bonitasoft.bpm.model.util.FragmentTypes;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesFileStore;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesRepositoryStore;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.tests.importer.bos.ImportBOSArchiveIT;
import org.bonitasoft.studio.tests.util.Await;
import org.bonitasoft.studio.tests.util.InitialProjectRule;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.ui.PlatformUI;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class DatabaseDriverConfigurationIT {

    @Rule
    public InitialProjectRule projectRule = InitialProjectRule.INSTANCE;
    
    private RepositoryAccessor repositoryAccessor;

    @After
    @Before
    public void cleanRepository() throws Exception {
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();

        repositoryAccessor.getCurrentRepository()
                .orElseThrow()
                .getRepositoryStore(DiagramRepositoryStore.class).getChildren()
                .stream().forEach(IRepositoryFileStore::delete);
        repositoryAccessor.getCurrentRepository()
                .orElseThrow()
                .getRepositoryStore(DatabaseConnectorPropertiesRepositoryStore.class)
                .getChildren()
                .stream().forEach(IRepositoryFileStore::delete);
    }

    @Test
    public void should_migrate_database_driver_jar_references() throws Exception {
        ImportBosArchiveOperation operation = new ImportBosArchiveOperation(repositoryAccessor);
        final File file = new File(
                FileLocator.toFileURL(ImportBOSArchiveIT.class.getResource("/DatabaseDriverMigration.bos"))
                        .getFile());
        operation.setArchiveFile(file.getAbsolutePath());
        operation.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        PlatformUI.getWorkbench().getProgressService().run(true, false, operation);
        assertThat(operation.getStatus()).isNotNull();

        DependencyRepositoryStore depStore = repositoryAccessor.getRepositoryStore(DependencyRepositoryStore.class);
        DiagramRepositoryStore diagramStore = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class);
        ProcessConfigurationRepositoryStore processConfigurationStore = repositoryAccessor
                .getRepositoryStore(ProcessConfigurationRepositoryStore.class);
        DatabaseConnectorPropertiesRepositoryStore dcpStore = repositoryAccessor
                .getRepositoryStore(DatabaseConnectorPropertiesRepositoryStore.class);

        AbstractProcess process = diagramStore.findProcess("DatabaseDriverMigration", "1.0");

        assertThat(process)
                .isNotNull();

        ProcessConfigurationFileStore fileStore = processConfigurationStore.getChild(
                ModelHelper.getEObjectID(process) + ".conf",
                true);
        Configuration localConfiguration = fileStore.getContent();
        assertThat(ModelHelper.getAllElementOfTypeIn(localConfiguration, Fragment.class).stream()
                .filter(Fragment::isExported)
                .filter(f -> Objects.equals(f.getType(), FragmentTypes.JAR))
                .map(Fragment::getValue)
                .collect(Collectors.toList()))
                        .contains("ojdbc11-21.1.0.0.jar", "postgresql-42.2.19.jar");

        for (Configuration config : process.getConfigurations()) {
            assertThat(ModelHelper.getAllElementOfTypeIn(config, Fragment.class).stream()
                    .filter(Fragment::isExported)
                    .filter(f -> Objects.equals(f.getType(), FragmentTypes.JAR))
                    .map(Fragment::getValue)
                    .collect(Collectors.toList()))
                            .contains("ojdbc11-21.1.0.0.jar", "postgresql-42.2.19.jar");
        }
        
        process = diagramStore.findProcess("DatabaseDriverMigration", "2.0");

        assertThat(process)
                .isNotNull();
        
        for (Configuration config : process.getConfigurations()) {
            assertThat(ModelHelper.getAllElementOfTypeIn(config, Fragment.class).stream()
                    .filter(Fragment::isExported)
                    .filter(f -> Objects.equals(f.getType(), FragmentTypes.JAR))
                    .map(Fragment::getValue)
                    .collect(Collectors.toList()))
                            .doesNotContain("h2-1.4.200.jar")
                            .contains("hsqldb-2.5.1.jar");
        }

        Await.waitUntil(() -> {
            List<String> dependencies = depStore.getChildren().stream().map(DependencyFileStore::getName)
                    .collect(Collectors.toList());
           return dependencies.contains("ojdbc11-21.1.0.0.jar") 
                   && dependencies.contains("postgresql-42.2.19.jar");
        } , 10000, 100);

        DatabaseConnectorPropertiesFileStore oracle = dcpStore.getChild("database-oracle11g.properties", false);
        assertThat(oracle.getDefault()).isEqualTo("ojdbc11-21.1.0.0.jar");
        assertThat(oracle.getJarList()).contains("ojdbc11-21.1.0.0.jar");

        DatabaseConnectorPropertiesFileStore postgres = dcpStore.getChild("database-postgresql92.properties", false);
        assertThat(postgres.getDefault()).isEqualTo("postgresql-42.2.19.jar");
        assertThat(postgres.getJarList()).contains("postgresql-42.2.19.jar");

        DatabaseConnectorPropertiesFileStore hsqldb = dcpStore.getChild("database-hsqldb.properties", false);
        assertThat(hsqldb.getDefault()).isEqualTo("hsqldb-2.5.1.jar");
        assertThat(hsqldb.getJarList()).contains("hsqldb-2.5.1.jar");

        DatabaseConnectorPropertiesFileStore generic = dcpStore.getChild("database-jdbc.properties", false);
        assertThat(generic.getDefault()).isEqualTo("h2-1.4.199.jar");
        assertThat(generic.getJarList()).contains("hsqldb-2.5.1.jar", "h2-1.4.199.jar", "ojdbc11-21.1.0.0.jar",
                "postgresql-42.2.19.jar");
    }

}
