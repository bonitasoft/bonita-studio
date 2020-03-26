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
package org.bonitasoft.studio.antTasks;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author Mickael Istria
 *
 */
public class InjectIntoMenuFile extends Task {

	private String srcMenuFile;
	private String targetMenuFile;

	@Override
	public void execute() throws BuildException {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			// lecture du contenu d'un fichier XML avec DOM
			File target = new File(targetMenuFile);
			File source = new File(srcMenuFile);
			Document srcDoc = builder.parse(source);
			Document targetDoc = builder.parse(target);
			Node node = targetDoc.importNode(srcDoc.getFirstChild(), true);
			for (int i = 0; i < node.getChildNodes().getLength(); i++) {
				Node child = node.getChildNodes().item(i);
				if (child instanceof Element) {
					((Element)targetDoc.getElementsByTagName("Menu").item(0)).appendChild(child);
				}
			}
			
			DOMSource domSource = new DOMSource(targetDoc);
			StreamResult result = new StreamResult(target);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);
		} catch (Exception ex) {
			throw new BuildException(ex);
		}
	}
	
	public void setSrcMenuExtensionFile(String srcMenuExtensionFile) {
		this.srcMenuFile = srcMenuExtensionFile;
	}
	
	public void setTargetMenuFile(String targetMenuFile) {
		this.targetMenuFile = targetMenuFile;
	}
}
