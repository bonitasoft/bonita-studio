/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
package org.bonitasoft.studio.team.git.ui;

import org.bonitasoft.studio.common.extension.IBonitaContributionItem;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.team.git.TeamGitPlugin;
import org.bonitasoft.studio.team.git.i18n.Messages;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class GitCoolbarItem extends ContributionItem implements IBonitaContributionItem {

    private Label label;
    private ToolItem item;

    @Override
    public void fill(ToolBar toolbar, int index, int iconSize) {
        item = new ToolItem(toolbar, SWT.DROP_DOWN);
        item.setToolTipText(Messages.gitButtonTooltip);
        if (iconSize < 0) {
            item.setImage(Pics.getImage("git_coolbar_big.png", TeamGitPlugin.getDefault()));
        } else {
            item.setImage(Pics.getImage("git_coolbar_small.png", TeamGitPlugin.getDefault()));
        }
        item.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (isEnabled()) {
                    var menu = new Menu(item.getParent().getShell());
                    final Rectangle rect = item.getBounds();
                    final Point pt = item.getParent().toDisplay(new Point(rect.x, rect.y));
                    menu.setLocation(pt.x, pt.y + rect.height);
                  
                    new MenuContributionItem().fill(menu, 0);
                    menu.setVisible(true);
                }
            }

        });

    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getId() {
        return "org.bonitasoft.studio.coolbar.git";
    }

    @Override
    public String getText() {
        return "Git";
    }

    @Override
    public void setLabelControl(Label label) {
        this.label = label;
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (item != null && !item.isDisposed()) {
            item.setEnabled(enabled);
        }
        if (label != null && !label.isDisposed()) {
            label.setEnabled(enabled);
        }
    }
    
    

}
