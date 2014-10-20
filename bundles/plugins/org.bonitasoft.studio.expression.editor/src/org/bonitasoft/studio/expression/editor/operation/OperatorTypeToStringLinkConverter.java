/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.expression.editor.operation;

import org.eclipse.core.databinding.conversion.Converter;

/**
 * This Converter is just adding the <A> tags around the String corresponding the Operator Message in order to be well displayed in Link components.
 *
 * @author Aurelien Pupier
 */
public class OperatorTypeToStringLinkConverter extends Converter {

    private final OperatorLabelProvider operatorLabelProvider;

    public OperatorTypeToStringLinkConverter() {
        super(String.class, String.class);
        operatorLabelProvider = new OperatorLabelProvider();
    }

    @Override
    public String convert(final Object arg0) {
        final String operatorType = (String) arg0;
        return "<A>" + operatorLabelProvider.getText(operatorType) + "</A>";
    }

}
