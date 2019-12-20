package process

import org.bonitasoft.asciidoc.templating.model.process.SequenceFlow

@Field BoundaryEvent[] events
@Field String process
@Field ResourceBundle messages

events.each { BoundaryEvent event ->
    
    write "image:icons/${event.bpmnType}.png[] $event.name:: ${event.description ?: "_${messages.getString('descriptionPlaceholder')}_" }"
    newLine()
    if(event.outgoings) {
        write '+'
        newLine()
        event.outgoings.each { SequenceFlow transition -> layout 'process/transition_template.tpl', transition:transition, process:process, messages:messages }
    }else {
        newLine()
    }
    
}