/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.repository.ui.viewer;

import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.provider.FileStoreLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Baptiste Mesta
 * 
 */
public class RepositoryTreeViewer extends TreeViewer {

    /**
     * @param parent
     * @param style
     */
    public RepositoryTreeViewer(Composite parent, int style) {
        super(parent, style | SWT.VIRTUAL);
        initialize();
    }

    /**
     * 
     */
    protected void initialize() {
        setLabelProvider(new FileStoreLabelProvider());
        setContentProvider(new RepositoryTreeContentProvider());
        addFilter(new ViewerFilter() {

            @Override
            public boolean select(Viewer arg0, Object parentElement, Object element) {
                if(element instanceof IRepositoryStore){
                    ITreeContentProvider provider = (ITreeContentProvider) getContentProvider() ;
                    return provider.hasChildren(element) ;
                }
                return true;
            }
        }) ;
    }

}
