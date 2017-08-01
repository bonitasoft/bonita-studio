/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common;

import org.osgi.framework.Version;

/**
 * @author Romain Bioteau
 */
public class ProductVersion {

    //OEM Variable to be use for redirect urls
    public static final String REDIRECT_URL_PRODUCT_ID = "bos";
    public static final String VERSION_6_0_0_ALPHA = "6.0.0-Alpha";
    public static final String CURRENT_VERSION = "7.5.4";

    public static final String CURRENT_YEAR = "2017";

    public static boolean sameVersion(final String version) {
        return CURRENT_VERSION.equals(version);
    }

    public static boolean sameMinorVersion(final String version) {
        if (version == null) {
            return false;
        }
        final String minor = CURRENT_VERSION.substring(0, CURRENT_VERSION.lastIndexOf("."));
        final String[] split = version.split("\\.");
        String testedVersion = version;
        if (split.length > 2) {
            testedVersion = split[0] + "." + split[1];
        }
        return minor.equals(testedVersion);
    }

    public static boolean canBeMigrated(final String version) {
        if (version == null) {
            return false;
        }
        Version current = new Version("0.0.0");
        try {
            current = Version.parseVersion(version);
        } catch (final IllegalArgumentException e) {

        }
        final Version productVersion = new Version(CURRENT_VERSION);
        return current.compareTo(productVersion) < 0;
    }

    public static boolean canBeImported(final String version) {
        if (version == null) {
            return true;
        }
        Version current = new Version("0.0.0");
        try {
            current = Version.parseVersion(version);
        } catch (final IllegalArgumentException e) {
            return false;
        }
        final Version productVersion = new Version(CURRENT_VERSION);
        return current.compareTo(productVersion) <= 0;
    }

    public static String majorVersion() {
        final Version productVersion = new Version(CURRENT_VERSION);
        return String.format("%s.%s", productVersion.getMajor(), productVersion.getMinor());
    }

}
