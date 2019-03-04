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

import java.util.Objects;

import org.bonitasoft.engine.bdm.BDMSimpleNameProvider;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMappingFactory;
import org.bonitasoft.studio.contract.core.mapping.operation.BusinessObjectInstantiationException;
import org.bonitasoft.studio.model.process.ContractInput;

import com.google.common.base.Joiner;

public class NewBusinessObjectListInitializer extends AbstractBusinessObjectInitializer {

    protected ContractInput contractInput;

    public NewBusinessObjectListInitializer(final InitializerContext context) {
        super(context);
        contractInput = context.getContractInput();
    }

    @Override
    public String getInitialValue() throws BusinessObjectInstantiationException {
        final BusinessObject businessObject = context.getField().getReference();
        final StringBuilder scriptBuilder = new StringBuilder();
        final String listVarName = context.getLocalListVariableName();
        delcareVariable(scriptBuilder, listVarName);
        scriptBuilder.append(" = ");
        listConstructor(scriptBuilder, businessObject);

        forEach(scriptBuilder, businessObject, listVarName);
        returnListVar(scriptBuilder, listVarName);
        return scriptBuilder.toString();
    }

    private void listConstructor(final StringBuilder scriptBuilder, final BusinessObject businessObject) {
        scriptBuilder.append("[]");
        scriptBuilder.append(System.lineSeparator());
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
            if (!Objects.equals(propertyInitializer.getPropertyName(),
                    FieldToContractInputMappingFactory.PERSISTENCE_ID_STRING_FIELD_NAME)) {
                initializeProperty(scriptBuilder, propertyInitializer, variableName);
            }
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
                String.format("Add a new composed %s instance",
                        BDMSimpleNameProvider.getSimpleBusinessObjectName(businessObject.getQualifiedName())));
    }

    protected String inputListToIterate() {
        return Joiner.on(".").join(toAncestorNameList().apply(contractInput));
    }

    protected String iteratorName(final BusinessObject bo) {
        return "current" + BDMSimpleNameProvider.getSimpleBusinessObjectName(bo.getQualifiedName()) + "Input";
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
