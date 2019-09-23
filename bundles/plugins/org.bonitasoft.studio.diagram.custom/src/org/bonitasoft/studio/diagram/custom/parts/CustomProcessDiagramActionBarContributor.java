/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.diagram.custom.parts;

import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramActionBarContributor;

public class CustomProcessDiagramActionBarContributor extends ProcessDiagramActionBarContributor {

    @Override
    protected String getEditorId() {
        return CustomProcessDiagramEditor.ID;
    }

}
