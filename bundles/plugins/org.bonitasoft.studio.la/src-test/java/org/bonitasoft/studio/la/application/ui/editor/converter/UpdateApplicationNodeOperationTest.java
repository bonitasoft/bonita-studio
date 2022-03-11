package org.bonitasoft.studio.la.application.ui.editor.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.engine.business.application.xml.ApplicationMenuNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeBuilder;
import org.bonitasoft.studio.la.application.ui.editor.NavigationPageNode;
import org.bonitasoft.studio.la.application.ui.editor.converter.UpdateApplicationNodeOperation;
import org.junit.Test;

import com.google.common.base.Objects;

public class UpdateApplicationNodeOperationTest {

    @Test
    public void should_update_application_node() {
        ApplicationNode applicationNode = createApplicationNode();
        UpdateApplicationNodeOperation operation = new UpdateApplicationNodeOperation(applicationNode);
        operation.updateApplicationNode(createNavigationPageNodeList());

        assertThat(applicationNode.getApplicationPages().stream()
                .anyMatch(page -> Objects.equal("newOrphanPageToken", page.getToken())
                        && Objects.equal("New Orphan Page", page.getCustomPage()))).isTrue();
        assertThat(applicationNode.getApplicationPages().stream()
                .anyMatch(page -> Objects.equal("singleMenuPageToken", page.getToken())
                        && Objects.equal("Single menu page", page.getCustomPage()))).isTrue();
        assertThat(applicationNode.getApplicationPages().stream()
                .anyMatch(page -> Objects.equal("deepMenuPageToken1", page.getToken())
                        && Objects.equal("Deep Menu Page 1", page.getCustomPage()))).isTrue();
        assertThat(applicationNode.getApplicationPages().stream()
                .noneMatch(page -> Objects.equal("Deep Menu Page 2", page.getCustomPage()))).isTrue();

        assertThat(applicationNode.getApplicationMenus().stream()
                .anyMatch(menu -> Objects.equal("Single Menu", menu.getDisplayName())
                        && Objects.equal("singleMenuPageToken", menu.getApplicationPage()))).isTrue();
        ApplicationMenuNode topMenu = applicationNode.getApplicationMenus().stream()
                .filter(menu -> Objects.equal(menu.getDisplayName(), "Top Menu")).findFirst().get();

        assertThat(topMenu.getApplicationMenus().stream()
                .noneMatch(menu -> Objects.equal("Deep menu 2", menu.getDisplayName()))).isTrue();
        assertThat(topMenu.getApplicationMenus().stream()
                .anyMatch(menu -> Objects.equal("Deep menu 1", menu.getDisplayName())
                        && Objects.equal("deepMenuPageToken1", menu.getApplicationPage()))).isTrue();

    }

    private ApplicationNode createApplicationNode() {
        return ApplicationNodeBuilder.newApplication("token", "name", "1.0")
                .havingApplicationPages(
                        ApplicationNodeBuilder.newApplicationPage("Single menu page", "singleMenuPageToken"),
                        ApplicationNodeBuilder.newApplicationPage("Deep menu page 1", "deepMenuPageToken1"),
                        ApplicationNodeBuilder.newApplicationPage("Deep menu page 2", "deepMenuPageToken2"))
                .havingApplicationMenus(
                        ApplicationNodeBuilder.newMenu("Single menu", "Single menu page"),
                        ApplicationNodeBuilder.newMenu("Top Menu", "").havingMenu(
                                ApplicationNodeBuilder.newMenu("Deep menu 1", "deepMenuPageToken1"),
                                ApplicationNodeBuilder.newMenu("Deep menu 2", "deepMenuPageToken2")))
                .create();
    }

    private List<NavigationPageNode> createNavigationPageNodeList() {
        List<NavigationPageNode> navigationPageNodeList = new ArrayList<>();

        NavigationPageNode orphanPageNode = new NavigationPageNode("", "New Orphan Page", "newOrphanPageToken");
        orphanPageNode.setOrphan(true);

        NavigationPageNode directMenuNode = new NavigationPageNode("Single Menu", "Single menu page", "singleMenuPageToken");

        NavigationPageNode topMenuPageNode = new NavigationPageNode("Top Menu");

        NavigationPageNode subMenuPageNode = new NavigationPageNode("Deep menu 1", "Deep Menu Page 1", "deepMenuPageToken1");

        topMenuPageNode.addChild(subMenuPageNode);
        navigationPageNodeList.add(topMenuPageNode);
        navigationPageNodeList.add(orphanPageNode);
        navigationPageNodeList.add(directMenuNode);

        return navigationPageNodeList;
    }

}
