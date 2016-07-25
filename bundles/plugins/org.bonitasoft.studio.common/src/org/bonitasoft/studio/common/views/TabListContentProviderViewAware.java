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
package org.bonitasoft.studio.common.views;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabListContentProvider;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyRegistryViewAware;

/**
 * @author Aurelien Pupier
 */
public class TabListContentProviderViewAware extends TabListContentProvider {

	private String viewID;

	public TabListContentProviderViewAware(TabbedPropertyRegistryViewAware registry,
			String viewID) {
		super(registry);
		this.viewID = viewID;
	}

	/**
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {
		Assert.isTrue(inputElement instanceof ISelection);
		return ((TabbedPropertyRegistryViewAware)registry)
		.getTabDescriptors(currentPart, (ISelection) inputElement, viewID);
	}
}
