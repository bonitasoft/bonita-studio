package process

@Field Contract contract
@Field ResourceBundle messages

contract.constraints.each { ContractConstraint constraint ->
    write "$constraint.name:: "
    writeWithLineBreaks constraint.description ?: "_${messages.getString('descriptionPlaceholder')}_"
    newLine()
    write '+'
    newLine()
    write true, new Block(caption: messages.getString('expression'),
                          properties: ['source','groovy'],
                          content: constraint.expression.trim())
    write '+'
    newLine()
    write true, new Block(caption: messages.getString('technicalErrorMessage'),
                          content: constraint.errorMessage.trim())
}

newLine()
