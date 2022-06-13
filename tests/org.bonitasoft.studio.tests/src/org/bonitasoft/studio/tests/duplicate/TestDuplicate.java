/**
 * Copyright (C) 2011-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.duplicate;

import static org.junit.Assert.assertTrue;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestDuplicate {

    private final SWTGefBot bot = new SWTGefBot();

    @Test
    public void testDuplicateWithSeveralPool() throws Exception {
        SWTBotTestUtil.createNewDiagram(bot);
        final int nbEditorBefore = bot.editors().size();
        bot.menu("File").menu("Duplicate diagram...").click();
        bot.waitUntil(Conditions.shellIsActive(Messages.openNameAndVersionDialogTitle));
        bot.textWithLabel("Name").setText("DuplicatedTestDuplicateWithSeveralPool");
        bot.textWithLabel("Version").setText("2.0");
        bot.textWithLabel("Name", 2).setText("Pool_updated");
        bot.text("1.0").setText("2.0");

        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return nbEditorBefore + 1 == bot.editors().size();
            }

            @Override
            public void init(final SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "Duplicate has failed !";
            }
        }, 10000);

        final DiagramRepositoryStore drs = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        final MainProcess mainProcess = drs.getDiagram("DuplicatedTestDuplicateWithSeveralPool", "2.0").getContent();

        boolean pool1ok = false;
        boolean pool1Versionok = false;

        for (final AbstractProcess pool : ModelHelper.getAllProcesses(mainProcess)) {
            final String poolName = pool.getName();
            if (poolName.equals("Pool_updated")) {
                pool1ok = true;
                if (pool.getVersion().equals("2.0")) {
                    pool1Versionok = true;
                }
            }
        }
        assertTrue("Pool_updated not found in the repository", pool1ok);
        assertTrue("Pool_updated has an invalid version not found in the repository", pool1Versionok);
    }

    @Test
    public void should_duplicate_all_confirmation_templates() throws Exception {
        new BotApplicationWorkbenchWindow(bot).importBOSArchive()
                .setArchive(
                        TestDuplicate.class.getResource("Test confirmation template-1.0.bos"))
                .currentRepository()
                .next()
                .next()
                .finish();

        final int nbEditorBefore = bot.editors().size();
        bot.menu("File").menu("Duplicate diagram...").click();
        bot.waitUntil(Conditions.shellIsActive(Messages.openNameAndVersionDialogTitle));
        bot.textWithLabel("Version", 0).setText("2.0");
        bot.textWithLabel("Version", 1).setText("2.0");

        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return nbEditorBefore + 1 == bot.editors().size();
            }

            @Override
            public void init(final SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "Duplicate has failed !";
            }
        }, 10000);
    }

}
