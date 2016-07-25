/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.form;

import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.BotQAUtil;
import org.bonitasoft.studio.swtbot.framework.diagram.general.form.data.BotDataFormPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.form.general.BotGeneralPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.form.validator.BotValidatorPropertySection;
import org.bonitasoft.studio.swtbot.framework.expression.BotExpressionEditorDialog;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * Form properties view.
 *
 * @author Joachim Segala
 */
public class BotGeneralFormPropertiesView extends BotBase {

    public BotGeneralFormPropertiesView(final SWTGefBot bot) {
        super(bot);
    }

    public BotGeneralPropertySection selectGeneralTab() {
        BotQAUtil.selectTabbedPropertyView(bot, "General");
        return new BotGeneralPropertySection(bot);
    }

    public void selectUserAidsTab() {
        BotQAUtil.selectTabbedPropertyView(bot, "User aids");
        //TODO:
    }

    public void selectOptionsTab() {
        BotQAUtil.selectTabbedPropertyView(bot, "Options");
        //TODO:
    }

    public BotDataFormPropertySection selectDataTab() {
        BotQAUtil.selectTabbedPropertyView(bot, "Data");
        return new BotDataFormPropertySection(bot);
    }

    public void selectActionsTab() {
        BotQAUtil.selectTabbedPropertyView(bot, "Action");
        //TODO:
    }

    public BotValidatorPropertySection selectValidatorsTab() {
        BotQAUtil.selectTabbedPropertyView(bot, "Validators");
        return new BotValidatorPropertySection(bot);
    }

    /**
     * Switch editor for the data
     */
    public void switchTableEditor() {
        bot.link("<A>" + org.bonitasoft.studio.expression.editor.i18n.Messages.editAsExpression + "</A>").click(
                org.bonitasoft.studio.expression.editor.i18n.Messages.editAsExpression); //the link 'switch editor' of index 0
        bot.sleep(1000);
    }

    /**
     * Click on the pen in order to edit a field.
     *
     * @param pIndex
     * @return the expression editor popup
     */
    public BotExpressionEditorDialog edit(final int pIndex) {
        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, pIndex).click();
        return new BotExpressionEditorDialog(bot);
    }
}
