/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.groovy.ui.widgets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.model.process.Element;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

/**
 * @author Romain Bioteau
 *
 */
public abstract class AbstractComboElementsProvider implements ComboElementsProvider {

	protected List<SelectionListener> listeners;

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.groovy.ui.widgets.ComboElementsProvider#appliesTo(org.bonitasoft.studio.groovy.ui.widgets.TextOrData)
	 */
	abstract public boolean appliesTo(TextOrData textOrData) ;

	abstract public List<String> getElements(TextOrData textOrData, Element eObject, Collection<EClass> eClasses);

	public List<SelectionListener> getListeners(final TextOrData textOrData) {
		if(listeners == null ){
			listeners = new ArrayList<SelectionListener>();
			listeners.add(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					proceed(textOrData);
				}
			});
		}
		return listeners;
	}

	public void resetListeners() {
		listeners = null ;
	}
	
	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.groovy.ui.widgets.ComboElementsProvider#proceed(org.bonitasoft.studio.groovy.ui.widgets.TextOrData)
	 */
	abstract public String proceed(TextOrData textOrData) ;

}
