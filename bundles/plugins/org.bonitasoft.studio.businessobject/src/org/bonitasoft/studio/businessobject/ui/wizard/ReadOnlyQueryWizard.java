/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.businessobject.ui.wizard;

import org.bonitasoft.studio.businessobject.i18n.Messages;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.Query;

/**
 * @author Romain Bioteau
 * 
 */
public class ReadOnlyQueryWizard extends QueryWizard {

    public ReadOnlyQueryWizard(BusinessObject businessObject, Query query) {
        super(businessObject, query);
        setWindowTitle(Messages.providedQuery);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        QueryWizardPage page = new ReadOnlyQueryWizardPage();
        page.setQuery(queryWorkingCopy);
        page.setBusinessObject(businessObject);
        page.setTitle(Messages.providedQuery);
        page.setDescription(Messages.providedQueryDescription);
        addPage(page);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        return true;
    }

}
