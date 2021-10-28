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

import org.bonitasoft.engine.business.application.xml.ApplicationMenuNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationPageNode;
import org.bonitasoft.studio.la.application.ui.editor.NavigationPageNode;

public class NavigationToApplicationConverter {

    public ApplicationNode toApplicationNode(List<NavigationPageNode> listPageNode) {

        ApplicationNode applicationNode = new ApplicationNode();

        listPageNode.stream()
                .filter(NavigationPageNode::isOrphan)
                .map(pageNode -> toApplicationPageNode(pageNode))
                .distinct()
                .forEach(applicationNode::addApplicationPage);

        listPageNode.stream()
                .filter(pageNode -> !pageNode.isOrphan())
                .map(pageNode -> toApplicationMenuNode(pageNode, applicationNode))
                .forEach(applicationNode::addApplicationMenu);

        return applicationNode;

    }

    protected ApplicationPageNode toApplicationPageNode(NavigationPageNode navigationPageNode) {
        ApplicationPageNode page = new ApplicationPageNode();
        page.setToken(navigationPageNode.getApplicationToken());
        page.setCustomPage(navigationPageNode.getApplicationPage());
        return page;
    }

    protected ApplicationMenuNode toApplicationMenuNode(NavigationPageNode pageNode, ApplicationNode applicationNode) {
        ApplicationMenuNode applicationMenuNode = toSingleApplicationMenuNode(pageNode, applicationNode);
        if (!pageNode.getChildren().isEmpty()) {
            pageNode.getChildren().stream()
                    .map(subMenu -> toApplicationMenuNode(subMenu, applicationNode))
                    .forEach(applicationMenuNode::addApplicationMenu);
        }
        return applicationMenuNode;
    }

    protected ApplicationMenuNode toSingleApplicationMenuNode(NavigationPageNode pageNode, ApplicationNode applicationNode) {
        if (!pageNode.isTopMenu()) {
            ApplicationPageNode applicationPageNode = toApplicationPageNode(pageNode);
            if (!applicationNode.getApplicationPages().contains(applicationPageNode)) {
                applicationNode.addApplicationPage(toApplicationPageNode(pageNode));
            }
        }
        ApplicationMenuNode menu = new ApplicationMenuNode();
        menu.setApplicationPage(pageNode.getApplicationToken());
        menu.setDisplayName(pageNode.getMenuLabel());
        return menu;
    }

}
