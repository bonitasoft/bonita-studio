/**
 * Copyright (C) 2011-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.properties.bos.sections.data.DataSectionBOS;

import java.util.Set;

import org.bonitasoft.studio.data.ui.property.section.AbstractDataSection;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Aurelien Pupier
 */
public class DataSectionBOS extends AbstractDataSection {

    @Override
    protected Set<EStructuralFeature> getDataFeatureToCheckUniqueID() {
        final Set<EStructuralFeature> res = super.getDataFeatureToCheckUniqueID();
        res.add(ProcessPackage.Literals.RECAP_FLOW__RECAP_TRANSIENT_DATA);
        res.add(ProcessPackage.Literals.PAGE_FLOW__TRANSIENT_DATA);
        res.add(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA);
        return res;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.data.ui.property.section.AbstractDataSection#createViewerHeaderLabel(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createViewerHeaderLabel(final Composite parent) {
        //DO NOTHING
    }

}
