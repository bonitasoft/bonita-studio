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
package org.bonitasoft.studio.simulation.properties.sections;

import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.model.simulation.SimulationActivity;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.bonitasoft.studio.simulation.properties.contributions.ContigousContribution;
import org.bonitasoft.studio.simulation.properties.contributions.EstimatedTimeContribution;
import org.bonitasoft.studio.simulation.properties.contributions.ExclusiveOutgoingTransitionContribution;
import org.bonitasoft.studio.simulation.properties.contributions.ExecutionTimeContribution;
import org.bonitasoft.studio.simulation.properties.contributions.MaximumProcessTimeContribution;
import org.bonitasoft.studio.simulation.properties.contributions.MaximumTimeContribution;

/**
 * @author Aurelien Pupier
 */
public class GeneralExtensibleGridPropertySection extends
		ExtensibleGridPropertySection {

	@Override
	protected void addContributions() {
		addContribution(new MaximumProcessTimeContribution());
		addContribution(new ExclusiveOutgoingTransitionContribution());
		addContribution(new ContigousContribution());
		addContribution(new ExecutionTimeContribution());
		addContribution(new EstimatedTimeContribution());
		addContribution(new MaximumTimeContribution());
	}

	

	@Override
	public String getSectionDescription() {
		if(eObject instanceof SimulationActivity){
			return Messages.simulationTaskGeneralDescription;
		}
		return Messages.simulationProcessGeneralDescription;
	}



}
