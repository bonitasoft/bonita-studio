/**
 * Copyright (C) 2012 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.views;

import org.eclipse.core.runtime.Adapters;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.DiagramRootTreeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.IPageBookViewPage;
import org.eclipse.ui.views.contentoutline.ContentOutline;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

public class BonitaContentOutlineTreeView extends ContentOutline {

    public static final String VIEW_ID = "org.bonitasoft.studio.views.overview.tree"; //$NON-NLS-1$
    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.views.contentoutline.ContentOutline#doCreatePage(org.eclipse.ui.IWorkbenchPart)
     */

    @Override
    protected PageRec doCreatePage(IWorkbenchPart part) {
        Object obj = Adapters.adapt(part, IContentOutlinePage.class, false);
        if (obj instanceof IContentOutlinePage && part instanceof DiagramEditor) {
            TreeViewer viewer = new TreeViewer();
            viewer.setRootEditPart(new DiagramRootTreeEditPart());
            IContentOutlinePage page = new BonitaTreeOutlinePage(viewer, (DiagramEditor) part);
            if (page instanceof IPageBookViewPage) {
                initPage((IPageBookViewPage) page);
            }
            page.createControl(getPageBook());
            return new PageRec(part, page);
        }
        return null;
    }

    @Override
    public void partClosed(IWorkbenchPart part) {
        if (part instanceof DiagramEditor) {
            super.partClosed(part);
        }
    }

}
