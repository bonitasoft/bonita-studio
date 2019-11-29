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
import org.junit.Rule
import org.junit.rules.TemporaryFolder

import spock.lang.Specification

class BDMDiagramTemplateTest extends Specification {

    @Rule
    TemporaryFolder temporaryFolder

    def "should generate bdm plantuml diagram"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("bdm.plantuml");
        def bdm = new BusinessDataModel(packages: [
            new Package(name: 'org.bonitasoft.model',
            businessObjects : [
                new BusinessObject(name: 'Employee', 
                                   description: 'A simple description',
                                   attributes: [new Attribute(name: 'persistenceId', type: 'Long'),
                                                new Attribute(name: 'firstName', type: 'String')],
                                   relations: [new Relation(name: 'addresses', relationType: 'COMPOSITION', type: 'Address', multiple: true)],
                                   customQueries: [new Query(name: 'findByNearestAddress', returnType: 'Employee', parameters: [new QueryParameter(name: 'location', type: 'Double')])])
            ])
        ])

        when:
        engine.run("bdm/bdm_class_diagram_template.tpl", outputFile, [businessDataModel:bdm])

        then:
        outputFile.text == '''|@startuml
                            |!pragma graphviz_dot jdot
                            |
                            |package org.bonitasoft.model {
                            |
                            |    note top of Employee : A simple description
                            |    class Employee {
                            |        +persistenceId : Long
                            |        +firstName : String
                            |        == Custom queries ==
                            |        +findByNearestAddress(Double location) : Employee
                            |    }
                            |
                            |    Employee  "composed of" *-- "*" Address : addresses
                            |
                            |}
                            |
                            |@enduml
                            |'''.stripMargin().replace('\n', System.lineSeparator())
    }

    def File templateFolder() {
        new File(BDMDiagramTemplateTest.getResource("/templates").getFile())
    }
}
