/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.handler;

import static org.bonitasoft.studio.ui.wizard.WizardBuilder.newWizard;
import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.la.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.ui.control.ImportApplicationPage;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;

public class ImportApplicationhandler {

    @Execute
    public void importApplicationDescriptorWizard(Shell activeShell, RepositoryAccessor repositoryAccessor) {
        createWizard(newWizard(), repositoryAccessor)
                .open(activeShell, Messages.importLabel)
                .ifPresent(IRepositoryFileStore::open);
    }

    private WizardBuilder<ApplicationFileStore> createWizard(WizardBuilder<ApplicationFileStore> builder,
            RepositoryAccessor repositoryAccessor) {
        final ImportApplicationPage importApplicationPage = new ImportApplicationPage(repositoryAccessor);
        return builder
                .withTitle(Messages.importApplicationDescriptor)
                .havingPage(newPage()
                        .withTitle(Messages.importApplicationDescriptor)
                        .withDescription(Messages.importApplicationDescriptorDesc)
                        .withControl(importApplicationPage))
                .onFinish(container -> finish(importApplicationPage, repositoryAccessor));
    }

    private Optional<ApplicationFileStore> finish(ImportApplicationPage importApplicationPage,
            RepositoryAccessor repositoryAccessor) {
        final File file = new File(importApplicationPage.getFilePath());
        final ApplicationRepositoryStore repositoryStore = repositoryAccessor
                .getRepositoryStore(ApplicationRepositoryStore.class);
        if (repositoryStore.getChildren().stream()
                .anyMatch(applicationFileStore -> Objects.equals(applicationFileStore.getName(), file.getName()))) {
            if (FileActionDialog.overwriteQuestion(file.getName())) {
                repositoryStore.getChild(file.getName()).delete();
            } else {
                return Optional.empty();
            }
        }

        try (FileInputStream fis = new FileInputStream(file)) {
            final ApplicationFileStore applicationFileStore = repositoryStore.importInputStream(file.getName(), fis);
            return Optional.ofNullable(applicationFileStore);
        } catch (final IOException e) {
            throw new RuntimeException("Failed to import the application descriptor", e);
        }
    }

}
