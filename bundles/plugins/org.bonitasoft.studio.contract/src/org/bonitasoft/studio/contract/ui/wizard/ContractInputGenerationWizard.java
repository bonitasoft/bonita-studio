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

import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Data;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.Wizard;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * @author aurelie
 */
public class ContractInputGenerationWizard extends Wizard {

    private final EObject container;
    private final List<Data> availableBusinessData;

    public ContractInputGenerationWizard(final EObject container) {
        this.container = container;
        availableBusinessData = availableBusinessData();
    }

    @Override
    public void addPages() {
        addPage(new SelectBusinessDataWizardPage(availableBusinessData));
        addPage(new CreateContractInputFromBusinessObjectWizardPage());
    };

    private List<Data> availableBusinessData() {
        final AbstractProcess pool = ModelHelper.getParentProcess(container);
        final Predicate<Object> predicate = Predicates.instanceOf(BusinessObjectData.class);
        return Lists.newArrayList(Iterables.filter(pool.getData(), predicate));
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
