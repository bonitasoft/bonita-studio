/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.ui.editor.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Objects;

import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeBuilder;
import org.bonitasoft.engine.business.application.xml.ApplicationPageNode;
import org.bonitasoft.studio.la.application.ui.editor.NavigationPageNode;
import org.bonitasoft.studio.la.application.ui.editor.converter.ApplicationToNavigationConverter;
import org.junit.Test;

public class ApplicationToNavigationConverterTest {

    @Test
    public void should_convert_ApplicationNode_into_NavigationPageNodeList() {
        final ApplicationToNavigationConverter converter = new ApplicationToNavigationConverter();
        final ApplicationNode applicationNode = createApplicationNode();

        final List<NavigationPageNode> navigationPageNodeList = converter.toNavigationPageNodeList(applicationNode);

        assertThat(navigationPageNodeList.stream()
                .anyMatch(navigationPageNode -> Objects.equals(navigationPageNode.getApplicationPage(), "Home page")
                        && Objects.equals(navigationPageNode.getApplicationToken(), "home")
                        && navigationPageNode.isOrphan()
                        && !navigationPageNode.isTopMenu())).isTrue();

        assertThat(navigationPageNodeList.stream()
                .anyMatch(navigationPageNode -> Objects.equals(navigationPageNode.getMenuLabel(), "Single menu")
                        && Objects.equals(navigationPageNode.getApplicationPage(), "Single menu page")
                        && Objects.equals(navigationPageNode.getApplicationToken(), "singleMenuPageToken")
                        && !navigationPageNode.isTopMenu())).isTrue();

        final NavigationPageNode topMenu = navigationPageNodeList.stream()
                .filter(navigationPageNode -> Objects.equals(navigationPageNode.getMenuLabel(), "Top menu"))
                .findFirst()
                .get();
        assertThat(topMenu.isTopMenu()).isTrue();

        assertThat(topMenu.getChildren().size()).isEqualTo(2);
        assertThat(topMenu.getChildren().stream()
                .anyMatch(child -> Objects.equals(child.getMenuLabel(), "Deep menu 1")
                        && Objects.equals(child.getApplicationPage(), "Deep menu page 1")
                        && Objects.equals(child.getApplicationToken(), "deepMenuPageToken1")
                        && !child.isTopMenu())).isTrue();
        assertThat(topMenu.getChildren().stream()
                .anyMatch(child -> Objects.equals(child.getMenuLabel(), "Deep menu 2")
                        && Objects.equals(child.getApplicationPage(), "Deep menu page 2")
                        && Objects.equals(child.getApplicationToken(), "deepMenuPageToken2")
                        && !child.isTopMenu())).isTrue();
    }

    @Test
    public void should_define_if_page_is_orphan() {
        final ApplicationToNavigationConverter converter = new ApplicationToNavigationConverter();

        final ApplicationNode applicationNode = ApplicationNodeBuilder.newApplication("token", "displayName", "1.0")
                .havingApplicationPages(
                        ApplicationNodeBuilder.newApplicationPage("orphanPage", "token"),
                        ApplicationNodeBuilder.newApplicationPage("page", "token2"))
                .havingApplicationMenus(
                        ApplicationNodeBuilder.newMenu("Top menu", "")
                                .havingMenu(ApplicationNodeBuilder.newMenu("Menu", "token2")))
                .create();

        assertThat(converter.isOrphanPage(ApplicationNodeBuilder.newApplicationPage("orphanPage", "token").create(),
                applicationNode)).isTrue();
        assertThat(converter.isOrphanPage(ApplicationNodeBuilder.newApplicationPage("page", "token2").create(),
                applicationNode)).isFalse();
    }

    @Test
    public void should_convert_single_ApplicationPageNode_into_NavigationPageNode() {
        final ApplicationToNavigationConverter converter = new ApplicationToNavigationConverter();
        final ApplicationPageNode applicationPageNode = ApplicationNodeBuilder.newApplicationPage("name", "token").create();

        final NavigationPageNode navigationPageNode = converter.toNavigationPageNode(applicationPageNode);
        assertEquals("token", navigationPageNode.getApplicationToken());
        assertEquals("name", navigationPageNode.getApplicationPage());
        assertTrue(navigationPageNode.isOrphan());
    }

    private ApplicationNode createApplicationNode() {
        return ApplicationNodeBuilder.newApplication("token", "name", "1.0")
                .havingApplicationPages(
                        ApplicationNodeBuilder.newApplicationPage("Home page", "home"),
                        ApplicationNodeBuilder.newApplicationPage("Single menu page", "singleMenuPageToken"),
                        ApplicationNodeBuilder.newApplicationPage("Deep menu page 1", "deepMenuPageToken1"),
                        ApplicationNodeBuilder.newApplicationPage("Deep menu page 2", "deepMenuPageToken2"))
                .havingApplicationMenus(
                        ApplicationNodeBuilder.newMenu("Single menu", "singleMenuPageToken"),
                        ApplicationNodeBuilder.newMenu("Top menu", "").havingMenu(
                                ApplicationNodeBuilder.newMenu("Deep menu 1", "deepMenuPageToken1"),
                                ApplicationNodeBuilder.newMenu("Deep menu 2", "deepMenuPageToken2")))
                .withHomePage("home")
                .create();
    }

}
