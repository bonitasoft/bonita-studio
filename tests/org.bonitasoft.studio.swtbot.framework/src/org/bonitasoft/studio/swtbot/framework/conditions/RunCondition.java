/**
 * Copyright (C) 2012 BonitaSoft S.A.
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

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

/**
 * @author Romain Bioteau
 */
public class RunCondition implements ICondition {

    private SWTBot bot;
    private IStatus status;

    /*
     * (non-Javadoc)
     * @see org.eclipse.swtbot.swt.finder.waits.ICondition#test()
     */
    public boolean test() throws Exception {
        String shellTitle = null;
        try {
            SWTBotShell shell = bot.activeShell();
            if (shell == null) {
                status = new Status(IStatus.ERROR, "", "No active shell found");
                return true;
            }
            shellTitle = shell.getText();
        } catch (Exception e) {
            status = new Status(IStatus.ERROR, "", e.getMessage());
            BonitaStudioLog.error(e);
        }
        final boolean finished = shellTitle != null && !shellTitle.equals("Progress Information");
        if (finished) {
            if (shellTitle.startsWith("Bonita Studio")) {
                status = Status.OK_STATUS;
            } else {
                status = new Status(IStatus.ERROR, "", "Shell displayed after run : " + shellTitle);
            }

        }
        return finished;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.swtbot.swt.finder.waits.ICondition#init(org.eclipse.swtbot.swt.finder.SWTBot)
     */
    public void init(SWTBot bot) {
        this.bot = bot;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.swtbot.swt.finder.waits.ICondition#getFailureMessage()
     */
    public String getFailureMessage() {
        return status != null ? status.getMessage() : "Run has failed";
    }

    public IStatus getStatus() {
        return status;
    }

}
