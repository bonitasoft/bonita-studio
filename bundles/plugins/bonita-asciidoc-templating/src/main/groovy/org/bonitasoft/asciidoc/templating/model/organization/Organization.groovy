package org.bonitasoft.asciidoc.templating.model.organization

import groovy.transform.Canonical
import groovy.transform.builder.Builder

@Canonical
@Builder
class Organization {
    
    Group[] groups
    
    Role[] roles
    
    Profile[] profiles
}
