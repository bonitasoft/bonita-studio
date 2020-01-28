/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
 
package org.bonitasoft.studio.properties.sections.diagram;


import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.properties.sections.general.DescriptionGridPropertySectionContribution;
import org.bonitasoft.studio.properties.sections.general.ProcessElementNameContribution;
import org.bonitasoft.studio.properties.sections.general.VersionGridPropertySectionContribution;


public class DiagramSection extends ExtensibleGridPropertySection {

    @Override
	protected void addContributions() {
		addContribution(new ProcessElementNameContribution(getTabbedPropertySheetPage()));
		addContribution(new VersionGridPropertySectionContribution());
		addContribution(new DescriptionGridPropertySectionContribution());
	}

	@Override
	public String getSectionDescription() {
		return Messages.diagramSectionDescription;
	}


}
