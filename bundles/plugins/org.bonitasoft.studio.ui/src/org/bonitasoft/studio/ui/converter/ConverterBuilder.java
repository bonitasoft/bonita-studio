/**
 * Copyright (C) 2017 Bonitasoft S.A.
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

package org.bonitasoft.studio.ui.converter;

import java.util.function.Function;

import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;

public class ConverterBuilder<F, T> {

    private Class<F> fromType;
    private Class<T> toType;
    private Function<F, T> converterFunction;

    private ConverterBuilder() {
    }

    public static <F, T> ConverterBuilder<F, T> newConverter() {
        return new ConverterBuilder<>();
    }

    public ConverterBuilder<F, T> fromType(Class<F> fromType) {
        this.fromType = fromType;
        return this;
    }

    public ConverterBuilder<F, T> toType(Class<T> toType) {
        this.toType = toType;
        return this;
    }

    public ConverterBuilder<F, T> withConvertFunction(Function<F, T> converterFunction) {
        this.converterFunction = converterFunction;
        return this;
    }

    public IConverter create() {
        return new Converter(fromType, toType) {

            @SuppressWarnings("unchecked")
            @Override
            public Object convert(Object fromObject) {
                return converterFunction.apply((F) fromObject);
            }
        };
    }
}
