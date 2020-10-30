/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.ui.control.model;

import org.bonitasoft.engine.business.application.xml.ApplicationNode;

public class ApplicationDescriptorWithFileName {

    private ApplicationNode applicationNode;
    private String fileName;

    public ApplicationDescriptorWithFileName(ApplicationNode applicationNode, String fileName) {
        this.applicationNode = applicationNode;
        this.fileName = fileName;
    }

    public ApplicationNode getApplicationNode() {
        return applicationNode;
    }

    public String getFileName() {
        return fileName;
    }
}
