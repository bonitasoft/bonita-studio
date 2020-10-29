/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.ui.editor.converter;

import java.util.List;

import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.studio.la.application.ui.editor.NavigationPageNode;

public class UpdateApplicationNodeOperation {

    private final NavigationToApplicationConverter toApplicationConverter;
    private final ApplicationNode applicationNode;

    public UpdateApplicationNodeOperation(ApplicationNode applicationNode) {
        this.applicationNode = applicationNode;
        toApplicationConverter = new NavigationToApplicationConverter();
    }

    public void updateApplicationNode(List<NavigationPageNode> navigationPageNodes) {
        final ApplicationNode tmpApplicationNode = toApplicationConverter.toApplicationNode(navigationPageNodes);
        applicationNode.setApplicationPages(tmpApplicationNode.getApplicationPages());
        applicationNode.setApplicationMenus(tmpApplicationNode.getApplicationMenus());
    }

}
