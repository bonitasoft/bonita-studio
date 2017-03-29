/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.ui.viewer;

import java.util.Optional;
import java.util.function.Function;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class LabelProviderBuilder<T> {

    private Optional<Function<T, String>> textFunction = Optional.empty();
    private Optional<Function<T, Image>> imageFunction = Optional.empty();

    public LabelProviderBuilder<T> withTextProvider(Function<T, String> textFunction) {
        this.textFunction = Optional.ofNullable(textFunction);
        return this;
    }

    public LabelProviderBuilder<T> withImageProvider(Function<T, Image> imageFunction) {
        this.imageFunction = Optional.ofNullable(imageFunction);
        return this;
    }

    public ColumnLabelProvider createColumnLabelProvider() {
        return new ColumnLabelProvider() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
             */
            @Override
            public String getText(Object element) {
                try {
                    final T e = (T) element;
                    return textFunction.map(function -> function.apply(e)).orElse(super.getText(element));
                } catch (final ClassCastException e) {
                    return super.getText(element);
                }
            }

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.viewers.ColumnLabelProvider#getImage(java.lang.Object)
             */
            @Override
            public Image getImage(Object element) {
                try {
                    final T e = (T) element;
                    return imageFunction.map(function -> function.apply(e)).orElse(super.getImage(element));
                } catch (final ClassCastException e) {
                    return super.getImage(element);
                }
            }
        };
    }

    public LabelProvider createLabelProvider() {
        return new LabelProvider() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
             */
            @Override
            public String getText(Object element) {
                try {
                    final T e = (T) element;
                    return textFunction.map(function -> function.apply(e)).orElse(super.getText(element));
                } catch (final ClassCastException e) {
                    return super.getText(element);
                }
            }

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.viewers.ColumnLabelProvider#getImage(java.lang.Object)
             */
            @Override
            public Image getImage(Object element) {
                try {
                    final T e = (T) element;
                    return imageFunction.map(function -> function.apply(e)).orElse(super.getImage(element));
                } catch (final ClassCastException e) {
                    return super.getImage(element);
                }
            }
        };
    }
}
