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
package org.bonitasoft.studio.properties.form.sections.general;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.DescriptionPropertySectionContribution;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.properties.form.sections.general.contributions.FormFielTypeSelectionGridPropertySectionContribution;
import org.bonitasoft.studio.properties.form.sections.general.contributions.NameGridPropertySectionContribution;
import org.bonitasoft.studio.properties.form.sections.general.contributions.ShowLabelGridPropertySectionContribution;
import org.bonitasoft.studio.properties.form.sections.general.contributions.ShowPageLabelContribution;
import org.bonitasoft.studio.properties.form.sections.general.contributions.TemplateGridPropertySectionContribution;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * @author Aurelien Pupier
 * 
 */
public class GeneralPropertySection extends ExtensibleGridPropertySection {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection
	 * #addContributions()
	 */
	@Override
	protected void addContributions() {

		FormFielTypeSelectionGridPropertySectionContribution activityTypeContrib = new FormFielTypeSelectionGridPropertySectionContribution(getTabbedPropertySheetPage());
		activityTypeContrib.addTypeChangedListener(new Listener() {
			public void handleEvent(Event event) {
				getTabbedPropertySheetPage().selectionChanged(getPart(), new StructuredSelection(event.data));
			}
		});

		IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
				"org.bonitasoft.studio.common.properties.contribution");

		List<IExtensibleGridPropertySectionContribution> contribs = new ArrayList<IExtensibleGridPropertySectionContribution>();
		
		for (IConfigurationElement elem : elements) {
			if (elem.getAttribute("contributeTo").equals(this.getClass().getName())) {
				try {
					contribs.add((IExtensibleGridPropertySectionContribution) elem.createExecutableExtension("class"));
				} catch (CoreException e) {
					BonitaStudioLog.error(e);
				}
			}
		}

		addContribution(new NameGridPropertySectionContribution(getTabbedPropertySheetPage(), this) );
		addContribution(new ShowLabelGridPropertySectionContribution());
		addContribution(new ShowPageLabelContribution());
		addContribution(new DescriptionPropertySectionContribution());
		addContribution(activityTypeContrib);
		addContribution(new TemplateGridPropertySectionContribution());
		for (IExtensibleGridPropertySectionContribution contrib : contribs) {
			addContribution(contrib);
		}
	}


	@Override
	public String getSectionDescription() {
		return null;
	}


	
}
