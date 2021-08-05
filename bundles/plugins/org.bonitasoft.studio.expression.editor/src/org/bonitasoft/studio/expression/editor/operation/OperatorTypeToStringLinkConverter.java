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

import org.bonitasoft.studio.model.expression.Operator;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.value.IObservableValue;

/**
 * This Converter is just adding the <A> tags around the String corresponding the Operator Message in order to be well displayed in Link components.
 *
 * @author Aurelien Pupier
 */
public class OperatorTypeToStringLinkConverter extends Converter {

    private final OperatorLabelProvider operatorLabelProvider;
    private final IObservableValue operatorObservableValue;

    public OperatorTypeToStringLinkConverter(final IObservableValue operatorObservableValue) {
        super(String.class, String.class);
        operatorLabelProvider = new OperatorLabelProvider();
        this.operatorObservableValue = operatorObservableValue;
    }

    @Override
    public String convert(final Object from) {
        final Operator operator = (Operator) operatorObservableValue.getValue();
        final String operatorType = (String) from;
        if (operator != null) {
            return "<A>" + operatorLabelProvider.getText(operator) + "</A>";
        }
        return "<A>" + operatorLabelProvider.getText(operatorType) + "</A>";

    }

}
