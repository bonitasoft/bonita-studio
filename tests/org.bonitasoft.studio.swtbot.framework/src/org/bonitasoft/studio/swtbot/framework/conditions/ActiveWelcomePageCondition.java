package org.bonitasoft.studio.swtbot.framework.conditions;

import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

public class ActiveWelcomePageCondition extends DefaultCondition {

    private SWTGefBot swtGefBot;

    @Override
    public void init(final SWTBot swtGefBot) {
        super.init(bot);
        this.swtGefBot = (SWTGefBot) swtGefBot;
    }

    @Override
    public boolean test() throws Exception {
        return swtGefBot.viewById("org.eclipse.ui.internal.introview").isActive();
    }

    @Override
    public String getFailureMessage() {
        return "Not back to Welcome page yet";
    }
}
