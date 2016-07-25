/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.document.ui;

import org.bonitasoft.studio.common.jface.dialog.FinishAndAddCustomWizardDialog;
import org.bonitasoft.studio.document.i18n.Messages;
import org.eclipse.swt.widgets.Shell;

public class DocumentWizardDialog extends FinishAndAddCustomWizardDialog {

    public DocumentWizardDialog(final Shell parentShell, final DocumentWizard newWizard, final boolean withFinishAndAddButton) {
        super(parentShell, newWizard, withFinishAndAddButton);
        setTitle(Messages.newDocument);
    }

    @Override
    protected void actionOnFinishAndAdd() {
        final DocumentWizardDialog documentWizardDialog = new DocumentWizardDialog(getParentShell(), new DocumentWizard(getWizard().getContext()), true);
        documentWizardDialog.open();
    }

    @Override
    protected DocumentWizard getWizard() {
        return (DocumentWizard) super.getWizard();
    }

}
