/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.core.validation;

import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.bonitasoft.studio.rest.api.extension.core.repository.PathTemplate;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class PathTemplateErrorStatus extends Status {

    private final PathTemplate pathTemplate;

    public PathTemplateErrorStatus(final String message, final PathTemplate pathTemplate) {
        super(IStatus.ERROR, RestAPIExtensionActivator.PLUGIN_ID, message);
        this.pathTemplate = pathTemplate;
    }

    public PathTemplate getPathTemplate() {
        return pathTemplate;
    }
}
