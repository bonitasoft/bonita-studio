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

import static org.bonitasoft.studio.common.functions.ContractInputFunctions.toAncestorNameList;
import static org.bonitasoft.studio.common.functions.ContractInputFunctions.toAncestorNameListUntilMultipleComplex;
import static org.bonitasoft.studio.common.predicate.ContractInputPredicates.withComplexMultipleInHierarchy;

import java.util.stream.Collectors;

import org.bonitasoft.engine.bdm.BDMSimpleNameProvider;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;

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
        String initialValueScript = initialValueScript("?.");
        StringBuilder scriptBuilder = new StringBuilder(initialValueScript);
        if (shouldCastStringToLong()) {
            castStringToLong(scriptBuilder, initialValueScript);
        } else if (shouldCastToFloat()) {
            castToFloat(scriptBuilder, initialValueScript);
        }
        return scriptBuilder.toString();
    }

    private void castToFloat(StringBuilder scriptBuilder, String initialValueScript) {
        scriptBuilder.append(contractInput.isMultiple() ? "?.collect{ it.toFloat() }" : "?.toFloat()");
    }

    //By default a Double is used so we have to cast the double value to a float value
    private boolean shouldCastToFloat() {
        return field.getType() == FieldType.FLOAT && contractInput.getType() == ContractInputType.DECIMAL;
    }

    private boolean shouldCastStringToLong() {
        return field.getType() == FieldType.LONG && contractInput.getType() == ContractInputType.TEXT;
    }

    private String initialValueScript(String separator) {
        return withComplexMultipleInHierarchy().apply(contractInput)
                ? prefixIterator(toAncestorNameListUntilMultipleComplex().apply(contractInput).stream()
                        .collect(Collectors.joining(separator)))
                : toAncestorNameList().apply(contractInput).stream().collect(Collectors.joining(separator));
    }

    private void castStringToLong(final StringBuilder scriptBuilder, String initialValueScript) {
        scriptBuilder
                .append(contractInput.isMultiple() ? "?.findAll().collect{ it.toLong() }"
                        : String.format("?.trim() ? %s.toLong() : null", initialValueScript(".")));
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
