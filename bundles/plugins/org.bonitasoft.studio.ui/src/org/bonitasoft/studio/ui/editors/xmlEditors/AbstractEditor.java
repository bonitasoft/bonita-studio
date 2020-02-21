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
package org.bonitasoft.studio.ui.editors.xmlEditors;

import java.util.Arrays;
import java.util.Optional;

import org.bonitasoft.studio.ui.i18n.Messages;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.wst.sse.ui.StructuredTextEditor;

public abstract class AbstractEditor<T> extends FormEditor implements IResourceChangeListener {

    protected AbstractFormPage<T> formPage;
    protected StructuredTextEditor fSourceEditor;
    protected T workingCopy;
    private IEclipseContext context;
    private boolean resourceChangeEventSkip = false;
    private Composite pageContainer;

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        super.init(site, input);
        context = ((WorkbenchWindow) getEditorSite().getWorkbenchWindow()).getModel().getContext();
        ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
    }

    @Override
    public void dispose() {
        super.dispose();
        ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
    }

    public T getWorkingCopy() {
        return workingCopy;
    }

    @Override
    protected Composite createPageContainer(Composite parent) {
        this.pageContainer = super.createPageContainer(parent);
        return pageContainer;
    }

    @Override
    protected void addPages() {
        createFormPage();
        formPage.initialize(this);
        formPage.setActive(true);

        try {
            setPageText(addPage(formPage), Messages.editor);
            setPageText(addPage(createSourceEditor(), getEditorInput()), Messages.source);
            initVariablesAndListeners();
            addPageChangedListener(e -> formPage.reflow());
            customizeTabItem();
        } catch (final PartInitException e) {
            throw new RuntimeException("fail to create editor", e);
        }
    }

    @Override
    protected void pageChange(int newPageIndex) {
        if (newPageIndex == formPage.getIndex()) {
            formPage.update();
        }
        super.pageChange(newPageIndex);
    }

    private void customizeTabItem() {
        Arrays.asList(pageContainer.getChildren()).stream()
                .filter(CTabFolder.class::isInstance)
                .map(CTabFolder.class::cast)
                .findFirst()
                .ifPresent(cTabFolder -> {
                    cTabFolder.setBorderVisible(true);
                    cTabFolder.setTabPosition(SWT.TOP);
                });
    }

    @Override
    public String getPartName() {
        return getEditorInput().getName();
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        try {
            resourceChangeEventSkip = true;
            formPage.doSave(monitor);
            fSourceEditor.doSave(monitor);
        } finally {
            resourceChangeEventSkip = false;
        }
    }

    @Override
    public void doSaveAs() {
        fSourceEditor.doSaveAs();
    }

    @Override
    public boolean isDirty() {
        return fSourceEditor.isDirty() || formPage.isDirty();
    }

    @Override
    public boolean isSaveAsAllowed() {
        return fSourceEditor.isSaveAsAllowed();
    }

    @Override
    public void resourceChanged(IResourceChangeEvent e) {
        IResourceDelta delta = e.getDelta();
        final FileEditorInput fInput = (FileEditorInput) getEditorInput();
        final IFile file = fInput.getFile();
        if (delta != null) {
            delta = delta.findMember(file.getFullPath());
        }
        if (delta != null) {
            final int flags = delta.getFlags();
            if (delta.getKind() == IResourceDelta.REMOVED && (IResourceDelta.MOVED_TO & flags) != 0) {
                updateEditorInput(
                        new FileEditorInput(file.getWorkspace().getRoot().getFile(delta.getMovedToPath())));
            } else if (delta.getKind() == IResourceDelta.CHANGED) {
                if ((flags & IResourceDelta.CONTENT) != 0 || (flags & IResourceDelta.REPLACED) != 0) {
                    if (!resourceChangeEventSkip) {
                        FileEditorInput newEditorInput = new FileEditorInput(delta.getResource().getAdapter(IFile.class));
                        Display.getDefault().asyncExec(() -> updateEditorInput(newEditorInput));
                    }
                }
            }
        }
    }

    public void updateEditorInput(IEditorInput input) {
        Display.getDefault().asyncExec(() -> {
            fSourceEditor.setInput(input);
            setInputWithNotify(input);
            setPartName(input.getName());
            initVariablesAndListeners();
            formPage.recreateForm();
            formPage.reflow();
        });
    }

    protected abstract void createFormPage();

    protected abstract void initVariablesAndListeners();

    protected abstract Optional<T> xmlToModel(byte[] xml);

    protected abstract void updateWorkingCopy(T newModel);

    private IEditorPart createSourceEditor() {
        fSourceEditor = new StructuredTextEditor();
        fSourceEditor.setEditorPart(this);
        return fSourceEditor;
    }

    public StructuredTextEditor getSourceEditor() {
        return fSourceEditor;
    }

    public AbstractFormPage<T> getFormPage() {
        return formPage;
    }

    public IEclipseContext getContext() {
        return context;
    }

}
