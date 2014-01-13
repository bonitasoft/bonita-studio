/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.editor;

import java.lang.reflect.Field;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.internal.forms.widgets.FormHeading;
import org.eclipse.ui.internal.forms.widgets.FormsResources;

/**
 * @author Romain Bioteau
 *
 */
public class CustomFormToolkit extends FormToolkit {

	private ToolBarManager toolBarManager;
	
	public CustomFormToolkit(Display display) {
		super(display);
	}
	
	@Override
	public ScrolledForm createScrolledForm(Composite parent) {
		ScrolledForm form = new ScrolledForm(parent, SWT.V_SCROLL
				| SWT.H_SCROLL | getOrientation()){
		

			public org.eclipse.jface.action.IToolBarManager getToolBarManager() {
				if (toolBarManager == null) {
					toolBarManager = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
					ToolBar toolbar = toolBarManager.createControl(getForm().getHead());
					toolbar.setBackground(getBackground());
					toolbar.setForeground(getForeground());
					toolbar.setCursor(FormsResources.getHandCursor());
					getForm().getHead().addDisposeListener(new DisposeListener() {
						public void widgetDisposed(DisposeEvent e) {
							if (toolBarManager != null) {
								toolBarManager.dispose();
								toolBarManager = null;
							}
						}
					});
					try {
						Field f = FormHeading.class.getDeclaredField("toolBarManager") ;
						f.setAccessible(true) ;
						f.set(getForm().getHead(), toolBarManager) ;
					} catch (Exception e1) {
						BonitaStudioLog.error(e1) ;
					}
				}
				return toolBarManager;
				
			}
		} ;
		form.setExpandHorizontal(true);
		form.setExpandVertical(true);
		form.setBackground(getColors().getBackground());
		form.setForeground(getColors().getColor(IFormColors.TITLE));
		form.setFont(JFaceResources.getHeaderFont());
		return form;
	}


}
