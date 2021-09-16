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
package org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.query;

import org.bonitasoft.studio.businessobject.editor.editor.BusinessDataModelEditorContribution;
import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.AbstractBdmFormPage;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.swt.widgets.Display;

public class QueryFormPage extends AbstractBdmFormPage implements IDocumentListener {

    QueryFormPart queryFormPart;

    public QueryFormPage(String id, String title, IEclipseContext context,
            BusinessDataModelEditorContribution editorContribution) {
        super(id, title, context, editorContribution);
    }

    @Override
    protected void createForm() {
        queryFormPart = new QueryFormPart(toolkit.createComposite(scrolledForm.getBody()), this);
        getDocument().addDocumentListener(this);
        getManagedForm().addPart(queryFormPart);
        Display.getDefault().asyncExec(() -> queryFormPart.showBusinessObjectSelection());
    }

    @Override
    public void update() {
        if ((queryFormPart != null && (queryFormPart.isStale() || isErrorState()))) {
            updateWorkingCopy();
        }
    }

    @Override
    public void makeDirty() {
        if (queryFormPart != null && !queryFormPart.isDirty()) {
            queryFormPart.markDirty();
            getManagedForm().dirtyStateChanged();
        }
    }

    public void makeStale() {
        if (queryFormPart != null) {
            queryFormPart.markStale();
        }
    }

    public boolean isStale() {
        return queryFormPart != null && queryFormPart.isStale();
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

    public void refreshQueryViewers() {
        if (queryFormPart != null) {
            queryFormPart.refreshQueryList();
        }
    }

    @Override
    public void refreshBusinessObjectList() {
        if (queryFormPart != null) {
            queryFormPart.refreshBusinessObjectList();
        }
    }

}
