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
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.IContributedContentsView;
import org.eclipse.ui.part.IPage;
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
            page = (IPropertySheetPage) Adapters.adapt(part, IPropertySheetPage.class);
        }
        if (page != null) {
            if (page instanceof IPageBookViewPage) {
                initPage((IPageBookViewPage) page);
            }
            page.createControl(getPageBook());
            page.selectionChanged(part, part.getSite().getPage().getActiveEditor().getSite().getSelectionProvider().getSelection());
            return new PageRec(part, page);
        }

        // IContributedContentsView without contributed view, show default page
        IContributedContentsView view = Adapters.adapt(part, IContributedContentsView.class);
        if (view != null && view.getContributingPart() == null) {
            return null;
        }

        // Only if a part is a selection provider, it could have properties for the
        // default PropertySheetPage. Every part gets its own PropertySheetPage
        ISelectionProvider provider = part.getSite().getSelectionProvider();
        if (provider != null) {
            IPage dPage = createPropertySheetPage(getPageBook());
            return new PageRec(part, dPage);
        }

        // No properties to be shown, use the default page
        return null;
    }

    protected abstract BonitaPropertiesBrowserPage getBonitaPropertiesBrowserPage(
            ITabbedPropertySheetPageContributor part);

    @Override
    public IWorkbenchPart getContributingPart() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    }
    
    @Override
    protected IWorkbenchPart getBootstrapPart() {
        return getContributingPart();
    }

    @Override
    protected boolean isImportant(IWorkbenchPart part) {
        // Don't interfere with other property views
        String partID = part.getSite().getId();
        boolean isPropertyView = BonitaPerspectivesUtils.getAllPropertiesViews().contains(partID);
        return !isPropertyView && super.isImportant(part) && !(part instanceof BonitaProjectExplorer);
    }

}
