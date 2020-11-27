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
package org.bonitasoft.studio.tests;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import junit.framework.TestCase;

/**
 * @author Mickael Istria
 */
public class TestPatchedBundles extends TestCase {

    String[] PATCHED_BUNDLES = {
            "org.eclipse.ui.views.properties.tabbed",
            "org.eclipse.gmf.runtime.lite.svg",
            "org.eclipse.emf.edapt.migration",
            "org.eclipse.datatools.modelbase.sql.query"
    };

    public void testPatchedBundles() throws Exception {
        boolean isOk = true;
        final StringBuilder sb = new StringBuilder();
        for (final String bundleName : PATCHED_BUNDLES) {
            final Bundle bundle = Platform.getBundle(bundleName);
            assertNotNull(bundleName + " not found in platform", bundle);
            final String vendorName = bundle.getHeaders().get("Bundle-Vendor");
            final boolean isCurrentBundleOk = vendorName.contains("Bonitasoft");
            isOk = isOk && isCurrentBundleOk;
            if (!isCurrentBundleOk) {
                sb.append("Bundle not patched: ");
                sb.append(bundleName);
                sb.append(" vendorname: ");
                sb.append(vendorName);
                sb.append("\n");
            }
        }
        assertTrue(sb.toString(), isOk);
    }

}
