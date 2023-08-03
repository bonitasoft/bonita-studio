/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.builder.validator;

import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class StatusWithLocation extends Status {

    private final Location location;

    public StatusWithLocation(final int severtity, final String message, final Location location) {
        super(severtity, RestAPIExtensionActivator.PLUGIN_ID, message);
        this.location = location;
    }

    public StatusWithLocation(final String message, final Location location) {
        this(IStatus.ERROR, message, location);
    }

    public StatusWithLocation(final String message) {
        this(message, new Location(0, 0, 0));
    }

    public Location getLocation() {
        return location;
    }

}
