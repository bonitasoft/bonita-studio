package bdm

@Field BusinessObject businessObject 

section 5, 'Attributes'

newLine()

def allFields = []
allFields.addAll(businessObject.attributes as List)
allFields.addAll(businessObject.relations  as List)

def nameColumn = allFields.collect { "${it.hasProperty('relationType') ? "${relationSymbol(it.relationType)} " : ''}$it.name${it.mandatory ? '*' : ''}" }
def typeColumn = allFields.collect { it.multiple ? "List<$it.type>" : it.type}
def descriptionColumn = allFields.collect { it.label ? "*Label:* $it.label + "+System.lineSeparator()+  it.description : it.description ?: '' }

def keepIndent = true
write  keepIndent, new Table(columnName : ['Name', 'Type', 'Description'],
                columnsFormat: ['1','1e','3a'],
                columms : [nameColumn, typeColumn, descriptionColumn])

newLine()

def String relationSymbol(Object relationType) {
    relationType == 'COMPOSITION' ? BLACK_DIAMOND : WHITE_DIAMOND
}