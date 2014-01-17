/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.repository.test.swtbot;

import java.io.File;

import org.bonitasoft.studio.exporter.Messages;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCombo;
import org.junit.Test;


/**
 * @author Romain Bioteau
 *
 */
public class TestExportBosArchive extends SWTBotGefTestCase {

    @Test
    public void testExportDiagramBOSArchive() throws Exception {
        SWTBotTestUtil.createNewDiagram(bot);
        bot.saveAllEditors();

        bot.toolbarButton("Export").click();
        bot.waitUntil(Conditions.shellIsActive(Messages.ExportButtonLabel));

        final SWTBotCombo destComboBot = bot.comboBoxWithLabel(org.bonitasoft.studio.common.repository.Messages.destinationPath +" *");
        final String defaultPath = destComboBot.getText();
        assertTrue("Invalid default file name", defaultPath.endsWith(".bos"));
        final File destFile = new File(defaultPath);
        if(destFile.exists()){
            destFile.delete();
        }
        try{
            SWTBotButton finishButton = bot.button(IDialogConstants.FINISH_LABEL);
            bot.waitUntil(Conditions.widgetIsEnabled(finishButton));
            finishButton.click();
            bot.waitUntil(Conditions.shellIsActive("Export result"));
            bot.button(IDialogConstants.OK_LABEL).click();
            assertTrue("Destination file doesn't exists",destFile.exists());
            assertTrue("Destination file is empty",destFile.length() > 0);
        }finally{
            destFile.delete();
        }
    }

}
