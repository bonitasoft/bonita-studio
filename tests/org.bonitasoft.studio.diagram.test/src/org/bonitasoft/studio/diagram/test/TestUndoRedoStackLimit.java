/**
 * Copyright (C) 2010-2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.test;

import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.ui.IEditorPart;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestUndoRedoStackLimit  {

    private SWTGefBot bot = new SWTGefBot();
    
    @Test
    public void testFormsDiagramTest() throws ExecutionException, InterruptedException {
        /*Open a process diagram editor*/
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotEditor botEditor = bot.activeEditor();
        int limit = retrieveLimit(botEditor);
        Assert.assertEquals("process diagram editor: Undo/redo stack limit not good", 20, limit);

        /*Open a form diagram editor*/
        SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
        SWTBotTestUtil.createFormWhenOnAProcessWithStep(bot, gmfEditor, "Step1");

        botEditor = bot.activeEditor();
        limit = retrieveLimit(botEditor);
        Assert.assertEquals("form diagram editor: Undo/redo stack limit not good", 20, limit);

        /*Close the form editor*/
        botEditor.close();

        /*Test the stack limit on the process diagram editor*/
        botEditor = bot.activeEditor();
        limit = retrieveLimit(botEditor);
        Assert.assertEquals("Reopened process diagram editor: Undo/redo stack limit not good", 20, limit);

    }

    private int retrieveLimit(SWTBotEditor botEditor) {
        IEditorPart editor = botEditor.getReference().getEditor(false);
        IOperationHistory history = (IOperationHistory) editor.getAdapter(IOperationHistory.class);
        IUndoContext context = (IUndoContext) editor.getAdapter(IUndoContext.class);

        if(history != null && context != null){
            return history.getLimit(context);
        }
        return -1;
    }
}
