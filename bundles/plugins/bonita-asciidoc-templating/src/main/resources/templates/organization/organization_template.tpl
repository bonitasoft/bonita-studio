package organization

@Field Organization organization
@Field ResourceBundle messages

section 2, messages.getString('organization')

newLine()

section 3, messages.getString('groups')

newLine()

write 'image::groups.svg[]'

2.times { newLine() }

if(organization.roles) {
    section 3, messages.getString('roles')
    
    newLine()
    
    write true, new Table(columnName: [messages.getString('name'), messages.getString('displayName'), messages.getString('description')],
                          columnsFormat: ['1','1e','3a'],
                          columms: [
                              organization.roles.name,
                              organization.roles.displayName,
                              organization.roles.description.collect { it ?: ""}
                              ])
    
    newLine()
}

if(organization.profiles) {
    section 3, messages.getString('profiles')
    
    newLine()
    
    write true, new Table(columnName: [messages.getString('name'), messages.getString('description')],
        columnsFormat: ['1e','3a'],
        columms: [
            organization.profiles.name.collect { "${new ProfileXRef(profile: it).inlinedRefTag()}$it" } ,
            organization.profiles.description.collect { it ?: ""}
            ])
    
    newLine()
}