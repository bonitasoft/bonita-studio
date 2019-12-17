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
import org.bonitasoft.asciidoc.templating.model.process.Connector
import org.bonitasoft.asciidoc.templating.model.process.Contract
import org.bonitasoft.asciidoc.templating.model.process.ContractInput
import org.bonitasoft.asciidoc.templating.model.process.DecisionTable
import org.bonitasoft.asciidoc.templating.model.process.DecisionTableLine
import org.bonitasoft.asciidoc.templating.model.process.Expression
import org.bonitasoft.asciidoc.templating.model.process.ExpressionType
import org.bonitasoft.asciidoc.templating.model.process.FlowElement
import org.bonitasoft.asciidoc.templating.model.process.SequenceFlow
import org.junit.Rule
import org.junit.rules.TemporaryFolder

import spock.lang.Specification

class ContractInputTemplateTest extends Specification {

    @Rule
    TemporaryFolder temporaryFolder
    
    def setup() {
        Locale.setDefault(Locale.ENGLISH)
    }

    def "should generate contract input template"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("contract.adoc");
        def contract = new Contract(inputs: [
                new ContractInput(name: 'employee', type: 'Complex', description: 'employee structure', children: [
                        new ContractInput(name: 'firstName', type: 'Text', description: "employee's first name"),
                        new ContractInput(name: 'lastName', type: 'Text', description: "employee's last name"),
                        new ContractInput(name: 'addresses',multiple: true, type: 'Complex', description: "employee's address", children: [
                            new ContractInput(name: 'zipcode', type: 'Integer', description: "address zipcode")
                            ]),
                        new ContractInput(name: 'birthDate', type: 'LocalDate', description: "employee's birthDate"),
                    ])
            ])

        when:
        engine.run("process/contract_inputs_template.tpl", outputFile, [contract:contract])

        then:
        outputFile.text == '''|[verse]
                              |{
                              |    employee ([teal]_Employee_) [green]_//employee structure_
                              |}
                              |
                              |[verse]
                              |[teal]#Employee# {
                              |    firstName ([olive]_Text_) [green]_//employee's first name_,
                              |    lastName ([olive]_Text_) [green]_//employee's last name_,
                              |    addresses ([teal]_Addresses_, _multiple_) [green]_//employee's address_,
                              |    birthDate ([olive]_Localdate_) [green]_//employee's birthDate_
                              |}
                              |
                              |[verse]
                              |[teal]#Addresses# {
                              |    zipcode ([olive]_Integer_) [green]_//address zipcode_
                              |}
                              |
                              |'''.stripMargin().denormalize()
    }

    def File templateFolder() {
        new File(ContractInputTemplateTest.getResource("/templates").getFile())
    }
}
