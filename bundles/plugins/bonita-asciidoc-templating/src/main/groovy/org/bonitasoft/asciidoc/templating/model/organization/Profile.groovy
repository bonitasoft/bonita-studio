package org.bonitasoft.asciidoc.templating.model.organization

import groovy.transform.Canonical
import groovy.transform.builder.Builder

@Canonical
@Builder
class Profile {
    
    String name
    
    String description
    
}
