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
package org.bonitasoft.studio.common.repository.extension.update;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.common.Strings;

public class DependencyUpdate {

    private Dependency currentDependency;
    private Dependency updatedDependency;
    private boolean rename;

    public DependencyUpdate(Dependency currentDependency, String newVersion) {
        this.currentDependency = currentDependency;
        if (!Strings.isNullOrEmpty(newVersion)) {
            this.updatedDependency = currentDependency.clone();
            this.updatedDependency.setVersion(newVersion);
            this.rename = false;
        }
    }

    public DependencyUpdate(Dependency currentDependency, Dependency updatedDependency, boolean rename) {
        this.currentDependency = currentDependency;
        this.updatedDependency = updatedDependency;
        this.rename = rename;
    }

    public Dependency getCurrentDependency() {
        return currentDependency;
    }

    public Dependency getUpdatedDependency() {
        return updatedDependency;
    }

    public boolean isRename() {
        return rename;
    }
}
