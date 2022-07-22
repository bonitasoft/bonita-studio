/**
 * Copyright (C) 2015 BonitaSoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.google.common.base.Preconditions;

public class ConnectorEventFilter extends ViewerFilter {

	private final String event;

	public ConnectorEventFilter(final String event) {
		Preconditions
				.checkArgument(event.equals(ConnectorEvent.ON_ENTER.name())
						|| event.equals(ConnectorEvent.ON_FINISH.name()));
		this.event = event;
	}

	@Override
	public boolean select(final Viewer viewer, final Object parentElement,
			final Object element) {
		if (element instanceof Connector
				&& ((Connector) element).getEvent().equals(event)) {
			return true;
		}
		return false;
	}

	protected String getEvent() {
		return event;
	}

}
