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

package org.bonitasoft.studio.diagram.test;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.junit.Test;

public class TestDeleteTaskWithForm {
    
    private SWTGefBot bot = new SWTGefBot();
	private String diagramTitle=null;

	@Test 
	public void testDeleteTaskWithForm(){
		SWTBotTestUtil.createNewDiagram(bot);
		SWTBotEditor botEditor = bot.activeEditor();
		diagramTitle=botEditor.getTitle();
		SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
		
		SWTBotTestUtil.createFormWhenOnAProcessWithStep(bot, gmfEditor, "Step1");

		bot.editorByTitle(diagramTitle).show();
		bot.editorByTitle(diagramTitle).setFocus();
		
		SWTBotGefEditPart element =gmfEditor.getEditPart("Step1");
        element.click();
        gmfEditor.clickContextMenu("Delete");
        
        List<? extends SWTBotEditor> editors = bot.editors();
        for (SWTBotEditor editor:editors){
        	assertFalse("Step1 form should be closed",editor.getTitle().equals("Step1"));
        }	
	}


}
