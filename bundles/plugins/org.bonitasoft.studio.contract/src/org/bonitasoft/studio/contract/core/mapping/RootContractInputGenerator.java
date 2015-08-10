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
package org.bonitasoft.studio.contract.core.mapping;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.RelationField.Type;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.contract.core.mapping.expression.FieldToContractInputMappingExpressionBuilder;
import org.bonitasoft.studio.contract.core.mapping.operation.BusinessObjectInstantiationException;
import org.bonitasoft.studio.contract.core.mapping.operation.FieldToContractInputMappingOperationBuilder;
import org.bonitasoft.studio.contract.core.mapping.operation.OperationCreationException;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.jdt.core.JavaModelException;

public class RootContractInputGenerator {

    private final String rootContractInputName;
    private List<? extends FieldToContractInputMapping> children = new ArrayList<FieldToContractInputMapping>();
    private final List<Operation> mappingOperations = new ArrayList<Operation>();
    private ContractInput contractInput;
    private final RepositoryAccessor repositoryAccessor;
    private final FieldToContractInputMappingOperationBuilder operationBuilder;
    private final FieldToContractInputMappingExpressionBuilder expressionBuilder;
    private Expression initialValueExpression;
    private boolean allAttributesGenerated = true;

    public RootContractInputGenerator(final String rootContractInputName, final List<? extends FieldToContractInputMapping> children,
            final RepositoryAccessor repositoryAccessor, final FieldToContractInputMappingOperationBuilder operationBuilder,
            final FieldToContractInputMappingExpressionBuilder expressionBuilder) {
        this.rootContractInputName = rootContractInputName;
        this.children = children;
        this.repositoryAccessor = repositoryAccessor;
        this.operationBuilder = operationBuilder;
        this.expressionBuilder = expressionBuilder;
    }

    public void build(final BusinessObjectData data) throws OperationCreationException {
        contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setName(rootContractInputName);
        contractInput.setType(ContractInputType.COMPLEX);
        contractInput.setMultiple(data.isMultiple());
        for (final FieldToContractInputMapping mapping : children) {
            if (mapping.isGenerated()) {
                mapping.toContractInput(contractInput);
                if (!contractInput.isMultiple()) {
                    mappingOperations.add(operationBuilder.toOperation(data, mapping));
                }
            } else {
                allAttributesGenerated = false;
            }
        }
        if (contractInput.isMultiple()) {
            mappingOperations.add(operationBuilder.toOperation(data, createParentMapping(data, rootContractInputName)));
        }
        try {
            initialValueExpression = expressionBuilder.toExpression(data, createParentMapping(data, rootContractInputName));
        } catch (JavaModelException | BusinessObjectInstantiationException e) {
            throw new OperationCreationException("Failed to create initial value expression", e);
        }
    }

    private RelationFieldToContractInputMapping createParentMapping(final BusinessObjectData data, final String inputName) {
        final RelationField relationField = new RelationField();
        relationField.setCollection(data.isMultiple());
        relationField.setName(inputName);
        relationField.setType(Type.COMPOSITION);
        relationField.setReference(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class).getBusinessObjectByQualifiedName(
                data.getClassName()));
        final RelationFieldToContractInputMapping mapping = new RelationFieldToContractInputMapping(relationField);
        for (final FieldToContractInputMapping child : children) {
            mapping.addChild(child);
        }
        return mapping;
    }

    public ContractInput getRootContractInput() {
        return contractInput;
    }

    public List<Operation> getMappingOperations() {
        return mappingOperations;
    }

    public Expression getInitialValueExpression() {
        return initialValueExpression;
    }

    /**
     * @return the allAttributesGenerated
     */
    public boolean isAllAttributesGenerated() {
        return allAttributesGenerated;
    }
}
