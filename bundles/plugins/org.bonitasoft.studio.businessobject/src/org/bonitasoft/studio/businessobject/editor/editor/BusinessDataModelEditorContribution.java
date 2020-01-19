/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.editor.editor;

import java.io.IOException;
import java.util.Collection;

import javax.xml.bind.JAXBException;

import org.bonitasoft.engine.bdm.BusinessObjectModelConverter;
import org.bonitasoft.studio.businessobject.core.repository.AbstractBDMFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BDMArtifactDescriptor;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.editor.converter.BusinessDataModelConverter;
import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.constraint.ConstraintFormPage;
import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.index.IndexFormPage;
import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.model.BusinessDataModelFormPage;
import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.query.QueryFormPage;
import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelFactory;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.editor.model.Package;
import org.bonitasoft.studio.businessobject.editor.validator.BusinessObjectListValidator;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.ui.editors.xmlEditors.AbstractEditorContribution;
import org.bonitasoft.studio.ui.editors.xmlEditors.AbstractMultiSourceFormEditor;
import org.bonitasoft.studio.ui.editors.xmlEditors.ReadOnlyStructuredTextEditor;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.part.NullEditorInput;
import org.eclipse.ui.part.FileEditorInput;
import org.xml.sax.SAXException;

public class BusinessDataModelEditorContribution extends AbstractEditorContribution {

    public static final String ID = "bdmEditorContributionId";
    private static final String ACCESS_CONTROL_ID = "accessControlEditorContributionId";

    private AbstractMultiSourceFormEditor editor;

    private ReadOnlyStructuredTextEditor sourceEditor;
    private BusinessDataModelFormPage modelFormPage;
    private ConstraintFormPage constraintFormPage;
    private QueryFormPage queryFormPage;
    private IndexFormPage indexFormPage;
    private BusinessObjectModelConverter parser = new BusinessObjectModelConverter(); // marshaller
    private BusinessDataModelConverter converter = new BusinessDataModelConverter(); // emf - engine converter 
    private IObservableValue<BusinessObjectModel> workingCopyObservable = new WritableValue<>();
    private IObservableValue<BusinessObject> boSelectedObservable;
    private int sourceEditorIndex;
    private BusinessObjectListValidator validator;

    @Override
    public void addPages(AbstractMultiSourceFormEditor editor) throws PartInitException {
        this.editor = editor;
        modelFormPage = new BusinessDataModelFormPage("Model", Messages.modelPageName, editor.getEclipseContext(), this);
        modelFormPage.initialize(editor);
        constraintFormPage = new ConstraintFormPage("Constraints", Messages.constraintsPageName, editor.getEclipseContext(),
                this);
        constraintFormPage.initialize(editor);
        queryFormPage = new QueryFormPage("Queries", Messages.queries, editor.getEclipseContext(), this);
        queryFormPage.initialize(editor);
        indexFormPage = new IndexFormPage("Indexes", Messages.indexes, editor.getEclipseContext(), this);
        indexFormPage.initialize(editor);
        sourceEditor = new ReadOnlyStructuredTextEditor();
        sourceEditor.setEditorPart(editor);

        editor.addPage(modelFormPage);
        editor.addPage(constraintFormPage);
        editor.addPage(queryFormPage);
        editor.addPage(indexFormPage);
        sourceEditorIndex = editor.addPage(sourceEditor, input);
        editor.setPageTitle(sourceEditorIndex, input.getName());
        initFormPages();

        validator = new BusinessObjectListValidator(workingCopyObservable);
    }

    private void initFormPages() {
        final IDocument document = sourceEditor.getDocumentProvider().getDocument(input);
        try {
            boSelectedObservable = new WritableValue<>();
            modelFormPage.init(workingCopyObservable, boSelectedObservable, document);
            constraintFormPage.init(workingCopyObservable, boSelectedObservable, document);
            queryFormPage.init(workingCopyObservable, boSelectedObservable, document);
            indexFormPage.init(workingCopyObservable, boSelectedObservable, document);
            DirtyStateAdapter dirtyStateAdapter = new DirtyStateAdapter(modelFormPage, constraintFormPage, queryFormPage,
                    indexFormPage);
            workingCopyObservable.addValueChangeListener(e -> {
                dirtyStateAdapter.setIgnore(true);
                try {
                    BusinessObjectModel oldBDM = e.diff.getOldValue();
                    if (oldBDM != null) {
                        oldBDM.eAdapters().remove(dirtyStateAdapter);
                    }
                    BusinessObjectModel newBDM = e.diff.getNewValue();
                    if (newBDM != null) {
                        newBDM.eAdapters().add(dirtyStateAdapter);
                    }
                } finally {
                    dirtyStateAdapter.setIgnore(false);
                }
            });
            BDMArtifactDescriptor artifactDescriptor = loadBdmArtifactDescriptor();
            workingCopyObservable
                    .setValue(converter.toEmfModel(parser.unmarshall(document.get().getBytes()), artifactDescriptor));
        } catch (JAXBException | IOException | SAXException e) {
            workingCopyObservable.setValue(BusinessDataModelFactory.eINSTANCE.createBusinessObjectModel());
            modelFormPage.init(workingCopyObservable, new WritableValue<>(), document);
            modelFormPage.setErrorState(true);
        }
    }

    public BDMArtifactDescriptor loadBdmArtifactDescriptor() {
        try {
            return retrieveBdmFileStore().loadArtifactDescriptor();
        } catch (CoreException e) {
            throw new RuntimeException("An error occured while loading BDM artifact descriptor", e);
        }
    }

    public void saveBdmArtifactDescriptor(BDMArtifactDescriptor descriptor) {
        try {
            retrieveBdmFileStore().saveArtifactDescriptor(descriptor);
        } catch (CoreException e) {
            throw new RuntimeException("An error occured while saving BDM artifact descriptor", e);
        }
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        if (workingCopyObservable.getValue().getPackages().stream()
                .map(Package::getBusinessObjects)
                .flatMap(Collection::stream).count() == 0) {
            MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.modelNotSavableTitle,
                    Messages.emptyModelNotSavable);
        } else if (workingCopyObservable.getValue().getPackages().stream() // TODO on packages
                .map(Package::getBusinessObjects)
                .flatMap(Collection::stream)
                .anyMatch(bo -> validator.validate(bo).getSeverity() == ValidationStatus.ERROR)) {
            MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.modelNotSavableTitle,
                    Messages.modelNotSavable);
        } else {
            modelFormPage.doSave(monitor);
            constraintFormPage.doSave(monitor);
            queryFormPage.doSave(monitor);
            indexFormPage.doSave(monitor);
            sourceEditor.doSave(monitor);
        }
    }

    @Override
    public boolean isDirty() {
        return sourceEditor.isDirty()
                || modelFormPage.isDirty()
                || constraintFormPage.isDirty()
                || queryFormPage.isDirty()
                || indexFormPage.isDirty();
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void resourceChanged(IResourceChangeEvent event) {
        // Nothing to do here, used when a file can be renamed
    }

    @Override
    public String getEditorId() {
        return BusinessDataModelEditor.EDITOR_ID;
    }

    @Override
    protected IEditorInput initEditorInput() {
        AbstractBDMFileStore bdmFileStore = retrieveBdmFileStore();
        return bdmFileStore != null
                ? new FileEditorInput(bdmFileStore.getResource())
                : new NullEditorInput();
    }

    private BusinessObjectModelFileStore retrieveBdmFileStore() {
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = repositoryAccessor
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        return repositoryStore.getChild("bom.xml", false);
    }

    @Override
    public int getContributionIndex() {
        return 0;
    }

    @Override
    public boolean canContribute() {
        return retrieveBdmFileStore() != null;
    }

    @Override
    public int getMainPageIndex() {
        return modelFormPage.getIndex();
    }

    @Override
    protected void pageChange(int newPageIndex) {
        if (editor.getCurrentPageIndex() == sourceEditorIndex) {
            modelFormPage.update(); // reload shared working copy (text editor -> graphical editor)
        } else {
            if (modelFormPage.getIndex() == newPageIndex) {
                modelFormPage.refreshBusinessObjectList();
            } else if (constraintFormPage.getIndex() == newPageIndex) {
                constraintFormPage.refreshConstraintList();
            } else if (queryFormPage.getIndex() == newPageIndex) {
                queryFormPage.refreshQueryViewers();
            } else if (indexFormPage.getIndex() == newPageIndex) {
                indexFormPage.refreshIndexViewers();
            }
        }
    }

    public BusinessObjectModelConverter getParser() {
        return parser;
    }

    public BusinessDataModelConverter getConverter() {
        return converter;
    }

    public void refreshBusinessObjectList() {
        modelFormPage.refreshBusinessObjectList();
        constraintFormPage.refreshBusinessObjectList();
        queryFormPage.refreshBusinessObjectList();
        indexFormPage.refreshBusinessObjectList();
    }

    public void refreshConstraintList() {
        constraintFormPage.refreshConstraintList();
    }

    public void refreshIndexList() {
        indexFormPage.refreshIndexViewers();
    }

    public void refreshQueryViewers() {
        queryFormPage.refreshQueryViewers();
    }

    public BusinessObjectModel getWorkingCopy() {
        return workingCopyObservable.getValue();
    }

    public void makeAccessControlStale() {
        editor.getEditorContribution(ACCESS_CONTROL_ID).ifPresent(AbstractEditorContribution::makeStale);
    }

    @Override
    public void makeStale() {
        modelFormPage.makeStale();
        constraintFormPage.makeStale();
        queryFormPage.makeStale();
        indexFormPage.makeStale();
    }

}
