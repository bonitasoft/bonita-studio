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
package org.bonitasoft.studio.businessobject.ui.handler;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.wizard.ExportBusinessDataModelWizard;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 */
public class ExportBusinessDataModelHandler {

    private BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store;
    private RepositoryAccessor repositoryAccessor;

    @Execute
    public Object execute(RepositoryAccessor repositoryAccessor, Shell shell) throws ExecutionException {
        this.repositoryAccessor = repositoryAccessor;
        store = repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        final CustomWizardDialog dialog = createWizardDialog(shell, Messages.export);
        dialog.setPageSize(SWT.DEFAULT, 100);
        return dialog.open();
    }

    protected CustomWizardDialog createWizardDialog(Shell shell, String finishLabel) {
        return new CustomWizardDialog(shell, new ExportBusinessDataModelWizard(store), finishLabel);
    }

    public boolean isEnabled() {
        if (!PlatformUtil.isHeadless() && repositoryAccessor.getCurrentRepository().isLoaded()) {
            final BusinessObjectModelFileStore fileStore = store.getChild(BusinessObjectModelFileStore.BOM_FILENAME);
            return fileStore != null && fileStore.getContent() != null
                    && !fileStore.getContent().getBusinessObjects().isEmpty();
        }
        return false;

    }

}
