/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.ui.validator;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.la.application.ui.editor.NavigationPageNode;
import org.bonitasoft.studio.la.application.ui.editor.customPage.CustomPageDescriptor;
import org.bonitasoft.studio.la.application.ui.editor.customPage.CustomPageProvider;
import org.bonitasoft.studio.theme.ThemeRepositoryStore;
import org.eclipse.core.runtime.IStatus;
import org.junit.Test;

public class ApplicationDescriptorValidatorsTest {

    @Test
    public void should_fail_if_token_is_empty() throws Exception {
        final List<NavigationPageNode> navigationPageNodes = new ArrayList<>();
        final NavigationPageNode myPage = new NavigationPageNode("menu2", "myPage", "");
        navigationPageNodes.add(myPage);

        final IStatus status = ApplicationDescriptorValidators.tokenPageColumnValidator(navigationPageNodes).apply(myPage);

        StatusAssert.assertThat(status).isNotOK();
    }

    @Test
    public void should_fail_if_token_is_already_defined() throws Exception {
        final List<NavigationPageNode> navigationPageNodes = new ArrayList<>();
        final NavigationPageNode myPage = new NavigationPageNode("menu1", "custompage_myPage", "myToken");
        navigationPageNodes.add(myPage);
        final NavigationPageNode myPage2 = new NavigationPageNode("menu2", "custompage_myPage2", "myToken");
        navigationPageNodes.add(myPage2);

        final IStatus status = ApplicationDescriptorValidators.tokenPageColumnValidator(navigationPageNodes).apply(myPage2);

        StatusAssert.assertThat(status).isNotOK();
    }

    @Test
    public void should_fail_if_token_is_not_alphamueric() throws Exception {
        final List<NavigationPageNode> navigationPageNodes = new ArrayList<>();
        final NavigationPageNode myPage = new NavigationPageNode("menu1", "custompage_myPage", "my Token");
        navigationPageNodes.add(myPage);

        final IStatus status = ApplicationDescriptorValidators.tokenPageColumnValidator(navigationPageNodes).apply(myPage);

        StatusAssert.assertThat(status).isNotOK();
    }

    @Test
    public void should_not_fail_if_node_is_a_topMenu() throws Exception {
        final List<NavigationPageNode> navigationPageNodes = new ArrayList<>();
        final NavigationPageNode myPage = new NavigationPageNode("menu1");
        navigationPageNodes.add(myPage);

        final IStatus status = ApplicationDescriptorValidators.tokenPageColumnValidator(navigationPageNodes).apply(myPage);

        StatusAssert.assertThat(status).isOK();
    }

    @Test
    public void should_fail_if_menu_is_empty() throws Exception {
        final NavigationPageNode myPage = new NavigationPageNode("", "custompage_myPage", "my Token");

        final IStatus status = ApplicationDescriptorValidators.menuColumnValidator().apply(myPage);

        StatusAssert.assertThat(status).isNotOK();
    }

    @Test
    public void should_not_fail_if_menu_is_empty_when_node_is_orphan() throws Exception {
        final NavigationPageNode myPage = new NavigationPageNode("", "custompage_myPage", "my Token");
        myPage.setOrphan(true);

        final IStatus status = ApplicationDescriptorValidators.menuColumnValidator().apply(myPage);

        StatusAssert.assertThat(status).isOK();
    }

    @Test
    public void should_fail_if_page_name_is_empty() throws Exception {
        final NavigationPageNode myPage = new NavigationPageNode("Menu", "", "my Token");
        myPage.setOrphan(true);

        final IStatus status = ApplicationDescriptorValidators.applicationPageColumnValidator(emptyCustomPageProvider())
                .apply(myPage);

        StatusAssert.assertThat(status).isNotOK();
    }

    @Test
    public void should_fail_if_page_name_has_an_invalid_format() throws Exception {
        final NavigationPageNode myPage = new NavigationPageNode("Menu", "noPrefix", "my Token");
        myPage.setOrphan(true);

        final IStatus status = ApplicationDescriptorValidators.applicationPageColumnValidator(emptyCustomPageProvider())
                .apply(myPage);

        StatusAssert.assertThat(status).isNotOK();
    }

    @Test
    public void should_warn_if_page_name_does_not_exists_in_ui_designer() throws Exception {
        final NavigationPageNode myPage = new NavigationPageNode("Menu", "custompage_MyPage", "my Token");
        myPage.setOrphan(true);

        final IStatus status = ApplicationDescriptorValidators.applicationPageColumnValidator(emptyCustomPageProvider())
                .apply(myPage);

        StatusAssert.assertThat(status).hasSeverity(IStatus.WARNING);
    }

    @Test
    public void should_not_fail_if_page_name_exists_in_ui_designer() throws Exception {
        final NavigationPageNode myPage = new NavigationPageNode("Menu", "custompage_MyPage", "my Token");
        myPage.setOrphan(true);

        final IStatus status = ApplicationDescriptorValidators
                .applicationPageColumnValidator(customPageProviderWithPage())
                .apply(myPage);

        StatusAssert.assertThat(status).isOK();
    }

    @Test
    public void should_fail_if_token_contains_reserved_keywords() throws Exception {
        final NavigationPageNode pageWithAPItoken = new NavigationPageNode("Menu", "custompage_MyPage", "api");
        final NavigationPageNode pageWithContenttoken = new NavigationPageNode("Menu", "custompage_MyPage", "content");
        final NavigationPageNode pageWithThemetoken = new NavigationPageNode("Menu", "custompage_MyPage", "theme");

        final Function<NavigationPageNode, IStatus> tokenPageColumnValidator = ApplicationDescriptorValidators
                .tokenPageColumnValidator(new ArrayList<>());

        StatusAssert.assertThat(tokenPageColumnValidator.apply(pageWithAPItoken)).isNotOK();
        StatusAssert.assertThat(tokenPageColumnValidator.apply(pageWithContenttoken)).isNotOK();
        StatusAssert.assertThat(tokenPageColumnValidator.apply(pageWithThemetoken)).isNotOK();
    }

    @Test
    public void should_fail_if_display_name_is_null_or_empty() {
        IStatus status = ApplicationDescriptorValidators.appDisplayNameValidator().validate("");
        StatusAssert.assertThat(status).isError();
        ApplicationDescriptorValidators.appDisplayNameValidator().validate(null);
        StatusAssert.assertThat(status).isError();
    }

    @Test
    public void should_fail_if_display_name_is_too_long() {
        String displayName_256 = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas";
        IStatus status = ApplicationDescriptorValidators.appDisplayNameValidator().validate(displayName_256);
        StatusAssert.assertThat(status).isError();
        status = ApplicationDescriptorValidators.appDisplayNameValidator().validate(displayName_256.substring(0, 254));
        StatusAssert.assertThat(status).isOK();
    }

    private CustomPageProvider customPageProviderWithPage() {
        CustomPageProvider customPageProvider = spy(
                new CustomPageProvider(mock(WebPageRepositoryStore.class), mock(ThemeRepositoryStore.class)));
        doReturn(Arrays.asList(new CustomPageDescriptor("custompage_MyPage", "MyPage", "")))
                .when(customPageProvider).getApplicationPages();
        return customPageProvider;
    }

    private CustomPageProvider emptyCustomPageProvider() {
        CustomPageProvider provider = spy(
                new CustomPageProvider(mock(WebPageRepositoryStore.class), mock(ThemeRepositoryStore.class)));
        doReturn(Collections.emptyList()).when(provider).getApplicationPages();
        return provider;
    }
}
