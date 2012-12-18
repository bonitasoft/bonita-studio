/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.antTasks.tests;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import junit.framework.TestCase;

import org.bonitasoft.studio.antTasks.InjectIntoMenuFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author Mickael Istria
 *
 */
public class TestShortcut extends TestCase {
	
	File file = new File("file");
	
	public void setUp() throws Exception {
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream out = new FileOutputStream(file);
		InputStream in = getClass().getResourceAsStream("applications.menu");
		byte[] content = new byte[(int) new File(getClass().getResource("applications.menu").getFile()).length()];
		in.read(content);
		out.write(content);
		in.close();
		out.flush();
		out.close();
	}

	public void testInjectionFile() throws Exception {
		InjectIntoMenuFile task = new InjectIntoMenuFile();
		task.setTargetMenuFile(file.getAbsolutePath());
		task.setSrcMenuExtensionFile(new File(getClass().getResource("product.menu").getFile()).getAbsolutePath());
		task.execute();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(file);
		
		assertEquals(1, ((Element)doc.getElementsByTagName("Menu").item(0)).getElementsByTagName("AppDir").getLength());
		
	}
	
	public void tearDown() {
		file.delete();
	}
}
