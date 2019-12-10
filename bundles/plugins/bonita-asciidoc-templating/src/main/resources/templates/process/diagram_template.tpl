package process

@Field Diagram diagram
@Field ResourceBundle messages

section 3, "$diagram.name ($diagram.version)"

newLine()

writeWithLineBreaks diagram.description ?: "_${messages.getString('descriptionPlaceholder')}_"

2.times { newLine() }

if( diagram.processes.size() > 1) {

    write "image::diagrams/$diagram.name-${diagram.version}.png[]"

    2.times { newLine() }
}

diagram.processes.each { layout 'process/process_template.tpl', process:it, messages:messages }

