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

import java.util.Optional;
import java.util.function.Consumer;

import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.PlatformUI;

/**
 * Web style button:
 * - An image and a text on the right
 * - The image and the text are clickable
 * - Mouse hover behavior (a 'hot image' has to be provided for the image)
 */
public class DynamicButtonWidget {

    public static class Builder {

        private Optional<String> text = Optional.empty();
        private Optional<String> tooltipText = Optional.empty();
        private Optional<Image> image = Optional.empty();
        private Optional<Image> hotImage = Optional.empty();
        private Optional<Consumer<Event>> onClickListener = Optional.empty();
        private Optional<Integer> maxTextWidth = Optional.empty();
        private Optional<String> cssClass = Optional.empty();

        public Builder withText(String text) {
            this.text = Optional.ofNullable(text);
            return this;
        }

        public Builder withTooltipText(String tooltipText) {
            this.tooltipText = Optional.ofNullable(tooltipText);
            return this;
        }

        public Builder withImage(Image image) {
            this.image = Optional.ofNullable(image);
            return this;
        }

        public Builder withHotImage(Image hotImage) {
            this.hotImage = Optional.ofNullable(hotImage);
            return this;
        }

        public Builder onClick(Consumer<Event> onClickListener) {
            this.onClickListener = Optional.ofNullable(onClickListener);
            return this;
        }

        public Builder withMaxTextWidth(int maxTextWidth) {
            this.maxTextWidth = Optional.ofNullable(maxTextWidth);
            return this;
        }

        public Builder withCssclass(String cssClass) {
            this.cssClass = Optional.ofNullable(cssClass);
            return this;
        }

        public DynamicButtonWidget createIn(Composite parent) {
            return new DynamicButtonWidget(parent, text, tooltipText, image, hotImage, onClickListener, maxTextWidth,
                    cssClass);
        }

    }

    private Composite parent;
    private Optional<String> text;
    private Optional<String> tooltipText;
    private Optional<Image> image;
    private Optional<Image> hotImage;
    private Optional<Consumer<Event>> onClickListener;
    private Optional<Integer> maxTextWidth;
    private Optional<String> cssClass = Optional.empty();

    private IThemeEngine engine;
    private ToolItem toolItem;
    private Cursor cursorArrow;
    private Cursor cursorHand;

    public DynamicButtonWidget(Composite parent,
            Optional<String> text,
            Optional<String> tooltipText,
            Optional<Image> image,
            Optional<Image> hotImage,
            Optional<Consumer<Event>> onClickListener,
            Optional<Integer> maxTextWidth,
            Optional<String> cssClass) {
        this.parent = parent;
        this.text = text;
        this.tooltipText = tooltipText;
        this.image = image;
        this.hotImage = hotImage;
        this.onClickListener = onClickListener;
        this.maxTextWidth = maxTextWidth;
        this.cssClass = cssClass;

        cursorHand = parent.getDisplay().getSystemCursor(SWT.CURSOR_HAND);
        cursorArrow = parent.getDisplay().getSystemCursor(SWT.CURSOR_ARROW);
        engine = PlatformUI.getWorkbench().getService(IThemeEngine.class);

        createControl();
    }

    private void createControl() {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(text.isPresent() ? 2 : 1)
                        .spacing(1, LayoutConstants.getSpacing().y).create());
        container.setLayoutData(GridDataFactory.swtDefaults().create());
        cssClass.ifPresent(css -> container.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, css));

        ToolBar toolbar = new ToolBar(container, SWT.HORIZONTAL | SWT.FLAT);
        toolbar.setLayoutData(GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.FILL).create());
        cssClass.ifPresent(css -> toolbar.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, css));

        toolItem = new ToolItem(toolbar, SWT.PUSH);
        image.ifPresent(img -> toolItem.setImage(img));
        hotImage.ifPresent(hotImg -> toolItem.setHotImage(hotImg));
        tooltipText.ifPresent(tooltip -> toolItem.setToolTipText(tooltip));

        onClickListener.ifPresent(onClick -> toolItem.addListener(SWT.Selection, onClick::accept));

        if (text.isPresent()) {
            Label label = new Label(container, SWT.WRAP);
            label.setLayoutData(GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.CENTER)
                    .grab(maxTextWidth.isEmpty(), false)
                    .hint(maxTextWidth.orElse(SWT.DEFAULT), SWT.DEFAULT)
                    .create());
            label.setText(text.get());
            label.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TOOLBAR_TEXT_COLOR);
            cssClass.ifPresent(css -> label.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, css));

            if (onClickListener.isPresent()) {
                label.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseUp(MouseEvent e) {
                        if (toolItem.isEnabled()) {
                            Rectangle bounds = label.getBounds();
                            if (e.x >= 0 && e.x <= bounds.width && e.y >= 0 && e.y <= bounds.height) {
                                onClickListener.get().accept(new Event());
                            }
                        }
                    }
                });
            }

            label.addMouseTrackListener(new MouseTrackAdapter() {

                @Override
                public void mouseExit(MouseEvent e) {
                    label.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TOOLBAR_TEXT_COLOR);
                    engine.applyStyles(label, false);
                    image.ifPresent(toolItem::setImage);
                    label.setCursor(cursorArrow);
                    toolbar.setCursor(cursorArrow);
                }

                @Override
                public void mouseEnter(MouseEvent e) {
                    if (toolItem.isEnabled()) {
                        label.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
                        engine.applyStyles(label, false);
                        hotImage.ifPresent(toolItem::setImage);
                        label.setCursor(cursorHand);
                        toolbar.setCursor(cursorHand);
                    }
                }
            });

            toolbar.addMouseTrackListener(new MouseTrackAdapter() {

                @Override
                public void mouseExit(MouseEvent e) {
                    label.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TOOLBAR_TEXT_COLOR);
                    engine.applyStyles(label, false);
                    label.setCursor(cursorArrow);
                    toolbar.setCursor(cursorArrow);
                }

                @Override
                public void mouseEnter(MouseEvent e) {
                    if (toolItem.isEnabled()) {
                        label.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
                        engine.applyStyles(label, false);
                        label.setCursor(cursorHand);
                        toolbar.setCursor(cursorHand);
                    }
                }
            });
        }
    }

    public IObservableValue<Boolean> observeEnable() {
        return WidgetProperties.enabled().observe(toolItem);
    }

}
