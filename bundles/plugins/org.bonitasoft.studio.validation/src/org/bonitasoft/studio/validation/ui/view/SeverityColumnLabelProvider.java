/**
 * Copyright (C) 2013-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.validation.ui.view;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.internal.resources.Marker;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * @author Florine Boudin
 */
public class SeverityColumnLabelProvider extends StyledCellLabelProvider
        implements ILabelProvider {

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(final Object element) {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(final Object element) {
        try {
            return String.valueOf(((Marker) element).getAttribute("severity"));
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
            return "";
        }
    }

    @Override
    public void paint(final Event event, final Object element) {
        super.paint(event, element);
        final Marker marker = (Marker) element;
        if (marker != null && marker.exists()) {
            try {
                final int severity = (Integer) marker.getAttribute("severity");
                final Image image = getImageForSeverity(severity);
                final Rectangle bounds = ((TableItem) event.item)
                        .getBounds(event.index);
                final Rectangle imgBounds = image.getBounds();
                bounds.width /= 2;
                bounds.width -= imgBounds.width / 2;
                bounds.height /= 2;
                bounds.height -= imgBounds.height / 2;

                final int x = bounds.width > 0 ? bounds.x + bounds.width : bounds.x;
                final int y = bounds.height > 0 ? bounds.y + bounds.height : bounds.y;

                event.gc.drawImage(image, x, y);

            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    /**
     * @param status
     * @return
     */
    private Image getImageForSeverity(final int status) {
        switch (status) {
            case IMarker.SEVERITY_WARNING:
                return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK);
            case IMarker.SEVERITY_INFO:
                return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_INFO_TSK);
            case IMarker.SEVERITY_ERROR:
                return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK);
            default:
                break;
        }

        return null;
    }

    @Override
    public String getToolTipText(final Object element) {
        if (element instanceof Marker && ((Marker) element).exists()) {
            try {
                final int severity = (Integer) ((Marker) element).getAttribute("severity");
                switch (severity) {
                    case IMarker.SEVERITY_WARNING:
                        return Messages.warningTooltip;
                    case IMarker.SEVERITY_INFO:
                        return Messages.infoTooltip;
                    case IMarker.SEVERITY_ERROR:
                        return Messages.errorTooltip;
                    default:
                        break;
                }

            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        return super.getToolTipText(element);
    }
}
