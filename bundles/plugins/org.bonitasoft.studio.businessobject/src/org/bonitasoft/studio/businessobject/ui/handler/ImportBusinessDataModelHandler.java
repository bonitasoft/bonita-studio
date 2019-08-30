/**
 * Copyright (C) 2018 Bonitasoft S.A.
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

import static org.bonitasoft.studio.ui.wizard.WizardBuilder.newWizard;
import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

import javax.inject.Named;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.wizard.ImportBdmPage;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ImportBusinessDataModelHandler extends AbstractHandler {

    @Execute
    public void importBdmWizard(@Named(IServiceConstants.ACTIVE_SHELL) Shell activeShell,
            RepositoryAccessor repositoryAccessor) {
        createWizard(newWizard(), repositoryAccessor)
                .open(activeShell, Messages.importButtonLabel)
                .ifPresent(o -> MessageDialog.openInformation(activeShell, Messages.bdmImportedTitle, Messages.bdmImported));
    }

    private WizardBuilder<BusinessObjectModelFileStore> createWizard(WizardBuilder<BusinessObjectModelFileStore> builder,
            RepositoryAccessor repositoryAccessor) {
        final ImportBdmPage importBdmPage = new ImportBdmPage(repositoryAccessor);
        return builder
                .withTitle(Messages.importBdm)
                .havingPage(newPage()
                        .withTitle(Messages.importBdm)
                        .withDescription(Messages.importBdmDesc)
                        .withControl(importBdmPage))
                .onFinish(container -> finish(importBdmPage, repositoryAccessor));
    }

    private Optional<BusinessObjectModelFileStore> finish(ImportBdmPage importBdmPage,
            RepositoryAccessor repositoryAccessor) {
        final File file = new File(importBdmPage.getFilePath());
        final BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = repositoryAccessor
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        if (repositoryStore.getChild(BusinessObjectModelFileStore.BOM_FILENAME, true) != null) {
            if (FileActionDialog.overwriteQuestion(file.getName())) {
                repositoryStore.getChild(BusinessObjectModelFileStore.BOM_FILENAME, true).delete();
            } else {
                return Optional.empty();
            }
        }

        try (FileInputStream fis = new FileInputStream(file)) {
            BusinessObjectModelFileStore bdmFileStore = (BusinessObjectModelFileStore) repositoryStore
                    .importInputStream(file.getName(), fis);
            return Optional.of(bdmFileStore);
        } catch (final IOException e) {
            throw new RuntimeException("Failed to import the bdm file", e);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        RepositoryAccessor repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        importBdmWizard(Display.getDefault().getActiveShell(), repositoryAccessor);
        return null;
    }

}
