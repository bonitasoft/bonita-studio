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
package org.bonitasoft.asciidoc.templating.bdm

import org.bonitasoft.asciidoc.templating.TemplateEngine
import org.bonitasoft.asciidoc.templating.model.bdm.Attribute
import org.bonitasoft.asciidoc.templating.model.bdm.BusinessDataModel
import org.bonitasoft.asciidoc.templating.model.bdm.BusinessObject
import org.bonitasoft.asciidoc.templating.model.bdm.Package
import org.bonitasoft.asciidoc.templating.model.bdm.Query
import org.bonitasoft.asciidoc.templating.model.bdm.QueryParameter
import org.bonitasoft.asciidoc.templating.model.bdm.Relation
import org.bonitasoft.asciidoc.templating.model.bdm.UniqueConstraint
import org.junit.Rule
import org.junit.rules.TemporaryFolder

import spock.lang.Specification

class BDMUniqueConstraintsTemplateTest extends Specification {

    @Rule
    TemporaryFolder temporaryFolder
    
    def setup() {
        Locale.setDefault(Locale.ENGLISH)
    }

    def "should generate business object queries in a table"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("bdm_queries.adoc");
        def businessObject =  new BusinessObject(name: 'Employee',
                                                uniqueConstraints: [
                                                    new UniqueConstraint(name: 'uniqueFirstAndLastName', description: 'I am a constraint description', attributes: [ 'firstName', 'lastName' ])
                                                ])


        when:
        engine.run("bdm/bdm_constraints_template.tpl", outputFile, [businessObject:businessObject])

        then:
        outputFile.text == '''*===== icon:link[] Unique constraints
                              *
                              *====== uniqueFirstAndLastName [<<Employee.firstName,firstName>>, <<Employee.lastName,lastName>>]
                              *
                              *I am a constraint description
                              *
                              *'''.stripMargin('*').denormalize()
    }

    def File templateFolder() {
        new File(BDMUniqueConstraintsTemplateTest.getResource("/templates").getFile())
    }
}
