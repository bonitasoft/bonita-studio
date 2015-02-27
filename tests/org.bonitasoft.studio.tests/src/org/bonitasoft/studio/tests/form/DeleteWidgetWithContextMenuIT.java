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

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.draw.BotGefFormDiagramEditor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Mickael Istria
 *
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class DeleteWidgetWithContextMenuIT extends SWTBotGefTestCase {


    @Test
    public void testBug1682() throws Exception {
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                try {
                    TestCommands.openFormEditorWithBaseTestForForm();
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
        });
        final BotGefFormDiagramEditor activeFormDiagramEditor = new BotProcessDiagramPerspective(bot).activeFormDiagramEditor();
        EObject form = activeFormDiagramEditor.selectForm().getSelectedSemanticElement();
        assertThat(form).isInstanceOf(Form.class);
        assertThat(((Form) form).getWidgets()).hasSize(9);
        activeFormDiagramEditor.selectWidget(1, 1).clickContextMenu("Delete");
        bot.button(IDialogConstants.OK_LABEL).click();
        form = activeFormDiagramEditor.selectForm().getSelectedSemanticElement();
        assertThat(((Form) form).getWidgets()).hasSize(8);
    }

}
