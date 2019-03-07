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

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.contract.core.mapping.operation.BusinessObjectInstantiationException;
import org.bonitasoft.studio.model.process.ContractInput;

public class CompositionReferencePropertyInitializer extends NewBusinessObjectInitializer {

    public CompositionReferencePropertyInitializer(final InitializerContext context) {
        super(context);
    }

    @Override
    public String getInitialValue() throws BusinessObjectInstantiationException {
        final String initialValue = super.getInitialValue();
        final StringBuilder scriptBuilder = new StringBuilder();
        scriptBuilder.append("{");
        scriptBuilder.append(System.lineSeparator());
        scriptBuilder.append(initialValue);
        scriptBuilder.append("}()");
        return scriptBuilder.toString();
    }

    @Override
    protected boolean checkExistence() {
        return hasAMultipleParent()
                ? super.checkExistence()
                : true;
    }

    private boolean hasAMultipleParent() {
        final ContractInput parentInput = ModelHelper.getFirstContainerOfType(context.getContractInput().eContainer(),
                ContractInput.class);
        return parentInput != null && parentInput.isMultiple();
    }

}
