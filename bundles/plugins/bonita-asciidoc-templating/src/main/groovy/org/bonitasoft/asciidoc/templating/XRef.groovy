package org.bonitasoft.asciidoc.templating

import java.text.Normalizer

import groovy.transform.CompileStatic

abstract class XRef {

    /**
     * @return an Asciidoc inlined cross reference tag
     */
    def String inlinedRefTag() {
        "[[${getId()}]]"
    }

    /**
     * @return an Asciidoc cross reference link
     */
    def String refLink() {
        "<<${getId()},${getLabel()}>>"
    }

    /**
     * @return The cross reference id
     */
    def abstract String getId()
    
    /**
     * @return The cross reference label
     */
    def abstract String getLabel()

    protected def String crossRefId(String id) {
        normalize(id.toLowerCase())
    }

    private def String normalize(String value) {
	def normalized = Normalizer
        	.normalize(value, Normalizer.Form.NFD)
        	.replaceAll("[^\\p{ASCII}]", "")
	
        def replacements = {
            if(it == ' ') {
                '-'
            }else if(it == '?' 
		|| it == '*' 
		||  it == '\\' 
		||  it == '$' 
		||  it == '!' 
		||  it == '|' 
		||  it == ';'
		||  it == '%'
		||  it == '"'
		||  it == '\''
		||  it == '`' ) {
                ''
            }else {
                null
            }
        }
        normalized.collectReplacements(replacements)
    }
}
