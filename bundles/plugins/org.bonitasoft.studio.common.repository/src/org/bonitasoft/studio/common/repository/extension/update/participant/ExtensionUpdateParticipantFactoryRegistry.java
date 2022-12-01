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
package org.bonitasoft.studio.common.repository.extension.update.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.repository.extension.update.DependencyUpdate;

public class ExtensionUpdateParticipantFactoryRegistry {

    private static final ExtensionUpdateParticipantFactoryRegistry INSTANCE = new ExtensionUpdateParticipantFactoryRegistry();
    private List<ExtensionUpdateParticipantFactory> participantFactories = new ArrayList<>();

    public void register(ExtensionUpdateParticipantFactory participantFactory) {
        participantFactories.add(participantFactory);
    }

    public List<ExtensionUpdateParticipant> createParticipants(List<DependencyUpdate> dependenciesUpdate) {
        return getParticipantFactories()
                .stream()
                .map(factory -> factory.create(dependenciesUpdate))
                .collect(Collectors.toList());
    }

    public List<ExtensionUpdateParticipantFactory> getParticipantFactories() {
        return participantFactories;
    }

    public static ExtensionUpdateParticipantFactoryRegistry getInstance() {
        return INSTANCE;
    }

}
