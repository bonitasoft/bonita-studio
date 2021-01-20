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

import org.bonitasoft.studio.identity.organization.editor.control.overview.OverviewEditionControl;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.AbstractFormPart;

public class OverviewFormPart extends AbstractFormPart {

    private DataBindingContext ctx = new DataBindingContext();
    private OverviewFormPage formPage;
    private OverviewEditionControl overviewEditionControl;

    public OverviewFormPart(Composite parent, OverviewFormPage formPage) {
        this.formPage = formPage;

        parent.setLayout(GridLayoutFactory.fillDefaults().margins(5, 5).numColumns(2)
                .spacing(20, LayoutConstants.getSpacing().y).create());
        parent.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createOverviewControl(parent);
    }

    private void createOverviewControl(Composite parent) {
        overviewEditionControl = new OverviewEditionControl(parent, formPage, formPage.observeWorkingCopy(), ctx);
    }

    @Override
    public void commit(boolean onSave) {
        formPage.commit();
        super.commit(onSave);
        if (onSave) {
            getManagedForm().dirtyStateChanged();
        }
    }

    public void updateDefaultUserViewerInput() {
        overviewEditionControl.updateDefaultUserViewerInput();
    }

    public void refreshList() {
        overviewEditionControl.refreshList();
    }

}
