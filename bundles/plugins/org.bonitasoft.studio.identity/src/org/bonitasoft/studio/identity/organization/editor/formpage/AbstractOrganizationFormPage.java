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
package org.bonitasoft.studio.identity.organization.editor.formpage;

import java.util.Optional;

import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.util.OrganizationXMLProcessor;
import org.bonitasoft.studio.ui.editors.xmlEditors.AbstractFormPage;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.ui.forms.AbstractFormPart;

public abstract class AbstractOrganizationFormPage extends AbstractFormPage<Organization> implements IDocumentListener {

    protected IObservableValue<Organization> workingCopyObservable;
    private OrganizationXMLProcessor xmlProcessor;

    public AbstractOrganizationFormPage(String id, String title, IEclipseContext context) {
        super(id, title, context);
    }

    public void init(IObservableValue<Organization> workingCopyObservable, IDocument document,
            OrganizationXMLProcessor xmlProcessor) {
        this.xmlProcessor = xmlProcessor;
        super.init(workingCopyObservable.getValue(), document);
        this.workingCopyObservable = workingCopyObservable;
    }

    @Override
    protected void createForm() {
        getDocument().addDocumentListener(this);
    }

    public IObservableValue<Organization> observeWorkingCopy() {
        return workingCopyObservable;
    }

    public void updateWorkingCopy() {
        Optional<Organization> orga = xmlToModel(getDocument().get().getBytes());
        if (orga.isPresent()) {
            if (isErrorState()) {
                setErrorState(false);
            }
            workingCopyObservable.getRealm().exec(() -> workingCopyObservable.setValue(orga.get()));
        } else {
            setErrorState(true);
        }
    }

    public abstract AbstractFormPart getFormPart();

    public void makeDirty() {
        AbstractFormPart formPart = getFormPart();
        if (formPart != null && !formPart.isDirty()) {
            formPart.markDirty();
            getManagedForm().dirtyStateChanged();
        }
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        if (isDirty()) {
            super.doSave(monitor);
        }
    }

    public OrganizationXMLProcessor getXmlProcessor() {
        return xmlProcessor;
    }

    public abstract void makeStale();

}
