package org.bonitasoft.studio.swtbot.framework.conditions;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.BoolResult;
import org.eclipse.swtbot.swt.finder.utils.StringUtils;
import org.eclipse.swtbot.swt.finder.utils.internal.Assert;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

/**
 * A condition that waits until the specified shell is the active shell.
 * Duplicated from ShellIsActive of SWTBot code in order to dump thread stack t identify why the test is failing.
 *
 * @see Conditions
 * @since 1.3
 * @author Vincent MAHE &lt;vmahe [at] free[dot]fr&gt;
 * @version $Id$
 */
public class ShellIsActiveWithThreadSTacksOnFailure extends DefaultCondition {

    private final String text;

    public ShellIsActiveWithThreadSTacksOnFailure(final String text) {
        Assert.isNotNull(text, "The shell text was null"); //$NON-NLS-1$
        Assert.isLegal(!StringUtils.isEmpty(text), "The shell text was empty"); //$NON-NLS-1$
        this.text = text;
    }

    public String getFailureMessage() {
        final StringBuilder dump = new StringBuilder();
        final ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();
        final ThreadInfo[] threadInfos = threadMxBean.getThreadInfo(threadMxBean.getAllThreadIds(), 100);
        for (final ThreadInfo threadInfo : threadInfos) {
            dump.append('"');
            dump.append(threadInfo.getThreadName());
            dump.append('"');
            dump.append("\n  java.lang.Thread.State:");
            dump.append(threadInfo.getThreadState());
            for (final StackTraceElement stactraceElement : threadInfo.getStackTrace()) {
                dump.append("\n       at ");
                dump.append(stactraceElement);
            }
            dump.append("\n\n");
        }

        dump.append("\nAvailablable shells:\n");
        for (final SWTBotShell shell : bot.shells()) {
            dump.append(shell.getText()).append("\n");
        }

        return "The shell '" + text + "' did not activate.\n" //$NON-NLS-1$ //$NON-NLS-2$
                + dump.toString();
    }

    public boolean test() throws Exception {
        try {
            final SWTBotShell shell = bot.shell(text);
            return UIThreadRunnable.syncExec(new BoolResult() {

                public Boolean run() {
                    return shell.widget.isVisible() || shell.widget.isFocusControl();
                }
            });
        } catch (final WidgetNotFoundException e) {
        }
        return false;
    }

}
