/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.contract.core.mapping.operation;

import org.codehaus.groovy.eclipse.refactoring.formatter.IFormatterPreferences;

public class DefaultFormatterPreferences implements IFormatterPreferences {

    /*
     * (non-Javadoc)
     * @see org.codehaus.groovy.eclipse.refactoring.formatter.IFormatterPreferences#getBracesEnd()
     */
    @Override
    public int getBracesEnd() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * @see org.codehaus.groovy.eclipse.refactoring.formatter.IFormatterPreferences#getBracesStart()
     */
    @Override
    public int getBracesStart() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * @see org.codehaus.groovy.eclipse.refactoring.formatter.IFormatterPreferences#getIndentationMultiline()
     */
    @Override
    public int getIndentationMultiline() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * @see org.codehaus.groovy.eclipse.refactoring.formatter.IFormatterPreferences#getIndentationSize()
     */
    @Override
    public int getIndentationSize() {
        return 4;
    }

    /*
     * (non-Javadoc)
     * @see org.codehaus.groovy.eclipse.refactoring.formatter.IFormatterPreferences#getLongListLength()
     */
    @Override
    public int getLongListLength() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * @see org.codehaus.groovy.eclipse.refactoring.formatter.IFormatterPreferences#getMaxLineLength()
     */
    @Override
    public int getMaxLineLength() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * @see org.codehaus.groovy.eclipse.refactoring.formatter.IFormatterPreferences#getTabSize()
     */
    @Override
    public int getTabSize() {
        return 4;
    }

    /*
     * (non-Javadoc)
     * @see org.codehaus.groovy.eclipse.refactoring.formatter.IFormatterPreferences#isIndentEmptyLines()
     */
    @Override
    public boolean isIndentEmptyLines() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.codehaus.groovy.eclipse.refactoring.formatter.IFormatterPreferences#isRemoveUnnecessarySemicolons()
     */
    @Override
    public boolean isRemoveUnnecessarySemicolons() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.codehaus.groovy.eclipse.refactoring.formatter.IFormatterPreferences#isSmartPaste()
     */
    @Override
    public boolean isSmartPaste() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.codehaus.groovy.eclipse.refactoring.formatter.IFormatterPreferences#useTabs()
     */
    @Override
    public boolean useTabs() {
        return true;
    }

}
