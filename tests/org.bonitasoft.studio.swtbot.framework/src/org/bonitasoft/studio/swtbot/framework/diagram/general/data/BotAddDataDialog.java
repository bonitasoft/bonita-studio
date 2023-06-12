/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.data;

import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.swtbot.framework.BotWizardDialog;
import org.bonitasoft.studio.swtbot.framework.expression.BotExpressionEditorDialog;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

/**
 * Add data dialog.
 *
 * @author Joachim Segala
 */
public class BotAddDataDialog extends BotWizardDialog {

    public BotAddDataDialog(final SWTGefBot bot) {
        super(bot, Messages.newVariable);
    }

    protected BotAddDataDialog(final SWTGefBot bot, final String dialogTitle) {
        super(bot, dialogTitle);
    }

    /**
     * Set name of data.
     *
     * @param pName
     */
    public BotAddDataDialog setName(final String pName) {
        bot.textWithLabel(Messages.name + " *").setText(pName);
        return this;
    }

    /**
     * Set description of data.
     *
     * @param pDescription
     */
    public BotAddDataDialog setDescription(final String pDescription) {
        bot.textWithLabel(Messages.dataDescriptionLabel).setText(pDescription);
        return this;
    }

    /**
     * Set type of data.
     *
     * @param pType
     */
    public BotAddDataDialog setType(final String pType) {
        bot.comboBoxWithLabel(Messages.datatypeLabel).setSelection(pType);
        return this;
    }

    public BotAddDataDialog setClassname(final String pClass) {
        bot.textWithLabel(Messages.classLabel).setText(pClass);
        return this;
    }

    public BotExpressionEditorDialog editDefaultValueExpression() {
    	SWTBotShell activeShell = bot.activeShell();
        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
        return new BotExpressionEditorDialog(bot,activeShell);
    }

    public BotAddDataDialog setAutoGenerateForm(final boolean pAutoGenerate) {
        final SWTBotCheckBox cb = bot.checkBox("Auto-generate form");
        if (cb.isChecked() && !pAutoGenerate) {
            cb.deselect();
        } else if (!cb.isChecked() && pAutoGenerate) {
            cb.select();
        }
        return this;
    }
}
