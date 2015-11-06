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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Sets.newHashSet;
import static org.bonitasoft.studio.common.emf.tools.ExpressionHelper.createContractInputExpression;
import static org.bonitasoft.studio.common.emf.tools.ModelHelper.getAllElementOfTypeIn;
import static org.bonitasoft.studio.common.predicate.ExpressionPredicates.withExpressionType;

import java.util.Collection;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.refactoring.core.AbstractRefactorOperation;
import org.bonitasoft.studio.refactoring.core.RefactorPair;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.bonitasoft.studio.refactoring.core.script.IScriptRefactoringOperationFactory;
import org.bonitasoft.studio.refactoring.core.script.ScriptContainer;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

public class RefactorContractInputOperation extends AbstractRefactorOperation<ContractInput, ContractInput, ContractInputRefactorPair> {

    private final ContractContainer container;
    private final IScriptRefactoringOperationFactory scriptRefactorOperationFactory;

    public RefactorContractInputOperation(final ContractContainer container, final IScriptRefactoringOperationFactory scriptRefactorOperationFactory,
            final RefactoringOperationType operationType) {
        super(operationType);
        checkArgument(container != null);
        this.container = container;
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
     * @see org.bonitasoft.studio.refactoring.core.AbstractRefactorOperation#allScriptWithReferencedElement(org.bonitasoft.studio.refactoring.core.RefactorPair)
     */
    @Override
    protected Set<ScriptContainer<?>> allScriptWithReferencedElement(final RefactorPair<ContractInput, ContractInput> pairRefactor) {
        final Set<ScriptContainer<?>> allScriptWithReferencedElement = super.allScriptWithReferencedElement(pairRefactor, container instanceof Pool);
        allScriptWithReferencedElement.addAll(constraintExpressionsReferencing(ModelHelper.getFirstContainerOfType(pairRefactor.getOldValue(), Contract.class),
                pairRefactor.getOldValue()));
        return allScriptWithReferencedElement;
    }

    @Override
    protected void addAllScriptExpressionsSetToRefactor(final Set<ScriptContainer<?>> scriptExpressionsSetToRefactor,
            final RefactorPair<ContractInput, ContractInput> pairRefactor) {
        scriptExpressionsSetToRefactor.addAll(allScriptWithReferencedElement(pairRefactor, container instanceof Pool));
    }

    private Collection<? extends ScriptContainer<?>> constraintExpressionsReferencing(final Contract contract, final ContractInput contractInput) {
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
        for (final Expression exp : filter(getAllElementOfTypeIn(container, Expression.class, container instanceof Pool),
                withExpressionType(ExpressionConstants.CONTRACT_INPUT_TYPE))) {
            for (final ContractInputRefactorPair pairToRefactor : filter(pairsToRefactor, matchingOldName(exp.getName()))) {
                final ContractInput newValue = pairToRefactor.getNewValue();
                cc.append(new UpdateExpressionCommand(getEditingDomain(), exp, newValue != null ? createContractInputExpression(newValue)
                        : createDefaultExpression(exp)));
            }
        }

    }

    private Expression createDefaultExpression(final Expression exp) {
        return ExpressionHelper.createConstantExpression("", exp.isReturnTypeFixed() ? exp.getReturnType() : String.class.getName());
    }

    private Predicate<ContractInputRefactorPair> matchingOldName(final String expressionName) {
        return new Predicate<ContractInputRefactorPair>() {

            @Override
            public boolean apply(final ContractInputRefactorPair refactorPair) {
                return refactorPair.getOldValueName().equals(expressionName);
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
