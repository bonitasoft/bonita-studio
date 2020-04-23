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
package org.bonitasoft.studio.businessobject.editor.editor.ui.control;

import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.AbstractBdmFormPage;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;

public class DeployRequiredControl extends Composite {

    public DeployRequiredControl(Composite parent, AbstractBdmFormPage formPage, GridData gridData) {
        super(parent, SWT.NONE);
        setLayout(GridLayoutFactory.fillDefaults().create());
        setLayoutData(gridData);
        formPage.getToolkit().adapt(this);

        Group group = new Group(this, SWT.NONE);
        group.setLayout(GridLayoutFactory.fillDefaults().create());
        group.setLayoutData(GridDataFactory.fillDefaults().create());

        CLabel label = new CLabel(group, SWT.WRAP);
        label.setLayoutData(GridDataFactory.fillDefaults().create());
        label.setText(Messages.bdmDeployRequired);
        label.setImage(JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_WARNING));

        boolean show = formPage.getEditorContribution().observeDeployRequired().getValue();
        gridData.exclude = !show;
        setVisible(show);
    }

    public void hide() {
        Display.getDefault().asyncExec(() -> {
            if (!isDisposed()) {
                setVisible(false);
                ((GridData) getLayoutData()).exclude = true;
                getParent().layout();
            }
        });
    }

    public void show() {
        Display.getDefault().asyncExec(() -> {
            if (!isDisposed()) {
                setVisible(true);
                ((GridData) getLayoutData()).exclude = false;
                getParent().layout();
            }
        });
    }

}
