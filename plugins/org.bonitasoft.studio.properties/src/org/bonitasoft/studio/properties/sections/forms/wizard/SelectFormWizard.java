/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.sections.forms.wizard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.properties.sections.forms.FormsUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.Wizard;

public class SelectFormWizard extends Wizard {

    protected FormWizardPageVars pageVars;
    protected Element pageFlow;
    protected TransactionalEditingDomain editingDomain;
    protected EStructuralFeature feature;

    public SelectFormWizard(Element element, EStructuralFeature feature, TransactionalEditingDomain editingDomain) {
        pageFlow = element;
        this.editingDomain = editingDomain;
        this.feature = feature;
        setWindowTitle(Messages.addFormTitle);
    }

    @Override
    public void addPages() {
        super.addPages();
        pageVars = new FormWizardPageVars(pageFlow,feature);
        addPage(pageVars);
    }

    @Override
    public boolean performFinish() {
        return createForm();
    }

    /**
     * 
     */
    protected boolean createForm() {
        String name = pageVars.getNameField();
        pageVars.setMessage(null);
        boolean allreadyExists = false;
        for (Iterator<?> iterator = ((List<?>)pageFlow.eGet(feature)).iterator(); iterator.hasNext();) {
            Form form = (Form) iterator.next();
            allreadyExists = form.getName().equals(name);
            if (allreadyExists) {
                pageVars.setMessage(Messages.error_allreadyExists, IMessageProvider.ERROR);
                return false;
            }
        }
        if (name.length() == 0) {
            pageVars.setMessage(Messages.error_empty, IMessageProvider.ERROR);
            return false;
        }
        // get the data and documents from we will create a form
        Map<Element, FormsUtils.WidgetEnum> vars = pageVars.getFormFields();
        FormsUtils.addForm(pageFlow,editingDomain,feature, name, pageVars.getdescField(), vars);
        return true;
    }

    @Override
    public boolean canFinish() {
        return pageVars.isPageComplete();

    }

    /**
     * 
     * @return the forms that can be duplicated
     */
    public List<?> getAvailiablesForms() {
        ArrayList<EObject> list = new ArrayList<EObject>();
        list.add(ModelHelper.getMainProcess(pageFlow));
        return list;
    }

}
