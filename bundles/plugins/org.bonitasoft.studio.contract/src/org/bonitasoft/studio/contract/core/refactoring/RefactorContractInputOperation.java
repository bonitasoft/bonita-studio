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
package org.bonitasoft.studio.contract.core.refactoring;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.Objects.requireNonNull;
import static org.bonitasoft.studio.common.emf.tools.ModelHelper.getAllElementOfTypeIn;
import static org.bonitasoft.studio.common.predicate.ExpressionPredicates.withExpressionType;

import java.util.Collection;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.EMFModelUpdater;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.refactoring.core.AbstractRefactorOperation;
import org.bonitasoft.studio.refactoring.core.RefactorPair;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.bonitasoft.studio.refactoring.core.script.IScriptRefactoringOperationFactory;
import org.bonitasoft.studio.refactoring.core.script.ScriptContainer;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

public class RefactorContractInputOperation
        extends AbstractRefactorOperation<ContractInput, ContractInput, ContractInputRefactorPair> {

    private final ContractContainer container;
    private final IScriptRefactoringOperationFactory scriptRefactorOperationFactory;

    public RefactorContractInputOperation(final ContractContainer container,
            final IScriptRefactoringOperationFactory scriptRefactorOperationFactory,
            final RefactoringOperationType operationType) {
        super(operationType);
        this.container = requireNonNull(container);
        this.scriptRefactorOperationFactory = scriptRefactorOperationFactory;
    }

    @Override
    protected CompoundCommand doBuildCompoundCommand(final CompoundCommand cc,
            final IProgressMonitor monitor) {
        updateContractInputExpressions(cc);
        return cc;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.refactoring.core.AbstractRefactorOperation#shouldUpdateReferencesInScripts(org.bonitasoft.studio
     * .refactoring.core.RefactorPair)
     */
    @Override
    protected boolean shouldUpdateReferencesInScripts(RefactorPair<ContractInput, ContractInput> pairRefactor) {
        return super.shouldUpdateReferencesInScripts(pairRefactor) || hasContractInputTypeChanged(pairRefactor);
    }

    private boolean hasContractInputTypeChanged(RefactorPair<ContractInput, ContractInput> pairRefactor) {
        return pairRefactor.getOldValue().getType() != pairRefactor.getNewValue().getType();
    }

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.refactoring.core.AbstractRefactorOperation#allScriptWithReferencedElement(org.bonitasoft.studio.
     * refactoring.core.RefactorPair)
     */
    @Override
    protected Set<ScriptContainer<?>> allScriptWithReferencedElement(
            final RefactorPair<ContractInput, ContractInput> pairRefactor) {
        final Set<ScriptContainer<?>> allScriptWithReferencedElement = newHashSet(
                filter(super.allScriptWithReferencedElement(pairRefactor), inContractContainer(
                        ModelHelper.getFirstContainerOfType(pairRefactor.getOldValue(), ContractContainer.class))));
        allScriptWithReferencedElement.addAll(constraintExpressionsReferencing(
                ModelHelper.getFirstContainerOfType(pairRefactor.getOldValue(), Contract.class),
                pairRefactor.getOldValue()));
        return allScriptWithReferencedElement;
    }

    private Predicate<ScriptContainer<?>> inContractContainer(final ContractContainer contractContainer) {
        return new Predicate<ScriptContainer<?>>() {

            @Override
            public boolean apply(ScriptContainer<?> sc) {
                return EcoreUtil.equals(ModelHelper.getFirstContainerOfType(sc.getModelElement(), ContractContainer.class),
                        contractContainer);
            }
        };
    }

    private Collection<? extends ScriptContainer<?>> constraintExpressionsReferencing(final Contract contract,
            final ContractInput contractInput) {
        return newHashSet(transform(
                filter(contract.getConstraints(),
                        constraintReferencing(contractInput)),
                toConstraintExpressionContainer()));
    }

    private Function<ContractConstraint, ScriptContainer<?>> toConstraintExpressionContainer() {
        return new Function<ContractConstraint, ScriptContainer<?>>() {

            @Override
            public ScriptContainer<?> apply(final ContractConstraint constraint) {
                return new ConstraintExpressionScriptContainer(constraint, scriptRefactorOperationFactory);
            }
        };
    }

    private Predicate<ContractConstraint> constraintReferencing(final ContractInput contractInput) {
        return new Predicate<ContractConstraint>() {

            @Override
            public boolean apply(final ContractConstraint constraint) {
                return constraint.getInputNames().contains(contractInput.getName());
            }
        };
    }

    private void updateContractInputExpressions(final CompoundCommand cc) {
        for (final Expression exp : filter(getAllElementOfTypeIn(container, Expression.class),
                withExpressionType(ExpressionConstants.CONTRACT_INPUT_TYPE))) {
            for (final ContractInputRefactorPair pairToRefactor : filter(pairsToRefactor, matches(exp))) {
                if (pairToRefactor.getNewValue() != null) {
                    cc.append(SetCommand.create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__NAME,
                            pairToRefactor.getNewValue().getName()));
                    cc.append(SetCommand.create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__CONTENT,
                            pairToRefactor.getNewValue().getName()));
                    cc.append(SetCommand.create(getEditingDomain(), exp, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE,
                            ExpressionHelper.getContractInputReturnType(pairToRefactor.getNewValue())));
                    updateDependencies(cc, pairToRefactor, exp);
                } else {
                    EMFModelUpdater<EObject> updater = new EMFModelUpdater<>().from(exp);
                    updater.editWorkingCopy(ExpressionHelper.createDependencyFromEObject(createDefaultExpression(exp)));
                    cc.append(updater.createUpdateCommand(getEditingDomain()));
                }
            }
        }

    }

    private void updateDependencies(final CompoundCommand cc, final ContractInputRefactorPair pairToRefactor,
            final Expression exp) {
        for (final EObject dependency : exp.getReferencedElements()) {
            if (dependency instanceof ContractInput
                    && ((ContractInput) dependency).getName().equals(pairToRefactor.getOldValueName())) {
                EMFModelUpdater<EObject> updater = new EMFModelUpdater<>().from(dependency);
                updater.editWorkingCopy(ExpressionHelper.createDependencyFromEObject(pairToRefactor.getNewValue()));
                cc.append(updater.createUpdateCommand(getEditingDomain()));
            }
        }
    }

    private Expression createDefaultExpression(final Expression exp) {
        return ExpressionHelper.createConstantExpression("",
                exp.isReturnTypeFixed() ? exp.getReturnType() : String.class.getName());
    }

    private Predicate<ContractInputRefactorPair> matches(final Expression expression) {
        return new Predicate<ContractInputRefactorPair>() {

            @Override
            public boolean apply(final ContractInputRefactorPair refactorPair) {
                return refactorPair.getOldValueName().equals(expression.getName())
                        && EcoreUtil.equals(ModelHelper.getFirstContainerOfType(expression, ContractContainer.class),
                                ModelHelper.getFirstContainerOfType(refactorPair.getOldValue(), ContractContainer.class));
            }
        };
    }

    @Override
    protected ContractInputRefactorPair createRefactorPair(final ContractInput newItem,
            final ContractInput oldItem) {
        return new ContractInputRefactorPair(newItem, oldItem);
    }

    @Override
    protected EObject getContainer(final ContractInput oldValue) {
        return container;
    }

}
