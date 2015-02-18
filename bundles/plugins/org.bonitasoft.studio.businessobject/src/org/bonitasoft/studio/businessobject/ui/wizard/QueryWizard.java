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

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.jface.wizard.Wizard;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.Query;
import org.bonitasoft.engine.bdm.model.QueryParameter;

/**
 * @author Romain Bioteau
 * 
 */
public class QueryWizard extends Wizard {

    protected Query queryWorkingCopy;

    protected BusinessObject businessObject;

    public QueryWizard(BusinessObject businessObject, Query query) {
        this.businessObject = businessObject;
        String returnType = query.getReturnType();
        if (returnType == null) {
            returnType = List.class.getName();
        }
        this.queryWorkingCopy = new Query(query.getName(), query.getContent(), returnType);
        queryWorkingCopy.setQueryParameters(new ArrayList<QueryParameter>(query.getQueryParameters()));
        setDefaultPageImageDescriptor(Pics.getWizban());
        setForcePreviousAndNextButtons(false);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        QueryWizardPage page = new QueryWizardPage();
        page.setQuery(queryWorkingCopy);
        page.setBusinessObject(businessObject);
        String simpleName = NamingUtils.getSimpleName(businessObject.getQualifiedName());
        if (queryWorkingCopy.getContent() == null || queryWorkingCopy.getContent().isEmpty()) {
            page.setTitle(Messages.bind(Messages.newQuery, simpleName));
            page.setDescription(Messages.newQueryDescription);
            setWindowTitle(Messages.newQueryTitle);
        } else {
            page.setTitle(Messages.bind(Messages.editQuery, queryWorkingCopy.getName(), simpleName));
            page.setDescription(Messages.editQueryDescription);
            setWindowTitle(Messages.editQueryTitle);
        }

        addPage(page);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        return queryWorkingCopy != null;
    }

    public Query getQueryWorkingCopy() {
        return queryWorkingCopy;
    }

}
