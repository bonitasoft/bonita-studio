/**
 * Copyright (C) 2010-2015 Bonitasoft S.A.
 * Bonitasoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.sections.forms;

import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;


/**
 * @author Aurelien Pupier
 */
public class OverviewFormsSection extends AbstractFormsSection {

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.properties.sections.forms.AbstractFormsSection#getFormFeature()
	 */
	@Override
	protected EReference getPageFlowFormFeature() {
		return ProcessPackage.Literals.RECAP_FLOW__RECAP_FORMS;
	}

	/**
	 * @return
	 */
	@Override
	protected EReference getPageFlowTransitionsFeature() {
		return ProcessPackage.Literals.RECAP_FLOW__RECAP_PAGE_FLOW_TRANSITIONS;
	}

	/**
	 * @return
	 */
	@Override
	protected EAttribute getPageFlowTypeFeature() {
		return ProcessPackage.Literals.RECAP_FLOW__RECAP_PAGE_FLOW_TYPE;
	}

	/**
	 * @return
	 */
	@Override
	protected EReference getPageFlowRedirectionURLFeature() {
		return ProcessPackage.Literals.RECAP_FLOW__RECAP_PAGE_FLOW_REDIRECTION_URL;
	}

	@Override
	public String getSectionDescription() {
        return org.bonitasoft.studio.common.Messages.deprecatedLegacyMode;
	}
	
	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection#getDescriptionSeverity()
	 */
	@Override
	protected int getDescriptionSeverity() {
	    return IStatus.WARNING;
	}
}
