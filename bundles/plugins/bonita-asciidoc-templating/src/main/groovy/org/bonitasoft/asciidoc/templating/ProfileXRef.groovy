package org.bonitasoft.asciidoc.templating

import org.bonitasoft.asciidoc.templating.model.process.Actor
import org.bonitasoft.asciidoc.templating.model.process.Process

import groovy.transform.Canonical
import groovy.transform.CompileStatic
import groovy.transform.builder.Builder

@Builder
class ProfileXRef extends XRef {

    /**
     * The profile name
     */
    String profile
    
    @Override
    public String getId() {
       crossRefId("profile.${profile}")
    }

    @Override
    public String getLabel() {
        profile
    }
    
}
