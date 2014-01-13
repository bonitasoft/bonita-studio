/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
 *
 */
public class SeverityColumnLabelProvider extends StyledCellLabelProvider
		implements ILabelProvider {

	/**
	 * 
	 */
	public SeverityColumnLabelProvider() {
	}

	/**
	 * @param style
	 */
	public SeverityColumnLabelProvider(int style) {
		super(style);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object element) {
		//Use paint instead of getimage in order to have image drawn in the middle of the cell
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		try {
			return String.valueOf(((Marker)element).getAttribute("severity"));
		} catch (CoreException e) {
			BonitaStudioLog.error(e);
			return "";
		}
	}

	
	@Override
	public void paint(Event event, Object element) {
		super.paint(event, element);
		Marker marker = (Marker) element;
		if(marker!=null && marker.exists()){
			try {
				int severity = (Integer) marker.getAttribute("severity");
				final Image image =  getImageForSeverity(severity);
				Rectangle bounds = ((TableItem) event.item)
						.getBounds(event.index);
				Rectangle imgBounds = image.getBounds();
				bounds.width /= 2;
				bounds.width -= imgBounds.width / 2;
				bounds.height /= 2;
				bounds.height -= imgBounds.height / 2;

				int x = bounds.width > 0 ? bounds.x + bounds.width : bounds.x;
				int y = bounds.height > 0 ? bounds.y + bounds.height : bounds.y;

				event.gc.drawImage(image, x, y);
				
			} catch (CoreException e) {
				BonitaStudioLog.error(e);
			}
		}
	}
	
	/**
	 * 
	 * @param status
	 * @return
	 */
	private Image getImageForSeverity(int status) {
		switch (status) {
		case IMarker.SEVERITY_WARNING: return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK);
		case IMarker.SEVERITY_INFO: return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_INFO_TSK);
		case IMarker.SEVERITY_ERROR: return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK);
		default:break;
		}

		return null;
	}
		
	@Override
	public String getToolTipText(Object element) {
		if(element instanceof Marker && ((Marker) element).exists()){
			try {
				final int severity = (Integer) ((Marker) element).getAttribute("severity");
				switch (severity) {
				case IMarker.SEVERITY_WARNING: return Messages.errorTooltip;
				case IMarker.SEVERITY_INFO: return Messages.infoTooltip;
				case IMarker.SEVERITY_ERROR: return Messages.errorTooltip;
				default:break;
				}

			} catch (CoreException e) {
				BonitaStudioLog.error(e);
			}		
		}
		return super.getToolTipText(element);
	}
}
