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
package org.bonitasoft.studio.properties.sections.resources;

import org.bonitasoft.studio.repository.themes.ApplicationLookNFeelFileStore;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Mickael Istria
 *
 */
public class SelectLocalTemplateWizard extends Wizard {

    private SelectLocalTemplateWizardPage page;

    public SelectLocalTemplateWizard(){
        setNeedsProgressMonitor(true);
    }

    @Override
    public void addPages() {
        page = new SelectLocalTemplateWizardPage();
        addPage(page);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        return true;
    }

    /**
     * @return
     */
    public ApplicationLookNFeelFileStore getSelectedTemplate() {
        return page.getSelectedTemplate();
    }

}
