/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.coolbar;

import org.bonitasoft.studio.application.coolbar.CoolbarToolControl.CoolbarSize;
import org.bonitasoft.studio.common.extension.IBonitaContributionItem;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class CustomToolItem {

    private IBonitaContributionItem contributionItem;
    private static final int ICON_SIZE = 24;
    private ToolItem toolItem;
    private Label text;

    public CustomToolItem(IBonitaContributionItem contributionItem) {
        this.contributionItem = contributionItem;
    }

    public void setEnabled(boolean enabled) {
        if (toolItem != null && !toolItem.isDisposed()) {
            toolItem.setEnabled(enabled);
        }
        if (text != null && !text.isDisposed()) {
            text.setEnabled(enabled);
        }
    }

    public void update() {
        setEnabled(contributionItem.isEnabled());
    }

    public void createControl(Composite parent, CoolbarSize size) {
        if (contributionItem instanceof SeparatorCoolbarItem) {
            Label separator = new Label(parent, SWT.SEPARATOR);
            separator.setLayoutData(GridDataFactory.fillDefaults()
                    .hint(SWT.DEFAULT, size == CoolbarSize.SMALL ? ICON_SIZE : SWT.DEFAULT).create());
        } else {
            Composite container = new Composite(parent, SWT.NONE);
            container.setLayoutData(GridDataFactory.swtDefaults().create());
            container.setLayout(GridLayoutFactory.fillDefaults().spacing(0, 3)
                    .extendedMargins(0, 0, 0, size != CoolbarSize.SMALL ? 5 : 0).create());
            ToolBar tb = new ToolBar(container, SWT.FLAT | SWT.HORIZONTAL);
            tb.setLayoutData(GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.FILL).grab(true, true).create());
            contributionItem.fill(tb, -1, size == CoolbarSize.SMALL ? ICON_SIZE : -1);
            toolItem = tb.getItem(0);
            if (size != CoolbarSize.SMALL) {
                text = new Label(container, SWT.CENTER);
                text.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
                text.setText(contributionItem.getText());
            }
            update();
        }
    }

    public IBonitaContributionItem getContributionItem() {
        return contributionItem;
    }

}
