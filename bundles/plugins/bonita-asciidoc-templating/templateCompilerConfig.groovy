withConfig(configuration){
    source(extension : 'tpl') { 
        imports {
            star('org.bonitasoft.asciidoc.templating.model',
                'org.bonitasoft.asciidoc.templating.model.bdm')
            normal(groovy.transform.Field)
        }
    }
}