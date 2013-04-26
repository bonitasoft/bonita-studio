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
package org.bonitasoft.studio.expression.editor.widget;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.expression.editor.autocompletion.AutoCompletionField;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 * @author Romain Bioteau
 *
 */
public class ContentAssistText extends Composite implements SWTBotConstants {

	private Text textControl;
	private AutoCompletionField autoCompletion;
	private boolean drawBorder = true;
	private ToolBar tb;

	public ContentAssistText(Composite parent, ILabelProvider contentProposalLabelProvider, int style) {
		super(parent, SWT.NONE);
		setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(3, 3).spacing(32, 0).create());
		setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		if ((style & SWT.BORDER) == 0){
			drawBorder = false;
		}else{
			style = style ^ SWT.BORDER;
		}
		
		textControl = new Text(this,style | SWT.SINGLE);
		textControl.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		/*Data for test purpose*/
		textControl.setData(SWTBOT_WIDGET_ID_KEY, SWTBOT_ID_EXPRESSIONVIEWER_TEXT);
		tb = new ToolBar(this, SWT.FLAT | SWT.NO_FOCUS);
		tb.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		tb.setLayoutData(GridDataFactory.swtDefaults().create());
		final ToolItem ti = new ToolItem(tb, SWT.FLAT | SWT.NO_FOCUS);
		ti.setImage(Pics.getImage("resize_S.gif"));
		ti.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if(autoCompletion.getContentProposalAdapter().isProposalPopupOpen()){
					autoCompletion.getContentProposalAdapter().closeProposalPopup();
				}else{
					autoCompletion.getContentProposalAdapter().showProposalPopup();
				}
			}
		});
		addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				if(drawBorder) {
					if(e.data == null & e.x < getBounds().width - 24){ //Hack to avoid clipping effect on toolitem
						paintControlBorder(e);
					}
				}
			}
		});
		textControl.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				if(drawBorder){
					paintControlBorder(e);
				}
			}
		});
		autoCompletion = new AutoCompletionField(textControl, new TextContentAdapter(), contentProposalLabelProvider) ;
	}

	
	protected void paintControlBorder(PaintEvent e) {
		GC gc = e.gc;
		Display display = e.display ;
		if(display!= null && gc != null){
			Control focused = display.getFocusControl() ;
			GC parentGC  =null;
			if(focused == null || !focused.equals(textControl)){
				parentGC = new GC(ContentAssistText.this);
				Rectangle r = ContentAssistText.this.getBounds();
				parentGC.setClipping(getBorderPath(r, display));
				parentGC.setForeground(display.getSystemColor(SWT.COLOR_GRAY));
				parentGC.setLineWidth(1);
				parentGC.drawRectangle(0, 0, r.width-1, r.height-1);
			}else{
				final Composite parent = focused.getParent();
				parentGC = new GC(parent);
				Rectangle r = parent.getBounds();
				parentGC.setClipping(getBorderPath(r, display));
				parentGC.setForeground(e.display.getSystemColor(SWT.COLOR_WIDGET_BORDER));
				parentGC.setLineWidth(1);
				parentGC.drawRectangle(0, 0, r.width-1, r.height-1);
			}
			parentGC.dispose();
		}
	}

	private Path getBorderPath(Rectangle widgetBounds, Display display) {
		final Path path = new Path(display);
		path.addRectangle(0,0,2,widgetBounds.height);//Left border
		path.addRectangle(0,0,widgetBounds.width-1,2);//Top border
		path.addRectangle(0, widgetBounds.height-1, widgetBounds.width-1,2);//Bottom border
		path.addRectangle(widgetBounds.width-1, 0, 2,widgetBounds.height);//Right border
		return path;
	}

	public Text getTextControl() {
		return textControl;
	}

	public AutoCompletionField getAutocompletion() {
		return autoCompletion;
	}

	public ToolBar getToolbar() {
		return tb;
	}

}
