/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.diagram.custom.refactoring;

import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.InputMapping;
import org.bonitasoft.studio.model.process.InputMappingAssignationType;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class BusinessDataMapper {
    
    private IExpressionProvider queryExpressionProvider;
    private IExpressionProvider daoExpressionProvider;
    
    
    public BusinessDataMapper(IExpressionProvider queryExpressionProvider, IExpressionProvider daoExpressionProvider) {
        this.daoExpressionProvider = daoExpressionProvider;
        this.queryExpressionProvider = queryExpressionProvider;
    }


    public void map(BusinessObjectData sourceData, Pool targetProcess, CallActivity callActivity) {
        final BusinessObjectData targetData = (BusinessObjectData) EcoreUtil.copy(sourceData);

        ContractInput contractInput = createTargetContractInput(sourceData);
        targetProcess.getContract().getInputs().add(contractInput);
        
        String className = ((BusinessObjectData) sourceData).getClassName();
        String boName = NamingUtils.getSimpleName(className);
        targetData.setDefaultValue(sourceData.isMultiple() ?  createMultipleDefaultValueExpression(targetData, contractInput, boName) : createDefaultValueExpression(targetData, contractInput, boName));
        targetProcess.getData().add(targetData);

        callActivity.getInputMappings().add(createCallActivityInputMapping(sourceData));
    }


    private InputMapping createCallActivityInputMapping(BusinessObjectData sourceData) {
        final InputMapping inputMapping = ProcessFactory.eINSTANCE.createInputMapping();
        Expression groovyScriptExpression = ExpressionHelper.createGroovyScriptExpression(
                sourceData.getName() + ".persistenceId", 
                sourceData.isMultiple() ? List.class.getName() : Long.class.getName(),
                sourceData.getName() + "_persistenceId()");
        groovyScriptExpression.getReferencedElements()
                .add(ExpressionHelper.createDependencyFromEObject(sourceData));
        inputMapping.setProcessSource(groovyScriptExpression);
        inputMapping.setSubprocessTarget(sourceData.getName() + "_persistenceId");
        inputMapping.setAssignationType(InputMappingAssignationType.CONTRACT_INPUT);
        return inputMapping;
    }


    private Expression createMultipleDefaultValueExpression(BusinessObjectData targetData, 
            ContractInput contractInput,
            String boName) {
        String daoName = Character.toLowerCase(boName.charAt(0)) + boName.substring(1, boName.length())+"DAO";
        Expression daoExpression = daoExpressionProvider.getExpressions(null).stream()
                .filter(expression -> Objects.equals(expression.getName(),daoName))
                .findFirst()
                .orElse(null);
        if(daoExpression != null) {
            Expression script = ExpressionHelper.createGroovyScriptExpression(String.format("%s_persistenceId.collect { %s.findByPersistenceId(it) }",targetData.getName(), daoExpression.getName()), 
                    List.class.getName(),
                    "init_"+targetData.getName());
            script.getReferencedElements().add(daoExpression);
            script.getReferencedElements().add(ExpressionHelper.createContractInputExpression(contractInput));
            return script;
        }
        return null;
    }

    private Expression createDefaultValueExpression(final BusinessObjectData targetData, 
            ContractInput contractInput,
            String boName) {
        Expression findByPersistenceIdQueryExpression = queryExpressionProvider.getExpressions(targetData)
                .stream()
                .filter(expression -> Objects.equals(boName+".findByPersistenceId", expression.getName()))
                .findFirst()
                .orElse(null);
        if (findByPersistenceIdQueryExpression != null) {
            Expression persistenceIdParam = (Expression) findByPersistenceIdQueryExpression.getReferencedElements().get(0);
            persistenceIdParam.getReferencedElements().clear();
            persistenceIdParam.getReferencedElements().add(ExpressionHelper.createContractInputExpression(contractInput));
            return findByPersistenceIdQueryExpression;
        }
        return null;
    }

    private ContractInput createTargetContractInput(BusinessObjectData sourceData) {
        ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setName(sourceData.getName() + "_persistenceId");
        contractInput.setMultiple(sourceData.isMultiple());
        contractInput.setType(ContractInputType.LONG);
        return contractInput;
    }

}
