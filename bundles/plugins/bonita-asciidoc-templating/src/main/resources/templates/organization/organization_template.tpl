package organization

@Field Organization organization
@Field ResourceBundle messages

section 2, messages.getString('organization')

newLine()

section 3, messages.getString('groups')

newLine()

write 'image::groups.svg[]'

2.times { newLine() }

section 3, messages.getString('roles')

newLine()

section 3, messages.getString('profiles')

newLine()