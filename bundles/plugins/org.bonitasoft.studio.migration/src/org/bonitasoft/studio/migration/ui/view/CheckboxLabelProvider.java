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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.migration.ui.view;

import org.bonitasoft.studio.migration.model.report.Change;
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
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

public class CheckboxLabelProvider extends StyledCellLabelProvider implements ILabelProvider{

	private static final String CHECKED_KEY = "checkedKey";//NON-NLS-1
	private static final String UNCHECK_KEY = "uncheckKey";//NON-NLS-1
	
	public CheckboxLabelProvider(Control control){
		if (JFaceResources.getImageRegistry().getDescriptor(CHECKED_KEY) == null) {
			JFaceResources.getImageRegistry().put(UNCHECK_KEY,
					makeShot(control, false));
			JFaceResources.getImageRegistry().put(CHECKED_KEY,
					makeShot(control, true));
		}
	}
	

	@Override
	public Image getImage(Object element) {
		return null;
	}

	@Override
	public String getText(Object element) {
		if(element instanceof Change){
			return Boolean.valueOf(((Change) element).isReviewed()).toString();
		}
		return Boolean.FALSE.toString();
	}
	
	private Image makeShot(Control control, boolean type){
		// Hopefully no platform uses exactly this color because we'll make
		// it transparent in the image.
		Color greenScreen = new Color(control.getDisplay(), 222, 223, 224);

		Shell shell = new Shell(control.getShell(), SWT.NO_TRIM);

		// otherwise we have a default gray color
		shell.setBackground(greenScreen);

		Button button = new Button(shell, SWT.CHECK);
		button.setBackground(greenScreen);
		button.setSelection(type);

		// otherwise an image is located in a corner
		button.setLocation(1, 1);
		Point bsize = button.computeSize(SWT.DEFAULT, SWT.DEFAULT);

		// otherwise an image is stretched by width
		bsize.x = Math.max(bsize.x - 1, bsize.y - 1);
		bsize.y = Math.max(bsize.x - 1, bsize.y - 1);
		button.setSize(bsize);
		shell.setSize(bsize);

		shell.open();
		GC gc = new GC(shell);
		Image image = new Image(control.getDisplay(), bsize.x, bsize.y);
		gc.copyArea(image, 0, 0);
		gc.dispose();
		shell.close();

		ImageData imageData = image.getImageData();
		imageData.transparentPixel = imageData.palette.getPixel(greenScreen
				.getRGB());

		return new Image(control.getDisplay(), imageData);
	}


	@Override
	protected void paint(Event event, Object element) {
		super.paint(event, element);
		Image image = null;
		Change change = (Change)element;
		if(change.isReviewed()){
			image = JFaceResources.getImage(CHECKED_KEY);
		} else {
			image = JFaceResources.getImage(UNCHECK_KEY);
		}
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
