
/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.properties.sections.transientdata;

import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * @author Baptiste Mesta
 *
 */
public class OverviewPageFlowTransientDataSection extends ApplicationTransientDataSection {

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.properties.sections.transientdata.ApplicationTransientDataSection#getTransientDataFeature()
	 */
	@Override
	protected EStructuralFeature getDataFeature() {
		return ProcessPackage.Literals.RECAP_FLOW__RECAP_TRANSIENT_DATA;
	}



	
/* (non-Javadoc)
 * @see org.bonitasoft.studio.data.ui.property.section.AbstractDataSection#isOverViewContext()
 */
@Override
public boolean isOverViewContext() {
	return true;
}
}

