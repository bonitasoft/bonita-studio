/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.designer.core.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.net.URL;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.preferences.browser.OpenBrowserOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class WebPageFileStoreTest {

    private WebPageFileStore webPageFileStore;
    @Mock
    private PageDesignerURLFactory urlFactory;
    @Mock
    private OpenBrowserOperation operationBrowser;
    @Mock
    private IRepositoryStore<? extends IRepositoryFileStore> parentStore;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        webPageFileStore = spy(new WebPageFileStore("myPageId.json", parentStore));
        doReturn("myPageId").when(webPageFileStore).getId();
        doReturn(urlFactory).when(webPageFileStore).urlFactory();
        doReturn(new URL("http://localhost:8080/page-designer/#/en/pages/myPageId")).when(urlFactory).openPage("myPageId");
        doReturn(operationBrowser).when(webPageFileStore)
                .openBrowserOperation(new URL("http://localhost:8080/page-designer/#/en/pages/myPageId"));
    }

    @Test
    public void should_open_browser_on_page_designer() throws Exception {
        webPageFileStore.doOpen();

        verify(urlFactory).openPage("myPageId");
        verify(operationBrowser).execute();
    }

    @Test
    public void should_compare_web_pages() {
        WebPageFileStore form = spy(new WebPageFileStore("myform.json", parentStore));
        WebPageFileStore form2 = spy(new WebPageFileStore("myform2.json", parentStore));
        WebPageFileStore page = spy(new WebPageFileStore("mypage.json", parentStore));
        WebPageFileStore layout = spy(new WebPageFileStore("mylayout.json", parentStore));
        doReturn(WebPageFileStore.FORM_TYPE).when(form).getType();
        doReturn(WebPageFileStore.FORM_TYPE).when(form2).getType();
        doReturn(WebPageFileStore.PAGE_TYPE).when(page).getType();
        doReturn(WebPageFileStore.LAYOUT_TYPE).when(layout).getType();
        doReturn(null).when(form).getDisplayName();
        doReturn(null).when(form2).getDisplayName();
        doReturn(null).when(page).getDisplayName();
        doReturn(null).when(layout).getDisplayName();

        assertThat(form.compareTo(page)).isLessThan(0);
        assertThat(form.compareTo(layout)).isLessThan(0);
        assertThat(form.compareTo(form2)).isLessThan(0);
        assertThat(form.compareTo(form)).isEqualTo(0);

        assertThat(page.compareTo(form)).isGreaterThan(0);
        assertThat(page.compareTo(layout)).isLessThan(0);
        assertThat(page.compareTo(page)).isEqualTo(0);

        assertThat(layout.compareTo(form)).isGreaterThan(0);
        assertThat(layout.compareTo(page)).isGreaterThan(0);
        assertThat(layout.compareTo(layout)).isEqualTo(0);

        doReturn("B - My form").when(form2).getDisplayName();
        assertThat(form.compareTo(form2)).isGreaterThan(0);
        doReturn("A - My form").when(form).getDisplayName();
        assertThat(form.compareTo(form2)).isLessThan(0);
    }
}
