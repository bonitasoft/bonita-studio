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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.ActorFilter;
import org.bonitasoft.studio.model.process.Element;
import org.eclipse.emf.ecore.EObject;

public class ActorFilterUsagesControlSupplier extends ConnectorUsagesControlSupplier {

    public ActorFilterUsagesControlSupplier(ExtendedConnectorDefinition definition) {
        super(definition);
    }

    @Override
    protected String getDescriptionText() {
        return String.format(Messages.actorFilterUsagesDescription, definition.getConnectorDefinitionLabel());
    }

    @Override
    protected Map<AbstractProcess, List<Element>> computeUsages() {
        Map<AbstractProcess, List<Element>> connectorUsages = new HashMap<>();
        RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class).getAllProcesses()
                .stream()
                .forEach(p -> ModelHelper.getAllElementOfTypeIn(p, ActorFilter.class)
                        .stream()
                        .filter(c -> Objects.equals(c.getDefinitionId(), definition.getId()))
                        .filter(c -> Objects.equals(c.getDefinitionVersion(), definition.getVersion()))
                        .map(EObject::eContainer)
                        .filter(Element.class::isInstance)
                        .map(Element.class::cast)
                        .distinct()
                        .forEach(element -> {
                            if (!connectorUsages.containsKey(p)) {
                                connectorUsages.put(p, new ArrayList<>());
                            }
                            if (!Objects.equals(p, element)) {
                                connectorUsages.get(p).add(element);
                            }
                        }));
        return connectorUsages;
    }

}
