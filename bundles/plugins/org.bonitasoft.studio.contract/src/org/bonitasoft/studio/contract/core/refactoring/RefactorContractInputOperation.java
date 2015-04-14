package org.bonitasoft.studio.contract.core.refactoring;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;
import static org.bonitasoft.studio.common.emf.tools.ExpressionHelper.createContractInputExpression;
import static org.bonitasoft.studio.common.emf.tools.ModelHelper.getAllElementOfTypeIn;
import static org.bonitasoft.studio.common.predicate.ExpressionPredicates.withExpressionType;
import static org.bonitasoft.studio.refactoring.core.groovy.ReferenceDiff.newReferenceDiff;

import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.refactoring.core.AbstractRefactorOperation;
import org.bonitasoft.studio.refactoring.core.AbstractScriptExpressionRefactoringAction;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.bonitasoft.studio.refactoring.core.groovy.GroovyScriptRefactoringOperation;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.google.common.base.Predicate;

public class RefactorContractInputOperation extends AbstractRefactorOperation<ContractInput, ContractInput, ContractInputRefactorPair> {

    private final ContractContainer container;

    public RefactorContractInputOperation(final ContractContainer container, final RefactoringOperationType operationType) {
        super(operationType);
        this.container = container;
    }

    @Override
    protected CompoundCommand doBuildCompoundCommand(final CompoundCommand cc,
            final IProgressMonitor monitor) {
        updateContractInputExpressions(cc);
        updateContractInputReferenceInConstraints(cc);
        return cc;
    }

    @Override
    protected AbstractScriptExpressionRefactoringAction<ContractInputRefactorPair> getScriptExpressionRefactoringAction(
            final List<ContractInputRefactorPair> pairsToRefactor,
            final List<Expression> scriptExpressions,
            final List<Expression> refactoredScriptExpression,
            final CompoundCommand compoundCommand, final EditingDomain domain,
            final RefactoringOperationType operationType) {
        return new ContractInputScriptExpressionRefactoringAction(pairsToRefactor, scriptExpressions, refactoredScriptExpression, compoundCommand, domain,
                operationType);
    }

    private void updateContractInputExpressions(final CompoundCommand cc) {
        for (final Expression exp : filter(getAllElementOfTypeIn(container, Expression.class), withExpressionType(ExpressionConstants.CONTRACT_INPUT_TYPE))) {
            for (final ContractInputRefactorPair pairToRefactor : filter(pairsToRefactor, matchingOldName(exp.getName()))) {
                cc.append(new UpdateExpressionCommand(getEditingDomain(), exp, createContractInputExpression(pairToRefactor.getNewValue())));
            }
        }

    }

    private Predicate<ContractInputRefactorPair> matchingOldName(final String expressionName) {
        return new Predicate<ContractInputRefactorPair>() {

            @Override
            public boolean apply(final ContractInputRefactorPair refactorPair) {
                return refactorPair.getOldValueName().equals(expressionName);
            }
        };
    }

    private void updateContractInputReferenceInConstraints(final CompoundCommand cc) {
        for (final ContractInputRefactorPair refactorPair : pairsToRefactor) {
            for (final ContractConstraint constraint : filter(container.getContract().getConstraints(), constraintWithInputName(refactorPair.getOldValueName()))) {
                cc.append(new RenameContractConstraintInputNameCommand(getEditingDomain(), constraint, createGroovyScriptRefactoringOperation(constraint,
                        refactorPair)));
            }
        }
    }

    protected GroovyScriptRefactoringOperation createGroovyScriptRefactoringOperation(final ContractConstraint constraint,
            final ContractInputRefactorPair refactorPair) {
        return new GroovyScriptRefactoringOperation(constraint.getExpression(), newArrayList(newReferenceDiff(
                refactorPair.getOldValueName(), refactorPair.getNewValueName())));
    }

    private Predicate<ContractConstraint> constraintWithInputName(final String inputName) {
        return new Predicate<ContractConstraint>() {

            @Override
            public boolean apply(final ContractConstraint input) {
                return input.getInputNames().contains(inputName);
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
