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
package org.bonitasoft.asciidoc.templating.organization

import org.bonitasoft.asciidoc.templating.TemplateEngine
import org.bonitasoft.asciidoc.templating.model.bdm.Attribute
import org.bonitasoft.asciidoc.templating.model.bdm.BusinessDataModel
import org.bonitasoft.asciidoc.templating.model.bdm.BusinessObject
import org.bonitasoft.asciidoc.templating.model.bdm.Package
import org.bonitasoft.asciidoc.templating.model.bdm.Query
import org.bonitasoft.asciidoc.templating.model.bdm.QueryParameter
import org.bonitasoft.asciidoc.templating.model.bdm.Relation
import org.bonitasoft.asciidoc.templating.model.organization.Group
import org.bonitasoft.asciidoc.templating.model.organization.Organization
import org.junit.Rule
import org.junit.rules.TemporaryFolder

import spock.lang.Specification

class GroupDiagramTemplateTest extends Specification {

    @Rule
    TemporaryFolder temporaryFolder
    
    def setup() {
        Locale.setDefault(Locale.ENGLISH)
    }

    def "should generate group plantuml diagram"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("groups.plantuml");
        def organization = new Organization(groups: [
            new Group(name: 'acme', displayName: 'Acme', subGroups: [
                new Group(name: 'hr', displayName: 'HR', description: 'Human and Resources group'),
                new Group(name: 'sales', displayName: 'Sales', subGroups: [new Group(name: 'eu', displayName: 'EU')])
                ])
            ])

        when:
        engine.run("organization/groups_diagram_template.tpl", outputFile, [organization:organization])

        then:
        outputFile.text == '''|@startuml
                              |!pragma graphviz_dot jdot
                              |
                              |object acme{
                              |    Acme
                              |}
                              |acme -- hr
                              |acme -- sales
                              |
                              |object hr{
                              |    HR
                              |    Human and Resources group
                              |}
                              |
                              |object sales{
                              |    Sales
                              |}
                              |sales -- eu
                              |
                              |object eu{
                              |    EU
                              |}
                              |
                              |@enduml
                              |'''.stripMargin().denormalize()
    }

    def File templateFolder() {
        new File(GroupDiagramTemplateTest.getResource("/templates").getFile())
    }
}
