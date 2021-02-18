/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.maven.migration.filter;

import org.bonitasoft.studio.common.repository.core.maven.migration.BonitaJarDependencyReplacement;


public class UserManagerActorFilterDependenciesReplacement extends BonitaJarDependencyReplacement {
    
    private static final String DEFINITION_ID = "bonita-actorfilter-user-manager";
    
    public UserManagerActorFilterDependenciesReplacement() {
        super(dependency(ACTOR_FILTER_GROUP_ID, "bonita-actorfilter-user-manager", "1.0.0"), 
                "bonita-actorfilter-user-manager-1.0.0.jar",
                "bonita-userfilter-user-manager-impl-1.0.0-SNAPSHOT.jar");
    }
    
    @Override
    public boolean matchesDefinition(String definitionId) {
        return DEFINITION_ID.equals(definitionId);
    }

}
