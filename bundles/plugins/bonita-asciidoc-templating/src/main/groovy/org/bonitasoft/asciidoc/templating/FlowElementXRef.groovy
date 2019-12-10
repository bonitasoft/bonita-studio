package org.bonitasoft.asciidoc.templating

import org.bonitasoft.asciidoc.templating.model.process.Actor
import org.bonitasoft.asciidoc.templating.model.process.Process

import groovy.transform.Canonical
import groovy.transform.CompileStatic
import groovy.transform.builder.Builder

@Builder
class FlowElementXRef extends XRef {

    /**
     * The flow element name
     */
    String name
    /**
     * The process name where the flow element is defined
     */
    String process
    
    @Override
    public String getId() {
       crossRefId("${process}.flowElement.${name}")
    }

    @Override
    public String getLabel() {
        name
    }
    
}
