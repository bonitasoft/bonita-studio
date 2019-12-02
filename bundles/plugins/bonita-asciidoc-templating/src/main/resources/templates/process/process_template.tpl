package process

@Field Process process

def keepIndent = true

section 4, "$process.name ($process.version)"

newLine()

if(process.displayName) {
    write "*Display name:* $process.displayName + "
    newLine()
}
write process.description ?: '_No description available_'

2.times { newLine() }

write "image::processes/$process.name-${process.version}.png[]"

2.times { newLine() }

if(process.parameters) {
    
    section 5, 'Parameters'
    
    newLine()
    

    write keepIndent, new Table( columnName: ['Name','Type','Description'],
                                 columnsFormat: ['1','1e','3a'],
                                 columms: [
                                     process.parameters.name,
                                     process.parameters.type,
                                     process.parameters.description])
    
}

if(process.documents) {
    
    section 5, 'Documents'
    
    newLine()
    
    write keepIndent, new Table( columnName: ['Name','Multiple','Description'],
                                 columnsFormat: ['1','1','3a'],
                                 columms: [
                                     process.documents.name.collect { "${crossRefId("doc.$it",it)}$it" },
                                     process.documents.multiple,
                                     process.documents.description])
    
}