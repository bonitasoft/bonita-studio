/**
 * Copyright (C) 2011-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.tests.dialog;

import java.io.IOException;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.Test;

/**
 * @author Aurelien Pupier
 *
 */
public class TestDuplicate extends SWTBotGefTestCase {

    @Test
    public void testDuplicateWithSeveralPool() throws IOException {
        SWTBotTestUtil.createNewDiagram(bot);
        final int nbEditorBefore = bot.editors().size();
        bot.menu("Diagram").menu("Duplicate...").click();
        bot.waitUntil(Conditions.shellIsActive(Messages.openNameAndVersionDialogTitle));
        bot.textWithLabel("Name").setText("DuplicatedTestDuplicateWithSeveralPool");
        bot.textWithLabel("Version").setText("2.0");
        bot.textWithLabel("Name",2).setText("Pool_updated");
        bot.text("1.0").setText("2.0");

        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(new ICondition() {

            public boolean test() throws Exception {
                return nbEditorBefore +1 == bot.editors().size();
            }

            public void init(SWTBot bot) {}

            public String getFailureMessage() {
                return "Deuplicate has failed !";
            }
        });

        DiagramRepositoryStore drs = (DiagramRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        MainProcess mainProcess  = drs.getDiagram("DuplicatedTestDuplicateWithSeveralPool","2.0").getContent();


        boolean pool1ok = false;
        boolean pool1Versionok = false;


        for(AbstractProcess pool : ModelHelper.getAllProcesses(mainProcess)){
            final String poolName = pool.getName();
            if(poolName.equals("Pool_updated")){
                pool1ok = true;
                if(pool.getVersion().equals("2.0")){
                    pool1Versionok = true;
                }
            }
        }
        assertTrue("Pool_updated not found in the repository",pool1ok);
        assertTrue("Pool_updated has an invalid version not found in the repository",pool1Versionok);
    }




}
