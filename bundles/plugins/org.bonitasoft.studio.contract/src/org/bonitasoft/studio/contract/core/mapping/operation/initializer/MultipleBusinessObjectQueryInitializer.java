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

import static com.google.common.collect.Iterables.tryFind;
import static org.bonitasoft.studio.common.functions.ContractInputFunctions.toAncestorNameList;
import static org.bonitasoft.studio.common.predicate.ContractInputPredicates.withContractInputName;

import java.util.Objects;

import org.bonitasoft.engine.bdm.BDMSimpleNameProvider;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.contract.core.mapping.operation.BusinessObjectInstantiationException;
import org.bonitasoft.studio.model.process.ContractInput;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;

public class MultipleBusinessObjectQueryInitializer extends NewBusinessObjectListInitializer implements IPropertyInitializer {

    private final ContractInput persistenceIdInput;
    private final BusinessObject businessObject;

    public MultipleBusinessObjectQueryInitializer(final BusinessObject businessObject, final InitializerContext context) {
        super(context);
        persistenceIdInput = persistenceIdInput(context.getContractInput());
        this.businessObject = businessObject;
    }

    @Override
    protected void constructor(final StringBuilder scriptBuilder, final BusinessObject businessObject) {
        daoQuery(scriptBuilder, businessObject);
    }

    @Override
    protected void initializeProperty(final StringBuilder scriptBuilder, final IPropertyInitializer propertyInitializer, final String varName)
            throws BusinessObjectInstantiationException {
        if (!isPersistenceIdInitializer(propertyInitializer)) {
            super.initializeProperty(scriptBuilder, propertyInitializer, varName);
        }
    }

    private boolean isPersistenceIdInitializer(final IPropertyInitializer propertyInitializer) {
        return propertyInitializer instanceof SimpleFieldPropertyInitializer && Objects.equals(propertyInitializer.getPropertyName(), Field.PERSISTENCE_ID);
    }

    @Override
    protected String inputListToIterate() {
        return Joiner.on(".").join(toAncestorNameList().apply((ContractInput) persistenceIdInput.eContainer()));
    }

    @Override
    protected void addCommentBeforeAddToList(final StringBuilder scriptBuilder, final BusinessObject businessObject) {
        addCommentLine(scriptBuilder,
                String.format("Add aggregated %s instance", BDMSimpleNameProvider.getSimpleBusinessObjectName(businessObject.getQualifiedName())));
    }

    protected void daoQuery(final StringBuilder scriptBuilder, final BusinessObject bo) {
        final SimpleField peristenceIdField = new SimpleField();
        peristenceIdField.setType(FieldType.LONG);
        peristenceIdField.setName(Field.PERSISTENCE_ID);
        final SimpleFieldPropertyInitializer persistenceIdInitializer = new SimpleFieldPropertyInitializer(businessObject,
                peristenceIdField, persistenceIdInput);
        scriptBuilder.append(daoName(bo));
        scriptBuilder.append(".findByPersistenceId(");
        scriptBuilder.append(persistenceIdInitializer.getInitialValue());
        scriptBuilder.append(")");
    }

    private String daoName(final BusinessObject bo) {
        return uncapitalizeFirst(BDMSimpleNameProvider.getSimpleBusinessObjectName(bo.getQualifiedName())) + "DAO";
    }

    protected ContractInput persistenceIdInput(final ContractInput contractInput) {
        if (withContractInputName(Field.PERSISTENCE_ID).apply(contractInput)) {
            return contractInput;
        }
        final Optional<ContractInput> persistenceIdInput = tryFind(contractInput.getInputs(), withContractInputName(Field.PERSISTENCE_ID));
        if (persistenceIdInput.isPresent()) {
            return persistenceIdInput.get();
        }
        throw new IllegalStateException(String.format("persistenceId input not found in %s", contractInput.getName()));
    }
}
