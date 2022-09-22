package org.bonitasoft.studio.diagram.custom.figures;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;


public class SlideMenuBarFigure extends RoundedRectangle {
	
	static final Color THIS_BACK = new Color(null, 246, 246, 237);
	
	private List<IFigure> elements = new ArrayList<IFigure>();
	private IFigure parent;

	public SlideMenuBarFigure(IFigure parent){
		this(parent,20,20);
	}
	

	
	/**
	 * @param composite
	 * @param i
	 * @param j
	 */
	public SlideMenuBarFigure(IFigure parent, int w, int h) {
		super();
		this.parent = parent;
		setBackgroundColor(THIS_BACK);
		getBounds().setSize(20,70);
		setXOR(true);
		setCornerDimensions(new Dimension(0,0));
		setAlpha(0);
		setSize(w,h);
		setLocation(parent.getBounds().getCopy().getTopLeft());
		this.parent.add(this);
	}



	public void addToMenu(IFigure menuEntry){
		
		elements.add(menuEntry);
		
		for(IFigure elem : elements){
			elem.setSize(new Dimension(getSize().width/elements.size(),getSize().height));
			if(elements.indexOf(elem) >0){
				elem.setLocation(new Point(getBounds().getTopLeft().x+elem.getSize().width,getBounds().getTopLeft().y));
			}else{
				elem.setLocation(new Point(getBounds().getTopLeft().x,getBounds().getTopLeft().y));
			}
			this.add(elem);
		}
	
	}

}
