/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.parameters.wizard.page;

import org.bonitasoft.studio.common.jface.dialog.FinishAndAddCustomWizardDialog;
import org.bonitasoft.studio.parameters.i18n.Messages;
import org.bonitasoft.studio.parameters.property.section.ParameterPropertySection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Maxence Raoux
 */
public class ParameterWizardDialog extends FinishAndAddCustomWizardDialog {

    public static final int CREATE_AND_NEW_ID = 1255;

    // Minimum dialog width (in dialog units)
    private static final int MIN_DIALOG_WIDTH = 350;

    // Minimum dialog height (in dialog units)
    private static final int MIN_DIALOG_HEIGHT = 200;

    private ParameterPropertySection parameterPropertySection;

    public ParameterWizardDialog(final Shell parentShell, final IWizard newWizard, final ParameterPropertySection parameterPropertySection) {
        super(parentShell, newWizard, parameterPropertySection != null);
        this.parameterPropertySection = parameterPropertySection;
        if (this.parameterPropertySection == null) {
            setTitle(Messages.editParameterWizardTitle);
        } else {
            setTitle(Messages.newParameter);
        }
    }

    public ParameterWizardDialog(final Shell parentShell, final IWizard newWizard) {
        super(parentShell, newWizard, false);
        setTitle(Messages.newParameter);
    }

    public ParameterWizardDialog(final Shell parentShell, final IWizard newWizard, final String finishLabel) {
        super(parentShell, newWizard, finishLabel);
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
        if (parameterPropertySection != null) {
            parameterPropertySection.openAddParameterWizardDialog();
        }
    }

}
