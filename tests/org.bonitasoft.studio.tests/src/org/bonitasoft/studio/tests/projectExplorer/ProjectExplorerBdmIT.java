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

import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.swtbot.framework.bdm.DefineBdmWizardBot;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.ProjectExplorerBot;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings("unchecked")
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
        createBdmIfRequired();
        ProjectExplorerBot projectExplorerBot = new ProjectExplorerBot(bot);
        projectExplorerBot.bdm().openBdm().cancel();
        projectExplorerBot.bdm().getBdmTreeItem().doubleClick();
        new DefineBdmWizardBot(bot, org.bonitasoft.studio.businessobject.i18n.Messages.manageBusinessDataModelTitle)
                .cancel();
        projectExplorerBot.bdm().deployBdm();
        projectExplorerBot.bdm().deleteBdm();
        assertThat(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class).getChild("bom.xml"))
                .isNull();
    }

    private void createBdmIfRequired() {
        if (repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class).getChild("bom.xml") == null) {
            new ProjectExplorerBot(bot).newBdm().addBusinessObject("ProjectExplorerBo1")
                    .addAttribute("ProjectExplorerBo1", "name", FieldType.STRING.toString()).deploy();
            assertThat(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)
                    .getBusinessObjectByQualifiedName("com.company.model.ProjectExplorerBo1")).isPresent();
        }
    }

}
