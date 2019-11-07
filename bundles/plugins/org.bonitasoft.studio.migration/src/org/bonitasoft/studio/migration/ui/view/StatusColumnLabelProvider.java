/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.migration.ui.view;

import org.bonitasoft.studio.migration.MigrationPlugin;
import org.bonitasoft.studio.migration.i18n.Messages;
import org.bonitasoft.studio.migration.model.report.Change;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 *
 */
public class StatusColumnLabelProvider extends StyledCellLabelProvider implements ILabelProvider {
	
	@Override
	protected void paint(Event event, Object element) {
		super.paint(event, element);
		if(element instanceof Change){
			int status =  ((Change) element).getStatus();
			final Image image =  getImageForStatus(status);
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
			
		}
	}
	
	@Override
	public String getToolTipText(Object element) {
		int status =  ((Change) element).getStatus();
		switch (status) {
		case IStatus.OK: return Messages.okStatusTooltip;
		case IStatus.WARNING: return Messages.warningStatusTooltip;
		case IStatus.ERROR: return Messages.errorStatusTooltip;
		default:break;
		}
		return super.getToolTipText(element);
	}

	@Override
	public Image getImage(Object element) {
		return null;
	}

	@Override
	public String getText(Object element) {
		return String.valueOf(((Change)element).getStatus());
	}

	private Image getImageForStatus(int status) {
		switch (status) {
		case IStatus.OK: return Pics.getImage("valid.png",MigrationPlugin.getDefault());
		case IStatus.WARNING: return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK);
		case IStatus.INFO: return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_INFO_TSK);
		case IStatus.ERROR: return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK);
		default:break;
		}

		return null;
	}

}
