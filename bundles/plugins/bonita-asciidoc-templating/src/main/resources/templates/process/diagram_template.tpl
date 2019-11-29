package process

@Field Diagram diagram

section 3, "$diagram.name ($diagram.version)"

newLine()

write diagram.description ?: '_No description available_'

2.times { newLine() }

write "image::diagrams/$diagram.name-${diagram.version}.png[]"

2.times { newLine() }

