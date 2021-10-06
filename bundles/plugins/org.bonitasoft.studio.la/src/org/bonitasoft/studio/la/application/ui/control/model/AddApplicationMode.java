/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.ui.control.model;

public enum AddApplicationMode {
    NEW("new"), FROM("from");

    private String option;

    private AddApplicationMode(String option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return option;
    }
}
