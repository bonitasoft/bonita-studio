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
import org.bonitasoft.asciidoc.templating.model.organization.Profile
import org.bonitasoft.asciidoc.templating.model.organization.Role
import org.junit.Rule
import org.junit.rules.TemporaryFolder

import spock.lang.Specification

class OrganizationTemplateTest extends Specification {

    @Rule
    TemporaryFolder temporaryFolder
    
    def setup() {
        Locale.setDefault(Locale.ENGLISH)
    }

    def "should generate organization roles table"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("organization.adoc");
        def organization = new Organization(roles: [
            new Role(name: 'member', displayName: 'Member', description: 'member of the organization'),
            new Role(name: 'manager', displayName: 'Manager')
            ])

        when:
        engine.run("organization/organization_template.tpl", outputFile, [organization:organization])

        then:
        outputFile.text == '''|== Organization
                              |
                              |=== Groups
                              |
                              |image::groups.svg[]
                              |
                              |=== Roles
                              |
                              |[grid=cols,options="header",cols="1,1e,3a",stripes=even,frame=topbot]
                              ||===
                              ||Name   |Display name|Description               
                              ||member |Member      |member of the organization
                              ||manager|Manager     |                          
                              ||===
                              |
                              |'''.stripMargin().denormalize()
    }
    
    def "should generate organization profiles table"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("organization.adoc");
        def organization = new Organization(profiles: [
            new Profile(name: 'User', description: 'The user can view and perform tasks'),
            new Profile(name: 'Administrator'),
            new Profile(name: 'Custom Profile')
            ])

        when:
        engine.run("organization/organization_template.tpl", outputFile, [organization:organization])

        then:
        outputFile.text == '''|== Organization
                              |
                              |=== Groups
                              |
                              |image::groups.svg[]
                              |
                              |=== Profiles
                              |
                              |[grid=cols,options="header",cols="1e,3a",stripes=even,frame=topbot]
                              ||===
                              ||Name                                    |Description                        
                              ||[[profile.user]]User                    |The user can view and perform tasks
                              ||[[profile.administrator]]Administrator  |                                   
                              ||[[profile.custom-profile]]Custom Profile|                                   
                              ||===
                              |
                              |'''.stripMargin().denormalize()
    }

    def File templateFolder() {
        new File(OrganizationTemplateTest.getResource("/templates").getFile())
    }
}
