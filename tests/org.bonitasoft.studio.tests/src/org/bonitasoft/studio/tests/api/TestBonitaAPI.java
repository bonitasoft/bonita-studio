/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.xml.api.XSDImport;
import org.bonitasoft.studio.xml.repository.XSDRepositoryStore;
import org.junit.Test;

/**
 * @author Aur�lien
 *
 */
public class TestBonitaAPI {
	
	@Test
	public void testXSDImport() throws IOException{
		XSDRepositoryStore xsdStore = (XSDRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(XSDRepositoryStore.class) ;
		int countBefore = xsdStore.getChildren().size();
		String xsdFilePath = createXSDFileToImport();
		XSDImport.importXSD(xsdFilePath);
		int countAfter = xsdStore.getChildren().size();
		Assert.assertEquals("XSD import doesn't work",countBefore+1, countAfter);
	}

	private String createXSDFileToImport() throws IOException, FileNotFoundException {
		InputStream stream = TestBonitaAPI.class.getResourceAsStream("RealTimeMarketData.xsd");		
		File createTempFile = File.createTempFile("testXSD", "RealTimeMarketData.xsd");
		FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
		FileUtil.copy(stream, fileOutputStream);				
		String xsdFilePath = createTempFile.getAbsolutePath();
		return xsdFilePath;
	}
	
	
}
