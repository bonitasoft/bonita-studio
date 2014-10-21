/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.document.refactoring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.refactoring.core.AbstractScriptExpressionRefactoringAction;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;


public class DocumentScriptExpressionRefactoringAction extends AbstractScriptExpressionRefactoringAction<DocumentRefactorPair> {

    public DocumentScriptExpressionRefactoringAction(final List<DocumentRefactorPair> pairsToRefactor, final List<Expression> scriptExpressions,
            final List<Expression> refactoredScriptExpression, final CompoundCommand compoundCommand, final EditingDomain domain,
            final RefactoringOperationType operationType) {
        super(pairsToRefactor, scriptExpressions, refactoredScriptExpression, compoundCommand, domain, operationType);
    }

    @Override
    protected Map<EObject, EObject> getReferencedObjectInScriptsOperation(final Expression expr) {
        final Map<EObject, EObject> res = new HashMap<EObject, EObject>();
        for (final EObject object : expr.getReferencedElements()) {
            for (final DocumentRefactorPair pairToRefactor : pairsToRefactor) {
                if (object instanceof Document && ((Document) object).getName().equals(pairToRefactor.getOldValueName())) {
                    res.put(object, pairToRefactor.getNewValue());
                }
            }
        }
        return res;
    }

}
