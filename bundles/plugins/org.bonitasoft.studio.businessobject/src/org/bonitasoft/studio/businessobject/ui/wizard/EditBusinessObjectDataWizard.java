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

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.EMFModelUpdater;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.refactoring.core.RefactorDataOperation;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * @author Romain Bioteau
 */
public class EditBusinessObjectDataWizard extends AbstractBusinessObjectWizard {

    private final BusinessObjectModelRepositoryStore businessObjectDefinitionStore;

    private BusinessObjectDataWizardPage editBusinessObjectDataWizardPage;

    private final TransactionalEditingDomain editingDomain;

    private final BusinessObjectData data;

    private final DataAware container;

    private BusinessObjectData businessObjectDataWorkingCopy;

    private EMFModelUpdater<Data> emfModelUpdater;

    public EditBusinessObjectDataWizard(final BusinessObjectData data,
            final BusinessObjectModelRepositoryStore businessObjectDefinitionStore,
            final TransactionalEditingDomain editingDomain) {
        this.data = data;
        container = (DataAware) data.eContainer();
        this.businessObjectDefinitionStore = businessObjectDefinitionStore;
        this.editingDomain = editingDomain;
        this.emfModelUpdater = new EMFModelUpdater<>();
        setDefaultPageImageDescriptor(Pics.getWizban());
        setWindowTitle(Messages.editBusinessObjectDataWindowTitle);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.businessobject.ui.wizard.AbstractBusinessObjectWizard#computeExistingNames(org.bonitasoft.studio
     * .model.process.DataAware)
     */
    @Override
    protected Set<String> computeExistingNames(final DataAware container) {
        final Set<String> existingNames = super.computeExistingNames(container);
        existingNames.remove(data.getName());
        return existingNames;
    }

    @Override
    public void addPages() {
        editBusinessObjectDataWizardPage = createEditBusinessObjectDataWizardPage();
        addPage(editBusinessObjectDataWizardPage);
    }

    protected BusinessObjectDataWizardPage createEditBusinessObjectDataWizardPage() {
        businessObjectDataWorkingCopy = (BusinessObjectData) emfModelUpdater.from(data).getWorkingCopy();
        if (businessObjectDataWorkingCopy.getDefaultValue() == null) {
            businessObjectDataWorkingCopy.setDefaultValue(defaultValueExpression());
        }
        final BusinessObjectDataWizardPage page = new BusinessObjectDataWizardPage(container, businessObjectDataWorkingCopy,
                businessObjectDefinitionStore, computeExistingNames(container), new HintImageProvider());
        page.setTitle(
                Messages.bind(Messages.editBusinessObjectDataTitle, ModelHelper.getParentProcess(container).getName()));
        page.setDescription(Messages.editBusinessObjectDataDescription);
        return page;
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

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        return refactorBusinessData(businessObjectDataWorkingCopy);
    }

    private boolean refactorBusinessData(Data updatedData) {
        final RefactorDataOperation op = newRefactorOperation(updatedData);
        if (op.canExecute()) {
            try {
                getContainer().run(true, false, op);
                if (op.isCancelled()) {
                    return false;
                }
            } catch (final InvocationTargetException | InterruptedException e) {
                BonitaStudioLog.error(e);
                return false;
            }
        }
        return true;
    }

    private RefactorDataOperation newRefactorOperation(final Data updatedData) {
        final RefactorDataOperation op = new RefactorDataOperation(RefactoringOperationType.UPDATE);
        op.setEditingDomain(editingDomain);
        op.addItemToRefactor(updatedData, data);
        op.setDataContainer(container);
        op.setAskConfirmation(true);
        op.setDataContainmentFeature(ProcessPackage.Literals.DATA_AWARE__DATA);
        op.setUpdater(emfModelUpdater);
        return op;
    }
}
