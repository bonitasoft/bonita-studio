package org.bonitasoft.studio.swtbot.framework.conditions;

import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

public class NoPopupCondition extends DefaultCondition {

    private SWTGefBot swtGefBot;
    private String shellText;

    @Override
    public void init(final SWTBot swtGefBot) {
        super.init(bot);
        this.swtGefBot = (SWTGefBot) swtGefBot;
    }

    @Override
    public boolean test() throws Exception {
        shellText = swtGefBot.activeShell().getText();
        return shellText != null && shellText.startsWith("Bonita Studio");
    }

    @Override
    public String getFailureMessage() {
        return String.format("Shell with text '%s' has still focus. Close all dialogs and wizard inside your test.",
                shellText);
    }
}
