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

class BDMQueriesTemplateTest extends Specification {

    @Rule
    TemporaryFolder temporaryFolder

    def "should generate business object queries in a table"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("bdm_queries.adoc");
        def businessObject =  new BusinessObject(customQueries: [
                                                    new Query(name: 'findByNearestAddress', returnType: 'Employee', parameters: [
                                                                    new QueryParameter(name: 'location', type: 'Double'),
                                                                    new QueryParameter(name: 'lastUpdated', type: 'Date')
                                                                ])
                                                ])


        when:
        engine.run("bdm/bdm_queries_template.tpl", outputFile, [businessObject:businessObject])

        then:
        outputFile.text == '''*===== Queries
                              *
                              *====== findByNearestAddress
                              *
                              *_No description available_
                              *[grid=cols, options="header",cols="1e,1a"]
                              *|===
                              *|Return type|Parameters            
                              *|Employee   |location (_Double_) + 
                              *             lastUpdated (_Date_)
                              *|===
                              *
                              *'''.stripMargin('*').replace("\n", System.lineSeparator())
    }

    def File templateFolder() {
        new File(BDMQueriesTemplateTest.getResource("/templates").getFile())
    }
}
