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
package org.bonitasoft.studio.data.ui.wizard;

import org.bonitasoft.studio.common.jface.dialog.FinishAndAddCustomWizardDialog;
import org.bonitasoft.studio.data.ui.property.section.IAddData;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;

/**
 * @author aurelie zara
 */
public class DataWizardDialog extends FinishAndAddCustomWizardDialog {

    // Minimum dialog width (in dialog units)
    private static final int MIN_DIALOG_WIDTH = 350;

    // Minimum dialog height (in dialog units)
    private static final int MIN_DIALOG_HEIGHT = 270;

    private final IAddData addData;

    public DataWizardDialog(final Shell parentShell, final Wizard newWizard, final IAddData addData) {
        super(parentShell, newWizard, addData != null);
        this.addData = addData;
    }

    @Override
    protected Point getInitialSize() {
        final Point shellSize = super.getInitialSize();
        return new Point(Math.max(
                convertHorizontalDLUsToPixels(MIN_DIALOG_WIDTH), shellSize.x),
                Math.max(convertVerticalDLUsToPixels(MIN_DIALOG_HEIGHT),
                        shellSize.y));
    }

    @Override
    protected void actionOnFinishAndAdd() {
        if (addData != null) {
            addData.addData();
        }
    }
}
