/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.preferences.pages;

import org.bonitasoft.studio.common.jface.BonitaStudioFontRegistry;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public abstract class AbstractBonitaPreferencePage extends FieldEditorPreferencePage {

	public AbstractBonitaPreferencePage() {
		super();
	}

	public AbstractBonitaPreferencePage(int style) {
		super(style);
	}

	public AbstractBonitaPreferencePage(String title, int style) {
		super(title, style);
	}

	public AbstractBonitaPreferencePage(String title, ImageDescriptor image,
			int style) {
		super(title, image, style);
	}

	protected void createTitleBar(String titleLabel, Image image,boolean useSeparator) {

		if(useSeparator){
			new Label(getFieldEditorParent(), SWT.NONE) ;
			Label separator = new Label(getFieldEditorParent(), SWT.SEPARATOR | SWT.HORIZONTAL);
			separator.setLayoutData(GridDataFactory.fillDefaults().span(3, 1).grab(true, false).create()) ;
		}
		Composite composite = new Composite(getFieldEditorParent(), SWT.NONE) ;
		composite.setLayout(new GridLayout(2,false)) ;
		composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create()) ;
	
		Label imageLabel = new Label(composite, SWT.NONE) ;
		imageLabel.setImage(image) ;

		Label title = new Label(composite, SWT.NONE) ;
		title.setText(titleLabel) ;
		title.setFont(BonitaStudioFontRegistry.getPreferenceTitleFont()) ;

	}

}