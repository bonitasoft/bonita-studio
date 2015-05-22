/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.views;

import java.util.Iterator;

import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.properties.views.PropertiesBrowserPage;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyRegistryViewAware;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

/**
 * @author Aurelien Pupier
 */
public abstract class BonitaPropertiesBrowserPage extends PropertiesBrowserPage {

    public BonitaPropertiesBrowserPage(
            final ITabbedPropertySheetPageContributor contributor) {
        super(contributor);
    }

    /**
     * Returns the last known selected tab for the given input.
     */
    @Override
    protected int getLastTabSelection(final IWorkbenchPart part, final ISelection input) {
        if (registry instanceof TabbedPropertyRegistryViewAware) {
            final ITabDescriptor[] descriptors = ((TabbedPropertyRegistryViewAware) registry).getTabDescriptors(part, input, getViewID());
            if (descriptors.length != 0) {
                for (final Iterator iter = selectionQueue.iterator(); iter.hasNext();) {
                    final String text = (String) iter.next();
                    for (int i = 0; i < descriptors.length; i++) {
                        if (text.equals(descriptors[i].getLabel())) {
                            return i;
                        }
                    }
                }
            }
            return defaultSelectedTabIndex(input);
        } else {
            return super.getLastTabSelection(part, input);
        }
    }

    protected int defaultSelectedTabIndex(final ISelection input) {
        return 0;
    }

    @Override
    protected void setInput(final IWorkbenchPart part, final ISelection selection) {
        if (registry instanceof TabbedPropertyRegistryViewAware) {
            if (selection.equals(currentSelection)) {
                return;
            }

            currentSelection = selection;

            // see if the selection provides a new contributor
            validateRegistry(selection);
            if (part instanceof DiagramEditor) {
                final ITabDescriptor[] descriptors = ((TabbedPropertyRegistryViewAware) registry).getTabDescriptors(part,
                        currentSelection, getViewID());
                // If there are no descriptors for the given input we do not need to
                // touch the tab objects. We might reuse them for the next valid
                // input.
                if (descriptors.length > 0) {
                    updateTabs(descriptors);
                }
                // update tabs list
                tabbedPropertyViewer.setInput(part, currentSelection);
                final int lastTabSelectionIndex = getLastTabSelection(part, currentSelection);
                final Object selectedTab = tabbedPropertyViewer
                        .getElementAt(lastTabSelectionIndex);
                selectionQueueLocked = true;
                try {
                    if (selectedTab == null) {
                        tabbedPropertyViewer.setSelection(null);
                    } else {
                        tabbedPropertyViewer.setSelection(new StructuredSelection(
                                selectedTab));
                    }
                } finally {
                    selectionQueueLocked = false;
                }
                refreshTitleBar();
            }
        } else {
            super.setInput(part, selection);
        }
    }

    /**
     * Gets the tab list content provider for the contributor.
     * 
     * @return the tab list content provider for the contributor.
     */
    @Override
    protected IStructuredContentProvider getTabListContentProvider() {
        if (registry instanceof TabbedPropertyRegistryViewAware) {
            return new TabListContentProviderViewAware((TabbedPropertyRegistryViewAware) registry, getViewID());
        } else {
            return super.getTabListContentProvider();
        }
    }

    protected abstract String getViewID();

}
