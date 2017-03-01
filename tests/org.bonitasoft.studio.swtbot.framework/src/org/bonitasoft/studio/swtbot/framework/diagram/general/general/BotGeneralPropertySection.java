/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.general;

import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;

/**
 * General property section.
 *
 * @author Joachim Segala
 */
public class BotGeneralPropertySection extends BotBase {

    public BotGeneralPropertySection(final SWTGefBot bot) {
        super(bot);
    }

    public void setName(final String pName) {
        final SWTBotText textWithLabel = bot.textWithLabel(Messages.name);
        if (textWithLabel.isEnabled()) {
            textWithLabel.setText(pName);
        } else {
            //FIXME: what happend when text is not enabled?
        }
        bot.sleep(500);
    }

    /**
     * TASK.
     */

    /**
     * Set the type of the task.
     *
     * @param pType
     */
    public void setTaskType(final String pType) {
        bot.comboBoxWithLabel(Messages.activityType).setSelection(pType);
    }

    /**
     * GATEWAY.
     */

    /**
     * Set the type of the gateway.
     *
     * @param pType
     */
    public void setGatewayType(final String pType) {
        bot.comboBoxWithLabel(Messages.gatewayType).setSelection(pType);
    }

    /**
     * SEQUENCE FLOW.
     */

    /**
     * Check/Uncheck "Default flow".
     *
     * @param pIsDefaultFlow
     */
    public void setIsDefaultFlow(final boolean pIsDefaultFlow) {
        final SWTBotCheckBox cb = bot.checkBox(Messages.defaultFlowLabel);
        if (cb.isChecked() && !pIsDefaultFlow) {
            cb.deselect();
        } else if (!cb.isChecked() && pIsDefaultFlow) {
            cb.select();
        }
    }

    public BotGeneralPropertySection setConditionExpression(final String condition) {
        bot.textWithId(ExpressionViewer.SWTBOT_ID_EXPRESSIONVIEWER_TEXT).setText(condition);
        return this;
    }
}
