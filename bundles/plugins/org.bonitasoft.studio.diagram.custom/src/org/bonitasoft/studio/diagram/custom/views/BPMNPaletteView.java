/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.diagram.custom.views;

import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.eclipse.gef.ui.views.palette.PalettePage;
import org.eclipse.gef.ui.views.palette.PaletteView;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.part.PageBookView;

/**
 * @author Romain Bioteau
 *
 */
public class BPMNPaletteView extends PaletteView {

    public static final String ID = "org.bonitasoft.studio.bpmn.palette_view"; //$NON-NLS-1$
    private IPerspectiveListener perspectiveListener = new IPerspectiveListener() {
        public void perspectiveChanged(IWorkbenchPage page,
                IPerspectiveDescriptor perspective, String changeId) {
        }

        // fix for bug 109245 and 69098 - fake a partActivated when the
        // perpsective is switched
        public void perspectiveActivated(IWorkbenchPage page,
                IPerspectiveDescriptor perspective) {
            viewInPage = page.findViewReference(ID) != null;
            // getBootstrapPart could return null; but isImportant() can handle
            // null
            partActivated(getBootstrapPart());
        }
    };

    private boolean viewInPage = true;

    @Override
    protected PageRec doCreatePage(IWorkbenchPart part) {
        if(part instanceof ProcessDiagramEditor){
            // Try to get a custom palette page
            Object obj = part.getAdapter(PalettePage.class);

            if (obj != null && obj instanceof IPage) {
                PageBook pageBook = super.getPageBook();
                if(!pageBook.isDisposed()){
                    return super.doCreatePage(part);
                }
            }
        }
        // Use the default page by returning null
        return null;
    }

    /**
     * Add a perspective listener so the palette view can be updated when the
     * perspective is switched.
     * 
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
        super.createPartControl(parent);
        getSite().getPage().getWorkbenchWindow()
        .addPerspectiveListener(perspectiveListener);
    }

    /**
     * Remove the perspective listener.
     * 
     * @see org.eclipse.ui.IWorkbenchPart#dispose()
     */
    public void dispose() {
        getSite().getPage().getWorkbenchWindow()
        .removePerspectiveListener(perspectiveListener);
        super.dispose();
    }

    /**
     * Only editors in the same perspective as the view are important.
     * 
     * @see PageBookView#isImportant(org.eclipse.ui.IWorkbenchPart)
     */
    protected boolean isImportant(IWorkbenchPart part) {
        // Workaround for Bug# 69098 -- This should be removed when/if Bug#
        // 70510 is fixed
        // We only want a palette page to be created when this view is visible
        // in the current
        // perspective, i.e., when both this view and the given editor are on
        // the same page.
        return viewInPage  && part instanceof IEditorPart;
    }
}
