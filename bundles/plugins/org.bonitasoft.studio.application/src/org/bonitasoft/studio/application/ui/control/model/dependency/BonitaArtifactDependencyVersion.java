/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.ui.control.model.dependency;

import org.apache.maven.artifact.versioning.ComparableVersion;
import org.bonitasoft.studio.common.ProductVersion;
import org.osgi.framework.Version;

public class BonitaArtifactDependencyVersion implements Comparable<BonitaArtifactDependencyVersion> {

    private String version;
    private String bonitaMinVersion;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBonitaMinVersion() {
        return bonitaMinVersion;
    }

    public void setBonitaMinVersion(String bonitaMinVersion) {
        this.bonitaMinVersion = bonitaMinVersion;
    }

    @Override
    public int compareTo(BonitaArtifactDependencyVersion other) {
        return compareTo(new ComparableVersion(other.getVersion()));
    }

    public int compareTo(ComparableVersion otherVersion) {
        ComparableVersion thisVersion = new ComparableVersion(version);
        return otherVersion.compareTo(thisVersion); // The latest version has the priority
    }

    public boolean isCompatible() {
        Version productVersion = getProductVersion();
        Version minVersion = new Version(bonitaMinVersion);
        return minVersion.compareTo(productVersion) <= 0;
    }

    protected Version getProductVersion() {
        return new Version(ProductVersion.CURRENT_VERSION);
    }

}
