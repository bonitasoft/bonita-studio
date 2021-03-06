/**
 * Copyright (C) 2020 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.businessobject.refactor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

public class DiffElement {

    private Event event;
    private EObject oldElement;
    private EObject newElement;

    private Map<String, String> properties = new HashMap<>();

    public DiffElement(Event event, EObject oldElement, EObject newElement) {
        this.event = event;
        this.oldElement = oldElement;
        this.newElement = newElement;
    }

    public Event getEvent() {
        return event;
    }

    public EObject getOldElement() {
        return oldElement;
    }

    public EObject getNewElement() {
        return newElement;
    }

    public void addProperty(String key, String value) {
        properties.put(key, value);
    }

    public String getProperty(String key) {
        return properties.get(key);
    }

}
