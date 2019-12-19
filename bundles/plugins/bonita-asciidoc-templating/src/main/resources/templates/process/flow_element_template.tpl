package process

@Field FlowElement flowElement
@Field ResourceBundle messages

def iterationTypeImage = ''
if(flowElement.iterationType) {
    iterationTypeImage = " image:icons/${flowElement.iterationType}.png[title=\"${messages.getString(flowElement.iterationType)}\"]"
}

section 5, "${new FlowElementXRef(name: flowElement.name, process: flowElement.process).inlinedRefTag()}image:icons/${flowElement.bpmnType}.png[title=\"${flowElement.bpmnType}\"]$iterationTypeImage $flowElement.name"

newLine()

writeWithLineBreaks flowElement.description ?: "_${messages.getString('descriptionPlaceholder')}_"

2.times { newLine() }

if(flowElement.incomings) {
    write "*${messages.getString('previousFlowElements')}*: ${flowElement.incomings.source.collect{ new FlowElementXRef(name: it, process: flowElement.process).refLink() }.join(', ')}"
    2.times { newLine() }
}

if(flowElement.contract?.inputs) {
    section 6, "icon:handshake-o[] ${messages.getString('contractInputs')}"
    newLine()
    layout 'process/contract_inputs_template.tpl', contract:flowElement.contract, messages:messages
}

if(flowElement.contract?.constraints) {
    section 6, "icon:lock[] ${messages.getString('contractConstraints')}"
    newLine()
    layout 'process/contract_constraints_template.tpl', contract:flowElement.contract, messages:messages
}


if(flowElement.connectorsIn) {
    section 6, "icon:plug[] ${messages.getString('connectorsIn')}"
    newLine()
    layout 'process/connectors_template.tpl', connectors:flowElement.connectorsIn, messages:messages
}

if(flowElement.bpmnType == 'CallActivity') {
    section 6, "icon:plus-square[] ${messages.getString('calledProcess')}"
    newLine()
    
    if(flowElement.calledProcessName.type == ExpressionType.CONSTANT && flowElement.calledProcessName.content && flowElement.calledProcessVersion.type == ExpressionType.CONSTANT) {
        write "<<$flowElement.calledProcessName.content ($flowElement.calledProcessVersion.content)>>"
        newLine()
    }else {
        write true, new Block(caption: messages.getString('nameExpression'),
                              properties: ['source', 'groovy'],
                              content: flowElement.calledProcessName.content.trim())
        if(flowElement.calledProcessVersion?.content) {
          write true, new Block(caption: messages.getString('versionExpression'),
                    properties: ['source', 'groovy'],
                    content: flowElement.calledProcessVersion.content.trim())
        }
    }
    newLine()
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

}

