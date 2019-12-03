package bdm

import org.bonitasoft.asciidoc.templating.model.bdm.UniqueConstraint

@Field BusinessObject businessObject 

def keepIndent = true

section 5, 'icon:link[] Unique constraints'

newLine()


businessObject.uniqueConstraints.each { UniqueConstraint constraint ->
    
    section 6, "$constraint.name [${constraint.attributes.collect { new AttributeXRef(businessObject : businessObject, attributeName : it).refLink() }.join(', ')}]"
    
    newLine()
   
    write constraint.description ?: '_No description available_'
    
    2.times { newLine() }
    
}