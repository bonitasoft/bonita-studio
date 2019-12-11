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

/**
 * Build an asciidoc table
 * @param headers the list of headers for this table eg: ['Key','Value']
 * @param columms the columns values of the table as a list of list eg: [['keyRow1Col1','keyRow2Col1'],['valueRow1Col1','valueRow2Col1'],...]
 */
class Table {

    /**
     * The list of column names. Must be used when header is true.
     */
    List<String> columnName;
    /**
     * The list of column values.
     */
    List<List<String>> columms;
    /**
     * Describe how the table border are rendered
     * Possible values are: 'all', 'rows', 'cols' and 'none'
     * Default is 'cols'
     */
    String gridStyle = 'cols'
    /**
     * Describe how the table border are rendered
     * Possible values are: 'even', 'odd', 'hover' (html only), 'all' and 'none'
     * Default is 'even'
     */
    String stripes = 'even'
    /**
     * Describe how the table frame border are rendered
     * Possible values are: 'all', 'topbot', 'sides' and 'none'
     * Default is 'topbot'
     */
    String frame = 'topbot'
    /**
     * Whether a header row should rendered
     * Default is true
     */
    boolean header = true
    /**
     * Whether a footer row should rendered
     * Default is false
     */
    boolean footer = false
    /**
     * Disables explicit column widths
     * Default is false
     */
    boolean autowidth = false
    /**
     * Configure the columns format (size, style, alignment...)
     * @see <a href="https://asciidoctor.org/docs/user-manual/#cols-format">AsciiDoctor columns format</a>
     */
   List<String> columnsFormat = []

    private static String cellPadding(Object value, List values, int minSize) {
        def trimmedValue = value.toString().trim()
        def int valueSize = trimmedValue.length()
        if(trimmedValue.contains(System.lineSeparator())) {
           valueSize = trimmedValue.split(System.lineSeparator()).collect { it.length() }.max()
        }
        def int paddingSize = columnLength(values, minSize) - valueSize
        "$value${' '*paddingSize}".replace('|','\\|')
    }
    
    private static int columnLength(List values, int minSize) {
       return [
            values.collect{
                def trimmedValue = it.toString().trim()
                if(trimmedValue.contains(System.lineSeparator())) {
                    trimmedValue.split(System.lineSeparator()).collect { it.length() }.max()
                }else {
                    trimmedValue.length()
                }
            }.max(),
            minSize
        ].max()
    }

    @Override
    public String toString() {
        def options = []
        if(header) options << 'header'
        if(footer) options << 'footer'
        if(autowidth) options << 'autowidth'
        
        def tableContent = """*[grid=$gridStyle,options="${options.join(',')}",cols="${columnsFormat.join(',')}",stripes=$stripes,frame=$frame]
                          *|===
                          *""".stripMargin('*')

        if(columnName) {
            def headerTable = columnName.withIndex().collect { String header, int index ->  cellPadding(header , columms[index], header.length()) }.join("|")
            tableContent = tableContent + '|' + headerTable + System.lineSeparator()
        }
        if(!columnName) {
            columnName = columms.collect { '' }
        }
        columms[0].withIndex().collect { Object v, int rowIndex ->
            def columnsSizes = []
            def row = columms.withIndex().collect{ List<String> rows, int columnIndex -> 
                def cellValue = cellPadding( rows[rowIndex], columms[columnIndex] , columnName[columnIndex].length())
                if(cellValue.contains(System.lineSeparator())){
                    cellValue = cellValue.replaceAll(System.lineSeparator(), System.lineSeparator()+' '*(columnsSizes.sum() + 1))
                }
                columnsSizes << columnLength(columms[columnIndex], columnName[columnIndex].length()) + 1
                return cellValue
            }.join("|")
            tableContent = tableContent + "*|$row" +  System.lineSeparator()
        }
    
        tableContent = tableContent + '*|===' + System.lineSeparator()
        tableContent.stripMargin('*').denormalize().toString()
    }
}



