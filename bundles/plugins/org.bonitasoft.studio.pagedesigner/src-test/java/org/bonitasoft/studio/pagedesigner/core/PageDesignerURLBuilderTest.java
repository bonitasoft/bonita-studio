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
package org.bonitasoft.studio.pagedesigner.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.net.URL;

import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PageDesignerURLBuilderTest {

    @Mock
    private IPreferenceStore preferenceStore;
    private PageDesignerURLBuilder pageDesignerURLBuilder;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        pageDesignerURLBuilder = new PageDesignerURLBuilder(preferenceStore);
        when(preferenceStore.getString(BonitaPreferenceConstants.CONSOLE_HOST)).thenReturn("localhost");
        when(preferenceStore.getString(BonitaPreferenceConstants.CONSOLE_PORT)).thenReturn("8080");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_build_return_URL_pointing_to_page_builder_webapp() throws Exception {
        assertThat(pageDesignerURLBuilder.build()).isEqualTo(new URL("http://localhost:8080/page-designer"));
    }

}
