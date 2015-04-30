/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.contract.ui.wizard;

import static com.google.common.base.Predicates.instanceOf;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Data;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author aurelie
 */
public class ContractInputGenerationWizard extends Wizard {

    private final EObject container;
    private final List<Data> availableBusinessData;
    private final BusinessObjectModelRepositoryStore businessObjectStore;

    public ContractInputGenerationWizard(final EObject container, final BusinessObjectModelRepositoryStore businessObjectStore) {
        this.container = container;
        availableBusinessData = availableBusinessData();
        this.businessObjectStore = businessObjectStore;
    }

    @Override
    public void addPages() {
        final EMFDataBindingContext dbc = new EMFDataBindingContext();
        final WritableValue selectedDataObservable = new WritableValue();
        addPage(new SelectBusinessDataWizardPage(availableBusinessData, selectedDataObservable));
        addPage(new CreateContractInputFromBusinessObjectWizardPage(selectedDataObservable, businessObjectStore));
    }

    private List<Data> availableBusinessData() {
        final AbstractProcess pool = ModelHelper.getParentProcess(container);
        return newArrayList(filter(pool.getData(), instanceOf(BusinessObjectData.class)));
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {

        return false;
    }

}
