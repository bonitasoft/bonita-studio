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
package org.bonitasoft.studio.groovy.tests;

import java.io.IOException;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Aurelien Pupier
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class TestBonitaGroovyEditorDialog extends SWTBotGefTestCase {

    @Override
    @Before
    public void setUp() throws Exception {

        super.setUp();
        //WORKAROUND : we need to load bonitasoft groovy ui plugin before start of the test.
        // There are issues with access to PreferenceStore of Groovy plugin in Activator of BonitaSoft Grooy UI plugin.
        System.out.println(Messages.add);
    }

    @Test
    public void testOpenBonitaGroovyEditorDialog() throws ExecutionException, CoreException, IOException{
        bot.menu("Development").menu("Manage Groovy scripts...").click();
        bot.waitUntil(Conditions.shellIsActive("Manage Groovy scripts"));
        bot.tree().setFocus();
        bot.button("Create...").click();
        bot.waitUntil(Conditions.shellIsActive("Create new Groovy script"));
        bot.text().setText("MyTestScript");
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.tree().getTreeItem("MyTestScript").doubleClick();
        bot.waitUntil(Conditions.shellIsActive("Edit expression"));
        bot.styledText().setText("\"test me\"");
        bot.button("Evaluate").click();
        bot.waitUntil(Conditions.shellIsActive("Evaluation results"));
        assertEquals("test me", bot.text().getText()) ;
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.button(IDialogConstants.OK_LABEL).click();
        if(!FileActionDialog.getDisablePopup()){
            bot.button(IDialogConstants.OK_LABEL).click();
        }
    }

}
