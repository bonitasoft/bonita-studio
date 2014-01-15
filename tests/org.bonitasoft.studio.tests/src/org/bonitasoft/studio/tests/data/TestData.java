/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.data;

import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Aurelien Pupier
 *
 */


@RunWith(SWTBotJunit4ClassRunner.class)
public class TestData extends SWTBotGefTestCase {
	
	
	@After
	public void tearDown(){
		bot.saveAllEditors();
	}
	
	@Test
    public void testMaxLengthDescription(){
        SWTBotTestUtil.createNewDiagram(bot);
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Data");
        bot.button(Messages.addData).click();

        bot.textWithLabel(Messages.name +" *").setText("MyName");
        assertFalse("We shouldn't be able to create a data with an Uppercase as first charater", bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.textWithLabel(Messages.name +" *").setText("myName");
        String text270 = "";
        for (int i = 0; i < 270; i++) {
            text270 += "a";
        }
        bot.textWithLabel(Messages.dataDescriptionLabel).setText(text270);
        assertFalse("We shouldn't be able to put more than 255 characters",   bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        String text255 = "";
        for (int i = 0; i < 254; i++) {
            text255 += "b";
        }
        bot.textWithLabel(Messages.dataDescriptionLabel).setText(text255);
        assertTrue("We should be able to put at least 254 characters", bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }
    

}
