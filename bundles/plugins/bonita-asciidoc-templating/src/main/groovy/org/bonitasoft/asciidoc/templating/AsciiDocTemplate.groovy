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
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

@CompileStatic
@InheritConstructors
abstract class AsciiDocTemplate extends BaseTemplate {

    /**
     * Generate an asciidoc table
     * @param headers the list of headers for this table eg: ['Key','Value']
     * @param content the content values of the table as a list of list eg: [['keyRow1Col1','keyRow2Col1'],['valueRow1Col1','valueRow2Col1'],...]
     * @return a string representing an asciidoc table
     */
    def AsciiDocTemplate table(List<String> headers, List<List<String>> content) {
        def tableContent = """*${headers ? '[options="header"]' : ''}
                          *|===
                          *""".stripMargin('*')

        if(headers) {
            def headerTable = headers.withIndex().collect { String header, int index ->  cellPadding(header,content[index],header.length()) }.join("|")
            tableContent = tableContent + '|' + headerTable + '\n'
        }
        if(!headers) {
            headers = content.collect { '' }
        }
        content[0].withIndex().collect { String v, int rowIndex ->
            def row = content.withIndex().collect{ List<String> rows, int index ->  cellPadding( rows[rowIndex], content[index] , headers[index].length())}.join("|")
            tableContent = tableContent + """*|$row
                                             *""".stripMargin('*')
        }

        tableContent = tableContent + '''*|===
                                         *'''.stripMargin('*')
        yieldUnescaped tableContent.toString()
        return this
    }

    /**
     * Strips the package name of a qualifiedName
     * @param qualifiedName
     * @return a simple class name
     */
    def String toSimpleName(String qualifiedName) {
        qualifiedName?.contains('.') ? qualifiedName.split("\\.").inject("") { s1, s2 ->  s2 } : qualifiedName
    }

    private static String cellPadding(Object value, List values, int minSize) {
        def int valueSize = value.toString().trim().length()
        def int maxValue = [
            values.collect{ it.toString().trim().length() }.max(),
            minSize
        ].max()
        def int paddingSize = maxValue - valueSize
        "$value${' '*paddingSize}".replace('|','\\|')
    }

    def section(int level = 1, Object content) {
        yieldUnescaped "${ '='*level } ${content?.toString()}"
	newLine()
    }

    def author(String author) {
        var('author', author)
    }

    def date(String date) {
        var('date', date)
    }

    def revision(String revision) {
        var('revision', revision)
    }

    def email(String email) {
        var('Email', "<$email>")
    }

    /**
     * Inject a variable declaration in the asciidoc document (eg: :myVar: someValue)
     * @param varName the name of the variable
     * @param defaultValue the value for this varaible
     */
    def var(Object varName, Object defaultValue = '') {
        yieldUnescaped ":${varName?.toString()}: ${defaultValue?.toString()}"
	newLine()
    }
    
    
    /**
     * Write text into the asciidoc document.
     */
    def write(Object content) {
        yieldUnescaped content?.toString().split('\n').collect{ it?.toString().stripIndent() }.join('\n')
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
