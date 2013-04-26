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

package org.bonitasoft.studio.diagram.form.custom.tests;

import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *@Author Aurélie Zara
 *
 *
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class TestBug1888 extends SWTBotGefTestCase {

	@Before
	public void before(){
		
	}
	
	@After
	public void tearDown(){
		bot.closeAllEditors();
	}
	
	/**
	 * STUDIO-1888
	 *on submit buttons, 'insert widget if' edit expression window does not save any values
	 */
	@Test
	public void testBug1888(){
		final String widgetName = "Submit1";
		final String tabName ="Options";
		final String checkBoxLabel = "Insert widget if";
		SWTBotTestUtil.createNewDiagram(bot);
		SWTBotEditor editor = bot.activeEditor();
		SWTBotGefEditor gefEditor = bot.gefEditor(editor.getTitle());
		SWTBotTestUtil.createFormWhenOnAProcessWithStep(bot, gefEditor, "Step1");
		SWTBotTestUtil.selectElementFromFormOverview(bot,widgetName);
		bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_FORM_GENERAL).show();
		SWTBotTestUtil.selectTabbedPropertyView(bot, tabName);
		bot.checkBox().select();
		assertTrue("expression viewer should be enabled",bot.textWithId(ExpressionViewer.SWTBOT_ID_EXPRESSIONVIEWER_TEXT).isEnabled());
		bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON).click();
		SWTBotTestUtil.setScriptExpression(bot, "test","\"test\"", null);
		assertFalse(bot.textWithId(ExpressionViewer.SWTBOT_ID_EXPRESSIONVIEWER_TEXT).getText().isEmpty());
	}
}
