/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.migration.custom.migration;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.List;

import org.bonitasoft.studio.common.DataTypeLabels;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.MigrationFactory;
import org.eclipse.emf.edapt.migration.MigrationPackage;
import org.eclipse.emf.edapt.migration.Model;
import org.eclipse.emf.edapt.migration.impl.MetamodelImpl;
import org.eclipse.emf.edapt.migration.impl.ModelImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class BusinessObjectTypeCustomMigrationTest {

	private BusinessObjectTypeCustomMigration migrationUnderTest;
	
	@Spy
	private ModelImpl model; 
	
	@Spy
	private MetamodelImpl metamodel; 

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		migrationUnderTest= new BusinessObjectTypeCustomMigration();
		MigrationFactory.eINSTANCE.createType();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Ignore
	@Test
	public void shouldMigrateAfter_AddBusinessObjectType_InDiagram_IfNotExists() throws Exception {
		doReturn(metamodel).when(model).getMetamodel();
		EPackage ePackage = ProcessPackage.eINSTANCE.getMainProcess().getEPackage();
		EList<EPackage> result = new BasicEList<EPackage>();
		result.add(ePackage);
		doReturn(result).when(metamodel).getEPackages();
		EList<Instance> emptyList = new BasicEList<Instance>();
		doReturn(emptyList).when(model).getAllInstances("process.BusinessObjectType");
		EList<Instance> diagramList = new BasicEList<Instance>();
		Instance instance = model.newInstance("process.MainProcess");
		diagramList.add(instance);
		doReturn(diagramList).when(model).getAllInstances("process.MainProcess");
		migrationUnderTest.migrateAfter(model , metamodel);
		assertThat((List<Instance>)instance.get("datatypes")).onProperty("name").containsOnly(NamingUtils.convertToId(DataTypeLabels.businessObjectType));
	}

}
