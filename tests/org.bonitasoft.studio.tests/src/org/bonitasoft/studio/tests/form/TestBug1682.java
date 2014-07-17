/**
 * Copyright (C) 2010-2014 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.form;

import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.ui.PlatformUI;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Mickael Istria
 *
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class TestBug1682 extends SWTBotGefTestCase {

    protected Rectangle bounds;
    private int nbParts;

    @Test
    public void testBug1682() throws Exception {
        Display.getDefault().syncExec(new Runnable() {

            public void run() {
                try {
                    final FormDiagramEditor editor = TestCommands.openFormEditorWithBaseTestForForm();
                    nbParts = editor.getDiagramEditPart().getChildren().size();
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
        });

        /*The editor for process*/
        final String title = bot.activeEditor().getTitle();
        final SWTBotGefEditor gefEditor = bot.gefEditor(title);
        /*get the form in the first process (pool)*/
        gefEditor.mainEditPart().children().get(0).select();
        gefEditor.clickContextMenu("Delete");
        bot.button(IDialogConstants.OK_LABEL).click();

        bot.waitUntil(new ICondition() {

            private int nbPartsAfterDelete;

            public boolean test() throws Exception {
                Display.getDefault().syncExec(new Runnable() {

                    public void run() {
                        try {
                            final FormDiagramEditor editor = (FormDiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                                    .getActiveEditor();
                            nbPartsAfterDelete = editor.getDiagramEditPart().getChildren().size();
                        } catch (final Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                return nbParts - 1 == nbPartsAfterDelete;
            }

            public void init(final SWTBot bot) {

            }

            public String getFailureMessage() {
                return "Part not deleted";
            }
        });

        gefEditor.save();
    }
}
