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

import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.forms.widgets.FormToolkit;

public abstract class AbstractDynamicButtonWidgetBuilder<T> {

    protected Optional<String> text = Optional.empty();
    protected Optional<String> tooltipText = Optional.empty();
    protected Optional<Image> image = Optional.empty();
    protected Optional<Image> hotImage = Optional.empty();
    protected Optional<Consumer<Event>> onClickListener = Optional.empty();
    protected Optional<Integer> maxTextWidth = Optional.empty();
    protected Optional<String> cssClass = Optional.empty();
    protected Optional<Object> layoutData = Optional.empty();
    protected Optional<Font> font = Optional.empty();
    protected Optional<String> defaultTextColorCssId = Optional.empty();
    protected Optional<String> hoverTextColorCssId = Optional.empty();
    protected Optional<FormToolkit> toolkit = Optional.empty();
    protected Optional<String> id = Optional.empty();
    protected boolean labelBelow = false;

    public T withLabel(String text) {
        this.text = Optional.ofNullable(text);
        return getThis();
    }

    public T withId(String id) {
        this.id = Optional.ofNullable(id);
        return getThis();
    }

    public T withTooltipText(String tooltipText) {
        this.tooltipText = Optional.ofNullable(tooltipText);
        return getThis();
    }

    public T withImage(Image image) {
        this.image = Optional.ofNullable(image);
        return getThis();
    }

    public T withHotImage(Image hotImage) {
        this.hotImage = Optional.ofNullable(hotImage);
        return getThis();
    }

    public T withMaxTextWidth(int maxTextWidth) {
        this.maxTextWidth = Optional.ofNullable(maxTextWidth);
        return getThis();
    }

    public T withCssclass(String cssClass) {
        this.cssClass = Optional.ofNullable(cssClass);
        return getThis();
    }

    public T withTextColors(String defaultTextColorCssId, String hoverTextColorCssId) {
        this.defaultTextColorCssId = Optional.ofNullable(defaultTextColorCssId);
        this.hoverTextColorCssId = Optional.ofNullable(hoverTextColorCssId);
        return getThis();
    }

    public T withLayoutData(Object layoutData) {
        this.layoutData = Optional.ofNullable(layoutData);
        return getThis();
    }

    public T withFont(Font font) {
        this.font = Optional.ofNullable(font);
        return getThis();
    }

    public T withToolkit(FormToolkit toolkit) {
        this.toolkit = Optional.ofNullable(toolkit);
        return getThis();
    }

    public T labelBelow() {
        this.labelBelow = true;
        return getThis();
    }

    protected T getThis() {
        return (T) this;
    }

}
