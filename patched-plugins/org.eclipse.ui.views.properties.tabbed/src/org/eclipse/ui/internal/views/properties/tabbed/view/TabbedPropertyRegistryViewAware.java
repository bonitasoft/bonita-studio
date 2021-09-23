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
package org.eclipse.ui.internal.views.properties.tabbed.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ISectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;

/**
 * @since 3.5
 */
public class TabbedPropertyRegistryViewAware extends TabbedPropertyRegistry {

    private static final String ELEMENT_SECTION = "propertySection"; //$NON-NLS-1$
    private static final String EXTPT_SECTIONS = "propertySections"; //$NON-NLS-1$

    /**
     * @param id
     */
    protected TabbedPropertyRegistryViewAware(String id) {
        super(id);
    }

    /**
     * Reads property section extensions. Returns all section descriptors for
     * the current contributor id or an empty array if none is found.
     * 
     * @param viewId
     *        take care in which view it is
     */
    protected ISectionDescriptor[] readSectionDescriptors(String viewId) {
        List<ISectionDescriptor> result = new ArrayList<ISectionDescriptor>();
        IConfigurationElement[] extensions = getConfigurationElements(EXTPT_SECTIONS);
        for (int i = 0; i < extensions.length; i++) {
            IConfigurationElement extension = extensions[i];
            IConfigurationElement[] sections = extension
                    .getChildren(ELEMENT_SECTION);
            for (int j = 0; j < sections.length; j++) {
                IConfigurationElement section = sections[j];
                String sectionViewId = section.getAttribute("viewID"); //$NON-NLS-1$
                if (sectionViewId != null && sectionViewId.equals(viewId)) {
                    ISectionDescriptor descriptor = new SectionDescriptor(section,
                            typeMapper);
                    result.add(descriptor);
                }
            }
        }
        return result.toArray(new ISectionDescriptor[result.size()]);
    }

    /**
     * Populates the given tab descriptors with section descriptors.
     */
    protected void populateWithSectionDescriptors(List<?> aTabDescriptors, String viewId) {
        ISectionDescriptor[] sections = null;
        if (sectionDescriptorProvider != null) {
            sections = sectionDescriptorProvider.getSectionDescriptors();
        } else {
            sections = readSectionDescriptors(viewId);
        }
        for (int i = 0; i < sections.length; i++) {
            ISectionDescriptor section = sections[i];
            appendToTabDescriptor(section, aTabDescriptors);
        }
    }

    /**
     * Reads property tab extensions. Returns all tab descriptors for the
     * current contributor id or an empty array if none is found.
     */
    protected ITabDescriptor[] getAllTabDescriptors(String viewId) {
        //TODO: we no more use a cached tabDescriptors, might cause issue performance
        //if (tabDescriptors == null) {
        List<TabDescriptor> temp = readTabDescriptors();
        populateWithSectionDescriptors(temp, viewId);
        temp = sortTabDescriptorsByCategory(temp);
        temp = sortTabDescriptorsByAfterTab(temp);
        tabDescriptors = temp
                .toArray(new TabDescriptor[temp.size()]);
        //}
        return tabDescriptors;
    }

    /**
     * Returns all section descriptors for the provided selection.
     * 
     * @param part
     *        the workbench part containing the selection
     * @param selection
     *        the current selection.
     * @param viewID
     *        The view in which we want the get tab
     * @return all section descriptors.
     */
    public ITabDescriptor[] getTabDescriptors(IWorkbenchPart part,
            ISelection selection, String viewID) {
        if (selection == null || selection.isEmpty()) {
            return EMPTY_DESCRIPTOR_ARRAY;
        }

        ITabDescriptor[] allDescriptors = null;
        if (tabDescriptorProvider == null) {
            allDescriptors = getAllTabDescriptors(viewID);
        } else {
            allDescriptors = tabDescriptorProvider.getTabDescriptors(part,
                    selection);
        }

        ITabDescriptor[] result = filterTabDescriptors(allDescriptors, part,
                selection);
        return result;
    }

}
