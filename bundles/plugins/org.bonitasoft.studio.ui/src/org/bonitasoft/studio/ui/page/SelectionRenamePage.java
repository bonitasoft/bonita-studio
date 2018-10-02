/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.ui.page;

import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.ui.dialog.RenameXMLFileDialog;
import org.bonitasoft.studio.ui.provider.FileStoreLabelProvider;
import org.bonitasoft.studio.ui.validator.ExtensionSupported;
import org.bonitasoft.studio.ui.validator.FileNameValidator;
import org.bonitasoft.studio.ui.widget.ButtonWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;

public class SelectionRenamePage<T extends IRepositoryStore<? extends IRepositoryFileStore>>
        extends SelectionMultiPage<T> {

    private FileNameValidator nameValidator;
    private Shell currentShell;
    private Optional<String> unRenamableFile = Optional.empty();

    public SelectionRenamePage(RepositoryAccessor repositoryAccessor, Class<T> type, FileStoreLabelProvider provider) {
        super(repositoryAccessor, type, provider);
        this.nameValidator = new FileNameValidator(repositoryAccessor.getRepositoryStore(type), ExtensionSupported.XML);
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        super.createControl(mainComposite, wizardContainer, ctx);
        currentShell = wizardContainer.getShell();

        ButtonWidget renameButton = new ButtonWidget.Builder()
                .alignLeft()
                .withLabel(org.bonitasoft.studio.ui.i18n.Messages.rename)
                .onClick(this::rename)
                .createIn(mainComposite);
        renameButton.disable();

        tableViewer.addSelectionChangedListener(e -> {
            if (getSelection().count() == 1
                    && !Objects.equals(getSelection().findFirst().get().getName(), unRenamableFile.orElse(""))) {
                renameButton.enable();
            } else {
                renameButton.disable();
            }
        });

        return mainComposite;
    }

    public void rename(Event e) {
        nameValidator.setCurrentFileName(getSelection().findFirst().get().getDisplayName());
        if (RenameXMLFileDialog.open(currentShell, getSelection().findFirst().get(), nameValidator)) {
            tableViewer.refresh();
        }
    }

    public void setUnRenamableFile(String unRenamableFile) {
        this.unRenamableFile = Optional.ofNullable(unRenamableFile);
    }

}
