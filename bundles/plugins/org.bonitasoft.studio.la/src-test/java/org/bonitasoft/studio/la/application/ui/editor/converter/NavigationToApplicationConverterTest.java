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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.engine.business.application.xml.ApplicationMenuNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeBuilder;
import org.bonitasoft.engine.business.application.xml.ApplicationPageNode;
import org.bonitasoft.studio.la.application.ui.editor.NavigationPageNode;
import org.bonitasoft.studio.la.application.ui.editor.converter.NavigationToApplicationConverter;
import org.junit.Test;

import com.google.common.base.Strings;

public class NavigationToApplicationConverterTest {

    @Test
    public void should_convert_navigationPageNode_into_ApplicationPageNode() {
        NavigationToApplicationConverter converter = new NavigationToApplicationConverter();
        ApplicationPageNode applicationPageNode = converter
                .toApplicationPageNode(new NavigationPageNode("", "name", "token"));
        assertThat(applicationPageNode.getCustomPage()).isEqualTo("name");
        assertThat(applicationPageNode.getToken()).isEqualTo("token");
    }

    @Test
    public void should_convert_singleMenu_into_ApplicationMenuNode() {
        NavigationToApplicationConverter converter = new NavigationToApplicationConverter();
        ApplicationNode applicationNode = ApplicationNodeBuilder.newApplication("token", "name", "1.0").create();
        ApplicationMenuNode singleApplicationMenuNode = converter
                .toSingleApplicationMenuNode(new NavigationPageNode("menu", "page", "pageToken"), applicationNode);
        assertThat(singleApplicationMenuNode.getDisplayName()).isEqualTo("menu");
        assertThat(singleApplicationMenuNode.getApplicationPage()).isEqualTo("pageToken");
        ApplicationPageNode applicationPageNode = applicationNode.getApplicationPages().get(0);
        assertThat(applicationPageNode.getCustomPage()).isEqualTo("page");
        assertThat(applicationPageNode.getToken()).isEqualTo("pageToken");
    }

    @Test
    public void should_convert_tree_menu_into_ApplicationPageNode() {
        NavigationToApplicationConverter converter = new NavigationToApplicationConverter();
        ApplicationNode applicationNode = ApplicationNodeBuilder.newApplication("token", "name", "1.0").create();
        NavigationPageNode topMenuPageNode = new NavigationPageNode("Top Menu");
        NavigationPageNode subMenuPageNode = new NavigationPageNode("Sub Menu", "page", "token");
        topMenuPageNode.addChild(subMenuPageNode);

        ApplicationMenuNode applicationMenuNode = converter.toApplicationMenuNode(topMenuPageNode, applicationNode);
        assertThat(applicationMenuNode.getDisplayName()).isEqualTo("Top Menu");
        assertThat(Strings.isNullOrEmpty(applicationMenuNode.getApplicationPage())).isTrue();
        ApplicationMenuNode subApplicationMenuNode = applicationMenuNode.getApplicationMenus().get(0);
        assertThat(subApplicationMenuNode.getDisplayName()).isEqualTo("Sub Menu");
        assertThat(subApplicationMenuNode.getApplicationPage()).isEqualTo("token");
        assertThat(applicationNode.getApplicationPages()).hasSize(1);
        ApplicationPageNode applicationPageNode = applicationNode.getApplicationPages().get(0);
        assertThat(applicationPageNode.getCustomPage()).isEqualTo("page");
        assertThat(applicationPageNode.getToken()).isEqualTo("token");
    }

    @Test
    public void should_convert_NavigationPageNodeList_into_ApplicationNode() {
        NavigationToApplicationConverter converter = new NavigationToApplicationConverter();
        List<NavigationPageNode> navigationPageNodeList = createNavigationPageNodeList();

        ApplicationNode applicationNode = converter.toApplicationNode(navigationPageNodeList);

        assertThat(applicationNode.getApplicationPages()).hasSize(3);

        assertThat(applicationNode.getApplicationPages()
                .stream()
                .anyMatch(applicationPage -> Objects.equals(applicationPage.getCustomPage(), "Orphan page")
                        && Objects.equals(applicationPage.getToken(), "orphanToken"))).isTrue();

        assertThat(applicationNode.getApplicationPages()
                .stream()
                .anyMatch(applicationPage -> Objects.equals(applicationPage.getCustomPage(), "Page")
                        && Objects.equals(applicationPage.getToken(), "token"))).isTrue();

        assertThat(applicationNode.getApplicationPages()
                .stream()
                .anyMatch(applicationPage -> Objects.equals(applicationPage.getCustomPage(), "Home page")
                        && Objects.equals(applicationPage.getToken(), "home"))).isTrue();

        assertThat(applicationNode.getApplicationMenus()).hasSize(2);

        assertThat(applicationNode.getApplicationMenus()
                .stream()
                .anyMatch(applicationMenu -> Objects.equals(applicationMenu.getDisplayName(), "Direct Menu")
                        && Objects.equals(applicationMenu.getApplicationPage(), "token"))).isTrue();

        ApplicationMenuNode topMenu = applicationNode.getApplicationMenus()
                .stream()
                .filter(applicationMenu -> Objects.equals(applicationMenu.getDisplayName(), "Top Menu"))
                .findFirst()
                .get();

        assertThat(topMenu.getApplicationMenus()).hasSize(2);

        assertThat(topMenu.getApplicationMenus()
                .stream()
                .anyMatch(applicationMenu -> Objects.equals(applicationMenu.getDisplayName(), "Sub Menu")
                        && Objects.equals(applicationMenu.getApplicationPage(), "home"))).isTrue();
        assertThat(topMenu.getApplicationMenus()
                .stream()
                .anyMatch(applicationMenu -> Objects.equals(applicationMenu.getDisplayName(), "Sub Menu 2")
                        && Objects.equals(applicationMenu.getApplicationPage(), "token"))).isTrue();

    }

    @Test
    public void should_support_several_menus_with_same_page() {
        NavigationToApplicationConverter converter = new NavigationToApplicationConverter();
        List<NavigationPageNode> navigationPageNodeList = new ArrayList<>();
        navigationPageNodeList.add(new NavigationPageNode("menu1", "page", "token"));
        navigationPageNodeList.add(new NavigationPageNode("menu2", "page", "token"));
        ApplicationNode applicationNode = converter.toApplicationNode(navigationPageNodeList);

        assertEquals(1, applicationNode.getApplicationPages().size());
        assertEquals(2, applicationNode.getApplicationMenus().size());
    }

    @Test
    public void should_support_deep_menu() {
        NavigationToApplicationConverter converter = new NavigationToApplicationConverter();
        List<NavigationPageNode> navigationPageNodeList = new ArrayList<>();

        NavigationPageNode singleMenuLevel2 = new NavigationPageNode("singleMenuLevel2", "page2", "token2");

        NavigationPageNode topMenuLevel1 = new NavigationPageNode("topMenuLevel1");
        topMenuLevel1.addChild(singleMenuLevel2);
        NavigationPageNode singleMenuLevel1 = new NavigationPageNode("singleMenuLevel1", "page", "token");

        NavigationPageNode topMenuLevel0 = new NavigationPageNode("topMenuLevel0");
        topMenuLevel0.addChild(topMenuLevel1);
        topMenuLevel0.addChild(singleMenuLevel1);

        navigationPageNodeList.add(topMenuLevel0);

        ApplicationNode applicationNode = converter.toApplicationNode(navigationPageNodeList);

        assertThat(applicationNode.getApplicationPages()).hasSize(2);

        assertThat(applicationNode.getApplicationMenus()).hasSize(1);
        ApplicationMenuNode topMenuNodeLevel0 = applicationNode.getApplicationMenus().get(0);
        assertThat(topMenuNodeLevel0.getDisplayName()).isEqualTo("topMenuLevel0");

        assertThat(topMenuNodeLevel0.getApplicationMenus()).hasSize(2);
        assertThat(topMenuNodeLevel0.getApplicationMenus().stream()
                .anyMatch(menu -> Objects.equals(menu.getDisplayName(), "singleMenuLevel1")
                        && Objects.equals(menu.getApplicationPage(), "token"))).isTrue();
        ApplicationMenuNode topMenuNodeLevel1 = topMenuNodeLevel0.getApplicationMenus().stream()
                .filter(menu -> Objects.equals(menu.getDisplayName(), "topMenuLevel1")).findFirst().get();

        assertThat(topMenuNodeLevel1.getApplicationMenus()).hasSize(1);
        ApplicationMenuNode singleMenuNodeLevel2 = topMenuNodeLevel1.getApplicationMenus().get(0);
        assertThat(singleMenuNodeLevel2.getDisplayName()).isEqualTo("singleMenuLevel2");
        assertThat(singleMenuNodeLevel2.getApplicationPage()).isEqualTo("token2");
    }

    private List<NavigationPageNode> createNavigationPageNodeList() {
        List<NavigationPageNode> navigationPageNodeList = new ArrayList<>();

        NavigationPageNode orphanPageNode = new NavigationPageNode("", "Orphan page", "orphanToken");
        orphanPageNode.setOrphan(true);

        NavigationPageNode directMenuNode = new NavigationPageNode("Direct Menu", "Page", "token");

        NavigationPageNode topMenuPageNode = new NavigationPageNode("Top Menu");

        NavigationPageNode subMenuPageNode = new NavigationPageNode("Sub Menu", "Home page", "home");
        NavigationPageNode subMenuPageNode2 = new NavigationPageNode("Sub Menu 2", "Page", "token");

        topMenuPageNode.addChild(subMenuPageNode);
        topMenuPageNode.addChild(subMenuPageNode2);
        navigationPageNodeList.add(topMenuPageNode);
        navigationPageNodeList.add(orphanPageNode);
        navigationPageNodeList.add(directMenuNode);

        return navigationPageNodeList;
    }

}
