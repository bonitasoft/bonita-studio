/**
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.projectExplorer;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.businessobject.core.repository.AbstractBDMFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.swtbot.framework.ConditionBuilder;
import org.bonitasoft.studio.swtbot.framework.bdm.BotBdmEditor;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.BDMProjectExplorerBot;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.ProjectExplorerBot;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ProjectExplorerBdmIT {

    private SWTGefBot bot = new SWTGefBot();
    private RepositoryAccessor repositoryAccessor;

    @Rule
    public SWTGefBotRule botRule = new SWTGefBotRule(bot);

    @Before
    public void init() throws Exception {
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        BOSEngineManager.getInstance().start();
    }

    @Test
    public void should_manage_bdm_from_explorer() {
        createNewBdm();
        BDMProjectExplorerBot projectExplorerBot = new ProjectExplorerBot(bot).bdm();
        projectExplorerBot.setDeployRequired(true);
        projectExplorerBot.openBdm().close();
        projectExplorerBot.getBdmTreeItem().doubleClick();
        new BotBdmEditor(bot).close();
        projectExplorerBot.deployBdm();
        projectExplorerBot.setDeployRequired(false);
        projectExplorerBot.deleteBdm();
        validateBdmIsDeleted();
    }

    private void validateBdmIsDeleted() {
        ICondition bdmDeletedCondition = new ConditionBuilder()
                .withTest(() -> repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)
                        .getChild("bom.xml", true) == null)
                .withFailureMessage(() -> "Business data model has not been deleted.")
                .create();
        bot.waitUntil(bdmDeletedCondition);
    }

    private void createNewBdm() {
        AbstractBDMFileStore fileStore = repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)
                .getChild("bom.xml", false);
        if (fileStore != null) {
            fileStore.delete();
            validateBdmIsDeleted();
        }
        new ProjectExplorerBot(bot).newBdm().close();
        assertThat(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)
                .getBusinessObjectByQualifiedName("com.company.model.BusinessObject")).isPresent();
    }

}
