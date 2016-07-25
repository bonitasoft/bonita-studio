/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.tests.conditions;

import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.diagram.general.data.BotAddDataWizardPage;
import org.bonitasoft.studio.swtbot.framework.diagram.general.general.BotGeneralPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.validation.BotValidationPropertiesView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestConditionWithUTF8 extends SWTBotGefTestCase {

    @Test
    @Ignore("refresh issue of the validation AND can't make failing the test with the known bug... sometimes it seems to work")
    public void testConditionWithUTF8() {
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, false);

        final BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        final BotProcessDiagramPerspective botProcessDiagramPerspective = botApplicationWorkbenchWindow.createNewDiagram();
        final BotAddDataWizardPage botAddDataWizardPage = botProcessDiagramPerspective.getDiagramPropertiesPart().selectDataTab().selectLocalDataTab().addData();
        botAddDataWizardPage.setName("ç®¡ç�†è€…");
        botAddDataWizardPage.finish();
        botProcessDiagramPerspective.activeProcessDiagramEditor().selectFlowBetween("Start1", "Step1");
        botProcessDiagramPerspective.getDiagramPropertiesPart().selectApplicationTab();
        final BotGeneralPropertySection botGeneralPropertySection = botProcessDiagramPerspective.getDiagramPropertiesPart().selectGeneralTab()
                .selectGeneralTab();
        botGeneralPropertySection.setConditionExpression("ç®¡ç�†è€… == \"test\"");
        final BotValidationPropertiesView validationPropertiesTab = botProcessDiagramPerspective.getDiagramPropertiesPart().selectValidationStatusTab();
        botApplicationWorkbenchWindow.save();
        bot.sleep(1000);
        validationPropertiesTab.refresh();
        bot.sleep(100000);
        Assert.assertEquals(2, validationPropertiesTab.getRowNumber());

    }


}
