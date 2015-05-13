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
package org.bonitasoft.studio.designer.core.bar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.doReturn;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.bar.CustomPageBarResourceFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.io.ByteStreams;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomPageBarResourceFactoryTest {

    @Mock
    private PageDesignerURLFactory pageDesignerURLFactory;

    @Spy
    @InjectMocks
    private CustomPageBarResourceFactory customPageBarResourceFactory;

    @Mock
    private InputStream fakesIs;

    private InputStream is;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        doReturn(new URL("http://localhost:8080/page-designer/export/")).when(pageDesignerURLFactory).exportPage(notNull(String.class));
        is = new ByteArrayInputStream(ByteStreams.toByteArray(this.getClass().getResourceAsStream("/page-Step1.zip")));
        doReturn(is).when(customPageBarResourceFactory).get(notNull(String.class));
    }

    @After
    public void tearDown() throws Exception {
        if (is != null) {
            is.close();
            is = null;
        }
    }

    @Test
    public void should_create_bar_resource_for_custompage() throws Exception {
        final BarResource processFormCustomPage = customPageBarResourceFactory.newBarResource("Pool1--1.0--processForm", "process-form-id");
        final BarResource taskFormCustomPage = customPageBarResourceFactory.newBarResource("Pool1--1.0--StepForm", "step-form-id");

        assertThat(processFormCustomPage.getName()).isEqualTo("customPages/Pool1--1.0--processForm.zip");
        assertThat(taskFormCustomPage.getName()).isEqualTo("customPages/Pool1--1.0--StepForm.zip");
        assertThat(processFormCustomPage.getContent()).isNotEmpty();
    }
}
