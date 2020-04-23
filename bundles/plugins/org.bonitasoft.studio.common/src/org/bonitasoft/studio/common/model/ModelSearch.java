/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

public class ModelSearch implements IModelSearch {

    private IProcessContextProvider processContextProvider;
    private IConnectorDefContextProvider connectorDefContextProvider;

    public ModelSearch(IProcessContextProvider processContextProvider) {
        this.processContextProvider = processContextProvider;
    }

    public ModelSearch(IProcessContextProvider processContextProvider,
            IConnectorDefContextProvider connectorDefContextProvider) {
        this.processContextProvider = processContextProvider;
        this.connectorDefContextProvider = connectorDefContextProvider;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.engine.export.IModelSearch#getAllItemsOfType(org.eclipse.emf.ecore.EObject, java.lang.Class)
     */
    @Override
    public <T> List<T> getAllItemsOfType(EObject parent, Class<T> type) {
        final List<T> res = new ArrayList<T>();
        addAllElementOfContainer(parent, res, type);
        return res;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.engine.export.IModelSearch#isInEvenementialSubProcessPool(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean isInEvenementialSubProcessPool(EObject element) {
        EObject current = element;
        while (current != null && !(current instanceof SubProcessEvent)) {
            current = current.eContainer();
        }
        return current instanceof SubProcessEvent;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.engine.export.IModelSearch#getDirectParentOfType(org.eclipse.emf.ecore.EObject, java.lang.Class)
     */
    @Override
    public <T> T getDirectParentOfType(EObject element, Class<T> type) {
        EObject result = element;
        while (result != null && !type.isAssignableFrom(result.getClass())) {
            result = result.eContainer();
        }
        return type.isAssignableFrom(result.getClass()) ? (T) result : null;
    }

    private static <T> void addAllElementOfContainer(final EObject parent, final List<T> res,
            final Class<T> type) {
        if (parent != null) {
            if (type.isAssignableFrom(parent.getClass())) {
                res.add((T) parent);
            }
            for (final EObject child : parent.eContents()) {
                addAllElementOfContainer(child, res, type);
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.engine.export.IModelSearch#findProcess(java.lang.String, java.lang.String)
     */
    @Override
    public Optional<AbstractProcess> findProcess(String name, String version) {
        List<AbstractProcess> allProcesses = processContextProvider.getAllProcesses();
        if (version == null || version.trim().isEmpty()) {// search the latest version
            return allProcesses.stream()
                    .filter(process -> Objects.equals(process.getName(), name))
                    .sorted((p1, p2) -> p1.getVersion().compareTo(p2.getVersion()))
                    .findFirst();
        } else {
            return allProcesses.stream()
                    .filter(process -> Objects.equals(process.getName(), name))
                    .filter(process -> Objects.equals(process.getVersion(), version))
                    .findFirst();
        }
    }

    @Override
    public List<Data> getAccessibleData(final EObject element) {
        final List<Data> data = new ArrayList<>();
        EObject currentElement = element;
        boolean processFound = false;
        while (!processFound && currentElement != null) {
            if (currentElement instanceof SequenceFlow) {
                if (((SequenceFlow) currentElement).getSource() instanceof DataAware) {
                    data.addAll(((DataAware) ((SequenceFlow) currentElement).getSource()).getData());
                }
            }
            if (currentElement instanceof DataAware) {
                data.addAll(((DataAware) currentElement).getData());
            }
            processFound = currentElement instanceof AbstractProcess && !(currentElement instanceof SubProcessEvent);
            currentElement = currentElement.eContainer();
        }
        return data;
    }


    @Override
    public String getEObjectID(final EObject eObject) {
        if (eObject == null) {
            return null;
        }
        final Resource eResource = eObject.eResource();
        if (eResource != null) {
            return eResource.getURIFragment(eObject);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.model.IModelSearch#getConnectorDefinitions()
     */
    @Override
    public List<ConnectorDefinition> getConnectorDefinitions() {
        if (connectorDefContextProvider == null) {
            return Collections.emptyList();
        }
        return connectorDefContextProvider.getConnectorDefinitions();
    }

}
