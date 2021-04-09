/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.model.configuration.builders;

import java.util.Arrays;

import org.bonitasoft.studio.model.Buildable;
import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.bonitasoft.studio.model.actormapping.ActorMappingFactory;
import org.bonitasoft.studio.model.actormapping.MembershipType;

/**
 * @author Romain Bioteau
 */
public class ActorMappingBuilder implements Buildable<ActorMapping> {

    private final ActorMapping actorMapping;

    private ActorMappingBuilder(final ActorMapping actorMapping) {
        this.actorMapping = actorMapping;
    }

    public static ActorMappingBuilder anActorMapping() {
        ActorMapping mapping = ActorMappingFactory.eINSTANCE.createActorMapping();
        mapping.setUsers(ActorMappingFactory.eINSTANCE.createUsers());
        mapping.setMemberships(ActorMappingFactory.eINSTANCE.createMembership());
        mapping.setGroups(ActorMappingFactory.eINSTANCE.createGroups());
        mapping.setRoles(ActorMappingFactory.eINSTANCE.createRoles());
        return new ActorMappingBuilder(mapping);
    }

    public ActorMappingBuilder withActor(final String actorName) {
        actorMapping.setName(actorName);
        return this;
    }
    
    public ActorMappingBuilder havingUsers(final String... users) {
        actorMapping.getUsers().getUser().addAll(Arrays.asList(users));
        return this;
    }
    
    public ActorMappingBuilder havingRoles(final String... roles) {
        actorMapping.getRoles().getRole().addAll(Arrays.asList(roles));
        return this;
    }
    
    public ActorMappingBuilder havingGroups(final String... groups) {
        actorMapping.getGroups().getGroup().addAll(Arrays.asList(groups));
        return this;
    }
    
    @SafeVarargs
    public final ActorMappingBuilder havingMemberships(final Buildable<MembershipType>... memberships) {
        for (final Buildable<? extends MembershipType> membership : memberships) {
            actorMapping.getMemberships().getMembership().add(membership.build());
        }
        return this;
    }

    public ActorMapping build() {
        return actorMapping;
    }

}
