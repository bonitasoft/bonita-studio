/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.validators.ui.wizard;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.validators.ValidatorPlugin;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor;
import org.bonitasoft.studio.validators.i18n.Messages;
import org.bonitasoft.studio.validators.repository.ValidatorDescriptorRepositoryStore;
import org.bonitasoft.studio.validators.repository.ValidatorSourceRepositorySotre;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class SelectValidatorWizardPage extends WizardPage implements ISelectionChangedListener, IDoubleClickListener {

    private TableViewer table;
    protected Connector connector;
    private EMFDataBindingContext context;
    private WizardPageSupport pageSupport;
    private ValidatorDescriptor selectedValidator;
    private Button removeButton;

    public SelectValidatorWizardPage() {
        super(SelectValidatorWizardPage.class.getName());
        setTitle(Messages.selectValidatorTitle);
        setDescription(Messages.selectValidatorDesc);
    }

    @Override
    public void createControl(final Composite parent) {
        final Composite composite = new Composite(parent, SWT.None);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).extendedMargins(10, 10, 10, 0).create());
        table = new TableViewer(composite, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
        table.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        table.setContentProvider(new ArrayContentProvider());
        table.setLabelProvider(new LabelProvider() {

            @Override
            public String getText(final Object element) {
                return ((ValidatorDescriptor) element).getName() + " (" + ((ValidatorDescriptor) element).getClassName() + ") -- "
                        + ((ValidatorDescriptor) element).getType();
            }

            @Override
            public Image getImage(final Object element) {
                return Pics.getImage("Validator.png", ValidatorPlugin.getDefault());
            }
        });

        table.addSelectionChangedListener(this);
        table.addDoubleClickListener(this);
        table.setSorter(new ViewerSorter());

        table.setInput(getInput());

        context = new EMFDataBindingContext();

        final IValidator selectionValidator = new IValidator() {

            @Override
            public IStatus validate(final Object value) {
                if (value == null) {
                    return new Status(IStatus.ERROR, ValidatorPlugin.PLUGIN_ID, Messages.selectAValidatorWarning);
                }
                return Status.OK_STATUS;
            }
        };

        final UpdateValueStrategy selectionStrategy = new UpdateValueStrategy();
        selectionStrategy.setBeforeSetValidator(selectionValidator);

        context.bindValue(ViewersObservables.observeSingleSelection(table),
                PojoProperties.value(SelectValidatorWizardPage.class, "selectedValidator").observe(this), selectionStrategy, null);

        removeButton = new Button(composite, SWT.PUSH);
        removeButton.setText(Messages.Remove);
        removeButton.setLayoutData(GridDataFactory.swtDefaults().hint(85, SWT.DEFAULT).create());
        removeButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (selectedValidator != null) {
                    final ValidatorDescriptorRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(
                            ValidatorDescriptorRepositoryStore.class);
                    final String fileName = URI.decode(selectedValidator.eResource().getURI().lastSegment());
                    final IRepositoryFileStore file = store.getChild(fileName);
                    if (FileActionDialog.confirmDeletionQuestion(fileName)) {
                        file.delete();
                        final ValidatorSourceRepositorySotre sourceStore = RepositoryManager.getInstance().getRepositoryStore(
                                ValidatorSourceRepositorySotre.class);
                        final IRepositoryFileStore sourceFile = sourceStore.getChild(selectedValidator.getClassName());
                        if (sourceFile != null && FileActionDialog.confirmDeletionQuestion(sourceFile.getName())) {
                            sourceFile.delete();
                        }
                    }
                    refresh();
                }
            }
        });
        removeButton.setEnabled(false);

        pageSupport = WizardPageSupport.create(this, context);
        setControl(composite);
    }

    @Override
    public void dispose() {
        super.dispose();
        if (context != null) {
            context.dispose();
        }
        if (pageSupport != null) {
            pageSupport.dispose();
        }
    }

    protected void refresh() {
        if (table != null && !table.getTable().isDisposed()) {
            table.setInput(getInput());
        }
    }

    private Object getInput() {
        final ValidatorDescriptorRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(ValidatorDescriptorRepositoryStore.class);
        final List<ValidatorDescriptor> input = new ArrayList<ValidatorDescriptor>();
        for (final IRepositoryFileStore file : store.getChildren()) {
            if (!file.isReadOnly()) {
                try {
                    input.add((ValidatorDescriptor) file.getContent());
                } catch (final ReadFileStoreException e) {
                    BonitaStudioLog.error("Failed to retrieve validator descriptor", e);
                }
            }
        }
        return input;
    }

    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        final Object selection = ((IStructuredSelection) event.getSelection()).getFirstElement();
        if (removeButton != null && selection instanceof ValidatorDescriptor) {
            removeButton.setEnabled(true);
        }
    }

    @Override
    public void doubleClick(final DoubleClickEvent event) {
        final Object selection = ((IStructuredSelection) event.getSelection()).getFirstElement();
        if (selection instanceof ValidatorDescriptor) {
            if (getNextPage() != null) {
                getContainer().showPage(getNextPage());
            } else {
                if (getWizard().performFinish()) {
                    ((WizardDialog) getContainer()).close();
                }
            }
        }
    }

    public ValidatorDescriptor getSelectedValidator() {
        return selectedValidator;
    }

    public void setSelectedValidator(final ValidatorDescriptor selectedValidator) {
        this.selectedValidator = selectedValidator;
    }

}
