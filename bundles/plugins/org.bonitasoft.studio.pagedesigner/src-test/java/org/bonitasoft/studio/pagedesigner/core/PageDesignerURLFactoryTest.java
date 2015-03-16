/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.pagedesigner.core;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class PageDesignerURLFactoryTest {

    private PageDesignerURLFactory pageDesignerURLBuilder;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        pageDesignerURLBuilder = new PageDesignerURLFactory("localhost", 8080);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_openPageDesignerHome_return_URL_pointing_to_page_builder_webapp() throws Exception {
        assertThat(pageDesignerURLBuilder.openPageDesignerHome()).isEqualTo(new URL("http://localhost:8080/page-designer"));
    }

    @Test
    public void should_openPage_return_URL_pointing_to_page_builder_webapp_on_the_given_page() throws Exception {
        assertThat(pageDesignerURLBuilder.openPage("page-id")).isEqualTo(
                new URL("http://localhost:8080/page-designer/#/" + Locale.getDefault().getLanguage() + "/pages/page-id"));
    }

    @Test
    public void should_newPage_return_URL_to_post_a_new_page() throws Exception {
        assertThat(pageDesignerURLBuilder.newPage()).isEqualTo(
                new URL("http://localhost:8080/page-designer/api/rest/pages/"));
    }

}
