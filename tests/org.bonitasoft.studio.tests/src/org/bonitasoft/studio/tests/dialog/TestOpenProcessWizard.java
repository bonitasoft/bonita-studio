/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.dialog;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.diagram.custom.wizard.OpenDiagramWizard;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.eclipse.finder.SWTBotEclipseTestCase;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.ui.PlatformUI;

/**
 * @author Aurelien Pupier
 */

public class TestOpenProcessWizard extends SWTBotEclipseTestCase {

    private SWTBot bot;
    private SWTBotShell target;
    public OpenDiagramWizard wizard;

    public void testShow() throws Throwable {
        assertThat(wizard, is(notNullValue()));
        bot.button(IDialogConstants.CANCEL_LABEL).click();
        assertThat(Conditions.shellCloses(target).test(), is(true));
    }

    public void not_close_wizard_when_click_finish_and_no_process_is_selected() throws Throwable {
        assertThat(bot.button(/* Messages.OpenProcessButtonLabel */IDialogConstants.FINISH_LABEL).isEnabled(), is(false));
    }

    protected void openWizard() {
        Display.getDefault().asyncExec(new Runnable() {

            public void run() {
                IWizard newWizard = new OpenDiagramWizard();
                Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
                WizardDialog dialog = new WizardDialog(shell, newWizard);
                dialog.open();

            }
        });
        bot.waitUntil(
                org.eclipse.swtbot.eclipse.finder.waits.Conditions.shellIsActive(Messages.openProcessWizardPage_title));
    }

}
