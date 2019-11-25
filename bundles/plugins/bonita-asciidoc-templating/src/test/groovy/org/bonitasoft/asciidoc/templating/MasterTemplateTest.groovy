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
    
    def "should generate master template with proper header variables"() {
        given:
        def engine = new TemplateEngine(templateFolder())
        def outputFile = temporaryFolder.newFile("master.adoc");
        def project = new Project(name: "Test Project", version: "0.0.1", bonitaVersion: '7.11')
        
        when:
        engine.run("master_template.tpl",outputFile,project)
        
        then:
        def asciiDocContent = outputFile.text
        asciiDocContent.contains('= Test Project')
        asciiDocContent.contains('Generated with Bonita')
        asciiDocContent.contains(':revision: v0.0.1')
        asciiDocContent.contains(':date: {docdate}')
        asciiDocContent.contains(':toc:')
        asciiDocContent.contains(':toc-title: Table of contents')
        asciiDocContent.contains(':toclevels: 2')
        asciiDocContent.contains(':bonita-version: 7.11')
        asciiDocContent.contains(':imagesdir: ./doc/images')
        asciiDocContent.contains(':sectnums: numbered')
        asciiDocContent.contains(':sectanchors:')
    }
    
    def File templateFolder() {
        new File(MasterTemplateTest.getResource("/templates").getFile())
    }
    
    def File testFile(String name) {
        new File(MasterTemplateTest.getResource(name).getFile())
    }
    
}
