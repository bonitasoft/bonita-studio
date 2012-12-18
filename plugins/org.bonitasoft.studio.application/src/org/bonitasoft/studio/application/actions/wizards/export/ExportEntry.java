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

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.osgi.util.NLS;

public final class ExportEntry {
	private AbstractProcess exportArtifact;
	private boolean exportBar;
	private boolean exportWar;
	
	public ExportEntry(AbstractProcess exportArtifact, boolean exportBar, boolean exportWar) {
		this.exportArtifact = exportArtifact;
		this.exportBar = exportBar;
		this.exportWar = exportWar;
	}
	
	public AbstractProcess getExportArtifact() {
		return exportArtifact;
	}

	public boolean getExportBAR() {
		return exportBar;
	}

	/**
	 * @param value
	 */
	public void setExportBAR(boolean value) {
		this.exportBar = value;
	}

	/**
	 * @return
	 */
	public boolean getExportWAR() {
		return this.exportWar;
	}

	/**
	 * @param value
	 */
	public void setExportWAR(Boolean value) {
		this.exportWar = value;
	}
	
	@Override
	public String toString() {
		if (this == ExportableContentProvider.MY_PROCESSES_CATEGORY) {
			return Messages.myProcessCategory;
		} else if(exportArtifact != null){
			return NLS.bind(Messages.processLabel, exportArtifact.getName(), exportArtifact.getVersion());
		}else{
			return super.toString() ;
		}
	}
}