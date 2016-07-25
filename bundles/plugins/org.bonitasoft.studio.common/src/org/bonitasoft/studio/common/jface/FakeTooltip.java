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
package org.bonitasoft.studio.common.jface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Baptiste Mesta
 * 
 */
public class FakeTooltip {

	protected final Control control;
	private final Listener labelListener;
	private final Listener tableListener;

	private String text = "";
	private boolean show;

	/**
	 * 
	 */
	public FakeTooltip(final Control control) {
		this.control = control;
		labelListener = new Listener() {
			public void handleEvent(Event event) {
				Label label = (Label) event.widget;
				Shell shell = label.getShell();
				switch (event.type) {
				case SWT.MouseDown:
					shell.dispose();
					break;
				case SWT.MouseExit:
					if(!show){
						shell.dispose();
					}
					break;
				}
			}
		};
		tableListener = new Listener() {
			Shell tip = null;
			Label label = null;

			public void handleEvent(Event event) {
				switch (event.type) {
				case SWT.Dispose:
				case SWT.KeyDown:
				case SWT.MouseMove: {
					if (!show) {
						if (tip == null)
							break;
						tip.dispose();
						tip = null;
						label = null;
						break;
					}
				}
				case SWT.MouseHover: {
					if (!show) {
						if (tip != null && !tip.isDisposed())
							tip.dispose();
						tip = new Shell(control.getShell(), SWT.ON_TOP | SWT.NO_FOCUS | SWT.TOOL);
						tip.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
						FillLayout layout = new FillLayout();
						layout.marginWidth = 2;
						tip.setLayout(layout);
						label = new Label(tip, SWT.NONE);
						label.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_INFO_FOREGROUND));
						label.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
						label.setText(text);
						label.addListener(SWT.MouseExit, labelListener);
						label.addListener(SWT.MouseDown, labelListener);
						Point size = tip.computeSize(SWT.DEFAULT, SWT.DEFAULT);
						Rectangle rect = control.getBounds();
						Point pt = control.getShell().toDisplay(rect.x, rect.y);
						tip.setBounds(pt.x + rect.width, pt.y - size.y, size.x, size.y);
						tip.setVisible(true);
					}
				}
				}
			}
		};
		control.addListener(SWT.Dispose, tableListener);
		control.addListener(SWT.KeyDown, tableListener);
		control.addListener(SWT.MouseMove, tableListener);
		control.addListener(SWT.MouseHover, tableListener);
	}

	public void setText(String text) {
		this.text = text;
	}

	/**
	 * 
	 */
	public void show() {
		Event event = new Event();
		event.type = SWT.MouseHover;
		tableListener.handleEvent(event);
		show = true;
	}

	public void hide() {
		show = false;
		Event event = new Event();
		event.type = SWT.MouseMove;
		tableListener.handleEvent(event);
	}

}
