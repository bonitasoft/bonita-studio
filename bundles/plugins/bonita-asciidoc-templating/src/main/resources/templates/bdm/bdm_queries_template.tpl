package bdm

@Field BusinessObject businessObject 

def keepIndent = true

section 5, 'Queries'

newLine()

businessObject.customQueries.each { Query query ->
    
    section 6, query.name
    
    newLine()
   
    write query.description ?: '_No description available_'
    
    newLine()
    
    def parametersColumn =  query.parameters.collect{ "$it.name (_${it.type}_)" }.join(" + ${System.lineSeparator()}")
    write  keepIndent, new Table(columnName : ['Return type', 'Parameters'],
                    columnsFormat: ['1e','1a'],
                    columms : [[query.returnType], [parametersColumn]])
    
    newLine()
    
}