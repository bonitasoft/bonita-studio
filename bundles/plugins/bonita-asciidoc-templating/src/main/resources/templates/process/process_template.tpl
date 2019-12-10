package process

import org.bonitasoft.asciidoc.templating.model.process.FlowElement

@Field Process process
@Field ResourceBundle messages

def keepIndent = true

section 4, "$process.name ($process.version)"

newLine()

if(process.displayName) {
    write "*${messages.getString('displayName')}:* $process.displayName + "
    newLine()
}
writeWithLineBreaks process.description ?: "_${messages.getString('descriptionPlaceholder')}_"

2.times { newLine() }

write "image::processes/$process.name-${process.version}.png[]"

2.times { newLine() }

if(process.actors) {
    
    section 5, "icon:users[] ${messages.getString('actors')}"
    
    newLine()
    
    write keepIndent, new Table( columnName: [messages.getString('name'), messages.getString('description')],
        columnsFormat: ['1','3a'],
        columms: [
            process.actors.collect { "${new ActorXRef(process: process, actor: it.name).inlinedRefTag()}$it.name${it.initiator ? " icon:play-circle-o[title=\"${messages.getString('processInitiator')}\"]" : ''}" },
            process.actors.description])
    
    newLine()
}

if(process.parameters) {
    
    section 5, "icon:gear[] ${messages.getString('parameters')}"
    
    newLine()
    

    write keepIndent, new Table( columnName: [messages.getString('name'), messages.getString('type'), messages.getString('description')],
                                 columnsFormat: ['1','1e','3a'],
                                 columms: [
                                     process.parameters.name,
                                     process.parameters.type,
                                     process.parameters.description])
    newLine()
    
}

if(process.documents) {
    
    section 5, "icon:file[] ${messages.getString('documents')}"
    
    newLine()
    
    write keepIndent, new Table( columnName: [messages.getString('name'), messages.getString('description')],
                                 columnsFormat: ['1','3a'],
                                 columms: [
                                     process.documents.collect { "${new DocumentXRef(process: process, documentName: it.name).inlinedRefTag()}$it.name${it.multiple ? " icon:files-o[title=\"${messages.getString('multiple')}\"]" : ''}" },
                                     process.documents.description])
    newLine()
    
}

if(process.lanes) {
    
    section 5, "image:icons/Lane.png[] ${messages.getString('lanes')}"
    
    newLine()
    
    process.lanes.each { Lane lane ->
        
        section 6, "$lane.name${lane.actor ? " (${new ActorXRef(process: process, actor: lane.actor).refLink()} actor)" : ''}"
        
        newLine()
        
        writeWithLineBreaks lane.description ?: "_${messages.getString('descriptionPlaceholder')}_"
        
        2.times { newLine() }
        
        if(lane.actorFilter) {
            write "icon:filter[] *${messages.getString('actorFilter')}:* $lane.actorFilter.name ($lane.actorFilter.definitionName) + "
            newLine()
            writeWithLineBreaks  lane.actorFilter.description ?: "_${messages.getString('descriptionPlaceholder')}_"
            2.times { newLine() }
        }
    }
}

if(process.flowElements) {
    process.flowElements.each { FlowElement flowElement -> layout 'process/flow_element_template.tpl', flowElement:flowElement, messages:messages }
}
