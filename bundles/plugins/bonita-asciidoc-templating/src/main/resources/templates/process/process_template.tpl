package process

import org.bonitasoft.asciidoc.templating.model.process.EventSubProcess
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
            process.actors.collect { "${new ActorXRef(process: process.name, actor: it.name).inlinedRefTag()}$it.name${it.initiator ? " icon:play-circle-o[title=\"${messages.getString('processInitiator')}\"]" : ''}" },
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

if(process.contract?.inputs) {
    section 5, "icon:handshake-o[] ${messages.getString('contractInputs')}"
    newLine()
    layout 'process/contract_inputs_template.tpl', contract:process.contract, messages:messages
}

if(process.contract?.constraints) {
    section 5, "icon:lock[] ${messages.getString('contractConstraints')}"
    newLine()
    layout 'process/contract_constraints_template.tpl', contract:process.contract, messages:messages
}

if(process.connectorsIn) {
    section 5, "icon:plug[] ${messages.getString('connectorsIn')}"
    layout 'process/connectors_template.tpl', connectors:process.connectorsIn, messages:messages
}

if(process.connectorsOut) {
    section 5, "icon:plug[] ${messages.getString('connectorsOut')}"
    layout 'process/connectors_template.tpl', connectors:process.connectorsOut, messages:messages
}

if(process.lanes) {
    process.lanes.each { Lane lane ->
        layout 'process/lane_template.tpl', lane:lane, messages:messages
    }
}

if(process.flowElements) {
    process.flowElements.each { FlowElement flowElement -> layout 'process/flow_element_template.tpl', flowElement:flowElement, messages:messages }
}

if(process.eventSubprocesses) {
    process.eventSubprocesses.each { EventSubProcess eventSubprocess ->
         section 4, "image:icons/SubProcessEvent.png[] $eventSubprocess.name" 
         newLine()
         writeWithLineBreaks eventSubprocess.description ?: "_${messages.getString('descriptionPlaceholder')}_"
         2.times { newLine() }
         eventSubprocess.flowElements.each { FlowElement flowElement->
             layout 'process/flow_element_template.tpl', flowElement:flowElement, messages:messages
         }
   }
}
