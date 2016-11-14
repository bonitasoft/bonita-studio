/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.validators.test.swtbot;

import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.diagram.application.pageflow.BotAddFormWizardDialog;
import org.bonitasoft.studio.swtbot.framework.diagram.general.form.validator.BotValidatorPropertySection;
import org.bonitasoft.studio.validators.i18n.Messages;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestFormValidatorIT  {

    private SWTGefBot bot = new SWTGefBot();
    
    @Test
    public void testParameterEnablementForGroovyValidator() {
        final BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        final BotProcessDiagramPerspective botProcessDiagramPerspective = botApplicationWorkbenchWindow.createNewDiagram();
        final BotAddFormWizardDialog botAddFormWizardDialog = botProcessDiagramPerspective.getDiagramPropertiesPart().selectApplicationTab()
                .selectPageflowTab().addForm();
        botAddFormWizardDialog.finish();

        final BotValidatorPropertySection botValidatorPropertySection = botProcessDiagramPerspective.getFormPropertiesPart().selectGeneralTab()
                .selectValidatorsTab();
        botValidatorPropertySection.add();
        Assert.assertTrue(bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_ERASEBUTTON, 0).isEnabled());
        Assert.assertFalse(bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_ERASEBUTTON, 1).isEnabled());

        botValidatorPropertySection.selectValidatorType("Groovy expression");

        bot.textWithLabel(Messages.Validator_Parameter).isEnabled();
        Assert.assertTrue(bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_ERASEBUTTON, 0).isEnabled());
        Assert.assertTrue(bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_ERASEBUTTON, 1).isEnabled());

        bot.saveAllEditors();
        bot.closeAllEditors();
    }

}
