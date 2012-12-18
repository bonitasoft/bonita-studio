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
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.osgi.util.NLS;

/**
 * @author Mickael Istria
 *
 */
public class ExportEntryNameLabelProvider extends ColumnLabelProvider {

	private ExportableContentProvider contentProvider;
	
	public ExportEntryNameLabelProvider(ExportableContentProvider contentProvider) {
		this.contentProvider = contentProvider;
	}
	
	@Override
	public String getText(Object item) {
		ExportEntry entry = (ExportEntry)item;
		if (entry == contentProvider.getUserXPEntry()) {
			return Messages.userXP;
		} else if (entry == contentProvider.getRestWarEntry()) {
			return Messages.restWar;
		} else if (entry == ExportableContentProvider.MY_PROCESSES_CATEGORY) {
			return Messages.myProcessCategory;
		} else {
			AbstractProcess process = entry.getExportArtifact();
			return NLS.bind(Messages.processLabel, process.getName(), process.getVersion());
		}
	}
}
