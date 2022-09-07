/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.ui.styler;

import java.util.Optional;

import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.TextStyle;

public class StylerBuilder {

    private Optional<Color> color = Optional.empty();
    private Optional<Font> font = Optional.empty();

    public StylerBuilder withColor(Color color) {
        this.color = Optional.ofNullable(color);
        return this;
    }

    public StylerBuilder withFont(Font font) {
        this.font = Optional.ofNullable(font);
        return this;
    }

    public Styler create() {
        return new Styler() {

            @Override
            public void applyStyles(TextStyle textStyle) {
                color.ifPresent(c -> textStyle.foreground = c);
                font.ifPresent(f -> textStyle.font = f);
            }
        };
    }

}
