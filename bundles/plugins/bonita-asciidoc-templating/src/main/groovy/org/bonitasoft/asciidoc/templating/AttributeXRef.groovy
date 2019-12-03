package org.bonitasoft.asciidoc.templating

import org.bonitasoft.asciidoc.templating.model.bdm.BusinessObject
import org.bonitasoft.asciidoc.templating.model.process.Actor
import org.bonitasoft.asciidoc.templating.model.process.Process

import groovy.transform.Canonical
import groovy.transform.CompileStatic
import groovy.transform.builder.Builder

@Builder
class AttributeXRef extends XRef {

    /**
     * The attribute name
     */
    String attributeName
    /**
     * The Business Object where the attribute is defined
     */
    BusinessObject businessObject
    
    @Override
    public String getId() {
       "${businessObject.name}.$attributeName"
    }


    @Override
    public String getLabel() {
        attributeName
    }
    
}
