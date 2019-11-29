withConfig(configuration){
    source(extension : 'tpl') { 
        imports {
            star('org.bonitasoft.asciidoc.templating.model',
                 'org.bonitasoft.asciidoc.templating.model.bdm',
                 'org.bonitasoft.asciidoc.templating.model.process',
                 'org.bonitasoft.asciidoc.templating')
            normal(groovy.transform.Field)
        }
    }
}