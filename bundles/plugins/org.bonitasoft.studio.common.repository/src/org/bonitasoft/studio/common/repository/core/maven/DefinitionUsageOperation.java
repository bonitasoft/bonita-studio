/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.repository.core.maven;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.InputStreamSupplier;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DefinitionUsageOperation implements IRunnableWithProgress {
    
    private List<InputStreamSupplier> inputStreamSuppliers;
    private Set<String> usedDefinitionId = new HashSet<>();

    public DefinitionUsageOperation(List<InputStreamSupplier> inputStreamSuppliers) {
        this.inputStreamSuppliers = inputStreamSuppliers;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.definitionUsageAnalysis, inputStreamSuppliers.size());
        XPath xPath = XPathFactory.newInstance().newXPath();
        inputStreamSuppliers.stream()
                .forEach(iss -> {
                    try (InputStream is = iss.get()) {
                        Document document = asXMLDocument(is);
                        NodeList nodes = (NodeList) xPath.evaluate("//configuration/@definitionId", document,
                                XPathConstants.NODESET);
                        for (int i = 0; i < nodes.getLength(); ++i) {
                            Node item = nodes.item(i);
                            usedDefinitionId.add(item.getTextContent());
                        }
                    } catch (IOException | XPathExpressionException e) {
                        BonitaStudioLog.error(e);
                    } finally {
                        monitor.worked(1);
                    }
                });
    }
    
    public Set<String> getUsedDefinitions() {
        return usedDefinitionId;
    }

    private static Document asXMLDocument(InputStream source) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        factory.setNamespaceAware(true);
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(new InputSource(source));
        } catch (SAXException | IOException | ParserConfigurationException e) {
            return null;
        }
    }

}
