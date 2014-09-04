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
package org.bonitasoft.studio.maven.plugin;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import org.apache.maven.plugin.MojoExecutionException;
import org.bonitasoft.studio.maven.plugin.exception.CreateImageException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SetImageVersionMojoTest {

    @Spy
    private SetImageVersionMojo setImageVersionMojo;

    @Mock
    private SetImageVersion setImageVersion;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        doReturn(setImageVersion).when(setImageVersionMojo).createSetImageVersion();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_execute_call_createImage() throws Exception {
        setImageVersionMojo.execute();
        verify(setImageVersion).createImage();
    }

    @Test(expected = MojoExecutionException.class)
    public void should_throw_MojoExecutionException_when_createImage_failed() throws Exception {
        doThrow(CreateImageException.class).when(setImageVersion).createImage();
        setImageVersionMojo.execute();
    }

}
