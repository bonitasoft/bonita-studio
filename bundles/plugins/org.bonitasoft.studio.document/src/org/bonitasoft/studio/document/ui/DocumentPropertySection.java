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
package org.bonitasoft.studio.document.ui;

import static org.bonitasoft.studio.common.Messages.removalConfirmationDialogTitle;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import org.bonitasoft.studio.common.dialog.OutlineDialog;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.jface.ElementForIdLabelProvider;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.common.widgets.GTKStyleHandler;
import org.bonitasoft.studio.document.i18n.Messages;
import org.bonitasoft.studio.document.refactoring.RefactorDocumentOperation;
import org.bonitasoft.studio.document.ui.dialog.DocumentWizardDialog;
import org.bonitasoft.studio.document.ui.wizard.DocumentWizard;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author Aurelien Pupier
 */
public class DocumentPropertySection extends AbstractBonitaDescriptionSection
        implements ISelectionChangedListener, IDoubleClickListener {

    private TableViewer documentViewer;
    private EMFDataBindingContext emfDataBindingContext;
    private Button removeButton;
    private Button editButton;

    public DocumentPropertySection() {
        // keep it for reflective instantiation by Eclipse
    }

    protected void createMainComposite(final Composite mainComposite) {
        final Composite masterComposite = getWidgetFactory().createComposite(mainComposite);
        masterComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(5, 5).create());
        masterComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createButtons(masterComposite);
        documentViewer = createList(masterComposite);
    }

    private TableViewer createList(final Composite mainComposite) {
        
        TableViewer viewer = new TableViewer(getWidgetFactory().createTable(mainComposite,
                GTKStyleHandler.removeBorderFlag(SWT.BORDER | SWT.MULTI | SWT.NO_FOCUS)));
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 120).create());
        viewer.setLabelProvider(new ElementForIdLabelProvider());
        viewer.setContentProvider(new ObservableListContentProvider<Document>());
        viewer.addDoubleClickListener(this);
        viewer.addSelectionChangedListener(this);
        viewer.getTable().addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.keyCode == SWT.DEL) {
                    e.doit = false;
                    removeDocuments();
                }
            }
        });

        return viewer;
    }

    private void createButtons(final Composite mainComposite) {
        final Composite buttonComposite = getWidgetFactory().createComposite(mainComposite);
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).spacing(0, 3).create());
        buttonComposite.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.TOP).grab(false, true).create());
        createAddButton(buttonComposite);
        createEditButton(buttonComposite);
        createRemoveButton(buttonComposite);
    }

    private void createEditButton(final Composite buttonComposite) {
        editButton = getWidgetFactory().createButton(buttonComposite, org.bonitasoft.studio.common.Messages.edit, SWT.FLAT);
        editButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(85, SWT.DEFAULT).create());
        editButton.setEnabled(false);
        editButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                editDocumentAction(documentViewer.getSelection());
            }
        });

    }

    private void createAddButton(final Composite buttonComposite) {
        final Button addButton = getWidgetFactory().createButton(buttonComposite,
                org.bonitasoft.studio.document.i18n.Messages.AddSimple, SWT.FLAT);
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(85, SWT.DEFAULT).create());
        addButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);

                final DocumentWizard documentWizard = new DocumentWizard(getEObject());
                final Dialog dialog = new DocumentWizardDialog(Display.getDefault().getActiveShell(), documentWizard, true);
                if (IDialogConstants.OK_ID == dialog.open()) {
                    final Document newDocument = documentWizard.getDocumentWorkingCopy();
                    documentViewer.setSelection(new StructuredSelection(newDocument));
                }
            }
        });
    }

    private void createRemoveButton(final Composite buttonComposite) {
        removeButton = getWidgetFactory().createButton(buttonComposite, Messages.remove, SWT.FLAT);
        removeButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(85, SWT.DEFAULT).create());
        removeButton.setEnabled(false);
        removeButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                removeDocuments();
            }

        });
    }

    protected void removeDocuments() {
        final int ok = 0;
        if (ok == openOutlineDialog((IStructuredSelection) documentViewer.getSelection())) {
            final Iterator<Document> selection = ((IStructuredSelection) documentViewer.getSelection())
                    .iterator();
            if (selection.hasNext()) {
                final RefactorDocumentOperation rdo = createDeleteRefactorOperation(selection);
                executeDeleteReactorOperation(rdo);
                documentViewer.refresh();
                documentViewer.setSelection(new StructuredSelection());
            }
        }
    }

    private void executeDeleteReactorOperation(final RefactorDocumentOperation rdo) {
        try {
            rdo.run(new NullProgressMonitor());
        } catch (final InvocationTargetException e1) {
            BonitaStudioLog.error(e1);
        } catch (final InterruptedException e1) {
            BonitaStudioLog.error(e1);
        }
    }

    private RefactorDocumentOperation createDeleteRefactorOperation(final Iterator<Document> selection) {
        final RefactorDocumentOperation rdo = new RefactorDocumentOperation(RefactoringOperationType.REMOVE);
        rdo.setEditingDomain(getEditingDomain());
        rdo.setAskConfirmation(true);
        while (selection.hasNext()) {
            rdo.addItemToRefactor(null, selection.next());
        }
        return rdo;
    }

    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
        super.setInput(part, selection);
        resetDatabindingContext();
        bindList();
    }

    protected void bindList() {
        final IObservableList documentsListObserved = EMFEditProperties
                .list(getEditingDomain(), ProcessPackage.Literals.POOL__DOCUMENTS).observe(getPool());
        documentViewer.setInput(documentsListObserved);
    }

    protected void resetDatabindingContext() {
        if (emfDataBindingContext != null) {
            emfDataBindingContext.dispose();
        }
        emfDataBindingContext = new EMFDataBindingContext();
    }

    protected Pool getPool() {
        return (Pool) ModelHelper.getParentProcess(getEObject());
    }

    @Override
    public void dispose() {
        if (emfDataBindingContext != null) {
            emfDataBindingContext.dispose();
        }
        super.dispose();
    }

    @Override
    public String getSectionDescription() {
        return Messages.documentPropertySectionDescription;
    }

    @Override
    public void doubleClick(final DoubleClickEvent event) {
        editDocumentAction(event.getSelection());

    }

    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        final StructuredSelection listSelection = (StructuredSelection) event.getSelection();
        removeButton.setEnabled(!listSelection.isEmpty());
        editButton.setEnabled(listSelection.size() == 1);

    }

    private void editDocumentAction(final ISelection selection) {
        if (!selection.isEmpty()) {
            final Document selectedDocument = (Document) ((IStructuredSelection) selection).getFirstElement();
            final DocumentWizard documentWizard = new DocumentWizard(getEObject(), selectedDocument, true);
            final Dialog dialog = new CustomWizardDialog(Display.getDefault().getActiveShell(), documentWizard,
                    IDialogConstants.OK_LABEL);
            dialog.open();
            documentViewer.refresh();
            documentViewer.setSelection(new StructuredSelection(documentWizard.getDocumentWorkingCopy()));
        }
    }

    private int openOutlineDialog(final IStructuredSelection selection) {
        final StringBuilder sb = new StringBuilder();
        for (final Object selectionElement : selection.toList()) {
            if (selectionElement instanceof Document) {
                sb.append(((Document) selectionElement).getName() + "\n");
            }
        }
        if (sb.length() > 0) {
            sb.delete(sb.length() - 1, sb.length());
        }
        final String[] buttonList = { IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL };
        final java.util.List<Object> selectionList = ((IStructuredSelection) documentViewer.getSelection()).toList();
        final OutlineDialog dialog = new OutlineDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                removalConfirmationDialogTitle, Display
                        .getCurrent().getSystemImage(SWT.ICON_WARNING),
                NLS.bind(Messages.areYouSureMessage, sb.toString()), MessageDialog.CONFIRM, buttonList,
                1, selectionList);
        return dialog.open();
    }

    @Override
    protected void createContent(final Composite parent) {
        final Composite mainComposite = getWidgetFactory().createComposite(parent);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createMainComposite(mainComposite);
    }
}
