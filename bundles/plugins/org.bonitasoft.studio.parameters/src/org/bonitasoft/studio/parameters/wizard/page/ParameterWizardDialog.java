/*******************************************************************************
 * Copyright (C) 2013-2014 Bonitasoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
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
