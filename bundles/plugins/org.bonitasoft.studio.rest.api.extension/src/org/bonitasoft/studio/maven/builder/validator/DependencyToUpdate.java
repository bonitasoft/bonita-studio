/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.builder.validator;

public class DependencyToUpdate {

    private Location location;
    private String tag;
    private String message;
    private int code;
    private String minVersion;
    private int severity;

    public DependencyToUpdate(Location location, String tag, String message, int code, String minVersion,
            int severity) {
        this.location = location;
        this.tag = tag;
        this.message = message;
        this.code = code;
        this.minVersion = minVersion;
        this.severity = severity;
    }

    public Location getLocation() {
        return location;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public String getMinVersion() {
        return minVersion;
    }

    public int getSeverity() {
        return severity;
    }

    public String getTag() {
        return tag;
    }

}
