/**
 * Copyright (C) 2017 Bonitasoft S.A.
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

import org.bonitasoft.studio.businessobject.editor.editor.ui.contribution.DeployContributionItem;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.ProjectExplorerBot;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotMultiPageEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

public class BotBdmEditor extends BotBase {

    private static final String BDM_EDITOR_ID = "org.bonitasoft.studio.businessobject.editor";
    protected SWTBotMultiPageEditor editor;
    private BotBdmModelEditor botBdmModelEditor;
    private BotBdmConstraintsEditor botBdmConstraintsEditor;
    private BotBdmQueriesEditor botBdmQueriesEditor;
    private BotBdmIndexesEditor botBdmIndexesEditor;

    public BotBdmEditor(SWTGefBot bot) {
        super(bot);
        this.editor = bot.multipageEditorById(BDM_EDITOR_ID);
        new ProjectExplorerBot(bot).bdm();
        this.botBdmModelEditor = new BotBdmModelEditor(bot, editor, this);
        this.botBdmConstraintsEditor = new BotBdmConstraintsEditor(bot, editor, this);
        this.botBdmQueriesEditor = new BotBdmQueriesEditor(bot, editor, this);
        this.botBdmIndexesEditor = new BotBdmIndexesEditor(bot, editor, this);
    }

    public BotBdmModelEditor modelPage() {
        editor.activatePage(Messages.modelPageName);
        return botBdmModelEditor;
    }

    public BotBdmConstraintsEditor constraintsPage() {
        editor.activatePage(Messages.constraintsPageName);
        return botBdmConstraintsEditor;
    }

    public BotBdmQueriesEditor queriesPage() {
        editor.activatePage(Messages.queries);
        return botBdmQueriesEditor;
    }

    public BotBdmIndexesEditor indexesPage() {
        editor.activatePage(Messages.indexes);
        return botBdmIndexesEditor;
    }

    public BotBdmEditor deploy() {
        bot.toolbarButtonWithId(DeployContributionItem.ID).click();
        bot.activeShell().bot().waitUntil(Conditions.shellIsActive(Messages.bdmDeployedTitle), 20000);
        SWTBotShell activeShell = bot.activeShell();
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
        return this;
    }

    public BotBdmEditor save() {
        editor.save();
        bot.waitUntil(new DefaultCondition() {
            
            @Override
            public boolean test() throws Exception {
                if(editor.isDirty()) {
                    editor.save();
                    return false;
                }
                return true;
            }
            
            @Override
            public String getFailureMessage() {
                return "BDM Editor still ditrty after save !";
            }
        });
        return this;
    }

    public void close() {
        editor.close();
    }
}
