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

import org.bonitasoft.studio.common.jface.SWTBotConstants;
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
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * Web style button:
 * - An image and a text on the right
 * - The image and the text are clickable
 * - Mouse hover behavior (a 'hot image' has to be provided for the image)
 */
public class DynamicButtonWidget {

    public static class Builder extends AbstractDynamicButtonWidgetBuilder<Builder> {

        public Builder onClick(Consumer<Event> onClickListener) {
            this.onClickListener = Optional.ofNullable(onClickListener);
            return getThis();
        }

        public DynamicButtonWidget createIn(Composite parent) {
            var widget = new DynamicButtonWidget(parent, text, id, tooltipText, image, hotImage, onClickListener,
                    maxTextWidth,
                    cssClass, layoutData, font, defaultTextColorCssId, hoverTextColorCssId, toolkit);
            widget.createControl();
            return widget;
        }

    }

    private Composite parent;
    private Optional<String> text;
    protected Optional<String> id;
    protected Optional<String> tooltipText;
    protected Optional<Image> image;
    protected Optional<Image> hotImage;
    private Optional<Consumer<Event>> onClickListener;
    private Optional<Integer> maxTextWidth;
    private Optional<String> cssClass;
    private Optional<Object> layoutData;
    private Optional<Font> font;
    private Optional<FormToolkit> toolkit;

    protected IThemeEngine engine;
    protected ToolItem toolItem;
    protected Cursor cursorArrow;
    protected Cursor cursorHand;
    protected Optional<String> defaultTextColorCssId;
    protected Optional<String> hoverTextColorCssId;

    private Composite container;

    public DynamicButtonWidget(Composite parent,
            Optional<String> text,
            Optional<String> id,
            Optional<String> tooltipText,
            Optional<Image> image,
            Optional<Image> hotImage,
            Optional<Consumer<Event>> onClickListener,
            Optional<Integer> maxTextWidth,
            Optional<String> cssClass,
            Optional<Object> layoutData,
            Optional<Font> font,
            Optional<String> defaultTextColorCssId,
            Optional<String> hoverTextColorCssId,
            Optional<FormToolkit> toolkit) {
        this.parent = parent;
        this.text = text;
        this.id = id;
        this.tooltipText = tooltipText;
        this.image = image;
        this.hotImage = hotImage;
        this.onClickListener = onClickListener;
        this.maxTextWidth = maxTextWidth;
        this.cssClass = cssClass;
        this.layoutData = layoutData;
        this.font = font;
        this.defaultTextColorCssId = defaultTextColorCssId;
        this.hoverTextColorCssId = hoverTextColorCssId;
        this.toolkit = toolkit;

        cursorHand = parent.getDisplay().getSystemCursor(SWT.CURSOR_HAND);
        cursorArrow = parent.getDisplay().getSystemCursor(SWT.CURSOR_ARROW);
        engine = PlatformUI.getWorkbench().getService(IThemeEngine.class);
    }

    protected void createControl() {
        container = toolkit.map(t -> t.createComposite(parent))
                .orElseGet(() -> new Composite(parent, SWT.NONE));
        container.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(text.isPresent() ? 2 : 1)
                        .spacing(1, LayoutConstants.getSpacing().y).create());
        container.setLayoutData(layoutData.orElse(GridDataFactory.swtDefaults().create()));
        cssClass.ifPresent(css -> container.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, css));

        ToolBar toolbar = new ToolBar(container, SWT.HORIZONTAL | SWT.FLAT);
        toolbar.setLayoutData(GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.FILL).create());
        cssClass.ifPresent(css -> toolbar.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, css));
        toolkit.ifPresent(t -> t.adapt(toolbar));

        createToolItem(toolbar);

        if (text.isPresent()) {
            Label label = toolkit.map(t -> t.createLabel(container, text.get(), SWT.WRAP))
                    .orElseGet(() -> new Label(container, SWT.WRAP));
            label.setLayoutData(GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.CENTER)
                    .grab(maxTextWidth.isEmpty(), false)
                    .hint(maxTextWidth.orElse(SWT.DEFAULT), SWT.DEFAULT)
                    .create());
            label.setText(text.get());
            tooltipText.ifPresent(label::setToolTipText);
            font.ifPresent(label::setFont);
            label.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME,
                    defaultTextColorCssId.orElse(BonitaThemeConstants.TOOLBAR_TEXT_COLOR));
            cssClass.ifPresent(css -> label.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, css));

            addMouseBehavior(toolbar, label);

        }

        toolbar.addMouseTrackListener(new MouseTrackAdapter() {

            @Override
            public void mouseExit(MouseEvent e) {
                toolbar.setCursor(cursorArrow);
            }

            @Override
            public void mouseEnter(MouseEvent e) {
                if (toolItem.isEnabled()) {
                    toolbar.setCursor(cursorHand);
                }
            }
        });
    }

    private void addMouseBehavior(ToolBar toolbar, Label label) {
        if (applyMouseBehavior()) {
            addTextSelectionBehavior(label);
            addHoverFeedbacks(toolbar, label);
        }
    }

    protected boolean applyMouseBehavior() {
        return onClickListener.isPresent();
    }

    protected void addTextSelectionBehavior(Label label) {
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

    private void addHoverFeedbacks(ToolBar toolbar, Label label) {
        label.addMouseTrackListener(new MouseTrackAdapter() {

            @Override
            public void mouseExit(MouseEvent e) {
                label.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME,
                        defaultTextColorCssId.orElse(BonitaThemeConstants.TOOLBAR_TEXT_COLOR));
                engine.applyStyles(label, false);
                image.ifPresent(toolItem::setImage);
                label.setCursor(cursorArrow);
                toolbar.setCursor(cursorArrow);
            }

            @Override
            public void mouseEnter(MouseEvent e) {
                if (toolItem.isEnabled()) {
                    label.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME,
                            hoverTextColorCssId.orElse(BonitaThemeConstants.TITLE_TEXT_COLOR));
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
                label.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME,
                        defaultTextColorCssId.orElse(BonitaThemeConstants.TOOLBAR_TEXT_COLOR));
                engine.applyStyles(label, false);
                label.setCursor(cursorArrow);
            }

            @Override
            public void mouseEnter(MouseEvent e) {
                if (toolItem.isEnabled()) {
                    label.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME,
                            hoverTextColorCssId.orElse(BonitaThemeConstants.TITLE_TEXT_COLOR));
                    engine.applyStyles(label, false);
                    label.setCursor(cursorHand);
                }
            }
        });
    }

    protected void createToolItem(ToolBar toolbar) {
        toolItem = new ToolItem(toolbar, SWT.PUSH);
        id.ifPresent(id -> toolItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, id));
        image.ifPresent(img -> toolItem.setImage(img));
        hotImage.ifPresent(hotImg -> toolItem.setHotImage(hotImg));
        tooltipText.ifPresent(tooltip -> toolItem.setToolTipText(tooltip));

        onClickListener.ifPresent(onClick -> toolItem.addListener(SWT.Selection, onClick::accept));
    }

    public IObservableValue<Boolean> observeEnable() {
        return WidgetProperties.enabled().observe(toolItem);
    }

    public Composite getContainer() {
        return container;
    }

}
