/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.intro.content;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.intro.Activator;
import org.bonitasoft.studio.intro.content.actions.ExecuteCommandAction;
import org.eclipse.core.runtime.FileLocator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class ButtonLinkContentProvider implements DOMContentProvider {

    private String id;
    private String commandId;
    private String text;
    private String svgName;
    private int size = 6;
    private Map<String, Object> parameters;

    public ButtonLinkContentProvider(String id, String commandId, String text, String svgName,
            Map<String, Object> parameters) {
        this.id = id;
        this.commandId = commandId;
        this.text = text;
        this.svgName = svgName;
        this.parameters = parameters;
    }

    public ButtonLinkContentProvider(String id, String commandId, String text, String svgName) {
        this.id = id;
        this.commandId = commandId;
        this.text = text;
        this.svgName = svgName;
    }

    @Override
    public void createContent(Document dom, Element parent) {
        try {
            // Div containing the svg image
            Element divSvg = dom.createElement("div");
            Document document = loadXMLFromFile(toSvgFile());
            Element svg = (Element) dom.importNode(document.getFirstChild(), true);
            svg.setAttribute("class", String.format("inline h-%s w-%s mb-2 fill-current stroke-current", size, size));
            divSvg.appendChild(svg);
            divSvg.setAttribute("class", "inline");

            // Div containing the label of the button
            Element divLink = dom.createElement("div");
            divLink.setTextContent(text);

            // Div button containing the div svg and the div label
            Element divButton = dom.createElement("div");
            divButton.setAttribute("class", "w-full p-6");
            divButton.appendChild(divSvg);
            divButton.appendChild(divLink);

            // Making the element a with action command
            Element a = dom.createElement("a");
            boolean canExecute = canExecute();
            a.setAttribute("class", canExecute ? "" : "text-gray-500 cursor-not-allowed");
            String actionLink = String.format(
                    "http://org.eclipse.ui.intro/runAction?pluginId=%s&class=%s&id=%s",
                    Activator.PLUGIN_ID,
                    ExecuteCommandAction.class.getName(),
                    commandId);
            if (parameters != null) {
                actionLink = actionLink.concat(parameters.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue() != null)
                        .map(entry -> String.format("&%s=%s", entry.getKey(), entry.getValue()))
                        .collect(Collectors.joining()));
            }
            if (canExecute) {
                a.setAttribute("href", actionLink);
            }

            // The element "a" receive the div button as child (to have the whole area clickable)
            a.appendChild(divButton);
            parent.appendChild(a);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            BonitaStudioLog.error(e);
        }
    }

    private boolean canExecute() {
        return new CommandExecutor().canExecute(commandId, parameters);
    }

    @Override
    public boolean appliesTo(String id) {
        return Objects.equals(this.id, id);
    }

    private File toSvgFile() throws IOException {
        URL resource = Activator.getDefault().getBundle().getResource("content/svg/" + svgName);
        if (resource == null) {
            throw new IllegalArgumentException("No svg file found: " + svgName);
        }
        return new File(FileLocator.toFileURL(resource).getFile());
    }

    public Document loadXMLFromFile(File svgFile) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        builder = factory.newDocumentBuilder();
        return builder.parse(svgFile);
    }

}
