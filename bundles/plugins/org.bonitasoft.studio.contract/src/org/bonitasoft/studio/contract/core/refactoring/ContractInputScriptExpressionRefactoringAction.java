package org.bonitasoft.studio.contract.core.refactoring;

import static com.google.common.base.Predicates.instanceOf;
import static com.google.common.collect.Iterables.filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.refactoring.core.AbstractScriptExpressionRefactoringAction;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

import com.google.common.base.Predicate;

public class ContractInputScriptExpressionRefactoringAction extends
        AbstractScriptExpressionRefactoringAction<ContractInputRefactorPair> {

    public ContractInputScriptExpressionRefactoringAction(
            final List<ContractInputRefactorPair> pairsToRefactor,
            final List<Expression> scriptExpressions,
            final List<Expression> refactoredScriptExpression,
            final CompoundCommand compoundCommand, final EditingDomain domain,
            final RefactoringOperationType operationType) {
        super(pairsToRefactor, scriptExpressions, refactoredScriptExpression,
                compoundCommand, domain, operationType);
    }

    @Override
    protected Map<EObject, EObject> getReferencedObjectInScriptsOperation(
            final Expression expr) {
        final Map<EObject, EObject> res = new HashMap<EObject, EObject>();
        for (final EObject object : filter(expr.getReferencedElements(), instanceOf(ContractInput.class))) {
            for (final ContractInputRefactorPair pairToRefactor : filter(pairsToRefactor, matchingOldName((ContractInput) object))) {
                res.put(object, pairToRefactor.getNewValue());
            }
        }
        return res;
    }

    private Predicate<ContractInputRefactorPair> matchingOldName(final ContractInput input) {
        return new Predicate<ContractInputRefactorPair>() {

            @Override
            public boolean apply(final ContractInputRefactorPair refactorPair) {
                return refactorPair.getOldValueName().equals(input.getName());
            }
        };
    }
}
