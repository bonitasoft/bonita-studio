/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.connectors.database.drivers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.bonitasoft.studio.common.repository.model.IFileStoreContribution;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesFileStore;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesRepositoryStore;

public class DriversAssociationFileStoreContribution implements
		IFileStoreContribution {
	
	private static final String GENERIC_DEFINITION_ID = "database-jdbc";

	private static final String MYSQL_DEFINITION_ID = "database-mysql";
	private static final String MYSQL_DRIVER_JARNAME = "mysql-connector-java-5.1.23-bin.jar";
	
	
	
	
	private static final String POSTGRES_DEFINITION_84_ID = "database-postgresql84";
	private static final String POSTGRES_DEFINITION_ID = "database-postgresql92";
	private static final String POSTGRES_DRIVER_JARNAME = "postgresql-9.2-1002.jdbc4.jar";
	
	private static final String SQLSERVER_2008_DEFINITION_ID = "database-mssqlserver2008";
	private static final String SQLSERVER_DRIVER_JARNAME = "sqljdbc4.jar";
	private static final String SQLSERVER_2012_DEFINITION_ID = "database-mssqlserver2012";

	
	private static final String ORACLE_11G_DEFINITION_ID = "database-oracle11g";
	private static final String ORACLE_10G_DEFINITION_ID = "database-oracle10g";
	private static final String ORACLE_DRIVER_JARNAME = "ojdbc6.jar";
	
	private static final String H2_DEFINITION_ID = "database-h2";
	private static final String H2_DRIVER_JARNAME = "h2-1.3.170.jar";
	
	private static final String HSQLDB_DEFINITION_ID = "database-hsqldb";
	private static final String HSQLDB_DRIVER_JARNAME = "hsqldb.jar";


	@Override
	public boolean appliesTo(
			IRepositoryStore<? extends IRepositoryFileStore> repository) {
		 return repository instanceof DatabaseConnectorPropertiesRepositoryStore;
	}

	@Override
	public void execute(IRepositoryStore<? extends IRepositoryFileStore> repository) {
		createDatabaseDriverAssociation(repository,MYSQL_DEFINITION_ID,MYSQL_DRIVER_JARNAME);
		createDatabaseDriverAssociation(repository, POSTGRES_DEFINITION_ID, POSTGRES_DRIVER_JARNAME);
		createDatabaseDriverAssociation(repository, POSTGRES_DEFINITION_84_ID, POSTGRES_DRIVER_JARNAME);
		createDatabaseDriverAssociation(repository, SQLSERVER_2008_DEFINITION_ID, SQLSERVER_DRIVER_JARNAME);
		createDatabaseDriverAssociation(repository, SQLSERVER_2012_DEFINITION_ID, SQLSERVER_DRIVER_JARNAME);
		createDatabaseDriverAssociation(repository, ORACLE_11G_DEFINITION_ID, ORACLE_DRIVER_JARNAME);
		createDatabaseDriverAssociation(repository, ORACLE_10G_DEFINITION_ID, ORACLE_DRIVER_JARNAME);
		createDatabaseDriverAssociation(repository, H2_DEFINITION_ID, H2_DRIVER_JARNAME);
		createDatabaseDriverAssociation(repository, HSQLDB_DEFINITION_ID, HSQLDB_DRIVER_JARNAME);
		createGenericDriverList(repository);
	}

	private void createGenericDriverList(
			IRepositoryStore<? extends IRepositoryFileStore> repository) {
		  Enumeration<URL> drivers = ConnectorPlugin.getDefault().getBundle().findEntries("drivers", "*.jar", false) ;
	        final List<String> jarList = new ArrayList<String>();
	        while(drivers.hasMoreElements()){
	            URL url = drivers.nextElement() ;
	            String file = url.getFile() ;
	            String[] segments = file.split("/") ;
	            String name =  segments[segments.length -1] ;
	            jarList.add(name);
	        }
	        
	        DatabaseConnectorPropertiesFileStore file =  (DatabaseConnectorPropertiesFileStore) repository.createRepositoryFileStore(GENERIC_DEFINITION_ID);
			file.setAutoAddDriver(false);
			file.setJarList(jarList);
	}

	private void createDatabaseDriverAssociation(IRepositoryStore<? extends IRepositoryFileStore> repository, String definitionId, String driverJarname) {
		DatabaseConnectorPropertiesFileStore file =  (DatabaseConnectorPropertiesFileStore) repository.createRepositoryFileStore(definitionId);
		file.setAutoAddDriver(true);
		file.setDefault(driverJarname);
		file.setJarList(Collections.singletonList(driverJarname));
	}

}
