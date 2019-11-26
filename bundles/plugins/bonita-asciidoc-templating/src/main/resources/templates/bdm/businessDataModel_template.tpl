package bdm

@Field BusinessDataModel businessDataModel

section 2,'Business Data Model'

newLine()

businessDataModel.businessObjects.each { BusinessObject object ->
    section 3, object.name
    newLine()
    write object.description ?: "_No description available_"
    newLine()
}
