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

import javax.inject.Named;

import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.wizard.ExportBusinessDataModelWizard;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.bonitasoft.studio.common.ui.jface.CustomWizardDialog;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


public class ExportBusinessDataModelHandler extends AbstractHandler {

    private BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store;
    private RepositoryAccessor repositoryAccessor;

    @Execute
    public Object execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell, RepositoryAccessor repositoryAccessor)
            throws ExecutionException {
        this.repositoryAccessor = repositoryAccessor;
        store = repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        final CustomWizardDialog dialog = createWizardDialog(shell, Messages.export);
        dialog.setPageSize(SWT.DEFAULT, 100);
        return dialog.open();
    }

    protected CustomWizardDialog createWizardDialog(Shell shell, String finishLabel) {
        return new CustomWizardDialog(shell, new ExportBusinessDataModelWizard(store), finishLabel);
    }

    @CanExecute
    @Override
    public boolean isEnabled() {
        if (!PlatformUtil.isHeadless() && RepositoryManager.getInstance().getCurrentRepository().filter(IRepository::isLoaded).isPresent()) {
            final BusinessObjectModelFileStore fileStore = (BusinessObjectModelFileStore) RepositoryManager.getInstance()
                    .getRepositoryStore(BusinessObjectModelRepositoryStore.class)
                    .getChild(BusinessObjectModelFileStore.BOM_FILENAME, true);
            BusinessObjectModel businessObjectModel;
            try {
                businessObjectModel = fileStore.getContent();
            } catch (ReadFileStoreException e) {
                BonitaStudioLog.warning(e.getMessage(), BusinessObjectPlugin.PLUGIN_ID);
               return false;
            }
            return businessObjectModel != null
                    && !businessObjectModel.getBusinessObjects().isEmpty();
        }
        return false;
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        return execute(Display.getDefault().getActiveShell(), repositoryAccessor);
    }

}
