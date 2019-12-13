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


class AsciiDocTemplateTest extends Specification {

    def "should add asciidoc line breaks signs"(){
        given:
        def tplConfig = new TemplateConfiguration()
        tplConfig.baseTemplateClass = AsciiDocTemplate
        def engine = new MarkupTemplateEngine(tplConfig);

        when:
        def template = engine.createTemplate('')
        def out = new StringWriter()
        def AsciiDocTemplate tpl = template.make()
        def withLineBreaks = tpl.insertLineBreaks('''|Description with
                                                       |line breaks'''.stripMargin().denormalize())

        then:
        withLineBreaks == '''|Description with + 
                             |line breaks'''.stripMargin().denormalize()
    }
    
    def "should not add asciidoc line breaks signs when more than one line separator is found"(){
        given:
        def tplConfig = new TemplateConfiguration()
        tplConfig.baseTemplateClass = AsciiDocTemplate
        def engine = new MarkupTemplateEngine(tplConfig);

        when:
        def template = engine.createTemplate('')
        def out = new StringWriter()
        def AsciiDocTemplate tpl = template.make()
        def withLineBreaks = tpl.insertLineBreaks('''|Description with
                                                       |
                                                       |2 line breaks'''.stripMargin().denormalize())

        then:
        withLineBreaks == '''|Description with
                             |
                             |2 line breaks'''.stripMargin().denormalize()
    }
}
