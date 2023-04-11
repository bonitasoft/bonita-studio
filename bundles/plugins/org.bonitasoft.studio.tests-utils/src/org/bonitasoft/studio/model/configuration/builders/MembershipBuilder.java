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

import org.bonitasoft.studio.model.Buildable;
import org.bonitasoft.studio.model.actormapping.ActorMappingFactory;
import org.bonitasoft.studio.model.actormapping.MembershipType;

/**
 * @author Romain Bioteau
 */
public class MembershipBuilder implements Buildable<MembershipType> {

    private final MembershipType membership;

    private MembershipBuilder(final MembershipType actorMapping) {
        this.membership = actorMapping;
    }

    public static MembershipBuilder aMembership() {
        return new MembershipBuilder(ActorMappingFactory.eINSTANCE.createMembershipType());
    }

    public static MembershipBuilder aMembership(String group, String role) {
        return aMembership().withGroup(group).withRole(role);
    }

    public MembershipBuilder withGroup(String group) {
        membership.setGroup(group);
        return this;
    }
    
    public MembershipBuilder withRole(String role) {
        membership.setRole(role);
        return this;
    }

    public MembershipType build() {
        return membership;
    }

}
