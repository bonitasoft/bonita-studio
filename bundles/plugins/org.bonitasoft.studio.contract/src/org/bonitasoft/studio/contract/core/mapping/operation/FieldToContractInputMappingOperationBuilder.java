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
package org.bonitasoft.studio.contract.core.mapping.operation;

import static com.google.common.collect.Lists.newArrayList;
import static org.bonitasoft.studio.common.functions.ContractInputFunctions.toAncestorNameList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.expression.editor.ExpressionEditorService;
import org.bonitasoft.studio.expression.editor.filter.ExpressionReturnTypeFilter;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.groovy.GroovyCompilationUnitFactory;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.groovy.ui.job.ComputeScriptDependenciesJob;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ContractInput;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.jdt.core.JavaModelException;

import com.google.common.base.Joiner;

@Creatable
public class FieldToContractInputMappingOperationBuilder {

    private final ExpressionReturnTypeFilter expressionReturnTypeFilter;
    private final RepositoryAccessor repositoryAccessor;
    private final ExpressionEditorService expressionEditorService;

    @Inject
    public FieldToContractInputMappingOperationBuilder(final ExpressionReturnTypeFilter expressionReturnTypeFilter,
            final RepositoryAccessor repositoryAccessor, final ExpressionEditorService expressionEditorService) {
        this.expressionReturnTypeFilter = expressionReturnTypeFilter;
        this.repositoryAccessor = repositoryAccessor;
        this.expressionEditorService = expressionEditorService;
    }

    public Operation toOperation(final BusinessObjectData data, final FieldToContractInputMapping mapping) throws OperationCreationException {
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        operation.setLeftOperand(ExpressionHelper.createVariableExpression(data));
        operation.setOperator(operator(mapping, data));
        try {
            operation.setRightOperand(rightOperand(mapping, data));
        } catch (final BusinessObjectInstantiationException | JavaModelException e) {
            throw new OperationCreationException("Failed to create right operand expression", e);
        }
        if (!typesMatches(operation)) {
            throw new OperationCreationException(String.format("Expected setter parameter type (%s) does not match expression type (%s)"
                    , operation.getOperator().getInputTypes().get(0), operation.getRightOperand().getReturnType()));
        }
        return operation;
    }

    private boolean typesMatches(final Operation operation) {
        final String expectedType = operation.getOperator().getInputTypes().get(0);
        final String returnType = operation.getRightOperand().getReturnType();
        return expressionReturnTypeFilter.compatibleReturnTypes(expectedType, returnType);
    }

    private Expression rightOperand(final FieldToContractInputMapping mapping, final BusinessObjectData data)
            throws BusinessObjectInstantiationException, JavaModelException {
        final ContractInput contractInput = mapping.getContractInput();
        final ContractInput rootContractInput = rootContractInput(contractInput);
        if (Objects.equals(rootContractInput, mapping.getContractInput()) && !rootContractInput.isMultiple()) {
            return ExpressionHelper.createContractInputExpression(mapping.getContractInput());
        } else {
            final MappingOperationScriptBuilder mappingOperationScriptBuilder = mapping.getScriptBuilder(data);
            final Expression scriptExpression = ExpressionHelper.createGroovyScriptExpression(mappingOperationScriptBuilder.toScript(),
                    mapping.getFieldType());
            addScriptDependencies(mappingOperationScriptBuilder, mapping.getContractInput(), data, scriptExpression);
            scriptExpression.setName(Joiner.on(".").join(toAncestorNameList().apply(contractInput)));
            return scriptExpression;
        }
    }

    private void addScriptDependencies(final MappingOperationScriptBuilder scriptBuilder, final ContractInput contractInput, final BusinessObjectData data,
            final Expression groovyScriptExpression) throws JavaModelException {
        groovyScriptExpression.getReferencedElements().add(
                ExpressionHelper.createDependencyFromEObject(rootContractInput(contractInput)));
        if (scriptBuilder.needsDataDependency()) {
            groovyScriptExpression.getReferencedElements().add(
                    ExpressionHelper.createDependencyFromEObject(data));
        }
        final ComputeScriptDependenciesJob job = new ComputeScriptDependenciesJob(groovyCompilationUnit(groovyScriptExpression));
        job.setNodes(availableDao());
        job.setContext(ModelHelper.getParentPool(data));
        groovyScriptExpression.getReferencedElements().addAll(job.findDependencies());
    }

    private List<ScriptVariable> availableDao() {
        final List<ScriptVariable> scriptVariables = new ArrayList<ScriptVariable>();
        final IExpressionProvider daoExpressionProvider = expressionEditorService.getExpressionProvider(ExpressionConstants.DAO_TYPE);
        if (daoExpressionProvider != null) {
            final List<Expression> expressions = newArrayList(daoExpressionProvider.getExpressions(null));
            for (final Expression e : expressions) {
                scriptVariables.add(new ScriptVariable(e.getName(), e.getReturnType()));
            }
        }
        return scriptVariables;
    }

    private GroovyCompilationUnit groovyCompilationUnit(final Expression groovyScriptExpression) throws JavaModelException {
        return (GroovyCompilationUnit) new GroovyCompilationUnitFactory(repositoryAccessor).newCompilationUnit(groovyScriptExpression.getContent(),
                Repository.NULL_PROGRESS_MONITOR);
    }

    private ContractInput rootContractInput(final ContractInput contractInput) {
        ContractInput current = contractInput;
        while (current.eContainer() instanceof ContractInput) {
            current = (ContractInput) current.eContainer();
        }
        return current;
    }

    private Operator operator(final FieldToContractInputMapping mapping, final BusinessObjectData data) {
        final Operator operator = ExpressionFactory.eINSTANCE.createOperator();
        operator.setType(ExpressionConstants.JAVA_METHOD_OPERATOR);
        operator.getInputTypes().add(data.isMultiple() ? Collection.class.getName() : mapping.getFieldType());
        operator.setExpression(data.isMultiple() ? "addAll" : mapping.getSetterName());
        return operator;
    }

}
