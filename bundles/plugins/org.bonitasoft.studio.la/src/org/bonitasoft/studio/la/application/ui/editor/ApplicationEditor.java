/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.ui.editor;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;

import org.bonitasoft.engine.business.application.exporter.ApplicationNodeContainerConverter;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.ui.editors.xmlEditors.AbstractEditor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.xml.sax.SAXException;

public class ApplicationEditor extends AbstractEditor<ApplicationNodeContainer> {

    public static final String EDITOR_ID = "org.bonitasoft.studio.la.editor";

    protected final ApplicationNodeContainerConverter applicationNodeContainerConverter = RepositoryManager.getInstance()
            .getRepositoryStore(ApplicationRepositoryStore.class).getConverter();

    @Override
    protected void initVariablesAndListeners() {
        final IDocument document = fSourceEditor.getDocumentProvider().getDocument(getEditorInput());
        try {
            workingCopy = applicationNodeContainerConverter.unmarshallFromXML(document.get().getBytes());
            formPage.init(workingCopy, document);
        } catch (JAXBException | IOException | SAXException e) {
            workingCopy = new ApplicationNodeContainer();
            formPage.init(workingCopy, document);
            formPage.setErrorState(true);
            setActivePage(0);
            setActiveEditor(fSourceEditor);
        }
    }

    @Override
    protected void createFormPage() {
        formPage = new ApplicationFormPage("Editor",
                org.bonitasoft.studio.ui.i18n.Messages.editor,
                applicationNodeContainerConverter,
                getContext());
    }

    @Override
    protected Optional<ApplicationNodeContainer> xmlToModel(byte[] xml) {
        try {
            return Optional.of(applicationNodeContainerConverter.unmarshallFromXML(xml));
        } catch (final UnmarshalException unmarshalException) {
            formPage.setErrorState(true);
            formPage.loadErrorPage();
        } catch (JAXBException | IOException | SAXException e) {
            throw new RuntimeException("Fail to update the applicationNodeContainer", e);
        }
        return Optional.empty();
    }

    @Override
    protected void updateWorkingCopy(ApplicationNodeContainer newModel) {
        workingCopy.getApplications().clear();
        workingCopy.getApplications().addAll(newModel.getApplications());
    }

    @Override
    protected void pageChange(int newPageIndex) {
        if (newPageIndex != formPage.getIndex()) {
            ((ApplicationFormPage) formPage).saveExpendedApplications();
        }
        super.pageChange(newPageIndex);
    }

    public static Optional<ApplicationEditor> getOpenedEditor(ApplicationFileStore fileStore) {
        if (PlatformUI.isWorkbenchRunning()
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null) {
            final String resourceName = fileStore.getResource().getName();
            for (IEditorReference ref : PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                    .getEditorReferences()) {
                try {
                    IEditorInput editorInput = ref.getEditorInput();
                    if (editorInput != null && Objects.equals(resourceName, editorInput.getName())) {
                        IEditorPart editor = ref.getEditor(false);
                        if (editor instanceof ApplicationEditor) {
                            return Optional.ofNullable((ApplicationEditor) editor);
                        }
                    }
                } catch (PartInitException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void dispose() {
        if (formPage != null) {
            ((ApplicationFormPage) formPage).disposeProviders();
        }
        super.dispose();
    }
}
