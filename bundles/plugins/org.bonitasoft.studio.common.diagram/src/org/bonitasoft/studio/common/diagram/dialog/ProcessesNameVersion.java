/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.diagram.dialog;

import org.bonitasoft.studio.model.process.AbstractProcess;

public class ProcessesNameVersion {

    private final AbstractProcess pool;
    private String newName;
    private String newVersion;

    public ProcessesNameVersion(final AbstractProcess pool) {
        this.pool = pool;
        newName = pool.getName();
        newVersion = pool.getVersion();
    }

    public AbstractProcess getAbstractProcess() {
        return pool;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(final String newName) {
        this.newName = newName;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public void setNewVersion(final String newVersion) {
        this.newVersion = newVersion;
    }
}
