package bdm

import org.bonitasoft.asciidoc.templating.model.bdm.UniqueConstraint

@Field BusinessObject businessObject
@Field ResourceBundle messages

def keepIndent = true

section 5, "icon:link[] ${messages.getString('uniqueConstraints')}"

newLine()


businessObject.uniqueConstraints.each { UniqueConstraint constraint ->
    
    section 6, "$constraint.name [${constraint.attributes.collect { new AttributeXRef(businessObject : businessObject, attributeName : it).refLink() }.join(', ')}]"
    
    newLine()
   
    writeWithLineBreaks constraint.description ?: "_${messages.getString('descriptionPlaceholder')}_"
    
    2.times { newLine() }
    
}