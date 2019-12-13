package process

@Field SequenceFlow transition
@Field String process
@Field ResourceBundle messages

def hasCondition = transition.useDecisionTable || transition.condition?.content
def asList = hasCondition || transition.description

def transitionName = transition.name ? "$transition.name: " : ''
if(!asList) {
    write '*'
}
write "$transitionName${messages.getString('to')} ${new FlowElementXRef(name: transition.target, process: process).refLink()}"
if(transition.defaultFlow) {
    write true, " (${messages.getString('default')})"
}
if(asList) {
    write '::'
}else {
    write '*'
    newLine()
}
if(transition.description) {
    write true, ' '
    writeWithLineBreaks transition.description
    newLine()
    if(hasCondition) {
        write '+'
        newLine()
    }
}
if(hasCondition) {
    newLine()
    write '+'
    newLine()
    write ".${messages.getString('when')}:"
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
        def conditionColumn = transition.decisionTable.lines.collect { it.conditions.content.collect{ "`$it`"}.join(" ${messages.getString('and')} ") }
        conditionColumn << messages.getString('byDefault').capitalize()
        def decisionColumn =  transition.decisionTable.lines.collect { it.takeTransition ? messages.getString('takeTransition') : messages.getString('doNotTakeTransition') }
        decisionColumn << (transition.decisionTable.defaultTakeTransition ?  messages.getString('takeTransition') : messages.getString('doNotTakeTransition'))
        write true, new Table(columnName: [messages.getString('conditions'), messages.getString('decision')],
                              footer : true,
                              stripes: 'none',
                              columnsFormat: ['4', '1'],
                              columms: [conditionColumn, decisionColumn])
    }
}
newLine()