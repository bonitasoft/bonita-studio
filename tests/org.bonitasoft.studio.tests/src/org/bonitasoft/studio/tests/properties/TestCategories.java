/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.properties;

import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;

/**
 * @author Aurelien Pupier
 *         /!\ we suppose that the first element selected on teh new is the pool.
 */
public class TestCategories extends SWTBotGefTestCase {

    public void testAddCategory() {
        createProcessAndGotoUserXPTab();
        addCategory("categoryAdded");

        /* Check that it was really created */
        bot.label("categoryAdded");
    }

    public void testDeleteCategory() {
        createProcessAndGotoUserXPTab();
        addCategory("categoryToDelete");
        /* Delete the category */
        bot.label("categoryToDelete").image();
        //TODO: I don't know how to do it with swtbot... there is a mouseup on a composite
        /* Check that it was deleted */
    }

    public void testCantCreateDuplicateCategory() {
        createProcessAndGotoUserXPTab();
        final String categoryName = "categoryCantDuplicated";
        addCategory(categoryName);

        bot.button("+").click();
        bot.waitUntil(Conditions.shellIsActive(Messages.newCategory));
        bot.text().setText(categoryName);
        final boolean okEnabled = bot.button(IDialogConstants.OK_LABEL).isEnabled();
        bot.button(IDialogConstants.CANCEL_LABEL).click();
        assertFalse("We can create duplicate categories", okEnabled);
    }

    private void createProcessAndGotoUserXPTab() {
        SWTBotTestUtil.createNewDiagram(bot);
        /* Go to the right properties sheet/tab */
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).setFocus();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "UserXP");
    }

    private void addCategory(final String categoryName) {
        String mainShellName = bot.activeShell().getText();
        bot.button("+").click();
        bot.waitUntil(Conditions.shellIsActive(Messages.newCategory));
        bot.text().setText(categoryName);
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellIsActive(mainShellName));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        bot.saveAllEditors();
    }
}
