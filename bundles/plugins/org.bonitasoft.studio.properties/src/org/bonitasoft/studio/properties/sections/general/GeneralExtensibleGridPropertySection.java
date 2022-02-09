/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.sections.general;

import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.properties.sections.general.activities.ActivityTypeSelectionGridPropertySectionContribution;
import org.bonitasoft.studio.properties.sections.general.events.ErrorEventSectionContribution;
import org.bonitasoft.studio.properties.sections.general.events.MessageEventTypeSelectionGridPropertySectionContribution;
import org.bonitasoft.studio.properties.sections.general.events.SignalEventEventSelectionContribution;
import org.bonitasoft.studio.properties.sections.general.gateway.GatewayTypeSelectionGridPropertySectionContribution;
import org.bonitasoft.studio.properties.sections.general.link.CatchLinkEventSectionContribution;
import org.bonitasoft.studio.properties.sections.general.link.LinkTypeSelectionSectionContribution;
import org.bonitasoft.studio.properties.sections.general.link.ThrowLinkEventSectionContribution;
import org.bonitasoft.studio.properties.sections.general.task.TaskPriorityPropertySection;
import org.bonitasoft.studio.properties.sections.message.CatchEventEventSelectionContribution;
import org.bonitasoft.studio.properties.sections.timer.TimerEventConditionContribution;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Layout;

/**
 * @author Mickael Istria
 */
public class GeneralExtensibleGridPropertySection extends ExtensibleGridPropertySection {

    @Override
    protected void addContributions() {
        addContribution(new ProcessElementNameContribution(getTabbedPropertySheetPage()));
        addContribution(new MessageFlowContribution());
        addContribution(new DescriptionGridPropertySectionContribution(5));
        addContribution(new VersionGridPropertySectionContribution());
        addContribution(new TextAnnotationTextPropertySectionContribution());
        addContribution(new CatchLinkEventSectionContribution());
        addContribution(new ThrowLinkEventSectionContribution());
        addContribution(new LinkTypeSelectionSectionContribution(getTabbedPropertySheetPage()));
        final ActivityTypeSelectionGridPropertySectionContribution activityTypeContrib = new ActivityTypeSelectionGridPropertySectionContribution(
                getTabbedPropertySheetPage());
        addContribution(activityTypeContrib);
        final MessageEventTypeSelectionGridPropertySectionContribution messageEventContrib = new MessageEventTypeSelectionGridPropertySectionContribution(
                getTabbedPropertySheetPage());
        addContribution(messageEventContrib);
        addContribution(new TimerEventConditionContribution());
        addContribution(new CatchEventEventSelectionContribution());
        addContribution(new SignalEventEventSelectionContribution());
        addContribution(new TransitionConditionContribution());
        final GatewayTypeSelectionGridPropertySectionContribution gatewayTypeContrib = new GatewayTypeSelectionGridPropertySectionContribution(
                getTabbedPropertySheetPage());
        addContribution(gatewayTypeContrib);
        addContribution(new TaskPriorityPropertySection());
        addContribution(new ErrorEventSectionContribution());
    }
    
    protected Layout getLayout() {
   	final GridLayout layout = new GridLayout(2, false);
   	layout.verticalSpacing = 2;
   	layout.horizontalSpacing = 10;
   	return layout;
       }

    @Override
    public String getSectionDescription() {
        return Messages.generalPropertiesSectionDescription;
    }

}
