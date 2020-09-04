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
package org.bonitasoft.studio.common.gmf.tools.tree.selection;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class RefreshPropertyViewsSelection implements Runnable {

    private final IWorkbenchPart part;
    private final ISelection newSelection;

    public RefreshPropertyViewsSelection(final IWorkbenchPart part, final ISelection newSelection) {
        this.part = part;
        this.newSelection = newSelection;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        for (final IViewReference vr : viewReferences()) {
            final TabbedPropertySheetPage propertyPage = (TabbedPropertySheetPage) vr.getView(true).getAdapter(TabbedPropertySheetPage.class);
            if (propertyPage != null) {
                propertyPage.selectionChanged(part, newSelection);
            }
        }
    }

    protected IViewReference[] viewReferences() {
        return part.getSite().getPage().getViewReferences();
    }

}
