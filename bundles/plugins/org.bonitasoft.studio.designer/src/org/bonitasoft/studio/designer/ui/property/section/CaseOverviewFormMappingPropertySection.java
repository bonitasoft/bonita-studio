/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.ui.property.section;

import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * @author Romain Bioteau
 */
public class CaseOverviewFormMappingPropertySection extends EntryFormMappingPropertySection {

    @Override
    protected EReference getFormMappingFeature() {
        return ProcessPackage.Literals.RECAP_FLOW__OVERVIEW_FORM_MAPPING;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection#getSectionDescription()
     */
    @Override
    public String getSectionDescription() {
        return Messages.caseOverviewFormMappingDescription;
    }

}
