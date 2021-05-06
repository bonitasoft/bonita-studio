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
package org.bonitasoft.studio.application.handler;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.extension.ExtensionUpdateParticipantFactory;
import org.bonitasoft.studio.application.ui.control.BonitaMarketplacePage;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependency;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesFileStore;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesRepositoryStore;
import org.eclipse.core.runtime.IProgressMonitor;

public class AddDatabaseDriverHandler extends OpenMarketplaceHandler {

    @Inject
    public AddDatabaseDriverHandler(ExtensionUpdateParticipantFactory definitionUpdateOperationFactory,
            MavenProjectHelper mavenProjectHelper, RepositoryAccessor repositoryAccessor) {
        super(definitionUpdateOperationFactory, mavenProjectHelper, repositoryAccessor);
    }

    private static final Map<String, String> DEPENDENCY_TO_CONNECTOR_DEF = new HashMap<>();
    static {
        DEPENDENCY_TO_CONNECTOR_DEF.put("com.h2database:h2", "database-h2");
        DEPENDENCY_TO_CONNECTOR_DEF.put("com.microsoft.sqlserver:mssql-jdbc", "database-mssqlserver");
        DEPENDENCY_TO_CONNECTOR_DEF.put("com.oracle.database.jdbc:ojdbc11", "database-oracle11g");
        DEPENDENCY_TO_CONNECTOR_DEF.put("org.postgresql:postgresql", "database-postgresql92");
        DEPENDENCY_TO_CONNECTOR_DEF.put("mysql:mysql-connector-java", "database-mysql");
        DEPENDENCY_TO_CONNECTOR_DEF.put("com.ibm.informix:jdbc", "database-informix");
        DEPENDENCY_TO_CONNECTOR_DEF.put("net.sf.jt400:jt400", "database-as400");
        DEPENDENCY_TO_CONNECTOR_DEF.put("com.ibm.db2:jcc", "database-db2");
        DEPENDENCY_TO_CONNECTOR_DEF.put("com.teradata.jdbc:terajdbc4", "database-teradata");
    }

    @Override
    protected BonitaMarketplacePage createBonitaMarketPlacePage(RepositoryAccessor repositoryAccessor, String... types) {
        return new BonitaMarketplacePage(repositoryAccessor.getCurrentRepository().getProject(), BonitaMarketplacePage.DATABASE_DRIVER_TYPE);
    }

    @Override
    protected String getWizardTitle() {
        return Messages.addDatabaseDriverTitle;
    }

    @Override
    protected String getPageDescription() {
        return Messages.addDatabaseDriverDesc;
    }

    @Override
    protected void installDependencies(BonitaMarketplacePage extendProjectPage, RepositoryAccessor repositoryAccessor,
            IProgressMonitor monitor) throws InvocationTargetException {
        super.installDependencies(extendProjectPage, repositoryAccessor, monitor);

        DatabaseConnectorPropertiesRepositoryStore databaseConnectorPropertiesRepositoryStore = repositoryAccessor
                .getRepositoryStore(DatabaseConnectorPropertiesRepositoryStore.class);
        DatabaseConnectorPropertiesFileStore genericConnectorConf = geConnectorConfigurationStore("database-jdbc",
                databaseConnectorPropertiesRepositoryStore);
        extendProjectPage.getDependenciesToAdd().stream().forEach(dep -> {
            updateDriverConf(genericConnectorConf, toJarName(dep));
            findConnectorDefinitionFor(dep)
                    .map(defId -> geConnectorConfigurationStore(defId, databaseConnectorPropertiesRepositoryStore))
                    .ifPresent(confStore -> updateDriverConf(confStore, toJarName(dep)));
        });

    }

    private void updateDriverConf(DatabaseConnectorPropertiesFileStore confStore,
            String jarName) {
        List<String> jarList = confStore.getJarList();
        if (!jarList.contains(jarName)) {
            jarList.add(jarName);
        }
        confStore.setJarList(jarList);
        var defaultDriverJar = confStore.getDefault();
        if (defaultDriverJar == null || defaultDriverJar.isBlank()) {
            confStore.setDefault(jarName);
        }
    }

    private Optional<String> findConnectorDefinitionFor(BonitaArtifactDependency dep) {
        return Optional.ofNullable(DEPENDENCY_TO_CONNECTOR_DEF.get(key(dep)));
    }

    private String key(BonitaArtifactDependency dep) {
        return dep.getGroupId() + ":" + dep.getArtifactId();
    }

    private DatabaseConnectorPropertiesFileStore geConnectorConfigurationStore(String definitionId,
            DatabaseConnectorPropertiesRepositoryStore databaseConnectorPropertiesRepositoryStore) {
        DatabaseConnectorPropertiesFileStore genericConnectorConf = databaseConnectorPropertiesRepositoryStore
                .getChild(definitionId + ".properties", true);
        if (genericConnectorConf == null) {
            genericConnectorConf = databaseConnectorPropertiesRepositoryStore
                    .createRepositoryFileStore(definitionId + ".properties");
            genericConnectorConf.setAutoAddDriver(!definitionId.equals("database-h2"));
        }
        return genericConnectorConf;
    }

    private String toJarName(BonitaArtifactDependency dep) {
        return String.format("%s-%s.jar", dep.getArtifactId(), dep.getBestVersion());
    }
}
