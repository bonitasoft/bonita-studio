package bdm

@Field BusinessObject businessObject
@Field ResourceBundle messages

def keepIndent = true

section 5, "icon:search[] ${messages.getString('queries')}"

newLine()

businessObject.customQueries.each { Query query ->
    
    section 6, query.name
    
    newLine()
   
    writeWithLineBreaks query.description ?: "_${messages.getString('descriptionPlaceholder')}_"
    
    newLine()
    
    def parametersColumn =  query.parameters.collect{ "$it.name (_${it.type}_)" }.join(" + ${System.lineSeparator()}")
    write  keepIndent, new Table(columnName : [messages.getString('returnType'), messages.getString('parameters')],
                                 columnsFormat: ['1e','1a'],
                                 columms : [[query.returnType], [parametersColumn]])
    
    newLine()
    
}