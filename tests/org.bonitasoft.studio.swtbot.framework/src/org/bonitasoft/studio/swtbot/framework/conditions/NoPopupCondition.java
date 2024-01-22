package org.bonitasoft.studio.swtbot.framework.conditions;

import java.util.stream.Stream;

import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.BoolResult;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

public class NoPopupCondition extends DefaultCondition {

    @Override
    public boolean test() throws Exception {
        try {
            final SWTBotShell shell = Stream.of(bot.shells())
                    .filter(s -> s.getText() != null && s.getText().startsWith("Bonita Studio") && s.isActive())
                    .findFirst().orElse(null);
            if (shell != null) {
                return UIThreadRunnable.syncExec(new BoolResult() {

                    @Override
                    public Boolean run() {
                        return shell.widget.isVisible() || shell.widget.isFocusControl();
                    }
                });
            }
        } catch (WidgetNotFoundException e) {
        }
        return false;
    }

    @Override
    public String getFailureMessage() {
        SWTBotShell activeShell;
        try {
            activeShell = bot.activeShell();
        } catch (WidgetNotFoundException e) {
            activeShell = Stream.of(bot.shells())
                    .filter(s -> s.getText() != null && s.getText().startsWith("Bonita Studio") && s.isActive())
                    .findFirst().orElse(null);
        }
        if (activeShell == null) {
            return "Shell (now closed) still had focus. Was waiting for main shell 'Bonita Studio' to be active.";
        } else {
            return String.format(
                    "Shell with text '%s' has still focus. Was waiting for main shell 'Bonita Studio' to be active.",
                    activeShell.getText());
        }
    }
}
