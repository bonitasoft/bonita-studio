package bdm

@Field BusinessDataModel businessDataModel

section 2,'Business Data Model'

newLine()

write 'image::bdm.svg[]'

2.times { newLine() }

businessDataModel.packages.each { Package pckg ->
    
    section 3, "Package $pckg.name"
    newLine()
    
    pckg.businessObjects.each { BusinessObject object ->
        section 4, object.name
        newLine()
        write object.description ?: "_No description available_"
        2.times { newLine() }
        
        if(object.attributes || object.relations) layout 'bdm/bdm_attributes_template.tpl', businessObject: object
    }

}