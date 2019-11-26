@Field Project project

section 1, project.name
write "$project.author${project.email ? " <$project.email>": ''}"
newLine()
write "v$project.version, {docdate}"
newLine()
var 'toc'
var 'toc-title', 'Table of contents'
var 'toclevels', 2
var 'bonita-version', project.bonitaVersion
var 'imagesdir', './doc/images'
var 'sectnums', 'numbered'
var 'sectanchors'

newLine()


if (project.businessDataModel) {
    layout 'bdm/businessDataModel_template.tpl', businessDataModel:project.businessDataModel
}