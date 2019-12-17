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
package org.bonitasoft.asciidoc.templating;

import org.codehaus.groovy.control.customizers.ImportCustomizer
import org.codehaus.groovy.control.customizers.builder.ImportCustomizerFactory

import groovy.text.markup.MarkupTemplateEngine
import groovy.text.markup.TemplateConfiguration
import spock.lang.Specification
import spock.lang.Unroll


class BlockTest extends Specification {

    def "should generate a formatted asciidoc block"(){
        given:
        def block = new Block( content: 'Some block content' )

        when:
        def blockOutput = block.toString()

        then:
        blockOutput == '''|----
                          |Some block content
                          |----
                          |'''.stripMargin().denormalize()
    }
    
    def "should generate a formatted asciidoc block with caption"(){
        given:
        def block = new Block( caption: 'Block title', content: 'Some block content' )

        when:
        def blockOutput = block.toString()

        then:
        blockOutput == '''|.Block title
                          |----
                          |Some block content
                          |----
                          |'''.stripMargin().denormalize()
    }
    
    def "should generate a formatted asciidoc block with properties"(){
        given:
        def block = new Block( caption: 'Block title', properties: ['source', 'groovy'], content: 'Some block content' )

        when:
        def blockOutput = block.toString()

        then:
        blockOutput == '''|.Block title
                          |[source,groovy]
                          |----
                          |Some block content
                          |----
                          |'''.stripMargin().denormalize()
    }

}
