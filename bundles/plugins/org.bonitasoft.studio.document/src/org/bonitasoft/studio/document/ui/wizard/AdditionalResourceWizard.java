/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.document.ui.wizard;

import org.bonitasoft.studio.common.emf.tools.EMFModelUpdater;
import org.bonitasoft.studio.document.i18n.Messages;
import org.bonitasoft.studio.document.ui.wizardPage.AdditionalResourceWizardPage;
import org.bonitasoft.studio.model.process.AdditionalResource;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.wizard.Wizard;

public class AdditionalResourceWizard extends Wizard {

    private Pool pool;
    private AdditionalResource workingCopy;
    private AdditionalResource originalAdditionalResource;
    private EMFModelUpdater<AdditionalResource> updater;

    public AdditionalResourceWizard(Pool pool) {
        super();
        this.pool = pool;
        this.workingCopy = ProcessFactory.eINSTANCE.createAdditionalResource();
        setWindowTitle();
    }

    public AdditionalResourceWizard(Pool pool, AdditionalResource additionalResource) {
        super();
        this.pool = pool;
        this.originalAdditionalResource = additionalResource;
        this.updater = new EMFModelUpdater<AdditionalResource>().from(additionalResource);
        this.workingCopy = updater.getWorkingCopy();
        setWindowTitle();
    }

    private void setWindowTitle() {
        if (updater != null) {
            setWindowTitle(Messages.editAdditionalResource);
        } else {
            setWindowTitle(Messages.newAdditionalResource);
        }
        setDefaultPageImageDescriptor(Pics.getWizban());
    }

    @Override
    public void addPages() {
        AdditionalResourceWizardPage page = new AdditionalResourceWizardPage(pool, workingCopy, originalAdditionalResource);
        if (updater != null) {
            page.setTitle(Messages.editAdditionalResource);
        }
        addPage(page);
    }

    @Override
    public boolean performFinish() {
        TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(pool);
        if (updater == null) {
            editingDomain.getCommandStack()
                    .execute(new AddCommand(editingDomain, pool.getAdditionalResources(), EcoreUtil.copy(workingCopy)));
        } else {
            editingDomain.getCommandStack().execute(updater.createUpdateCommand(editingDomain));
        }
        return true;
    }

    public Pool getPool() {
        return pool;
    }

}
