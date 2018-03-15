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
package org.bonitasoft.studio.designer.ui.handler;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URL;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.browser.OpenBrowserOperation;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class OpenUIDesignerHandlerTest {

    private OpenUIDesignerHandler openPageDesignerHandler;
    @Mock
    private IEclipsePreferences preferenceStore;
    @Mock
    private OpenBrowserOperation openBrowserOperation;
    @Mock
    private PageDesignerURLFactory urlFactory;

    private boolean disablePopup;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        disablePopup = FileActionDialog.getDisablePopup();
        FileActionDialog.setDisablePopup(true);
        openPageDesignerHandler = spy(new OpenUIDesignerHandler());
        when(preferenceStore.get(BonitaPreferenceConstants.CONSOLE_HOST, BonitaPreferenceConstants.DEFAULT_HOST))
                .thenReturn("localhost");
        when(preferenceStore.getInt(BonitaPreferenceConstants.CONSOLE_PORT, BonitaPreferenceConstants.DEFAULT_PORT))
                .thenReturn(8080);
        when(preferenceStore.get(BonitaPreferenceConstants.CURRENT_STUDIO_LOCALE, "en")).thenReturn("en");
        doReturn(preferenceStore).when(openPageDesignerHandler).getPreferenceStore();
        doReturn(urlFactory).when(openPageDesignerHandler).urlFactory();
        doReturn(openBrowserOperation).when(openPageDesignerHandler).createOpenBrowserOperation(any(URL.class));
        doReturn(true).when(openPageDesignerHandler).waitUntilTomcatIsReady(any(PageDesignerURLFactory.class));
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        FileActionDialog.setDisablePopup(disablePopup);
    }

    @Test
    public void should_execute_open_an_external_browser_with_page_builder_url() throws Exception {
        openPageDesignerHandler.execute(null);
        verify(openBrowserOperation).execute();
    }

}
