/**
 * Copyright (C) 2012-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.common;

/**
 * @author Romain Bioteau
 */
public class ModelVersion {

    public static final String VERSION_6_0_0_ALPHA = "6.0.0-Alpha";
    public static final String CURRENT_VERSION = "7.2.0-002";

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

}
