/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.properties;

import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;


/**
 * @author aurelie
 *
 */
public class NamePropertySectionContributionForTest extends AbstractNamePropertySectionContribution {

    /**
     * @param tabbedPropertySheetPage
     * @param extensibleGridPropertySection
     */
    public NamePropertySectionContributionForTest(TabbedPropertySheetPage tabbedPropertySheetPage, ExtensibleGridPropertySection extensibleGridPropertySection) {
        super(tabbedPropertySheetPage, extensibleGridPropertySection);
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#getLabel()
     */
    @Override
    public String getLabel() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#setEObject(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public void setEObject(EObject object) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#setSelection(org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setSelection(ISelection selection) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.AbstractNamePropertySectionContribution#createBinding(org.eclipse.emf.databinding.EMFDataBindingContext)
     */
    @Override
    protected void createBinding(EMFDataBindingContext context) {
        // TODO Auto-generated method stub

    }

}
