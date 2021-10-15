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
package org.bonitasoft.studio.intro.content.actions;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.intro.Activator;
import org.bonitasoft.studio.intro.content.DOMContentProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ExecuteCommandContentProvider implements DOMContentProvider {

    private String id;
    private String commandId;
    private String text;
    private Map<String, Object> parameters;

    public ExecuteCommandContentProvider(String id, String commandId, String text) {
        this.id = id;
        this.commandId = commandId;
        this.text = text;
    }

    public ExecuteCommandContentProvider(String id, String commandId, String text, Map<String, Object> parameters) {
        this.id = id;
        this.commandId = commandId;
        this.text = text;
        this.parameters = parameters;
    }

    @Override
    public void createContent(Document dom, Element parent) {
        Element a = dom.createElement("a");
        boolean canExecute = canExecute();
        a.setAttribute("class", canExecute ? "hover:text-red-600" : "text-gray-500 cursor-not-allowed");
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
        if(canExecute) {
            a.setAttribute("href", actionLink);
        }
       
        a.setTextContent(text);
        parent.appendChild(a);
    }

    private boolean canExecute() {
        return new CommandExecutor().canExecute(commandId, parameters);
    }

    @Override
    public boolean appliesTo(String id) {
        return Objects.equals(this.id, id);
    }

}
