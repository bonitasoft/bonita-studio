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
import static org.bonitasoft.studio.common.predicate.ContractInputPredicates.withContractInputName;

import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.engine.bdm.BDMSimpleNameProvider;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMappingFactory;
import org.bonitasoft.studio.contract.core.mapping.operation.BusinessObjectInstantiationException;
import org.bonitasoft.studio.model.process.ContractInput;

public class MultipleAggregationBusinessObjectQueryInitializer extends NewBusinessObjectListInitializer {

    protected ContractInput persistenceIdInput;
    protected BusinessObject businessObject;

    public MultipleAggregationBusinessObjectQueryInitializer(final BusinessObject businessObject,
            final InitializerContext context) {
        super(context);
        persistenceIdInput = persistenceIdInput(context.getContractInput());
        this.businessObject = businessObject;
    }

    @Override
    protected void constructor(final StringBuilder scriptBuilder, final BusinessObject businessObject) {
        daoQuery(scriptBuilder, businessObject);
    }

    @Override
    protected void initializeProperty(final StringBuilder scriptBuilder, final IPropertyInitializer propertyInitializer,
            final String varName)
            throws BusinessObjectInstantiationException {
        if (!isPersistenceIdInitializer(propertyInitializer)) {
            super.initializeProperty(scriptBuilder, propertyInitializer, varName);
        }
    }

    private boolean isPersistenceIdInitializer(final IPropertyInitializer propertyInitializer) {
        return propertyInitializer instanceof SimpleFieldPropertyInitializer
                && Objects.equals(propertyInitializer.getPropertyName(), Field.PERSISTENCE_ID);
    }

    @Override
    protected String inputListToIterate() {
        return toAncestorNameList().apply((ContractInput) persistenceIdInput.eContainer()).stream()
                .collect(Collectors.joining("?."));
    }

    @Override
    protected void addCommentBeforeAddToList(final StringBuilder scriptBuilder, final BusinessObject businessObject) {
        addCommentLine(scriptBuilder,
                String.format("Add %s instance", businessObject.getSimpleName()));
    }

    protected void daoQuery(final StringBuilder scriptBuilder, final BusinessObject bo) {
        final SimpleField peristenceIdField = new SimpleField();
        peristenceIdField.setType(FieldType.LONG);
        peristenceIdField.setName(Field.PERSISTENCE_ID);
        final SimpleFieldPropertyInitializer persistenceIdInitializer = new SimpleFieldPropertyInitializer(
                businessObject,
                peristenceIdField, persistenceIdInput);
        scriptBuilder.append(daoName(bo));
        scriptBuilder.append(".findByPersistenceId(");
        scriptBuilder.append(persistenceIdInitializer.getInitialValue());
        scriptBuilder.append(")");
        validateQueryResult(scriptBuilder, persistenceIdInitializer);
    }

    private void validateQueryResult(StringBuilder scriptBuilder,
            SimpleFieldPropertyInitializer persistenceIdInitializer) {
        String localVariableName = context.getLocalVariableName();
        scriptBuilder.append(System.lineSeparator());
        scriptBuilder.append(String.format("if(!%s) {" + System.lineSeparator(), localVariableName));
        scriptBuilder.append(String.format(
                "throw new IllegalArgumentException(\"The aggregated reference of type `%s` with the persistence id \" + %s + \" has not been found.\")"
                        + System.lineSeparator(),
                context.getField().getReference().getSimpleName(), persistenceIdInitializer.getInitialValue()));
        scriptBuilder.append("}");
    }

    protected String daoName(BusinessObject bo) {
        return uncapitalizeFirst(BDMSimpleNameProvider.getSimpleBusinessObjectName(bo.getQualifiedName())) + "DAO";
    }

    protected ContractInput persistenceIdInput(final ContractInput contractInput) {
        if (withContractInputName(FieldToContractInputMappingFactory.PERSISTENCE_ID_STRING_FIELD_NAME)
                .test(contractInput)) {
            return contractInput;
        }
        return contractInput.getInputs().stream()
                .filter(withContractInputName(FieldToContractInputMappingFactory.PERSISTENCE_ID_STRING_FIELD_NAME))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        String.format("persistenceId_string input not found in %s", contractInput.getName())));
    }
}
