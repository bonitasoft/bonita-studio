/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.businessobject.ui.wizard;

import java.util.Set;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.DataTypeLabels;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * @author Romain Bioteau
 */
public class AddBusinessObjectDataWizard extends AbstractBusinessObjectWizard {

    private DataAware container;

    private BusinessObjectModelRepositoryStore businessObjectDefinitionStore;

    private BusinessObjectDataWizardPage addBusinessObjectDataWizardPage;

    private TransactionalEditingDomain editingDomain;

    private Set<String> existingNames;

    private BusinessObjectData businessObjectData;

    public AddBusinessObjectDataWizard(DataAware container,
            BusinessObjectModelRepositoryStore businessObjectDefinitionStore,
            TransactionalEditingDomain editingDomain) {
        this.container = container;
        this.businessObjectData = ProcessFactory.eINSTANCE.createBusinessObjectData();
        this.businessObjectData.setDataType(ModelHelper.getDataTypeForID(container, DataTypeLabels.businessObjectType));
        this.businessObjectDefinitionStore = businessObjectDefinitionStore;
        this.editingDomain = editingDomain;
        this.existingNames = computeExistingNames(container);
        setDefaultPageImageDescriptor(Pics.getWizban());
    }

    @Override
    public void addPages() {
        addBusinessObjectDataWizardPage = createAddBusinessObjectDataWizardPage();
        addPage(addBusinessObjectDataWizardPage);
    }

    protected BusinessObjectDataWizardPage createAddBusinessObjectDataWizardPage() {
        BusinessObjectDataWizardPage page = new BusinessObjectDataWizardPage(businessObjectData, businessObjectDefinitionStore, existingNames);
        page.setTitle(Messages.bind(Messages.addBusinessObjectDataTitle, ModelHelper.getParentProcess(container).getName()));
        page.setDescription(Messages.addBusinessObjectDataDescription);
        return page;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        BusinessObjectData data = addBusinessObjectDataWizardPage.getBusinessObjectData();
        Command addCommand = AddCommand.create(editingDomain, container, ProcessPackage.Literals.DATA_AWARE__DATA, data);
        editingDomain.getCommandStack().execute(addCommand);
        return !addCommand.getResult().isEmpty();
    }

    public BusinessObjectData getBusinessObjectData() {
        return addBusinessObjectDataWizardPage.getBusinessObjectData();
    }
}
