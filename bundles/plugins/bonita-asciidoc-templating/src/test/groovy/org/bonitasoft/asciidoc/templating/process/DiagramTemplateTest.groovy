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
package org.bonitasoft.asciidoc.templating.process

import org.bonitasoft.asciidoc.templating.TemplateEngine
import org.bonitasoft.asciidoc.templating.model.bdm.BusinessDataModel
import org.bonitasoft.asciidoc.templating.model.bdm.BusinessObject
import org.bonitasoft.asciidoc.templating.model.bdm.Package
import org.bonitasoft.asciidoc.templating.model.process.Diagram
import org.junit.Rule
import org.junit.rules.TemporaryFolder

import spock.lang.Specification

class DiagramTemplateTest extends Specification {

    @Rule
    TemporaryFolder temporaryFolder

    def "should generate diagram template"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("diagram.adoc");
        def diagram = new Diagram(name: 'My Diagram', version: '1.0', description: 'Some simple description')

        when:
        engine.run("process/diagram_template.tpl", outputFile, [diagram:diagram])

        then:
        outputFile.text == '''|=== My Diagram (1.0)
                              |
                              |Some simple description
                              |
                              |image::diagrams/My Diagram-1.0.png[]
                              |
                              |'''.stripMargin().replace('\n', System.lineSeparator())
    }

    def File templateFolder() {
        new File(DiagramTemplateTest.getResource("/templates").getFile())
    }
}
