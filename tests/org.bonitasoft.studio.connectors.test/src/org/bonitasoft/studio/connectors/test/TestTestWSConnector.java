/**
 * Copyright (C) 2011-2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.connectors.test;

import java.io.IOException;

import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;

/**
 * @author Aurelien Pupier
 *
 */
public class TestTestWSConnector extends SWTBotGefTestCase {

	
	public void testTestWSConnector() throws IOException{
		/*Import process*/
		bot.waitUntil(Conditions.shellIsActive("Bonita BPM"));
		SWTBotTestUtil.importProcessWIthPathFromClass(bot, "WS_Without_Introspect--1.0.bar", SWTBotTestUtil.IMPORTER_TITLE_BONITA, "WS_Without_Introspect (1.0)", this.getClass(), false);
		SWTBotGefEditor gefEditor = bot.gefEditor("WS_Without_Introspect (1.0)");
		/*Select step on which there is the connector to test*/
		gefEditor.getEditPart("Step1").parent().select();
		
		/*Open connector configuration wizard to test the connector*/
		bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).setFocus();
		SWTBotTestUtil.selectTabbedPropertyView(bot, "Connectors");
		bot.tree().select(0);
		bot.button("Edit...").click();
		bot.waitUntil(Conditions.shellIsActive("Web Service Client"));
		
		/*In the wizard go to the right page to test it*/
		bot.button(IDialogConstants.NEXT_LABEL).click();
		bot.button("Test Configuration").click();
		
		/*Wait that the correct shell opened*/
		bot.waitUntil(Conditions.shellIsActive("Results"));
		
		assertContains("DOMSource",bot.tree().getAllItems()[0].getText());
		
		bot.button("Back").click();
		
		bot.button(IDialogConstants.CANCEL_LABEL).click();
			
	}
	
}
