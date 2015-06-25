/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.contract.core.mapping.operation.initializer;

import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.studio.contract.core.mapping.operation.BusinessObjectInstantiationException;
import org.eclipse.swt.SWT;

import com.google.common.base.Splitter;

public class CompositionReferencePropertyInitializer extends BusinessObjectInitializer implements IPropertyInitializer {

    public CompositionReferencePropertyInitializer(final RelationField field, final String refName) {
        super(field, refName);
    }

    @Override
    public String getInitialValue() throws BusinessObjectInstantiationException {
        final String initialValue = super.getInitialValue();
        final StringBuilder scriptBuilder = new StringBuilder();
        scriptBuilder.append("{");
        scriptBuilder.append(System.lineSeparator());
        scriptBuilder.append(indent(initialValue));
        scriptBuilder.append("}()");
        return scriptBuilder.toString();
    }

    private String indent(final String initialValue) {
        final StringBuilder scriptBuilder = new StringBuilder();
        final Iterable<String> lines = Splitter.on(System.lineSeparator()).split(initialValue);
        for (final String line : lines) {
            scriptBuilder.append(SWT.TAB);
            scriptBuilder.append(line);
            scriptBuilder.append(System.lineSeparator());
        }
        return scriptBuilder.toString();
    }

}
