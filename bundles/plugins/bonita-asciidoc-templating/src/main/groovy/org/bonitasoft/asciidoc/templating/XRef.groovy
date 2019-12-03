package org.bonitasoft.asciidoc.templating

import groovy.transform.CompileStatic

abstract class XRef {

    /**
     * @return an Asciidoc inlined cross reference tag
     */
    def String inlinedRefTag() {
        "[[${getId()},${getLabel()}]]"
    }

    /**
     * @return an Asciidoc cross reference link
     */
    def String refLink() {
        "<<${getId()}>>"
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
        replaceSpaces(id.toLowerCase())
    }

    private def String replaceSpaces(String value) {
        def replacements = {
            if(it == ' ') {
                '-'
            }else {
                null
            }
        }
        value.collectReplacements(replacements)
    }
}
