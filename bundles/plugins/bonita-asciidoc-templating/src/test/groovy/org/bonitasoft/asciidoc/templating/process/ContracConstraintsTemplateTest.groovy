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
import org.bonitasoft.asciidoc.templating.model.process.ContractConstraint
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

class ContracConstraintsTemplateTest extends Specification {

    @Rule
    TemporaryFolder temporaryFolder
    
    def setup() {
        Locale.setDefault(Locale.ENGLISH)
    }

    def "should generate contract constraint template"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("contract.adoc");
        def contract = new Contract(constraints:  [
                new ContractConstraint(name: 'employeeFirstNameLength', expression: 'firstName.size() < 50', errorMessage: 'firstName input must be lower than 50', description: 'firstName max length constraint'),
                new ContractConstraint(name: 'employeeFirstNameMandatory', expression: 'firstName != null', errorMessage: "firstName input can't be null", description: 'mandatory first name constraint')
            ])

        when:
        engine.run("process/contract_constraints_template.tpl", outputFile, [contract:contract])

        then:
        outputFile.text == '''|employeeFirstNameLength:: firstName max length constraint
                              |+
                              |.Expression
                              |[source,groovy]
                              |----
                              |firstName.size() < 50
                              |----
                              |+
                              |.Technical error message
                              |----
                              |firstName input must be lower than 50
                              |----
                              |employeeFirstNameMandatory:: mandatory first name constraint
                              |+
                              |.Expression
                              |[source,groovy]
                              |----
                              |firstName != null
                              |----
                              |+
                              |.Technical error message
                              |----
                              |firstName input can't be null
                              |----
                              |
                              |'''.stripMargin().denormalize()
    }

    def File templateFolder() {
        new File(ContracConstraintsTemplateTest.getResource("/templates").getFile())
    }
}
