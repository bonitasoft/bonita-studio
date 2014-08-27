/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.parameters;

import org.bonitasoft.studio.parameters.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotWizardDialog;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;

/**
 * Add parameter dialog.
 *
 * @author Joachim Segala
 */
public class BotAddParameterDialog extends BotWizardDialog {

    public BotAddParameterDialog(final SWTGefBot bot) {
        super(bot);
        bot.waitUntil(Conditions.shellIsActive(Messages.newParameter));
        bot.shell(Messages.newParameter);
    }

    /**
     * Set name of parameter.
     *
     * @param pName
     */
    public void setName(final String pName) {
        bot.textWithLabel(Messages.name).setText(pName);
    }

    /**
     * Set description of parameter.
     *
     * @param pDescription
     */
    public void setDescription(final String pDescription) {
        bot.textWithLabel(Messages.parameterDescription).setText(pDescription);
    }

    /**
     * Set type of parameter.
     *
     * @param pType
     */
    public void setType(final String pType) {
        bot.comboBoxWithLabel(Messages.type).setSelection(pType);
    }

}
