/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.migration.custom.migration.multiinstance;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.migration.utils.StringToExpressionConverter;
import org.bonitasoft.studio.model.process.MultiInstanceType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;


/**
 * @author Romain Bioteau
 *
 */
public class MultiInstanceMigrator {

    private final Instance sourceInstance;
    private boolean isLoop;
    private boolean testBefore;
    private boolean useCardinality;
    private boolean isMulti;
    private boolean isSequential;
    private Instance cardinalityExpression;
    private Instance iteratorExpression;
    private Instance collectionDataToMultiInstantiate;
    private Instance outputData;
    private Instance listDataContainingOutputResults;
    private Instance completionCondition;
    private Instance loopCondition;
    private Instance loopMaximum;

    public MultiInstanceMigrator(final Instance sourceInstance) {
        this.sourceInstance = sourceInstance;

    }

    public void save(final Model model, final Metamodel metamodel) {
        isLoop = isStandardLoop();
        testBefore = isTestBefore();
        useCardinality = useCardinality();
        isMulti = isMultiInstantiated();
        isSequential = isSequential();
        cardinalityExpression = getCardinalityExpression(model);
        iteratorExpression = getIteratorExpression(model);
        collectionDataToMultiInstantiate = getInputList();
        outputData = getOutputData();
        listDataContainingOutputResults = getOutputList();
        completionCondition = getCompletionCondition(model);
        loopCondition = getLoopCondition(model);
        loopMaximum = getLoopMaximum(model);

    }

    public void migrate(final Instance targetInstance, final Model model, final Metamodel metamodel) {
        targetInstance.set("type", getType(metamodel));
        targetInstance.set("testBefore", testBefore);
        targetInstance.set("loopCondition", loopCondition);
        targetInstance.set("loopMaximum", loopMaximum);
        targetInstance.set("useCardinality", !useCardinality);
        targetInstance.set("useCardinality", useCardinality);
        targetInstance.set("cardinalityExpression", cardinalityExpression);
        targetInstance.set("collectionDataToMultiInstantiate", collectionDataToMultiInstantiate);
        targetInstance.set("iteratorExpression", iteratorExpression);
        targetInstance.set("outputData", outputData);
        targetInstance.set("listDataContainingOutputResults", listDataContainingOutputResults);
        targetInstance.set("completionCondition", completionCondition);
        targetInstance.set("storeOutput", listDataContainingOutputResults != null || outputData != null);
    }

    private Instance getOutputList() {
        final Instance multiInstantiationInstance = sourceInstance.get("multiInstantiation");
        if (multiInstantiationInstance != null) {
            return multiInstantiationInstance.get("listDataContainingOutputResults");
        }
        return null;
    }

    private Instance getOutputData() {
        final Instance multiInstantiationInstance = sourceInstance.get("multiInstantiation");
        if (multiInstantiationInstance != null) {
            return multiInstantiationInstance.get("outputData");
        }
        return null;
    }

    private Instance getInputList() {
        final Instance multiInstantiationInstance = sourceInstance.get("multiInstantiation");
        if (multiInstantiationInstance != null) {
            return multiInstantiationInstance.get("collectionDataToMultiInstantiate");
        }
        return null;
    }

    private Instance getIteratorExpression(final Model model) {
        final Instance multiInstantiationInstance = sourceInstance.get("multiInstantiation");
        if (multiInstantiationInstance != null && multiInstantiationInstance.get("inputData") != null) {
            final Instance dataInstance = multiInstantiationInstance.get("inputData");
            final String dataName = (String) dataInstance.get("name");
            final Instance iteratorExpression = StringToExpressionConverter.createExpressionInstance(model,
                    dataName, dataName, StringToExpressionConverter.getDataReturnType(dataInstance),
                    ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE, false);
            // model.delete(dataInstance);
            return iteratorExpression;
        }
        return StringToExpressionConverter.createExpressionInstance(model, "multiInstanceIterator", "multiInstanceIterator", Object.class.getName(),
                ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE,
                false);
    }

    private Instance getCompletionCondition(final Model model) {
        final Instance multiInstantiationInstance = sourceInstance.get("multiInstantiation");
        Instance expressionInstance = null;
        if (multiInstantiationInstance != null) {
            final Instance originalInstance = multiInstantiationInstance.get("completionCondition");
            if (originalInstance != null) {
                expressionInstance = originalInstance.copy();
                model.delete(originalInstance);
            }
        }
        if (expressionInstance == null) {
            expressionInstance = StringToExpressionConverter.createExpressionInstance(model,
                    "", "", Boolean.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
        }
        return expressionInstance;
    }

    private Instance getCardinalityExpression(final Model model) {
        final Instance multiInstantiationInstance = sourceInstance.get("multiInstantiation");
        Instance expressionInstance = null;
        if (multiInstantiationInstance != null) {
            final Instance originalInstance = multiInstantiationInstance.get("cardinality");
            if (originalInstance != null) {
                expressionInstance = originalInstance.copy();
                model.delete(originalInstance);
            }
        }
        if (expressionInstance == null) {
            expressionInstance = StringToExpressionConverter.createExpressionInstance(model,
                    "", "", Integer.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
        }
        return expressionInstance;
    }

    private boolean useCardinality() {
        final Instance multiInstantiationInstance = sourceInstance.get("multiInstantiation");
        if (multiInstantiationInstance != null) {
            return multiInstantiationInstance.get("useCardinality");
        }
        return true;//default is true
    }

    private Instance getLoopMaximum(final Model model) {
        Instance expressionInstance = sourceInstance.get("loopMaximum");
        if (expressionInstance == null) {
            expressionInstance = StringToExpressionConverter.createExpressionInstance(model,
                    "", "", Integer.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
        }
        return expressionInstance;
    }

    private Instance getLoopCondition(final Model model) {
        Instance expressionInstance = sourceInstance.get("loopCondition");
        if (expressionInstance == null) {
            expressionInstance = StringToExpressionConverter.createExpressionInstance(model,
                    "", "", Boolean.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
        }
        return expressionInstance;
    }

    private boolean isTestBefore() {
        return sourceInstance.get("testBefore");
    }

    private Object getType(final Metamodel metamodel) {
        final EEnum multiInstanceType = metamodel.getEEnum("process.MultiInstanceType");
        if (isLoop) {
            return multiInstanceType.getEEnumLiteral(MultiInstanceType.STANDARD.name());
        }else if( isParallelMultiInstantiation()){
            return multiInstanceType.getEEnumLiteral(MultiInstanceType.PARALLEL.name());
        }else if(isSequentialMultiInstantiation()){
            return multiInstanceType.getEEnumLiteral(MultiInstanceType.SEQUENTIAL.name());
        }else{
            return multiInstanceType.getEEnumLiteral(MultiInstanceType.NONE.name());
        }
    }

    private boolean isParallelMultiInstantiation(){
        return isMulti && !isSequential;
    }

    private boolean isSequential() {
        final Instance multiInstantiationInstance = sourceInstance.get("multiInstantiation");
        if (multiInstantiationInstance != null) {
            return multiInstantiationInstance.get("sequential");
        }
        return false;
    }

    private Boolean isMultiInstantiated() {
        return (Boolean) sourceInstance.get("isMultiInstance") && sourceInstance.get("multiInstantiation") != null;
    }

    private boolean isSequentialMultiInstantiation(){
        return isMulti && isSequential;
    }

    private boolean isStandardLoop() {
        final Boolean isLoop = sourceInstance.get("isLoop");
        return isLoop != null && isLoop;
    }

}