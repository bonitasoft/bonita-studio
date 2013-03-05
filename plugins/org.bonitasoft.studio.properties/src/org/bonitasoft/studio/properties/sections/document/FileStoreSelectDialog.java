/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.properties.sections.document;

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
import org.bonitasoft.studio.properties.i18n.Messages;
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
import org.eclipse.swt.layout.RowLayout;
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

    private ListViewer fileStoreListViewer;
    private Button removeButton;
    private AbstractFileStore selected;

    public FileStoreSelectDialog(Shell parentShell) {
        super(parentShell);
    }

    public FileStoreSelectDialog(IShellProvider parentShell) {
        super(parentShell);
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(getDialogTitle());
    }

    @Override
    protected Control createContents(Composite parent) {
        final Control createContents = super.createContents(parent);
        fileStoreListViewer.setSelection(new StructuredSelection());
        return createContents;
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        final Control dialogArea = super.createDialogArea(parent);

        Composite mainComposite = new Composite((Composite) dialogArea, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createDescription(mainComposite);
        createList(mainComposite);
        createButtons(mainComposite);

        return dialogArea;
    }

    private void createDescription(Composite mainComposite) {
        Label descriptionLabel = new Label(mainComposite, SWT.NONE);
        descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2,1).create());
        descriptionLabel.setText(Messages.selectDocumentDescription);
    }

    private void createList(Composite mainComposite) {
        Composite listComposite = new Composite(mainComposite, SWT.NONE);
        listComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        listComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        final Text fileStoreListFilter = new Text(listComposite, SWT.BORDER | SWT.SEARCH | SWT.ICON_SEARCH | SWT.ICON_CANCEL);
        fileStoreListFilter.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        fileStoreListFilter.setMessage(WorkbenchMessages.FilteredTree_FilterMessage);
        fileStoreListFilter.addModifyListener(new ModifyListener() {

            private ViewerFilter filter;

            @Override
            public void modifyText(ModifyEvent e) {
                final String textForFiltering = fileStoreListFilter.getText();
                if(filter != null){
                    fileStoreListViewer.removeFilter(filter);
                }
                if(textForFiltering != null
                        && !textForFiltering.isEmpty()){
                    filter = new ViewerFilter() {

                        @Override
                        public boolean select(Viewer viewer, Object parentElement, Object element) {
                            return ((AbstractFileStore)element).getName().contains(textForFiltering);
                        }
                    };
                    fileStoreListViewer.addFilter(filter);
                }

            }
        });
        fileStoreListViewer = new ListViewer(listComposite, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
        fileStoreListViewer.getList().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 300).create());
        fileStoreListViewer.setContentProvider(new ArrayContentProvider());
        fileStoreListViewer.setLabelProvider(new FileStoreLabelProvider());
        IRepositoryStore<?> ars = RepositoryManager.getInstance().getRepositoryStore(getRepositoryStoreClass());
        fileStoreListViewer.setInput(ars.getChildren());
        fileStoreListViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                final boolean empty = event.getSelection().isEmpty();
                getButton(IDialogConstants.OK_ID).setEnabled(!empty);
                removeButton.setEnabled(!empty);
                selected = (AbstractFileStore) ((IStructuredSelection)event.getSelection()).getFirstElement();
            }
        });
        fileStoreListViewer.getList().setFocus();
    }

    private void createButtons(Composite mainComposite) {
        Composite buttonComposite = new Composite(mainComposite, SWT.NONE);
        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
        rowLayout.fill = true;
        buttonComposite.setLayout(rowLayout);
        createAddButton(buttonComposite);
        createRemoveButton(buttonComposite);

    }

    private void createAddButton(Composite buttonComposite) {
        Button addButton = new Button(buttonComposite, SWT.FLAT);
        addButton.setText(Messages.importEtc);
        addButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                FileDialog fileDialog = new FileDialog(getShell());
                String fileDialogResult = fileDialog.open();
                if(fileDialogResult != null){
                    AbstractRepositoryStore<IRepositoryFileStore> ars = (AbstractRepositoryStore<IRepositoryFileStore>)RepositoryManager.getInstance().getRepositoryStore(getRepositoryStoreClass());
                    File newFile = new File(fileDialogResult);
                    FileInputStream inputStream = null;
                    try {
                        inputStream = new FileInputStream(newFile);
                        IRepositoryFileStore fileStoreCreated = ars.importInputStream(newFile.getName(), inputStream);
                        fileStoreListViewer.setInput(ars.getChildren());
                        fileStoreListViewer.setSelection(new StructuredSelection(fileStoreCreated));
                        fileStoreListViewer.refresh();
                    } catch (FileNotFoundException e1) {
                        BonitaStudioLog.error(e1);
                    } finally {
                        if(inputStream != null){
                            try {
                                inputStream.close();
                            } catch (IOException e1) {
                                BonitaStudioLog.error(e1);
                            }
                        }
                    }
                }

            }
        });
    }

    private void createRemoveButton(Composite buttonComposite) {
        removeButton = new Button(buttonComposite, SWT.FLAT);
        removeButton.setText(Messages.remove);
        removeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                ((IRepositoryFileStore) ((StructuredSelection) fileStoreListViewer.getSelection()).getFirstElement()).delete();
                AbstractRepositoryStore<IRepositoryFileStore> ars = (AbstractRepositoryStore<IRepositoryFileStore>)RepositoryManager.getInstance().getRepositoryStore(getRepositoryStoreClass());
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