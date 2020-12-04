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
package org.bonitasoft.studio.identity.organization.editor.formpage.role;

import org.bonitasoft.studio.identity.organization.editor.formpage.AbstractOrganizationFormPage;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.widgets.ToolBar;

public class RoleFormPage extends AbstractOrganizationFormPage {

    private RoleFormPart roleFormPart;

    public RoleFormPage(String id, String title, IEclipseContext context) {
        super(id, title, context);
    }

    @Override
    protected void createHeaderContent(ToolBar toolBar) {
        // TODO Auto-generated method stub
    }

    @Override
    protected void createForm() {
        roleFormPart = new RoleFormPart(scrolledForm.getBody(), this);
        getManagedForm().addPart(roleFormPart);
    }

}
