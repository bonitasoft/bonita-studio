/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.importer.bpmn;

import org.bonitasoft.studio.importer.ImporterFactory;
import org.bonitasoft.studio.importer.processors.ToProcProcessor;

/**
 * @author Mickael Istria
 */
public class BPMNToProcFactory extends ImporterFactory {

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.ImporterFactory#appliesTo(java.lang.String, java.io.InputStream)
     */
    public boolean appliesTo(final String resourceName) {
        return resourceName.endsWith(".bpmn") || resourceName.endsWith(".xml");
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.ImporterFactory#createProcessor(java.lang.String)
     */
    @Override
    public ToProcProcessor createProcessor(final String resourceName) {
        return new BPMNToProc(resourceName);
    }

}
