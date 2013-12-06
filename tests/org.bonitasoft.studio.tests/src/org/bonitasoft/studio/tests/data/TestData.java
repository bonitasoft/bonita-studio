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

import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Ignore;
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
    
    
    /** Test related to BUG STUDIO-340
     * @throws ExecutionException 
     * 
     */
    @Ignore
    @Test
    public void changeDataTypeInPool() throws ExecutionException{
    	
    	boolean isOK;
    	
    	SWTBotTestUtil.createNewDiagram(bot);
    	SWTBotEditor botEditor = bot.activeEditor();
        SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
        
		AbstractProcess proc = ModelHelper.getParentProcess(((IGraphicalEditPart)gmfEditor.getEditPart("Step1").part()).resolveSemanticElement());
		gmfEditor.getEditPart(proc.getName()).click();

		bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
		SWTBotTestUtil.selectTabbedPropertyView(bot, "Data");
		bot.button("Add...").click();
		
        bot.waitUntil(Conditions.shellIsActive("New variable"));
        bot.textWithLabel(org.bonitasoft.studio.properties.i18n.Messages.name +" *").setText("my_collec");
        bot.comboBoxWithLabel(org.bonitasoft.studio.properties.i18n.Messages.datatypeLabel).setSelection("Java Object");

        bot.waitUntil(Conditions.widgetIsEnabled(bot.textWithLabel("Class")));
        bot.textWithLabel("Class").setText("java.util.List");

		bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
		String valueOf = "[\'myvar1\',\'myvar2\']";
		SWTBotTestUtil.setScriptExpression(bot, "theList", valueOf, List.class.getName());
		bot.waitUntil(new ICondition() {
			
			public boolean test() throws Exception {
				return bot.textWithId(ExpressionViewer.SWTBOT_ID_EXPRESSIONVIEWER_TEXT,0).getText().equals("theList");
			}
			
			public void init(SWTBot bot) {
	
			}
			
			public String getFailureMessage() {
				return "Expression not set properly";
			}
		});
		
		
		
        isOK = bot.button(IDialogConstants.FINISH_LABEL).isEnabled();
        Assert.assertTrue("Button "+IDialogConstants.FINISH_LABEL+" should be enable", isOK);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
		SWTBotTestUtil.selectTabbedPropertyView(bot, "Data");
		bot.table().select(0);
		bot.button("Edit...").click();
        
		bot.waitUntil(Conditions.shellIsActive("Edit variable"));
        bot.comboBoxWithLabel(org.bonitasoft.studio.properties.i18n.Messages.datatypeLabel).setSelection("Text");
        
        isOK = bot.button(IDialogConstants.OK_LABEL).isEnabled();
        Assert.assertTrue("Button "+IDialogConstants.OK_LABEL+" should be enable", isOK);
        bot.button(IDialogConstants.OK_LABEL).click();

//        isOK = bot.button(IDialogConstants.FINISH_LABEL).isEnabled();
//        Assert.assertFalse("Button "+IDialogConstants.FINISH_LABEL+" should not exist", isOK);
        isOK =false;
    	try{
    		bot.waitUntil(Conditions.shellIsActive("Edit variable"), 1000);
    		isOK = bot.button(IDialogConstants.OK_LABEL).isEnabled();
    	}catch(TimeoutException e){
    		System.out.println("End of changeDataTypeInPool test OK.");
    		bot.menu("Diagram").menu("Save").click();
    		SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
       	}
    	if(isOK){
    		Assert.assertFalse("Error: The shell must be closed.", isOK);
    	}
    	
    }

}
