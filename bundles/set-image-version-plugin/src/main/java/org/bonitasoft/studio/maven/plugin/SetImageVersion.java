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

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.bonitasoft.studio.maven.plugin.exception.CreateImageException;


/**
 * @author Romain Bioteau
 *
 */
public class SetImageVersion {

    protected static final String DEFAULT_FONT_NAME = "HelveticaNeueLTStd-Cn";

    private String baseImgPath;
    private String fontName;
    private String fontResourcePath;
    private String versionLabel;
    private String outputImageFormat; //bmp,jpg,png..
    private int xLocation;
    private int yLocation;
    private String outputImagePath;
    private boolean isBold = false;

    private float size;
    private String color = "#ffffff"; //white


    public void createImage() throws CreateImageException {
        configure();
        checkArgumentsNotNull(baseImgPath, "baseImgPath");
        checkArgumentsNotNull(fontName, "fontName");
        checkArgumentsNotNull(versionLabel, "versionLabel");
        checkArgumentsNotNull(outputImagePath, "outputImagePath");
        checkArgumentsNotNull(outputImageFormat, "outputImageFormat");

        BufferedImage loadImg;
        try {
            loadImg = loadBaseImage();
        } catch (final IOException e) {
            throw new CreateImageException(e.getMessage(), e);
        }
        final Graphics2D graphics = loadImg.createGraphics();

        Font bontitaBrandingFont = null;
        try {
            bontitaBrandingFont = createCustomFont();
        } catch (final FontFormatException e) {
            throw new CreateImageException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new CreateImageException(e.getMessage(), e);
        }

        graphics.setColor(Color.decode(color));
        graphics.setFont(configureFontStyle(bontitaBrandingFont));
        graphics.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        graphics.drawString(getVersionLabel(), getxLocation(), getyLocation());
        graphics.dispose();

        try {
            writeOutputImage(loadImg);
        } catch (final IOException e) {
            throw new CreateImageException(e.getMessage(), e);
        }
    }

    protected void writeOutputImage(final BufferedImage loadImg) throws IOException {
        ImageIO.write(loadImg, getOutputImageFormat(), new File(getOutputImagePath()));
    }

    private BufferedImage loadBaseImage() throws IOException {
        final File inputFile = getBaseImageFile();
        return ImageIO.read(inputFile);
    }

    private void checkArgumentsNotNull(final Object arg, final String argName) {
        if (arg == null) {
            throw new IllegalArgumentException(argName + " must be set.");
        }
    }

    protected void configure() {
        if (fontName == null) {
            setFontName(DEFAULT_FONT_NAME);
        }
        if (size == 0) {
            setSize(32);
        }
    }

    private InputStream getDefaultFontInputStream() {
        return SetImageVersion.class.getResourceAsStream("/HelveticaNeueLTStd-Cn.otf");
    }

    private File getBaseImageFile() throws FileNotFoundException {
        final File inputFile = new File(getBaseImgPath());
        if (!inputFile.exists()) {
            throw new FileNotFoundException(inputFile.getAbsolutePath());
        }
        return inputFile;
    }

    public String getBaseImgPath() {
        return baseImgPath;
    }

    public void setBaseImgPath(final String baseImgPath) {
        this.baseImgPath = baseImgPath;
    }

    protected Font configureFontStyle(final Font bontitaBrandingFont) {
        final Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>();
        attributes.put(TextAttribute.WIDTH, TextAttribute.WIDTH_SEMI_CONDENSED);
        attributes.put(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);
        if (isBold) {
            attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_MEDIUM);
        } else {
            attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_LIGHT);
        }
        return bontitaBrandingFont.deriveFont(Font.TRUETYPE_FONT, getSize()).deriveFont(attributes);
    }

    private Font createCustomFont() throws FontFormatException, IOException {
        final GraphicsEnvironment ge =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font customFont = getFont(ge);
        if (customFont == null) {
            InputStream fontInputStream = null;
            try {
                if (getFontResourcePath() != null) {
                    final File fontResourceFile = new File(getFontResourcePath());
                    fontInputStream = new FileInputStream(fontResourceFile);
                } else {
                    fontInputStream = getDefaultFontInputStream();
                }
                customFont = Font.createFont(Font.TRUETYPE_FONT, fontInputStream);
                ge.registerFont(customFont);
            } finally {
                if (fontInputStream != null) {
                    fontInputStream.close();
                }
            }
        }

        return customFont;
    }

    private Font getFont(final GraphicsEnvironment ge) {
        for (final Font f : ge.getAllFonts()) {
            if (getFontName().equals(f.getName())) {
                return f;
            }
        }
        return null;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(final String fontName) {
        this.fontName = fontName;
    }

    public String getVersionLabel() {
        return versionLabel;
    }

    public void setVerisonLabel(final String versionLabel) {
        this.versionLabel = versionLabel;
    }

    public String getOutputImagePath() {
        return outputImagePath;
    }

    public void setOutputImagePath(final String outputImagePath) {
        this.outputImagePath = outputImagePath;
    }

    public void setOutputImageFormat(final String outputImageFormat) {
        this.outputImageFormat = outputImageFormat;
    }

    public String getOutputImageFormat() {
        return outputImageFormat;
    }

    public int getxLocation() {
        return xLocation;
    }

    public void setxLocation(final int xLocation) {
        this.xLocation = xLocation;
    }

    public int getyLocation() {
        return yLocation;
    }

    public void setyLocation(final int yLocation) {
        this.yLocation = yLocation;
    }

    public String getFontResourcePath() {
        return fontResourcePath;
    }

    public void setFontResourcePath(final String fontResourcePath) {
        this.fontResourcePath = fontResourcePath;
    }

    public void setColor(final String color) {
        this.color = color;
    }

    public float getSize() {
        return size;
    }

    public void setSize(final float size) {
        this.size = size;
    }

    public boolean isBold() {
        return isBold;
    }

    public void setBold(final boolean isBold) {
        this.isBold = isBold;
    }

}
