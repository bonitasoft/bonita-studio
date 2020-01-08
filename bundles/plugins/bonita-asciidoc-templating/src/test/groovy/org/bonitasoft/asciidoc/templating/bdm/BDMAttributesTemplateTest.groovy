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

class BDMAttributesTemplateTest extends Specification {

    @Rule
    TemporaryFolder temporaryFolder
    
    def setup() {
        Locale.setDefault(Locale.ENGLISH)
    }

    def "should generate business object attributes in a table"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("bdm_attributes.adoc");
        def businessObject =  new BusinessObject(name: 'Employee',
                                                description: 'A simple description',
                                                attributes: [
                                                    new Attribute(name: 'persistenceId', type: 'Long', mandatory: true),
                                                    new Attribute(name: 'firstName', description: 'The first name of the employee, must not be greater than 75 characters', type: 'String')
                                                ],
                                                relations: [
                                                    new Relation(name: 'addresses', description:'Employee addresses', relationType: 'COMPOSITION', type: 'Address', multiple: true)
                                                ],
                                                customQueries: [
                                                    new Query(name: 'findByNearestAddress', returnType: 'Employee', parameters: [new QueryParameter(name: 'location', type: 'Double')])
                                                ])


        when:
        engine.run("bdm/bdm_attributes_template.tpl", outputFile, [businessObject:businessObject])

        then:
        outputFile.text == '''*===== icon:list[] Attributes
                              *
                              *[grid=cols,options="header",cols="1,1e,3a",stripes=even,frame=topbot]
                              *|===
                              *|Name                                    |Type         |Description                                                           
                              *|[[Employee.persistenceId]]persistenceId*|Long         |                                                                      
                              *|[[Employee.firstName]]firstName         |String       |The first name of the employee, must not be greater than 75 characters
                              *|[[Employee.addresses]]&#x25c6; addresses|List<Address>|Employee addresses                                                    
                              *|===
                              *
                              *'''.stripMargin('*').denormalize()
    }

    def File templateFolder() {
        new File(BDMAttributesTemplateTest.getResource("/templates").getFile())
    }
}
