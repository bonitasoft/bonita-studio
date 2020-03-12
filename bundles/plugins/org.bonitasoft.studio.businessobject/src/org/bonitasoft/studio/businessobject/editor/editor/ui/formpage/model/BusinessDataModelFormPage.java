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
package org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.model;

import java.util.Collection;
import java.util.Objects;

import org.bonitasoft.studio.businessobject.editor.editor.BusinessDataModelEditorContribution;
import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.AbstractBdmFormPage;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.Package;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.swt.widgets.Display;

public class BusinessDataModelFormPage extends AbstractBdmFormPage implements IDocumentListener {

    private BusinessDataModelFormPart businessDataModelFormPart;

    public BusinessDataModelFormPage(String id, String title, IEclipseContext context,
            BusinessDataModelEditorContribution editorContribution) {
        super(id, title, context, editorContribution);
    }

    @Override
    protected void createForm() {
        businessDataModelFormPart = new BusinessDataModelFormPart(toolkit.createComposite(scrolledForm.getBody()), this);
        getDocument().addDocumentListener(this);
        getManagedForm().addPart(businessDataModelFormPart);
        Display.getDefault().asyncExec(() -> businessDataModelFormPart.showBusinessObjectSelection());
    }

    @Override
    public void update() {
        if ((businessDataModelFormPart != null && (businessDataModelFormPart.isStale() || isErrorState()))) {
            BusinessObject boSelected = boSelectedObservable.getValue();
            updateWorkingCopy();
            if (boSelected != null) {
                workingCopyObservable.getValue().getPackages().stream()
                        .map(Package::getBusinessObjects)
                        .flatMap(Collection::stream)
                        .filter(newBo -> Objects.equals(newBo.getSimpleName(), boSelected.getSimpleName()))
                        .findFirst().ifPresent(boSelectedObservable::setValue);
            }
        }
    }

    @Override
    public void makeDirty() {
        if (businessDataModelFormPart != null && !businessDataModelFormPart.isDirty()) {
            businessDataModelFormPart.markDirty();
            getManagedForm().dirtyStateChanged();
        }
    }

    public void makeStale() {
        if (businessDataModelFormPart != null) {
            businessDataModelFormPart.markStale();
        }
    }

    public boolean isStale() {
        return businessDataModelFormPart != null && businessDataModelFormPart.isStale();
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

    public void updateFieldDetailsTopControl() {
        businessDataModelFormPart.updateFieldDetailsTopControl();
    }

    @Override
    public void refreshBusinessObjectList() {
        if (businessDataModelFormPart != null) {
            businessDataModelFormPart.refreshBusinessObjectList();
        }
    }

    @Override
    public void updateDeployRequiredComposite(Boolean deployRequired) {
        if (businessDataModelFormPart != null) {
            businessDataModelFormPart.updateDeployRequiredComposite(deployRequired);
        }
    }

}
