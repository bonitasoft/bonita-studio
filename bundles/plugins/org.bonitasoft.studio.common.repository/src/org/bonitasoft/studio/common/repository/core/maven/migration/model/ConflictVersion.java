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
package org.bonitasoft.studio.common.repository.core.maven.migration.model;

import org.bonitasoft.studio.common.repository.Messages;

public class ConflictVersion {

    private String ourVersion;
    private String theirVersion;
    private Status status;

    public enum Status {
        CONFLICTING, KEEP_OURS, KEEP_THEIR
    }

    public ConflictVersion(String ourVersion, String theirVersion, Status status) {
        this.ourVersion = ourVersion;
        this.theirVersion = theirVersion;
        this.status = status;
    }

    public String getOurVersion() {
        return ourVersion;
    }

    public String getTheirVersion() {
        return theirVersion;
    }

    public Status getStatus() {
        return status;
    }

    public void markConflicting() {
        status = Status.CONFLICTING;
    }
    
    public String getSelectedVersion() {
        return status == Status.KEEP_OURS ? getOurVersion() : getTheirVersion();
    }

    public void keepOurs() {
        status = Status.KEEP_OURS;
    }
    
    public void keepTheir() {
        status = Status.KEEP_THEIR;
    }
    
    @Override
    public String toString() {
        switch (status) {
            case KEEP_THEIR: return getTheirVersionLabel();
            case KEEP_OURS: return getOurVersionLabel();
            default:
                return Messages.conflicting;
        }
    }

    public String getOurVersionLabel() {
        return String.format(Messages.ourVersion, getOurVersion()) ;
    }

    public String getTheirVersionLabel() {
        return String.format(Messages.theirVersion, getTheirVersion()) ;
    }
    
}
