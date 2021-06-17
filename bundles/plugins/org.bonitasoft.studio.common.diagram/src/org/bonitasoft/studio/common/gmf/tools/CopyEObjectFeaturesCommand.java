/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.gmf.tools;

import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.ANDGateway;
import org.bonitasoft.studio.model.process.CatchLinkEvent;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.InclusiveGateway;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.ThrowLinkEvent;
import org.bonitasoft.studio.model.process.XORGateway;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * 
 * This {@link Command} copies or moves all features value from a model element to another.
 * It basically sets a target element with the same values for attributes and references
 * as a source element.
 * The source element is deleted at the end of this operation.
 * Both element can be of different type, then only shared feature (coming from common
 * supertypes) will be copied.
 * @author Mickael Istria
 *
 */
public final class CopyEObjectFeaturesCommand extends AbstractTransactionalCommand {
    /**
     * 
     */
    private final EObject sourceElement;
    /**
     * 
     */
    private final EObject targetElement;

    /**
     * @param sourceElement The element that is to be replaced. Removed once command is executed
     * @param targetElement The element that will take its place
     */
    public CopyEObjectFeaturesCommand(TransactionalEditingDomain domain, EObject sourceElement, EObject targetElement) {
        super(domain,"Copy EObject Features", getWorkspaceFiles(sourceElement));//$NON-NLS-1$
        this.sourceElement = sourceElement;
        this.targetElement = targetElement;
    }


    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
            IAdaptable info) throws ExecutionException {
        copyFeatures(sourceElement, targetElement);

        if(targetElement instanceof ANDGateway && (sourceElement instanceof XORGateway || sourceElement instanceof InclusiveGateway)){
            ANDGateway andGateway =  (ANDGateway) targetElement;

            for(Connection connection : andGateway.getOutgoing()){
                if(connection instanceof SequenceFlow){
                    ((SequenceFlow) connection).setIsDefault(false);
                    Expression defaultExpression = ExpressionFactory.eINSTANCE.createExpression();
                    defaultExpression.setReturnType(Boolean.class.getName());
                    defaultExpression.setReturnTypeFixed(true);
                    ((SequenceFlow) connection).setCondition(defaultExpression);
                }
            }
            for(Connection connection : andGateway.getIncoming()){
                if(connection instanceof SequenceFlow){
                    ((SequenceFlow) connection).setIsDefault(false);
                    Expression defaultExpression = ExpressionFactory.eINSTANCE.createExpression();
                    defaultExpression.setReturnType(Boolean.class.getName());
                    defaultExpression.setReturnTypeFixed(true);
                    ((SequenceFlow) connection).setCondition(defaultExpression);
                }
            }
        }
        
        if(sourceElement.eClass().equals(ProcessPackage.Literals.THROW_LINK_EVENT)){
        	((ThrowLinkEvent)sourceElement).setTo(null);
        }else if(sourceElement.eClass().equals(ProcessPackage.Literals.CATCH_LINK_EVENT)){
        	((CatchLinkEvent)sourceElement).getFrom().clear();
        }
        return CommandResult.newOKCommandResult(targetElement);
    }


    /**
     * @param targetElement
     * @param sourceElement
     * 
     */
    public static void copyFeatures(EObject sourceElement, EObject targetElement) {
        List<EStructuralFeature> targetFeatures = targetElement.eClass().getEAllStructuralFeatures();
        for (EStructuralFeature feature : sourceElement.eClass().getEAllStructuralFeatures()) {
            if (targetFeatures.contains(feature)) {
                if (feature instanceof EReference) {
                    EReference reference = (EReference)feature;
                    Object value = sourceElement.eGet(reference);
                    if (value instanceof EList<?>) {
                        EList<EObject> sourceList = (EList<EObject>)value;
                        EList<EObject> destList = (EList<EObject>)targetElement.eGet(feature);
                        while (!sourceList.isEmpty()) {
                            EObject referencedItem = sourceList.get(0);
                            sourceList.remove(referencedItem);
                            if (reference.getEOpposite() != null) {
                                referencedItem.eSet(reference.getEOpposite(), targetElement);
                            } else {
                                destList.add(referencedItem);
                            }
                        }
                    } else {
                        targetElement.eSet(feature, sourceElement.eGet(feature));
                    }
                } else if (feature instanceof EAttribute) {
                    targetElement.eSet(feature, sourceElement.eGet(feature));
                }
            }else{//unset other features
                if(feature.isMany()){
                    sourceElement.eSet(feature, Collections.emptyList());
                }else{
                    sourceElement.eSet(feature, feature.getDefaultValue());
                }
            }
        }


        EObject container = sourceElement.eContainer();
        if (container != null) {
            Object parentFeatureValue = container.eGet(sourceElement.eContainingFeature());
            if (parentFeatureValue instanceof EList<?>) {
                //			int index = ((EList) parentFeatureValue).indexOf(sourceElement);
                ((EList<?>) parentFeatureValue).remove(sourceElement);
                // Add the new element at the same place than the older one
                // ((EList) parentFeatureValue).set(((EList)
                // parentFeatureValue).indexOf(sourceElement), targetElement);
                ((EList) parentFeatureValue).add(/* index, */targetElement);
            } else {
                container.eSet(sourceElement.eContainingFeature(), targetElement);
            }
        }
    }

}