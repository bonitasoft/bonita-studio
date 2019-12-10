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

if(flowElement.outgoings) {
    write "*${messages.getString('outgoingTransitions')}*:"
    
    2.times { newLine() }
    
    write '[horizontal]'
    newLine()
    
    flowElement.outgoings.collect{ SequenceFlow transition -> 
        
        write transition.name ?: "_${messages.getString('unnamed')}_"
        if(transition.defaultFlow) {
            write true, " (${messages.getString('default')})"
        }
        write ':: '
        writeWithLineBreaks transition.description ?: "_${messages.getString('descriptionPlaceholder')}_"
        newLine()
        write '+'
        newLine()
    	write "${messages.getString('to')} ${new FlowElementXRef(name: transition.target, process: flowElement.process).refLink()}"
        
        def hasCondition = transition.useDecisionTable || transition.condition?.content
    	if(hasCondition) {
        	    write true, " ${messages.getString('when')}:"
        	    newLine()
        	    write '+'
        	    newLine()
        	    if(!transition.useDecisionTable) {
            		write '[source,groovy]'
            		newLine()
            		write '----'
            		newLine()
            		write transition.condition.content
            		newLine()
            		write '----'
                    newLine()
    	       }else {
            		def conditionColumn = transition.decisionTable.lines.collect {  it.conditions.content.collect{ "`$it`"}.join(' and ') }
            		conditionColumn << messages.getString('byDefault').capitalize()
            		def decisionColumn =  transition.decisionTable.lines.collect { it.takeTransition ? messages.getString('takeTransition') : messages.getString('doNotTakeTransition')}
            		decisionColumn << (transition.decisionTable.defaultTakeTransition ?  messages.getString('takeTransition') : messages.getString('doNotTakeTransition'))
            		write true, new Table(columnName: [messages.getString('conditions'), messages.getString('decision')],
            		    		       footer : true,
            		    		       columnsFormat: ['4','1'],
            				           columms: [conditionColumn, decisionColumn])
    	     }
    	}else {
            newLine()
        }
        
    }
    newLine() 
}

