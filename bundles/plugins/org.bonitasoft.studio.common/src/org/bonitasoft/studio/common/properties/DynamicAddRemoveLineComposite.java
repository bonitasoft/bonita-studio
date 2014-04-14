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
package org.bonitasoft.studio.common.properties;

import java.util.ArrayList;

import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Baptiste Mesta
 * 
 */
public abstract class DynamicAddRemoveLineComposite extends Composite {

	private Image imageplus;
	private Image imagemoins;
	private Region regionForAdd;
	private ArrayList<Button> removes = new ArrayList<Button>();
	private ArrayList<Control> controls = new ArrayList<Control>();

	protected GridData minusGD;
	private Button addButton;

	/**
	 * @param parent
	 * @param style
	 */
	public DynamicAddRemoveLineComposite(Composite parent, int style) {
		super(parent, style);
		if(getWidgetFactory() != null) {
			this.setBackground(ColorConstants.white);
		}
		this.setLayout(new GridLayout(2, false));

		imageplus = Pics.getImage(PicsConstants.plusBlack);
		imagemoins = Pics.getImage(PicsConstants.remove);

		minusGD = GridDataFactory.swtDefaults().align(SWT.CENTER, SWT.CENTER).create();
		// circle button
		regionForAdd = new Region();
		regionForAdd.add(createCircle(13, 13, 10));

		addButton = createAddButton(this);
		addButton.setLayoutData(GridDataFactory.swtDefaults().span(2, 1).create()) ;
		addButton.addSelectionListener(new SelectionAdapter() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse
			 * .swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				addLine(null);
			}
		});
	}

	/**
	 * @param parent
	 * @return 
	 */
	protected Button createAddButton(Composite parent) {
		Button addButton;
		if(getWidgetFactory() != null){
			addButton = getWidgetFactory().createButton(parent,"", SWT.FLAT);
		} else {
			addButton = new Button(parent,SWT.FLAT);
		}
		addButton.setRegion(regionForAdd);
		addButton.setImage(imageplus);

		return addButton;
	}

	/**
	 * create a circle region (for buttons)
	 * 
	 * @param xOffset
	 * @param yOffset
	 * @param radius
	 * @return
	 */
	static int[] createCircle(int xOffset, int yOffset, int radius) {
		int[] circlePoints = new int[10 * radius];
		for (int loopIndex = 0; loopIndex < 2 * radius + 1; loopIndex++) {
			int xCurrent = loopIndex - radius;
			int yCurrent = (int) Math.sqrt(radius * radius - xCurrent * xCurrent);
			int doubleLoopIndex = 2 * loopIndex;

			circlePoints[doubleLoopIndex] = xCurrent + xOffset;
			circlePoints[doubleLoopIndex + 1] = yCurrent + yOffset;
			circlePoints[10 * radius - doubleLoopIndex - 2] = xCurrent + xOffset;
			circlePoints[10 * radius - doubleLoopIndex - 1] = -yCurrent + yOffset;
		}

		return circlePoints;
	}

	/**
	 * Allow client to create the dynamically added control
	 * 
	 * @param parent
	 * 
	 * @param lineNumber
	 *            the number of the created line
	 * @return 
	 */
	protected abstract Control createLineComposite(Composite parent,Object object);

	public int addLine(Object object) {
		Control lineComposite = createLineComposite(this,object);
		lineComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		controls.add(lineComposite);
		//create remove button
		Button remove = createRemoveButton(this);
		remove.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				int indexOf = removes.indexOf(e.getSource());
				removeLine(indexOf);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		removes.add(remove);
		int addLine =  removes.indexOf(remove);
		layoutComposite();
		lineAdded(addLine);
		bindLine(addLine,object);
		return addLine;
	}

	/**
	 * @param addLine
	 * @param object
	 */
	protected void bindLine(int addLine, Object object) {
		// can be overridden

	}

	/**
	 * @param table2
	 * @return
	 */
	protected Button createRemoveButton(Composite table2) {
		// button to remove the current script line
		Button remove; //$NON-NLS-1$
		if(getWidgetFactory() != null){
			remove = getWidgetFactory().createButton(this,"", SWT.FLAT);
		} else {
			remove = new Button(this,SWT.FLAT);
		}
		remove.setLayoutData(GridDataFactory.fillDefaults().create());
		// circle button
		//		Region region = new Region();
		//		region.add(createCircle(13, 13, 10));
		//
		//		remove.setRegion(region);
		remove.setImage(imagemoins);
		remove.setLayoutData(minusGD);
		//		regions.add(region);

		return remove;
	}

	public void removeLine(int i) {
		int lineNumber = i;
		removes.get(lineNumber).dispose();
		removes.remove(lineNumber);
		controls.get(lineNumber).dispose();
		controls.remove(lineNumber);
		//		regions.get(lineNumber).dispose();
		//		regions.remove(lineNumber);
		layoutComposite();
		lineRemoved(i);
	}
	
	public void removeAllLines() {
		int size = removes.size() ;
		for(Button remove : removes){
			remove.dispose() ;
		}
		removes.clear();
		for(Control control : controls){
			control.dispose() ;
		}
		controls.clear();
		
		layoutComposite();
		for(int i = 0 ; i< size ; i++)
			lineRemoved(i);	
		}

	public Control getLineComposite(int lineNumber) {
		return controls.get(lineNumber);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Widget#dispose()
	 */
	@Override
	public void dispose() {
		super.dispose();
		if (regionForAdd != null && !regionForAdd.isDisposed()) {
			regionForAdd.dispose();
		}
		//		for (Region region : regions) {
		//			if (region != null && !region.isDisposed()) {
		//				region.dispose();
		//			}
		//		}
		for (Control composite : controls) {
			if (!composite.isDisposed()) {
				composite.dispose();
			}
		}
		for (Button toDispose : removes) {
			if (toDispose != null && !toDispose.isDisposed()) {
				toDispose.dispose();
			}
		}
	}


	protected abstract void lineRemoved(int i);
	protected abstract void lineAdded(int i);
	protected abstract TabbedPropertySheetWidgetFactory getWidgetFactory();

	/**
	 * 
	 */
	protected void layoutComposite() {


		this.getParent().getParent().layout();
		Composite top = getTopComposite();
		Composite parent = this;
		while(parent != top && parent != null){
			parent.layout();
			parent = parent.getParent();
		}
	}


	/**
	 * @return
	 */
	protected abstract Composite getTopComposite();

}
