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
package org.bonitasoft.studio.common.diagram.palette;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.gef.editparts.SimpleRootEditPart;
import org.eclipse.gef.internal.ui.palette.editparts.ToolEntryEditPart;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteListener;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.gef.ui.palette.customize.PaletteViewerEx;
import org.eclipse.jface.action.MenuManager;

/**
 * @author Romain Bioteau
 *
 */
public class CustomMainPaletteViewer extends PaletteViewerEx {

	private Set<String> filters = new HashSet<String>();
	private String onlyDisplayedEntry;
	protected ToolEntry activeEntry = null;
	private List paletteListeners = new ArrayList();
	private CustomToolPaletteViewer toolPalette;

	public CustomMainPaletteViewer(){
		setEditPartFactory(new CustomPaletteEditPartFactory());
	}

	protected void createDefaultRoot() {
		setRootEditPart(new SimpleRootEditPart(){
			@Override
			protected IFigure createFigure() {
				Figure figure = new Figure();
				figure.setLayoutManager(new StackLayout());
				return figure;
			}
		});
	}


	protected LightweightSystem createLightweightSystem() {
		return new AsyncLightweightSystem();
	}


	public void hidePaletteEntry(String id){
		filters.add(id);
	}

	@Override
	public void addPaletteListener(PaletteListener paletteListener) {
		if (paletteListeners != null)
			paletteListeners.add(paletteListener);
	}

	public void removePaletteListener(PaletteListener paletteListener) {
		paletteListeners.remove(paletteListener);
	}

	@Override
	public void setPaletteRoot(PaletteRoot root) {
		if (root == getPaletteRoot())
			return;
		super.setPaletteRoot(root);
		if (getPaletteRoot() != null) {
			final PaletteRoot filteredPalette = filterPaletteRoot(getPaletteRoot());
			for(Object c : filteredPalette.getChildren()){
				if(c instanceof PaletteDrawer){
					((PaletteDrawer)c).setShowDefaultIcon(false);
					((PaletteDrawer)c).setUserModificationPermission(PaletteDrawer.PERMISSION_NO_MODIFICATION);
				}
			}
			getRootEditPart().setContents(getEditPartFactory().createEditPart(
					getRootEditPart(),filteredPalette));
		}
	}


	@Override
	public void setContextMenu(MenuManager contextMenu) {
	
	}

	public void setActiveTool(ToolEntry newMode) {
		if (newMode == null)
			newMode = getPaletteRoot().getDefaultEntry();

		if(newMode.equals(activeEntry) ){
			fireModeChanged();
			return;
		}
		if (activeEntry != null){
			final ToolEntryEditPart toolEntryEditPart = getToolEntryEditPart(activeEntry);
			if(toolEntryEditPart != null){
				toolEntryEditPart.setToolSelected(false);
			}
		}

		activeEntry = newMode;
		if (activeEntry != null) {
			ToolEntryEditPart editpart = getToolEntryEditPart(activeEntry);
			if (editpart != null) {
				editpart.setToolSelected(true);
			}
		}
		fireModeChanged();

		if(toolPalette != null  && getControl() != null && !getControl().isDisposed()){
			setEditDomain(toolPalette.getEditDomain());
			toolPalette.setActiveTool(newMode);
		}
	}

	/**
	 * @return the entry for the currently active tool
	 */
	public ToolEntry getActiveTool() {
		return activeEntry;
	}

	protected void fireModeChanged() {
		if (paletteListeners == null)
			return;
		for (int listener = 0; listener < paletteListeners.size(); listener++){
			((PaletteListener) paletteListeners.get(listener)).activeToolChanged(this, activeEntry);
		}
	}

	protected ToolEntryEditPart getToolEntryEditPart(ToolEntry entry) {
		return (ToolEntryEditPart) getEditPartRegistry().get(entry);
	}

	private PaletteRoot filterPaletteRoot(PaletteRoot paletteRoot) {
		PaletteRoot newRoot = new PaletteRoot();
		for(Object child : paletteRoot.getChildren()){
			if(child instanceof PaletteEntry){
				final String id = ((PaletteEntry) child).getId();
				if(onlyDisplayedEntry != null && onlyDisplayedEntry.equals(id)) {
					newRoot.add((PaletteEntry) child);
					break;
				}else if(!filters.contains(id)){
					newRoot.add((PaletteEntry) child);
				}
			}
		}

		return newRoot;
	}


	public void showOnlyPaletteEntry(String onlyDisplayedEntry) {
		this.onlyDisplayedEntry = onlyDisplayedEntry;
	}

	public void setToolPaletteViewer(CustomToolPaletteViewer toolPalette) {
		this.toolPalette = toolPalette;
	}
}
