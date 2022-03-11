/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.data;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.AbstractBotWizardPage;
import org.bonitasoft.studio.swtbot.framework.expression.BotExpressionEditorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;

/**
 * Add data dialog.
 *
 * @author Joachim Segala
 */
public class AbstractBotDataWizardPage extends AbstractBotWizardPage {

	public AbstractBotDataWizardPage(final SWTGefBot bot) {
        super(bot);
    }

    /**
     * Set name of data.
     * 
     * @param pName
     */
    public AbstractBotDataWizardPage setName(final String pName) {
        bot.textWithLabel(Messages.name + " *").setText(pName);
        return this;
    }

    /**
     * Set description of data.
     * 
     * @param pDescription
     */
    public AbstractBotDataWizardPage setDescription(final String pDescription) {
        bot.textWithLabel(Messages.dataDescriptionLabel).setText(pDescription);
        return this;
    }

    /**
     * Set type of data.
     * 
     * @param pType
     */
    public AbstractBotDataWizardPage setType(final String pType) {
        bot.comboBoxWithLabel(Messages.datatypeLabel).setSelection(pType);
        return this;
    }

    public AbstractBotDataWizardPage setClassname(final String pClass) {
        SWTBotText classNameText = bot.textWithLabel(Messages.classLabel);
        Display.getDefault().syncExec(()->{
        	 Text text = classNameText.widget;
             text.setText(pClass);
        });
        return this;
    }

    public BotExpressionEditorDialog editDefaultValueExpression() {
    	SWTBotShell activeShell = bot.activeShell();
        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_EDITBUTTON, 0).click();
        return new BotExpressionEditorDialog(bot,activeShell);
    }
    
    public AbstractBotDataWizardPage setDefaultValueExpression(String value) {
        bot.textWithId(SWTBotConstants.SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 0).setText(value);
        return this;
    }
    
    
    public AbstractBotDataWizardPage setAutoGenerateForm(final boolean pAutoGenerate) {
        final SWTBotCheckBox cb = bot.checkBox("Auto-generate form");
        if (cb.isChecked() && !pAutoGenerate) {
            cb.deselect();
        } else if (!cb.isChecked() && pAutoGenerate) {
            cb.select();
        }
        return this;
    }

    public AbstractBotDataWizardPage finishAndAdd() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(org.bonitasoft.studio.common.Messages.createAndNewButton)),
                5000);
        bot.button(org.bonitasoft.studio.common.Messages.createAndNewButton).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.newVariable));
        bot.shell(Messages.newVariable).activate();
        return this;
    }

}
