/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.maven.model;

import org.bonitasoft.studio.common.ui.PlatformUtil;

public class BonitaCommonDependency extends MavenDependency {

    public BonitaCommonDependency() {
        super(groupId(), artifactId(), null);
    }

    private static String groupId() {
        return PlatformUtil.isACommunityBonitaProduct() ? "org.bonitasoft.engine" : "com.bonitasoft.engine";
    }

    private static String artifactId() {
        return PlatformUtil.isACommunityBonitaProduct() ? "bonita-common" : "bonita-common-sp";
    }

}
