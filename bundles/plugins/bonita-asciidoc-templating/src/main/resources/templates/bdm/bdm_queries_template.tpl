package bdm

@Field BusinessObject businessObject
@Field ResourceBundle messages

def keepIndent = true

section 5, "icon:search[] ${messages.getString('queries')}"

newLine()

businessObject.customQueries.each { Query query ->
    
    section 6, query.name
    
    newLine()
   
    write "${messages.getString('returnType')}: _${query.returnType}_ + "
    newLine()
    writeWithLineBreaks query.description ?: "_${messages.getString('descriptionPlaceholder')}_"
    
    2.times { newLine() }
    
    write ".${messages.getString('queryParameters')}"
    newLine()
    write  keepIndent, new Table(columnName : [messages.getString('name'),messages.getString('type'), messages.getString('description')],
                                 columnsFormat: ['1','1e','3a'],
                                 caption: '',
                                 columms : [query.parameters.name, query.parameters.type, query.parameters.collect{insertLineBreaks(it.description ?: '')} ])
    
    newLine()
    
}