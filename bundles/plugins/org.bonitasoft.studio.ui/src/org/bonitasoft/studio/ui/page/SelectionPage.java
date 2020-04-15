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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.ui.provider.FileStoreLabelProvider;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.bonitasoft.studio.ui.wizard.listener.WizardDoubleClickListener;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public abstract class SelectionPage<T extends IRepositoryStore<? extends IRepositoryFileStore>> implements ControlSupplier {

    private static final int TABLE_WIDTH_HINT = 600;

    protected RepositoryAccessor repositoryAccessor;

    protected TableViewer tableViewer;

    protected Class<T> type;

    private FileStoreLabelProvider labelProvider;

    private Optional<List<IRepositoryFileStore>> unselectableElements = Optional.empty();

    public SelectionPage(RepositoryAccessor repositoryAccessor, Class<T> type, FileStoreLabelProvider labelProvider) {
        this.repositoryAccessor = repositoryAccessor;
        this.type = type;
        this.labelProvider = labelProvider;
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().create());

        tableViewer = createTableViewer(mainComposite);
        tableViewer.getControl().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).hint(TABLE_WIDTH_HINT, SWT.DEFAULT).create());
        tableViewer.setContentProvider(ArrayContentProvider.getInstance());
        tableViewer.setLabelProvider(labelProvider);
        tableViewer.setInput(getInput());
        tableViewer.addDoubleClickListener(new WizardDoubleClickListener((WizardDialog) wizardContainer));

        ColumnViewerToolTipSupport.enableFor(tableViewer);

        IObservableList<IRepositoryFileStore> multiSelection = ViewersObservables.observeMultiSelection(tableViewer);
        ctx.addValidationStatusProvider(new MultiValidator() {

            @Override
            protected IStatus validate() {
                return multiSelection.isEmpty()
                        ? ValidationStatus.error("No selection")
                        : unselectableElements.isPresent()
                                && multiSelection.stream().anyMatch(unselectableElements.get()::contains)
                                        ? ValidationStatus.error("Unselectable element selected")
                                        : ValidationStatus.ok();
            }
        });

        return mainComposite;
    }

    protected List<? extends IRepositoryFileStore> getInput() {
        return repositoryAccessor.getRepositoryStore(type).getChildren();
    }

    protected abstract TableViewer createTableViewer(Composite mainComposite);

    public Stream<IRepositoryFileStore> getSelection() {
        return ((IStructuredSelection) tableViewer.getSelection()).toList().stream();
    }

    public void addUnselectableElements(IRepositoryFileStore... unselectableElements) {
        this.unselectableElements = Optional.ofNullable(Arrays.asList(unselectableElements));
    }

}
