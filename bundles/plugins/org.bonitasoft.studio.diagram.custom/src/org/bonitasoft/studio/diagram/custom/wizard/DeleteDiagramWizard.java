/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.diagram.custom.wizard;

import java.util.List;

import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;

public class DeleteDiagramWizard extends AbstractManageDiagramWizard {

    protected DeleteDiagramWizardPage page;

    public DeleteDiagramWizard() {
        setWindowTitle(Messages.DeleteDiagramWizardPage_title);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        page = new DeleteDiagramWizardPage(Messages.DeleteDiagramWizardPage_title, getDiagramRepositoryStore());
        addPage(page);
    }

    public List<DiagramFileStore> getSelectedDiagrams() {
        return page.getSelectedDiagrams();
    }

    @Override
    public boolean performFinish() {
        return confirmDelete(page.getSelectedDiagrams());
    }

}
