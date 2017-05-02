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
package org.bonitasoft.studio.la.ui.control;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.la.repository.ApplicationRepositoryStore;
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

public class SelectRenameApplicationDescriptorPage extends SelectMultiApplicationDescriptorPage {

    Shell currentShell;

    public SelectRenameApplicationDescriptorPage(RepositoryAccessor repositoryAccessor) {
        super(repositoryAccessor);
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
                .withLabel(Messages.rename)
                .onClick(this::rename)
                .createIn(mainComposite);
        renameButton.disable();

        applicationsTableViewer.addSelectionChangedListener(e -> {
            if (getSelection().count() == 1) {
                renameButton.enable();
            } else {
                renameButton.disable();
            }
        });

        return mainComposite;
    }

    public void rename(Event e) {
        if (RenameApplicationDescriptorFileDialog.open(currentShell,
                repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class), getSelection().findFirst().get())) {
            applicationsTableViewer.refresh();
        }
    }
}
