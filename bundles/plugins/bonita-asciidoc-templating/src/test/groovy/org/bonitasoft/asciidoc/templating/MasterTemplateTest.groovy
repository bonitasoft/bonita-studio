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
package org.bonitasoft.asciidoc.templating

import org.bonitasoft.asciidoc.templating.model.Project
import org.junit.Rule
import org.junit.rules.TemporaryFolder

import spock.lang.Specification

class MasterTemplateTest extends Specification {

    @Rule
    TemporaryFolder temporaryFolder

    def "should generate master template with default header variables"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("master.adoc");
        def project = new Project(name: "Test Project", version: "0.0.1", bonitaVersion: '7.11')

        when:
        engine.run("master_template.tpl", outputFile, [project:project])

        then:
        outputFile.text == '''|= Test Project
                              |Generated with Bonita
                              |v0.0.1, {docdate}
                              |:toc:
                              |:toc-title: Table of contents
                              |:toclevels: 3
                              |:bonita-version: 7.11
                              |:imagesdir: ./doc/images
                              |:sectnums: numbered
                              |:sectanchors:
                              |
                              |'''.stripMargin().replace('\n', System.lineSeparator())
    }

    def "should generate master template with specific author"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("master.adoc");
        def project = new Project(name: "Test Project",
                                    version: "0.0.1",
                                    bonitaVersion: '7.11',
                                    author: 'Romain Bioteau',
                                    email: 'romain.bioteau@bonitasoft.com')

        when:
        engine.run("master_template.tpl", outputFile, [project:project])

        then:
        outputFile.text == '''|= Test Project
                              |Romain Bioteau <romain.bioteau@bonitasoft.com>
                              |v0.0.1, {docdate}
                              |:toc:
                              |:toc-title: Table of contents
                              |:toclevels: 3
                              |:bonita-version: 7.11
                              |:imagesdir: ./doc/images
                              |:sectnums: numbered
                              |:sectanchors:
                              |
                              |'''.stripMargin().replace('\n', System.lineSeparator())
    }

    def File templateFolder() {
        new File(MasterTemplateTest.getResource("/templates").getFile())
    }
}
