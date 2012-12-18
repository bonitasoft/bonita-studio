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
package org.bonitasoft.studio.groovy.ui.widgets;

import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.model.process.Element;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.swt.events.SelectionListener;

/**
 * @author Mickael Istria
 *
 */
public interface ComboElementsProvider {

	/**
	 * @param textOrData
	 * @return
	 */
	boolean appliesTo(TextOrData textOrData);

	/**
	 * @param textOrData 
	 * @param eObject
	 * @return
	 */
	public List<String> getElements(TextOrData textOrData, Element eObject, Collection<EClass> eClasses);

	/**
	 * @param textOrData 
	 * @return
	 */
	public List<SelectionListener> getListeners(TextOrData textOrData);

	
	public String proceed( TextOrData textOrData );

	public void resetListeners();

}
