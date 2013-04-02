/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.actors.tests.organization;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.bonitasoft.studio.actors.model.organization.Membership;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.User;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.runtime.FileLocator;

/**
 * @author Aurélie Zara
 *
 */

public class TestImportOrganization extends  TestCase{

	protected void setUp() throws Exception {}

	public void testImportOrganization() throws Exception{
		final OrganizationRepositoryStore organizationStore = (OrganizationRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(OrganizationRepositoryStore.class);
		String organizationName="OrganizationTest.xml";
		URL archiveURL = TestImportOrganization.class.getResource(organizationName);
		assertNotNull("filePath should not be null",archiveURL.getPath());
		FileInputStream fis = null ;
		String id =	null;
		final File toImport = new File(FileLocator.toFileURL(archiveURL).getFile());
		assertTrue("organization to import does not exist",toImport.exists());
		fis = new FileInputStream(toImport);
		id  = toImport.getName() ;
		organizationStore.importInputStream(id, fis) ;
		if(fis != null){
			fis.close() ;	
		}
		String orgaNameWithBosExtansion = organizationName.substring(0,organizationName.indexOf("."))+"."+OrganizationRepositoryStore.ORGANIZATION_EXT;
		Organization organizationTest = (Organization) organizationStore.getChild(orgaNameWithBosExtansion).getContent();
		assertNotNull(orgaNameWithBosExtansion+" was not imported",organizationTest);
		assertTrue("not all groups have been imported",organizationTest.getGroups().getGroup().size()==5);
		assertTrue("not all roles have been imported", organizationTest.getRoles().getRole().size()==3);
		assertTrue("not all users have been imported",organizationTest.getUsers().getUser().size()==2);
		final User user1=organizationTest.getUsers().getUser().get(0);
		final User user2=organizationTest.getUsers().getUser().get(1);
		assertEquals("user1 firstname is not imported correctly",user1.getFirstName(),"Aurore");
		assertEquals("user1 lastname is not imported correctly",user1.getLastName(),"Richard");
		ArrayList<Membership> userms=new ArrayList<Membership>();
		ArrayList<Membership> userms2=new ArrayList<Membership>();
		for (Membership ms:organizationTest.getMemberships().getMembership()) {
			if(ms.getUserName().equals(user1.getUserName())){
				userms.add(ms);
			} else {
				if(ms.getUserName().equals(user2.getUserName())){
					userms2.add(ms);
				}
			}
		}
		assertEquals("memberships are not imported correctly",userms.size(),1);
		assertEquals("memberships are not imported correctly",userms2.size(),2);
	}

	public void testImportAlphaOrganization() throws Exception{
		final OrganizationRepositoryStore organizationStore = (OrganizationRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(OrganizationRepositoryStore.class);
		String organizationName="ACME-Alpha.xml";
		URL archiveURL = TestImportOrganization.class.getResource(organizationName);
		assertNotNull("filePath should not be null",archiveURL.getPath());
		FileInputStream fis = null ;
		String id =	null;
		final File toImport = new File(FileLocator.toFileURL(archiveURL).getFile());
		assertTrue("organization to import does not exist",toImport.exists());
		fis = new FileInputStream(toImport);
		id  = toImport.getName() ;
		organizationStore.importInputStream(id, fis) ;
		if(fis != null){
			fis.close() ;	
		}
		String orgaNameWithBosExtansion = organizationName.substring(0,organizationName.indexOf("."))+"."+OrganizationRepositoryStore.ORGANIZATION_EXT;
		Organization organizationTest = (Organization) organizationStore.getChild(orgaNameWithBosExtansion).getContent();
		assertNotNull(orgaNameWithBosExtansion+" was not imported",organizationTest);
	}

	protected void tearDown(){}

}
