@Field Project project

section 1, project.name
write "${project.author ?: 'Generated with Bonita' }${project.email ? " <$project.email>": ''}"
newLine()
write "v$project.version, {docdate}"
newLine()
var 'toc'
var 'toc-title', 'Table of contents'
var 'toclevels', 3
var 'bonita-version', project.bonitaVersion
var 'imagesdir', "./$project.imageFolderPath"
var 'sectnums', 'numbered'
var 'sectanchors'

newLine()


if (project.businessDataModel) layout 'bdm/bdm_template.tpl', businessDataModel:project.businessDataModel

if (project.diagrams) {
   newLine()
   section 2, 'Diagrams'
   newLine()
   project.diagrams.each { Diagram diagram -> layout 'process/diagram_template.tpl', diagram:diagram }
}
