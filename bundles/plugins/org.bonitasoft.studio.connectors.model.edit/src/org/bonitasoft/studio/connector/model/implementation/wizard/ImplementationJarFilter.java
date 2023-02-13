/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.connector.model.implementation.wizard;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;


/**
 * @author Romain Bioteau
 *
 */
public class ImplementationJarFilter extends ViewerFilter {

    private final ConnectorImplementation implementation;

    public ImplementationJarFilter(final ConnectorImplementation implementation) {
        this.implementation = implementation;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    @Override
    public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
        final String connectorImplementationFilename = NamingUtils.toConnectorImplementationFilename(implementation.getImplementationId(),
                implementation.getImplementationVersion(), false) + ".jar";
        return !connectorImplementationFilename.equals(element);
    }

}
