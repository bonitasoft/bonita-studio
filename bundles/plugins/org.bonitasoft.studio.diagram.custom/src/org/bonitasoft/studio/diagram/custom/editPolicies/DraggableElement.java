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
package org.bonitasoft.studio.diagram.custom.editPolicies;

import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.TreeSearch;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.handles.AbstractHandle;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.gef.ui.internal.l10n.Cursors;
import org.eclipse.swt.graphics.Image;

/**
 * @author Mickael Istria
 *
 */
public class DraggableElement extends AbstractHandle {

	private ToolEntry toolEntry;
	private IFigure layer;
	private Image image;
	private UnspecifiedTypeCreationTool tool;
	private ZoomManager zoomManager;
	private static Image abstractTaskImage = new AdapterFactoryLabelProvider(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE)).getImage(ProcessFactory.eINSTANCE.createActivity()) ; 

	/**
	 * Creates a new draggable figure reprensenting the 'types', and supporting
	 * the creating of a new {@link UnspecifiedTypeCreationTool} on mouse released
	 * @param host
	 * @param toolEntry A tool entry (as one used in Palette) to be used for icons & creation tool
	 * @param layer 
	 */
	public DraggableElement(GraphicalEditPart host, UnspecifiedTypeCreationTool tool, ToolEntry toolEntry, IFigure layer,ZoomManager zoomManager) {
		super(host, new Locator() {

			public void relocate(IFigure target) {
				target.setLocation(new Point(10, 10));
			}
		}, Pics.getOpenedHandCursor());
		this.zoomManager = zoomManager ; 
		this.toolEntry = toolEntry;
		this.tool = tool;
		this.layer = layer;
		setOwner(host);
		setLayoutManager(new StackLayout());
		setSize(16, 16);
		ImageFigure imageFigure = null ;
		if(toolEntry.getId().contains("ServiceTask")){
			imageFigure = new ImageFigure(abstractTaskImage);
		}else{
			image = toolEntry.getSmallIcon().createImage();
			imageFigure = new ImageFigure(image);
		}

		imageFigure.setSize(16, 16);
		add(imageFigure);

		setLocator(new Locator() {

			public void relocate(IFigure target) {
				target.setLocation(new Point(10, 10));
			}
		});
	}

	/**
	 * @see org.eclipse.draw2d.IFigure#findFigureAt(int, int, org.eclipse.draw2d.TreeSearch)
	 */
	public IFigure findFigureAt(int x, int y, TreeSearch search) {
		// return the ConnectionHandle and not the children figures
		if (containsPoint(x, y)) {
			return this;
		}
		return super.findFigureAt(x, y, search);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.handles.AbstractHandle#createDragTracker()
	 */
	@Override
	protected DragTracker createDragTracker() {
		return new DraggableElementCreationTool(tool, this,zoomManager);
	}

	@Override
	public boolean isValid() {
		return true;
	}

	public IFigure getLayer() {
		return this.layer;
	}

	/**
	 * @return
	 */
	public EditPart getHost() {
		return getOwner();
	}

	public ToolEntry getToolEntry() {
		return this.toolEntry;
	}

	public void freeHandle(){
		if(image != null && !image.isDisposed()){
			image.dispose();
		}
	}

}
