/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.ui.editor;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;

import org.bonitasoft.engine.business.application.exporter.ApplicationNodeContainerConverter;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.studio.la.i18n.Messages;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.wst.sse.ui.StructuredTextEditor;
import org.xml.sax.SAXException;

public class ApplicationEditor extends FormEditor {

    public static final String EDITOR_ID = "org.bonitasoft.studio.la.editor";
    private final ApplicationNodeContainerConverter applicationNodeContainerConverter = new ApplicationNodeContainerConverter();
    private ApplicationNodeContainer applicationWorkingCopy;
    private StructuredTextEditor fSourceEditor;
    private ApplicationOverviewPage overviewPage;

    @Override
    protected void addPages() {
        overviewPage = new ApplicationOverviewPage("overview", Messages.overview);
        overviewPage.initialize(this);
        overviewPage.setActive(true);
        try {
            setPageText(addPage(overviewPage), Messages.overview);
            setPageText(addPage(createSourceEditor(), getEditorInput()), Messages.source);
            initVariablesAndListeners();
            addPageChangedListener(e -> overviewPage.reflow());
        } catch (final PartInitException e) {
            throw new RuntimeException("fail to create editor", e);
        }
    }

    private void initVariablesAndListeners() {
        final IDocument document = fSourceEditor.getDocumentProvider().getDocument(getEditorInput());
        try {
            applicationWorkingCopy = applicationNodeContainerConverter.unmarshallFromXML(document.get().getBytes());
        } catch (JAXBException | IOException | SAXException e) {
            throw new RuntimeException("fail to read XML document", e);
        }
        overviewPage.init(applicationWorkingCopy, document);
    }

    @Override
    public String getPartName() {
        return getEditorInput().getName();
    }

    private IEditorPart createSourceEditor() {
        fSourceEditor = new StructuredTextEditor();
        fSourceEditor.setEditorPart(this);
        return fSourceEditor;
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        fSourceEditor.doSave(monitor);
    }

    @Override
    public void doSaveAs() {
        fSourceEditor.doSaveAs();
    }

    @Override
    public boolean isDirty() {
        return fSourceEditor.isDirty();
    }

    @Override
    public boolean isSaveAsAllowed() {
        return fSourceEditor.isSaveAsAllowed();
    }

    @Override
    public Object getAdapter(Class adapter) {
        return fSourceEditor.getAdapter(adapter);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.forms.editor.FormEditor#pageChange(int)
     */
    @Override
    protected void pageChange(int newPageIndex) {
        if (newPageIndex == overviewPage.getIndex()) {
            try {
                final ApplicationNodeContainer newAppNode = applicationNodeContainerConverter
                        .unmarshallFromXML(
                                fSourceEditor.getDocumentProvider().getDocument(getEditorInput()).get().getBytes());
                applicationWorkingCopy.getApplications().clear();
                applicationWorkingCopy.getApplications().addAll(newAppNode.getApplications());
                overviewPage.update();
            } catch (UnmarshalException unmarshalException) {
                // let it be -> disable overview ? 
            } catch (JAXBException | IOException | SAXException e) {
                throw new RuntimeException("Fail to update the applicationNodeContainer", e);
            }
        }
        super.pageChange(newPageIndex);
    }
}
