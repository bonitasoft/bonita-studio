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

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.studio.contract.core.mapping.operation.BusinessObjectInstantiationException;
import org.bonitasoft.studio.model.process.ContractInput;

import com.google.common.base.Joiner;

public class MultipleAggregationReferencePropertyInitializer extends MultipleBusinessObjectQueryInitializer implements IPropertyInitializer {

    private final BusinessObject multipleParentBusinessObject;

    public MultipleAggregationReferencePropertyInitializer(final BusinessObject multipleParentBusinessObject, final BusinessObject businessObject,
            final RelationField field,
            final ContractInput contractInput, final String refName, final boolean isOnPool) {
        super(businessObject, field, contractInput, refName, isOnPool);
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
    protected boolean shouldAppendExistingObjects() {
        return multipleParentBusinessObject == null && !isOnPool;
    }

    @Override
    protected String inputListToIterate() {
        return shouldUseParentIterator() ? buildListAccessorWithIteratorName() : super.inputListToIterate();
    }

    private String buildListAccessorWithIteratorName() {
        return iteratorName(multipleParentBusinessObject) + "." + Joiner.on(".").join(toAncestorNameListUntilMultipleComplex().apply(contractInput));
    }

    private boolean shouldUseParentIterator() {
        return contractInput.eContainer() != null && multipleParentBusinessObject != null && withComplexMultipleInHierarchy().apply(contractInput);
    }

}
