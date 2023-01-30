/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.contract.ui.property.constraint.edit.editor;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.ui.browser.IWebBrowser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class WebBrowserFactoryTest {

    @Spy
    private WebBrowserFactory factory;
    @Mock
    private IWebBrowser browser;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        doReturn(browser).when(factory).createExternalBrowser();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_openExteranlBrowser_open_url_on_external_browser() throws Exception {
        final String urlAsString = "http://www.bonitasoft.com";
        factory.openExteranlBrowser(urlAsString);
        verify(browser).openURL(new URL(urlAsString));
    }

    @Test(expected = MalformedURLException.class)
    public void should_openExteranlBrowser_throw_MalformedURLException() throws Exception {
        final String urlAsString = "htt//www.boni*tasoft.com";
        factory.openExteranlBrowser(urlAsString);
    }

}
