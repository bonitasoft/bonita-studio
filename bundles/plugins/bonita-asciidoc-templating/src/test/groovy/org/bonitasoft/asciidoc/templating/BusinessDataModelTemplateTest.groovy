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

import org.bonitasoft.asciidoc.templating.TemplateEngine
import org.bonitasoft.asciidoc.templating.model.Project
import org.bonitasoft.asciidoc.templating.model.bdm.BusinessDataModel
import org.bonitasoft.asciidoc.templating.model.bdm.BusinessObject
import org.junit.Rule
import org.junit.rules.TemporaryFolder

import net.bytebuddy.matcher.HasSuperTypeMatcher
import spock.lang.Specification
import spock.lang.Unroll

class BusinessDataModelTemplateTest extends Specification {

    @Rule
    TemporaryFolder temporaryFolder

    def "should generate bdm template"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("bdm.adoc");
        def bdm = new BusinessDataModel(businessObjects: [
            new BusinessObject(name: 'Employee', packageName: 'org.bonitasoft.model',description: 'A simple description')
        ])

        when:
        engine.run("bdm/businessDataModel_template.tpl", outputFile, [businessDataModel:bdm])

        then:
        def asciiDocContent = outputFile.text
        asciiDocContent.contains('== Business Data Model')
        asciiDocContent.contains('=== Employee')
        asciiDocContent.contains('A simple description')
    }

    def File templateFolder() {
        new File(BusinessDataModelTemplateTest.getResource("/templates").getFile())
    }
}
