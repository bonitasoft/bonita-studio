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
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.SWT;

/**
 * @author Romain Bioteau
 */
public class ExportBusinessDataModelHandler extends AbstractBusinessObjectHandler {

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store = getStore();
        final IWizard exportWizard = createWizard(store);
        final CustomWizardDialog dialog = createWizardDialog(exportWizard, Messages.export);
        dialog.setPageSize(SWT.DEFAULT, 100);
        return dialog.open();
    }

    protected IWizard createWizard(
            final BusinessObjectModelRepositoryStore store) {
        return new ExportBusinessDataModelWizard(store);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        if (!PlatformUtil.isHeadless() && RepositoryManager.getInstance().getCurrentRepository().isLoaded()) {
            final BusinessObjectModelFileStore fileStore = getStore().getChild(BusinessObjectModelFileStore.BOM_FILENAME);
            return fileStore != null && fileStore.getContent() != null
                    && !fileStore.getContent().getBusinessObjects().isEmpty();
        }
        return false;

    }

}
