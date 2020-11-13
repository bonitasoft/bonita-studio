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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

/**
 * On linux and osx, this widget creates a @TabFolder control.
 * On windows, this widget creates a @CTabFolder control.
 * -> The TabFolder visual rendering is really great on linux and macos,
 * but pretty bad on windows (especially when css themes are used).
 * This widget is used by @NativeTabItemWidget widgets, which are @TabItem or @CTabItem controls, dependening on the os.
 */
public class NativeTabFolderWidget {

    private Composite parent;
    private Composite tabFolder;
    private List<NativeTabItemWidget> children = new ArrayList<>();

    public static class Builder {

        public NativeTabFolderWidget createIn(Composite parent) {
            NativeTabFolderWidget widget = new NativeTabFolderWidget(parent);
            widget.init();
            return widget;
        }
    }

    private NativeTabFolderWidget(Composite parent) {
        this.parent = parent;
    }

    private void init() {
        tabFolder = createNativeTabFolder();
    }

    private Composite createNativeTabFolder() {
        if (isWindows()) {
            return new CTabFolder(parent, SWT.TOP);
        }
        return new TabFolder(parent, SWT.TOP);
    }

    private boolean isWindows() {
        return Platform.getOS().contains("win");
    }

    public Composite getTabFolder() {
        return tabFolder;
    }

    public void addSelectionListener(SelectionListener listener) {
        if (isWindows()) {
            ((CTabFolder) tabFolder).addSelectionListener(listener);
        } else {
            ((TabFolder) tabFolder).addSelectionListener(listener);
        }
    }

    public void setSelection(NativeTabItemWidget tabItem) {
        if (isWindows()) {
            ((CTabFolder) tabFolder).setSelection((CTabItem) tabItem.getItem());
            Event event = new Event();
            event.item =  tabItem.getItem();
            ((CTabFolder) tabFolder).notifyListeners(SWT.Selection, event);
        } else {
            ((TabFolder) tabFolder).setSelection((TabItem) tabItem.getItem());
        }
    }

    void addChild(NativeTabItemWidget tabItem) {
        children.add(tabItem);
    }

    public NativeTabItemWidget getSelection() {
        Object selection = isWindows()
                ? ((CTabFolder) tabFolder).getSelection()
                : ((TabFolder) tabFolder).getSelection()[0];
        return children.stream()
                .filter(child -> Objects.equals(child.getItem(), selection))
                .findFirst().orElse(null);
    }

    public int getSelectionIndex() {
        return isWindows()
                ? ((CTabFolder) tabFolder).getSelectionIndex()
                : ((TabFolder) tabFolder).getSelectionIndex();
    }

    public NativeTabItemWidget getItem(int index) {
        Item item = isWindows()
                ? ((CTabFolder) tabFolder).getItem(index)
                : ((TabFolder) tabFolder).getItem(index);
        return children.stream().filter(child -> Objects.equals(item, child.getItem())).findFirst().orElse(null);
    }

    public List<NativeTabItemWidget> getItems() {
        return children;
    }

    public void notifyListeners(int eventType, Event event) {
        if (isWindows()) {
            ((CTabFolder) tabFolder).notifyListeners(eventType, event);
        } else {
            ((TabFolder) tabFolder).notifyListeners(eventType, event);
        }
    }

    public void setLayoutData(Object layoutData) {
        tabFolder.setLayoutData(layoutData);
    }

    public void setLayout(Layout layout) {
        tabFolder.setLayout(layout);
    }

}
