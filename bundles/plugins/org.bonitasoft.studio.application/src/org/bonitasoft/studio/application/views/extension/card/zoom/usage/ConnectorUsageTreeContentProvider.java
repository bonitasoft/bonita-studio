/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.views.extension.card.zoom.usage;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Element;
import org.eclipse.jface.viewers.ITreeContentProvider;

public class ConnectorUsageTreeContentProvider implements ITreeContentProvider {

    private Map<AbstractProcess, List<Element>> usages;
    private Object[] emptyArray = new Object[] {};

    public ConnectorUsageTreeContentProvider(Map<AbstractProcess, List<Element>> usages) {
        this.usages = usages;
    }

    @Override
    public Object[] getElements(Object inputElement) {
        return usages.keySet().toArray();
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        List<Element> children = usages.get(parentElement);
        return children != null ? children.toArray() : emptyArray;
    }

    @Override
    public Object getParent(Object element) {
        if (element instanceof Element) {
            return usages.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().contains(element))
                    .map(Entry::getKey)
                    .findFirst()
                    .orElse(null);

        }
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        return element instanceof AbstractProcess && usages.containsKey(element);
    }

}
