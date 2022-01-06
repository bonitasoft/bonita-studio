/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.identity.organization.editor.formpage.overview;

import org.bonitasoft.studio.identity.organization.editor.OrganizationEditor;
import org.bonitasoft.studio.identity.organization.editor.formpage.AbstractOrganizationFormPage;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.ui.forms.AbstractFormPart;

public class OverviewFormPage extends AbstractOrganizationFormPage {

    private OverviewFormPart overviewFormPart;

    public OverviewFormPage(String id, String title, IEclipseContext context, OrganizationEditor editor) {
        super(id, title, context, editor);
    }

    @Override
    protected void createForm() {
        super.createForm();
        overviewFormPart = new OverviewFormPart(scrolledForm.getBody(), this);
        getManagedForm().addPart(overviewFormPart);
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

    @Override
    public void makeStale() {
        if (overviewFormPart != null) {
            overviewFormPart.markStale();
        }
    }

    @Override
    public AbstractFormPart getFormPart() {
        return overviewFormPart;
    }

    @Override
    public void refreshList() {
        if (overviewFormPart != null) {
            overviewFormPart.refreshList();
        }
    }

    @Override
    public void refreshOverviewGroupList() {
        if (overviewFormPart != null) {
            overviewFormPart.refreshGroupList();
        }
    }

    @Override
    public void refreshOverviewUserList() {
        if (overviewFormPart != null) {
            overviewFormPart.refreshUserList();
        }
    }

    @Override
    public void refreshOverviewRoleList() {
        if (overviewFormPart != null) {
            overviewFormPart.refreshRoleList();
        }
    }

    @Override
    public void updateDefaultUserViewerInput() {
        overviewFormPart.updateDefaultUserViewerInput();
    }

}
