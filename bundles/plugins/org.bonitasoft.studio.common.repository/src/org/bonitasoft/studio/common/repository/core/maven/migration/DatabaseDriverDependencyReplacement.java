/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.maven.migration;

import org.apache.maven.model.Dependency;

public abstract class DatabaseDriverDependencyReplacement extends BonitaJarDependencyReplacement {

    private String legacyJarName;

    public DatabaseDriverDependencyReplacement(Dependency mavenDependency, String legacyJarName) {
        super(mavenDependency, legacyJarName);
        this.legacyJarName = legacyJarName;
    }
    
    abstract public String getReplacementJarName();

    public String getFileName() {
        return legacyJarName;
    }
    
}
