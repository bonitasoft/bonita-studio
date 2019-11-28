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


class TableTest extends Specification {

    def "should generate a formatted asciidoc table with headers"(){
        given:
        def table = new Table( columnName : ['Header A','Header B'],
                               columms: [['A.row1', 'A.row2'],['B.row1', 'B.row2']],
                               columnsFormat: ["1","1"])

        when:
        def tableOutput = table.toString()

        then:
        tableOutput == '''*[grid=cols, options="header",cols="1,1"]
                            *|===
                            *|Header A|Header B
                            *|A.row1  |B.row1  
                            *|A.row2  |B.row2  
                            *|===
                            *'''.stripMargin('*').replace('\n', System.lineSeparator())
    }

    def "should generate a formatted asciidoc table without headers"(){
        given:
        def table = new Table(header: false, columms: [['A.row1.longer', 'A.row2'],['B.row1', 'B.row2']], columnsFormat: ["1","1"])

        when:
        def tableOutput = table.toString()

        then:
        tableOutput == '''*[grid=cols, options="",cols="1,1"]
                            *|===
                            *|A.row1.longer|B.row1
                            *|A.row2       |B.row2
                            *|===
                            *'''.stripMargin('*').replace('\n', System.lineSeparator())
    }
    
    def "should escape '|' character in table content"(){
        given:
        def table = new Table(header: false, columms: [['A.row1 | escaped pipe']],  columnsFormat: ["1","1"])

        when:
        def tableOutput = table.toString()

        then:
        tableOutput == '''*[grid=cols, options="",cols="1,1"]
                            *|===
                            *|A.row1 \\| escaped pipe
                            *|===
                            *'''.stripMargin('*').replace('\n', System.lineSeparator())
    }
    
    def "should align multi-line cell content"(){
        given:
        def table = new Table(header: false, columms: [['A.row1'],['B.row1'+System.lineSeparator()+'some content on a second line'],['C.row1']],  columnsFormat: ["1","1"])

        when:
        def tableOutput = table.toString()

        then:
        tableOutput == '''*[grid=cols, options="",cols="1,1"]
                            *|===
                            *|A.row1|B.row1
                            *        some content on a second line|C.row1
                            *|===
                            *'''.stripMargin('*').replace('\n', System.lineSeparator())
    }

}
