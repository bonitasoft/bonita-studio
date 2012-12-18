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
package org.bonitasoft.studio.application.actions.wizards.export;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.osgi.util.NLS;

/**
 * @author Mickael Istria
 *
 */
public class ExportableContentProvider implements ITreeContentProvider {
	
	public static ExportEntry MY_PROCESSES_CATEGORY = new ExportEntry(null, false, false);
	protected ExportEntry userXP = new ExportEntry(null, false, true);
	protected ExportEntry restWar = new ExportEntry(null, false, true);
	private List<ExportEntry> res;
	private DiagramFileStore diagramStore;
	
	public ExportableContentProvider(){
		diagramStore = (DiagramFileStore) RepositoryManager.getInstance().getRepositoryStore(DiagramFileStore.class) ;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {
		return new Object[] { userXP, restWar, MY_PROCESSES_CATEGORY };
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement == MY_PROCESSES_CATEGORY) {
			return getAllProcessEntries();
		} else {
			return null;
		}
	}

	/**
	 * @return
	 */
	public ExportEntry[] getAllProcessEntries() {
		if (res == null) {
			res = new ArrayList<ExportEntry>();
			for (AbstractProcess process : diagramStore.getProcesses()) {
				res.add(new ExportEntry(process, false, false));
			}
		}
		return res.toArray(new ExportEntry[res.size()]);
	}

	public void setProcessEntries(List<ExportEntry> processes) {
		this.res = processes ;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	public Object getParent(Object element) {
		if (((ExportEntry)element).getExportArtifact() instanceof AbstractProcess) {
			return MY_PROCESSES_CATEGORY;
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object element) {
		return element == MY_PROCESSES_CATEGORY;
	}

	/**
	 * @param el
	 * @return
	 */
	public ExportEntry getEntry(AbstractProcess el) {
		for (ExportEntry entry : getAllProcessEntries()) {
			AbstractProcess process = entry.getExportArtifact();
			if (process != null && process.getName().equals(el.getName()) && process.getVersion().equals(el.getVersion())) {
				return entry;
			}
		}
		return null;
	}

	/**
	 * @return
	 */
	public ExportEntry getUserXPEntry() {
		return userXP;
	}

	public ExportEntry getElement(String entryName) {
		if(Messages.userXP.equals(entryName)){
			return userXP ;
		} else if (Messages.restWar.equals(entryName)) {
			return restWar;
		}
		for (ExportEntry entry : getAllProcessEntries()) {
			AbstractProcess process = entry.getExportArtifact();
			if (process != null && NLS.bind(Messages.processLabel, process.getName(), process.getVersion()).equals(entryName)) {
				return entry;
			}
		}
		return null;
	}

	/**
	 * @return
	 */
	public ExportEntry getRestWarEntry() {
		return restWar;
	}

}
