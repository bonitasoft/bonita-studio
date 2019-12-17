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
class Process {

    /**
     * The name of the process
     */
    String name

    /**
     * The display name of the process
     */
    String displayName

    /**
     * The version of the process
     */
    String version

    /**
     * The description of the process
     */
    String description = ""

    /**
     * The list of lanes of the process
     */
    Lane[] lanes = []

    /**
     * The list of global variables of the process
     */
    Data[] globalVariables = []

    /**
     * The list of parameters of the process
     */
    Parameter[] parameters = []

    /**
     * The list of documents of the process
     */
    Document[] documents = []
    
    /**
     * The list of actors of the process
     */
    Actor[] actors = []
    
    /**
     * The list of flow elements of the process
     */
    FlowElement[] flowElements = []
    
    /**
     * The list of connectors on enter of the process
     */
    Connector[] connectorsIn = []
    
    /**
     * The list of connectors on finish of the process
     */
    Connector[] connectorsOut = []
    
    Contract contract
}
