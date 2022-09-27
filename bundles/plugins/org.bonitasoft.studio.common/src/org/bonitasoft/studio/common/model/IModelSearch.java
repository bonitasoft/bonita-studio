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

import java.util.List;
import java.util.Optional;

import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Data;
import org.eclipse.emf.ecore.EObject;

public interface IModelSearch {

    <T> List<T> getAllItemsOfType(EObject parent, Class<T> type);

    boolean isInEvenementialSubProcessPool(EObject element);

    <T> T getDirectParentOfType(EObject element, Class<T> type);

    Optional<AbstractProcess> findProcess(String name, String version);

    String getEObjectID(EObject element);

    List<Data> getAccessibleData(EObject context);

    List<ConnectorDefinition> getConnectorDefinitions();

}
