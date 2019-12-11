package process

@Field FlowElement flowElement
@Field ResourceBundle messages

section 5, "${new FlowElementXRef(name: flowElement.name, process: flowElement.process).inlinedRefTag()}image:icons/${flowElement.bpmnType}.png[title=\"${flowElement.bpmnType}\"] $flowElement.name"

newLine()

writeWithLineBreaks flowElement.description ?: "_${messages.getString('descriptionPlaceholder')}_"

2.times { newLine() }

if(flowElement.incomings) {
    write "*${messages.getString('previousFlowElements')}*: ${flowElement.incomings.source.collect{ new FlowElementXRef(name: it, process: flowElement.process).refLink() }.join(', ')}"
    2.times { newLine() }
}

if(flowElement.connectorsIn) {
    section 6, "icon:plug[] ${messages.getString('connectorsIn')}"
    newLine()
    layout 'process/connectors_template.tpl', connectors:flowElement.connectorsIn, messages:messages
}

if(flowElement.connectorsOut) {
    section 6, "icon:plug[] ${messages.getString('connectorsOut')}"
    newLine()
    layout 'process/connectors_template.tpl', connectors:flowElement.connectorsOut, messages:messages
}


if(flowElement.outgoings) {
    section 6, "icon:arrow-right[] ${messages.getString('outgoingTransitions')}"

    newLine()

    flowElement.outgoings.collect{ SequenceFlow transition ->
        layout 'process/transition_template.tpl', transition:transition, messages:messages, process:flowElement.process
    }

    newLine()
}

