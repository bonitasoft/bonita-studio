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
package org.bonitasoft.studio.importer.ui.wizard;

import org.bonitasoft.studio.importer.ImporterFactory;
import org.bonitasoft.studio.importer.i18n.Messages;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Mickael Istria
 *
 */
public class ImportFileWizard extends Wizard {

	private ImportFileWizardPage page;
	private String filePath;
	private ImporterFactory selectedTransfo;
	
	@Override
	public String getWindowTitle() {
		return Messages.importTitle ;
	}
		
	public void addPages() {
		page = new ImportFileWizardPage();
		addPage(page);
	}

	@Override
	public boolean performFinish() {
		this.filePath = page.getSelectedFilePath();
		this.selectedTransfo = page.getSelectedTransfo();
		return true;
	}
	
	@Override
	public boolean canFinish() {
		return page.isPageComplete();
	}

	public String getSelectedFilePath() {
		return this.filePath;
	}

	public ImporterFactory getSelectedTransfo() {
		return this.selectedTransfo;
	}
}
