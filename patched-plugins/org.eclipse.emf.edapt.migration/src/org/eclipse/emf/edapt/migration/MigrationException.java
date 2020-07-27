/*******************************************************************************
 * Copyright (c) 2007, 2010 BMW Car IT, Technische Universitaet Muenchen, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * BMW Car IT - Initial API and implementation
 * Technische Universitaet Muenchen - Major refactoring and extension
 *******************************************************************************/
package org.eclipse.emf.edapt.migration;

import org.eclipse.emf.common.util.URI;

/**
 * Exception to witness the failure of a migration.
 *
 * @author herrmama
 * @author $Author: mherrmannsd $
 * @version $Rev: 114 $
 * @levd.rating YELLOW Hash: 566A4B9AAB3B3C4B9C0A825DFC6105CE
 */
public class MigrationException extends Exception {

    private static final long serialVersionUID = -8389485839750438901L;
    /** The location where the exception occurred. */
    private final URI location;

    /** Constructor. */
    public MigrationException(Throwable cause) {
        this((URI) null, cause);
    }

    /** Constructor. */
    public MigrationException(URI location, Throwable cause) {
        super(cause);
        this.location = location;
    }

    /** Constructor. */
    public MigrationException(String message, Throwable cause) {
        this(null, message, cause);
    }

    /** Constructor. */
    public MigrationException(URI location, String message, Throwable cause) {
        super(message, cause);
        this.location = location;
    }

    /** Return the location where the exception occurred. */
    public URI getLocation() {
        return location;
    }
}
