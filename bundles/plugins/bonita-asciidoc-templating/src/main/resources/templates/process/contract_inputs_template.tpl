package process

@Field Contract contract
@Field ResourceBundle messages

addContractInputs(contract.inputs)

def addContractInputs(inputs) {
    def nodes = inputs.collect { createContractInputNode it }.join(",${System.lineSeparator()}    ")
    write '[verse]'
    newLine()
    write '{'
    newLine()
    write true, "    $nodes"
    newLine()
    write '}'
    newLine()
    inputs.findAll{ it.children }.each{ addComplexContractInputReferences it }
    newLine()
}

def addComplexContractInputReferences(input) {
    def name = input.name.capitalize()
    def nodes = input.children.collect { createContractInputNode it }.join(",${System.lineSeparator()}    ")
    newLine()
    write '[verse]'
    newLine()
    write "[teal]#$name# {"
    newLine()
    write true, "    $nodes"
    newLine()
    write  '}'
    newLine()
    input.children.findAll{ it.children }.each{ addComplexContractInputReferences it }
}

def createContractInputNode(input) {
    def inputName = input.name
    def capitalizedInputName = input.name.capitalize()
    def inputType = input.type.toString().toLowerCase().capitalize()
    def multiple = input.multiple ? ", _multiple_" : ''
    def isComplex = inputType == "Complex"
    def descritpion = input.description ? " [green]_//${input.description}_" : ''

    def node = isComplex
            ? "$inputName ([teal]_${capitalizedInputName}_$multiple)$descritpion"
            : "$inputName ([olive]_${inputType}_$multiple)$descritpion"
}
