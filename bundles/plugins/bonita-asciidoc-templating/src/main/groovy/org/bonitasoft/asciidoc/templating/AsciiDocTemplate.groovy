/**
 * Copyright (C) 2019 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.asciidoc.templating

import groovy.text.markup.BaseTemplate
import groovy.text.markup.DelegatingIndentWriter
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

@CompileStatic
@InheritConstructors
abstract class AsciiDocTemplate extends BaseTemplate implements UnicodeCharacters {

    /**
     * Strips the package name of a qualifiedName
     * @param qualifiedName
     * @return a simple class name
     */
    def String toSimpleName(String qualifiedName) {
        qualifiedName?.contains('.') ? qualifiedName.split("\\.").inject("") { s1, s2 ->  s2 } : qualifiedName
    }

    /**
     * Write an Asciidoc section title in the document
     * @param level, the section level
     * @param title, the section title
     */
    def section(int level = 1, Object title) {
        yieldUnescaped "${ '='*level } ${title?.toString()}"
        newLine()
    }

    /**
     * Inject a variable declaration in the asciidoc document (eg: :myVar: someValue)
     * @param varName the name of the variable
     * @param defaultValue the value for this varaible
     */
    def var(Object varName, Object defaultValue = '') {
        yieldUnescaped ":${varName?.toString()}:${defaultValue? " $defaultValue" : ''}"
        newLine()
    }
    
    
    /**
     * Write text into the asciidoc document.
     * @param boolean keepIndent, optional, false by default. When false each indented line of the text parameter is stripped. When true, indent is left as is.
     * @param Object text, the text to inject in the document
     */
    def write(boolean keepIndent = false, Object text) {
        if(keepIndent) {
            yieldUnescaped text
        }else {
            boolean endsWithNewLine = text?.toString()?.endsWith(System.lineSeparator())
            def lineCount = text?.toString().split(System.lineSeparator()).length
            text?.toString().eachLine{ String line, int lineNumber ->
                   yieldUnescaped line.stripIndent()
                   def lastLine =  lineNumber+1 == lineCount
                   if(!lastLine || (lastLine && endsWithNewLine)) {
                       newLine()
                   }
            }
        }
    }
    
    private String asciidocLineBreaks(final Object text) {
        text.toString().denormalize().replaceAll(System.lineSeparator(), ' + '+ System.lineSeparator())
    }
    
    /**
     * Write text into the asciidoc document with an indent
     * @param int indentCount, optional, 1 by default (1 indent = 4 spaces). The number of indent used as prefix
     * @param Object text, the text to inject in the document
     */
    def writeIndent(int indentCount = 1, Object text) throws IOException {
        indentCount.times { yieldUnescaped DelegatingIndentWriter.SPACES }
        write text
    }
    
    /**
     * Write text into the asciidoc document. Replaces line breaks with asciidoc line breaks.
     * @param boolean keepIndent, optional, false by default. When false each indented line of the text parameter is stripped. When true, indent is left as is.
     * @param Object text, the text to inject in the document
     */
    def writeWithLineBreaks(boolean keepIndent = false, Object text) {
        write(keepIndent, asciidocLineBreaks(text))
    }
    
    @Override
    public BaseTemplate comment(Object cs) throws IOException {
        out.write("////");
        newLine()
        out.write(cs.toString());
        newLine()
        out.write("////");
        newLine()
        return this;
    }

    @Override
    public Object methodMissing(String tagName, Object args) throws IOException {
        throw new RuntimeException("Undefined method '$tagName' with args $args")
    }
   
}
