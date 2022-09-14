/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.ui.editor.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bonitasoft.engine.business.application.xml.ApplicationMenuNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationPageNode;
import org.bonitasoft.studio.la.application.ui.editor.NavigationPageNode;

import com.google.common.base.Objects;

public class ApplicationToNavigationConverter {

    public List<NavigationPageNode> toNavigationPageNodeList(ApplicationNode applicationNode) {
        final List<NavigationPageNode> navigationPageNodeList = new ArrayList<>();
        applicationNode.getApplicationPages()
                .stream()
                .filter(applicationPageNode -> isOrphanPage(applicationPageNode, applicationNode))
                .map(this::toNavigationPageNode)
                .forEach(navigationPageNodeList::add);

        applicationNode.getApplicationMenus()
                .stream()
                .map(applicationMenuNode -> toNavigationPageNode(applicationMenuNode, applicationNode))
                .forEach(navigationPageNodeList::add);

        return navigationPageNodeList;
    }

    public static boolean isOrphanPage(ApplicationPageNode applicationPageNode, ApplicationNode applicationNode) {
        return applicationNode.getApplicationMenus()
                .stream()
                .noneMatch(applicationMenuNode -> arePageAndMenulinked(applicationMenuNode, applicationPageNode));
    }

    private static boolean arePageAndMenulinked(ApplicationMenuNode applicationMenuNode, ApplicationPageNode applicationPageNode) {
        return Objects.equal(applicationMenuNode.getApplicationPage(), applicationPageNode.getToken()) ? true
                : applicationMenuNode.getApplicationMenus().stream()
                        .anyMatch(subMenu -> arePageAndMenulinked(subMenu, applicationPageNode));
    }

    protected NavigationPageNode toNavigationPageNode(ApplicationPageNode applicationPageNode) {
        final NavigationPageNode navigationPageNode = new NavigationPageNode("", applicationPageNode.getCustomPage(),
                applicationPageNode.getToken());
        navigationPageNode.setOrphan(true);
        return navigationPageNode;
    }

    protected NavigationPageNode toNavigationPageNode(ApplicationMenuNode applicationMenuNode,
            ApplicationNode applicationNode) {

        final Optional<ApplicationPageNode> menuPage = applicationNode.getApplicationPages().stream()
                .filter(applicationPageNode -> Objects.equal(applicationPageNode.getToken(),
                        applicationMenuNode.getApplicationPage()))
                .findFirst();

        final boolean isTopMenu = !applicationMenuNode.getApplicationMenus().isEmpty();

        final NavigationPageNode navigationPageNode = new NavigationPageNode(applicationMenuNode.getDisplayName(),
                menuPage.map(ApplicationPageNode::getCustomPage).orElse(isTopMenu ? null : ""),
                menuPage.map(ApplicationPageNode::getToken).orElse(isTopMenu ? null : ""));

        navigationPageNode.setTopMenu(isTopMenu);

        applicationMenuNode.getApplicationMenus().stream()
                .map(subMenu -> toNavigationPageNode(subMenu, applicationNode))
                .forEach(navigationPageNode::addChild);

        return navigationPageNode;
    }

}
