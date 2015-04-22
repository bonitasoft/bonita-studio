/**
 * Copyright (C) 2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.connectors.ui.property.section;

import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class ConnectorEventFilter extends ViewerFilter {

	private String event;
	
	public ConnectorEventFilter(String event) {
		Assert.isTrue(event.equals(ConnectorEvent.ON_ENTER.toString())||event.equals(ConnectorEvent.ON_FINISH.toString()));
		this.event = event;
	}
	
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if ((element instanceof Connector) && ((Connector)element).getEvent().equals(event)){
			return true;
		}
		return false;
	}

}
