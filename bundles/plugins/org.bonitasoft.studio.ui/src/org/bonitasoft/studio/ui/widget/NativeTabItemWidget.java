/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.ui.widget;

import java.util.Objects;

import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

/**
 * On linux and osx, this widget creates a @TabItem control.
 * On windows, this widget creates a @CTabItem control.
 * -> The TabFolder visual rendering is really great on linux and macos,
 * but pretty bad on windows (especially when css themes are used).
 */
public class NativeTabItemWidget {

    private NativeTabFolderWidget tabFolder;
    private Control control;
    private String text;
    private int style;
    private Item item;

    public static class Builder {

        private Control control;
        private String text;
        private int style = SWT.NONE;

        public Builder withControl(Control control) {
            this.control = control;
            return this;
        }

        public Builder withText(String text) {
            this.text = text;
            return this;
        }

        public Builder withStyle(int style) {
            this.style = style;
            return this;
        }

        public NativeTabItemWidget createIn(NativeTabFolderWidget tabFolder) {
            NativeTabItemWidget tabItem = new NativeTabItemWidget(tabFolder, control, text, style);
            tabItem.init();
            return tabItem;
        }

    }

    public NativeTabItemWidget(NativeTabFolderWidget tabFolder, Control control, String text, int style) {
        this.tabFolder = tabFolder;
        this.control = control;
        this.text = text;
        this.style = style;
    }

    private void init() {
        if (useCTabItem()) {
            createCTabItem();
        } else {
            createTabItem();
        }
        tabFolder.addChild(this);
    }

    private void createTabItem() {
        TabFolder parent = (TabFolder) tabFolder.getTabFolder();
        TabItem tabItem = new TabItem(parent, style);
        tabItem.setText(text);
        if (control != null) {
            tabItem.setControl(control);
        }
        this.item = tabItem;
    }

    private void createCTabItem() {
        CTabFolder parent = (CTabFolder) tabFolder.getTabFolder();
        CTabItem cTabItem = new CTabItem(parent, style);
        cTabItem.setText(text);
        if (control != null) {
            cTabItem.setControl(control);
        }
        this.item = cTabItem;
    }

    private boolean useCTabItem() {
        return Objects.equals(Platform.OS_MACOSX, Platform.getOS()) || Objects.equals(Platform.OS_WIN32, Platform.getOS());
    }

    public void setControl(Control control) {
        if (useCTabItem()) {
            ((CTabItem) item).setControl(control);
        } else {
            ((TabItem) item).setControl(control);
        }
    }

    public Item getItem() {
        return item;
    }

    public Control getControl() {
        return control;
    }
}
