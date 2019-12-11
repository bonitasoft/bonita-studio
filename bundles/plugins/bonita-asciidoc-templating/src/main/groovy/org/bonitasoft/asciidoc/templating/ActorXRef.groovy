package org.bonitasoft.asciidoc.templating

import org.bonitasoft.asciidoc.templating.model.process.Actor
import org.bonitasoft.asciidoc.templating.model.process.Process

import groovy.transform.Canonical
import groovy.transform.CompileStatic
import groovy.transform.builder.Builder

@Builder
class ActorXRef extends XRef {

    /**
     * The actor name
     */
    String actor
    /**
     * The process where the actor is defined
     */
    String process
    
    @Override
    public String getId() {
       crossRefId("${process}.actor.${actor}")
    }

    @Override
    public String getLabel() {
        actor
    }
    
}
