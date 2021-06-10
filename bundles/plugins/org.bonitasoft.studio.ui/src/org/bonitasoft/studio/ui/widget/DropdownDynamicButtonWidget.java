/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
import java.util.Optional;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class DropdownDynamicButtonWidget extends DynamicButtonWidget {

    public static class Builder extends AbstractDynamicButtonWidgetBuilder<Builder> {

        private List<DropdownItem> dropDownItems = new ArrayList<>();

        public Builder addDropdownItem(String label, Image image, Listener onClickListener) {
            dropDownItems.add(new DropdownItem(label, Optional.ofNullable(image), onClickListener));
            return getThis();
        }

        public DropdownDynamicButtonWidget createIn(Composite parent) {
            var widget = new DropdownDynamicButtonWidget(parent, text, id, tooltipText, image, hotImage, maxTextWidth,
                    cssClass, layoutData, font, defaultTextColorCssId, hoverTextColorCssId, toolkit, dropDownItems);
            widget.createControl();
            return widget;
        }

    }

    static class DropdownItem {

        private String label;
        private Optional<Image> image;
        private Listener onClickListener;

        public DropdownItem(String label, Optional<Image> image, Listener onClickListener) {
            this.label = label;
            this.image = image;
            this.onClickListener = onClickListener;
        }

        public String getLabel() {
            return label;
        }

        public Optional<Image> getImage() {
            return image;
        }

        public Listener getOnClickListener() {
            return onClickListener;
        }

    }

    class DropdownSelectionListener extends SelectionAdapter {

        private final Menu menu;

        public DropdownSelectionListener(ToolItem dropdown, List<DropdownItem> dropdownItems) {
            menu = new Menu(dropdown.getParent().getShell());
            dropdownItems.forEach(item -> {
                MenuItem menuItem = new MenuItem(menu, SWT.PUSH);
                menuItem.setText(item.getLabel());
                item.getImage().ifPresent(menuItem::setImage);
                menuItem.addListener(SWT.Selection, item.getOnClickListener());
            });
        }

        @Override
        public void widgetSelected(final SelectionEvent event) {
            final ToolItem item = (ToolItem) event.widget;
            final Rectangle rect = item.getBounds();
            final Point pt = item.getParent().toDisplay(new Point(rect.x, rect.y));
            menu.setLocation(pt.x, pt.y + rect.height);
            menu.setVisible(true);
        }

        public void addSeparator() {
            new MenuItem(menu, SWT.SEPARATOR | SWT.HORIZONTAL);
        }
    }

    private List<DropdownItem> menuItems;

    public DropdownDynamicButtonWidget(Composite parent,
            Optional<String> text,
            Optional<String> id,
            Optional<String> tooltipText,
            Optional<Image> image,
            Optional<Image> hotImage,
            Optional<Integer> maxTextWidth,
            Optional<String> cssClass,
            Optional<Object> layoutData,
            Optional<Font> font,
            Optional<String> defaultTextColorCssId,
            Optional<String> hoverTextColorCssId,
            Optional<FormToolkit> toolkit,
            List<DropdownItem> menuItems) {
        super(parent, text, id, tooltipText, image, hotImage, Optional.empty(), maxTextWidth, cssClass, layoutData,
                font,
                defaultTextColorCssId, hoverTextColorCssId, toolkit);
        this.menuItems = menuItems;
    }

    @Override
    protected void createToolItem(ToolBar toolbar) {
        toolItem = new ToolItem(toolbar, SWT.DROP_DOWN);
        id.ifPresent(id -> toolItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, id));
        image.ifPresent(img -> toolItem.setImage(img));
        hotImage.ifPresent(hotImg -> toolItem.setHotImage(hotImg));
        tooltipText.ifPresent(tooltip -> toolItem.setToolTipText(tooltip));

        toolItem.addSelectionListener(new DropdownSelectionListener(toolItem, menuItems));
    }

}
