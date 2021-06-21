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

import static org.bonitasoft.studio.common.functions.ContractInputFunctions.toAncestorNameListUntilMultipleComplex;
import static org.bonitasoft.studio.common.predicate.ContractInputPredicates.withComplexMultipleInHierarchy;

import java.util.stream.Collectors;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.studio.contract.core.mapping.operation.BusinessObjectInstantiationException;

public class MultipleAggregationReferencePropertyInitializer extends MultipleAggregationBusinessObjectQueryInitializer {

    private final BusinessObject multipleParentBusinessObject;

    public MultipleAggregationReferencePropertyInitializer(final BusinessObject multipleParentBusinessObject,
            final BusinessObject businessObject,
            final InitializerContext context) {
        super(businessObject, context);
        this.multipleParentBusinessObject = multipleParentBusinessObject;
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
    protected String inputListToIterate() {
        return shouldUseParentIterator() ? buildListAccessorWithIteratorName() : super.inputListToIterate();
    }

    private String buildListAccessorWithIteratorName() {
        return iteratorName(multipleParentBusinessObject) + "."
                + toAncestorNameListUntilMultipleComplex().apply(contractInput).stream().collect(Collectors.joining("."));
    }

    private boolean shouldUseParentIterator() {
        return contractInput.eContainer() != null && multipleParentBusinessObject != null
                && withComplexMultipleInHierarchy().test(contractInput);
    }

}
