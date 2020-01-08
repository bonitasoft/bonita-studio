package bdm

@Field BusinessObject businessObject
@Field ResourceBundle messages

section 5, "icon:list[] ${messages.getString('attributes')}"

newLine()

def allFields = []
allFields.addAll(businessObject.attributes as List)
allFields.addAll(businessObject.relations  as List)

def nameColumn = allFields.collect { "${new AttributeXRef(businessObject : businessObject, attributeName : it.name).inlinedRefTag()}${it.hasProperty('relationType') ? "${relationSymbol(it.relationType)} " : ''}$it.name${it.mandatory ? '*' : ''}" }
def typeColumn = allFields.collect { it.multiple ? "List<$it.type>" : it.type}
def descriptionColumn = allFields.collect { insertLineBreaks(it.description ?: '') }

def keepIndent = true
write  keepIndent, new Table(columnName : [messages.getString('name'), messages.getString('type'), messages.getString('description')],
                columnsFormat: ['1','1e','3a'],
                columms : [nameColumn, typeColumn, descriptionColumn])

newLine()

def String relationSymbol(Object relationType) {
    relationType == 'COMPOSITION' ? BLACK_DIAMOND : WHITE_DIAMOND
}