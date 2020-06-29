/**
 * Copyright (C) 2020 BonitaSoft S.A.
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
package org.bonitasoft.studio.businessobject.editor.editor.ui.contribution;

import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.preferences.PreferenceUtil;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class ExploreBDMContributionItem extends ContributionItem {

    public static final String ID = "org.bonitasoft.studio.bdm.editor.explore";
    public static final String EXPLORE_BDM_COMMAND = "org.bonitasoft.studio.businessobject.explore.command";

    protected ToolItem item;
    private CommandExecutor commandExecutor;

    public ExploreBDMContributionItem() {
        super(ID);
        commandExecutor = new CommandExecutor();
    }

    @Override
    public void fill(ToolBar parent, int index) {
        item = new ToolItem(parent, SWT.PUSH);
        item.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ID);
        item.setText(Messages.explore);
        item.setToolTipText(Messages.exploreTooltip);
        Image image = PreferenceUtil.isDarkTheme()
                ? BusinessObjectPlugin.getImage("icons/explore_16_dark.png")
                : BusinessObjectPlugin.getImage("icons/explore_16.png");
        item.setImage(image);
        item.addListener(SWT.Selection, event -> commandExecutor.executeCommand(EXPLORE_BDM_COMMAND, null));
        item.setEnabled(isEnabled());
    }

    @Override
    public boolean isEnabled() {
        return commandExecutor.canExecute(EXPLORE_BDM_COMMAND, null);
    }

}
