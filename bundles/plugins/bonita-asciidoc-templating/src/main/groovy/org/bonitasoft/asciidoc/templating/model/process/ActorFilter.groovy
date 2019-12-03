/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.asciidoc.templating.model.process

import groovy.transform.Canonical
import groovy.transform.builder.Builder

@Canonical
@Builder
class ActorFilter {
    
    /**
     * The name of the actor filter
     */
    String name
    
    /**
     * The definition name of the actor filter
     */
    String definitionName
    
    /**
     * The definition id of the actor filter
     */
    String definitionId
    
    /**
     * The definition version of the actor filter
     */
    String definitionVersion
    
    /**
     * The description of the actor filter
     */
    String description = ""
    
}
