/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.application.coolbar;

import org.bonitasoft.studio.common.extension.IBonitaContributionItem;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class SeparatorCoolbarItem extends ContributionItem implements IBonitaContributionItem {

    @Override
    public String getId() {
        return "org.bonitasoft.studio.coolbar.separator";
    }

    @Override
    public void fill(final ToolBar toolbar, final int index, final int iconSize) {
        ToolItem toolItem = new ToolItem(toolbar, SWT.SEPARATOR | SWT.VERTICAL);
        toolItem.setEnabled(true);
    }

}
