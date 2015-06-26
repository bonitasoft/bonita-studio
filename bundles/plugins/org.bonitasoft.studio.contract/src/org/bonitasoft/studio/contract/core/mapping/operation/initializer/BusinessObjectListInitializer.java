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

import org.bonitasoft.engine.bdm.BDMQueryUtil;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.studio.contract.core.mapping.operation.BusinessObjectInstantiationException;
import org.bonitasoft.studio.model.process.ContractInput;

import com.google.common.base.Joiner;

public class BusinessObjectListInitializer extends BusinessObjectInitializer implements IPropertyInitializer {

    protected final ContractInput contractInput;

    public BusinessObjectListInitializer(final RelationField field, final ContractInput contractInput, final String refName) {
        super(field, refName);
        this.contractInput = contractInput;
    }

    @Override
    public String getInitialValue() throws BusinessObjectInstantiationException {
        final BusinessObject businessObject = field.getReference();
        checkNotNullableFields(businessObject);

        final StringBuilder scriptBuilder = new StringBuilder();
        delcareVariable(scriptBuilder, listVarName(businessObject));
        scriptBuilder.append(" = ");
        scriptBuilder.append("[]");
        scriptBuilder.append(System.lineSeparator());

        if (shouldAppendExistingObjects()) {
            appendExistingBusinessObjects(scriptBuilder, businessObject);
        }

        forEach(scriptBuilder, businessObject);
        returnListVar(scriptBuilder, businessObject);
        return scriptBuilder.toString();
    }

    protected boolean shouldAppendExistingObjects() {
        return contractInput.eContainer() != null;
    }

    private void returnListVar(final StringBuilder scriptBuilder, final BusinessObject businessObject) {
        scriptBuilder.append("return");
        scriptBuilder.append(" ");
        scriptBuilder.append(listVarName(businessObject));
    }

    private void forEach(final StringBuilder scriptBuilder, final BusinessObject businessObject) throws BusinessObjectInstantiationException {
        //Iterate over the multiple input collection
        scriptBuilder.append(inputListToIterate());
        scriptBuilder.append(".each{");
        scriptBuilder.append(System.lineSeparator());

        //Add new business object based on current element in collection
        scriptBuilder.append(listVarName(businessObject));
        scriptBuilder.append(".add({ ");
        scriptBuilder.append(iteratorName(businessObject));
        scriptBuilder.append(" ->");
        scriptBuilder.append(System.lineSeparator());

        //Instantiate the new business object
        delcareVariable(scriptBuilder, varName(businessObject));
        scriptBuilder.append(" = new ");
        scriptBuilder.append(businessObject.getQualifiedName());
        scriptBuilder.append("()");
        scriptBuilder.append(System.lineSeparator());

        //Set new business object instance properties
        for (final IPropertyInitializer propertyInitializer : propertyInitializers) {
            initializeProperty(scriptBuilder, propertyInitializer, businessObject);
            scriptBuilder.append(System.lineSeparator());
        }

        returnVar(scriptBuilder, businessObject);
        scriptBuilder.append(System.lineSeparator());

        scriptBuilder.append("}(it))");
        scriptBuilder.append(System.lineSeparator());
        scriptBuilder.append("}");
        scriptBuilder.append(System.lineSeparator());
    }

    protected String inputListToIterate() {
        return Joiner.on(".").join(toAncestorNameList().apply(contractInput));
    }

    protected String iteratorName(final BusinessObject bo) {
        return "current" + BDMQueryUtil.getSimpleBusinessObjectName(bo.getQualifiedName()) + "Input";
    }

    protected void appendExistingBusinessObjects(final StringBuilder scriptBuilder, final BusinessObject businessObject) {
        scriptBuilder.append(listVarName(businessObject));
        scriptBuilder.append(".addAll(");
        scriptBuilder.append(refName);
        scriptBuilder.append(")");
        scriptBuilder.append(System.lineSeparator());
    }

    private String listVarName(final BusinessObject bo) {
        return uncapitalizeFirst(BDMQueryUtil.getSimpleBusinessObjectName(bo.getQualifiedName())) + "List";
    }

}
