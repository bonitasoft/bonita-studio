/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.common.repository.model;

import java.util.Optional;

public interface IRenamable {

    public void rename(String newName);

    public Optional<String> retrieveNewName();

    /**
     * Used to handle some specific cases, like the default profiles file
     */
    public default boolean canBeRenamed() {
        return true;
    }

}
