package process

@Field Diagram diagram

section 3, "$diagram.name ($diagram.version)"

newLine()

write diagram.description ?: '_No description available_'

2.times { newLine() }

if( diagram.processes.size() > 1) {

    write "image::diagrams/$diagram.name-${diagram.version}.png[]"

    2.times { newLine() }
}

diagram.processes.each { layout 'process/process_template.tpl', process:it }

