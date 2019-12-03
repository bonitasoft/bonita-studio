package org.bonitasoft.asciidoc.templating

import org.bonitasoft.asciidoc.templating.model.process.Actor
import org.bonitasoft.asciidoc.templating.model.process.Process

import groovy.transform.Canonical
import groovy.transform.CompileStatic
import groovy.transform.builder.Builder

@Builder
class DocumentXRef extends XRef {

    /**
     * The document name
     */
    String documentName
    /**
     * The process where the document is defined
     */
    Process process
    
    @Override
    public String getId() {
       crossRefId("${process.name}.doc.${documentName}")
    }

    @Override
    public String getLabel() {
       documentName
    }
    
}
