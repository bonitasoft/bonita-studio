@Field Project project
@Field ResourceBundle messages

section 1, project.name
write "${project.author ?: messages.getString('generatedWithBonita') }${project.email ? " <$project.email>": ''}"
newLine()
write "v$project.version, {docdate}"
newLine()
var 'toc'
var 'toc-title', messages.getString('tableOfContents')
var 'toclevels', 3
var 'bonita-version', project.bonitaVersion
var 'imagesdir', "./$project.imageFolderPath"
var 'sectnums', 'numbered'
var 'sectanchors'

newLine()


if (project.businessDataModel) layout 'bdm/bdm_template.tpl', businessDataModel:project.businessDataModel, messages:messages

if (project.diagrams) {
   newLine()
   section 2, messages.getString('diagrams')
   newLine()
   project.diagrams.each { Diagram diagram -> layout 'process/diagram_template.tpl', diagram:diagram, messages:messages }
}
