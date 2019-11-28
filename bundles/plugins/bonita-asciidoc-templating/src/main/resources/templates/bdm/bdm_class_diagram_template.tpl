package bdm

@Field BusinessDataModel businessDataModel

write '@startuml'
newLine()
write '!pragma graphviz_dot jdot'
2.times { newLine() }

businessDataModel.packages.each{ Package pckg ->

    write "package $pckg.name {"
    2.times { newLine() }

    pckg.businessObjects.each{  BusinessObject businessObject ->

        if(businessObject.description) {
            writeIndent "note top of $businessObject.name : "
            write groovy.json.StringEscapeUtils.escapeJava(businessObject.description)
            newLine()
        }

        writeIndent "class $businessObject.name {"
        newLine()

        businessObject.attributes.each{ Attribute attribute ->
            writeIndent 2, "+$attribute.name : ${attribute.multiple ? "List<$attribute.type>" : attribute.type}"
            newLine()
        }

        if(businessObject.customQueries) {
            writeIndent 2, '== Custom queries =='
            newLine()

            businessObject.customQueries.each{ Query query ->
                def parameterSignature = query.parameters.collect{ QueryParameter parameter -> "$parameter.type $parameter.name" }.join(", ")
                writeIndent 2, "+${query.name}($parameterSignature) : $query.returnType"
                newLine()
            }
        }

        writeIndent '}'
        2.times { newLine() }
        businessObject.relations.each { Relation relation ->
            writeIndent "$businessObject.name  ${relation.relationType == 'COMPOSITION' ? '"composed of"' :  '"references"'} ${relation.relationType == 'COMPOSITION' ? '*' :  'o'}-- ${relation.multiple ? '"*"' : ''} $relation.type : $relation.name"
            newLine()
        }
    }
    
    newLine()
    write '}'
    2.times { newLine() }
}

write '@enduml'
newLine()
