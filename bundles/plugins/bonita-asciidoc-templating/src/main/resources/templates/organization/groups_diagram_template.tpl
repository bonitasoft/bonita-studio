package organization

@Field Organization organization

write '@startuml'
newLine()
write '!pragma graphviz_dot jdot'
2.times { newLine() }

createGroups(organization.groups)

write '@enduml'
newLine()

def createGroups(Object groups) {
    groups.each{ Group group ->
        
            write "object $group.name"
            
            if(group.description || group.displayName) {
                write '{'
                
                newLine()
                
                if(group.displayName) {
                    writeIndent 1, group.displayName
                    newLine()
                }
                
                if(group.description) {
                    wrap(group.description, 50).eachLine {  String line ->
                        writeIndent 1, line
                        newLine()
                    }
                }
                
                write '}'
            }
            
            newLine()
            
            if(group.subGroups) {
                group.subGroups.each { Group subGroup ->
                    write "$group.name -- $subGroup.name"
                    newLine()
                }
                newLine()
                createGroups(group.subGroups)
            }else {
                newLine()
            }
        }
}
