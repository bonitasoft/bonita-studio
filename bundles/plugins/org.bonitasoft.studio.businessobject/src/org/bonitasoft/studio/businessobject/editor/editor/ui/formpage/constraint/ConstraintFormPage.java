/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.constraint;

import org.bonitasoft.studio.businessobject.editor.editor.BusinessDataModelEditorContribution;
import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.AbstractBdmFormPage;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.swt.widgets.Display;

public class ConstraintFormPage extends AbstractBdmFormPage implements IDocumentListener {

    private ConstraintFormPart constraintFormPart;

    public ConstraintFormPage(String id, String title, IEclipseContext context,
            BusinessDataModelEditorContribution editorContribution) {
        super(id, title, context, editorContribution);
    }

    @Override
    protected void createForm() {
        constraintFormPart = new ConstraintFormPart(toolkit.createComposite(scrolledForm.getBody()), this);
        getDocument().addDocumentListener(this);
        getManagedForm().addPart(constraintFormPart);
        Display.getDefault().asyncExec(() -> constraintFormPart.showBusinessObjectSelection());
    }

    @Override
    public void update() {
        if ((constraintFormPart != null && (constraintFormPart.isStale() || isErrorState()))) {
            updateWorkingCopy();
        }
    }

    @Override
    public void makeDirty() {
        if (constraintFormPart != null && !constraintFormPart.isDirty()) {
            constraintFormPart.markDirty();
            getManagedForm().dirtyStateChanged();
        }
    }

    public void makeStale() {
        if (constraintFormPart != null) {
            constraintFormPart.markStale();
        }
    }

    public boolean isStale() {
        return constraintFormPart != null && constraintFormPart.isStale();
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

    public void refreshConstraintList() {
        if (constraintFormPart != null) {
            constraintFormPart.refreshConstraintList();
        }
    }

    @Override
    public void refreshBusinessObjectList() {
        if (constraintFormPart != null) {
            constraintFormPart.refreshBusinessObjectList();
        }
    }

    @Override
    public void updateDeployRequiredComposite(Boolean deployRequired) {
        if (constraintFormPart != null) {
            constraintFormPart.updateDeployRequiredComposite(deployRequired);
        }
    }

}
