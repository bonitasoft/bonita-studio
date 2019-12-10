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
import org.bonitasoft.asciidoc.templating.model.process.DecisionTable
import org.bonitasoft.asciidoc.templating.model.process.DecisionTableLine
import org.bonitasoft.asciidoc.templating.model.process.Diagram
import org.bonitasoft.asciidoc.templating.model.process.Document
import org.bonitasoft.asciidoc.templating.model.process.Expression
import org.bonitasoft.asciidoc.templating.model.process.FlowElement
import org.bonitasoft.asciidoc.templating.model.process.Lane
import org.bonitasoft.asciidoc.templating.model.process.Parameter
import org.bonitasoft.asciidoc.templating.model.process.Process
import org.bonitasoft.asciidoc.templating.model.process.SequenceFlow
import org.junit.Rule
import org.junit.rules.TemporaryFolder

import spock.lang.Specification

class FlowElementTemplateTest extends Specification {

    @Rule
    TemporaryFolder temporaryFolder
    
    def setup() {
        Locale.setDefault(Locale.ENGLISH)
    }

    def "should generate flow element template"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("flowElement.adoc");
        def flowElement = new FlowElement(name: 'My Task',
        description: 'Some simple description',
        bpmnType: 'Task',
        process: 'Pool')

        when:
        engine.run("process/flow_element_template.tpl", outputFile, [flowElement:flowElement])

        then:
        outputFile.text == '''|===== [[pool.flowelement.my-task]]image:icons/Task.png[title="Task"] My Task
                              |
                              |Some simple description
                              |
                              |'''.stripMargin().denormalize()
    }

    def "should generate flow element previous flow elements"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("flowElement.adoc");
        def flowElement = new FlowElement(name: 'My Task',
        description: 'Some simple description',
        bpmnType: 'Task',
        process: 'Pool',
        incomings: [new SequenceFlow(source: 'Start', target: 'My Task')])

        when:
        engine.run("process/flow_element_template.tpl", outputFile, [flowElement:flowElement])

        then:
        outputFile.text == '''|===== [[pool.flowelement.my-task]]image:icons/Task.png[title="Task"] My Task
                              |
                              |Some simple description
                              |
                              |*Previous flow element(s)*: <<pool.flowelement.start,Start>>
		                      |
			                  |'''.stripMargin().denormalize()
    }
    
    def "should generate flow element outgoing transitions without conditions"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("flowElement.adoc");
        def flowElement = new FlowElement(name: 'My Task',
        description: 'Some simple description',
        bpmnType: 'Task',
        process: 'Pool',
        outgoings: [new SequenceFlow(source: 'My Task', target: 'Another task'), new SequenceFlow(source: 'My Task', target: 'Gateway')])

        when:
        engine.run("process/flow_element_template.tpl", outputFile, [flowElement:flowElement])

        then:
        outputFile.text == '''|===== [[pool.flowelement.my-task]]image:icons/Task.png[title="Task"] My Task
                              |
                              |Some simple description
                              |
                              |*Outgoing transition(s)*:
                              |
                              |[horizontal]
                              |_unnamed_:: _No description available_
                              |+
                              |To <<pool.flowelement.another-task,Another task>>
                              |_unnamed_:: _No description available_
                              |+
                              |To <<pool.flowelement.gateway,Gateway>>
                              |
                              |'''.stripMargin().denormalize()
    }
    
    def "should generate flow element outgoing transitions with condition expression"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("flowElement.adoc");
        def flowElement = new FlowElement(name: 'My Task',
                                          description: 'Some simple description',
                                          bpmnType: 'Task',
                                          process: 'Pool',
                                          outgoings: [new SequenceFlow(source: 'My Task', target: 'Another task', condition: new Expression(content: 'a > b')), 
                                                      new SequenceFlow(source: 'My Task', target: 'Gateway')])

        when:
        engine.run("process/flow_element_template.tpl", outputFile, [flowElement:flowElement])

        then:
        outputFile.text == '''|===== [[pool.flowelement.my-task]]image:icons/Task.png[title="Task"] My Task
                              |
                              |Some simple description
                              |
                              |*Outgoing transition(s)*:
                              |
                              |[horizontal]
                              |_unnamed_:: _No description available_
                              |+
                              |To <<pool.flowelement.another-task,Another task>> when:
                              |+
                              |[source,groovy]
                              |----
                              |a > b
                              |----
                              |_unnamed_:: _No description available_
                              |+
                              |To <<pool.flowelement.gateway,Gateway>>
                              |
                              |'''.stripMargin().denormalize()
    }
    
    def "should generate flow element outgoing transitions with decision table"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("flowElement.adoc");
        def flowElement = new FlowElement(name: 'My Task',
                                          description: 'Some simple description',
                                          bpmnType: 'Task',
                                          process: 'Pool',
                                          outgoings: [new SequenceFlow(source: 'My Task', target: 'Another task', 
                                                                      useDecisionTable: true, 
                                                                      decisionTable: new DecisionTable(lines: [
                                                                          new DecisionTableLine(conditions: [new Expression(content: 'a > b'), new Expression(content: 'c > b')], takeTransition: true),
                                                                          new DecisionTableLine(conditions: [new Expression(content: 'requestApproved')], takeTransition: true),
                                                                          ])),
                                                      new SequenceFlow(source: 'My Task', target: 'Gateway')])

        when:
        engine.run("process/flow_element_template.tpl", outputFile, [flowElement:flowElement])

        then:
        outputFile.text == '''*===== [[pool.flowelement.my-task]]image:icons/Task.png[title="Task"] My Task
                              *
                              *Some simple description
                              *
                              **Outgoing transition(s)*:
                              *
                              *[horizontal]
                              *_unnamed_:: _No description available_
                              *+
                              *To <<pool.flowelement.another-task,Another task>> when:
                              *+
                              *[grid=cols, options="header,footer",cols="4,1",stripes=even,frame=topbot]
                              *|===
                              *|Conditions         |Decision              
                              *|`a > b` and `c > b`|Take transition       
                              *|`requestApproved`  |Take transition       
                              *|By default         |Do not take transition
                              *|===
                              *_unnamed_:: _No description available_
                              *+
                              *To <<pool.flowelement.gateway,Gateway>>
                              *
                              *'''.stripMargin('*').denormalize()
    }
    
    def "should generate flow element outgoing transitions description"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("flowElement.adoc");
        def flowElement = new FlowElement(name: 'My Task',
                                          description: 'Some simple description',
                                          bpmnType: 'Task',
                                          process: 'Pool',
                                          outgoings: [new SequenceFlow(name: 'Enough time', description: 'Some sequence flow description' ,source: 'My Task', target: 'Another task', defaultFlow: true)])

        when:
        engine.run("process/flow_element_template.tpl", outputFile, [flowElement:flowElement])

        then:
        outputFile.text == '''*===== [[pool.flowelement.my-task]]image:icons/Task.png[title="Task"] My Task
                              *
                              *Some simple description
                              *
                              **Outgoing transition(s)*:
                              *
                              *[horizontal]
                              *Enough time (default):: Some sequence flow description
                              *+
                              *To <<pool.flowelement.another-task,Another task>>
                              *
                              *'''.stripMargin('*').denormalize()
    }

    def File templateFolder() {
        new File(FlowElementTemplateTest.getResource("/templates").getFile())
    }
}
