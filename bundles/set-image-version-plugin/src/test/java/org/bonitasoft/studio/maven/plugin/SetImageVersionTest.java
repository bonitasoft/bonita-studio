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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;

import java.io.File;

import org.bonitasoft.studio.maven.plugin.exception.CreateImageException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SetImageVersionTest {

    @Spy
    private SetImageVersion setImageVersion;

    private File tmpOutputFile;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        tmpOutputFile = new File(System.getProperty("java.io.tmpdir"), "splash.bmp");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        if (tmpOutputFile.exists()) {
            tmpOutputFile.delete();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void must_have_a_valid_configuration() throws Exception {
        setImageVersion.createImage();
    }

    @Test(expected = IllegalArgumentException.class)
    public void must_have_a_baseImgPath() throws Exception {
        setImageVersion.setBaseImgPath(null);
        setImageVersion.setxLocation(200);
        setImageVersion.setyLocation(200);
        setImageVersion.setVerisonLabel("6.4.0");
        setImageVersion.setOutputImageFormat("bmp");
        setImageVersion.setOutputImagePath(tmpOutputFile.getAbsolutePath());
        setImageVersion.createImage();
    }

    @Test(expected = IllegalArgumentException.class)
    public void must_have_a_versionLabel() throws Exception {
        setImageVersion.setBaseImgPath(new File(SetImageVersionTest.class.getResource("/splash_sp_without_version.bmp").getFile())
        .getAbsolutePath());
        setImageVersion.setxLocation(200);
        setImageVersion.setyLocation(200);
        setImageVersion.setVerisonLabel(null);
        setImageVersion.setOutputImageFormat("bmp");
        setImageVersion.setOutputImagePath(tmpOutputFile.getAbsolutePath());
        setImageVersion.createImage();
    }

    @Test(expected = IllegalArgumentException.class)
    public void must_have_a_outputImageFormat() throws Exception {
        setImageVersion.setBaseImgPath(new File(SetImageVersionTest.class.getResource("/splash_sp_without_version.bmp").getFile())
        .getAbsolutePath());
        setImageVersion.setxLocation(200);
        setImageVersion.setyLocation(200);
        setImageVersion.setVerisonLabel("6.4.0");
        setImageVersion.setOutputImageFormat(null);
        setImageVersion.setOutputImagePath(tmpOutputFile.getAbsolutePath());
        setImageVersion.createImage();
    }

    @Test(expected = IllegalArgumentException.class)
    public void must_have_a_outputImagePath() throws Exception {
        setImageVersion.setBaseImgPath(new File(SetImageVersionTest.class.getResource("/splash_sp_without_version.bmp").getFile())
        .getAbsolutePath());
        setImageVersion.setxLocation(200);
        setImageVersion.setyLocation(200);
        setImageVersion.setVerisonLabel("6.4.0");
        setImageVersion.setOutputImageFormat("bmp");
        setImageVersion.setOutputImagePath(null);
        setImageVersion.createImage();
    }

    @Test
    public void create_image_write_a_valid_file() throws Exception {
        setImageVersion.setBaseImgPath(new File(SetImageVersionTest.class.getResource("/splash_sp_without_version.bmp").getFile())
        .getAbsolutePath());
        setImageVersion.setxLocation(200);
        setImageVersion.setyLocation(200);
        setImageVersion.setVerisonLabel("6.4.0");
        setImageVersion.setOutputImageFormat("bmp");
        setImageVersion.setOutputImagePath(tmpOutputFile.getAbsolutePath());
        setImageVersion.createImage();
        assertThat(tmpOutputFile).exists().canRead();

        setImageVersion.setVerisonLabel("6.4.1");
        setImageVersion.createImage();
        assertThat(tmpOutputFile).exists().canRead();
    }

    @Test
    public void configure_set_valid_output_img_format() throws Exception {
        setImageVersion.configure();
        assertThat(setImageVersion.getFontName()).isEqualTo(SetImageVersion.DEFAULT_FONT_NAME);
    }

    @Test(expected = CreateImageException.class)
    public void create_image_throw_CreateImageException_if_baseImgPath_points_to_invalid_file() throws Exception {
        setImageVersion.setBaseImgPath("/not_existing.bmp");
        setImageVersion.setxLocation(200);
        setImageVersion.setyLocation(200);
        setImageVersion.setVerisonLabel("6.4.0");
        setImageVersion.setOutputImageFormat("bmp");
        setImageVersion.setOutputImagePath(tmpOutputFile.getAbsolutePath());
        setImageVersion.createImage();
    }

    @Test(expected = CreateImageException.class)
    public void create_image_throw_CreateImageException_if_font_is_invalid() throws Exception {
        doNothing().when(setImageVersion).configure();
        final String absolutePath = new File(SetImageVersionTest.class.getResource("/splash_sp_without_version.bmp").getFile())
        .getAbsolutePath();
        setImageVersion.setBaseImgPath(absolutePath);
        setImageVersion.setxLocation(200);
        setImageVersion.setyLocation(200);
        setImageVersion.setVerisonLabel("6.4.0");
        setImageVersion.setOutputImageFormat("bmp");
        setImageVersion.setOutputImagePath(tmpOutputFile.getAbsolutePath());
        setImageVersion.setFontName("invalid");
        setImageVersion.setFontResourcePath(absolutePath);
        setImageVersion.createImage();
    }

}
