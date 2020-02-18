/**
 * Copyright (C) 2012-2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.provider.FileStoreLabelProvider;
import org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore;
import org.bonitasoft.studio.document.i18n.Messages;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.WorkbenchMessages;
/**
 * @author Aurelien Pupier
 *
 */
public abstract class FileStoreSelectDialog extends Dialog {

    private final class ViewerFilterOnFileStoreName extends ViewerFilter {

        private final String textForFiltering;

        private ViewerFilterOnFileStoreName(String textForFiltering) {
            this.textForFiltering = textForFiltering;
        }

        @Override
        public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
            return ((AbstractFileStore)element).getName().contains(textForFiltering);
        }
    }

    private ListViewer fileStoreListViewer;
    private Button removeButton;
    private AbstractFileStore selected;

    public FileStoreSelectDialog(final Shell parentShell) {
        super(parentShell);
    }

    public FileStoreSelectDialog(final IShellProvider parentShell) {
        super(parentShell);
    }

    @Override
    protected void configureShell(final Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(getDialogTitle());
    }

    @Override
    protected Control createContents(final Composite parent) {
        final Control createContents = super.createContents(parent);
        fileStoreListViewer.setSelection(new StructuredSelection());
        return createContents;
    }

    @Override
    protected Control createDialogArea(final Composite parent) {
        final Control dialogArea = super.createDialogArea(parent);

        final Composite mainComposite = new Composite((Composite) dialogArea, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createDescription(mainComposite);
        createButtons(mainComposite);
        createList(mainComposite);

        return dialogArea;
    }

    private void createDescription(final Composite mainComposite) {
        final Label descriptionLabel = new Label(mainComposite, SWT.NONE);
        descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2,1).create());
        descriptionLabel.setText(getDialogDescription());
    }

    protected abstract String getDialogDescription();

    private void createList(final Composite mainComposite) {
        final Composite listComposite = new Composite(mainComposite, SWT.NONE);
        listComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        listComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createFilter(listComposite);
        createListViewer(listComposite);
    }

    private void createListViewer(final Composite listComposite) {
        fileStoreListViewer = new ListViewer(listComposite, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
        fileStoreListViewer.getList().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(400, 300).create());
        fileStoreListViewer.setContentProvider(new ArrayContentProvider());
        fileStoreListViewer.setLabelProvider(new FileStoreLabelProvider());
        final IRepositoryStore<?> ars = (IRepositoryStore<?>) RepositoryManager.getInstance().getRepositoryStore(getRepositoryStoreClass());
        fileStoreListViewer.setInput(ars.getChildren());
        fileStoreListViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                final boolean empty = event.getSelection().isEmpty();
                getButton(IDialogConstants.OK_ID).setEnabled(!empty);
                removeButton.setEnabled(!empty);
                selected = (AbstractFileStore) ((IStructuredSelection)event.getSelection()).getFirstElement();
            }
        });
        fileStoreListViewer.getList().setFocus();
    }

    private void createFilter(final Composite listComposite) {
        final Text fileStoreListFilter = new Text(listComposite, SWT.BORDER | SWT.SEARCH | SWT.ICON_SEARCH | SWT.ICON_CANCEL);
        fileStoreListFilter.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        fileStoreListFilter.setMessage(WorkbenchMessages.FilteredTree_FilterMessage);
        fileStoreListFilter.addModifyListener(new ModifyListener() {

            private ViewerFilter filter;

            @Override
            public void modifyText(final ModifyEvent e) {
                final String textForFiltering = fileStoreListFilter.getText();
                if(filter != null){
                    fileStoreListViewer.removeFilter(filter);
                }
                if(textForFiltering != null
                        && !textForFiltering.isEmpty()){
                    filter = new ViewerFilterOnFileStoreName(textForFiltering);
                    fileStoreListViewer.addFilter(filter);
                }

            }
        });
    }

    private void createButtons(final Composite mainComposite) {
        final Composite buttonComposite = new Composite(mainComposite, SWT.NONE);
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).create());
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 35).create());
        createAddButton(buttonComposite);
        createRemoveButton(buttonComposite);

    }

    private void createAddButton(final Composite buttonComposite) {
        final Button addButton = new Button(buttonComposite, SWT.FLAT);
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        addButton.setText(Messages.importEtc);
        addButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final FileDialog fileDialog = new FileDialog(getShell());
                final String fileDialogResult = fileDialog.open();
                if(fileDialogResult != null){
                    final AbstractRepositoryStore<IRepositoryFileStore> ars = (AbstractRepositoryStore<IRepositoryFileStore>)RepositoryManager.getInstance().getRepositoryStore(getRepositoryStoreClass());
                    final File newFile = new File(fileDialogResult);
                    FileInputStream inputStream = null;
                    try {
                        inputStream = new FileInputStream(newFile);
                        final IRepositoryFileStore fileStoreCreated = ars.importInputStream(newFile.getName(), inputStream);
                        fileStoreListViewer.setInput(ars.getChildren());
                        fileStoreListViewer.setSelection(new StructuredSelection(fileStoreCreated));
                        fileStoreListViewer.refresh();
                    } catch (final FileNotFoundException e1) {
                        BonitaStudioLog.error(e1);
                    } finally {
                        if(inputStream != null){
                            try {
                                inputStream.close();
                            } catch (final IOException e1) {
                                BonitaStudioLog.error(e1);
                            }
                        }
                    }
                }

            }
        });
    }

    private void createRemoveButton(final Composite buttonComposite) {
        removeButton = new Button(buttonComposite, SWT.FLAT);
        removeButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        removeButton.setText(Messages.remove);
        removeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                ((IRepositoryFileStore) ((StructuredSelection) fileStoreListViewer.getSelection()).getFirstElement()).delete();
                final AbstractRepositoryStore<IRepositoryFileStore> ars = (AbstractRepositoryStore<IRepositoryFileStore>)RepositoryManager.getInstance().getRepositoryStore(getRepositoryStoreClass());
                fileStoreListViewer.setInput(ars.getChildren());
            }
        });
    }

    protected abstract Class<?> getRepositoryStoreClass() ;

    protected abstract String getDialogTitle();

    public AbstractFileStore getSelectedFileStore() {
        return selected;
    }

}
