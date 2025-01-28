/**
 * Copyright (C) 2025 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.swtbot.framework.conditions;

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withRegex;
import static org.eclipse.swtbot.swt.finder.waits.Conditions.waitForShell;

import java.util.List;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.BoolResult;
import org.eclipse.swtbot.swt.finder.waits.WaitForObjectCondition;
import org.hamcrest.Matcher;

/**
 * A condition that waits until a shell matching the specified regex is the active shell.
 */
public class ShellWithRegexIsActive extends ShellIsActiveWithThreadSTacksOnFailure {

    public ShellWithRegexIsActive(final String regex) {
        super(regex);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.swtbot.framework.conditions.ShellIsActiveWithThreadSTacksOnFailure#test()
     */
    @Override
    public boolean test() throws Exception {
        try {
            Matcher<Shell> withRegex = withRegex(text);
            WaitForObjectCondition<Shell> wait = waitForShell(withRegex);
            bot.waitUntilWidgetAppears(wait);
            List<Shell> matchingShells = wait.getAllMatches();
            BoolResult boolResult = () -> matchingShells.stream().anyMatch(w -> w.isVisible() || w.isFocusControl());
            return UIThreadRunnable.syncExec(boolResult);
        } catch (final WidgetNotFoundException e) {
            return false;
        }
    }

}
