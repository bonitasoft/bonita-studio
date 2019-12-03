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

if(process.actors) {
    
    section 5, 'icon:users[] Actors'
    
    newLine()
    
    write keepIndent, new Table( columnName: ['Name','Description'],
        columnsFormat: ['1','3a'],
        columms: [
            process.actors.collect { "${new ActorXRef(process: process, actor: it.name).inlinedRefTag()}$it.name${it.initiator ? ' icon:play-circle-o[title=\"Process initiator\"]' : ''}" },
            process.actors.description])
    
    newLine()
}

if(process.parameters) {
    
    section 5, 'icon:gear[] Parameters'
    
    newLine()
    

    write keepIndent, new Table( columnName: ['Name','Type','Description'],
                                 columnsFormat: ['1','1e','3a'],
                                 columms: [
                                     process.parameters.name,
                                     process.parameters.type,
                                     process.parameters.description])
    newLine()
    
}

if(process.documents) {
    
    section 5, 'icon:file[] Documents'
    
    newLine()
    
    write keepIndent, new Table( columnName: ['Name','Description'],
                                 columnsFormat: ['1','3a'],
                                 columms: [
                                     process.documents.collect { "${new DocumentXRef(process: process, documentName: it.name).inlinedRefTag()}$it.name${it.multiple ? ' icon:files-o[title=\"Mutiple\"]' : ''}" },
                                     process.documents.description])
    newLine()
    
}

if(process.lanes) {
    
    section 5, 'image:icons/Lane.png[] Lanes'
    
    newLine()
    
    process.lanes.each { Lane lane ->
        
        section 6, "$lane.name${lane.actor ? " (${new ActorXRef(process: process, actor: lane.actor).refLink()} actor)" : ''}"
        
        newLine()
        
        write lane.description ?: '_No description available_'
        
        2.times { newLine() }
        
        if(lane.actorFilter) {
            write "icon:filter[] *Actor filter:* $lane.actorFilter.name ($lane.actorFilter.definitionName) + "
            newLine()
            write  lane.actorFilter.description ?: '_No description available_'
            2.times { newLine() }
        }
        
    }
    
}
