/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.sqlbuilder.ex.wizard;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;


public class ViewerInput extends Viewer {

    private Object input;

    public ViewerInput(Object input) {
        this.input = input;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.Viewer#getControl()
     */
    @Override
    public Control getControl() {
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.Viewer#getInput()
     */
    @Override
    public Object getInput() {
        return input;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.Viewer#getSelection()
     */
    @Override
    public ISelection getSelection() {
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.Viewer#refresh()
     */
    @Override
    public void refresh() {
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.Viewer#setInput(java.lang.Object)
     */
    @Override
    public void setInput(Object input) {
        this.input = input;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.Viewer#setSelection(org.eclipse.jface.viewers.ISelection, boolean)
     */
    @Override
    public void setSelection(ISelection selection, boolean reveal) {

    }

}
