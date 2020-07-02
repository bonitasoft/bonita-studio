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
package org.bonitasoft.studio.contract.core.mapping.expression;

import static com.google.common.collect.Lists.newArrayList;
import static org.bonitasoft.studio.common.functions.ContractInputFunctions.toAncestorNameList;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.operation.BusinessObjectInstantiationException;
import org.bonitasoft.studio.contract.core.mapping.operation.MappingOperationScriptBuilder;
import org.bonitasoft.studio.expression.editor.ExpressionProviderService;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.groovy.BonitaScriptGroovyCompilationUnit;
import org.bonitasoft.studio.groovy.GroovyCompilationUnitFactory;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.groovy.ui.job.ComputeScriptDependenciesJob;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ContractInput;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.jdt.core.JavaModelException;

import com.google.common.base.Joiner;

@Creatable
public class FieldToContractInputMappingExpressionBuilder {

    private ExpressionProviderService expressionEditorService;
    private RepositoryAccessor repositoryAccessor;

    @Inject
    public FieldToContractInputMappingExpressionBuilder(RepositoryAccessor repositoryAccessor,
            ExpressionProviderService expressionEditorService) {
        this.repositoryAccessor = repositoryAccessor;
        this.expressionEditorService = expressionEditorService;
    }

    public Expression toExpression(BusinessObjectData data, FieldToContractInputMapping mapping, boolean createMode)
            throws BusinessObjectInstantiationException, JavaModelException {
        return toExpression(data, mapping, createMode, false);
    }

    public Expression toExpression(BusinessObjectData data, FieldToContractInputMapping mapping, boolean createMode,
            boolean fromOperation)
            throws BusinessObjectInstantiationException, JavaModelException {
        ContractInput contractInput = mapping.getContractInput();
        MappingOperationScriptBuilder mappingOperationScriptBuilder = mapping.getScriptBuilder(data);
        String script = getScriptText(createMode, mappingOperationScriptBuilder);
        Expression scriptExpression = ExpressionHelper.createGroovyScriptExpression(script, mapping.getFieldType());
        addScriptDependencies(mappingOperationScriptBuilder, mapping.getContractInput(), data, scriptExpression, createMode);
        setGroovyScriptName(scriptExpression, data, contractInput, fromOperation);
        return scriptExpression;
    }

    protected String getScriptText(boolean createMode, MappingOperationScriptBuilder mappingOperationScriptBuilder)
            throws BusinessObjectInstantiationException {
        if (createMode) {
            return mappingOperationScriptBuilder.toInstanciationScript();
        }
        return mappingOperationScriptBuilder.toScript();
    }

    private void setGroovyScriptName(final Expression scriptExpression, final BusinessObjectData data,
            final ContractInput contractInput, boolean fromOperation) {
        if (fromOperation) {
            scriptExpression.setName(Joiner.on(".").join(toAncestorNameList().apply(contractInput)));
        } else {
            final String dataName = data.getName();
            final String nameToUpperCase = dataName.length() > 1
                    ? dataName.substring(0, 1).toUpperCase() + dataName.substring(1) : dataName.toUpperCase();
            scriptExpression.setName("init" + nameToUpperCase + "()");
        }
    }

    private void addScriptDependencies(final MappingOperationScriptBuilder scriptBuilder, final ContractInput contractInput,
            final BusinessObjectData data,
            final Expression groovyScriptExpression, final boolean isOnPool) throws JavaModelException {
        groovyScriptExpression.getReferencedElements().add(
                ExpressionHelper.createDependencyFromEObject(rootContractInput(contractInput)));
        if (scriptBuilder.needsDataDependency() && !isOnPool) {
            groovyScriptExpression.getReferencedElements().add(
                    ExpressionHelper.createDependencyFromEObject(data));
        }
        final BonitaScriptGroovyCompilationUnit groovyCompilationUnit = groovyCompilationUnit(groovyScriptExpression);
        final ComputeScriptDependenciesJob job = new ComputeScriptDependenciesJob(groovyCompilationUnit);
        job.setNodes(availableDao());
        job.getNodes().add(new ScriptVariable(data.getName(), data.getDataType().toString()));
        job.setContext(ModelHelper.getParentPool(data));
        groovyScriptExpression.getReferencedElements().addAll(job.findDependencies());
        groovyCompilationUnit.delete(true, Repository.NULL_PROGRESS_MONITOR);
    }

    private List<ScriptVariable> availableDao() {
        final List<ScriptVariable> scriptVariables = new ArrayList<>();
        final IExpressionProvider daoExpressionProvider = expressionEditorService
                .getExpressionProvider(ExpressionConstants.DAO_TYPE);
        if (daoExpressionProvider != null) {
            final List<Expression> expressions = newArrayList(daoExpressionProvider.getExpressions(null));
            for (final Expression e : expressions) {
                scriptVariables.add(new ScriptVariable(e.getName(), e.getReturnType()));
            }
        }
        return scriptVariables;
    }

    protected BonitaScriptGroovyCompilationUnit groovyCompilationUnit(final Expression groovyScriptExpression)
            throws JavaModelException {
        return (BonitaScriptGroovyCompilationUnit) new GroovyCompilationUnitFactory(repositoryAccessor).newCompilationUnit(
                groovyScriptExpression.getContent(),
                Repository.NULL_PROGRESS_MONITOR);
    }

    private ContractInput rootContractInput(final ContractInput contractInput) {
        ContractInput current = contractInput;
        while (current.eContainer() instanceof ContractInput) {
            current = (ContractInput) current.eContainer();
        }
        return current;
    }

}
