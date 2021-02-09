/******************************************************************************
 * Copyright (c) 2006, Intalio Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Intalio Inc. - initial API and implementation
 *******************************************************************************/

/**
 * Date Author Changes
 * Jul 12, 2006 hmalphettes Created
 * Nov 2006 Hallvard Traetteberg Fix for caching the image and disposing it
 * correctly.
 **/

package org.bonitasoft.studio.diagram.custom.figures;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ImageUtilities;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.draw2d.ui.text.TextUtilitiesEx;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Display;

/**
 * A vertical label. Rotatable.
 * 
 * @author htraetteberg caching of the image and dispose of it
 * @author vlevytskyy added support for scale
 * @author hmalphettes
 * @author Romain Bioteau center the label for Bonita Studio Lane
 * @author <a href="http://www.intalio.com">&copy; Intalio, Inc.</a>
 */
public class VerticalLabel extends WrappingLabel {

    // cache the image
    private Image labelImage = null;

    private Color parentBackgroundColor = null;

    private double cacheScale = -1;

    /**
     * true when vertical
     * false when standard horizontal label
     */
    private boolean isVertical = true;

    private static boolean ERROR_WHEN_PAINTING = false;

    /**
     * Creates a new label.
     */
    public VerticalLabel() {
        setOpaque(true);
        // setBackgroundColor(ColorConstants.blue);
    }

    /**
     * Creates a new label.
     */
    public VerticalLabel(boolean isVertical) {
        this();
        setVertical(isVertical);
    }

    /**
     * Invalidate should be called whenever visual attributes change
     */
    public void invalidate() {
        disposeImage();
        prefSize = null;
        // minSize = null;
        LayoutManager manager = getLayoutManager();
        if (manager != null) {
            manager.invalidate();
        }
        setValid(false);
    }

    /**
     * Disposes the image prior to being disposed
     * "erased" in the Figures world
     */
    @Override
    public void erase() {
        disposeImage();
        super.erase();
    }

    /**
     * removeNotify will be called whenever the figure is removed from its parent
     */
    public void removeNotify() {
        super.removeNotify();
        disposeImage();
    }

    /**
     * dispose the image
     */
    private void disposeImage() {
        if (labelImage != null) {
            if (!labelImage.isDisposed()) {
                labelImage.dispose();
            }
            labelImage = null;
        }
    }

    /**
     * 
     */
    @Override
    public void paint(final Graphics graphics) {

        // if (!isVertical || ERROR_WHEN_PAINTING) {
        // super.paintFigure(graphics);
        // return;
        // }
        double scale = graphics.getAbsoluteScale();
        if (labelImage != null && cacheScale != scale) {
            disposeImage();
        }
        if (labelImage == null ||
                (!graphics.getBackgroundColor().
                        equals(parentBackgroundColor))) {
            String theText = super.getText();
            if (theText == null || theText.trim().length() == 0) {
                theText = " "; //$NON-NLS-1$
            }
            parentBackgroundColor = graphics.getBackgroundColor();
            try {
                labelImage = createRotatedImageOfString(graphics, theText, super
                        .getFont(), getForegroundColor(), graphics
                        .getBackgroundColor(), false);
            } catch (Exception e) {
                BonitaStudioLog.error(e);
                disposeImage();
                // if there is an error here, stop trying to create the vertical label.
                ERROR_WHEN_PAINTING = true;
                super.paintFigure(graphics);
                return;
            }
            cacheScale = scale;
        }
        // BUGFIX : Label disappearing under MacOS runtime when using popState/pushState
        // graphics.pushState();

        graphics.scale(1 / scale);
        Point left = getBounds().getLeft();

        // Calculate the center position for the label
        float center = 0;
        int labelSize = labelImage.getBounds().height;
        float containerCenter = getParent().getBounds().height / 2F;
        center = containerCenter - labelSize / 2F;
        float widthCenter =  getParent().getBounds().width / 2F;

        if (center != 0) {
            double x = (widthCenter  * scale) - labelImage.getBounds().width/2F;
            left = new Point(isVertical ? x : (left.x * scale), center * scale);
        } else {
            left = new Point(left.x * scale, left.y * scale);
        }

        graphics.drawImage(labelImage, left);
        // graphics.popState();
    }

    /**
     * Fix the issues in the ImageUtilities where the size of the image is the
     * ascent of the font instead of being its height.
     * 
     * Also uses the GC for the rotation.
     * 
     * The biggest issue is the very idea of using an image. The size of the
     * font should be given by the mapmode, not in absolute device pixel as it
     * does look ugly when zooming in.
     * 
     * 
     * @param string
     *            the String to be rendered
     * @param font
     *            the font
     * @param foreground
     *            the text's color
     * @param background
     *            the background color
     * @param useGCTransform
     *            true to use the Transform on the GC object.
     *            false to rely on the homemade algorithm. Transform seems to
     *            not be supported in eclipse-3.2 except on windows.
     *            It is working on macos-3.3M3.
     * @return an Image which must be disposed
     */
    public Image createRotatedImageOfString(Graphics g, String string,
            Font font, Color foreground, Color background,
            boolean useGCTransform) {
        Display display = Display.getCurrent();
        if (display == null) {
            SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);
        }

        List<FontData> fontDatas = new ArrayList<FontData>();
        // take all font datas, mac and linux specific
        for (FontData data : font.getFontData()) {
            FontData data2 = new FontData(data.getName(),
                    (int) (data.getHeight() * g.getAbsoluteScale()), data.getStyle());
            fontDatas.add(data2);
        }
        // create the new font
        Font zoomedFont = new Font(display, fontDatas.toArray(new FontData[fontDatas.size()]));
        // get the dimension in this font
        Dimension strDim = FigureUtilities
                .getTextExtents(string, zoomedFont);
        if (strDim.width == 0 || strDim.height == 0) {
            strDim = FigureUtilities
                    .getTextExtents(string, font);
        }
        Image srcImage = useGCTransform ?
                new Image(display, strDim.height, strDim.width) :
                new Image(display, strDim.width, strDim.height);
        GC gc = new GC(srcImage);
        if (useGCTransform) {
            Transform transform = new Transform(display);
            transform.rotate(-90);

            gc.setTransform(transform);
        }
        gc.setFont(zoomedFont);
        gc.setForeground(foreground);
        gc.setBackground(background);
        gc.fillRectangle(gc.getClipping());
        gc.drawText(string, gc.getClipping().x, gc.getClipping().y
                // - metrics.getLeading()
                );
        gc.dispose();
        if (useGCTransform) {
            srcImage.dispose();
            zoomedFont.dispose();
            return srcImage;
        }
        disposeImage();
        Image rotated = ImageUtilities.createRotatedImage(srcImage);
        srcImage.dispose();
        zoomedFont.dispose();
        return rotated;
    }

    @Override
    public Rectangle getTextBounds() {
        Rectangle rect = super.getTextBounds();

        if (!isVertical) {
            return rect;
        }

        // let's move label start to parent's left side
        Rectangle bounds = new Rectangle(getParent().getBounds().x, rect.y,
                rect.height, rect.width);
        return bounds;
    }

    @Override
    protected Dimension calculateLabelSize(Dimension txtSize) {
        if (isVertical) {
            return new Dimension(Math.max(txtSize.height, 20), txtSize.width + 4);
        } else {
            txtSize.height = txtSize.height + 4;
            return super.calculateLabelSize(txtSize);
        }
    }

    /**
     * @param isVertical
     *            true when the label should in fact be vertical
     *            false to keep the standard horizontal painting of the label.
     */
    public void setVertical(boolean isVertical) {
        if (this.isVertical != isVertical) {
            this.isVertical = isVertical;
            disposeImage();
            revalidate();
        }
    }

    /**
     * @generated NOT
     *            just marking the vertical label as dirty so it will be
     *            repainted.
     */
    @Override
    public void setForegroundColor(Color fg) {
        super.setForegroundColor(fg);
        disposeImage();
        revalidate();
    }

    /**
     * @generated NOT
     *            just marking the vertical label as dirty so it will be
     *            repainted.
     */
    @Override
    public void setFont(Font f) {
        super.setFont(f);
        disposeImage();
        revalidate();
    }
}
