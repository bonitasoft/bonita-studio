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
package org.bonitasoft.asciidoc.templating.model

import org.bonitasoft.asciidoc.templating.model.bdm.BusinessDataModel
import org.bonitasoft.asciidoc.templating.model.process.Diagram
import groovy.transform.Canonical
import groovy.transform.builder.Builder

/**
 * Root element of a Bonita Project. The Project model holds all injectable information
 * that can be used when generating a project asciidoc document.
 */
@Canonical
@Builder
class Project {

    /**
     * The name of the current project
     */
    String name

    /**
     * The version of the current project
     */
    String version

    /**
     * The bonita version used when generating the documentation
     */
    String bonitaVersion
    
    /**
     * The document author
     */
    String author
    
    /**
     * The document author's email
     */
    String email

    /**
     * The Business Data Model of the current project. 
     * Can be null.
     */
    BusinessDataModel businessDataModel
    
    /**
     * The list of diagrams of the current project
     */
    Diagram[] diagrams = []
    
    /**
     * The default image folder path
     * Use in asciidoc header for :imagedir: variable and for generated images location
     */
    String imageFolderPath = 'doc/images'
}
