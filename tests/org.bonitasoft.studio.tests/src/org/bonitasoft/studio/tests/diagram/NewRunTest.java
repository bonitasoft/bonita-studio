/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.diagram;

import org.bonitasoft.engine.api.ProcessManagementAPI;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class NewRunTest {

    private final SWTGefBot bot = new SWTGefBot();

    @Test
    public void testNewRun() throws Exception {
        final long nbProcBeforeRun = getNBProcessDefinitions();

        for (int i = 0; i < 5; i++) {
            SWTBotTestUtil.createNewDiagram(bot);
        }
        SWTBotTestUtil.selectAndRunFirstPoolFound(bot);

        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return getNBProcessDefinitions() == nbProcBeforeRun + 1;
            }

            @Override
            public void init(final SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "the new+run swtbot test should be started and ready";
            }
        }, 30000, 2000);
    }

    @After
    public void closeEditors() {
        bot.closeAllEditors();
    }

    private long getNBProcessDefinitions() throws Exception {
        APISession session = null;
        long nbProc = 0;
        try {
            session = BOSEngineManager.getInstance().loginDefaultTenant(new NullProgressMonitor());
            final ProcessManagementAPI processAPI = BOSEngineManager.getInstance().getProcessAPI(session);
            nbProc = processAPI.getNumberOfProcessDeploymentInfos();
        } finally {
            if (session != null) {
                BOSEngineManager.getInstance().logoutDefaultTenant(session);
            }
        }

        return nbProc;
    }

}
