/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.actions.wizards.export;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class CheckboxLabelProvider extends LabelProvider implements ITableLabelProvider {
	private static final String CHECKED_KEY = "CHECKED";
	private static final String UNCHECK_KEY = "UNCHECKED";
	private ExportableContentProvider contentProvider;

	public CheckboxLabelProvider(ColumnViewer viewer,ExportableContentProvider contentProvider) {
		if (JFaceResources.getImageRegistry().getDescriptor(CHECKED_KEY) == null) {
			JFaceResources.getImageRegistry().put(UNCHECK_KEY,
					makeShot(viewer.getControl(), false));
			JFaceResources.getImageRegistry().put(CHECKED_KEY,
					makeShot(viewer.getControl(), true));
		}
		this.contentProvider =contentProvider ;
	}


	public String getColumnText(Object element, int columnIndex) {
		if(columnIndex == 0 ){
			ExportEntry entry = (ExportEntry)element;
			if (entry == contentProvider.getUserXPEntry()) {
				return Messages.userXP;
			} else if (entry == contentProvider.getRestWarEntry()) {
				return Messages.restWar;
			} else if (entry == ExportableContentProvider.MY_PROCESSES_CATEGORY) {
				return Messages.myProcessCategory;
			} else {
				AbstractProcess process = entry.getExportArtifact();
				return NLS.bind(Messages.processLabel, process.getName(), process.getVersion());
			}
		}else{
			return "";
		}
	}
	
	private Image makeShot(Control control, boolean type)
	{
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


	public Image getColumnImage(Object element, int columnIndex) {
		if(columnIndex != 0 ){
			if (element instanceof ExportEntry) {
				if(!showNothing(element,columnIndex)){
					if (columnIndex == 1) {
						if(((ExportEntry)element).getExportBAR()){
							return JFaceResources.getImageRegistry().get(CHECKED_KEY);
						}else{
							return JFaceResources.getImageRegistry().get(UNCHECK_KEY);
						}
					} 
					
					if(columnIndex == 2){
						if(((ExportEntry)element).getExportWAR()){
							return JFaceResources.getImageRegistry().get(CHECKED_KEY);
						}else{
							return JFaceResources.getImageRegistry().get(UNCHECK_KEY);
						}
					}
				}
			}
		}
		return null ;
	}

	public boolean showNothing(Object element, int columIndex ){
		if(columIndex == 2){
			return element.equals(ExportableContentProvider.MY_PROCESSES_CATEGORY);
		}else if(columIndex == 1){
			return element.equals(ExportableContentProvider.MY_PROCESSES_CATEGORY) ||
				element.equals(contentProvider.getUserXPEntry()) ||
				element.equals(contentProvider.getRestWarEntry());
		}else{
			return false ;
		}
	}
}
