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
package org.bonitasoft.studio.application.actions;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIPlugin;
import org.eclipse.gmf.runtime.gef.ui.internal.GefDebugOptions;
import org.eclipse.gmf.runtime.gef.ui.internal.GefPlugin;
import org.eclipse.gmf.runtime.gef.ui.internal.GefStatusCodes;
import org.eclipse.gmf.runtime.gef.ui.palette.customize.IPaletteState;
import org.eclipse.gmf.runtime.gef.ui.palette.customize.PaletteDrawerState;
import org.eclipse.gmf.runtime.gef.ui.palette.customize.PaletteEntryState;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.XMLMemento;

/**
 * @author Baptiste Mesta
 * 
 */
@SuppressWarnings("restriction")
public class SwitchPaletteMode extends AbstractHandler {

	private IPreferenceStore preferences = DiagramUIPlugin.getInstance().getPreferenceStore();
	/**
	 * id used in the workspace preference store for palette customizations
	 */
	private static final String PALETTE_CUSTOMIZATIONS_ID = "org.eclipse.gmf.runtime.diagram.ui.paletteCustomizations"; //$NON-NLS-1$
	private static final String SIMPLE = "simple";
	private static final String ICONES = "icones";
//	private static final String FULL = "full";
	private final List<Integer> simplePaletteItems = new ArrayList<Integer>();

	/**
	 * Matches each palette entry with a palette state.
	 */
	private HashMap<PaletteEntry, IPaletteState> paletteStates = new HashMap<PaletteEntry, IPaletteState>();

	/**
	 * 
	 */
	public SwitchPaletteMode() {
		//step
		simplePaletteItems.add(0);
		//gate
		simplePaletteItems.add(5);
		//transition
		simplePaletteItems.add(8);
		//pool
		simplePaletteItems.add(12);
		//lane
		simplePaletteItems.add(13);
		//text annotation
		simplePaletteItems.add(14);
		//event
		simplePaletteItems.add(32);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.
	 * ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
//		String parameter = event.getParameter("org.bonitasoft.studio.application.switch.advanced");
//
//		IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
//		if (activeEditor instanceof ProcessDiagramEditor) {
//			String mode;
//			if (parameter != null) {
//				mode = parameter;
//			} else {
//				mode = SIMPLE;
//			}
//
//			ProcessDiagramEditor processEditor = (ProcessDiagramEditor) activeEditor;
//			PaletteRoot paletteRoot = processEditor.getPaletteRoot();
//			PaletteViewer paletteViewer = ((DiagramEditDomain) processEditor.getDiagramEditDomain()).getPaletteViewer();
//			if (mode.equals(SIMPLE)) {
//				paletteViewer.getPaletteViewerPreferences().setLayoutSetting(PaletteViewerPreferences.LAYOUT_LIST);
//				List<?> children = ((PaletteContainer) paletteRoot.getChildren().get(1)).getChildren();
//				for (int i = 0; i < children.size(); i++) {
//					Object item = children.get(i);
//					if (item instanceof PaletteEntry) {
//						PaletteEntry entry = (PaletteEntry) item;
//						storePaletteState(entry);
//						entry.setVisible(simplePaletteItems.contains(i));
//					}
//
//				}
//			} else {
//				if (mode.equals(ICONES)) {
//					paletteViewer.getPaletteViewerPreferences().setLayoutSetting(PaletteViewerPreferences.LAYOUT_ICONS);
//				} else {
//					paletteViewer.getPaletteViewerPreferences().setLayoutSetting(PaletteViewerPreferences.LAYOUT_LIST);
//				}
//				List<?> children = ((PaletteContainer) paletteRoot.getChildren().get(1)).getChildren();
//				for (int i = 0; i < children.size(); i++) {
//					Object item = children.get(i);
//					if (item instanceof PaletteEntry) {
//						PaletteEntry entry = (PaletteEntry) item;
//						storePaletteState(entry);
//						entry.setVisible(true);
//					}
//
//				}
//			}
//			save();
//			paletteViewer.getControl().getParent().layout();
//		}

		return null;
	}

	/**
	 * Stores the palette state for a given palette entry if the state has not
	 * yet been stored.
	 * 
	 * @param entry
	 *            the palette entry
	 */
	private void storePaletteState(PaletteEntry entry) {
		IPaletteState paletteState = createPaletteState(entry);
		paletteState.storeState();
		paletteStates.put(entry, paletteState);
	}

	/**
	 * Creation factory method for the <code>IPaletteState</code>. Clients may
	 * override to provide custom <code>IPaletteStates</code>.
	 * 
	 * @param entry
	 *            the palette entry
	 * @return a new <code>IPaletteState</code> instance.
	 */
	protected IPaletteState createPaletteState(PaletteEntry entry) {
		if (entry instanceof PaletteDrawer) {
			return new PaletteDrawerState((PaletteDrawer) entry);
		} else {
			return new PaletteEntryState(entry);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		if(PlatformUI.isWorkbenchRunning() && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null){
			return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() instanceof ProcessDiagramEditor;
		}
		return false;
	}

	public void save() {
		if (paletteStates.isEmpty()) {
			return;
		}

		// If there are already existing palette customizations we will add to
		// them, otherwise, create a new XML memento which makes it easy to save
		// the customizations in a tree format.
		XMLMemento rootMemento = getExistingCustomizations();
		if (rootMemento == null) {
			rootMemento = XMLMemento.createWriteRoot(PALETTE_CUSTOMIZATIONS_ID);
		}
		for (Iterator<Entry<PaletteEntry, IPaletteState>> iterator = paletteStates.entrySet().iterator(); iterator.hasNext();) {
			Entry<PaletteEntry, IPaletteState> entry = iterator.next();
			IMemento memento = getMementoForEntry(rootMemento, entry.getKey());
			if (memento != null) {
				entry.getValue().storeChangesInMemento(memento);
			}
		}

		StringWriter writer = new StringWriter();
		try {
			rootMemento.save(writer);

			if (preferences != null) {
				preferences.setValue(PALETTE_CUSTOMIZATIONS_ID, writer.toString());
			}
		} catch (IOException e) {
			Trace.catching(GefPlugin.getInstance(), GefDebugOptions.EXCEPTIONS_CATCHING, getClass(),
					"Problem saving the XML memento when saving the palette customizations.", //$NON-NLS-1$
					e);
			Log.warning(GefPlugin.getInstance(), GefStatusCodes.IGNORED_EXCEPTION_WARNING,
					"Problem saving the XML memento when saving the palette customizations.", //$NON-NLS-1$
					e);
		}

		paletteStates.clear();
	}

	/**
	 * Retrieves the root memento from the workspace preferences if there were
	 * existing palette customizations.
	 * 
	 * @return the root memento if there were existing customizations; null
	 *         otherwise
	 */
	private XMLMemento getExistingCustomizations() {
		if (preferences != null) {
			String sValue = preferences.getString(PALETTE_CUSTOMIZATIONS_ID);
			if (sValue != null && sValue.length() != 0) { //$NON-NLS-1$
				try {
					XMLMemento rootMemento = XMLMemento.createReadRoot(new StringReader(sValue));
					return rootMemento;
				} catch (WorkbenchException e) {
					Trace.catching(GefPlugin.getInstance(), GefDebugOptions.EXCEPTIONS_CATCHING, getClass(),
							"Problem creating the XML memento when saving the palette customizations.", //$NON-NLS-1$
							e);
					Log.warning(GefPlugin.getInstance(), GefStatusCodes.IGNORED_EXCEPTION_WARNING,
							"Problem creating the XML memento when saving the palette customizations.", //$NON-NLS-1$
							e);
				}
			}
		}
		return null;
	}

	/**
	 * Given the rootMemento, gets the memento that already exists for the
	 * palette entry or creates a new one in the rootMemento (and the necessary
	 * palette container mementos) if one does not exist yet. The root memento's
	 * tree structure matches that of the palette root. If a palette entry in
	 * stack A, in drawer B is customized, the root memento will have a child
	 * memento for drawer B which has a child memento for stack A which has a
	 * child memento for the entry. The memento's use the palette entry's id.
	 * 
	 * @param rootMemento
	 *            the root memento representing the palette root
	 * @param paletteEntry
	 *            the palette entry for which a memento should be retrieved or
	 *            created
	 * @return returns the memento that already exists for the palette entry or
	 *         creates a new one in the rootMemento if one does not exist yet or
	 *         null if the memento could not be created (most likely because the
	 *         palete id is not acceptable).
	 */
	private IMemento getMementoForEntry(IMemento rootMemento, PaletteEntry paletteEntry) {

		ArrayList<String> idList = new ArrayList<String>();
		idList.add(paletteEntry.getId());

		PaletteContainer parent = paletteEntry.getParent();
		while (parent != null && !PaletteRoot.PALETTE_TYPE_ROOT.equals(parent.getType())) {
			idList.add(parent.getId());
			parent = parent.getParent();
		}

		// go through ids in reverse order and create the mementos as necessary
		IMemento containerMemento = rootMemento;
		for (int i = idList.size() - 1; i >= 0; i--) {
			String id = idList.get(i);
			IMemento memento = containerMemento.getChild(id);
			if (memento == null) {
				try {
					memento = containerMemento.createChild(id);
				} catch (Exception e) {
					Trace.catching(GefPlugin.getInstance(), GefDebugOptions.EXCEPTIONS_CATCHING, getClass(),
							"Invalid palette id encountered when saving the palette customizations.", //$NON-NLS-1$
							e);
					Log.warning(GefPlugin.getInstance(), GefStatusCodes.IGNORED_EXCEPTION_WARNING,
							"Invalid palette id encountered when saving the palette customizations.", //$NON-NLS-1$
							e);
					return null;
				}
			}
			containerMemento = memento;
		}

		return containerMemento;
	}

}
