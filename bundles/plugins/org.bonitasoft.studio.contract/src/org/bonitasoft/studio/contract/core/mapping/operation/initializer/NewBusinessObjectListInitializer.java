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

import static com.google.common.collect.Iterables.getLast;
import static org.bonitasoft.studio.common.functions.ContractInputFunctions.toAncestorNameList;

import org.bonitasoft.engine.bdm.BDMQueryUtil;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.studio.contract.core.mapping.operation.BusinessObjectInstantiationException;
import org.bonitasoft.studio.model.process.ContractInput;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

public class NewBusinessObjectListInitializer extends AbstractBusinessObjectInitializer implements IPropertyInitializer {

    protected ContractInput contractInput;

    public NewBusinessObjectListInitializer(final InitializerContext context) {
        super(context);
        contractInput = context.getContractInput();
    }

    @Override
    public String getInitialValue() throws BusinessObjectInstantiationException {
        final BusinessObject businessObject = context.getField().getReference();
        checkNotNullableFields(businessObject);

        final StringBuilder scriptBuilder = new StringBuilder();
        final String listVarName = context.getLocalListVariableName();
        delcareVariable(scriptBuilder, listVarName);
        scriptBuilder.append(" = ");
        listConstructor(scriptBuilder, businessObject);

        if (canAppendExistingObjects()) {
            appendExistingBusinessObjects(scriptBuilder, listVarName);
        }

        forEach(scriptBuilder, businessObject, listVarName);
        returnListVar(scriptBuilder, listVarName);
        return scriptBuilder.toString();
    }

    private void listConstructor(final StringBuilder scriptBuilder, final BusinessObject businessObject) {
        scriptBuilder.append("[]");
        scriptBuilder.append(System.lineSeparator());
    }

    protected boolean canAppendExistingObjects() {
        return contractInput.eContainer() != null && !context.isOnPool();
    }

    private void returnListVar(final StringBuilder scriptBuilder, final String listVarName) {
        scriptBuilder.append("return");
        scriptBuilder.append(" ");
        scriptBuilder.append(listVarName);
    }

    private void forEach(final StringBuilder scriptBuilder, final BusinessObject businessObject, final String listVarName)
            throws BusinessObjectInstantiationException {
        addCommentLine(scriptBuilder, "For each item collected in multiple input");

        //Iterate over the multiple input collection
        scriptBuilder.append(inputListToIterate());
        scriptBuilder.append(".each{");
        scriptBuilder.append(System.lineSeparator());

        addCommentBeforeAddToList(scriptBuilder, businessObject);

        //Add new business object based on current element in collection
        scriptBuilder.append(listVarName);
        scriptBuilder.append(".add({ ");
        scriptBuilder.append(iteratorName(businessObject));
        scriptBuilder.append(" ->");
        scriptBuilder.append(System.lineSeparator());

        final String variableName = context.getLocalVariableName();
        //Instantiate the new business object
        delcareVariable(scriptBuilder, variableName);
        scriptBuilder.append(" = ");
        constructor(scriptBuilder, businessObject);
        scriptBuilder.append(System.lineSeparator());

        //Set new business object instance properties
        for (final IPropertyInitializer propertyInitializer : propertyInitializers) {
            initializeProperty(scriptBuilder, propertyInitializer, variableName);
        }

        returnVar(scriptBuilder, variableName);
        scriptBuilder.append(System.lineSeparator());

        scriptBuilder.append("}(it))");
        scriptBuilder.append(System.lineSeparator());
        scriptBuilder.append("}");
        scriptBuilder.append(System.lineSeparator());
    }

    protected void addCommentBeforeAddToList(final StringBuilder scriptBuilder, final BusinessObject businessObject) {
        addCommentLine(scriptBuilder,
                String.format("Add a new composed %s instance", BDMQueryUtil.getSimpleBusinessObjectName(businessObject.getQualifiedName())));
    }

    protected String inputListToIterate() {
        return Joiner.on(".").join(toAncestorNameList().apply(contractInput));
    }

    protected String iteratorName(final BusinessObject bo) {
        return "current" + BDMQueryUtil.getSimpleBusinessObjectName(bo.getQualifiedName()) + "Input";
    }

    protected void appendExistingBusinessObjects(final StringBuilder scriptBuilder, final String listVarName) {
        final String refName = context.getRef(getParent() != null ? getParent().getContext() : null);
        addCommentLine(scriptBuilder, String.format("Uncomment line below to append existing %s", getLast(Splitter.on(".").split(refName))));
        scriptBuilder.append("//");
        scriptBuilder.append(listVarName);
        scriptBuilder.append(".addAll(");
        scriptBuilder.append(refName);
        scriptBuilder.append(")");
        scriptBuilder.append(System.lineSeparator());
    }

    @Override
    protected boolean checkExistence() {
        return false;
    }

    @Override
    protected void constructor(final StringBuilder scriptBuilder, final BusinessObject businessObject) {
        scriptBuilder.append("new ");
        scriptBuilder.append(businessObject.getQualifiedName());
        scriptBuilder.append("()");
    }

}
