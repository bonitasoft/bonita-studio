/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.diagram.custom.figures;

import java.util.Objects;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ImageUtilities;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Display;

/**
 * A vertical label.
 */
public class VerticalLabel extends WrappingLabel {

    // cache the image
    private Image labelImage = null;

    private Color parentBackgroundColor = null;

    /**
     * Creates a new label.
     */
    public VerticalLabel() {
        setOpaque(true);
    }

    /**
     * Invalidate should be called whenever visual attributes change
     */
    @Override
    public void invalidate() {
        disposeImage();
        prefSize = null;
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
    @Override
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

    @Override
    public void paint(final Graphics graphics) {
        if (labelImage != null) {
            disposeImage();
        }
        if (labelImage == null ||
                (!graphics.getBackgroundColor().equals(parentBackgroundColor))) {
            String theText = super.getText();
            if (theText == null || theText.trim().length() == 0) {
                theText = " "; //$NON-NLS-1$
            }
            parentBackgroundColor = graphics.getBackgroundColor();
            try {
                labelImage = createRotatedImageOfString(theText, super.getFont(), getForegroundColor(),
                        graphics
                                .getBackgroundColor(),
                        useGCTransformation());
            } catch (Exception e) {
                BonitaStudioLog.error(e);
                disposeImage();
                super.paintFigure(graphics);
                return;
            }
        }

        Point left = getBounds().getLeft();

        // Calculate the center position for the label
        float center = 0;
        var imageBounds = labelImage.getBounds();
        var parentBounds = getParent().getBounds();
        int labelSize = imageBounds.height;
        float containerCenter = parentBounds.height / 2F;
        center = containerCenter - labelSize / 2F;
        float widthCenter = parentBounds.width / 2F;

        if (center != 0) {
            double x = widthCenter- imageBounds.width / 2F;
            left = new PrecisionPoint(x, center );
        } 
        graphics.drawImage(labelImage, left);
    }

    private boolean useGCTransformation() {
        return Objects.equals(Platform.getOS(), Platform.OS_WIN32);
    }

    /**
     * Fix the issues in the ImageUtilities where the size of the image is the
     * ascent of the font instead of being its height.
     * Also uses the GC for the rotation.
     * The biggest issue is the very idea of using an image. The size of the
     * font should be given by the mapmode, not in absolute device pixel as it
     * does look ugly when zooming in.
     * 
     * @param string
     *        the String to be rendered
     * @param font
     *        the font
     * @param foreground
     *        the text's color
     * @param background
     *        the background color
     * @param useGCTransform
     *        true to use the Transform on the GC object.
     *        false to rely on the homemade algorithm. Transform seems to
     *        not be supported in eclipse-3.2 except on windows.
     *        It is working on macos-3.3M3.
     * @return an Image which must be disposed
     */
    public Image createRotatedImageOfString(String string,
            Font font, Color foreground, Color background,
            boolean useGCTransform) {
        Display display = Display.getCurrent();
        if (display == null) {
            SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);
        }
        
        Dimension strDim = FigureUtilities
                .getTextExtents(string, font);
        Image srcImage = useGCTransform ? new Image(display, strDim.height, strDim.width)
                : new Image(display, strDim.width, strDim.height);
        if (useGCTransform) {
            GC gc = new GC(srcImage);
            Transform transform = new Transform(display);
            transform.rotate(-90);
            gc.setTransform(transform);
            gc.setFont(font);
            gc.setForeground(foreground);
            gc.setBackground(background);
            gc.fillRectangle(gc.getClipping());
            gc.drawText(string, gc.getClipping().x, gc.getClipping().y);
            transform.dispose();
            gc.dispose();
            return srcImage;
        }
        disposeImage();
        return ImageUtilities.createRotatedImageOfString(string, font, foreground, background);
    }

    @Override
    public Rectangle getTextBounds() {
        Rectangle rect = super.getTextBounds();
        // let's move label start to parent's left side
       return new Rectangle(getParent().getBounds().x, rect.y,
                rect.height, rect.width);
    }

    @Override
    protected Dimension calculateLabelSize(Dimension txtSize) {
        return new Dimension(Math.max(txtSize.height, 20), txtSize.width + 4);
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
