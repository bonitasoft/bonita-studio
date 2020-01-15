package bdm

@Field BusinessDataModel businessDataModel
@Field ResourceBundle messages

section 2, messages.getString('businessDataModel')

newLine()

write 'image::bdm.svg[]'

2.times { newLine() }

businessDataModel.packages.each { Package pckg ->
    
    section 3, "Package $pckg.name"
    newLine()
    
    pckg.businessObjects.each { BusinessObject object ->
        section 4, object.name
        newLine()
        writeWithLineBreaks object.description ?: "_${messages.getString('descriptionPlaceholder')}_"
        2.times { newLine() }
        
        if(object.attributes || object.relations) layout 'bdm/bdm_attributes_template.tpl', businessObject:object, messages:messages
        if(object.uniqueConstraints) layout 'bdm/bdm_constraints_template.tpl', businessObject:object, messages:messages
        if(object.customQueries) layout 'bdm/bdm_queries_template.tpl', businessObject:object, messages:messages
    }

}