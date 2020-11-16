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
package org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.index;

import org.bonitasoft.studio.businessobject.editor.editor.BusinessDataModelEditorContribution;
import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.AbstractBdmFormPage;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.swt.widgets.Display;

public class IndexFormPage extends AbstractBdmFormPage implements IDocumentListener {

    IndexFormPart indexFormPart;

    public IndexFormPage(String id, String title, IEclipseContext context,
            BusinessDataModelEditorContribution editorContribution) {
        super(id, title, context, editorContribution);
    }

    @Override
    protected void createForm() {
        indexFormPart = new IndexFormPart(toolkit.createComposite(scrolledForm.getBody()), this);
        getDocument().addDocumentListener(this);
        getManagedForm().addPart(indexFormPart);
        Display.getDefault().asyncExec(() -> indexFormPart.showBusinessObjectSelection());
    }

    @Override
    public void update() {
        if ((indexFormPart != null && (indexFormPart.isStale() || isErrorState()))) {
            updateWorkingCopy();
        }
    }

    @Override
    public void makeDirty() {
        if (indexFormPart != null && !indexFormPart.isDirty()) {
            indexFormPart.markDirty();
            getManagedForm().dirtyStateChanged();
        }
    }

    public void makeStale() {
        if (indexFormPart != null) {
            indexFormPart.markStale();
        }
    }

    public boolean isStale() {
        return indexFormPart != null && indexFormPart.isStale();
    }

    @Override
    public void documentAboutToBeChanged(DocumentEvent event) {
    }

    @Override
    public void documentChanged(DocumentEvent event) {
        if (!isActive()) {
            makeStale();
        }
    }

    public void refreshIndexViewers() {
        if (indexFormPart != null) {
            indexFormPart.refreshIndexList();
        }
    }

    @Override
    public void refreshBusinessObjectList() {
        if (indexFormPart != null) {
            indexFormPart.refreshBusinessObjectList();
        }
    }

}
