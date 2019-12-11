package process

@Field Connector[] connectors
@Field ResourceBundle messages

connectors.each { Connector connector ->
    if(connector.description) {
        write "$connector.definitionName: $connector.name:: "
        writeWithLineBreaks connector.description
    }else {
        write "*$connector.definitionName: $connector.name*"
    }
    newLine()
}
newLine()