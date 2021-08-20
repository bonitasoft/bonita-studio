package org.bonitasoft.studio.swtbot.framework.expression;

import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

public class BotConditionExpressionEditor extends AbstractBotExpressionEditor {

    public BotConditionExpressionEditor(SWTGefBot bot,
            BotExpressionEditorDialog botExpressionEditorDialog) {
        super(bot, botExpressionEditorDialog);

    }

    public BotConditionExpressionEditor setValue(final String value) {
        bot.styledText().setText(value);
        return this;
    }
}
