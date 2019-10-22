/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
 
package org.bonitasoft.studio.diagram.custom.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformViewport;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.ScrollPaneLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemsAwareFreeFormLayer;
import org.eclipse.gmf.runtime.diagram.ui.figures.ShapeCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.layout.FreeFormLayoutEx;
import org.eclipse.gmf.runtime.draw2d.ui.internal.figures.AnimatableScrollPane;
import org.eclipse.gmf.runtime.draw2d.ui.internal.figures.OverlayScrollPaneLayout;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;

/**
 * @author Romain Bioteau
 *
 */
public class CustomShapeCompartmentFigure extends ShapeCompartmentFigure {

	public CustomShapeCompartmentFigure(String title, IMapMode mm) {
		super(title, mm);
	}
	
	@Override
	 protected void configureFigure(IMapMode mm) {
	        ScrollPane scrollpane = getScrollPane();
	        if(scrollpane==null){
	            scrollpane = scrollPane = new ScrollPane();
	        }
	        scrollpane.setViewport(new FreeformViewport());
	        scrollPane.setScrollBarVisibility(ScrollPane.NEVER);
	        scrollPane.setVerticalScrollBar(null);
	        scrollpane.setLayoutManager(new ScrollPaneLayout() );
	
	        IFigure contents = new BorderItemsAwareFreeFormLayer();
	        contents.setLayoutManager(new FreeFormLayoutEx());
	        scrollpane.setContents(contents);
	        
	        int MB = mm.DPtoLP(0);
	        scrollpane.setBorder(new MarginBorder(MB, MB,MB, MB));
	        int W_SZ = mm.DPtoLP(10);
	        int H_SZ = mm.DPtoLP(10);
	        scrollpane.setMinimumSize(new Dimension(W_SZ, H_SZ));
	    
	        this.setFont(FONT_TITLE);
	    }   
	
	@Override
	 protected AnimatableScrollPane createScrollpane(IMapMode mm) {
	        scrollPane = new AnimatableScrollPane();
	        scrollPane.getViewport().setContentsTracksWidth(false);
	        scrollPane.getViewport().setContentsTracksHeight(false);
	        scrollPane.setLayoutManager(new OverlayScrollPaneLayout());
	        scrollPane.setVerticalScrollBarVisibility(ScrollPane.NEVER);
	        scrollPane.setHorizontalScrollBarVisibility(ScrollPane.NEVER);
	        scrollPane.setContents(new Figure());
	        int half_minClient = getMinClientSize()/2;
	        scrollPane.getContents().setBorder(
	                new MarginBorder(1, half_minClient, 1, half_minClient));            
	        return (AnimatableScrollPane)scrollPane;

	    }    

}
