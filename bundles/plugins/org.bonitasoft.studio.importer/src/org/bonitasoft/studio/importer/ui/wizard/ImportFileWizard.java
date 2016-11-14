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

public class ImportFileWizard extends Wizard {

    private ImportFileData importFileData;
    
	@Override
	public String getWindowTitle() {
		return Messages.importTitle ;
	}
		
	@Override
    public void addPages() {
		addPage(createPage());
	}

    protected ImportFileWizardPage createPage() {
        return new ImportFileWizardPage(getImportFileData());
    }

    protected ImportFileData getImportFileData() {
        if (importFileData == null) {
            importFileData = createImportFileData();
        }
        return importFileData;
    }

    protected ImportFileData createImportFileData() {
        return new ImportFileData();
    }

    @Override
	public boolean performFinish() {
		return true;
	}

	public String getSelectedFilePath() {
        return importFileData.getFilePath();
	}

	public ImporterFactory getSelectedTransfo() {
        return importFileData.getImporterFactory();
	}
}
