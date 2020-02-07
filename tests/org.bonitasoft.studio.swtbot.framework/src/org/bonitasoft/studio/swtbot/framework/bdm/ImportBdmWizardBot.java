/**
 * Copyright (C) 2019 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.swtbot.framework.bdm;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.stream.Stream;

import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.wizard.SmartImportBdmPage;
import org.bonitasoft.studio.swtbot.framework.BotWizardDialog;
import org.bonitasoft.studio.swtbot.framework.ConditionBuilder;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

public class ImportBdmWizardBot extends BotWizardDialog {

    public ImportBdmWizardBot(SWTGefBot bot) {
        super(bot, Messages.importBdm);
    }

    public ImportBdmWizardBot setArchive(URL url) {
        SWTBotText pathText = bot.textWithLabel(org.bonitasoft.studio.ui.i18n.Messages.importLabel);
        Display.getDefault().syncExec(() -> {
            try {
                pathText.widget.setText(toAbsoluteFilePath(url));
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
        });
        return this;
    }

    public ImportBdmWizardBot setImportAction(String packageName, String action) {
        SWTBotTree tree = bot.tree();
        SWTBotShell activeShell = bot.activeShell();
        bot.waitUntil(treeItemAvailable(tree, packageName));
        SWTBotTreeItem treeItem = tree.getTreeItem(packageName);
        treeItem.select();
        treeItem.click(1);

        SWTBot activeBot = activeShell.bot();
        SWTBotCCombo ccomboBoxInGroup = activeBot.ccomboBoxWithId(SmartImportBdmPage.IMPORT_ACTION_COMBO_EDITOR_ID);
        activeBot.waitUntil(new ConditionBuilder()
                .withTest(() -> Stream.of(ccomboBoxInGroup.items()).anyMatch(action::equals))
                .withFailureMessage(() -> String.format("Action '%s' not found in combo", action))
                .create());
        ccomboBoxInGroup.setSelection(action);
        return this;
    }

    public void doImport() {
        bot.button(org.bonitasoft.studio.ui.i18n.Messages.importLabel).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.bdmImportedTitle));
        bot.button(IDialogConstants.OK_LABEL).click();
    }

    public ICondition treeItemAvailable(SWTBotTree tree, String itemName) {
        return new ConditionBuilder()
                .withTest(() -> {
                    try {
                        tree.getTreeItem(itemName);
                        return true;
                    } catch (WidgetNotFoundException e) {
                        return false;
                    }
                })
                .withFailureMessage(() -> String.format("Tree item `%s` not found.", itemName))
                .create();
    }

    public ICondition treeNodeAvailable(SWTBotTreeItem treeItem, String nodeName) {
        return new ConditionBuilder()
                .withTest(() -> {
                    try {
                        treeItem.getNode(nodeName);
                        return true;
                    } catch (WidgetNotFoundException e) {
                        return false;
                    }
                })
                .withFailureMessage(() -> String.format("Tree node `%s` not found.", nodeName))
                .create();
    }

    public ICondition treeNodeNotAvailable(SWTBotTreeItem treeItem, String nodeName) {
        return new ConditionBuilder()
                .withTest(() -> {
                    try {
                        treeItem.getNode(nodeName);
                        return false;
                    } catch (WidgetNotFoundException e) {
                        return true;
                    }
                })
                .withFailureMessage(() -> String.format("Tree node `%s` should not be present.", nodeName))
                .create();
    }

    private String toAbsoluteFilePath(URL url) throws IOException {
        return new File(FileLocator.toFileURL(url).getFile()).getAbsolutePath();
    }

}
