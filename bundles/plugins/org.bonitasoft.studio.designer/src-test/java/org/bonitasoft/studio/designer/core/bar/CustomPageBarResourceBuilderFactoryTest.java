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
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.common.io.ByteStreams;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomPageBarResourceBuilderFactoryTest {

    @Mock
    private FormBuilder formBuilder;

    @Spy
    @InjectMocks
    private CustomPageBarResourceBuilderFactory customPageBarResourceFactory;



    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
       var content = ByteStreams.toByteArray(CustomPageBarResourceBuilderFactoryTest.class.getResourceAsStream("/page-Step1.zip"));
       doReturn(formBuilder).when(customPageBarResourceFactory).newFormBuilder();
       when(formBuilder.export(anyString())).thenReturn(content);
    }

    @Test
    public void should_create_bar_resource_for_custompage() throws Exception {
        CustomPageBarResourceBuilder customPageBarResourceBuilder = customPageBarResourceFactory.create();
        final BarResource processFormCustomPage = customPageBarResourceBuilder.newBarResource("Pool1--1.0--processForm",
                "process-form-id");
        final BarResource taskFormCustomPage = customPageBarResourceBuilder.newBarResource("Pool1--1.0--StepForm",
                "step-form-id");

        assertThat(processFormCustomPage.getName()).isEqualTo("customPages/Pool1--1.0--processForm.zip");
        assertThat(taskFormCustomPage.getName()).isEqualTo("customPages/Pool1--1.0--StepForm.zip");
        assertThat(processFormCustomPage.getContent()).isNotEmpty();
    }
}
