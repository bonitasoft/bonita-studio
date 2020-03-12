/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.intro.content;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.intro.Activator;
import org.eclipse.core.runtime.FileLocator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class SVGContentProvider implements DOMContentProvider {

    private String id;
    private String svgName;
    private int size = 6;

    public SVGContentProvider(String id, String svgName) {
        this.id = id;
        this.svgName = svgName;
    }
    
    public SVGContentProvider(String id, String svgName, int size) {
        this.id = id;
        this.svgName = svgName;
        this.size = size;
    }

    @Override
    public void createContent(Document dom, Element parent) {
        try {
            Document document = loadXMLFromFile(toSvgFile());
            Element node = (Element) dom.importNode(document.getFirstChild(), true);
            node.setAttribute("class", String.format("inline h-%s w-%s mb-2",size, size));
            parent.appendChild(node);
            parent.setAttribute("class", "inline");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            BonitaStudioLog.error(e);
        }

    }

    private File toSvgFile() throws IOException {
        URL resource = Activator.getDefault().getBundle().getResource("content/svg/" + svgName);
        if(resource == null) {
            throw new IllegalArgumentException("No svg file found: " + svgName);
        }
        return new File(FileLocator.toFileURL(resource).getFile());
    }

    @Override
    public boolean appliesTo(String id) {
        return this.id.equals(id);
    }

    public Document loadXMLFromFile(File svgFile) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        builder = factory.newDocumentBuilder();
        return builder.parse(svgFile);
    }

}
