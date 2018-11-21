/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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

import org.bonitasoft.studio.common.perspectives.BonitaPerspectivesUtils;
import org.bonitasoft.studio.common.views.BonitaPropertiesBrowserPage;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.IContributedContentsView;
import org.eclipse.ui.part.IPageBookViewPage;
import org.eclipse.ui.part.PageBookView;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheet;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

/**
 * @author Mickael Istria
 * @author Romain Bioteau
 *         implements IContributedContentsView to have a "better" empty view
 */
public abstract class BonitaPropertiesView extends PropertySheet implements IContributedContentsView {

    @Override
    protected ISaveablePart getSaveablePart() {
        return null;
    }

    @Override
    public boolean isDirtyStateSupported() {
        return false;
    }

    @Override
    protected PageBookView.PageRec doCreatePage(IWorkbenchPart part) {
        // Get a custom property sheet page but not if the part is also a
        // PropertySheet. In this case the child property sheet would
        // accidentally reuse the parent's property sheet page.
        if (part instanceof PropertySheet) {
            return null;
        }
        IPropertySheetPage page;
        /* Use our own PropertySheetPage depending on the view */
        if (part instanceof ProcessDiagramEditor) {
            page = getBonitaPropertiesBrowserPage((ITabbedPropertySheetPageContributor) part);
        } else {
            page = (IPropertySheetPage) Adapters.adapt(part,
                    IPropertySheetPage.class, false);
        }

        if (page != null) {
            if (page instanceof IPageBookViewPage) {
                initPage((IPageBookViewPage) page);
            }
            page.createControl(getPageBook());
            return new PageRec(part, page);
        }

        // Use the default page
        return null;
    }

    protected abstract BonitaPropertiesBrowserPage getBonitaPropertiesBrowserPage(ITabbedPropertySheetPageContributor part);

    @Override
    public IWorkbenchPart getContributingPart() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    }


    @Override
    protected boolean isImportant(IWorkbenchPart part) {
        // Don't interfere with other property views
        String partID = part.getSite().getId();
        boolean isPropertyView = BonitaPerspectivesUtils.getAllPropertiesViews().contains(partID);
        return !isPropertyView && super.isImportant(part) && !(part instanceof BonitaProjectExplorer);
    }


    @Override
    protected IWorkbenchPart getBootstrapPart() {
        IWorkbenchPage page = getSite().getPage();
        //TODO: find a cleaner way to initialize the multiple Property views
        /*
         * by setting the focus here to the editor,
         * the getSelection that initialize the bootstrapSelection in the super
         * will return the selection of the editor as we want instead of no selection
         */
        if (page != null && page.getActiveEditor() != null) {
            page.getActiveEditor().setFocus();
        }
        return super.getBootstrapPart();
    }

}
