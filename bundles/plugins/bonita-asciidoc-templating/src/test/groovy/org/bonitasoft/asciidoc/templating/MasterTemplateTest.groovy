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
package org.bonitasoft.asciidoc.templating

import org.bonitasoft.asciidoc.templating.TemplateEngine
import org.bonitasoft.asciidoc.templating.model.Project
import org.junit.Rule
import org.junit.rules.TemporaryFolder

import net.bytebuddy.matcher.HasSuperTypeMatcher
import spock.lang.Specification
import spock.lang.Unroll

class MasterTemplateTest extends Specification {
    
    @Rule
    TemporaryFolder temporaryFolder
    
    def "should generate master template with default header variables"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("master.adoc");
        def project = new Project(name: "Test Project", version: "0.0.1", bonitaVersion: '7.11')
        
        when:
        engine.run("master_template.tpl", outputFile, [project:project])
        
        then:
        def asciiDocContent = outputFile.text
        asciiDocContent.contains('= Test Project')
        asciiDocContent.contains('Generated with Bonita')
        asciiDocContent.contains('v0.0.1, {docdate}')
        asciiDocContent.contains(':toc:')
        asciiDocContent.contains(':toc-title: Table of contents')
        asciiDocContent.contains(':toclevels: 2')
        asciiDocContent.contains(':bonita-version: 7.11')
        asciiDocContent.contains(':imagesdir: ./doc/images')
        asciiDocContent.contains(':sectnums: numbered')
        asciiDocContent.contains(':sectanchors:')
    }
    
    def "should generate master template with specific author"() {
	given:
	def engine = new TemplateEngine(templateFolder())
	def outputFile = temporaryFolder.newFile("master.adoc");
	def project = new Project(name: "Test Project", 
	    version: "0.0.1", 
	    bonitaVersion: '7.11',
	    author: 'Romain Bioteau',
	    email: 'romain.bioteau@bonitasoft.com')
	
	when:
	engine.run("master_template.tpl", outputFile, [project:project])
	
	then:
	def asciiDocContent = outputFile.text
	asciiDocContent.contains('Romain Bioteau <romain.bioteau@bonitasoft.com>')

    }
    
    def File templateFolder() {
        new File(MasterTemplateTest.getResource("/templates").getFile())
    }
    
}
