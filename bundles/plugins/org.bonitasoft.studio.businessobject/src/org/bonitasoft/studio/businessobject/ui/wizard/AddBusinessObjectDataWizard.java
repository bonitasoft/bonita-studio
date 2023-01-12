/**
 * Copyright (C) 2013-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.ui.wizard;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.DataTypeLabels;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
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

    private final DataAware container;

    private final BusinessObjectModelRepositoryStore businessObjectDefinitionStore;

    private BusinessObjectDataWizardPage addBusinessObjectDataWizardPage;

    private final TransactionalEditingDomain editingDomain;

    private BusinessObjectData businessObjectData;

    public AddBusinessObjectDataWizard(final DataAware container,
            final BusinessObjectModelRepositoryStore businessObjectDefinitionStore,
            final TransactionalEditingDomain editingDomain) {
        this.container = container;
        businessObjectData = newBusinessData(container);
        this.businessObjectDefinitionStore = businessObjectDefinitionStore;
        this.editingDomain = editingDomain;
        setDefaultPageImageDescriptor(Pics.getWizban());
        setWindowTitle(Messages.addBusinessObjectDataWindowTitle);
    }

    public AddBusinessObjectDataWizard(final DataAware container, final BusinessObjectData workingCopy,
            final BusinessObjectModelRepositoryStore businessObjectDefinitionStore,
            final TransactionalEditingDomain editingDomain) {
        this(container, businessObjectDefinitionStore, editingDomain);
        businessObjectData = workingCopy;
        if (businessObjectData.getDefaultValue() == null) {
            businessObjectData.setDefaultValue(defaultValueExpression());
        }
    }

    private BusinessObjectData newBusinessData(final DataAware container) {
        final BusinessObjectData businessObjectData = ProcessFactory.eINSTANCE.createBusinessObjectData();
        businessObjectData.setDataType(ModelHelper.getDataTypeForID(container, DataTypeLabels.businessObjectType));
        businessObjectData.setDefaultValue(defaultValueExpression());
        return businessObjectData;
    }

    private Expression defaultValueExpression() {
        final Expression defaultValueExpression = ExpressionFactory.eINSTANCE.createExpression();
        defaultValueExpression.setType(ExpressionConstants.SCRIPT_TYPE);
        defaultValueExpression.setInterpreter(ExpressionConstants.GROOVY);
        defaultValueExpression.setName("");
        defaultValueExpression.setContent("");
        defaultValueExpression.setReturnType(Object.class.getName());
        return defaultValueExpression;
    }

    @Override
    public void addPages() {
        addBusinessObjectDataWizardPage = createAddBusinessObjectDataWizardPage();
        addPage(addBusinessObjectDataWizardPage);
    }

    protected BusinessObjectDataWizardPage createAddBusinessObjectDataWizardPage() {
        final BusinessObjectDataWizardPage page = new BusinessObjectDataWizardPage(container, businessObjectData, businessObjectDefinitionStore,
                computeExistingNames(container), new HintImageProvider());
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
        final Command addCommand = AddCommand.create(editingDomain, container, ProcessPackage.Literals.DATA_AWARE__DATA,
                businessObjectData);
        editingDomain.getCommandStack().execute(addCommand);
        return !addCommand.getResult().isEmpty();
    }

    public BusinessObjectData getBusinessObjectData() {
        return businessObjectData;
    }
}
