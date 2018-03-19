/**
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.emf.tools;

import java.util.Comparator;

import org.apache.maven.artifact.versioning.ComparableVersion;
import org.bonitasoft.studio.model.process.AbstractProcess;

public class ProcessVersionComparator implements Comparator<AbstractProcess> {

    /*
     * (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(AbstractProcess o1, AbstractProcess o2) {
        ComparableVersion v1 = new ComparableVersion(o1.getVersion());
        ComparableVersion v2 = new ComparableVersion(o2.getVersion());
        return v1.compareTo(v2);
    }

}
