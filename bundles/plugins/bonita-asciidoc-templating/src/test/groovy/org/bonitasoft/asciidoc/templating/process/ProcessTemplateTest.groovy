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
import org.bonitasoft.asciidoc.templating.model.bdm.BusinessDataModel
import org.bonitasoft.asciidoc.templating.model.bdm.BusinessObject
import org.bonitasoft.asciidoc.templating.model.bdm.Package
import org.bonitasoft.asciidoc.templating.model.process.Actor
import org.bonitasoft.asciidoc.templating.model.process.ActorFilter
import org.bonitasoft.asciidoc.templating.model.process.Diagram
import org.bonitasoft.asciidoc.templating.model.process.Document
import org.bonitasoft.asciidoc.templating.model.process.Lane
import org.bonitasoft.asciidoc.templating.model.process.Parameter
import org.bonitasoft.asciidoc.templating.model.process.Process
import org.junit.Rule
import org.junit.rules.TemporaryFolder

import spock.lang.Specification

class ProcessTemplateTest extends Specification {

    @Rule
    TemporaryFolder temporaryFolder

    def "should generate process template"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("process.adoc");
        def process = new Process(name: 'MyProcess', displayName: 'My Process', version: '1.0', description: 'Some simple description')

        when:
        engine.run("process/process_template.tpl", outputFile, [process:process])

        then:
        outputFile.text == '''|==== MyProcess (1.0)
                              |
                              |*Display name:* My Process + 
                              |Some simple description
                              |
                              |image::processes/MyProcess-1.0.png[]
                              |
                              |'''.stripMargin().replace('\n', System.lineSeparator())
    }
    
    def "should generate process parameters table"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("process.adoc");
        def process = new Process(name: 'MyProcess', version: '1.0', parameters: [
                new Parameter(name: 'smtpHost', description: 'Email smtp server address', type: 'String'),
                new Parameter(name: 'smtpPassword', type: 'String')
            ])

        when:
        engine.run("process/process_template.tpl", outputFile, [process:process])

        then:
        outputFile.text == '''*==== MyProcess (1.0)
                              *
                              *_No description available_
                              *
                              *image::processes/MyProcess-1.0.png[]
                              *
                              *===== icon:gear[] Parameters
                              *
                              *[grid=cols, options="header",cols="1,1e,3a",stripes=even,frame=topbot]
                              *|===
                              *|Name        |Type  |Description              
                              *|smtpHost    |String|Email smtp server address
                              *|smtpPassword|String|                         
                              *|===
                              *
                              *'''.stripMargin('*').replace('\n', System.lineSeparator())
    }
    
    def "should generate process documents table"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("process.adoc");
        def process = new Process(name: 'MyProcess', version: '1.0', documents: [
                new Document(name: 'contractDoc', description: 'The contract to be signed'),
                new Document(name: 'photos', multiple: true)
            ])

        when:
        engine.run("process/process_template.tpl", outputFile, [process:process])

        then:
        outputFile.text == '''*==== MyProcess (1.0)
                              *
                              *_No description available_
                              *
                              *image::processes/MyProcess-1.0.png[]
                              *
                              *===== icon:file[] Documents
                              *
                              *[grid=cols, options="header",cols="1,3a",stripes=even,frame=topbot]
                              *|===
                              *|Name                                                               |Description              
                              *|[[myprocess.doc.contractdoc,contractDoc]]contractDoc               |The contract to be signed
                              *|[[myprocess.doc.photos,photos]]photos icon:files-o[title="Mutiple"]|                         
                              *|===
                              *
                              *'''.stripMargin('*').replace('\n', System.lineSeparator())
    }
    
    def "should generate process actors table"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("process.adoc");
        def process = new Process(name: 'MyProcess', version: '1.0', actors: [
                new Actor(name: 'Customer Service', description: 'The customer service actor'),
                new Actor(name: 'Customer', initiator: true)
            ])

        when:
        engine.run("process/process_template.tpl", outputFile, [process:process])

        then:
        outputFile.text == '''*==== MyProcess (1.0)
                              *
                              *_No description available_
                              *
                              *image::processes/MyProcess-1.0.png[]
                              *
                              *===== icon:users[] Actors
                              *
                              *[grid=cols, options="header",cols="1,3a",stripes=even,frame=topbot]
                              *|===
                              *|Name                                                                                       |Description               
                              *|[[myprocess.actor.customer-service,Customer Service]]Customer Service                      |The customer service actor
                              *|[[myprocess.actor.customer,Customer]]Customer icon:play-circle-o[title="Process initiator"]|                          
                              *|===
                              *
                              *'''.stripMargin('*').replace('\n', System.lineSeparator())
    }
    
    def "should generate process lanes section"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("process.adoc");
        def process = new Process(name: 'MyProcess', version: '1.0', lanes: [
                new Lane(name: 'My Lane', actor: 'Employee', actorFilter: new ActorFilter(name: 'ransomUser', definitionName: 'Single user'))
                ])

        when:
        engine.run("process/process_template.tpl", outputFile, [process:process])

        then:
        outputFile.text == '''*==== MyProcess (1.0)
                              *
                              *_No description available_
                              *
                              *image::processes/MyProcess-1.0.png[]
                              *
                              *===== image:icons/Lane.png[] Lanes
                              *
                              *====== My Lane (<<myprocess.actor.employee>> actor)
                              *
                              *_No description available_
                              *
                              *icon:filter[] *Actor filter:* ransomUser (Single user) + 
                              *_No description available_
                              *
                              *'''.stripMargin('*').replace('\n', System.lineSeparator())
    }

    def File templateFolder() {
        new File(ProcessTemplateTest.getResource("/templates").getFile())
    }
}
