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

import org.bonitasoft.engine.bdm.BDMSimpleNameProvider;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.common.functions.ContractInputFunctions;
import org.bonitasoft.studio.model.process.ContractInput;

import com.google.common.base.Joiner;

public class SimpleFieldPropertyInitializer implements IPropertyInitializer {

    private final SimpleField field;
    private final ContractInput contractInput;
    private final BusinessObject parentBusinessObject;
    private AbstractBusinessObjectInitializer parent;

    public SimpleFieldPropertyInitializer(final BusinessObject parentBusinessObject, final SimpleField field,
            final ContractInput contractInput) {
        this.parentBusinessObject = parentBusinessObject;
        this.field = field;
        this.contractInput = contractInput;
    }

    public BusinessObject getParentBusinessObject() {
        return parentBusinessObject;
    }

    @Override
    public String getPropertyName() {
        return field.getName();
    }

    @Override
    public String getInitialValue() {
        final StringBuilder scriptBuilder = withComplexMultipleInHierarchy().apply(contractInput)
                ? new StringBuilder(
                        prefixIterator(Joiner.on(".").join(toAncestorNameListUntilMultipleComplex().apply(contractInput))))
                : new StringBuilder(Joiner.on(".").join(ContractInputFunctions.toAncestorNameList().apply(contractInput)));
        castInputValue(scriptBuilder);
        return scriptBuilder.toString();
    }

    private void castInputValue(final StringBuilder scriptBuilder) {
        switch (field.getType()) {
            case FLOAT:
                scriptBuilder.append(contractInput.isMultiple() ? "?.collect{ it.toFloat() }" : "?.toFloat()");
                break;
            case LONG:
                scriptBuilder.append(contractInput.isMultiple() ? "?.collect{ it.toLong() }" : "?.toLong()");
                break;
            default:
                break;
        }
    }

    private String prefixIterator(final String content) {
        return iteratorName() + "." + content;
    }

    private String iteratorName() {
        return "current" + BDMSimpleNameProvider.getSimpleBusinessObjectName(parentBusinessObject.getQualifiedName())
                + "Input";
    }

    @Override
    public void setParent(final AbstractBusinessObjectInitializer parentInitializer) {
        parent = parentInitializer;
    }

    @Override
    public AbstractBusinessObjectInitializer getParent() {
        return parent;
    }
}
