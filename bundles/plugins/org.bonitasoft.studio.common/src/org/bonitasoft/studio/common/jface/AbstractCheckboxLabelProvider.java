/**
 * Copyright (C) 2012-2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.jface;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TreeItem;

public abstract class AbstractCheckboxLabelProvider extends StyledCellLabelProvider implements ILabelProvider {

    protected static final String CHECKED_KEY = "checkedKey";// NON-NLS-1

    protected static final String UNCHECK_KEY = "uncheckKey";// NON-NLS-1

    protected static final String DISABLED_CHECKED_KEY = "checkedKeyDisabled";// NON-NLS-1

    protected static final String DISABLED_UNCHECKED_KEY = "uncheckKeyDisabled";// NON-NLS-1

    public AbstractCheckboxLabelProvider() {
        if (JFaceResources.getImageRegistry().getDescriptor(UNCHECK_KEY) == null) {
            JFaceResources.getImageRegistry().put(UNCHECK_KEY,
                    makeShot(false, true));
        }
        if (JFaceResources.getImageRegistry().getDescriptor(CHECKED_KEY) == null) {
            JFaceResources.getImageRegistry().put(CHECKED_KEY,
                    makeShot(true, true));
        }
        if (JFaceResources.getImageRegistry().getDescriptor(DISABLED_CHECKED_KEY) == null) {
            JFaceResources.getImageRegistry().put(DISABLED_CHECKED_KEY,
                    makeShot(true, false));
        }
        if (JFaceResources.getImageRegistry().getDescriptor(DISABLED_UNCHECKED_KEY) == null) {
            JFaceResources.getImageRegistry().put(DISABLED_UNCHECKED_KEY,
                    makeShot(false, false));
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(final Object element) {
        if (isSelected(element)) {
            if (isEnabled(element)) {
                return JFaceResources.getImage(CHECKED_KEY);
            } else {
                return JFaceResources.getImage(DISABLED_CHECKED_KEY);
            }

        }
        return isEnabled(element) ? JFaceResources.getImage(UNCHECK_KEY) : JFaceResources.getImage(DISABLED_UNCHECKED_KEY);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(final Object element) {
        return ((Boolean) isSelected(element)).toString();
    }

    protected abstract boolean isSelected(Object element);

    protected boolean isEnabled(final Object element) {
        return true;
    }

    private Image makeShot(final boolean type, final boolean enabled) {
        // Hopefully no platform uses exactly this color because we'll make
        // it transparent in the image.
        final Display display = Display.getDefault();
        final Color greenScreen = new Color(display, 222, 223, 224);

        final Shell shell = new Shell(SWT.NO_TRIM);
        shell.setVisible(false);
        shell.setLocation(42, 42);
        // otherwise we have a default gray color
        shell.setBackground(greenScreen);

        final Button button = new Button(shell, SWT.CHECK);
        button.setBackground(greenScreen);
        button.setSelection(type);
        if (!enabled) {
            button.setEnabled(false);
        }

        // otherwise an image is located in a corner
        button.setLocation(1, 1);
        final Point bsize = button.computeSize(SWT.DEFAULT, SWT.DEFAULT);

        // otherwise an image is stretched by width
        bsize.x = Math.max(bsize.x - 1, bsize.y - 1);
        bsize.y = Math.max(bsize.x - 1, bsize.y - 1);
        button.setSize(bsize);
        shell.setSize(bsize);

        shell.open();
        final GC gc = new GC(shell);
        final Image image = new Image(display, bsize.x, bsize.y);
        gc.copyArea(image, 0, 0);
        gc.dispose();
        shell.close();

        final ImageData imageData = image.getImageData();
        imageData.transparentPixel = imageData.palette.getPixel(greenScreen
                .getRGB());

        return new Image(display, imageData);
    }

    @Override
    protected void paint(final Event event, final Object element) {

        final Image img = getImage(element);

        if (img != null) {
            Rectangle bounds;

            if (event.item instanceof TableItem) {
                bounds = ((TableItem) event.item).getBounds(event.index);
            } else {
                bounds = ((TreeItem) event.item).getBounds(event.index);
            }

            final Rectangle imgBounds = img.getBounds();
            bounds.width /= 2;
            bounds.width -= imgBounds.width / 2;
            bounds.height /= 2;
            bounds.height -= imgBounds.height / 2;

            final int x = bounds.width > 0 ? bounds.x + bounds.width : bounds.x;
            final int y = bounds.height > 0 ? bounds.y + bounds.height : bounds.y;

            if (SWT.getPlatform().equals("carbon")) {
                event.gc.drawImage(img, x + 2, y - 1);
            } else {
                event.gc.drawImage(img, x, y - 1);
            }

        }

    }

    @Override
    protected void measure(final Event event, final Object element) {
        final Image image = getImage(element);
        if (image != null) {
            event.height = image.getBounds().height;
        }

    }

}
