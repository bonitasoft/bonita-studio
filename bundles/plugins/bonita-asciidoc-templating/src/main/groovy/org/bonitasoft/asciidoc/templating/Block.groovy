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
 * Build an asciidoc block
 */
class Block {

    /**
     * The list of properties for this block. (eg: source, listing, sidebar...)
     */
    List<String> properties
    
    String caption
    
    String content

    @Override
    public String toString() {
        def blockString = ''<<''
        if(caption) {
            blockString << ".$caption"
            blockString << System.lineSeparator()
        }
        if(properties) {
            blockString << "[${properties.join(',')}]"
            blockString << System.lineSeparator()
        }
        blockString << '----'
        blockString << System.lineSeparator()
        blockString << content
        blockString << System.lineSeparator()
        blockString << '----'
        blockString << System.lineSeparator()
        return blockString.toString()
    }
}



