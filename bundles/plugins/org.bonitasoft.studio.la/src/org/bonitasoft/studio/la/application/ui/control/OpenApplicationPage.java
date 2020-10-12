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
package org.bonitasoft.studio.la.application.ui.control;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.application.ui.provider.ApplicationFileStoreLabelProvider;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class OpenApplicationPage implements ControlSupplier {

    private RepositoryAccessor repositoryAccessor;
    private WritableList applicationFileStoreObservable;
    private static final int TABLE_WIDTH_HINT = 600;

    public OpenApplicationPage(RepositoryAccessor repositoryAccessor, WritableList applicationFileStoreObservable) {
        this.repositoryAccessor = repositoryAccessor;
        this.applicationFileStoreObservable = applicationFileStoreObservable;
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().create());

        TableViewer applicationsTableViewer = new TableViewer(mainComposite);
        applicationsTableViewer.getControl().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).hint(TABLE_WIDTH_HINT, SWT.DEFAULT).create());
        applicationsTableViewer.setContentProvider(ArrayContentProvider.getInstance());
        applicationsTableViewer.setLabelProvider(new ApplicationFileStoreLabelProvider());
        applicationsTableViewer
                .setInput(repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class).getChildren());

        ColumnViewerToolTipSupport.enableFor(applicationsTableViewer);
        ctx.bindList(ViewerProperties.multipleSelection().observe(applicationsTableViewer), applicationFileStoreObservable);
        ctx.addValidationStatusProvider(new org.eclipse.core.databinding.validation.MultiValidator() {

            @Override
            protected IStatus validate() {
                return applicationFileStoreObservable.isEmpty()
                        ? ValidationStatus.error("No selection")
                        : ValidationStatus.ok();
            }
        });

        return mainComposite;
    }

}
