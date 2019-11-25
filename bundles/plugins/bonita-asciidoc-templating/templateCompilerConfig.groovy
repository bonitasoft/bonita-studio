withConfig(configuration){
    source(extension : 'tpl') { 
        imports {
            star('org.bonitasoft.asciidoc.templating.model')
            normal(groovy.transform.Field)
        }
    }
}